package game;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.DebugViewer;
import city.cs.engine.SolidFixture;
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
        new DebugViewer(world, 500, 500);

        // Ground & Platform
        Shape shape = new BoxShape(11.0F, 0.5F);
        StaticBody ground = new StaticBody(world, shape);
        ground.setPosition(new Vec2(0.0F, -11.5F));

        Shape platformShape = new BoxShape(3.5F, 0.5F);
        StaticBody platform1 = new StaticBody(world, platformShape);
        platform1.setPosition(new Vec2(-0.0F, -5.0F));

        StaticBody platform2 = new StaticBody(world, platformShape);
        platform2.setPosition(new Vec2(-8.0F, 1.0F));

        StaticBody platform3 = new StaticBody(world, platformShape);
        platform3.setPosition(new Vec2(8.0F, 1.0F));


        // Knight

        // 1. MAIN BODY (The Base)
        // Keeps the feet flat for standing, but tapers the chest.
        Shape mainShape = new PolygonShape(
                -0.25f, -2.05f,  // Bottom-Left (Feet)
                0.25f, -2.05f,  // Bottom-Right (Feet)
                0.55f, -1.61f,  // Top-Right (Waist)
                -0.53f, -1.61f   // Top-Left (Waist)
        );
        Walker knight = new Walker(world, mainShape);
        knight.setPosition(new Vec2(7.0f, -9.0f));

        // 2. UPPER BODY & CLOAK
        Shape upperBodyShape = new PolygonShape(
                -0.53f, -1.61f, // Waist Left
                0.55f, -1.61f, // Waist Right
                0.80f, -0.90f, // Cloak Bulge Right
                0.60f, -0.30f, // Neck Right
                -0.60f, -0.30f, // Neck Left
                -0.80f, -0.90f  // Cloak Bulge Left
        );
        SolidFixture upperBodyFixture = new SolidFixture(knight, upperBodyShape);

        // 3. HEAD (The "Roof" Fix)
        // Instead of a flat top, we make it a pentagon with a peak.
        // This lets the horns attach to slopes so they don't hold flat ledges.
        Shape headShape = new PolygonShape(
                -0.60f, -0.30f, // Bottom-Left
                0.60f, -0.30f, // Bottom-Right
                0.50f,  0.60f, // Top-Right (Ear)
                0.00f,  0.80f, // Top-Center (Peak - prevents sticking to ceilings)
                -0.50f,  0.60f  // Top-Left (Ear)
        );
        SolidFixture headFixture = new SolidFixture(knight, headShape);

        // 4. LEFT HORN
        // Slopes upwards from the head. No flat bottom edge to hook onto things.
        Shape leftHornShape = new PolygonShape(
                0.00f,  0.80f, // Connects to Head Peak
                -0.30f,  1.80f, // Inner Tip
                -0.80f,  2.00f, // Outer Tip
                -1.00f,  1.50f, // Outer Bulge
                -0.50f,  0.60f  // Connects to Head Ear
        );
        SolidFixture leftHornFixture = new SolidFixture(knight, leftHornShape);

        // 5. RIGHT HORN
        Shape rightHornShape = new PolygonShape(
                0.00f,  0.80f, // Connects to Head Peak
                0.50f,  0.60f, // Connects to Head Ear
                1.00f,  1.50f, // Outer Bulge
                0.80f,  2.00f, // Outer Tip
                0.30f,  1.80f  // Inner Tip
        );
        SolidFixture rightHornFixture = new SolidFixture(knight, rightHornShape);


        // --- IMAGES & FRICTION ---
        knight.addImage(new BodyImage("data/idle.png", 4.0F));

        // Important: Keep friction at 0.0 for the upper parts so you slide off ceilings
        SolidFixture mainFixture = (SolidFixture)knight.getFixtureList().get(0);
        mainFixture.setFriction(0.1f);      // Feet have grip
        upperBodyFixture.setFriction(0.0f); // Body is slippery
        headFixture.setFriction(0.0f);      // Head is slippery
        leftHornFixture.setFriction(0.0f);  // Horns are slippery
        rightHornFixture.setFriction(0.0f);

        

        // Creates a View
        UserView view = new UserView(world, 500, 500);
        //view.setDebug(true);

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
