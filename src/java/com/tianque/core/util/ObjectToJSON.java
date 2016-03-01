package com.tianque.core.util;



import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import com.tianque.core.base.BaseDomain;

public class ObjectToJSON {
	public static String convertJSON(BaseDomain domain) throws JSONException{
		return JSONUtil.serialize(domain);
	}


}
