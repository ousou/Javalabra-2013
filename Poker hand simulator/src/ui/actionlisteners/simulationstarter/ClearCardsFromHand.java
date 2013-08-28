package ui.actionlisteners.simulationstarter;

import ui.SimulationStarter;

/**
 * Clears all cards from a starting hand.
 *
 * @author Sebastian Björkqvist
 */
public class ClearCardsFromHand extends ClearCardsFromCollection {

    private int handNumber;

    /**
     * Creates a new ClearCardsFromHand.
     * 
     * @param simulationStarter The simulation starter
     * @param handNumber Number of hand in the Starting hands-array of the
     * SimulationStarter-object.
     */
    public ClearCardsFromHand(SimulationStarter simulationStarter, int handNumber) {
        super(simulationStarter, simulationStarter.getStartingHands()[handNumber],
                simulationStarter.getCardLabelsInStartingHands()[handNumber]);
    }
}
