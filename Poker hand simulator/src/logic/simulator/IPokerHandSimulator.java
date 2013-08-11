package logic.simulator;

/**
 * Interface for the main simulation class.
 * 
 * The classes that implement this interface performs simulations of poker
 * hands, and returns the result in a SimulationResult-class.
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
