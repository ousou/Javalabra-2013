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
import poker.AbstractStartingHand;
import poker.FiveCardBoard;
import poker.FiveCardPokerHand;
import poker.comparators.FiveCardPokerHandComparator;
import poker.enums.PokerGameType;

/**
 * Simulates the result when pitting poker hands against each other.
 * 
 * Implementations of this class must implement the createBestHands-method.
 * There the implementation can specify how the best hands for a
 * starting hand are to be found.
 * 
 * @author Sebastian Björkqvist
 */
public abstract class AbstractPokerHandSimulator {

    protected final List<AbstractStartingHand> startingHands;
    protected FiveCardBoard board;
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
     * The board will be used in the simulation if the given starting hands
     * have a game type with uses community cards.
     * 
     * @param startingHands List of starting hands
     * @param numberOfSimulations Number of simulations.
     * @throws IllegalArgumentException if numberOfSimulations isn't positive
     * @throws IllegalArgumentException if there are less than two
     * startingHands.
     * @throws IllegalArgumentException if hands or the board have overlapping
     * cards, or if some of the starting hands aren't full.
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
     * Verifies that the starting hands and the board do not have overlapping cards.
     * 
     * @throws IllegalArgumentException if overlapping cards are found.
     */
    private void verifyHandsAndBoard() {
        Set<Card> allCards = new HashSet<Card>();
        int totalNumberOfCards = 0;

        gameType = startingHands.get(0).getGameType();
        for (AbstractStartingHand hand : startingHands) {
            if (!hand.isFull()) {
                throw new IllegalArgumentException("One of the hands isn't full!");
            }
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
     * This is done so that we don't retrieve these cards from the deck
     * when simulating.
     * 
     * If any board cards are given as input, they are added to this list too
     * in the constructor.
     */
    private void addCardsFromStartingHandsToRemovedCards() {
        for (AbstractStartingHand hand : startingHands) {
            removedCards.addAll(hand.getCards());
        }
    }
    /**
     * Performs the desired simulation.
     * 
     * The class will simulate the result the amount of times
     * requested in the constructor.
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

        if (!gameType.isCommunityCardGame()) {
            throw new UnsupportedOperationException("Non-community card games not supported yet.");
        }
        FiveCardBoard simulatedBoard = simulateBoard(deck);

        Map<FiveCardPokerHand, List<AbstractStartingHand>> bestFiveCardHandForThisStartingHand = new HashMap<FiveCardPokerHand, List<AbstractStartingHand>>();
        List<FiveCardPokerHand> allBestHands = new ArrayList<FiveCardPokerHand>();
        
        createBestHands(allBestHands, bestFiveCardHandForThisStartingHand, simulatedBoard);
        
        // Determining the winning hand by sorting the list of best hands.
        Collections.sort(allBestHands, fiveCardPokerHandComparator);

        // Adding the index or indices of the best hand to the set.
        winningHands.addAll(bestFiveCardHandForThisStartingHand.get(allBestHands.get(0)));
        
        // Checking if other hands tie with this hand, and if so, we add them to the set also
        int nextIndex = 1;
        while (nextIndex < allBestHands.size() && 
                fiveCardPokerHandComparator.compare(allBestHands.get(0), allBestHands.get(nextIndex)) == 0) {
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
     * Determines the best possible hand for the startingHands in this simulation.
     *
     * @param allBestHands All best hands are added to this list
     * @param bestFiveCardHandForStartingHand Maps five card hands
     * to starting hands for which the five card hand hand is the best hand.
     * @param simulatedBoard A full simulated board
     */
    protected abstract void createBestHands(List<FiveCardPokerHand> allBestHands, Map<FiveCardPokerHand, List<AbstractStartingHand>> bestFiveCardHandForStartingHand, FiveCardBoard simulatedBoard);

    /**
     * Simulates the board.
     * 
     * @param deck Carddeck
     * @return Simulated full board.
     * @throws RuntimeException If the deck runs out of cards.
     */
    protected FiveCardBoard simulateBoard(ICardDeck deck) throws RuntimeException {
        FiveCardBoard simulatedBoard = copyOfBoard();
        while (!simulatedBoard.isFull()) {
            Card nextCard = deck.getCard();
            if (nextCard == null) {
                throw new RuntimeException("The deck ran out of cards!");
            }
            simulatedBoard.addCard(nextCard);
        }
        return simulatedBoard;
    }
}
