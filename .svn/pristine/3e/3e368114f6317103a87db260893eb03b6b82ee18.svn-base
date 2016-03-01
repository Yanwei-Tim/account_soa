package com.tianque.plugin.account;

import java.util.HashMap;
import java.util.Map;

import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;

public final class ThreeRecordsIssueHelper {
	static Map<String, String> RELATETYPENAMES = new HashMap<String, String>();
	static {
		RELATETYPENAMES.put("v", "n");
	}

	public static String translateRelateObjectType(String key) {
		if (RELATETYPENAMES.containsKey(key)) {
			return RELATETYPENAMES.get(key);
		}
		return key;
	}

	public static ThreeRecordsIssueState constructIssueStateFromStep(ThreeRecordsIssueStep step) throws Exception {
		return (ThreeRecordsIssueState) Class.forName(step.getState()).newInstance();
	}
}
