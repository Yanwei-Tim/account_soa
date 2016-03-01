package com.tianque.plugin.account.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.LedgerFeedBack;

@Service("ledgerFeedBackValidatorImpl")
public class LedgerFeedBackValidatorImpl implements
		DomainValidator<LedgerFeedBack> {

	@Autowired
	protected ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(LedgerFeedBack ledgerFeedBack) {
		ValidateResult result = new ValidateResult();
		if (null == ledgerFeedBack.getLedgerId()) {
			result.addErrorMessage("台账编号不能为空");
		}
		if (null == ledgerFeedBack.getFeedBackType()) {
			result.addErrorMessage("必须选择反馈方式");
		}
		if (null == ledgerFeedBack.getFeedBackOpinion()) {
			result.addErrorMessage("必须选择反馈意见");
		}
		if (StringUtil.isStringAvaliable(ledgerFeedBack.getRemark())
				&& validateHelper.illegalStringLength(0, 100, ledgerFeedBack
						.getRemark())) {
			result.addErrorMessage("备注不能大于100个字");
		}
		return result;
	}

}
