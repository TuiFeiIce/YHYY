package com.yhyy.qwframe.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IceWolf on 2019/9/20.
 */
public class DateUtil {
    static SimpleDateFormat formatshort = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
    static SimpleDateFormat formatlong = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM

    /**
     * 指定格式返回当前系统日期
     */
    public static String getNowDate() {
        return formatshort.format(new Date());
    }

    /**
     * 指定格式返回当前系统时间
     */
    public static String getNowTime() {
        return formatlong.format(new Date());
    }

    /**
     * 根据两个秒数 获取两个时间差
     */
    public static int getDatePoor(long nowDate, long lastDate) {
        long dm = 1000 * 24 * 60 * 60;
        // 获得两个时间的秒时间差异
        long diff = (nowDate * 1000L) - (lastDate * 1000L);
        // 计算差多少天
        long day = diff / dm;
        return (int) day;
    }

    /**
     * 根据时间转换成Date
     */
    public static Date getDateShort(String dateTime) {
        Date date = null;
        try {
            date = formatshort.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 根据时间转换成Date
     */
    public static Date getDateLong(String dateTime) {
        Date date = null;
        try {
            date = formatlong.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * date转string日期
     */
    public static String getDate(Date date) {
        String getDate = formatshort.format(date);
        return getDate;
    }

    /**
     * date转string时间
     */
    public static String getTime(Date date) {
        String getTime = formatlong.format(date);
        return getTime;
    }

}
