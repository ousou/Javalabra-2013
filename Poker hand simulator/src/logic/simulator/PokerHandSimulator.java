package logic.simulator;

import card.Card;
import card.CardDeckStandard;
import card.ICardDeck;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import poker.AbstractStartingHand;
import poker.FiveCardBoard;

/**
 * Simulates the result when pitting poker hands against each other.
 *
 * The class supports only community card games at the moment.
 *
 * @author Sebastian Björkqvist
 */
public class PokerHandSimulator {

    private final List<AbstractStartingHand> startingHands;
    private final FiveCardBoard board;
    private boolean useBoard;
    private int numberOfSimulations;

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
    public PokerHandSimulator(List<AbstractStartingHand> startingHands, List<Card> boardCards, int numberOfSimulations) {
        this(startingHands, numberOfSimulations);
        this.useBoard = true;
        for (int i = 0; i < boardCards.size(); i++) {
            board.addCard(boardCards.get(i));
        }
        verifyHandsAndBoard();
    }

    /**
     * Creates a new hand simulator without any board cards.
     *
     * If useBoard is true, a board is present in the game. If false, there is
     * no board, i.e. the game is not a community card game.
     *
     * @param startingHands List of starting hands
     * @param useBoard If true, game is a community card game.
     * @param numberOfSimulations Number of simulations.
     * @throws IllegalArgumentException if numberOfSimulations isn't positive
     * @throws IllegalArgumentException if there are less than two
     * startingHands.
     * @throws IllegalArgumentException if hands or the board have overlapping cards,
     * or if some of the starting hands aren't full.
     */
    public PokerHandSimulator(List<AbstractStartingHand> startingHands, boolean useBoard, int numberOfSimulations) {
        this(startingHands, numberOfSimulations);
        this.useBoard = useBoard;
        verifyHandsAndBoard();        
    }

    /**
     * Private constructor.
     *
     * Used to avoid copy-pasting.
     *
     * @param startingHands
     * @param numberOfSimulations
     */
    private PokerHandSimulator(List<AbstractStartingHand> startingHands, int numberOfSimulations) {
        if (numberOfSimulations < 1) {
            throw new IllegalArgumentException("Number of simulations must be positive!");
        }
        if (startingHands == null || startingHands.size() < 2) {
            throw new IllegalArgumentException("There must be atleast two starting hands.");
        }
        this.board = new FiveCardBoard();
        this.numberOfSimulations = numberOfSimulations;        
        this.startingHands = startingHands;
    }   

    private void verifyHandsAndBoard() {
        Set<Card> allCards = new HashSet<Card>();
        int totalNumberOfCards = 0;
        
        for (AbstractStartingHand hand : startingHands) {
            if (!hand.isFull()) {
                throw new IllegalArgumentException("One of the hands isn't full!");
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
     * Simulates a hand.
     *
     * If there isn't a tie for the best hand, the list only contains one
     * element: the index of the winning hand in the startingHands-list.
     *
     * If there's a tie, the list contains the indices for the winning hands.
     *
     * @return the indices of the winning hand(s) in the startingHands-list.
     */
    private List<Integer> simulateHand() {
        ICardDeck deck = new CardDeckStandard();

        if (!useBoard) {
            throw new UnsupportedOperationException("Non-community card games not supported yet.");
        }
        while (!board.isFull()) {
            Card nextCard = deck.getCard();
            if (nextCard == null) {
                throw new RuntimeException("The deck ran out of cards!");
            }
            board.addCard(nextCard);
        }
        
        


        return null;
    }
}
