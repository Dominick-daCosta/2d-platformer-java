package game;

import city.cs.engine.*;
import game.knight.Knight;
import java.awt.*;
import static game.UIAssets.UIFrameImage;

public class GameView extends UserView {

    private final Knight knight;
    private final int heartWidth = 47;
    private final int heartHeight = 63;
    private final int spacing = -15;

    public GameView(World world, Knight knight, int width, int height) {
        super(world, width, height);
        this.knight = knight;
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







    }
}