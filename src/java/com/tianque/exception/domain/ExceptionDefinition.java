package com.tianque.exception.domain;

/**
 * 异常描述类，记录异常的异常码、是否记录日志、异常处理类名
 * 
 * @author yulei
 * @date 2014-9-2下午2:18:44
 * @version 1.0.0
 * 
 */
public class ExceptionDefinition {
	private String errorCode;
	private Boolean isLogging;
	private String handlerName;
	private String expLevel;

	ExceptionDefinition() {

	}

	public ExceptionDefinition(String errorCode, String expLevel,
			Boolean isLogging) {
		this.errorCode = errorCode;
		this.isLogging = isLogging;
		this.expLevel = expLevel;
	}

	public ExceptionDefinition(String errorCode, String expLevel) {
		new ExceptionDefinition(errorCode, expLevel, true);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	public Boolean getIsLogging() {
		return isLogging;
	}

	public void setIsLogging(Boolean isLogging) {
		this.isLogging = isLogging;
	}

	public String getExpLevel() {
		return expLevel;
	}

	public void setExpLevel(String expLevel) {
		this.expLevel = expLevel;
	}
}
