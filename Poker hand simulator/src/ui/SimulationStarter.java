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
import ui.actionlisteners.CloseWindow;
import ui.actionlisteners.simulationstarter.AddCardsToBoard;
import ui.actionlisteners.simulationstarter.AddCardsToHand;
import ui.actionlisteners.simulationstarter.CardSelectionListener;
import ui.actionlisteners.simulationstarter.ClearCardsFromBoard;
import ui.actionlisteners.simulationstarter.ClearCardsFromHand;
import ui.actionlisteners.simulationstarter.PicturesNotFoundErrorWindow;
import ui.actionlisteners.simulationstarter.StartSimulation;
import ui.actionlisteners.simulationstarter.UnselectAllCardsListener;
import ui.guitools.CardDrawer;
import ui.guitools.WindowCreator;

/**
 * Opens window where the user can input the simulation parameters.
 *
 * @author Sebastian Björkqvist
 */
public class SimulationStarter implements Runnable {

    private GUIMainWindow gui;
    private Container container;
    private JDialog dialog;
    private CardDrawer cardDrawer;
    /**
     * Keeps track of where the cards are drawn in the Available cards-section.
     */
    private Map<Card, Component> drawnCards;
    private PokerGameType gameType;
    private int numberOfStartingHands;
    private int numberOfSimulations;
    /**
     * Array containing all starting hands.
     */
    private AbstractStartingHand[] startingHands;
    /**
     * Array containing the labels of the cards added to starting hands.
     */
    private List<Component>[] cardLabelsInStartingHands;
    /**
     * Keeps track of where to draw a card when it's removed from a hand.
     */
    private Map<Card, Integer> indexForCard;
    private FiveCardBoard board;
    /**
     * List containing labels of the cards added to the board.
     */
    private List<Component> cardLabelsInBoard;
    /**
     * Contains cards currently selected in the Available cards-section.
     */
    private List<Card> selectedCards;
    /**
     * Contains labels of cards currently selected in the Available
     * cards-section.
     */
    private List<Component> selectedCardLabels;
    private boolean showMinorErrorDialogs;
    private boolean isRerun;

    public SimulationStarter(GUIMainWindow gui, PokerGameType gameType,
            int numberOfStartingHands, int numberOfSimulations) {
        this.gui = gui;
        this.gameType = gameType;
        this.numberOfStartingHands = numberOfStartingHands;
        this.numberOfSimulations = numberOfSimulations;
        this.drawnCards = new HashMap<Card, Component>();
        initStartingHandArrays();
        this.selectedCards = new ArrayList<Card>();
        this.selectedCardLabels = new ArrayList<Component>();
        this.indexForCard = new HashMap<Card, Integer>();
        if (gameType.isCommunityCardGame()) {
            this.board = new FiveCardBoard();
            this.cardLabelsInBoard = new ArrayList<Component>();
        }
        this.showMinorErrorDialogs = gui.getSettings().isShowMinorErrorDialogs();
    }

    public SimulationStarter(GUIMainWindow gui, PokerGameType gameType, int numberOfStartingHands,
            int numberOfSimulations, List<AbstractStartingHand> startingHands, FiveCardBoard board) {
        this.gui = gui;
        this.gameType = gameType;
        this.numberOfStartingHands = numberOfStartingHands;
        this.numberOfSimulations = numberOfSimulations;
        this.drawnCards = new HashMap<Card, Component>();
        this.selectedCards = new ArrayList<Card>();
        this.selectedCardLabels = new ArrayList<Component>();
        this.indexForCard = new HashMap<Card, Integer>();

        this.showMinorErrorDialogs = gui.getSettings().isShowMinorErrorDialogs();

        this.startingHands = new AbstractStartingHand[startingHands.size()];
        for (int i = 0; i < this.startingHands.length; i++) {
            this.startingHands[i] = startingHands.get(i);
        }

        cardLabelsInStartingHands = new List[numberOfStartingHands];
        for (int i = 0; i < cardLabelsInStartingHands.length; i++) {
            cardLabelsInStartingHands[i] = new ArrayList<Component>();
        }

        if (gameType.isCommunityCardGame()) {
            this.board = board;
            this.cardLabelsInBoard = new ArrayList<Component>();
        }

        this.isRerun = true;
    }

    @Override
    public void run() {
        openWindow();
    }

    /**
     * Opens the main window.
     */
    private void openWindow() {
        WindowCreator creator = new WindowCreator(gui.getFrame());
        dialog = creator.createNewJDialog("Simulation starter", 700, 800);

        container = dialog.getLayeredPane();

        try {
            drawAllCards();
        } catch (IOException ex) {
            PicturesNotFoundErrorWindow errorWindow = new PicturesNotFoundErrorWindow(dialog, gui);
            errorWindow.create();
            return;
        }
        createAvailableCardsLabel();

        createMainButtons();

        createGraphicsForStartingHands();

        if (gameType.isCommunityCardGame()) {
            createGraphicsForBoard();
        }

        if (isRerun) {
            drawInitialCardsToStartingHands();
            if (gameType.isCommunityCardGame()) {
                drawInitialCardsToBoard();
            }
        }
    }

    /**
     * Draws all cards to the available cards-section.
     *
     * @throws IOException
     */
    private void drawAllCards() throws IOException {
        ICardDeck deck = new CardDeckStandard(false);
        cardDrawer = new CardDrawer(container, gui.getPictureDirectory(),
                gui.getPictureType());
        int index = 0;
        while (!deck.isEmpty()) {
            Card c = deck.takeCard();
            indexForCard.put(c, index);
            drawCard(c);
            index++;
        }
    }

    private void createGraphicsForBoard() {
        Insets insets = container.getInsets();
        JLabel boardCardsLabel = new JLabel("Board cards");
        Dimension size = boardCardsLabel.getPreferredSize();
        boardCardsLabel.setBounds(480 + insets.left,
                155 + insets.top, size.width, size.height);
        container.add(boardCardsLabel);

        JButton addCards = new JButton("Add");
        size = addCards.getPreferredSize();
        addCards.setBounds(460 + insets.left,
                235, size.width, size.height);
        addCards.addActionListener(new AddCardsToBoard(this,
                480, 185));

        JButton clear = new JButton("Clear");
        size = clear.getPreferredSize();
        clear.setBounds(520 + insets.left,
                235, size.width, size.height);
        clear.addActionListener(new ClearCardsFromBoard(this));

        container.add(addCards);
        container.add(clear);
    }

    /**
     * Creates labels and buttons for starting hands.
     */
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
                    400 + yDistance * (i / handsOnRow), size.width, size.height);
            addCards.addActionListener(new AddCardsToHand(this, i,
                    30 + xDistance * (i % handsOnRow), 350 + yDistance * (i / handsOnRow)));

            JButton clear = new JButton("Clear");
            size = clear.getPreferredSize();
            clear.setBounds(77 + xDistance * (i % handsOnRow) + insets.left,
                    400 + yDistance * (i / handsOnRow), size.width, size.height);
            clear.addActionListener(new ClearCardsFromHand(this, i));

            container.add(addCards);
            container.add(clear);
        }
    }

    /**
     * Creates available cards-label.
     */
    private void createAvailableCardsLabel() {
        JLabel cardsLabel = new JLabel("Available cards");
        Dimension size = cardsLabel.getPreferredSize();
        Insets insets = container.getInsets();
        cardsLabel.setBounds(30 + insets.left, 10 + insets.top,
                size.width, size.height);
        container.add(cardsLabel);
    }

    /**
     * Creates buttons Unselect all, Start simulation and Abort.
     */
    private void createMainButtons() {
        Insets insets = container.getInsets();
        JButton clearSelection = new JButton("Unselect all");
        Dimension size = clearSelection.getPreferredSize();
        clearSelection.setBounds(265 + insets.left, 235 + insets.top, size.width, size.height);
        clearSelection.addActionListener(new UnselectAllCardsListener(container, selectedCards, selectedCardLabels, this));

        container.add(clearSelection);

        JButton startSimulation = new JButton("Start simulation");
        size = startSimulation.getPreferredSize();
        startSimulation.setBounds(450 + insets.left, 720 + insets.top, size.width, size.height);
        startSimulation.addActionListener(new StartSimulation(this, gui));

        container.add(startSimulation);

        JButton abort = new JButton("Abort");
        size = abort.getPreferredSize();
        abort.setBounds(600 + insets.left, 720 + insets.top, size.width, size.height);
        abort.addActionListener(new CloseWindow(dialog));

        container.add(abort);
    }

    /**
     * Draws a card to its original place among selected cards.
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

    public GUIMainWindow getGui() {
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

    /**
     * Initializes the starting hand arrays.
     */
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

    public FiveCardBoard getBoard() {
        return board;
    }

    public List<Component> getCardLabelsInBoard() {
        return cardLabelsInBoard;
    }

    public PokerGameType getGameType() {
        return gameType;
    }

    public int getNumberOfSimulations() {
        return numberOfSimulations;
    }

    public boolean isShowMinorErrorDialogs() {
        return showMinorErrorDialogs;
    }

    private void drawInitialCardsToStartingHands() {
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
            AbstractStartingHand hand = startingHands[i];
            List<Card> cards = hand.getCards();

            for (int j = 0; j < cards.size(); j++) {
                JLabel cardLabel;
                try {
                    cardLabel = cardDrawer.draw(cards.get(j), 30 + xDistance * (i % handsOnRow)
                            + insets.left + 30 * j, 350 + yDistance * (i / handsOnRow), 0, false);
                    cardLabelsInStartingHands[i].add(cardLabel);
                } catch (IOException ex) {
                    Logger.getLogger(SimulationStarter.class.getName()).log(Level.SEVERE, null, ex);
                }
                container.remove(drawnCards.get(cards.get(j)));
            }
        }
    }

    private void drawInitialCardsToBoard() {
        List<Card> cards = board.getCards();
        Insets insets = container.getInsets();
        
        for (int j = 0; j < cards.size(); j++) {
            JLabel cardLabel;
            try {
                cardLabel = cardDrawer.draw(cards.get(j), 480
                        + insets.left + 30 * j, 185, 0, false);
                cardLabelsInBoard.add(cardLabel);
            } catch (IOException ex) {
                Logger.getLogger(SimulationStarter.class.getName()).log(Level.SEVERE, null, ex);
            }
            container.remove(drawnCards.get(cards.get(j)));
        }
    }
}
