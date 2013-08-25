package ui;

import javax.swing.SwingUtilities;

/**
 * Creates GUIMainWindow-object and invokes it.
 * 
 * @author Sebastian Björkqvist
 */
public class GUIInvoker implements UI {

    @Override
    public void start() {
        GUIMainWindow gui = new GUIMainWindow();
        SwingUtilities.invokeLater(gui);
    }

}
