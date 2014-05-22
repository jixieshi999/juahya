package com.android.apis.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

/** 
 * 时间tools
 * author Oscar
 * Created on ：2011-1-19 
 */

public class DateUtil {
	/** yyyy-MM-dd HH:mm:ss */
	public static final String FORMAT_DEFAULT="yyyy-MM-dd HH:mm:ss";
	/** yyyy-MM-dd */
	public static final String FORMAT_DATE="yyyy-MM-dd";
	/** HH:mm:ss */
	public static final String FORMAT_TIME="HH:mm:ss";
	/**
	 * 返回当前时间 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDefaultDateTime(){
		return getFormatTime(FORMAT_DEFAULT);
	}	
	public static String getFormatTime(){
		return getFormatTime(FORMAT_DEFAULT);
	}
	/**
	 * 取相应格式的时间
	 * @param format
	 * @return
	 */
	public static String getFormatTime(String format){
		TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
		// Calendar calendar = Calendar.getInstance(Locale.CHINA);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(timeZone);
		String time = sdf.format(new Date());
		return time;
	}
	/**
	 * 自定义相应格式，相应日期时间
	 * @param dayNums	正数为当天以后，负数为当天以前
	 * @param format
	 * @return
	 */
	public static String getFormatTime(long dayNums, String format) {
		long currentTime = new Date().getTime();
		long aimTime = currentTime + (dayNums * 24 * 3600 * 1000);
		Date date = new Date(aimTime);
		
		TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(timeZone);
		String time = sdf.format(date);
		return time;
	}
	/**减去上一个月日期*/
	public static String getFormatTime(int mouth, String format) {
		Date date = new Date();
		date.setMonth(date.getMonth()+mouth);
		TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(timeZone);
		String time = sdf.format(date);
		return time;
	}
	/**
	 * 日历取当前年，月，日
	 * @param calendarType  (Calendar.YEAR, Calendar.MONTH)
	 * @return
	 */
	public static int getDateTime(int calendarType){
		TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
		Calendar calendar = Calendar.getInstance(timeZone,
				Locale.CHINESE);
		int date = calendar.get(calendarType);
		return date;
	}
	/**
	 * 返回两个时间相差微秒数
	 * @param dateStr1
	 * @param dateStr2
	 * @return
	 */
	public static long getDefferences(String dateStr1, String dateStr2){
		long interval = 0;	// 相差微秒数
		String format = "yyyy-MM-dd hh:mm:ss";
		TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(timeZone);
		try{
			long date1 = sdf.parse(dateStr1).getTime();
			long date2 = sdf.parse(dateStr2).getTime();
			interval = Math.abs(date1 - date2);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return interval;
	}
	/**
	 * 验证第一个时间是否比第二个时间早
	 * @param dateStr1
	 * @param dateStr2
	 * @return
	 */
	public static boolean compareDefferences(String dateStr1, String dateStr2, String format) {
		boolean str1BeforeStr2 = false;
		// String format = "yyyy-MM-dd hh:mm:ss";
		TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(timeZone);
		try{
			Calendar ca1 = Calendar.getInstance();
			ca1.setTime(sdf.parse(dateStr1));
			Calendar ca2 = Calendar.getInstance();
			ca2.setTime(sdf.parse(dateStr2));
			// if 0 means two are equal, if -1 means cal1 is before cal2
			// if 1 means cal1 is after cal2
			int result = ca1.compareTo(ca2);
			if(result == -1){
				str1BeforeStr2 = true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return str1BeforeStr2;
	}
	
	/**
	 * 根据当天日期，判断是否与所提供的日期相配
	 * @param weekDay
	 * @param monthDay
	 * @return
	 */
	public static boolean compareWeekDayAndMonthDay(int weekDay, int monthDay){
		boolean match = false;
		Calendar calendar = Calendar.getInstance();
		
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if(dayOfWeek == 0){
			dayOfWeek = 7;
		}
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		if(weekDay == dayOfWeek || dayOfMonth == monthDay){
			match = true;
		}else if(weekDay == 0 && monthDay == 0){
			match = true;
		}
//		Log.v("compareWeekDayAndMonthDay", "dayOfWeek = " + dayOfWeek + " dayOfMonth = " + dayOfMonth); 
		return match;
	}
	
	/**
	 * 返回时间微秒数
	 * @param dateStr1
	 * @param dateStr2
	 * @return
	 */
	public static long getlongdate(String dateStr){
		long interval = 0;	
		String format = "yyyy-MM-dd HH:mm:ss";
		TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(timeZone);
		try{
			 interval = sdf.parse(dateStr).getTime();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return interval;
	}
	
	/**获取年月日*/
	public static String getDateYYYYMMDD(String dateStr){
		String returnval=null;
		try{
			returnval=getFormatDate(dateStr, FORMAT_DATE);
			if(dateStr.equals(returnval)){
				returnval=dateStr.split(" ")[0];
			}
		}catch (Exception e) {
			returnval=dateStr;
		}
		return returnval;
	}
	public static String getFormatDate(String dateStr,String format){
		TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
		String baseformat = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(baseformat);
		sdf.setTimeZone(timeZone);
		String times=null;
		try{
			Date d = sdf.parse(dateStr);
			sdf = new SimpleDateFormat(format);
			sdf.setTimeZone(timeZone);
			times= sdf.format(d);
		}catch(Exception ex){
			Debug.dLog("date",ex);
			times=dateStr;
		}
		return times;
	}
	/**
	 * 取到当日星期几
	 * 
	 * @return
	 */
	public static int getWeeklyDay(){
		Calendar calandar = Calendar.getInstance();
		int day = calandar.get(Calendar.DAY_OF_WEEK) - 1;
		if(day == 0){
			day = 7;
		}
		return day;
	}
	private static HashMap<String, Integer> dayHashMap = new HashMap<String, Integer>();
	/**
	 * 返回星期对应天数
	 * 
	 * @param context
	 * @return
	 */
//	public static HashMap<String, Integer> getDayHashMap(Context context){
//		if(dayHashMap.size() == 0){
//			dayHashMap.put(context.getString(R.string.Non_Schedular_Monday), 1);
//			dayHashMap.put(context.getString(R.string.Non_Schedular_Tuesday), 2);
//			dayHashMap.put(context.getString(R.string.Non_Schedular_Wednesday), 3);
//			dayHashMap.put(context.getString(R.string.Non_Schedular_Thursday), 4);
//			dayHashMap.put(context.getString(R.string.Non_Schedular_Friday), 5);
//			dayHashMap.put(context.getString(R.string.Non_Schedular_Saturday), 6);
//			dayHashMap.put(context.getString(R.string.Non_Schedular_Sunday), 7);
//			dayHashMap.put(context.getString(R.string.visit_line_others), 8);
//		}
//		return dayHashMap;
//	}

	/**
	 * Format = "yyyyMMddhhmmss";
	 * @return
	 */
	public static Long getLong(){
		String format = "yyyyMMddHHmmss";
		TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
		// Calendar calendar = Calendar.getInstance(Locale.CHINA);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
	
		sdf.setTimeZone(timeZone);
		String time = sdf.format(new Date());
		return new Date().getTime();
	}
}
