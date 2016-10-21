package com.edu.gy.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

/**
 * 时间转换工具类
 * @author guoy1
 *
 */
public class TimeUtil {
	
	/**
	 * 时间戳转化为string format时间格式
	 * @param time    时间戳
	 * @param format  时间格式
	 * @return
	 */
	public static String convert2String(long time,String format){
		if(time<=0l){
			time = System.currentTimeMillis();
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(time));
	}
	
	/**
	 * yyyyMMdd格式
	 * @return
	 */
	public static String GetCurrentTime(){
		Date date = new Date();
		long temp = date.getTime()+1000*60*30;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String format = sdf.format(temp);
		return format;
	}
	
	/**
	 * 将yyyy-MM-dd_HH_mm_ss格式转为timestamp形式
	 * @param time
	 * @return
	 */
	public static Timestamp StringtoTimeStamp(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
		try {
			Date date = sdf.parse(time);
			Long longtime = date.getTime();
			return new Timestamp(longtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 将2016-07-04T17:39:02+08:00格式转为timestamp形式
	 * @param time
	 * @return
	 */
	public static Timestamp StringToTimeStamp2(String time){
		String date = time.substring(0,time.indexOf("T"));
		String hms = time.substring(time.indexOf("T")+1,time.indexOf("+"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
		try {
			Date parse = sdf.parse(date+"_"+hms);
			Long longtime = parse.getTime();
			return new Timestamp(longtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 将timestap转为yyyMMddd
	 * @param ts
	 * @return
	 */
	public static String TimeStampToString(Timestamp ts){
		SimpleDateFormat sdf = 	new SimpleDateFormat("yyyMMdd");
		return sdf.format(ts);
	}
	
	/**
	 * 正确格式的
	 * @param time
	 * @return
	 */
	public static Timestamp StringToStamp(String time){
		SimpleDateFormat sdf = 	new SimpleDateFormat("yyy-MM-ddd hh:mm:ss");
		try {
			return new Timestamp(sdf.parse(time).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
