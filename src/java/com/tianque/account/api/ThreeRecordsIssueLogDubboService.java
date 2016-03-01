package com.tianque.account.api;

import java.util.List;

import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.domain.ThreeRecordsIssueMap;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;

public interface ThreeRecordsIssueLogDubboService {
	/**
	 * 新增台账处理记录
	 * 
	 * @param log
	 *            台账处理记录
	 * @return
	 */
	public ThreeRecordsIssueLogNew addLog(ThreeRecordsIssueLogNew log);

	/**
	 * 根据id查询办理记录
	 * 
	 * @param id
	 *            处理记录id
	 * @return
	 */
	public ThreeRecordsIssueLogNew getLogById(Long id);

	/**
	 * 获取台账的所有的处理记录
	 * 
	 * @param issueId
	 *            台账id
	 * @param ledgerType
	 *            台账类型
	 * @return
	 */
	public List<ThreeRecordsIssueLogNew> loadOperationLogsByIssueId(Long issueId,
			Long ledgerType);

	/**
	 * 删除台账处理记录
	 * 
	 * @param ledgerId
	 * @param ledgerType
	 */
	public void deleteIssueLogByLedgerIdAndLedgerType(Long ledgerId, int ledgerType);

	/***
	 * 根据处理步骤id获取处理记录
	 * 
	 * @param stepid
	 *            步骤id
	 * @return
	 */
	public List<ThreeRecordsIssueLogNew> getLogsByStepId(Long stepid);

	/**
	 * 获取台账的在某个部门的处理记录
	 * 
	 * @param issueMap
	 * @param group
	 * @return
	 */
	public List<ThreeRecordsIssueLogNew> findDealLogByMapAndStepGroup(
			ThreeRecordsIssueMap issueMap, ThreeRecordsIssueStepGroup group);

	/**
	 * 更新台账处理记录
	 * 
	 * @param log
	 * @return
	 */
	public ThreeRecordsIssueLogNew updateLog(ThreeRecordsIssueLogNew log);

}
