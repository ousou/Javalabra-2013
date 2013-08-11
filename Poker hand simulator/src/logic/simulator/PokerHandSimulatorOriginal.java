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
 * The class supports only community card games at the moment.
 * 
 * This class does extra work by sorting all the possible hands created
 * during the simulation, when it would be enough to find the best five card hand
 * for each starting hand. Other implementations of IPokerHandSimulator do less
 * work and are faster.
 * 
 * @todo Add support for non-community card games.
 *
 * @author Sebastian Björkqvist
 */
public class PokerHandSimulatorOriginal implements IPokerHandSimulator {

    private final List<AbstractStartingHand> startingHands;
    private FiveCardBoard board;
    private List<Card> removedCards;
    private int numberOfSimulations;
    private PokerGameType gameType;

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
    public PokerHandSimulatorOriginal(List<AbstractStartingHand> startingHands, List<Card> boardCards, int numberOfSimulations) {
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
    public PokerHandSimulatorOriginal(List<AbstractStartingHand> startingHands, int numberOfSimulations) {
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

    @Override
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
        
        FiveCardBoard simulatedBoard = copyOfBoard();
        
        while (!simulatedBoard.isFull()) {
            Card nextCard = deck.getCard();
            if (nextCard == null) {
                throw new RuntimeException("The deck ran out of cards!");
            }
            simulatedBoard.addCard(nextCard);
        }

        Map<FiveCardPokerHand, List<AbstractStartingHand>> bestFiveCardHandForThisStartingHand = new HashMap<FiveCardPokerHand, List<AbstractStartingHand>>();
        FiveCardPokerHandComparator handComparator = new FiveCardPokerHandComparator();
        List<FiveCardPokerHand> allBestHands = new ArrayList<FiveCardPokerHand>();
        
        createBestHands(handComparator, allBestHands, bestFiveCardHandForThisStartingHand, simulatedBoard);
        
        // Determining the winning hand by sorting the list of best hands.
        Collections.sort(allBestHands, handComparator);

        // Adding the index or indices of the best hand to the set.
        winningHands.addAll(bestFiveCardHandForThisStartingHand.get(allBestHands.get(0)));
        
        // Checking if other hands tie with this hand, and if so, we add them to the set also
        int nextIndex = 1;
        while (nextIndex < allBestHands.size() && 
                handComparator.compare(allBestHands.get(0), allBestHands.get(nextIndex)) == 0) {
            winningHands.addAll(bestFiveCardHandForThisStartingHand.get(allBestHands.get(nextIndex)));
            nextIndex++;
        }        
        
        return winningHands;
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
     * Determines the best possible hand for the startingHands in this simulation.
     * 
     * @param handComparator FiveCardPokerHandComparator
     * @param allBestHands All best hands are added to this list
     * @param bestFiveCardHandForStartingHand Maps five card hands
     * to starting hands for which the five card hand hand is the best hand.
     */
    private void createBestHands(FiveCardPokerHandComparator handComparator, 
            List<FiveCardPokerHand> allBestHands, Map<FiveCardPokerHand, 
            List<AbstractStartingHand>> bestFiveCardHandForStartingHand,
            FiveCardBoard simulatedBoard) {
        /* Creating all possible hands for each starting hand, and determining the best possible
         * hand each starting hand can form.
         */
        
        for (int i = 0; i < startingHands.size(); i++) {
            PossibleHandsCreator handCreator = new PossibleHandsCreator(startingHands.get(i), simulatedBoard);
            List<FiveCardPokerHand> allHands = handCreator.createAllPossibleHands();
            Collections.sort(allHands, handComparator);
            if (!allHands.isEmpty()) {
                FiveCardPokerHand bestHand = allHands.get(0);
                allBestHands.add(bestHand);
                if (!bestFiveCardHandForStartingHand.containsKey(bestHand)) {
                    bestFiveCardHandForStartingHand.put(bestHand, new ArrayList<AbstractStartingHand>());
                }
                bestFiveCardHandForStartingHand.get(bestHand).add(startingHands.get(i));
            }
        }
    }

    /**
     * Copies the boardcards given in the constructor
     * 
     * @return Copied board.
     */
    private FiveCardBoard copyOfBoard() {
        List<Card> copyOfBoardCards = board.getCards();
        FiveCardBoard simulatedBoard = new FiveCardBoard();
        for (Card card: copyOfBoardCards) {
            simulatedBoard.addCard(card);
        }
        return simulatedBoard;
    }
}