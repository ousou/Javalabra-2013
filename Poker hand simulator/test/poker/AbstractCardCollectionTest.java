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
public class AbstractCardCollectionTest {

    public AbstractCardCollectionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
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
        
        if (!toString.equals(expected1) || toString.equals(expected2)) {
            fail("toString method doesn't work correctly");
        }
    }

    public class AbstractCardCollectionImpl extends AbstractCardCollection {

        public AbstractCardCollectionImpl(int maxNumberOfCards) {
            super(maxNumberOfCards);
        }
    }

}