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
public class PokerHandTypeComparatorTest {
    
    PokerHandTypeComparator comparator;

    public PokerHandTypeComparatorTest() {
        this.comparator = new PokerHandTypeComparator();
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

        /* Some hands have overlapping cards, but this is allowed, since
         * this comparator also works for community card games where
         * hands can have overlapping cards.
         */

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

}