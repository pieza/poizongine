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
    private boolean isDebug;

    public Settings() {
        this.screenHeight = 900;
        this.screenWidth = 1600;
        this.frameRate = 60;
        this.scale = 1f;
        this.isDebug = false;
    }

    public Settings(int screenHeight, int screenWidth, int frameRate, float scale, boolean isDebug) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.frameRate = frameRate;
        this.scale = scale;
        this.isDebug = isDebug;
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

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }
}
