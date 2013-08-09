package logic.simulator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import poker.AbstractStartingHand;
import poker.FiveCardBoard;
import poker.PokerGameType;

/**
 * Collects all the information of a performed simulation.
 *
 * This class takes a set of hands instead of a list to make it more effective.
 *
 * @author Sebastian Björkqvist
 *
 * @todo Implement inserting and outputting the results
 */
public class SimulationResult {

    private Set<AbstractStartingHand> startingHands;
    private FiveCardBoard board;
    private int totalNumberOfSimulationsToPerform;
    private int performedSimulations;
    private PokerGameType gameType;
    /* The map resultForHand contains the result for each of the
     * starting hands. The value is an integer array with the same
     * size as the set of the starting hands. 
     * 
     * The number that is in index 0 of the array are the number of times 
     * the hand won in a one-way tie, i.e. the times the hand won outright.
     * The number that is saved in index 1 is the number of times the 
     * hand won in a two-way tie, the index 2 the number it won in a three-way
     * tie and so on.
     * 
     * The data for different ties is needed when the expected value
     * for the hand is calculated.
     */
    private Map<AbstractStartingHand, int[]> resultForHand;

    /**
     * Creates a new SimulationResult for a game with board cards.
     *
     * For games without board cards, use the other constructor.
     *
     * @param startingHands Set of starting hands
     * @param board The board
     * @param numberOfSimulations Number of performed simulations
     * @param gameType Poker game type
     * @throws IllegalArgumentException if any of the objects are null.
     * @throws IllegalArgumentException if numberOfSimulations isn't positive
     * @throws IllegalArgumentException if there are fewer than two starting
     * hands.
     * @throws IllegalArgumentException if the gameType isn't a community card
     * game.
     * @throws IllegalArgumentException if the hands don't have the game type
     * given in the constructor
     */
    public SimulationResult(Set<AbstractStartingHand> startingHands, FiveCardBoard board, int numberOfSimulations, PokerGameType gameType) {
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
     * @param startingHands Set of starting hands
     * @param numberOfSimulations Number of performed simulations
     * @param gameType Poker game type
     * @throws IllegalArgumentException if any of the objects are null
     * @throws IllegalArgumentException if numberOfSimulations isn't positive
     * @throws IllegalArgumentException if there are fewer than two starting
     * hands.
     * @throws IllegalArgumentException if the gameType is a community card game
     * @throws IllegalArgumentException if the hands don't have the game type
     * given in the constructor
     */
    public SimulationResult(Set<AbstractStartingHand> startingHands, int numberOfSimulations, PokerGameType gameType) {
        checkArguments(startingHands, numberOfSimulations, gameType);
        initalizeVariables(startingHands, numberOfSimulations, gameType);
        if (gameType.isCommunityCardGame()) {
            throw new IllegalArgumentException("The game type can't be a community card game");
        }
    }

    /**
     * Checks that the arguments given in the constructor are correct.
     *
     * If some of them aren't correct, it will throw an
     * IllegalArgumentException.
     *
     * @see SimulationResult
     *
     * @param startingHands
     * @param numberOfSimulations
     * @param gameType
     */
    private void checkArguments(Set<AbstractStartingHand> startingHands, int numberOfSimulations, PokerGameType gameType) {
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
    private void initalizeVariables(Set<AbstractStartingHand> startingHands, int numberOfSimulations, PokerGameType gameType) {
        this.startingHands = startingHands;
        this.totalNumberOfSimulationsToPerform = numberOfSimulations;
        this.gameType = gameType;
        this.resultForHand = new HashMap<AbstractStartingHand, int[]>();
        for (AbstractStartingHand hand : this.startingHands) {
            this.resultForHand.put(hand, new int[this.startingHands.size()]);
        }
        this.performedSimulations = 0;
    }

    /**
     * Adds a result of one simulation.
     *
     * @param winners
     * @throws IllegalArgumentException if the winning hands aren't in the set
     * of starting hands.
     * @throws IllegalArgumentException if winners is null or empty.
     * @throws IllegalStateException if enough simulations have been
     * performed, i.e. the number of performed simulations is already at the
     * maximum number given in the constructor.
     */
    public void addResultForOneSimulation(Set<AbstractStartingHand> winners) {
        if (winners == null || winners.isEmpty()) {
            throw new IllegalArgumentException("Set of winning hands can't be null"
                    + "or empty!");
        }
        if (!startingHands.containsAll(winners)) {
            throw new IllegalArgumentException("Winning hands aren't contained"
                    + "in the set of starting hands!");
        }
        if (performedSimulations >= totalNumberOfSimulationsToPerform) {
            throw new IllegalStateException("Enough simulations have already"
                    + "been performed.");
        }

        int numberOfWinningHands = winners.size();
        for (AbstractStartingHand hand : winners) {
            resultForHand.get(hand)[numberOfWinningHands - 1]++;
        }
        performedSimulations++;
    }

    /**
     * Returns a copy of the set of starting hands.
     * 
     * @return Set of starting hands.
     */
    public Set<AbstractStartingHand> getStartingHands() {
        return new HashSet<AbstractStartingHand>(startingHands);
    }    
    
    /**
     * Retrieves the win percentage for a hand.
     * 
     * @param hand Starting hand
     * @param numberOfSignificantDigits Accuracy of the returned double
     * @return Winning probability in percent.
     * @throws IllegalArgumentException if the hand is null or isn't
     * contained in the set of starting hands of the simulation result.
     * @throws IllegalStateException if enough simulations haven't been 
     * performed yet.
     * @throws IllegalArgumentException if numberOfSignificantDigits isn't
     * positive.
     */
    public double getWinPercentageForHand(AbstractStartingHand hand, int numberOfSignificantDigits) {
        checkArgumentsForResultRetrieval(hand, numberOfSignificantDigits);
        BigDecimal winningPercentage = new BigDecimal(resultForHand.get(hand)[0]);
        winningPercentage = winningPercentage.divide(new BigDecimal(performedSimulations), 
                new MathContext(numberOfSignificantDigits));
        return winningPercentage.doubleValue();
    }
    
    /**
     * Retrieves the tie percentage for a hand.
     * 
     * All types of ties are included here. Different types of ties
     * are used to calculate the expected value.
     * 
     * @param hand Starting hand
     * @param numberOfSignificantDigits Accuracy of the returned double
     * @return Tying probability in percent.
     * @throws IllegalArgumentException if the hand is null or isn't
     * contained in the set of starting hands of the simulation result.
     * @throws IllegalStateException if enough simulations haven't been 
     * performed yet.
     * @throws IllegalArgumentException if numberOfSignificantDigits isn't
     * positive.
     */
    public double getTiePercentageForHand(AbstractStartingHand hand, int numberOfSignificantDigits) {
        checkArgumentsForResultRetrieval(hand, numberOfSignificantDigits);
        int numberOfTies = 0;
        int[] resultForThisHand = resultForHand.get(hand);
        for (int i = 1; i < resultForThisHand.length; i++) {
            numberOfTies += resultForThisHand[i];
        }
        BigDecimal tiePercentage = new BigDecimal(numberOfTies);
        tiePercentage = tiePercentage.divide(new BigDecimal(performedSimulations), 
                new MathContext(numberOfSignificantDigits));
        return tiePercentage.doubleValue();
    }
    
    /**
     * Retrieves the loss percentage for a hand.
     * 
     * @param hand Starting hand
     * @param numberOfSignificantDigits Accuracy of the returned double
     * @return Losing probability in percent.
     * @throws IllegalArgumentException if the hand is null or isn't
     * contained in the set of starting hands of the simulation result.
     * @throws IllegalStateException if enough simulations haven't been 
     * performed yet.
     * @throws IllegalArgumentException if numberOfSignificantDigits isn't
     * positive.  
     */
    public double getLossPercentageForHand(AbstractStartingHand hand, int numberOfSignificantDigits) {
        checkArgumentsForResultRetrieval(hand, numberOfSignificantDigits); 
        
        /* The number of losses is the amount performed simulations
         * that didn't result in a win or tie.
         */
        int numberOfLosses = performedSimulations;
        int[] resultForThisHand = resultForHand.get(hand);
        for (int i = 0; i < resultForThisHand.length; i++) {
            numberOfLosses -= resultForThisHand[i];
        }        
        BigDecimal lossPercentage = new BigDecimal(numberOfLosses);
        lossPercentage = lossPercentage.divide(new BigDecimal(performedSimulations), 
                new MathContext(numberOfSignificantDigits));
        return lossPercentage.doubleValue();
    }
    
    /**
     * Retrieves the expected value for a hand.
     * 
     * The expected value is given as a number between 0 and 1, not
     * as a percentage.
     * 
     * @param hand Starting hand
     * @param numberOfSignificantDigits Accuracy of the returned double
     * @return The expected value
     */
    public double getExpectedValueForHand(AbstractStartingHand hand, int numberOfSignificantDigits) {
        checkArgumentsForResultRetrieval(hand, numberOfSignificantDigits); 
        BigDecimal expectedValue = new BigDecimal(0);
        int[] resultForThisHand = resultForHand.get(hand);
        
        for (int i = 0; i < resultForThisHand.length; i++) {
            double numerator = resultForThisHand[i];
            BigDecimal toAdd = new BigDecimal(numerator);
            toAdd = toAdd.divide(new BigDecimal(i+1));
            expectedValue.add(toAdd);
        }    
        
        return expectedValue.round(new MathContext(numberOfSignificantDigits)).doubleValue();
    }

    /**
     * Checks that the arguments are correct when getting win, tie or loss percentage
     * or expected value for a hand.
     * 
     * @see getWinPercentageForHand
     * @see getTiePercentageForHand
     * @see getLossPercentageForHand
     * 
     * @param hand starting hand
     * @param numberOfSignificantDigits accuracy of result
     */
    private void checkArgumentsForResultRetrieval(AbstractStartingHand hand, int numberOfSignificantDigits) {
        if (hand == null) {
            throw new IllegalArgumentException("Hand can't be null");
        }
        if (!startingHands.contains(hand)) {
            throw new IllegalArgumentException("The hand isn't contained in the set of starting hands");
        }
        if (numberOfSignificantDigits <= 0) {
            throw new IllegalArgumentException("Number of significant digits must be positive");
        }
        if (performedSimulations < totalNumberOfSimulationsToPerform) {
            throw new IllegalStateException("Simulation isn't done yet");
        }        
    }
}
