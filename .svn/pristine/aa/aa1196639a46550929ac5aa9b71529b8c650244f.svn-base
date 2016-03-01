package com.tianque.service.bridge;

import java.util.HashMap;
import java.util.Map;

public class AutocompleteConvertDefine {
	static Map<String, String> defines = new HashMap<String, String>();
	static {
		defines.put("all", "userAutocompleteAllService");
		defines.put("subordinate", "userAutocompleteSubordinateService");
		defines.put("superior", "userAutocompleteSuperiorService");
	}
	public static String getConvertBeanDefine(String data_type) {
		return defines.get(data_type);
	}
}
