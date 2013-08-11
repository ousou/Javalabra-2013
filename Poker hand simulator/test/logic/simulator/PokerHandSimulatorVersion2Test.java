package logic.simulator;

import card.Card;
import card.Rank;
import card.Suit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import poker.AbstractStartingHand;
import poker.FiveCardBoard;
import poker.FiveCardPokerHand;
import poker.startinghands.TexasHoldemStartingHand;

/**
 *
 * @author Sebastian Björkqvist
 */
public class PokerHandSimulatorVersion2Test {

    public PokerHandSimulatorVersion2Test() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    
    
    
    @Test
    public void testSimulateHand() {
        AbstractStartingHand winner = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        AbstractStartingHand loser = new TexasHoldemStartingHand(new Card(Suit.SPADE, Rank.DEUCE), new Card(Suit.HEART, Rank.SEVEN));
        
        List<AbstractStartingHand> startingHands = new ArrayList<AbstractStartingHand>();
        startingHands.add(winner);
        startingHands.add(loser);
        
        // Making sure the hand winner always wins
        List<Card> boardCards = new ArrayList<Card>();
        boardCards.add(new Card(Suit.HEART, Rank.ACE));
        boardCards.add(new Card(Suit.DIAMOND, Rank.ACE));
        boardCards.add(new Card(Suit.DIAMOND, Rank.JACK));
        
        AbstractPokerHandSimulator simulator = new PokerHandSimulatorVersion2(startingHands, boardCards, 1);
        Set<AbstractStartingHand> winners = simulator.simulateHand();
        assertEquals(1, winners.size());
        assertTrue(winners.contains(winner));
    }
    
    @Test
    public void testPerformSimulation() {
        AbstractStartingHand winner = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        AbstractStartingHand loser = new TexasHoldemStartingHand(new Card(Suit.SPADE, Rank.DEUCE), new Card(Suit.HEART, Rank.SEVEN));
        
        int digits = 3;
        
        List<AbstractStartingHand> startingHands = new ArrayList<AbstractStartingHand>();
        startingHands.add(winner);
        startingHands.add(loser);
        
        // Making sure the hand winner always wins
        List<Card> boardCards = new ArrayList<Card>();
        boardCards.add(new Card(Suit.HEART, Rank.ACE));
        boardCards.add(new Card(Suit.DIAMOND, Rank.ACE));
        boardCards.add(new Card(Suit.DIAMOND, Rank.JACK));
        
        AbstractPokerHandSimulator simulator = new PokerHandSimulatorVersion2(startingHands, boardCards, 10000);
        SimulationResult result = simulator.performSimulation();
        
        // Checking that the hand winner always won
        assertEquals(1, result.getEquityForHand(winner, digits), 0);
        assertEquals(0, result.getEquityForHand(loser, digits), 0);        
        
        assertEquals(100, result.getWinPercentageForHand(winner, digits), 0);
        assertEquals(0, result.getTiePercentageForHand(winner, digits), 0);
        assertEquals(0, result.getLossPercentageForHand(winner, digits), 0);
        
        assertEquals(0, result.getWinPercentageForHand(loser, digits), 0);
        assertEquals(0, result.getTiePercentageForHand(loser, digits), 0);
        assertEquals(100, result.getLossPercentageForHand(loser, digits), 0);        
    }
    
    @Test
    public void testPerformSimulation2() {
        AbstractStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.KING));
        AbstractStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.HEART, Rank.SIX), new Card(Suit.HEART, Rank.SEVEN));
        
        int digits = 3;
        
        List<AbstractStartingHand> startingHands = new ArrayList<AbstractStartingHand>();
        startingHands.add(hand1);
        startingHands.add(hand2);
        
        // Creating a board that give the hands about equal winning chances
        List<Card> boardCards = new ArrayList<Card>();
        boardCards.add(new Card(Suit.HEART, Rank.KING));
        boardCards.add(new Card(Suit.DIAMOND, Rank.NINE));
        boardCards.add(new Card(Suit.HEART, Rank.EIGHT));
        
        AbstractPokerHandSimulator simulator = new PokerHandSimulatorVersion2(startingHands, boardCards, 10000);
        SimulationResult result = simulator.performSimulation();
        
        // Checking that the results are sensible
        System.out.println("Hand 1 (top pair) EV: " + result.getEquityForHand(hand1, digits));
        System.out.println("Hand 2 (open ended straight + flush draw) EV: " + result.getEquityForHand(hand2, digits));
        
        assertTrue(result.getEquityForHand(hand1, digits) > 0.4);
        assertTrue(result.getEquityForHand(hand1, digits) < 0.6);   
        
        assertTrue(result.getEquityForHand(hand2, digits) > 0.4);
        assertTrue(result.getEquityForHand(hand2, digits) < 0.6);
        
        assertTrue(result.getWinPercentageForHand(hand1, digits) > 40);
        assertTrue(result.getWinPercentageForHand(hand1, digits) < 60);        
        assertTrue(result.getTiePercentageForHand(hand1, digits) < 5);
        assertTrue(result.getLossPercentageForHand(hand1, digits) > 40);
        assertTrue(result.getLossPercentageForHand(hand1, digits) < 60);     
        
        assertTrue(result.getWinPercentageForHand(hand2, digits) > 40);
        assertTrue(result.getWinPercentageForHand(hand2, digits) < 60);        
        assertTrue(result.getTiePercentageForHand(hand2, digits) < 5);
        assertTrue(result.getLossPercentageForHand(hand2, digits) > 40);
        assertTrue(result.getLossPercentageForHand(hand2, digits) < 60);           
    }
    
    @Test
    public void testPerformSimulation3() {
        // These starting hands have about equal expected value pre-flop
        AbstractStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.KING));
        AbstractStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.HEART, Rank.SIX), new Card(Suit.SPADE, Rank.SIX));        
        int digits = 3;
        
        List<AbstractStartingHand> startingHands = new ArrayList<AbstractStartingHand>();
        startingHands.add(hand1);
        startingHands.add(hand2);
       
        
        AbstractPokerHandSimulator simulator = new PokerHandSimulatorVersion2(startingHands, 10000);
        SimulationResult result = simulator.performSimulation();
        
        // Checking that the results are sensible
        System.out.println("Hand 1 (Ace-king) EV: " + result.getEquityForHand(hand1, digits));
        System.out.println("Hand 2 (Pair of sixes) EV: " + result.getEquityForHand(hand2, digits));
        
        assertTrue(result.getEquityForHand(hand1, digits) > 0.4);
        assertTrue(result.getEquityForHand(hand1, digits) < 0.6);   
        
        assertTrue(result.getEquityForHand(hand2, digits) > 0.4);
        assertTrue(result.getEquityForHand(hand2, digits) < 0.6);
        
        assertTrue(result.getWinPercentageForHand(hand1, digits) > 40);
        assertTrue(result.getWinPercentageForHand(hand1, digits) < 60);        
        assertTrue(result.getTiePercentageForHand(hand1, digits) < 5);
        assertTrue(result.getLossPercentageForHand(hand1, digits) > 40);
        assertTrue(result.getLossPercentageForHand(hand1, digits) < 60);     
        
        assertTrue(result.getWinPercentageForHand(hand2, digits) > 40);
        assertTrue(result.getWinPercentageForHand(hand2, digits) < 60);        
        assertTrue(result.getTiePercentageForHand(hand2, digits) < 5);
        assertTrue(result.getLossPercentageForHand(hand2, digits) > 40);
        assertTrue(result.getLossPercentageForHand(hand2, digits) < 60);           
    }
    
    @Test
    public void testPerformSimulationWith2Threads() throws InterruptedException {
        AbstractStartingHand winner = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        AbstractStartingHand loser = new TexasHoldemStartingHand(new Card(Suit.SPADE, Rank.DEUCE), new Card(Suit.HEART, Rank.SEVEN));
        
        int digits = 3;
        
        List<AbstractStartingHand> startingHands = new ArrayList<AbstractStartingHand>();
        startingHands.add(winner);
        startingHands.add(loser);
        
        // Making sure the hand winner always wins
        List<Card> boardCards = new ArrayList<Card>();
        boardCards.add(new Card(Suit.HEART, Rank.ACE));
        boardCards.add(new Card(Suit.DIAMOND, Rank.ACE));
        boardCards.add(new Card(Suit.DIAMOND, Rank.JACK));
        
        AbstractPokerHandSimulator simulator = new PokerHandSimulatorVersion2(startingHands, boardCards, 10000);
        SimulationResult result = simulator.performSimulation(2);
        
        // Checking that the hand winner always won
        assertEquals(1, result.getEquityForHand(winner, digits), 0);
        assertEquals(0, result.getEquityForHand(loser, digits), 0);        
        
        assertEquals(100, result.getWinPercentageForHand(winner, digits), 0);
        assertEquals(0, result.getTiePercentageForHand(winner, digits), 0);
        assertEquals(0, result.getLossPercentageForHand(winner, digits), 0);
        
        assertEquals(0, result.getWinPercentageForHand(loser, digits), 0);
        assertEquals(0, result.getTiePercentageForHand(loser, digits), 0);
        assertEquals(100, result.getLossPercentageForHand(loser, digits), 0);        
    }
    
    @Test
    public void testPerformSimulation2With4Threads() throws InterruptedException {
        AbstractStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.KING));
        AbstractStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.HEART, Rank.SIX), new Card(Suit.HEART, Rank.SEVEN));
        
        int digits = 3;
        
        List<AbstractStartingHand> startingHands = new ArrayList<AbstractStartingHand>();
        startingHands.add(hand1);
        startingHands.add(hand2);
        
        // Creating a board that give the hands about equal winning chances
        List<Card> boardCards = new ArrayList<Card>();
        boardCards.add(new Card(Suit.HEART, Rank.KING));
        boardCards.add(new Card(Suit.DIAMOND, Rank.NINE));
        boardCards.add(new Card(Suit.HEART, Rank.EIGHT));
        
        AbstractPokerHandSimulator simulator = new PokerHandSimulatorVersion2(startingHands, boardCards, 10000);
        SimulationResult result = simulator.performSimulation(4);
        
        // Checking that the results are sensible
        System.out.println("Hand 1 (top pair) EV: " + result.getEquityForHand(hand1, digits));
        System.out.println("Hand 2 (open ended straight + flush draw) EV: " + result.getEquityForHand(hand2, digits));
        
        assertTrue(result.getEquityForHand(hand1, digits) > 0.4);
        assertTrue(result.getEquityForHand(hand1, digits) < 0.6);   
        
        assertTrue(result.getEquityForHand(hand2, digits) > 0.4);
        assertTrue(result.getEquityForHand(hand2, digits) < 0.6);
        
        assertTrue(result.getWinPercentageForHand(hand1, digits) > 40);
        assertTrue(result.getWinPercentageForHand(hand1, digits) < 60);        
        assertTrue(result.getTiePercentageForHand(hand1, digits) < 5);
        assertTrue(result.getLossPercentageForHand(hand1, digits) > 40);
        assertTrue(result.getLossPercentageForHand(hand1, digits) < 60);     
        
        assertTrue(result.getWinPercentageForHand(hand2, digits) > 40);
        assertTrue(result.getWinPercentageForHand(hand2, digits) < 60);        
        assertTrue(result.getTiePercentageForHand(hand2, digits) < 5);
        assertTrue(result.getLossPercentageForHand(hand2, digits) > 40);
        assertTrue(result.getLossPercentageForHand(hand2, digits) < 60);           
    }
    
    @Test
    public void testPerformSimulation3With8Threads() throws InterruptedException {
        // These starting hands have about equal expected value pre-flop
        AbstractStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.KING));
        AbstractStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.HEART, Rank.SIX), new Card(Suit.SPADE, Rank.SIX));        
        int digits = 3;
        
        List<AbstractStartingHand> startingHands = new ArrayList<AbstractStartingHand>();
        startingHands.add(hand1);
        startingHands.add(hand2);
       
        
        AbstractPokerHandSimulator simulator = new PokerHandSimulatorVersion2(startingHands, 10000);
        SimulationResult result = simulator.performSimulation(8);
        
        // Checking that the results are sensible
        System.out.println("Hand 1 (Ace-king) EV: " + result.getEquityForHand(hand1, digits));
        System.out.println("Hand 2 (Pair of sixes) EV: " + result.getEquityForHand(hand2, digits));
        
        assertTrue(result.getEquityForHand(hand1, digits) > 0.4);
        assertTrue(result.getEquityForHand(hand1, digits) < 0.6);   
        
        assertTrue(result.getEquityForHand(hand2, digits) > 0.4);
        assertTrue(result.getEquityForHand(hand2, digits) < 0.6);
        
        assertTrue(result.getWinPercentageForHand(hand1, digits) > 40);
        assertTrue(result.getWinPercentageForHand(hand1, digits) < 60);        
        assertTrue(result.getTiePercentageForHand(hand1, digits) < 5);
        assertTrue(result.getLossPercentageForHand(hand1, digits) > 40);
        assertTrue(result.getLossPercentageForHand(hand1, digits) < 60);     
        
        assertTrue(result.getWinPercentageForHand(hand2, digits) > 40);
        assertTrue(result.getWinPercentageForHand(hand2, digits) < 60);        
        assertTrue(result.getTiePercentageForHand(hand2, digits) < 5);
        assertTrue(result.getLossPercentageForHand(hand2, digits) > 40);
        assertTrue(result.getLossPercentageForHand(hand2, digits) < 60);           
    }



}