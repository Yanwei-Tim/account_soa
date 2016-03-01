package com.tianque.core.systemLog.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.exception.DAOException;
import com.tianque.core.systemLog.domain.SystemLog;
import com.tianque.core.vo.PageInfo;


/**
 * 系统操作日志数据库操作实现类。
 */
@Repository("systemLogDao")
public class SystemLogDaoImpl extends AbstractBaseDao implements SystemLogDao {

	@Override
	public SystemLog addSystemLog(SystemLog systemLog) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"systemLog.addSystemLog", systemLog);
		return this.getSystemLogById(id);
	}

	@Override
	public SystemLog getSystemLogById(Long id) {
		return (SystemLog) getSqlMapClientTemplate().queryForObject(
				"systemLog.getSystemLogById", id);
	}

	/**
	 * 查询所有的系统日志
	 */
	@Override
	public PageInfo<SystemLog> findAllSystemLogsForPage(String orgInternalCode,
			Date startDate, Date endDate, String userName, int pageNum,
			int pageSize, String sortField, String order) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("userName", userName);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"systemLog.countAllSystemlogsByQueryBuilder", map);

		if (isStrotFieldAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);

		List<SystemLog> list = getSqlMapClientTemplate().queryForList(
				"systemLog.findSystemlogsByQueryBuilder", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<SystemLog> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List<SystemLog> list) {
		PageInfo<SystemLog> pageInfo = new PageInfo<SystemLog>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	/**
	 * 查询登录成功的系统日志
	 */
	@Override
	public PageInfo<SystemLog> findSuccessSystemLogsForPage(
			String orgInternalCode, Date startDate, Date endDate,
			String userName, int pageNum, int pageSize, String sortField,
			String order) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("userName", userName);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"systemLog.countSystemlogsByQueryBuilder", map);

		if (isStrotFieldAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);

		List list = getSqlMapClientTemplate().queryForList(
				"systemLog.findSuccessSystemlogsByQueryBuilder", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	/**
	 * 查询登录失败的系统日志
	 */
	@Override
	public PageInfo<SystemLog> findFailSystemLogsForPage(Date startDate, Date endDate,
			int pageNum,int pageSize, String sortField, String order) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);

	    Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"systemLog.countFailSystemlogsByQueryBuilder", map);

		if (isStrotFieldAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);

		List list = getSqlMapClientTemplate().queryForList(
				"systemLog.findFailSystemlogsByQueryBuilder", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo<SystemLog> findAllSystemLogsForPagebyQuery(String orgInternalCode,
			Date startDate, Date endDate, String moduleName,
			Integer operationType, String operationUserName, int pageNum,
			int pageSize, String sortField, String order) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("moduleName", moduleName);
		map.put("operationType", operationType);
		map.put("userName", operationUserName);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"systemLog.countAllSystemhighlogsbyQuery", map);

		if (isStrotFieldAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		List list = getSqlMapClientTemplate().queryForList(
				"systemLog.findAllSystemLogsForPagebyQuery", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);

	}

	@Override
	public PageInfo<SystemLog> findAllSystemLogsForPage(String orgInternalCode,
			Date startDate, Date endDate, int pageNum, int pageSize,
			String sortField, String order) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"systemLog.countAllSystemhighlogs", map);

		if (isStrotFieldAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		List<SystemLog> list = getSqlMapClientTemplate().queryForList(
				"systemLog.findAllSystemhighLogsForPage", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);

	}

	@Override
	public void archiveSystemLogsByDateRange(Date startDate, Date endDate) {
		if(startDate == null || endDate == null){
			throw new DAOException("为指定明确的时间区间");
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		
		getSqlMapClientTemplate().insert("systemLog.archiveSystemLogsByDateRange", map);
	}

	@Override
	public int deleteSystemLogsByDateRange(Date startDate, Date endDate) {
		if(startDate == null || endDate == null){
			throw new DAOException("未指定明确的时间区间");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		
		return getSqlMapClientTemplate().delete("systemLog.deleteSystemLogsByDateRange", map);
	}

}
