package poker;

import card.Card;
import card.Rank;
import card.Suit;
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
public class FiveCardPokerHandTest {

    public FiveCardPokerHandTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testAddCard() {
        FiveCardPokerHand hand = new FiveCardPokerHand();
        assertEquals(0, hand.getNumberOfCards());
        assertFalse(hand.isFull());
        
        assertTrue(hand.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertEquals(1, hand.getNumberOfCards());
        assertFalse(hand.isFull());        
    }
    
    @Test
    public void testAddCardToFullHand() {
        FiveCardPokerHand hand = new FiveCardPokerHand();
        List<Card> addedCards = new ArrayList<Card>();
        
        Card c1 = new Card(Suit.CLUB, Rank.ACE);
        addedCards.add(c1);
        assertTrue(hand.addCard(c1));        
        assertFalse(hand.isFull());  
        
        Card c2 = new Card(Suit.CLUB, Rank.DEUCE);        
        addedCards.add(c2);        
        assertTrue(hand.addCard(c2));
        assertFalse(hand.isFull());             
        
        Card c3 = new Card(Suit.CLUB, Rank.THREE);        
        addedCards.add(c3);        
        assertTrue(hand.addCard(c3));
        assertFalse(hand.isFull());             
        
        Card c4 = new Card(Suit.CLUB, Rank.FOUR);
        addedCards.add(c4);        
        assertTrue(hand.addCard(c4));
        assertFalse(hand.isFull());             
        
        Card c5 = new Card(Suit.CLUB, Rank.QUEEN);        
        addedCards.add(c5);        
        assertTrue(hand.addCard(c5));        
        
        assertTrue(hand.isFull());
        Card c6 = new Card(Suit.SPADE, Rank.ACE);        
        assertFalse(hand.addCard(c6));        
        
        List<Card> cards = hand.getCards();
        
        assertTrue(cards.containsAll(addedCards));
        assertFalse(cards.contains(c6));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullCard() {
        FiveCardPokerHand hand = new FiveCardPokerHand();
        
        hand.addCard(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddSameCard() {
        FiveCardPokerHand hand = new FiveCardPokerHand();
        Card c1 = new Card(Suit.SPADE, Rank.ACE);         
        assertTrue(hand.addCard(c1));
        
        hand.addCard(c1);
    }
    
    @Test
    public void testEquals() {
        FiveCardPokerHand hand = new FiveCardPokerHand();
        
        Card c1 = new Card(Suit.CLUB, Rank.ACE);
        assertTrue(hand.addCard(c1));        
        
        Card c2 = new Card(Suit.CLUB, Rank.DEUCE);        
        assertTrue(hand.addCard(c2));
        
        Card c3 = new Card(Suit.CLUB, Rank.THREE);        
        assertTrue(hand.addCard(c3));
        
        Card c4 = new Card(Suit.CLUB, Rank.FOUR);
        assertTrue(hand.addCard(c4));
        
        Card c5 = new Card(Suit.CLUB, Rank.QUEEN);        
        assertTrue(hand.addCard(c5));        
        
        FiveCardPokerHand hand2 = new FiveCardPokerHand();       
        
        Card d1 = new Card(Suit.CLUB, Rank.ACE);
        assertTrue(hand2.addCard(d1));        
        
        Card d2 = new Card(Suit.CLUB, Rank.DEUCE);        
        assertTrue(hand2.addCard(d2));
        
        Card d3 = new Card(Suit.CLUB, Rank.THREE);        
        assertTrue(hand2.addCard(d3));
        
        Card d5 = new Card(Suit.CLUB, Rank.QUEEN);        
        assertTrue(hand2.addCard(d5));        
        
        Card d4 = new Card(Suit.CLUB, Rank.FOUR);
        assertTrue(hand2.addCard(d4));
        
        assertTrue(hand.equals(hand2));
        assertTrue(hand2.equals(hand));
    }
    
    @Test
    public void testEquals2() {
        FiveCardPokerHand hand = new FiveCardPokerHand();
        
        Card c1 = new Card(Suit.CLUB, Rank.ACE);
        assertTrue(hand.addCard(c1));        
        
        Card c2 = new Card(Suit.CLUB, Rank.DEUCE);        
        assertTrue(hand.addCard(c2));
        
        Card c3 = new Card(Suit.CLUB, Rank.THREE);        
        assertTrue(hand.addCard(c3));
        
        Card c4 = new Card(Suit.CLUB, Rank.FOUR);
        assertTrue(hand.addCard(c4));
        
        Card c5 = new Card(Suit.CLUB, Rank.QUEEN);        
        assertTrue(hand.addCard(c5));        
        
        FiveCardPokerHand hand2 = new FiveCardPokerHand();       
        
        Card d1 = new Card(Suit.CLUB, Rank.ACE);
        assertTrue(hand2.addCard(d1));        
        
        Card d2 = new Card(Suit.CLUB, Rank.DEUCE);        
        assertTrue(hand2.addCard(d2));
        
        Card d3 = new Card(Suit.CLUB, Rank.THREE);        
        assertTrue(hand2.addCard(d3));
        
        Card d5 = new Card(Suit.SPADE, Rank.QUEEN);        
        assertTrue(hand2.addCard(d5));        
        
        Card d4 = new Card(Suit.CLUB, Rank.FOUR);
        assertTrue(hand2.addCard(d4));
        
        assertFalse(hand.equals(hand2));
        assertFalse(hand2.equals(hand));
    }
}