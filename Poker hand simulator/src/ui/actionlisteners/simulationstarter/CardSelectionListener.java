package ui.actionlisteners.simulationstarter;

import card.Card;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import ui.SimulationStarter;
import ui.guitools.CardDrawer;

/**
 * Listener for unselected cards in the Available cards-section in SimulationStarter.
 * 
 * Handles selecting of cards, i.e. it removes the white card and draws the grey card.
 * 
 * @author Sebastian Björkqvist
 */
public class CardSelectionListener implements MouseListener {

    private Container container;
    private List<Card> selectedCards;
    private Card card;
    private int xPlace;
    private int yPlace;
    private JLabel cardLabel;
    private List<Component> selectedCardLabels;
    private CardDrawer cardDrawer;
    private SimulationStarter simulationStarter;

    /**
     * Creates a new CardSelectionListener.
     * 
     * @param simulationStarter The simulation starter.
     * @param card The card to which the listener is added.
     * @param xPlace The x-coordinate for the card label.
     * @param yPlace The y-coordinate for the card label.
     * @param cardLabel The card label corresponding to the card.
     */
    public CardSelectionListener(SimulationStarter simulationStarter, Card card, int xPlace, int yPlace, JLabel cardLabel) {
        this.simulationStarter = simulationStarter;        
        this.container = simulationStarter.getContainer();
        this.selectedCards = simulationStarter.getSelectedCards();
        this.card = card;
        this.xPlace = xPlace;
        this.yPlace = yPlace;
        this.cardLabel = cardLabel;
        this.selectedCardLabels = simulationStarter.getSelectedCardLabels();
        this.cardDrawer = simulationStarter.getCardDrawer();
    }

    /**
     * Draws the grey card to the screen and removes the white card.
     */
    
    public void addGreyCard() {
        selectedCards.add(card);
        Map<Card, Component> drawnCards = simulationStarter.getDrawnCards();

        container.remove(drawnCards.get(card));        
        try {
            JLabel greyCardLabel = cardDrawer.draw(card, xPlace, yPlace, 2, true);
            greyCardLabel.addMouseListener(
                    new CardDeselectionListener(simulationStarter, card, 
                    greyCardLabel, cardLabel));            
            selectedCardLabels.add(greyCardLabel);
        } catch (IOException ex) {
            PicturesNotFoundErrorWindow errorWindow = new PicturesNotFoundErrorWindow
                    (simulationStarter.getDialog(), simulationStarter.getGui());
            errorWindow.create();
        }
        container.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        addGreyCard();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
