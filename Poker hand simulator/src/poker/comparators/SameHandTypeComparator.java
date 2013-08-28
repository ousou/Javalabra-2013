package poker.comparators;

import poker.enums.PokerHandType;
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
import poker.FiveCardPokerHand;
import static poker.enums.PokerHandType.FLUSH;
import static poker.enums.PokerHandType.FOUR_OF_A_KIND;
import static poker.enums.PokerHandType.FULL_HOUSE;
import static poker.enums.PokerHandType.HIGH_CARD;
import static poker.enums.PokerHandType.PAIR;
import static poker.enums.PokerHandType.STRAIGHT;
import static poker.enums.PokerHandType.STRAIGHT_FLUSH;
import static poker.enums.PokerHandType.THREE_OF_A_KIND;
import static poker.enums.PokerHandType.TWO_PAIR;

/**
 *
 * @author Sebastian Björkqvist
 */
public class SameHandTypeComparator implements Comparator<FiveCardPokerHand>, Serializable {

    private PokerHandType handType;
    
    /**
     * Creates a new SameHandTypeComparator.
     * 
     * This constructor can be used if the hand type has already
     * been determined.
     * 
     * @param handType The handType to consider.
     */
    public SameHandTypeComparator(PokerHandType handType) {
        this.handType = handType;
    }

    /**
     * Creates a new SameHandComparator without a handType.
     * 
     * Because the hand type isn't given, it will be determined
     * in the compare-method.
     */
    
    public SameHandTypeComparator() {
    }
    
    /**
     * Compares poker hands that have the same hand type.
     * 
     * This method assumes that the hands are of the same type.
     * The behavior is unspecified if they aren't.
     * 
     * The best hand will be first on the list after the
     * sort.
     * 
     * @param o1 
     * @param o2
     * @return A negative, positive or zero integer if o1 is better, worse
     * or equal to o2.
     */
    @Override
    public int compare(FiveCardPokerHand o1, FiveCardPokerHand o2) {
        if (handType == null) {
            handType = new PokerHandTypeComparator().determineHandType(o1);         
        }
        return compareHandsOfSameType(o1, o2, handType);
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
     * Checks which of two paired hands are better.
     *
     * @param cards1
     * @param cards2
     * @return 
     */
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
        
        Collections.sort(listsOfCardsByRank, new SameHandTypeComparator.CardListComparator());
        
        for (List<Card> listOfCards : listsOfCardsByRank) {
            for (Card card : listOfCards) {
                sorted.add(card);
            }
        }
        
        return sorted;
    }
    
    private static class CardListComparator implements Comparator<List<Card>>, Serializable {

        /**
         * Sorts lists of cards for the pairedHandSorter-method.
         * 
         * This method assumes that the cards in each of the lists
         * are of the same rank.
         * 
         * @see SameHandTypeComparator.pairedHandSorter
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
