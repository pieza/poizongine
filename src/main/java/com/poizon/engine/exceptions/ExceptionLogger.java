package com.poizon.engine.exceptions;

import com.poizon.engine.utils.log.ILogger;
import com.poizon.engine.utils.log.LogLevel;

public class ExceptionLogger {
    public static ILogger logger;

    public static void log(Exception ex) {
        logger.log(LogLevel.ERROR, ex.getMessage());
    }
}
