package com.cengizhanozeyranoglu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getCurrentDate(Date date ){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.format(date);
        return simpleDateFormat.format(date);
    }
}
