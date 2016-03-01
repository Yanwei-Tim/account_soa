package com.tianque.account.api;

import java.util.List;

import com.tianque.plugin.account.domain.ThreeRecordsIssueMap;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;

public interface ThreeRecordsIssueProcessDubboService {

	/***
	 * 保存台账的处理步骤
	 * 
	 * @param issueStep
	 *            台账步骤
	 * @return
	 */
	public ThreeRecordsIssueStep addLedgerStep(ThreeRecordsIssueStep issueStep);

	/**
	 * 删除台账步骤
	 * 
	 * @param ledgerId
	 *            台账id
	 * @param ledgerType
	 *            台账类型
	 */
	public void deleteLedgerStepsByIdAndType(Long ledgerId, int ledgerType);

	/**
	 * 删除台账步骤组
	 * 
	 * @param ledgerId
	 *            台账id
	 * @param ledgerType
	 *            台账类型
	 */
	public void deleteLedgerStepGroupsByIdAndType(Long ledgerId, int ledgerType);

	/**
	 * 根据id获取台账步骤
	 * 
	 * @param id
	 *            台账步骤id
	 * @return
	 */
	public ThreeRecordsIssueStep getSimpleIssueStepById(Long id);

	/**
	 * 更新台账步骤
	 * 
	 * @param step
	 *            步骤
	 * @return
	 */
	public ThreeRecordsIssueStep updateIssueStepExceptOrg(ThreeRecordsIssueStep step);

	/**
	 * 更新台账组
	 * 
	 * @param step
	 *            步骤
	 * @return
	 */
	public boolean updateGroupId(ThreeRecordsIssueStep step);

	/**
	 * 新增步骤组
	 * 
	 * @param issueStepGroup
	 *            步骤组
	 * @return
	 */
	public ThreeRecordsIssueStepGroup addIssueStepGroup(
			ThreeRecordsIssueStepGroup issueStepGroup);

	/**
	 * 简单的步骤组信息
	 * 
	 * @param id
	 * @return
	 */
	public ThreeRecordsIssueStepGroup getSimpleIssueStepGroupById(Long id);

	/**
	 * 根据台账id 获取该台账所有的处理步骤组
	 * 
	 * @param issueId
	 * @param ledgerType
	 * @return
	 */
	public List<ThreeRecordsIssueStepGroup> getIssueStepGroupByIssueId(Long issueId,
			int ledgerType);

	/**
	 * 更新输出工作记录
	 * 
	 * @param issueStepGroup
	 *            步骤组
	 * @return
	 */
	public boolean updateOutLog(ThreeRecordsIssueStepGroup issueStepGroup);

	/***
	 * 根据台账IssueStepGroup issuemap
	 * 
	 * @param issueStepGroup
	 *            处理步骤组
	 * @return
	 */
	public ThreeRecordsIssueMap getIssueMapByStepGroup(
			ThreeRecordsIssueStepGroup issueStepGroup);
	
	
	public void updateStateAndCode(Long ledgerId, int ledgerType);

}
