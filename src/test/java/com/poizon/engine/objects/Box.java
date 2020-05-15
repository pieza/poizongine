package com.poizon.engine.objects;

import com.poizon.engine.graphics.Image;
import com.poizon.engine.graphics.Light;

public class Box extends GameObject {


    public Box(int x, int y) {
        this.positionX = x;
        this.positionY = y;
        sprite = new Image("/sprites/box.png");
        sprite.setAlpha(true);
        sprite.setLightBlock(Light.FULL);
    }

}
