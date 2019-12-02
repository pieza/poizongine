package com.poizon.engine;

import com.poizon.engine.exceptions.MissingSceneException;
import com.poizon.engine.scene.TestGameScene;

public class EnginePlayground {

    public static void main(String[] args) {
        Game.settings.setDebug(true);

        Game.start();

        Game.addScene("TEST", new TestGameScene());
        try {
            Game.setScene("TEST");
        } catch (MissingSceneException e) {
            e.printStackTrace();
        }

    }
}
