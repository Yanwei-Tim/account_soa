package com.tianque.plugin.account.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.AcceptForm;

@Service("acceptFormValidatorImpl")
public class AcceptFormValidatorImpl implements DomainValidator<AcceptForm> {

	@Autowired
	protected ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(AcceptForm acceptForm) {
		ValidateResult result = new ValidateResult();

//		if (null == acceptForm.getId()) {
//			result.addErrorMessage("呈报单 ID不能为空");
//		}
//		if (null == acceptForm.getStepId()) {
//			result.addErrorMessage("步骤编号不能为空");
//		}
//		if (null == acceptForm.getLedgerId()) {
//			result.addErrorMessage("台账编号不能为空");
//		}
//		if (null == acceptForm.getLedgerType()) {
//			result.addErrorMessage("台账类型不能为空");
//		}
//		if (null == acceptForm.getAcceptOrg()) {
//			result.addErrorMessage("受理部门ID不能为空");
//		}
//		if (null == acceptForm.getCurrentOrgOpinion()) {
//			result.addErrorMessage("本级台账办拟办意见不能为空");
//		}
//		if (null == acceptForm.getSerialNumber()) {
//			result.addErrorMessage("呈报单编码不能为空");
//		}
		return result;
	}
}
