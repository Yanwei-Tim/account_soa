package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.exception.DAOException;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.WorkContacterDao;
import com.tianque.domain.WorkContacter;
import com.tianque.domain.vo.ContacterVo;

@Repository("workContacterDao")
public class WorkContacterDaoImpl extends AbstractBaseDao implements WorkContacterDao {

	@Override
	public WorkContacter addWorkContacter(WorkContacter workContacter) {
		if (!validate(workContacter))
			throw new DAOException();
		Long id = (Long) getSqlMapClientTemplate().insert(
				"workContacter.addWorkContacter", workContacter);
		return this.getSimpleWorkContacterById(id);
	}

	

	@Override
	public WorkContacter getSimpleWorkContacterById(Long id) {
		return (WorkContacter) getSqlMapClientTemplate().queryForObject(
				"workContacter.getSimpleWorkContacterById", id);
	}

	public ContacterVo getSimpleContacterById(Long id) {
		return (ContacterVo) getSqlMapClientTemplate().queryForObject(
				"workContacter.getSimpleContacterById", id);
	}

	@Override
	public WorkContacter updateWorkContacter(WorkContacter workContacter) {
		if (!validate(workContacter))
			throw new DAOException();
		getSqlMapClientTemplate().update("workContacter.updateWorkContacter",
				workContacter);
		return this.getSimpleWorkContacterById(workContacter.getId());
	}

	private boolean validate(WorkContacter workContacter) {
		if (workContacter == null)
			return false;
		if (workContacter.getName() == null
				|| "".equals(workContacter.getName().trim()))
			return false;
		if (workContacter.getMobileNumber() == null
				|| "".equals(workContacter.getMobileNumber().trim()))
			return false;
		if (workContacter.getFromUser() == null)
			return false;
		if (workContacter.getBelongClass() == null
				|| "".equals(workContacter.getBelongClass().trim()))
			return false;
		return true;
	}

	@Override
	public PageInfo<WorkContacter> findWorkContacterForPage(Integer page,
			Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("belongClass", WorkContacter.WORKCONTACTER);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"workContacter.countWorkContacters", map);
		int pageCount = 0;
		if (rows != 0 && countNum > 0)
			pageCount = (countNum - 1) / rows + 1;
		page = page > pageCount ? pageCount : page;
		List<WorkContacter> list = getSqlMapClientTemplate().queryForList(
				"workContacter.findFullWorkContacters", map, (page - 1) * rows,
				rows);
		PageInfo<WorkContacter> pageInfo = new PageInfo<WorkContacter>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public List<WorkContacter> findWorkContacter() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("belongClass", WorkContacter.WORKCONTACTER);
		return getSqlMapClientTemplate().queryForList(
				"workContacter.findWorkContacters", map);
	}

	@Override
	public WorkContacter getSimpleWorkContacterByUserId(Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fromUserId", userId);
		map.put("belongClass", WorkContacter.WORKCONTACTER);
		return (WorkContacter) getSqlMapClientTemplate().queryForObject(
				"workContacter.findWorkContacters", map);
	}

	@Override
	public List<WorkContacter> findWorkContactersByNameAndPinyin(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tagName", name);
		map.put("belongClass", WorkContacter.WORKCONTACTER);
		return getSqlMapClientTemplate().queryForList(
				"workContacter.findWorkContactersByNameAndPinyin", map);
	}

	@Override
	public List<WorkContacter> findWorkContactersByOrganizationId(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("organizationId", id);
		map.put("belongClass", WorkContacter.WORKCONTACTER);
		return getSqlMapClientTemplate().queryForList(
				"workContacter.findWorkContactersByOrganizationId", map);
	}

	
}
