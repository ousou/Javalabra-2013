package ui.actionlisteners.simulationstarter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import logic.simulator.AbstractPokerHandSimulator;
import logic.simulator.PokerHandSimulatorVersion2;
import logic.simulator.SimulationResult;
import poker.FiveCardBoard;
import poker.startinghands.AbstractStartingHand;
import ui.GUIMainWindow;
import ui.SimulationStarter;
import ui.actionlisteners.CloseWindow;
import ui.guitools.WindowCreator;

/**
 * ActionListener for button Start in the Simulation starter-window.
 *
 * This class handles starting the simulation and returning the results to the
 * main window.
 *
 * @author Sebastian Björkqvist
 */
public class StartSimulation implements ActionListener {

    private SimulationStarter simulationStarter;
    private GUIMainWindow gui;

    /**
     * Creates a new StartSimulation.
     * 
     * @param simulationStarter The simulation starter.
     * @param gui The gui main window.
     */
    public StartSimulation(SimulationStarter simulationStarter, GUIMainWindow gui) {
        this.simulationStarter = simulationStarter;
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        start();
    }

    /**
     * Starts the simulation.
     * 
     * If all starting hands are empty, the method opens an error dialog and
     * the simulation does not start.
     */
    private void start() {
        List<AbstractStartingHand> startingHands = Arrays.asList(simulationStarter.getStartingHands());

        if (areAllHandsEmpty(startingHands)) {
            openAllHandsAreEmptyErrorDialog();
            return;
        }
        
        int numberOfThreads = gui.getSettings().getNumberOfThreads();
        FiveCardBoard board = simulationStarter.getBoard();
        int numberOfSimulations = simulationStarter.getNumberOfSimulations();

        AbstractPokerHandSimulator simulator;
        
        simulator = createSimulatorObject(board, startingHands, numberOfSimulations);

        SimulationResult result = null;

        simulationStarter.getDialog().dispose();         
        try {
            result = simulator.performSimulation(numberOfThreads);
        } catch (InterruptedException ex) {
            createSimulationInterruptedErrorWindow();
        }

        if (result != null) {
            outputResults(result, startingHands);
        }
    }

    /**
     * Creates the Simulation interrupted error window.
     */
    private void createSimulationInterruptedErrorWindow() {
        WindowCreator windowCreator = new WindowCreator(simulationStarter.getDialog());
        JDialog errorWindow = windowCreator.createNewJDialog("Error", 400, 200);

        JPanel mainPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        mainPanel.setBorder(padding);
        writeErrorMessageToPanel(mainPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(new JLabel(""));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new CloseWindow(errorWindow));

        buttonPanel.add(okButton);
        buttonPanel.add(new JLabel(""));
        mainPanel.add(buttonPanel);

        errorWindow.setContentPane(mainPanel);
    }

    /**
     * Writes the 'Simulation interrupted'-message to the given panel.
     * 
     * @param panel 
     */
    private void writeErrorMessageToPanel(JPanel panel) {
        panel.setLayout(new GridLayout(5, 1));
        JLabel message1 = new JLabel("One of the simulation threads were interrupted.");
        JLabel message2 = new JLabel("Please try again.");
        JLabel message3 = new JLabel("If the problem persists, try using only one thread.");
        panel.add(message1);
        panel.add(message2);
        panel.add(new JLabel(""));
        panel.add(message3);
    }

    /**
     * Checks if all hands are empty.
     * 
     * @param startingHands List of starting hands
     * @return true if all hands are empty.
     */
    private boolean areAllHandsEmpty(List<AbstractStartingHand> startingHands) {
        for (AbstractStartingHand hand : startingHands) {
            if (hand.getNumberOfCards() != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Opens 'All hands are empty'-error dialog.
     */
    private void openAllHandsAreEmptyErrorDialog() {
        WindowCreator creator = new WindowCreator(simulationStarter.getDialog());
        JDialog errorDialog = creator.createNewJDialog("Error", 270, 140);

        JPanel mainPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        mainPanel.setBorder(padding);
        mainPanel.setLayout(new GridLayout(3, 1));

        createErrorMessageForDialog(mainPanel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(new JLabel(""));        

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new CloseWindow(errorDialog));

        buttonPanel.add(okButton);
        buttonPanel.add(new JLabel(""));
        mainPanel.add(buttonPanel);

        errorDialog.setContentPane(mainPanel);        
    }

    /**
     * Writes 'All hands are empty'-message to given panel.
     * 
     * @param panel 
     */
    
    private void createErrorMessageForDialog(JPanel panel) {
        JLabel message1 = new JLabel("All starting hands are empty!");
        JLabel message2 = new JLabel("Add some cards before starting.");        
        panel.add(message1);
        panel.add(message2);
    }

    /**
     * Creates the AbstractPokerHandSimulator-object.
     * 
     * @param board
     * @param startingHands
     * @param numberOfSimulations
     * @return Created AbstractPokerHandSimulator
     */
    private AbstractPokerHandSimulator createSimulatorObject(FiveCardBoard board, 
            List<AbstractStartingHand> startingHands, int numberOfSimulations) {
        AbstractPokerHandSimulator simulator;
        if (simulationStarter.getGameType().isCommunityCardGame()
                && board != null && board.getNumberOfCards() > 0) {
            simulator = new PokerHandSimulatorVersion2(startingHands, board.getCards(), 
                    numberOfSimulations);
        } else {
            simulator = new PokerHandSimulatorVersion2(startingHands, numberOfSimulations);
        }
        return simulator;
    }

    /**
     * Writes the results to the screen.
     * 
     * @param result
     * @param startingHands 
     */
    private void outputResults(SimulationResult result, 
            List<AbstractStartingHand> startingHands) {
        gui.setSimulationResult(result);
        gui.setStartingHands(startingHands);
        gui.writeResultsToScreen();
    }
}
