package ui;

import java.io.Serializable;

/**
 * Settings-class for GUI.
 * 
 * @author Sebastian Björkqvist
 */
public class Settings implements Serializable {

    /**
     * Number of threads to use in simulations.
     */
    private int numberOfThreads;
    /**
     * Number of significant digits that the displayed results have.
     */
    private int numberOfDigits;
    /**
     * Default number of simulations to use.
     */
    private int defaultNumberOfSimulations;
    /**
     * Show minor error dialogs or not.
     */
    private boolean showMinorErrorDialogs;

    /**
     * Creates a new Settings-object.
     * 
     * @param numberOfThreads Number of threads to use in simulations
     * @param numberOfDigits Number of significant digits that the displayed results have
     * @param defaultNumberOfSimulations Default number of simulations to use
     * @param showMinorErrorDialogs Show minor error dialogs or not
     */
    public Settings(int numberOfThreads, int numberOfDigits, int defaultNumberOfSimulations, boolean showMinorErrorDialogs) {
        this.numberOfThreads = numberOfThreads;
        this.numberOfDigits = numberOfDigits;
        this.defaultNumberOfSimulations = defaultNumberOfSimulations;
        this.showMinorErrorDialogs = showMinorErrorDialogs;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public void setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    public int getNumberOfDigits() {
        return numberOfDigits;
    }

    public void setNumberOfDigits(int numberOfDigits) {
        this.numberOfDigits = numberOfDigits;
    }

    public boolean isShowMinorErrorDialogs() {
        return showMinorErrorDialogs;
    }

    public void setShowMinorErrorDialogs(boolean showMinorErrorDialogs) {
        this.showMinorErrorDialogs = showMinorErrorDialogs;
    }

    public int getDefaultNumberOfSimulations() {
        return defaultNumberOfSimulations;
    }

    public void setDefaultNumberOfSimulations(int defaultNumberOfSimulations) {
        this.defaultNumberOfSimulations = defaultNumberOfSimulations;
    }
}
