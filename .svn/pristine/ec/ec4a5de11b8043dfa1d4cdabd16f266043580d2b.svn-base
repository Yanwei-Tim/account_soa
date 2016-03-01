package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.exception.DAOException;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.MyGroupDao;
import com.tianque.domain.MyGroup;
import com.tianque.domain.WorkContacter;
import com.tianque.domain.vo.ContacterVo;

@Repository("myGroupDao")
public class MyGroupDaoImpl extends AbstractBaseDao implements MyGroupDao {

	@Override
	public MyGroup addMyGroup(MyGroup myGroup) {
		if (!validate(myGroup))
			throw new DAOException();
		Long id = (Long) getSqlMapClientTemplate().insert("myGroup.addMyGroup",
				myGroup);
		return getSimpleMyGroupById(id);
	}

	

	@Override
	public List<MyGroup> findMyGroupByOwnerId(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ownerId", id);
		map.put("belongClass", MyGroup.MYGROUP);
		map.put("sortField", "id");
		map.put("order", "asc");
		return getSqlMapClientTemplate().queryForList("myGroup.findMyGroups",
				map);
	}

	

	@Override
	public MyGroup getSimpleMyGroupById(Long id) {
		return (MyGroup) getSqlMapClientTemplate().queryForObject(
				"myGroup.getSimpleMyGroupById", id);
	}
	
	@Override
	public List<WorkContacter> findMyGroupHasWorkContactersByGroupId(Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", id);
		map.put("belongClass", WorkContacter.WORKCONTACTER);
		return getSqlMapClientTemplate().queryForList("myGroup.findMyGroupHasWorkContacters",
				map);
	}

	@Override
	public MyGroup updateMyGroup(MyGroup myGroup) {
		if (!validate(myGroup))
			throw new DAOException();
		getSqlMapClientTemplate().update("myGroup.updateMyGroup", myGroup);
		return this.getSimpleMyGroupById(myGroup.getId());
	}

	private boolean validate(MyGroup myGroup) {
		if (myGroup == null)
			return false;
		if (myGroup.getName() == null || "".equals(myGroup.getName().trim()))
			return false;
		if (myGroup.getOwner() == null || myGroup.getOwner().getId() == null)
			return false;
		if (myGroup.getBelongClass() == null
				|| "".equals(myGroup.getBelongClass().trim()))
			return false;
		return true;
	}

	@Override
	public void addMyGroupHasContacter(Long groupId, Long contacterId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", groupId);
		map.put("contacterId", contacterId);
		getSqlMapClientTemplate().insert("myGroup.addMyGroupHasContacter", map);
	}

	@Override
	public PageInfo<ContacterVo> findMyGroupHasContactersByGroupId(Long id,
			String belongClass, Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", id);
		map.put("belongClass", belongClass);
		map.put("sortField", sidx);
		map.put("order", sord);
		
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"myGroup.countMyGroupHasContacters", map);
		int pageCount = 0;
		if (rows != 0 && countNum > 0)
			pageCount = (countNum - 1) / rows + 1;
		page = page > pageCount ? pageCount : page;
		List<ContacterVo> list = getSqlMapClientTemplate().queryForList(
				"myGroup.findMyGroupHasContacters", map, (page - 1) * rows, rows);
		PageInfo<ContacterVo> pageInfo = new PageInfo<ContacterVo>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public void deleteMyGroupHasAllContacterByGroupId(Long groupId) {
		getSqlMapClientTemplate().delete("myGroup.deleteMyGroupHasAllContacter", groupId);
	}

	@Override
	public void deleteMyGroupHasSingleContacterByTwoId(Long groupId,
			Long contacterId) {
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("groupId", groupId);
		map.put("contacterId", contacterId);
		getSqlMapClientTemplate().delete("myGroup.deleteMyGroupHasSingleContacter", map);
		
	}

	@Override
	public List<MyGroup> findMyGroupsByNameAndPinyinAndOwnerId(String name,
			Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tagName", name);
		map.put("ownerId", id);
		map.put("belongClass", MyGroup.MYGROUP);
		return getSqlMapClientTemplate().queryForList("myGroup.findMyGroupsByNameAndPinyinAndOwnerId",
				map);
	}

	@Override
	public List<ContacterVo> findMyGroupHasContactersByGroupId(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", id);
		return  getSqlMapClientTemplate().queryForList(
				"myGroup.findMyGroupHasContacters", map);
	}
	
	public boolean isMyGroupById(Long id){
		if(null!=getSimpleMyGroupById(id))
			return true;
		return false;
	}

}
