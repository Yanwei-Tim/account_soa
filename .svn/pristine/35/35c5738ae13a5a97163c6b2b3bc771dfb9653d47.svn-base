package com.tianque.plugin.account.dao;

import java.util.List;

import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.domain.ThreeRecordsIssueMap;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;

public interface ThreeRecordsIssueLogDao {
	/**
	 * 新增台账处理记录
	 * 
	 * @param log
	 *            台账处理记录
	 * @return
	 */
	ThreeRecordsIssueLogNew addLog(ThreeRecordsIssueLogNew log);

	/**
	 * 根据id查询办理记录
	 * 
	 * @param id
	 *            处理记录id
	 * @return
	 */
	ThreeRecordsIssueLogNew getIssueLogById(Long id);

	/**
	 * 获取台账的所有的处理记录
	 * 
	 * @param issueid
	 *            台账id
	 * @return
	 */
	List<ThreeRecordsIssueLogNew> loadIssueOperationLogsByIssueId(Long issueid,
			Long ledgerType);

	/**
	 * 删除台账处理记录
	 * 
	 * @param issueId
	 *            台账id
	 * @return
	 */
	boolean deleteIssueLogByIssueId(Long issueId, int ledgerType);

	/***
	 * 根据处理步骤id获取处理记录
	 * 
	 * @param stepid
	 *            步骤id
	 * @return
	 */
	List<ThreeRecordsIssueLogNew> getIssueLogsByStepId(Long stepid);

	/**
	 * 获取台账的在某个部门的处理记录
	 * 
	 * @param issueMap
	 * @param group
	 * @return
	 */
	List<ThreeRecordsIssueLogNew> findIssueDealLogByIssueMapAndIssueStepGroup(
			ThreeRecordsIssueMap issueMap, ThreeRecordsIssueStepGroup group);

	public ThreeRecordsIssueLogNew updateLog(ThreeRecordsIssueLogNew log);

}
