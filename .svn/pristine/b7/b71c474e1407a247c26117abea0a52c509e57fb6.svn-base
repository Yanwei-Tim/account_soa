package com.tianque.account.api;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.LedgerPoorPeople;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.vo.LedgerPoorPeopleVo;
import com.tianque.plugin.account.vo.ThreeRecordsIssueViewObject;

public interface LedgerPoorPeopleDubboService {

	/**
	 * 按ID获取困难群众
	 * 
	 * @param id
	 * @return
	 */
	public LedgerPoorPeople getLedgerPoorPeopleById(Long id);

	/**
	 * 按ID获取困难群众所有信息
	 * 
	 * @param id
	 * @return
	 */
	public LedgerPoorPeople getFullLedgerPoorPeopleById(Long id);

	/**
	 * 新增困难群众
	 * 
	 * @param id
	 * @return
	 */
	public LedgerPoorPeople addLedgerPoorPeople(
			LedgerPoorPeople ledgerPoorPeople);

	/**
	 * 修改困难群众
	 * 
	 * @param id
	 * @return
	 */
	public void updateLedgerPoorPeople(LedgerPoorPeople ledgerPoorPeople);

	/**
	 * 删除困难群众
	 * 
	 * @param id
	 * @return
	 */
	public void deleteLedgerPoorPeopleById(Long id);

	/**
	 * 修改困难群众状态
	 * 
	 * @param id
	 * @return
	 */
	public void updateLedgerPoorPeopleStatus(LedgerPoorPeople ledgerPoorPeople);

	/**
	 * 按身份证号查找困难群众
	 * 
	 * @param idCardNo
	 * @return
	 */
	public LedgerPoorPeople findByIdCardNo(String idCardNo);

	/**
	 * 分页查找待办的困难群众事项列表
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
	 * @return
	 */
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsNeedDo(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalorgType, Integer viewProcess,
			Long sourceType,Integer year,Integer month);

	/**
	 * 分页查找办理中的困难群众事项列表
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
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsDone(Long orgId,
			Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,Integer year,Integer month);

	/**
	 * 下辖部门上级交办困难群众事项列表
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
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsAssgin(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType,Integer year,Integer month);

	/**
	 * 下辖部门待跟进困难群众事项列表
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
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsFollow(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType,Integer year,Integer month);

	/**
	 * 下辖部门待反馈困难群众事项列表
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
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsFeedBack(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType,Integer year,Integer month);

	/**
	 * 查询阶段已办结困难群众事项列表
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
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsPeriodDone(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType,Integer year,Integer month);

	/**
	 * 查询实质办结困难群众事项列表
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
	PageInfo<LedgerPoorPeopleVo> findJurisdictionsSubstanceDone(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType,Integer year,Integer month);

	/**
	 * 查询上报的困难群众事项列表
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
	PageInfo<LedgerPoorPeopleVo> findJurisdictionsSubmit(String seachValue,
			Long orgId, Integer page, Integer rows, String sidx, String sord,
			Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,Integer year,Integer month);

	/**
	 * 处理受理的困难群众
	 * 
	 * @param stepId
	 * @param log
	 * @return
	 */
	public ThreeRecordsIssueViewObject concept(Long stepId,
			ThreeRecordsIssueLogNew log);

	/**
	 * 处理从办的困难群众
	 * 
	 * @param stepId
	 * @param log
	 * @param files
	 * @return
	 */
	public ThreeRecordsIssueViewObject support(Long stepId,
			ThreeRecordsIssueLogNew log, List<ThreeRecordsIssueAttachFile> files);

	/**
	 * 处理办理中的困难群众
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
	 * 处理结案的困难群众
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
	 * 处理申报的困难群众
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
	 * 处理转办的困难群众
	 * 
	 * @param stepId
	 *            步骤编号
	 * @param log
	 *            工作措施
	 * @param target
	 *            主办
	 * @param tellorgs
	 *            协办
	 * @param files
	 *            附件
	 * @param noticeOrgIds
	 *            抄告
	 * @return
	 */
	public ThreeRecordsIssueViewObject turn(Long stepId,
			ThreeRecordsIssueLogNew log, Long target, Long[] tellorgs,
			List<ThreeRecordsIssueAttachFile> files, Long[] noticeOrgIds);

	/**
	 * 处理派单（交办）的困难群众
	 * 
	 * @param stepId
	 *            步骤编号
	 * @param log
	 *            工作措施
	 * @param targeOrg
	 *            主办
	 * @param tells
	 *            协办
	 * @param attachFiles
	 *            附件
	 * @param notices
	 *            抄告
	 * @return
	 */
	public ThreeRecordsIssueViewObject assign(Long stepId,
			ThreeRecordsIssueLogNew log, Long targeOrg, Long[] tells,
			List<ThreeRecordsIssueAttachFile> attachFiles, Long[] notices);

	/**
	 * 处理上报的困难群众
	 * 
	 * @param stepId
	 *            步骤编号
	 * @param log
	 *            工作措施
	 * @param submitTarget
	 *            主办
	 * @param tells
	 *            协办
	 * @param attachFiles
	 *            附件
	 * @return
	 */
	public ThreeRecordsIssueViewObject submit(Long stepId,
			ThreeRecordsIssueLogNew log, Long submitTarget, Long[] tells,
			List<ThreeRecordsIssueAttachFile> attachFiles);

	/**
	 * 处理回退的困难群众
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
	 * 处理阶段结案的困难群众
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
	 * 处理程序结案的困难群众
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
	 * 处理临时办理的困难群众
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
	 * 根据组织机构编号 创建困难临时信息
	 * 
	 * @param orgId
	 * @return
	 */
	public LedgerPoorPeople createTemporaryLedgerPoorPeople(Long orgId);

	/**
	 * 数据导入时新增困难群众
	 * 
	 * @param ledgerPoorPeople
	 * @return
	 */
	public LedgerPoorPeople saveLedgerPoorPeople(
			LedgerPoorPeople ledgerPoorPeople);

	/*
	 * 数据导入无日志记录的启动流程
	 * 
	 * @param ledgerPoorPeople
	 */
	public void registerProcess(LedgerPoorPeople ledgerPoorPeople);

	/**
	 * 分页查找协办的困难群众事项列表
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
	 * @return
	 */
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsSupportDo(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalorgType, Integer viewProcess,
			Long sourceType,Integer year,Integer month);

	/**
	 * 分页查找抄告的困难群众事项列表
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
	 * @return
	 */
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsNoticeDo(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalorgType, Integer viewProcess,
			Long sourceType,Integer year,Integer month);

	/**
	 * 查询已办和新建困难群众事项列表
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
	public PageInfo<LedgerPoorPeopleVo> findJurisdictionsCreateAndDone(
			String seachValue, Long orgId, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType,Integer year,Integer month);

	/**
	 * 统计台账数，数据导入用，判断是否已经导入过
	 * @param oldHistoryId
	 * @return
	 */
	public int countLedgerByOldHistoryId(String oldHistoryId, String orgCode);
	
	public Map<String, Integer> getIssueCount(Long orgId, Long issueType, Long orgLevel, String leaderView, Long sourceType, Long functionalOrgType, Integer year, Integer month);
}
