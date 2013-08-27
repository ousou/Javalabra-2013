package ui.actionlisteners;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import ui.GUIMainWindow;
import ui.Settings;
import ui.guitools.WindowCreator;

/**
 *
 * @author Sebastian Björkqvist
 */
public class ModifySettings implements ActionListener {

    private GUIMainWindow gui;

    public ModifySettings(GUIMainWindow gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        WindowCreator creator = new WindowCreator(gui.getFrame());
        JDialog dialog = creator.createNewJDialog("Modify settings", 500, 250);
        dialog.setResizable(false);

        JPanel mainPanel = new JPanel();
        setBorderAndLayout(mainPanel);

        Settings settings = gui.getSettings();

        JComboBox numberOfThreadsList = createNumberOfThreadsSelection(mainPanel, settings);
        JComboBox numberOfDigitsList = createNumberOfDigitsSelection(mainPanel, settings);
        JCheckBox showMinorErrorMessages = createShowMinorErrorMessagesSelection(mainPanel, settings);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4, 20, 20));
        createOkButton(buttonPanel, numberOfThreadsList, numberOfDigitsList, showMinorErrorMessages, dialog, settings);
        createCancelButton(buttonPanel, dialog);

        mainPanel.add(new JLabel(""));
        mainPanel.add(buttonPanel);
        dialog.setContentPane(mainPanel);
    }

    private void setBorderAndLayout(JPanel mainPanel) {
        Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);

        mainPanel.setBorder(padding);
        mainPanel.setLayout(new GridLayout(4, 2, 20, 20));
    }

    private JComboBox createNumberOfThreadsSelection(JPanel mainPanel, Settings settings) {
        JLabel numberOfThreadsText = new JLabel("Number of threads to use");
        mainPanel.add(numberOfThreadsText);

        String[] numberOfThreads = new String[16];
        for (int i = 0; i < numberOfThreads.length; i++) {
            numberOfThreads[i] = Integer.toString(i + 1);
        }
        JComboBox numberOfThreadsList = new JComboBox(numberOfThreads);
        numberOfThreadsList.setSelectedIndex(settings.getNumberOfThreads() - 1);
        mainPanel.add(numberOfThreadsList);

        return numberOfThreadsList;
    }

    private JComboBox createNumberOfDigitsSelection(JPanel mainPanel, Settings settings) {
        JLabel numberOfDigitsText = new JLabel("Number of digits in result");
        mainPanel.add(numberOfDigitsText);

        String[] numberOfDigits = new String[5];
        for (int i = 0; i < numberOfDigits.length; i++) {
            numberOfDigits[i] = Integer.toString(i + 2);
        }
        JComboBox numberOfDigitsList = new JComboBox(numberOfDigits);
        numberOfDigitsList.setSelectedIndex(settings.getNumberOfDigits() - 2);
        mainPanel.add(numberOfDigitsList);

        return numberOfDigitsList;
    }

    private void createOkButton(JPanel panel, JComboBox numberOfThreadsList, JComboBox numberOfDigitsList, JCheckBox showMinorErrorMessages, JDialog dialog, Settings settings) {
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new UpdateSettings(numberOfThreadsList,
                numberOfDigitsList, showMinorErrorMessages, settings, dialog, gui));

        panel.add(okButton);
    }

    private void createCancelButton(JPanel panel, JDialog dialog) {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CloseWindow(dialog));
        panel.add(cancelButton);
    }

    private JCheckBox createShowMinorErrorMessagesSelection(JPanel mainPanel,
            Settings settings) {
        JLabel showMinorErrorLabel = new JLabel("Show minor error messages");
        JCheckBox showMinorErrors = new JCheckBox();
        showMinorErrors.setSelected(settings.isShowMinorErrorDialogs());

        mainPanel.add(showMinorErrorLabel);
        mainPanel.add(showMinorErrors);

        return showMinorErrors;
    }

    private static class UpdateSettings implements ActionListener {

        private JComboBox numberOfThreadsList;
        private JComboBox numberOfDigitsList;
        private JCheckBox showMinorErrorMessages;
        private Settings settings;
        private JDialog dialog;
        private GUIMainWindow gui;

        public UpdateSettings(JComboBox numberOfThreadsList, JComboBox numberOfDigitsList, JCheckBox showMinorErrorMessages, Settings settings, JDialog dialog, GUIMainWindow gui) {
            this.numberOfThreadsList = numberOfThreadsList;
            this.numberOfDigitsList = numberOfDigitsList;
            this.showMinorErrorMessages = showMinorErrorMessages;
            this.settings = settings;
            this.dialog = dialog;
            this.gui = gui;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int numberOfThreads = numberOfThreadsList.getSelectedIndex() + 1;
            int numberOfDigits = numberOfDigitsList.getSelectedIndex() + 2;
            boolean showMinorErrors = showMinorErrorMessages.isSelected();

            settings.setNumberOfThreads(numberOfThreads);
            settings.setNumberOfDigits(numberOfDigits);
            settings.setShowMinorErrorDialogs(showMinorErrors);

            dialog.dispose();
            gui.writeResultsToScreen();
        }
    }
}
