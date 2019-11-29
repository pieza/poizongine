package com.poizon.engine.config;

public class Settings {
    private int screenHeight;
    private int screenWidth;
    private int frameRate;
    private float scale;

    public Settings() {
        this.screenHeight = 240;
        this.screenWidth = 320;
        this.frameRate = 60;
        this.scale = 1f;
    }

    public Settings(int screenHeight, int screenWidth, int frameRate, float scale) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.frameRate = frameRate;
        this.scale = scale;
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
}
