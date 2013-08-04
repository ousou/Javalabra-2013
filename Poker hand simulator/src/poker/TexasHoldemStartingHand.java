package poker;

import card.Card;

/**
 * Represents a starting hand in Texas hold'em.
 * 
 * Texas hold'em-hands contain two cards, and zero to two
 * of them may be used to create the best hand.
 * 
 * @author Sebastian Björkqvist
 */
public class TexasHoldemStartingHand extends AbstractStartingHand {

    /**
     * Creates a new Texas hold'em-hand without any cards.
     */
    public TexasHoldemStartingHand() {
        super(2, 0, 2);
    }
    
    /**
     * Creates a new Texas hold'em hand with two cards.
     * 
     * @param card1 First card
     * @param card2 Second card
     */
    public TexasHoldemStartingHand(Card card1, Card card2) {
        super(2, 0, 2);
        addCard(card1);
        addCard(card2);
    }
}
