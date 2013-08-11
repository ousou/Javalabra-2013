package logic.simulator;

import card.Card;
import card.CardDeckStandard;
import card.ICardDeck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import poker.AbstractStartingHand;
import poker.FiveCardBoard;
import poker.FiveCardPokerHand;
import poker.comparators.FiveCardPokerHandComparator;



/**
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
    protected Set<AbstractStartingHand> simulateHand() {
        Set<AbstractStartingHand> winningHands = new HashSet<AbstractStartingHand>();        
        ICardDeck deck = new CardDeckStandard(removedCards);

        if (!gameType.isCommunityCardGame()) {
            throw new UnsupportedOperationException("Non-community card games not supported yet.");
        }
        
        FiveCardBoard simulatedBoard = copyOfBoard();
        
        while (!simulatedBoard.isFull()) {
            Card nextCard = deck.getCard();
            if (nextCard == null) {
                throw new RuntimeException("The deck ran out of cards!");
            }
            simulatedBoard.addCard(nextCard);
        }

        Map<FiveCardPokerHand, List<AbstractStartingHand>> bestFiveCardHandForThisStartingHand = new HashMap<FiveCardPokerHand, List<AbstractStartingHand>>();
        FiveCardPokerHandComparator handComparator = new FiveCardPokerHandComparator();
        List<FiveCardPokerHand> allBestHands = new ArrayList<FiveCardPokerHand>();
        
        createBestHands(handComparator, allBestHands, bestFiveCardHandForThisStartingHand, simulatedBoard);
        
        // Determining the winning hand by sorting the list of best hands.
        Collections.sort(allBestHands, handComparator);

        // Adding the index or indices of the best hand to the set.
        winningHands.addAll(bestFiveCardHandForThisStartingHand.get(allBestHands.get(0)));
        
        // Checking if other hands tie with this hand, and if so, we add them to the set also
        int nextIndex = 1;
        while (nextIndex < allBestHands.size() && 
                handComparator.compare(allBestHands.get(0), allBestHands.get(nextIndex)) == 0) {
            winningHands.addAll(bestFiveCardHandForThisStartingHand.get(allBestHands.get(nextIndex)));
            nextIndex++;
        }        
        
        return winningHands;
    }

}
