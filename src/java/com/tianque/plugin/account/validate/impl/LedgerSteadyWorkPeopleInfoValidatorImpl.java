package com.tianque.plugin.account.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.LedgerSteadyWorkPeopleInfo;

@Service("ledgerSteadyWorkPeopleInfoValidatorImpl")
public class LedgerSteadyWorkPeopleInfoValidatorImpl implements
		DomainValidator<LedgerSteadyWorkPeopleInfo> {

	@Autowired
	protected ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(LedgerSteadyWorkPeopleInfo domain) {
		ValidateResult result = new ValidateResult();

		if (!validateHelper.emptyString(domain.getName())
				&& validateHelper.illegalStringLength(1, 20, domain.getName())) {
			result.addErrorMessage("姓名只能输入1-20个字符!");
		}

		if (!validateHelper.emptyString(domain.getIdCardNo())
				&& validateHelper.illegalIdcard(domain.getIdCardNo())) {
			result.addErrorMessage("请输入合法的身份证号码!");
		}

		if (!validateHelper.emptyString(domain.getMobileNumber())
				&& validateHelper.illegalStringLength(0, 15,
						domain.getMobileNumber())
				&& validateHelper.illegalNumberZS(domain.getMobileNumber())) {
			result.addErrorMessage("联系电话最多输入长度15，且只能输入正整数!");
		}
		if (!validateHelper.emptyString(domain.getPermanentAddress())
				&& validateHelper.illegalStringLength(0, 50,
						domain.getPermanentAddress())) {
			result.addErrorMessage("常住地址最多输入长度50!");
		}
		return result;
	}
}
