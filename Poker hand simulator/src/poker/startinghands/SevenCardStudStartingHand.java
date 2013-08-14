package poker.startinghands;

import card.Card;
import java.util.List;
import poker.enums.PokerGameType;

/**
 * Represents a starting hand in Seven card stud.
 * 
 * @author Sebastian Björkqvist
 */
public class SevenCardStudStartingHand extends AbstractStartingHand {

    /**
     * Creates a new Seven card stud hand without any cards.
     */
    public SevenCardStudStartingHand() {
        super(7, 5, 5, PokerGameType.SEVEN_STUD);
    }

    /**
     * Creates a new Seven card stud hand with the given cards.
     * 
     * @param cardsToAdd List of cards
     * @throws IllegalArgumentException if cardsToAdd is null
     * @throws IllegalArgumentException if cardsToAdd contains more than seven cards.
     * @throws IllegalArgumentException if cardsToAdd contains overlapping cards.
     */
    
    public SevenCardStudStartingHand(List<Card> cardsToAdd) {
        this();
        addCards(cardsToAdd);
    }

    @Override
    public AbstractStartingHand copyOfHand() {
        AbstractStartingHand copy = new SevenCardStudStartingHand();
        copy.addCards(getCards());        
        return copy;
    }
    
}
