package com.poizon.engine.scenes;

import com.poizon.engine.Game;
import com.poizon.engine.render.IRenderer;

public abstract class GameScene {

    public abstract void update(float frame);

    public abstract  void render();
}
