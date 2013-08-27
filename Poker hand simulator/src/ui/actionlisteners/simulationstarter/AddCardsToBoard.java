package ui.actionlisteners.simulationstarter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import ui.SimulationStarter;
import ui.actionlisteners.CloseWindow;
import ui.actionlisteners.ProgramShutdown;
import ui.guitools.WindowCreator;

/**
 *
 * @author Sebastian Björkqvist
 */
public class AddCardsToBoard extends AddCardsToCollection {

    public AddCardsToBoard(SimulationStarter simulationStarter, int xStart, int yStart) {
        super(simulationStarter, xStart, yStart, simulationStarter.getBoard(), simulationStarter.getCardLabelsInBoard());
    }

    @Override
    protected void createErrorMessageForDialog(JPanel panel) {
        panel.add(new JLabel("Couldn't add cards to board."));        
        panel.add(new JLabel("The board can take a maximum of " + 
                collection.getMaximumAmountOfCardsInCollection() +" cards."));        
    }
    
}
