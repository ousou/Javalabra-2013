package poker;

import card.Card;
import card.CardAce14Comparator;
import card.Rank;
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
        
        PokerHandType hand1Type = determineHandType(o1);
        PokerHandType hand2Type = determineHandType(o2);
        
        if (hand1Type != hand2Type) {
            return hand2Type.getValue() - hand1Type.getValue();
        }
        
        return compareHandsOfSameType(o1, o2, hand1Type);        
    }

    /**
     * Determines the hand type of a five card poker hand.
     * 
     * All hand types have a value representing their strength. A higher value
     * means a better hand, so using this method we can immediately determine 
     * which of two hands is better if they aren't of the same type.
     *
     * The method has modifier protected so that the test class can access it.
     *
     * @param hand FiveCardPokerHand
     * @return integer representing the hand type
     * @throws IllegalArgumentException if the hand doesn't have five cards in
     * it.
     */
    protected PokerHandType determineHandType(FiveCardPokerHand hand) {
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
     * @exception IllegalArgumentException if one of the hands doesn't have five cards.
     */
    private int compareHandsOfSameType(FiveCardPokerHand o1, FiveCardPokerHand o2, PokerHandType handType) {        
        if (o1.getNumberOfCards() != 5 || o2.getNumberOfCards() != 5) {
            throw new IllegalArgumentException("One of the hands doesn't have five cards!");
        }
        List<Card> cards1 = o1.getCards();
        List<Card> cards2 = o2.getCards();
        Collections.sort(cards1);
        Collections.sort(cards2);
        
        switch (handType) {
            case HIGH_CARD:
                return checkBetterFlushOrHighCard(cards1, cards2);
            case PAIR:
                return checkBetterPairedHand(cards1, cards2);
            case TWO_PAIR:
                return checkBetterPairedHand(cards1, cards2);
            case THREE_OF_A_KIND:
                return checkBetterPairedHand(cards1, cards2);                
            case STRAIGHT:
                return checkBetterStraightOrStraightFlush(cards1, cards2);            
            case FLUSH:
                return checkBetterFlushOrHighCard(cards1, cards2);    
            case FULL_HOUSE:
                return checkBetterPairedHand(cards1, cards2);   
            case FOUR_OF_A_KIND:
                return checkBetterPairedHand(cards1, cards2);                  
            case STRAIGHT_FLUSH:
                return checkBetterStraightOrStraightFlush(cards1, cards2);    
            default:
                throw new RuntimeException("invalid handType");
        }
        
    }

    /**
     * Checks which of two flushes are better.
     * 
     * Assumes that the cards are sorted.
     * 
     * @param cards1
     * @param cards2
     * @return 
     */
    private int checkBetterFlushOrHighCard(List<Card> cards1, List<Card> cards2) {
        /* If one hand contains an ace and the other one doesn't,
         * the hand with an ace wins.
         */
        Rank hand1RankOfFirstCard = cards1.get(0).getRank();
        Rank hand2RankOfFirstCard = cards2.get(0).getRank();        
        if (hand1RankOfFirstCard == Rank.ACE && hand2RankOfFirstCard != Rank.ACE) {
            return -1;
        }
        if (hand1RankOfFirstCard != Rank.ACE && hand2RankOfFirstCard == Rank.ACE) {
            return 1;
        }        
        // Checking which hand has the higher cards.
        for (int i = cards1.size() - 1; i >= 0; i--) {
            int hand1CardValue = cards1.get(i).getRank().getValue();
            int hand2CardValue = cards2.get(i).getRank().getValue();
            int difference = hand2CardValue - hand1CardValue;
            if (difference != 0) {
                return difference;
            }
        }
        return 0;
    }
    /**
     * Checks which of two straights or straight flushes are better.
     * 
     * Assumes that the cards are sorted.
     * 
     * @param cards1
     * @param cards2
     * @return
     */
    private int checkBetterStraightOrStraightFlush(List<Card> cards1, List<Card> cards2) {
        int hand1LastCardValue = cards1.get(4).getRank().getValue();
        int hand2LastCardValue = cards2.get(4).getRank().getValue(); 
        int difference = hand2LastCardValue - hand1LastCardValue;
        
        if (difference != 0) {
            return difference;
        }
        /* If the last card is a king, we might have two straights:
         * 9-T-J-Q-K or A-T-J-Q-K, so we need to check which we have.
         * 
         * Here ofcourse the straight with the ace is better, so the hand
         * that has the LOWER valued first card wins.
         */
        if (hand1LastCardValue == 13) {
            return cards1.get(0).getRank().getValue() - cards2.get(0).getRank().getValue();
        }   
        
        return 0;        
    }
    /**
     * Checks which of two pair hands are better.
     * 
     * @param cards1
     * @param cards2
     * @return
     */
    private int checkBetterPair(List<Card> cards1, List<Card> cards2) {
        cards1 = pairedHandSorter(cards1);
        cards2 = pairedHandSorter(cards2);
        
        CardAce14Comparator ace14Comparator = new CardAce14Comparator();
        
        Card hand1PairCard = cards1.get(0);
        Card hand2PairCard = cards2.get(0);
        
        if (hand1PairCard.getRank() != hand2PairCard.getRank()) {
            return ace14Comparator.compare(hand1PairCard, hand2PairCard);
        }
        
        for (int i = 2; i < 5; i++) {
            Card hand1Card = cards1.get(i);
            Card hand2Card = cards2.get(i);
            if (hand1Card.getRank() != hand2Card.getRank()) {
                return ace14Comparator.compare(hand1Card, hand2Card);                
            }
        }
        
        return 0;
    }
    
    /**
     * Checks which of two two pair hands are better.
     * 
     * @param cards1
     * @param cards2
     * @return 
     */
    private int checkBetterTwoPair(List<Card> cards1, List<Card> cards2) {
        cards1 = pairedHandSorter(cards1);
        cards2 = pairedHandSorter(cards2);
        
        CardAce14Comparator ace14Comparator = new CardAce14Comparator();
   
        // The relevant cards are the first, third and fifth cards.
        for (int i = 0; i < 5; i = i + 2) {
            Card hand1Card = cards1.get(i);
            Card hand2Card = cards2.get(i);
            if (hand1Card.getRank() != hand2Card.getRank()) {
                return ace14Comparator.compare(hand1Card, hand2Card);                
            }
        }
        
        return 0;
    }    
    
    private int checkBetterPairedHand(List<Card> cards1, List<Card> cards2) {
        cards1 = pairedHandSorter(cards1);
        cards2 = pairedHandSorter(cards2);        
        
        CardAce14Comparator ace14Comparator = new CardAce14Comparator();        

        /* Since pairedHandSorter sorts the cards of a paired hand
         * by relevance, we can just loop through the cards to determine the winner.
         */
        
        for (int i = 0; i < 5; i++) {
            Card hand1Card = cards1.get(i);
            Card hand2Card = cards2.get(i);
            if (hand1Card.getRank() != hand2Card.getRank()) {
                return ace14Comparator.compare(hand1Card, hand2Card);                
            }      
        }
        
        return 0;
    }
    
    /**
     * Sorts paired or x of a kind-type hands in a particular order.
     * 
     * Used for determining which of two hands of the same hand type is better.
     * The method puts the most relevant cards first, i.e. if a hand contains 
     * three cards of the same rank, it puts them first on the list.
     * 
     * The method assumes that the list of cards is sorted by rank.
     * 
     * Examples:
     * The list {A, K, K, 2, 2} becomes {K, K, 2, 2, A}
     * The list {3, 3, 7, 8, 3} becomes {3, 3, 3, 8, 7}
     * The list {2, 2, K, 2, 2} becomes {2, 2, 2, 2, K}
     * The list {4, 5, Q, K, K} becomes {K, K, Q, 5, 4}
     * 
     * @param cards List of cards.
     * @return A list sorted by relevance.
     */
    private List<Card> pairedHandSorter(List<Card> cards) {
        List<Card> sorted = new ArrayList<Card>();
        Map<Rank, List<Card>> occurencesOfRank = new EnumMap<Rank, List<Card>>(Rank.class);

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            Rank rank = card.getRank();
            if (!occurencesOfRank.containsKey(rank)) {
                occurencesOfRank.put(rank, new ArrayList<Card>());
            }
            occurencesOfRank.get(rank).add(card);
        }
        
        List<List<Card>> listsOfCardsByRank = new ArrayList<List<Card>>();       
        listsOfCardsByRank.addAll(occurencesOfRank.values());
        
        Collections.sort(listsOfCardsByRank, new CardListComparator());
        
        for (List<Card> listOfCards : listsOfCardsByRank) {
            for (Card card : listOfCards) {
                sorted.add(card);
            }
        }
        
        return sorted;
    }
    
    private static class CardListComparator implements Comparator<List<Card>> {

        /**
         * Sorts lists of cards.
         * 
         * This method assumes that the cards in each of the lists
         * are of the same rank.
         * 
         * @see pairedHandSorter for details.
         * 
         * @param o1
         * @param o2
         * @return 
         */
        @Override
        public int compare(List<Card> o1, List<Card> o2) {            
            int list1Size = o1.size();
            int list2Size = o2.size();            
            
            if (list1Size != list2Size) {
                return list2Size - list1Size;
            }
            if (list1Size == 0 && list2Size == 0) {
                return 0;
            }
            
            Card list1Card = o1.get(0);
            Card list2Card = o2.get(0);
            
            return new CardAce14Comparator().compare(list1Card, list2Card);
        }
        
    }
}
