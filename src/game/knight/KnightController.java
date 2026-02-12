    package game.knight;

    import city.cs.engine.*;
    import java.awt.event.MouseListener;
    import java.awt.event.MouseEvent;
    import org.jbox2d.common.Vec2;
    import java.awt.event.KeyEvent;

    public class KnightController extends KnightAssets implements StepListener, MouseListener {
        private Knight knight;


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        //Setting Default Variables
        private String currentState = "idle";

        private boolean facingRight = true;
        private boolean lastFacingRight = true;

        private boolean leftPressed = false;
        private boolean rightPressed = false;
        private boolean upPressed = false;
        private boolean downPressed = false;

        private int jumpPower = 34;

        private int stepCount = 0;
        private int stateStartTime = 0;

        private boolean isSlashing = false;
        private boolean useAltSlash = false;
        private int lastSlashEndTime = -100;
        private static final int COMBO_TOLERANCE = 20;
        private KnightSlash currentSlashBox = null;

        public KnightController(Knight knight) {
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

            // Look Up
            else if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                upPressed = true;
            }

            // Look Down
            else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                downPressed = true;
            }

            // Jump
            else if (code == KeyEvent.VK_SPACE) {
                knight.jump(jumpPower);
            }
        }

        // IS a Key Released
        @Override
        public void keyReleased(KeyEvent e) {
            int code = e.getKeyCode();

            // Stop Going Left
            if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
                leftPressed = false;
                startMoving();
            }

            // Stop Going Right
            else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
                rightPressed = false;
                startMoving();
            }

            // Stop Looking Up
            else if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                upPressed = false;
            }

            // Stop Looking Down
            else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                downPressed = false;
            }

            // Jump Control
            else if (code == KeyEvent.VK_SPACE) {
                Vec2 v = knight.getLinearVelocity();

                // If let go of space bar and going up slow down
                if (v.y > 0.1f) {
                    knight.setLinearVelocity(new Vec2(v.x, v.y * 0.5f));
                }
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
            if (knight.knightGetHealth() <= 0) {
                newState = "death";

            }else if (isSlashing) {
                if (upPressed) {
                    newState = "upSlash";

                } else if (downPressed && velocity.y != 0) { // Only while in air
                    newState = "downSlash";

                } else {
                    newState = "slash";
                }

            }else if (velocity.y > 0.001) {
                newState = "jump";

            } else if (velocity.y < -0.001) {
                newState = "fall";

            } else if (Math.abs(velocity.x) > 0.1) {
                newState = "walk";

            } else if (upPressed == true) {
                newState = "lookUp";

            } else if (downPressed == true) {
                newState = "lookDown";

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
                        img.setOffset(new Vec2(0, 0.2f));
                        break;

                    case "upSlash":
                        img = upSlash(timeInState);
                        img.setOffset(new Vec2(0, 0.2f));
                        break;

                    case "downSlash":
                        img = downSlash(timeInState);
                        img.setOffset(new Vec2(0, 0.2f));
                        break;

                    case "jump":
                        img = jump(timeInState);
                        img.setOffset(new Vec2(0, 0.2f));
                        break;

                    case "fall":
                        img = fall();
                        img.setOffset(new Vec2(0, 0.2f));
                        break;

                    case "walk":
                        img = walk();
                        img.setOffset(new Vec2(0, 0.2f));
                        break;

                    case "lookUp":
                        img = lookUp(timeInState);
                        img.setOffset(new Vec2(0, 0.2f));
                        break;

                    case "lookDown":
                        img = lookDown(timeInState);
                        img.setOffset(new Vec2(0, 0.2f));
                        break;

                    case "death":
                        img = die(timeInState);
                        img.setOffset(new Vec2(0, 3.0f));
                        break;

                    default: // Idle
                        img = idle();
                        img.setOffset(new Vec2(0, 0.2f));
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
            return state.equals("walk") || state.equals("fall") || state.equals("idle") ||
                    state.equals("jump") || state.equals("slash") ||
                    state.equals("lookUp") || state.equals("lookDown") ||
                    state.equals("upSlash") || state.equals("downSlash") ||
                    state.equals("death");
        }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // Action Methods (to be called in the switch case)


        // Slashing
        public AttachedImage slash (int timeInState){
            BodyImage[] currentSlashAnim;
            BodyImage[] currentEffectAnim;

            // Use Alternate Slash
            if (useAltSlash) {
                currentSlashAnim = knightSlashAltImages;
                currentEffectAnim = knightSlashEffectAltImages;

            // Use Normal Slash
            } else {
                currentSlashAnim = knightSlashImages;
                currentEffectAnim = knightSlashEffectImages;
            }

            // Combo Calculation
            int slashIndex = timeInState / 2;

            if (slashIndex >= currentSlashAnim.length) {
                isSlashing = false;

                lastSlashEndTime = stepCount; // How long in-between each slash

                slashIndex = currentSlashAnim.length - 1;
            }

            // Add The Main Body
            AttachedImage img = knight.addImage(currentSlashAnim[slashIndex]);

            // Add The Effect
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



        // UP SLASH
        public AttachedImage upSlash(int timeInState) {

            int slashIndex = timeInState / 2;
            if (slashIndex >= knightUpSlashImages.length) {
                isSlashing = false;
                lastSlashEndTime = stepCount;
                slashIndex = knightUpSlashImages.length - 1;
            }

            // Add The Main Body
            AttachedImage img = knight.addImage(knightUpSlashImages[slashIndex]);

            // Add The Effect
            if (slashIndex > 0) {
                int effectIndex = slashIndex - 1;
                if (effectIndex < knightUpSlashEffectImages.length) {
                    AttachedImage effectImg = knight.addImage(knightUpSlashEffectImages[effectIndex]);

                    effectImg.setOffset(new Vec2(0, 1.5f));

                    if (facingRight) effectImg.flipHorizontal();
                }
            }
            return img;
        }



        // DOWN SLASH
        public AttachedImage downSlash(int timeInState) {

            int slashIndex = timeInState / 2;
            if (slashIndex >= knightDownSlashImages.length) {
                isSlashing = false;
                lastSlashEndTime = stepCount;
                slashIndex = knightDownSlashImages.length - 1;
            }

            AttachedImage img = knight.addImage(knightDownSlashImages[slashIndex]);

            if (slashIndex > 0) {
                int effectIndex = slashIndex - 1;
                if (effectIndex < knightDownSlashEffectImages.length) {
                    AttachedImage effectImg = knight.addImage(knightDownSlashEffectImages[effectIndex]);

                    effectImg.setOffset(new Vec2(0, -2.0f));

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



        // Look Up
        public AttachedImage lookUp (int timeInState){
            int frameIndex = timeInState / 10;
            int indexToRender;

            if (frameIndex == 0) { // If first frame
                indexToRender = 0; // Play it once
            }
            else {
                int loopLength = knightLookUpImages.length - 1;

                // (rawIndex - 1) -> Shifts time so 0 is the start of the loop
                // % loopLength   -> Cycles through the remaining frames
                // + 1            -> Shifts the result back up to skip Frame 0
                indexToRender = 1 + ((frameIndex - 1) % loopLength);
            }

            return knight.addImage(knightLookUpImages[indexToRender]);
        }



        // Look down
        public AttachedImage lookDown (int timeInState){
            int knightLookDownIndex = (stepCount / 10) % knightLookDownImages.length;

            int frameIndex = timeInState / 10;
            int indexToRender;

            if (frameIndex == 0) { // If first frame
                indexToRender = 0; // Play it once
            }
            else {
                int loopLength = knightLookDownImages.length - 1;

                // (rawIndex - 1) -> Shifts time so 0 is the start of the loop
                // % loopLength   -> Cycles through the remaining frames
                // + 1            -> Shifts the result back up to skip Frame 0
                indexToRender = 1 + ((frameIndex - 1) % loopLength);
            }
            return knight.addImage(knightLookDownImages[indexToRender]);
        }


        // Idle
        public AttachedImage idle (){
            int knightIdleIndex = (stepCount / 10) % knightIdleImages.length;
            return knight.addImage(knightIdleImages[knightIdleIndex]);
        }

        // Die
        public AttachedImage die (int timeInState){
            int frameIndex = timeInState / 5;

            knight.stopWalking();
            knight.setLinearVelocity(new Vec2(0,0)); // Stops movment

            knight.removeAllCollisionListeners(); // Stops Colision
            knight.setGravityScale(0); // Stops Gravity

            knight.removeAllCollisionListeners();



            // Death Animation
            if (frameIndex >= knightDeathImages.length) {
                frameIndex = knightDeathImages.length - 1;
                knight.destroy();
            }

            return knight.addImage(knightDeathImages[frameIndex]);
        }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // SLASH HITBOX

        private void updateSlashHitbox(String newState) {

            boolean isAnySlash = newState.equals("slash") || newState.equals("upSlash") || newState.equals("downSlash");
            boolean wasAnySlash = currentState.equals("slash") || currentState.equals("upSlash") || currentState.equals("downSlash");

            boolean justStarted = isAnySlash && !wasAnySlash;
            boolean justStopped = !isAnySlash && wasAnySlash;

            // Create Hitbox
            if (justStarted) {
                // Set Default
                currentSlashBox = new KnightSlash(knight.getWorld(), knight.getPosition(), newState, facingRight, knight);
            }

            // Destroy Hitbox When Finished
            if (justStopped && currentSlashBox != null) {
                currentSlashBox.destroy();
                currentSlashBox = null;
            }

            // Move Hitbox
            if (currentSlashBox != null) {
                float offsetX = 0;
                float offsetY = 0;

                if (newState.equals("upSlash")) {
                    offsetX = -0.5f;
                    offsetY = 1.8f;
                }

                else if (newState.equals("downSlash")) {
                    offsetX = 0.5f;
                    offsetY = -2.2f;
                }

                else {

                    // Normal Slash
                    if (!useAltSlash) {
                        offsetX = facingRight ? 2.1f : -2.1f;
                        offsetY = facingRight ? 0.1f : -1.2f;

                    } else {
                        offsetX = facingRight ? 2.9f : -2.9f;
                        offsetY = facingRight ? 0.1f : -1.2f;
                    }
                }

                Vec2 spawnPos = knight.getPosition().add(new Vec2(offsetX, offsetY));

                currentSlashBox.setPosition(spawnPos) ;
            }
        }




















        @Override
        public void postStep (StepEvent stepEvent){
        }




    }