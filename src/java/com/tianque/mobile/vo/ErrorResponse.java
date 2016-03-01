package com.tianque.mobile.vo;

import java.io.Serializable;

import org.apache.struts2.ServletActionContext;


public class ErrorResponse implements Serializable {
	
	public ErrorResponse(String mMessage,String mErrorCode){
		this.errorCode=mErrorCode;
		this.message=mMessage;
		addErrorHeader();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The external content in HTTP head, and the static variable is not serializable
	 */
	public static final String KEY_HTTP_HEAD = "ErrorForMobile";
	public static final String VALUE_ERROR_CAUGHT = "1";
			
	/**
	 * The session error codes means the client have to Re-verified itself.
	 */
	public final static String[] SESSION_ERROR = {
		SessionError.ACCOUNT_ERROR,
		SessionError.LOGIN_ELSEWHERE
	};

	
	/**
	 * The error message for mobile to show
	 */
	private String message = null;
	private String errorCode = null;
	public String getMessage() {
		return message;
	}
	public void setMessage(String mMessage) {
		this.message = mMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String mErrorCode) {
		this.errorCode = mErrorCode;
	}
	
	public void addErrorHeader(){
		ServletActionContext.getResponse().setHeader(KEY_HTTP_HEAD, VALUE_ERROR_CAUGHT);
	}
	/**
	 * 505: normal error
	 */	
	public static final String 	NORMAL_ERROR="505";
	
	public static final class SessionError {

		 /**
		  * 301: user name or password is incorrect
		  */
		public static final String ACCOUNT_ERROR = "301";
		
		/**
		 * 302:The account login elsewhere
		 */
		public static final String LOGIN_ELSEWHERE = "302"; 
	}

}
