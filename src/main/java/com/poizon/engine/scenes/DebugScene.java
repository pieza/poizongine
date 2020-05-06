package com.poizon.engine.scenes;

import com.poizon.engine.Game;
import com.poizon.engine.graphics.Font;
import com.poizon.engine.utils.time.Time;


public class DebugScene extends GameScene {
    private Font font = new Font("/fonts/test.png");
    private float fps;

    @Override
    public void update(Game game) {
        fps = Time.fps;
    }

    @Override
    public void render(Game game) {
        game.renderer.drawText("POIZONGINE V" + 0.3, font, 0, 0, 0xff00ffff);
        game.renderer.drawText("DEBUG MODE", font, 0, 16, 0xff00ffff);
        game.renderer.drawText("FPS: " + fps, font, 0, 32, 0xff00ffff);
    }
}
