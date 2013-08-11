package stresstesting;

import card.Card;
import card.Rank;
import card.Suit;
import java.util.ArrayList;
import java.util.List;
import logic.simulator.AbstractPokerHandSimulator;
import logic.simulator.PokerHandSimulatorOriginal;
import logic.simulator.PokerHandSimulatorVersion2;
import logic.simulator.SimulationResult;
import poker.startinghands.AbstractStartingHand;
import poker.startinghands.OmahaHoldemStartingHand;
import poker.startinghands.TexasHoldemStartingHand;

/**
 *
 * @author Sebastian Björkqvist
 */
public class RunTestSimulations {

    public static void main(String[] args) throws InterruptedException {
                
        int times = 50000;
        
        // Testing Texas hold'em simulation speed
        AbstractStartingHand aces = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), 
                new Card(Suit.SPADE, Rank.ACE));
        AbstractStartingHand fives = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.FIVE), 
                new Card(Suit.HEART, Rank.FIVE));
        List<AbstractStartingHand> hands = new ArrayList<AbstractStartingHand>();
        hands.add(aces);
        hands.add(fives);
        
        System.out.println("Simulating As Ac vs. 5h 5c " + times + " times using PokerHandSimulatorOriginal without threads:");
        
        AbstractPokerHandSimulator simulator = new PokerHandSimulatorOriginal(hands, times);
        long start = System.currentTimeMillis();
        SimulationResult result = simulator.performSimulation();
        long end = System.currentTimeMillis();
        double timeSpentInSeconds = (1.0*end-start)/1000;        
        
        System.out.println("As Ac expected value: " + result.getEquityForHand(aces, 3));
        System.out.println("5h 5c expected value: " + result.getEquityForHand(fives, 3));
        System.out.println("Time: " + timeSpentInSeconds + " seconds");
        System.out.println("");
        
        System.out.println("Simulating As Ac vs. 5h 5c " + times + " times using PokerHandSimulatorOriginal using two threads:");        
        
        simulator = new PokerHandSimulatorOriginal(hands, times);
        start = System.currentTimeMillis();
        result = simulator.performSimulation(2);
        end = System.currentTimeMillis();
        timeSpentInSeconds = (1.0*end-start)/1000;  
        
        System.out.println("As Ac expected value: " + result.getEquityForHand(aces, 3));
        System.out.println("5h 5c expected value: " + result.getEquityForHand(fives, 3));
        System.out.println("Time: " + timeSpentInSeconds + " seconds");
        System.out.println("");     
        
        System.out.println("Simulating As Ac vs. 5h 5c " + times + " times using PokerHandSimulatorVersion2 without threads:");        
        
        simulator = new PokerHandSimulatorVersion2(hands, times);
        start = System.currentTimeMillis();
        result = simulator.performSimulation();
        end = System.currentTimeMillis();
        timeSpentInSeconds = (1.0*end-start)/1000;  
        
        System.out.println("As Ac expected value: " + result.getEquityForHand(aces, 3));
        System.out.println("5h 5c expected value: " + result.getEquityForHand(fives, 3));
        System.out.println("Time: " + timeSpentInSeconds + " seconds");
        System.out.println("");
        
        System.out.println("Simulating As Ac vs. 5h 5c " + times + " times using PokerHandSimulatorVersion2 using two threads:");        
        
        simulator = new PokerHandSimulatorVersion2(hands, times);
        start = System.currentTimeMillis();
        result = simulator.performSimulation(2);
        end = System.currentTimeMillis();
        timeSpentInSeconds = (1.0*end-start)/1000;  
        
        System.out.println("As Ac expected value: " + result.getEquityForHand(aces, 3));
        System.out.println("5h 5c expected value: " + result.getEquityForHand(fives, 3));
        System.out.println("Time: " + timeSpentInSeconds + " seconds");
        System.out.println("");        
        
        
        // Testing omaha simulation speed.
        
        AbstractStartingHand AAKKds = new OmahaHoldemStartingHand();
        AAKKds.addCard(new Card(Suit.CLUB, Rank.ACE));
        AAKKds.addCard(new Card(Suit.SPADE, Rank.ACE));        
        AAKKds.addCard(new Card(Suit.CLUB, Rank.KING));        
        AAKKds.addCard(new Card(Suit.SPADE, Rank.KING));                
        AbstractStartingHand JT98ds = new OmahaHoldemStartingHand();
        JT98ds.addCard(new Card(Suit.HEART, Rank.JACK));
        JT98ds.addCard(new Card(Suit.HEART, Rank.TEN));
        JT98ds.addCard(new Card(Suit.DIAMOND, Rank.NINE));
        JT98ds.addCard(new Card(Suit.DIAMOND, Rank.EIGHT));   
        
        List<AbstractStartingHand> omahaHands = new ArrayList<AbstractStartingHand>();
        
        omahaHands.add(AAKKds);
        omahaHands.add(JT98ds);
        
        System.out.println("Simulating As Ac Ks Kc vs. Jh Th 9d 8d " + times + " times using PokerHandSimulatorOriginal without threads:");                
        
        simulator = new PokerHandSimulatorOriginal(omahaHands, times);
        start = System.currentTimeMillis();
        result = simulator.performSimulation();
        end = System.currentTimeMillis();
        timeSpentInSeconds = (1.0*end-start)/1000;  
        
        System.out.println("As Ac Ks Kc expected value: " + result.getEquityForHand(AAKKds, 3));
        System.out.println("Jh Th 9d 8d expected value: " + result.getEquityForHand(JT98ds, 3));
        System.out.println("Time: " + timeSpentInSeconds + " seconds");  
        System.out.println("");            
        
        System.out.println("Simulating As Ac Ks Kc vs. Jh Th 9d 8d " + times + " times using PokerHandSimulatorOriginal with 2 threads:");                
        
        simulator = new PokerHandSimulatorOriginal(omahaHands, times);
        start = System.currentTimeMillis();
        result = simulator.performSimulation(2);
        end = System.currentTimeMillis();
        timeSpentInSeconds = (1.0*end-start)/1000;  
        
        System.out.println("As Ac Ks Kc expected value: " + result.getEquityForHand(AAKKds, 3));
        System.out.println("Jh Th 9d 8d expected value: " + result.getEquityForHand(JT98ds, 3));
        System.out.println("Time: " + timeSpentInSeconds + " seconds");  
        System.out.println("");            
        
        System.out.println("Simulating As Ac Ks Kc vs. Jh Th 9d 8d " + times + " times using PokerHandSimulatorVersion2 without threads:");                
        
        simulator = new PokerHandSimulatorVersion2(omahaHands, times);
        start = System.currentTimeMillis();
        result = simulator.performSimulation();
        end = System.currentTimeMillis();
        timeSpentInSeconds = (1.0*end-start)/1000;     
        
        System.out.println("As Ac Ks Kc expected value: " + result.getEquityForHand(AAKKds, 3));
        System.out.println("Jh Th 9d 8d expected value: " + result.getEquityForHand(JT98ds, 3));
        System.out.println("Time: " + timeSpentInSeconds + " seconds");   
        System.out.println("");            
        
        System.out.println("Simulating As Ac Ks Kc vs. Jh Th 9d 8d " + times + " times using PokerHandSimulatorVersion2 using 2 threads:");                
        
        simulator = new PokerHandSimulatorVersion2(omahaHands, times);
        start = System.currentTimeMillis();
        result = simulator.performSimulation(2);
        end = System.currentTimeMillis();
        timeSpentInSeconds = (1.0*end-start)/1000;     
        
        System.out.println("As Ac Ks Kc expected value: " + result.getEquityForHand(AAKKds, 3));
        System.out.println("Jh Th 9d 8d expected value: " + result.getEquityForHand(JT98ds, 3));
        System.out.println("Time: " + timeSpentInSeconds + " seconds");            
    }
}
