package com.poizon.engine.audio;

import com.poizon.engine.exceptions.ExceptionLogger;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Handles sound file using javax sound.
 *
 * @author Jose Ulloa
 */
public class SoundClip implements ISound {
    private Clip clip;
    private FloatControl gainControl;

    public SoundClip(String path) {
        try {
            InputStream audioSrc = SoundClip.class.getResourceAsStream(path);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
            AudioFormat baseFormat = audioInputStream.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                                                        baseFormat.getSampleRate(),
                                                        16,
                                                        baseFormat.getChannels(),
                                                        baseFormat.getChannels() * 2,
                                                        baseFormat.getSampleRate(),
                                              false);

            AudioInputStream decodedAudioInputStream = AudioSystem.getAudioInputStream(decodeFormat, audioInputStream);
            clip = AudioSystem.getClip();
            clip.open(decodedAudioInputStream);

            gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            ExceptionLogger.log(e);
        }
    }

    @Override
    public void play() {
        if(clip == null) return;

        stop();
        clip.setFramePosition(0);
        while (!clip.isRunning()) {
            clip.start();
        }
    }

    @Override
    public void stop() {
        if(clip.isRunning()) {
            clip.stop();
        }
    }

    @Override
    public void close() {
        stop();
        clip.drain();
        clip.close();
    }

    @Override
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        play();
    }

    @Override
    public void setVolume(float value) {
        gainControl.setValue(value);
    }

    @Override
    public boolean isRunning() {
        return clip.isRunning();
    }
}
