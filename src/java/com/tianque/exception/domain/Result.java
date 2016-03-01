package com.tianque.exception.domain;

/**
 * 异常处理后，存储处理结果的bean
 * 
 * @author yulei
 * @date 2014-9-2下午2:16:26
 * @version 1.0.0
 * 
 */
public class Result {

	private String errorCode;
	private String message;
	private String expLevel;

	/**
	 * 获取异常码
	 * 
	 * @return 返回异常码
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * 设置异常码
	 * 
	 * @param errorCode
	 *            异常码
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 获取在前端页面展示的异常提示信息
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置在前端页面展示的异常提示信息
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public String getExpLevel() {
		return expLevel;
	}

	public void setExpLevel(String expLevel) {
		this.expLevel = expLevel;
	}

	@Override
	public String toString() {
		return "Result [errorCode=" + errorCode + ", message=" + message
				+ ", expLevel=" + expLevel + "]";
	}
}
