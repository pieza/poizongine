package com.poizon.engine.utils.log;

public interface ILogger {
    void log(String message);

    void log(LogLevel type, String message);
}
