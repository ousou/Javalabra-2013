package ui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingUtilities;
import logic.simulator.SimulationResult;
import poker.startinghands.AbstractStartingHand;
import ui.GUIMainWindow;
import ui.SimulationStarter;

/**
 * Used for opening a SimulationStarter-window with some preselected cards.
 * 
 * @author Sebastian Björkqvist
 */
public class ModifyLastSimulation implements ActionListener {

    private GUIMainWindow gui;
    private List<AbstractStartingHand> startingHands;
    private SimulationResult simulationResult;

    public ModifyLastSimulation(GUIMainWindow gui, List<AbstractStartingHand> startingHands, SimulationResult simulationResult) {
        this.gui = gui;
        this.startingHands = startingHands;
        this.simulationResult = simulationResult;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        SimulationStarter starter = new SimulationStarter(gui, simulationResult.getGameType(), 
                startingHands.size(), simulationResult.getTotalNumberOfSimulationsToPerform(), 
                startingHands, simulationResult.getBoard());
        
        SwingUtilities.invokeLater(starter);
    }
}
