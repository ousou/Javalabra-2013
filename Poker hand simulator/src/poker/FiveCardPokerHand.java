package poker;

import card.Card;
import java.util.List;
import poker.enums.PokerHandType;

/**
 * Represents a standard five card poker hand with cards from a 52 card deck.
 *
 * @author Sebastian Björkqvist
 */
public class FiveCardPokerHand extends AbstractCardCollection {

    private PokerHandType handType;
    /**
     * Creates a new empty five card hand.
     */
    public FiveCardPokerHand() {
        super(5);
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

    public PokerHandType getHandType() {
        return handType;
    }

    public void setHandType(PokerHandType handType) {
        this.handType = handType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (getCardSet() != null ? getCardSet().hashCode() : 0);
        hash = 37 * hash + getMaximumAmountOfCardsInCollection();        
        return hash;
    }

    /**
     * Equals-method for FiveCardPokerHand.
     * 
     * This class needs to override the equals-method for the simulations to
     * work properly. The other classes that extend AbstractCardCollection
     * don't need to override equals.
     *  
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FiveCardPokerHand other = (FiveCardPokerHand) obj;
        
        if (this.getCards() != other.getCards() && (this.getCards() == null || !this.getCardSet().equals(other.getCardSet()))) {
            return false;
        }
        if (this.getMaximumAmountOfCardsInCollection() != other.getMaximumAmountOfCardsInCollection()) {
            return false;
        }
        
        return true;
    }
    
    
}
