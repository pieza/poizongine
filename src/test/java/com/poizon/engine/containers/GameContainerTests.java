package com.poizon.engine;

import com.poizon.engine.containers.GameContainer;
import org.junit.Test;
import org.junit.Assert;

public class GameContainerTests {

    @Test
    public void CreateGameContainer() {
        GameContainer game = new GameContainer();

        Assert.assertNotNull(game);
    }
}
