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
