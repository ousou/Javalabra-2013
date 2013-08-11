package card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the suit of a card.
 *
 * The short name is used for output in the text-based user interface, and in
 * image file names.
 *
 * @author Sebastian Björkqvist
 */
public enum Suit {

    SPADE("s"),
    HEART("h"),
    DIAMOND("d"),
    CLUB("c");
    private String shortName;
    private static Map<String, Suit> stringToSuitMap;    

    private Suit(String shortName) {
        this.shortName = shortName;
    }

    private static Map<String, Suit> createStringToSuitMap() {
        stringToSuitMap = new HashMap<String, Suit>();
        for (int i = 0; i < values().length; i++) {
            stringToSuitMap.put(values()[i].shortName, values()[i]);
        }
        
        return stringToSuitMap;
    }   
    /**
     * Returns String to Suit-map.
     * 
     * The returned map maps the one-character string representation
     * of a rank to the corresponding Suit-object.
     * 
     * @return stringToSuitMap
     */
    public static Map<String, Suit> getStringToSuitMap() {
        if (stringToSuitMap == null) {
            stringToSuitMap = createStringToSuitMap();
        }
        return stringToSuitMap;
    }    
    
    /**
     * Gets a list of all suits.
     *
     * @return List of all suits.
     */
    public static List<Suit> getAllSuits() {
        return new ArrayList<Suit>(Arrays.asList(values()));
    }

    /**
     * Gets the one-character representation of the suit.
     * 
     * @return A String of length 1.
     */
    public String getShortName() {
        return shortName;
    }
}
