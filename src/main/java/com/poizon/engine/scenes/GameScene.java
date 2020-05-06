package com.poizon.engine.scenes;

import com.poizon.engine.Game;
import com.poizon.engine.objects.GameObject;

import java.util.HashMap;
import java.util.Map;

public abstract class GameScene {
    protected Map<String, GameObject> objects = new HashMap();

    public abstract void update(Game game);

    public abstract void render(Game game);

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
