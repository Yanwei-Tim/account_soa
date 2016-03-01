package com.tianque.core.systemLog.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.systemLog.dao.SystemLogDao;
import com.tianque.core.systemLog.domain.SystemLog;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;

@Transactional
@Service("systemLogService")
public class SystemLogServiceImpl extends AbstractBaseService implements
		SystemLogService {

	@Autowired
	private SystemLogDao systemLogDao;

	@Override
	public PageInfo<SystemLog> findAllSystemLogsForPage(String orgInternalCode,
			Date startDate, Date endDate, String userName, int pageNum,
			int pageSize, String sortField, String order) {
		return systemLogDao.findAllSystemLogsForPage(orgInternalCode,
				startDate, endDate, userName, pageNum, pageSize, sortField,
				order);
	}

	@Override
	public SystemLog log(String operation,Integer operationType, String moduleName, int logLevel,
			String userName, String clientIp, String orgInternalCode) {
		return this.log(operation,operationType, moduleName, logLevel, userName, clientIp, orgInternalCode, "");
	}

	private SystemLog log(String operation,Integer operationType, String moduleName, int logLevel,
			String userName, String clientIp, String orgInternalCode, String operationContext) {
		if (operation == null || "".equals(operation.trim())) {
			throw new ServiceException("操作字符串不能为空");
		}
		if (moduleName == null || "".equals(moduleName.trim())) {
			throw new ServiceException("模块名不能为空");
		}
		SystemLog log = createSystemLog(operation,operationType, moduleName, logLevel,
				userName, clientIp, orgInternalCode, operationContext);
//		Organization organization = new Organization();
//		if(userName!=null && !"".equals(userName)){
//			User user = permissionService.findUserByUserName(userName);
//			if(user!=null){
//				organization = organizationService.getSimpleOrgById(user.getOrganization().getId());
//			}
//		}
//		log.setOrganization(organization);
		return this.systemLogDao.addSystemLog(log);
	}

	private SystemLog createSystemLog(String operation,Integer operationType, String moduleName,
			int logLevel, String userName, String clientIp,
			String orgInternalCode, String operationContext) {
		SystemLog log = new SystemLog();
		log.setLogLevel(logLevel);
		log.setOperateTime(Calendar.getInstance().getTime());
		log.setOperation(operation);
		log.setOrgInternalCode(orgInternalCode);
		log.setOperationType(operationType);
		log.setModuleName(moduleName);
		if(ThreadVariable.getSession()!=null&&ThreadVariable.getSession().getUserId()!=null){
			log.setUserName(ThreadVariable.getSession().getUserName());
		}

		log.setClientIp(clientIp);
		log.setOperationContent(operationContext);
		return log;
	}

	@Override
	public SystemLog log(String operation, String moduleName,Integer operationType) {
		return this.log(SystemLog.INFO, moduleName,operationType, operation, "");
	}

	@Override
	public SystemLog log(String operation, String moduleName,Integer operationType ,int logLevel,
			String clientIp) {
		String userName = "";
		String orgInternalCode = "";
		if (ThreadVariable.getSession() != null) {
			userName = ThreadVariable.getSession().getUserName();
			orgInternalCode = ThreadVariable.getSession().getOrgInternalCode();
		}
		return this.log(operation,operationType, moduleName, logLevel, userName, clientIp,
				orgInternalCode);
	}

	@Override
	public SystemLog log(int logLevel, String moduleName,Integer operationType, String operation,
			String operateContent) {
		String userName = "";
		String clientIp = "";
		String orgInternalCode = "";
		if (ThreadVariable.getSession() != null) {
			userName = ThreadVariable.getSession().getUserName();
			clientIp = ThreadVariable.getSession().getAccessIp();
			orgInternalCode =ThreadVariable.getSession().getOrgInternalCode();
		}
		return this.log(operation,operationType, moduleName, logLevel, userName, clientIp, orgInternalCode, operateContent);
	}

	@Override
	public PageInfo<SystemLog> findSuccessSystemLogsForPage(
			String orgInternalCode, Date startDate, Date endDate,
			String userName, int pageNum, int pageSize, String sortField,
			String order) {
		return systemLogDao.findSuccessSystemLogsForPage(orgInternalCode, startDate,
				endDate, userName, pageNum, pageSize, sortField, order);
	}

	@Override
	public PageInfo<SystemLog> findFailSystemLogsForPage(Date startDate,
			Date endDate, int pageNum, int pageSize, String sortField,
			String order) {
		return systemLogDao.findFailSystemLogsForPage(startDate, endDate,
				pageNum, pageSize, sortField, order);
	}

	@Override
	public PageInfo<SystemLog> findAllSystemLogsForPagebyQuery(String OrgCode,
			Date startDate, Date endDate, String modelName,
			Integer operationType, String operationUserName, Integer pageNum,
			Integer pageSize, String sidx, String sord) {

		return systemLogDao.findAllSystemLogsForPagebyQuery(OrgCode, startDate, endDate, modelName, operationType, operationUserName, pageNum, pageSize, sidx, sord);
	}

	@Override
	public PageInfo<SystemLog> findSystemLogByOrgCode(String OrgCode,
			Date startDate, Date endDate, Integer pageNum, Integer pageSize,
			String sidx, String sord) {

		return systemLogDao.findAllSystemLogsForPage(OrgCode, startDate, endDate, pageNum, pageSize, sidx, sord);
	}

	@Override
	public void archiveSystemLogs() {
		Calendar can = Calendar.getInstance();
		can.add(Calendar.MONTH, -2);
		Date startDate = CalendarUtil.getMonthStart(can.get(Calendar.YEAR), can.get(Calendar.MONTH)+1);
		can.add(Calendar.MONTH, +1);
		Date endDate = CalendarUtil.getMonthStart(can.get(Calendar.YEAR), can.get(Calendar.MONTH)+1);
		
		systemLogDao.archiveSystemLogsByDateRange(startDate, endDate);
		systemLogDao.deleteSystemLogsByDateRange(startDate, endDate);
	}

	@Override
	public void archiveSystemLogsForOld() {
		Calendar can = Calendar.getInstance();
		can.add(Calendar.MONTH, -2);
		Date startDate = CalendarUtil.getMonthStart(can.get(Calendar.YEAR)-2, can.get(Calendar.MONTH)+1);
		
		can.add(Calendar.MONTH, +1);
		Date endDate = CalendarUtil.getMonthStart(can.get(Calendar.YEAR), can.get(Calendar.MONTH)+1);

		systemLogDao.archiveSystemLogsByDateRange(startDate, endDate);
		systemLogDao.deleteSystemLogsByDateRange(startDate, endDate);
	}
}
