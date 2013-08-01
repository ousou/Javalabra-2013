package poker;

import card.Card;
import card.Rank;
import card.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sebastian Björkqvist
 */
public class FiveCardPokerHandComparatorTest {

    FiveCardPokerHandComparator comparator;

    public FiveCardPokerHandComparatorTest() {
        this.comparator = new FiveCardPokerHandComparator();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testOrderOfHandTypes() {
        List<FiveCardPokerHand> hands = new ArrayList<FiveCardPokerHand>();
        List<FiveCardPokerHand> expectedOrder = new ArrayList<FiveCardPokerHand>();

        // Some hands have overlapping cards, but it doesn't matter while testing

        FiveCardPokerHand highCard = new FiveCardPokerHand();
        highCard.addCard(new Card(Suit.SPADE, Rank.ACE));
        highCard.addCard(new Card(Suit.CLUB, Rank.QUEEN));
        highCard.addCard(new Card(Suit.SPADE, Rank.SEVEN));
        highCard.addCard(new Card(Suit.DIAMOND, Rank.KING));
        highCard.addCard(new Card(Suit.SPADE, Rank.NINE));
        hands.add(highCard);
        expectedOrder.add(highCard);

        FiveCardPokerHand pair = new FiveCardPokerHand();
        pair.addCard(new Card(Suit.HEART, Rank.DEUCE));
        pair.addCard(new Card(Suit.CLUB, Rank.DEUCE));
        pair.addCard(new Card(Suit.SPADE, Rank.THREE));
        pair.addCard(new Card(Suit.HEART, Rank.FOUR));
        pair.addCard(new Card(Suit.HEART, Rank.FIVE));
        hands.add(pair);
        expectedOrder.add(pair);        

        FiveCardPokerHand twoPair = new FiveCardPokerHand();
        pair.addCard(new Card(Suit.HEART, Rank.FOUR));
        pair.addCard(new Card(Suit.CLUB, Rank.FOUR));
        pair.addCard(new Card(Suit.SPADE, Rank.SEVEN));
        pair.addCard(new Card(Suit.HEART, Rank.SEVEN));
        pair.addCard(new Card(Suit.DIAMOND, Rank.FIVE));
        hands.add(twoPair);
        expectedOrder.add(twoPair);        

        FiveCardPokerHand threeOfAKind = new FiveCardPokerHand();
        pair.addCard(new Card(Suit.HEART, Rank.TEN));
        pair.addCard(new Card(Suit.CLUB, Rank.TEN));
        pair.addCard(new Card(Suit.SPADE, Rank.TEN));
        pair.addCard(new Card(Suit.DIAMOND, Rank.FOUR));
        pair.addCard(new Card(Suit.CLUB, Rank.FIVE));
        hands.add(threeOfAKind);
        expectedOrder.add(threeOfAKind);        

        FiveCardPokerHand straight = new FiveCardPokerHand();
        pair.addCard(new Card(Suit.HEART, Rank.EIGHT));
        pair.addCard(new Card(Suit.CLUB, Rank.SEVEN));
        pair.addCard(new Card(Suit.SPADE, Rank.SIX));
        pair.addCard(new Card(Suit.HEART, Rank.NINE));
        pair.addCard(new Card(Suit.SPADE, Rank.FIVE));
        hands.add(straight);
        expectedOrder.add(straight);        

        FiveCardPokerHand flush = new FiveCardPokerHand();
        pair.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        pair.addCard(new Card(Suit.DIAMOND, Rank.QUEEN));
        pair.addCard(new Card(Suit.DIAMOND, Rank.ACE));
        pair.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        pair.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        hands.add(flush);
        expectedOrder.add(flush);        

        FiveCardPokerHand fullHouse = new FiveCardPokerHand();
        pair.addCard(new Card(Suit.HEART, Rank.SIX));
        pair.addCard(new Card(Suit.CLUB, Rank.SIX));
        pair.addCard(new Card(Suit.SPADE, Rank.SIX));
        pair.addCard(new Card(Suit.HEART, Rank.QUEEN));
        pair.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        hands.add(fullHouse);
        expectedOrder.add(fullHouse);        

        FiveCardPokerHand fourOfAKind = new FiveCardPokerHand();
        pair.addCard(new Card(Suit.HEART, Rank.JACK));
        pair.addCard(new Card(Suit.CLUB, Rank.JACK));
        pair.addCard(new Card(Suit.SPADE, Rank.JACK));
        pair.addCard(new Card(Suit.DIAMOND, Rank.JACK));
        pair.addCard(new Card(Suit.HEART, Rank.ACE));
        hands.add(fourOfAKind);
        expectedOrder.add(fourOfAKind);        

        FiveCardPokerHand straightFlush = new FiveCardPokerHand();
        pair.addCard(new Card(Suit.HEART, Rank.SIX));
        pair.addCard(new Card(Suit.HEART, Rank.DEUCE));
        pair.addCard(new Card(Suit.HEART, Rank.THREE));
        pair.addCard(new Card(Suit.HEART, Rank.FOUR));
        pair.addCard(new Card(Suit.HEART, Rank.FIVE));
        hands.add(straightFlush);
        expectedOrder.add(straightFlush);        
        
        /* The expected order has the hands in descending order by value, so we 
         * reverse the list.
         */
        Collections.reverse(expectedOrder);
        
        // Making sure the order in which the hands are doesn't change the result
        Collections.shuffle(hands);
        
        Collections.sort(hands, comparator);
        
        assertEquals("Hand values are in incorrect order", expectedOrder, hands);
    }
    
    @Test
    public void testHighCardVsHighCard() {
        FiveCardPokerHand highCard = new FiveCardPokerHand();
        highCard.addCard(new Card(Suit.SPADE, Rank.ACE));
        highCard.addCard(new Card(Suit.CLUB, Rank.QUEEN));
        highCard.addCard(new Card(Suit.SPADE, Rank.SEVEN));
        highCard.addCard(new Card(Suit.DIAMOND, Rank.KING));
        highCard.addCard(new Card(Suit.SPADE, Rank.NINE));

        
        FiveCardPokerHand highCard2 = new FiveCardPokerHand();
        highCard.addCard(new Card(Suit.CLUB, Rank.KING));
        highCard.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        highCard.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        highCard.addCard(new Card(Suit.CLUB, Rank.THREE));
        highCard.addCard(new Card(Suit.CLUB, Rank.NINE));
        
        assertTrue(comparator.compare(highCard, highCard2) < 0);      
    }
    
    @Test
    public void testHighCardVsHighCard2() {
        FiveCardPokerHand highCard = new FiveCardPokerHand();
        highCard.addCard(new Card(Suit.SPADE, Rank.ACE));
        highCard.addCard(new Card(Suit.CLUB, Rank.QUEEN));
        highCard.addCard(new Card(Suit.SPADE, Rank.SEVEN));
        highCard.addCard(new Card(Suit.DIAMOND, Rank.KING));
        highCard.addCard(new Card(Suit.SPADE, Rank.NINE));

        
        FiveCardPokerHand highCard2 = new FiveCardPokerHand();
        highCard2.addCard(new Card(Suit.CLUB, Rank.ACE));
        highCard2.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        highCard2.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        highCard2.addCard(new Card(Suit.CLUB, Rank.KING));
        highCard2.addCard(new Card(Suit.CLUB, Rank.NINE));
        
        assertTrue(comparator.compare(highCard, highCard2) < 0);      
    }
    
    @Test
    public void testHighCardVsHighCardTie() {
        FiveCardPokerHand highCard = new FiveCardPokerHand();
        highCard.addCard(new Card(Suit.SPADE, Rank.ACE));
        highCard.addCard(new Card(Suit.CLUB, Rank.QUEEN));
        highCard.addCard(new Card(Suit.SPADE, Rank.SEVEN));
        highCard.addCard(new Card(Suit.DIAMOND, Rank.KING));
        highCard.addCard(new Card(Suit.SPADE, Rank.NINE));
        
        FiveCardPokerHand highCard2 = new FiveCardPokerHand();
        highCard2.addCard(new Card(Suit.CLUB, Rank.ACE));
        highCard2.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        highCard2.addCard(new Card(Suit.CLUB, Rank.NINE));
        highCard2.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        highCard2.addCard(new Card(Suit.CLUB, Rank.KING));        
        
        assertTrue(comparator.compare(highCard, highCard2) == 0);      
    }
    
    @Test
    public void testPairVsPair() {
        FiveCardPokerHand pair1 = new FiveCardPokerHand();
        pair1.addCard(new Card(Suit.SPADE, Rank.KING));
        pair1.addCard(new Card(Suit.CLUB, Rank.ACE));
        pair1.addCard(new Card(Suit.SPADE, Rank.SEVEN));
        pair1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        pair1.addCard(new Card(Suit.SPADE, Rank.NINE));
        
        FiveCardPokerHand pair2 = new FiveCardPokerHand();
        pair2.addCard(new Card(Suit.CLUB, Rank.ACE));
        pair2.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        pair2.addCard(new Card(Suit.CLUB, Rank.QUEEN));
        pair2.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        pair2.addCard(new Card(Suit.CLUB, Rank.KING));        
        
        assertTrue(comparator.compare(pair1, pair2) < 0);          
    }
    
    @Test
    public void testPairVsPair2() {
        FiveCardPokerHand pair1 = new FiveCardPokerHand();
        pair1.addCard(new Card(Suit.SPADE, Rank.KING));
        pair1.addCard(new Card(Suit.CLUB, Rank.ACE));
        pair1.addCard(new Card(Suit.SPADE, Rank.SEVEN));
        pair1.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        pair1.addCard(new Card(Suit.SPADE, Rank.NINE));
        
        FiveCardPokerHand pair2 = new FiveCardPokerHand();
        pair2.addCard(new Card(Suit.CLUB, Rank.ACE));
        pair2.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        pair2.addCard(new Card(Suit.CLUB, Rank.QUEEN));
        pair2.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        pair2.addCard(new Card(Suit.CLUB, Rank.KING));        
        
        assertTrue(comparator.compare(pair1, pair2) > 0);          
    }
    
    @Test
    public void testPairVsPair3() {
        FiveCardPokerHand pair1 = new FiveCardPokerHand();
        pair1.addCard(new Card(Suit.SPADE, Rank.ACE));
        pair1.addCard(new Card(Suit.HEART, Rank.QUEEN));
        pair1.addCard(new Card(Suit.DIAMOND, Rank.QUEEN));
        pair1.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        pair1.addCard(new Card(Suit.SPADE, Rank.NINE));
        
        FiveCardPokerHand pair2 = new FiveCardPokerHand();
        pair2.addCard(new Card(Suit.CLUB, Rank.ACE));
        pair2.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        pair2.addCard(new Card(Suit.CLUB, Rank.QUEEN));
        pair2.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        pair2.addCard(new Card(Suit.CLUB, Rank.KING));        
        
        assertTrue(comparator.compare(pair1, pair2) > 0);          
    }
    
    @Test
    public void testPairVsPairTie() {
        FiveCardPokerHand pair1 = new FiveCardPokerHand();
        pair1.addCard(new Card(Suit.SPADE, Rank.ACE));
        pair1.addCard(new Card(Suit.HEART, Rank.QUEEN));
        pair1.addCard(new Card(Suit.DIAMOND, Rank.QUEEN));
        pair1.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        pair1.addCard(new Card(Suit.SPADE, Rank.NINE));
        
        FiveCardPokerHand pair2 = new FiveCardPokerHand();
        pair2.addCard(new Card(Suit.CLUB, Rank.ACE));
        pair2.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        pair2.addCard(new Card(Suit.CLUB, Rank.QUEEN));
        pair2.addCard(new Card(Suit.DIAMOND, Rank.NINE));
        pair2.addCard(new Card(Suit.CLUB, Rank.SEVEN));        
        
        assertTrue(comparator.compare(pair1, pair2) == 0);          
    }
    
    @Test
    public void testTwoPairVsTwoPair() {
        FiveCardPokerHand twoPair1 = new FiveCardPokerHand();
        twoPair1.addCard(new Card(Suit.SPADE, Rank.KING));
        twoPair1.addCard(new Card(Suit.CLUB, Rank.ACE));
        twoPair1.addCard(new Card(Suit.SPADE, Rank.ACE));
        twoPair1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        twoPair1.addCard(new Card(Suit.SPADE, Rank.NINE));
        
        FiveCardPokerHand twoPair2 = new FiveCardPokerHand();
        twoPair2.addCard(new Card(Suit.DIAMOND, Rank.ACE));
        twoPair2.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        twoPair2.addCard(new Card(Suit.CLUB, Rank.QUEEN));
        twoPair2.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        twoPair2.addCard(new Card(Suit.CLUB, Rank.SEVEN));        
        
        assertTrue(comparator.compare(twoPair1, twoPair2) < 0);          
    }
    
    @Test
    public void testTwoPairVsTwoPair2() {
        FiveCardPokerHand twoPair1 = new FiveCardPokerHand();
        twoPair1.addCard(new Card(Suit.SPADE, Rank.DEUCE));
        twoPair1.addCard(new Card(Suit.CLUB, Rank.ACE));
        twoPair1.addCard(new Card(Suit.SPADE, Rank.ACE));
        twoPair1.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        twoPair1.addCard(new Card(Suit.SPADE, Rank.THREE));
        
        FiveCardPokerHand twoPair2 = new FiveCardPokerHand();
        twoPair2.addCard(new Card(Suit.DIAMOND, Rank.ACE));
        twoPair2.addCard(new Card(Suit.SPADE, Rank.KING));
        twoPair2.addCard(new Card(Suit.CLUB, Rank.KING));
        twoPair2.addCard(new Card(Suit.DIAMOND, Rank.QUEEN));
        twoPair2.addCard(new Card(Suit.CLUB, Rank.QUEEN));        
        
        assertTrue(comparator.compare(twoPair1, twoPair2) < 0);          
    }
    
    @Test
    public void testTwoPairVsTwoPair3() {
        FiveCardPokerHand twoPair1 = new FiveCardPokerHand();
        twoPair1.addCard(new Card(Suit.SPADE, Rank.JACK));
        twoPair1.addCard(new Card(Suit.HEART, Rank.QUEEN));
        twoPair1.addCard(new Card(Suit.HEART, Rank.KING));
        twoPair1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        twoPair1.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        
        FiveCardPokerHand twoPair2 = new FiveCardPokerHand();
        twoPair2.addCard(new Card(Suit.DIAMOND, Rank.ACE));
        twoPair2.addCard(new Card(Suit.SPADE, Rank.KING));
        twoPair2.addCard(new Card(Suit.CLUB, Rank.KING));
        twoPair2.addCard(new Card(Suit.DIAMOND, Rank.QUEEN));
        twoPair2.addCard(new Card(Suit.CLUB, Rank.QUEEN));        
        
        assertTrue(comparator.compare(twoPair1, twoPair2) > 0);          
    }
    
    @Test
    public void testTwoPairVsTwoPairTie() {
        FiveCardPokerHand twoPair1 = new FiveCardPokerHand();
        twoPair1.addCard(new Card(Suit.SPADE, Rank.JACK));
        twoPair1.addCard(new Card(Suit.HEART, Rank.QUEEN));
        twoPair1.addCard(new Card(Suit.HEART, Rank.THREE));
        twoPair1.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        twoPair1.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        
        FiveCardPokerHand twoPair2 = new FiveCardPokerHand();
        twoPair2.addCard(new Card(Suit.DIAMOND, Rank.JACK));
        twoPair2.addCard(new Card(Suit.SPADE, Rank.THREE));
        twoPair2.addCard(new Card(Suit.CLUB, Rank.THREE));
        twoPair2.addCard(new Card(Suit.DIAMOND, Rank.QUEEN));
        twoPair2.addCard(new Card(Suit.CLUB, Rank.QUEEN));        
        
        assertTrue(comparator.compare(twoPair1, twoPair2) == 0);          
    }
    
    @Test
    public void testThreeOfAKindVsThreeOfAKind() {
        FiveCardPokerHand threeOfAKind1 = new FiveCardPokerHand();
        threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.ACE));
        threeOfAKind1.addCard(new Card(Suit.CLUB, Rank.ACE));
        threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.ACE));
        threeOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.NINE));
        
        FiveCardPokerHand threeOfAKind2 = new FiveCardPokerHand();
        threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.QUEEN));
        threeOfAKind2.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        threeOfAKind2.addCard(new Card(Suit.CLUB, Rank.QUEEN));
        threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        threeOfAKind2.addCard(new Card(Suit.CLUB, Rank.EIGHT));        
        
        assertTrue(comparator.compare(threeOfAKind1, threeOfAKind2) < 0);          
    }
    
    @Test
    public void testThreeOfAKindVsThreeOfAKind2() {
        FiveCardPokerHand threeOfAKind1 = new FiveCardPokerHand();
        threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.THREE));
        threeOfAKind1.addCard(new Card(Suit.CLUB, Rank.THREE));
        threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.THREE));
        threeOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.NINE));
        
        FiveCardPokerHand threeOfAKind2 = new FiveCardPokerHand();
        threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.QUEEN));
        threeOfAKind2.addCard(new Card(Suit.SPADE, Rank.SEVEN));
        threeOfAKind2.addCard(new Card(Suit.CLUB, Rank.SEVEN));
        threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        threeOfAKind2.addCard(new Card(Suit.CLUB, Rank.EIGHT));        
        
        assertTrue(comparator.compare(threeOfAKind1, threeOfAKind2) > 0);          
    }
    
    @Test
    public void testStraightVsStraight() {
        FiveCardPokerHand straight1 = new FiveCardPokerHand();
        straight1.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        straight1.addCard(new Card(Suit.CLUB, Rank.JACK));
        straight1.addCard(new Card(Suit.SPADE, Rank.TEN));
        straight1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        straight1.addCard(new Card(Suit.SPADE, Rank.NINE));
        
        FiveCardPokerHand straight2 = new FiveCardPokerHand();
        straight2.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        straight2.addCard(new Card(Suit.SPADE, Rank.FIVE));
        straight2.addCard(new Card(Suit.CLUB, Rank.SEVEN));
        straight2.addCard(new Card(Suit.DIAMOND, Rank.FOUR));
        straight2.addCard(new Card(Suit.CLUB, Rank.EIGHT));        
        
        assertTrue(comparator.compare(straight1, straight2) < 0);          
    }
    
    @Test
    public void testStraightVsStraight2() {
        FiveCardPokerHand straight1 = new FiveCardPokerHand();
        straight1.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        straight1.addCard(new Card(Suit.CLUB, Rank.JACK));
        straight1.addCard(new Card(Suit.SPADE, Rank.TEN));
        straight1.addCard(new Card(Suit.DIAMOND, Rank.KING));
        straight1.addCard(new Card(Suit.SPADE, Rank.NINE));
        
        FiveCardPokerHand straight2 = new FiveCardPokerHand();
        straight2.addCard(new Card(Suit.DIAMOND, Rank.QUEEN));
        straight2.addCard(new Card(Suit.SPADE, Rank.JACK));
        straight2.addCard(new Card(Suit.CLUB, Rank.TEN));
        straight2.addCard(new Card(Suit.DIAMOND, Rank.NINE));
        straight2.addCard(new Card(Suit.CLUB, Rank.EIGHT));        
        
        assertTrue(comparator.compare(straight1, straight2) < 0);          
    }
    
    @Test
    public void testStraightVsStraight3() {
        FiveCardPokerHand straight1 = new FiveCardPokerHand();
        straight1.addCard(new Card(Suit.SPADE, Rank.ACE));
        straight1.addCard(new Card(Suit.CLUB, Rank.FIVE));
        straight1.addCard(new Card(Suit.SPADE, Rank.FOUR));
        straight1.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        straight1.addCard(new Card(Suit.SPADE, Rank.THREE));
        
        FiveCardPokerHand straight2 = new FiveCardPokerHand();
        straight2.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        straight2.addCard(new Card(Suit.SPADE, Rank.SEVEN));
        straight2.addCard(new Card(Suit.CLUB, Rank.TEN));
        straight2.addCard(new Card(Suit.DIAMOND, Rank.NINE));
        straight2.addCard(new Card(Suit.CLUB, Rank.EIGHT));        
        
        assertTrue(comparator.compare(straight1, straight2) > 0);          
    }
    
    @Test
    public void testStraightVsStraight4() {
        FiveCardPokerHand straight1 = new FiveCardPokerHand();
        straight1.addCard(new Card(Suit.SPADE, Rank.ACE));
        straight1.addCard(new Card(Suit.CLUB, Rank.KING));
        straight1.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        straight1.addCard(new Card(Suit.DIAMOND, Rank.JACK));
        straight1.addCard(new Card(Suit.SPADE, Rank.TEN));
        
        FiveCardPokerHand straight2 = new FiveCardPokerHand();
        straight2.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        straight2.addCard(new Card(Suit.SPADE, Rank.SEVEN));
        straight2.addCard(new Card(Suit.CLUB, Rank.TEN));
        straight2.addCard(new Card(Suit.DIAMOND, Rank.NINE));
        straight2.addCard(new Card(Suit.CLUB, Rank.EIGHT));        
        
        assertTrue(comparator.compare(straight1, straight2) < 0);          
    }
    
    @Test
    public void testStraightVsStraightTie() {
        FiveCardPokerHand straight1 = new FiveCardPokerHand();
        straight1.addCard(new Card(Suit.CLUB, Rank.SIX));
        straight1.addCard(new Card(Suit.CLUB, Rank.FIVE));
        straight1.addCard(new Card(Suit.CLUB, Rank.FOUR));
        straight1.addCard(new Card(Suit.CLUB, Rank.DEUCE));
        straight1.addCard(new Card(Suit.SPADE, Rank.THREE));
        
        FiveCardPokerHand straight2 = new FiveCardPokerHand();
        straight2.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        straight2.addCard(new Card(Suit.SPADE, Rank.FIVE));
        straight2.addCard(new Card(Suit.CLUB, Rank.THREE));
        straight2.addCard(new Card(Suit.DIAMOND, Rank.FOUR));
        straight2.addCard(new Card(Suit.HEART, Rank.DEUCE));        
        
        assertTrue(comparator.compare(straight1, straight2) == 0);          
    }
    
    @Test
    public void testFlushVsFlush() {
        FiveCardPokerHand flush1 = new FiveCardPokerHand();
        flush1.addCard(new Card(Suit.CLUB, Rank.SIX));
        flush1.addCard(new Card(Suit.CLUB, Rank.FIVE));
        flush1.addCard(new Card(Suit.CLUB, Rank.FOUR));
        flush1.addCard(new Card(Suit.CLUB, Rank.DEUCE));
        flush1.addCard(new Card(Suit.CLUB, Rank.NINE));
        
        FiveCardPokerHand flush2 = new FiveCardPokerHand();
        flush2.addCard(new Card(Suit.DIAMOND, Rank.ACE));
        flush2.addCard(new Card(Suit.DIAMOND, Rank.FIVE));
        flush2.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        flush2.addCard(new Card(Suit.DIAMOND, Rank.FOUR));
        flush2.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));        
        
        assertTrue(comparator.compare(flush1, flush2) > 0);          
    }
    
    @Test
    public void testFlushVsFlush2() {
        FiveCardPokerHand flush1 = new FiveCardPokerHand();
        flush1.addCard(new Card(Suit.CLUB, Rank.SIX));
        flush1.addCard(new Card(Suit.CLUB, Rank.TEN));
        flush1.addCard(new Card(Suit.CLUB, Rank.JACK));
        flush1.addCard(new Card(Suit.CLUB, Rank.QUEEN));
        flush1.addCard(new Card(Suit.CLUB, Rank.NINE));
        
        FiveCardPokerHand flush2 = new FiveCardPokerHand();
        flush2.addCard(new Card(Suit.SPADE, Rank.KING));
        flush2.addCard(new Card(Suit.SPADE, Rank.FIVE));
        flush2.addCard(new Card(Suit.SPADE, Rank.JACK));
        flush2.addCard(new Card(Suit.SPADE, Rank.FOUR));
        flush2.addCard(new Card(Suit.SPADE, Rank.DEUCE));        
        
        assertTrue(comparator.compare(flush1, flush2) > 0);          
    }
    
    @Test
    public void testFlushVsFlush3() {
        FiveCardPokerHand flush1 = new FiveCardPokerHand();
        flush1.addCard(new Card(Suit.CLUB, Rank.SIX));
        flush1.addCard(new Card(Suit.CLUB, Rank.TEN));
        flush1.addCard(new Card(Suit.CLUB, Rank.JACK));
        flush1.addCard(new Card(Suit.CLUB, Rank.QUEEN));
        flush1.addCard(new Card(Suit.CLUB, Rank.KING));
        
        FiveCardPokerHand flush2 = new FiveCardPokerHand();
        flush2.addCard(new Card(Suit.SPADE, Rank.KING));
        flush2.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        flush2.addCard(new Card(Suit.SPADE, Rank.JACK));
        flush2.addCard(new Card(Suit.SPADE, Rank.TEN));
        flush2.addCard(new Card(Suit.SPADE, Rank.EIGHT));        
        
        assertTrue(comparator.compare(flush1, flush2) > 0);          
    }
    
    @Test
    public void testFlushVsFlushTie() {
        FiveCardPokerHand flush1 = new FiveCardPokerHand();
        flush1.addCard(new Card(Suit.CLUB, Rank.SIX));
        flush1.addCard(new Card(Suit.CLUB, Rank.TEN));
        flush1.addCard(new Card(Suit.CLUB, Rank.JACK));
        flush1.addCard(new Card(Suit.CLUB, Rank.QUEEN));
        flush1.addCard(new Card(Suit.CLUB, Rank.KING));
        
        FiveCardPokerHand flush2 = new FiveCardPokerHand();
        flush2.addCard(new Card(Suit.SPADE, Rank.KING));
        flush2.addCard(new Card(Suit.SPADE, Rank.QUEEN));
        flush2.addCard(new Card(Suit.SPADE, Rank.JACK));
        flush2.addCard(new Card(Suit.SPADE, Rank.TEN));
        flush2.addCard(new Card(Suit.SPADE, Rank.SIX));        
        
        assertTrue(comparator.compare(flush1, flush2) == 0);          
    }
    
    @Test
    public void testFullHouseVsFullHouse() {
        FiveCardPokerHand fullHouse1 = new FiveCardPokerHand();
        fullHouse1.addCard(new Card(Suit.CLUB, Rank.SIX));
        fullHouse1.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        fullHouse1.addCard(new Card(Suit.SPADE, Rank.SIX));
        fullHouse1.addCard(new Card(Suit.CLUB, Rank.KING));
        fullHouse1.addCard(new Card(Suit.SPADE, Rank.KING));
        
        FiveCardPokerHand fullHouse2 = new FiveCardPokerHand();
        fullHouse2.addCard(new Card(Suit.HEART, Rank.THREE));
        fullHouse2.addCard(new Card(Suit.SPADE, Rank.THREE));
        fullHouse2.addCard(new Card(Suit.CLUB, Rank.THREE));
        fullHouse2.addCard(new Card(Suit.HEART, Rank.TEN));
        fullHouse2.addCard(new Card(Suit.CLUB, Rank.TEN));        
        
        assertTrue(comparator.compare(fullHouse1, fullHouse2) < 0);          
    }
}