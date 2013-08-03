package card;

import java.util.Comparator;

/**
 * 
 * @author Sebastian Björkqvist
 */
public class CardAce14Comparator implements Comparator<Card> {

    /**
     * Compares cards by their rank, with ace having rank 14.
     * 
     * This method orders cards in descending order by rank.
     * 
     * @param o1 Card
     * @param o2 Card
     * @return Positive, negative or zero value if first card is smaller, bigger or the same
     * as the second.
     */
    @Override
    public int compare(Card o1, Card o2) {
        int card1RankValue = o1.getRank().getValue();
        int card2RankValue = o2.getRank().getValue();
        
        if (card1RankValue == 1) {
            card1RankValue = 14;
        }
        if (card2RankValue == 1) {
            card2RankValue = 14;
        }       
        
        return card2RankValue - card1RankValue;
    }

}
