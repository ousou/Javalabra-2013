package ui.actionlisteners.simulationstarter;

import card.Card;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import poker.AbstractCardCollection;
import ui.SimulationStarter;
import ui.actionlisteners.CloseWindow;
import ui.guitools.CardDrawer;
import ui.guitools.WindowCreator;

/**
 * Adds cards to a collection.
 *
 * This class is extended by AddCardsToBoard and AddCardsToHand.
 *
 * @author Sebastian Björkqvist
 */
public abstract class AddCardsToCollection implements ActionListener {

    protected SimulationStarter simulationStarter;
    protected int xStart;
    protected int yStart;
    protected AbstractCardCollection collection;
    protected List<Component> cardLabels;

    public AddCardsToCollection(SimulationStarter simulationStarter, int xStart, int yStart, AbstractCardCollection collection, List<Component> cardLabels) {
        this.simulationStarter = simulationStarter;
        this.xStart = xStart;
        this.yStart = yStart;
        this.collection = collection;
        this.cardLabels = cardLabels;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Container container = simulationStarter.getContainer();
        List<Card> cardsToAdd = simulationStarter.getSelectedCards();

        int cardsBeforeAddition = collection.getNumberOfCards();

        if (cardsToAdd.size() <= collection.getMaximumAmountOfCardsInCollection()
                - cardsBeforeAddition) {
            try {
                collection.addCards(cardsToAdd);
            } catch (IllegalArgumentException ex) {
                openSeriousCardAddingErrorDialog();
                return;
            }
        } else {
            if (simulationStarter.isShowMinorErrorDialogs()) {
                openCardCantFitThatManyCardsDialog();
            }
            return;
        }
        CardDrawer cardDrawer = simulationStarter.getCardDrawer();

        int howMany = cardsBeforeAddition;

        Map<Card, Component> drawnCards = simulationStarter.getDrawnCards();
        
        drawCardsToCollection(cardsToAdd, cardDrawer, howMany, container, drawnCards);
        
        List<Component> selectedCardLabels = simulationStarter.getSelectedCardLabels();

        // Removes the grey cards under Available cards.
        for (Component c : selectedCardLabels) {
            container.remove(c);
        }

        container.repaint();

        selectedCardLabels.clear();
        cardsToAdd.clear();
    }

    /**
     * Opens error dialog when added cards don't fit the collection.
     */
    
    protected void openCardCantFitThatManyCardsDialog() {
        WindowCreator creator = new WindowCreator(simulationStarter.getDialog());
        JDialog errorDialog = creator.createNewJDialog("Error", 350, 170);

        JPanel mainPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        mainPanel.setBorder(padding);
        mainPanel.setLayout(new GridLayout(3, 1));

        createErrorMessageForTooManyCardsDialog(mainPanel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(new JLabel(""));        

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new CloseWindow(errorDialog));

        buttonPanel.add(okButton);
        buttonPanel.add(new JLabel(""));
        mainPanel.add(buttonPanel);

        errorDialog.setContentPane(mainPanel);

    }

    /**
     * Creates the error message for the too many cards in collection-dialog.
     * 
     * @param panel Panel to which the message is added.
     */
    protected abstract void createErrorMessageForTooManyCardsDialog(JPanel panel);
    
    /**
     * Creates an error dialog for a serious error.
     * 
     * If this method is called, there is a bug in the program somewhere.
     */ 
    
    private void openSeriousCardAddingErrorDialog() {
        WindowCreator creator = new WindowCreator(simulationStarter.getDialog());
        JDialog errorDialog = creator.createNewJDialog("Error", 450, 250);    
        
        JPanel mainPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        mainPanel.setBorder(padding);
        mainPanel.setLayout(new GridLayout(6, 1));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(new JLabel(""));

        createSeriousErrorMessageForDialog(mainPanel);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new CloseWindow(errorDialog));

        buttonPanel.add(okButton);
        buttonPanel.add(new JLabel(""));
        mainPanel.add(buttonPanel);

        errorDialog.setContentPane(mainPanel);        
    }

    private void createSeriousErrorMessageForDialog(JPanel panel) {
        panel.add(new JLabel("Couldn't add cards to hand, because "
                + "the hand already "));        
        panel.add(new JLabel("contained one of the cards you tried to add."));              
        panel.add(new JLabel("This should not happen."));          
        panel.add(new JLabel("Please report this bug by creating"));   
        panel.add(new JLabel("an issue on the GitHub repository."));        
    }

    
    /**
     * Draws the card images to the correct collection.
     * 
     * The method also removes the card images from
     * the Available cards-section.
     * 
     * @param cardsToAdd
     * @param cardDrawer
     * @param howMany
     * @param container
     * @param drawnCards 
     */
    private void drawCardsToCollection(List<Card> cardsToAdd, CardDrawer cardDrawer, 
            int howMany, Container container, Map<Card, Component> drawnCards) {
        for (Card c : cardsToAdd) {
            try {
                JLabel cardLabel = cardDrawer.draw(c, xStart + 30 * howMany, yStart, 0, false);
                cardLabels.add(cardLabel);
            } catch (IOException ex) {
                PicturesNotFoundErrorWindow errorWindow =
                        new PicturesNotFoundErrorWindow(simulationStarter.getDialog(),
                        simulationStarter.getGui());
                errorWindow.create();
            }

            // Removes the card drawn under Available cards.
            container.remove(drawnCards.get(c));
            howMany++;
        }
    }
}
