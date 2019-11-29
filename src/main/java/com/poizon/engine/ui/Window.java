package com.poizon.engine.ui;

import com.poizon.engine.GameContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {

    private JFrame frame;
    private BufferedImage image;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;


    public Window(GameContainer gameContainer) {
        int width = gameContainer.settings.getScreenWidth(),
                height = gameContainer.settings.getScreenHeight();
        float scale = gameContainer.settings.getScale();

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
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
    }

    public void update() {
        graphics.drawImage(image,0, 0, canvas.getWidth(), canvas.getHeight(), null);
        bufferStrategy.show();
    }
}
