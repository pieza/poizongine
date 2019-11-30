package com.poizon.engine.scene;

import com.poizon.engine.Game;
import com.poizon.engine.audio.SoundClip;
import com.poizon.engine.graphics.Image;
import com.poizon.engine.render.IRenderer;
import com.poizon.engine.scenes.GameScene;
import com.poizon.engine.utils.log.LogLevel;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static com.poizon.engine.Game.*;

public class TestGameScene extends GameScene {

    private Image image = new Image("/sprites/test.png");
    private Image image2 = new Image("/sprites/test.png");
    private SoundClip sound = new SoundClip("/audio/shot.wav");
    int x = 0,  y = 0, velocity = 20;

    @Override
    public void update(float deltaTime) {
        if(input.isButtonDown(MouseEvent.BUTTON1)) {
            sound.play();
        }


        if(input.isKey(KeyEvent.VK_D)) {
            x += velocity;
        }

        if(input.isKey(KeyEvent.VK_A)) {
            x -= velocity;
        }

        if(input.isKey(KeyEvent.VK_W)) {
            y -= velocity;
        }

        if(input.isKey(KeyEvent.VK_S)) {
            y += velocity;
        }
    }

    @Override
    public void render() {
        renderer.drawImage(image, input.getMouseX()-image.getWidth()/2, input.getMouseY()-image.getHeight()/2);
        renderer.drawImage(image2, x, y);
    }
}
