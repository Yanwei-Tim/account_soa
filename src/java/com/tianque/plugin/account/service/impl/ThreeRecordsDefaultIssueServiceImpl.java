package com.tianque.plugin.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.plugin.account.service.ThreeRecordsIssueWorkFlowEngine;
import com.tianque.plugin.account.validate.ThreeRecordsIssueOperationLogValidator;

@Service("threeRecordsDefaultIssueService")
@Transactional
public class ThreeRecordsDefaultIssueServiceImpl extends
		ThreeRecordsAbstractIssueServiceImpl {
	@Autowired
	@Qualifier("threeRecordsDefaultIssueLogValidator")
	private ThreeRecordsIssueOperationLogValidator issueLogValidator;
	@Autowired
	@Qualifier("threeRecordsDefaultIssueWorkFlowEngine")
	private ThreeRecordsIssueWorkFlowEngine issueWorkFlowEngine;

	@Override
	protected ThreeRecordsIssueWorkFlowEngine getIssueWorkFlowEngine() {
		return issueWorkFlowEngine;
	}

	@Override
	protected ThreeRecordsIssueOperationLogValidator getIssueLogValidator() {
		return issueLogValidator;
	}

}
