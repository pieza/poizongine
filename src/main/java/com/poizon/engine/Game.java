package com.poizon.engine;

import com.poizon.engine.config.Settings;
import com.poizon.engine.containers.GameContainer;
import com.poizon.engine.exceptions.ExceptionLogger;
import com.poizon.engine.exceptions.MissingSceneException;
import com.poizon.engine.input.GameInput;
import com.poizon.engine.input.Input;
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
 * Static class to force one instance of the game.
 *
 * @see GameContainer
 * @author Jose Ulloa
 */
public final class Game {
    private static GameContainer gameContainer;
    private static ILogger logger;
    private static IWindow window;

    public static IRenderer renderer;
    public static Input input;
    public static Settings settings;

    private Game() {}

    static {
        settings = new Settings();
        logger = new ConsoleLogger(settings.isDebug());
        window = new GameWindow(settings);
        renderer = new Renderer(window, settings);
        input = new GameInput(window, settings);

        ExceptionLogger.logger = logger;

        gameContainer = new GameContainer(logger, window, renderer, input, settings);
        logger.log(LogLevel.DEBUG, "Default game settings loaded");
    }

    /**
     * Starts the game loop using the game container, this must have all configuration properly loaded.
     */
    public static synchronized void start() {
        logger.log(LogLevel.INFO, "Starting game loop.");
        new Thread(() -> {
            gameContainer.start();
        }).start();

    }

    public static void addScene(String key, GameScene scene) {
        gameContainer.addScene(key, scene);
    }

    public static void removeScene(String key) {
        gameContainer.removeScene(key);
    }

    public static void setScene(String key) throws MissingSceneException {
        gameContainer.setScene(key);
    }
}
