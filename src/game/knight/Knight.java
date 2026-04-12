package game.knight;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Knight extends Walker {

    // Initialise variables

    private Vec2 respawnPosition;
    private boolean isDead = false;
    private boolean controlsEnabled = false;

    private int health;
    private final int maxHealth = 5;

    private int soul = 0;
    private final int maxSoul = 4;

    private long lastHitTime = 0;
    private static final int invisFrames = 500;
    private int stunTimer = 0;

    private int damageSoundIndex = 0;

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Hitbox
    private static final Shape knightHitbox = new PolygonShape(
            -0.66f, -1.60f, // Bottom left side
            -0.14f, -2.6f, // Bottom left foot
            0.32f, -2.6f, // Bottom right foot
            0.72f, -1.66f, // Bottom right side
            0.72f, 0.92f, // Top right
            -0.66f, 0.89f // Top left
    );

    public Knight(World world) {
        super(world); // Call super without shape, we will add it in resetHitbox
        this.health = maxHealth;

        resetHitbox();

        BodyImage knightImg = new BodyImage("data/images/knight sprites/idle 1.png", 6.0f);
        AttachedImage img = this.addImage(knightImg);
        img.setOffset(new Vec2(0, 0.4f));

        this.setPosition(new Vec2(7.0f, -9.0f));
    }

    public void resetHitbox() {
        // Remove existing fixtures if any (to avoid duplicates)
        List<Fixture> fixtures = this.getFixtureList();
        for (int i = fixtures.size() - 1; i >= 0; i--) {
            fixtures.get(i).destroy();
        }

        // Add the main hitbox
        SolidFixture mainFixture = new SolidFixture(this, knightHitbox);
        mainFixture.setFriction(0.01f);
    }

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // HEALTH
    public boolean takeDamage(int damage) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastHitTime <= invisFrames) { // invis frames
            return false;
        }

        // Take Damage
        health = health - damage;
        lastHitTime = currentTime;

        if (health <= 0) {
            die();
        }

        return true; // The hit was successful!
    }

    // Death

    public void die() {
        if (isDead) return; // Already dead
        isDead = true;

        List<Fixture> fixtures = this.getFixtureList(); // Gets Everything Thr Knight has

        // Destroys all of those things
        for (int i = fixtures.size() - 1; i >= 0; i--) {
            Fixture f = fixtures.get(i);
            f.destroy();
        }


        // Allows the death animation to play before respawning
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                respawn();
            }
        });

        timer.setRepeats(false); //Dont allow the timer to repeat
        timer.start();

    }

    public boolean isDead() {
        return isDead;
    }
    
    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int knightGetHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    // Knockback Stun

    public void applyKnockback(Vec2 force) {
        this.setLinearVelocity(force);
        this.stunTimer = 15;

        // Play Sound
        if (KnightAssets.damageSounds[damageSoundIndex] != null) {
            KnightAssets.damageSounds[damageSoundIndex].play();
        }
        damageSoundIndex = (damageSoundIndex + 1) % KnightAssets.damageSounds.length; // Plays the sounds from the array
    }

    public void decrementStunTimer() {
        if (stunTimer > 0) {
            stunTimer--;
        }
    }

    public boolean isStunned() {
        return stunTimer > 0;
    }


    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Soul (Magic power)
    public void gainSoul() {
        if (soul != maxSoul) {
            soul++;
        }
    }

    public int getSoul() {
        return soul;
    }

    public void setSoul(int soul) {
        this.soul = soul;
    }

    public void looseSoul() {
        if (soul > 0) {
            soul--;
        }
    }

    public void gainHealth() {
        if (health < maxHealth) {
            health++;
        }
    }

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Respawn

    public void setRespawnPosition(Vec2 position) {
        this.respawnPosition = position;
    }

    public void respawn() {
        this.isDead = false;
        this.health = maxHealth;
        this.soul = 0;

        resetHitbox();

        // Move to respawn location TODO:
        this.setLinearVelocity(new Vec2(0, 0));
        this.setGravityScale(1.0f);
        this.setPosition(respawnPosition);
        this.controlsEnabled = false;
    }

    public boolean isControlsEnabled() {
        return controlsEnabled;
    }

    public void setControlsEnabled(boolean controlsEnabled) {
        this.controlsEnabled = controlsEnabled;
    }




}