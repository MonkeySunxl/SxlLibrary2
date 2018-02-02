package com.sxl.sxllibrary.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date: 时间工具类
 */
public class TimeDifferenceUtils {
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE    = new SimpleDateFormat("yyyy-MM-dd");

    private TimeDifferenceUtils() {
        throw new AssertionError();
    }

    /**
     * 长时间的字符串
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * chang time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * 以毫秒为计算时间
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *   2017-07-26 06:12:42
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *  2017-07-26
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }
    public static String getTimeDifference(String time) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        long diff = 0;
        try {
            Date publish_date = df.parse(time);
            Date now_date = new Date();
            diff = now_date.getTime() - publish_date.getTime();
            if (diff < 0) {
                return "from未来";
            } else {
                diff /= (1000 * 60);
                if (diff <= 60) {
                    return diff + "分钟前";
                }
                diff /= 60;
                if (diff <= 24) {
                    return diff + "小时前";
                }
                diff /= 24;
                if (diff < 30) {
                    if (diff == 1) return "昨天";
                    return diff + "天前";
                }
                DateFormat df_date = new SimpleDateFormat("MM-dd");
                return df_date.format(publish_date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
}