package com.poizon.engine.render;

import com.poizon.engine.config.Settings;
import com.poizon.engine.graphics.Image;
import com.poizon.engine.graphics.ImageTile;
import com.poizon.engine.windows.IWindow;

import java.awt.image.DataBufferInt;

/**
 * @author Jose Ulloa
 */
public class Renderer implements IRenderer {
    private int pixelWidth;
    private int pixelHeight;
    private int[] pixels;

    public Renderer(IWindow window, Settings settings) {
        pixelWidth = settings.getScreenWidth();
        pixelHeight = settings.getScreenHeight();

        pixels = ((DataBufferInt)window.getImage().getRaster().getDataBuffer()).getData();
    }

    private void setPixel(int x, int y, int value) {
        if((x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) || value == 0xffff00ff ) {
            return;
        }

        pixels[x + y * pixelWidth] = value;
    }

    @Override
    public void clear(){
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    @Override
    public void drawImage(Image image, int offX, int offY) {
        // do not render if
        if(offX < -image.getWidth()) return;
        if(offY < -image.getHeight()) return;
        if(offX >= pixelWidth) return;
        if(offY >= pixelHeight) return;

        int newX= 0;
        int newY = 0;
        int newWidth = image.getWidth();
        int newHeight = image.getHeight();

        if(offX < 0) newX -= offX;
        if(offY < 0) newY -= offY;
        if(newWidth + offX >= pixelWidth) { newWidth -= newWidth + offX - pixelWidth; }
        if(newHeight + offY >= pixelHeight) { newHeight -= newHeight + offY - pixelHeight; }

        for (int y = newY; y < newHeight; y++) {
            for (int x = newX; x < newWidth; x++) {
                setPixel(x + offX, y + offY, image.getPixels()[x + y * image.getWidth()]);
            }
        }
    }

    @Override
    public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY) {
        // do not render if
        if(offX < -image.getTileWidth()) return;
        if(offY < -image.getTileHeight()) return;
        if(offX >= pixelWidth) return;
        if(offY >= pixelHeight) return;

        int newX= 0;
        int newY = 0;
        int newWidth = image.getWidth();
        int newHeight = image.getHeight();

        if(offX < 0) newX -= offX;
        if(offY < 0) newY -= offY;
        if(newWidth + offX >= pixelWidth) { newWidth -= newWidth + offX - pixelWidth; }
        if(newHeight + offY >= pixelHeight) { newHeight -= newHeight + offY - pixelHeight; }

        for (int y = newY; y < newHeight; y++) {
            for (int x = newX; x < newWidth; x++) {
                setPixel(x + offX, y + offY, image.getPixels()[(x + tileX * image.getTileWidth()) + (y + tileY * image.getTileHeight()) * image.getWidth()]);
            }
        }
    }
}
