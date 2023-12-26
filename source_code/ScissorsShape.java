import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

/**
 * ScissorsShape represents the Scissors character in the RPSKL game.
 *
 * @author Nikola Desnica (ndd2131)
 */
public class ScissorsShape implements CharacterShape {

    /**
     * Constructor for ScissorsShape.
     *
     * @param position The initial position of the Scissors.
     */
    public ScissorsShape(Point position) {
        this.position = position;
        this.image = new ImageIcon("scissors.png").getImage();
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, position.x, position.y, STANDARD_WIDTH, STANDARD_HEIGHT, null);
    }

    @Override
    public void move() {
        // Scissors move from left to right
        position.x += MOVEMENT_SPEED;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, STANDARD_WIDTH, STANDARD_HEIGHT);
    }

    @Override
    public String getType() {
        return "scissors";
    }

    private Point position;
    private Image image;
    private static final int STANDARD_WIDTH = 100;
    private static final int STANDARD_HEIGHT = 100;
    private static final int MOVEMENT_SPEED = 3;
}
