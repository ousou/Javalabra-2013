package poker.comparators;

import poker.enums.PokerHandType;
import card.Card;
import card.Rank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import poker.FiveCardPokerHand;

/**
 * Sorts hands by their hand type.
 *
 * @author Sebastian Björkqvist
 */
public class PokerHandTypeComparator implements Comparator<FiveCardPokerHand>, Serializable {

    
    /**
     * Sorts five card poker in descending hands by their hand type.
     *
     * The hand(s) with the best hand type will be first on the list when using
     * this sorter.
     *
     * Hands of the same type are considered equal.
     * 
     * The method uses the predetermined hand types of the given hands if available.
     * When the method has run, the hands will contain their hand type.
     *
     * @param o1
     * @param o2
     * @return Returns a negative, positive or zero integer if o1 is of a
     * better, worse or equal hand type than o2.
     * @throws IllegalArgumentException if one of the hands don't have five
     * cards in it.
     */
    @Override
    public int compare(FiveCardPokerHand o1, FiveCardPokerHand o2) {
        if (o1.getHandType() == null) {
            o1.setHandType(determineHandType(o1));
        }
        if (o2.getHandType() == null) {
            o2.setHandType(determineHandType(o2));
        }

        return o2.getHandType().getValue() - o1.getHandType().getValue();
    }

    /**
     * Determines the hand type of a five card poker hand.
     *
     * All hand types have a value representing their strength. A higher value
     * means a better hand, so using this method we can immediately determine
     * which of two hands is better if they aren't of the same type.
     *
     * @param hand FiveCardPokerHand
     * @return The type of the hand
     * @throws IllegalArgumentException if the hand doesn't have five cards in
     * it.
     */
    public PokerHandType determineHandType(FiveCardPokerHand hand) {
        if (hand.getNumberOfCards() != 5) {
            throw new IllegalArgumentException("The hand must have exactly five cards");
        }

        List<Card> cards = hand.getCards();

        Collections.sort(cards);

        // Check straight flush, flush and straight
        boolean isFlush = isFlush(cards);
        boolean isStraight = isStraight(cards);

        if (isFlush && isStraight) {
            return PokerHandType.STRAIGHT_FLUSH;
        }
        if (isFlush) {
            return PokerHandType.FLUSH;
        }
        if (isStraight) {
            return PokerHandType.STRAIGHT;
        }
        List<Integer> cardsOfSameRankList = cardsOfSameRankList(cards);

        // Four of a kind
        if (cardsOfSameRankList.get(0) == 4) {
            return PokerHandType.FOUR_OF_A_KIND;
        }
        // Full house and three of a kind
        if (cardsOfSameRankList.get(0) == 3) {
            if (cardsOfSameRankList.get(1) == 2) {
                return PokerHandType.FULL_HOUSE;
            }
            return PokerHandType.THREE_OF_A_KIND;
        }
        // Two pair and pair
        if (cardsOfSameRankList.get(0) == 2) {
            if (cardsOfSameRankList.get(1) == 2) {
                return PokerHandType.TWO_PAIR;
            }
            return PokerHandType.PAIR;
        }

        // If none of the above are true, the hand is a high card hand.
        return PokerHandType.HIGH_CARD;
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
     * Examples: For the hand 9-9-9-K-A the method returns the list {3,1,1} For
     * the hand 5-5-4-T-Q the method returns the list {2,1,1,1} For the hand
     * 4-4-K-K-K the method returns {3,2} For the hand K-T-K-K-K the method
     * returns {4,1} For the hand 5-5-7-7-T the method returns {2,2,1} For the
     * hand 5-4-8-7-9 the method returns {1,1,1,1,1}
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
}
