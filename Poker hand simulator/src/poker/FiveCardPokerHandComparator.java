package poker;

import java.util.Comparator;

/**
 *
 * @author Sebastian Björkqvist
 */
public class FiveCardPokerHandComparator implements Comparator<FiveCardPokerHand> {

    /**
     * Sorts five card poker hands in descending order by hand value.
     * 
     * Returns a negative number the first hand o1 is better than the 
     * second hand o2, a positive number if o2 is better than o1,
     * and zero if the hands tie.
     * 
     * That means, if we sort a list of FiveCardPokerHand-objects using
     * this comparator, the best hand will be first.
     * 
     * @param o1
     * @param o2
     * @return 
     */
    @Override
    public int compare(FiveCardPokerHand o1, FiveCardPokerHand o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
