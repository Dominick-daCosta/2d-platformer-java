package game.levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.crawler.Crawler;
import game.crawler.CrawlerController;
import game.knight.KnightCollision;
import game.levels.objects.BreakableWall;
import game.levels.objects.Spikes;
import org.jbox2d.common.Vec2;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Level1 extends GameLevel {

    public Level1(game.Game game) {
        super(game);
        try {
            backgroundMusic = new SoundClip("data/sounds/level 1.mp3");
        } catch (Exception e) {
            System.out.println("Error loading Level 1 music: " + e);
        }
    }

    @Override
    public void populate() {

        Vec2 startPosition = new Vec2(0, 40f);

        setLeftBoundary(0.0f); // Camera Boundary

        getKnight().setPosition(new Vec2(startPosition));
        getKnight().setRespawnPosition(startPosition);

        getKnight().addCollisionListener(new KnightCollision(getKnight()));

        // Ground & Platforms

        // Ground
        Shape groundShape = new BoxShape(10F, 1.0F);

        // Wall
        Shape wallShape = new BoxShape(1F, 5F);

        // Celling
        Shape cellingShape = new BoxShape(5F, 1F);


        // First room

        // Big wall to the left
        for (int i = 0; i < 10; i++) { // Loop to avoid massive block of text
            StaticBody wall = new StaticBody(this, wallShape);
            wall.setPosition(new Vec2(-25f, -10f + (i * 10f)));
            wall.addImage(new BodyImage("data/images/level 1/wall Left.png", 10.1f)).setOffset(new Vec2(0.4f, 0f));
        }


        //Floor
        for (float x = -20f; x <= 300f; x += 20f) {
            StaticBody ground = new StaticBody(this, groundShape);
            ground.setPosition(new Vec2(x, -13.5f));
            ground.addImage(new BodyImage("data/images/level 1/floor.png", 3f)).setOffset(new Vec2(0f, 0f));
        }


        // Big wall to the right
        for (int i = 0; i < 10; i++) {
            StaticBody wall = new StaticBody(this, wallShape);
            wall.setPosition(new Vec2(30f, 0f + (i * 10f)));
            wall.addImage(new BodyImage("data/images/level 1/wall Right.png", 10.1f)).setOffset(new Vec2(-0.6f, 0f));
        }




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Corridor

        // Corridor Celling
        for (float x = 35f; x <= 65f; x += 10f) {
            StaticBody celling = new StaticBody(this, cellingShape);
            celling.setPosition(new Vec2(x, -4f));
            celling.addImage(new BodyImage("data/images/level 1/celling.png", 2.1f)).setOffset(new Vec2(0f, -0.2f));
        }

        // Corridor breakable walls

        BreakableWall fragileWall1 = new BreakableWall(this);
        fragileWall1.setPosition(new Vec2(30.0f, -8f));
        fragileWall1.addImage(new BodyImage("data/images/level 1/broken Wall.png", 10f)).setOffset(new Vec2(-0.6f, 0f));

        BreakableWall fragileWall2 = new BreakableWall(this);
        fragileWall2.setPosition(new Vec2(50.0f, -8f));
        fragileWall2.addImage(new BodyImage("data/images/level 1/broken Wall.png", 10f)).setOffset(new Vec2(-0.6f, 0f));

        BreakableWall fragileWall3 = new BreakableWall(this);
        fragileWall3.setPosition(new Vec2(70.0f, -8f));
        fragileWall3.addImage(new BodyImage("data/images/level 1/broken Wall.png", 10f)).setOffset(new Vec2(-0.6f, 0f));

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Crawler room
        StaticBody leftWall21 = new StaticBody(this, wallShape);
        leftWall21.setPosition(new Vec2(70f, 0f));
        leftWall21.addImage(new BodyImage("data/images/level 1/wall Left.png", 10.1f)).setOffset(new Vec2(0.4f, 0f));

        for (float x = 75f; x <= 95f; x += 10f) {
            StaticBody celling = new StaticBody(this, cellingShape);
            celling.setPosition(new Vec2(x, 4f));
            celling.addImage(new BodyImage("data/images/level 1/celling.png", 2.1f)).setOffset(new Vec2(0f, -0.2f));
        }

        StaticBody wall22 = new StaticBody(this, wallShape);
        wall22.setPosition(new Vec2(100f, 0f));
        wall22.addImage(new BodyImage("data/images/level 1/wall Right.png", 10.1f)).setOffset(new Vec2(-0.6f, 0f));

        BreakableWall fragileWall4 = new BreakableWall(this);
        fragileWall4.setPosition(new Vec2(100f, -8f));
        fragileWall4.addImage(new BodyImage("data/images/level 1/broken Wall.png", 10f)).setOffset(new Vec2(-0.6f, 0f));


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // 2nd Corridor

        // 2nd Corridor Celling
        for (float x = 105f; x <= 135f; x += 10f) {
            StaticBody celling = new StaticBody(this, cellingShape);
            celling.setPosition(new Vec2(x, -4f));
            celling.addImage(new BodyImage("data/images/level 1/celling.png", 2.1f)).setOffset(new Vec2(0f, -0.2f));
        }

        // 2nd Corridor breakable walls
        BreakableWall fragileWall5 = new BreakableWall(this);
        fragileWall5.setPosition(new Vec2(120.0f, -8f));
        fragileWall5.addImage(new BodyImage("data/images/level 1/broken Wall.png", 10f)).setOffset(new Vec2(-0.6f, 0f));

        BreakableWall fragileWall6 = new BreakableWall(this);
        fragileWall6.setPosition(new Vec2(140.0f, -8f));
        fragileWall6.addImage(new BodyImage("data/images/level 1/broken Wall.png", 10f)).setOffset(new Vec2(-0.6f, 0f));


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // 1st Platforming room

        StaticBody leftWall23 = new StaticBody(this, wallShape);
        leftWall23.setPosition(new Vec2(140f, 0f));
        leftWall23.addImage(new BodyImage("data/images/level 1/wall Left.png", 10.1f)).setOffset(new Vec2(0.4f, 0f));

        StaticBody leftWall24 = new StaticBody(this, wallShape);
        leftWall24.setPosition(new Vec2(140f, 10f));
        leftWall24.addImage(new BodyImage("data/images/level 1/wall Left.png", 10.1f)).setOffset(new Vec2(0.4f, 0f));

        StaticBody leftWall25 = new StaticBody(this, wallShape);
        leftWall25.setPosition(new Vec2(140f, 20f));
        leftWall25.addImage(new BodyImage("data/images/level 1/wall Left.png", 10.1f)).setOffset(new Vec2(0.4f, 0f));

        StaticBody leftWall27 = new StaticBody(this, wallShape);
        leftWall27.setPosition(new Vec2(140f, 30f));
        leftWall27.addImage(new BodyImage("data/images/level 1/wall Left.png", 10.1f)).setOffset(new Vec2(0.4f, 0f));

        StaticBody leftWall28 = new StaticBody(this, wallShape);
        leftWall28.setPosition(new Vec2(1140f, 40f));
        leftWall28.addImage(new BodyImage("data/images/level 1/wall Left.png", 10.1f)).setOffset(new Vec2(0.4f, 0f));


        // 1st platforming room platforms

        Shape shortPlatform = new BoxShape(3.5F, 0.5F);
        StaticBody platform1 = new StaticBody(this, shortPlatform);
        platform1.setPosition(new Vec2(165f, -5f));
        platform1.addImage(new BodyImage("data/images/level 1/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));


        StaticBody platform2 = new StaticBody(this, shortPlatform);
        platform2.setPosition(new Vec2(152f, 0f));
        platform2.addImage(new BodyImage("data/images/level 1/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        StaticBody platform3 = new StaticBody(this, shortPlatform);
        platform3.setPosition(new Vec2(165f, 8f));
        platform3.addImage(new BodyImage("data/images/level 1/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        Shape rectanglePlatform = new BoxShape(2F, 3F);
        StaticBody boxPlatform1 = new StaticBody(this, rectanglePlatform);
        boxPlatform1.setPosition(new Vec2(177f, 10f));
        boxPlatform1.addImage(new BodyImage("data/images/level 1/rectangle platform.png", 5f)).setOffset(new Vec2(0f, 0.4f));

        Shape squarePlatform = new BoxShape(2F, 2F);
        StaticBody squarePlatform1 = new StaticBody(this, squarePlatform);
        squarePlatform1.setPosition(new Vec2(165f, 19f));
        squarePlatform1.addImage(new BodyImage("data/images/level 1/square platform.png", 3f)).setOffset(new Vec2(0f, 0.4f));

        StaticBody wall29 = new StaticBody(this, wallShape);
        wall29.setPosition(new Vec2(185f, -10f));
        wall29.addImage(new BodyImage("data/images/level 1/wall Right.png", 10.1f)).setOffset(new Vec2(-0.6f, 0f));

        StaticBody wall30 = new StaticBody(this, wallShape);
        wall30.setPosition(new Vec2(185f, 0f));
        wall30.addImage(new BodyImage("data/images/level 1/wall Right.png", 10.1f)).setOffset(new Vec2(-0.6f, 0f));

        StaticBody wall31 = new StaticBody(this, wallShape);
        wall31.setPosition(new Vec2(185f, 10f));
        wall31.addImage(new BodyImage("data/images/level 1/wall Right.png", 10.1f)).setOffset(new Vec2(-0.6f, 0f));

        StaticBody wall32 = new StaticBody(this, wallShape);
        wall32.setPosition(new Vec2(185f, 20f));
        wall32.addImage(new BodyImage("data/images/level 1/wall Right.png", 10.1f)).setOffset(new Vec2(-0.6f, 0f));

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Platforming Corridor

        StaticBody ground18 = new StaticBody(this, groundShape);
        ground18.setPosition(new Vec2(185f, 25f));
        ground18.addImage(new BodyImage("data/images/level 1/floor.png", 3f)).setOffset(new Vec2(0f, 0f));

        StaticBody ground19 = new StaticBody(this, groundShape);
        ground19.setPosition(new Vec2(195f, 25f));
        ground19.addImage(new BodyImage("data/images/level 1/floor.png", 3f)).setOffset(new Vec2(0f, 0f));

        StaticBody ground20 = new StaticBody(this, groundShape);
        ground20.setPosition(new Vec2(205f, 25f));
        ground20.addImage(new BodyImage("data/images/level 1/floor.png", 3f)).setOffset(new Vec2(0f, 0f));

        StaticBody ground21 = new StaticBody(this, groundShape);
        ground21.setPosition(new Vec2(225f, 25f));
        ground21.addImage(new BodyImage("data/images/level 1/floor.png", 3f)).setOffset(new Vec2(0f, 0f));

        // Spikes
        for (float x : new float[]{245f, 265f, 285f, 320f, 325f, 345f}) {
            Spikes spikes = new Spikes(this);
            spikes.setPosition(new Vec2(x, 23f));
            spikes.addImage(new BodyImage("data/images/level 1/spikes.png", 3f)).setOffset(new Vec2(0f, 0f));
        }



        // Platforming Corridor Platforms
        StaticBody squarePlatform2 = new StaticBody(this, squarePlatform);
        squarePlatform2.setPosition(new Vec2(240f, 32f));
        squarePlatform2.addImage(new BodyImage("data/images/level 1/square platform.png", 3f)).setOffset(new Vec2(0f, 0.4f));

        StaticBody rectanglePlatform2 = new StaticBody(this, rectanglePlatform);
        rectanglePlatform2.setPosition(new Vec2(257f, 35f));
        rectanglePlatform2.addImage(new BodyImage("data/images/level 1/rectangle platform.png", 5f)).setOffset(new Vec2(0f, 0.4f));

        StaticBody squarePlatform3 = new StaticBody(this, squarePlatform);
        squarePlatform3.setPosition(new Vec2(254f, 34f));
        squarePlatform3.addImage(new BodyImage("data/images/level 1/square platform.png", 3f)).setOffset(new Vec2(0f, 0.4f));

        StaticBody platform4 = new StaticBody(this, shortPlatform);
        platform4.setPosition(new Vec2(268f, 30f));
        platform4.addImage(new BodyImage("data/images/level 1/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        StaticBody platform5 = new StaticBody(this, shortPlatform);
        platform5.setPosition(new Vec2(274f, 30f));
        platform5.addImage(new BodyImage("data/images/level 1/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        StaticBody platform6 = new StaticBody(this, shortPlatform);
        platform6.setPosition(new Vec2(287f, 33f));
        platform6.addImage(new BodyImage("data/images/level 1/platform.png", 2f)).setOffset(new Vec2(0f, -0.5f));

        // End Hole

        // End Hole Walls
        float[] yPositions = {19f, 9f, -1f, -11f, -22f};
        for (float y : yPositions) {

            // Left end wall
            StaticBody wallL = new StaticBody(this, wallShape);
            wallL.setPosition(new Vec2(294.1f, y));
            wallL.addImage(new BodyImage("data/images/level 1/wall Left.png", 10.1f)).setOffset(new Vec2(0.4f, 0f));

            // Right end wall
            StaticBody wallR = new StaticBody(this, wallShape);
            wallR.setPosition(new Vec2(310.9f, y));
            wallR.addImage(new BodyImage("data/images/level 1/wall Right.png", 10.1f)).setOffset(new Vec2(-0.6f, 0f));
        }


        // Next Level Teleport
        Shape exitShape = new BoxShape(5f, 5f);
        StaticBody exitDoor = new StaticBody(this, exitShape);
        exitDoor.addImage(new BodyImage("data/images/level 1/level End.png", 5f));

        exitDoor.setPosition(new Vec2(302.0f, 5f));
        exitDoor.addCollisionListener(new LevelExitListener(getGame()));


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Enemies
        Crawler enemy1 = new Crawler(this);
        enemy1.setPosition(new Vec2(84.0f, -10.0f));
        CrawlerController enemy1AI = new CrawlerController(enemy1, 5.0f);
        this.addStepListener(enemy1AI);

        Crawler enemy2 = new Crawler(this);
        enemy2.setPosition(new Vec2(271.0f, 33.0f));
        CrawlerController enemy2AI = new CrawlerController(enemy2, 3.5f);
        this.addStepListener(enemy2AI);
    }

    @Override
    public Image getBackground() {
        return new ImageIcon("data/images/level 1/background.png").getImage();
    }

    @Override
    public boolean isDashUnlocked() {
        return false;
    }


}