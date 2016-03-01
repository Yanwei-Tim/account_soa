package com.tianque.exception.base;

import org.springframework.stereotype.Component;

import com.tianque.exception.annotation.Exceptional;
import com.tianque.exception.constant.ExceptionCode;

/**
 * 操作失败异常，用于系统未知异常
 * 
 * @author yulei
 * @date 2014-9-5下午2:13:39
 * @version 1.0.0
 * 
 */
@Component
@Exceptional(errorCode = ExceptionCode.OPERATION_FAILED)
public class OperationFailedException extends BaseAppRuntimeException {
	public OperationFailedException() {
		super();
	}

	public OperationFailedException(String message) {
		super(message);
	}

	public OperationFailedException(String message, Throwable cause) {
		super(message, cause);
	}
}
