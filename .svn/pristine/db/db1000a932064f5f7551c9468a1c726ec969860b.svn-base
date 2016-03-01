package com.tianque.plugin.account.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.LedgerConvert;

@Service("ledgerConvertValidatorImpl")
public class LedgerConvertValidatorImpl implements
		DomainValidator<LedgerConvert> {

	@Autowired
	protected ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(LedgerConvert ledgerConvert) {
		ValidateResult result = new ValidateResult();
		if (null == ledgerConvert.getIssueId()) {
			result.addErrorMessage("事件编号不能为空");
		}
		if (null == ledgerConvert.getOccurOrg()) {
			result.addErrorMessage("事件发生网格不能为空");
		}
		if (null == ledgerConvert.getCreateOrg()) {
			result.addErrorMessage("事件创建网格不能为空");
		}
		if (!StringUtil.isStringAvaliable(ledgerConvert.getDescription())){
			result.addErrorMessage("事件简述不能为空");
		}
		if (validateHelper.illegalStringLength(0, 500, ledgerConvert
						.getDescription())) {
			result.addErrorMessage("事件简述不能超过500字");
		}
		return result;
	}
}
