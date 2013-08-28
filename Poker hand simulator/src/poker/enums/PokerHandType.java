package poker.enums;

/**
 * Enum representing a type of poker hand.
 * 
 * Used when comparing poker hands.
 * 
 * @author Sebastian Björkqvist
 */
public enum PokerHandType {
    HIGH_CARD(0),
    PAIR(1),
    TWO_PAIR(2),
    THREE_OF_A_KIND(3),
    STRAIGHT(4),
    FLUSH(5),
    FULL_HOUSE(6),
    FOUR_OF_A_KIND(7),
    STRAIGHT_FLUSH(8);
    
    /**
     * The value of the hand type, higher means a better hand type.
     */
    private int value;

    private PokerHandType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }  
}
