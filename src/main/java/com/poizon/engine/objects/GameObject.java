package com.poizon.engine.objects;

import com.poizon.engine.Game;
import com.poizon.engine.graphics.Image;
import com.poizon.engine.graphics.ImageTile;

public abstract class GameObject {
    protected Image sprite;
    protected int positionX, positionY;
    protected boolean visible;

    public GameObject() {
        this.positionX = 0;
        this.positionY = 0;
        this.visible = true;
    }

    public void render() {
        if(!(sprite instanceof ImageTile) && visible && sprite != null) Game.renderer.drawImage(sprite, positionX, positionY);
    }

    public void update(float deltaTime) {
        // do nothing
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
