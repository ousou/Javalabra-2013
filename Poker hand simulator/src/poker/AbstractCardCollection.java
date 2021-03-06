package poker;

import card.Card;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Abstract collection of cards.
 * 
 * @author Sebastian Björkqvist
 */
public abstract class AbstractCardCollection implements Serializable {

    private final Set<Card> cards;
    private final int maximumAmountOfCardsInCollection;    

    /**
     * Creates a new AbstractCardCollection
     * 
     * @param maximumAmountOfCardsInCollection 
     * @throws IllegalArgumentException if maximumAmountOfCardsInCollection isn't positive.
     */
    public AbstractCardCollection(int maximumAmountOfCardsInCollection) {
        if (maximumAmountOfCardsInCollection <= 0) {
            throw new IllegalArgumentException("Maximum amount of cards in collection must be positive");
        }
        this.cards = new HashSet<Card>();
        this.maximumAmountOfCardsInCollection = maximumAmountOfCardsInCollection;
    }

    /**
     * Adds a card to the collection.
     *
     * Doesn't add a card and returns false if the collection is full.
     *
     * @param card
     * @return true if card is added, false otherwise.
     * @throws IllegalArgumentException if the collection already contains the card
     */
    public boolean addCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Null card can't be added to hand");
        }
        if (cards.contains(card)) {
            throw new IllegalArgumentException("Hand already contains card " + card);
        }
        if (isFull()) {
            return false;
        }
        cards.add(card);
        return true;
    }
    
    /**
     * Adds a collection of cards to the hand.
     * 
     * @param cards Collection of cards.
     * @return true if all cards were added.
     * @throws IllegalArgumentException if cards is null.
     */
    public boolean addCards(Collection<Card> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("Collection can't be null");
        }
        boolean added = true;
        for (Card card : cards) {
            added = addCard(card);
        }
        return added;
    }

    /**
     * Returns the number of cards that the collection can hold.
     *
     * @return The number of cards the collection can hold
     */
    public int getMaximumAmountOfCardsInCollection() {
        return maximumAmountOfCardsInCollection;
    }

    /**
     * Tells if the collection is full.
     *
     * @return true if the collection is full, false otherwise.
     */
    public boolean isFull() {
        return cards.size() >= maximumAmountOfCardsInCollection;
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
     * Returns a copy of the list of cards that the collection contains.
     *
     * @return List of cards
     */
    public List<Card> getCards() {
        return new ArrayList<Card>(cards);
    }
    
    /**
     * Retrieves copy of the set of cards.
     * 
     * @return Set of cards
     */
    protected Set<Card> getCardSet() {
        return new HashSet<Card>(cards);
    }
    
    /**
     * Removes all cards from this collection.
     */
    public void removeAllCards() {
        cards.clear();
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
