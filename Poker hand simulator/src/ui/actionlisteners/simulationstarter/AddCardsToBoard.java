package ui.actionlisteners.simulationstarter;

import java.awt.event.ActionEvent;
import ui.SimulationStarter;

/**
 *
 * @author Sebastian Björkqvist
 */
public class AddCardsToBoard extends AddCardsToCollection {

    public AddCardsToBoard(SimulationStarter simulationStarter, int xStart, int yStart) {
        super(simulationStarter, xStart, yStart, simulationStarter.getBoard(), simulationStarter.getCardLabelsInBoard());
    }
    
}
