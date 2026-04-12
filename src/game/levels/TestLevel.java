package game.levels;

import city.cs.engine.*;
import game.Game;
import game.crawler.CrawlerController;
import game.knight.KnightCollision;
import org.jbox2d.common.Vec2;
import game.levels.GameLevel;
import game.crawler.Crawler;

public class TestLevel extends GameLevel {

    public TestLevel(Game game) {
        super(game);
    }

    @Override
    public void populate() {

        getKnight().setPosition(new Vec2(0.0F, -10F)); // Knight starting position
        getKnight().addCollisionListener(new KnightCollision(getKnight()));

        // Ground & Platform
        Shape shape = new BoxShape(30.0F, 1.0F);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0.0F, -13.5F));

        Shape platformShape = new BoxShape(3.5F, 0.5F);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-0.0F, -5.0F));

        StaticBody platform2 = new StaticBody(this, platformShape);
        platform2.setPosition(new Vec2(-8.0F, 2.0F));

        StaticBody platform3 = new StaticBody(this, platformShape);
        platform3.setPosition(new Vec2(8.0F, 2.0F));


        StaticBody platform4 = new StaticBody(this, platformShape);
        platform4.setPosition(new Vec2(-0.0F, 9.0F));

        StaticBody platform5 = new StaticBody(this, platformShape);
        platform5.setPosition(new Vec2(-8.0F, 16.0F));

        StaticBody platform6 = new StaticBody(this, platformShape);
        platform6.setPosition(new Vec2(8.0F, 16.0F));


        StaticBody platform7 = new StaticBody(this, platformShape);
        platform7.setPosition(new Vec2(-0.0F, 24.0F));

        StaticBody platform8 = new StaticBody(this, platformShape);
        platform8.setPosition(new Vec2(-8.0F, 31.0F));

        StaticBody platform9 = new StaticBody(this, platformShape);
        platform9.setPosition(new Vec2(8.0F, 31.0F));



        StaticBody platform10 = new StaticBody(this, platformShape);
        platform10.setPosition(new Vec2(-0.0F, 38.0F));

        StaticBody platform11 = new StaticBody(this, platformShape);
        platform11.setPosition(new Vec2(-8.0F, 45.0F));

        StaticBody platform12 = new StaticBody(this, platformShape);
        platform12.setPosition(new Vec2(8.0F, 45.0F));

        // Enemies
        Crawler enemy1 = new Crawler(this);
        enemy1.setPosition(new Vec2(-0.0F, -2.0F));

        CrawlerController enemyAI = new CrawlerController(enemy1, 3.0f);
        this.addStepListener(enemyAI);
    }


}