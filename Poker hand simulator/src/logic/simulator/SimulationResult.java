package logic.simulator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import poker.AbstractStartingHand;
import poker.FiveCardBoard;
import poker.PokerGameType;

/**
 * Collects all the information of a performed simulation.
 * 
 * @author Sebastian Björkqvist
 * 
 * @todo Implement inserting and outputting the results
 */
public class SimulationResult {
    
    private List<AbstractStartingHand> startingHands;
    private FiveCardBoard board;
    private int numberOfSimulations;
    private PokerGameType gameType;
    private Map<AbstractStartingHand,List<Integer>> resultForHand;

    /**
     * Creates a new SimulationResult for a game with board cards.
     * 
     * For games without board cards, use the other constructor.
     * 
     * @param startingHands List of starting hands
     * @param board The board
     * @param numberOfSimulations Number of performed simulations
     * @param gameType Poker game type
     * @throws IllegalArgumentException if any of the objects are null.
     * @throws IllegalArgumentException if numberOfSimulations isn't positive
     * @throws IllegalArgumentException if there are fewer than two starting hands.
     * @throws IllegalArgumentException if the gameType isn't a community card game.
     * @throws IllegalArgumentException if the hands don't have the game type given in the constructor      
     */
    public SimulationResult(List<AbstractStartingHand> startingHands, FiveCardBoard board, int numberOfSimulations, PokerGameType gameType) {
        checkArguments(startingHands, numberOfSimulations, gameType);
        initalizeVariables(startingHands, numberOfSimulations, gameType);
        if (board == null) {
            throw new IllegalArgumentException("Board can't be null.");
        }
        if (!gameType.isCommunityCardGame()) {
            throw new IllegalArgumentException("A board can't be used if the gameType isn't a community card game");
        }
        this.board = board;        
    }

    /**
     * Creates a new SimulationResult for a game without board cards.
     * 
     * For games with board cards, use the other constructor.
     * 
     * @param startingHands List of starting hands
     * @param numberOfSimulations Number of performed simulations
     * @param gameType Poker game type
     * @throws IllegalArgumentException if any of the objects are null
     * @throws IllegalArgumentException if numberOfSimulations isn't positive
     * @throws IllegalArgumentException if there are fewer than two starting hands.
     * @throws IllegalArgumentException if the gameType is a community card game
     * @throws IllegalArgumentException if the hands don't have the game type given in the constructor
     */
    public SimulationResult(List<AbstractStartingHand> startingHands, int numberOfSimulations, PokerGameType gameType) {
        checkArguments(startingHands, numberOfSimulations, gameType);
        initalizeVariables(startingHands, numberOfSimulations, gameType);
        if (gameType.isCommunityCardGame()) {        
            throw new IllegalArgumentException("The game type can't be a community card game");
        }
    }    

    /**
     * Checks that the arguments given in the constructor are correct.
     * 
     * If some of them aren't correct, it will throw an IllegalArgumentException.
     * 
     * @see SimulationResult
     * 
     * @param startingHands
     * @param numberOfSimulations
     * @param gameType 
     */
    private void checkArguments(List<AbstractStartingHand> startingHands, int numberOfSimulations, PokerGameType gameType) {
        if (startingHands == null) {
            throw new IllegalArgumentException("Starting hands list can't be null.");
        }
        if (gameType == null) {
            throw new IllegalArgumentException("Game type list can't be null.");            
        }
        if (startingHands.size() < 2) {
            throw new IllegalArgumentException("There must be atleast two starting hands.");
        }
        if (numberOfSimulations < 1) {
            throw new IllegalArgumentException("Number of simulations must be positive");
        }
        for (AbstractStartingHand hand : startingHands) {
            if (hand.getGameType() != gameType) {
                throw new IllegalArgumentException("Hand " + hand + " has the wrong game type");
            }
        }
    }

    /**
     * Initializes variables in constructor.
     * 
     * @see SimulationResult
     * 
     * @param startingHands List of starting hands
     * @param numberOfSimulations Number of simulations
     * @param gameType Game type
     */
    private void initalizeVariables(List<AbstractStartingHand> startingHands, int numberOfSimulations, PokerGameType gameType) {
        this.startingHands = startingHands;
        this.numberOfSimulations = numberOfSimulations;
        this.gameType = gameType;
        this.resultForHand = new HashMap<AbstractStartingHand,List<Integer>>();
    }
    
}
