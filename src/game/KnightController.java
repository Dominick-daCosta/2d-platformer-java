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

    private String currentState = "idle";

    private boolean facingRight = true;
    private boolean lastFacingRight = true;

    public KnightController(Walker knight) {
        this.knight = knight;
    }

    // Keyboard Input
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Move Left
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            knight.startWalking(-7);
            facingRight = false;
        }

        // Move Right
        else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            knight.startWalking(7);
            facingRight = true;
        }

        // Jump
        else if (code == KeyEvent.VK_SPACE) {
            knight.jump(12);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        //Stop Movment
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A ||
                code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            knight.stopWalking();
        }
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        Vec2 velocity = knight.getLinearVelocity();
        String newState = "idle";

        // Check vertical speed to determine state
        if (velocity.y > 0.001) {
            newState = "jump";
        } else if (velocity.y < -0.001) {
            newState = "fall";
        } else {
            newState = "idle";
        }

        // Only swap images if the state changes

        if (!newState.equals(currentState) || facingRight != lastFacingRight) {

            knight.removeAllImages();
            AttachedImage img = null;


            if (newState.equals("jump")) {
                img = knight.addImage(jumpImage);

            } else if (newState.equals("fall")) {
                img = knight.addImage(fallImage);

            } else {
                img = knight.addImage(idleImage);
            }


            if (!facingRight) {
                img.flipHorizontal();
            }

            currentState = newState;
        }
    }

        @Override
        public void postStep (StepEvent stepEvent){
        }
}