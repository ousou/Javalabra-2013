package logic.simulator;

/**
 * Interface for the main simulation class.
 * 
 * @author Sebastian Björkqvist
 */
public interface IPokerHandSimulator {

    /**
     * Performs the desired simulation.
     * 
     * @return SimulationResult-object containing the results of the simulation.
     */    
    SimulationResult performSimulation();
}
