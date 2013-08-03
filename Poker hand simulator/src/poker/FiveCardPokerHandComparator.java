package poker;

import card.Card;
import card.Rank;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Sebastian Björkqvist
 */
public final class FiveCardPokerHandComparator implements Comparator<FiveCardPokerHand> {

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
        return 0;
    }

    /**
     * Determines the hand type of a five card poker hand.
     *
     * Returns an integer representing the type of the hand, according to the
     * list below:
     *
     * High card - 0 Pair - 1 Two pair - 2 Three of a kind - 3 Straight - 4
     * Flush - 5 Full house - 6 Four of a kind - 7 Straight flush - 8
     *
     * A higher hand type number means a better hand, so with this method we can
     * immediately determine which of two hands is better if they aren't of the
     * same type.
     * 
     * The method has modifier protected so that the test class
     * can access it.
     *
     * @param hand FiveCardPokerHand
     * @return integer representing the hand type
     */
    protected int determineHandType(FiveCardPokerHand hand) {
        List<Card> cards = hand.getCards();

        Collections.sort(cards);

        // Check straight flush, flush and straight
        boolean isFlush = isFlush(cards);
        boolean isStraight = isStraight(cards);

        if (isFlush && isStraight) {
            return 8;
        }
        if (isFlush) {
            return 5;
        }
        if (isStraight) {
            return 4;
        }

        return 0;
    }

    private boolean isFlush(List<Card> cards) {
        Card first = cards.get(0);
        for (int i = 1; i < cards.size(); i++) {
            if (first.getSuit() != cards.get(i).getSuit()) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight(List<Card> cards) {
        if (valueIncreasesByOne(cards, 0)) {
            return true;
        }
        
        /* Since ace has value one when sorting, we need to check the
         * broadway straight A-K-Q-J-T separately
         */
        if (cards.get(0).getRank() == Rank.ACE && cards.get(1).getRank() == Rank.TEN) {
            return valueIncreasesByOne(cards, 1);
        }
        
        return false;
    }

    private boolean valueIncreasesByOne(List<Card> cards, int startingIndex) {
        int previousRankValue = cards.get(startingIndex).getRank().getValue();
        for (int i = startingIndex + 1; i < cards.size(); i++) {
            int nextRankValue = cards.get(i).getRank().getValue();
            if (nextRankValue != previousRankValue + 1) {
                return false;
            }
            previousRankValue = nextRankValue;
        }
        return true;
    }
}
