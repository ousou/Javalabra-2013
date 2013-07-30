package card;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ozo
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
    public void testCompareTo() {
        Card c1 = new Card(Suit.CLUB, Rank.ACE);
        Card c2 = new Card(Suit.CLUB, Rank.DEUCE);

        assertEquals(-1, c1.compareTo(c2));
        assertEquals(1, c2.compareTo(c1));
    }

    @Test
    public void testCompareTo2() {
        Card c1 = new Card(Suit.CLUB, Rank.ACE);
        Card c2 = new Card(Suit.CLUB, Rank.DEUCE);

        assertEquals(-1, c1.compareTo(c2));
        assertEquals(1, c2.compareTo(c1));
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
}