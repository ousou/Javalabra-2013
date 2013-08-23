package ui.actionlisteners.simulationstarter;

import ui.SimulationStarter;

/**
 * Clears cards from the board.
 * 
 * @author Sebastian Björkqvist
 */
public class ClearCardsFromBoard extends ClearCardsFromCollection {

    public ClearCardsFromBoard(SimulationStarter simulationStarter) {
        super(simulationStarter, simulationStarter.getBoard(), 
                simulationStarter.getCardLabelsInBoard());
    }

    
}
