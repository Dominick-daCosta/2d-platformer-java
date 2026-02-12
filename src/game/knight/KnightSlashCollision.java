package game.knight;

import city.cs.engine.*;
import game.crawler.Crawler;
import org.jbox2d.common.Vec2;

public class KnightSlashCollision implements CollisionListener {

    private Knight knight;
    private String style;

    // We pass the Knight and the Style (e.g., "downSlash") into the listener
    public KnightSlashCollision(Knight knight, String style) {
        this.knight = knight;
        this.style = style;
    }

    @Override
    public void collide(CollisionEvent e) {
        Body other = e.getOtherBody();

        // 1. HIT AN ENEMY
        if (other instanceof Crawler) {
            Crawler enemy = (Crawler) other;
            enemy.takeDamage(1);

            // If we hit an enemy with a DOWN SLASH -> POGO!
            if (style.equals("downSlash")) {
                performPogo();
            }
        }

        // 2. HIT THE FLOOR (Optional)
        // If you want to pogo off the ground/platforms too:
        else if (other instanceof StaticBody && style.equals("downSlash")) {
            performPogo();
        }
    }

    private void performPogo() {
        // Stop current fall instantly
        knight.setLinearVelocity(new Vec2(knight.getLinearVelocity().x, 0));

        // Apply upward boost (Adjust "15" to change bounce height)
        knight.setLinearVelocity(new Vec2(knight.getLinearVelocity().x, 20));
    }
}