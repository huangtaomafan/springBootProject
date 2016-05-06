/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期处理工具类
 * @author liuzq
 * @version $Id: DateUtil.java, v 0.1 2016年4月27日 上午11:27:43 liuzq Exp $
 */
public class DateUtil {

    /**一天的秒数*/
    public final static long   ONE_DAY_SECONDS      = 86400;

    /**一天的毫秒数*/
    public final static long   ONE_DAY_MILL_SECONDS = 86400000;

    /**Number of milliseconds in a standard second*/
    public static final long   MILLIS_PER_SECOND    = 1000;

    /**Number of milliseconds in a standard minute*/
    public static final long   MILLIS_PER_MINUTE    = 60 * MILLIS_PER_SECOND;

    /** Number of milliseconds in a standard hour*/
    public static final long   MILLIS_PER_HOUR      = 60 * MILLIS_PER_MINUTE;

    /**Number of milliseconds in a standard day*/
    public static final long   MILLIS_PER_DAY       = 24 * MILLIS_PER_HOUR;

    /**yyyyMMdd时间格式*/
    public final static String shortFormat          = "yyyyMMdd";

    /**yyyyMMddHHmmss时间格式*/
    public final static String longFormat           = "yyyyMMddHHmmss";

    /** 比long少秒的日期格式 yyyyMMddHHmm */
    public final static String lowwerLongFormat     = "yyyyMMddHHmm";

    /**yyyy-MM-dd时间格式*/
    public final static String webFormat            = "yyyy-MM-dd";

    /**HHmmss时间格式*/
    public final static String timeFormat           = "HHmmss";

    /**yyyyMM时间格式*/
    public final static String monthFormat          = "yyyyMM";

    /**yyyy年MM月dd日时间格式*/
    public final static String chineseDtFormat      = "yyyy年MM月dd日";

    /**yyyy-MM-dd HH:mm:ss时间格式*/
    public final static String newFormat            = "yyyy-MM-dd HH:mm:ss";

    /**yyyy-MM-dd HH:mm时间格式*/
    public final static String noSecondFormat       = "yyyy-MM-dd HH:mm";

    /**yyyy时间格式*/
    public static String       yearFormat           = "yyyy";

    /**yyyy年MM月dd日HH点mm分ss秒*/
    public final static String chineseAllDateFormat = "yyyy年MM月dd日HH点mm分ss秒";

    /**HH:mm时间格式*/
    public final static String hoursFormat          = "HH:mm";

    /**HH:mm时间格式*/
    public final static String SecondFormat         = "HH:mm:ss";

    /**yyyy.MM.dd 时间格式*/
    public final static String DatePickerFormat     = "yyyy.MM.dd";

    /**
     * 得到新的时间格式
     * @param pattern   匹配表达式
     * @return          新的时间格式
     */
    public static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);

        df.setLenient(false);
        return df;
    }

    /**
     * 时间格式
     * @param date      格式化前的时间
     * @param format    需要转换的格式
     * @return          格式化后的时间
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 时间格式
     * @param date      格式化前的时间
     * @param format    需要转换的格式
     * @return          格式化后的时间
     * @throws ParseException 
     */
    public static Date formatToDate(String dateStr, String format) throws ParseException {
        if (dateStr == null) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateStr);
    }

    /**
     * 日期格式之间进行转换
     * 
     * @param date
     * @param formatFrom
     * @param formatTo
     * @return
     */
    public static String formatDate(String date, String formatFrom, String formatTo) {

        DateFormat df = getNewDateFormat(formatFrom);
        String newDate = null;
        try {
            Date dt = df.parse(date);
            newDate = format(dt, formatTo);
        } catch (ParseException e) {
            return newDate;
        }

        return newDate;

    }

    /**
     * 返回日期时间
     *
     * @param stringDate
     *
     * @return
     *
     * @throws ParseException
     */
    public static final Date stringToDateTime(String stringDate, String format) {
        if (stringDate == null) {
            return null;
        }

        try {
            return getFormat(format).parse(stringDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 取得当前年份
     * @return
     */
    public static String getYearString() {
        DateFormat dateFormat = getNewDateFormat(yearFormat);

        return getDateString(new Date(), dateFormat);
    }

    /**
     * 格式化日期，去除时间
     * @param sDate   格式化前的日期
     * @return        格式化后的日期
     * @throws ParseException   时间格式化异常
     */
    public static Date parseDateNoTime(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() < shortFormat.length())) {
            throw new ParseException("length too little", 0);
        }

        if (!StringUtil.isNumeric(sDate)) {
            throw new ParseException("not all digit", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 格式化日期，去除时间
     * @param sDate     格式化前的日期
     * @param format    需要转换的格式
     * @return          格式化后的日期
     * @throws ParseException 时间格式化异常
     */
    public static Date parseDateNoTime(String sDate, String format) throws ParseException {
        if (StringUtil.isBlank(format)) {
            throw new ParseException("Null format. ", 0);
        }

        DateFormat dateFormat = new SimpleDateFormat(format);

        if ((sDate == null) || (sDate.length() < format.length())) {
            throw new ParseException("length too little", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 格式化日期，去除时间
     * @param sDate     格式化前的日期
     * @param delimit   替换字符
     * @return          格式化后的日期
     * @throws ParseException 时间格式化异常
     */
    public static Date parseDateNoTimeWithDelimit(String sDate,
                                                  String delimit) throws ParseException {
        sDate = sDate.replaceAll(delimit, "");

        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() != shortFormat.length())) {
            throw new ParseException("length not match", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 将时间转换成yyyyMMddHHmmss格式
     * @param sDate 格式化前的日期
     * @return      格式化后的日期
     */
    public static Date parseDateLongFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);
        Date d = null;

        if ((sDate != null) && (sDate.length() == longFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }

        return d;
    }

    /**
     * 将时间yyyy-MM-dd HH:mm:ss转换成Date格式
     * @param sDate 格式化前的日期
     * @return      格式化后的日期
     */
    public static Date parseDateNewFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(newFormat);
        Date d = null;
        if ((sDate != null) && (sDate.length() == newFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }
        return d;
    }

    /**
     * 将时间转换成yyyy-MM-dd格式
     * @param sDate 格式化前的日期
     * @return      格式化后的日期
     */
    public static Date parseDateFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(webFormat);
        Date d = null;
        if ((sDate != null) && (sDate.length() == webFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }
        return d;
    }

    /**
     * 计算当前时间几小时之前的时间
     *
     * @param date
     * @param hours
     *
     * @return
     */
    public static Date decreaseHours(Date date, long hours) {
        return decreaseMinutes(date, hours * 60);
    }

    /**
     * 计算当前时间几分钟之前的时间
     *
     * @param date
     * @param minutes
     *
     * @return
     */
    public static Date decreaseMinutes(Date date, long minutes) {
        return decreaseSeconds(date, minutes * 60);
    }

    /**
     * @param date1
     * @param secs
     *计算当前时间几秒钟之前的时间
     * @return
     */

    public static Date decreaseSeconds(Date date1, long secs) {
        return new Date(date1.getTime() - (secs * 1000));
    }

    /**
     * 计算当前时间几小时之后的时间
     *
     * @param date  时间
     * @param hours 小时数
     *
     * @return      转换后的时间
     */
    public static Date addHours(Date date, long hours) {
        return addMinutes(date, hours * 60);
    }

    /**
     * 计算当前时间几分钟之后的时间
     *
     * @param date      时间
     * @param minutes   分钟数
     *
     * @return          转换后的时间
     */
    public static Date addMinutes(Date date, long minutes) {
        return addSeconds(date, minutes * 60);
    }

    /**
     * 增加毫秒数
     * @param date1 日期
     * @param secs  毫秒数
     * @return      增加后的毫秒数
     */
    public static Date addSeconds(Date date1, long secs) {
        return new Date(date1.getTime() + (secs * 1000));
    }

    /**
     * 判断输入的字符串是否为合法的小时
     *
     * @param hourStr       小时
     *
     * @return true/false   是否合法
     */
    public static boolean isValidHour(String hourStr) {
        if (!StringUtil.isEmpty(hourStr) && StringUtil.isNumeric(hourStr)) {
            int hour = new Integer(hourStr).intValue();

            if ((hour >= 0) && (hour <= 23)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断输入的字符串是否为合法的分或秒
     *
     * @param str 分或秒
     *
     * @return true/false   是否合法
     */
    public static boolean isValidMinuteOrSecond(String str) {
        if (!StringUtil.isEmpty(str) && StringUtil.isNumeric(str)) {
            int hour = new Integer(str).intValue();

            if ((hour >= 0) && (hour <= 59)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 取得新的日期
     *
     * @param date1 日期
     * @param days 天数
     *
     * @return 新的日期
     */
    public static Date addDays(Date date1, long days) {
        return addSeconds(date1, days * ONE_DAY_SECONDS);
    }

    /**
     * 获取明天的日期
     * @param sDate 今天的日期
     * @return      明天的日期
     * @throws ParseException
     */
    public static String getTomorrowDateString(String sDate) throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    /**
     * 获得yyyyMMddHHmmss格式的时间
     * @param date  转换前的时间
     * @return      转换后的时间
     */
    public static String getLongDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 获得yyyyMMddHHmm格式的时间
     * @param date  转换前的时间
     * @return      转换后的时间
     */
    public static String getLowwerLongDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(lowwerLongFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 获得yyyy-MM-dd HH:mm:ss格式的时间
     * @param date  转换前的时间
     * @return      转换后的时间
     */
    public static String getNewFormatDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(newFormat);
        return getDateString(date, dateFormat);
    }

    /**
     * 获得HH:mm格式的时间
     * @param date  转换前的时间
     * @return      转换后的时间
     */
    public static String getHoursFormatDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(hoursFormat);
        return getDateString(date, dateFormat);
    }

    /**
     * 获得HH:mm:ss格式的时间
     * @param date  转换前的时间
     * @return      转换后的时间
     */
    public static String getSecondFormatDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(SecondFormat);
        return getDateString(date, dateFormat);
    }

    /**
     * 格式化时间
     * @param date          转换前的日期
     * @param dateFormat    需要转换的格式
     * @return              转换后的日期
     */
    public static String getDateString(Date date, DateFormat dateFormat) {
        if (date == null || dateFormat == null) {
            return null;
        }

        return dateFormat.format(date);
    }

    /**
     * 获得昨天的时间
     * @param sDate 今天的时间
     * @return      昨天的时间
     * @throws ParseException
     */
    public static String getYesterDayDateString(String sDate) throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, -ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    /**
     * 当天的时间格式化为 yyyyMMdd
     * @param date 转换前的日期
     * @return     转换后的日期
     */
    public static String getDateString(Date date) {
        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(date);
    }

    /**
     * 获得yyyy-MM-dd格式的时间
     * @param date 转换前的时间
     * @return     转换后的时间
     */
    public static String getWebDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(webFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得“X年X月X日”的日期格式
     *
     * @param date 转换前的时间
     *
     * @return     转换后的时间
     */
    public static String getChineseDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(chineseDtFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得“X年X月X日x点x分x秒”的日期格式
     *
     * @param date 转换前的时间
     *
     * @return     转换后的时间
     */
    public static String getChineseAllDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(chineseAllDateFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 获得yyyyMMdd格式的时间
     * @return 转换后的时间
     */
    public static String getTodayString() {
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(new Date(), dateFormat);
    }

    /**
     * 获得HHmmss格式的时间
     * @param date  转换前的时间
     * @return      转换后的时间
     */
    public static String getTimeString(Date date) {
        DateFormat dateFormat = getNewDateFormat(timeFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 获得几天前的时间    
     * @param days 天数
     * @return     几天前的时间        
     */
    public static Date getBeforeDay(int days) {
        return new Date(System.currentTimeMillis() - (ONE_DAY_MILL_SECONDS * days));
    }

    /**
     * 获得几天前的时间    
     * @param days 天数
     * @return     几天前的时间        "yyyyMMdd"
     */
    public static String getBeforeDayString(int days) {
        Date date = new Date(System.currentTimeMillis() - (ONE_DAY_MILL_SECONDS * days));
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 获得几天前的时间
     * @param days 天数
     * @return     几天前的时间                "yyyy-MM-dd"
     */
    public static String getBeforeDayStringWithWebFormat(int days) {
        Date date = new Date(System.currentTimeMillis() - (ONE_DAY_MILL_SECONDS * days));
        DateFormat dateFormat = getNewDateFormat(webFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得两个日期间隔秒数（日期1-日期2）
     *
     * @param one 日期1
     * @param two 日期2
     *
     * @return 间隔秒数
     */
    public static long getDiffSeconds(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000;
    }

    /**
     * 获得相差的分钟数
     * @param one 前面的时间
     * @param two 后面的时间
     * @return    分钟数
     */
    public static long getDiffMinutes(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / (60 * 1000);
    }

    /**
     * 取得两个日期的间隔天数
     *
     * @param one   前面的时间
     * @param two   后面的时间
     *
     * @return 间隔天数
     */
    public static long getDiffDays(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 获得几天前的时间
     * @param dateString 时间
     * @param days       天数
     * @return           转换后的时间
     */
    public static String getBeforeDayString(String dateString, int days) {
        Date date;
        DateFormat df = getNewDateFormat(shortFormat);

        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            date = new Date();
        }

        date = new Date(date.getTime() - (ONE_DAY_MILL_SECONDS * days));

        return df.format(date);
    }

    /**
    * 获取指定天数以前的字符串
    * @param days
    * @param format
    * @return
    */
    public static String getBeforeDayString(int days, String format) {
        Date date = new Date(System.currentTimeMillis() - (ONE_DAY_MILL_SECONDS * days));
        DateFormat dateFormat = getNewDateFormat(format);

        return getDateString(date, dateFormat);
    }

    /**
     * 验证是否合法的yyyyMMdd时间格式
     * @param strDate 时间
     * @return        是否合法
     */
    public static boolean isValidShortDateFormat(String strDate) {
        if (strDate.length() != shortFormat.length()) {
            return false;
        }

        try {
            Integer.parseInt(strDate); //---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(shortFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 验证是否合法的yyyyMMdd时间格式
     * @param strDate   时间   
     * @param delimiter 替换字符
     * @return          是否合法
     */
    public static boolean isValidShortDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidShortDateFormat(temp);
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     * 
     * @param strDate 时间
     * @return        转换后的时间
     */
    public static boolean isValidLongDateFormat(String strDate) {
        if (strDate.length() != longFormat.length()) {
            return false;
        }

        try {
            Long.parseLong(strDate); //---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(longFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 日期格式是否正确
     *
     * @param stringDate
     * @param dataFormat
     * @return
     *
     * @throws ParseException
     */
    public static boolean isValidDateFormat(String strDate, String dataFormat) {

        DateFormat df = getNewDateFormat(dataFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     * 
     * @param strDate    时间
     * @param delimiter  替换字符
     * @return           转换后的时间
     */
    public static boolean isValidLongDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidLongDateFormat(temp);
    }

    /**
     * 获得yyyyMMdd格式的时间
     * @param strDate 日期
     * @return        格式化后的时间
     */
    public static String getShortDateString(String strDate) {
        return getShortDateString(strDate, "-|/");
    }

    /**
     * 获得yyyyMMdd格式的时间
     * @param strDate   日期
     * @param delimiter
     * @return          格式化后的时间
     */
    public static String getShortDateString(String strDate, String delimiter) {
        if (StringUtil.isBlank(strDate)) {
            return null;
        }

        String temp = strDate.replaceAll(delimiter, "");

        if (isValidShortDateFormat(temp)) {
            return temp;
        }

        return null;
    }

    /**
     * 获得每个月的第一天
     * @return 获得每个月的第一天
     */
    public static String getShortFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(cal.getTime());
    }

    /**
     * 获得yyyy-MM-dd时间格式
     * @return 获得yyyy-MM-dd时间格式
     */
    public static String getWebTodayString() {
        DateFormat df = getNewDateFormat(webFormat);

        return df.format(new Date());
    }

    /**
     * 获得每个月的第一天，以yyyy-MM-dd格式
     * @return 获得每个月的第一天
     */
    public static String getWebFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(webFormat);

        return df.format(cal.getTime());
    }

    /**
     * 将时间从一种格式转换成另一种格式
     * @param dateString 时间
     * @param formatIn   转换前的格式
     * @param formatOut  转换后的格式
     * @return           转换后的时间
     */
    public static String convert(String dateString, DateFormat formatIn, DateFormat formatOut) {
        try {
            Date date = formatIn.parse(dateString);

            return formatOut.format(date);
        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * 将时间转换成yyyy-MM-dd格式
     * @param dateString 时间
     * @return           转换后的时间
     */
    public static String convert2WebFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df1, df2);
    }

    /**
     * 将时间转换成yyyy年MM月dd日时间格式
     * @param dateString 时间
     * @return           转换后的时间
     */
    public static String convert2ChineseDtFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(chineseDtFormat);

        return convert(dateString, df1, df2);
    }

    /**
     * 从yyyy年MM月dd日时间格式转换
     * @param dateString 转换前的时间
     * @return           转换后的时间
     */
    public static String convertFromWebFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df2, df1);
    }

    /**
     * 比较两个时间的大小
     * @param date1 时间1
     * @param date2 时间2
     * @return      是否不小于
     */
    public static boolean webDateNotLessThan(String date1, String date2) {
        DateFormat df = getNewDateFormat(webFormat);

        return dateNotLessThan(date1, date2, df);
    }

    /**
     * 比较两个时间的大小
     * @param date1 时间1
     * @param date2 时间2
     * @param format yyyy-MM-dd格式的时间
     *
     * @return       是否不小于
     */
    public static boolean dateNotLessThan(String date1, String date2, DateFormat format) {
        try {
            Date d1 = format.parse(date1);
            Date d2 = format.parse(date2);

            if (d1.before(d2)) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 获取yyyy年MM月dd日HH:mm:ss格式的时间
     * @param today 今天的时间
     * @return      转换后的时间
     */
    public static String getEmailDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");

        todayStr = sdf.format(today);
        return todayStr;
    }

    /**
     * 获取MM月dd日HH:mm格式的时间
     * @param today 今天的时间
     * @return      转换后的时间
     */
    public static String getSmsDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH:mm");

        todayStr = sdf.format(today);
        return todayStr;
    }

    /**
     * 格式化时间段
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param format    指定格式
     * @return          转换后的时间
     */
    public static String formatTimeRange(Date startDate, Date endDate, String format) {
        if ((endDate == null) || (startDate == null)) {
            return null;
        }

        String rt = null;
        long range = endDate.getTime() - startDate.getTime();
        long day = range / MILLIS_PER_DAY;
        long hour = (range % MILLIS_PER_DAY) / MILLIS_PER_HOUR;
        long minute = (range % MILLIS_PER_HOUR) / MILLIS_PER_MINUTE;

        if (range < 0) {
            day = 0;
            hour = 0;
            minute = 0;
        }

        rt = format.replaceAll("dd", String.valueOf(day));
        rt = rt.replaceAll("hh", String.valueOf(hour));
        rt = rt.replaceAll("mm", String.valueOf(minute));

        return rt;
    }

    /**
     * 格式月
     * @param date 格式化前的时间
     * @return     格式化后的时间
     */
    public static String formatMonth(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(monthFormat).format(date);
    }

    /**
     * 获取系统日期的前一天日期，返回Date
     *
     * @return 前一天日期
     */
    public static Date getBeforeDate() {
        Date date = new Date();

        return new Date(date.getTime() - (ONE_DAY_MILL_SECONDS));
    }

    /**
     * 获得指定时间当天起点时间
     * 
     * @param date 当前时间
     * @return 当天起点时间
     */
    public static Date getDayBegin(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        df.setLenient(false);

        String dateString = df.format(date);

        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            return date;
        }
    }

    /**
     * 判断参date上min分钟后，是否小于当前时间
     * @param date 当前时间
     * @param min  分钟数
     * @return     是否小于当前时间
     */
    public static boolean dateLessThanNowAddMin(Date date, long min) {
        return addMinutes(date, min).before(new Date());

    }

    /**
     * 是否在当前时间之前
     * @param date 当前时间
     * @return     是否在当前时间之前
     */
    public static boolean isBeforeNow(Date date) {
        if (date == null)
            return false;
        return date.compareTo(new Date()) < 0;
    }

    /**
     * 转换成yyyy-MM-dd HH:mm时间格式
     * @param sDate 转换前的时间
     * @return      转换后的时间
     * @throws ParseException   时间格式化问题
     */
    public static Date parseNoSecondFormat(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(noSecondFormat);

        if ((sDate == null) || (sDate.length() < noSecondFormat.length())) {
            throw new ParseException("length too little", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 返回日期的时分
     * @param date 当前时间
     * @return     日期的时分
     */
    public static String getFormatDate(Date date) {
        if (date == null)
            return null;
        String strDate = format(date, DateUtil.noSecondFormat);
        return StringUtil.substring(strDate, 11);
    }

    /**
     * 获得前一天的时间
     *
     * @return 
     */
    public static Date getBeforeFormatDate() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = formatter.format(c.getTime());
        return formatter.parse(dateStr);
    }

    /**
     * 获取指定时间的后一天时间
     *
     * @param sdate 指定的时间
     * @return      指定时间的后一天
     * @throws ParseException
     */
    public static Date getAfterFormatDate(Date sdate) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(sdate);
        c.add(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = formatter.format(c.getTime());
        return formatter.parse(dateStr);
    }

    /**
     * 获取上个月当天00:00:00
     *
     * @return date
     */
    public static Date getTodayBeforeThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (month == 0) {
            year = year - 1;
            month = 11;
        } else {
            month = month - 1;
        }
        calendar.set(year, month, day, 00, 00, 00);
        return calendar.getTime();
    }

    /**
     * 获取当天23:59：59
     *
     * @return Date
     */
    public static Date getTodayLastSecond() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year, month, day, 23, 59, 59);
        return calendar.getTime();
    }

    /**
     * 通过格式化字符串（如"yyyy-MM-dd HH:mm"）获取format对象
     * @param format 格式化字符串
     * @return DateFormat format对象
     */
    public static final DateFormat getFormat(String format) {
        return new SimpleDateFormat(format);
    }

    /**
     * 转换日期格式字符串
     *
     * @param date "2010.10.21"
     * @param time "00:00"
     * @return "2010-10-21 00:00"
     */
    public static String getFormatString(String date, String time, String defaultTime) {
        if (StringUtil.isNotBlank(date)) {
            date = date.replace(".", "-");
            return StringUtil.trim(date) + " "
                   + (StringUtil.isNotBlank(time) ? StringUtil.trim(time) : defaultTime);
        }
        return null;
    }

    /**
     * 字符串  2005-06-30 15:50 转换成时间
     * @param date String
     * @return
     * @throws ParseException 
     */
    public static final Date simpleFormatDate(String dateString) throws ParseException {
        if (dateString == null) {
            return null;
        }
        return getFormat(noSecondFormat).parse(dateString);
    }

    /**
     * 获取HH:SS时间
     *  
     * @param endDate 操作时间
     * @param type 时间类型 1:时间往后移,
     *  例如:当前时间为2012-08-30 14:12:00返回时间为2012-08-30 14:30:00   
     *       当前时间为2012-08-30 14:31:00返回时间为2012-08-30 15:00:00  
     * 时间类型2：时间往前移
     *       当前时间为2012-08-30 14:35:00返回时间为2012-08-30 14:30:00   
     *       当前时间为2012-08-30 14:25:00返回时间为2012-08-30 14:00:00  
     * @return 时分
     */
    public static String gethoursFormatBeforeAndAfter(Date endDate, String type) {

        //当前小时
        String beforTime = StringUtil.substring(DateUtil.getHoursFormatDateString(endDate), 0, 2);
        //当前分钟
        String endTimeTemp = StringUtil.substring(DateUtil.getHoursFormatDateString(endDate), 3, 5);

        int endTime = Integer.parseInt(endTimeTemp);

        //当type等于1时间往后靠,说明取的是结束时间,其它情况取的是开始时间,开始时间往迁移
        if (StringUtil.equals(type, "1")) {

            // 如果当前分钟小于等于30默认为30,例如当前时间为1:25,默认为1:30
            if (endTime <= 30) {
                endTime = 30;
                return beforTime = beforTime + ":" + endTime;
            }
            //如果当前分钟大于30默认往前追加一个小时,例如当前时间为1:35,默认为2:00
            Date endDateTemp = DateUtil.addHours(endDate, 1);
            beforTime = StringUtil.substring(DateUtil.getHoursFormatDateString(endDateTemp), 0, 2)
                        + ":00";

            return beforTime;
        }

        // 如果当前分钟大于等于30默认为30,例如当前时间为1:35,默认为1:30
        if (endTime >= 30) {
            endTime = 30;
            return beforTime = beforTime + ":" + endTime;
        }

        // 如果当前分钟小于30默认为30,例如当前时间为1:25,默认为1:00
        return beforTime + ":00";

    }

    /**
     * 计算日期差值
     *
     * @param one
     * @param two
     * @return
     */
    public static int getDiffDays2(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (int) ((sysDate.getTimeInMillis() - failDate.getTimeInMillis())
                      / (24 * 60 * 60 * 1000));
    }

    /**
     * 加时间 
     * 例如入参为2013-10-18 16:12:15,10
     * 返回结果为2013-10-18 16:22:15,在2013-10-18 16:12:15基础上加10分钟 
     * 
     * @param now当前时间
     * @param minute 添加时间分钟
     * @return
     */
    public static Date addDateByMinute(Date now, int minute) {
        Date newDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(newDate);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }

}
