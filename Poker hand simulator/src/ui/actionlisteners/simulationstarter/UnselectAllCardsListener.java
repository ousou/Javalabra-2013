package ui.actionlisteners.simulationstarter;

import card.Card;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import ui.SimulationStarter;

/**
 *
 * @author Sebastian Björkqvist
 */
public class UnselectAllCardsListener implements ActionListener {

    private Container container;
    private List<Card> selectedCards;
    private List<Component> selectedCardLabels;
    private SimulationStarter simulationStarter;

    public UnselectAllCardsListener(Container container, List<Card> selectedCards,
            List<Component> selectedCardLabels, SimulationStarter simulationStarter) {
        this.container = container;
        this.selectedCards = selectedCards;
        this.selectedCardLabels = selectedCardLabels;
        this.simulationStarter = simulationStarter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Component c : selectedCardLabels) {
            container.remove(c);
        }
        for (Card c : selectedCards) {
            try {
                simulationStarter.drawCard(c);
            } catch (IOException ex) {
                PicturesNotFoundErrorWindow errorWindow = new PicturesNotFoundErrorWindow(simulationStarter.getDialog(), simulationStarter.getGui());
                errorWindow.create();
            }
        }

        container.repaint();
        selectedCards.clear();
    }
}
