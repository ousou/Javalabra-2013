package ui;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import poker.enums.PokerGameType;
import ui.guitools.CardDrawer;
import ui.actionlisteners.ProgramShutdown;
import ui.actionlisteners.SimulationStarter;
import ui.actionlisteners.StartNewSimulationDialog;

/**
 * Graphical user interface for Poker hand simulator.
 * 
 * @author Sebastian Björkqvist
 */
public class GUI implements Runnable {

    private JFrame frame;
    private final String pictureDirectory = "pictures_small/";
    private final String pictureType = ".png";    
    private CardDrawer cardDrawer;
    
    @Override
    public void run() {
        createMainWindow();
        this.cardDrawer = new CardDrawer(frame.getLayeredPane(), pictureDirectory, pictureType);
        createMenuBar();
    }

    /**
     * Creates the main window for the poker hand simulator.
     */
    private void createMainWindow() {
        frame = new JFrame("Poker Hand Simulator");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new ProgramShutdown(this));
        
        frame.getLayeredPane().setLayout(null);
        
        frame.pack();
        frame.setVisible(true);        
    }
    
    /**
     * Creates the menu bar.
     */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        createMainMenu(menuBar);
        frame.setJMenuBar(menuBar);
    }

    /**
     * Creates the main menu.
     * 
     * @param menuBar 
     */
    private void createMainMenu(JMenuBar menuBar) {
        JMenu mainMenu = new JMenu("Main");
        mainMenu.setMnemonic(KeyEvent.VK_M);

        menuBar.add(mainMenu);

        JMenuItem startSimulationButton = new JMenuItem("New simulation",
                KeyEvent.VK_S);
        startSimulationButton.addActionListener(new StartNewSimulationDialog(this));
        mainMenu.add(startSimulationButton);
        mainMenu.addSeparator();   
        
        JMenuItem exitProgramButton = new JMenuItem("Exit",
                KeyEvent.VK_E);   
        exitProgramButton.addActionListener(new ProgramShutdown(this));
        mainMenu.add(exitProgramButton);        
    }

    public JFrame getFrame() {
        return frame;
    }

    public String getPictureDirectory() {
        return pictureDirectory;
    }

    public String getPictureType() {
        return pictureType;
    }
}
