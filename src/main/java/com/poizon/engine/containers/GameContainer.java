package com.poizon.engine;

import com.poizon.engine.config.Settings;
import com.poizon.engine.ui.Window;
import com.poizon.engine.utils.log.ConsoleLogger;
import com.poizon.engine.utils.log.ILogger;
import com.poizon.engine.utils.log.LogTypes;
import com.poizon.engine.utils.time.Time;

public class GameContainer implements Runnable {

    public Settings settings;

    private Thread thread;

    private boolean isRunning = false;

    private double UPDATE_CAP;

    private ILogger logger;

    private Window window;

    public GameContainer() {
        logger = new ConsoleLogger();
        settings = new Settings();
        UPDATE_CAP = 1.0 / settings.getFrameRate();
    }

    public GameContainer(Settings opts) {
        logger = new ConsoleLogger();
        settings = opts;
        UPDATE_CAP = 1.0 / settings.getFrameRate();
    }

    public void start() {
        window = new Window(this);
        thread = new Thread(this);

        thread.run();
    }

    public void stop() {

    }

    public void run() {
        logger.log("Engine running...");
        isRunning = true;

        boolean shouldRender = false;
        double firstTime = 0;
        double lastTime = Time.now();
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        while(isRunning) {
            shouldRender = false;
            firstTime = Time.now();
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while(unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP;
                shouldRender = true;

                // TODO: Update game
                if(frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    logger.log("FPS: " + fps, LogTypes.DEBUG);
                }

            }

            if(shouldRender) {
                window.update();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    logger.log(e.getMessage(), LogTypes.ERROR);
                }
            }
        }

        dispose();
    }

    public void dispose() {

    }
}
