package game.levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.Game;
import game.crawler.Crawler;
import game.crawler.CrawlerController;
import game.knight.KnightCollision;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Level3 extends GameLevel {

    public Level3(Game game) {
        super(game);
        try {
            backgroundMusic = new SoundClip("data/sounds/level 3.mp3");
        } catch (Exception e) {
            System.out.println("Error loading Level 3 music: " + e);
        }
    }


    @Override
    public void populate() {

        Vec2 startPosition = new Vec2(-6f, 150f); // Knight starting position

        setLeftBoundary(-30.0f); // Camera Boundary

        getKnight().setPosition(new Vec2(startPosition));
        getKnight().setRespawnPosition(startPosition);

        getKnight().addCollisionListener(new KnightCollision(getKnight()));

        // --- SHAPES ---
        Shape groundShape = new BoxShape(10F, 1.0F);
        Shape wallShape = new BoxShape(1F, 5F);
        Shape cellingShape = new BoxShape(5F, 1F);
        Shape shortPlatform = new BoxShape(3.5F, 0.5F);
        Shape rectanglePlatform = new BoxShape(2F, 3F);
        Shape squarePlatform = new BoxShape(2F, 2F);


        // FALLING


        // Walls
        for (int i = 0; i < 100; i++) {
            StaticBody wall = new StaticBody(this, wallShape);
            wall.setPosition(new Vec2(-30f, -10f + (i * 10f)));
            wall.addImage(new BodyImage("data/images/level 3/wall Left.png", 10.1f)).setOffset(new Vec2(0.4f, 0f));
        }

        for (int i = 0; i < 100; i++) {
            StaticBody wall = new StaticBody(this, wallShape);
            wall.setPosition(new Vec2(15f, 0f + (i * 10f)));
            wall.addImage(new BodyImage("data/images/level 3/wall Right.png", 10.1f)).setOffset(new Vec2(-0.6f, 0f));
        }


        // Top platform
        StaticBody ground1 = new StaticBody(this, groundShape);
        ground1.setPosition(new Vec2(-20, 100f));
        ground1.addImage(new BodyImage("data/images/level 3/floor.png", 3f)).setOffset(new Vec2(0f, 0f));

        StaticBody ground2 = new StaticBody(this, groundShape);
        ground2.setPosition(new Vec2(0, 100f));
        ground2.addImage(new BodyImage("data/images/level 3/floor.png", 3f)).setOffset(new Vec2(0f, 0f));

        StaticBody platform1 = new StaticBody(this, shortPlatform);
        platform1.setPosition(new Vec2(-18f, 50f));
        platform1.addImage(new BodyImage("data/images/level 3/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        StaticBody platform2 = new StaticBody(this, shortPlatform);
        platform2.setPosition(new Vec2(4f, 50f));
        platform2.addImage(new BodyImage("data/images/level 3/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        StaticBody squarePlatform1 = new StaticBody(this, squarePlatform);
        squarePlatform1.setPosition(new Vec2(-6f, 47f));
        squarePlatform1.addImage(new BodyImage("data/images/level 3/square platform.png", 3f)).setOffset(new Vec2(0f, 0.4f));

        StaticBody boxPlatform1 = new StaticBody(this, rectanglePlatform);
        boxPlatform1.setPosition(new Vec2(-10f, 57f ));
        boxPlatform1.addImage(new BodyImage("data/images/level 3/rectangle platform.png", 5f)).setOffset(new Vec2(0f, 0.4f));

        StaticBody platform3 = new StaticBody(this, shortPlatform);
        platform3.setPosition(new Vec2(3f, 59f));
        platform3.addImage(new BodyImage("data/images/level 3/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        StaticBody boxPlatform2 = new StaticBody(this, rectanglePlatform);
        boxPlatform2.setPosition(new Vec2(-25f, 55f ));
        boxPlatform2.addImage(new BodyImage("data/images/level 3/rectangle platform.png", 5f)).setOffset(new Vec2(0f, 0.4f));

        StaticBody squarePlatform2 = new StaticBody(this, squarePlatform);
        squarePlatform2.setPosition(new Vec2(-17f, 63f));
        squarePlatform2.addImage(new BodyImage("data/images/level 3/square platform.png", 3f)).setOffset(new Vec2(0f, 0.4f));

        StaticBody platform4 = new StaticBody(this, shortPlatform);
        platform4.setPosition(new Vec2(-4f, 65f));
        platform4.addImage(new BodyImage("data/images/level 3/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        StaticBody squarePlatform3 = new StaticBody(this, squarePlatform);
        squarePlatform3.setPosition(new Vec2(-10f, 73f));
        squarePlatform3.addImage(new BodyImage("data/images/level 3/square platform.png", 3f)).setOffset(new Vec2(0f, 0.4f));

        StaticBody boxPlatform3 = new StaticBody(this, rectanglePlatform);
        boxPlatform3.setPosition(new Vec2(0f, 78f ));
        boxPlatform3.addImage(new BodyImage("data/images/level 3/rectangle platform.png", 5f)).setOffset(new Vec2(0f, 0.4f));

        StaticBody platform5 = new StaticBody(this, shortPlatform);
        platform5.setPosition(new Vec2(-14f, 86f));
        platform5.addImage(new BodyImage("data/images/level 3/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        StaticBody platform6 = new StaticBody(this, shortPlatform);
        platform6.setPosition(new Vec2(8f, 71f));
        platform6.addImage(new BodyImage("data/images/level 3/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        StaticBody platform7 = new StaticBody(this, shortPlatform);
        platform7.setPosition(new Vec2(9f, 92f));
        platform7.addImage(new BodyImage("data/images/level 3/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));








        StaticBody ground3 = new StaticBody(this, groundShape);
        ground3.setPosition(new Vec2(-20, 35f));
        ground3.addImage(new BodyImage("data/images/level 3/floor.png", 3f)).setOffset(new Vec2(0f, 0f));

        StaticBody ground4 = new StaticBody(this, groundShape);
        ground4.setPosition(new Vec2(0, 35f));
        ground4.addImage(new BodyImage("data/images/level 3/floor.png", 3f)).setOffset(new Vec2(0f, 0f));

        StaticBody ground5 = new StaticBody(this, groundShape);
        ground5.setPosition(new Vec2(4f, 35f));
        ground5.addImage(new BodyImage("data/images/level 3/floor.png", 3f)).setOffset(new Vec2(0f, 0f));




        // Next Level Teleport
        Shape exitShape = new BoxShape(5f, 5f);
        StaticBody exitDoor = new StaticBody(this, exitShape);
        exitDoor.addImage(new BodyImage("data/images/level 3/level End.png", 5f));

        exitDoor.setPosition(new Vec2(-24.0f, 39f));
        exitDoor.addCollisionListener(new LevelExitListener(getGame()));

        // Enemies
        Crawler enemy1 = new Crawler(this);
        enemy1.setPosition(new Vec2(-15.0f, 89.0f));
        CrawlerController enemy1AI = new CrawlerController(enemy1, 1.0f);
        this.addStepListener(enemy1AI);

        Crawler enemy2 = new Crawler(this);
        enemy2.setPosition(new Vec2(-17.0f, 66.0f));
        CrawlerController enemy2AI = new CrawlerController(enemy2, 1f);
        this.addStepListener(enemy2AI);

        Crawler enemy3 = new Crawler(this);
        enemy3.setPosition(new Vec2(2.0f, 63.0f));
        CrawlerController enemy3AI = new CrawlerController(enemy3, 3f);
        this.addStepListener(enemy3AI);

        Crawler enemy4 = new Crawler(this);
        enemy4.setPosition(new Vec2(-4.0f, 68.0f));
        CrawlerController enemy4AI = new CrawlerController(enemy3, 3f);
        this.addStepListener(enemy4AI);
    }

    @Override
    public Image getBackground() {
        return new javax.swing.ImageIcon("data/images/level 3/background.jpg").getImage();
    }

    @Override
    public boolean isDashUnlocked() {
        return true;
    }
}




