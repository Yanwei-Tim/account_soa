package com.tianque.userAuth.api;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.sysadmin.service.impl.ReferType;

/**
 * @ClassName: OrganizationDubboService
 * @Description: 认证中心dubbo服务接口
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2015年3月9日 下午1:50:02
 */
public interface OrganizationDubboRemoteService {
	/**
	 * 根据所属足迹机构的Id和组织机构类型查询组织机构列表
	 * 
	 * @param parentId
	 * @param orgType
	 * @return
	 */
	public List<Organization> findOrganizationByParentIdAndOrgType(
			Long parentId, Long orgType);

	/**
	 * 根据组织机构的Id查询所属组织机构的id列表,例如参数是西湖区的id，结果中包含杭州市、浙江省、中国的id
	 * 
	 * @param id
	 * @return
	 */
	public List<Long> searchParentOrgIds(Long id, Long rootId);

	/**
	 * 根据组织机构的Id查询所属组织机构的id列表，直到镇级别,例如参数是翠苑社区网格一的ID，会查询出，翠苑社区ID，翠园街道ID
	 * 
	 * @param id
	 * @return
	 */
	public List<Long> searchParentOrgIdsWhenRootIsTown(Long id);

	/**
	 * 根据所属组织机构，查询所有子组织机构列表
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Organization> findOrganizationsByParentId(Long parentId);

	/**
	 * 根据所属组织机构，查询职能部门子组织机构列表
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Organization> findFunOrgsByParentId(Long parentId);

	/**
	 * 根据所属组织机构，查询行政部门子组织机构列表
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Organization> findAdminOrgsByParentId(Long id);

	/**
	 * 根据id,查询记录，不做关联查询
	 * 
	 * @param parentId
	 * @return
	 */
	public Organization getSimpleOrgById(Long id);

	/**
	 * 根据id,查询记录，做关联查询
	 * 
	 * @param id
	 * @return
	 */
	public Organization getFullOrgById(Long id);

	/**
	 * 添加组织机构
	 * 
	 * @param organization
	 * @return
	 */
	public Organization addOrganization(Organization organization);

	/**
	 * 更新组织机构的名称、所属类型、联系方式
	 * 
	 * @param organization
	 *            参数中必须要有id
	 * @return
	 */
	public Organization updateOrgNameAndOrgTypeAndContactWay(
			Organization organization);

	/**
	 * 根据部门编号查询组织机构，部门编号是唯一的字段
	 * 
	 * @param departmentNo
	 * @return
	 */
	public Organization getOrgByDepartmentNo(String departmentNo);

	/**
	 * 根据组织机构Id，删除组织机构
	 * 
	 * @param id
	 * @return
	 */
	public String deleteOrgById(Long id);

	/**
	 * 在用户权限下，根据‘名称%’，查询所有部门
	 * 
	 * @param orgName
	 *            此参数做了like查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Organization> findOrganizationsByOrgNameForPage(String orgName,
			int pageNum, int pageSize);

	/**
	 * 在用户权限下，根据‘名称%’，查询和用户所在部门类型相同的部门
	 * 
	 * @param orgName
	 *            此参数做了like查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Organization> findOrganizationsByorgNameAndOrgType(
			String orgName, int pageNum, int pageSize);

	/**
	 * 把参数为id的组织机构，移动到referorgid的position位置
	 * 
	 * @param id
	 * @param parentId
	 *            必须是在此组织机构下进行移动操作
	 * @param postion
	 *            位置参数，包括上、下、到底、到顶
	 * @param referOrgId
	 */
	public void moveOrganization(Long id, Long parentId, ReferType postion,
			Long referOrgId);

	/**
	 * @param orgIds
	 * @return
	 */
	public Map<Long, String> getOrganizationDisplayName(Long[] orgIds);

	/**
	 * 根据用户的id，查询责任区
	 * 
	 * @param userId
	 * @return
	 */
	public List<Organization> findMultizonesByUserId(Long userId);

	/**
	 * 根据 根部门id 和 目标部门id 获得 相对路径名，->分割
	 * 
	 * @param rootOrgId
	 *            根部门id
	 * @param orgId
	 *            目标部门id
	 * @return 相对路径名，->分割
	 */
	public String getOrganizationRelativeNameByRootOrgIdAndOrgId(Long orgId,
			Long rootOrgId);

	/**
	 * 根据所属组织机构id，组织机构名称，查询组织机构列表，但是不包含参数为id的组织机构
	 * 
	 * @param id
	 * @param orgName
	 *            做等于查询
	 * @param parentId
	 * @return
	 */
	public List<Organization> findOrganizationsByOrgNameAndParentId(Long id,
			String orgName, Long parentId);

	/**
	 * 根据所属组织机构id（organization.parent.id），orgLevel,orgType，departmentNo,查询组织机构列表
	 * ， 但是不包含参数为(organization.id)的组织机构
	 * 
	 * @param organization
	 * @return
	 */
	public List<Organization> findOrganizationsByDepartmentNoAndTypeAndLevel(
			Organization organization);

	/**
	 * 统计辖区的org总数
	 * 
	 * @param orgInternalCode
	 *            内置编码
	 * @return
	 */
	public int countOrgsByOrgInternalCode(String orgInternalCode);

	/**
	 * 根据所属组织机构ID，org类型的internalId，查询组织机构
	 * 
	 * @param parentId
	 * @param orgTypeInternalIds
	 * @return
	 */
	public List<Organization> findOrgsByParentIdAndOrgTypeInternalIds(
			Long parentId, Long[] orgTypeInternalIds);

	public List<Organization> findOrgsByParentIdAndFunTypes(Long parentId);

	public List<Organization> findMultizonesWithParentOrgNameByUserId(
			Long userId);

	/**
	 * 根据父组织机构、组织机构名称获得单个组织机构
	 */
	public Organization getOrganizationsByParentAndOrgName(Long parentId,
			String orgName);

	public boolean isGridOrganization(Long orgId);

	public boolean isTownOrganization(Long orgId);

	public boolean isDistrictOfAdministrativeRegion(Organization organization);

	public Long getTownOrganizationId(Long orgId);

	/**
	 * 根据父组织id获取该组织机构下子部门的最大部门编号
	 * 
	 * @param parentId
	 * @return
	 */
	public String getMaxDepartmentNoByParentId(Long parentId, Long orgLevel);

	/**
	 * 判断组织机构是否在区县级别以上
	 * 
	 * @param orgId
	 * @return boolean 是：true,否：false
	 */
	public boolean ifGreaterThanDistrictOrgId(Long orgId);

	public Organization getDistrictOrganizationId(Long orgId);

	public Organization getOrganizationByOrgInternalCode(String orgInternalCode);

	public List<Organization> findOrganizationsByOrgInternalCode(
			String orgInternalCode);

	public Organization updateOrganizationByName(String orgName, Long id,
			Organization domain);

	public List<Organization> findFunOrgsByParentIdAndName(Long id, String tag);

	public List<Organization> findAdminOrgsByParentIdAndName(Long id, String tag);

	public List<Organization> findFunOrgsByParentFunOrgIdAndName(Long id,
			String tag);

	public Organization getSimplePinyinBySimpleCode(String simpleCode);

	public boolean validateRepeatDepartmentNo(Organization organization);

	public List<Organization> getOrganizationByIdAndOrgInternalCode();

	public String[] findDepartmentNosByParentDeparmentNo(String departmentNo);

	public Organization getOrgAndLevelInfo(Long orgId);

	public List<Organization> fingOrganizationforLevel(Long orgLevelId);

	public Organization updateOrganizationForGis(Long orgId, GisInfo gisInfo);

	public Organization unBundOrgToGis(Long orgId);

	public Organization getDistrictTownOrganizationId(Long orgId);

	/**
	 * 向上查找与指定级别相同的上级部门，如果本身级别大于指定级别返回空。
	 */
	public Organization findSomeLevelParentOrg(Long orgId, int levelInternalId);

	/**
	 * 判断是否存在该部门
	 */
	public boolean isHasThisOrganization(Long orgId);

	public List<Organization> findOrgsByOrgCodeAndOrgLevelInternalsAndOrgTypeInternals(
			String orgCode, List<Integer> orgLevelInternals,
			List<Integer> orgTypeInternals, List<Long> exceptOrgIds);

	/**
	 * 取得顶级的org，有且只有一条 顶级org的条件是parnetId为null，
	 * 
	 * @return
	 */
	public Organization getRootOrganization();

	public List<Organization> findChildOrgs(Long startOrgId, Long endOrgId);

	/**
	 * 根据关键字查询组织机构列表，提取一定数量的记录，并把父组织机构也捞取出来
	 * 
	 * @param keyword
	 *            搜索关键字
	 * @param size
	 *            共捞取前几条
	 * @return 组织机构的列表，包含了父组织机构的数据
	 */
	public List<Organization> findOrgsFetchParentOrgByKeyword(String keyword,
			int size);

	/**
	 * 根据orgTypeInternalId、orgLevelInternalId、所属的orgId，查询辖区及以下组织机构列表
	 * 
	 * @param orgTypeInternalId
	 * @param orgLevelInternalId
	 * @param orgId
	 * @return
	 */
	public List<Organization> findOrgsByOrgTypeAndOrgLevelAndParentId(
			Integer orgTypeInternalId, Integer orgLevelInternalId, Long parentId);

	/**
	 * 根据orgTypeInternalId、orgLevelInternalId、所属的orgId，查询辖区及以下组织机构列表
	 * 
	 * @param orgTypeInternalId
	 * @param orgLevelInternalId
	 * @param orgId
	 * @return
	 */
	public List<Organization> findOrgsByOrgTypeAndOrgLevel(
			Integer orgTypeInternalId, Integer orgLevelInternalId,
			Long userOrgId);

	/**
	 * 查询职能部门类型的组织机构，参数为父职能部门Id
	 * 
	 * @param funParentId
	 * @return
	 */
	public List<Organization> findFunOrgsByFunParentId(Long funParentId);

	public String getOrgInternalCodeById(Long orgId);

	public Organization getTownOrganizationById(Long orgId);

	public List<String> findOrganizationInternaCodeHaiNing();

	public List<String> findOrganizationInternaCodeSpecifiedOrgName(
			String orgName);

	public List<Organization> getOrgZN(Long id);

	public String getUserCityOrganizationName();

	/**
	 * 根据部门编号(departmentno)模糊查询所有的子组织机构列表
	 * 
	 * @param departmentNo
	 * @return
	 */
	public List<Organization> findOrgsByDepartmentNo(String departmentNo);

	public List<Organization> findFunOrgsByParentOrgId(Long parentOrgId);

	public List<Organization> findOrganizationsByOrgName(String orgName);

	public List<Organization> findOrgsByParentIdAndOrgTypeInternalIdsAndFunctionalType(
			Long parentId, Long[] orgTypeInternalIds, Long orgFunctionalType);

	public Organization getParentOrgByOrgTypeAndChildOrgId(Long orgId,
			int orgLevel);

	public boolean hasFunOrganizationByParentOrgAndFunOrgType(
			String parentOrgCode, Long funOrgType);

	public List<Long> getOrganizationsByLevel(Long typeId, Long levelId);

	/**
	 * 根据部门类型层级获得 DepartmentNo 用于分表
	 * 
	 * @param typeId
	 * @param levelId
	 * @return
	 */
	public List<String> getDepartmentNoByLevel(Long typeId, Long levelId);

	/**
	 * 根据网格类型id orgType查询县级以下（包括县级）组织机构
	 * 
	 * @param orgTypeId
	 * @param searchOrgCode
	 * @param orgCodeFindLevel
	 * @return
	 */
	public List<Organization> findDistrictAdminOrgsByOrgType(Long orgTypeId,
			String searchOrgCode, String orgCodeFindLevel);

	/**
	 * 根据所属组织机构，往上查询省级的组织机构
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Organization> findProvinceOrganizationsByOrgId(Long orgId);

	public List<Organization> findOrganizationByOrgIdAndNum(Long orgId,
			Integer num);

	/**
	 * 根据当前用户的 OrganzitionCode查询该用户所有的组织机构
	 */
	public List<Organization> findOrganizationByOrgTypeAndOrgLevelAndParentId(
			Integer orgTypeInternalId, Integer orgLevelInternalId, Long parentId);

	/**
	 * 根据组织机构的OrganizationCode查询组织机构的信息
	 */
	public Organization getOrganizationByOrganizationCode(
			String OrganizationCode);

	/**
	 * 
	 * @Title: getOrganizationByIdAndDictName
	 * @Description: TODO根据组织机构id，字典项名称，查询组织机构
	 * @param @param id
	 * @param @param domainName
	 * @param @param dictName
	 * @param @return 设定文件
	 * @return Organization 返回类型
	 * @author wanggz
	 * @date 2014-8-29 上午10:46:16
	 */
	public Organization getOrganizationByIdAndDictName(Long id,
			String domainName, String dictName);

	/**
	 * 查询用户所有关联的组织机构id，对上一条线，对下是所有下辖 startLevelId 代表对上的起点例如四川 那么就是对上到四川为止
	 * 如武胜县用户就是找到 广安市 四川省 和武胜县 武胜县所有下辖的组织机构id
	 * */
	public List<Long> findAllRelatedOrgIdsByUserOrgId(int startLevelId);

	/**
	 * 按照网格类型，组织机构编码查询所有下辖的组织结构
	 * 如查询四川省的行政部门，则查询出四川下辖的市、县、乡镇所有行政部门也包括四川省自己但是不包含网格。
	 * 
	 * @param OrgInternalCode
	 * @param orgTypeInternalId
	 * @return
	 */
	public List<Organization> findOrgsByParentOrgAndOrgTypeInternalId(
			String OrgInternalCode, Integer orgTypeInternalId);

	/***
	 * 
	 * 根据市级下辖的某一个组织机构id查询所在在市级的组织机构
	 * 
	 * @param id
	 * @return
	 */
	public Organization getCityOrgByAreaOrgId(Long id);

	/**
	 * 根据所属组织机构，往上查询市级的组织机构
	 * 
	 * @param orgId
	 * @return
	 */
	public Organization getCityOrganizationId(Long orgId);

	/**
	 * 查询所有的组织机构
	 * 
	 * @return
	 */
	public List<Organization> findAllOrganization();

	/**
	 * @Description: 通过组织机构名称和父级code查询组织机构列表
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param string
	 * @param string2
	 * @param i
	 * @param j
	 * @return
	 * @throws
	 */
	public List<Organization> findOrgByOrgNameAndInternalCode(String orgName,
			String orgInternalCode, int pageNum, int pageSize);

	public List<Long> findLeafOrgIdByCode(String orgCode);

	/***
	 * 根据组织机构类别以及组织机构层级查询所有网格层级以上的行政部门组织机构ID
	 */
	public List<Long> getOrganizationByOrgLevelAndOrgType(Long orgLevel,
			Long orgType);

	public List<Organization> findAllOrgByParentOrgCode(String orgCode,
			String orgType);

	public List<Long> findOrganizationsByParentIdAndType(Long orgId,
			int orgTypeId);

	// add by bing 2014年11月12日 下午6:04:30

	public List<Long> findOrganIdForLevelExcludeGrid(Long orgLevelId,
			int taskItemNum, List<Long> idModList, int fetchNum, int year,
			int month, String targetIssueTable);

	// add by bing 2014年11月12日 下午6:04:30

	/**
	 * 三本台账时限成绩分批次查询各个组织机构的id包括职能部门的但是不包括中国成绩(可根据表去对应不同的表不仅仅只适用三本台账时限成绩)
	 * 
	 * @param idModList
	 * @param pageSize
	 * @param taskParameter
	 * @param tableName
	 * @return
	 */

	public List<Long> queryOrgIdsByModelForStatisticsAccountTimeouts(
			List<Long> idModList, String taskParameter, Integer pageSize,
			String tableName);

	/**
	 * 三本台账报表取模后一次查出所有的orgId（分多线程处理）
	 * 
	 * @param idModList
	 * @param taskParameter
	 * @param orgLevelInternalId
	 * @param orgTypeInternalId
	 * @return
	 */
	public List<Long> queryOrgIdsByModelForAccountReport(List<Long> idModList,
			String taskParameter, int orgLevelInternalId, int orgTypeInternalId);

	/**
	 * 根据分发过来的取模的参数去统计有多少记录（用于分页操作）
	 * 
	 * @param idModList
	 * @param taskParameter
	 * @return
	 */
	public Integer countOrgIdsByModelForStatisticsAccountTimeouts(
			List<Long> idModList, String taskParameter);

	/************************ 组织机构迁移合并方法 *******************************/
	public Organization updateOrgSubCount(Long id, int num);

	public int updateAllOrgSubCount(Long id);

	public void updateOrgSeqAndParentId(Long id, Long seq, Long parentId);

	public Integer getMaxCodeById(Long id);

	public void updateOrgInternalCode(Long id, String newOrgCode);

	public Integer findChildrenMaxSeqByParentId(Long parentId);

	public List<String> getDepartmentnosByParentOrgId(Long parentId);

	public void editChildOrganizationDeptNo(String oldDeptNo, String newDeptNo);

	public List<Long> findOrgIdByParentId(Long parentId);

	public Organization getOrgForFirstCity(Long userOrgId);

	public Organization findTownOrgInfoByOrgId(Long orgId);

	/**
	 * 根据搜索条件查询orgId集合
	 * 
	 * @param searchVo
	 * @return
	 */
	public List<Long> findOrgIdsBySearchVo(OrganizationVo searchVo);

	/**
	 * 根据搜索条件查询org集合
	 * 
	 * @param searchVo
	 * @return
	 */
	public List<Organization> findOrgsBySearchVo(OrganizationVo searchVo);

	/**
	 * 根据搜索条件查询orgName和remark集合
	 * 
	 * @param searchVo
	 * @return
	 */
	public List<Organization> findNameAndRemarkBySearchVo(OrganizationVo searchVo);

	/**
	 * 根据搜索条件统计数量
	 * 
	 * @param searchVo
	 * @return
	 */
	public Integer countOrgsBySearchVo(OrganizationVo searchVo);

	/**
	 * 根据orgId 取得省级 orgCode
	 * 
	 * @param searchVo
	 * @return
	 */
	public String getUserOrganztionCodeByOrgId(Long orgId);

	/**
	 * 根据条件查询各层级组织机构数量
	 * 
	 * @param searchVo
	 * @return
	 */
	public List<Integer> getViewObjectDefNum(List<Map<String, Object>> list);

	/**
	 * 根据网格分级 和网格类型 组织机构code查询组织机构ID
	 * 
	 * @param searchVo
	 * @return
	 */
	public List<Long> getOrgIdsWhenSelectByLevel(
			List<Map<String, Object>> selectedLevelList);

	/**
	 * 根据搜索条件查询orgName集合
	 * 
	 * @param searchVo
	 * @return
	 */
	public List<String> getSelectedOrgNamesByOrgIdsAndTypeLevel(
			OrganizationVo organizationVo);

	
	/**
	 * 根据所属组织机构，查询指定层级的组织机构
	 * 
	 * @param orgId
	 * @param organizationLevel
	 *            (OrganizationType的层级)
	 * @return
	 */
	public List<Organization> findProvinceOrganizationsByOrgIdAndCityLevel(
			Long orgId, Integer organizationLevel);

	/**
	 * 根据组织机构ID和orgLevel
	 * @param orgId
	 * @param internalId
	 * @return
	 */
	public Organization getOrgByOrgIdAndOrgLevelInternalId(Long orgId, Integer internalId);

	public Integer countOrgByOrgIdsListAndResidentStatusDict(
			OrganizationVo searchVo);
	/**
	 * 根据orgTypeId orgLevelId orgName 查询组织机构
	 * 
	 * @param orgTypeId
	 * @param orgLevelId
	 * @param orgName
	 * @return
	 */
	public Organization findOrganizationByOrgTypeAndOrgLevelAndOrgName(
			Long orgTypeId, Long orgLevelId, String orgName,String fullOrgName);
	/**
	 * 获取网格总数
	 * @return
	 */
	public Long getOrganizationforLevelWithGridTotal();
	
	// ==================================================中江三本台账所需接口=======================================================//
	/***
	 * 根据部门编号和组织机构父ID查询组织机构信息 departmentNo 部门编号 parentId 组织机构父ID
	 * 数据量：当前数据量约为10条一下
	 */
	public List<Organization> findByDepartmentNoAndParentId(
			String departmentNo, Long parentId);

	/***
	 * 台账处理时，根据用户输入的关键字查询台账的主办单位
	 * 
	 * @param orgType
	 *            组织机构类别
	 * @param parentId
	 *            父ID
	 * @param tag
	 *            关键字
	 * @param exceptIds
	 *            不需要查询出来组织机构ID
	 * @param exceptDeptNos
	 *            不需要查询的组织机构部门编号
	 * @param page
	 *            页码
	 * @param rows
	 *            每页显示条数
	 * @return
	 */
	public PageInfo<AutoCompleteData> findChildOrgsByParentIdAndName(
			PropertyDict orgType, Long parentId, String tag, Long[] exceptIds,
			String exceptDeptNos[], int page, int rows);

	/**
	 * 根据parentfunorgid,名字查询组织机构
	 * 
	 * @param parentId
	 *            职能部门的父Id
	 * @param tag
	 *            关键字
	 * @param exceptIds
	 *            不需要查询出来的组织机构ID
	 * @return
	 */
//	public List<AutoCompleteData> findChildFunOrgsByParentFunIdAndName(
//			Long parentId, String orgName, Long[] exceptIds);

	/***
	 * 根据组织机构职能部门的父ID和名称查询组织机构信息
	 * 
	 * @param id
	 *            组织机构ID
	 * @param tag
	 *            关键字
	 * @param exceptIds
	 *            不需要查询的组织机构ID
	 * @return
	 */
//	public List<AutoCompleteData> findParentFunOrgsByIdAndName(Long id,
//			String tag, Long[] exceptIds);
	
	/***
	 * 根据组织机构层级和组织机构code查询组织机构信息
	 * @param orgCode 
	 * @param orgLevel
	 * @param isUp
	 * 数据量：多条
	 * @return
	 */
	public List<Organization> findFuncOrgInfoByCondition(String orgCode,
			Long orgLevel,Integer isUp);
	
	/**
	 * 根据组织机构id，查询备份表的组织机构
	 * 
	 */
	public Organization getSimpleOrgAllOrganizationById(Long id);

}
