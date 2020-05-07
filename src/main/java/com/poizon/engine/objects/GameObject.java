package com.poizon.engine.objects;

import com.poizon.engine.Game;
import com.poizon.engine.graphics.Image;
import com.poizon.engine.graphics.ImageTile;
import com.poizon.engine.render.IRenderable;
import com.poizon.engine.render.IUpdateable;

public abstract class GameObject implements IUpdateable, IRenderable {
    protected Image sprite;
    protected int positionX, positionY;
    protected int[] spriteIndex;
    protected boolean visible;

    public GameObject() {
        this.positionX = 0;
        this.positionY = 0;
        this.spriteIndex = new int[] { 0, 0 };
        this.visible = true;
    }

    @Override
    public void render(Game game) {
        if(visible && sprite != null) {
            if(sprite instanceof ImageTile) {
                game.renderer.drawImageTile((ImageTile)sprite, positionX, positionY, spriteIndex[0], spriteIndex[1]);
            } else if(sprite instanceof Image) {
                game.renderer.drawImage(sprite, positionX, positionY);
            }

        }
    }

    @Override
    public void update(Game game) {
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
