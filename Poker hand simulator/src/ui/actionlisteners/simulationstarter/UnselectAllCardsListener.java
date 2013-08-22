package ui.actionlisteners.simulationstarter;

import card.Card;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author Sebastian Björkqvist
 */
public class UnselectAllCardsListener implements ActionListener {

    private Container container;
    private List<Card> selectedCards;
    private List<Component> selectedCardLabels;

    public UnselectAllCardsListener(Container container, List<Card> selectedCards, List<Component> selectedCardLabels) {
        this.container = container;
        this.selectedCards = selectedCards;
        this.selectedCardLabels = selectedCardLabels;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Component c : selectedCardLabels) {
            container.remove(c);
        }

        container.repaint();
        selectedCards.clear();
    }

}
