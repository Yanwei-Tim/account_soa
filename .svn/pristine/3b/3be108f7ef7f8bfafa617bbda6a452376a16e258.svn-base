package com.tianque.workMemo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.workMemo.domain.WorkMemo;

@Repository("workMemoDao")
public class WorkMemoDaoImpl extends AbstractBaseDao implements WorkMemoDao {

	@Override
	public WorkMemo addWorkMemo(WorkMemo workMemo) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"workMemo.addWorkMemo", workMemo);
		return getWorkMemoById(id);
	}

	public WorkMemo getWorkMemoById(Long id) {
		return (WorkMemo) getSqlMapClientTemplate().queryForObject(
				"workMemo.getWorkMemoById", id);
	}

	@Override
	public void deleteWorkMemoById(Long id) {
		getSqlMapClientTemplate().delete("workMemo.deleteWorkMemoById", id);
	}
	@Override
	public void deleteAllWorkMemoByUserId(Long userId){
		getSqlMapClientTemplate().delete("workMemo.deleteWorkMemoByUserId",userId);
	}

	@Override
	public List<WorkMemo> getMemoContentsByUserIdAndAddMemoDate(Long userId,
			String date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("date", date);
		return getSqlMapClientTemplate().queryForList(
				"workMemo.getMemoContentsByUserIdAndAddMemoDate", map);
	}

	@Override
	public List<String> getDaysByUserIdAndDate(Long userId, String year,
			String month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("year", year);
		map.put("month", month);
		return getSqlMapClientTemplate().queryForList(
				"workMemo.getDaysByUserIdAndDate", map);
	}

}
