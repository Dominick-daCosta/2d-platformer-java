package game.levels;

import city.cs.engine.World;
import city.cs.engine.SoundClip;
import game.knight.Knight;
import game.Game;
import java.awt.Image;


public abstract class GameLevel extends World {

    private Knight knight;
    private Game game;

    // Camera Boundaries
    private boolean leftLocked = false;
    private float leftBoundary = 0f;
    private boolean rightLocked = false;
    private float rightBoundary = 0f;

    protected SoundClip backgroundMusic;


    public GameLevel(Game game) {
        super();
        this.game = game;
        knight = new Knight(this); // Brings the Knight in to the level
    }

    // Gets what Level you are currently in
    public Game getGame() {
        return game;
    }


    // GET and SET Camera Boundaries
    public void setLeftBoundary(float leftBoundary) {
        this.leftLocked = true;
        this.leftBoundary = leftBoundary;
    }

    public void setRightBoundary(float rightBoundary) {
        this.rightLocked = true;
        this.rightBoundary = rightBoundary;
    }

    public boolean isLeftLocked() {
        return leftLocked;
    }

    public float getLeftBoundary() {
        return leftBoundary;
    }

    public boolean isRightLocked() {
        return rightLocked;
    }

    public float getRightBoundary() {
        return rightBoundary;
    }


    public Knight getKnight() {
        return knight;
    }

    public abstract void populate(); // Where the knight starts

    public abstract Image getBackground();

    public abstract boolean isDashUnlocked();

    public void startMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.loop();
        }
    }

    public void stopMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }

}