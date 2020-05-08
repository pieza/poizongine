package com.poizon.engine.render;

import com.poizon.engine.graphics.Font;
import com.poizon.engine.graphics.Image;
import com.poizon.engine.graphics.ImageTile;
import com.poizon.engine.graphics.Light;

/**
 * A renderer will update pixels in the screen.
 *
 * @author Jose Ulloa
 */
public interface  IRenderer {
    /**
     * Clears pixels in the screen or window.
     */
    void clear();

    /**
     * Draws an image on the screen or window.
     * @param image Image to draw.
     * @param offX Position x.
     * @param offY Position y.
     */
    void drawImage(Image image, int offX, int offY);

    /**
     * Draws an image on the screen or window.
     * @param image Image to draw.
     * @param offX Position x.
     * @param offY Position y.
     */
    void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY);

    /**
     * Draws a text on the screen or window.
     * @param text Text wanted to be draw.
     * @param offX Position x.
     * @param offY Position y.
     * @param color Color in hexadecimal.
     * @see com.poizon.engine.graphics.Color
     */
    void drawText(String text, Font font, int offX, int offY, int color);

    void drawLight(Light light, int offX, int offY);

    void process();
}
