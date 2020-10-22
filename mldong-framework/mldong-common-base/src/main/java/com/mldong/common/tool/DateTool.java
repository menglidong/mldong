package com.mldong.common.tool;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author MENG_LI_DONG
 * @version 2017年10月4日 下午5:28:24
 */
public class DateTool {
	public final static String DEFAULT_FORMART="yyyy-MM-dd HH:mm:ss";
	private DateTool(){}
	/**
	 * 字符串转时间
	 * @param source
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String source,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(source);
		} catch (ParseException e) {
			date = new Date();
		}
		return date;
	}
	public static Date stringToDate(String source){
		return stringToDate(source,DEFAULT_FORMART);
	}
	/**
	 * 日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sdf.format(date);
	}
	public static String dateToString(Date date){
		return dateToString(date,DEFAULT_FORMART);
	}
	
	 //获取当天的开始时间  
    public static Date getDayBegin() {  
        Calendar cal = new GregorianCalendar();  
        cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);  
        return cal.getTime();  
    }  
  
    //获取当天的结束时间  
    public static Date getDayEnd() {  
        Calendar cal = new GregorianCalendar();  
        cal.set(Calendar.HOUR_OF_DAY, 23);  
        cal.set(Calendar.MINUTE, 59);  
        cal.set(Calendar.SECOND, 59);  
        cal.set(Calendar.MILLISECOND, 999);  
        return cal.getTime();  
    }  
	 //获取本年的开始时间  
    public static Date getBeginDayOfYear() {  
        Calendar cal = Calendar.getInstance();  
        cal.set(Calendar.YEAR, getNowYear());  
        cal.set(Calendar.MONTH, Calendar.JANUARY);  
        cal.set(Calendar.DATE, 1);  
        return getDayStartTime(cal.getTime());  
    }  
  
    //获取本年的结束时间  
    public static Date getEndDayOfYear() {  
        Calendar cal = Calendar.getInstance();  
        cal.set(Calendar.YEAR, getNowYear());  
        cal.set(Calendar.MONTH, Calendar.DECEMBER);  
        cal.set(Calendar.DATE, 31);  
        return getDayEndTime(cal.getTime());  
    }  
    
  //获取今年是哪一年  
    public static Integer getNowYear() {  
        Date date = new Date();  
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();  
        gc.setTime(date);  
        return Integer.valueOf(gc.get(1));  
    }  
  //获取某个日期的开始时间  
    public static Timestamp getDayStartTime(Date d) {  
        Calendar calendar = Calendar.getInstance();  
        if(null != d){  
            calendar.setTime(d);  
        }  
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);  
        calendar.set(Calendar.MILLISECOND, 0);  
        return new Timestamp(calendar.getTimeInMillis());  
    }  
  
    //获取某个日期的结束时间  
    public static Timestamp getDayEndTime(Date d) {  
        Calendar calendar = Calendar.getInstance();  
        if(null != d) {  
            calendar.setTime(d);  
        }  
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);  
        calendar.set(Calendar.MILLISECOND, 999);  
        return new Timestamp(calendar.getTimeInMillis());  
    }  
  
}
