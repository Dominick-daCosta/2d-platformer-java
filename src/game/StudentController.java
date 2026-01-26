package game;

import city.cs.engine.BodyImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.Walker;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StudentController extends KeyAdapter implements StepListener {
    private Walker student;

    // IMAGES: Ensure these file names match your actual files exactly!
    private BodyImage idleImage = new BodyImage("data/idle.png", 4.0f);
    private BodyImage jumpImage = new BodyImage("data/jump.png", 4.0f);
    private BodyImage fallImage = new BodyImage("data/falling.png", 4.0f);

    private String currentState = "idle";

    public StudentController(Walker student) {
        this.student = student;
    }

    // --- KEYBOARD INPUT ---
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Move Left (Arrow or A)
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            student.startWalking(-4);
        }
        // Move Right (Arrow or D)
        else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            student.startWalking(4);
        }
        // Jump (Arrow or W)
        else if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            student.jump(8);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // Stop walking if left or right keys are released
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A ||
                code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            student.stopWalking();
        }
    }

    // --- ANIMATION LOGIC (Runs 60 times/sec) ---
    @Override
    public void preStep(StepEvent stepEvent) {
        Vec2 velocity = student.getLinearVelocity();
        String newState = "idle";

        // Check vertical speed to determine state
        if (velocity.y > 0.5) {
            newState = "jump";
        } else if (velocity.y < -0.5) {
            newState = "fall";
        } else {
            newState = "idle";
        }

        // Only swap images if the state changes (saves performance)
        if (!newState.equals(currentState)) {
            student.removeAllImages();

            if (newState.equals("jump")) {
                student.addImage(jumpImage);
            } else if (newState.equals("fall")) {
                student.addImage(fallImage);
            } else {
                student.addImage(idleImage);
            }

            currentState = newState;
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }
}