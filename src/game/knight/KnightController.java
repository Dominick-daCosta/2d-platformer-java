package game.knight;

import city.cs.engine.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyEvent;

public class KnightController implements StepListener, MouseListener, java.awt.event.KeyListener {
    private final Knight knight;

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // DEFAULT VARIABLES

    public enum State {
        idle, walk, jump, fall, dash, slash, upSlash, downSlash, lookUp, lookDown, dead, healing
    }

    // Constants
    private static final float jumpPower = 34.0f;
    private static final float walkSpeed = 12.0f;
    private static final float dashSpeed = 30.0f;
    private static final int dashDuration = 15;
    private static final int dashCooldown = 20;
    private static final int comboTolerance = 20;

    // Input Polling
    private boolean leftPressed, rightPressed, upPressed, downPressed, spacePressed, shiftPressed;
    private boolean rightClicked, leftClicked;

    private boolean jumpProcessed = false; // Used to check if action is done or not
    private boolean attackProcessed = false;
    private boolean dashProcessed = false;

    // Game State
    private State currentState = State.idle;
    private boolean facingRight = true;
    private boolean lastFacingRight = true;

    private int stepCount = 0;
    private int stateStartTime = 0;

    // Attacking
    private boolean isSlashing = false;
    private boolean useAltSlash = false;
    private int lastSlashEndTime = -100;
    private KnightSlash currentSlashBox = null;

    // Dashing
    private boolean isDashing = false;
    private int startDashTime;
    private int lastDashEndTime = -100;
    private boolean dashedInAir = false;

    // Healing Tracking
    private boolean isHealing = false;
    private boolean wasHealingPressed = false;
    private int healingPressStartTime = 0;

    // Move Camera When Looking
    private float cameraOffsetY = 0f;
    private static final float maxCameraOffset = 8.0f;
    private static final float cameraSpeed = 0.2f;

    public KnightController(Knight knight) {
        this.knight = knight;
    }

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // MOUSE INPUT

    // If a Button Is Pressed
    @Override
    public void mousePressed(MouseEvent e) {
        int mousePressed = e.getButton();
        ((java.awt.Component) e.getSource()).requestFocus();

        // Attacking
        if (mousePressed == MouseEvent.BUTTON1) {
            leftClicked = true;
        }

        // Dashing
        if (mousePressed == MouseEvent.BUTTON3) {
            rightClicked = true;
        }
    }

    // If The Button Is Released
    @Override
    public void mouseReleased(MouseEvent e) {
        int mousePressed = e.getButton();

        // Attacking
        if (mousePressed == MouseEvent.BUTTON1) {
            leftClicked = false;
            attackProcessed = false;
        }

        // Dashing
        if (mousePressed == MouseEvent.BUTTON3) {
            rightClicked = false;
            dashProcessed = false;
        }
    }

    // Required empty methods for MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // KEYBOARD INPUT

    // Is a Key Pressed
    @Override
    public void keyPressed(KeyEvent e) {
        int keyPressed = e.getKeyCode();

        // Move Left
        if (keyPressed == KeyEvent.VK_LEFT || keyPressed == KeyEvent.VK_A) {
            leftPressed = true;
        }

        // Move Right
        if (keyPressed == KeyEvent.VK_RIGHT || keyPressed == KeyEvent.VK_D) {
            rightPressed = true;
        }

        // Look Up
        if (keyPressed == KeyEvent.VK_UP || keyPressed == KeyEvent.VK_W) {
            upPressed = true;
        }

        // Look Down
        if (keyPressed == KeyEvent.VK_DOWN || keyPressed == KeyEvent.VK_S) {
            downPressed = true;
        }

        // Jump
        if (keyPressed == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }

        // Spells/Heal
        if (keyPressed == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }

    }

    // Is a Key Released
    @Override
    public void keyReleased(KeyEvent e) {
        int keyPressed = e.getKeyCode();

        // Stop Moving Left
        if (keyPressed == KeyEvent.VK_LEFT || keyPressed == KeyEvent.VK_A) {
            leftPressed = false;
        }

        // Stop Moving Right
        if (keyPressed == KeyEvent.VK_RIGHT || keyPressed == KeyEvent.VK_D) {
            rightPressed = false;
        }

        // Stop Looking Up
        if (keyPressed == KeyEvent.VK_UP || keyPressed == KeyEvent.VK_W) {
            upPressed = false;
        }

        // Stop Looking Down
        if (keyPressed == KeyEvent.VK_DOWN || keyPressed == KeyEvent.VK_S) {
            downPressed = false;
        }

        // Slow Down Jump
        if (keyPressed == KeyEvent.VK_SPACE) {
            spacePressed = false;
            jumpProcessed = false;

            Vec2 v = knight.getLinearVelocity(); // when space is released slow the vertical speed
            if (v.y > 0.1f) {
                knight.setLinearVelocity(new Vec2(v.x, v.y * 0.5f));
            }
        }

        // Spells/Heal
        if (keyPressed == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }

    }

    // Required empty methods for KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void preStep(StepEvent stepEvent) {
        stepCount++;

        // Healing Input Tracking
        if (shiftPressed && !wasHealingPressed) {
            healingPressStartTime = stepCount;
        }

        if (shiftPressed) {
            int holdDuration = stepCount - healingPressStartTime;
            if (holdDuration >= 30 && !isHealing && knight.getSoul() > 0 && knight.knightGetHealth() < 5) {
                isHealing = true;
            }
            // Stop healing if conditions unmet
            if (isHealing && (knight.getSoul() <= 0 || knight.knightGetHealth() == 5)) {
                isHealing = false;
            }
        } else {
            isHealing = false;
        }

        wasHealingPressed = shiftPressed;

        pollInputs();
        updatePhysics();
        updateAnimation();
        updateCamera();

    }

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // MANAGE INPUTS
    private void pollInputs() {

        // Jump
        if (spacePressed && !jumpProcessed) {
            knight.jump(jumpPower);
            jumpProcessed = true;
        }

        // Attack
        if (leftClicked && !attackProcessed) {
            attackProcessed = true;

            if (!isSlashing) {
                isSlashing = true;
                stateStartTime = stepCount;

                int timeSinceLastAttack = stepCount - lastSlashEndTime;
                useAltSlash = (timeSinceLastAttack < comboTolerance) && !useAltSlash;
            }
        }

        // Dash

        boolean isGrounded = Math.abs(knight.getLinearVelocity().y) <= 0.0001f;
        if (isGrounded && !isDashing) {
            dashedInAir = false;
        }

        if (rightClicked && !dashProcessed) {
            dashProcessed = true;

            if ((!isDashing && (stepCount - lastDashEndTime) > dashCooldown) && !dashedInAir) {
                startDashTime = stepCount;
                isDashing = true;
                dashedInAir = true;
            }
        }
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // MOVEMENT
    private void updatePhysics() {

        // Dash
        if (isDashing) {
            int timeDashed = stepCount - startDashTime;

            if (timeDashed < dashDuration) { // If not already dashed recently (stops spamming)
                // Dashing
                knight.setGravityScale(0.0f);
                knight.stopWalking();
                float direction = facingRight ? 1.0f : -1.0f;

                knight.setLinearVelocity(new Vec2(dashSpeed * direction, 0));
            } else {
                // Dash End
                isDashing = false;
                knight.setGravityScale(1.0f);
                knight.setLinearVelocity(new Vec2(0, -1)); // Kill momentum
                lastDashEndTime = stepCount;
            }

            return;
        }

        // Walking

        if (leftPressed && !rightPressed) { // Go left
            knight.startWalking(-walkSpeed);
            facingRight = false;

        } else if (rightPressed && !leftPressed) { // Go right
            knight.startWalking(walkSpeed);
            facingRight = true;

        } else { // Stop
            knight.stopWalking();
            knight.setLinearVelocity(new Vec2(0, knight.getLinearVelocity().y));
        }
    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void updateAnimation() {

        Vec2 velocity = knight.getLinearVelocity();
        State newState = State.idle;

        // DETERMINE CURRENT STATE

        // Die
        if (knight.knightGetHealth() <= 0) {
            newState = State.dead;

            if (isDashing) { // Makes sure knight stops when it dies
                isDashing = false;
                knight.setGravityScale(1.0f);
            }
        }

        // Slashing
        else if (isSlashing) {
            if (upPressed)
                newState = State.upSlash;
            else if (downPressed && velocity.y != 0)
                newState = State.downSlash;
            else
                newState = State.slash;
        }

        // Healing
        else if (isHealing) {
            newState = State.healing;
        }

        // Dashing
        else if (isDashing) {
            newState = State.dash;
        }

        // Jumping
        else if (velocity.y > 0.001) {
            newState = State.jump;
        }

        // Falling
        else if (velocity.y < -0.001) {
            newState = State.fall;
        }

        // Walking
        else if (Math.abs(velocity.x) > 0.1) {
            newState = State.walk;
        }

        // Looking Up
        else if (upPressed) {
            newState = State.lookUp;
        }

        // Looking Down
        else if (downPressed) {
            newState = State.lookDown;
        }

        boolean stateChanged = (newState != currentState);
        boolean directionChanged = (facingRight != lastFacingRight);

        if (stateChanged)
            stateStartTime = stepCount;

        // Animation Speed
        if (!stateChanged && !directionChanged) {
            if (newState == State.idle && stepCount % 5 != 0)
                return;
            if (newState == State.walk && stepCount % 10 != 0)
                return;
            if (newState == State.fall && stepCount % 5 != 0)
                return;
        }

        knight.removeAllImages();
        AttachedImage img;
        int timeInState = stepCount - stateStartTime;

        if (timeInState < 0) { // Prevents animations from having a negative number
            timeInState = 0;
        }

        switch (newState) {

            case slash:
                img = slash(timeInState);
                img.setOffset(new Vec2(0, 0.2f));
                break;

            case upSlash:
                img = upSlash(timeInState);
                img.setOffset(new Vec2(0, 0.2f));
                break;

            case downSlash:
                img = downSlash(timeInState);
                img.setOffset(new Vec2(0, 0.2f));
                break;

            case jump:
                img = getFrame(KnightAssets.JumpImages, timeInState, 10);
                img.setOffset(new Vec2(0, 0.2f));
                break;

            case dash:
                img = getFrame(KnightAssets.DashImages, timeInState, 2);
                img.setOffset(new Vec2(0, 0.2f));
                break;

            case fall:
                img = getFrame(KnightAssets.FallImages, stepCount, 10);
                img.setOffset(new Vec2(0, 0.2f));
                break;

            case walk:
                img = getFrame(KnightAssets.WalkImages, stepCount, 10);
                img.setOffset(new Vec2(0, 0.2f));
                break;

            case lookUp:
                img = lookUp(timeInState);
                img.setOffset(new Vec2(0, 0.2f));
                break;

            case lookDown:
                img = lookDown(timeInState);
                img.setOffset(new Vec2(0, 0.2f));
                break;

            case dead:
                img = die(timeInState);
                img.setOffset(new Vec2(0, 3.0f));
                break;

            case healing:
                img = healingAnim(timeInState);
                img.setOffset(new Vec2(0, 0.2f));
                break;

            default:
                img = getFrame(KnightAssets.IdleImages, stepCount, 10);
                img.setOffset(new Vec2(0, 0.2f));
                break;
        }

        updateSlashHitbox(newState);
        if (facingRight)
            img.flipHorizontal();

        currentState = newState;
        lastFacingRight = facingRight;

    }

    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void postStep(StepEvent stepEvent) {

        // Makes sure dash stops and doesn't fly infinitely
        if (isDashing) {
            float direction = facingRight ? 1.0f : -1.0f;
            knight.setLinearVelocity(new Vec2(dashSpeed * direction, 0));
        }
    }

    /// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // HELPER METHODS

    // Animation Control ( Idle, Walk, Jump, Dash)
    private AttachedImage getFrame(BodyImage[] frames, int time, int speed) {
        int index = (time / speed) % frames.length;
        return knight.addImage(frames[index]);
    }

    // Slash

    public AttachedImage slash(int timeInState) {

        // Which Slash Animation Should be Used
        BodyImage[] currentSlashAnim = useAltSlash ? KnightAssets.SlashAltImages : KnightAssets.SlashImages;
        BodyImage[] currentEffectAnim = useAltSlash ? KnightAssets.SlashEffectAltImages
                : KnightAssets.SlashEffectImages;

        int slashIndex = timeInState / 2;

        // Stop Slashing Animation
        if (slashIndex >= currentSlashAnim.length) {
            isSlashing = false;
            lastSlashEndTime = stepCount;
            slashIndex = currentSlashAnim.length - 1;
        }

        AttachedImage img = knight.addImage(currentSlashAnim[slashIndex]);

        // Slash Effect
        if (slashIndex > 0) {
            int effectIndex = slashIndex - 1;
            if (effectIndex < currentEffectAnim.length) { // Only play the effect after 1 frame of the slash has
                // happened
                AttachedImage effectImg = knight.addImage(currentEffectAnim[effectIndex]);
                effectImg.setOffset(new Vec2(-1.2f, 0));
                if (facingRight)
                    effectImg.flipHorizontal();
            }
        }
        return img;
    }

    // Up Slash

    public AttachedImage upSlash(int timeInState) {

        int slashIndex = timeInState / 2;
        if (slashIndex >= KnightAssets.UpSlashImages.length) {
            isSlashing = false;
            lastSlashEndTime = stepCount;
            slashIndex = KnightAssets.UpSlashImages.length - 1;
        }

        AttachedImage img = knight.addImage(KnightAssets.UpSlashImages[slashIndex]);

        // Slash Effect
        if (slashIndex > 0) {
            int effectIndex = slashIndex - 1;
            if (effectIndex < KnightAssets.UpSlashEffectImages.length) {
                AttachedImage effectImg = knight.addImage(KnightAssets.UpSlashEffectImages[effectIndex]);
                effectImg.setOffset(new Vec2(0, 1.5f));
                if (facingRight)
                    effectImg.flipHorizontal();
            }
        }
        return img;
    }

    // Down Slash

    public AttachedImage downSlash(int timeInState) {
        int slashIndex = timeInState / 2;
        if (slashIndex >= KnightAssets.DownSlashImages.length) {
            isSlashing = false;
            lastSlashEndTime = stepCount;
            slashIndex = KnightAssets.DownSlashImages.length - 1;
        }
        AttachedImage img = knight.addImage(KnightAssets.DownSlashImages[slashIndex]);

        // Slash Effect
        if (slashIndex > 0) {
            int effectIndex = slashIndex - 1;
            if (effectIndex < KnightAssets.DownSlashEffectImages.length) {
                AttachedImage effectImg = knight.addImage(KnightAssets.DownSlashEffectImages[effectIndex]);
                effectImg.setOffset(new Vec2(0, -2.0f));
                if (facingRight)
                    effectImg.flipHorizontal();
            }
        }
        return img;
    }

    // Look Up

    public AttachedImage lookUp(int timeInState) {
        int index = timeInState / 10;
        int renderIndex = (index == 0) ? 0 : 1 + ((index - 1) % (KnightAssets.LookUpImages.length - 1));
        return knight.addImage(KnightAssets.LookUpImages[renderIndex]);
    }

    // Look Down

    public AttachedImage lookDown(int timeInState) {
        int index = timeInState / 10;

        /*
         * (index == 0) ? 0 : -> Plays frame 0 once at the very beginning.
         * 1 +                -> Offsets the math so we never draw frame 0 again.
         * (index - 1)        -> Shifts the time backward by 1 to account for the intro frame.
         * % (length - 1)     -> Forces the remaining frames to loop endlessly.
         */

        int renderIndex = (index == 0) ? 0 : 1 + ((index - 1) % (KnightAssets.LookDownImages.length - 1));
        return knight.addImage(KnightAssets.LookDownImages[renderIndex]);
    }

    // Die

    public AttachedImage die(int timeInState) {
        int index = timeInState / 5;

        // Stop Everything
        knight.stopWalking();
        knight.setLinearVelocity(new Vec2(0, 0));
        knight.removeAllCollisionListeners();
        knight.setGravityScale(0);

        if (index >= KnightAssets.DeathImages.length) { // When animation finishes delete the knight
            index = KnightAssets.DeathImages.length - 1;
            knight.destroy();
        }

        return knight.addImage(KnightAssets.DeathImages[index]); // Play death animation
    }

    // Healing
    private AttachedImage healingAnim(int timeInState) {
        if (timeInState > 0 && timeInState % 100 == 0) {
            knight.gainHealth();
            knight.looseSoul();
        }
        int index = timeInState / 10;
        int renderIndex = (index < 2) ? index : 2 + ((index - 2) % (KnightAssets.HealingImages.length - 2));
        return knight.addImage(KnightAssets.HealingImages[renderIndex]);
    }


    /// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // Slash Hitbox
    private void updateSlashHitbox(State newState) {
        boolean isAnySlash = (newState == State.slash || newState == State.upSlash || newState == State.downSlash); // If about to slash this is true
        boolean wasAnySlash = (currentState == State.slash || currentState == State.upSlash
                || currentState == State.downSlash); // If currently slashing this is true

        if (isAnySlash && !wasAnySlash) { // If about to slash and not currently slashing
            currentSlashBox = new KnightSlash(knight.getWorld(), knight.getPosition(), newState, facingRight, knight);
        }

        if (!isAnySlash && wasAnySlash && currentSlashBox != null) { // If not about to slash and not currently slashing
            // and if there is a slash hitbox
            currentSlashBox.destroy();
            currentSlashBox = null;
        }

        if (currentSlashBox != null) { // If a slash hitbox exists
            float offsetX, offsetY;

            // Up Slash Offset
            if (newState == State.upSlash) {
                offsetX = -0.5f;
                offsetY = 1.8f;
            }

            // Down Slash Offset
            else if (newState == State.downSlash) {
                offsetX = 0.5f;
                offsetY = -2.2f;
            }

            // Regular Slash
            else {
                offsetX = facingRight ? (useAltSlash ? 2.9f : 2.1f) : (useAltSlash ? -2.9f : -2.1f);
                offsetY = facingRight ? 0.1f : -1.2f;
            }

            currentSlashBox.setPosition(knight.getPosition().add(new Vec2(offsetX, offsetY)));
        }
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private void updateCamera() {

        // Pan Up
        if (currentState == State.lookUp) {
            if (cameraOffsetY < maxCameraOffset) {
                cameraOffsetY += cameraSpeed;
            }
        }
        // Pan Down
        else if (currentState == State.lookDown) {
            if (cameraOffsetY > -maxCameraOffset) {
                cameraOffsetY -= cameraSpeed;
            }
        }
        // Return Camera
        else {
            if (cameraOffsetY > 0) {
                cameraOffsetY = Math.max(0, cameraOffsetY - cameraSpeed);

            }
            else if (cameraOffsetY < 0) {
                cameraOffsetY = Math.min(0, cameraOffsetY + cameraSpeed);
            }

            /*
            Min and Max means that the camera will never over shoot the original position and shake
            */
        }
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // GETTERS

    public float getCameraOffsetY() {
        return cameraOffsetY;
    }




}