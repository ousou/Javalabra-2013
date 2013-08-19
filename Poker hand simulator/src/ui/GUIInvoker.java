package ui;

import javax.swing.SwingUtilities;

/**
 * Creates GUI-object and invokes it.
 * 
 * @author Sebastian Björkqvist
 */
public class GUIInvoker implements UI {

    @Override
    public void start() {
        GUI gui = new GUI();
        SwingUtilities.invokeLater(gui);
    }

}
