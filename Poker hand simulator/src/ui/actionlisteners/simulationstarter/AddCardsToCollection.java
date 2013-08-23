package ui.actionlisteners.simulationstarter;

import card.Card;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import poker.AbstractCardCollection;
import ui.SimulationStarter;
import ui.guitools.CardDrawer;

/**
 * Adds cards to a collection.
 *
 * This class is extended by AddCardsToBoard and AddCardsToHand.
 *
 * @author Sebastian Björkqvist
 */
public abstract class AddCardsToCollection implements ActionListener {

    protected SimulationStarter simulationStarter;
    protected int xStart;
    protected int yStart;
    protected AbstractCardCollection collection;
    protected List<Component> cardLabels;

    public AddCardsToCollection(SimulationStarter simulationStarter, int xStart, int yStart, AbstractCardCollection collection, List<Component> cardLabels) {
        this.simulationStarter = simulationStarter;
        this.xStart = xStart;
        this.yStart = yStart;
        this.collection = collection;
        this.cardLabels = cardLabels;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Container container = simulationStarter.getContainer();
        List<Card> cardsToAdd = simulationStarter.getSelectedCards();

        int cardsBeforeAddition = collection.getNumberOfCards();

        if (cardsToAdd.size() <= collection.getMaximumAmountOfCardsInCollection()
                - cardsBeforeAddition) {
            try {
                collection.addCards(cardsToAdd);
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
                PicturesNotFoundErrorWindow errorWindow =
                        new PicturesNotFoundErrorWindow(simulationStarter.getDialog(),
                        simulationStarter.getGui());
                errorWindow.create();
            }

            // Removes the card drawn under Available cards.
            container.remove(drawnCards.get(c));
            howMany++;
        }
        List<Component> selectedCardLabels = simulationStarter.getSelectedCardLabels();

        // Removes the grey cards under Available cards.
        for (Component c : selectedCardLabels) {
            container.remove(c);
        }


        container.repaint();

        selectedCardLabels.clear();
        cardsToAdd.clear();
    }
}
