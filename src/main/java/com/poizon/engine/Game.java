package com.poizon.engine;

import com.poizon.engine.camera.Camera;
import com.poizon.engine.config.Settings;
import com.poizon.engine.containers.GameContainer;
import com.poizon.engine.exceptions.ExceptionLogger;
import com.poizon.engine.exceptions.MissingSceneException;
import com.poizon.engine.input.GameInput;
import com.poizon.engine.input.Input;
import com.poizon.engine.objects.GameObject;
import com.poizon.engine.render.IRenderer;
import com.poizon.engine.render.Renderer;
import com.poizon.engine.scenes.GameScene;
import com.poizon.engine.windows.IWindow;
import com.poizon.engine.windows.GameWindow;
import com.poizon.engine.utils.log.ConsoleLogger;
import com.poizon.engine.utils.log.ILogger;
import com.poizon.engine.utils.log.LogLevel;

/**
 * Entry point of the engine, unique game instance that handles all dependencies to run the game.
 *
 * @see GameContainer
 * @author Jose Ulloa
 */
public final class Game {
    private GameContainer gameContainer;
    public IWindow window;
    public ILogger logger;
    public IRenderer renderer;
    public Input input;
    public Settings settings;
    public Camera camera;

    public Game() {
        // load defaults settings
        settings = new Settings();
        this.init();
    }

    public Game(Settings settings) {
        this.settings = settings;
        this.init();
    }


    /**
     * Starts the game loop using the game container, this must have all configuration properly loaded.
     */
    public synchronized void start() {
        logger.log(LogLevel.INFO, "Starting game loop.");
        new Thread(() -> {
            gameContainer.start();
        }).start();

    }

    private void init() {
        logger = new ConsoleLogger(settings.isDebug());
        logger.log(LogLevel.DEBUG, "Initializing game...");
        window = new GameWindow(settings);
        renderer = new Renderer(window, settings);
        input = new GameInput(window, settings);
        ExceptionLogger.logger = logger;
        logger.log(LogLevel.DEBUG, "Creating game container");
        logger.log(LogLevel.TRACE, "Using settings: " + settings.toString());
        gameContainer = new GameContainer(this);
        logger.log(LogLevel.DEBUG, "Game settings loaded");
        camera = new Camera();
    }

    public void addScene(String key, GameScene scene) {
        gameContainer.addScene(key, scene);
    }

    public void removeScene(String key) {
        gameContainer.removeScene(key);
    }

    public void setScene(String key) throws MissingSceneException {
        gameContainer.setScene(key);
    }

    public GameScene getActualScene() {
        return gameContainer.getActualScene();
    }

    public GameObject getObject(String key) {
        return gameContainer.getActualScene().objects.get(key);
    }
}
