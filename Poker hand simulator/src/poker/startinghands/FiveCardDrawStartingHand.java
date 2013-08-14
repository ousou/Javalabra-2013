package poker.startinghands;

import card.Card;
import java.util.List;
import poker.enums.PokerGameType;

/**
 * Represents a starting hand in five card draw.
 * 
 * This class may be used for five card stud hands also.
 * 
 * @author Sebastian Björkqvist
 */
public class FiveCardDrawStartingHand extends AbstractStartingHand {

    /**
     * Creates a new Five card draw starting hand without cards.
     */
    public FiveCardDrawStartingHand() {
        super(5, 5, 5, PokerGameType.FIVE_DRAW);
    }
    
    /**
     * Creates a new Five card draw starting hand with the given cards.
     * 
     * @param cardsToAdd List of cards
     * @throws IllegalArgumentException if the list is null
     * @throws IllegalArgumentException if the list contains more
     * than five cards.
     * @throws IllegalArgumentException if the list contains overlapping
     * cards. 
     */
    public FiveCardDrawStartingHand(List<Card> cardsToAdd) {
        this();
        addCards(cardsToAdd);
    }
   
    
    @Override
    public AbstractStartingHand copyOfHand() {
        AbstractStartingHand copy = new FiveCardDrawStartingHand();
        copy.addCards(getCards());        
        return copy;
    }

}
