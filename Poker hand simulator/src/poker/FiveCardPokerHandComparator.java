package poker;

import card.Card;
import card.CardAce14Comparator;
import card.Rank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sebastian Björkqvist
 */
public final class FiveCardPokerHandComparator implements Comparator<FiveCardPokerHand>, Serializable {

    /**
     * Sorts five card poker hands in descending order by hand value.
     *
     * Returns a negative number if the first hand o1 is better than the second
     * hand o2, a positive number if o2 is better than o1, and zero if the hands
     * tie.
     *
     * That means, if we sort a list of FiveCardPokerHand-objects using this
     * comparator, the best hand will be first.
     *
     * This comparator assumes the hands have cards from a standard 52 card
     * deck.
     *
     * The comparator compares only full five card hands. If one of the hands
     * isn't full and the other is, the full hand automatically wins. If both
     * hands have less than five cards, the method returns 0.
     * 
     * The hands can have overlapping cards.
     *
     * @param o1 FiveCardPokerHand
     * @param o2 FiveCardPokerHand
     * @return A negative, positive or zero integer if o1 is better, worse or
     * equal to o2.
     */
    @Override
    public int compare(FiveCardPokerHand o1, FiveCardPokerHand o2) {
        if (!o1.isFull() && !o2.isFull()) {
            return 0;
        }
        if (!o1.isFull()) {
            return 1;
        }
        if (!o2.isFull()) {
            return -1;
        }
        
        PokerHandTypeComparator handTypeComparator = new PokerHandTypeComparator();
       
        int handTypeComparison = handTypeComparator.compare(o1, o2);
        
        if (handTypeComparison != 0) {
            return handTypeComparison;
        }
        // If we are here, we know that the hands must be of the same type.
        PokerHandType handType = handTypeComparator.getHand1Type(); 
        
        SameHandTypeComparator sameHandTypeComparator = new SameHandTypeComparator(handType);
        
        return sameHandTypeComparator.compare(o1, o2);
    }
}
