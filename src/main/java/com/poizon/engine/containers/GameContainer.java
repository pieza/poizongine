package com.poizon.engine.containers;

import com.poizon.engine.config.Settings;
import com.poizon.engine.exceptions.MissingSceneException;
import com.poizon.engine.input.GameInput;
import com.poizon.engine.input.Input;
import com.poizon.engine.render.IRenderer;
import com.poizon.engine.render.Renderer;
import com.poizon.engine.scenes.GameScene;
import com.poizon.engine.scenes.ISceneManager;
import com.poizon.engine.windows.IWindow;
import com.poizon.engine.windows.GameWindow;
import com.poizon.engine.utils.log.ConsoleLogger;
import com.poizon.engine.utils.log.ILogger;
import com.poizon.engine.utils.log.LogLevel;
import com.poizon.engine.utils.time.Time;

import java.util.HashMap;
import java.util.Map;

public class GameContainer implements Runnable, IContainer, ISceneManager {

    public Settings settings;

    private Thread thread;

    private boolean isRunning = false;

    private double UPDATE_CAP;

    private ILogger logger;

    private IWindow window;

    private IRenderer renderer;

    private Input input;

    private String actualScene = "";

    private Map<String, GameScene> scenes = new HashMap<String, GameScene>();

    public GameContainer() {
        logger = new ConsoleLogger();
        settings = new Settings();
        window = new GameWindow(settings);
        renderer = new Renderer(window, settings);
        input = new GameInput(window, settings);

        UPDATE_CAP = 1.0 / settings.getFrameRate();
    }

    public GameContainer(ILogger logger, IWindow window, IRenderer renderer, Input input, Settings settings) {
        this.logger = logger;
        this.settings = settings;
        this.window = window;
        this.renderer = renderer;
        this.input = input;

        UPDATE_CAP = 1.0 / settings.getFrameRate();
    }

    private GameScene getActualScene() {
        if( actualScene != null && !actualScene.isEmpty() && scenes.get(actualScene) != null)
            return scenes.get(actualScene);

        return null;
    }

    @Override
    public void start() {
        window.show();
        thread = new Thread(this);

        thread.run();
    }

    public void stop() {

    }

    @Override
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

                GameScene scene = getActualScene();
                if(scene != null)
                    scene.update((float)UPDATE_CAP);
                input.update();

                if(frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    logger.log(LogLevel.DEBUG, "FPS: " + fps);
                }
            }

            if(shouldRender) {
                renderer.clear();
                GameScene scene = getActualScene();
                if(scene != null)
                    scene.render();
                window.update();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    logger.log(LogLevel.ERROR, e.getMessage());
                }
            }
        }

        dispose();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void addScene(String key, GameScene scene) {
        logger.log(LogLevel.TRACE, String.format("Adding scene %s.", key));
        scenes.put(key, scene);
        logger.log(LogLevel.TRACE, String.format("Scene %s added.", key));
    }

    @Override
        public void removeScene(String key) {
        logger.log(LogLevel.TRACE, String.format("Removing scene %s.", key));
        scenes.remove(key);
        logger.log(LogLevel.TRACE, String.format("Scene %s removed.", key));
    }

    @Override
    public void setScene(String key) throws MissingSceneException {
        logger.log(LogLevel.TRACE, String.format("Switching scene %s.", key));
        if(scenes.get(key) == null) throw new MissingSceneException(key);
        actualScene = key;
    }
}
