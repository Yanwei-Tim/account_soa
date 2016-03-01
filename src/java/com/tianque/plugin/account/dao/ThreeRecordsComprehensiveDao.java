package com.tianque.plugin.account.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.vo.LedgerAgricultureDetail;
import com.tianque.plugin.account.vo.LedgerEducationDetail;
import com.tianque.plugin.account.vo.LedgerEnergyDetail;
import com.tianque.plugin.account.vo.LedgerEnvironmentDetail;
import com.tianque.plugin.account.vo.LedgerLaborDetail;
import com.tianque.plugin.account.vo.LedgerMedicalDetail;
import com.tianque.plugin.account.vo.LedgerOtherDetail;
import com.tianque.plugin.account.vo.LedgerPeopleSubstanceDesc;
import com.tianque.plugin.account.vo.LedgerScienceDetail;
import com.tianque.plugin.account.vo.LedgerTownDetail;
import com.tianque.plugin.account.vo.LedgerTrafficDetail;
import com.tianque.plugin.account.vo.LedgerWaterDetail;
import com.tianque.plugin.account.vo.ThreeRecordsCatalogVo;
import com.tianque.plugin.account.vo.ThreeRecordsIssueViewObject;
import com.tianque.plugin.account.vo.SearchComprehensiveVo;
import com.tianque.plugin.account.vo.ThreeRecordsViewObject;

public interface ThreeRecordsComprehensiveDao {

	/**
	 * 待办/抄告/协办
	 * @param searchVo
	 * @param org
	 * @param childOrg
	 * @param page
	 * @param rows
	 * @param isSupported
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsNeedDo(
			SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows, Integer isSupported);
	
	public Integer countfindJurisdictionsNeedDo(Map<String, Object> map);
	
	/**
	 * 程序性办结
	 * @param searchVo
	 * @param org
	 * @param childOrg
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsDone(
			SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows);
	
	public Integer cuntfindJurisdictionsDone(Map<String, Object> map);
	
	/**
	 * 阶段性办结
	 * @param searchVo
	 * @param org
	 * @param childOrg
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsPeriodDone(
			SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows);
	
	public Integer countfindJurisdictionsPeriodDone(Map<String, Object> map);
	
	
	/**
	 * 实质性办结
	 * @param searchVo
	 * @param org
	 * @param childOrg
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubStanceDone(
			SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows);
	
	
	/**
	 * 待反馈
	 * @param searchVo
	 * @param org
	 * @param childOrg
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsFeedBack(
			SearchComprehensiveVo searchVo,  List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows);
	
	public Integer countfindJurisdictionsFeedBack(Map<String, Object> map);

	
	/**
	 * 交办
	 * @param searchVo
	 * @param org
	 * @param childOrg
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsAssgin(
			SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows);
	
	public Integer countfindJurisdictionsAssgin(Map<String, Object> map);

	/**
	 * 上报
	 * @param searchVo
	 * @param org
	 * @param childOrg
	 * @param page
	 * @param rows
	 * @return
	 */
	PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsSubmit(
			SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows);
	
	public Integer countfindJurisdictionsSubmit(Map<String, Object> map);
	
	
	/**
	 * 已办
	 * @param searchVo
	 * @param org
	 * @param childOrg
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<ThreeRecordsIssueViewObject> findJurisdictionsCreateAndDone(
			SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows);
	
	public Integer countfindJurisdictionsSubstanceDone(Map<String, Object> map);
	public Integer countfindJurisdictionsCreateAndDone(Map<String, Object> map);
	
	/**
	 * 导出台账基本信息
	 * @param searchVo
	 * @param orgs
	 * @param childOrg
	 * @param page
	 * @param rows
	 * @param query
	 * @return
	 */
	public PageInfo<ThreeRecordsViewObject> exportCreateAndDone(
			SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows, String query);
	
	/**
	 * 导出台账基本信息数量
	 * @param searchVo
	 * @param orgs
	 * @param childOrg
	 * @param page
	 * @param rows
	 * @return
	 */
	public Integer exportCountCreateAndDone(
			SearchComprehensiveVo searchVo, List<Organization> orgs, List<Long> childOrg,
			Integer page, Integer rows);
	
	/**
	 * 导出目录列表
	 * @param searchVo
	 * @param orgs
	 * @param childOrg
	 * @param page
	 * @param rows
	 * @param map
	 * @param query
	 * @return
	 */
	public PageInfo<ThreeRecordsCatalogVo> exportCataLog(SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Integer page, Integer rows, Map<String, Object> map, String query);
	
	/**
	 * 目录列表数量
	 * @param searchVo
	 * @param orgs
	 * @param childOrg
	 * @param page
	 * @param rows
	 * @param map
	 * @return
	 */
	public Integer countCataLog(SearchComprehensiveVo searchVo, List<Organization> orgs,
			List<Long> childOrg, Integer page, Integer rows, Map<String, Object> map);
	
	public List<LedgerPeopleSubstanceDesc> countSubstanceDoneByDesc(Map<String, Object> map);
	public List<LedgerWaterDetail> getAllWaterResult(Map<String, Object> map);
	public List<LedgerWaterDetail> getBuildWaterResult(Map<String, Object> map);
	
	public List<LedgerTrafficDetail> getAllTrafficResult(Map<String, Object> map);
	public List<LedgerTrafficDetail> getBuildTrafficResult(Map<String, Object> map);
	public List<LedgerTrafficDetail> getSubCategoryTrafficResult(Map<String, Object> map);
	public List<LedgerTrafficDetail> getPassSubCategoryTrafficResult(Map<String, Object> map);
	
	public List<LedgerEducationDetail> getAllEductionResult(Map<String, Object> map);
	public List<LedgerEducationDetail> getBuildEductionResult(Map<String, Object> map);
	public List<LedgerEducationDetail> getBuildEductionAreaResult(Map<String, Object> map);
	public List<LedgerEducationDetail> getSubBuildEducationResult(Map<String, Object> map);
	public List<LedgerEducationDetail> getDisEducationResult(Map<String, Object> map);
	public List<LedgerEducationDetail> getRoadEducationResult(Map<String, Object> map);
	
	public List<LedgerEnergyDetail> getAllEnergyResult(Map<String, Object> map);
	public List<LedgerEnergyDetail> getSubEnergyResult(Map<String, Object> map);
	public List<LedgerEnergyDetail> getSubSecEnergyResult(Map<String, Object> map);
	
	public List<LedgerScienceDetail> getAllScienceResult(Map<String, Object> map);
	
	public List<LedgerMedicalDetail> getAllMedicalResult(Map<String, Object> map);
	
	public List<LedgerLaborDetail> getAllLaborResult(Map<String, Object> map);
	public List<LedgerLaborDetail> getSubLaborResult(Map<String, Object> map);
	public List<LedgerLaborDetail> getSubCotentLaborResult(Map<String, Object> map);
	
	public List<LedgerEnvironmentDetail> getAllEnvironmentResult(Map<String, Object> map);
	public List<LedgerEnvironmentDetail> getSubEnvironmentResult(Map<String, Object> map);
	
	public List<LedgerTownDetail> getAllTownResult(Map<String, Object> map);
	
	public List<LedgerAgricultureDetail> getAllAgricultureResult(Map<String, Object> map);
	public List<LedgerAgricultureDetail> getSubAgricultureResult(Map<String, Object> map);
	
	public List<LedgerOtherDetail> getAllOtherResult(Map<String, Object> map);
}

