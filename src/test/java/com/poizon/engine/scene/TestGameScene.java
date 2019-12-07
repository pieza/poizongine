package com.poizon.engine.scene;

import com.poizon.engine.Game;
import com.poizon.engine.audio.SoundClip;
import com.poizon.engine.graphics.Image;
import com.poizon.engine.objects.Player;
import com.poizon.engine.render.IRenderer;
import com.poizon.engine.scenes.GameScene;
import com.poizon.engine.utils.log.LogLevel;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static com.poizon.engine.Game.*;

public class TestGameScene extends GameScene {


    public TestGameScene() {
        objects.put("PLAYER", new Player());
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }
}
