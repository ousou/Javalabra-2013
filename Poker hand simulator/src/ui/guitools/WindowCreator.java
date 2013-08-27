package ui.guitools;

import java.awt.Dimension;
import java.awt.Window;
import javax.swing.JDialog;

/**
 * Class used for creating new windows.
 * 
 * @author Sebastian Björkqvist
 */
public class WindowCreator {

    private Window owner;

    public WindowCreator(Window owner) {
        if (owner == null) {
            throw new IllegalArgumentException("The owner window can't be null!");
        }
        this.owner = owner;
    }
    
    /**
     * Creates a new JDialog-object (a new window).
     * 
     * @param title Title of the window
     * @param width Width of the window
     * @param height Height of the window
     * @return JDialog The created dialog
     */
    
    public JDialog createNewJDialog(String title, int width, int height) {
        JDialog dialog = new JDialog(owner);

        dialog.setTitle(title);
        dialog.setSize(new Dimension(width, height));
        dialog.setLocationRelativeTo(owner);
        dialog.setResizable(false);
        dialog.setVisible(true);

        return dialog;
    }
    
}
