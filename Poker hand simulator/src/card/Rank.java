package card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /**
     * The value of the rank.
     */
    private int value;        
    private static Map<String, Rank> stringToRankMap;

    private Rank(String shortName, int value) {
        this.shortName = shortName;
        this.value = value;
    }
    
    private static Map<String, Rank> createStringToRankMap() {
        stringToRankMap = new HashMap<String, Rank>();
        for (int i = 0; i < values().length; i++) {
            stringToRankMap.put(values()[i].shortName, values()[i]);
        }
        
        return stringToRankMap;
    }   
    /**
     * Returns String to Rank-map.
     * 
     * The returned map maps the one-character string representation
     * of a rank to the corresponding Rank-object.
     * 
     * @return stringToRankMap
     */
    public static Map<String, Rank> getStringToRankMap() {
        if (stringToRankMap == null) {
            stringToRankMap = createStringToRankMap();
        }
        return stringToRankMap;
    }
    
    /**
     * Gets list of all ranks.
     * 
     * @return List of all ranks.
     */
    public static List<Rank> getAllRanks() {
        return new ArrayList<Rank>(Arrays.asList(values()));
    }

    /**
     * Gets the one-character representation of the rank.
     * 
     * @return String with length 1.
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Gets the value of the rank.
     * 
     * @return An integer representing the value of the rank.
     */
    public int getValue() {
        return value;
    }
}
