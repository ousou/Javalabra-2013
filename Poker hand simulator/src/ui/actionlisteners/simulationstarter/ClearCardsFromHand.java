package ui.actionlisteners.simulationstarter;

import card.Card;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import poker.startinghands.AbstractStartingHand;
import ui.SimulationStarter;

/**
 * Clears all cards from a starting hand.
 * 
 * @author Sebastian Björkqvist
 */
public class ClearCardsFromHand implements ActionListener {

    private SimulationStarter simulationStarter;
    private int handNumber;

    public ClearCardsFromHand(SimulationStarter simulationStarter, int handNumber) {
        this.simulationStarter = simulationStarter;
        this.handNumber = handNumber;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Container container = simulationStarter.getContainer();
        
        AbstractStartingHand hand = simulationStarter.getStartingHands()[handNumber];
        List<Card> cards = hand.getCards();
        hand.removeAllCards();
        
        List<Component> cardLabelsInHand = simulationStarter.getCardLabelsInStartingHands()[handNumber];
        for (Component c : cardLabelsInHand) {
            container.remove(c);
        }
        container.repaint();
        
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
