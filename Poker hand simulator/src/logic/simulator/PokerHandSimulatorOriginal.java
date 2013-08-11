package logic.simulator;

import card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import poker.startinghands.AbstractStartingHand;
import poker.FiveCardBoard;
import poker.FiveCardPokerHand;

/**
 * Original implementation of AbstractPokerHandSimulator.
 *
 * The class supports only community card games at the moment.
 * 
 * This class does extra work by sorting all the possible hands created
 * during the simulation, when it would be enough to find the best five card hand
 * for each starting hand. Other implementations of IPokerHandSimulator do less
 * work and are faster.
 * 
 * @todo Add support for non-community card games.
 *
 * @author Sebastian Björkqvist
 */
public class PokerHandSimulatorOriginal extends AbstractPokerHandSimulator {

    /**
     * Creates a new hand simulator with the given starting hands and board
     * cards.
     *
     * @param startingHands List of starting hands
     * @param boardCards List of board cards.
     * @param numberOfSimulations Number of simulations.
     * @throws IllegalArgumentException if numberOfSimulations isn't positive
     * @throws IllegalArgumentException if there are less than two
     * startingHands.
     */    
    public PokerHandSimulatorOriginal(List<AbstractStartingHand> startingHands, List<Card> boardCards, int numberOfSimulations) {
        super(startingHands, boardCards, numberOfSimulations);
    }

    /**
     * Creates a new hand simulator without any board cards.
     *
     * The board will be used in the simulation if the given starting hands
     * have a game type with uses community cards.
     * 
     * @param startingHands List of starting hands
     * @param numberOfSimulations Number of simulations.
     * @throws IllegalArgumentException if numberOfSimulations isn't positive
     * @throws IllegalArgumentException if there are less than two
     * startingHands.
     * @throws IllegalArgumentException if hands or the board have overlapping
     * cards, or if some of the starting hands aren't full.
     */    
    public PokerHandSimulatorOriginal(List<AbstractStartingHand> startingHands, int numberOfSimulations) {
        super(startingHands, numberOfSimulations);
    }   
    
    @Override
    protected void createBestHands(List<FiveCardPokerHand> allBestHands, Map<FiveCardPokerHand, List<AbstractStartingHand>> bestFiveCardHandForStartingHand, FiveCardBoard simulatedBoard) {
        /* Creating all possible hands for each starting hand, and determining the best possible
         * hand each starting hand can form.
         */
        for (int i = 0; i < startingHands.size(); i++) {
            PossibleHandsCreator handCreator = new PossibleHandsCreator(startingHands.get(i), simulatedBoard);
            List<FiveCardPokerHand> allHands = handCreator.createAllPossibleHands();
            Collections.sort(allHands, fiveCardPokerHandComparator);
            if (allHands.isEmpty()) {
                throw new RuntimeException("No hands created for starting hand " + startingHands.get(i));
            }            
            
            FiveCardPokerHand bestHand = allHands.get(0);
            allBestHands.add(bestHand);
            if (!bestFiveCardHandForStartingHand.containsKey(bestHand)) {
                bestFiveCardHandForStartingHand.put(bestHand, new ArrayList<AbstractStartingHand>());
            }
            bestFiveCardHandForStartingHand.get(bestHand).add(startingHands.get(i));
        }
    }    
}