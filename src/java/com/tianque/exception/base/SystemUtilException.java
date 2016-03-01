package com.tianque.exception.base;

import org.springframework.stereotype.Component;

import com.tianque.exception.annotation.Exceptional;
import com.tianque.exception.constant.ExceptionCode;

/**
 * 系统工具类异常
 * 
 * @author yulei
 * @date 2014-9-2上午10:19:58
 * @version 1.0.0
 * 
 */
@Component
@Exceptional(errorCode = ExceptionCode.SYSTEM_UTIL)
public class SystemUtilException extends BaseAppRuntimeException {
	public SystemUtilException() {
		super();
	}

	public SystemUtilException(String message) {
		super(message);
	}

	public SystemUtilException(String message, Throwable cause) {
		super(message, cause);
	}
}