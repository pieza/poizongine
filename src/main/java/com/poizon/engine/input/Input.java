package com.poizon.engine.input;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

/**
 * Input handler for mouse and keyboard using awt lib.
 *
 * @author Jose Ulloa
 */
public interface Input extends KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
    void update();

    int getMouseX();

    int getMouseY();

    int getScroll();

    boolean isKey(int keyCode);

    boolean isKeyUp(int keyCode);

    boolean isKeyDown(int keyCode);

    boolean isButton(int buttonCode);

    boolean isButtonUp(int buttonCode);

    boolean isButtonDown(int buttonCode);
}
