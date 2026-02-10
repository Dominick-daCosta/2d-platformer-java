package game.knight;

import city.cs.engine.*;
import game.crawler.Crawler;

public class KnightCollision implements CollisionListener {

    private Knight knight;

    public KnightCollision(Knight knight) {
        this.knight = knight;
    }

    @Override
    public void collide(CollisionEvent e) {

        if (e.getOtherBody() instanceof Crawler) {

            Crawler enemy = (Crawler) e.getOtherBody();
            int damageToTake = enemy.getDamage(); // Checks the damage the enemy does
            knight.takeDamage(damageToTake);
            knight.jump(20);
        }
    }
}