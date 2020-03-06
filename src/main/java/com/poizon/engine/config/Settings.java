package com.poizon.engine.config;

/**
 * Plain object that handles the settings of the game.
 *
 * @author Jose Ulloa
 */
public class Settings {
    private int screenHeight;
    private int screenWidth;
    private int frameRate;
    private float scale;
    private boolean fullScreen;
    private boolean lockFrameRate;
    private boolean isDebug;

    public Settings() {
        this.screenHeight = 270;
        this.screenWidth = 480;
        this.frameRate = 60;
        this.scale = 2f;
        this.fullScreen = false;
        this.lockFrameRate = true;
        this.isDebug = false;
    }

    public Settings(int screenHeight, int screenWidth, int frameRate, float scale, boolean fullScreen, boolean lockFrameRate, boolean isDebug) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.frameRate = frameRate;
        this.scale = scale;
        this.fullScreen = fullScreen;
        this.lockFrameRate = lockFrameRate;
        this.isDebug = isDebug;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "screenHeight=" + screenHeight +
                ", screenWidth=" + screenWidth +
                ", frameRate=" + frameRate +
                ", scale=" + scale +
                ", fullScreen=" + fullScreen +
                ", lockFrameRate=" + lockFrameRate +
                ", isDebug=" + isDebug +
                '}';
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public boolean isLockFrameRate() {
        return lockFrameRate;
    }

    public void setLockFrameRate(boolean lockFrameRate) {
        this.lockFrameRate = lockFrameRate;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }
}
