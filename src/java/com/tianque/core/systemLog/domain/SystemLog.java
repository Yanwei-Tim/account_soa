package com.tianque.core.systemLog.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.logger.Logger;
import com.tianque.domain.Organization;


/**
 * @systemLogs类描述 系统操作日志实体类。
 */
public class SystemLog extends BaseDomain implements Logger{
	private static final long serialVersionUID = 1L;

	// 持久化字段
	/**
	 * 操作
	 */
	private Integer operationType;

	private String operation;
	/**
	 * 操作内容
	 */
	private String operationContent;

	/**
	 * 操作时间
	 */
	private Date operateTime;
	/**
	 * 模块名
	 */
	private String moduleName;
	/**
	 * 用户名
	 */
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 日志等级
	 */
	private Integer logLevel;
	/**
	 * 客户IP
	 */
	private String clientIp;
	/**
	 * 组织机构内置属性
	 */
	private String orgInternalCode;
	/** 所属责任区 */
	private Organization organization;
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}



	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}



	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getModuleName() {
		return moduleName;
	}

	public Integer getOperationType() {
		return operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}












//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}



	public Integer getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(Integer logLevel) {
		this.logLevel = logLevel;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	/**
	 * @deprecated Use {@link #getOperationContent()} instead
	 */
	public String getOperationContext() {
		return getOperationContent();
	}

	public String getOperationContent() {
		return operationContent;
	}

	/**
	 * @deprecated Use {@link #setOperationContent(String)} instead
	 */
	public void setOperationContext(String operationContext) {
		setOperationContent(operationContext);
	}

	public void setOperationContent(String operationContext) {
		this.operationContent = operationContext;
	}

}
