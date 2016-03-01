package com.tianque.core.validate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tianque.core.util.StringUtil;

public class DomainValidateResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String EMPTY_MESSAGE = "";
	private static final String DEFAULT_MSG_SEPERATOR = "\n";

	protected List<String> messages = new ArrayList<String>();
	
	public void addAllErrorMessage(DomainValidateResult validateResult){
		messages.addAll(validateResult.messages);
	}

	public void addErrorMessage(String msg) {
		if (StringUtil.isStringAvaliable(msg))
			this.messages.add(msg);
	}
	
	public void addErrorMessage(String filed,String msg) {
		this.messages.add(filed + msg);
	}

	public boolean hasError() {
		return messages.size() > 0;
	}

	public String getErrorMessages() {
		if (!hasError())
			return EMPTY_MESSAGE;
		StringBuilder sb = new StringBuilder();
		for (String subErrorMsg : messages) {
			sb.append(subErrorMsg);
			sb.append(DEFAULT_MSG_SEPERATOR);
		}
		return sb.toString();
	}

}
