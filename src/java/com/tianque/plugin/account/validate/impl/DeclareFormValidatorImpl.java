package com.tianque.plugin.account.validate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.DeclareForm;

@Service("declareFormValidatorImpl")
public class DeclareFormValidatorImpl implements DomainValidator<DeclareForm> {

	@Autowired
	protected ValidateHelper validateHelper;

	@Override
	public ValidateResult validate(DeclareForm declareForm) {
		ValidateResult result = new ValidateResult();

		// if (null == declareForm.getId()) {
		// result.addErrorMessage("申报单 ID不能为空");
		// }
		// if (null == declareForm.getStepId()) {
		// result.addErrorMessage("步骤编号不能为空");
		// }
		// if (null == declareForm.getLedgerId()) {
		// result.addErrorMessage("台账编号不能为空");
		// }
		// if (null == declareForm.getLedgerType()) {
		// result.addErrorMessage("台账类型不能为空");
		// }
		// if (null == declareForm.getSourceOrg()) {
		// result.addErrorMessage("当前上报的部门ID不能为空");
		// }
		// if (null == declareForm.getTargetOrg()) {
		// result.addErrorMessage("上级部门ID不能为空");
		// }
		// if (null == declareForm.getReason()) {
		// result.addErrorMessage("申报主要事项及原因不能为空");
		// }
		// if (null == declareForm.getHandleContent()) {
		// result.addErrorMessage("申报主要事项及原因不能为空");
		// }
		// if (null == declareForm.getSerialNumber()) {
		// result.addErrorMessage("申报单编码不能为空");
		// }
		// if (null == declareForm.getName()) {
		// result.addErrorMessage("申报单位联系人不能为空");
		// }
		// if (null == declareForm.getMobile()) {
		// result.addErrorMessage("联系电话不能为空");
		// }
		return result;
	}
}
