package com.lizzie.lotto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String getDate(int y, int m, int d){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.set(y,m-1,d);

        Date date = new Date(calendar.getTimeInMillis());

        dateFormat.format(date);
        return dateFormat.format(date);
    }
}
