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
    public void testDetermineHandTypeHighCard() {
        FiveCardPokerHand highCard = new FiveCardPokerHand();
        assertTrue(highCard.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(highCard.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(highCard.addCard(new Card(Suit.SPADE, Rank.JACK)));
        assertTrue(highCard.addCard(new Card(Suit.DIAMOND, Rank.TEN)));
        assertTrue(highCard.addCard(new Card(Suit.SPADE, Rank.NINE)));

        assertEquals(PokerHandType.HIGH_CARD, comparator.determineHandType(highCard));
    }

    @Test
    public void testDetermineHandTypePair() {
        FiveCardPokerHand pair1 = new FiveCardPokerHand();
        assertTrue(pair1.addCard(new Card(Suit.SPADE, Rank.KING)));
        assertTrue(pair1.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(pair1.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(pair1.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(pair1.addCard(new Card(Suit.SPADE, Rank.NINE)));

        assertEquals(PokerHandType.PAIR, comparator.determineHandType(pair1));
    }

    @Test
    public void testDetermineHandTypeTwoPair() {
        FiveCardPokerHand twoPair2 = new FiveCardPokerHand();
        assertTrue(twoPair2.addCard(new Card(Suit.DIAMOND, Rank.QUEEN)));
        assertTrue(twoPair2.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(twoPair2.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(twoPair2.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(twoPair2.addCard(new Card(Suit.CLUB, Rank.SEVEN)));
        
        assertEquals(PokerHandType.TWO_PAIR, comparator.determineHandType(twoPair2));
    }
    
    @Test
    public void testDetermineHandTypeThreeOfAKind() {
        FiveCardPokerHand threeOfAKind2 = new FiveCardPokerHand();
        assertTrue(threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.QUEEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.CLUB, Rank.SEVEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.CLUB, Rank.EIGHT)));        
        assertTrue(threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));        
        
        assertEquals(PokerHandType.THREE_OF_A_KIND, comparator.determineHandType(threeOfAKind2));        
    }
    
    @Test
    public void testDetermineHandValueStraight() {
        FiveCardPokerHand straight1 = new FiveCardPokerHand();
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(straight1.addCard(new Card(Suit.CLUB, Rank.FIVE)));
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.FOUR)));
        assertTrue(straight1.addCard(new Card(Suit.DIAMOND, Rank.DEUCE)));
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.THREE)));
        
        assertEquals(PokerHandType.STRAIGHT, comparator.determineHandType(straight1));
    }
    
    @Test
    public void testDetermineHandValueStraight2() {
        FiveCardPokerHand straight2 = new FiveCardPokerHand();
        assertTrue(straight2.addCard(new Card(Suit.DIAMOND, Rank.SIX)));
        assertTrue(straight2.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(straight2.addCard(new Card(Suit.CLUB, Rank.TEN)));
        assertTrue(straight2.addCard(new Card(Suit.DIAMOND, Rank.NINE)));
        assertTrue(straight2.addCard(new Card(Suit.CLUB, Rank.EIGHT)));        
        
        assertEquals(PokerHandType.STRAIGHT, comparator.determineHandType(straight2));        
    }
    
    @Test
    public void testDetermineHandValueStraight3() {
        FiveCardPokerHand straight1 = new FiveCardPokerHand();
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(straight1.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.KING)));
        assertTrue(straight1.addCard(new Card(Suit.DIAMOND, Rank.JACK)));
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.TEN)));    
        
        assertEquals(PokerHandType.STRAIGHT, comparator.determineHandType(straight1));        
    }
    
    @Test
    public void testDetermineHandValueFlush() {
        FiveCardPokerHand flush1 = new FiveCardPokerHand();
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.SIX)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.TEN)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.JACK)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.KING)));
        
        assertEquals(PokerHandType.FLUSH, comparator.determineHandType(flush1));
    }
    
    @Test
    public void testDetermineHandValueFullHouse() {
        FiveCardPokerHand fullHouse1 = new FiveCardPokerHand();
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(fullHouse1.addCard(new Card(Suit.DIAMOND, Rank.SIX)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.KING)));
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.SIX)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.SIX)));
        
        assertEquals(PokerHandType.FULL_HOUSE, comparator.determineHandType(fullHouse1));
    }
    
    @Test
    public void testDetermineHandValueFullHouse2() {
        FiveCardPokerHand fullHouse2 = new FiveCardPokerHand();
        assertTrue(fullHouse2.addCard(new Card(Suit.HEART, Rank.QUEEN)));
        assertTrue(fullHouse2.addCard(new Card(Suit.SPADE, Rank.TEN)));
        assertTrue(fullHouse2.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(fullHouse2.addCard(new Card(Suit.HEART, Rank.TEN)));
        assertTrue(fullHouse2.addCard(new Card(Suit.CLUB, Rank.TEN)));         
        
        assertEquals(PokerHandType.FULL_HOUSE, comparator.determineHandType(fullHouse2));        
    }
    
    @Test
    public void testDetermineHandValueFullHouse3() {
        FiveCardPokerHand fullHouse2 = new FiveCardPokerHand();
        assertTrue(fullHouse2.addCard(new Card(Suit.HEART, Rank.ACE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.SPADE, Rank.TEN)));
        assertTrue(fullHouse2.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.HEART, Rank.TEN)));
        assertTrue(fullHouse2.addCard(new Card(Suit.DIAMOND, Rank.ACE)));  
        
        assertEquals(PokerHandType.FULL_HOUSE, comparator.determineHandType(fullHouse2));          
    }
    
    @Test
    public void testDetermineHandValueFourOfAKind() {
        FiveCardPokerHand fourOfAKind1 = new FiveCardPokerHand();
        assertTrue(fourOfAKind1.addCard(new Card(Suit.CLUB, Rank.SEVEN)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.HEART, Rank.SEVEN)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.SPADE, Rank.DEUCE)));    
        
        assertEquals(PokerHandType.FOUR_OF_A_KIND, comparator.determineHandType(fourOfAKind1));
    }
    
    @Test
    public void testDetermineHandValueFourOfAKind2() {
        FiveCardPokerHand fourOfAKind2 = new FiveCardPokerHand();
        assertTrue(fourOfAKind2.addCard(new Card(Suit.HEART, Rank.SIX)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.SPADE, Rank.SIX)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.CLUB, Rank.SIX)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.HEART, Rank.ACE)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.SIX)));           
        
        assertEquals(PokerHandType.FOUR_OF_A_KIND, comparator.determineHandType(fourOfAKind2));        
    }
    
    @Test
    public void testDetermineHandValueFourOfAKind3() {
        FiveCardPokerHand fourOfAKind2 = new FiveCardPokerHand();
        assertTrue(fourOfAKind2.addCard(new Card(Suit.HEART, Rank.ACE)));        
        assertTrue(fourOfAKind2.addCard(new Card(Suit.HEART, Rank.SIX)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.SPADE, Rank.SIX)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.CLUB, Rank.SIX)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.SIX)));  
        
        assertEquals(PokerHandType.FOUR_OF_A_KIND, comparator.determineHandType(fourOfAKind2));        
    }
    
    @Test
    public void testDetermineHandValueStraightFlush() {
        FiveCardPokerHand straightFlush1 = new FiveCardPokerHand();
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.JACK)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.TEN)));
        
        assertEquals(PokerHandType.STRAIGHT_FLUSH, comparator.determineHandType(straightFlush1));
    }
    
    @Test
    public void testDetermineHandValueStraightFlush2() {
        FiveCardPokerHand straightFlush2 = new FiveCardPokerHand();
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.ACE)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.DEUCE)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.FIVE)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.FOUR)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.THREE)));                
        
        assertEquals(PokerHandType.STRAIGHT_FLUSH, comparator.determineHandType(straightFlush2));        
    }
    
    @Test
    public void testDetermineHandValueStraightFlush3() {
        FiveCardPokerHand straightFlush2 = new FiveCardPokerHand();
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.EIGHT)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.SEVEN)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.FIVE)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.FOUR)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.SIX)));                
        
        assertEquals(PokerHandType.STRAIGHT_FLUSH, comparator.determineHandType(straightFlush2));        
    }
    
    @Test
    public void testOrderOfHandTypes() {
        List<FiveCardPokerHand> hands = new ArrayList<FiveCardPokerHand>();
        List<FiveCardPokerHand> expectedOrder = new ArrayList<FiveCardPokerHand>();

        // Some hands have overlapping cards, but it doesn't matter while testing

        FiveCardPokerHand highCard = new FiveCardPokerHand();
        assertTrue(highCard.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(highCard.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(highCard.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(highCard.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(highCard.addCard(new Card(Suit.SPADE, Rank.NINE)));
        hands.add(highCard);
        expectedOrder.add(highCard);

        FiveCardPokerHand pair = new FiveCardPokerHand();
        assertTrue(pair.addCard(new Card(Suit.HEART, Rank.DEUCE)));
        assertTrue(pair.addCard(new Card(Suit.CLUB, Rank.DEUCE)));
        assertTrue(pair.addCard(new Card(Suit.SPADE, Rank.THREE)));
        assertTrue(pair.addCard(new Card(Suit.HEART, Rank.FOUR)));
        assertTrue(pair.addCard(new Card(Suit.HEART, Rank.FIVE)));
        hands.add(pair);
        expectedOrder.add(pair);

        FiveCardPokerHand twoPair = new FiveCardPokerHand();
        assertTrue(twoPair.addCard(new Card(Suit.HEART, Rank.FOUR)));
        assertTrue(twoPair.addCard(new Card(Suit.CLUB, Rank.FOUR)));
        assertTrue(twoPair.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(twoPair.addCard(new Card(Suit.HEART, Rank.SEVEN)));
        assertTrue(twoPair.addCard(new Card(Suit.DIAMOND, Rank.FIVE)));
        hands.add(twoPair);
        expectedOrder.add(twoPair);

        FiveCardPokerHand threeOfAKind = new FiveCardPokerHand();
        assertTrue(threeOfAKind.addCard(new Card(Suit.HEART, Rank.TEN)));
        assertTrue(threeOfAKind.addCard(new Card(Suit.CLUB, Rank.TEN)));
        assertTrue(threeOfAKind.addCard(new Card(Suit.SPADE, Rank.TEN)));
        assertTrue(threeOfAKind.addCard(new Card(Suit.DIAMOND, Rank.FOUR)));
        assertTrue(threeOfAKind.addCard(new Card(Suit.CLUB, Rank.FIVE)));
        hands.add(threeOfAKind);
        expectedOrder.add(threeOfAKind);

        FiveCardPokerHand straight = new FiveCardPokerHand();
        assertTrue(straight.addCard(new Card(Suit.HEART, Rank.EIGHT)));
        assertTrue(straight.addCard(new Card(Suit.CLUB, Rank.SEVEN)));
        assertTrue(straight.addCard(new Card(Suit.SPADE, Rank.SIX)));
        assertTrue(straight.addCard(new Card(Suit.HEART, Rank.NINE)));
        assertTrue(straight.addCard(new Card(Suit.SPADE, Rank.FIVE)));
        hands.add(straight);
        expectedOrder.add(straight);

        FiveCardPokerHand flush = new FiveCardPokerHand();
        assertTrue(flush.addCard(new Card(Suit.DIAMOND, Rank.DEUCE)));
        assertTrue(flush.addCard(new Card(Suit.DIAMOND, Rank.QUEEN)));
        assertTrue(flush.addCard(new Card(Suit.DIAMOND, Rank.ACE)));
        assertTrue(flush.addCard(new Card(Suit.DIAMOND, Rank.SIX)));
        assertTrue(flush.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        hands.add(flush);
        expectedOrder.add(flush);

        FiveCardPokerHand fullHouse = new FiveCardPokerHand();
        assertTrue(fullHouse.addCard(new Card(Suit.HEART, Rank.SIX)));
        assertTrue(fullHouse.addCard(new Card(Suit.CLUB, Rank.SIX)));
        assertTrue(fullHouse.addCard(new Card(Suit.SPADE, Rank.SIX)));
        assertTrue(fullHouse.addCard(new Card(Suit.HEART, Rank.QUEEN)));
        assertTrue(fullHouse.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        hands.add(fullHouse);
        expectedOrder.add(fullHouse);

        FiveCardPokerHand fourOfAKind = new FiveCardPokerHand();
        assertTrue(fourOfAKind.addCard(new Card(Suit.HEART, Rank.JACK)));
        assertTrue(fourOfAKind.addCard(new Card(Suit.CLUB, Rank.JACK)));
        assertTrue(fourOfAKind.addCard(new Card(Suit.SPADE, Rank.JACK)));
        assertTrue(fourOfAKind.addCard(new Card(Suit.DIAMOND, Rank.JACK)));
        assertTrue(fourOfAKind.addCard(new Card(Suit.HEART, Rank.ACE)));
        hands.add(fourOfAKind);
        expectedOrder.add(fourOfAKind);

        FiveCardPokerHand straightFlush = new FiveCardPokerHand();
        assertTrue(straightFlush.addCard(new Card(Suit.HEART, Rank.SIX)));
        assertTrue(straightFlush.addCard(new Card(Suit.HEART, Rank.DEUCE)));
        assertTrue(straightFlush.addCard(new Card(Suit.HEART, Rank.THREE)));
        assertTrue(straightFlush.addCard(new Card(Suit.HEART, Rank.FOUR)));
        assertTrue(straightFlush.addCard(new Card(Suit.HEART, Rank.FIVE)));
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
        assertTrue(highCard.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(highCard.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(highCard.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(highCard.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(highCard.addCard(new Card(Suit.SPADE, Rank.NINE)));


        FiveCardPokerHand highCard2 = new FiveCardPokerHand();
        assertTrue(highCard2.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(highCard2.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        assertTrue(highCard2.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(highCard2.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(highCard2.addCard(new Card(Suit.CLUB, Rank.NINE)));

        assertTrue(comparator.compare(highCard, highCard2) < 0);
    }

    @Test
    public void testHighCardVsHighCard2() {
        FiveCardPokerHand highCard = new FiveCardPokerHand();
        assertTrue(highCard.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(highCard.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(highCard.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(highCard.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(highCard.addCard(new Card(Suit.SPADE, Rank.NINE)));


        FiveCardPokerHand highCard2 = new FiveCardPokerHand();
        assertTrue(highCard2.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(highCard2.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        assertTrue(highCard2.addCard(new Card(Suit.DIAMOND, Rank.SIX)));
        assertTrue(highCard2.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(highCard2.addCard(new Card(Suit.CLUB, Rank.NINE)));

        assertTrue(comparator.compare(highCard, highCard2) < 0);
    }

    @Test
    public void testHighCardVsHighCardTie() {
        FiveCardPokerHand highCard = new FiveCardPokerHand();
        assertTrue(highCard.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(highCard.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(highCard.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(highCard.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(highCard.addCard(new Card(Suit.SPADE, Rank.NINE)));

        FiveCardPokerHand highCard2 = new FiveCardPokerHand();
        assertTrue(highCard2.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(highCard2.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        assertTrue(highCard2.addCard(new Card(Suit.CLUB, Rank.NINE)));
        assertTrue(highCard2.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(highCard2.addCard(new Card(Suit.CLUB, Rank.KING)));

        assertTrue(comparator.compare(highCard, highCard2) == 0);
    }

    @Test
    public void testPairVsPair() {
        FiveCardPokerHand pair1 = new FiveCardPokerHand();
        assertTrue(pair1.addCard(new Card(Suit.SPADE, Rank.KING)));
        assertTrue(pair1.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(pair1.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(pair1.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(pair1.addCard(new Card(Suit.SPADE, Rank.NINE)));

        FiveCardPokerHand pair2 = new FiveCardPokerHand();
        assertTrue(pair2.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(pair2.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        assertTrue(pair2.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(pair2.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(pair2.addCard(new Card(Suit.CLUB, Rank.KING)));

        assertTrue(comparator.compare(pair1, pair2) < 0);
    }

    @Test
    public void testPairVsPair2() {
        FiveCardPokerHand pair1 = new FiveCardPokerHand();
        assertTrue(pair1.addCard(new Card(Suit.SPADE, Rank.KING)));
        assertTrue(pair1.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(pair1.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(pair1.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(pair1.addCard(new Card(Suit.SPADE, Rank.NINE)));

        FiveCardPokerHand pair2 = new FiveCardPokerHand();
        assertTrue(pair2.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(pair2.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        assertTrue(pair2.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(pair2.addCard(new Card(Suit.DIAMOND, Rank.SIX)));
        assertTrue(pair2.addCard(new Card(Suit.CLUB, Rank.KING)));

        assertTrue(comparator.compare(pair1, pair2) > 0);
    }

    @Test
    public void testPairVsPair3() {
        FiveCardPokerHand pair1 = new FiveCardPokerHand();
        assertTrue(pair1.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(pair1.addCard(new Card(Suit.HEART, Rank.QUEEN)));
        assertTrue(pair1.addCard(new Card(Suit.DIAMOND, Rank.QUEEN)));
        assertTrue(pair1.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(pair1.addCard(new Card(Suit.SPADE, Rank.NINE)));

        FiveCardPokerHand pair2 = new FiveCardPokerHand();
        assertTrue(pair2.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(pair2.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        assertTrue(pair2.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(pair2.addCard(new Card(Suit.DIAMOND, Rank.SIX)));
        assertTrue(pair2.addCard(new Card(Suit.CLUB, Rank.KING)));

        assertTrue(comparator.compare(pair1, pair2) > 0);
    }

    @Test
    public void testPairVsPairTie() {
        FiveCardPokerHand pair1 = new FiveCardPokerHand();
        assertTrue(pair1.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(pair1.addCard(new Card(Suit.HEART, Rank.QUEEN)));
        assertTrue(pair1.addCard(new Card(Suit.DIAMOND, Rank.QUEEN)));
        assertTrue(pair1.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(pair1.addCard(new Card(Suit.SPADE, Rank.NINE)));

        FiveCardPokerHand pair2 = new FiveCardPokerHand();
        assertTrue(pair2.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(pair2.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        assertTrue(pair2.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(pair2.addCard(new Card(Suit.DIAMOND, Rank.NINE)));
        assertTrue(pair2.addCard(new Card(Suit.CLUB, Rank.SEVEN)));

        assertTrue(comparator.compare(pair1, pair2) == 0);
    }

    @Test
    public void testTwoPairVsTwoPair() {
        FiveCardPokerHand twoPair1 = new FiveCardPokerHand();
        assertTrue(twoPair1.addCard(new Card(Suit.SPADE, Rank.KING)));
        assertTrue(twoPair1.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(twoPair1.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(twoPair1.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(twoPair1.addCard(new Card(Suit.SPADE, Rank.NINE)));

        FiveCardPokerHand twoPair2 = new FiveCardPokerHand();
        assertTrue(twoPair2.addCard(new Card(Suit.DIAMOND, Rank.ACE)));
        assertTrue(twoPair2.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        assertTrue(twoPair2.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(twoPair2.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(twoPair2.addCard(new Card(Suit.CLUB, Rank.SEVEN)));

        assertTrue(comparator.compare(twoPair1, twoPair2) < 0);
    }

    @Test
    public void testTwoPairVsTwoPair2() {
        FiveCardPokerHand twoPair1 = new FiveCardPokerHand();
        assertTrue(twoPair1.addCard(new Card(Suit.SPADE, Rank.DEUCE)));
        assertTrue(twoPair1.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(twoPair1.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(twoPair1.addCard(new Card(Suit.DIAMOND, Rank.DEUCE)));
        assertTrue(twoPair1.addCard(new Card(Suit.SPADE, Rank.THREE)));

        FiveCardPokerHand twoPair2 = new FiveCardPokerHand();
        assertTrue(twoPair2.addCard(new Card(Suit.DIAMOND, Rank.ACE)));
        assertTrue(twoPair2.addCard(new Card(Suit.SPADE, Rank.KING)));
        assertTrue(twoPair2.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(twoPair2.addCard(new Card(Suit.DIAMOND, Rank.QUEEN)));
        assertTrue(twoPair2.addCard(new Card(Suit.CLUB, Rank.QUEEN)));

        assertTrue(comparator.compare(twoPair1, twoPair2) < 0);
    }

    @Test
    public void testTwoPairVsTwoPair3() {
        FiveCardPokerHand twoPair1 = new FiveCardPokerHand();
        assertTrue(twoPair1.addCard(new Card(Suit.SPADE, Rank.JACK)));
        assertTrue(twoPair1.addCard(new Card(Suit.HEART, Rank.QUEEN)));
        assertTrue(twoPair1.addCard(new Card(Suit.HEART, Rank.KING)));
        assertTrue(twoPair1.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(twoPair1.addCard(new Card(Suit.SPADE, Rank.QUEEN)));

        FiveCardPokerHand twoPair2 = new FiveCardPokerHand();
        assertTrue(twoPair2.addCard(new Card(Suit.DIAMOND, Rank.ACE)));
        assertTrue(twoPair2.addCard(new Card(Suit.SPADE, Rank.KING)));
        assertTrue(twoPair2.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(twoPair2.addCard(new Card(Suit.DIAMOND, Rank.QUEEN)));
        assertTrue(twoPair2.addCard(new Card(Suit.CLUB, Rank.QUEEN)));

        assertTrue(comparator.compare(twoPair1, twoPair2) > 0);
    }

    @Test
    public void testTwoPairVsTwoPairTie() {
        FiveCardPokerHand twoPair1 = new FiveCardPokerHand();
        assertTrue(twoPair1.addCard(new Card(Suit.SPADE, Rank.JACK)));
        assertTrue(twoPair1.addCard(new Card(Suit.HEART, Rank.QUEEN)));
        assertTrue(twoPair1.addCard(new Card(Suit.HEART, Rank.THREE)));
        assertTrue(twoPair1.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(twoPair1.addCard(new Card(Suit.SPADE, Rank.QUEEN)));

        FiveCardPokerHand twoPair2 = new FiveCardPokerHand();
        assertTrue(twoPair2.addCard(new Card(Suit.DIAMOND, Rank.JACK)));
        assertTrue(twoPair2.addCard(new Card(Suit.SPADE, Rank.THREE)));
        assertTrue(twoPair2.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(twoPair2.addCard(new Card(Suit.DIAMOND, Rank.QUEEN)));
        assertTrue(twoPair2.addCard(new Card(Suit.CLUB, Rank.QUEEN)));

        assertTrue(comparator.compare(twoPair1, twoPair2) == 0);
    }

    @Test
    public void testThreeOfAKindVsThreeOfAKind() {
        FiveCardPokerHand threeOfAKind1 = new FiveCardPokerHand();
        assertTrue(threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.ACE)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.NINE)));

        FiveCardPokerHand threeOfAKind2 = new FiveCardPokerHand();
        assertTrue(threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.QUEEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.CLUB, Rank.EIGHT)));

        assertTrue(comparator.compare(threeOfAKind1, threeOfAKind2) < 0);
    }

    @Test
    public void testThreeOfAKindVsThreeOfAKind2() {
        FiveCardPokerHand threeOfAKind1 = new FiveCardPokerHand();
        assertTrue(threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.THREE)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.NINE)));

        FiveCardPokerHand threeOfAKind2 = new FiveCardPokerHand();
        assertTrue(threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.QUEEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.CLUB, Rank.SEVEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.CLUB, Rank.EIGHT)));

        assertTrue(comparator.compare(threeOfAKind1, threeOfAKind2) > 0);
    }

    @Test
    public void testStraightVsStraight() {
        FiveCardPokerHand straight1 = new FiveCardPokerHand();
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        assertTrue(straight1.addCard(new Card(Suit.CLUB, Rank.JACK)));
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.TEN)));
        assertTrue(straight1.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.NINE)));

        FiveCardPokerHand straight2 = new FiveCardPokerHand();
        assertTrue(straight2.addCard(new Card(Suit.DIAMOND, Rank.SIX)));
        assertTrue(straight2.addCard(new Card(Suit.SPADE, Rank.FIVE)));
        assertTrue(straight2.addCard(new Card(Suit.CLUB, Rank.SEVEN)));
        assertTrue(straight2.addCard(new Card(Suit.DIAMOND, Rank.FOUR)));
        assertTrue(straight2.addCard(new Card(Suit.CLUB, Rank.EIGHT)));

        assertTrue(comparator.compare(straight1, straight2) < 0);
    }

    @Test
    public void testStraightVsStraight2() {
        FiveCardPokerHand straight1 = new FiveCardPokerHand();
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        assertTrue(straight1.addCard(new Card(Suit.CLUB, Rank.JACK)));
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.TEN)));
        assertTrue(straight1.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.NINE)));

        FiveCardPokerHand straight2 = new FiveCardPokerHand();
        assertTrue(straight2.addCard(new Card(Suit.DIAMOND, Rank.QUEEN)));
        assertTrue(straight2.addCard(new Card(Suit.SPADE, Rank.JACK)));
        assertTrue(straight2.addCard(new Card(Suit.CLUB, Rank.TEN)));
        assertTrue(straight2.addCard(new Card(Suit.DIAMOND, Rank.NINE)));
        assertTrue(straight2.addCard(new Card(Suit.CLUB, Rank.EIGHT)));

        assertTrue(comparator.compare(straight1, straight2) < 0);
    }

    @Test
    public void testStraightVsStraight3() {
        FiveCardPokerHand straight1 = new FiveCardPokerHand();
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(straight1.addCard(new Card(Suit.CLUB, Rank.FIVE)));
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.FOUR)));
        assertTrue(straight1.addCard(new Card(Suit.DIAMOND, Rank.DEUCE)));
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.THREE)));

        FiveCardPokerHand straight2 = new FiveCardPokerHand();
        assertTrue(straight2.addCard(new Card(Suit.DIAMOND, Rank.SIX)));
        assertTrue(straight2.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(straight2.addCard(new Card(Suit.CLUB, Rank.TEN)));
        assertTrue(straight2.addCard(new Card(Suit.DIAMOND, Rank.NINE)));
        assertTrue(straight2.addCard(new Card(Suit.CLUB, Rank.EIGHT)));

        assertTrue(comparator.compare(straight1, straight2) > 0);
    }

    @Test
    public void testStraightVsStraight4() {
        FiveCardPokerHand straight1 = new FiveCardPokerHand();
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(straight1.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        assertTrue(straight1.addCard(new Card(Suit.DIAMOND, Rank.JACK)));
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.TEN)));

        FiveCardPokerHand straight2 = new FiveCardPokerHand();
        assertTrue(straight2.addCard(new Card(Suit.DIAMOND, Rank.SIX)));
        assertTrue(straight2.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(straight2.addCard(new Card(Suit.CLUB, Rank.TEN)));
        assertTrue(straight2.addCard(new Card(Suit.DIAMOND, Rank.NINE)));
        assertTrue(straight2.addCard(new Card(Suit.CLUB, Rank.EIGHT)));

        assertTrue(comparator.compare(straight1, straight2) < 0);
    }

    @Test
    public void testStraightVsStraight5() {
        FiveCardPokerHand straight1 = new FiveCardPokerHand();
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(straight1.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        assertTrue(straight1.addCard(new Card(Suit.DIAMOND, Rank.JACK)));
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.TEN)));

        FiveCardPokerHand straight2 = new FiveCardPokerHand();
        assertTrue(straight2.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(straight2.addCard(new Card(Suit.SPADE, Rank.JACK)));
        assertTrue(straight2.addCard(new Card(Suit.CLUB, Rank.TEN)));
        assertTrue(straight2.addCard(new Card(Suit.DIAMOND, Rank.QUEEN)));
        assertTrue(straight2.addCard(new Card(Suit.CLUB, Rank.NINE)));

        assertTrue(comparator.compare(straight1, straight2) < 0);
    }

    @Test
    public void testStraightVsStraightTie() {
        FiveCardPokerHand straight1 = new FiveCardPokerHand();
        assertTrue(straight1.addCard(new Card(Suit.CLUB, Rank.SIX)));
        assertTrue(straight1.addCard(new Card(Suit.CLUB, Rank.FIVE)));
        assertTrue(straight1.addCard(new Card(Suit.CLUB, Rank.FOUR)));
        assertTrue(straight1.addCard(new Card(Suit.CLUB, Rank.DEUCE)));
        assertTrue(straight1.addCard(new Card(Suit.SPADE, Rank.THREE)));

        FiveCardPokerHand straight2 = new FiveCardPokerHand();
        assertTrue(straight2.addCard(new Card(Suit.DIAMOND, Rank.SIX)));
        assertTrue(straight2.addCard(new Card(Suit.SPADE, Rank.FIVE)));
        assertTrue(straight2.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(straight2.addCard(new Card(Suit.DIAMOND, Rank.FOUR)));
        assertTrue(straight2.addCard(new Card(Suit.HEART, Rank.DEUCE)));

        assertTrue(comparator.compare(straight1, straight2) == 0);
    }

    @Test
    public void testFlushVsFlush() {
        FiveCardPokerHand flush1 = new FiveCardPokerHand();
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.SIX)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.FIVE)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.FOUR)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.DEUCE)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.NINE)));

        FiveCardPokerHand flush2 = new FiveCardPokerHand();
        assertTrue(flush2.addCard(new Card(Suit.DIAMOND, Rank.ACE)));
        assertTrue(flush2.addCard(new Card(Suit.DIAMOND, Rank.FIVE)));
        assertTrue(flush2.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(flush2.addCard(new Card(Suit.DIAMOND, Rank.FOUR)));
        assertTrue(flush2.addCard(new Card(Suit.DIAMOND, Rank.SIX)));

        assertTrue(comparator.compare(flush1, flush2) > 0);
    }

    @Test
    public void testFlushVsFlush2() {
        FiveCardPokerHand flush1 = new FiveCardPokerHand();
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.SIX)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.TEN)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.JACK)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.NINE)));

        FiveCardPokerHand flush2 = new FiveCardPokerHand();
        assertTrue(flush2.addCard(new Card(Suit.SPADE, Rank.KING)));
        assertTrue(flush2.addCard(new Card(Suit.SPADE, Rank.FIVE)));
        assertTrue(flush2.addCard(new Card(Suit.SPADE, Rank.JACK)));
        assertTrue(flush2.addCard(new Card(Suit.SPADE, Rank.FOUR)));
        assertTrue(flush2.addCard(new Card(Suit.SPADE, Rank.DEUCE)));

        assertTrue(comparator.compare(flush1, flush2) > 0);
    }

    @Test
    public void testFlushVsFlush3() {
        FiveCardPokerHand flush1 = new FiveCardPokerHand();
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.SIX)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.TEN)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.JACK)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.KING)));

        FiveCardPokerHand flush2 = new FiveCardPokerHand();
        assertTrue(flush2.addCard(new Card(Suit.SPADE, Rank.KING)));
        assertTrue(flush2.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        assertTrue(flush2.addCard(new Card(Suit.SPADE, Rank.JACK)));
        assertTrue(flush2.addCard(new Card(Suit.SPADE, Rank.TEN)));
        assertTrue(flush2.addCard(new Card(Suit.SPADE, Rank.EIGHT)));

        assertTrue(comparator.compare(flush1, flush2) > 0);
    }

    @Test
    public void testFlushVsFlushTie() {
        FiveCardPokerHand flush1 = new FiveCardPokerHand();
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.SIX)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.TEN)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.JACK)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(flush1.addCard(new Card(Suit.CLUB, Rank.KING)));

        FiveCardPokerHand flush2 = new FiveCardPokerHand();
        assertTrue(flush2.addCard(new Card(Suit.SPADE, Rank.KING)));
        assertTrue(flush2.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        assertTrue(flush2.addCard(new Card(Suit.SPADE, Rank.JACK)));
        assertTrue(flush2.addCard(new Card(Suit.SPADE, Rank.TEN)));
        assertTrue(flush2.addCard(new Card(Suit.SPADE, Rank.SIX)));

        assertTrue(comparator.compare(flush1, flush2) == 0);
    }

    @Test
    public void testFullHouseVsFullHouse() {
        FiveCardPokerHand fullHouse1 = new FiveCardPokerHand();
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.SIX)));
        assertTrue(fullHouse1.addCard(new Card(Suit.DIAMOND, Rank.SIX)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.SIX)));
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.KING)));

        FiveCardPokerHand fullHouse2 = new FiveCardPokerHand();
        assertTrue(fullHouse2.addCard(new Card(Suit.HEART, Rank.THREE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.SPADE, Rank.THREE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.HEART, Rank.TEN)));
        assertTrue(fullHouse2.addCard(new Card(Suit.CLUB, Rank.TEN)));

        assertTrue(comparator.compare(fullHouse1, fullHouse2) < 0);
    }

    @Test
    public void testFullHouseVsFullHouse2() {
        FiveCardPokerHand fullHouse1 = new FiveCardPokerHand();
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.SIX)));
        assertTrue(fullHouse1.addCard(new Card(Suit.DIAMOND, Rank.SIX)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.SIX)));
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.KING)));

        FiveCardPokerHand fullHouse2 = new FiveCardPokerHand();
        assertTrue(fullHouse2.addCard(new Card(Suit.HEART, Rank.QUEEN)));
        assertTrue(fullHouse2.addCard(new Card(Suit.SPADE, Rank.QUEEN)));
        assertTrue(fullHouse2.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(fullHouse2.addCard(new Card(Suit.HEART, Rank.TEN)));
        assertTrue(fullHouse2.addCard(new Card(Suit.CLUB, Rank.TEN)));

        assertTrue(comparator.compare(fullHouse1, fullHouse2) > 0);
    }

    @Test
    public void testFullHouseVsFullHouse3() {
        FiveCardPokerHand fullHouse1 = new FiveCardPokerHand();
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.DIAMOND, Rank.ACE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.DEUCE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.DEUCE)));

        FiveCardPokerHand fullHouse2 = new FiveCardPokerHand();
        assertTrue(fullHouse2.addCard(new Card(Suit.HEART, Rank.KING)));
        assertTrue(fullHouse2.addCard(new Card(Suit.SPADE, Rank.KING)));
        assertTrue(fullHouse2.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(fullHouse2.addCard(new Card(Suit.HEART, Rank.DEUCE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.DIAMOND, Rank.DEUCE)));

        assertTrue(comparator.compare(fullHouse1, fullHouse2) < 0);
    }

    @Test
    public void testFourOfAKindVsFourOfAKind() {
        FiveCardPokerHand fourOfAKind1 = new FiveCardPokerHand();
        assertTrue(fourOfAKind1.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.ACE)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.HEART, Rank.ACE)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.SPADE, Rank.DEUCE)));

        FiveCardPokerHand fourOfAKind2 = new FiveCardPokerHand();
        assertTrue(fourOfAKind2.addCard(new Card(Suit.HEART, Rank.KING)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.SPADE, Rank.KING)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.HEART, Rank.QUEEN)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.KING)));

        assertTrue(comparator.compare(fourOfAKind1, fourOfAKind2) < 0);
    }

    @Test
    public void testFourOfAKindVsFourOfAKind2() {
        FiveCardPokerHand fourOfAKind1 = new FiveCardPokerHand();
        assertTrue(fourOfAKind1.addCard(new Card(Suit.CLUB, Rank.SEVEN)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.HEART, Rank.SEVEN)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.SPADE, Rank.DEUCE)));

        FiveCardPokerHand fourOfAKind2 = new FiveCardPokerHand();
        assertTrue(fourOfAKind2.addCard(new Card(Suit.HEART, Rank.SIX)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.SPADE, Rank.SIX)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.CLUB, Rank.SIX)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.HEART, Rank.ACE)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.SIX)));

        assertTrue(comparator.compare(fourOfAKind1, fourOfAKind2) < 0);
    }

    @Test
    public void testStraightFlushVsStraightFlush() {
        FiveCardPokerHand straightFlush1 = new FiveCardPokerHand();
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.FIVE)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.FOUR)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.DEUCE)));

        FiveCardPokerHand straightFlush2 = new FiveCardPokerHand();
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.SIX)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.SEVEN)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.EIGHT)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.FIVE)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.FOUR)));

        assertTrue(comparator.compare(straightFlush1, straightFlush2) > 0);
    }

    @Test
    public void testStraightFlushVsStraightFlush2() {
        FiveCardPokerHand straightFlush1 = new FiveCardPokerHand();
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.SIX)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.FIVE)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.FOUR)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.DEUCE)));

        FiveCardPokerHand straightFlush2 = new FiveCardPokerHand();
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.SIX)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.SEVEN)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.EIGHT)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.FIVE)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.FOUR)));

        assertTrue(comparator.compare(straightFlush1, straightFlush2) > 0);
    }

    @Test
    public void testStraightFlushVsStraightFlush3() {
        FiveCardPokerHand straightFlush1 = new FiveCardPokerHand();
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.JACK)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.TEN)));

        FiveCardPokerHand straightFlush2 = new FiveCardPokerHand();
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.KING)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.QUEEN)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.JACK)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.TEN)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.NINE)));

        assertTrue(comparator.compare(straightFlush1, straightFlush2) < 0);
    }

    @Test
    public void testStraightFlushVsStraightFlush4() {
        FiveCardPokerHand straightFlush1 = new FiveCardPokerHand();
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.JACK)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.TEN)));

        FiveCardPokerHand straightFlush2 = new FiveCardPokerHand();
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.ACE)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.DEUCE)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.FIVE)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.FOUR)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.THREE)));

        assertTrue(comparator.compare(straightFlush1, straightFlush2) < 0);
    }

    @Test
    public void testStraightFlushVsStraightFlushTie() {
        FiveCardPokerHand straightFlush1 = new FiveCardPokerHand();
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.NINE)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.JACK)));
        assertTrue(straightFlush1.addCard(new Card(Suit.CLUB, Rank.TEN)));

        FiveCardPokerHand straightFlush2 = new FiveCardPokerHand();
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.KING)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.QUEEN)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.JACK)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.TEN)));
        assertTrue(straightFlush2.addCard(new Card(Suit.HEART, Rank.NINE)));

        assertTrue(comparator.compare(straightFlush1, straightFlush2) == 0);
    }

    @Test
    public void testCompareNonFullHands() {
        FiveCardPokerHand hand1 = new FiveCardPokerHand();
        assertTrue(hand1.addCard(new Card(Suit.CLUB, Rank.NINE)));
        assertTrue(hand1.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(hand1.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(hand1.addCard(new Card(Suit.CLUB, Rank.JACK)));

        FiveCardPokerHand hand2 = new FiveCardPokerHand();
        assertTrue(hand2.addCard(new Card(Suit.HEART, Rank.KING)));
        assertTrue(hand2.addCard(new Card(Suit.HEART, Rank.QUEEN)));
        assertTrue(hand2.addCard(new Card(Suit.HEART, Rank.JACK)));
        assertTrue(hand2.addCard(new Card(Suit.HEART, Rank.TEN)));
        assertTrue(hand2.addCard(new Card(Suit.HEART, Rank.NINE)));

        assertTrue(comparator.compare(hand1, hand2) > 0);
    }

    @Test
    public void testCompareNonFullHands2() {
        FiveCardPokerHand hand1 = new FiveCardPokerHand();
        assertTrue(hand1.addCard(new Card(Suit.CLUB, Rank.NINE)));
        assertTrue(hand1.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(hand1.addCard(new Card(Suit.CLUB, Rank.QUEEN)));
        assertTrue(hand1.addCard(new Card(Suit.CLUB, Rank.JACK)));
        assertTrue(hand1.addCard(new Card(Suit.HEART, Rank.JACK)));

        FiveCardPokerHand hand2 = new FiveCardPokerHand();
        assertTrue(hand2.addCard(new Card(Suit.HEART, Rank.KING)));
        assertTrue(hand2.addCard(new Card(Suit.HEART, Rank.QUEEN)));
        assertTrue(hand2.addCard(new Card(Suit.HEART, Rank.TEN)));
        assertTrue(hand2.addCard(new Card(Suit.HEART, Rank.NINE)));

        assertTrue(comparator.compare(hand1, hand2) < 0);
    }

    @Test
    public void testCompareNonFullHands3() {
        FiveCardPokerHand hand1 = new FiveCardPokerHand();

        FiveCardPokerHand hand2 = new FiveCardPokerHand();
        assertTrue(hand2.addCard(new Card(Suit.HEART, Rank.KING)));
        assertTrue(hand2.addCard(new Card(Suit.HEART, Rank.TEN)));
        assertTrue(hand2.addCard(new Card(Suit.HEART, Rank.NINE)));

        assertTrue(comparator.compare(hand1, hand2) == 0);
    }
}