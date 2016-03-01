package com.tianque.plugin.account.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.LedgerPoorPeopleMember;

@Service("ledgerPoorPeopleMemberValidatorImpl")
public class LedgerPoorPeopleMemberValidatorImpl implements
		DomainValidator<LedgerPoorPeopleMember> {

	@Autowired
	protected ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(LedgerPoorPeopleMember domain) {
		ValidateResult result = new ValidateResult();
		if (domain == null)
			result.addErrorMessage("参数错误！");
		if (!validateHelper.emptyString(domain.getName())
				&& validateHelper.illegalStringLength(0, 20, domain.getName())) {
			result.addErrorMessage("姓名只能输入0-20个字符!");
		}
		if (!validateHelper.emptyString(domain.getIdCardNo())
				&& validateHelper.illegalIdcard(domain.getIdCardNo())) {
			result.addErrorMessage("请输入合法的身份证号码!");
		}
		if (!validateHelper.emptyString(domain.getHeadHouseholdRelation())
				&& validateHelper.illegalStringLength(0, 10, domain
						.getHeadHouseholdRelation())) {
			result.addErrorMessage("与房主关系只能输入0-10个字符!");
		}
		if (!validateHelper.emptyString(domain.getHealthCondition())
				&& validateHelper.illegalStringLength(0, 10, domain
						.getHealthCondition())) {
			result.addErrorMessage("健康状况只能输入0-10个字符!");
		}

		return result;
	}
}
