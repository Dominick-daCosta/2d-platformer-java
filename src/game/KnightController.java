package game;

import city.cs.engine.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KnightController extends KnightAssets implements StepListener, MouseListener {
    private Walker knight;


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //Setting Default Variables
    private String currentState = "idle";

    private boolean facingRight = true;
    private boolean lastFacingRight = true;

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private int stepCount = 0;
    private int stateStartTime = 0;

    private boolean isSlashing = false;
    private boolean useAltSlash = false;
    private int lastSlashEndTime = -100;
    private static final int COMBO_TOLERANCE = 20;
    private KnightSlash currentSlashBox = null;

    public KnightController(Walker knight) {
        this.knight = knight;
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // --- MOUSE INPUT (NEW) ---
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (!isSlashing) {
                int timeSinceLastAttack = stepCount - lastSlashEndTime;

                if (timeSinceLastAttack < COMBO_TOLERANCE) {
                    useAltSlash = !useAltSlash;
                } else {
                    useAltSlash = false;
                }

                isSlashing = true;
                stateStartTime = stepCount;
            }
        }
    }

    // Required empty methods for MouseListener
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    // KEYBOARD INPUT

    // Is a Key Pressed
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
            knight.jump(25);
        }
    }

    // IS a Key Released
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


    // Controls if moving or not
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

    // Makes Character stop quickly so can switch directions without stutter
    private void stopMovingInstant() {
        knight.stopWalking();
        knight.setLinearVelocity(new Vec2(0, knight.getLinearVelocity().y));
    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //ANIMATION CONTROL

    @Override
    public void preStep(StepEvent stepEvent) {
        Vec2 velocity = knight.getLinearVelocity();
        String newState = "idle";

        stepCount++;

        // Determine Current State
        if (isSlashing) {
            newState = "slash";

        }else if (velocity.y > 0.001) {
            newState = "jump";

        } else if (velocity.y < -0.001) {
            newState = "fall";

        } else if (Math.abs(velocity.x) > 0.1) {
            newState = "walk";

        } else {
            newState = "idle";
        }



        // Only Update On Change Of State
        boolean stateChanged = !newState.equals(currentState);
        boolean directionChanged = facingRight != lastFacingRight;

        if (stateChanged) {
            stateStartTime = stepCount;
        }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        if (stateChanged || directionChanged || isAnimatedState(newState)) {

            if (!stateChanged && !directionChanged) {
                if (newState.equals("idle") && stepCount % 5 != 0) return;
                if (newState.equals("walk") && stepCount % 10 != 0) return;
                if (newState.equals("fall") && stepCount % 5 != 0) return;
            }

            knight.removeAllImages();
            AttachedImage img = null;



            // Switch Options

            int timeInState = stepCount - stateStartTime;

            switch (newState) {
                case "slash":
                    img = slash(timeInState);
                    break;

                case "jump":
                    img = jump(timeInState);
                    break;

                case "fall":
                    img = fall();
                    break;

                case "walk":
                    img = walk();
                    break;

                default: // Idle
                    img = idle();
                    break;
            }

            // Updates the SlashHitbox
            updateSlashHitbox(newState);

            // Flip if facing left
            if (facingRight) {
                img.flipHorizontal();
            }

            currentState = newState;
            lastFacingRight = facingRight;
        }
    }

    // Animated States
    private boolean isAnimatedState(String state) {
        return state.equals("walk") || state.equals("fall") || state.equals("idle") || state.equals("jump") || state.equals("slash");
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // Action Methods (to be called in the switch case)


    // Slashing
    public AttachedImage slash (int timeInState){
        BodyImage[] currentSlashAnim;
        BodyImage[] currentEffectAnim;

        if (useAltSlash) {
            currentSlashAnim = knightSlashAltImages;
            currentEffectAnim = knightSlashEffectAltImages;
        } else {
            currentSlashAnim = knightSlashImages;
            currentEffectAnim = knightSlashEffectImages;
        }
        int slashIndex = timeInState / 2;

        if (slashIndex >= currentSlashAnim.length) {
            isSlashing = false;

            lastSlashEndTime = stepCount; // How long inbetween each slash

            slashIndex = currentSlashAnim.length - 1;
        }

        // Add Main Body
        AttachedImage img = knight.addImage(currentSlashAnim[slashIndex]);

        // Add Effect
        if (slashIndex > 0) {
            int effectIndex = slashIndex - 1;
            if (effectIndex < currentEffectAnim.length) {
                AttachedImage effectImg = knight.addImage(currentEffectAnim[effectIndex]);

                // creates distance between slash and knight
                float imgOffset = -1.2f;
                effectImg.setOffset(new Vec2(imgOffset, 0));

                if (facingRight) effectImg.flipHorizontal();
            }
        }
        return img;
    }


    // Jumping
    public AttachedImage jump (int timeInState){
        int knightJumpIndex = timeInState / 10;
        if (knightJumpIndex >= knightJumpImages.length) {
            knightJumpIndex = knightJumpImages.length - 1;
        }
        return knight.addImage(knightJumpImages[knightJumpIndex]);
    }


    // Falling
    public AttachedImage fall (){
        int knightFallIndex = (stepCount / 10) % knightFallImages.length;
        return knight.addImage(knightFallImages[knightFallIndex]);
    }


    // Walking
    public AttachedImage walk (){
        int knightWalkIndex = (stepCount / 10) % knightWalkImages.length;
        return knight.addImage(knightWalkImages[knightWalkIndex]);
    }


    // Idle
    public AttachedImage idle (){
        int knightIdleIndex = (stepCount / 10) % knightIdleImages.length;
        return knight.addImage(knightIdleImages[knightIdleIndex]);
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // SLASH HITBOX

    private void updateSlashHitbox(String newState) {
    // 1. Detect if we just started or stopped slashing
        boolean justStarted = newState.equals("slash") && !currentState.equals("slash");
        boolean justStopped = !newState.equals("slash") && currentState.equals("slash");

    // 2. Create Hitbox (Only once!)
        if (justStarted) {
            float offset = -1.5f;
            Vec2 spawnPos = knight.getPosition().add(new Vec2(offset, 0));
            currentSlashBox = new KnightSlash(knight.getWorld(), spawnPos, facingRight);
        }

        // 3. Destroy Hitbox (Only once!)
        if (justStopped && currentSlashBox != null) {
            currentSlashBox.destroy();
            currentSlashBox = null;
        }

        // 4. Move Hitbox (Every frame while it exists)
        if (currentSlashBox != null) {
            float offsetX;
            float offsetY;

            if (!useAltSlash) {
                offsetX = facingRight ? 1.2f : -1.2f;
                offsetY = facingRight ? 0 : -1.2f;
            } else {
                offsetX = facingRight ? 1.8f : -1.8f;
                offsetY = facingRight ? 0 : -1.2f;
            }

            currentSlashBox.setPosition(knight.getPosition().add(new Vec2(offsetX, offsetY)));
        }
    }




















    @Override
    public void postStep (StepEvent stepEvent){
    }




}