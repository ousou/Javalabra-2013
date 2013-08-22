package ui.actionlisteners.simulationstarter;

import card.Card;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import poker.startinghands.AbstractStartingHand;
import ui.SimulationStarter;
import ui.guitools.CardDrawer;

/**
 * Adds cards to a starting hand.
 *
 * @author Sebastian Björkqvist
 */
public class AddCardsToHand implements ActionListener {

    private SimulationStarter simulationStarter;
    private int handNumber;
    private int xStart;
    private int yStart;

    public AddCardsToHand(SimulationStarter simulationStarter, int handNumber, int xStart, int yStart) {
        this.simulationStarter = simulationStarter;
        this.handNumber = handNumber;
        this.xStart = xStart;
        this.yStart = yStart;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Container container = simulationStarter.getContainer();
        List<Card> cardsToAdd = simulationStarter.getSelectedCards();

        AbstractStartingHand hand = simulationStarter.getStartingHands()[handNumber];
        List<Component> cardLabels = simulationStarter.getCardLabelsInStartingHands()[handNumber];
        int cardsBeforeAddition = hand.getNumberOfCards();
        
        if (cardsToAdd.size() <= hand.getMaximumAmountOfCardsInCollection()
                - cardsBeforeAddition) {
            try {
                hand.addCards(cardsToAdd);
            } catch (IllegalArgumentException ex) {
                System.out.println("Cards couldn't be added to this hand!");
                return;
            }
        } else {
            System.out.println("Can't add that many!");
            return;
        }
        CardDrawer cardDrawer = simulationStarter.getCardDrawer();

        int howMany = cardsBeforeAddition;

        Map<Card, Component> drawnCards = simulationStarter.getDrawnCards();
        
        for (Card c : cardsToAdd) {
            try {
                JLabel cardLabel = cardDrawer.draw(c, xStart + 30 * howMany, yStart, 0, false);
                cardLabels.add(cardLabel);
            } catch (IOException ex) {
                PicturesNotFoundErrorWindow errorWindow = new PicturesNotFoundErrorWindow(simulationStarter.getDialog(), simulationStarter.getGui());
                errorWindow.create();
            }
            
            container.remove(drawnCards.get(c));
            howMany++;
        }
        List<Component> selectedCardLabels = simulationStarter.getSelectedCardLabels();
        
        for (Component c : selectedCardLabels) {
            container.remove(c);
        }

        
        container.repaint();        
        
        selectedCardLabels.clear();
        cardsToAdd.clear();
    }
}
