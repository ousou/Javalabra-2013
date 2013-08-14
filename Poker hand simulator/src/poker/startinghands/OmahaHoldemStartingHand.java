package poker.startinghands;

import poker.enums.PokerGameType;
import card.Card;
import java.util.List;

/**
 * Represents a starting hand in Omaha hold'em.
 * 
 * Omaha-hands contain four cards, and exactly two of them
 * must be used to create the best hand. 
 * 
 * @author Sebastian Björkqvist
 */
public class OmahaHoldemStartingHand extends AbstractStartingHand {

    /**
     * Creates a new Omaha hold'em-hand.
     */
    public OmahaHoldemStartingHand() {
        super(4, 2, 2, PokerGameType.OMAHA);
    }

    /**
     * Creates a new Omaha hold'em-hand with the specified cards.
     * 
     * @param cardsToAdd List of cards to add.
     * @throws IllegalArgumentException if one of the cards are null, or
     * if the same card is on the list twice.
     */
    public OmahaHoldemStartingHand(List<Card> cardsToAdd) {
        this();
        addCards(cardsToAdd);
    }

    @Override
    public AbstractStartingHand copyOfHand() {
        AbstractStartingHand copy = new OmahaHoldemStartingHand();
        copy.addCards(getCards());        
        return copy;
    }
    
    
}
