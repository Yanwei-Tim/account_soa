package com.tianque.plugin.account.validate.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;
import com.tianque.plugin.account.validate.ThreeRecordsAbstractValidator;
import com.tianque.plugin.account.validate.ThreeRecordsIssueOperationLogValidator;

@Component("threeRecordsDefaultIssueLogValidator")
public class ThreeRecordsDefaultIssueLogValidatorImpl extends
		ThreeRecordsAbstractValidator implements
		ThreeRecordsIssueOperationLogValidator {

	@Override
	public ValidateResult validate(ThreeRecordsIssueOperate operate,
			ThreeRecordsIssueLogNew log, List<ThreeRecordsIssueAttachFile> files) {
		ValidateResult result = validateLog(log, files);
		if (operate == null) {
			result.addErrorMessage("请选择处理类型!");
		}
		if (mustInputDealContent(operate)
				&& !StringUtil.isStringAvaliable(log.getContent())) {
			result.addErrorMessage("请输入处理意见!");
		}
		return result;
	}

	@Override
	public ValidateResult validate(ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		ValidateResult result = validateLog(log, attachFiles);
		return result;
	}

	private boolean mustInputDealContent(ThreeRecordsIssueOperate operate) {
		if (!ThreeRecordsIssueOperate.REPORT_TO.equals(operate)
				&& !ThreeRecordsIssueOperate.CONCEPT.equals(operate)
				&& !ThreeRecordsIssueOperate.READ.equals(operate)
				&& !ThreeRecordsIssueOperate.CANCEL_SUPERVISE.equals(operate)
				&& !ThreeRecordsIssueOperate.HISTORIC.equals(operate)
				&& !ThreeRecordsIssueOperate.CANCEL_HISTORIC.equals(operate)
				&& !ThreeRecordsIssueOperate.URGENT.equals(operate)
				&& !ThreeRecordsIssueOperate.CANCEL_URGENT.equals(operate)
				&& !ThreeRecordsIssueOperate.SUPPORT.equals(operate)) {
			return true;
		}
		return false;

	}

	private ValidateResult validateLog(ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles) {
		ValidateResult result = new ValidateResult();
		/*if (log.getDealOrg() == null || log.getDealOrg().getId() == null) {
			result.addErrorMessage("请输入处理部门!");
		}
		if (!StringUtil.isStringAvaliable(log.getDealUserName())) {
			result.addErrorMessage("请输入承办人!");
		}
		if (!StringUtil.isStringAvaliable(log.getMobile())) {
			result.addErrorMessage("请输入承办人联系手机!");
		}
		if (attachFiles != null && attachFiles.size() > 10) {
			result.addErrorMessage("事件处理附件最多只能上传10个!");
		}*/
		return result;
	}
}
