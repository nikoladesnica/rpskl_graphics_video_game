import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

/**
 * LizardShape represents the Lizard character in the RPSKL game.
 *
 * @author Nikola Desnica (ndd2131)
 */
public class LizardShape implements CharacterShape {

    /**
     * Constructor for LizardShape.
     *
     * @param position The initial position of the Lizard.
     */
    public LizardShape(Point position) {
        this.position = position;
        this.image = new ImageIcon("lizard.png").getImage();
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, position.x, position.y, STANDARD_WIDTH, STANDARD_HEIGHT, null);
    }

    @Override
    public void move() {
        // Lizard moves from right to left
        position.x -= MOVEMENT_SPEED;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, STANDARD_WIDTH, STANDARD_HEIGHT);
    }

    @Override
    public String getType() {
        return "lizard";
    }

    private Point position;
    private Image image;
    private static final int STANDARD_WIDTH = 100;
    private static final int STANDARD_HEIGHT = 100;
    private static final int MOVEMENT_SPEED = 3;
}
