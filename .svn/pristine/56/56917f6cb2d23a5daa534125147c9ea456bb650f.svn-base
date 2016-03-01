package com.tianque.exception.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.tianque.exception.annotation.Exceptional;
import com.tianque.exception.base.BaseAppException;
import com.tianque.exception.base.BaseAppRuntimeException;
import com.tianque.exception.context.ExceptionContext;
import com.tianque.exception.context.ExceptionContextFactory;
import com.tianque.exception.domain.ExceptionDefinition;

/**
 * 异常bean后置处理器，在spring容器启动时自动将异常码、异常、异常处理器三者关联起来
 * 
 * @author yulei
 * @date 2014-9-2下午2:15:21
 * @version 1.0.0
 * 
 */
public class ExceptionalAnnotationBeanPostProcessor implements
		BeanPostProcessor {

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof BaseAppRuntimeException
				|| bean instanceof BaseAppException) {
			Exceptional exceptional = bean.getClass().getAnnotation(
					Exceptional.class);
			if (null != exceptional) {
				ExceptionContext ctx = ExceptionContextFactory.getInstance()
						.getExceptionContext();
				if (!ctx.containsException(bean.getClass())) {
					ExceptionDefinition expDefinition = new ExceptionDefinition(
							exceptional.errorCode(), exceptional.expLevel(),
							exceptional.isLogging());
					ctx.setExceptionDefinition(bean.getClass(), expDefinition);
				}
				ctx.setExceptionHandler(bean.getClass(), exceptional.handler());
				return null;
			}
		}
		return bean;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

}
