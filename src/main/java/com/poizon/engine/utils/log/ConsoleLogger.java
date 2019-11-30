package com.poizon.engine.utils.log;

import com.poizon.engine.utils.time.Time;

public class ConsoleLogger implements ILogger {
    private boolean isDebug;

    public ConsoleLogger() {
        isDebug = false;
    }

    public ConsoleLogger(boolean isDebug) {
        this.isDebug = isDebug;
    }

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
                if(isDebug || (!type.equals(LogLevel.DEBUG) || !type.equals(LogLevel.TRACE)))
                    System.out.println(getMessage(message, type));
                break;
        }
    }

}
