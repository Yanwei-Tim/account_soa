package com.tianque.exception.base;

import org.springframework.stereotype.Component;

import com.tianque.exception.annotation.Exceptional;
import com.tianque.exception.constant.ExceptionCode;

/**
 * 参数不合法异常,该异常的异常码为PIE100-01，异常处理类为DefaultExceptionHandler
 * 
 * @author yulei
 * @date 2014-9-2上午10:17:22
 * @version 1.0.0
 * 
 */
@Component
@Exceptional(errorCode = ExceptionCode.PARAMETER_ILLEGAL)
public class ParameterIllegalException extends BaseAppRuntimeException {
	public ParameterIllegalException() {
		super();
	}

	public ParameterIllegalException(String title, String message,
			Throwable cause) {
		super(title, message, cause);
	}

	public ParameterIllegalException(String message) {
		super(message);
	}

	public ParameterIllegalException(String message, Throwable cause) {
		super(message, cause);
	}
}
