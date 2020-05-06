package com.poizon.engine;

import com.poizon.engine.exceptions.MissingSceneException;
import com.poizon.engine.scene.TestGameScene;
import com.poizon.engine.scene.TestMouseScene;

public class EnginePlayground {

    public static void main(String[] args) {
        Game game = new Game();
        game.settings.setDebug(true);
        game.addScene("TEST", new TestGameScene());
        game.addScene("MOUSE", new TestMouseScene());

        game.start();
        try {
            game.setScene("TEST");
        } catch (MissingSceneException e) {
            e.printStackTrace();
        }

    }
}
