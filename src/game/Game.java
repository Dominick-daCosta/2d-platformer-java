package game;

import javax.swing.JFrame;
import java.awt.Color;
import game.levels.GameLevel;
import game.levels.Level1;
import game.knight.*;
import game.levels.Level2;
import game.levels.Level3;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Game {

    private GameLevel currentLevel;
    private GameView view;
    private JFrame frame;
    private boolean gameWon = false;

    // Tutorial State
    private String tutorialMessage = null;
    private int tutorialCountdown = 0;
    private boolean lookDownTriggered = false;
    private boolean moveTutorialTriggered = false;
    private boolean attackTutorialTriggered = false;
    private boolean dashTutorialTriggered = false;
    private boolean jumpTutorialTriggered = false;
    private boolean healingTutorialTriggered = false;

    public Game() {
        // Window Setup
        frame = new JFrame("Dominicks Amazing project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);

        // Start with the Menu
        showMenu();

        frame.setVisible(true);
    }

    public void showMenu() {
        frame.getContentPane().removeAll();
        frame.add(new MenuView(this));
        frame.pack();
        frame.revalidate();
        frame.repaint();
    }

    public void startGame() {
        // Clear Menu
        frame.getContentPane().removeAll();

        // Initialise level
        currentLevel = new Level1(this);
        currentLevel.setGravity(60.0f);
        currentLevel.populate();

        // Create The View
        view = new GameView(this, currentLevel, currentLevel.getKnight(), 1024, 567);
        view.setBackground(Color.LIGHT_GRAY);

        // Knight Controller
        KnightController controller = new KnightController(currentLevel.getKnight());
        controller.setDashUnlocked(currentLevel.isDashUnlocked());
        view.addKeyListener(controller);
        view.addMouseListener(controller);
        currentLevel.addStepListener(controller);

        addTutorialTracker(currentLevel);

        // Camera Tracker
        CameraTracker tracker = new CameraTracker(view, currentLevel.getKnight(), controller);

        if (currentLevel.isLeftLocked()) {
            tracker.setLeftBoundary(currentLevel.getLeftBoundary());
        }

        if (currentLevel.isRightLocked()) {
            tracker.setRightBoundary(currentLevel.getRightBoundary());
        }

        currentLevel.addStepListener(tracker);
        tracker.update(); // Sync camera immediately

        // Add View to Frame
        frame.add(view);
        frame.pack();
        frame.revalidate();
        frame.repaint();
        view.requestFocus();

        // --- DEVELOPER TOOL: CLICK TO GET COORDINATES ---
        view.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                Vec2 worldPoint = view.viewToWorld(e.getPoint());
                System.out.println(".setPosition(new Vec2(" + worldPoint.x + "f, " + worldPoint.y + "f));");
            }
        });

        currentLevel.start(); // Start the level
        currentLevel.startMusic();
    }


    public void goToNextLevel() {

        // Memory of current stats
        int currentHealth = currentLevel.getKnight().knightGetHealth();
        int currentSoul = currentLevel.getKnight().getSoul();

        if (currentLevel instanceof game.levels.Level1) {
            currentLevel.stopMusic();
            currentLevel.stop();
            currentLevel = new game.levels.Level2(this);
        } else if (currentLevel instanceof game.levels.Level2) {
            currentLevel.stopMusic();
            currentLevel.stop();
            currentLevel = new game.levels.Level3(this);
        } else if (currentLevel instanceof game.levels.Level3) {
            currentLevel.stopMusic();
            KnightAssets.stopAllSounds();
            SharedAssets.stopAllSounds();
            currentLevel.stop();
            gameWon = true;

            // Stop inputs
            for(java.awt.event.KeyListener kl : view.getKeyListeners()) { view.removeKeyListener(kl); }
            for(java.awt.event.MouseListener ml : view.getMouseListeners()) { view.removeMouseListener(ml); }

            return;
        } else {
            return;
        }

        currentLevel.setGravity(60.0f);
        currentLevel.populate();

        // Set new stats
        currentLevel.getKnight().setHealth(currentHealth);
        currentLevel.getKnight().setSoul(currentSoul);

        view.setWorld(currentLevel); // sets Camera to next level

        // Creates a new knight controller
        KnightController controller = new KnightController(currentLevel.getKnight());
        controller.setDashUnlocked(currentLevel.isDashUnlocked());
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
        tracker.update(); // Sync camera immediately

        addTutorialTracker(currentLevel);

        currentLevel.start(); // Start the new level
        currentLevel.startMusic();
    }

    private void addTutorialTracker(GameLevel level) {
        level.addStepListener(new StepListener() {
            @Override
            public void preStep(StepEvent e) {}

            @Override
            public void postStep(StepEvent e) {
                // Trigger logic
                if (level instanceof Level3 && !lookDownTriggered) {
                    if (level.getKnight().getPosition().y < 99f) {
                        tutorialMessage = "press 's' to look down";
                        tutorialCountdown = 120; // 2 seconds
                        lookDownTriggered = true;
                    }
                }

                if (level instanceof Level1 && !moveTutorialTriggered) {
                    if (level.getKnight().isControlsEnabled()) {
                        tutorialMessage = "Use \"a\" and \"d\" to move ";
                        tutorialCountdown = 120; // 2 seconds
                        moveTutorialTriggered = true;
                    }
                }

                if (level instanceof Level1 && !attackTutorialTriggered) {
                    if (level.getKnight().getPosition().x > -10f) {
                        tutorialMessage = "Left Click to Attack ";
                        tutorialCountdown = 120; // 2 seconds
                        attackTutorialTriggered = true;
                    }
                }

                if (level instanceof Level1 && !jumpTutorialTriggered) {
                    if (level.getKnight().getPosition().x > 140f) {
                        tutorialMessage = "Press the \"space\" bar to Jump";
                        tutorialCountdown = 120; // 2 seconds
                        jumpTutorialTriggered = true;
                    }
                }

                if (level instanceof Level2 && !dashTutorialTriggered) {
                    if (level.getKnight().isControlsEnabled()) {
                        tutorialMessage = "Right Click to dash";
                        tutorialCountdown = 120; // 2 seconds
                        dashTutorialTriggered = true;
                    }
                }

                // Healing tutorial (any level)
                if (!healingTutorialTriggered && !level.getKnight().isDead()) {
                    if (level.getKnight().knightGetHealth() < 5 && level.getKnight().getSoul() > 0) {
                        tutorialMessage = "Press the Shift key to \"Focus\" and gain health";
                        tutorialCountdown = 120; // 2 seconds
                        healingTutorialTriggered = true;
                    }
                }

                // Timer logic
                if (tutorialCountdown > 0) {
                    tutorialCountdown--;
                    if (tutorialCountdown == 0) {
                        tutorialMessage = null;
                    }
                }
            }
        });
    }


    public boolean isGameWon() {
        return gameWon;
    }

    public String getTutorialMessage() {
        return tutorialMessage;
    }


    public static void main(String[] args) {
        new Game();
    }
}