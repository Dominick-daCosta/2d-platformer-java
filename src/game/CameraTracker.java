package game;

import city.cs.engine.*;
import game.knight.Knight;
import game.knight.KnightController;
import org.jbox2d.common.Vec2;

public class CameraTracker implements StepListener {

    private final GameView view;
    private final Knight knight;
    private final KnightController controller;

    public final float minimumYAxis = 0f;

    // The Constructor gives the Tracker everything it needs to know
    public CameraTracker(GameView view, Knight knight, KnightController controller) {
        this.view = view;
        this.knight = knight;
        this.controller = controller;
    }

    @Override
    public void preStep(StepEvent e) {}

    @Override
    public void postStep(StepEvent e) {


        Vec2 currentCamPos = view.getCentre();

        float offset = controller.getCameraOffsetY();
        float targetX = knight.getPosition().x; // Where the camera should be
        float targetY = Math.max(minimumYAxis, knight.getPosition().y) + offset;


        float maxDistanceX = 12.0f; // Camera "leash" to the knight (cant be further than this distance)
        float maxDistanceY = 2.0f;

        float gapX = targetX - currentCamPos.x;
        float gapY = targetY - currentCamPos.y;

        // The "Leash"
        if (gapX > maxDistanceX) {
            currentCamPos.x = targetX - maxDistanceX;
        }
        else if (gapX < -maxDistanceX) {
            currentCamPos.x = targetX + maxDistanceX;
        }

        if (gapY > maxDistanceY) {
            currentCamPos.y = targetY - maxDistanceY;
        }
        else if (gapY < -maxDistanceY) {
            currentCamPos.y = targetY + maxDistanceY;
        }

        // "Lerp" Maths (smooths the camea tracking so it isn't snappy)
        float smoothing = 0.08f;
        float newX = currentCamPos.x + (targetX - currentCamPos.x) * smoothing;
        float newY = currentCamPos.y + (targetY - currentCamPos.y) * smoothing;

        view.setCentre(new Vec2(newX, newY));

    }
}