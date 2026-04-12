package game;

import city.cs.engine.*;
import game.knight.Knight;
import java.awt.*;
import static game.UIAssets.UIFrameImage;

public class GameView extends UserView {

    private final Knight knight;
    private final Game game;
    private final int heartWidth = 47;
    private final int heartHeight = 63;
    private final int spacing = -15;

    public GameView(Game game, World world, Knight knight, int width, int height) {
        super(world, width, height);
        this.game = game;
        this.knight = knight;
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        if (getWorld() instanceof game.levels.GameLevel) {
            Image bg = ((game.levels.GameLevel) getWorld()).getBackground();
            if (bg != null) {
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    @Override
    protected void paintForeground(Graphics2D g) {

        int currentHealth = knight.knightGetHealth();
        int maxHealth = 5;

        int currentSoul = knight.getSoul();


        g.drawImage(UIFrameImage, 10, 10, 129, 82, this);


        // Health Bar
        for (int i = 0; i < maxHealth; i++) {
            int x = 85 + (i * (heartWidth + spacing));
            int y = 15;

            Image imageToDraw;

            if (i < currentHealth) {
                imageToDraw = UIAssets.fullHealthImage;

            } else {
                imageToDraw = UIAssets.emptyHealthImage;
            }

            g.drawImage(imageToDraw, x, y, heartWidth, heartHeight, this);
        }

        // Soul sphere

        switch (currentSoul){

            case 4:
                g.drawImage(UIAssets.soulFullImages[4], -9, -1, 110, 120, this);
                //g.drawImage(UIAssets.soulFaceImage, 10, 10, 129, 82, this);
                break;

            case 3:
                g.drawImage(UIAssets.soulThreeQuarterImages[1], -9, -1, 110, 120, this);
                break;

            case 2:
                g.drawImage(UIAssets.soulHalfImages[1], -9, -1, 110, 120, this);
                break;

            case 1:
                g.drawImage(UIAssets.soulQuarterImages[1], -9, -1, 110, 120, this);
                break;

            default:
                g.drawImage(UIAssets.emptySoulImage, -9, -1, 110, 120, this);
                break;
        }


        // --- VICTORY SCREEN ---
        if (game.isGameWon()) {
            // Dark overlay
            g.setColor(new Color(0, 0, 0, 180));
            g.fillRect(0, 0, getWidth(), getHeight());

            // You Win Text
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 100));
            FontMetrics metrics = g.getFontMetrics();
            String text = "YOU WIN";
            int xPos = (getWidth() - metrics.stringWidth(text)) / 2;
            int yPos = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            
            g.drawString(text, xPos, yPos);
        }

        // --- TUTORIAL MESSAGE ---
        String tutorial = game.getTutorialMessage();
        if (tutorial != null) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            // Bottom left corner
            g.drawString(tutorial, 20, getHeight() - 30);
        }

    }
}