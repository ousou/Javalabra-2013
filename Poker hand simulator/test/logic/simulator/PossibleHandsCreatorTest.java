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
import poker.startinghands.AbstractStartingHand;
import poker.FiveCardBoard;
import poker.FiveCardPokerHand;
import poker.startinghands.OmahaHoldemStartingHand;
import poker.startinghands.SevenCardStudStartingHand;
import poker.startinghands.TexasHoldemStartingHand;

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
        AbstractStartingHand holdemHand = new TexasHoldemStartingHand(new Card(Suit.SPADE, Rank.ACE), new Card(Suit.DIAMOND, Rank.DEUCE));
        FiveCardBoard board = new FiveCardBoard();
        board.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        board.addCard(new Card(Suit.SPADE, Rank.FOUR));
        board.addCard(new Card(Suit.HEART, Rank.FIVE));
        board.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        board.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        
        // Creating all combinations and checking that the resulting list contains them
        
        List<FiveCardPokerHand> expectedResult = new ArrayList<FiveCardPokerHand>();
        
        FiveCardPokerHand hand1 = new FiveCardPokerHand();
        hand1.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand1.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand1.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand1.addCard(new Card(Suit.SPADE, Rank.FOUR));
        hand1.addCard(new Card(Suit.HEART, Rank.FIVE));
        
        expectedResult.add(hand1);
        
        FiveCardPokerHand hand2 = new FiveCardPokerHand();
        hand2.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand2.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand2.addCard(new Card(Suit.DIAMOND, Rank.THREE));        
        hand2.addCard(new Card(Suit.SPADE, Rank.FOUR));
        hand2.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        
        expectedResult.add(hand2);        
        
        FiveCardPokerHand hand3 = new FiveCardPokerHand();
        hand3.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand3.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand3.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand3.addCard(new Card(Suit.SPADE, Rank.FOUR));
        hand3.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        
        expectedResult.add(hand3);     
        
        FiveCardPokerHand hand4 = new FiveCardPokerHand();
        hand4.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand4.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand4.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand4.addCard(new Card(Suit.HEART, Rank.FIVE));
        hand4.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        
        expectedResult.add(hand4);             
        
        FiveCardPokerHand hand5 = new FiveCardPokerHand();
        hand5.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand5.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand5.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand5.addCard(new Card(Suit.HEART, Rank.FIVE));
        hand5.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        
        expectedResult.add(hand5);           
        
        FiveCardPokerHand hand6 = new FiveCardPokerHand();
        hand6.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand6.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand6.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand6.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        hand6.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        
        expectedResult.add(hand6);              
        
        FiveCardPokerHand hand7 = new FiveCardPokerHand();
        hand7.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand7.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand7.addCard(new Card(Suit.SPADE, Rank.FOUR));
        hand7.addCard(new Card(Suit.HEART, Rank.FIVE));        
        hand7.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        
        expectedResult.add(hand7);        
        
        FiveCardPokerHand hand8 = new FiveCardPokerHand();
        hand8.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand8.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand8.addCard(new Card(Suit.SPADE, Rank.FOUR));
        hand8.addCard(new Card(Suit.HEART, Rank.FIVE));        
        hand8.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        
        expectedResult.add(hand8);    
        
        FiveCardPokerHand hand9 = new FiveCardPokerHand();
        hand9.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand9.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand9.addCard(new Card(Suit.SPADE, Rank.FOUR));
        hand9.addCard(new Card(Suit.DIAMOND, Rank.SIX));       
        hand9.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        
        expectedResult.add(hand9); 
        
        FiveCardPokerHand hand10 = new FiveCardPokerHand();
        hand10.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand10.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand10.addCard(new Card(Suit.HEART, Rank.FIVE));  
        hand10.addCard(new Card(Suit.DIAMOND, Rank.SIX));       
        hand10.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        
        expectedResult.add(hand10);                 
        
        FiveCardPokerHand hand11 = new FiveCardPokerHand();
        hand11.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand11.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand11.addCard(new Card(Suit.SPADE, Rank.FOUR));        
        hand11.addCard(new Card(Suit.HEART, Rank.FIVE));  
        hand11.addCard(new Card(Suit.DIAMOND, Rank.SIX));   
        
        expectedResult.add(hand11);         
        
        FiveCardPokerHand hand12 = new FiveCardPokerHand();
        hand12.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand12.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand12.addCard(new Card(Suit.SPADE, Rank.FOUR));        
        hand12.addCard(new Card(Suit.HEART, Rank.FIVE));  
        hand12.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand12);      
        
        FiveCardPokerHand hand13 = new FiveCardPokerHand();
        hand13.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand13.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand13.addCard(new Card(Suit.SPADE, Rank.FOUR));        
        hand13.addCard(new Card(Suit.DIAMOND, Rank.SIX));   
        hand13.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand13);
        
        FiveCardPokerHand hand14 = new FiveCardPokerHand();
        hand14.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand14.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand14.addCard(new Card(Suit.HEART, Rank.FIVE));         
        hand14.addCard(new Card(Suit.DIAMOND, Rank.SIX));   
        hand14.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand14);      
        
        FiveCardPokerHand hand15 = new FiveCardPokerHand();
        hand15.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand15.addCard(new Card(Suit.SPADE, Rank.FOUR)); 
        hand15.addCard(new Card(Suit.HEART, Rank.FIVE));         
        hand15.addCard(new Card(Suit.DIAMOND, Rank.SIX));   
        hand15.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand15);          
        
        FiveCardPokerHand hand16 = new FiveCardPokerHand();
        hand16.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));        
        hand16.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand16.addCard(new Card(Suit.SPADE, Rank.FOUR)); 
        hand16.addCard(new Card(Suit.HEART, Rank.FIVE));         
        hand16.addCard(new Card(Suit.DIAMOND, Rank.SIX));   
        
        expectedResult.add(hand16); 
        
        FiveCardPokerHand hand17 = new FiveCardPokerHand();
        hand17.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));        
        hand17.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand17.addCard(new Card(Suit.SPADE, Rank.FOUR)); 
        hand17.addCard(new Card(Suit.HEART, Rank.FIVE));         
        hand17.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand17);       
        
        FiveCardPokerHand hand18 = new FiveCardPokerHand();
        hand18.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));        
        hand18.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand18.addCard(new Card(Suit.SPADE, Rank.FOUR)); 
        hand18.addCard(new Card(Suit.DIAMOND, Rank.SIX));         
        hand18.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand18);
        
        FiveCardPokerHand hand19 = new FiveCardPokerHand();
        hand19.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));        
        hand19.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand19.addCard(new Card(Suit.HEART, Rank.FIVE));  
        hand19.addCard(new Card(Suit.DIAMOND, Rank.SIX));         
        hand19.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand19);        
        
        FiveCardPokerHand hand20 = new FiveCardPokerHand();
        hand20.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));        
        hand20.addCard(new Card(Suit.SPADE, Rank.FOUR)); 
        hand20.addCard(new Card(Suit.HEART, Rank.FIVE));  
        hand20.addCard(new Card(Suit.DIAMOND, Rank.SIX));         
        hand20.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand20); 
        
        
        FiveCardPokerHand hand21 = new FiveCardPokerHand();
        hand21.addCard(new Card(Suit.DIAMOND, Rank.THREE));        
        hand21.addCard(new Card(Suit.SPADE, Rank.FOUR)); 
        hand21.addCard(new Card(Suit.HEART, Rank.FIVE));  
        hand21.addCard(new Card(Suit.DIAMOND, Rank.SIX));         
        hand21.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand21);    
        
        assertEquals(21, expectedResult.size());        
        
        PossibleHandsCreator creator = new PossibleHandsCreator(holdemHand, board);
        List<FiveCardPokerHand> createAllPossibleHands = creator.createAllPossibleHands();
        assertEquals("Wrong amount of possible Texas Hold'em hands", 21, createAllPossibleHands.size());
        
        // Checking that the lists contain the same hands.
        createAllPossibleHands.removeAll(expectedResult);
        assertTrue("The method doesn't create the right hands",createAllPossibleHands.isEmpty());
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
        assertEquals("Wrong amount of possible Omaha hands",60, createAllPossibleHands.size());
    }

    @Test
    public void testCreateAllPossibleHandsSevenCardStud() {
        AbstractStartingHand sevenStudHand = new SevenCardStudStartingHand();
        assertTrue(sevenStudHand.addCard(new Card(Suit.SPADE, Rank.ACE)));
        assertTrue(sevenStudHand.addCard(new Card(Suit.DIAMOND, Rank.DEUCE)));
        assertTrue(sevenStudHand.addCard(new Card(Suit.DIAMOND, Rank.THREE)));
        assertTrue(sevenStudHand.addCard(new Card(Suit.SPADE, Rank.FOUR)));
        assertTrue(sevenStudHand.addCard(new Card(Suit.HEART, Rank.FIVE)));
        assertTrue(sevenStudHand.addCard(new Card(Suit.DIAMOND, Rank.SIX)));
        assertTrue(sevenStudHand.addCard(new Card(Suit.DIAMOND, Rank.SEVEN)));
        
        // Creating all combinations and checking that the resulting list contains them
        
        List<FiveCardPokerHand> expectedResult = new ArrayList<FiveCardPokerHand>();
        
        FiveCardPokerHand hand1 = new FiveCardPokerHand();
        hand1.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand1.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand1.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand1.addCard(new Card(Suit.SPADE, Rank.FOUR));
        hand1.addCard(new Card(Suit.HEART, Rank.FIVE));
        
        expectedResult.add(hand1);
        
        FiveCardPokerHand hand2 = new FiveCardPokerHand();
        hand2.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand2.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand2.addCard(new Card(Suit.DIAMOND, Rank.THREE));        
        hand2.addCard(new Card(Suit.SPADE, Rank.FOUR));
        hand2.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        
        expectedResult.add(hand2);        
        
        FiveCardPokerHand hand3 = new FiveCardPokerHand();
        hand3.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand3.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand3.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand3.addCard(new Card(Suit.SPADE, Rank.FOUR));
        hand3.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        
        expectedResult.add(hand3);     
        
        FiveCardPokerHand hand4 = new FiveCardPokerHand();
        hand4.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand4.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand4.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand4.addCard(new Card(Suit.HEART, Rank.FIVE));
        hand4.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        
        expectedResult.add(hand4);             
        
        FiveCardPokerHand hand5 = new FiveCardPokerHand();
        hand5.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand5.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand5.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand5.addCard(new Card(Suit.HEART, Rank.FIVE));
        hand5.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        
        expectedResult.add(hand5);           
        
        FiveCardPokerHand hand6 = new FiveCardPokerHand();
        hand6.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand6.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand6.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand6.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        hand6.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        
        expectedResult.add(hand6);              
        
        FiveCardPokerHand hand7 = new FiveCardPokerHand();
        hand7.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand7.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand7.addCard(new Card(Suit.SPADE, Rank.FOUR));
        hand7.addCard(new Card(Suit.HEART, Rank.FIVE));        
        hand7.addCard(new Card(Suit.DIAMOND, Rank.SIX));
        
        expectedResult.add(hand7);        
        
        FiveCardPokerHand hand8 = new FiveCardPokerHand();
        hand8.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand8.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand8.addCard(new Card(Suit.SPADE, Rank.FOUR));
        hand8.addCard(new Card(Suit.HEART, Rank.FIVE));        
        hand8.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        
        expectedResult.add(hand8);    
        
        FiveCardPokerHand hand9 = new FiveCardPokerHand();
        hand9.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand9.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand9.addCard(new Card(Suit.SPADE, Rank.FOUR));
        hand9.addCard(new Card(Suit.DIAMOND, Rank.SIX));       
        hand9.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        
        expectedResult.add(hand9); 
        
        FiveCardPokerHand hand10 = new FiveCardPokerHand();
        hand10.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand10.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));
        hand10.addCard(new Card(Suit.HEART, Rank.FIVE));  
        hand10.addCard(new Card(Suit.DIAMOND, Rank.SIX));       
        hand10.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));
        
        expectedResult.add(hand10);                 
        
        FiveCardPokerHand hand11 = new FiveCardPokerHand();
        hand11.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand11.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand11.addCard(new Card(Suit.SPADE, Rank.FOUR));        
        hand11.addCard(new Card(Suit.HEART, Rank.FIVE));  
        hand11.addCard(new Card(Suit.DIAMOND, Rank.SIX));   
        
        expectedResult.add(hand11);         
        
        FiveCardPokerHand hand12 = new FiveCardPokerHand();
        hand12.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand12.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand12.addCard(new Card(Suit.SPADE, Rank.FOUR));        
        hand12.addCard(new Card(Suit.HEART, Rank.FIVE));  
        hand12.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand12);      
        
        FiveCardPokerHand hand13 = new FiveCardPokerHand();
        hand13.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand13.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand13.addCard(new Card(Suit.SPADE, Rank.FOUR));        
        hand13.addCard(new Card(Suit.DIAMOND, Rank.SIX));   
        hand13.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand13);
        
        FiveCardPokerHand hand14 = new FiveCardPokerHand();
        hand14.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand14.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand14.addCard(new Card(Suit.HEART, Rank.FIVE));         
        hand14.addCard(new Card(Suit.DIAMOND, Rank.SIX));   
        hand14.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand14);      
        
        FiveCardPokerHand hand15 = new FiveCardPokerHand();
        hand15.addCard(new Card(Suit.SPADE, Rank.ACE));
        hand15.addCard(new Card(Suit.SPADE, Rank.FOUR)); 
        hand15.addCard(new Card(Suit.HEART, Rank.FIVE));         
        hand15.addCard(new Card(Suit.DIAMOND, Rank.SIX));   
        hand15.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand15);          
        
        FiveCardPokerHand hand16 = new FiveCardPokerHand();
        hand16.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));        
        hand16.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand16.addCard(new Card(Suit.SPADE, Rank.FOUR)); 
        hand16.addCard(new Card(Suit.HEART, Rank.FIVE));         
        hand16.addCard(new Card(Suit.DIAMOND, Rank.SIX));   
        
        expectedResult.add(hand16); 
        
        FiveCardPokerHand hand17 = new FiveCardPokerHand();
        hand17.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));        
        hand17.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand17.addCard(new Card(Suit.SPADE, Rank.FOUR)); 
        hand17.addCard(new Card(Suit.HEART, Rank.FIVE));         
        hand17.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand17);       
        
        FiveCardPokerHand hand18 = new FiveCardPokerHand();
        hand18.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));        
        hand18.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand18.addCard(new Card(Suit.SPADE, Rank.FOUR)); 
        hand18.addCard(new Card(Suit.DIAMOND, Rank.SIX));         
        hand18.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand18);
        
        FiveCardPokerHand hand19 = new FiveCardPokerHand();
        hand19.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));        
        hand19.addCard(new Card(Suit.DIAMOND, Rank.THREE));
        hand19.addCard(new Card(Suit.HEART, Rank.FIVE));  
        hand19.addCard(new Card(Suit.DIAMOND, Rank.SIX));         
        hand19.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand19);        
        
        FiveCardPokerHand hand20 = new FiveCardPokerHand();
        hand20.addCard(new Card(Suit.DIAMOND, Rank.DEUCE));        
        hand20.addCard(new Card(Suit.SPADE, Rank.FOUR)); 
        hand20.addCard(new Card(Suit.HEART, Rank.FIVE));  
        hand20.addCard(new Card(Suit.DIAMOND, Rank.SIX));         
        hand20.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand20); 
        
        
        FiveCardPokerHand hand21 = new FiveCardPokerHand();
        hand21.addCard(new Card(Suit.DIAMOND, Rank.THREE));        
        hand21.addCard(new Card(Suit.SPADE, Rank.FOUR)); 
        hand21.addCard(new Card(Suit.HEART, Rank.FIVE));  
        hand21.addCard(new Card(Suit.DIAMOND, Rank.SIX));         
        hand21.addCard(new Card(Suit.DIAMOND, Rank.SEVEN));   
        
        expectedResult.add(hand21);    
        
        assertEquals(21, expectedResult.size());        
        
        PossibleHandsCreator creator = new PossibleHandsCreator(sevenStudHand);
        List<FiveCardPokerHand> createAllPossibleHands = creator.createAllPossibleHands();
        assertEquals("Wrong amount of possible Seven card stud hands", 21, createAllPossibleHands.size());
        
        // Checking that the lists contain the same hands.
        createAllPossibleHands.removeAll(expectedResult);
        assertTrue("The method doesn't create the right hands",createAllPossibleHands.isEmpty());
    }

}