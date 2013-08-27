package ui.guitools;

import java.awt.GridLayout;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import ui.actionlisteners.CloseWindow;

/**
 * Validates user input into the number of simulations-field.
 * 
 * @author Sebastian Björkqvist
 */
public class NumberOfSimulationsInputValidator {

    
    /**
     * Parses the input in number of simulations-box
     * 
     * @param input Input string
     * @return The parsed integer, or null if the input couldn't be parsed
     * or if the parsed number was not positive.
     */
    public Integer parseInput(String input) {
        Integer selectedNumberOfSimulations;
        
        try {
            selectedNumberOfSimulations = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return null;
        }
        if (selectedNumberOfSimulations <= 0) {
            return null;
        }

        return selectedNumberOfSimulations;
    }
    
    /**
     * Creates error window telling the user that
     * the parsing failed.
     * 
     * @param owner 
     */
    
    public void createErrorWindow(Window owner) {
            WindowCreator creator = new WindowCreator(owner);
            JDialog dialog = creator.createNewJDialog("Error!", 470, 140);

            JPanel mainPanel = new JPanel();
            Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);

            mainPanel.setBorder(padding);
            mainPanel.setLayout(new GridLayout(2, 1, 20, 20));

            JPanel topPanel = new JPanel();            
            JLabel errorMessage = new JLabel("Number of simulations has to be a positive integer.");
            topPanel.add(errorMessage);
            mainPanel.add(topPanel);
            
            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new GridLayout(1,3,20,20));
            
            JButton okButton = new JButton("OK");
            okButton.addActionListener(new CloseWindow(dialog));
            bottomPanel.add(new JLabel(""));
            bottomPanel.add(okButton);
            bottomPanel.add(new JLabel(""));            
            
            mainPanel.add(bottomPanel);
            
            dialog.setContentPane(mainPanel);           
    }
}
