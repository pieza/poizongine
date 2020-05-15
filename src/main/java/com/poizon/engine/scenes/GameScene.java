package com.poizon.engine.scenes;

import com.poizon.engine.Game;
import com.poizon.engine.graphics.Image;
import com.poizon.engine.objects.GameObject;
import com.poizon.engine.render.IInitializable;
import com.poizon.engine.render.IRenderable;
import com.poizon.engine.render.IUpdateable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class GameScene implements IInitializable, IUpdateable, IRenderable {
    public Map<String, GameObject> objects = new HashMap<>();


    public void initializeObjects(Game game) {
        objects.forEach((key, value) -> {
            value.init(game);
        });
    }

    public void renderObjects(Game game) {
        objects.forEach((key, value) -> {
            value.render(game);
            value.renderComponents(game);
        });
    }

    public void updateObjects(Game game, float deltaTime) {
        Iterator it = objects.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap.Entry entry = (HashMap.Entry) it.next();
            GameObject object = (GameObject) entry.getValue();
            if(!object.isAlive()) {
                it.remove();
            } else {
                object.update(game, deltaTime);
                object.updateComponents(game, deltaTime);
            }
        }
    }
}
