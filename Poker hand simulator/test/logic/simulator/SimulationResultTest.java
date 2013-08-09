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
import poker.PokerGameType;
import poker.TexasHoldemStartingHand;

/**
 *
 * @author Sebastian Björkqvist
 */
public class SimulationResultTest {

    public SimulationResultTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullStartingHands() {
        FiveCardBoard board = new FiveCardBoard();
        SimulationResult result = new SimulationResult(null, board, 1000, PokerGameType.TEXAS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullGameType() {
        List<AbstractStartingHand> hands = new ArrayList<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();        
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));        
        SimulationResult result = new SimulationResult(hands, board, 1000, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullBoard() {
        List<AbstractStartingHand> hands = new ArrayList<AbstractStartingHand>();
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));        
        SimulationResult result = new SimulationResult(hands, null, 1000, PokerGameType.TEXAS);
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testConstructorNonPositiveNumberOfSimulations() {
        List<AbstractStartingHand> hands = new ArrayList<AbstractStartingHand>();
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));        
        SimulationResult result = new SimulationResult(hands, 0, PokerGameType.TEXAS);        
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testConstructorNonPositiveNumberOfSimulations2() {
        List<AbstractStartingHand> hands = new ArrayList<AbstractStartingHand>();
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));        
        SimulationResult result = new SimulationResult(hands, 0, PokerGameType.TEXAS);        
    }
}
