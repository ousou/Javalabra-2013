package poker.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enum representing type of poker game.
 * 
 * @author Sebastian Björkqvist
 */
public enum PokerGameType {
    TEXAS("Texas hold'em", true),
    OMAHA("Omaha hold'em", true),
    SEVEN_STUD("Seven card stud", false),
    FIVE_DRAW("Five card draw / stud", false);
    
    private String fullName;
    private boolean communityCardGame;

    private PokerGameType(String fullName, boolean communityCardGame) {
        this.fullName = fullName;
        this.communityCardGame = communityCardGame;
    }

    /**
     * Retrieves the full name of the game type.
     * 
     * @return String with the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Tells if the game is a community card game.
     * 
     * Community card games use a board which all players can share.
     * 
     * @return true if the game is a community card game, false otherwise.
     */
    public boolean isCommunityCardGame() {
        return communityCardGame;
    }    
    
    /**
     * Retrieves a list of all poker game types.
     * 
     * @return List containing all types.
     */
    public static List<PokerGameType> getAllGameTypes() {
        return new ArrayList<PokerGameType>(Arrays.asList(values()));
    }
    
    /**
     * Retrieves and array of all poker game types.
     * 
     * @return Array containing all types;
     */
    public static PokerGameType[] getAllGameTypesArray() {
        return values();
    }
            
}
