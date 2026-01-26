package game;

import city.cs.engine.BodyImage;
import city.cs.engine.AttachedImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.UserView;
import city.cs.engine.Walker;
import city.cs.engine.World;
import javax.swing.JFrame;
import org.jbox2d.common.Vec2;

public class Game {
    public Game() {
        World world = new World();

        // Ground & Platform
        Shape shape = new BoxShape(11.0F, 0.5F);
        StaticBody ground = new StaticBody(world, shape);
        ground.setPosition(new Vec2(0.0F, -11.5F));

        Shape platformShape = new BoxShape(3.5F, 0.5F);
        StaticBody platform1 = new StaticBody(world, platformShape);
        platform1.setPosition(new Vec2(-0.0F, -5.0F));


        // Knight
        Shape knightShape = new BoxShape(1.0F, 2.0F);
        Walker knight = new Walker(world, knightShape);
        knight.setPosition(new Vec2(7.0F, -9.0F));
        knight.addImage(new BodyImage("data/idle.png", 4.0F));


        // Creates a View
        UserView view = new UserView(world, 500, 500);


        // Make The Controller
        KnightController controller = new KnightController(knight);
        view.addKeyListener(controller);   // Checks for key press
        world.addStepListener(controller); // Checks the movment


        // Make The Window
        JFrame frame = new JFrame("City Game");
        frame.add(view);
        frame.setDefaultCloseOperation(3);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        view.requestFocus(); //Focus the view so keyboard works immediately


        world.start();
    }

    public static void main(String[] args) {
        new Game();
    }
}