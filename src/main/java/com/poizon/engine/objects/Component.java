package com.poizon.engine.objects;

import com.poizon.engine.render.IRenderable;
import com.poizon.engine.render.IUpdateable;

/**
 * Anything that could be attached to a game object to add more functionality, like animations, collision.
 * Components will be rendered and updated automatically for each object.
 *
 * @author Jose Ulloa
 */
public abstract class Component implements IUpdateable, IRenderable {
    /**
     * Parent object to attach.
     */
    protected GameObject parent;

    public Component(GameObject parent) {
        this.parent = parent;
    }

    public GameObject getParent() {
        return parent;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }

}
