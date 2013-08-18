package testing;

import card.Card;
import card.CardDeckStandard;
import card.ICardDeck;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import ui.guitools.CardDrawer;

/**
 * Method that draws all cards and writes the string
 * representation of the cards.
 * 
 * Used for testing that the card pictures are named
 * correctly.
 * 
 * @author Sebastian Björkqvist
 */
public class DrawAllCards implements Runnable {

    private JFrame frame;
    private final String pictureDirectory = "pictures/";
    private final String pictureType = ".png";
    private ICardDeck deck;
    private CardDrawer cardDrawer;

    public DrawAllCards() {
        this.deck = new CardDeckStandard(false);
    }    
    
    @Override
    public void run() {
        frame = new JFrame("Drawing all cards");
        frame.setPreferredSize(new Dimension(800, 400));
        
        JLayeredPane contentPanel = new JLayeredPane();

        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        contentPanel.setBorder(padding);

        frame.setContentPane(contentPanel);  
        frame.setLayeredPane(contentPanel);
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            createComponents(frame.getLayeredPane());
        } catch (IOException ex) {
            Logger.getLogger(DrawAllCards.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.pack();
        frame.setVisible(true);      
        
    }

    private void createComponents(Container container) throws IOException {
        this.cardDrawer = new CardDrawer(container, pictureDirectory, pictureType);        
        
        int index = 0;
        while (!deck.isEmpty()) {
            Card c = deck.getCard();
            int xPlace = (index % 13)*50;
            int yPlace = (index / 13)*75 + 25;
            cardDrawer.draw(c, xPlace, yPlace, index);
            
            Insets insets = container.getInsets();
            JLabel text = new JLabel(c.toString());
            container.add(text, index);
            Dimension size = text.getPreferredSize();
            text.setBounds(((index % 13)) * 50 + 5 + insets.left, 85 + 75 * (index / 13) + insets.top,
                    size.width, size.height);   
            index++;            
        }
    }
    
    public static void main(String[] args) {
        DrawAllCards draw = new DrawAllCards();
        SwingUtilities.invokeLater(draw);
    }

}
