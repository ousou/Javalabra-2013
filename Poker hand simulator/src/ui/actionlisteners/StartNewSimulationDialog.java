package ui.actionlisteners;

import ui.SimulationStarter;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import poker.enums.PokerGameType;
import ui.GUIMainWindow;
import ui.guitools.NumberOfSimulationsInputValidator;
import ui.guitools.WindowCreator;

/**
 *
 * @author Sebastian Björkqvist
 */
public class StartNewSimulationDialog implements ActionListener {

    private GUIMainWindow gui;

    public StartNewSimulationDialog(GUIMainWindow gui) {
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        WindowCreator creator = new WindowCreator(gui.getFrame());
        JDialog dialog = creator.createNewJDialog("Select parameters", 450, 250);
        dialog.setResizable(false);

        JPanel mainPanel = new JPanel();
        setBorderAndLayout(mainPanel);

        JLabel gameTypeText = new JLabel("Game type");
        mainPanel.add(gameTypeText);
        
        PokerGameType[] allGameTypesArray = PokerGameType.getAllGameTypesArray();     
        
        JComboBox gameTypeList = createGameTypeList(allGameTypesArray);
        mainPanel.add(gameTypeList);
        
        JComboBox numberOfHandsList = createNumberOfHandsList(mainPanel);

        JLabel numberOfSimulationsText = new JLabel("Number of simulations");
        mainPanel.add(numberOfSimulationsText);

        JTextField numberOfSimulations = new JTextField(
                Integer.toString(gui.getSettings().getDefaultNumberOfSimulations()));
        mainPanel.add(numberOfSimulations);

        mainPanel.add(new JLabel(""));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new InputValidation(gui, dialog, gameTypeList, numberOfHandsList, numberOfSimulations, allGameTypesArray));
                
        mainPanel.add(okButton);

        dialog.setContentPane(mainPanel);
    }

    /**
     * Sets the border and layout for the main panel.
     * 
     * @param mainPanel 
     */
    private void setBorderAndLayout(JPanel mainPanel) {
        Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);

        mainPanel.setBorder(padding);
        mainPanel.setLayout(new GridLayout(4, 2, 20, 20));
    }
    
    /**
     * Creates the game type list.
     * 
     * @param allGameTypesArray
     * @return JComboBox The list created
     */
    private JComboBox createGameTypeList(PokerGameType[] allGameTypesArray) {
        String[] gameTypeNames = createAllGameTypesArray(allGameTypesArray);

        JComboBox gameTypeList = new JComboBox(gameTypeNames);
        return gameTypeList;
    }

    /**
     * Creates the content array for the game type list.
     * @param allGameTypesArray
     * @return String[] The created array
     */
    private String[] createAllGameTypesArray(PokerGameType[] allGameTypesArray) {
        String[] gameTypeNames = new String[allGameTypesArray.length];
        for (int i = 0; i < gameTypeNames.length; i++) {
            gameTypeNames[i] = allGameTypesArray[i].getFullName();
        }
        return gameTypeNames;
    }

    
    /**
     * Creates the number of hands list.
     * 
     * @param mainPanel
     * @return JComboBox The list created
     */
    private JComboBox createNumberOfHandsList(JPanel mainPanel) {
        JLabel numberOfHandsText = new JLabel("Number of starting hands");
        mainPanel.add(numberOfHandsText);
        String[] numberOfHands = new String[9];
        for (int i = 0; i < numberOfHands.length; i++) {
            numberOfHands[i] = Integer.toString(i + 2);
        }
        JComboBox numberOfHandsList = new JComboBox(numberOfHands);
        mainPanel.add(numberOfHandsList);
        return numberOfHandsList;
    }

    private static class InputValidation implements ActionListener {

        private GUIMainWindow gui;
        private JDialog mainDialog;
        private JComboBox gameTypeList;
        private JComboBox numberOfHandsList;
        private JTextField numberOfSimulations;
        private PokerGameType[] allGameTypesArray;

        public InputValidation(GUIMainWindow gui, JDialog mainDialog, JComboBox gameTypeList,
                JComboBox numberOfHandsList, JTextField numberOfSimulations,
                PokerGameType[] allGameTypesArray) {
            this.gui = gui;
            this.mainDialog = mainDialog;
            this.gameTypeList = gameTypeList;
            this.numberOfHandsList = numberOfHandsList;
            this.numberOfSimulations = numberOfSimulations;
            this.allGameTypesArray = allGameTypesArray;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedGameTypeIndex = gameTypeList.getSelectedIndex();
            PokerGameType gameType = allGameTypesArray[selectedGameTypeIndex];
            int selectedNumberOfHands = Integer.parseInt((String) numberOfHandsList.getSelectedItem());

            if (gameType == PokerGameType.SEVEN_STUD && selectedNumberOfHands > 7) {
                createErrorWindow("Seven card stud simulations can have a maximum of seven starting hands.", 580, 140);
                return;
            }
            NumberOfSimulationsInputValidator validator = new NumberOfSimulationsInputValidator();
            
            Integer selectedNumberOfSimulations = validator.parseInput(numberOfSimulations.getText());
            
            if (selectedNumberOfSimulations == null) {
                validator.createErrorWindow(mainDialog);
                return;                
            }
            
            SimulationStarter starter = new SimulationStarter(gui, gameType, 
                    selectedNumberOfHands, selectedNumberOfSimulations);
            SwingUtilities.invokeLater(starter);
            mainDialog.dispose();
        }

        private void createErrorWindow(String infoString, int width, int height) {
            WindowCreator creator = new WindowCreator(mainDialog);
            JDialog dialog = creator.createNewJDialog("Error!", width, height);

            JPanel mainPanel = new JPanel();
            Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);

            mainPanel.setBorder(padding);
            mainPanel.setLayout(new GridLayout(2, 1, 20, 20));

            JPanel topPanel = new JPanel();            
            JLabel errorMessage = new JLabel(infoString);
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
}
