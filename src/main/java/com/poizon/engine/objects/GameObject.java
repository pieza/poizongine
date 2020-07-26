package com.poizon.engine.objects;

import com.poizon.engine.Game;
import com.poizon.engine.graphics.Image;
import com.poizon.engine.graphics.ImageTile;
import com.poizon.engine.render.IInitializable;
import com.poizon.engine.render.IRenderable;
import com.poizon.engine.render.IUpdateable;

import java.util.ArrayList;

/**
 * Element of an scene
 *
 * @author Jose Ulloa
 */
public abstract class GameObject implements IInitializable, IUpdateable, IRenderable {
    /**
     * Image of the game object
     */
    public Image sprite;

    /**
     * Position of the object.
     */
    protected int positionX, positionY;

    /**
     * Size of the object in pixels.
     */
    protected int height, width;

    /**
     * Padding of the object in pixels.
     */
    protected int paddingX, paddingY;

    /**
     * Index of the sprite sheet to display.
     */
    protected int[] spriteIndex;

    /**
     * Object mus be rendered.
     */
    protected boolean visible;

    /**
     * Tells if an object should exist.
     * Default true. If false, engine will destroy object automatically.
     */
    protected boolean alive;

    private ArrayList<Component> components = new ArrayList<>();

    public GameObject() {
        this.positionX = 0;
        this.positionY = 0;
        this.height = 0;
        this.width = 0;
        this.paddingX = 0;
        this.paddingY = 0;
        this.spriteIndex = new int[] { 0, 0 };
        this.visible = true;
        this.alive = true;
    }

    /**
     * Attach components to the game object.
     *
     * @param component component to add.
     */
    public void addComponent(Component component) {
        this.components.add(component);
    }

    /**
     * Updates all the components of the object.
     * @param game      game instance.
     * @param deltaTime real game time.
     */
    public void updateComponents(Game game, float deltaTime) {
        for (int i = 0; i < components.size(); i++) {
            components.get(i).update(game, deltaTime);
        }
    }

    /**
     * Renders all the components of the object.
     * @param game game instance.
     */
    public void renderComponents(Game game) {
        for (int i = 0; i < components.size(); i++) {
            components.get(i).render(game);
        }
    }

    /**
     * Detects when an object is colliding with other.
     *
     * @param other other object that
     */
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

    public int[] getSpriteIndex() {
        return spriteIndex;
    }

    public void setSpriteIndex(int[] spriteIndex) {
        this.spriteIndex = spriteIndex;
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
                return ((ImageTile) sprite).getTileWidth() - paddingX;
            } else {
                return sprite.getWidth() - paddingX;
            }
        }

        return width - paddingX;
    }

    public int getHeight() {
        if(sprite != null) {
            if(sprite instanceof ImageTile) {
                return ((ImageTile) sprite).getTileHeight() - paddingY;
            } else {
                return sprite.getHeight() - paddingY;
            }
        }

        return height - paddingY;
    }

    public int getPaddingX() {
        return paddingX;
    }

    public void setPaddingX(int paddingX) {
        this.paddingX = paddingX;
    }

    public int getPaddingY() {
        return paddingY;
    }

    public void setPaddingY(int paddingY) {
        this.paddingY = paddingY;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
