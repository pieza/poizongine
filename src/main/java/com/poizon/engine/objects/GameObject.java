package com.poizon.engine.objects;

import com.poizon.engine.Game;
import com.poizon.engine.graphics.Image;

public abstract class GameObject {
    protected Image sprite;
    protected int positionX, positionY;
    protected boolean visible;

    public GameObject() {

    }

    public GameObject(String path) {
        this.sprite = new Image(path);
        this.positionX = 0;
        this.positionY = 0;
        this.visible = true;
    }

    public GameObject(String path, int positionX, int positionY) {
        this.sprite = new Image(path);
        this.positionX = positionX;
        this.positionY = positionY;
        this.visible = true;
    }

    public GameObject(String path, int positionX, int positionY, boolean visible) {
        this.sprite = new Image(path);
        this.positionX = positionX;
        this.positionY = positionY;
        this.visible = visible;
    }

    public void render() {
        if(visible) Game.renderer.drawImage(sprite, positionX, positionY);
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
