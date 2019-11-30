package com.poizon.engine.scene;

import com.poizon.engine.Game;
import com.poizon.engine.graphics.Image;
import com.poizon.engine.render.IRenderer;
import com.poizon.engine.scenes.GameScene;

import static com.poizon.engine.Game.*;

public class TestGameScene extends GameScene {

    private Image image = new Image("/sprites/test.png");

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render() {
        renderer.drawImage(image, input.getMouseX()-image.getWidth()/2, input.getMouseY()-image.getHeight()/2);
    }
}
