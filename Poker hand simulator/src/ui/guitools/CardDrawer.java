package ui.guitools;

import card.Card;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Sebastian Björkqvist
 */
public class CardDrawer {

    private Container container;
    private String pictureDirectory;
    private String pictureType;

    public CardDrawer(Container container, String pictureDirectory, String pictureType) {
        this.container = container;
        this.pictureDirectory = pictureDirectory;
        this.pictureType = pictureType;
    }

    /**
     * Draws a card to the given container.
     *
     * @param card The card to draw
     * @param x Place of image on the x-axis
     * @param y Place of image on the y-axis
     * @param index Where to put the image in the container. Used to determine
     * which image will be visible if two images are on top of each other
     * @param grey if true, draws the grey version of the card
     *
     * @return The JLabel-object in which the image is placed.
     *
     * @throws IOException If the card picture isn't found.
     */
    public JLabel draw(Card card, int x, int y, int index, boolean grey) throws IOException {
        String fileName;
        if (grey) {
            fileName = pictureDirectory + card.toString() + "_grey" + pictureType;
        } else {
            fileName = pictureDirectory + card.toString() + pictureType;
        }
        BufferedImage picture = ImageIO.read(new File(fileName));
        JLabel pictureLabel = new JLabel(new ImageIcon(picture));

        container.add(pictureLabel, index);

        Insets insets = container.getInsets();
        Dimension size = pictureLabel.getPreferredSize();

        pictureLabel.setBounds(x + insets.left, y + insets.top, size.width, size.height);

        return pictureLabel;
    }
}
