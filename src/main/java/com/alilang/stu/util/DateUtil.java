package com.alilang.stu.util;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * 时间工具类 天 - 小时 ：分
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
public class DateUtil {


    /**
     * 将 Date 转为 LocalDate
     *
     * @param date
     * @return java.time.LocalDate;
     */
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 将 LocalDate 转为 Date
     *
     * @param date
     * @return java.time.LocalDate;
     */
    public static Date localDateToDate(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDate格式化
     */
    public static String localDateToString(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dtf.format(date);
    }


    /**
     * LocalDate格式化
     */
    public static LocalDate stringToLocalDate(String date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, df);
    }

    public static LocalDate stringToLocalDate(String date, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(date, df);
    }

    public static String getDateMin(String date) {
        LocalDate localDate = stringToLocalDate(date);
        return LocalDateTime.of(localDate, LocalTime.MIN).toInstant(ZoneOffset.of("+8")).toString();
    }

    public static String getDateMax(String date) {
        LocalDate localDate = stringToLocalDate(date);
        return LocalDateTime.of(localDate, LocalTime.MIN).toInstant(ZoneOffset.of("+8")).toString();
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 获取当前时间的前1小时
     */
    public static Date getBeforeHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        return calendar.getTime();
    }
}
