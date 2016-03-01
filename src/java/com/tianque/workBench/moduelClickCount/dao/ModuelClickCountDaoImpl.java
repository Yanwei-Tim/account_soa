package com.tianque.workBench.moduelClickCount.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.exception.DAOException;
import com.tianque.workBench.moduelClickCount.domain.ModuelClick;

@Repository("moduelClickCountDao")
public class ModuelClickCountDaoImpl extends AbstractBaseDao implements
		ModuelClickCountDao {

	@Override
	public ModuelClick findModuelClickCountByPermissionIdAndUserId(
			Long permissionId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("permissionId", permissionId);
		map.put("userId", userId);
		ModuelClick moduelClick = new ModuelClick();
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"moduelClicks.countModuelClickCountByPermissionIdAndUserId",
				map);
		if (count == 1) {
			moduelClick = (ModuelClick) getSqlMapClientTemplate()
					.queryForObject(
							"moduelClicks.findModuelClickCountByPermissionIdAndUserId",
							map);
		} else {
			moduelClick = null;
		}
		return moduelClick;
	}

	@Override
	public ModuelClick addModuelClickCount(ModuelClick moduelClick) {
		checkData(moduelClick);
		Long id = (Long) getSqlMapClientTemplate().insert(
				"moduelClicks.addModuelClickCount", moduelClick);
		if (id != null) {
			return getModuelClickById(id);
		} else {
			return null;
		}
	}

	private void checkData(ModuelClick moduelClick) {
		if (null == moduelClick.getUserId()) {
			throw new DAOException("用户信息不能为空");
		}
		if (null == moduelClick.getClickTimes()) {
			throw new DAOException("点击次数不能为空");
		}
		if (null == moduelClick.getPermissionId()) {
			throw new DAOException("权限信息不能为空");
		}
	}

	@Override
	public ModuelClick getModuelClickById(Long id) {
		if (null == id) {
			throw new DAOException("Id不能为空");
		}
		ModuelClick moduelClick = (ModuelClick) getSqlMapClientTemplate()
				.queryForObject("moduelClicks.findModuelClickCountById", id);
		if (moduelClick != null) {
			return moduelClick;
		} else {
			return null;
		}
	}

	@Override
	public ModuelClick updateModuelClickCount(ModuelClick moduelClick) {
		checkData(moduelClick);
		getSqlMapClientTemplate().update("moduelClicks.updateModuelClickCount",
				moduelClick);
		return getModuelClickById(moduelClick.getId());
	}

	@Override
	public List<ModuelClick> findModuelClickCountByUserId(Long userId,
			Integer nums) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("nums", nums);
		return getSqlMapClientTemplate().queryForList(
				"moduelClicks.findModuelClickCountByUserId", map);
	}

}
