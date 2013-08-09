package logic.simulator;

import card.Card;
import card.Rank;
import card.Suit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();        
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));        
        SimulationResult result = new SimulationResult(hands, board, 1000, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullBoard() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));        
        SimulationResult result = new SimulationResult(hands, null, 1000, PokerGameType.TEXAS);
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testConstructorNonPositiveNumberOfSimulations() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));        
        SimulationResult result = new SimulationResult(hands, 0, PokerGameType.TEXAS);        
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testConstructorNonPositiveNumberOfSimulations2() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));        
        SimulationResult result = new SimulationResult(hands, -1, PokerGameType.TEXAS);        
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testAddResultForOneSimulationNullHandSet() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));    
        SimulationResult result = new SimulationResult(hands, 1000, PokerGameType.TEXAS);         
        
        result.addResultForOneSimulation(null);
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testAddResultForOneSimulationEmptyHandSet() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));    
        SimulationResult result = new SimulationResult(hands, 1000, PokerGameType.TEXAS);         
        
        result.addResultForOneSimulation(new HashSet<AbstractStartingHand>());
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testAddResultForOneSimulationIncorrectHandSet() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));    
        SimulationResult result = new SimulationResult(hands, 1000, PokerGameType.TEXAS);         
        
        Set<AbstractStartingHand> winner = new HashSet<AbstractStartingHand>();        
        
        winner.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.FIVE), new Card(Suit.SPADE, Rank.THREE)));            
        result.addResultForOneSimulation(winner);
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testAddResultForOneSimulationEnoughSimulations() {    
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));    
        SimulationResult result = new SimulationResult(hands, 1, PokerGameType.TEXAS);   
        
        result.addResultForOneSimulation(hands);
        result.addResultForOneSimulation(hands);        
    }

}
