package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.ProclamationDao;
import com.tianque.domain.Proclamation;

@Repository("proclamationDao")
public class ProclamationDaoImpl extends AbstractBaseDao implements
		ProclamationDao {

	@Autowired
	private CacheService cacheService;

	@Override
	public PageInfo findProclamations(Integer page, Integer rows,
			String sortField, String order) {
		Map map = new HashMap();
		if (sortField != null) {
			map.put("sortField", sortField);
			map.put("order", order);
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"proclamation.getCountProclamation", map);
		int pageCount = 0;
		if (rows > 0 && countNum > 0)
			pageCount = (countNum - 1) / rows + 1;
		page = page > pageCount ? pageCount : page;
		List<Proclamation> proclamations = getSqlMapClientTemplate()
				.queryForList("proclamation.findProclamations", map,
						(page - 1) * rows, rows);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setResult(proclamations);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		pageInfo.setTotalRowSize(countNum);
		return pageInfo;
	}

	@Override
	public Proclamation getProclamationById(Long id) {
		return (Proclamation) getSqlMapClientTemplate().queryForObject(
				"proclamation.getProclamationById", id);
	}

	@Override
	public Proclamation addProclamation(Proclamation proclamation) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"proclamation.addProclamation", proclamation);
		proclamation = getProclamationById(id);
		if (proclamation.isDisplay()) {
			cacheService.remove("getDisplayProclamation");
			cacheService.remove("getDisplayProclamationForMobile");
		}
		return proclamation;
	}

	@Override
	public Proclamation updateProclamation(Proclamation proclamation) {
		getSqlMapClientTemplate().update("proclamation.updateProclamation",
				proclamation);
		proclamation = getProclamationById(proclamation.getId());
		cacheService.remove("getDisplayProclamation");
		cacheService.remove("getDisplayProclamationForMobile");
		return proclamation;
	}

	@Override
	public Proclamation getDisplayProclamation() {
		Proclamation proclamation = (Proclamation) cacheService
				.get("getDisplayProclamation");
		if (null == proclamation) {
			proclamation = (Proclamation) getSqlMapClientTemplate()
					.queryForObject("proclamation.getDisplayProclamation");
			// if (null == proclamation) {
			// proclamation = new Proclamation();
			// }
			cacheService.set("getDisplayProclamation", proclamation);
		}
		return proclamation;
	}

	/**
	 * 为手机端新增查询系统公告方法
	 */
	public Proclamation getDisplayProclamationForMobile() {
		Proclamation proclamation = null;
		proclamation = (Proclamation) getSqlMapClientTemplate().queryForObject(
				"proclamation.getDisplayProclamationForMobile");
		return proclamation;
	}

	@Override
	public boolean updateDisplay() {
		getSqlMapClientTemplate().update("proclamation.updateDisplay");
		cacheService.remove("getDisplayProclamation");
		cacheService.remove("getDisplayProclamationForMobile");
		return true;
	}

}
