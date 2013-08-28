package ui.actionlisteners.simulationstarter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import ui.SimulationStarter;

/**
 * Adds cards to a starting hand.
 *
 * @author Sebastian Björkqvist
 */
public class AddCardsToHand extends AddCardsToCollection {

    /**
     * Creates a new AddCardsToHand.
     * 
     * @param simulationStarter The simulation starter
     * @param handNumber Number of hand in the Starting hands-array of the
     * SimulationStarter-object.
     * @param xStart The starting x-coordinate for the images
     * @param yStart The starting y-coordinate for the images
     */
    public AddCardsToHand(SimulationStarter simulationStarter, int handNumber, 
            int xStart, int yStart) {
        super(simulationStarter, xStart, yStart,
                simulationStarter.getStartingHands()[handNumber], 
                simulationStarter.getCardLabelsInStartingHands()[handNumber]);
    }

    @Override
    protected void createErrorMessageForTooManyCardsDialog(JPanel panel) {
        panel.add(new JLabel("Couldn't add cards to hand."));        
        panel.add(new JLabel("The hand can take a maximum of " + 
                collection.getMaximumAmountOfCardsInCollection() +" cards."));              
    }
}
