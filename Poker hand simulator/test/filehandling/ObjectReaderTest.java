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
public class ObjectReaderTest {

    ObjectReader reader;

    public ObjectReaderTest() {
        reader = new ObjectReader();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadObjectNullFileName() {
        reader.readObject(null);
    }

    /**
     * A presaved list of cards is available at
     * test/filehandling/testcardlist.dat
     *
     */
    @Test
    public void testReadObject() {
        String fileName = "test/filehandling/testcardlist.dat";
        Object readObject = reader.readObject(fileName);

        assertNotNull("The reading failed!", readObject);

        List<Card> expectedList = new ArrayList<Card>();
        expectedList.add(new Card(Suit.CLUB, Rank.ACE));
        expectedList.add(new Card(Suit.SPADE, Rank.TEN));        
        
        List<Card> readCards = null;
        try {
            readCards = (List<Card>) readObject;
        } catch (ClassCastException e) {
            fail("The read object was of the wrong type.");
        }
        
        assertEquals("The list didn't equal the expected list",expectedList, readCards);
    }
}