import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;

public enum AudioPlayer {
    BEGINNING("beginning.wav"),
    DOT("dot.wav"),
    CHOMP("chomp.wav"),
    DEATH("death.wav"),
    EATFRUIT("eatfruit.wav"),
    EATGHOST("eatghost.wav"),
    EXTRAPAC("extrapac.wav"),
    INTERMISSION("intermission.wav");

    private Clip clip;

    AudioPlayer(String soundFileName) {
        soundFileName = "./media/"+soundFileName;
        try {
            File soundFile = new File(soundFileName);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        Clip c = clip;
        if (c.isRunning()) {
            c.stop();
        }
        c.setFramePosition(0);
        c.start();
    }

    public boolean isPlaying() {
        Clip c = clip;
        return c.isRunning();
    }

    public void stop() {
        Clip c = clip;
        c.stop();
    }

    public void loop(int start, int end) {
        Clip c = clip;
        c.setLoopPoints(start , end);
        c.loop(c.LOOP_CONTINUOUSLY);
    }

    static void stopAll() {
        AudioPlayer sounds[] = AudioPlayer.values();
        for (AudioPlayer sound : sounds) {
            sound.stop();
        }
    }

    static void init() {
        //Construct All Enums
        values();
    }
}

