package ui.actionlisteners;

import card.Card;
import card.CardDeckStandard;
import card.ICardDeck;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import poker.enums.PokerGameType;
import ui.GUI;
import ui.guitools.CardDrawer;
import ui.guitools.WindowCreator;

/**
 * Opens window where the user can input the simulation parameters.
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
        dialog = creator.createNewJDialog("Simulation starter", 700, 800);
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
        
        JButton clearSelection = new JButton("Unselect all");
        size = clearSelection.getPreferredSize();        
        clearSelection.setBounds(265 + insets.left, 235 + insets.top, size.width, size.height);
        
        container.add(clearSelection);
        
        JButton startSimulation = new JButton("Start simulation");
        size = startSimulation.getPreferredSize();           
        startSimulation.setBounds(450 + insets.left, 720 + insets.top, size.width, size.height);
        
        container.add(startSimulation);
        
        JButton abort = new JButton("Abort");
        size = abort.getPreferredSize();           
        abort.setBounds(600 + insets.left, 720 + insets.top, size.width, size.height);
        
        container.add(abort);        
        
        createGraphicsForStartingHands();
        dialog.pack();
        dialog.setVisible(true);
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

    private void createGraphicsForStartingHands() {
        int xDistance = 150;
        int yDistance = 140;
        int handsOnRow = 4;
        switch (gameType) {
            case SEVEN_STUD:
                xDistance = 220;
                handsOnRow = 3;
                break;
            case FIVE_DRAW:
                xDistance = 160;
                break;               
        }

        Insets insets = container.getInsets();        
        for (int i = 0; i < numberOfStartingHands; i++) {
            JLabel handLabel = new JLabel("Hand " + (i + 1));
            Dimension size = handLabel.getPreferredSize();            
            handLabel.setBounds(30 + xDistance*(i % handsOnRow) + insets.left, 
                    320 + yDistance*(i/handsOnRow) + insets.top, size.width, size.height);            
            container.add(handLabel);
            
            JButton addCards = new JButton("Add");
            size = addCards.getPreferredSize();
            addCards.setBounds(17 + xDistance*(i % handsOnRow) + insets.left
                    , 420 + yDistance*(i/handsOnRow), size.width, size.height);
            JButton clear = new JButton("Clear");
            size = clear.getPreferredSize();
            clear.setBounds(77 + xDistance*(i % handsOnRow) + insets.left
                    , 420 + yDistance*(i/handsOnRow), size.width, size.height);
                    
            container.add(addCards);
            container.add(clear);
        }
        
        
        
    }
}
