package filehandling;

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
public class ObjectWriterTest {
    
    ObjectWriter writer;
    ObjectReader reader;

    public ObjectWriterTest() {
        writer = new ObjectWriter();
        reader = new ObjectReader();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testWriteObjectNullObject() {
        String fileName = "test/filehandling/testcardlist.dat";
        Object object = null;
        
        writer.writeObject(object, fileName);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testWriteObjectNullFileName() {
        String fileName = null;
        String object = "Object";
        
        writer.writeObject(object, fileName);
    }

    @Test
    public void testWriteObject() {
        List<Card> cardsToWrite = new ArrayList<Card>();
        cardsToWrite.add(new Card(Suit.CLUB, Rank.ACE));
        cardsToWrite.add(new Card(Suit.SPADE, Rank.TEN));
        
        String fileName = "test/filehandling/testcardlist.dat";
        
        assertTrue("Writing failed!", writer.writeObject(cardsToWrite, fileName));
        // Reading the object to see that it is correctly saved
        Object readObject = reader.readObject(fileName);
        
        assertNotNull("Reading failed!", readObject);
        
        List<Card> readCards = null;
        try {
            readCards = (List<Card>) readObject;
        } catch (ClassCastException e) {
            fail("The read object was not of the correct type!");
        }
        
        assertEquals("The read card list was not equal to the saved one!", 
                cardsToWrite, readCards);
    }

}