package card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the rank of a card.
 * 
 * The short name is used for output in the text-based user interface, and in
 * image file names.
 * 
 * The integer value is used when sorting cards in a hand 
 * to facilitate easier hand value calculations.
 * 
 * @author Sebastian Björkqvist
 */
public enum Rank {

    ACE("A", 1),
    DEUCE("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("T", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13);
    
    private String shortName;
    private int value;        

    private Rank(String shortName, int value) {
        this.shortName = shortName;
        this.value = value;
    }

    /**
     * Gets list of all ranks.
     * 
     * @return List of all ranks.
     */
    public static List<Rank> getAllRanks() {
        return new ArrayList<Rank>(Arrays.asList(values()));
    }

    public String getShortName() {
        return shortName;
    }

    public int getValue() {
        return value;
    }
}
