package game.crawler;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class CrawlerController extends CrawlerAssets implements StepListener {

    private Crawler crawler;

    private float speed = 2.0f;
    private float leftBound;
    private float rightBound;
    private boolean movingRight = true;

    private int stepCount = 0;

    public CrawlerController(Crawler crawler, float patrolRange) {
        this.crawler = crawler;

        float startX = crawler.getPosition().x;
        this.leftBound = startX - patrolRange;
        this.rightBound = startX + patrolRange;

        crawler.startWalking(speed);
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void preStep(StepEvent stepEvent) {
        stepCount++;

        // Patrol Logic
        if (movingRight && crawler.getPosition().x > rightBound) {
            movingRight = false;
            crawler.startWalking(-speed);
        }
        else if (!movingRight && crawler.getPosition().x < leftBound) {
            movingRight = true;
            crawler.startWalking(speed);
        }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // ANIMATION LOGIC

        crawler.removeAllImages();
        int frameIndex = (stepCount / 10) % crawlerWalkImages.length; // Calculate which frame to show
        AttachedImage newImg = crawler.addImage(crawlerWalkImages[frameIndex]); // Add the new image from the array

        float yOffset = 0.1f;
        newImg.setOffset(new Vec2(0, yOffset));

        // Flip based on direction facing
        if (movingRight) {
            newImg.flipHorizontal();
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }
}