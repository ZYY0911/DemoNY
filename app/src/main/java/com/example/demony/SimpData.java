package com.example.demony;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpData {
    public static String Simp(Date date, String type) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }
}
