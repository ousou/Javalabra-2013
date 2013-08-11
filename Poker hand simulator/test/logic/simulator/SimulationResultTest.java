package logic.simulator;

import card.Card;
import card.Rank;
import card.Suit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import poker.startinghands.AbstractStartingHand;
import poker.FiveCardBoard;
import poker.enums.PokerGameType;
import poker.startinghands.TexasHoldemStartingHand;
import static org.junit.Assert.*;

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
    
    @Test
    public void testResultsForOneSimulation() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        int digits = 4;
        
        TexasHoldemStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        hands.add(hand1);
        TexasHoldemStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE));
        hands.add(hand2);            
        SimulationResult result = new SimulationResult(hands, board, 1, PokerGameType.TEXAS);          
        Set<AbstractStartingHand> winningHand = new HashSet<AbstractStartingHand>();
        winningHand.add(hand1);
        result.addResultForOneSimulation(winningHand);
        
        assertEquals(100.0, result.getWinPercentageForHand(hand1, digits), 0);
        assertEquals(0, result.getLossPercentageForHand(hand1, digits), 0);
        assertEquals(0, result.getTiePercentageForHand(hand1, digits), 0);        
        assertEquals(1, result.getEquityForHand(hand1, digits), 0);
        
        assertEquals(0, result.getWinPercentageForHand(hand2, digits), 0);
        assertEquals(100.0, result.getLossPercentageForHand(hand2, digits), 0);
        assertEquals(0, result.getTiePercentageForHand(hand2, digits), 0);        
        assertEquals(0, result.getEquityForHand(hand2, digits), 0);
    }
    
    @Test
    public void testResultsForOneSimulationTie() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        int digits = 4;
        
        TexasHoldemStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        hands.add(hand1);
        TexasHoldemStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE));
        hands.add(hand2);            
        SimulationResult result = new SimulationResult(hands, board, 1, PokerGameType.TEXAS);          
        Set<AbstractStartingHand> winningHand = new HashSet<AbstractStartingHand>();
        winningHand.add(hand1);
        winningHand.add(hand2);
        result.addResultForOneSimulation(winningHand);
        
        assertEquals(0, result.getWinPercentageForHand(hand1, digits), 0);
        assertEquals(0, result.getLossPercentageForHand(hand1, digits), 0);
        assertEquals(100.0, result.getTiePercentageForHand(hand1, digits), 0);        
        assertEquals(0.5000, result.getEquityForHand(hand1, digits), 0);
        
        assertEquals(0, result.getWinPercentageForHand(hand2, digits), 0);
        assertEquals(0, result.getLossPercentageForHand(hand2, digits), 0);
        assertEquals(100.0, result.getTiePercentageForHand(hand2, digits), 0);        
        assertEquals(0.5000, result.getEquityForHand(hand2, digits), 0);
    }
    
    @Test
    public void testResultsForTwoSimulations() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        int digits = 4;
        
        TexasHoldemStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        hands.add(hand1);
        TexasHoldemStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE));
        hands.add(hand2);            
        SimulationResult result = new SimulationResult(hands, board, 2, PokerGameType.TEXAS);          
        Set<AbstractStartingHand> winningHand = new HashSet<AbstractStartingHand>();
        winningHand.add(hand1);
        result.addResultForOneSimulation(winningHand);
        winningHand.add(hand2);        
        result.addResultForOneSimulation(winningHand);
        
        assertEquals(50.00, result.getWinPercentageForHand(hand1, digits), 0);
        assertEquals(0, result.getLossPercentageForHand(hand1, digits), 0);
        assertEquals(50.00, result.getTiePercentageForHand(hand1, digits), 0);        
        assertEquals(0.7500, result.getEquityForHand(hand1, digits), 0);
        
        assertEquals(0, result.getWinPercentageForHand(hand2, digits), 0);
        assertEquals(50.00, result.getLossPercentageForHand(hand2, digits), 0);
        assertEquals(50.00, result.getTiePercentageForHand(hand2, digits), 0);        
        assertEquals(0.2500, result.getEquityForHand(hand2, digits), 0);
    }    
    
    @Test
    public void testResultsForSimulationsThreeHands() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        int digits = 4;
        
        TexasHoldemStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        hands.add(hand1);
        TexasHoldemStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE));
        hands.add(hand2);  
        TexasHoldemStartingHand hand3 = new TexasHoldemStartingHand(new Card(Suit.SPADE, Rank.FIVE), new Card(Suit.DIAMOND, Rank.FIVE));
        hands.add(hand3);             
        SimulationResult result = new SimulationResult(hands, board, 3, PokerGameType.TEXAS);          
        Set<AbstractStartingHand> winningHand = new HashSet<AbstractStartingHand>();
        // Hand 1 wins
        winningHand.add(hand1);
        result.addResultForOneSimulation(winningHand);
        // Hands 1 and 2 win
        winningHand.add(hand2);        
        result.addResultForOneSimulation(winningHand);
        // Hands 2 and 3 win
        winningHand.remove(hand1);
        winningHand.add(hand3);
        result.addResultForOneSimulation(winningHand);        
        
        assertEquals(33.33, result.getWinPercentageForHand(hand1, digits), 0);
        assertEquals(33.33, result.getLossPercentageForHand(hand1, digits), 0);
        assertEquals(33.33, result.getTiePercentageForHand(hand1, digits), 0);        
        assertEquals(0.5000, result.getEquityForHand(hand1, digits), 0);
        
        assertEquals(0, result.getWinPercentageForHand(hand2, digits), 0);
        assertEquals(33.33, result.getLossPercentageForHand(hand2, digits), 0);
        assertEquals(66.67, result.getTiePercentageForHand(hand2, digits), 0);        
        assertEquals(0.3333, result.getEquityForHand(hand2, digits), 0);
        
        assertEquals(0, result.getWinPercentageForHand(hand3, digits), 0);
        assertEquals(66.67, result.getLossPercentageForHand(hand3, digits), 0);
        assertEquals(33.33, result.getTiePercentageForHand(hand3, digits), 0);        
        assertEquals(0.1667, result.getEquityForHand(hand3, digits), 0);        
    }    
    
    @Test
    public void testResultsForSimulationsThreeHands2() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        int digits = 4;
        
        TexasHoldemStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        hands.add(hand1);
        TexasHoldemStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE));
        hands.add(hand2);  
        TexasHoldemStartingHand hand3 = new TexasHoldemStartingHand(new Card(Suit.SPADE, Rank.FIVE), new Card(Suit.DIAMOND, Rank.FIVE));
        hands.add(hand3);             
        SimulationResult result = new SimulationResult(hands, board, 4, PokerGameType.TEXAS);          
        Set<AbstractStartingHand> winningHand = new HashSet<AbstractStartingHand>();
        // Hand 1 wins
        winningHand.add(hand1);
        result.addResultForOneSimulation(winningHand);
        // Hands 1 and 2 win
        winningHand.add(hand2);        
        result.addResultForOneSimulation(winningHand);
        // Hands 2 and 3 win
        winningHand.remove(hand1);
        winningHand.add(hand3);
        result.addResultForOneSimulation(winningHand);        
        // Hands 1, 2 and 3 win
        winningHand.add(hand1);        
        result.addResultForOneSimulation(winningHand);                
        
        assertEquals(25.00, result.getWinPercentageForHand(hand1, digits), 0);
        assertEquals(25.00, result.getLossPercentageForHand(hand1, digits), 0);
        assertEquals(50.00, result.getTiePercentageForHand(hand1, digits), 0);        
        assertEquals(0.4583, result.getEquityForHand(hand1, digits), 0);
        
        assertEquals(0, result.getWinPercentageForHand(hand2, digits), 0);
        assertEquals(25.00, result.getLossPercentageForHand(hand2, digits), 0);
        assertEquals(75.00, result.getTiePercentageForHand(hand2, digits), 0);        
        assertEquals(0.3333, result.getEquityForHand(hand2, digits), 0);
        
        assertEquals(0, result.getWinPercentageForHand(hand3, digits), 0);
        assertEquals(50.00, result.getLossPercentageForHand(hand3, digits), 0);
        assertEquals(50.00, result.getTiePercentageForHand(hand3, digits), 0);        
        assertEquals(0.2083, result.getEquityForHand(hand3, digits), 0);        
    }
    
    @Test
    public void testResultsForManySimulationsTwoHands() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        int digits = 4;
        
        TexasHoldemStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        hands.add(hand1);
        TexasHoldemStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE));
        hands.add(hand2);            
        SimulationResult result = new SimulationResult(hands, board, 165, PokerGameType.TEXAS);          
        Set<AbstractStartingHand> winningHand = new HashSet<AbstractStartingHand>();
        // Hand 1 wins 100 times        
        winningHand.add(hand1);
        for (int i = 0; i < 100; i++) {
            result.addResultForOneSimulation(winningHand);
        }        
        // The hands tie 5 times
        winningHand.add(hand2);        
        for (int i = 0; i < 5; i++) {
            result.addResultForOneSimulation(winningHand);        
        }
        // Hand 2 wins 60 times
        winningHand.remove(hand1);
        for (int i = 0; i < 60; i++) {
            result.addResultForOneSimulation(winningHand);        
        }        
        assertEquals(60.61, result.getWinPercentageForHand(hand1, digits), 0);
        assertEquals(36.36, result.getLossPercentageForHand(hand1, digits), 0);
        assertEquals(3.030, result.getTiePercentageForHand(hand1, digits), 0);        
        assertEquals(0.6212, result.getEquityForHand(hand1, digits), 0);
        
        assertEquals(36.36, result.getWinPercentageForHand(hand2, digits), 0);
        assertEquals(60.61, result.getLossPercentageForHand(hand2, digits), 0);
        assertEquals(3.030, result.getTiePercentageForHand(hand2, digits), 0);        
        assertEquals(0.3788, result.getEquityForHand(hand2, digits), 0);
    }
    
    @Test
    public void testResultsForManySimulationsThreeHands() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>();
        FiveCardBoard board = new FiveCardBoard();               
        int digits = 4;
        
        TexasHoldemStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        hands.add(hand1);
        TexasHoldemStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE));
        hands.add(hand2);           
        TexasHoldemStartingHand hand3 = new TexasHoldemStartingHand(new Card(Suit.HEART, Rank.NINE), new Card(Suit.HEART, Rank.THREE));
        hands.add(hand3);             
        SimulationResult result = new SimulationResult(hands, board, 200, PokerGameType.TEXAS);          
        Set<AbstractStartingHand> winningHand = new HashSet<AbstractStartingHand>();
        // Hand 1 wins 100 times        
        winningHand.add(hand1);
        for (int i = 0; i < 100; i++) {
            result.addResultForOneSimulation(winningHand);
        }        
        // The hands 1 and 2 tie 10 times
        winningHand.add(hand2);        
        for (int i = 0; i < 10; i++) {
            result.addResultForOneSimulation(winningHand);        
        }
        // Hand 2 wins 50 times
        winningHand.remove(hand1);
        for (int i = 0; i < 50; i++) {
            result.addResultForOneSimulation(winningHand);        
        }  
        // Hands 2 and 3 tie 5 times
        winningHand.add(hand3);
        for (int i = 0; i < 5; i++) {
            result.addResultForOneSimulation(winningHand);        
        }        
        // Hand 3 wins 25 times
        winningHand.remove(hand2);
        for (int i = 0; i < 25; i++) {
            result.addResultForOneSimulation(winningHand);        
        }
        // Hands 1 and 3 tie 5 times
        winningHand.add(hand1);
        for (int i = 0; i < 5; i++) {
            result.addResultForOneSimulation(winningHand);        
        }        
        // Hands 1, 2 and 3 tie 5 times
        winningHand.add(hand2);
        for (int i = 0; i < 5; i++) {
            result.addResultForOneSimulation(winningHand);        
        }         
        
        
        assertEquals(50, result.getWinPercentageForHand(hand1, digits), 0);
        assertEquals(40, result.getLossPercentageForHand(hand1, digits), 0);
        assertEquals(10, result.getTiePercentageForHand(hand1, digits), 0);        
        assertEquals(0.5458, result.getEquityForHand(hand1, digits), 0);
        
        assertEquals(25, result.getWinPercentageForHand(hand2, digits), 0);
        assertEquals(65, result.getLossPercentageForHand(hand2, digits), 0);
        assertEquals(10, result.getTiePercentageForHand(hand2, digits), 0);        
        assertEquals(0.2958, result.getEquityForHand(hand2, digits), 0);
        
        assertEquals(12.5, result.getWinPercentageForHand(hand3, digits), 0);
        assertEquals(80, result.getLossPercentageForHand(hand3, digits), 0);
        assertEquals(7.5, result.getTiePercentageForHand(hand3, digits), 0);        
        assertEquals(0.1583, result.getEquityForHand(hand3, digits), 0);        
    }
    
    @Test
    public void testRandomSimulationResult() {
        Set<AbstractStartingHand> hands = new HashSet<AbstractStartingHand>(); 
        List<AbstractStartingHand> handList = new ArrayList<AbstractStartingHand>();        
        FiveCardBoard board = new FiveCardBoard(); 
        int digits = 9;
        int simulations = 10000;
        double epsilon = Math.pow(10, -8);
        
        TexasHoldemStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        hands.add(hand1);
        handList.add(hand1);
        TexasHoldemStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.THREE));
        hands.add(hand2);  
        handList.add(hand2);        
        TexasHoldemStartingHand hand3 = new TexasHoldemStartingHand(new Card(Suit.SPADE, Rank.FIVE), new Card(Suit.DIAMOND, Rank.FIVE));
        hands.add(hand3); 
        handList.add(hand3);        
        TexasHoldemStartingHand hand4 = new TexasHoldemStartingHand(new Card(Suit.DIAMOND, Rank.NINE), new Card(Suit.DIAMOND, Rank.THREE));
        hands.add(hand4); 
        handList.add(hand4);        
        TexasHoldemStartingHand hand5 = new TexasHoldemStartingHand(new Card(Suit.HEART, Rank.FIVE), new Card(Suit.HEART, Rank.SEVEN));
        hands.add(hand5); 
        handList.add(hand5);        
        

        
        SimulationResult result = new SimulationResult(hands, board, simulations, PokerGameType.TEXAS);        
        
        Random random = new Random();
        
        double[] probabilityForHand = {0.35,0.3,0.2,0.1,0.05};
        
        Set<AbstractStartingHand> winners = new HashSet<AbstractStartingHand>();
        
        for (int i = 0; i < simulations; i++) {
            winners.clear();
            for (int j = 0; j < 5; j++) {
                double number = random.nextDouble();
                if (number < probabilityForHand[j]) {
                    winners.add(handList.get(j));
                }
            }
            if (winners.isEmpty()) {
                winners.add(handList.get(0));
            }
            result.addResultForOneSimulation(winners);
        }
        
        // Verifying results
        double sumOfEquities = 0;
        for (int i = 0; i < 5; i++) {
            double sumOfPercentages = 0;
            sumOfPercentages += result.getWinPercentageForHand(handList.get(i), digits);
            sumOfPercentages += result.getTiePercentageForHand(handList.get(i), digits);
            sumOfPercentages += result.getLossPercentageForHand(handList.get(i), digits);            
            assertEquals(100, sumOfPercentages, epsilon);
            sumOfEquities += result.getEquityForHand(handList.get(i), digits);
        }
        
        assertEquals(1, sumOfEquities, epsilon);
    }
} 
