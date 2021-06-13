package main.java.utils;

import javax.swing.text.DateFormatter;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.Calendar;

public class DateTime {
    public static String toString(Timestamp date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }
    public static Timestamp now() {
        Timestamp date = new Timestamp(Calendar.getInstance().getTime().getTime());
       return date;
    }
}
