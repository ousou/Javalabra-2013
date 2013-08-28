package ui.actionlisteners.simulationstarter;

import card.Card;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import ui.SimulationStarter;

/**
 * Listener for selected cards in the Available cards-section in Simulation starter.
 * 
 * Handles deselecting cards, i.e. removing the grey card
 * from the screen and drawing the white card.
 * 
 * @author Sebastian Björkqvist
 */
public class CardDeselectionListener implements MouseListener {

    private Container container;
    private List<Card> selectedCards;
    private Card card;
    private JLabel greyCardLabel;
    private JLabel cardLabel;
    private List<Component> selectedCardLabels;
    private SimulationStarter simulationStarter;    

    /**
     * Creates a new CardDeselectionListener.
     * 
     * @param simulationStarter The simulation starter
     * @param card The card to which the listener is added
     * @param greyCardLabel The label for the grey card.
     * @param cardLabel The label for the white card.
     */
    public CardDeselectionListener(SimulationStarter simulationStarter, Card card, JLabel greyCardLabel, JLabel cardLabel) {
        this.simulationStarter = simulationStarter;
        this.container = simulationStarter.getContainer();
        this.selectedCards = simulationStarter.getSelectedCards();
        this.card = card;
        this.greyCardLabel = greyCardLabel;
        this.cardLabel = cardLabel;
        this.selectedCardLabels = simulationStarter.getSelectedCardLabels();
    }

    /**
     * Removes the grey card from the screen and adds the white card.
     */
    public void removeGreyCard() {
        selectedCards.remove(card);
        selectedCardLabels.remove(greyCardLabel);
        container.remove(greyCardLabel);
        container.add(cardLabel);
        container.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        removeGreyCard();
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
