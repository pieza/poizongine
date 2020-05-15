package com.poizon.engine;

import com.poizon.engine.exceptions.MissingSceneException;
import com.poizon.engine.scene.TestCollisionScene;
import com.poizon.engine.scene.TestGameScene;
import com.poizon.engine.scene.TestLightScene;
import com.poizon.engine.scene.TestMouseScene;

public class EnginePlayground {

    public static void main(String[] args) {
        Game game = new Game();
        game.settings.setDebug(true);
        game.addScene("TEST", new TestGameScene());
        game.addScene("MOUSE", new TestMouseScene());
        game.addScene("LIGHT", new TestLightScene());
        game.addScene("COLLISION", new TestCollisionScene());

        try {
            game.setScene("COLLISION");
        } catch (MissingSceneException e) {
            e.printStackTrace();
        }

        game.start();
    }
}
