package com.tianque.core.datatransfer.dataconvert;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.NumbericUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Component("validateHelper")
public class ValidateHelper {

	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;
	/**
	 * 
	 *功能：校验字符串 并返回(如果为空或者null)
	 */
	public boolean emptyString(String text) {
		return !StringUtil.isStringAvaliable(text);
	}

	public boolean nullObj(Object obj) {
		return null == obj;
	}
	
	public boolean nullBoolean(Boolean flag) {
		return null == flag;
	}

	public boolean containValue(String value, String[] values) {
		boolean flag = false;
		for(String vl : values) {
			if(vl.equals(value)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public String getShortClassName(Class clazz) {
		String[] names = clazz.getName().split("\\.");
		String attr = names[names.length-1];
		attr = attr.substring(0,1).toLowerCase() + attr.substring(1);
		return attr;
	}
	
	public String [] cellValueTrim(String [] cellValues){
		for (int i = 0; i < cellValues.length; i++) {
			cellValues[i]=cellValues[i].trim();
		}
		return cellValues;
	}

	/**
	 * 功能：判断一个字符串是否包含特殊字符 汉字a-z A-Z 0-9
	 *
	 */
	public boolean isConSpeCharacters(String string) {
		if (string.replaceAll("[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*）*（*\\(*\\)*", "")
				.length() == 0) {
			return true;
		}
		return false;
	}

	public boolean illegalStringLength(int min, int max, String text) {
		if (emptyString(text)) {
			if (min > 0) {
				return true;
			} else {
				return false;
			}
		}
		return text.length() < min || text.length() > max;
	}
	
	public boolean illegalPropertyDictDisplayName(String domainName,
			String dictDisplayName) {
		return propertyDictDubboService.findPropertyDictByDomainNameAndDictDisplayName(
				domainName, dictDisplayName) == null;
	}
	
	public boolean existsSamePropertyTypes(List<PropertyDict> dicts, String... propertyTypes) {
		boolean flag = false;
		for(String propertyType : propertyTypes) {
			List<PropertyDict> list = propertyDictDubboService.findPropertyDictByDomainName(propertyType);
			if(list.containsAll(dicts)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public boolean illegalOrganizationName(Organization parent, String orgName) {
		if (orgName != null)
			orgName = orgName.trim();
		List<Organization> results =null;
		int vlaue = orgName.indexOf("@");
		if(vlaue==-1){
			results = organizationDubboService
			.findOrganizationsByOrgNameAndParentId(null, orgName,
					parent.getId());
		}
		if(vlaue!=-1){
			String countryOrgName=orgName.substring(0, vlaue);
			String gridOrgName=orgName.substring(vlaue+1);
			results = organizationDubboService.findOrganizationsByOrgNameAndParentId(null, countryOrgName,parent.getId());
			if(results == null || results.size() != 1){
				return true;
			}
			if(results!=null && results.size() == 1){
				results = organizationDubboService.findOrganizationsByOrgNameAndParentId(null, gridOrgName,results.get(0).getId());
			}
		}
		return results == null || results.size() != 1;
	}


	public boolean illegalMobilePhone(String text) {
		if (text.length() < 11 || text.length() > 11) {
			return true;
		}
		return !text.matches("^1[0-9]\\d{9}$");
	}

	public boolean illegalEmail(String mail){
		    String regex = ("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		    Pattern   p   =   Pattern.compile(regex);
		    Matcher   m   =   p.matcher(mail);
		    return !m.find();
	}
	
	public boolean illegalWebSite(String mail){
	    String regex = "^(http|https)://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*" ;
	    Pattern   p   =   Pattern.compile(regex);
	    Matcher   m   =   p.matcher(mail);
	    return !m.find();
	}
	
	public boolean illegalTelephone(String text) {
		if (text.length() <= 20 && text.charAt(0) != '-'
				&& text.charAt(text.length() - 1) != '-') {
			for (int i = 0; i < text.length(); i++) {
				int charToInt = text.charAt(i);
				if ((charToInt < 48 && charToInt != 45) || charToInt > 57) {
					return true;
				}
			}
			return false;
		}
		return true;
	}

	public boolean illegalIdcard(String idcard) {
		// 验证长度
		if (idcard == null ||( idcard.length() != 15 && idcard.length() != 18))
			return true;
		// 验证地区
		String cantonCode = idcard.substring(0, 2);
		if (getAreaCode().containsKey(cantonCode) == false)
			return true;
		// 验证出生日期
		String dateStr = "";
		if (idcard.length() == 15)
			dateStr = "19" + idcard.substring(6, 8) + "-"
					+ idcard.substring(8, 10) + "-" + idcard.substring(10, 12);
		if (idcard.length() == 18)
			dateStr = idcard.substring(6, 10) + "-" + idcard.substring(10, 12)
					+ "-" + idcard.substring(12, 14);
		if (checkDate(dateStr) == false)
			return true;
		// 验证数字
		if (idcard.length() == 15 &&checkNumeric(idcard) == false)return true;
		//18位身份证的年份验证
		if(idcard.length() == 18 && Integer.parseInt(idcard.substring(6, 8))<19) return true;
		//18位身份证需要 验证最后一位数字码
//		if(idcard.length()==18 && getLastNum(idcard).equalsIgnoreCase(idcard.substring(idcard.length()-1,idcard.length()))==false)
//			return true;
		else
			return false;
	}

	/**
	 * 获得身份证最后一位识别码
	 * @param IDStr
	 * @return
	 */
	public String getLastNum(String IDStr) {
		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4","3", "2" };
	    String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7","9", "10", "5", "8", "4", "2" };
	    String Ai = "";

        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
		int TotalmulAiWi = 0;
	    for (int i = 0; i < 17; i++) {
	        TotalmulAiWi = TotalmulAiWi
	                + Integer.parseInt(String.valueOf(Ai.charAt(i)))
	                * Integer.parseInt(Wi[i]);
	    }
	    return ValCodeArr[TotalmulAiWi % 11];
    }
	/**
	 * 身份证地区编码
	 *
	 * @return
	 */
	private Hashtable<String, String> getAreaCode() {
		Hashtable<String, String> hashtable = new Hashtable<String, String>();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙古");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙江");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
	}

	private boolean checkNumeric(String idcard) {
		char a1 = idcard.charAt(idcard.length() - 1);
		if (a1 == 'X') {
			return !illegalNum(idcard.substring(0, 17));
		} else {
			return !illegalNum(idcard);
		}
	}

	private boolean checkDate(String dateStr) {

		int year = Integer.parseInt(dateStr.substring(0, 4));
		if (year % 4 == 0 && !(year % 100 == 0) || year % 400 == 0) {
			String month = dateStr.substring(5, 7);
			int day = Integer.parseInt(dateStr.substring(8, 10));
			if ("02".equals(month)) {
				return day <= 29;
			} else if ("01".equals(month) || "03".equals(month)
					|| "05".equals(month) || "07".equals(month)
					|| "08".equals(month) || "10".equals(month)
					|| "12".equals(month)) {
				return day <= 31;
			} else if ("04".equals(month) || "06".equals(month)
					|| "09".equals(month) || "11".equals(month)) {
				return day <= 30;
			}

			return false;
		} else {
			String ereg = getPatternStr(dateStr);

			Pattern pattern = Pattern.compile(ereg);
			Matcher matcher = pattern.matcher(dateStr);

			return matcher.matches();
		}
	}

	public boolean illegalNum(String text) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher numbericMatcher = pattern.matcher(text);
		return !numbericMatcher.matches();
	}

	/**
	 *
	 * @param dateStr
	 *            yyyy-MM-dd
	 * @return
	 */
	private String getPatternStr(String dateStr) {
		String ereg = "";
		int year = Integer.parseInt(dateStr.substring(0, 4));
		if (!(year % 4 == 0 && !(year % 100 == 0) || year % 400 == 0)) {
			ereg = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";
		}
		return ereg;
	}

    public boolean notLettersOrNum (String text){
    	if(!text.matches("^[A-Za-z0-9]+$")){
	    	return true;
	    }
	    return false;
	}
	 
	public boolean endDateIsSameorBigForStartDate(Date endDate, Date startDate) {
		if (endDate.equals(startDate) || endDate.after(startDate)) {
			return true;
		}
		return false;
	}

	public boolean illegalNumeric(String str) {

		if (str.charAt(0) == '.' || str.charAt(str.length() - 1) == '.'
				|| (str.charAt(0) == '-' || str.charAt(0) == '+')
				&& str.charAt(1) == '.') {
			return true;
		} else {
			try {
				Double.parseDouble(str);
				return false;
			} catch (NumberFormatException ex) {
				return true;
			}
		}
	}
	
	public boolean illegalNumericRange(String str,double min,double max){
		try {
			double value=Double.parseDouble(str);
			return NumbericUtil.compare(value, min) <0 || NumbericUtil.compare(value, max)>0; 
		} catch (NumberFormatException ex) {
			return true;
		}
		
	}
	
	public boolean illegalDate(String strDate) {
		if (!StringUtil.isStringAvaliable(strDate)) {
			return false;
		}
		Date date = null;
		date = CalendarUtil.parseDate(strDate, "yyyy/MM/dd");
		if(null != date && getPrevFourString(strDate,"/")<1900){
			return true;
		}
		if (date == null) {
			date = CalendarUtil.parseDate(strDate, "yyyy-MM-dd");
			if(null != date && getPrevFourString(strDate,"-")<1900){
				return true;
			}
		}
		return date == null;
	}
	//对年份的验证
	public boolean illegaYear(String strYear){
		if(!StringUtil.isStringAvaliable(strYear)){
			return false;
		}
		Date year=null;
	    year=CalendarUtil.parseDate(strYear, "yyyy");
	    if(null!=year && Integer.parseInt(strYear)<1000){
	    	return true;
	    }
	    return false;
	}
	
	
	

	public boolean illegalBoolean(String text) {
		if("是".equals(text)||"否".equals(text)){
			return false;
		}else{
			return true;
		}
	}
	
	public int getPrevFourString(String strDate,String style){
		String str = strDate.substring(0,strDate.indexOf(style, 1));
		int result = Integer.parseInt(str);
		return result;
	}
	public boolean illegalUserName(String text){
		Pattern p = Pattern.compile("^[A-Za-z0-9_]+$");
		Matcher m = p.matcher(text);
		return !m.find();
	 }

	/**
	 * 验证是否输入特殊字符(数字,字母,中文字符)
	 */
	public boolean illegalExculdeParticalChar(String text){
		return !text.matches("^[A-Za-z0-9\u4E00-\u9FA5]+$");
	}
	/**验证是否输入特殊字符(数字,字母,中文字符,空格,点)
	 * 
	 */
	public boolean illegalExculdeParticalChar2(String text){
		return !text.matches("^[A-Za-z0-9\u4E00-\u9FA5\\s\\.]+$");
	}
	
	/**
	 * 正整数
	 * @param text
	 * @return
	 */
	public boolean illegalNumberZZ(String text) {
		Pattern pattern=Pattern.compile("[^(0|\\-|\\s)*][0-9]*$");
		Matcher numbericMatcher = pattern.matcher(text);
		return !numbericMatcher.matches();

	}
	/**
	 * 只能输入正数
	 * @param text
	 * @return
	 */
	public boolean illegalNumberZS(String text) {
		if(text.startsWith("0"))
			return false;
		Pattern pattern=Pattern.compile("[0-9]+(.[0-9]+)?");
		Matcher numbericMatcher = pattern.matcher(text);
		return !numbericMatcher.matches();

	}
	/**
	 * 校验带有1-2位小数的正实数
	 * @param text
	 * @return
	 */
	public boolean illegalPointNumber(String text){
		if(text.startsWith("0"))
			return false;
		Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{1,2})?$");
		Matcher numbericMatcher = pattern.matcher(text);
		return !numbericMatcher.matches();
	}
	public boolean illegalInteger(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher numbericMatcher = pattern.matcher(str);
		if (!numbericMatcher.matches()) {
			return true;
		} else {
			try {
				Integer.parseInt(str);
				return false;
			} catch (NumberFormatException ex) {
				return true;
			}
		}
	}
	/**
	 * 校验带有4位小数的正实数
	 * @param text
	 * @return
	 */
	public boolean illegalPointNumber4(String text){
		if(text.startsWith("0"))
			return false;
		Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{1,4})?$");
		Matcher numbericMatcher = pattern.matcher(text);
		return !numbericMatcher.matches();
	}
	public boolean dateIsnotNull(Date date) {
		if (null == date) {
			return false;
		}
		return true;
	}
}
