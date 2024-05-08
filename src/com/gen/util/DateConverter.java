package com.gen.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    // Convert Date to String
    public static String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT);
        return dateFormat.format(date);
    }

    // Convert String to Date
    public static Date stringToDate(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT);
        return dateFormat.parse(dateString);
    }
}
