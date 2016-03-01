package com.tianque.core.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.exolab.core.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.systemLog.domain.SystemLog;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Session;

@Aspect
@Repository("vaildDomainAspect")
public class VaildDomainAspect {
	final static Logger logger = LoggerFactory
			.getLogger(VaildDomainAspect.class);

	@Before("execution(public * com.tianque..*.*Service.add(..)) &&  args(baseDomain,..)")
	public void vaildDomainBussWhenAdd(BaseDomain baseDomain) throws Exception {
		if (baseDomain instanceof Session || baseDomain instanceof SystemLog) {
			return;
		}

		String className = baseDomain.getClass().getSimpleName();
		String validatorName = className + "Validator";

		DomainValidator validator = (DomainValidator) SpringBeanUtil
				.getBeanFromSpringByBeanName(validatorName);
		ValidateResult result = validator.validate(baseDomain);
		if (result.hasError()) {
			throw new ServiceException(result.getErrorMessages());
		}

	}

	@Before("execution(public * com.tianque..*.*Service.update(..)) &&  args(baseDomain,..)")
	public void vaildDomainBussWhenUpdate(BaseDomain baseDomain)
			throws Exception {
		if (baseDomain instanceof Session || baseDomain instanceof SystemLog) {
			return;
		}

		String className = baseDomain.getClass().getSimpleName();
		String validatorName = className + "Validator";

		DomainValidator validator = (DomainValidator) SpringBeanUtil
				.getBeanFromSpringByBeanName(validatorName);
		ValidateResult result = validator.validate(baseDomain);
		if (result.hasError()) {
			throw new ServiceException(result.getErrorMessages());
		}
	}

}
