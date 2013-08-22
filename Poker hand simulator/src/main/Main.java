package main;

import ui.GUIInvoker;
import ui.TextUI;
import ui.UI;

/**
 * Main class for Poker Hand Simulator.
 * 
 * Checks command line arguments, and starts the text user interface
 * if needed.
 * 
 * @author Sebastian Björkqvist
 */
public class Main {

    public static void main(String[] args) {
        boolean useGraphical = true;
        
        for (int i = 0; i < args.length; i++) {
            String arg = args[i].trim();
            if (arg.contains("text")) {
                useGraphical = false;
                break;
            }
        }
        
        UI ui;
        if (useGraphical) {
            ui = new GUIInvoker();
        } else {
            ui = new TextUI();
        }
        
        ui.start();
    }
}
