package com.poizon.engine.containers;

import com.poizon.engine.Game;
import com.poizon.engine.exceptions.MissingSceneException;
import com.poizon.engine.scenes.DebugScene;
import com.poizon.engine.scenes.GameScene;
import com.poizon.engine.scenes.ISceneManager;
import com.poizon.engine.utils.log.LogLevel;
import com.poizon.engine.utils.time.Time;

import java.util.HashMap;
import java.util.Map;

public class GameContainer implements Runnable, IContainer, ISceneManager {

    private Game game;

    private Thread thread;

    private boolean isRunning = false;

    private double UPDATE_CAP;

    private String actualScene = "";

    private Map<String, GameScene> scenes = new HashMap<>();

    private DebugScene debugScene = new DebugScene();

    public GameContainer(Game game) {
        this.game = game;
        UPDATE_CAP = 1.0 / game.settings.getFrameRate();
    }

    public GameScene getActualScene() {
        if( actualScene != null && !actualScene.isEmpty() && scenes.get(actualScene) != null)
            return scenes.get(actualScene);

        return null;
    }

    @Override
    public void start() {
        game.window.show();
        thread = new Thread(this);

        thread.run();
    }

    public void stop() {

    }

    @Override
    public void run() {
        game.logger.log("Engine running...");
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
            shouldRender = !game.settings.isLockFrameRate();
            firstTime = Time.now();
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while(unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP;
                shouldRender = true;

                float deltaTime = (float)UPDATE_CAP;

                if(game.settings.isDebug()) debugScene.update(game);

                GameScene scene = getActualScene();
                if(scene != null) {
                    scene.update(game);
                    scene.updateObjects(game);
                }

                game.input.update();

                if(frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    Time.fps = fps;
                    game.logger.log(LogLevel.DEBUG, "FPS: " + fps);
                }
            }

            if(shouldRender) {
                game.renderer.clear();
                GameScene scene = getActualScene();
                if(scene != null) {
                    scene.render(game);
                    scene.renderObjects(game);
                }
                game.renderer.process();
                if(game.settings.isDebug()) debugScene.render(game);
                game.window.update();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    game.logger.log(LogLevel.ERROR, e.getMessage());
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
        game.logger.log(LogLevel.TRACE, String.format("Adding scene %s.", key));
        scenes.put(key, scene);
        game.logger.log(LogLevel.TRACE, String.format("Scene %s added.", key));
    }

    @Override
        public void removeScene(String key) {
        game.logger.log(LogLevel.TRACE, String.format("Removing scene %s.", key));
        scenes.remove(key);
        game.logger.log(LogLevel.TRACE, String.format("Scene %s removed.", key));
    }

    @Override
    public void setScene(String key) throws MissingSceneException {
        game.logger.log(LogLevel.TRACE, String.format("Switching scene %s.", key));
        if(scenes.get(key) == null) throw new MissingSceneException(key);
        actualScene = key;
    }
}
