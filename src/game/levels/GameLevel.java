package game.levels;

import city.cs.engine.World;
import game.knight.Knight;
import game.Game;


public abstract class GameLevel extends World {

    private Knight knight;
    private Game game;

    // Camera Boundaries
    private boolean leftLocked = false;
    private float leftBoundary = 0f;
    private boolean rightLocked = false;
    private float rightBoundary = 0f;


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

}