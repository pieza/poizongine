package com.poizon.engine.scenes;

import com.poizon.engine.Game;
import com.poizon.engine.objects.GameObject;
import com.poizon.engine.render.IRenderer;

import java.util.HashMap;
import java.util.Map;

public abstract class GameScene {
    protected Map<String, GameObject> objects = new HashMap();

    public abstract void update(float deltaTime);

    public abstract  void render();

    public void renderObjects() {
        objects.forEach((key, value) -> {
            value.render();
        });
    }

    public void updateObjects(float deltaTime) {
        objects.forEach((key, value) -> {
            value.update(deltaTime);
        });
    }
}
