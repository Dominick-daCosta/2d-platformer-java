package game;

import city.cs.engine.BodyImage;
import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class SharedAssets {

    public static final BodyImage[] damageEffectImages = {
            new BodyImage("data/images/damage effect 1.png", 4.0f),
            new BodyImage("data/images/damage effect 2.png", 4.0f),
            new BodyImage("data/images/damage effect 3.png", 4.0f),
            new BodyImage("data/images/damage effect 4.png", 4.0f),
            new BodyImage("data/images/damage effect 5.png", 4.0f),
            new BodyImage("data/images/damage effect 6.png", 4.0f),
    };

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Sounds

    public static SoundClip damageSound,deathSound;

    // This static block runs once when the game starts to load the audio into memory
    static {
        try {
            damageSound = new SoundClip("data/sounds/enemydamage.wav");
            deathSound = new SoundClip("data/sounds/enemydeath.wav");

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error loading damage sound: " + e);
        }
    }

    public static void stopAllSounds() {
        if (damageSound != null) damageSound.stop();
        if (deathSound != null) deathSound.stop();
    }


}
