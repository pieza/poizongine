package com.poizon.engine.objects;

import com.poizon.engine.Game;
import com.poizon.engine.graphics.Image;
import com.poizon.engine.graphics.ImageTile;
import com.poizon.engine.physics.Component;
import com.poizon.engine.render.IInitializable;
import com.poizon.engine.render.IRenderable;
import com.poizon.engine.render.IUpdateable;

import java.util.ArrayList;

public abstract class GameObject implements IInitializable, IUpdateable, IRenderable {
    public Image sprite;
    protected int positionX, positionY;
    protected int[] spriteIndex;
    protected boolean visible;
    protected boolean alive;

    private ArrayList<Component> components = new ArrayList<>();

    public GameObject() {
        this.positionX = 0;
        this.positionY = 0;
        this.spriteIndex = new int[] { 0, 0 };
        this.visible = true;
        this.alive = true;
    }

    public void addComponent(Component component) {
        this.components.add(component);
    }

    public void updateComponents(Game game, float deltaTime) {
        for (int i = 0; i < components.size(); i++) {
            components.get(i).update(game, deltaTime);
        }
    }

    public void renderComponents(Game game) {
        for (int i = 0; i < components.size(); i++) {
            components.get(i).render(game);
        }
    }

    public void onCollisionEvent(GameObject other) {}



    @Override
    public void init(Game game) {

    }

    @Override
    public void render(Game game) {
        if(visible && sprite != null) {
            if(sprite instanceof ImageTile) {
                game.renderer.drawImageTile((ImageTile)sprite, positionX, positionY, spriteIndex[0], spriteIndex[1]);
            } else {
                game.renderer.drawImage(sprite, positionX, positionY);
            }

        }
    }

    @Override
    public void update(Game game, float deltaTime) {
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

    public int getWidth() {
        if(sprite != null) {
            if(sprite instanceof ImageTile) {
                return ((ImageTile) sprite).getTileWidth();
            } else {
                return sprite.getWidth();
            }
        }

        return 0;
    }

    public int getHeight() {
        if(sprite != null) {
            if(sprite instanceof ImageTile) {
                return ((ImageTile) sprite).getTileHeight();
            } else {
                return sprite.getHeight();
            }
        }

        return 0;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
