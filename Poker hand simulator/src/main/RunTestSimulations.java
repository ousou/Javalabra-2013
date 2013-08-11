package main;

import card.Card;
import card.Rank;
import card.Suit;
import java.util.ArrayList;
import java.util.List;
import logic.simulator.PokerHandSimulator;
import logic.simulator.SimulationResult;
import poker.AbstractStartingHand;
import poker.TexasHoldemStartingHand;

/**
 *
 * @author Sebastian Björkqvist
 */
public class RunTestSimulations {

    public static void main(String[] args) {
                
        int times = 2;
        System.out.println("Simulating As Ac vs. 5h 5c " + times + " times:");
        
        AbstractStartingHand aces = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.ACE), 
                new Card(Suit.SPADE, Rank.ACE));
        AbstractStartingHand fives = new TexasHoldemStartingHand(new Card(Suit.CLUB, Rank.FIVE), 
                new Card(Suit.HEART, Rank.FIVE));
        List<AbstractStartingHand> hands = new ArrayList<AbstractStartingHand>();
        hands.add(aces);
        hands.add(fives);
        
        
        PokerHandSimulator simulator = new PokerHandSimulator(hands, times);
        SimulationResult result = simulator.performSimulation();
        
        System.out.println("As Ac expected value: " + result.getExpectedValueForHand(aces, 3));
//        System.out.println("As Ac win: " + result.getWinPercentageForHand(aces, 3));
        System.out.println("5h 5c expected value: " + result.getExpectedValueForHand(fives, 3));
    }
}
