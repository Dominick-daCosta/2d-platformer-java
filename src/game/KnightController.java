package game;

import city.cs.engine.BodyImage;
import city.cs.engine.AttachedImage; // IMPORT THIS!
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.Walker;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KnightController extends KeyAdapter implements StepListener {
    private Walker knight;

    // Images
    private BodyImage idleImage = new BodyImage("data/idle.png", 4.0f);
    private BodyImage jumpImage = new BodyImage("data/jump.png", 4.0f);
    private BodyImage fallImage = new BodyImage("data/falling.png", 4.0f);

    private BodyImage[] walkImages = {
            new BodyImage("data/walk 1.png", 4.0f),
            new BodyImage("data/walk 2.png", 4.0f),
            //new BodyImage("data/walk 3.png", 4.0f),
            //new BodyImage("data/walk 4.png", 4.0f)
    };

    //Setting Defult Vriables
    private String currentState = "idle";

    private boolean facingRight = true;
    private boolean lastFacingRight = true;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private int stepCount = 0;

    public KnightController(Walker knight) {
        this.knight = knight;
    }



    // Keyboard Input
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Move Left
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            leftPressed = true;
            startMoving();
        }

        // Move Right
        else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = true;
            startMoving();
        }

        // Jump
        else if (code == KeyEvent.VK_SPACE) {
            knight.jump(12);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            leftPressed = false;
            startMoving();
        }

        else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = false;
            startMoving();

        }
    }



    //Controls if moving or not
    private void startMoving() {
        if (leftPressed && rightPressed) {
            stopMovingInstant();
        }

        else if (leftPressed) {
            knight.startWalking(-7);
            facingRight = false;
        }

        else if (rightPressed) {
            knight.startWalking(7);
            facingRight = true;
        }

        else {
            stopMovingInstant();
        }
    }

    private void stopMovingInstant() {
        knight.stopWalking();
        knight.setLinearVelocity(new Vec2(0, knight.getLinearVelocity().y));
    }




    @Override
    public void preStep(StepEvent stepEvent) {
        Vec2 velocity = knight.getLinearVelocity();
        String newState = "idle";

        stepCount = stepCount + 1;

        // Check vertical speed to determine state
        if (velocity.y > 0.001) {
            newState = "jump";
        } else if (velocity.y < -0.001) {
            newState = "fall";
        } else if (Math.abs(velocity.x) > 0.1) {
                newState = "walk";
        } else {
            newState = "idle";
        }


        // Only swap images if the state changes

        if (!newState.equals(currentState) || facingRight != lastFacingRight || newState.equals("walk")) {

            if (newState.equals("walk") && newState.equals(currentState) && stepCount % 10 != 0) {
                return; // only chnages the frame every 5 frames
            }

            knight.removeAllImages();
            AttachedImage img = null;

            if (newState.equals("jump")) {
                img = knight.addImage(jumpImage);

            } else if (newState.equals("fall")) {
                img = knight.addImage(fallImage);

            } else if (newState.equals("walk")) {
                int frameIndex = (stepCount / 10) % 2;
                img = knight.addImage(walkImages[frameIndex]);

            } else {
                img = knight.addImage(idleImage);
            }

            // Flip if facing left
            if (!facingRight) {
                img.flipHorizontal();
            }

            currentState = newState;
            lastFacingRight = facingRight;
        }
    }

        @Override
        public void postStep (StepEvent stepEvent){
        }
}