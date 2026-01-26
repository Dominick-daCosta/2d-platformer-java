package game;

import city.cs.engine.BodyImage;
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

        // --- Ground & Platform ---
        Shape shape = new BoxShape(11.0F, 0.5F);
        StaticBody ground = new StaticBody(world, shape);
        ground.setPosition(new Vec2(0.0F, -11.5F));

        Shape platformShape = new BoxShape(3.5F, 0.5F);
        StaticBody platform1 = new StaticBody(world, platformShape);
        platform1.setPosition(new Vec2(-8.0F, 5.5F));

        // --- Student ---
        Shape studentShape = new BoxShape(1.0F, 2.0F);
        Walker student = new Walker(world, studentShape);
        student.setPosition(new Vec2(7.0F, -9.0F));
        // Add the initial image so they aren't invisible at start
        student.addImage(new BodyImage("data/idle.png", 4.0F));

        // --- View ---
        UserView view = new UserView(world, 500, 500);

        // --- Controller Connection ---
        StudentController controller = new StudentController(student);
        view.addKeyListener(controller);   // Listens for keys
        world.addStepListener(controller); // Listens for physics steps (for animation)

        // --- Window Setup ---
        JFrame frame = new JFrame("City Game");
        frame.add(view);
        frame.setDefaultCloseOperation(3);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        // Important: Focus the view so keyboard works immediately
        view.requestFocus();

        world.start();
    }

    public static void main(String[] args) {
        new Game();
    }
}