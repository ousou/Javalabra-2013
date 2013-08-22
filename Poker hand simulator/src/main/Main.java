package main;

import ui.GUIInvoker;
import ui.TextUI;
import ui.UI;

/**
 *
 * @author Sebastian Björkqvist
 */
public class Main {

    public static void main(String[] args) {
        boolean useGraphical = true;
        if (args.length > 0) {
            String firstArg = args[0].trim();
            if (firstArg.equals("-text")) {
                useGraphical = false;
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
