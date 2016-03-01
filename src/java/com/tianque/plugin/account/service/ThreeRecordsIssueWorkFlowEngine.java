package com.tianque.plugin.account.service;

import java.util.List;

import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;

public interface ThreeRecordsIssueWorkFlowEngine {

	/**
	 * 台账步骤处理注册
	 * @param baseWork
	 * @return
	 */
	public ThreeRecordsIssueStep register(BaseWorking baseWork);

	/**
	 * 按台账ID和台账类型注销步骤处理
	 * @param ledgerId
	 * @param ledgerType
	 * @return
	 */
	public boolean unRegister(Long ledgerId, int ledgerType);

	/**
	 * 结案
	 * 
	 * @param baseWork
	 *            台账
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	public ThreeRecordsIssueStep complete(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles);

	/**
	 * 上报
	 * 
	 * @param baseWork
	 *            台账
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param submitTarget
	 *            上报目标部门
	 * @param tells
	 *            协办部门
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	public ThreeRecordsIssueStep submit(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			Long submitTarget, Long[] tells,
			List<ThreeRecordsIssueAttachFile> attachFiles,Long[] notices);

	/**
	 * 回退
	 * 
	 * @param baseWork
	 *            台账
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	public ThreeRecordsIssueStep back(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles);

	/**
	 * 受理
	 * 
	 * @param baseWork
	 *            台账
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @return
	 */
	public ThreeRecordsIssueStep concept(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log);

	/***
	 * 办理中
	 * 
	 * @param baseWork
	 *            台账
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	public ThreeRecordsIssueStep comment(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles);

	/***
	 * 交办
	 * 
	 * @param baseWork
	 *            台账
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param targetOrg
	 *            交办目标部门
	 * @param tells
	 *            协办部门
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	public ThreeRecordsIssueStep assign(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			Long targetOrg, Long[] tells,
			List<ThreeRecordsIssueAttachFile> attachFiles,Long[] notices);

	/**
	 * 根据id获取完整的台账处理步骤对象
	 * 
	 * @param stepId
	 *            步骤id
	 * @return
	 */
	public ThreeRecordsIssueStep getFullIssueStepById(Long stepId);

	/**
	 * 按台账处理步骤ID和机构获取台账操作
	 * @param stepId
	 * @param org
	 * @return
	 */
	public List<ThreeRecordsIssueOperate> getIssueCandoForOrg(Long stepId,
			Organization org);

	/**
	 * 按台账处理步骤ID、台账操作和标签查询 机构ID,NAEM
	 * @param stepid
	 * @param operate
	 * @param tag
	 * @param exceptIds
	 * @param twice
	 * @param pageIndex
	 * @param rows
	 * @return
	 */
	public PageInfo<AutoCompleteData> findAdminTargetsByName(Long stepid,
			ThreeRecordsIssueOperate operate, String tag, Long[] exceptIds,
			boolean twice, int pageIndex, int rows);

	/**
	 * 按台账处理步骤ID、台账操作和标签查询 机构ID,NAEM
	 * @param stepid
	 * @param operate
	 * @param tag
	 * @param exceptIds
	 * @param pageIndex
	 * @param rows
	 * @return
	 */
	public PageInfo<AutoCompleteData> findFunctionTargetsByName(Long stepid,
			ThreeRecordsIssueOperate operate, String tag, Long[] exceptIds,
			int pageIndex, int rows);

	/**
	 * 按台账处理步骤ID、台账操作和标签查询 机构ID,NAEM
	 * @param stepid
	 * @param operate
	 * @param tag
	 * @param transferToAdmin
	 * @param exceptIds
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<AutoCompleteData> findTellTargetsByName(Long stepid,
			ThreeRecordsIssueOperate operate, String tag,
			boolean transferToAdmin, Long[] exceptIds, int page, int rows);

	/**
	 * 台账操作改变时处理步骤及状态
	 * @param baseWork
	 * @param operate
	 * @param log
	 * @param attachFiles
	 */
	public void fireIssueChanged(BaseWorking baseWork,
			ThreeRecordsIssueOperate operate, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles);

	/**
	 * 台账操作改变时处理步骤及状态
	 * @param baseWork
	 * @param step
	 * @param operate
	 * @param log
	 * @param attachFiles
	 */
	public void fireIssueChanged(BaseWorking baseWork, ThreeRecordsIssueStep step,
			ThreeRecordsIssueOperate operate, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles);

	/***
	 * 转办
	 * 
	 * @param baseWork
	 *            台账
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param targetOrg
	 *            转办目标部门
	 * @param tells
	 *            协办部门
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	public ThreeRecordsIssueStep turn(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			Long targetOrg, Long[] tells,
			List<ThreeRecordsIssueAttachFile> attachFiles,Long[] notices);

	/***
	 * 申報
	 * 
	 * @param baseWork
	 *            台账
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param targetOrg
	 *            转办目标部门
	 * @param tells
	 *            协办部门
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	public ThreeRecordsIssueStep declare(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			Long targetOrg, Long[] tells,
			List<ThreeRecordsIssueAttachFile> attachFiles,Long[] notices);

	/***
	 * 輔助辦理
	 * 
	 * @param baseWork
	 *            台账
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	public ThreeRecordsIssueStep support(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles);

	/**
	 * 阶段结案
	 * 
	 * @param baseWork
	 *            台账
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	public ThreeRecordsIssueStep periodComplete(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles);

	/**
	 * 程序结案
	 * 
	 * @param baseWork
	 *            台账
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	public ThreeRecordsIssueStep programComplete(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles);

	/**
	 * 添加措施
	 * 
	 * @param baseWork
	 *            台账
	 * @param step
	 *            步骤
	 * @param log
	 *            处理记录
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	public ThreeRecordsIssueStep tmpComment(BaseWorking baseWork,
			ThreeRecordsIssueStep step, ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles);
	
	
	
	/**
	 * 按台账处理步骤ID、台账操作和标签查询 机构ID,NAEM
	 * @param stepid
	 * @param operate
	 * @param tag
	 * @param exceptIds
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<AutoCompleteData> findNoticeTargetsByName(Long stepid,
			ThreeRecordsIssueOperate operate, String tag, Long[] exceptIds, int page, int rows);
}
