package com.tianque.service;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.MyArea;
import com.tianque.domain.MyContacter;
import com.tianque.domain.MyGroup;
import com.tianque.domain.WorkContacter;
import com.tianque.domain.vo.ContacterVo;

public interface ContacterService {

	public MyGroup addMyGroup(MyGroup myGroup);

	public MyGroup updateMyGroup(MyGroup myGroup);

	public MyGroup getSimpleMyGroupById(Long id);
	
	public List<MyGroup> findMyGroupsByNameAndPinyinAndOwnerId(String name,Long id);

	public List<MyGroup> findMyGroupByOwnerId(Long id);

	public void addMyGroupHasContacter(Long groupId, Long contacterId);

	public PageInfo<ContacterVo> findMyGroupHasContactersByGroupId(Long id,
			String belongClass, Integer page, Integer rows, String sidx,
			String sord);
	
	public List<ContacterVo> findMyGroupHasContactersByGroupId(Long id);
	
	public List<WorkContacter> findWorkContacterById(Long id);
	
	public void deleteMyGroupHasAllContacterByGroupId(Long groupId);

	public void deleteMyGroupHasSingleContacterByTwoId(Long groupId,
			Long contacterId);

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

	public List<MyContacter> findMyContactersByNameAndPinyinAndOwnerId(String name,Long id);
	
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
			Integer rows, String sidx, String sord);
	
	public List<WorkContacter> findWorkContactersByNameAndPinyin(String name);
	
	public ContacterVo getSimpleContacterById(Long id);
	
	public List<WorkContacter> findWorkContactersByOrganizationId(Long id);
	public List<MyArea> findMyAreaByOrgIdAndOrgLevelAndOrgType(Map<Long,List<Integer>> map,List<Integer> orgTypeInternals,List<Long> exceptOrgIds);
}
