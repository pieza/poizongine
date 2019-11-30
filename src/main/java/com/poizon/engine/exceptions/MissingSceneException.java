package com.poizon.engine.exceptions;

import com.poizon.engine.utils.log.LogLevel;

import static com.poizon.engine.exceptions.ExceptionLogger.*;

public class MissingSceneException extends Exception {
    public MissingSceneException(String sceneName) {
        super(String.format("The scene %s doesn't exist.", sceneName));
        logger.log(LogLevel.ERROR, this.getMessage());
    }
}
