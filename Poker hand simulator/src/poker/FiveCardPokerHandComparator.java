package poker;

import card.Card;
import card.Rank;
import java.util.ArrayList;
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
        
        int hand1Type = determineHandType(o1);
        int hand2Type = determineHandType(o2);
        
        if (hand1Type != hand2Type) {
            return hand2Type - hand1Type;
        }
        
        return compareHandsOfSameType(o1, o2, hand1Type);        
    }

    /**
     * Determines the hand type of a five card poker hand.
     *
     * Returns an integer representing the type of the hand, according to the
     * list below:
     *
     * High card - 0, Pair - 1, Two pair - 2, Three of a kind - 3, Straight - 4
     * Flush - 5, Full house - 6, Four of a kind - 7, Straight flush - 8
     *
     * A higher hand type number means a better hand, so with this method we can
     * immediately determine which of two hands is better if they aren't of the
     * same type.
     *
     * The method has modifier protected so that the test class can access it.
     *
     * @param hand FiveCardPokerHand
     * @return integer representing the hand type
     * @throws IllegalArgumentException if the hand doesn't have five cards in
     * it.
     */
    protected int determineHandType(FiveCardPokerHand hand) {
        if (hand.getNumberOfCards() != 5) {
            throw new IllegalArgumentException("The hand must have exactly five cards");
        }

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
        List<Integer> cardsOfSameRankList = cardsOfSameRankList(cards);

        // Four of a kind
        if (cardsOfSameRankList.get(0) == 4) {
            return 7;
        }
        // Full house and three of a kind
        if (cardsOfSameRankList.get(0) == 3) {
            if (cardsOfSameRankList.get(1) == 2) {
                return 6;
            }
            return 3;
        }
        // Two pair and pair
        if (cardsOfSameRankList.get(0) == 2) {
            if (cardsOfSameRankList.get(1) == 2) {
                return 2;
            }
            return 1;
        }

        // If none of the above are true, the hand is a high card hand.
        return 0;
    }

    /**
     * Checks if a hand is a flush.
     * 
     * @param cards
     * @return true if all cards are of the same suit, false otherwise.
     */
    private boolean isFlush(List<Card> cards) {
        Card first = cards.get(0);
        for (int i = 1; i < cards.size(); i++) {
            if (first.getSuit() != cards.get(i).getSuit()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a hand is a straight.
     *
     * The method assumes that the cards are sorted.
     * 
     * @param cards
     * @return True if the hand is a straight, false otherwise.
     */
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

    /**
     * Checks if the value of the card increases by one starting from an index.
     * 
     * The method assumes that the cards are sorted.
     * 
     * @param cards List of cards
     * @param startingIndex starting index
     * @return true if the value always increases by one, false otherwise.
     */
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

    /**
     * Calculates how many cards of the same rank there is in the hand.
     *
     * The sum of the integers in the list is equal to the number of cards in
     * the hand.
     *
     * The method assumes that the cards are sorted.
     * 
     * Examples: 
     * For the hand 9-9-9-K-A the method returns the list {3,1,1} 
     * For the hand 5-5-4-T-Q the method returns the list {2,1,1,1} 
     * For the hand 4-4-K-K-K the method returns {3,2} 
     * For the hand K-T-K-K-K the method returns {4,1} 
     * For the hand 5-5-7-7-T the method returns {2,2,1} 
     * For the hand 5-4-8-7-9 the method returns {1,1,1,1,1}
     *
     * @param cards The cards of a hand
     * @return An list of integers, with the highest amount of cards with the
     * same rank first.
     */
    private List<Integer> cardsOfSameRankList(List<Card> cards) {
        int howManyCardsOfTheSameRank = 1;
        int previousRankValue = cards.get(0).getRank().getValue();
        List<Integer> cardsOfSameRankList = new ArrayList<Integer>();

        for (int i = 1; i < cards.size(); i++) {
            int currentRankValue = cards.get(i).getRank().getValue();
            if (previousRankValue == currentRankValue) {
                howManyCardsOfTheSameRank++;
            } else {
                cardsOfSameRankList.add(howManyCardsOfTheSameRank);
                howManyCardsOfTheSameRank = 1;
                previousRankValue = currentRankValue;
            }
        }
        cardsOfSameRankList.add(howManyCardsOfTheSameRank);

        Collections.sort(cardsOfSameRankList);
        Collections.reverse(cardsOfSameRankList);

        return cardsOfSameRankList;
    }

    /**
     * Compares hands that have the same type.
     * 
     * This method assumes that both hands have the type matching
     * the argument handType, and that both hands are full five card hands.
     * 
     * @param o1 Hand 1
     * @param o2 Hand 2
     * @param handType hand Type
     * @return A negative value, positive or zero value if o1 is better, worse or equal to o2.
     */
    private int compareHandsOfSameType(FiveCardPokerHand o1, FiveCardPokerHand o2, int handType) {        
        List<Card> cards1 = o1.getCards();
        List<Card> cards2 = o2.getCards();
        Collections.sort(cards1);
        Collections.sort(cards2);
        
        if (handType == 5) {
            return checkBetterFlush(cards1, cards2);
        }
        
        if (handType == 4 || handType == 8) {
            
        }
        
        return 0;
    }

    /**
     * Checks which of two flushes are better.
     * @param cards1
     * @param cards2
     * @return 
     */
    private int checkBetterFlush(List<Card> cards1, List<Card> cards2) {
        for (int i = 4; i >= 0; i++) {
            int hand1CardValue = cards1.get(i).getRank().getValue();
            int hand2CardValue = cards2.get(i).getRank().getValue();
            int difference = hand2CardValue - hand1CardValue;
            if (difference != 0) {
                return difference;
            }
        }
        return 0;
    }
}
