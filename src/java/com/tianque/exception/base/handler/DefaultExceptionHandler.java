package com.tianque.exception.base.handler;

import com.tianque.exception.domain.Result;

/**
 * 默认异常处理类，如果自定义的异常类继承自BaseAppException或者BaseAppRuntimeException并且异常处理类没有配置，
 * 则使用该类 。e.g. @Exceptional(errorCode = "SE100-01") public class SystemException
 * extends BaseAppRuntimeException
 * 
 * @author yulei
 * @date 2014-9-2下午1:56:14
 * @version 1.0.0
 * 
 */
public class DefaultExceptionHandler implements ExceptionHandler {
	public Result handleException(String errorCode, Exception bex, Result result) {
		result.setMessage("异常代码:[" + errorCode + "] " + bex.getMessage());
		result.setErrorCode(errorCode);
		return result;
	}

}
