package poker;

import card.Card;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sebastian Björkqvist
 */
public class FiveCardPokerHand {

    private List<Card> cards;

    public FiveCardPokerHand() {
        cards = new ArrayList<Card>();
    }
    
    public boolean addCard(Card card) {
        if (card == null) {
            return false;
        }
        if (cards.contains(card)) {
            throw new IllegalArgumentException("The hand already contains the card " + card + "!");
        }
        if (isFull()) {
            return false;
        }        
        
        cards.add(card);
        return true;
    }    
    
    public List<Card> getCards() {
        return new ArrayList<Card>(cards);
    }
    
    public int getNumberOfCards() {
        return cards.size();
    }
    
    public boolean isFull() {
        return cards.size() >= 5;
    }

    
}
