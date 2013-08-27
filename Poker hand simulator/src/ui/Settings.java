package ui;

import java.io.Serializable;

/**
 * Settings-class for GUI.
 * 
 * @author Sebastian Björkqvist
 */
public class Settings implements Serializable {

    private int numberOfThreads;
    private int numberOfDigits;
    private boolean showMinorErrorDialogs;

    public Settings(int numberOfThreads, int numberOfDigits, boolean showMinorErrorDialogs) {
        this.numberOfThreads = numberOfThreads;
        this.numberOfDigits = numberOfDigits;
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
}
