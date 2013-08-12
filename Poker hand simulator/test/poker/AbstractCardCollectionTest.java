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
public class AbstractCardCollectionTest {

    public AbstractCardCollectionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateAbstractCardCollectionWithNonPositiveMaxAmount() {
        AbstractCardCollection collection = new AbstractCardCollectionImpl(0);        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateAbstractCardCollectionWithNonPositiveMaxAmount2() {
        AbstractCardCollection collection = new AbstractCardCollectionImpl(-1);        
    }
            
    @Test
    public void testAddCard() {
        AbstractCardCollection collection = new AbstractCardCollectionImpl(2);

        assertFalse(collection.isFull());        
        assertTrue(collection.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertFalse(collection.isFull());         
        assertTrue(collection.addCard(new Card(Suit.SPADE, Rank.ACE)));   
        assertTrue(collection.isFull());       
        
        assertFalse(collection.addCard(new Card(Suit.CLUB, Rank.THREE)));
    }
    
    @Test
    public void testAddCards() {
        AbstractCardCollection collection = new AbstractCardCollectionImpl(2);

        assertFalse(collection.isFull());        
        List<Card> toAdd = new ArrayList<Card>();
        toAdd.add(new Card(Suit.CLUB, Rank.ACE));
        toAdd.add(new Card(Suit.SPADE, Rank.ACE));
        assertTrue(collection.addCards(toAdd));
        assertTrue(collection.isFull());          
        
        List<Card> cards = collection.getCards();
        assertEquals(2, cards.size());
        for (Card card : toAdd) {
            assertTrue(cards.contains(card));
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddCardsNullCollection() {
        AbstractCardCollection collection = new AbstractCardCollectionImpl(2);

        assertFalse(collection.addCards(null));
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullCard() {
        AbstractCardCollection collection = new AbstractCardCollectionImpl(2);
        
        collection.addCard(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddSameCard() {
        AbstractCardCollection collection = new AbstractCardCollectionImpl(2);
        
        collection.addCard(new Card(Suit.SPADE, Rank.ACE));
        collection.addCard(new Card(Suit.SPADE, Rank.ACE));        
    }
    
    @Test
    public void testGetMaximumAmountOfCardsInCollection() {
        AbstractCardCollection collection = new AbstractCardCollectionImpl(2);
        assertEquals(2, collection.getMaximumAmountOfCardsInCollection());
    }
    
    @Test
    public void testGetCards() {
        AbstractCardCollection collection = new AbstractCardCollectionImpl(2);

        assertTrue(collection.addCard(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(collection.addCard(new Card(Suit.SPADE, Rank.ACE)));   
        List<Card> cards = collection.getCards();
        assertTrue(cards.contains(new Card(Suit.CLUB, Rank.ACE)));
        assertTrue(cards.contains(new Card(Suit.SPADE, Rank.ACE)));
    }
    
    @Test
    public void testToString() {
        AbstractCardCollection collection = new AbstractCardCollectionImpl(2);  
        
        assertTrue(collection.addCard(new Card(Suit.CLUB, Rank.ACE)));      
        assertTrue(collection.addCard(new Card(Suit.SPADE, Rank.ACE)));  
        
        String expected1 = "[Ac, As]";
        String expected2 = "[As, Ac]";
        
        String toString = collection.toString();
        
        if (!toString.equals(expected1) && !toString.equals(expected2)) {
            fail("toString method doesn't work correctly");
        }
    }
    
    @Test
    public void testEquals() {
        AbstractCardCollection collection = new AbstractCardCollectionImpl(2);  
        
        assertTrue(collection.addCard(new Card(Suit.CLUB, Rank.ACE)));      
        assertTrue(collection.addCard(new Card(Suit.SPADE, Rank.ACE)));  
        
        AbstractCardCollection collection2 = new AbstractCardCollectionImpl(2);  
        
        assertTrue(collection2.addCard(new Card(Suit.CLUB, Rank.ACE)));      
        assertTrue(collection2.addCard(new Card(Suit.SPADE, Rank.ACE)));  
        
        assertTrue(collection.equals(collection2));
        assertTrue(collection2.equals(collection));
    }
    
    @Test
    public void testEquals2() {
        AbstractCardCollection collection = new AbstractCardCollectionImpl(2);  
        
        assertTrue(collection.addCard(new Card(Suit.CLUB, Rank.ACE)));      
        assertTrue(collection.addCard(new Card(Suit.HEART, Rank.ACE)));  
        
        AbstractCardCollection collection2 = new AbstractCardCollectionImpl(2);  
        
        assertTrue(collection2.addCard(new Card(Suit.CLUB, Rank.ACE)));      
        assertTrue(collection2.addCard(new Card(Suit.SPADE, Rank.ACE)));  
        
        assertFalse(collection.equals(collection2));
        assertFalse(collection2.equals(collection));
    }
    
    @Test
    public void testEquals3() {
        AbstractCardCollection collection = new AbstractCardCollectionImpl(4);  
        
        assertTrue(collection.addCard(new Card(Suit.CLUB, Rank.ACE)));      
        assertTrue(collection.addCard(new Card(Suit.SPADE, Rank.ACE)));  
        
        AbstractCardCollection collection2 = new AbstractCardCollectionImpl(2);  
        
        assertTrue(collection2.addCard(new Card(Suit.CLUB, Rank.ACE)));      
        assertTrue(collection2.addCard(new Card(Suit.SPADE, Rank.ACE)));  
        
        assertFalse(collection.equals(collection2));
        assertFalse(collection2.equals(collection));
    }

    public class AbstractCardCollectionImpl extends AbstractCardCollection {

        public AbstractCardCollectionImpl(int maxNumberOfCards) {
            super(maxNumberOfCards);
        }
    }

}