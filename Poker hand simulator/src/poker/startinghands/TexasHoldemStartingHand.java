package poker.startinghands;

import poker.enums.PokerGameType;
import card.Card;
import java.util.List;

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
        super(2, 0, 2, PokerGameType.TEXAS);
    }
    
    /**
     * Creates a new Texas hold'em hand with two cards.
     * 
     * @param card1 First card
     * @param card2 Second card
     * @throws IllegalArgumentException if one of the cards are null, or
     * if they are the same card.
     */
    public TexasHoldemStartingHand(Card card1, Card card2) {
        this();
        addCard(card1);
        addCard(card2);
    }
    
    /**
     * Creates a new Texas hold'em hand with two cards.
     * 
     * @param card1 First card
     * @param card2 Second card
     * @throws IllegalArgumentException if one of the cards are null, or
     * if they are the same card.
     * @throws IllegalArgumentException if the list size isn't 2.
     */
    public TexasHoldemStartingHand(List<Card> cardsToAdd) {
        this();
        if (cardsToAdd == null || cardsToAdd.size() != 2) {
            throw new IllegalArgumentException("List of cards must be of size 2");
        }
        addCard(cardsToAdd.get(0));
        addCard(cardsToAdd.get(1));
    }    

    @Override
    public AbstractStartingHand copyOfHand() {
        AbstractStartingHand copy = new TexasHoldemStartingHand();
        copy.addCards(getCards());
        return copy;
    }
}
