import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import java.util.Arrays;
import javax.swing.Timer;

/**
 * BattlefieldComponent manages and renders the characters on the battlefield.
 *
 * @author Nikola Desnica (ndd2131)
 */
public class BattlefieldComponent extends JComponent {

    /**
     * Constructor for BattlefieldComponent.
     */
    public BattlefieldComponent() {
        characters = new ArrayList<>();
        setupGameLoop();
    }

    /**
     * Sets up the game loop with a timer to regularly update the battlefield.
     * The timer triggers an action event at regular intervals, which leads to
     * the update of the battlefield including moving characters and checking for collisions.
     */
    private void setupGameLoop() {
        int delay = 50; // Milliseconds between timer ticks (update rate)
        timer = new Timer(delay, e -> updateBattlefield());
        timer.start();
    }

    /**
     * Updates the battlefield by moving characters, checking for collisions, and repainting.
     * This method is called on each tick of the timer set up in setupGameLoop.
     */
    private void updateBattlefield() {
        moveCharacters();
        checkForCollisions();
        repaint();
    }

    /**
     * Moves all characters on the battlefield.
     * Each character's move method is called, which updates its position based on its specific movement strategy.
     */
    private void moveCharacters() {
        for (CharacterShape character : characters) {
            character.move();
        }
    }

    /**
     * Adds a new character to the battlefield.
     *
     * @param character The character to be added.
     */
    public void addCharacter(CharacterShape character) {
        characters.add(character);
        repaint();
    }

    /**
     * Paints the component by drawing all characters.
     *
     * @param g The Graphics object used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (CharacterShape character : characters) {
            character.draw(g2);
        }
    }

    /**
     * Checks for collisions between characters and resolves them.
     */
    public void checkForCollisions() {
        for (int i = 0; i < characters.size(); i++) {
            for (int j = i + 1; j < characters.size(); j++) {
                CharacterShape char1 = characters.get(i);
                CharacterShape char2 = characters.get(j);
                if (char1.getBounds().intersects(char2.getBounds())) {
                    resolveCollision(char1, char2);
                }
            }
        }
    }

    /**
     * Resolves a collision between two characters.
     *
     * @param char1 The first character involved in the collision.
     * @param char2 The second character involved in the collision.
     */
    private void resolveCollision(CharacterShape char1, CharacterShape char2) {
        int index1 = validMoves.indexOf(char1.getType());
        int index2 = validMoves.indexOf(char2.getType());

        int outcome = RPSKL_RULES[index1][index2];

        if (outcome == 1) {
            // char1 wins, remove char2
            characters.remove(char2);
        } else if (outcome == -1) {
            // char2 wins, remove char1
            characters.remove(char1);
        }
    }

    /**
     * List of characters present on the battlefield.
     */
    private List<CharacterShape> characters;
    private Timer timer;
    private static final int[][] RPSKL_RULES = {
            {0, -1, 1, -1, 1},
            {1, 0, -1, 1, -1},
            {-1, 1, 0, -1, 1},
            {1, -1, 1, 0, -1},
            {-1, 1, -1, 1, 0}
    };
    private List<String> validMoves = Arrays.asList("rock", "paper", "scissors", "spock", "lizard");
}