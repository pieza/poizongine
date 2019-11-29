package com.poizon.engine.utils.log;

import com.poizon.engine.utils.time.Time;

public class ConsoleLogger implements ILogger {

    private String getMessage(String message, LogTypes type){
        return String.format("%s - [%s]: %s", Time.current(), type, message);
    }

    @Override
    public void log(String message) {
        log(message, LogTypes.INFO);
    }

    @Override
    public void log(String message, LogTypes type) {
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
