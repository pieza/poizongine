package com.poizon.engine.utils.log;

public interface ILogger {
    void log(String message);

    void log(String message, LogTypes type);
}
