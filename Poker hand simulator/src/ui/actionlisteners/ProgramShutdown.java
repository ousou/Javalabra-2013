package ui.actionlisteners;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import ui.GUIMainWindow;
import ui.guitools.WindowCreator;

/**
 * Shuts down the GUIMainWindow properly.
 *
 * @author Sebastian Björkqvist
 */
public class ProgramShutdown implements ActionListener, WindowListener {

    private GUIMainWindow gui;

    public ProgramShutdown(GUIMainWindow gui) {
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

    /**
     * Handles program shutdown.
     */
    private void handleShutdown() {
        if (!gui.saveSettingsToDisk()) {
            openSettingsSavingFailedDialog();
        } else {
            System.exit(0);
        }
    }

    /**
     * Opens when settings could not be saved to disk.
     */
    private void openSettingsSavingFailedDialog() {
        WindowCreator creator = new WindowCreator(gui.getFrame());
        JDialog errorDialog = creator.createNewJDialog("Error", 260, 110);

        JPanel mainPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        mainPanel.setBorder(padding);
        mainPanel.setLayout(new GridLayout(2, 1));

        createErrorMessageForDialog(mainPanel);

        JPanel buttonPanel = new JPanel();

        JButton okButton = new JButton("Close program");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(okButton);
        mainPanel.add(buttonPanel);

        errorDialog.setContentPane(mainPanel);
    }

    private void createErrorMessageForDialog(JPanel mainPanel) {
        JLabel message1 = new JLabel("Could not save settings to disk.");
        mainPanel.add(message1);
    }
}
