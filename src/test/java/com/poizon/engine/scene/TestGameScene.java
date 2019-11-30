package com.poizon.engine.scene;

import com.poizon.engine.Game;
import com.poizon.engine.audio.SoundClip;
import com.poizon.engine.graphics.Image;
import com.poizon.engine.render.IRenderer;
import com.poizon.engine.scenes.GameScene;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static com.poizon.engine.Game.*;

public class TestGameScene extends GameScene {

    private Image image = new Image("/sprites/test.png");
    private SoundClip sound = new SoundClip("/audio/shot.wav");

    @Override
    public void update(float deltaTime) {
        if(input.isButtonDown(MouseEvent.BUTTON1)) {
            sound.play();
        }
    }

    @Override
    public void render() {
        renderer.drawImage(image, input.getMouseX()-image.getWidth()/2, input.getMouseY()-image.getHeight()/2);
    }
}
