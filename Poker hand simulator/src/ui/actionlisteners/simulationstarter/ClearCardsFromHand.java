package ui.actionlisteners.simulationstarter;

import ui.SimulationStarter;

/**
 * Clears all cards from a starting hand.
 *
 * @author Sebastian Björkqvist
 */
public class ClearCardsFromHand extends ClearCardsFromCollection {

    private int handNumber;

    public ClearCardsFromHand(SimulationStarter simulationStarter, int handNumber) {
        super(simulationStarter, simulationStarter.getStartingHands()[handNumber],
                simulationStarter.getCardLabelsInStartingHands()[handNumber]);
    }
}
