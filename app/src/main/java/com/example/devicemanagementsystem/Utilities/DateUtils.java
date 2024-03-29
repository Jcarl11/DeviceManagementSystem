package com.example.devicemanagementsystem.Utilities;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    private static final String TAG = "DateUtils";
    private TimeZone timeZone;
    private DateFormat dateFormat; //Parse cloud date format
    private DateFormat simpleFormat;
    private DateFormat csvCompatibleFormat;

    public DateUtils() {
        timeZone = TimeZone.getDefault();
        //dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); //Parse cloud date format
        dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        simpleFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        csvCompatibleFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dateFormat.setTimeZone(timeZone);
        simpleFormat.setTimeZone(timeZone);
        csvCompatibleFormat.setTimeZone(timeZone);
    }

    public Date toISO8601Date(String dateString) {
        try {
            Log.d(TAG, "toISO8601: Parsing...");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "toISO8601: Exception thrown: " + e.getMessage());
        }

        return null;
    }

    public String toISO8601String(Date date) {
        Log.d(TAG, "toDateString: Result: " + dateFormat.format(date));
        return dateFormat.format(date);
    }

    public String toDateString(Date date){
        Log.d(TAG, "toDateString: Simple format" + simpleFormat.format(date));
        return simpleFormat.format(date);
    }

}
