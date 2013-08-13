package logic.simulator;

import card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import poker.startinghands.AbstractStartingHand;
import poker.FiveCardBoard;
import poker.FiveCardPokerHand;

/**
 * Better implementation of AbstractPokerHandSimulator.
 * 
 * This simulator only finds the best hand from the list
 * of all possible hands. It doesn't sort all the hands.
 * 
 * @author Sebastian Björkqvist
 */
public class PokerHandSimulatorVersion2 extends AbstractPokerHandSimulator {

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
    public PokerHandSimulatorVersion2(List<AbstractStartingHand> startingHands, List<Card> boardCards, int numberOfSimulations) {
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
    public PokerHandSimulatorVersion2(List<AbstractStartingHand> startingHands, int numberOfSimulations) {
        super(startingHands, numberOfSimulations);
    }    

    @Override
    protected void createBestHands(List<FiveCardPokerHand> allBestHands, Map<FiveCardPokerHand, List<AbstractStartingHand>> bestFiveCardHandForStartingHand,
            FiveCardBoard simulatedBoard, List<AbstractStartingHand> filledStartingHands) {
        /* Creating all possible hands for each starting hand, and determining the best possible
         * hand each starting hand can form.
         */
        for (int i = 0; i < filledStartingHands.size(); i++) {
            PossibleHandsCreator handCreator;
            if (simulatedBoard == null || !simulatedBoard.isFull()) {
                handCreator = new PossibleHandsCreator(filledStartingHands.get(i));
            } else {
                handCreator = new PossibleHandsCreator(filledStartingHands.get(i), simulatedBoard);
            }            
            List<FiveCardPokerHand> allHands = handCreator.createAllPossibleHands();
            
            if (allHands.isEmpty()) {
                throw new RuntimeException("No hands created for starting hand " + startingHands.get(i));
            }
            FiveCardPokerHand bestHand = findBestHandFromList(allHands);
            allBestHands.add(bestHand);
            if (!bestFiveCardHandForStartingHand.containsKey(bestHand)) {
                bestFiveCardHandForStartingHand.put(bestHand, new ArrayList<AbstractStartingHand>());
            }
            bestFiveCardHandForStartingHand.get(bestHand).add(startingHands.get(i));
        }
    }    

    private FiveCardPokerHand findBestHandFromList(List<FiveCardPokerHand> allHands) {
        FiveCardPokerHand bestHand = allHands.get(0);
        FiveCardPokerHand nextHand;
        for (int j = 1; j < allHands.size(); j++) {
            nextHand = allHands.get(j);
            if (fiveCardPokerHandComparator.compare(nextHand, bestHand) < 0) {
                bestHand = nextHand;
            }
        }
        return bestHand;
    }
}
