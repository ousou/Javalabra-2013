package poker;

import card.Card;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract collection of cards.
 * 
 * @author Sebastian Björkqvist
 */
public abstract class AbstractCardCollection {

    private List<Card> cards;
    private final int maximumAmountOfCardsInCollection;    

    public AbstractCardCollection(int maximumAmountOfCardsInCollection) {
        this.cards = new ArrayList<Card>();
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
     * Returns a copy of the list of cards that the collection contains.
     *
     * @return List of cards
     */
    public List<Card> getCards() {
        return new ArrayList<Card>(cards);
    }
    
    
}
