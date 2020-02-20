package com.example.demony.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by 张瀛煜 on 2020-02-20 ：）
 */
public class SimpData {
    public static String  getMyDate(String type, Date date){
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }
}
