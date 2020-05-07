package com.poizon.engine.render;

import com.poizon.engine.config.Settings;
import com.poizon.engine.graphics.Color;
import com.poizon.engine.graphics.Font;
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
    private int zDepth;
    private int[] pixels;
    private int[] zBuffer;
    private int[] lightMap;
    private int[] lightBlock;

    public Renderer(IWindow window, Settings settings) {
        pixelWidth = settings.getScreenWidth();
        pixelHeight = settings.getScreenHeight();

        pixels = ((DataBufferInt)window.getImage().getRaster().getDataBuffer()).getData();
        zBuffer = new int[pixels.length];
        lightMap = new int[pixels.length];
        lightBlock = new int[pixels.length];
    }

    private void process() {
        for (int i = 0; i < pixels.length; i++) {
            float red = ((lightMap[i] >> 16) & 0xff) / 255f;
            float green = ((lightMap[i] >> 8) & 0xff) / 255f;
            float blue = (lightMap[i] & 0xff) / 255f;

            pixels[i] =  ((int)(((pixels[i] >> 16) & 0xff) * red) << 16 | (int)(((pixels[i] >> 8) & 0xff) * green) << 8 | (int)((pixels[i] & 0xff) * blue));
        }
    }

    private void setPixel(int x, int y, int value) {
        int alpha = ((value >> 24) & 0xff);

        if((x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) || alpha == 0) {
            return;
        }

        if(zBuffer[x + y * pixelWidth] > zDepth) {
            return;
        }

        if(alpha == 255) {
            pixels[x + y * pixelWidth] = value;
        } else {
            int pixelColor =  pixels[x + y * pixelWidth];
            int newRed = ((pixelColor >> 16) & 0xff) - (int)((((pixelColor >> 16) & 0xff) - ((value >> 16) & 0xff)) * (alpha/255f));
            int newGreen = ((pixelColor >> 8) & 0xff) - (int)((((pixelColor >> 8) & 0xff) - ((value >> 8) & 0xff)) * (alpha/255f));
            int newBlue = (pixelColor & 0xff) - (int)(((pixelColor) & 0xff - (value & 0xff)) * (alpha/255f));

            pixels[x + y * pixelWidth] = (newRed << 16 | newGreen << 8 | newBlue);
        }


    }

    private void setLightMap(int x, int y, int value) {
        if(x < 0 || x >= pixelWidth || y < 0 || y>= pixelWidth) {
            return;
        }

        int baseColor = lightMap[x + y * pixelWidth];
        int finalColor = 0;

        int maxRed = Math.max((baseColor >> 16) & 0xff,  (value >> 16) & 0xff);
        int maxGreen = Math.max((baseColor >> 8) & 0xff,  (value >> 16) & 0xff);
        int maxBlue = Math.max((baseColor) & 0xff,  (value) & 0xff);

        lightMap[x + y * pixelWidth] = (maxRed << 16 | maxGreen << 8 | maxBlue);
    }

    @Override
    public void clear(){
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
            zBuffer[i] = 0;
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
    public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY ) {
        // do not render if
        if(offX < -image.getTileWidth()) return;
        if(offY < -image.getTileHeight()) return;
        if(offX >= pixelWidth) return;
        if(offY >= pixelHeight) return;

        int newX= 0;
        int newY = 0;
        int newWidth = image.getTileWidth();
        int newHeight = image.getTileHeight();

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

    @Override
    public void drawText(String text, Font font, int offX, int offY, int color) {
        text = text.toUpperCase();
        int offset = 0;

        for (int i = 0; i < text.length(); i++) {
            int unicode = text.codePointAt(i) - 32;

            for (int y = 0; y < font.getFontImage().getHeight(); y++) {
                for (int x = 0; x < font.getWidths()[unicode]; x++) {
                    if(font.getFontImage().getPixels()[(x + font.getOffsets()[unicode]) + y * font.getFontImage().getWidth()] == Color.WHITE){
                        setPixel(x + offX + offset, y + offY, color );
                    }
                }
            }

            offset += font.getWidths()[unicode];
        }
    }
}
