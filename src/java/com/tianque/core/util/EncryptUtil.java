/**
 * tianque-com.tianque.common.util-MD5.java Created on Apr 2, 2009
 * Copyright (c) 2010 by 杭州天阙科技有限公司
 */
package com.tianque.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Title: ***<br>
 * 
 * @author <a href=mailto:nifeng@hztianque.com>倪峰</a><br>
 * 
 * @description ***<br/>
 * 
 * @version 1.0
 */
public class EncryptUtil {
	public static String md5Encrypt(String str) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes());
			byte b[] = md5.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}
}
