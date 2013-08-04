package poker;

/**
 *
 * @author Sebastian Björkqvist
 */
public abstract class AbstractStartingHand extends AbstractCardCollection {
    
    private final int minimumAmountOfCardsUsed;
    private final int maximumAmountOfCardsUsed;

    public AbstractStartingHand(int maximumAmountOfCardsInHand, int minimumAmountOfCardsUsed, int maximumAmountOfCardsUsed) {
        super(maximumAmountOfCardsInHand);
        this.minimumAmountOfCardsUsed = minimumAmountOfCardsUsed;
        this.maximumAmountOfCardsUsed = maximumAmountOfCardsUsed;
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
}
