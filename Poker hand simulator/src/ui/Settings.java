package ui;

import java.io.Serializable;

/**
 * Settings-class for GUI.
 * 
 * @author Sebastian Björkqvist
 */
public class Settings implements Serializable {

    private int numberOfThreads;

    public Settings(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public void setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }
       
}
