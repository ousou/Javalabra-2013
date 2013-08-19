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
        UI ui = new GUIInvoker();
        
        ui.start();
    }
}
