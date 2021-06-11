package main.java.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {
    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}