package game;

import city.cs.engine.*;

import java.awt.event.KeyAdapter;

public class KnightAssets extends KeyAdapter {

    // IMAGES

    protected BodyImage[] knightIdleImages = {
            new BodyImage("data/knight sprites/idle 1.png", 5.5f),
            new BodyImage("data/knight sprites/idle 2.png", 5.5f),
            new BodyImage("data/knight sprites/idle 3.png", 5.5f),
            new BodyImage("data/knight sprites/idle 4.png", 5.5f),
            new BodyImage("data/knight sprites/idle 5.png", 5.5f),
            new BodyImage("data/knight sprites/idle 6.png", 5.5f),
            new BodyImage("data/knight sprites/idle 7.png", 5.5f),
            new BodyImage("data/knight sprites/idle 8.png", 5.5f),
            new BodyImage("data/knight sprites/idle 9.png", 5.5f)
    };

    protected BodyImage[] knightJumpImages = {
            new BodyImage("data/knight sprites/jump 1.png", 5.5f),
            new BodyImage("data/knight sprites/jump 2.png", 5.5f),
            new BodyImage("data/knight sprites/jump 3.png", 5.5f),
            new BodyImage("data/knight sprites/jump 4.png", 5.5f),
            new BodyImage("data/knight sprites/jump 5.png", 5.5f),
            new BodyImage("data/knight sprites/jump 6.png", 5.5f),
    };

    protected BodyImage[] knightWalkImages = {
            new BodyImage("data/knight sprites/walk 1.png", 5.5f),
            new BodyImage("data/knight sprites/walk 2.png", 5.5f),
            new BodyImage("data/knight sprites/walk 3.png", 5.5f),
            new BodyImage("data/knight sprites/walk 4.png", 5.5f),
            new BodyImage("data/knight sprites/walk 5.png", 5.5f),
            new BodyImage("data/knight sprites/walk 6.png", 5.5f),
            new BodyImage("data/knight sprites/walk 7.png", 5.5f)
    };

    protected BodyImage[] knightFallImages = {
            new BodyImage("data/knight sprites/fall 1.png", 5.5f),
            new BodyImage("data/knight sprites/fall 2.png", 5.5f),
            new BodyImage("data/knight sprites/fall 3.png", 5.5f),
            new BodyImage("data/knight sprites/fall 4.png", 5.5f),
            new BodyImage("data/knight sprites/fall 5.png", 5.5f),
            new BodyImage("data/knight sprites/fall 6.png", 5.5f),
    };

    protected BodyImage[] knightSlashImages = {
            new BodyImage("data/knight sprites/slash 1.png", 5.5f),
            new BodyImage("data/knight sprites/slash 2.png", 5.5f),
            new BodyImage("data/knight sprites/slash 3.png", 5.5f),
            new BodyImage("data/knight sprites/slash 4.png", 5.5f),
            new BodyImage("data/knight sprites/slash 5.png", 5.5f),
            new BodyImage("data/knight sprites/slash 6.png", 5.5f),
    };

    protected BodyImage[] knightSlashEffectImages = {
            new BodyImage("data/knight sprites/slash effect 1.png", 5.5f),
            new BodyImage("data/knight sprites/slash effect 2.png", 5.5f),
            new BodyImage("data/knight sprites/slash effect 3.png", 5.5f),
            new BodyImage("data/knight sprites/slash effect 4.png", 5.5f),
            new BodyImage("data/knight sprites/slash effect 5.png", 5.5f),
            new BodyImage("data/knight sprites/slash effect 6.png", 5.5f),
    };

    protected BodyImage[] knightSlashAltImages = {
            new BodyImage("data/knight sprites/slash alt 1.png", 5.5f),
            new BodyImage("data/knight sprites/slash alt 2.png", 5.5f),
            new BodyImage("data/knight sprites/slash alt 3.png", 5.5f),
            new BodyImage("data/knight sprites/slash alt 4.png", 5.5f),
            new BodyImage("data/knight sprites/slash alt 5.png", 5.5f),
            new BodyImage("data/knight sprites/slash alt 6.png", 5.5f),
    };

    protected BodyImage[] knightSlashEffectAltImages = {
            new BodyImage("data/knight sprites/slash effect alt 1.png", 5.5f),
            new BodyImage("data/knight sprites/slash effect alt 2.png", 5.5f),
            new BodyImage("data/knight sprites/slash effect alt 3.png", 5.5f),
            new BodyImage("data/knight sprites/slash effect alt 4.png", 5.5f),
            new BodyImage("data/knight sprites/slash effect alt 5.png", 5.5f),
            new BodyImage("data/knight sprites/slash effect alt 6.png", 5.5f),
    };

    protected BodyImage[] knightDownSlashImages = {
            new BodyImage("data/knight sprites/down slash 1.png", 5.5f),
            new BodyImage("data/knight sprites/down slash 2.png", 5.5f),
            new BodyImage("data/knight sprites/down slash 3.png", 5.5f),
            new BodyImage("data/knight sprites/down slash 4.png", 5.5f),
            new BodyImage("data/knight sprites/down slash 5.png", 5.5f),
            new BodyImage("data/knight sprites/down slash 6.png", 5.5f),
    };

    protected BodyImage[] knightDownSlashEffectImages = {
            new BodyImage("data/knight sprites/down slash effect 1.png", 5.5f),
            new BodyImage("data/knight sprites/down slash effect 2.png", 5.5f),
            new BodyImage("data/knight sprites/down slash effect 3.png", 5.5f),
            new BodyImage("data/knight sprites/down slash effect 4.png", 5.5f),
            new BodyImage("data/knight sprites/down slash effect 5.png", 5.5f),
            new BodyImage("data/knight sprites/down slash effect 6.png", 5.5f),
    };

    protected BodyImage[] knightUpSlashImages = {
            new BodyImage("data/knight sprites/up slash 1.png", 5.5f),
            new BodyImage("data/knight sprites/up slash 2.png", 5.5f),
            new BodyImage("data/knight sprites/up slash 3.png", 5.5f),
            new BodyImage("data/knight sprites/up slash 4.png", 5.5f),
            new BodyImage("data/knight sprites/up slash 5.png", 5.5f),
            new BodyImage("data/knight sprites/up slash 6.png", 5.5f),
    };

    protected BodyImage[] knightUpSlashEffectImages = {
            new BodyImage("data/knight sprites/up slash effect 1.png", 5.5f),
            new BodyImage("data/knight sprites/up slash effect 2.png", 5.5f),
            new BodyImage("data/knight sprites/up slash effect 3.png", 5.5f),
            new BodyImage("data/knight sprites/up slash effect 4.png", 5.5f),
            new BodyImage("data/knight sprites/up slash effect 5.png", 5.5f),
            new BodyImage("data/knight sprites/up slash effect 6.png", 5.5f),
    };

 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




}