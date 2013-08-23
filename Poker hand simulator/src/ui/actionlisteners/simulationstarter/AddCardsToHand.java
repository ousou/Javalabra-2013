package ui.actionlisteners.simulationstarter;

import ui.SimulationStarter;

/**
 * Adds cards to a starting hand.
 *
 * @author Sebastian Björkqvist
 */
public class AddCardsToHand extends AddCardsToCollection {

    public AddCardsToHand(SimulationStarter simulationStarter, int handNumber, 
            int xStart, int yStart) {
        super(simulationStarter, xStart, yStart,
                simulationStarter.getStartingHands()[handNumber], 
                simulationStarter.getCardLabelsInStartingHands()[handNumber]);
    }
}
