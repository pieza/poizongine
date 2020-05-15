package com.poizon.engine.physics;


import com.poizon.engine.objects.GameObject;
import com.poizon.engine.render.IRenderable;
import com.poizon.engine.render.IUpdateable;

public abstract class Component implements IUpdateable, IRenderable {
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
