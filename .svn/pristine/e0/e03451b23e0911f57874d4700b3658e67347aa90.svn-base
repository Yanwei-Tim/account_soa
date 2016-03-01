package com.tianque.exception.base;

import org.springframework.stereotype.Component;

import com.tianque.exception.annotation.Exceptional;
import com.tianque.exception.base.handler.BusinessValidationExceptionHandler;
import com.tianque.exception.constant.ExceptionCode;

/**
 * 业务规则校验失败异常，该类异常是由于用户误操作导致的，例如“身份证不允许重复”，只需给用户合理的提示，可以由用户自行修正.<br/>
 * 该类异常日志系统会记录在案<br/>
 * 该异常的异常码为SE100-01，异常处理类为BusinessValidationExceptionHandler
 * 
 * @author yulei
 * @date 2014-9-2上午10:10:16
 * @version 1.0.0
 * 
 */
@Component
@Exceptional(isLogging = true, errorCode = ExceptionCode.SERVICE_VALIDATION, handler = BusinessValidationExceptionHandler.class)
public class ServiceValidationException extends BaseAppRuntimeException {
	public ServiceValidationException() {
		super();
	}

	public ServiceValidationException(String title, String message,
			Throwable cause) {
		super(title, message, cause);
	}

	public ServiceValidationException(String message, Throwable cause) {
		super(message, cause);
	}
}
