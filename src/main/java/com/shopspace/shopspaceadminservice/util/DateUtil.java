package com.shopspace.shopspaceadminservice.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS zzz";
    static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    public static String getUTCdatetimeAsString(Date value) {
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(value);
    }
}
