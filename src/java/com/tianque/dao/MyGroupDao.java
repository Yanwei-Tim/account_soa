package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.MyGroup;
import com.tianque.domain.WorkContacter;
import com.tianque.domain.vo.ContacterVo;

public interface MyGroupDao {

	public MyGroup addMyGroup(MyGroup myGroup);

	public MyGroup updateMyGroup(MyGroup myGroup);

	
	
	public boolean isMyGroupById(Long id);

	public MyGroup getSimpleMyGroupById(Long id);

	

	public List<MyGroup> findMyGroupByOwnerId(Long id);

	public void addMyGroupHasContacter(Long groupId, Long contacterId);

	public PageInfo<ContacterVo> findMyGroupHasContactersByGroupId(Long id,
			String belongClass, Integer page, Integer rows, String sidx, String sord);
	
	public List<ContacterVo> findMyGroupHasContactersByGroupId(Long id);
	
	public List<WorkContacter> findMyGroupHasWorkContactersByGroupId(Long id);
	
	public void deleteMyGroupHasAllContacterByGroupId(Long groupId);
	
	public void deleteMyGroupHasSingleContacterByTwoId(Long groupId,Long contacterId);
	
	public List<MyGroup> findMyGroupsByNameAndPinyinAndOwnerId(String name,Long id);
	
}
