package com.tianque.plugin.account.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.AssignFormReceiver;

@Service("assignFormReceiverValidatorImpl")
public class AssignFormReceiverValidatorImpl implements DomainValidator<AssignFormReceiver> {

	@Autowired
	protected ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(AssignFormReceiver assignFormReceiver) {
		ValidateResult result = new ValidateResult();

//		if (null == reportForm.getId()) {
//			result.addErrorMessage("接件单 ID不能为空");
//		}
//		if (null == reportForm.getStepId()) {
//			result.addErrorMessage("步骤编号不能为空");
//		}
//		if (null == reportForm.getLedgerId()) {
//			result.addErrorMessage("台账编号不能为空");
//		}
//		if (null == reportForm.getLedgerType()) {
//			result.addErrorMessage("台账类型不能为空");
//		}
//		if (null == reportForm.getSourceOrg()) {
//			result.addErrorMessage("当前上报的部门ID不能为空");
//		}
//		if (null == reportForm.getTargetOrg()) {
//			result.addErrorMessage("上级部门ID不能为空");
//		}
//		if (null == reportForm.getReason()) {
//			result.addErrorMessage("呈报主要事项及原因不能为空");
//		}
//		if (null == reportForm.getHandleContent()) {
//			result.addErrorMessage("呈报主要事项及原因不能为空");
//		}
//		if (null == reportForm.getSerialNumber()) {
//			result.addErrorMessage("接件单编码不能为空");
//		}
//		if (null == reportForm.getName()) {
//			result.addErrorMessage("接件单位联系人不能为空");
//		}
//		if (null == reportForm.getMobile()) {
//			result.addErrorMessage("联系电话不能为空");
//		}
		return result;
	}
}
