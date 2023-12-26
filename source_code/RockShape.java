import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

/**
 * RockShape represents the Rock character in the RPSKL game.
 *
 * @author Nikola Desnica (ndd2131)
 */
public class RockShape implements CharacterShape {

    /**
     * Constructor for RockShape.
     *
     * @param position The initial position of the Rock.
     */
    public RockShape(Point position) {
        this.position = position;
        this.image = new ImageIcon("rock.png").getImage();
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, position.x, position.y, STANDARD_WIDTH, STANDARD_HEIGHT, null);
    }

    @Override
    public void move() {
        // Rock does not move
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, STANDARD_WIDTH, STANDARD_HEIGHT);
    }

    @Override
    public String getType() {
        return "rock";
    }

    private Point position;
    private Image image;
    private static final int STANDARD_WIDTH = 100;
    private static final int STANDARD_HEIGHT = 100;
}