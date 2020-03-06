package com.poizon.engine.windows;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * UI Window where the user interacts and the game will be displayed.
 *
 * @author Jose Ulloa
 */
public interface IWindow {

    void show();

    void update();

    BufferedImage getImage();

    Canvas getCanvas();
}
