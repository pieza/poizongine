package com.poizon.engine.objects;

import com.poizon.engine.audio.SoundClip;
import com.poizon.engine.graphics.ImageTile;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static com.poizon.engine.Game.input;

public class Player extends GameObject {
    private SoundClip sound = new SoundClip("/audio/shot.wav");
    int velocity = 2;

    public Player() {
        sprite = new ImageTile("/sprites/player.png", 19, 21);
    }

    @Override
    public void update(float deltaTime) {
        if(input.isKeyDown(KeyEvent.VK_SPACE)) {
            sound.play();
        }

        if(input.isKey(KeyEvent.VK_D)) {
            positionX += velocity;
        }

        if(input.isKey(KeyEvent.VK_A)) {
            positionX -= velocity;
        }

        if(input.isKey(KeyEvent.VK_W)) {
            positionY -= velocity;
        }

        if(input.isKey(KeyEvent.VK_S)) {
            positionY += velocity;
        }
    }
}
