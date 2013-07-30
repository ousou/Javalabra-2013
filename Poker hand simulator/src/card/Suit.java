package card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private Suit(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Gets a list of all suits.
     *
     * @return List of all suits.
     */
    public static List<Suit> getAllSuits() {
        return new ArrayList<Suit>(Arrays.asList(values()));
    }

    public String getShortName() {
        return shortName;
    }
}
