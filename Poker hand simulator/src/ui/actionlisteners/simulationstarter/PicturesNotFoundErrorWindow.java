package ui.actionlisteners.simulationstarter;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import ui.GUIMainWindow;
import ui.actionlisteners.ProgramShutdown;
import ui.guitools.WindowCreator;

/**
 *
 * @author Sebastian Björkqvist
 */
public class PicturesNotFoundErrorWindow {

    private JDialog dialog;
    private GUIMainWindow gui;

    public PicturesNotFoundErrorWindow(JDialog dialog, GUIMainWindow gui) {
        this.dialog = dialog;
        this.gui = gui;
    }

    public void create() {
        WindowCreator creator = new WindowCreator(dialog);
        JDialog errorWindow = creator.createNewJDialog("Error", 300, 200);

        JPanel mainPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        mainPanel.setBorder(padding);
        mainPanel.setLayout(new GridLayout(6, 1));
        JLabel message1 = new JLabel("Card pictures could not be found.");
        JLabel message2 = new JLabel("The program will shut down.");
        JLabel message3 = new JLabel("If the problem persists, ");
        JLabel message4 = new JLabel("please use the text user interface.");
        mainPanel.add(message1);
        mainPanel.add(message2);
        mainPanel.add(new JLabel(""));
        mainPanel.add(message3);
        mainPanel.add(message4);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(new JLabel(""));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ProgramShutdown(gui));

        buttonPanel.add(okButton);
        buttonPanel.add(new JLabel(""));
        mainPanel.add(buttonPanel);

        errorWindow.setContentPane(mainPanel);
    }
}
