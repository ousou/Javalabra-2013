package logic.simulator;

import card.Card;
import java.util.ArrayList;
import java.util.List;
import logic.math.Combination;
import poker.startinghands.AbstractStartingHand;
import poker.FiveCardBoard;
import poker.FiveCardPokerHand;
import poker.enums.PokerGameType;

/**
 * Creates all possible hands by combining cards from the starting hand with the
 * board cards.
 *
 * Here both the board and the starting hand must be full.
 *
 * @author Sebastian Björkqvist
 */
public class PossibleHandsCreator {

    private AbstractStartingHand startingHand;
    private FiveCardBoard board;

    /**
     * Creates a new PossibleHandCreator.
     *
     * This constructor is to be used when creating possible hands for community
     * card games.
     *
     * @param startingHand Starting hand
     * @param board Board
     * @throws IllegalArgumentException if startingHand or board is null.
     * @throws IllegalArgumentException if startingHand or board isn't full.
     */
    public PossibleHandsCreator(AbstractStartingHand startingHand, FiveCardBoard board) {
        if (startingHand == null) {
            throw new IllegalArgumentException("The starting hand is null");
        }
        if (board == null) {
            throw new IllegalArgumentException("The board is null");
        }
        if (!startingHand.isFull()) {
            throw new IllegalArgumentException("Starting hand isn't full");
        }
        if (!board.isFull()) {
            throw new IllegalArgumentException("Board isn't full");
        }
        this.startingHand = startingHand;
        this.board = board;
    }

    /**
     * Creates a new PossibleHandCreator without a board.
     *
     * This constructor should be used when creating possible hands for
     * non-community card games.
     *
     * @param startingHand Starting hand
     */
    public PossibleHandsCreator(AbstractStartingHand startingHand) {
        if (startingHand == null) {
            throw new IllegalArgumentException("The starting hand is null");
        }
        if (!startingHand.isFull()) {
            throw new IllegalArgumentException("Starting hand isn't full");
        }
        this.startingHand = startingHand;
        this.board = new FiveCardBoard();
    }

    /**
     * Creates all the possible five card poker hands with the given starting
     * hand and board.
     *
     * @return List of five card poker hands.
     */
    public List<FiveCardPokerHand> createAllPossibleHands() {
        int minimumAmountOfCardsUsed = startingHand.getMinimumAmountOfCardsUsed();
        int maximumAmountOfCardsUsed = startingHand.getMaximumAmountOfCardsUsed();

        List<FiveCardPokerHand> allPossibleHands = new ArrayList<FiveCardPokerHand>();
        List<Card> startingHandCards = startingHand.getCards();
        List<Card> boardCards = board.getCards();

        if (minimumAmountOfCardsUsed == 0) {
            allPossibleHands.add(new FiveCardPokerHand(boardCards));
            minimumAmountOfCardsUsed++;
        }

        for (int i = minimumAmountOfCardsUsed; i <= maximumAmountOfCardsUsed; i++) {
            List<List<Card>> combinationsOfStartingHandCards = Combination.takeKFromList(startingHandCards, i);
            List<List<Card>> combinationsOfBoardCards = Combination.takeKFromList(boardCards, 5 - i);

            for (List<Card> combinationHand : combinationsOfStartingHandCards) {                
                if (!combinationsOfBoardCards.isEmpty()) {
                    // Community card games
                    for (List<Card> combinationBoard : combinationsOfBoardCards) {
                        List<Card> fiveCards = new ArrayList<Card>();
                        fiveCards.addAll(combinationHand);
                        fiveCards.addAll(combinationBoard);
                        allPossibleHands.add(new FiveCardPokerHand(fiveCards));
                    }
                } else {
                    // Non-community card games
                    allPossibleHands.add(new FiveCardPokerHand(combinationHand));
                }
            }
        }

        return allPossibleHands;
    }
}
