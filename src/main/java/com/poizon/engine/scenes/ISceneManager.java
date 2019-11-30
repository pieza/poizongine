package com.poizon.engine.scenes;

import com.poizon.engine.exceptions.MissingSceneException;

public interface ISceneManager {
    void addScene(String key, GameScene scene);
    void removeScene(String key);
    void setScene(String key) throws MissingSceneException;
}
