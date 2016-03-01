package com.tianque.exception.base.handler;

import com.tianque.exception.domain.Result;

/**
 * 业务规则校验异常处理类
 * 
 * @author yulei
 * @date 2014-9-2下午1:55:45
 * @version 1.0.0
 * 
 */
public class BusinessValidationExceptionHandler implements ExceptionHandler {

	@Override
	public Result handleException(String errorCode, Exception bex, Result result) {
		result.setMessage(bex.getMessage());
		result.setErrorCode(errorCode);
		return result;
	}

}
