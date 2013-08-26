package ui.actionlisteners.simulationstarter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
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

    public StartSimulation(SimulationStarter simulationStarter, GUIMainWindow gui) {
        this.simulationStarter = simulationStarter;
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        start();
    }

    private void start() {
        List<AbstractStartingHand> startingHands = Arrays.asList(simulationStarter.getStartingHands());
        
        if (areAllHandsEmpty(startingHands)) {
            System.out.println("Need to choose something!");
            return;
        }
        int numberOfThreads = gui.getSettings().getNumberOfThreads();
        FiveCardBoard board = simulationStarter.getBoard();
        int numberOfSimulations = simulationStarter.getNumberOfSimulations();

        AbstractPokerHandSimulator simulator;

        if (simulationStarter.getGameType().isCommunityCardGame() 
                && board != null && board.getNumberOfCards() > 0) {
            simulator = new PokerHandSimulatorVersion2(startingHands, board.getCards(), numberOfSimulations);
        } else {
            simulator = new PokerHandSimulatorVersion2(startingHands, numberOfSimulations);
        }
        
        SimulationResult result = null;
        
        try {
            result = simulator.performSimulation(numberOfThreads);
        } catch (InterruptedException ex) {
            createSimulationInterruptedErrorWindow();
        }
        
        if (result != null) {
            simulationStarter.getDialog().dispose();            
            gui.setSimulationResult(result);
            gui.setStartingHands(startingHands);
            gui.writeResultsToScreen();
        }
    }


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

    private void writeErrorMessageToPanel(JPanel mainPanel) {
        mainPanel.setLayout(new GridLayout(5, 1));
        JLabel message1 = new JLabel("One of the simulation threads were interrupted.");
        JLabel message2 = new JLabel("Please try again.");
        JLabel message3 = new JLabel("If the problem persists, try using only one thread.");
        mainPanel.add(message1);
        mainPanel.add(message2);
        mainPanel.add(new JLabel(""));
        mainPanel.add(message3);
    }

    private boolean areAllHandsEmpty(List<AbstractStartingHand> startingHands) {
        for (AbstractStartingHand hand : startingHands) {
            if (hand.getNumberOfCards() != 0) {
                return false;
            }
        }
        
        return true;
    }
}
