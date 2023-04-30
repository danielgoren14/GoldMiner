import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

public class MusicEffects {
    static Clip clip;
    static AudioInputStream sound;
    public static void setFile(String soundFileName) {
        try {
            File file = new File(soundFileName);
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void play() {
        clip.start();
    }
    public static void stop() throws IOException {
        sound.close();
        clip.close();
        clip.stop();
    }
    public static void playTransition(){
        setFile("src/SoundEffects/transitionSoundEffect.wav");
        play();
    }
    public static void playMoneySound(){
        setFile("src/SoundEffects/CashRegisterSoundEffect.wav");
        play();
    }

    public static void playTimeSound(){
        setFile("src/SoundEffects/Clock Ticking Sound Effect.wav");
        play();
    }
    public static void playDraggingRockSound(){
        setFile("src/SoundEffects/dragging Stone - sound effect.wav");
        play();
    }

}
