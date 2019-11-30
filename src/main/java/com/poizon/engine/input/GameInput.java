package com.poizon.engine.input;

import com.poizon.engine.config.Settings;
import com.poizon.engine.windows.IWindow;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Handles the input controllers like keyboard and mouse.
 *
 * @author Jose Ulloa
 */
public class GameInput implements Input {
    private Settings settings;
    private IWindow window;

    private final int NUM_KEYS = 256;

    private boolean[] keys = new boolean[NUM_KEYS];
    private boolean[] keysLast = new boolean[NUM_KEYS];

    private final int NUM_BUTTONS = 5;
    private boolean[] buttons = new boolean[NUM_BUTTONS];
    private boolean[] buttonsLast = new boolean[NUM_BUTTONS];

    private int mouseX = 0;

    private int mouseY = 0;
    private int scroll = 0;

    public GameInput(IWindow window, Settings settings) {
        this.window = window;
        this.settings = settings;
        window.getCanvas().addKeyListener(this);
        window.getCanvas().addMouseListener(this);
        window.getCanvas().addMouseWheelListener(this);
        window.getCanvas().addMouseMotionListener(this);
    }

    public boolean isKey(int keyCode) {
        return keys[keyCode];
    }

    public boolean isKeyUp(int keyCode) {
        return !keys[keyCode] && keysLast[keyCode];
    }

    public boolean isKeyDown(int keyCode) {
        return keys[keyCode] && !keysLast[keyCode];
    }

    public boolean isButton(int buttonCode) {
        return buttons[buttonCode];
    }

    public boolean isButtonUp(int buttonCode) {
        return !buttons[buttonCode] && buttonsLast[buttonCode];
    }

    public boolean isButtonDown(int buttonCode) {
        return buttons[buttonCode] && !buttonsLast[buttonCode];
    }

    @Override
    public void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            keysLast[i] = keys[i];
        }

        for (int i = 0; i < NUM_BUTTONS; i++) {
            buttonsLast[i] = buttons[i];
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = (int)(e.getX() / settings.getScale());
        mouseY = (int)(e.getY() / settings.getScale());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll = e.getWheelRotation();
    }

    @Override
    public int getMouseX() {
        return mouseX;
    }

    @Override
    public int getMouseY() {
        return mouseY;
    }

    @Override
    public int getScroll() {
        return scroll;
    }
}
