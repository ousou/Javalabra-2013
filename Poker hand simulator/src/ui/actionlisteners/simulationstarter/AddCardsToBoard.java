package ui.actionlisteners.simulationstarter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import ui.SimulationStarter;

/**
 * Handles adding cards to the board.
 * 
 * @author Sebastian Björkqvist
 */
public class AddCardsToBoard extends AddCardsToCollection {

    public AddCardsToBoard(SimulationStarter simulationStarter, int xStart, int yStart) {
        super(simulationStarter, xStart, yStart, simulationStarter.getBoard(), simulationStarter.getCardLabelsInBoard());
    }

    @Override
    protected void createErrorMessageForTooManyCardsDialog(JPanel panel) {
        panel.add(new JLabel("Couldn't add cards to board."));        
        panel.add(new JLabel("The board can take a maximum of " + 
                collection.getMaximumAmountOfCardsInCollection() +" cards."));        
    }
    
}
