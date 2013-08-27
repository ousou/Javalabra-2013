package ui;

import card.Card;
import filehandling.ObjectReader;
import filehandling.ObjectWriter;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import logic.simulator.SimulationResult;
import poker.FiveCardBoard;
import poker.enums.PokerGameType;
import poker.startinghands.AbstractStartingHand;
import ui.actionlisteners.CloseWindow;
import ui.actionlisteners.ModifySettings;
import ui.guitools.CardDrawer;
import ui.actionlisteners.ProgramShutdown;
import ui.actionlisteners.StartNewSimulationDialog;
import ui.actionlisteners.simulationstarter.PicturesNotFoundErrorWindow;
import ui.guitools.WindowCreator;

/**
 * Graphical user interface for Poker hand simulator.
 *
 * @author Sebastian Björkqvist
 */
public class GUIMainWindow implements Runnable {

    private JFrame frame;
    private Container container;
    private final String pictureDirectory = "";
    private final String pictureType = ".png";
    private CardDrawer cardDrawer;
    private SimulationResult simulationResult;
    private Settings settings;
    private List<AbstractStartingHand> startingHands;
    private String savedSettingsPath = "PHSsettings/settings.dat";
    private String defaultSettingsPath;

    @Override
    public void run() {
        createMainWindow();
        this.cardDrawer = new CardDrawer(container, pictureDirectory, pictureType);
        createMenuBar();
        readSettings();
        createLabel("Results", 150, 0);
        createStartSimulationButton();
    }

    /**
     * Creates the main window for the poker hand simulator.
     */
    private void createMainWindow() {
        frame = new JFrame("Poker Hand Simulator");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new ProgramShutdown(this));

        JPanel mainPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        mainPanel.setBorder(padding);
        mainPanel.setLayout(null);

        frame.setContentPane(mainPanel);

        container = frame.getContentPane();

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Creates the menu bar.
     */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        createMainMenu(menuBar);
        frame.setJMenuBar(menuBar);
    }

    /**
     * Creates the main menu.
     *
     * @param menuBar
     */
    private void createMainMenu(JMenuBar menuBar) {
        JMenu mainMenu = new JMenu("Main");
        mainMenu.setMnemonic(KeyEvent.VK_M);

        menuBar.add(mainMenu);

        JMenuItem startSimulationButton = new JMenuItem("New simulation...",
                KeyEvent.VK_N);
        startSimulationButton.addActionListener(new StartNewSimulationDialog(this));
        mainMenu.add(startSimulationButton);
        mainMenu.addSeparator();

        JMenuItem modifySettingsButton = new JMenuItem("Settings...",
                KeyEvent.VK_S);
        modifySettingsButton.addActionListener(new ModifySettings(this));
        mainMenu.add(modifySettingsButton);
        mainMenu.addSeparator();

        JMenuItem exitProgramButton = new JMenuItem("Exit",
                KeyEvent.VK_E);
        exitProgramButton.addActionListener(new ProgramShutdown(this));
        mainMenu.add(exitProgramButton);
    }

    public JFrame getFrame() {
        return frame;
    }

    public String getPictureDirectory() {
        return pictureDirectory;
    }

    public String getPictureType() {
        return pictureType;
    }

    public void setSimulationResult(SimulationResult simulationResult) {
        this.simulationResult = simulationResult;
    }

    /**
     * This method has hard-coded the number of threads to use at the moment.
     *
     * This needs to be changed so that the user can decide how many threads to
     * use.
     */
    private void readSettings() {
        ObjectReader reader = new ObjectReader();
        Object savedSettingsObject = reader.readObject(savedSettingsPath);

        if (savedSettingsObject != null) {
            try {
                Settings savedSettings = (Settings) savedSettingsObject;
                this.settings = savedSettings;
                return;
            } catch (ClassCastException e) {
            }
        }
        createSettingsNotLoadedWindow();
        
        this.settings = new Settings(2, 4, 10000, true);
    }

    public Settings getSettings() {
        return settings;
    }

    /**
     * Writes the results from the latest simulation to the screen.
     */
    
    public void writeResultsToScreen() {
        if (simulationResult == null) {
            return;
        }
        container.removeAll();
        createLabel("Results", 150, 0);
        createStartSimulationButton();        
        PokerGameType gameType = simulationResult.getGameType();

        if (gameType.isCommunityCardGame()) {
            createLabel("Board", 10, 40);
            drawBoardCards(simulationResult.getBoard());
        }

        createLabelsForHands(startingHands.size());
        drawHandCards();

        createWinLossTieEquityLabels();
        drawWinLossTieEquityResults();

        container.repaint();
    }

    /**
     * Creates labels for hands.
     * 
     * @param numberOfHands 
     */
    
    private void createLabelsForHands(int numberOfHands) {
        int startX = 10;
        int startY = 100;
        int yChange = 45;

        for (int i = 0; i < numberOfHands; i++) {
            createLabel("Hand " + (i + 1), 10, 100 + yChange * i);
        }
    }

    /**
     * Creates and adds labels to the container.
     * 
     * @param message Text to write to screen
     * @param x Place, x-coordinate
     * @param y Place, y-coordinate
     */
    
    private void createLabel(String message, int x, int y) {
        Insets insets = container.getInsets();
        JLabel label = new JLabel(message);
        Dimension size = label.getPreferredSize();
        label.setBounds(insets.left + x, insets.top + y,
                size.width, size.height);
        container.add(label);
    }

    /**
     * Creates Win, Loss, Tie and Equity-labels
     */
    private void createWinLossTieEquityLabels() {
        createLabel("Win %", 300, 65);
        createLabel("Loss %", 370, 65);
        createLabel("Tie %", 450, 65);
        createLabel("Equity", 550, 65);
    }

    /**
     * Draws the board cards to the container.
     * @param board 
     */
    
    private void drawBoardCards(FiveCardBoard board) {
        List<Card> boardCards = board.getCards();
        int xChange = 30;
        int xStart = 100;
        int yStart = 35;

        for (int i = 0; i < boardCards.size(); i++) {
            try {
                cardDrawer.draw(boardCards.get(i), xStart + xChange * i, yStart, 1, false);
            } catch (IOException ex) {
                PicturesNotFoundErrorWindow errorWindow = new PicturesNotFoundErrorWindow((JDialog) container, this);
                errorWindow.create();
            }
        }
    }

    /**
     * Draws the cards from the starting hands to the screen.
     */
    private void drawHandCards() {
        int startX = 100;
        int startY = 90;
        int xChange = 30;
        int yChange = 45;


        for (int j = 0; j < startingHands.size(); j++) {
            List<Card> cards = startingHands.get(j).getCards();
            for (int i = 0; i < cards.size(); i++) {
                try {
                    cardDrawer.draw(cards.get(i), startX + xChange * i, startY + yChange * j, 1, false);
                } catch (IOException ex) {
                    PicturesNotFoundErrorWindow errorWindow = new PicturesNotFoundErrorWindow((JDialog) container, this);
                    errorWindow.create();
                }
            }
        }
    }

    /**
     * Writes the win, loss and tie percentages and the equity for each hand
     * to the screen.
     */
    private void drawWinLossTieEquityResults() {
        int yChange = 45;
        int yStart = 100;
        int numberOfDigits = settings.getNumberOfDigits();

        for (int j = 0; j < startingHands.size(); j++) {
            AbstractStartingHand hand = startingHands.get(j);
            createLabel(Double.toString(simulationResult.getWinPercentageForHand(hand,
                    numberOfDigits)), 300, 100 + j * yChange);
            createLabel(Double.toString(simulationResult.getLossPercentageForHand(hand,
                    numberOfDigits)), 370, 100 + j * yChange);
            createLabel(Double.toString(simulationResult.getTiePercentageForHand(hand,
                    numberOfDigits)), 450, 100 + j * yChange);
            createLabel(Double.toString(simulationResult.getEquityForHand(hand,
                    numberOfDigits)), 550, 100 + j * yChange);
        }
    }

    public void setStartingHands(List<AbstractStartingHand> startingHands) {
        this.startingHands = startingHands;
    }
    
    /**
     * Saves settings to disk.
     * 
     * @return true if saving succeeded, false otherwise.
     */
    public boolean saveSettingsToDisk() {
        ObjectWriter writer = new ObjectWriter();
        
        return writer.writeObject(settings, savedSettingsPath);
    }

    
    /**
     * Creates dialog that is shown if the settings couldn't
     * be loaded at startup.
     */
    private void createSettingsNotLoadedWindow() {
        WindowCreator creator = new WindowCreator(frame);
        JDialog dialog = creator.createNewJDialog("Settings not loaded", 325, 150);
        JPanel mainPanel = new JPanel();
        
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        mainPanel.setBorder(padding);
        mainPanel.setLayout(new GridLayout(4, 1));      
        
        JLabel message1 = new JLabel("Settings-file not found.");
        JLabel message2 = new JLabel("Default settings will be used.");
        JLabel message3 = new JLabel("A new settings file will be created.");
        
        mainPanel.add(message1);
        mainPanel.add(message2);
        mainPanel.add(message3);
        
        JPanel buttonPanel = new JPanel();
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new CloseWindow(dialog));
                
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(okButton);
        buttonPanel.add(new JLabel(""));
        mainPanel.add(buttonPanel);
        
        dialog.setContentPane(mainPanel);
        dialog.setResizable(false);
        
    }

    /**
     * Creates and adds 'Start new simulation'-button to the container.
     */
    private void createStartSimulationButton() {
        JButton startSimulation = new JButton("Start new simulation");
        
        Insets insets = container.getInsets();
        Dimension size = startSimulation.getPreferredSize();
        startSimulation.setBounds(insets.left + 550, insets.top + 20,
                size.width, size.height);
        startSimulation.addActionListener(new StartNewSimulationDialog(this));
        container.add(startSimulation);
    }
}
