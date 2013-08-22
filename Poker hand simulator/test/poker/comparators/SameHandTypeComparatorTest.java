package poker.comparators;

import poker.comparators.SameHandTypeComparator;
import poker.enums.PokerHandType;
import card.Card;
import card.Rank;
import card.Suit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import poker.FiveCardPokerHand;
import static org.junit.Assert.*;

/**
 *
 * @author Sebastian Björkqvist
 */
public class SameHandTypeComparatorTest {
    
    SameHandTypeComparator comparator;

    public SameHandTypeComparatorTest() {
        this.comparator = new SameHandTypeComparator();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
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

        assertTrue("Hand " + highCard2 + " beats hand " + highCard, 
                comparator.compare(highCard, highCard2) < 0);
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

        assertTrue("Hand " + highCard2 + " beats hand " + highCard,
                comparator.compare(highCard, highCard2) < 0);
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

        assertTrue("Hands " + highCard2 + " and " + highCard + " don't tie",
                comparator.compare(highCard, highCard2) == 0);
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

        assertTrue("Hand " + pair2 + " beats hand " + pair1, comparator.compare(pair1, pair2) < 0);
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
    public void testThreeOfAKindVsThreeOfAKind3() {
        FiveCardPokerHand threeOfAKind1 = new FiveCardPokerHand();
        assertTrue(threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.THREE)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.NINE)));

        FiveCardPokerHand threeOfAKind2 = new FiveCardPokerHand();
        assertTrue(threeOfAKind2.addCard(new Card(Suit.SPADE, Rank.THREE)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.SPADE, Rank.ACE)));

        assertTrue(comparator.compare(threeOfAKind1, threeOfAKind2) > 0);
    }

    @Test
    public void testThreeOfAKindVsThreeOfAKind4() {
        FiveCardPokerHand threeOfAKind1 = new FiveCardPokerHand();
        assertTrue(threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.QUEEN)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.THREE)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.CLUB, Rank.SEVEN)));        

        FiveCardPokerHand threeOfAKind2 = new FiveCardPokerHand();
        assertTrue(threeOfAKind2.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.CLUB, Rank.SEVEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.TEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.SPADE, Rank.NINE)));

        assertTrue(comparator.compare(threeOfAKind1, threeOfAKind2) < 0);
    }

    @Test
    public void testThreeOfAKindVsThreeOfAKindTie() {
        FiveCardPokerHand threeOfAKind1 = new FiveCardPokerHand();
        assertTrue(threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.QUEEN)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.THREE)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.CLUB, Rank.SEVEN)));        

        FiveCardPokerHand threeOfAKind2 = new FiveCardPokerHand();
        assertTrue(threeOfAKind2.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.CLUB, Rank.SEVEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.SPADE, Rank.QUEEN)));

        assertTrue(comparator.compare(threeOfAKind1, threeOfAKind2) == 0);
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
    public void testFullHouseVsFullHouse4() {
        FiveCardPokerHand fullHouse1 = new FiveCardPokerHand();
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.DIAMOND, Rank.ACE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.DEUCE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.DEUCE)));

        FiveCardPokerHand fullHouse2 = new FiveCardPokerHand();
        assertTrue(fullHouse2.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.DIAMOND, Rank.ACE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.HEART, Rank.ACE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.SPADE, Rank.THREE)));

        assertTrue(comparator.compare(fullHouse1, fullHouse2) > 0);
    }

    @Test
    public void testFullHouseVsFullHouse5() {
        FiveCardPokerHand fullHouse1 = new FiveCardPokerHand();
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.THREE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.KING)));

        FiveCardPokerHand fullHouse2 = new FiveCardPokerHand();
        assertTrue(fullHouse2.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.DIAMOND, Rank.ACE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.HEART, Rank.ACE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.SPADE, Rank.THREE)));

        assertTrue(comparator.compare(fullHouse1, fullHouse2) > 0);
    }

    @Test
    public void testFullHouseVsFullHouse6() {
        FiveCardPokerHand fullHouse1 = new FiveCardPokerHand();
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.THREE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.KING)));

        FiveCardPokerHand fullHouse2 = new FiveCardPokerHand();
        assertTrue(fullHouse2.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.DIAMOND, Rank.FIVE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.HEART, Rank.FIVE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.SPADE, Rank.THREE)));

        assertTrue(comparator.compare(fullHouse1, fullHouse2) < 0);
    }

    @Test
    public void testFullHouseVsFullHouseTie() {
        FiveCardPokerHand fullHouse1 = new FiveCardPokerHand();
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.THREE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.KING)));

        FiveCardPokerHand fullHouse2 = new FiveCardPokerHand();
        assertTrue(fullHouse2.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(fullHouse2.addCard(new Card(Suit.HEART, Rank.KING)));
        assertTrue(fullHouse2.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.SPADE, Rank.THREE)));

        assertTrue(comparator.compare(fullHouse1, fullHouse2) == 0);
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
    public void testFourOfAKindVsFourOfAKind3() {
        FiveCardPokerHand fourOfAKind1 = new FiveCardPokerHand();
        assertTrue(fourOfAKind1.addCard(new Card(Suit.CLUB, Rank.SEVEN)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.HEART, Rank.SEVEN)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.SPADE, Rank.DEUCE)));

        FiveCardPokerHand fourOfAKind2 = new FiveCardPokerHand();
        assertTrue(fourOfAKind2.addCard(new Card(Suit.CLUB, Rank.SEVEN)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.SPADE, Rank.SEVEN)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.HEART, Rank.SEVEN)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.SPADE, Rank.ACE)));

        assertTrue(comparator.compare(fourOfAKind1, fourOfAKind2) > 0);
    }

    @Test
    public void testFourOfAKindVsFourOfAKind4() {
        FiveCardPokerHand fourOfAKind1 = new FiveCardPokerHand();
        assertTrue(fourOfAKind1.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.TEN)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.HEART, Rank.ACE)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.ACE)));

        FiveCardPokerHand fourOfAKind2 = new FiveCardPokerHand();
        assertTrue(fourOfAKind2.addCard(new Card(Suit.CLUB, Rank.SEVEN)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.ACE)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.HEART, Rank.ACE)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.SPADE, Rank.ACE)));

        assertTrue(comparator.compare(fourOfAKind1, fourOfAKind2) < 0);
    }

    @Test
    public void testFourOfAKindVsFourOfAKindTie() {
        FiveCardPokerHand fourOfAKind1 = new FiveCardPokerHand();
        assertTrue(fourOfAKind1.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.TEN)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.HEART, Rank.ACE)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.ACE)));

        FiveCardPokerHand fourOfAKind2 = new FiveCardPokerHand();
        assertTrue(fourOfAKind2.addCard(new Card(Suit.CLUB, Rank.TEN)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.ACE)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.HEART, Rank.ACE)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.SPADE, Rank.ACE)));

        assertTrue(comparator.compare(fourOfAKind1, fourOfAKind2) == 0);
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
    public void testHighCardVsHighCardWithGivenHandType() {
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

        SameHandTypeComparator newComparator = new SameHandTypeComparator(PokerHandType.HIGH_CARD);
        
        assertTrue(newComparator.compare(highCard, highCard2) < 0);
    }

    @Test
    public void testPairVsPairTieWithGivenHandType() {
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

        SameHandTypeComparator newComparator = new SameHandTypeComparator(PokerHandType.PAIR);
        
        assertTrue(newComparator.compare(pair1, pair2) == 0);
    }

    @Test
    public void testTwoPairVsTwoPairWithGivenHandType() {
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

        SameHandTypeComparator newComparator = new SameHandTypeComparator(PokerHandType.TWO_PAIR);
        
        assertTrue(newComparator.compare(twoPair1, twoPair2) > 0);
    }

    @Test
    public void testThreeOfAKindVsThreeOfAKindWithGivenHandType() {
        FiveCardPokerHand threeOfAKind1 = new FiveCardPokerHand();
        assertTrue(threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.THREE)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(threeOfAKind1.addCard(new Card(Suit.SPADE, Rank.NINE)));

        FiveCardPokerHand threeOfAKind2 = new FiveCardPokerHand();
        assertTrue(threeOfAKind2.addCard(new Card(Suit.SPADE, Rank.THREE)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(threeOfAKind2.addCard(new Card(Suit.SPADE, Rank.ACE)));

        SameHandTypeComparator newComparator = new SameHandTypeComparator(PokerHandType.THREE_OF_A_KIND);

        assertTrue(newComparator.compare(threeOfAKind1, threeOfAKind2) > 0);
    }

    @Test
    public void testStraightVsStraightWithGivenHandType() {
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
        
        SameHandTypeComparator newComparator = new SameHandTypeComparator(PokerHandType.STRAIGHT);
        
        assertTrue(newComparator.compare(straight1, straight2) > 0);
    }
    
    @Test
    public void testFlushVsFlushWithGivenHandType() {
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

        SameHandTypeComparator newComparator = new SameHandTypeComparator(PokerHandType.FLUSH);
        
        assertTrue(newComparator.compare(flush1, flush2) > 0);
    }

    @Test
    public void testFullHouseVsFullHouseWithGivenHandType() {
        FiveCardPokerHand fullHouse1 = new FiveCardPokerHand();
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.THREE)));
        assertTrue(fullHouse1.addCard(new Card(Suit.CLUB, Rank.KING)));
        assertTrue(fullHouse1.addCard(new Card(Suit.SPADE, Rank.KING)));

        FiveCardPokerHand fullHouse2 = new FiveCardPokerHand();
        assertTrue(fullHouse2.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.DIAMOND, Rank.FIVE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.HEART, Rank.FIVE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.CLUB, Rank.THREE)));
        assertTrue(fullHouse2.addCard(new Card(Suit.SPADE, Rank.THREE)));

        SameHandTypeComparator newComparator = new SameHandTypeComparator(PokerHandType.FULL_HOUSE);
        
        assertTrue(newComparator.compare(fullHouse1, fullHouse2) < 0);
    }

    @Test
    public void testFourOfAKindVsFourOfAKindWithGivenHandType() {
        FiveCardPokerHand fourOfAKind1 = new FiveCardPokerHand();
        assertTrue(fourOfAKind1.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.TEN)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.HEART, Rank.ACE)));
        assertTrue(fourOfAKind1.addCard(new Card(Suit.DIAMOND, Rank.ACE)));

        FiveCardPokerHand fourOfAKind2 = new FiveCardPokerHand();
        assertTrue(fourOfAKind2.addCard(new Card(Suit.CLUB, Rank.SEVEN)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.DIAMOND, Rank.ACE)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.HEART, Rank.ACE)));
        assertTrue(fourOfAKind2.addCard(new Card(Suit.SPADE, Rank.ACE)));

        SameHandTypeComparator newComparator = new SameHandTypeComparator(PokerHandType.FOUR_OF_A_KIND);
        
        assertTrue(newComparator.compare(fourOfAKind1, fourOfAKind2) < 0);
    }

    @Test
    public void testStraightFlushVsStraightFlushWithGivenHandType() {
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

        SameHandTypeComparator newComparator = new SameHandTypeComparator(PokerHandType.STRAIGHT_FLUSH);        
        
        assertTrue(newComparator.compare(straightFlush1, straightFlush2) < 0);
    }    

}