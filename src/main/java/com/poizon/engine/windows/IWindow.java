package com.poizon.engine.windows;

import com.poizon.engine.render.IUpdateable;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * UI Window where the user interacts and the game will be displayed.
 *
 * @author Jose Ulloa
 */
public interface IWindow extends IUpdateable {

    void show();

    BufferedImage getImage();

    Canvas getCanvas();
}
