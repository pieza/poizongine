package com.poizon.engine.scene;

import com.poizon.engine.Game;
import com.poizon.engine.camera.Camera;
import com.poizon.engine.graphics.Image;
import com.poizon.engine.objects.Player;
import com.poizon.engine.scenes.GameScene;


public class TestGameScene extends GameScene {
    private Image background;

    public TestGameScene() {
        background = new Image("/sprites/world.png");
        objects.put("PLAYER", new Player());
    }

    @Override
    public void init(Game game) {
        game.camera = new Camera("PLAYER");
    }

    @Override
    public void update(Game game, float deltaTime) {

    }

    @Override
    public void render(Game game) {
        game.renderer.drawImage(background, 0, 0);
    }
}
