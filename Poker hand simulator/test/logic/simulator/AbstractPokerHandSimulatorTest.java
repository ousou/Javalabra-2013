package logic.simulator;

import card.Card;
import card.ICardDeck;
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
import poker.startinghands.AbstractStartingHand;
import poker.FiveCardBoard;
import poker.FiveCardPokerHand;
import poker.startinghands.OmahaHoldemStartingHand;
import poker.startinghands.TexasHoldemStartingHand;

/**
 *
 * @author Sebastian Björkqvist
 */
public class AbstractPokerHandSimulatorTest {

    public AbstractPokerHandSimulatorTest() {
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
        
        AbstractPokerHandSimulator simulator = new AbstractPokerHandSimulatorImpl(startingHands, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOnlyOneStartingHand() {
        AbstractStartingHand winner = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        
        List<AbstractStartingHand> startingHands = new ArrayList<AbstractStartingHand>(); 
        startingHands.add(winner);
        
        AbstractPokerHandSimulator simulator = new AbstractPokerHandSimulatorImpl(startingHands, 1);
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
        
        AbstractPokerHandSimulator simulator = new AbstractPokerHandSimulatorImpl(startingHands, 1);
    }    
    
    @Test(expected = IllegalArgumentException.class)
    public void testStartingHandsHaveOverlappingCards() {
        AbstractStartingHand hand1 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), new Card(Suit.SPADE, Rank.ACE));
        AbstractStartingHand hand2 = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.DEUCE), new Card(Suit.SPADE, Rank.ACE));        
        
        List<AbstractStartingHand> startingHands = new ArrayList<AbstractStartingHand>(); 
        startingHands.add(hand1);
        startingHands.add(hand2);        
        
        AbstractPokerHandSimulator simulator = new AbstractPokerHandSimulatorImpl(startingHands, 1);
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
        
        AbstractPokerHandSimulator simulator = new AbstractPokerHandSimulatorImpl(startingHands, boardCards, 1);
    }    
    

    public static class AbstractPokerHandSimulatorImpl extends AbstractPokerHandSimulator {

        public AbstractPokerHandSimulatorImpl(List<AbstractStartingHand> startingHands, List<Card> boardCards, int numberOfSimulations) {
            super(startingHands, boardCards, numberOfSimulations);
        }

        public AbstractPokerHandSimulatorImpl(List<AbstractStartingHand> startingHands, int numberOfSimulations) {
            super(startingHands, numberOfSimulations);
        }        

        @Override
        public void createBestHands(List<FiveCardPokerHand> allBestHands, Map<FiveCardPokerHand, List<AbstractStartingHand>> bestFiveCardHandForStartingHand, FiveCardBoard simulatedBoard) {
        }
    }

}