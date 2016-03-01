package com.tianque.plugin.account.validate;

import java.util.List;

import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;

public interface ThreeRecordsIssueOperationLogValidator {
	ValidateResult validate(ThreeRecordsIssueOperate operate,
			ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles);

	ValidateResult validate(ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles);
}
