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
 *
 * @author Sebastian Björkqvist
 */
public class CardDeselectionListener implements MouseListener {

    private Container container;
    private List<Component> drawnCards;
    private List<Card> selectedCards;
    private Card card;
    private int xPlace;
    private int yPlace;
    private JLabel greyCardLabel;
    private JLabel cardLabel;
    private List<Component> selectedCardLabels;
    private SimulationStarter simulationStarter;    

    public CardDeselectionListener(SimulationStarter simulationStarter, Card card, int xPlace, int yPlace, JLabel greyCardLabel, JLabel cardLabel) {
        this.simulationStarter = simulationStarter;
        this.container = simulationStarter.getContainer();
        this.drawnCards = simulationStarter.getDrawnCards();
        this.selectedCards = simulationStarter.getSelectedCards();
        this.card = card;
        this.xPlace = xPlace;
        this.yPlace = yPlace;
        this.greyCardLabel = greyCardLabel;
        this.cardLabel = cardLabel;
        this.selectedCardLabels = simulationStarter.getSelectedCardLabels();
    }

    public void removeGreyCard() {
        selectedCards.remove(card);
        drawnCards.remove(greyCardLabel);
        selectedCardLabels.remove(greyCardLabel);
        container.remove(greyCardLabel);

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
