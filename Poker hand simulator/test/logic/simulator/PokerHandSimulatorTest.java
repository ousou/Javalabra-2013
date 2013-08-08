package logic.simulator;

import card.Card;
import card.Rank;
import card.Suit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import poker.AbstractStartingHand;
import poker.OmahaHoldemStartingHand;
import poker.TexasHoldemStartingHand;

/**
 *
 * @author Sebastian Björkqvist
 */
public class PokerHandSimulatorTest {

    public PokerHandSimulatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNonPositiveAmountOfSimulations() {
        AbstractStartingHand winner = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        AbstractStartingHand loser = new TexasHoldemStartingHand(new Card(Suit.SPADE, Rank.DEUCE), new Card(Suit.HEART, Rank.SEVEN));
        
        List<AbstractStartingHand> startingHands = new ArrayList<AbstractStartingHand>(); 
        startingHands.add(winner);
        startingHands.add(loser);
        
        PokerHandSimulator simulator = new PokerHandSimulator(startingHands, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOnlyOneStartingHand() {
        AbstractStartingHand winner = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        
        List<AbstractStartingHand> startingHands = new ArrayList<AbstractStartingHand>(); 
        startingHands.add(winner);
        
        PokerHandSimulator simulator = new PokerHandSimulator(startingHands, 1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testStartingHandsHaveDifferentPokerGameTypes() {
        AbstractStartingHand texas = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        AbstractStartingHand omaha = new OmahaHoldemStartingHand();
        omaha.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        omaha.addCard(new Card(Suit.SPADE, Rank.THREE));
        omaha.addCard(new Card(Suit.CLUB, Rank.SEVEN));
        omaha.addCard(new Card(Suit.DIAMOND, Rank.NINE));        
        
        List<AbstractStartingHand> startingHands = new ArrayList<AbstractStartingHand>(); 
        startingHands.add(texas);
        startingHands.add(omaha);
        
        PokerHandSimulator simulator = new PokerHandSimulator(startingHands, 1);
    }    
    
    @Test(expected = IllegalArgumentException.class)
    public void testStartingHandsHaveOverlappingCards() {
        AbstractStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        AbstractStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.ACE));        
        
        List<AbstractStartingHand> startingHands = new ArrayList<AbstractStartingHand>(); 
        startingHands.add(hand1);
        startingHands.add(hand2);        
        
        PokerHandSimulator simulator = new PokerHandSimulator(startingHands, 1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testStartingHandAndBoardHaveOverlappingCards() {
        AbstractStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        AbstractStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.KING));        
        
        List<AbstractStartingHand> startingHands = new ArrayList<AbstractStartingHand>(); 
        startingHands.add(hand1);
        startingHands.add(hand2);        
        
        List<Card> boardCards = new ArrayList<Card>();
        boardCards.add(new Card(Suit.HEART, Rank.ACE));
        boardCards.add(new Card(Suit.SPADE, Rank.KING));
        boardCards.add(new Card(Suit.DIAMOND, Rank.JACK));        
        
        PokerHandSimulator simulator = new PokerHandSimulator(startingHands, boardCards, 1);
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
        
        PokerHandSimulator simulator = new PokerHandSimulator(startingHands, boardCards, 1);
        Set<Integer> winners = simulator.simulateHand();
        assertEquals(1, winners.size());
        assertTrue(winners.contains(0));
    }

}