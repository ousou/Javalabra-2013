package logic.simulator;

import card.Card;
import card.CardDeckStandard;
import card.ICardDeck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import poker.startinghands.AbstractStartingHand;
import poker.FiveCardBoard;
import poker.FiveCardPokerHand;
import poker.comparators.FiveCardPokerHandComparator;
import poker.enums.PokerGameType;

/**
 * Simulates the result when pitting poker hands against each other.
 *
 * Implementations of this class must implement the createBestHands-method.
 * There the implementation can specify how the best hands for a starting hand
 * are to be found.
 *
 * @author Sebastian Björkqvist
 */
public abstract class AbstractPokerHandSimulator {

    /**
     * List containing all starting hands.
     */
    protected final List<AbstractStartingHand> startingHands;
    protected FiveCardBoard board;
    /**
     * List containing cards that are removed from the deck
     * when each simulation starts.
     */
    protected List<Card> removedCards;
    protected int numberOfSimulations;
    protected PokerGameType gameType;
    protected FiveCardPokerHandComparator fiveCardPokerHandComparator;

    /**
     * Creates a new hand simulator with the given starting hands and board
     * cards.
     *
     * @param startingHands List of starting hands
     * @param boardCards List of board cards.
     * @param numberOfSimulations Number of simulations.
     * @throws IllegalArgumentException if numberOfSimulations isn't positive
     * @throws IllegalArgumentException if there are less than two
     * startingHands.
     */
    public AbstractPokerHandSimulator(List<AbstractStartingHand> startingHands, List<Card> boardCards, int numberOfSimulations) {
        this(startingHands, numberOfSimulations);
        for (int i = 0; i < boardCards.size(); i++) {
            board.addCard(boardCards.get(i));
            removedCards.add(boardCards.get(i));
        }
        verifyHandsAndBoard();
    }

    /**
     * Creates a new hand simulator without any board cards.
     *
     * The board will be used in the simulation if the given starting hands have
     * a game type with uses community cards.
     *
     * @param startingHands List of starting hands
     * @param numberOfSimulations Number of simulations.
     * @throws IllegalArgumentException if numberOfSimulations isn't positive
     * @throws IllegalArgumentException if there are less than two
     * startingHands.
     * @throws IllegalArgumentException if hands or the board have overlapping
     * cards.
     */
    public AbstractPokerHandSimulator(List<AbstractStartingHand> startingHands, int numberOfSimulations) {
        if (numberOfSimulations < 1) {
            throw new IllegalArgumentException("Number of simulations must be positive!");
        }
        if (startingHands == null || startingHands.size() < 2) {
            throw new IllegalArgumentException("There must be atleast two starting hands.");
        }
        this.board = new FiveCardBoard();
        this.numberOfSimulations = numberOfSimulations;
        this.startingHands = new ArrayList<AbstractStartingHand>(startingHands);
        this.removedCards = new ArrayList<Card>();
        this.fiveCardPokerHandComparator = new FiveCardPokerHandComparator();
        addCardsFromStartingHandsToRemovedCards();
        verifyHandsAndBoard();
    }

    /**
     * Verifies that the starting hands and the board do not have overlapping
     * cards.
     *
     * @throws IllegalArgumentException if overlapping cards are found.
     */
    private void verifyHandsAndBoard() {
        Set<Card> allCards = new HashSet<Card>();
        int totalNumberOfCards = 0;

        gameType = startingHands.get(0).getGameType();
        for (AbstractStartingHand hand : startingHands) {
            if (gameType != hand.getGameType()) {
                throw new IllegalArgumentException("Starting hands do not have the same game type");
            }
            List<Card> cards = hand.getCards();
            allCards.addAll(cards);
            totalNumberOfCards += cards.size();
        }
        List<Card> boardCards = board.getCards();
        allCards.addAll(boardCards);
        totalNumberOfCards += boardCards.size();

        if (allCards.size() != totalNumberOfCards) {
            throw new IllegalArgumentException("The hands and the board have overlapping cards!");
        }
    }

    /**
     * Adding all cards from the starting hands to the list removedCards.
     *
     * This is done so that we don't retrieve these cards from the deck when
     * simulating.
     *
     * If any board cards are given as input, they are added to this list too in
     * the constructor.
     */
    private void addCardsFromStartingHandsToRemovedCards() {
        for (AbstractStartingHand hand : startingHands) {
            removedCards.addAll(hand.getCards());
        }
    }

    /**
     * Performs the desired simulation.
     *
     * The class will simulate the result the amount of times requested in the
     * constructor.
     *
     * This method does not use threads.
     *
     * @return SimulationResult-object containing the results of the simulation.
     */
    public SimulationResult performSimulation() {
        SimulationResult simulationResult;
        Set<AbstractStartingHand> handSet = new HashSet<AbstractStartingHand>(startingHands);
        if (gameType.isCommunityCardGame()) {
            simulationResult = new SimulationResult(handSet, board, numberOfSimulations, gameType);
        } else {
            simulationResult = new SimulationResult(handSet, numberOfSimulations, gameType);
        }

        for (int i = 0; i < numberOfSimulations; i++) {
            Set<AbstractStartingHand> result = simulateHand();
            simulationResult.addResultForOneSimulation(result);
        }

        return simulationResult;
    }

    /**
     * Performs the desired simulation.
     *
     * The class will simulate the result the amount of times requested in the
     * constructor.
     *
     * This method uses parallel threads to perform the simulations.
     *
     * @param numberOfThreads Number of threads to use.
     * @return SimulationResult-object containing the results of the simulation.
     * @throws IllegalArgumentException if the number of threads is non-positive
     * and larger than 16.
     * @throws InterruptedException if the threads are interrupted.
     */
    public SimulationResult performSimulation(int numberOfThreads) throws InterruptedException {
        if (numberOfThreads <= 0) {
            throw new IllegalArgumentException("Number of threads must be positive.");
        }
        if (numberOfThreads > 16) {
            throw new IllegalArgumentException("A maximum of 16 threads are allowed.");
        }
        if (numberOfThreads == 1) {
            return performSimulation();
        }

        SimulationResult simulationResult;
        Set<AbstractStartingHand> handSet = new HashSet<AbstractStartingHand>(startingHands);

        if (gameType.isCommunityCardGame()) {
            simulationResult = new SimulationResult(handSet, board, numberOfSimulations, gameType);
        } else {
            simulationResult = new SimulationResult(handSet, numberOfSimulations, gameType);
        }
        int simulationsPerThread = numberOfSimulations / numberOfThreads;
        int remainder = numberOfSimulations - (numberOfThreads * simulationsPerThread);

        List<HandSimulationThread> threadList = new ArrayList<HandSimulationThread>();

        HandSimulationThread threadToAdd = new HandSimulationThread(this, simulationResult, simulationsPerThread + remainder);
        threadList.add(threadToAdd);

        for (int i = 1; i < numberOfThreads; i++) {
            threadToAdd = new HandSimulationThread(this, simulationResult, simulationsPerThread);
            threadList.add(threadToAdd);
        }

        for (HandSimulationThread thread : threadList) {
            thread.start();
        }

        for (HandSimulationThread thread : threadList) {
            thread.join();
        }

        return simulationResult;

    }

    /**
     * Simulates a hand.
     *
     * If there isn't a tie for the best hand, the list only contains one
     * element: the index of the winning hand in the startingHands-list.
     *
     * If there's a tie, the list contains the indices for the winning hands.
     *
     * @return set containing the winning hand(s).
     */
    protected Set<AbstractStartingHand> simulateHand() {
        Set<AbstractStartingHand> winningHands = new HashSet<AbstractStartingHand>();
        ICardDeck deck = new CardDeckStandard(removedCards);

        FiveCardBoard simulatedBoard;
        List<AbstractStartingHand> filledStartingHands = fillStartingHands(deck);

        if (!gameType.isCommunityCardGame()) {
            simulatedBoard = null;
        } else {
            simulatedBoard = simulateBoard(deck);
        }

        Map<FiveCardPokerHand, List<AbstractStartingHand>> bestFiveCardHandForThisStartingHand = new HashMap<FiveCardPokerHand, List<AbstractStartingHand>>();
        List<FiveCardPokerHand> allBestHands = new ArrayList<FiveCardPokerHand>();

        createBestHands(allBestHands, bestFiveCardHandForThisStartingHand, simulatedBoard, filledStartingHands);

        // Determining the winning hand by sorting the list of best hands.
        Collections.sort(allBestHands, fiveCardPokerHandComparator);

        // Adding the index or indices of the best hand to the set.
        winningHands.addAll(bestFiveCardHandForThisStartingHand.get(allBestHands.get(0)));

        // Checking if other hands tie with this hand, and if so, we add them to the set also
        int nextIndex = 1;
        while (nextIndex < allBestHands.size()
                && fiveCardPokerHandComparator.compare(allBestHands.get(0), allBestHands.get(nextIndex)) == 0) {
            winningHands.addAll(bestFiveCardHandForThisStartingHand.get(allBestHands.get(nextIndex)));
            nextIndex++;
        }

        return winningHands;
    }

    /**
     * Copies the boardcards given in the constructor
     *
     * @return Copied board.
     */
    protected FiveCardBoard copyOfBoard() {
        List<Card> copyOfBoardCards = board.getCards();
        FiveCardBoard simulatedBoard = new FiveCardBoard();
        for (Card card : copyOfBoardCards) {
            simulatedBoard.addCard(card);
        }
        return simulatedBoard;
    }

    /**
     * Determines the best possible hand for the startingHands in this
     * simulation.
     *
     * @param allBestHands All best hands are added to this list
     * @param bestFiveCardHandForStartingHand Maps five card hands to starting
     * hands for which the five card hand hand is the best hand.
     * @param simulatedBoard A full simulated board
     * @param simulatedHands Filled starting hands.
     */
    protected abstract void createBestHands(List<FiveCardPokerHand> allBestHands, Map<FiveCardPokerHand, List<AbstractStartingHand>> bestFiveCardHandForStartingHand, 
            FiveCardBoard simulatedBoard, List<AbstractStartingHand> simulatedHands);

    /**
     * Simulates the board.
     *
     * @param deck Card deck
     * @return Simulated full board.
     * @throws RuntimeException If the deck runs out of cards.
     */
    protected FiveCardBoard simulateBoard(ICardDeck deck) throws RuntimeException {
        FiveCardBoard simulatedBoard = copyOfBoard();
        while (!simulatedBoard.isFull()) {
            Card nextCard = deck.takeCard();
            if (nextCard == null) {
                throw new RuntimeException("The deck ran out of cards!");
            }
            simulatedBoard.addCard(nextCard);
        }
        return simulatedBoard;
    }

    /**
     * Creates copies of starting hands and fills them.
     * 
     * The filled copy of a hand is found at the same place in the startinghands-list
     * and in the returned list.
     *
     * @return List of AbstractStartingHand-objects with the same game type and cards
     * as the hands in the startingHands-list.
     */
    protected List<AbstractStartingHand> fillStartingHands(ICardDeck deck) {
        List<AbstractStartingHand> filledStartingHands = new ArrayList<AbstractStartingHand>();
        for (int i = 0; i < startingHands.size(); i++) {
            AbstractStartingHand copyOfHand = startingHands.get(i).copyOfHand();
            while (!copyOfHand.isFull()) {
                Card nextCard = deck.takeCard();
                if (nextCard == null) {
                    throw new RuntimeException("The deck ran out of cards!");
                }
                copyOfHand.addCard(nextCard);
            }
            filledStartingHands.add(copyOfHand);
        }

        return filledStartingHands;
    }

    /**
     * Class enabling parallel simulations.
     */
    private static class HandSimulationThread extends Thread {

        private AbstractPokerHandSimulator simulator;
        private SimulationResult simulationResult;
        private int timesToRun;

        public HandSimulationThread(AbstractPokerHandSimulator simulator, SimulationResult simulationResult, int timesToRun) {
            this.simulator = simulator;
            this.simulationResult = simulationResult;
            this.timesToRun = timesToRun;
        }

        @Override
        public void run() {
            for (int i = 0; i < timesToRun; i++) {
                Set<AbstractStartingHand> result = simulator.simulateHand();
                simulationResult.addResultForOneSimulation(result);
            }
        }
    }
}
