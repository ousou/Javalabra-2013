package ui.actionlisteners;

import card.Card;
import card.CardDeckStandard;
import card.ICardDeck;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import poker.enums.PokerGameType;
import ui.GUI;
import ui.guitools.CardDrawer;
import ui.guitools.WindowCreator;

/**
 *
 * @author Sebastian Björkqvist
 */
public class SimulationStarter {

    private GUI gui;
    private Container container;
    private JDialog dialog;
    private CardDrawer cardDrawer;
    private List<Component> drawnCards;
    private PokerGameType gameType;
    private int numberOfStartingHands;
    private int numberOfSimulations;

    public SimulationStarter(GUI gui, PokerGameType gameType,
            int numberOfStartingHands, int numberOfSimulations) {
        this.gui = gui;
        this.gameType = gameType;
        this.numberOfStartingHands = numberOfStartingHands;
        this.numberOfSimulations = numberOfSimulations;
        drawnCards = new ArrayList<Component>();
        openWindow();
    }

    private void openWindow() {
        WindowCreator creator = new WindowCreator(gui.getFrame());
        dialog = creator.createNewJDialog("Simulation starter", 600, 600);
        container = dialog.getLayeredPane();
        container.setLayout(null);
        try {
            drawAllCards();
        } catch (IOException ex) {
            Logger.getLogger(SimulationStarter.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Couldn't find pictures!");
        }
        JLabel cardsLabel = new JLabel("Available cards");
        Dimension size = cardsLabel.getPreferredSize();
        Insets insets = container.getInsets();
        cardsLabel.setBounds(30 + insets.left, 10 + insets.top,
                size.width, size.height);
        container.add(cardsLabel);        
    }

    private void drawAllCards() throws IOException {
        ICardDeck deck = new CardDeckStandard(false);
        cardDrawer = new CardDrawer(container, gui.getPictureDirectory(), gui.getPictureType());
        int index = 0;
        while (!deck.isEmpty()) {
            Card c = deck.getCard();
            int xPlace = (index % 12) * 30 + 30;
            int yPlace = (index / 12) * 50 + 30;
            JLabel cardLabel = cardDrawer.draw(c, xPlace, yPlace, 1, false);
            drawnCards.add(cardLabel);
            index++;
        }
    }
}
