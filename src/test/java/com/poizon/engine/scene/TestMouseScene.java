package com.poizon.engine.scene;

import com.poizon.engine.Game;
import com.poizon.engine.graphics.Image;
import com.poizon.engine.scenes.GameScene;

public class TestMouseScene extends GameScene {
    private Image image;

    public TestMouseScene() {
        this.image = new Image("/sprites/test.png");
    }

    @Override
    public void update(Game game, float deltaTime) {

    }

    @Override
    public void render(Game game) {
        game.renderer.drawImage(image, game.input.getMouseX()-image.getWidth()/2, game.input.getMouseY()-image.getHeight()/2);
    }

    @Override
    public void init(Game game) {

    }
}
