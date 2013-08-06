package card;

import java.util.ArrayList;
import java.util.List;
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
            assertEquals(52 - i - 1, deck.getNumberOfRemainingCards());
        }

        assertNull(deck.getCard());
        assertTrue(deck.isEmpty());
        assertEquals(0, deck.getNumberOfRemainingCards());
    }
    
    @Test
    public void testCreateDeckWithCardsRemoved() {
        List<Card> removedCards = new ArrayList<Card>();
        removedCards.add(new Card(Suit.CLUB, Rank.ACE));
        removedCards.add(new Card(Suit.HEART, Rank.ACE));
        removedCards.add(new Card(Suit.SPADE, Rank.THREE));
        removedCards.add(new Card(Suit.DIAMOND, Rank.FIVE));        
        ICardDeck deck = new CardDeckStandard(removedCards);
        assertEquals(48, deck.getNumberOfRemainingCards());
        
        while (!deck.isEmpty()) {
            Card retrieved = deck.getCard();
            assertFalse("A removed card is present in the deck", 
                    removedCards.contains(retrieved));
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateDeckWithNullCardsRemoved() {
        ICardDeck deck = new CardDeckStandard(null);        
    }
}