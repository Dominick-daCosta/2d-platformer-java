package game;

import city.cs.engine.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

import game.crawler.Crawler;
import game.crawler.CrawlerController;
import org.jbox2d.common.Vec2;
import java.awt.Color;
import game.knight.*;

public class Game {
    public Game() {
        World world = new World();
        world.setGravity(35.0f);

        new DebugViewer(world, 500, 500);

        // Ground & Platform
        Shape shape = new BoxShape(11.0F, 0.5F);
        StaticBody ground = new StaticBody(world, shape);
        ground.setPosition(new Vec2(0.0F, -11.5F));

        Shape platformShape = new BoxShape(3.5F, 0.5F);
        StaticBody platform1 = new StaticBody(world, platformShape);
        platform1.setPosition(new Vec2(-0.0F, -5.0F));

        StaticBody platform2 = new StaticBody(world, platformShape);
        platform2.setPosition(new Vec2(-8.0F, 2.0F));

        StaticBody platform3 = new StaticBody(world, platformShape);
        platform3.setPosition(new Vec2(8.0F, 2.0F));


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // PLACES CHARACTERS

        // KNIGHT
        Knight knight = new Knight(world);

        // Add the Initial Image
        knight.addImage(new BodyImage("data/knight sprites/idle 1.png", 4.0F));


        // Crawler
        Crawler enemy = new Crawler(world);

        // Add the Initial Image
        knight.addImage(new BodyImage("data/crawler sprites/walk 1.png", 4.0F));

        enemy.setPosition(new Vec2(0, 0));
        CrawlerController enemyAI = new CrawlerController(enemy, 3.0f);
        world.addStepListener(enemyAI);


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // VEIW

        // Creates a View
        UserView view = new UserView(world, 500, 500);
        view.setBackground(Color.LIGHT_GRAY);


        // Make The Controller
        KnightController controller = new KnightController(knight);
        view.addKeyListener(controller);
        view.addMouseListener(controller);
        world.addStepListener(controller); // Checks the movment


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
