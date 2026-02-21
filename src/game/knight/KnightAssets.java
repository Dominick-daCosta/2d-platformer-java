package game.knight;

import city.cs.engine.*;

import java.awt.event.KeyAdapter;

public class KnightAssets {

    // IMAGES

    // Idle
    public static final BodyImage[] IdleImages = {
            new BodyImage("data/knight sprites/idle 1.png", 6.0f),
            new BodyImage("data/knight sprites/idle 2.png", 6.0f),
            new BodyImage("data/knight sprites/idle 3.png", 6.0f),
            new BodyImage("data/knight sprites/idle 4.png", 6.0f),
            new BodyImage("data/knight sprites/idle 5.png", 6.0f),
            new BodyImage("data/knight sprites/idle 6.png", 6.0f),
            new BodyImage("data/knight sprites/idle 7.png", 6.0f),
            new BodyImage("data/knight sprites/idle 8.png", 6.0f),
            new BodyImage("data/knight sprites/idle 9.png", 6.0f)
    };


    // Jump
    public static final BodyImage[] JumpImages = {
            new BodyImage("data/knight sprites/jump 1.png", 6.0f),
            new BodyImage("data/knight sprites/jump 2.png", 6.0f),
            new BodyImage("data/knight sprites/jump 3.png", 6.0f),
            new BodyImage("data/knight sprites/jump 4.png", 6.0f),
            new BodyImage("data/knight sprites/jump 5.png", 6.0f),
            new BodyImage("data/knight sprites/jump 6.png", 6.0f),
    };


    // Walk
    public static final BodyImage[] WalkImages = {
            new BodyImage("data/knight sprites/walk 1.png", 6.0f),
            new BodyImage("data/knight sprites/walk 2.png", 6.0f),
            new BodyImage("data/knight sprites/walk 3.png", 6.0f),
            new BodyImage("data/knight sprites/walk 4.png", 6.0f),
            new BodyImage("data/knight sprites/walk 5.png", 6.0f),
            new BodyImage("data/knight sprites/walk 6.png", 6.0f),
            new BodyImage("data/knight sprites/walk 7.png", 6.0f)
    };

    // Dash
    public static final BodyImage[] DashImages = {
            new BodyImage("data/knight sprites/dash 1.png", 6.0f),
            new BodyImage("data/knight sprites/dash 2.png", 6.0f),
            new BodyImage("data/knight sprites/dash 3.png", 6.0f),
            new BodyImage("data/knight sprites/dash 4.png", 6.0f),
            new BodyImage("data/knight sprites/dash 5.png", 6.0f),
            new BodyImage("data/knight sprites/dash 6.png", 6.0f),
            new BodyImage("data/knight sprites/dash 7.png", 6.0f),
    };


    // Fall
    public static final BodyImage[] FallImages = {
            new BodyImage("data/knight sprites/fall 1.png", 6.0f),
            new BodyImage("data/knight sprites/fall 2.png", 6.0f),
            new BodyImage("data/knight sprites/fall 3.png", 6.0f),
            new BodyImage("data/knight sprites/fall 4.png", 6.0f),
            new BodyImage("data/knight sprites/fall 5.png", 6.0f),
            new BodyImage("data/knight sprites/fall 6.png", 6.0f),
    };


    // Look Up
    public static final BodyImage[] LookUpImages = {
            new BodyImage("data/knight sprites/look up 1.png", 6.0f),
            new BodyImage("data/knight sprites/look up 2.png", 6.0f),
            new BodyImage("data/knight sprites/look up 3.png", 6.0f),
            new BodyImage("data/knight sprites/look up 4.png", 6.0f),
            new BodyImage("data/knight sprites/look up 5.png", 6.0f),
            new BodyImage("data/knight sprites/look up 6.png", 6.0f),
    };


    // Look Down
    public static final BodyImage[] LookDownImages = {
            new BodyImage("data/knight sprites/look down 1.png", 6.0f),
            new BodyImage("data/knight sprites/look down 2.png", 6.0f),
            new BodyImage("data/knight sprites/look down 3.png", 6.0f),
            new BodyImage("data/knight sprites/look down 4.png", 6.0f),
            new BodyImage("data/knight sprites/look down 5.png", 6.0f),
            new BodyImage("data/knight sprites/look down 6.png", 6.0f),
    };


    // Slash
    public static final BodyImage[] SlashImages = {
            new BodyImage("data/knight sprites/slash 1.png", 6.0f),
            new BodyImage("data/knight sprites/slash 2.png", 6.0f),
            new BodyImage("data/knight sprites/slash 3.png", 6.0f),
            new BodyImage("data/knight sprites/slash 4.png", 6.0f),
            new BodyImage("data/knight sprites/slash 5.png", 6.0f),
            new BodyImage("data/knight sprites/slash 6.png", 6.0f),
    };


    // Slash Effect
    public static final BodyImage[] SlashEffectImages = {
            new BodyImage("data/knight sprites/slash effect 1.png", 6.0f),
            new BodyImage("data/knight sprites/slash effect 2.png", 6.0f),
            new BodyImage("data/knight sprites/slash effect 3.png", 6.0f),
            new BodyImage("data/knight sprites/slash effect 4.png", 6.0f),
            new BodyImage("data/knight sprites/slash effect 5.png", 6.0f),
            new BodyImage("data/knight sprites/slash effect 6.png", 6.0f),
    };


    // Alternate Slash
    public static final BodyImage[] SlashAltImages = {
            new BodyImage("data/knight sprites/slash alt 1.png", 6.0f),
            new BodyImage("data/knight sprites/slash alt 2.png", 6.0f),
            new BodyImage("data/knight sprites/slash alt 3.png", 6.0f),
            new BodyImage("data/knight sprites/slash alt 4.png", 6.0f),
            new BodyImage("data/knight sprites/slash alt 5.png", 6.0f),
            new BodyImage("data/knight sprites/slash alt 6.png", 6.0f),
    };


    // Alternate Slash Effect
    public static final BodyImage[] SlashEffectAltImages = {
            new BodyImage("data/knight sprites/slash effect alt 1.png", 6.0f),
            new BodyImage("data/knight sprites/slash effect alt 2.png", 6.0f),
            new BodyImage("data/knight sprites/slash effect alt 3.png", 6.0f),
            new BodyImage("data/knight sprites/slash effect alt 4.png", 6.0f),
            new BodyImage("data/knight sprites/slash effect alt 5.png", 6.0f),
            new BodyImage("data/knight sprites/slash effect alt 6.png", 6.0f),
    };


    // Down Slash
    public static final BodyImage[] DownSlashImages = {
            new BodyImage("data/knight sprites/down slash 1.png", 6.0f),
            new BodyImage("data/knight sprites/down slash 2.png", 6.0f),
            new BodyImage("data/knight sprites/down slash 3.png", 6.0f),
            new BodyImage("data/knight sprites/down slash 4.png", 6.0f),
            new BodyImage("data/knight sprites/down slash 5.png", 6.0f),
            new BodyImage("data/knight sprites/down slash 6.png", 6.0f),
    };


    // Down Slash Effect
    public static final BodyImage[] DownSlashEffectImages = {
            new BodyImage("data/knight sprites/down slash effect 1.png", 6.0f),
            new BodyImage("data/knight sprites/down slash effect 2.png", 6.0f),
            new BodyImage("data/knight sprites/down slash effect 3.png", 6.0f),
            new BodyImage("data/knight sprites/down slash effect 4.png", 6.0f),
            new BodyImage("data/knight sprites/down slash effect 5.png", 6.0f),
            new BodyImage("data/knight sprites/down slash effect 6.png", 6.0f),
    };


    // Up Slash
    public static final BodyImage[] UpSlashImages = {
            new BodyImage("data/knight sprites/up slash 1.png", 6.0f),
            new BodyImage("data/knight sprites/up slash 2.png", 6.0f),
            new BodyImage("data/knight sprites/up slash 3.png", 6.0f),
            new BodyImage("data/knight sprites/up slash 4.png", 6.0f),
            new BodyImage("data/knight sprites/up slash 5.png", 6.0f),
            new BodyImage("data/knight sprites/up slash 6.png", 6.0f),
    };


    //Up Slash Effect
    public static final BodyImage[] UpSlashEffectImages = {
            new BodyImage("data/knight sprites/up slash effect 1.png", 6.0f),
            new BodyImage("data/knight sprites/up slash effect 2.png", 6.0f),
            new BodyImage("data/knight sprites/up slash effect 3.png", 6.0f),
            new BodyImage("data/knight sprites/up slash effect 4.png", 6.0f),
            new BodyImage("data/knight sprites/up slash effect 5.png", 6.0f),
            new BodyImage("data/knight sprites/up slash effect 6.png", 6.0f),
    };

    // Healing
    public static final BodyImage[] HealingImages = {
            new BodyImage("data/knight sprites/healing 1.png", 6.0f),
            new BodyImage("data/knight sprites/healing 2.png", 6.0f),
            new BodyImage("data/knight sprites/healing 3.png", 6.0f),
            new BodyImage("data/knight sprites/healing 4.png", 6.0f),
            new BodyImage("data/knight sprites/healing 5.png", 6.0f),
            new BodyImage("data/knight sprites/healing 6.png", 6.0f),
            new BodyImage("data/knight sprites/healing end 1.png", 6.0f),
            new BodyImage("data/knight sprites/healing end 2.png", 6.0f),
            new BodyImage("data/knight sprites/healing end 3.png", 6.0f),
    };


    //Death
    public static final BodyImage[] DeathImages = {
            new BodyImage("data/knight sprites/death 1.png", 6.0f),
            new BodyImage("data/knight sprites/death 2.png", 6.0f),
            new BodyImage("data/knight sprites/death 3.png", 6.0f),
            new BodyImage("data/knight sprites/death 4.png", 6.0f),
            new BodyImage("data/knight sprites/death 5.png", 6.0f),
            new BodyImage("data/knight sprites/death 6.png", 6.0f),
            new BodyImage("data/knight sprites/death 7.png", 6.0f),
            new BodyImage("data/knight sprites/death 8.png", 6.0f),
            new BodyImage("data/knight sprites/death 9.png", 6.0f),
            new BodyImage("data/knight sprites/death 10.png", 6.0f),
            new BodyImage("data/knight sprites/death 11.png", 6.0f),
            new BodyImage("data/knight sprites/death 12.png", 6.0f),
            new BodyImage("data/knight sprites/death 13.png", 6.0f),
            new BodyImage("data/knight sprites/death 14.png", 6.0f),
            new BodyImage("data/knight sprites/death 15.png", 6.0f),
            new BodyImage("data/knight sprites/death 16.png", 6.0f),
            new BodyImage("data/knight sprites/death 17.png", 6.0f),
            new BodyImage("data/knight sprites/death 18.png", 6.0f),
    };



}