package com.poizon.engine.scenes;

import com.poizon.engine.graphics.Font;

import static com.poizon.engine.Game.*;

public class DebugScene extends GameScene {
    private Font font = new Font("/fonts/test.png");
    private float fps;

    @Override
    public void update(float deltaTime) {
        fps = deltaTime;
    }

    @Override
    public void render() {
        renderer.drawText("DEBUG MODE", font, 0, 0, 0xff00ffff);
        renderer.drawText("FPS: " + fps, font, 0, 16, 0xff00ffff);
    }
}
