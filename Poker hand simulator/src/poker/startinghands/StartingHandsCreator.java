package poker.startinghands;

import poker.enums.PokerGameType;

/**
 * Creates starting hands by game type.
 *
 * @author Sebastian Björkqvist
 */
public class StartingHandsCreator {

    /**
     * Creates an empty starting hand of the type given.
     *
     * @param gameType
     * @return
     */
    public static AbstractStartingHand createStartingHand(PokerGameType gameType) {
        switch (gameType) {
            case TEXAS:
                return new TexasHoldemStartingHand();
            case OMAHA:
                return new OmahaHoldemStartingHand();
            case SEVEN_STUD:
                return new SevenCardStudStartingHand();
            case FIVE_DRAW:
                return new FiveCardDrawStartingHand();
            default:
                throw new RuntimeException("Unsupported hand type!");
        }
    }
}
