package com.tianque.plugin.account.service;

import java.util.List;

import com.tianque.plugin.account.domain.LedgerSteadyWork;
import com.tianque.plugin.account.domain.LedgerSteadyWorkPeopleInfo;

public interface LedgerSteadyWorkPeopleInfoService {

	/**
	 * 按ID获取稳定工作成员
	 * 
	 * @param id
	 * @return
	 */
	public LedgerSteadyWorkPeopleInfo getLedgerSteadyWorkPeopleInfoById(Long id);

	/**
	 * 新增稳定工作成员
	 * 
	 * @param ledgerSteadyWorkPeopleInfo
	 * @return
	 */
	public LedgerSteadyWorkPeopleInfo addLedgerSteadyWorkPeopleInfo(
			LedgerSteadyWorkPeopleInfo ledgerSteadyWorkPeopleInfo);

	/**
	 * 修改稳定工作成员
	 * 
	 * @param ledgerSteadyWorkPeopleInfo
	 * @return
	 */
	public void updateLedgerSteadyWorkPeopleInfo(
			LedgerSteadyWorkPeopleInfo ledgerSteadyWorkPeopleInfo);

	/**
	 * 按ID删除稳定工作成员
	 * 
	 * @param ledgerSteadyWorkPeopleInfo
	 * @return
	 */
	public void deleteLedgerSteadyWorkPeopleInfoById(Long id);

	/**
	 * 按稳定工作Id删除关联的成员
	 * 
	 * @param id
	 */
	public void deleteLedgerSteadyWorkPeopleInfoByLedgerSteadyWorkId(Long id);

	/**
	 * 按稳定工作查找关联的成员
	 * 
	 * @param id
	 */
	public List<LedgerSteadyWorkPeopleInfo> findByLedgerSteadyWork(
			LedgerSteadyWork ledgerSteadyWork);

	/**
	 * 按稳定工作中的稳定成员新增成员
	 * @param ledgerSteadyWork
	 */
	public void addLedgerSteadyWorkPeopleInfos(LedgerSteadyWork ledgerSteadyWork);

}
