package com.muskteer.tm.common.util;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p> @author 张寅  </p>
 * <p> @since 2011-3-8 </p>
 * <p> @copyright (c)2011 </p>
 * <p> @see </p>
 * <p> @description </p>
 */
public class DateUtil {

    //private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 日期格式转换 yyyyMMddHHmmss
     *
     * @return
     */
    public static String formatDateToString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    public static String todayString(String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(new Date());
    }

    /**
     * 日期格式转换  当前时间转为目标格式
     */
    public static String formatDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateStr);
        return sdf.format(new Date());
    }

    /**
     * 日期格式转换
     *
     * @return formatStr 型的日期字符串
     * panjianhong 2012-10-12
     */
    public static String formatDate(String dateStr, String selfForamt, String targetFormatStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(selfForamt);
        SimpleDateFormat sdf2 = new SimpleDateFormat(targetFormatStr);
        Date date = sdf.parse(dateStr);
        return sdf2.format(date);
    }

    public static String formatDate(Date date, String formatStr) throws Exception {
        SimpleDateFormat sdf1 = new SimpleDateFormat(formatStr);
        return sdf1.format(date);
    }

    public static String parseDate(String dateStr) throws Exception {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf1.parse(dateStr);
        return sdf2.format(date);
    }

    public static Date toDate(String date) {
        return new Date(DateUtil.toCalendar(date).getTime().getTime());
    }

    public static Timestamp toTimestamp(String date) {
        if (StringUtils.isNotEmpty(date)) {
            return Timestamp.valueOf(date);
        }
        return null;
    }

    public static Calendar toCalendar(String date) {
        if (date == null) {
            return null;
        }
        String[] s = date.split("-");
        int day = Integer.parseInt(s[2]);
        int month = Integer.parseInt(s[1]) - 1;
        int year = Integer.parseInt(s[0]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar;
    }

    public static String dateAgo(int ago, String dateFormat) {
        Calendar calendars = Calendar.getInstance();
        calendars.add(Calendar.DAY_OF_MONTH, -ago);
        return new SimpleDateFormat(dateFormat).format(calendars.getTime());
    }

    public static String dateAgoByMonth(int ago, String dateFormat) {
        Calendar calendars = Calendar.getInstance();
        calendars.add(Calendar.MONTH, -ago);
        return new SimpleDateFormat(dateFormat).format(calendars.getTime());
    }

    public static Date toDate(String dateStr, String dateStrFomat) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(dateStrFomat);
        return sdf.parse(dateStr);
    }

    public static Date fomateDate(String fomat) throws Exception {
        return toDate(formatDate(new Date(), fomat), fomat);
    }

    /**
     * @throws Exception
     * @Summary 获取当前日期n分钟之后的日期
     * @Desc 详细描述函数的实现过程
     */
    public static String addMinutusToDate(Date date, int amount) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(13, amount * 60);
        Date timedate = c.getTime();
        return formatDate(timedate, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date parserStringToDate(String date, String formate) throws Exception {
        SimpleDateFormat sFormat = new SimpleDateFormat(formate);
        return sFormat.parse(date);
    }

    public static String getTodayStr(String formate) {
        return new SimpleDateFormat(formate).format(new Date());
    }

    /**
     * 获取到1970年1月1日00:00:00的秒数-10位长度
     *
     * @return
     */
    public static String getTimeBySec() {
        long time = new Date().getTime();
        return new BigDecimal(time).toString().substring(0, 10);
    }

    /**
     * 当前日期是否在某一个时间段内
     *
     * @param nowDate    当前日期
     * @param beforeDate
     * @param afterDate
     * @return boolean
     */
    public static boolean dateToCompare(Date nowDate, Date beforeDate, Date afterDate) {
        if (nowDate.before(beforeDate) || nowDate.after(afterDate)) {
            return false;
        }
        return true;
    }

    /**
     * 获取当前日期的天数
     *
     * @return
     */
    public static int getDayByCalendar() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        return day;
    }
}
