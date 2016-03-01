package com.tianque.plugin.account.service;

import java.util.List;

import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;

public interface ThreeRecordsSearchIssueStepService {

	/**
	 * 按台账ID和台账类型获取台账处理步骤 
	 * @param issueId
	 * @param ledgerType
	 * @return
	 */
	public List<ThreeRecordsIssueStep> searchIssueStepsByIssueId(Long issueId,
			int ledgerType);

	/**
	 * 按台账处理步骤 ID获取台账处理步骤 
	 * @param stepId
	 * @return
	 */
	public List<ThreeRecordsIssueStep> searchAllIssueStepsByStepId(Long stepId);

	/**
	 * 判断该操作是否有权限
	 * @param ledgerId
	 * @param ledgerType
	 * @param stepId
	 * @param userOrgCode
	 * @return
	 */
	public boolean hasPermission(Long ledgerId, int ledgerType, Long stepId,
			String userOrgCode);

}
