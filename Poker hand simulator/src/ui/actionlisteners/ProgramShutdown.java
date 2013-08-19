package ui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import ui.GUI;

/**
 * Shuts down the GUI properly.
 * 
 * @todo Implement settings saving before shutdown.
 * @author Sebastian Björkqvist
 */
public class ProgramShutdown implements ActionListener, WindowListener {

    private GUI gui;
    
    public ProgramShutdown(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handleShutdown();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        handleShutdown();        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        handleShutdown();        
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    
    private void handleShutdown() {
        System.exit(0);        
    }
}
