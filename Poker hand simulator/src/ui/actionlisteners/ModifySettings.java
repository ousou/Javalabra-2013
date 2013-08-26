package ui.actionlisteners;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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
        JDialog dialog = creator.createNewJDialog("Modify settings", 450, 200);
        dialog.setResizable(false);        
        
        JPanel mainPanel = new JPanel();
        setBorderAndLayout(mainPanel);    
        
        Settings settings = gui.getSettings();
        
        JComboBox numberOfThreadsList = createNumberOfThreadsSelection(mainPanel, settings);
        JComboBox numberOfDigitsList = createNumberOfDigitsSelection(mainPanel, settings);
        
        createOkButton(mainPanel, numberOfThreadsList, numberOfDigitsList, dialog, settings);
        
        dialog.setContentPane(mainPanel);        
    }
    
    private void setBorderAndLayout(JPanel mainPanel) {
        Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);

        mainPanel.setBorder(padding);
        mainPanel.setLayout(new GridLayout(3, 2, 20, 20));
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

    private JButton createOkButton(JPanel mainPanel, JComboBox numberOfThreadsList, 
            JComboBox numberOfDigitsList, JDialog dialog, Settings settings) {
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new UpdateSettings(numberOfThreadsList,
                numberOfDigitsList, settings, dialog, gui));        
        
        mainPanel.add(new JLabel(""));
        mainPanel.add(okButton);
        
        return null;
    }
    
    private static class UpdateSettings implements ActionListener {

        private JComboBox numberOfThreadsList;
        private JComboBox numberOfDigitsList;
        private Settings settings;
        private JDialog dialog;
        private GUIMainWindow gui;

        public UpdateSettings(JComboBox numberOfThreadsList, JComboBox numberOfDigitsList, Settings settings, JDialog dialog, GUIMainWindow gui) {
            this.numberOfThreadsList = numberOfThreadsList;
            this.numberOfDigitsList = numberOfDigitsList;
            this.settings = settings;
            this.dialog = dialog;
            this.gui = gui;
        }

        
        @Override
        public void actionPerformed(ActionEvent e) {
            int numberOfThreads = numberOfThreadsList.getSelectedIndex() + 1;
            int numberOfDigits = numberOfDigitsList.getSelectedIndex() + 2;
            
            settings.setNumberOfThreads(numberOfThreads);
            settings.setNumberOfDigits(numberOfDigits);
            
            dialog.dispose();
            gui.writeResultsToScreen();
        }
        
    }
}
