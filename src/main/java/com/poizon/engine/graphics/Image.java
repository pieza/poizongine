package com.poizon.engine.graphics;

import com.poizon.engine.exceptions.ExceptionLogger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Handles images files to render.
 *
 * @author Jose Ulloa
 */
public class Image {
    private int width, height;
    private int[] pixels;
    private boolean alpha = false;
    private int lightBlock = Light.NONE;

    public Image(String path) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(Image.class.getResourceAsStream(path));
        } catch (IOException e) {
            ExceptionLogger.log(e);
        }

        width = image.getWidth();
        height = image.getHeight();
        pixels = image.getRGB(0, 0, width, height, null, 0, width);
    }

    public Image(int[] pixels, int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getLightBlock() {
        return lightBlock;
    }

    public void setLightBlock(int lightBlock) {
        this.lightBlock = lightBlock;
    }


    public boolean isAlpha() {
        return alpha;
    }

    public void setAlpha(boolean alpha) {
        this.alpha = alpha;
    }
}
