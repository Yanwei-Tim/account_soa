package com.tianque.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Chinese2pinyin {
	private static Logger logger = LoggerFactory.getLogger(Chinese2pinyin.class);
	public static Map<String, String> changeChinese2Pinyin(String chinese) {
		Map<String, String> pinyin = new HashMap<String, String>();

		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);

		StringBuffer fullPinyin = new StringBuffer();
		StringBuffer simplePinyin = new StringBuffer();
		char[] chineseChar = chinese.toCharArray();
		for (int i = 0; i < chineseChar.length; i++) {
			String[] str = null;
			try {
				str = PinyinHelper.toHanyuPinyinStringArray(chineseChar[i],
						format);
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				logger.error("异常信息", e);
			}
			if (str != null) {
				fullPinyin = fullPinyin.append(str[0].toString());
				simplePinyin = simplePinyin.append(str[0].charAt(0));
			}
			if (str == null) {
				String regex = "^[0-9]*[a-zA-Z]*+$";
				Pattern pattern = Pattern.compile(regex);
				Matcher m = pattern.matcher(String.valueOf(chineseChar[i]));
				if (m.find()) {
					fullPinyin = fullPinyin.append(chineseChar[i]);
					simplePinyin = simplePinyin.append(chineseChar[i]);
				}
			}
		}
		pinyin.put("fullPinyin", fullPinyin.length()>20 ? fullPinyin.substring(0, 20) : fullPinyin.toString());
		pinyin.put("simplePinyin", simplePinyin.length()>10 ? simplePinyin.substring(0, 10) : simplePinyin.toString());

		return pinyin;
	}

	public static void main(String[] args) {
		Map<String, String> map = changeChinese2Pinyin("超级超級管理员");
		System.out.println(map.get("fullPinyin"));
		System.out.println(map.get("simplePinyin"));
	}
}
