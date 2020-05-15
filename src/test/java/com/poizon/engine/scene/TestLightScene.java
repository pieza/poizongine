package com.poizon.engine.scene;

import com.poizon.engine.Game;
import com.poizon.engine.graphics.Color;
import com.poizon.engine.graphics.Image;
import com.poizon.engine.graphics.Light;
import com.poizon.engine.scenes.GameScene;

public class TestLightScene extends GameScene {
    private Image image;
    private Image background;
    private Light light;

    public TestLightScene() {
        image = new Image("/sprites/hidden.png");
        image.setLightBlock(Light.FULL);
        background = new Image("/sprites/bg.png");
        light = new Light(50, Color.WHITE);
    }

    @Override
    public void update(Game game, float deltaTime) {

    }

    @Override
    public void render(Game game) {
        game.renderer.drawImage(background, 0, 0);
        game.renderer.drawImage(image, 50, 70);
        game.renderer.drawLight(light, game.input.getMouseX(), game.input.getMouseY());
    }

    @Override
    public void init(Game game) {
        game.renderer.setAmbientLight(Color.DARK_GREY);
    }
}
