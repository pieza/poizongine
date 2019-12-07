package com.poizon.engine.scenes;

import com.poizon.engine.graphics.Font;
import com.poizon.engine.utils.time.Time;

import static com.poizon.engine.Game.*;

public class DebugScene extends GameScene {
    private Font font = new Font("/fonts/test.png");
    private float fps;

    @Override
    public void update() {
        fps = Time.fps;
    }

    @Override
    public void render() {
        renderer.drawText("POIZONGINE V" + 0.3, font, 0, 0, 0xff00ffff);
        renderer.drawText("DEBUG MODE", font, 0, 16, 0xff00ffff);
        renderer.drawText("FPS: " + fps, font, 0, 32, 0xff00ffff);
    }
}
