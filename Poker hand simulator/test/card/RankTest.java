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
public class RankTest {

    private List<Rank> allRanks;
    private Map<String, Rank> stringToRankMap;
    
    public RankTest() {
        allRanks = Rank.getAllRanks();
        stringToRankMap = Rank.getStringToRankMap();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testAce() {
        Rank r = Rank.ACE;        
        assertEquals(1, r.getValue());
        assertEquals("A", r.getShortName());
        assertTrue(allRanks.contains(r));
        assertEquals(r, stringToRankMap.get("A"));
    }

    @Test
    public void testDeuce() {
        Rank r = Rank.DEUCE;
        assertEquals(2, r.getValue());
        assertEquals("2", r.getShortName());
        assertTrue(allRanks.contains(r));    
        assertEquals(r, stringToRankMap.get("2"));        
    }

    @Test
    public void testThree() {
        Rank r = Rank.THREE;
        assertEquals(3, r.getValue());
        assertEquals("3", r.getShortName());
        assertTrue(allRanks.contains(r));
        assertEquals(r, stringToRankMap.get("3"));        
    }

    @Test
    public void testFour() {
        Rank r = Rank.FOUR;
        assertEquals(4, r.getValue());
        assertEquals("4", r.getShortName());
        assertTrue(allRanks.contains(r));    
        assertEquals(r, stringToRankMap.get("4"));        
    }

    @Test
    public void testFive() {
        Rank r = Rank.FIVE;
        assertEquals(5, r.getValue());
        assertEquals("5", r.getShortName());
        assertTrue(allRanks.contains(r));      
        assertEquals(r, stringToRankMap.get("5"));        
    }

    @Test
    public void testSix() {
        Rank r = Rank.SIX;
        assertEquals(6, r.getValue());
        assertEquals("6", r.getShortName());
        assertTrue(allRanks.contains(r));
        assertEquals(r, stringToRankMap.get("6"));        
    }

    @Test
    public void testSeven() {
        Rank r = Rank.SEVEN;
        assertEquals(7, r.getValue());
        assertEquals("7", r.getShortName());
        assertTrue(allRanks.contains(r)); 
        assertEquals(r, stringToRankMap.get("7"));        
    }

    @Test
    public void testEight() {
        Rank r = Rank.EIGHT;
        assertEquals(8, r.getValue());
        assertEquals("8", r.getShortName());
        assertTrue(allRanks.contains(r));
        assertEquals(r, stringToRankMap.get("8"));        
    }

    @Test
    public void testNine() {
        Rank r = Rank.NINE;
        assertEquals(9, r.getValue());
        assertEquals("9", r.getShortName());
        assertTrue(allRanks.contains(r));        
        assertEquals(r, stringToRankMap.get("9"));        
    }

    @Test
    public void testTen() {
        Rank r = Rank.TEN;
        assertEquals(10, r.getValue());
        assertEquals("T", r.getShortName());
        assertTrue(allRanks.contains(r)); 
        assertEquals(r, stringToRankMap.get("T"));        
    }

    @Test
    public void testJack() {
        Rank r = Rank.JACK;
        assertEquals(11, r.getValue());
        assertEquals("J", r.getShortName());
        assertTrue(allRanks.contains(r));   
        assertEquals(r, stringToRankMap.get("J"));        
    }

    @Test
    public void testQueen() {
        Rank r = Rank.QUEEN;
        assertEquals(12, r.getValue());
        assertEquals("Q", r.getShortName());
        assertTrue(allRanks.contains(r));     
        assertEquals(r, stringToRankMap.get("Q"));        
    }

    @Test
    public void testKing() {
        Rank r = Rank.KING;
        assertEquals(13, r.getValue());
        assertEquals("K", r.getShortName());
        assertTrue(allRanks.contains(r));     
        assertEquals(r, stringToRankMap.get("K"));        
    }

}