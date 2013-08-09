package logic.simulator;

import card.Card;
import card.Rank;
import card.Suit;
import java.util.HashSet;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import poker.AbstractStartingHand;
import poker.FiveCardBoard;
import poker.PokerGameType;
import poker.TexasHoldemStartingHand;

/**
 *
 * @author Sebastian Björkqvist
 * 
 * @todo Write proper test for result getting methods.
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
        FiveCardBoard board = new FiveCardBoard();               
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));        
        SimulationResult result = new SimulationResult(hands, null, 1000, PokerGameType.TEXAS);
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testConstructorNonPositiveNumberOfSimulations() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));        
        SimulationResult result = new SimulationResult(hands, board, 0, PokerGameType.TEXAS);        
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testConstructorNonPositiveNumberOfSimulations2() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));        
        SimulationResult result = new SimulationResult(hands, board, -1, PokerGameType.TEXAS);        
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testAddResultForOneSimulationNullHandSet() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));    
        SimulationResult result = new SimulationResult(hands, board, 1000, PokerGameType.TEXAS);         
        
        result.addResultForOneSimulation(null);
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testAddResultForOneSimulationEmptyHandSet() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));    
        SimulationResult result = new SimulationResult(hands, board, 1000, PokerGameType.TEXAS);         
        
        result.addResultForOneSimulation(new HashSet<AbstractStartingHand>());
    }
    
    @Test(expected = IllegalArgumentException.class) 
    public void testAddResultForOneSimulationIncorrectHandSet() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));    
        SimulationResult result = new SimulationResult(hands, board, 1000, PokerGameType.TEXAS);         
        
        Set<AbstractStartingHand> winner = new HashSet<AbstractStartingHand>();        
        
        winner.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.FIVE), new Card(Suit.SPADE, Rank.THREE)));            
        result.addResultForOneSimulation(winner);
    }
    
    @Test(expected = IllegalStateException.class) 
    public void testAddResultForOneSimulationEnoughSimulations() {    
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));    
        SimulationResult result = new SimulationResult(hands, board, 1, PokerGameType.TEXAS);   
        
        result.addResultForOneSimulation(hands);
        result.addResultForOneSimulation(hands);        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetWinPercentageForNullHand() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));    
        SimulationResult result = new SimulationResult(hands, board, 1, PokerGameType.TEXAS);   
        
        result.addResultForOneSimulation(hands);
        
        result.getWinPercentageForHand(null, 1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetWinPercentageForHandNotInSet() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));    
        SimulationResult result = new SimulationResult(hands, board, 1, PokerGameType.TEXAS);   
        
        result.addResultForOneSimulation(hands);        
        
        result.getWinPercentageForHand(
                new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.FIVE), 
                new Card(Suit.SPADE, Rank.THREE)), 1);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testGetWinPercentageWhenSimulationIsntDone() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        TexasHoldemStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        hands.add(hand1);
        TexasHoldemStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE));
        hands.add(hand2);    
        SimulationResult result = new SimulationResult(hands, board, 3, PokerGameType.TEXAS);   
        
        result.addResultForOneSimulation(hands);      
        
        result.getWinPercentageForHand(hand1, 3);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetTiePercentageForNullHand() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));    
        SimulationResult result = new SimulationResult(hands, board, 1, PokerGameType.TEXAS);   
        
        result.addResultForOneSimulation(hands);
        
        result.getTiePercentageForHand(null, 1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetTiePercentageForHandNotInSet() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));    
        SimulationResult result = new SimulationResult(hands, board, 1, PokerGameType.TEXAS);   
        
        result.addResultForOneSimulation(hands);        
        
        result.getTiePercentageForHand(
                new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.FIVE), 
                new Card(Suit.SPADE, Rank.THREE)), 1);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testGetTiePercentageWhenSimulationIsntDone() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        TexasHoldemStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        hands.add(hand1);
        TexasHoldemStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE));
        hands.add(hand2);    
        SimulationResult result = new SimulationResult(hands, board, 3, PokerGameType.TEXAS);   
        
        result.addResultForOneSimulation(hands);      
        
        result.getTiePercentageForHand(hand1, 3);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLossPercentageForNullHand() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));    
        SimulationResult result = new SimulationResult(hands, board, 1, PokerGameType.TEXAS);   
        
        result.addResultForOneSimulation(hands);
        
        result.getLossPercentageForHand(null, 1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetLossPercentageForHandNotInSet() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE)));
        hands.add(new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE)));    
        SimulationResult result = new SimulationResult(hands, board, 1, PokerGameType.TEXAS);   
        
        result.addResultForOneSimulation(hands);        
        
        result.getLossPercentageForHand(
                new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.FIVE), 
                new Card(Suit.SPADE, Rank.THREE)), 1);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testGetLossPercentageWhenSimulationIsntDone() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        TexasHoldemStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        hands.add(hand1);
        TexasHoldemStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE));
        hands.add(hand2);    
        SimulationResult result = new SimulationResult(hands, board, 3, PokerGameType.TEXAS);   
        
        result.addResultForOneSimulation(hands);      
        
        result.getLossPercentageForHand(hand1, 3);
    }

}
