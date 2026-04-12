package game.levels;

import city.cs.engine.*;
import game.crawler.Crawler;
import game.crawler.CrawlerController;
import game.knight.KnightCollision;
import game.levels.objects.Spikes;
import org.jbox2d.common.Vec2;
import java.awt.Image;

public class Level2 extends GameLevel{

    public Level2(game.Game game) {
        super(game);
        try {
            backgroundMusic = new SoundClip("data/sounds/level 2.mp3");
        } catch (Exception e) {
            System.out.println("Error loading Level 2 music: " + e);
        }
    }

    @Override
    public void populate() {

        Vec2 startPosition = new Vec2(0f, 50f); // Knight starting position

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

        // First room

        // Big wall to the left
        for (int i = 0; i < 10; i++) {
            StaticBody wall = new StaticBody(this, wallShape);
            wall.setPosition(new Vec2(-25f, -10f + (i * 10f)));
            wall.addImage(new BodyImage("data/images/level 2/wall Left.png", 10.1f)).setOffset(new Vec2(0.4f, 0f));
        }



        //Floor
        for (float x = -20f; x <= 40f; x += 20f) {
            StaticBody ground = new StaticBody(this, groundShape);
            ground.setPosition(new Vec2(x, -13.5f));
            ground.addImage(new BodyImage("data/images/level 2/floor.png", 3f)).setOffset(new Vec2(0f, 0f));
        }


        // Big wall to the right
        for (int i = 0; i < 10; i++) {
            StaticBody wall = new StaticBody(this, wallShape);
            wall.setPosition(new Vec2(15f, 0f + (i * 10f)));
            wall.addImage(new BodyImage("data/images/level 2/wall Right.png", 10.1f)).setOffset(new Vec2(-0.6f, 0f));
        }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // Spikes
        for (float x : new float[]{60, 100, 140, 160, 180, 200}) {
            Spikes spikes = new Spikes(this);
            spikes.setPosition(new Vec2(x, -14f));
            spikes.addImage(new BodyImage("data/images/level 2/spikes.png", 3f)).setOffset(new Vec2(0f, 0.2f));
        }

        for (float x : new float[]{80, 120}) {
            StaticBody ground = new StaticBody(this, groundShape);
            ground.setPosition(new Vec2(x, -13.5F));
            ground.addImage(new BodyImage("data/images/level 2/floor.png", 3f)).setOffset(new Vec2(0f, 0.1f));
        }

        // Platforms over spikes

        StaticBody platform1 = new StaticBody(this, shortPlatform);
        platform1.setPosition(new Vec2(59f, -6f));
        platform1.addImage(new BodyImage("data/images/level 2/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        StaticBody platform2 = new StaticBody(this, shortPlatform);
        platform2.setPosition(new Vec2(137f, -6f));
        platform2.addImage(new BodyImage("data/images/level 2/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        StaticBody platform3 = new StaticBody(this, shortPlatform);
        platform3.setPosition(new Vec2(155f, -6f));
        platform3.addImage(new BodyImage("data/images/level 2/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        StaticBody boxPlatform1 = new StaticBody(this, rectanglePlatform);
        boxPlatform1.setPosition(new Vec2(161f, 2f));
        boxPlatform1.addImage(new BodyImage("data/images/level 2/rectangle platform.png", 5f)).setOffset(new Vec2(0f, 0.4f));

        StaticBody platform4 = new StaticBody(this, shortPlatform);
        platform4.setPosition(new Vec2(167f, -6f));
        platform4.addImage(new BodyImage("data/images/level 2/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        StaticBody squarePlatform1 = new StaticBody(this, squarePlatform);
        squarePlatform1.setPosition(new Vec2(175f, 0f));
        squarePlatform1.addImage(new BodyImage("data/images/level 2/square platform.png", 3f)).setOffset(new Vec2(0f, 0.4f));

        StaticBody squarePlatform2 = new StaticBody(this, squarePlatform);
        squarePlatform2.setPosition(new Vec2(151f, 10f));
        squarePlatform2.addImage(new BodyImage("data/images/level 2/square platform.png", 3f)).setOffset(new Vec2(0f, 0.4f));

        StaticBody squarePlatform3 = new StaticBody(this, squarePlatform);
        squarePlatform3.setPosition(new Vec2(161f, 18f));
        squarePlatform3.addImage(new BodyImage("data/images/level 2/square platform.png", 3f)).setOffset(new Vec2(0f, 0.4f));

        StaticBody platform5 = new StaticBody(this, shortPlatform);
        platform5.setPosition(new Vec2(174f, 21f));
        platform5.addImage(new BodyImage("data/images/level 2/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        StaticBody platform6 = new StaticBody(this, shortPlatform);
        platform6.setPosition(new Vec2(182f, 21f));
        platform6.addImage(new BodyImage("data/images/level 2/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        // Next Level Teleport

        Shape exitShape = new BoxShape(5f, 5f);
        StaticBody exitDoor = new StaticBody(this, exitShape);
        exitDoor.addImage(new BodyImage("data/images/level 2/level End.png", 5f));

        exitDoor.setPosition(new Vec2(180.0f, 25f));
        exitDoor.addCollisionListener(new LevelExitListener(getGame()));

        // Enemies
        Crawler enemy1 = new Crawler(this);
        enemy1.setPosition(new Vec2(25.0f, 18.0f));
        CrawlerController enemy1AI = new CrawlerController(enemy1, 5.0f);
        this.addStepListener(enemy1AI);

        Crawler enemy2 = new Crawler(this);
        enemy2.setPosition(new Vec2(59.0f, 0.0f));
        CrawlerController enemy2AI = new CrawlerController(enemy2, 2f);
        this.addStepListener(enemy2AI);
    }

    @Override
    public Image getBackground() {
        return new javax.swing.ImageIcon("data/images/level 2/background.png").getImage();
    }

    @Override
    public boolean isDashUnlocked() {
        return true;
    }
}

