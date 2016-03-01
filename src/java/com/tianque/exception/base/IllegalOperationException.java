package com.tianque.exception.base;

import org.springframework.stereotype.Component;

import com.tianque.exception.annotation.Exceptional;
import com.tianque.exception.constant.ExceptionCode;

/**
 * 非法操作异常，例如权限不足等，该异常的异常码为IOE100-01，异常处理类为DefaultExceptionHandler
 * 
 * @author yulei
 * @date 2014-9-2上午10:15:53
 * @version 1.0.0
 * 
 */
@Component
@Exceptional(errorCode = ExceptionCode.ILLEGAL_OPERATION)
public class IllegalOperationException extends BaseAppRuntimeException {
	public IllegalOperationException() {
		super();
	}

	public IllegalOperationException(String title, String message,
			Throwable cause) {
		super(title, message, cause);
	}

	public IllegalOperationException(String message) {
		super(message);
	}

	public IllegalOperationException(String message, Throwable cause) {
		super(message, cause);
	}
}
