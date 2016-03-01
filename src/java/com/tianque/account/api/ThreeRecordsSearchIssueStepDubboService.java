package com.tianque.account.api;

import java.util.List;

import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;

public interface ThreeRecordsSearchIssueStepDubboService {

	/**
	 * 根据台账编号，类型查询步骤
	 * 
	 * @param issueId
	 * @param ledgerType
	 * @return
	 */
	List<ThreeRecordsIssueStep> searchIssueStepsByIssueId(Long issueId,
			int ledgerType);

	/**
	 * 根据步骤编号查询此台账相关的所有步骤
	 * 
	 * @param stepId
	 * @return
	 */
	List<ThreeRecordsIssueStep> searchAllIssueStepsByStepId(Long stepId);

	/**
	 * 根据台账编号，类型，步骤，组织机构编号 查询是否有权限
	 * 
	 * @param ledgerId
	 * @param ledgerType
	 * @param stepId
	 * @param userOrgCode
	 * @return
	 */
	boolean hasPermission(Long ledgerId, int ledgerType, Long stepId,
			String userOrgCode);

}
