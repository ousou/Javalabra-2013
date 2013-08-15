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
}
