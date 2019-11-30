package com.poizon.engine.utils.log;

import com.poizon.engine.utils.time.Time;

public class ConsoleLogger implements ILogger {

    private String getMessage(String message, LogLevel type){
        return String.format("%s - [%s]: %s", Time.current(), type, message);
    }

    @Override
    public void log(String message) {
        log(LogLevel.INFO, message);
    }

    @Override
    public void log(LogLevel type, String message) {
        switch (type) {
            case ERROR:
                System.err.println(getMessage(message, type));
                break;
            default:
                System.out.println(getMessage(message, type));
                break;
        }
    }

}
