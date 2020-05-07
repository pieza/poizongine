package com.poizon.engine.scenes;

import com.poizon.engine.Game;
import com.poizon.engine.objects.GameObject;
import com.poizon.engine.render.IRenderable;
import com.poizon.engine.render.IUpdateable;

import java.util.HashMap;
import java.util.Map;

public abstract class GameScene implements IUpdateable, IRenderable {
    protected Map<String, GameObject> objects = new HashMap();

    public void renderObjects(Game game) {
        objects.forEach((key, value) -> {
            value.render(game);
        });
    }

    public void updateObjects(Game game) {
        objects.forEach((key, value) -> {
            value.update(game);
        });
    }
}
