import javax.swing.*;
import java.awt.*;

/**
 * MainApplication initializes and runs the RPSKL game application.
 * It sets up the main window and initializes the game components.
 *
 * @author Nikola Desnica (ndd2131)
 */
public class MainApplication {

    /**
     * Main method to launch the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApplication app = new MainApplication();
            app.initializeComponents();
        });
    }

    /**
     * Initializes the components of the application.
     */
    private void initializeComponents() {
        mainFrame = new JFrame("RPSKL Game");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        battlefieldComponent = new BattlefieldComponent();
        mainFrame.add(battlefieldComponent);

        JPanel buttonPanel = createButtonPanel();
        mainFrame.add(buttonPanel, BorderLayout.NORTH);

        mouseController = new MouseController(battlefieldComponent);
        battlefieldComponent.addMouseListener(mouseController);

        mainFrame.setVisible(true);
    }

    /**
     * Creates a panel with buttons for spawning shapes.
     *
     * @return JPanel with configured buttons.
     */
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] types = {"Rock", "Paper", "Scissors", "Spock", "Lizard"};
        for (String type : types) {
            JButton button = new JButton(type);
            button.addActionListener(e -> createAndAddRandomCharacter(type));
            buttonPanel.add(button);
        }
        return buttonPanel;
    }

    /**
     * Creates and adds a character at a random location.
     *
     * @param type The type of character to create.
     */
    private void createAndAddRandomCharacter(String type) {
        Point randomPoint = getRandomPointInBattlefield();
        mouseController.createAndAddCharacter(randomPoint, type);
    }

    /**
     * Generates a random point within the bounds of the battlefield.
     *
     * @return A random Point object.
     */
    private Point getRandomPointInBattlefield() {
        int x = (int)(Math.random() * (battlefieldComponent.getWidth()));
        int y = (int)(Math.random() * (battlefieldComponent.getHeight()));
        return new Point(x, y);
    }

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private JFrame mainFrame;
    private BattlefieldComponent battlefieldComponent;
    private MouseController mouseController;
}