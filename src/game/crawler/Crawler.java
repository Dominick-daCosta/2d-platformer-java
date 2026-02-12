package game.crawler;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.List;

public class Crawler extends Walker {

    // Initialise Variables

    private int damage = 1;
    private int health = 2;

    private long lastHitTime = 0;
    private static final int invisFrames = 500;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // HitBoxes
    private static final Shape topShape = new PolygonShape(
            1.15f, 0.1f,   // Top Right
            -0.92f, 0.1f,  // Top Left
            -0.92f, -0.5f,  // Bottom Left
            1.15f, -0.5f    // Bottom Right
    );

    private static final Shape bottomShape = new PolygonShape(
            1.5f, -0.5f,    // Top Right
            -1.47f, -0.5f,  // Top Left
            -1.47f, -1.8f,  // Bottom Left
            1.5f, -1.8f     // Bottom Right
    );

    public Crawler(World world) {
        super(world, bottomShape);
        new SolidFixture(this, topShape);

        BodyImage crawlerImg = new BodyImage("data/crawler sprites/walk 1.png", 4.0f);
        AttachedImage img = this.addImage(crawlerImg);
    }

    // Return Damage it Deals
    public int getDamage() {
        return damage;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Health

    public void takeDamage(int damage) {

        long currentTime = System.currentTimeMillis();

        if (currentTime - lastHitTime <= invisFrames){ // invis frames
            return;
        }

        // Take damage
        health = health - damage;
        lastHitTime = currentTime;


        // Death
        if (health <= 0) {
            die();
        }
    }


    public void die() {
        List<Fixture> fixtures = this.getFixtureList(); // Gets Everything The Crawler has

        // Destroys all of those things
        for (int i = fixtures.size() - 1; i >= 0; i--) {
            Fixture f = fixtures.get(i);
            f.destroy();
        }
    }

    public int crawlerGetHealth() {
        return health;
    }

    public boolean isInvincible(){
        long currentTime = System.currentTimeMillis();
        return (currentTime - lastHitTime) <= invisFrames; // if this statment is true then the method returns true
    }

}