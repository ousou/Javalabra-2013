package testing;

import card.Card;
import card.CardDeckStandard;
import card.ICardDeck;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import ui.guitools.CardDrawer;

/**
 * Method that draws all cards and writes the string representation of the
 * cards.
 *
 * Used for testing that the card pictures are named correctly.
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
        frame = new JFrame("All cards");
        frame.setPreferredSize(new Dimension(800, 600));

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
            int xPlace = (index % 8) * 90;
            int yPlace = (index / 8) * 75;
            cardDrawer.draw(c, xPlace, yPlace, index, false);
            cardDrawer.draw(c, xPlace + 45, yPlace, index, true);

            addCardText(c, xPlace, yPlace, index, container);
            index++;
        }
    }

    private void addCardText(Card c, int xPlace, int yPlace, int index, Container container) {
        Insets insets = container.getInsets();
        JLabel text = new JLabel(c.toString());
        container.add(text, index);
        Dimension size = text.getPreferredSize();
        text.setBounds(xPlace + 5 + insets.left, yPlace + 60 + insets.top,
                size.width, size.height);
    }

    public static void main(String[] args) {
        DrawAllCards draw = new DrawAllCards();
        SwingUtilities.invokeLater(draw);
    }
}
