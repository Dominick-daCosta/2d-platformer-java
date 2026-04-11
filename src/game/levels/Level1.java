package game.levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.crawler.Crawler;
import game.crawler.CrawlerController;
import game.knight.KnightCollision;
import game.levels.objects.BreakableWall;
import game.levels.objects.Spikes;
import org.jbox2d.common.Vec2;

public class Level1 extends GameLevel {

    @Override
    public void populate() {

        Vec2 startPosition = new Vec2(0f, -10f); // Knight starting position (should be 0,-10)

        setLeftBoundary(0.0f); // Camera Boundary

        getKnight().setPosition(new Vec2(startPosition));
        getKnight().setRespawnPosition(startPosition);

        getKnight().addCollisionListener(new KnightCollision(getKnight()));


        // Ground & Platforms


        // Defining shapes

        // Ground shape
        Shape groundShape = new BoxShape(1000F, 1.0F);
        createTexturedPlatform(0.0f, -13.5f, 1000f, 1.0f, 5.0f, 4.0f,
                0.1f, "data/images/level 1/floor.png");

        // Long Platform
        Shape longPlatform = new BoxShape(13F, 2F);
        createTexturedPlatform(0.0f, -13.5f, 13F, 2F, 5.0f, 4.0f,
                -0.1f, "data/images/level 1/floor.png");

        // Short Platform
        Shape shortPlatform = new BoxShape(3.5F, 0.5F);
        createTexturedPlatform(0.0f, -13.5f, 3.5F, 0.5F, 5.0f, 4.0f,
                0.1f, "data/images/level 1/platform.png");

        // Rectangle Platform
        Shape rectanglePlatform = new BoxShape(2F, 3F);
        createTexturedPlatform(0.0f, -13.5f, 2F, 3F, 5.0f, 4.0f,
                0.1f, "data/images/level 1/rectangle platform.png");

        // Square Platform
        Shape squarePlatform = new BoxShape(2F, 2F);
        createTexturedPlatform(0.0f, -13.5f, 2F, 2F, 5.0f, 4.0f,
                0.1f, "data/images/level 1/square platform.png");

        // Big Wall
        Shape bigWallShape = new BoxShape(1F, 13F);
        createTexturedPlatform(0.0f, -13.5f, 1F, 13F, 5.0f, 4.0f,
                0.1f, "data/images/level 1/wall.png");

        // Long Wall
        Shape longWall = new BoxShape(1F, 10F);
        createTexturedPlatform(0.0f, -13.5f, 1F, 10F, 5.0f, 4.0f,
                0.1f, "data/images/level 1/wall.png");

        // Short Wall
        Shape shortWallShape = new BoxShape(1F, 3F);
        createTexturedPlatform(0.0f, -13.5f, 1F, 3F, 5.0f, 4.0f,
                0.1f, "data/images/level 1/wall.png");

        // Celing
        Shape CelingShape = new BoxShape(21F, 1F);
        createTexturedPlatform(0.0f, -13.5f, 21F, 1F, 5.0f, 4.0f,
                0.1f, "data/images/level 1/celing.png");

        // Platform Room Celing
        Shape platformRoomCelling = new BoxShape(40F, 1F);
        createTexturedPlatform(0.0f, -13.5f, 40F, 1F, 5.0f, 4.0f,
                0.1f, "data/images/level 1/celing.png");

        // Short Celing
        Shape shortCelingShape = new BoxShape(13F, 1F);
        createTexturedPlatform(0.0f, -13.5f, 13F, 1F, 5.0f, 4.0f,
                0.1f, "data/images/level 1/celing.png");


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // First room
        StaticBody ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0.0F, -13.5F));

        StaticBody bigWall = new StaticBody(this, bigWallShape);
        bigWall.setPosition(new Vec2(-25f, 0f));

        StaticBody bigWall0 = new StaticBody(this, bigWallShape);
        bigWall0.setPosition(new Vec2(-25f, 13f));

        StaticBody bigWall1 = new StaticBody(this, bigWallShape);
        bigWall1.setPosition(new Vec2(30f, 8f));

        // Corridor
        StaticBody celling = new StaticBody(this, CelingShape);
        celling.setPosition(new Vec2(50f, -4f));

        BreakableWall fragileWall1 = new BreakableWall(this);
        fragileWall1.setPosition(new Vec2(30.0f, -8f));

        BreakableWall fragileWall2 = new BreakableWall(this);
        fragileWall2.setPosition(new Vec2(50.0f, -8f));

        BreakableWall fragileWall3 = new BreakableWall(this);
        fragileWall3.setPosition(new Vec2(70.0f, -8f));


        // Crawler Room
        StaticBody wall = new StaticBody(this, shortWallShape);
        wall.setPosition(new Vec2(70f, 0f));

        StaticBody celling2 = new StaticBody(this, shortCelingShape);
        celling2.setPosition(new Vec2(83f, 1f));

        StaticBody shortWall1 = new StaticBody(this, shortWallShape);
        shortWall1.setPosition(new Vec2(96f, 0f));

        BreakableWall fragileWall4 = new BreakableWall(this);
        fragileWall4.setPosition(new Vec2(96.0f, -8f));


        // Corridor
        StaticBody celling3 = new StaticBody(this, CelingShape);
        celling3.setPosition(new Vec2(116f, -4f));

        BreakableWall fragileWall5 = new BreakableWall(this);
        fragileWall5.setPosition(new Vec2(116.0f, -8f));

        BreakableWall fragileWall6 = new BreakableWall(this);
        fragileWall6.setPosition(new Vec2(136.0f, -8f));

        StaticBody bigWall2 = new StaticBody(this, bigWallShape);
        bigWall2.setPosition(new Vec2(136f, 8f));


        // First platforming room
        StaticBody bigWall3 = new StaticBody(this, bigWallShape);
        bigWall3.setPosition(new Vec2(185.0f, 0f));

        StaticBody bigWall4 = new StaticBody(this, bigWallShape);
        bigWall4.setPosition(new Vec2(185.0f, 26f));

        StaticBody platform1 = new StaticBody(this, shortPlatform);
        platform1.setPosition(new Vec2(160f, -5f));

        StaticBody platform2 = new StaticBody(this, shortPlatform);
        platform2.setPosition(new Vec2(147f, 0f));

        StaticBody platform3 = new StaticBody(this, shortPlatform);
        platform3.setPosition(new Vec2(160f, 8f));

        StaticBody boxPlatform1 = new StaticBody(this, rectanglePlatform);
        boxPlatform1.setPosition(new Vec2(172f, 10f));

        StaticBody squarePlatform1 = new StaticBody(this, squarePlatform);
        squarePlatform1.setPosition(new Vec2(160f, 19f));

        StaticBody longPlatform1 = new StaticBody(this, longPlatform);
        longPlatform1.setPosition(new Vec2(135f, 19f));

        StaticBody celling4 = new StaticBody(this, platformRoomCelling);
        celling4.setPosition(new Vec2(175f, 35f));

        // Stair Transition to second room
        StaticBody shortWall2 = new StaticBody(this, shortWallShape);
        shortWall2.setPosition(new Vec2(123f, 24f));

        Shape shortFloor = new BoxShape(7F, 1F);
        StaticBody shortFloor1 = new StaticBody(this, shortFloor);
        shortFloor1.setPosition(new Vec2(117f, 27f));

        StaticBody shortWall3 = new StaticBody(this, shortWallShape);
        shortWall3.setPosition(new Vec2(111f, 31f));

        StaticBody shortFloor2 = new StaticBody(this, shortFloor);
        shortFloor2.setPosition(new Vec2(104f, 33f));

        // Celling part of stairs
        StaticBody shortWall4 = new StaticBody(this, shortWallShape);
        shortWall4.setPosition(new Vec2(136f, 37f));

        StaticBody longPlatform2 = new StaticBody(this, longPlatform);
        longPlatform2.setPosition(new Vec2(135f, 42f));

        StaticBody shortWall5 = new StaticBody(this, shortWallShape);
        shortWall5.setPosition(new Vec2(123f, 45f));

        StaticBody shortFloor3 = new StaticBody(this, shortFloor);
        shortFloor3.setPosition(new Vec2(117f, 49f));

        StaticBody shortFloor4 = new StaticBody(this, shortFloor);
        shortFloor4.setPosition(new Vec2(104f, 49f));

        BreakableWall fragileWall7 = new BreakableWall(this);
        fragileWall7.setPosition(new Vec2(98f, 39f));

        // Platforming room 2

        StaticBody bigWall5 = new StaticBody(this, bigWallShape);
        bigWall5.setPosition(new Vec2(98f, 55f));

        StaticBody shortFloor5 = new StaticBody(this, shortFloor);
        shortFloor5.setPosition(new Vec2(90f, 33f));

        StaticBody shortFloor6 = new StaticBody(this, shortFloor);
        shortFloor6.setPosition(new Vec2(80f, 33f));

        StaticBody shortWall7 = new StaticBody(this, shortWallShape);
        shortWall7.setPosition(new Vec2(74f, 29f));

        Spikes spikes1 = new Spikes(this);
        spikes1.setPosition(new Vec2(69f, 29f));

        StaticBody shortWall8 = new StaticBody(this, shortWallShape);
        shortWall8.setPosition(new Vec2(61f, 29f));

        StaticBody shortFloor8 = new StaticBody(this, shortFloor);
        shortFloor8.setPosition(new Vec2(55f, 33f));

        StaticBody bigWall6 = new StaticBody(this, bigWallShape);
        bigWall6.setPosition(new Vec2(47f, 45f));

        StaticBody bigWall7 = new StaticBody(this, bigWallShape);
        bigWall7.setPosition(new Vec2(47f, 70f));

        StaticBody bigWall8 = new StaticBody(this, bigWallShape);
        bigWall8.setPosition(new Vec2(47f, 90f));

        // The Actual platforms
        StaticBody squarePlatform2 = new StaticBody(this, squarePlatform);
        squarePlatform2.setPosition(new Vec2(85f, 47f));

        StaticBody squarePlatform3 = new StaticBody(this, squarePlatform);
        squarePlatform3.setPosition(new Vec2(49f, 35f));

        StaticBody platform4 = new StaticBody(this, shortPlatform);
        platform4.setPosition(new Vec2(57f, 45f));

        StaticBody rectanglePlatform1 = new StaticBody(this, rectanglePlatform);
        rectanglePlatform1.setPosition(new Vec2(71f, 50f));

        StaticBody platform5 = new StaticBody(this, shortPlatform);
        platform5.setPosition(new Vec2(95f, 56f));

        StaticBody platform6 = new StaticBody(this, shortFloor);
        platform6.setPosition(new Vec2(82f, 64.5f));

        //Level Exit
        StaticBody shortFloor9 = new StaticBody(this, shortFloor);
        shortFloor9.setPosition(new Vec2(106f, 67f));

        StaticBody squarePlatform4 = new StaticBody(this, squarePlatform);
        squarePlatform4.setPosition(new Vec2(123f, 70f));

        StaticBody rectanglePlatform2 = new StaticBody(this, rectanglePlatform);
        rectanglePlatform2.setPosition(new Vec2(135f, 67f));

        StaticBody squarePlatform5 = new StaticBody(this, squarePlatform);
        squarePlatform5.setPosition(new Vec2(137f, 68f));

        StaticBody platform7 = new StaticBody(this, shortPlatform);
        platform7.setPosition(new Vec2(153f, 70f));

        //Spike pit

        StaticBody longWall2 = new StaticBody(this, longWall);
        longWall2.setPosition(new Vec2(112, 58f));

        Spikes spikes2 = new Spikes(this);
        spikes2.setPosition(new Vec2(108f, 57f));

        Spikes spikes3 = new Spikes(this);
        spikes3.setPosition(new Vec2(122f, 57f));

        Spikes spikes4 = new Spikes(this);
        spikes4.setPosition(new Vec2(132f, 57f));

        Spikes spikes5 = new Spikes(this);
        spikes5.setPosition(new Vec2(158f, 57f));

        Spikes spikes6 = new Spikes(this);
        spikes6.setPosition(new Vec2(178f, 57f));

        Spikes spikes7 = new Spikes(this);
        spikes7.setPosition(new Vec2(198f, 57f));

        // Level end hole
        StaticBody longWall3 = new StaticBody(this, longWall);
        longWall3.setPosition(new Vec2(141.1f, 48f));

        StaticBody longWall4 = new StaticBody(this, longWall);
        longWall4.setPosition(new Vec2(148.9f, 48f));



















        // Enemies
        Crawler enemy1 = new Crawler(this);
        enemy1.setPosition(new Vec2(84.0f, -10.0f));

        CrawlerController enemyAI = new CrawlerController(enemy1, 5.0f);
        this.addStepListener(enemyAI);

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Repeated image for shapes

    private void applyTiledTexture(StaticBody body, float shapeHalfWidth, float imageWidth, float imageHeight, float offsetY, String imagePath) {

        float fullShapeWidth = shapeHalfWidth * 2;

        // Calculate how many times the image needs to repeat to cover the whole body
        int numberOfTiles = (int) Math.ceil(fullShapeWidth / imageWidth);

        // Find the far-left edge of the invisible physics body
        float startOffsetX = -shapeHalfWidth + (imageWidth / 2);

        for (int i = 0; i < numberOfTiles; i++) {

            // 1. Attach the image to the body
            AttachedImage tile = body.addImage(new BodyImage(imagePath, imageHeight));

            // 2. Calculate this specific tile's X position
            float currentOffsetX = startOffsetX + (i * imageWidth);

            // 3. Slide the image to the correct spot on the block!
            tile.setOffset(new Vec2(currentOffsetX, offsetY));
        }
    }

    // --- MASTER HELPER METHOD: BUILDS AND PAINTS IN ONE STEP ---
    private StaticBody createTexturedPlatform(float posX, float posY, float halfWidth, float halfHeight, float imageWidth, float imageHeight, float offsetY, String imagePath) {

        // 1. Build the invisible physics box
        Shape platformShape = new BoxShape(halfWidth, halfHeight);
        StaticBody platform = new StaticBody(this, platformShape);
        platform.setPosition(new Vec2(posX, posY));

        // 2. Paint it using your other helper method!
        applyTiledTexture(platform, halfWidth, imageWidth, imageHeight, offsetY, imagePath);

        // 3. Return the body in case you want to tilt it or change friction later
        return platform;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public String getLevelName() {
        return "TestLevel";
    }
}