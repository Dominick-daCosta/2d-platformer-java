package game.knight;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Knight extends Walker {

    // Hitbox
    private static final Shape shapeMain = new PolygonShape(
            -0.66f, -1.60f, //Bottom left side
            -0.14f, -2.6f, // Bottom left foot
            0.32f, -2.6f, // Bottom right foot
            0.72f, -1.66f, // Bottom right side
            0.72f,  0.92f, // Top right
            -0.66f,  0.89f  // Top left
    );

    public Knight(World world) {
        super(world, shapeMain);

        BodyImage knightImg = new BodyImage("data/knight sprites/idle 1.png", 6.0f);
        AttachedImage img = this.addImage(knightImg);
        img.setOffset(new Vec2(0, 0.4f));

        this.setPosition(new Vec2(7.0f, -9.0f));

        // Friction
        SolidFixture mainFixture = (SolidFixture) this.getFixtureList().get(0);
        mainFixture.setFriction(0.0f);

    }
}