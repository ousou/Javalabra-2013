package card;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sebastian Björkqvist
 */
public class CardDeckStandardTest {

    public CardDeckStandardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testCreateDeck() {
        ICardDeck deck = new CardDeckStandard();
        assertEquals(52, deck.getNumberOfRemainingCards());
        assertFalse(deck.isEmpty());
    }
    
    @Test
    public void testRetrieveCards() {
        ICardDeck deck = new CardDeckStandard();
        Card card = null;
        Card prevCard;
        
        for (int i = 0; i < 52; i++) {
            prevCard = card;
            assertFalse(deck.isEmpty());
            card = deck.getCard();
            assertNotNull(card);
            assertNotSame(prevCard, card);
        }
        
        assertNull(deck.getCard());
        assertTrue(deck.isEmpty());       
    }
}