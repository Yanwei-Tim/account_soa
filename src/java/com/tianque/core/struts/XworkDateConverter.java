package com.tianque.core.struts;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class XworkDateConverter extends DefaultTypeConverter {

	private static final Logger logger = Logger.getLogger(XworkDateConverter.class);

	private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private static final String DATE_PATTERN = "yyyy-MM-dd";

	private static final String MONTH_PATTERN = "yyyy-MM";
	
	public Object convertValue(Map ognlContext, Object value, Class toType) {
		Object result = null;
		if (toType == Date.class) {
			result = doConvertToDate(value);
		} else if (toType == String.class) {
			result = doConvertToString(value);
		}
		return result;
	}

	private Date doConvertToDate(Object value) {
		if (value == null) {
			return null;
		}
		Date result = null;
		if (value instanceof String) {
			if ("".equals(value)) {
				return null;
			}
			try {
				result = DateUtils.parseDate((String) value, new String[] {
						DATE_PATTERN, DATETIME_PATTERN, MONTH_PATTERN });
				if (result == null && StringUtils.isNotEmpty((String) value)) {
					result = new Date(new Long((String) value).longValue());
				}
			} catch (Exception e) {
				logger.error("Converting from milliseconds to Date fails!");
				logger.error("异常信息", e);
			}
		} else if (value instanceof Object[]) {
			Object[] array = (Object[]) value;

			if ((array != null) && (array.length >= 1)) {
				value = array[0];
				result = doConvertToDate(value);
			}

		} else if (Date.class.isAssignableFrom(value.getClass())) {
			result = (Date) value;
		}
		return result;
	}

	private String doConvertToString(Object value) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				DATETIME_PATTERN);
		String result = null;
		if (value instanceof Date) {
			result = simpleDateFormat.format(value);
		}
		return result;
	}

	public static String DateToConverter(String idCard){
		String year="";
		String month="";
		String day = "";
		if(null!=idCard||!("").equals(idCard)){ 
			String card=idCard.trim();
			if(card.length()==18){
				year=card.substring(6,10);
				month=card.substring(10,12);
				day=card.substring(12,14);
			}else{
				year=card.substring(6,8);
				month=card.substring(8,10);
				day=card.substring(10,12);
				year="19"+year;
			}
			if(month.length()==1){
				month="0"+month;
			}
			if(day.length()==1){
				day="0"+day;
			}
		}	
		return year+"-"+month+"-"+day;
	}
}