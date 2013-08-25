package poker.comparators;

import poker.comparators.FiveCardPokerHandComparator;
import card.Card;
import card.CardDeckStandard;
import card.ICardDeck;
import card.Rank;
import card.Suit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import poker.FiveCardPokerHand;
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
    
    @Test
    public void testPairVsTwoPair() {
        FiveCardPokerHand pair = new FiveCardPokerHand();
        assertTrue(pair.addCard(new Card(Suit.HEART, Rank.DEUCE)));
        assertTrue(pair.addCard(new Card(Suit.CLUB, Rank.DEUCE)));
        assertTrue(pair.addCard(new Card(Suit.SPADE, Rank.THREE)));
        assertTrue(pair.addCard(new Card(Suit.HEART, Rank.FOUR)));
        assertTrue(pair.addCard(new Card(Suit.HEART, Rank.FIVE)));

        FiveCardPokerHand twoPair = new FiveCardPokerHand();
        assertTrue(twoPair.addCard(new Card(Suit.HEART, Rank.FOUR)));
        assertTrue(twoPair.addCard(new Card(Suit.CLUB, Rank.FOUR)));
        assertTrue(twoPair.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(twoPair.addCard(new Card(Suit.HEART, Rank.SEVEN)));
        assertTrue(twoPair.addCard(new Card(Suit.DIAMOND, Rank.FIVE)));
        
        assertTrue("Two pair hand " + twoPair + " doesn't beat pair hand " + pair, 
        comparator.compare(pair, twoPair) > 0);    
    }
    
    @Test
    public void testStraightVsFlush() {
        FiveCardPokerHand straight = new FiveCardPokerHand();
        assertTrue(straight.addCard(new Card(Suit.HEART, Rank.EIGHT)));
        assertTrue(straight.addCard(new Card(Suit.CLUB, Rank.SEVEN)));
        assertTrue(straight.addCard(new Card(Suit.SPADE, Rank.SIX)));
        assertTrue(straight.addCard(new Card(Suit.HEART, Rank.NINE)));
        assertTrue(straight.addCard(new Card(Suit.SPADE, Rank.FIVE)));

        FiveCardPokerHand flush = new FiveCardPokerHand();
        assertTrue(flush.addCard(new Card(Suit.DIAMOND, Rank.DEUCE)));
        assertTrue(flush.addCard(new Card(Suit.DIAMOND, Rank.QUEEN)));
        assertTrue(flush.addCard(new Card(Suit.DIAMOND, Rank.ACE)));
        assertTrue(flush.addCard(new Card(Suit.DIAMOND, Rank.SIX)));
        assertTrue(flush.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        
        assertTrue("Flush hand " + flush + " doesn't beat straight hand " + straight,
                comparator.compare(straight, flush) > 0);
    }
    
    @Test
    public void testRandomHandAgainstItself() {
        for (int i = 0; i < 10000; i++) {
            ICardDeck deck = new CardDeckStandard();
            FiveCardPokerHand randomHand = new FiveCardPokerHand();
            for (int j = 0; j < 5; j++) {
                assertTrue(randomHand.addCard(deck.takeCard()));
            }
        
            assertEquals("Hand " + randomHand + " doesn't tie with itself"
                    ,0, comparator.compare(randomHand, randomHand));
        }
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