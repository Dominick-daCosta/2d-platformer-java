package game.levels.objects;

import city.cs.engine.*;

public class BreakableWall extends StaticBody {
    // Breakable Wall class for fragile environment objects

    private long lastHitTime = 0;
    private static final int invisFrames = 200;

    private static final Shape wallShape = new BoxShape(1.0f, 5.0f);

    private int health = 2;

    // Take Damage
    public void takeDamage(int damage) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastHitTime <= invisFrames) { // invis frames
            return;
        }

        // Take damage
        health = health - damage;
        lastHitTime = currentTime;
    }

    // Health Getter
    public int getHealth() {
        return health;
    }

    public BreakableWall(World world) {
        super(world, wallShape);
    }

}