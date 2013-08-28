package ui.actionlisteners.simulationstarter;

import ui.SimulationStarter;

/**
 * Clears cards from the board.
 * 
 * @author Sebastian Björkqvist
 */
public class ClearCardsFromBoard extends ClearCardsFromCollection {

    /**
     * Creates a new ClearCardsFromBoard.
     * 
     * @param simulationStarter The simulation starter.
     */
    public ClearCardsFromBoard(SimulationStarter simulationStarter) {
        super(simulationStarter, simulationStarter.getBoard(), 
                simulationStarter.getCardLabelsInBoard());
    }

    
}
