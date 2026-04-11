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

    // Leash Settings
    private static final float maxDistanceX = 12.0f; // Camera "leash" to the knight (cant be further than this distance from the camera)
    private static final float maxDistanceY = 2.0f;
    private static final float smoothing = 0.08f;

    // Camera Boundaries
    private boolean isLeftLocked = false;
    private float minLeftX = 0f;
    private boolean isRightLocked = false;
    private float minRightX = 0f;



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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

        // "Lerp" Maths (smooths the camera tracking so it isn't snappy)
        float newX = currentCamPos.x + (targetX - currentCamPos.x) * smoothing;
        float newY = currentCamPos.y + (targetY - currentCamPos.y) * smoothing;

        // Boundary code
        if (isLeftLocked) {
            newX = Math.max(minLeftX, newX);
        }

        if (isRightLocked) {
            newX = Math.min(minRightX, newX);
        }

        view.setCentre(new Vec2(newX, newY));

    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // HELPER METHODS

    public void setLeftBoundary(float xCoordinate) {
        this.isLeftLocked = true;
        this.minLeftX = xCoordinate;
    }

    public void setRightBoundary(float xCoordinate) {
        this.isRightLocked = true;
        this.minRightX = xCoordinate;
    }

}