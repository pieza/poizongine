package com.poizon.engine.containers;

import com.poizon.engine.containers.GameContainer;
import org.junit.Test;
import org.junit.Assert;

public class GameContainerTests {

    @Test
    public void CreateGameContainerDefaults() {
        GameContainer game = new GameContainer();

        Assert.assertNotNull(game);
    }
}
