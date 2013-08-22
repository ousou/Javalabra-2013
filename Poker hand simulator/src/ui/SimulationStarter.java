package ui;

import card.Card;
import card.CardDeckStandard;
import card.ICardDeck;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import poker.FiveCardBoard;
import poker.enums.PokerGameType;
import poker.startinghands.AbstractStartingHand;
import poker.startinghands.StartingHandsCreator;
import poker.startinghands.TexasHoldemStartingHand;
import ui.actionlisteners.CloseWindow;
import ui.actionlisteners.simulationstarter.AddCardsToHand;
import ui.actionlisteners.simulationstarter.CardSelectionListener;
import ui.actionlisteners.simulationstarter.ClearCardsFromHand;
import ui.actionlisteners.simulationstarter.PicturesNotFoundErrorWindow;
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
    private Map<Card, Component> drawnCards;
    private PokerGameType gameType;
    private int numberOfStartingHands;
    private int numberOfSimulations;
    private AbstractStartingHand[] startingHands;   
    private List<Component>[] cardLabelsInStartingHands;
    // Keeps track of where to draw a card when it's removed from a hand
    private Map<Card, Integer> indexForCard;
    private FiveCardBoard board;
    private List<Card> selectedCards;
    private List<Component> selectedCardLabels;

    public SimulationStarter(GUI gui, PokerGameType gameType,
            int numberOfStartingHands, int numberOfSimulations) {
        this.gui = gui;
        this.gameType = gameType;
        this.numberOfStartingHands = numberOfStartingHands;
        this.numberOfSimulations = numberOfSimulations;
        drawnCards = new HashMap<Card, Component>();
        initStartingHandArrays();
        selectedCards = new ArrayList<Card>();
        selectedCardLabels = new ArrayList<Component>();
        indexForCard = new HashMap<Card, Integer>();
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
            PicturesNotFoundErrorWindow errorWindow = new PicturesNotFoundErrorWindow(dialog, gui);
            errorWindow.create();
            return;
        }
        createAvailableCardsLabel();

        createMainButtons();

        createGraphicsForStartingHands();

    }

    private void drawAllCards() throws IOException {
        ICardDeck deck = new CardDeckStandard(false);
        cardDrawer = new CardDrawer(container, gui.getPictureDirectory(), 
                gui.getPictureType());
        int index = 0;
        while (!deck.isEmpty()) {
            Card c = deck.getCard();
            indexForCard.put(c, index);
            drawCard(c);
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
            addCards.addActionListener(new AddCardsToHand(this, i, 
                    30 + xDistance * (i % handsOnRow), 350 + yDistance * (i / handsOnRow)));
            
            JButton clear = new JButton("Clear");
            size = clear.getPreferredSize();
            clear.setBounds(77 + xDistance * (i % handsOnRow) + insets.left, 
                    420 + yDistance * (i / handsOnRow), size.width, size.height);
            clear.addActionListener(new ClearCardsFromHand(this, i));

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

    /**
     * Draws a card to its original place among
     * selected cards.
     * 
     * @param c Card
     * @throws IOException 
     */
    
    public void drawCard(Card c) throws IOException {
        int index = indexForCard.get(c);
        int xPlace = (index % 12) * 30 + 30;
        int yPlace = (index / 12) * 50 + 30;
        JLabel cardLabel = cardDrawer.draw(c, xPlace, yPlace, 1, false);
        drawnCards.put(c, cardLabel);
        cardLabel.addMouseListener(new CardSelectionListener(this, c, 
                xPlace, yPlace, cardLabel));
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

    public Map<Card, Component> getDrawnCards() {
        return drawnCards;
    }

    public List<Card> getSelectedCards() {
        return selectedCards;
    }

    public List<Component> getSelectedCardLabels() {
        return selectedCardLabels;
    }

    public AbstractStartingHand[] getStartingHands() {
        return startingHands;
    }

    public List<Component>[] getCardLabelsInStartingHands() {
        return cardLabelsInStartingHands;
    }

    private void initStartingHandArrays() {
        startingHands = new AbstractStartingHand[numberOfStartingHands];
        for (int i = 0; i < startingHands.length; i++) {
            startingHands[i] = StartingHandsCreator.createStartingHand(gameType);
        }
        cardLabelsInStartingHands = new List[numberOfStartingHands];
        for (int i = 0; i < cardLabelsInStartingHands.length; i++) {
            cardLabelsInStartingHands[i] = new ArrayList<Component>();
        }
    }
    
}
