package poker;

import card.Card;
import card.Rank;
import card.Suit;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sebastian Björkqvist
 */
public class TexasHoldemStartingHandTest {

    public TexasHoldemStartingHandTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testCreateHandWithCards() {
        TexasHoldemStartingHand hand = new TexasHoldemStartingHand
                (new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.DEUCE));
        
        assertTrue(hand.isFull());
        assertEquals(2, hand.getMaximumAmountOfCardsInHand());
        assertEquals(0, hand.getMinimumAmountOfCardsUsed());   
        assertEquals(2, hand.getMaximumAmountOfCardsUsed());         
    }
    
    @Test
    public void testAddCard() {
        TexasHoldemStartingHand hand = new TexasHoldemStartingHand();
        
        assertTrue(hand.addCard(new Card(Suit.HEART, Rank.ACE)));
        assertFalse(hand.isFull());
        assertTrue(hand.addCard(new Card(Suit.DIAMOND, Rank.ACE)));
        assertTrue(hand.isFull()); 
        
        assertFalse(hand.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullCard() {
        TexasHoldemStartingHand hand = new TexasHoldemStartingHand();
        
        hand.addCard(null);
    }

    @Test(expected = IllegalArgumentException.class)    
    public void testAddSameCard() {
        TexasHoldemStartingHand hand = new TexasHoldemStartingHand();
        
        assertTrue(hand.addCard(new Card(Suit.HEART, Rank.ACE)));  
        hand.addCard(new Card(Suit.HEART, Rank.ACE));
    }
    
    @Test
    public void testGetCards() {
        TexasHoldemStartingHand hand = new TexasHoldemStartingHand
                (new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.DEUCE));
        List<Card> cards = hand.getCards();
        assertTrue(cards.contains(new Card(Suit.CLUB, Rank.DEUCE)));
        assertTrue(cards.contains(new Card(Suit.SPADE, Rank.DEUCE)));
    }

}