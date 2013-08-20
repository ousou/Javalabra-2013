package ui.actionlisteners;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Closes a window.
 * 
 * @author Sebastian Björkqvist
 */
public class CloseWindow implements ActionListener {

    private Window window;

    public CloseWindow(Window window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.dispose();
    }
    
}
