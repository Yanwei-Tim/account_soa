package com.tianque.plugin.account.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.dao.ThreeRecordsIssueDao;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.service.ThreeRecordsSearchIssueStepService;
import com.tianque.plugin.account.state.exception.ThreeRecordsIssueOperationException;

@Transactional
@Service("threeRecordsSearchIssueStepService")
public class ThreeRecordsSearchIssueStepServiceImpl implements
		ThreeRecordsSearchIssueStepService {
	@Autowired
	private ThreeRecordsIssueDao issueDao;

	@Override
	public List<ThreeRecordsIssueStep> searchIssueStepsByIssueId(Long issueId,
			int ledgerType) {
		if (issueId == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		return issueDao.searchIssueStepsByIssueId(issueId, ledgerType);
	}

	@Override
	public List<ThreeRecordsIssueStep> searchAllIssueStepsByStepId(Long stepId) {
		if (stepId == null) {
			throw new ThreeRecordsIssueOperationException("参数未获得！");
		}
		return issueDao.searchAllIssueStepsByStepId(stepId);
	}

	@Override
	public boolean hasPermission(Long ledgerId, int ledgerType, Long stepId,
			String userOrgCode) {
		boolean hasPermission = false;
		String issueStepOrgCode;
		Organization issueStepOrg;
		List<ThreeRecordsIssueStep> issueSteps;
		if (ledgerId != null) {
			issueSteps = issueDao.searchIssueStepsByIssueId(ledgerId,
					ledgerType);
		} else {
			issueSteps = issueDao.searchAllIssueStepsByStepId(stepId);
		}
		if (issueSteps == null) {
			return true;
		}
		for (ThreeRecordsIssueStep IssueStep : issueSteps) {
			issueStepOrg = IssueStep.getTarget();
			if (issueStepOrg != null) {
				issueStepOrgCode = issueStepOrg.getOrgInternalCode();
			} else {
				continue;
			}
			if (StringUtil.isStringAvaliable(issueStepOrgCode)
					&& issueStepOrgCode.indexOf(userOrgCode) == 0) {
				hasPermission = true;
				break;
			}
		}
		return hasPermission;
	}

}
