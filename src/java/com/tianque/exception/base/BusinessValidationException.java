package com.tianque.exception.base;

import org.springframework.stereotype.Component;

import com.tianque.exception.annotation.Exceptional;
import com.tianque.exception.base.handler.BusinessValidationExceptionHandler;
import com.tianque.exception.constant.ExceptionCode;

/**
 * 业务规则校验失败异常，该类异常是由于用户误操作导致的，例如“身份证不允许重复”，只需给用户合理的提示，可以由用户自行修正.<br/>
 * 该类异常日志系统不会做任何记录<br/>
 * 该异常的异常码为BE100-01，异常处理类为BusinessValidationExceptionHandler，日志系统将不会记录该异常信息
 * 
 * @author yulei
 * @date 2014-9-2上午10:10:16
 * @version 1.0.0
 * 
 */
@Component
@Exceptional(isLogging = false, errorCode = ExceptionCode.BUSINESS_VALIDATION, handler = BusinessValidationExceptionHandler.class)
public class BusinessValidationException extends BaseAppRuntimeException {
	public BusinessValidationException() {
		super();
	}

	public BusinessValidationException(String message) {
		super(message);
	}
}
