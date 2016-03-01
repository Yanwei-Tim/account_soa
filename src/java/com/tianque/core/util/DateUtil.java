package com.tianque.core.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期格式处理类
 */
public final class DateUtil {
	private DateUtil() {
	};

	/**
	 * 返回:20100910210637578
	 */
	public static String formate(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL", date);
	}

	/**
	 * 返回:2010-09-10 21:08:17
	 */
	public static String formateYMDHMS(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", date);
	}

	/**
	 * 返回:2010-09-10
	 */
	public static String formateYMD(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%1$tY-%1$tm-%1$td", date);
	}

	/**
	 * 返回:2010年09月10日
	 */
	public static String formateYMD_CN(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%1$tY年%1$tm月%1$td日", date);
	}

	/**
	 * 返回:09-10
	 */
	public static String formateMD(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%1$tm-%1$td", date);
	}

	/**
	 * 返回:09月10日
	 */
	public static String formateMD_CN(Date date) {
		if (date == null) {
			return "";
		}
		return String.format("%1$tm月%1$td日", date);
	}

	/**
	 * 按照指定的格式进行转化
	 * 
	 * @param d
	 * @param sFormat
	 * @return
	 */
	public static String toString(java.util.Date d, String sFormat) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				sFormat);
		String dateString = formatter.format(d);
		return dateString;
	}

	public static java.util.Date parseDate(String sDate, String sFormat) {
		if(null==sDate||"".equals(sDate)){
			return null;
		}
		java.text.SimpleDateFormat formatter = null;

		java.util.Date utildate = null;
		try {
			formatter = new java.text.SimpleDateFormat(sFormat);
			utildate = formatter.parse(sDate);
		} catch (ParseException e) {
			utildate = new Date();
		}

		return utildate;
	}
	
	/**
	 * 获取23:59:59时间点
	 * 
	 * @param Date 
	 * @return Date 年月日不变，时分秒改为当天的23:59:59
	 */
	public static Date getEndTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	public static void main(String[] args) {
		System.out.println(formate(new Date()));
		System.out.println(formateYMDHMS(new Date()));
		System.out.println(formateYMD(new Date()));
		System.out.println(formateYMD_CN(new Date()));
		System.out.println(formateMD(new Date()));
		System.out.println(formateMD_CN(new Date()));
		System.out.println(getEndTime(new Date()));
	}
}