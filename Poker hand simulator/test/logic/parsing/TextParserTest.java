package logic.parsing;

import card.Card;
import card.Rank;
import card.Suit;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sebastian Björkqvist
 */
public class TextParserTest {
    
    public TextParserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testParseTextToCards() {
        String toParse = "As, Kd";
        List<Card> parseTextToCards = new ArrayList<Card>();
        try {
            parseTextToCards = TextParser.parseTextToCards(toParse);
        } catch (ParseException ex) {
            fail("String " + toParse + " could not be parsed");
        }
        
        assertEquals(2, parseTextToCards.size());
        assertTrue(parseTextToCards.contains(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(parseTextToCards.contains(new Card(Suit.DIAMOND, Rank.KING)));
    }

    @Test
    public void testParseTextToCards2() {
        String toParse = "As,Kd,Ts,9h";
        List<Card> parseTextToCards = new ArrayList<Card>();
        try {
            parseTextToCards = TextParser.parseTextToCards(toParse);
        } catch (ParseException ex) {
            fail("String " + toParse + " could not be parsed");
        }
        
        assertEquals(4, parseTextToCards.size());
        assertTrue(parseTextToCards.contains(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(parseTextToCards.contains(new Card(Suit.DIAMOND, Rank.KING)));
        assertTrue(parseTextToCards.contains(new Card(Suit.SPADE, Rank.TEN)));
        assertTrue(parseTextToCards.contains(new Card(Suit.HEART, Rank.NINE)));        
    }

    @Test
    public void testParseTextToCardsEmptyString() {
        String toParse = "";
        List<Card> parseTextToCards = new ArrayList<Card>();
        try {
            parseTextToCards = TextParser.parseTextToCards(toParse);
        } catch (ParseException ex) {
            fail("String " + toParse + " could not be parsed");
        }
        
        assertEquals(0, parseTextToCards.size());
    }

    @Test
    public void testParseTextToCardsNullString() {
        String toParse = null;
        List<Card> parseTextToCards = new ArrayList<Card>();
        try {
            parseTextToCards = TextParser.parseTextToCards(toParse);
        } catch (ParseException ex) {
            fail("String " + toParse + " could not be parsed");
        }
        
        assertEquals(0, parseTextToCards.size());
    }

    @Test
    public void testParseTextToCardsInvalidString() {
        String toParse = "AsKd";
        List<Card> parseTextToCards = new ArrayList<Card>();
        try {
            parseTextToCards = TextParser.parseTextToCards(toParse);
            fail("Exception not thrown");            
        } catch (ParseException ex) {
        }
    }

    @Test
    public void testParseTextToCardsInvalidString2() {
        String toParse = "As,,Kd";
        List<Card> parseTextToCards = new ArrayList<Card>();
        try {
            parseTextToCards = TextParser.parseTextToCards(toParse);
            fail("Exception not thrown");            
        } catch (ParseException ex) {
        }
    }

    @Test
    public void testParseTextToCardsInvalidString3() {
        String toParse = "AceDiamond,KingSpade";
        List<Card> parseTextToCards = new ArrayList<Card>();
        try {
            parseTextToCards = TextParser.parseTextToCards(toParse);
            fail("Exception not thrown");            
        } catch (ParseException ex) {
        }
    }

}