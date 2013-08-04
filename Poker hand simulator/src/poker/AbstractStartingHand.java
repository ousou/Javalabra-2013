package poker;

import card.Card;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sebastian Björkqvist
 */
public abstract class AbstractStartingHand {
    
    private List<Card> cards;
    private final int maximumAmountOfCardsInHand;
    private final int minimumAmountOfCardsUsed;
    private final int maximumAmountOfCardsUsed;

    public AbstractStartingHand(int maximumAmountOfCardsInHand, int minimumAmountOfCardsUsed, int maximumAmountOfCardsUsed) {
        this.cards = new ArrayList<Card>();
        this.maximumAmountOfCardsInHand = maximumAmountOfCardsInHand;
        this.minimumAmountOfCardsUsed = minimumAmountOfCardsUsed;
        this.maximumAmountOfCardsUsed = maximumAmountOfCardsUsed;
    }        
    
    /**
     * Adds a card to the hand.
     * 
     * Returns false if the hand is full and no card is added.
     * 
     * @param card
     * @return true if card is added, false otherwise.
     * @throws IllegalArgumentException if the hand already contains the card
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
     * Minimum amount of cards that have to be used to create a
     * five card poker hand using this starting hand and community cards.
     * 
     * @return The minimum number of cards to be used
     */
    public int getMinimumAmountOfCardsUsed() {
        return minimumAmountOfCardsUsed;
    }
    
    /**
     * Maximum amount of cards that can be used to create a
     * five card poker hand using this starting hand and community cards.
     * 
     * @return The maximum number of cards to be used
     */    
    public int getMaximumAmountOfCardsUsed() {
        return maximumAmountOfCardsUsed;        
    }
    
    /**
     * Returns the number of cards that the hand can hold.
     * 
     * @return The number of cards the hand can hold
     */
    public int getMaximumAmountOfCardsInHand() {
        return maximumAmountOfCardsInHand;
    }
    
    /**
     * Returns a copy of the list of cards that the hand contains.
     * 
     * @return List of cards
     */
    public List<Card> getCards() {
        return new ArrayList<Card>(cards);
    }
    
    /**
     * Tells if the hand is full.
     * 
     * @return true if the hand is full, false otherwise.
     */
    public boolean isFull() {
        return cards.size() >= maximumAmountOfCardsInHand;
    }   
}
