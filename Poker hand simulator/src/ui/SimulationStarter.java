package ui;

import card.Card;
import card.CardDeckStandard;
import card.ICardDeck;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import poker.FiveCardBoard;
import poker.enums.PokerGameType;
import poker.startinghands.AbstractStartingHand;
import ui.actionlisteners.CloseWindow;
import ui.actionlisteners.ProgramShutdown;
import ui.actionlisteners.simulationstarter.CardSelectionListener;
import ui.actionlisteners.simulationstarter.UnselectAllCardsListener;
import ui.guitools.CardDrawer;
import ui.guitools.WindowCreator;

/**
 * Opens window where the user can input the simulation parameters.
 *
 * @author Sebastian Björkqvist
 */
public class SimulationStarter implements Runnable {

    private GUI gui;
    private Container container;
    private JDialog dialog;
    private CardDrawer cardDrawer;
    private List<Component> drawnCards;
    private PokerGameType gameType;
    private int numberOfStartingHands;
    private int numberOfSimulations;
    private List<AbstractStartingHand> startingHands;
    private FiveCardBoard board;
    private List<Card> selectedCards;
    private List<Component> selectedCardLabels;

    public SimulationStarter(GUI gui, PokerGameType gameType,
            int numberOfStartingHands, int numberOfSimulations) {
        this.gui = gui;
        this.gameType = gameType;
        this.numberOfStartingHands = numberOfStartingHands;
        this.numberOfSimulations = numberOfSimulations;
        drawnCards = new ArrayList<Component>();
        startingHands = new ArrayList<AbstractStartingHand>(numberOfStartingHands);
        selectedCards = new ArrayList<Card>();
        selectedCardLabels = new ArrayList<Component>();
        if (gameType.isCommunityCardGame()) {
            board = new FiveCardBoard();
        }
    }

    @Override
    public void run() {
        openWindow();
    }

    private void openWindow() {
        WindowCreator creator = new WindowCreator(gui.getFrame());
        dialog = creator.createNewJDialog("Simulation starter", 700, 800);

        container = dialog.getLayeredPane();
//        container.setLayout(null);

        try {
            drawAllCards();
        } catch (IOException ex) {
            Logger.getLogger(SimulationStarter.class.getName()).log(Level.SEVERE, null, ex);
            createPicturesNotFoundErrorWindow();
            return;
        }
        createAvailableCardsLabel();

        createMainButtons();

        createGraphicsForStartingHands();

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
            cardLabel.addMouseListener(new CardSelectionListener(this, c, 
                    xPlace, yPlace, cardLabel));
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
            handLabel.setBounds(30 + xDistance * (i % handsOnRow) + insets.left,
                    320 + yDistance * (i / handsOnRow) + insets.top, size.width, size.height);
            container.add(handLabel);

            JButton addCards = new JButton("Add");
            size = addCards.getPreferredSize();
            addCards.setBounds(17 + xDistance * (i % handsOnRow) + insets.left, 
                    420 + yDistance * (i / handsOnRow), size.width, size.height);
            JButton clear = new JButton("Clear");
            size = clear.getPreferredSize();
            clear.setBounds(77 + xDistance * (i % handsOnRow) + insets.left, 420 + yDistance * (i / handsOnRow), size.width, size.height);

            container.add(addCards);
            container.add(clear);
        }
    }

    private void createAvailableCardsLabel() {
        JLabel cardsLabel = new JLabel("Available cards");
        Dimension size = cardsLabel.getPreferredSize();
        Insets insets = container.getInsets();
        cardsLabel.setBounds(30 + insets.left, 10 + insets.top,
                size.width, size.height);
        container.add(cardsLabel);
    }

    private void createMainButtons() {
        Insets insets = container.getInsets();
        JButton clearSelection = new JButton("Unselect all");
        Dimension size = clearSelection.getPreferredSize();
        clearSelection.setBounds(265 + insets.left, 235 + insets.top, size.width, size.height);
        clearSelection.addActionListener(new UnselectAllCardsListener(container, selectedCards, selectedCardLabels));
        
        container.add(clearSelection);

        JButton startSimulation = new JButton("Start simulation");
        size = startSimulation.getPreferredSize();
        startSimulation.setBounds(450 + insets.left, 720 + insets.top, size.width, size.height);

        container.add(startSimulation);

        JButton abort = new JButton("Abort");
        size = abort.getPreferredSize();
        abort.setBounds(600 + insets.left, 720 + insets.top, size.width, size.height);
        abort.addActionListener(new CloseWindow(dialog));

        container.add(abort);
    }

    private void createPicturesNotFoundErrorWindow() {
        WindowCreator creator = new WindowCreator(dialog);
        JDialog errorWindow = creator.createNewJDialog("Error", 300, 200);

        JPanel mainPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        mainPanel.setBorder(padding);
        mainPanel.setLayout(new GridLayout(6, 1));
        JLabel message1 = new JLabel("Card pictures could not be found.");
        JLabel message2 = new JLabel("The program will shut down.");
        JLabel message3 = new JLabel("If the problem persists, ");
        JLabel message4 = new JLabel("please use the text user interface.");
        mainPanel.add(message1);
        mainPanel.add(message2);
        mainPanel.add(new JLabel(""));
        mainPanel.add(message3);
        mainPanel.add(message4);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(new JLabel(""));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ProgramShutdown(gui));

        buttonPanel.add(okButton);
        buttonPanel.add(new JLabel(""));
        mainPanel.add(buttonPanel);

        errorWindow.setContentPane(mainPanel);
    }

    public GUI getGui() {
        return gui;
    }

    public Container getContainer() {
        return container;
    }

    public JDialog getDialog() {
        return dialog;
    }

    public CardDrawer getCardDrawer() {
        return cardDrawer;
    }

    public List<Component> getDrawnCards() {
        return drawnCards;
    }

    public List<Card> getSelectedCards() {
        return selectedCards;
    }

    public List<Component> getSelectedCardLabels() {
        return selectedCardLabels;
    }
    
//    /**
//     * Handles card selecting when a card is clicked.
//     */
//    private class CardSelectionListener implements MouseListener {
//
//        private Container container;
//        private List<Component> drawnCards;
//        private List<Card> selectedCards;
//        private Card card;
//        private int xPlace;
//        private int yPlace;
//        private JLabel cardLabel;
//        private List<Component> selectedCardLabels;        
//
//        public CardSelectionListener(Container container, List<Component> drawnCards, 
//                List<Card> selectedCards, Card card, int xPlace, int yPlace, 
//                JLabel cardLabel, List<Component> selectedCardLabels) {
//            this.container = container;
//            this.drawnCards = drawnCards;
//            this.selectedCards = selectedCards;
//            this.card = card;
//            this.xPlace = xPlace;
//            this.yPlace = yPlace;
//            this.cardLabel = cardLabel;
//            this.selectedCardLabels = selectedCardLabels;
//        }
//        
//        public void addGreyCard() {
//            selectedCards.add(card);
//
//            try {
//                JLabel greyCardLabel = cardDrawer.draw(card, xPlace, yPlace, 1, true);
//                drawnCards.add(greyCardLabel);
//                selectedCardLabels.add(greyCardLabel);
//                greyCardLabel.addMouseListener(
//                        new CardDeselectionListener(container, drawnCards, selectedCards, 
//                        card, xPlace, yPlace, greyCardLabel, cardLabel, selectedCardLabels));
//            } catch (IOException ex) {
//                Logger.getLogger(SimulationStarter.class.getName()).log(Level.SEVERE, null, ex);
//                createPicturesNotFoundErrorWindow();
//            }            
//        }
//
//        @Override
//        public void mouseClicked(MouseEvent e) {
//            addGreyCard();
//        }
//
//        @Override
//        public void mousePressed(MouseEvent e) {
//        }
//
//        @Override
//        public void mouseReleased(MouseEvent e) {
//        }
//
//        @Override
//        public void mouseEntered(MouseEvent e) {
//        }
//
//        @Override
//        public void mouseExited(MouseEvent e) {
//        }
//    }
//
//    /**
//     * Handles card deselecting when a card is clicked.
//     */
//    private class CardDeselectionListener implements MouseListener {
//
//        private Container container;
//        private List<Component> drawnCards;
//        private List<Card> selectedCards;
//        private Card card;
//        private int xPlace;
//        private int yPlace;
//        private JLabel greyCardLabel;
//        private JLabel cardLabel;
//        private List<Component> selectedCardLabels;                
//
//        public CardDeselectionListener(Container container, List<Component> drawnCards, List<Card> selectedCards, Card card, int xPlace, int yPlace, JLabel greyCardLabel, JLabel cardLabel, List<Component> selectedCardLabels) {
//            this.container = container;
//            this.drawnCards = drawnCards;
//            this.selectedCards = selectedCards;
//            this.card = card;
//            this.xPlace = xPlace;
//            this.yPlace = yPlace;
//            this.greyCardLabel = greyCardLabel;
//            this.cardLabel = cardLabel;
//            this.selectedCardLabels = selectedCardLabels;
//        }
//        
//        public void removeGreyCard() {
//            selectedCards.remove(card);
//            drawnCards.remove(greyCardLabel);
//            selectedCardLabels.remove(greyCardLabel);
//            container.remove(greyCardLabel);
//            
//            container.repaint();
//        }
//        
//        @Override
//        public void mouseClicked(MouseEvent e) {
//            removeGreyCard();
//        }
//
//        @Override
//        public void mousePressed(MouseEvent e) {
//        }
//
//        @Override
//        public void mouseReleased(MouseEvent e) {
//        }
//
//        @Override
//        public void mouseEntered(MouseEvent e) {
//        }
//
//        @Override
//        public void mouseExited(MouseEvent e) {
//        }
//    }
//    
//    private class UnselectAllCardsListener implements ActionListener {
//
//        private Container container;
//        private List<Card> selectedCards;   
//        private List<Component> selectedCardLabels;     
//
//        public UnselectAllCardsListener(Container container, List<Card> selectedCards, List<Component> selectedCardLabels) {
//            this.container = container;
//            this.selectedCards = selectedCards;
//            this.selectedCardLabels = selectedCardLabels;
//        }
//        
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            for (Component c : selectedCardLabels) {
//                container.remove(c);
//            }
//            
//            container.repaint();
//            selectedCards.clear();
//        }
//        
//    }
}
