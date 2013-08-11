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
import poker.AbstractStartingHand;
import poker.startinghands.TexasHoldemStartingHand;

/**
 *
 * @author Sebastian Björkqvist
 */
public class RunTestSimulations {

    public static void main(String[] args) {
                
        int times = 50000;
        System.out.println("Simulating As Ac vs. 5h 5c " + times + " times using PokerHandSimulatorOriginal:");
        
        AbstractStartingHand aces = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), 
                new Card(Suit.SPADE, Rank.ACE));
        AbstractStartingHand fives = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.FIVE), 
                new Card(Suit.HEART, Rank.FIVE));
        List<AbstractStartingHand> hands = new ArrayList<AbstractStartingHand>();
        hands.add(aces);
        hands.add(fives);
        
        
        AbstractPokerHandSimulator simulator = new PokerHandSimulatorOriginal(hands, times);
        long start = System.currentTimeMillis();
        SimulationResult result = simulator.performSimulation();
        long end = System.currentTimeMillis();
        double timeSpentInSeconds = (1.0*end-start)/1000;        
        
        System.out.println("As Ac expected value: " + result.getExpectedValueForHand(aces, 3));
        System.out.println("5h 5c expected value: " + result.getExpectedValueForHand(fives, 3));
        System.out.println("Time: " + timeSpentInSeconds + " seconds");
        System.out.println("");
        
        System.out.println("Simulating As Ac vs. 5h 5c " + times + " times using PokerHandSimulatorVersion2:");        
        
        simulator = new PokerHandSimulatorVersion2(hands, times);
        start = System.currentTimeMillis();
        result = simulator.performSimulation();
        end = System.currentTimeMillis();
        timeSpentInSeconds = (1.0*end-start)/1000;  
        
        System.out.println("As Ac expected value: " + result.getExpectedValueForHand(aces, 3));
        System.out.println("5h 5c expected value: " + result.getExpectedValueForHand(fives, 3));
        System.out.println("Time: " + timeSpentInSeconds + " seconds");
        
    }
}
