package game;

import city.cs.engine.*;

import javax.swing.JFrame;

import game.crawler.Crawler;
import game.crawler.CrawlerController;
import org.jbox2d.common.Vec2;
import java.awt.Color;
import game.knight.*;

public class Game {
    public Game() {
        World world = new World();
        world.setGravity(60.0f);

        new DebugViewer(world, 1024, 567);

        // Ground & Platform
        Shape shape = new BoxShape(30.0F, 1.0F);
        StaticBody ground = new StaticBody(world, shape);
        ground.setPosition(new Vec2(0.0F, -13.5F));

        Shape platformShape = new BoxShape(3.5F, 0.5F);
        StaticBody platform1 = new StaticBody(world, platformShape);
        platform1.setPosition(new Vec2(-0.0F, -5.0F));

        StaticBody platform2 = new StaticBody(world, platformShape);
        platform2.setPosition(new Vec2(-8.0F, 2.0F));

        StaticBody platform3 = new StaticBody(world, platformShape);
        platform3.setPosition(new Vec2(8.0F, 2.0F));


        StaticBody platform4 = new StaticBody(world, platformShape);
        platform4.setPosition(new Vec2(-0.0F, 9.0F));

        StaticBody platform5 = new StaticBody(world, platformShape);
        platform5.setPosition(new Vec2(-8.0F, 16.0F));

        StaticBody platform6 = new StaticBody(world, platformShape);
        platform6.setPosition(new Vec2(8.0F, 16.0F));


        StaticBody platform7 = new StaticBody(world, platformShape);
        platform7.setPosition(new Vec2(-0.0F, 24.0F));

        StaticBody platform8 = new StaticBody(world, platformShape);
        platform8.setPosition(new Vec2(-8.0F, 31.0F));

        StaticBody platform9 = new StaticBody(world, platformShape);
        platform9.setPosition(new Vec2(8.0F, 31.0F));



        StaticBody platform10 = new StaticBody(world, platformShape);
        platform10.setPosition(new Vec2(-0.0F, 38.0F));

        StaticBody platform11 = new StaticBody(world, platformShape);
        platform11.setPosition(new Vec2(-8.0F, 45.0F));

        StaticBody platform12 = new StaticBody(world, platformShape);
        platform12.setPosition(new Vec2(8.0F, 45.0F));


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // PLACES CHARACTERS

        // KNIGHT
        Knight knight = new Knight(world);

        // Collision Listener
        knight.addCollisionListener(new KnightCollision(knight));


        // Crawler
        Crawler enemy = new Crawler(world);

        enemy.setPosition(new Vec2(0, 0));
        CrawlerController enemyAI = new CrawlerController(enemy, 3.0f);
        world.addStepListener(enemyAI);


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // VEIW

        // Creates a View
        GameView view = new GameView(world, knight, 1024, 567);
        view.setBackground(Color.LIGHT_GRAY);


        // Make The Controller
        KnightController controller = new KnightController(knight);
        view.addKeyListener(controller);
        view.addMouseListener(controller);
        world.addStepListener(controller); // Checks the movement


        // Camera Tracker
        CameraTracker tracker = new CameraTracker(view, knight, controller);
        world.addStepListener(tracker);


        // Make The Window
        JFrame frame = new JFrame("City Game");
        frame.add(view);
        frame.setDefaultCloseOperation(3);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        view.requestFocus();


        world.start();
    }

    public static void main(String[] args) {
        new Game();
    }
}
