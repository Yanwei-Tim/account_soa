package com.tianque.core.systemLog.dao;

import java.util.Date;

import com.tianque.core.systemLog.domain.SystemLog;
import com.tianque.core.vo.PageInfo;


/**
 * 系统操作日志数据库操作接口。
 */
public interface SystemLogDao {

	/**
	 * 添加系统日志
	 *
	 * @param systemLogs
	 *            系统日志对象
	 * @return SystemLog 系统日志对象
	 */
	public SystemLog addSystemLog(SystemLog systemLog);

	/**
	 * 根据系统日志ID获取系统日志对象
	 *
	 * @param id
	 *            系统日志ID
	 * @return SystemLog 系统日志对象
	 */
	public SystemLog getSystemLogById(Long id);

	/**
	 * 根据条件分页查询系统日志
	 *
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param userName
	 *            用户名
	 * @param pageNum
	 *            页号
	 * @param pageSize
	 *            每页显示的记录数
	 * @param sortField
	 *            排序字段
	 * @param order
	 *            ASC表示升序排序，DESC表示降序排序
	 * @return PageInfo<SystemLog>
	 */
	public PageInfo<SystemLog> findAllSystemLogsForPage(String orgInternalCode,
			Date startDate, Date endDate, String userName, int pageNum,
			int pageSize, String sortField, String order);
	public PageInfo<SystemLog> findSuccessSystemLogsForPage(String orgInternalCode,
			Date startDate, Date endDate, String userName, int pageNum,
			int pageSize, String sortField, String order);
	public PageInfo<SystemLog> findFailSystemLogsForPage(Date startDate, Date endDate,
			int pageNum,int pageSize, String sortField, String order);


	public PageInfo<SystemLog> findAllSystemLogsForPagebyQuery(String orgInternalCode,Date startDate, Date endDate,String modelName,Integer operationType,String userName, int pageNum,
			int pageSize, String sortField, String order );

	public PageInfo<SystemLog> findAllSystemLogsForPage(String orgInternalCode,Date startDate, Date endDate, int pageNum,
			int pageSize, String sortField, String order );
	
	public void archiveSystemLogsByDateRange(Date startDate, Date endDate);

	public int deleteSystemLogsByDateRange(Date startDate, Date endDate);
}
