package com.poizon.engine.audio;

public interface ISound {
    void play();
    void stop();
    void close();
    void loop();
    void setVolume(float value);
    boolean isRunning();
}
