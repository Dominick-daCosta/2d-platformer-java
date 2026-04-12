package game.knight;

import city.cs.engine.*;
import game.crawler.Crawler;
import org.jbox2d.common.Vec2;

public class KnightSlashCollision implements CollisionListener {

    // Initialise variables

    private Knight knight;
    private KnightController.State style;

    private final int slashDamage = 1;

    private long lastHitTime = 0;
    private static final int invisFrames = 500;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // COLLISIONS
    public KnightSlashCollision(Knight knight, KnightController.State style) {
        this.knight = knight;
        this.style = style;
    }

    @Override
    public void collide(CollisionEvent e) {
        Body other = e.getOtherBody();

        // Hit Enemy
        if (other instanceof Crawler) {
            Crawler enemy = (Crawler) other;
            enemy.takeDamage(slashDamage);

            // Soul
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastHitTime <= invisFrames){ // invis frames (only get soul when enemy takes damage)
                return;
            }
            knight.gainSoul();
            lastHitTime = currentTime;


            // Pogo on Enemy
            if (style == KnightController.State.downSlash) {
                performPogo();
            }
        }

        // Hit Breakable Wall
        else if (other instanceof game.levels.objects.BreakableWall) {
            // Force IDE re-detection
            game.levels.objects.BreakableWall bWall = (game.levels.objects.BreakableWall) other;
            bWall.takeDamage(slashDamage);

            if (bWall.getHealth() <= 0) {
                other.destroy();
            }
        }

        // Pogo on Floor
        else if (other instanceof StaticBody && style == KnightController.State.downSlash) {
            performPogo();
        }
    }

    private void performPogo() {
        knight.setLinearVelocity(new Vec2(knight.getLinearVelocity().x, 0));
        knight.setLinearVelocity(new Vec2(knight.getLinearVelocity().x, 20));
    }
}