package poker.startinghands;

import poker.enums.PokerGameType;
import card.Card;
import java.util.List;
import poker.AbstractStartingHand;

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
     * @throws IllegalArgumentException if the list size isn't 4.
     */
    public OmahaHoldemStartingHand(List<Card> cardsToAdd) {
        this();
        if (cardsToAdd == null || cardsToAdd.size() != 4) {
            throw new IllegalArgumentException("List of cards must be of size 4");
        }
        for (int i = 0; i < 4; i++) {
            addCard(cardsToAdd.get(i));
        }
    }
}
