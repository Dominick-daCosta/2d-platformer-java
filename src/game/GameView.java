package game;

import city.cs.engine.*;
import game.knight.Knight;
import java.awt.*;
import static game.UIAssets.UIFrameImage;

public class GameView extends UserView {

    private Knight knight;
    private int heartWidth = 47;
    private int heartHeight = 63;
    private int spacing = -15;

    public GameView(World world, Knight knight, int width, int height) {
        super(world, width, height);
        this.knight = knight;
    }

    @Override
    protected void paintForeground(Graphics2D g) {

        int currentHealth = knight.knightGetHealth();
        int maxHealth = 5;



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
    }
}