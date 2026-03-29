package game.knight;

import city.cs.engine.*;
import game.crawler.Crawler;
import org.jbox2d.common.Vec2;

public class KnightCollision implements CollisionListener {

    private final int knockbackY = 13;
    private final int knockbackX = 15;


    private Knight knight;

    public KnightCollision(Knight knight) {
        this.knight = knight;
    }

    @Override
    public void collide(CollisionEvent e) {

        if (e.getOtherBody() instanceof Crawler) {

            Crawler enemy = (Crawler) e.getOtherBody();
            int damageToTake = enemy.getDamage(); // Checks the damage the enemy does

            if (knight.takeDamage(damageToTake)) { // Makes sure invis frames aren't happening
                // Knockback
                float knockDirection = (knight.getPosition().x < enemy.getPosition().x) ? -knockbackX : knockbackX;
                knight.applyKnockback(new Vec2(knockDirection, knockbackY));
            }
        }
    }
}