package com.poizon.engine.utils.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time  {

    public static double now(){
        return System.nanoTime() / 1000000000.0;
    }

    public static String current() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
