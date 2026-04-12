package game;

import javax.swing.JFrame;
import java.awt.Color;
import game.levels.GameLevel;
import game.levels.Level1;
import game.knight.*;
import game.levels.Level2;
import org.jbox2d.common.Vec2;

public class Game {

    private GameLevel currentLevel;
    private final GameView view;

    public Game() {

        // Initialise level
        currentLevel = new Level2(this);
        currentLevel.setGravity(60.0f);
        currentLevel.populate();

        // Create The View
        view = new GameView(currentLevel, currentLevel.getKnight(), 1024, 567);
        view.setBackground(Color.LIGHT_GRAY);

        // Knight Controller
        KnightController controller = new KnightController(currentLevel.getKnight());
        view.addKeyListener(controller);
        view.addMouseListener(controller);
        currentLevel.addStepListener(controller);


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Camera Tracker
        CameraTracker tracker = new CameraTracker(view, currentLevel.getKnight(), controller);

        if (currentLevel.isLeftLocked()) {
            tracker.setLeftBoundary(currentLevel.getLeftBoundary());
        }

        if (currentLevel.isRightLocked()) {
            tracker.setRightBoundary(currentLevel.getRightBoundary());
        }

        currentLevel.addStepListener(tracker);


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // Window
        JFrame frame = new JFrame("City Game");
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        view.requestFocus();


        // --- DEVELOPER TOOL: CLICK TO GET COORDINATES ---
        view.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                // This converts the pixels on your screen into physical world coordinates!
                Vec2 worldPoint = view.viewToWorld(e.getPoint());

                // Prints the exact code you need to the console
                System.out.println(".setPosition(new Vec2(" + worldPoint.x + "f, " + worldPoint.y + "f));");
            }
        });


        currentLevel.start(); // Start the level
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void goToNextLevel() {

        if (currentLevel instanceof Level1) {
            // Memory of current stats
            int currentHealth = currentLevel.getKnight().knightGetHealth();
            int currentSoul = currentLevel.getKnight().getSoul();

            currentLevel.stop();

            // Makes the next level
            currentLevel = new game.levels.Level2(this);
            currentLevel.setGravity(60.0f);
            currentLevel.populate();

            // Set new stats
            currentLevel.getKnight().setHealth(currentHealth);
            currentLevel.getKnight().setSoul(currentSoul);

            view.setWorld(currentLevel); // sets Camera to next level

            // Creates a new knight
            KnightController controller = new KnightController(currentLevel.getKnight());
            for(java.awt.event.KeyListener kl : view.getKeyListeners()) { view.removeKeyListener(kl); }
            for(java.awt.event.MouseListener ml : view.getMouseListeners()) { view.removeMouseListener(ml); }

            view.addKeyListener(controller);
            view.addMouseListener(controller);
            currentLevel.addStepListener(controller);

            // Update Camera
            CameraTracker tracker = new CameraTracker(view, currentLevel.getKnight(), controller);
            if (currentLevel.isLeftLocked()) tracker.setLeftBoundary(currentLevel.getLeftBoundary());
            if (currentLevel.isRightLocked()) tracker.setRightBoundary(currentLevel.getRightBoundary());
            currentLevel.addStepListener(tracker);

            currentLevel.start(); // Start the new level

        }
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static void main(String[] args) {
        new Game();
    }
}