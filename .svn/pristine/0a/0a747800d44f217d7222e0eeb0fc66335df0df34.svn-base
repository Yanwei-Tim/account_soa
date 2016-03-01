package com.tianque.exception.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.exception.base.BaseAppException;
import com.tianque.exception.base.BaseAppRuntimeException;
import com.tianque.exception.base.SystemUtilException;
import com.tianque.exception.constant.ExceptionCode;
import com.tianque.exception.context.ExceptionContext;
import com.tianque.exception.context.ExceptionContextFactory;
import com.tianque.exception.domain.ExceptionDefinition;
import com.tianque.exception.domain.Result;

/**
 * 异常处理框架门面
 * 
 * @author yulei
 * @date 2014-9-2下午2:20:37
 * @version 1.0.0
 * 
 */
public final class ExceptionHandlerFacade {

	private static Logger logger = LoggerFactory
			.getLogger(ExceptionHandlerFacade.class);

	private ExceptionHandlerFacade() {

	}

	/**
	 * 处理Exception及其子类
	 * 
	 * @param ex
	 * @return 异常处理结果
	 */
	public static Result handleException(Exception ex) {
		ExceptionContext exceptionContext = ExceptionContextFactory
				.getInstance().getExceptionContext();
		ExceptionDefinition exceptionDefinition = exceptionContext
				.getExceptionDefinition(ex.getClass());

		if (exceptionDefinition == null) {
			return dealUnknownException(ex);
		}

		if (ex instanceof SystemUtilException) {
			return dealSystemUtilException(ex, exceptionDefinition);
		} else if (ex instanceof BaseAppRuntimeException
				|| ex instanceof BaseAppException) {
			return dealBaseAppRuntimeExceptionOrBaseAppException(ex,
					exceptionDefinition, exceptionContext);
		} else {
			return dealUnknownException(ex);
		}
	}

	private static Result dealSystemUtilException(Exception ex,
			ExceptionDefinition exceptionDefinition) {
		Result result = new Result();
		String errorCode = exceptionDefinition.getErrorCode();
		logger.error("intercept exception [errorCode=" + errorCode + "]:", ex);
		result.setErrorCode(errorCode);
		result.setMessage("异常代码:[" + errorCode + "] 操作失败，请联系管理员！");
		result.setExpLevel(exceptionDefinition.getExpLevel());
		return result;
	}

	private static Result dealBaseAppRuntimeExceptionOrBaseAppException(
			Exception ex, ExceptionDefinition exceptionDefinition,
			ExceptionContext exceptionContext) {
		Result result = new Result();
		String errorCode = exceptionDefinition.getErrorCode();
		if (exceptionDefinition.getIsLogging()) {
			logger.error("intercept exception [errorCode=" + errorCode
					+ getExceptionTitle(ex) + "]:", ex);
		}
		result.setExpLevel(exceptionDefinition.getExpLevel());
		exceptionContext.getExceptionHandler(ex.getClass()).handleException(
				errorCode, ex, result);
		return result;
	}

	private static String getExceptionTitle(Exception ex) {
		String title = "";
		if (ex instanceof BaseAppRuntimeException) {
			title = ((BaseAppRuntimeException) ex).getTitle();
		}
		if (ex instanceof BaseAppException) {
			title = ((BaseAppException) ex).getTitle();
		}
		return title == null ? "" : ", title=" + title;
	}

	private static Result dealUnknownException(Exception ex) {
		Result result = new Result();
		logger.error("intercept exception [errorCode="
				+ ExceptionCode.UNKNOWN_CODE + "]:", ex);
		result.setErrorCode(ExceptionCode.UNKNOWN_CODE);
		result.setMessage("异常代码:[" + ExceptionCode.UNKNOWN_CODE
				+ "] 操作失败，请重试或者联系管理员！");
		result.setExpLevel("error");
		return result;
	}

	/**
	 * 处理Throwable及其子类
	 * 
	 * @param ex
	 * @return 错误处理结果
	 */
	public static Result handleThrowable(Throwable ex) {
		logger.error("intercept error [errorCode=" + ExceptionCode.ERROR_CODE
				+ "]:", ex);
		Result result = new Result();
		result.setErrorCode(ExceptionCode.ERROR_CODE);
		result.setMessage("错误代码:[" + ExceptionCode.ERROR_CODE
				+ "] 系统出错，请联系管理员！");
		result.setExpLevel("error");
		return result;
	}
}
