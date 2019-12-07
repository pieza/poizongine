package com.poizon.engine.scene;

import com.poizon.engine.graphics.Image;
import com.poizon.engine.scenes.GameScene;

import static com.poizon.engine.Game.input;
import static com.poizon.engine.Game.renderer;

public class TestMouseScene extends GameScene {
    private Image image;

    public TestMouseScene() {
        this.image = new Image("/sprites/test.png");
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        renderer.drawImage(image, input.getMouseX()-image.getWidth()/2, input.getMouseY()-image.getHeight()/2);
    }
}
