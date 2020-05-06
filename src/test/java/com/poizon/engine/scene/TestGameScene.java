package com.poizon.engine.scene;

import com.poizon.engine.Game;
import com.poizon.engine.objects.Player;
import com.poizon.engine.scenes.GameScene;


public class TestGameScene extends GameScene {

    public TestGameScene() {
        objects.put("PLAYER", new Player());
    }

    @Override
    public void update(Game game) {
    }

    @Override
    public void render(Game game) {

    }
}
