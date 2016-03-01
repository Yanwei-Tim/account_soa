package com.tianque.plugin.account.service;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.LedgerSteadyWork;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.vo.LedgerSteadyWorkVo;
import com.tianque.plugin.account.vo.ThreeRecordsIssueViewObject;

public interface LedgerSteadyWorkService {

	/**
	 * 按ID获取稳定工作
	 * 
	 * @param id
	 * @return
	 */
	public LedgerSteadyWork getLedgerSteadyWorkById(Long id);

	/**
	 * 按ID获取稳定工作所有信息
	 * 
	 * @param id
	 * @return
	 */
	public LedgerSteadyWork getFullLedgerSteadyWorkById(Long id);

	/**
	 * 新增稳定工作
	 * 
	 * @param id
	 * @return
	 */
	public LedgerSteadyWork addLedgerSteadyWork(
			LedgerSteadyWork ledgerSteadyWork);

	/**
	 * 修改稳定工作
	 * 
	 * @param id
	 * @return
	 */
	public void updateLedgerSteadyWork(LedgerSteadyWork ledgerSteadyWork);

	/**
	 * 删除稳定工作
	 * 
	 * @param id
	 * @return
	 */
	public void deleteLedgerSteadyWorkById(Long id);

	/**
	 * 按身份证号查找稳定工作
	 * 
	 * @param idCardNo
	 * @return
	 */
	public LedgerSteadyWork findByIdCardNo(String idCardNo);

	/**
	 * 分页查找待办的稳定工作事项列表
	 * 
	 * @param seachValue
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param issueType
	 * @param orgLevel
	 * @param leaderView
	 * @param functionalorgType
	 * @param viewProcess
	 * @param sourceType
	 * @param supportType
	 *            区分主办 （0）协办（1） 抄告（2）
	 * @return
	 */
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsNeedDo(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalorgType, Integer viewProcess,
			Long sourceType, int supportType,Integer year, Integer month);

	/**
	 * 分页查找办理中的稳定工作事项列表
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param issueType
	 * @param orgLevel
	 * @param leaderView
	 * @param functionalOrgType
	 * @param viewProcess
	 * @param sourceType
	 * @return
	 */
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsDone(Long orgId,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,Integer year, Integer month);

	/**
	 * 下辖部门上级交办稳定工作事项列表
	 * 
	 * @param seachValue
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param issueType
	 * @param orgLevel
	 * @param leaderView
	 * @param functionalOrgType
	 * @param viewProcess
	 * @param sourceType
	 * @return
	 */
	PageInfo<LedgerSteadyWorkVo> findJurisdictionsAssgin(String seachValue,
			Long orgId, Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,Integer year, Integer month);

	/**
	 * 下辖部门待反馈稳定工作事项列表
	 * 
	 * @param seachValue
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param issueType
	 * @param orgLevel
	 * @param leaderView
	 * @param functionalOrgType
	 * @param viewProcess
	 * @param sourceType
	 * @return
	 */
	PageInfo<LedgerSteadyWorkVo> findJurisdictionsFeedBack(String seachValue,
			Long orgId, Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,Integer year, Integer month);

	/**
	 * 查询阶段已办结稳定工作事项列表
	 * 
	 * @param seachValue
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param issueType
	 * @param orgLevel
	 * @param leaderView
	 * @param functionalOrgType
	 * @param viewProcess
	 * @param sourceType
	 * @return
	 */
	PageInfo<LedgerSteadyWorkVo> findJurisdictionsPeriodDone(String seachValue,
			Long orgId, Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,Integer year, Integer month);

	/**
	 * 查询实质办结稳定工作事项列表
	 * 
	 * @param seachValue
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param issueType
	 * @param orgLevel
	 * @param leaderView
	 * @param functionalOrgType
	 * @param viewProcess
	 * @param sourceType
	 * @return
	 */
	PageInfo<LedgerSteadyWorkVo> findJurisdictionsSubstanceDone(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType,Integer year, Integer month);

	/**
	 * 查询上报的稳定工作事项列表
	 * 
	 * @param seachValue
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param issueType
	 * @param orgLevel
	 * @param leaderView
	 * @param functionalOrgType
	 * @param viewProcess
	 * @param sourceType
	 * @return
	 */
	PageInfo<LedgerSteadyWorkVo> findJurisdictionsSubmit(String seachValue,
			Long orgId, Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,Integer year, Integer month);

	/**
	 * 处理受理的稳定工作
	 * 
	 * @param stepId
	 * @param log
	 * @return
	 */
	public ThreeRecordsIssueViewObject concept(Long stepId,
			ThreeRecordsIssueLogNew log);

	/**
	 * 处理从办的稳定工作
	 * 
	 * @param stepId
	 * @param log
	 * @param files
	 * @return
	 */
	public ThreeRecordsIssueViewObject support(Long stepId,
			ThreeRecordsIssueLogNew log, List<ThreeRecordsIssueAttachFile> files);

	/**
	 * 处理办理中的稳定工作
	 * 
	 * @param stepId
	 * @param log
	 * @param attachFiles
	 * @return
	 */
	public ThreeRecordsIssueViewObject comment(Long stepId,
			ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles);

	/**
	 * 处理结案的稳定工作
	 * 
	 * @param stepId
	 * @param log
	 * @param attachFiles
	 * @return
	 */
	public ThreeRecordsIssueViewObject complete(Long stepId,
			ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles);

	/**
	 * 处理申报的稳定工作
	 * 
	 * @param stepId
	 * @param log
	 * @param target
	 * @param tellorgs
	 * @param files
	 * @return
	 */
	public ThreeRecordsIssueViewObject declare(Long stepId,
			ThreeRecordsIssueLogNew log, Long target, Long[] tellorgs,
			List<ThreeRecordsIssueAttachFile> files);

	/**
	 * 处理转办的稳定工作
	 * 
	 * @param stepId
	 * @param log
	 * @param target
	 * @param tellorgs
	 * @param files
	 * @param notices
	 *            抄告部门id
	 * @return
	 */
	public ThreeRecordsIssueViewObject turn(Long stepId,
			ThreeRecordsIssueLogNew log, Long target, Long[] tellorgs,
			List<ThreeRecordsIssueAttachFile> files, Long[] notices);

	/**
	 * 处理派单（交办）的稳定工作
	 * 
	 * @param stepId
	 * @param log
	 * @param targeOrg
	 * @param tells
	 * @param attachFiles
	 * @param notices
	 * @return
	 */
	public ThreeRecordsIssueViewObject assign(Long stepId,
			ThreeRecordsIssueLogNew log, Long targeOrg, Long[] tells,
			List<ThreeRecordsIssueAttachFile> attachFiles, Long[] notices);

	/**
	 * 处理上报的稳定工作
	 * 
	 * @param stepId
	 * @param log
	 * @param submitTarget
	 * @param tells
	 * @param attachFiles
	 * @return
	 */
	public ThreeRecordsIssueViewObject submit(Long stepId,
			ThreeRecordsIssueLogNew log, Long submitTarget, Long[] tells,
			List<ThreeRecordsIssueAttachFile> attachFiles);

	/**
	 * 处理回退的稳定工作
	 * 
	 * @param stepId
	 * @param log
	 * @param attachFiles
	 * @return
	 */
	public ThreeRecordsIssueViewObject back(Long stepId,
			ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> attachFiles);

	/**
	 * 阶段结案
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @param files
	 *            附件
	 * @return
	 */
	public ThreeRecordsIssueViewObject peroidComplete(Long stepId,
			ThreeRecordsIssueLogNew log, List<ThreeRecordsIssueAttachFile> files);

	/**
	 * 程序结案
	 * 
	 * @param stepId
	 *            步骤id
	 * @param log
	 *            处理记录
	 * @param files
	 *            附件
	 * @return
	 */
	ThreeRecordsIssueViewObject programComplete(Long stepId,
			ThreeRecordsIssueLogNew log, List<ThreeRecordsIssueAttachFile> files);

	/**
	 * 处理临时办理的稳定工作
	 * 
	 * @param keyId
	 * @param operation
	 * @param files
	 * @return
	 */
	public ThreeRecordsIssueViewObject tmpComment(Long keyId,
			ThreeRecordsIssueLogNew operation,
			List<ThreeRecordsIssueAttachFile> files);

	/**
	 * 根据组织机构编号 创建稳定临时信息
	 * 
	 * @param orgId
	 * @return
	 */
	public LedgerSteadyWork createTemporaryLedgerSteadyWork(Long orgId);

	/**
	 * 数据导入保存稳定信息
	 * 
	 * @param ledgerSteadyWork
	 * @return
	 */
	public LedgerSteadyWork saveLedgerSteadyWork(
			LedgerSteadyWork ledgerSteadyWork);

	/*
	 * 数据导入时更新状态
	 * 
	 * @param ledgerSteadyWork
	 */
	public void updateLedgerSteadyWorkStatus(LedgerSteadyWork ledgerSteadyWork);

	/*
	 * 数据导入无日志记录的启动流程
	 * 
	 * @param ledgerSteadyWork
	 */
	public void registerProcess(LedgerSteadyWork ledgerSteadyWork);

	/**
	 * 根据历史编号查询台账
	 * 
	 * @param id
	 * @return
	 */
	public List<LedgerSteadyWork> getLedgerSteadyWorkByHistoryId(String id);

	/**
	 * 更新稳定反馈信息状态
	 * 
	 * @param ledgerId
	 * @param isFeedBack
	 */
	public void updateFeedBackStatus(Long ledgerId, int isFeedBack);

	/**
	 * 已办和新建稳定工作事项列表
	 * 
	 * @param orgId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param issueType
	 * @param orgLevel
	 * @param leaderView
	 * @param functionalOrgType
	 * @param viewProcess
	 * @param sourceType
	 * @return
	 */
	public PageInfo<LedgerSteadyWorkVo> findJurisdictionsCreateAndDone(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType,Integer year, Integer month);

	/**
	 * 统计台账数，数据导入用，判断是否已经导入过
	 * @param oldHistoryId
	 * @return
	 */
	public int countLedgerByOldHistoryId(String oldHistoryId, String orgCode);
	
	public Map<String, Integer> getIssueCount(Long orgId, Long issueType, Long orgLevel, String leaderView, Long sourceType, Long functionalOrgType, Integer year, Integer month);
}
