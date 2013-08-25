package card;

/**
 * Interface for a card deck using the class Card.
 * 
 * @author Sebastian Björkqvist
 */
public interface ICardDeck {

    /**
     * Retrieves and removes the top card of the deck.
     * 
     * @return The card retrieved, or null if deck is empty.
     */
    Card takeCard();
    
    /**
     * Shuffles the deck.
     */    
    void shuffle();
    
    /**
     * Returns the number of cards remaining in the deck.
     * 
     * @return The number of remaining cards.
     */
    int getNumberOfRemainingCards();
    
    /**
     * Tells if there are any cards remaining in the deck.
     * 
     * @return true if the deck is empty, false otherwise
     */    
    boolean isEmpty();
}
