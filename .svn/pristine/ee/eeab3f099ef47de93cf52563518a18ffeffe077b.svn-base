package com.tianque.plugin.account.util;

public class DealYearOrMonthUtil {
	/**
	 * 取年份后两位
	 * @param year
	 * @return
	 */
	public static String dealYear(Integer year) {
		if (year == null) {
			return null;
		}
		String yearStr = year.toString();
		if (yearStr.length() <= 2) {
			return year.toString();
		}
		return yearStr.substring(2);
	}

	/**
	 * 处理月份,如果月份为1-9,则前面+0
	 * @param year
	 * @return
	 */
	public static String dealMonth(Integer month) {
		if (month == null) {
			return null;
		}
		String monthStr = month.toString();
		if (monthStr.length() == 1) {
			return "0" + month.toString();
		}
		return monthStr;
	}
	
	public static String dealYearMonth(Integer year, Integer month){
		if(year == null && month == null){
			return null;
		}
		String yearStr = null;
		if(year != null){
			if (year.toString().length() <= 2) {
				yearStr = year.toString();
			}else{
				yearStr = year.toString().substring(2);
			}
		}
		String monthStr = null;
		if(month != null){
			if(month.toString().length() == 1){
				monthStr = "0" + month.toString();
			}else{
				monthStr = month.toString();
			}
		}
		if(yearStr != null && monthStr != null){
			return yearStr + monthStr;
		}
		if(yearStr != null && monthStr == null){
			return yearStr;
		}
		if(yearStr == null && monthStr != null){
			return "__" + monthStr;
		}
		return null;
	}
}
