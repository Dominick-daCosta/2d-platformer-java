package game.knight;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class KnightSlash extends StaticBody {


    private static final Shape shapeTop = new PolygonShape(
            -0.14f, 0.0f,
            0.94f, -0.28f,
            1.37f, 0.11f,
            -0.05f, 0.36f
    );

    private static final Shape shapeTopMid = new PolygonShape(
            1.19f, -0.67f,
            0.94f, -0.28f,
            1.40f, 0.18f,
            2.24f, -0.52f
    );

    private static final Shape shapeBotMid = new PolygonShape(
            0.94f, -0.28f,
            1.81f, -1.05f,
            2.24f, -0.52f
    );

    private static final Shape shapeBot = new PolygonShape(
            0.17f, -1.15f,
            0.14f, -1.42f,
            1.81f, -1.05f,
            1.18f, -0.66f
    );

    public KnightSlash(World world, Vec2 position, String state, boolean facingRight) {
        super(world);

        // Add the shapes
        new GhostlyFixture(this, shapeTop);
        new GhostlyFixture(this, shapeTopMid);
        new GhostlyFixture(this, shapeBotMid);
        new GhostlyFixture(this, shapeBot);

        // Give it an invisible immage to fix random blocks appearing
        BodyImage invisibleImg = new BodyImage("data/knight sprites/invisible.png", 1.0f);
        this.addImage(invisibleImg);

        // 1. Set Position
        this.setPosition(position);

        // Rotate hitbox
        if (state.equals("upSlash")) {
            this.setAngleDegrees(90);

        } else if (state.equals("downSlash")) {
            this.setAngleDegrees(-90);

        } else {
            // Normal Side Slash
            if (!facingRight) {
                this.setAngleDegrees(180);
            }
        }
    }
}