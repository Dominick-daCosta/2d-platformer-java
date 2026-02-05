package game.crawler;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class CrawlerController implements StepListener {
    private Crawler crawler;

    // Movement settings
    private float speed = 2.0f;
    private float leftBound;
    private float rightBound;
    private boolean movingRight = true;


    public CrawlerController(Crawler crawler, float patrolRange) {
        this.crawler = crawler;

        // Calculate Boundries on where it can walk
        float startX = crawler.getPosition().x;
        this.leftBound = startX - patrolRange;
        this.rightBound = startX + patrolRange;

        crawler.startWalking(speed);
    }



    @Override
    public void preStep(StepEvent stepEvent) {

        if (movingRight && crawler.getPosition().x > rightBound) {
            movingRight = false;
            crawler.startWalking(-speed);
        }

        else if (!movingRight && crawler.getPosition().x < leftBound) {
            movingRight = true;
            crawler.startWalking(speed);
        }


        // Flip Image

        BodyImage img = crawler.getImages().get(0).getBodyImage();
        crawler.removeAllImages();

        AttachedImage newImg = crawler.addImage(img);

        float yOffset = 0.1f;
        newImg.setOffset(new Vec2(0, yOffset));

        // Flip if moving left
        if (movingRight) {
            newImg.flipHorizontal();
        }



    }




    @Override
    public void postStep(StepEvent stepEvent) {
    }
}