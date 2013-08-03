package card;

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
public class CardTest {

    public CardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    
    @Test
    public void testToString() {
        Card c1 = new Card(Suit.CLUB, Rank.ACE);
        assertEquals("Ac", c1.toString());
    }
    
    @Test
    public void testToString2() {
        Card c1 = new Card(Suit.SPADE, Rank.KING);
        assertEquals("Ks", c1.toString());
    }
    
    @Test
    public void testToString3() {
        Card c1 = new Card(Suit.CLUB, Rank.QUEEN);
        assertEquals("Qc", c1.toString());
    }
    
    @Test
    public void testToString4() {
        Card c1 = new Card(Suit.HEART, Rank.JACK);
        assertEquals("Jh", c1.toString());
    }
    
    @Test
    public void testToString5() {
        Card c1 = new Card(Suit.DIAMOND, Rank.TEN);
        assertEquals("Td", c1.toString());
    }
    
    @Test
    public void testToString6() {
        Card c1 = new Card(Suit.HEART, Rank.SIX);
        assertEquals("6h", c1.toString());
    }

    @Test
    public void testCompareTo() {
        Card c1 = new Card(Suit.CLUB, Rank.ACE);
        Card c2 = new Card(Suit.CLUB, Rank.DEUCE);

        assertEquals(-1, c1.compareTo(c2));
        assertEquals(1, c2.compareTo(c1));
    }

    @Test
    public void testCompareTo2() {
        Card c1 = new Card(Suit.CLUB, Rank.SEVEN);
        Card c2 = new Card(Suit.CLUB, Rank.DEUCE);

        assertEquals(5, c1.compareTo(c2));
        assertEquals(-5, c2.compareTo(c1));
    }

    @Test
    public void testCompareTo3() {
        Card c1 = new Card(Suit.SPADE, Rank.ACE);
        Card c2 = new Card(Suit.HEART, Rank.DEUCE);

        assertEquals(-1, c1.compareTo(c2));
        assertEquals(1, c2.compareTo(c1));
    }

    @Test
    public void testCompareTo4() {
        Card c1 = new Card(Suit.CLUB, Rank.ACE);
        Card c2 = new Card(Suit.DIAMOND, Rank.ACE);

        assertEquals(0, c1.compareTo(c2));
        assertEquals(0, c2.compareTo(c1));
    }

    @Test
    public void testCompareTo5() {
        Card c1 = new Card(Suit.CLUB, Rank.QUEEN);
        Card c2 = new Card(Suit.CLUB, Rank.SEVEN);

        assertEquals(5, c1.compareTo(c2));
        assertEquals(-5, c2.compareTo(c1));
    }

    @Test
    public void testSortList() {
        List<Card> cards = new ArrayList<Card>();

        Card shouldBeSecond = new Card(Suit.CLUB, Rank.THREE);
        cards.add(shouldBeSecond);
        Card shouldBeFirst = new Card(Suit.SPADE, Rank.ACE);
        cards.add(shouldBeFirst);
        Card shouldBeFourth = new Card(Suit.HEART, Rank.NINE);
        cards.add(shouldBeFourth);
        Card shouldBeThird = new Card(Suit.SPADE, Rank.EIGHT);
        cards.add(shouldBeThird);
        Card shouldBeFifth = new Card(Suit.SPADE, Rank.QUEEN);
        cards.add(shouldBeFifth);

        Collections.sort(cards);

        assertEquals(shouldBeFirst, cards.get(0));
        assertEquals(shouldBeSecond, cards.get(1));
        assertEquals(shouldBeThird, cards.get(2));
        assertEquals(shouldBeFourth, cards.get(3));
        assertEquals(shouldBeFifth, cards.get(4));
    }
}