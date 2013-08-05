package logic.simulator;

import card.Card;
import card.Rank;
import card.Suit;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import poker.AbstractStartingHand;
import poker.FiveCardBoard;
import poker.FiveCardPokerHand;
import poker.OmahaHoldemStartingHand;
import poker.TexasHoldemStartingHand;

/**
 *
 * @author Sebastian Björkqvist
 */
public class PossibleHandsCreatorTest {

    public PossibleHandsCreatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateAllPossibleHandsNullHand() {
        FiveCardBoard board = new FiveCardBoard();
        board.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        board.addCard(new Card(Suit.SPADE, Rank.DEUCE));
        board.addCard(new Card(Suit.HEART, Rank.ACE));
        board.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        board.addCard(new Card(Suit.DIAMOND, Rank.NINE));           
        
        PossibleHandsCreator creator = new PossibleHandsCreator(null, board);        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateAllPossibleHandsNullBoard() {
        AbstractStartingHand holdemHand = new TexasHoldemStartingHand(new Card(Suit.SPADE, Rank.ACE), new Card(Suit.DIAMOND, Rank.ACE));       
        
        PossibleHandsCreator creator = new PossibleHandsCreator(holdemHand, null);        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateAllPossibleHandsNonFullBoard() {
        AbstractStartingHand holdemHand = new TexasHoldemStartingHand(new Card(Suit.SPADE, Rank.ACE), new Card(Suit.DIAMOND, Rank.ACE));       
        FiveCardBoard board = new FiveCardBoard();
        board.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        board.addCard(new Card(Suit.SPADE, Rank.DEUCE));
        
        PossibleHandsCreator creator = new PossibleHandsCreator(holdemHand, board);        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateAllPossibleHandsNonFullHand() {
        AbstractStartingHand holdemHand = new TexasHoldemStartingHand();       
        FiveCardBoard board = new FiveCardBoard();
        board.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        board.addCard(new Card(Suit.SPADE, Rank.DEUCE));
        board.addCard(new Card(Suit.HEART, Rank.ACE));
        board.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        board.addCard(new Card(Suit.DIAMOND, Rank.NINE));  
        
        PossibleHandsCreator creator = new PossibleHandsCreator(holdemHand, board);        
    }

    @Test
    public void testCreateAllPossibleHandsTexasHoldEm() {
        AbstractStartingHand holdemHand = new TexasHoldemStartingHand(new Card(Suit.SPADE, Rank.ACE), new Card(Suit.DIAMOND, Rank.ACE));
        FiveCardBoard board = new FiveCardBoard();
        board.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        board.addCard(new Card(Suit.SPADE, Rank.DEUCE));
        board.addCard(new Card(Suit.HEART, Rank.ACE));
        board.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        board.addCard(new Card(Suit.DIAMOND, Rank.NINE));        
        
        PossibleHandsCreator creator = new PossibleHandsCreator(holdemHand, board);
        List<FiveCardPokerHand> createAllPossibleHands = creator.createAllPossibleHands();
        assertEquals(21, createAllPossibleHands.size());
    }

    @Test
    public void testCreateAllPossibleHandsOmahaHoldEm() {
        List<Card> omahaHandCards = new ArrayList<Card>();
        omahaHandCards.add(new Card(Suit.SPADE, Rank.ACE));
        omahaHandCards.add(new Card(Suit.DIAMOND, Rank.ACE));
        omahaHandCards.add(new Card(Suit.CLUB, Rank.EIGHT));
        omahaHandCards.add(new Card(Suit.CLUB, Rank.THREE));        
        AbstractStartingHand omahaHand = new OmahaHoldemStartingHand(omahaHandCards);
        FiveCardBoard board = new FiveCardBoard();
        board.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        board.addCard(new Card(Suit.SPADE, Rank.DEUCE));
        board.addCard(new Card(Suit.HEART, Rank.ACE));
        board.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        board.addCard(new Card(Suit.DIAMOND, Rank.NINE));        
        
        PossibleHandsCreator creator = new PossibleHandsCreator(omahaHand, board);
        List<FiveCardPokerHand> createAllPossibleHands = creator.createAllPossibleHands();
        assertEquals(60, createAllPossibleHands.size());
    }

}