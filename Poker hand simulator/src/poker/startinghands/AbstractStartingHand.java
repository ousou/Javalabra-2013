package poker.startinghands;

import poker.AbstractCardCollection;
import poker.enums.PokerGameType;

/**
 * Class representing a starting hand in a poker game.
 * 
 * @author Sebastian Björkqvist
 */
public abstract class AbstractStartingHand extends AbstractCardCollection {
    
    private final int minimumAmountOfCardsUsed;
    private final int maximumAmountOfCardsUsed;
    private final PokerGameType gameType;

    /**
     * Creates a new AbstractStartingHand
     * 
     * @param maximumAmountOfCardsInHand
     * @param minimumAmountOfCardsUsed
     * @param maximumAmountOfCardsUsed
     * @param gameType the gameType of the hand.
     * @throws IllegalArgumentException if minimumAmountOfCardsUsed is negative or
     * maximumAmountOfCardsUsed is non-positive.
     * @throws IllegalArgumentException if gameType is null.
     */
    public AbstractStartingHand(int maximumAmountOfCardsInHand, int minimumAmountOfCardsUsed, int maximumAmountOfCardsUsed, PokerGameType gameType) {
        super(maximumAmountOfCardsInHand);
        if (minimumAmountOfCardsUsed < 0) {
            throw new IllegalArgumentException("Minimum amount of cards used can't be negative");
        }
        if (maximumAmountOfCardsUsed <= 0) {
            throw new IllegalArgumentException("Maximum amount of cards used must be positive");
        }
        if (gameType == null) {
            throw new IllegalArgumentException("Gametype can't be null");
        }
        this.minimumAmountOfCardsUsed = minimumAmountOfCardsUsed;
        this.maximumAmountOfCardsUsed = maximumAmountOfCardsUsed;
        this.gameType = gameType;
    }        
    
    /**
     * Minimum amount of cards that have to be used to create a
     * five card poker hand using this starting hand and community cards.
     * 
     * @return The minimum number of cards to be used
     */
    public int getMinimumAmountOfCardsUsed() {
        return minimumAmountOfCardsUsed;
    }
    
    /**
     * Maximum amount of cards that can be used to create a
     * five card poker hand using this starting hand and community cards.
     * 
     * @return The maximum number of cards to be used
     */    
    public int getMaximumAmountOfCardsUsed() {
        return maximumAmountOfCardsUsed;        
    }

    /**
     * Retrieves the gameType of the hand.
     * 
     * @return 
     */
    public PokerGameType getGameType() {
        return gameType;
    }    
    
}
