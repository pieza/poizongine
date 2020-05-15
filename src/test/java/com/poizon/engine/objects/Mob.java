package com.poizon.engine.objects;

import com.poizon.engine.graphics.Image;
import com.poizon.engine.graphics.Light;
import com.poizon.engine.physics.collision.CollisionComponent;

public class Mob extends GameObject {

    public Mob(int x, int y) {
        this.positionX = x;
        this.positionY = y;
        sprite = new Image("/sprites/mob_1.png");
        sprite.setAlpha(true);
        sprite.setLightBlock(Light.FULL);
        this.addComponent(new CollisionComponent(this));
    }

    @Override
    public void onCollisionEvent(GameObject other) {
        setAlive(false);
    }
}
