package com.poizon.engine.windows;

import com.poizon.engine.config.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * UI Window where the user interacts and the game will be displayed.
 *
 * @author Jose Ulloa
 */
public class GameWindow implements IWindow {

    private JFrame frame;
    private BufferedImage image;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;


    public GameWindow(Settings settings) {
        int width = settings.getScreenWidth(), height = settings.getScreenHeight();
        float scale = settings.getScale();

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        canvas = new Canvas();
        Dimension dimension = new Dimension((int)(width * scale), (int)(height * scale));

        canvas.setPreferredSize(dimension);
        canvas.setMaximumSize(dimension);
        canvas.setMinimumSize(dimension);

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);

        if(settings.isFullScreen()) {
            frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
        }
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    @Override
    public void show() {
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
    }

    @Override
    public void update() {
        graphics.drawImage(image,0, 0, canvas.getWidth(), canvas.getHeight(), null);
        bufferStrategy.show();
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }
}
