package com.poizon.engine.render;

import com.poizon.engine.config.Settings;
import com.poizon.engine.graphics.*;
import com.poizon.engine.windows.IWindow;

import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Jose Ulloa
 */
public class Renderer implements IRenderer {
    private int pixelWidth;
    private int pixelHeight;
    private int zDepth;
    private int cameraX;
    private int cameraY;
    private int ambientLight = Color.WHITE;
    private int[] pixels;
    private int[] zBuffer;
    private int[] lightMap;
    private int[] lightBlock;
    private boolean processing = false;
    private ArrayList<ImageRequest> imageRequests = new ArrayList<>();
    private ArrayList<LightRequest> lightRequests = new ArrayList<>();

    public Renderer(IWindow window, Settings settings) {
        pixelWidth = settings.getScreenWidth();
        pixelHeight = settings.getScreenHeight();

        pixels = ((DataBufferInt)window.getImage().getRaster().getDataBuffer()).getData();
        zBuffer = new int[pixels.length];
        lightMap = new int[pixels.length];
        lightBlock = new int[pixels.length];
    }

    private void setPixel(int x, int y, int value) {
        int alpha = ((value >> 24) & 0xff);

        if((x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) || alpha == 0) {
            return;
        }
        int index = x + y * pixelWidth;

        if(zBuffer[index] > zDepth) {
            return;
        }

        zBuffer[index] = zDepth;

        if(alpha == 255) {
            pixels[index] = value;
        } else {
            int pixelColor =  pixels[index];
            int newRed = ((pixelColor >> 16) & 0xff) - (int)((((pixelColor >> 16) & 0xff) - ((value >> 16) & 0xff)) * (alpha/255f));
            int newGreen = ((pixelColor >> 8) & 0xff) - (int)((((pixelColor >> 8) & 0xff) - ((value >> 8) & 0xff)) * (alpha/255f));
            int newBlue = (pixelColor & 0xff) - (int)(((pixelColor) & 0xff - (value & 0xff)) * (alpha/255f));

            pixels[index] = (newRed << 16 | newGreen << 8 | newBlue);
        }


    }

    private void drawLightLine(Light light, int x0, int y0, int x1, int y1, int offX, int offY) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;

        int err = dx -dy;
        int e2;
        
        while (true) {
            int screenX = x0 - light.getRadius() + offX;
            int screenY = y0 - light.getRadius() + offY;

            if(screenX < 0 || screenY >= pixelWidth || screenY < 0 || screenY >= pixelHeight){
                return;
            }

            int lightColor = light.getLightValue(x0, y0);
            if(lightColor == 0)
                return;

            if(lightBlock[screenX + screenY * pixelWidth] == Light.FULL) {
                return;
            }

            setLightMap(screenX, screenY, lightColor);

            if(x0 == x1 && y0 == y1) {
                break;
            }

            e2 = 2 * err;

            if(e2 > -1 * dy) {
                err -= dy;
                x0 += sx;
            }

            if(e2 < dx) {
                err += dx;
                y0 += sy;
            }

        }
    }

    @Override
    public void process() {
        processing = true;

        Collections.sort(imageRequests, (i0, i1) -> {
            if(i0.zDepth < i1.zDepth)
                return -1;
            if(i0.zDepth > i1.zDepth)
                return 1;
            return 0;
        });

        for (int i = 0; i < imageRequests.size(); i++) {
            ImageRequest imageRequest = imageRequests.get(i);
            zDepth = imageRequest.zDepth;
            imageRequest.image.setAlpha(false);
            drawImage(imageRequest.image, imageRequest.offX, imageRequest.offY);
        }



        for (int i = 0; i < lightRequests.size(); i++) {
            LightRequest lightRequest = lightRequests.get(i);
            drawLightRequest(lightRequest.light, lightRequest.locationX, lightRequest.locationY);
        }

        for (int i = 0; i < pixels.length; i++) {
            float red = ((lightMap[i] >> 16) & 0xff) / 255f;
            float green = ((lightMap[i] >> 8) & 0xff) / 255f;
            float blue = (lightMap[i] & 0xff) / 255f;

            pixels[i] =  ((int)(((pixels[i] >> 16) & 0xff) * red) << 16 | (int)(((pixels[i] >> 8) & 0xff) * green) << 8 | (int)((pixels[i] & 0xff) * blue));
        }

        imageRequests.clear();
        lightRequests.clear();
        processing = false;
    }

    private void setLightMap(int x, int y, int value) {
        if(x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) {
            return;
        }

        if(zBuffer[x + y * pixelWidth] > zDepth) {
            return;
        }

        int baseColor = lightMap[x + y * pixelWidth];

        int maxRed = Math.max((baseColor >> 16) & 0xff,  (value >> 16) & 0xff);
        int maxGreen = Math.max((baseColor >> 8) & 0xff,  (value >> 16) & 0xff);
        int maxBlue = Math.max(baseColor & 0xff, value & 0xff);

        lightMap[x + y * pixelWidth] = (maxRed << 16 | maxGreen << 8 | maxBlue);
    }

    private void setLightBlock(int x, int y, int value) {
        if(x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) {
            return;
        }

        lightBlock[x + y * pixelWidth] = value;
    }

    private void drawLightRequest(Light light, int offX, int offY) {
        offX -= cameraX;
        offY -= cameraY;

        for (int i = 0; i < light.getDiameter(); i++) {
            drawLightLine(light, light.getRadius(), light.getRadius(), i, 0, offX, offY);
            drawLightLine(light, light.getRadius(), light.getRadius(), i, light.getDiameter(), offX, offY);
            drawLightLine(light, light.getRadius(), light.getRadius(),0, i, offX, offY);
            drawLightLine(light, light.getRadius(), light.getRadius(), light.getDiameter(), i, offX, offY);
        }
    }

    @Override
    public void clear(){
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
            zBuffer[i] = 0;
            lightMap[i] = ambientLight;
            lightBlock[i] = 0;
        }
    }

    @Override
    public void drawImage(Image image, int offX, int offY) {
        if(image.isAlpha() && !processing) {
            imageRequests.add(new ImageRequest(image, zDepth, offX, offY));
            return;
        }

        offX -= cameraX;
        offY -= cameraY;

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
                setLightBlock(x + offX, y + offY, image.getLightBlock());
            }
        }
    }

    @Override
    public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY) {
        if(image.isAlpha() && !processing) {
            imageRequests.add(new ImageRequest(image.getTileImage(tileX, tileY), zDepth, offX, offY));
            return;
        }

        offX -= cameraX;
        offY -= cameraY;

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
                setLightBlock(x + offX, y + offY, image.getLightBlock());
            }
        }
    }

    @Override
    public void drawText(String text, Font font, int offX, int offY, int color) {
        offX -= cameraX;
        offY -= cameraY;

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

    @Override
    public void drawLight(Light light, int offX, int offY) {
        lightRequests.add(new LightRequest(light, offX, offY));
    }

    @Override
    public void setAmbientLight(int color) {
        this.ambientLight = color;
    }

    @Override
    public void setCameraX(int offX) {
        this.cameraX = offX;
    }

    @Override
    public void setCameraY(int offY) {
        this.cameraY = offY;
    }
}
