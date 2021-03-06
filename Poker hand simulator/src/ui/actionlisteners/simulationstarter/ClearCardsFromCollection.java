package ui.actionlisteners.simulationstarter;

import card.Card;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import poker.AbstractCardCollection;
import ui.SimulationStarter;

/**
 * Clears cards from a collection.
 * 
 * This class is extended by ClearCardsFromBoard and ClearCardsFromHand.
 * 
 * @author Sebastian Björkqvist
 */
public abstract class ClearCardsFromCollection implements ActionListener {

    protected SimulationStarter simulationStarter;
    protected AbstractCardCollection collection;
    protected List<Component> cardLabels;    

    /**
     * Creates a new ClearCardsFromCollection.
     * 
     * @param simulationStarter The simulation starter-class.
     * @param collection The collection to clear cards from.
     * @param cardLabels The list of labels of the cleared cards.
     */
    public ClearCardsFromCollection(SimulationStarter simulationStarter, AbstractCardCollection collection, List<Component> cardLabels) {
        this.simulationStarter = simulationStarter;
        this.collection = collection;
        this.cardLabels = cardLabels;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Container container = simulationStarter.getContainer();
        
        List<Card> cards = collection.getCards();
        collection.removeAllCards();
        
        for (Component c : cardLabels) {
            container.remove(c);
        }
        container.repaint();
        drawCardsToAvailableCardsSection(cards);        
    }

    private void drawCardsToAvailableCardsSection(List<Card> cards) {
        for (Card c: cards) {
            try {
                simulationStarter.drawCard(c);
            } catch (IOException ex) {
                PicturesNotFoundErrorWindow errorWindow = new PicturesNotFoundErrorWindow
                        (simulationStarter.getDialog(), simulationStarter.getGui());
                errorWindow.create();
            }
        }        
    }

}
