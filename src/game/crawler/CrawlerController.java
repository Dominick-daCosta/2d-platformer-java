package game.crawler;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import game.SharedAssets;

public class CrawlerController extends CrawlerAssets implements StepListener {

    private Crawler crawler;

    private float speed = 2.0f;
    private float leftBound;
    private float rightBound;
    private boolean movingRight = true;

    private int stepCount = 0;

    private int lastHealth;
    private boolean isDead = false;
    private int deathStartTime = 0;

    private int damageAnimFrame = -1;

    public CrawlerController(Crawler crawler, float patrolRange) {
        this.crawler = crawler;

        this.lastHealth = crawler.crawlerGetHealth();

        float startX = crawler.getPosition().x;
        this.leftBound = startX - patrolRange;
        this.rightBound = startX + patrolRange;

        crawler.startWalking(speed);
    }

    @Override
    public void preStep(StepEvent stepEvent) {

        stepCount++;
        Vec2 velocity = crawler.getLinearVelocity();

        if (crawler.crawlerGetHealth() <= 0) {

            if (!isDead) {
                isDead = true;
                deathStartTime = stepCount;

                crawler.stopWalking();
                crawler.setLinearVelocity(new Vec2(0,0));
                crawler.removeAllCollisionListeners();
            }
        }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // MOVEMENT

        if (!isDead) {
            if (movingRight && crawler.getPosition().x > rightBound) {
                movingRight = false;
                crawler.startWalking(-speed);

            } else if (!movingRight && crawler.getPosition().x < leftBound) {
                movingRight = true;
                crawler.startWalking(speed);
            }
        }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // ANIMATION

        int currentHealth = crawler.crawlerGetHealth();

        if (currentHealth < lastHealth) { // if health has decreesed
            damageAnimFrame = 0;
        }
        lastHealth = currentHealth;


        crawler.removeAllImages();
        AttachedImage baseImg = null;


        if (isDead) { // Dead animation

            // Calculate how long we have been dead
            int timeDead = stepCount - deathStartTime;
            int frameIndex = timeDead / 10;

            // Stop at the last frame
            if (frameIndex >= crawlerDeathImages.length) {
                frameIndex = crawlerDeathImages.length - 1;
            }

            AttachedImage img = crawler.addImage(crawlerDeathImages[frameIndex]);
            img.setOffset(new Vec2(0, 0.1f));

            if (movingRight) img.flipHorizontal();

        } else { // Walk animation


            int walkIndex = (stepCount / 10) % crawlerWalkImages.length;
            AttachedImage img = crawler.addImage(crawlerWalkImages[walkIndex]);

            img.setOffset(new Vec2(0, 0.1f));


            if (movingRight) {
                img.flipHorizontal();
            }
        }


        // Damage effect
        if (baseImg != null) {
            baseImg.setOffset(new Vec2(0, 0.1f));
            if (movingRight) baseImg.flipHorizontal();
        }

        if (damageAnimFrame >= 0 && damageAnimFrame < SharedAssets.damageEffectImages.length) { // while animation frames are playiong

            AttachedImage effectImg = crawler.addImage(SharedAssets.damageEffectImages[damageAnimFrame]); // adds effect on top of current image
            effectImg.setOffset(new Vec2(0, 0.1f));

            damageAnimFrame++;
        }
        else { // stop playing animation when finished
            damageAnimFrame = -1;
        }

    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }
}