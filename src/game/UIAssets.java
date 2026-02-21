package game;

import javax.swing.*;
import java.awt.*;

public class UIAssets {

    // Empty Heart
    public static final Image UIFrameImage = new ImageIcon("data/knight sprites/health/UI frame.png").getImage();


    // HEALTH


    // Break Heart
    public static final Image[] damageImages = {
            new ImageIcon("data/knight sprites/health/damage 1.png").getImage(),
            new ImageIcon("data/knight sprites/health/damage 2.png").getImage(),
            new ImageIcon("data/knight sprites/health/damage 3.png").getImage(),
            new ImageIcon("data/knight sprites/health/damage 4.png").getImage(),
            new ImageIcon("data/knight sprites/health/damage 5.png").getImage(),
            new ImageIcon("data/knight sprites/health/damage 6.png").getImage(),
    };


    // Fill Heart
    public static final Image[] healImages = {
            new ImageIcon("data/knight sprites/health/refill 1.png").getImage(),
            new ImageIcon("data/knight sprites/health/refill 2.png").getImage(),
            new ImageIcon("data/knight sprites/health/refill 3.png").getImage(),
            new ImageIcon("data/knight sprites/health/refill 4.png").getImage(),
            new ImageIcon("data/knight sprites/health/refill 5.png").getImage(),
    };

    // Full Heart
    public static final Image fullHealthImage = new ImageIcon("data/knight sprites/health/full health.png").getImage();

    // Empty Heart
    public static final Image emptyHealthImage = new ImageIcon("data/knight sprites/health/empty health.png").getImage();


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // SOUL


    // Soul Full
    public static final Image[] soulFullImages = {
            new ImageIcon("data/knight sprites/health/full 1.png").getImage(),
            new ImageIcon("data/knight sprites/health/full 2.png").getImage(),
            new ImageIcon("data/knight sprites/health/full 3.png").getImage(),
            new ImageIcon("data/knight sprites/health/full 4.png").getImage(),
            new ImageIcon("data/knight sprites/health/full 5.png").getImage(),
            new ImageIcon("data/knight sprites/health/full 6.png").getImage(),
            new ImageIcon("data/knight sprites/health/full 7.png").getImage(),
            new ImageIcon("data/knight sprites/health/full 8.png").getImage(),
            new ImageIcon("data/knight sprites/health/full 9.png").getImage(),
            new ImageIcon("data/knight sprites/health/full 10.png").getImage(),
    };


    // Soul Three Quarters
    public static final Image[] soulThreeQuarterImages = {
            new ImageIcon("data/knight sprites/health/three quarter 1.png").getImage(),
            new ImageIcon("data/knight sprites/health/three quarter 2.png").getImage(),
            new ImageIcon("data/knight sprites/health/three quarter 3.png").getImage(),
            new ImageIcon("data/knight sprites/health/three quarter 4.png").getImage(),
            new ImageIcon("data/knight sprites/health/three quarter 5.png").getImage(),
            new ImageIcon("data/knight sprites/health/three quarter 6.png").getImage(),
    };


    // Soul Half
    public static final Image[] soulHalfImages = {
            new ImageIcon("data/knight sprites/health/half 1.png").getImage(),
            new ImageIcon("data/knight sprites/health/half 2.png").getImage(),
            new ImageIcon("data/knight sprites/health/half 3.png").getImage(),
            new ImageIcon("data/knight sprites/health/half 4.png").getImage(),
            new ImageIcon("data/knight sprites/health/half 5.png").getImage(),
            new ImageIcon("data/knight sprites/health/half 6.png").getImage(),
    };


    // Soul Quarter
    public static final Image[] soulQuarterImages = {
            new ImageIcon("data/knight sprites/health/quarter 1.png").getImage(),
            new ImageIcon("data/knight sprites/health/quarter 2.png").getImage(),
            new ImageIcon("data/knight sprites/health/quarter 3.png").getImage(),
            new ImageIcon("data/knight sprites/health/quarter 4.png").getImage(),
            new ImageIcon("data/knight sprites/health/quarter 5.png").getImage(),
            new ImageIcon("data/knight sprites/health/quarter 6.png").getImage(),
    };


    // Empty Soul
    public static final Image emptySoulImage = new ImageIcon("data/knight sprites/health/empty soul.png").getImage();

    // Full Soul Face
    public static final Image soulFaceImage = new ImageIcon("data/knight sprites/health/soul face.png").getImage();
}
