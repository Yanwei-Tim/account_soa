package com.tianque.userAuth.api;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Contacter;
import com.tianque.domain.MyArea;
import com.tianque.domain.MyContacter;
import com.tianque.domain.MyGroup;
import com.tianque.domain.Organization;
import com.tianque.domain.WorkContacter;
import com.tianque.domain.vo.ContacterVo;

public interface ContacterDubboService {
	/**
	 * myContacter
	 * 
	 * @param myContact
	 * @return
	 */
	public MyContacter addMyContacter(MyContacter myContact);

	public MyContacter updateMyContacter(MyContacter myContact);

	public boolean deleteContacterById(Long id);

	public MyContacter getSimpleMyContacterById(Long id);

	public List<MyContacter> findMyContactersByNameAndPinyinAndOwnerId(
			String name, Long id);

	public PageInfo<MyContacter> findMyContacterByOwnerId(Long ownerId,
			Integer page, Integer rows, String sidx, String sord);

	public PageInfo<MyContacter> searchMyContacter(MyContacter myContact,
			Integer page, Integer rows, String sidx, String sord);

	public List<MyContacter> findMyContacterByOwnerId(Long ownerId);

	/**
	 * workContacter
	 * 
	 * @param workContacter
	 * @return
	 */
	public WorkContacter addWorkContacter(WorkContacter workContacter);

	public WorkContacter updateWorkContacter(WorkContacter workContacter);

	public WorkContacter getSimpleWorkContacterByUserId(Long userId);

	public List<WorkContacter> findWorkContacter();

	public PageInfo<WorkContacter> findWorkContacterForUpate(Integer page,
			Integer rows, String sidx, String sord, Organization organization,
			String leavel);

	public List<WorkContacter> findWorkContactersByNameAndPinyin(String name);

	public ContacterVo getSimpleContacterById(Long id);

	public List<WorkContacter> findWorkContactersByOrganizationId(Long id);

	public List<MyArea> findMyAreaByOrgIdAndOrgLevelAndOrgType(
			Map<Long, List<Integer>> map, List<Integer> orgTypeInternals,
			List<Long> exceptOrgIds);

	public WorkContacter getWorkContactersByName(String userName);

	public WorkContacter getWorkContacterById(Long id);
	
public MyGroup addMyGroup(MyGroup myGroup);
	
	public List<MyGroup> findMyGroupByOwnerId(Long id);
	
	public Contacter findUserContacters(Long fromUserId);
	
	public PageInfo<MyGroup> findMyGroupByOwnerId(Long id, Integer page,
			Integer rows, String sidx, String sord);
	
	public MyGroup getSimpleMyGroupById(Long id);
	
	public List<MyGroup> findMyGroupsByNameAndPinyinAndOwnerId(String name,
			Long id);
	
	public MyGroup updateMyGroup(MyGroup myGroup);
	
	public List<ContacterVo> findMyGroupHasContactersByGroupId(Long id);
	
	public List<WorkContacter> findWorkContacterById(Long id);
	
	public void addMyGroupHasContacter(Long groupId, Long contacterId);
	
	public PageInfo<ContacterVo> findMyGroupHasContactersByGroupId(Long id,
			String belongClass, String name, Integer page, Integer rows,
			String sidx, String sord);
	
	public void deleteMyGroupHasAllContacterByGroupId(Long groupId);
	
	public void deleteMyGroupHasSingleContacterByTwoId(Long groupId,
			Long contacterId);
	
	/**
	 * 我的群组高级查询
	 */
	public PageInfo<MyGroup> findMyGroupByCondition(Long groupId,
			ContacterVo contacterVo, String belongClass, Integer page,
			Integer rows, String sidx, String sord);
	
	/** 查询我的群组下所有的联系人信息 */
	public List<MyGroup> findMygroupMemberByGroupId(Long groupId);
	
	/***
	 * 获取群组的成员数量
	 */
	public Integer getGroupMemberNum(Long id);
	
	/***
	 * 修改群组成员数量
	 */
	public void updateGroupMemberNumber(Long Id, Integer number);
	
	/***
	 * 批量删除群组
	 */
	public void deleteAllMyGroup(String[] ids);
	
	public PageInfo<WorkContacter> searchUsersByWorkContacter(
			ContacterVo contacterVo, Boolean isHasOrg, Integer page,
			Integer rows, String sidx, String sord);
}
