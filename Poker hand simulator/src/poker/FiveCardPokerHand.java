package poker;

import card.Card;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a standard five card poker hand with cards from a 52 card deck.
 *
 * @author Sebastian Björkqvist
 */
public class FiveCardPokerHand {

    private List<Card> cards;

    /**
     * Creates a new empty five card hand.
     */
    public FiveCardPokerHand() {
        cards = new ArrayList<Card>();
    }
    
    /**
     * Creates a hand and adds the given cards to the hand.
     * 
     * @param cardsToAdd 
     * @throws IllegalArgumentException if list is null
     * @throws IllegalArgumentException if the same card is in the list more than once.
     */
    public FiveCardPokerHand(List<Card> cardsToAdd) {
        this();
        if (cardsToAdd == null) {
            throw new IllegalArgumentException("List is null");
        }
        for (Card card : cardsToAdd) {
            addCard(card);
        }
    }

    /**
     * Adds a card to the hand.
     *
     * The hand doesn't accept any new cards if it is full, i.e. if it already
     * contains five cards.
     *
     * Null-cards can't be added to a hand.
     *
     * @param card A Card-object
     * @return true if the card was added, false if it wasn't.
     * @throws IllegalArgumentException if the hand already contains the card to
     * be added.
     */
    public final boolean addCard(Card card) {
        if (card == null) {
            return false;
        }
        if (cards.contains(card)) {
            throw new IllegalArgumentException("The hand already contains the card " + card + "!");
        }
        if (isFull()) {
            return false;
        }

        cards.add(card);
        return true;
    }

    /**
     * Retrieves a copy of the list of cards that the hand contains.
     *
     * @return List<Card> A list of the cards in the hand. Never null.
     */
    public List<Card> getCards() {
        return new ArrayList<Card>(cards);
    }

    /**
     * Returns the number of cards in the hand.
     *
     * @return The number of cards in the hand
     */
    public int getNumberOfCards() {
        return cards.size();
    }

    /**
     * Tells if the hand is full.
     *
     * The hand is full if it has five cards, and is not full if it has less.
     * Cards can't be added to a full hand.
     *
     * @return true if the hand is full, false otherwise.
     */
    public boolean isFull() {
        return cards.size() >= 5;
    }
}
