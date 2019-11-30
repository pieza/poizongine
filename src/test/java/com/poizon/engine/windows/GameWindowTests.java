package com.poizon.engine.windows;

import com.poizon.engine.config.Settings;
import org.junit.Assert;
import org.junit.Test;

public class GameWindowTests {

    @Test
    public void CreateGameWindow() {
        GameWindow gameWindow = new GameWindow(new Settings());

        Assert.assertNotNull(gameWindow);
    }
}
