package com.poizon.engine.objects;

import com.poizon.engine.Game;
import com.poizon.engine.audio.SoundClip;
import com.poizon.engine.graphics.ImageTile;
import com.poizon.engine.physics.collision.CollisionComponent;

import java.awt.event.KeyEvent;

public class Player extends GameObject {
    private SoundClip sound = new SoundClip("/audio/shot.wav");
    int velocity = 2;


    public Player() {
        sprite = new ImageTile("/sprites/player.png", 19, 21);
        sprite.setAlpha(true);

        this.addComponent(new CollisionComponent(this));
    }

    @Override
    public void update(Game game, float deltaTime) {
        if(game.input.isKeyDown(KeyEvent.VK_SPACE)) {
            sound.play();
        }

        if(game.input.isKey(KeyEvent.VK_D)) {
            if(!game.collisionMap.checkRightCollision(this)) {
                positionX += velocity;
            }
        }

        if(game.input.isKey(KeyEvent.VK_A)) {
            if(!game.collisionMap.checkLeftCollision(this)) {
                positionX -= velocity;
            }
        }

        if(game.input.isKey(KeyEvent.VK_W)) {
            if(!game.collisionMap.checkUpCollision(this)) {
                positionY -= velocity;
            }
        }

        if(game.input.isKey(KeyEvent.VK_S)) {
            if(!game.collisionMap.checkDownCollision(this)) {
                positionY += velocity;
            }
        }
    }
}
