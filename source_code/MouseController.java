import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.SwingUtilities;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

/**
 * MouseController handles mouse interactions within the BattlefieldComponent.
 * It allows for the creation of character shapes at specific points on the screen
 * via a right-click context menu.
 *
 * @author Nikola Desnica (ndd2131)
 */
public class MouseController extends MouseAdapter {

    /**
     * Constructor for MouseController.
     * Initializes the controller with the given BattlefieldComponent and sets up
     * the right-click popup menu for character creation.
     *
     * @param battlefieldComponent The component to which this controller is attached.
     */
    public MouseController(BattlefieldComponent battlefieldComponent) {
        this.battlefieldComponent = battlefieldComponent;
        createPopupMenu();
    }

    /**
     * Creates a popup menu with options to create each type of character shape.
     */
    private void createPopupMenu() {
        popupMenu = new JPopupMenu();
        String[] types = {"Rock", "Paper", "Scissors", "Spock", "Lizard"};
        for (String type : types) {
            JMenuItem item = new JMenuItem(type);
            item.addActionListener(e -> createAndAddCharacter(lastClickPoint, type));
            popupMenu.add(item);
        }
    }

    /**
     * Overrides the mouseClicked method to display a popup menu on right-click.
     * The popup menu allows for the creation of character shapes at the click location.
     *
     * @param e The MouseEvent to process.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            lastClickPoint = e.getPoint();
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    /**
     * Creates and adds a character shape to the battlefield at the specified point.
     * The type of character is determined by the given type string.
     *
     * @param point The location at which to add the new character.
     * @param type  The type of the character to create.
     */
    protected void createAndAddCharacter(Point point, String type) {
        CharacterShape character;
        switch (type.toLowerCase()) {
            case "rock":
                character = new RockShape(point);
                break;
            case "paper":
                character = new PaperShape(point);
                break;
            case "scissors":
                character = new ScissorsShape(point);
                break;
            case "spock":
                character = new SpockShape(point);
                break;
            case "lizard":
                character = new LizardShape(point);
                break;
            default:
                return; // Invalid type
        }
        battlefieldComponent.addCharacter(character);
    }

    private final BattlefieldComponent battlefieldComponent;
    private JPopupMenu popupMenu;
    /**
     * Stores the last click point for non-random character creation.
     */
    private Point lastClickPoint;
}
