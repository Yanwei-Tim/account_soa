package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.exception.DAOException;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.MyContacterDao;
import com.tianque.domain.MyContacter;

@Repository("myContacterDao")
public class MyContacterDaoImpl extends AbstractBaseDao implements MyContacterDao {

	@Override
	public MyContacter addMyContacter(MyContacter myContact) {
		if (!validate(myContact))
			throw new DAOException();
		Long id = (Long) getSqlMapClientTemplate().insert(
				"myContacter.addMyContacter", myContact);
		return this.getSimpleMyContacterById(id);
	}

	@Override
	public void deleteMyContacterById(Long id) {
		getSqlMapClientTemplate().delete("myContacter.deleteMyContacterById",
				id);
	}

	@Override
	public MyContacter getSimpleMyContacterById(Long id) {
		return (MyContacter) getSqlMapClientTemplate().queryForObject(
				"myContacter.getSimpleMyContacterById", id);
	}

	@Override
	public MyContacter updateMyContacter(MyContacter myContact) {
		if (!validate(myContact))
			throw new DAOException();
		getSqlMapClientTemplate().update("myContacter.updateMyContacter",
				myContact);
		return getSimpleMyContacterById(myContact.getId());
	}

	private boolean validate(MyContacter myContact) {
		if (myContact == null)
			return false;
		if (myContact.getName() == null
				|| "".equals(myContact.getName().trim()))
			return false;
		if (myContact.getMobileNumber() == null
				|| "".equals(myContact.getMobileNumber().trim()))
			return false;
		if (myContact.getOwner() == null)
			return false;
		if (myContact.getBelongClass() == null
				|| "".equals(myContact.getBelongClass().trim()))
			return false;
		return true;
	}

	@Override
	public PageInfo<MyContacter> findMyContacterByOwnerIdForPage(Long ownerId,
			Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ownerId", ownerId);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("belongClass", MyContacter.MYCONTACTER);
		return exeQuery(page, rows, map);
	}

	@Override
	public PageInfo<MyContacter> searchMyContacter(MyContacter myContact,
			Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", myContact.getName());
		map.put("mobileNumber", myContact.getMobileNumber());
		map.put("ownerId", myContact.getOwner().getId());
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("belongClass", MyContacter.MYCONTACTER);
		return exeQuery(page, rows, map);
	}

	private PageInfo<MyContacter> exeQuery(Integer page, Integer rows,
			Map<String, Object> map) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"myContacter.countMyContacters", map);
		int pageCount = 0;
		if (rows != 0 && countNum > 0)
			pageCount = (countNum - 1) / rows + 1;
		page = page > pageCount ? pageCount : page;
		List<MyContacter> list = getSqlMapClientTemplate().queryForList(
				"myContacter.findMyContacters", map, (page - 1) * rows, rows);
		PageInfo<MyContacter> pageInfo = new PageInfo<MyContacter>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public List<MyContacter> findMyContacterByOwnerId(Long ownerId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ownerId", ownerId);
		map.put("belongClass", MyContacter.MYCONTACTER);
		return getSqlMapClientTemplate().queryForList(
				"myContacter.findMyContacters", map);
	}

	@Override
	public List<MyContacter> findMyContactersByNameAndPinyinAndOwnerId(
			String name, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tagName", name);
		map.put("ownerId", id);
		map.put("belongClass", MyContacter.MYCONTACTER);
		return getSqlMapClientTemplate().queryForList(
				"myContacter.findMyContactersByNameAndPinyinAndOwnerId", map);
	}

	@Override
	public Integer getMyContacterByIdInMyGroup(Long id) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("id", id);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"myContacter.getMyContacterByIdInMyGroup", id);
	}

}
