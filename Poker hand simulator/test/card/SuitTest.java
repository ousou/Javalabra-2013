package card;

import java.util.List;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sebastian Björkqvist
 */
public class SuitTest {

    private List<Suit> allSuits;
    private Map<String, Suit> stringToSuitMap;
    
    public SuitTest() {
        allSuits = Suit.getAllSuits();
        stringToSuitMap = Suit.getStringToSuitMap();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testSpade() {
        Suit s = Suit.SPADE;
        assertEquals("s", s.getShortName());
        assertTrue(allSuits.contains(s));
        assertEquals(s, stringToSuitMap.get("s"));
    }

    @Test
    public void testDiamond() {
        Suit s = Suit.DIAMOND;
        assertEquals("d", s.getShortName());
        assertTrue(allSuits.contains(s));
        assertEquals(s, stringToSuitMap.get("d"));        
    }

    @Test
    public void testHeart() {
        Suit s = Suit.HEART;
        assertEquals("h", s.getShortName());
        assertTrue(allSuits.contains(s));
        assertEquals(s, stringToSuitMap.get("h"));        
    }

    @Test
    public void testClub() {
        Suit s = Suit.CLUB;
        assertEquals("c", s.getShortName());
        assertTrue(allSuits.contains(s));
        assertEquals(s, stringToSuitMap.get("c"));        
    }
    
}