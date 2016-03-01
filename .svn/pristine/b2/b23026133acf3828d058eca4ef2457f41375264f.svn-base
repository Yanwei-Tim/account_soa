package com.tianque.plugin.account.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;

public class ThreeRecordsIssueViewObject implements Serializable {
	/** 服务办事Id */
	private Long issueId;
	/** 服务单号 */
	private String serialNumber;
	/** 主题 */
	private String subject;
	/** 状态 */
	private Integer status;
	/** 发生时间 */
	private Date occurDate;
	/** 最后处理部门org显示名称 */
	private String lastOrgDisplayName;
	/** 发生网格 */
	private Organization occurOrg;
	/** 处理时间 */
	private Date dealTime;
	/** 来源人 */
	private String sourcePerson;
	/** 来源方式 */
	private PropertyDict sourceKind;
	/** 来源手机号码 */
	private String sourceMobile;
	private Long issueStepId;
	/** 督办状态 */
	private Integer supervisionState;
	/** 加急 */
	private Long urgent;
	/** 处理状态 * */
	private Long dealState;
	/** 延期状态 ，默认是未申请 */
	private Integer delayState = 0;
	/** 当前处理部门 * */
	private Organization currentOrg;
	/** 目标部门 * */
	private Organization targetOrg;
	/** 目标部门 （最后处理部门） * */
	private Organization lastOrg;
	/** 录入时间 * */
	private Date createDate;
	/** 是否可以反馈 */
	private Integer superviseLevel;
	/** 事件类型 */
	private String domainName;
	/** 评论时间 */
	private Date evaluateTime;
	/** 评论类型 */
	private Integer evaluateType;
	/** 评论内容 */
	private String evaluateContent;
	/** 是否宣传案例（已办事项视图用到） */
	private Integer publicltycass;
	/** 是否置顶 */
	private Integer topState;
	/** 时间发生的小时 */
	private String hours;
	/** 时间发生的分钟 */
	private String minute;

	private List<ThreeRecordsIssueOperate> canDoList;

	private String issueTypes;// 用于显示滚动列表的事件类型

	private Organization createOrg;// 创建网格

	private String createUser;// 创建人

	private Integer earingWarn;

	private String createName;

	/** 事件简述 */
	private String issueContent;
	private Integer deadLine;

	private List<ThreeRecordsIssueAttachFile> attachFilesList;// 附件列表
	private PropertyDict createTableType;

	private Integer earingWarnType;// 亮灯类型，黄灯，绿灯

	private String name;
	private String mobileNumber;
	private String appealContent;

	private String state;

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Integer getSuperviseLevel() {
		return superviseLevel;
	}

	public void setSuperviseLevel(Integer superviseLevel) {
		this.superviseLevel = superviseLevel;
	}

	private ThreeRecordsIssueOperate OperateForSupervision;

	public ThreeRecordsIssueOperate getOperateForSupervision() {
		if (superviseLevel != null && superviseLevel == 200) {
			return ThreeRecordsIssueOperate.allOperates
					.get(ThreeRecordsIssueOperate.RED_SUPERVISE_CODE);
		} else if (superviseLevel != null && superviseLevel == 100) {
			return ThreeRecordsIssueOperate.allOperates
					.get(ThreeRecordsIssueOperate.YELLOW_SUPERVISE_CODE);
		} else if (superviseLevel != null && superviseLevel == 1) {
			return ThreeRecordsIssueOperate.allOperates
					.get(ThreeRecordsIssueOperate.NORMAL_SUPERVISE_CODE);
		} else if (superviseLevel != null && superviseLevel == -1) {
			return ThreeRecordsIssueOperate.allOperates
					.get(ThreeRecordsIssueOperate.CANCEL_SUPERVISE_CODE);
		} else {
			return OperateForSupervision;
		}
	}

	public void setOperateForSupervision(
			ThreeRecordsIssueOperate operateForSupervision) {
		this.OperateForSupervision = operateForSupervision;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getIssueId() {
		return issueId;
	}

	public PropertyDict getCreateTableType() {
		return createTableType;
	}

	public void setCreateTableType(PropertyDict createTableType) {
		this.createTableType = createTableType;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getOccurDate() {
		return occurDate;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public String getCurrentOrgDisplayName() {
		return currentOrg == null ? "" : currentOrg.getOrgName();
	}

	public Organization getOccurOrg() {
		return occurOrg;
	}

	public void setOccurOrg(Organization occurOrg) {
		this.occurOrg = occurOrg;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getSourcePerson() {
		return sourcePerson;
	}

	public void setSourcePerson(String sourcePerson) {
		this.sourcePerson = sourcePerson;
	}

	public PropertyDict getSourceKind() {
		return sourceKind;
	}

	public void setSourceKind(PropertyDict sourceKind) {
		this.sourceKind = sourceKind;
	}

	public String getSourceMobile() {
		return sourceMobile;
	}

	public void setSourceMobile(String sourceMobile) {
		this.sourceMobile = sourceMobile;
	}

	public Integer getSupervisionState() {
		return supervisionState;
	}

	public void setSupervisionState(Integer supervisionState) {
		this.supervisionState = supervisionState;
	}

	public Long getUrgent() {
		return urgent;
	}

	public void setUrgent(Long urgent) {
		this.urgent = urgent;
	}

	public Long getDealState() {
		return dealState;
	}

	public void setDealState(Long dealState) {
		this.dealState = dealState;
	}

	public Organization getTargetOrg() {
		return targetOrg;
	}

	public void setTargetOrg(Organization targetOrg) {
		this.targetOrg = targetOrg;
	}

	public String getLastOrgDisplayName() {
		return lastOrgDisplayName;
	}

	public void setLastOrgDisplayName(String lastOrgDisplayName) {
		this.lastOrgDisplayName = lastOrgDisplayName;
	}

	public Organization getLastOrg() {
		return lastOrg;
	}

	public void setLastOrg(Organization lastOrg) {
		this.lastOrg = lastOrg;
	}

	public Organization getCurrentOrg() {
		return currentOrg;
	}

	public void setCurrentOrg(Organization currentOrg) {
		this.currentOrg = currentOrg;
	}

	public Long getIssueStepId() {
		return issueStepId;
	}

	public void setIssueStepId(Long issueStepId) {
		this.issueStepId = issueStepId;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getDomainName() {
		return domainName;
	}

	public Date getEvaluateTime() {
		return evaluateTime;
	}

	public void setEvaluateTime(Date evaluateTime) {
		this.evaluateTime = evaluateTime;
	}

	public Integer getEvaluateType() {
		return evaluateType;
	}

	public void setEvaluateType(Integer evaluateType) {
		this.evaluateType = evaluateType;
	}

	public String getEvaluateContent() {
		return evaluateContent;
	}

	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}

	public List<ThreeRecordsIssueOperate> getCanDoList() {
		return canDoList;
	}

	public void setCanDoList(List<ThreeRecordsIssueOperate> canDoList) {
		this.canDoList = canDoList;
	}

	public void setPublicltycass(Integer publicltycass) {
		this.publicltycass = publicltycass;
	}

	public Integer getPublicltycass() {
		return publicltycass;
	}

	public Integer getTopState() {
		return topState;
	}

	public void setTopState(Integer topState) {
		this.topState = topState;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getOccurDateString() {
		if (null == occurDate) {
			return "";
		}
		SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = sformat.format(this.occurDate);
		if (hours != null && !"".equals(hours)) {
			nowTime = nowTime + " " + hours + ":" + minute;
		}
		return nowTime;
	}

	public void setIssueTypes(String issueTypes) {
		this.issueTypes = issueTypes;
	}

	public String getIssueTypes() {
		return issueTypes;
	}

	public void setCreateOrg(Organization createOrg) {
		this.createOrg = createOrg;
	}

	public Organization getCreateOrg() {
		return createOrg;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateUser() {
		return createUser;
	}

	public Integer getEaringWarn() {
		return earingWarn;
	}

	public void setEaringWarn(Integer earingWarn) {
		this.earingWarn = earingWarn;
	}

	public Integer getDelayState() {
		return delayState;
	}

	public void setDelayState(Integer delayState) {
		this.delayState = delayState;
	}

	public List<ThreeRecordsIssueAttachFile> getAttachFilesList() {
		return attachFilesList;
	}

	public void setAttachFilesList(
			List<ThreeRecordsIssueAttachFile> attachFilesList) {
		this.attachFilesList = attachFilesList;
	}

	public String getIssueContent() {
		return issueContent;
	}

	public void setIssueContent(String issueContent) {
		this.issueContent = issueContent;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil
				.encryptDomainId(this.issueId, null, null);
	}

	public String getEncryptIdByIssueStepId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getIssueStepId(), null,
				null);
	}

	public String getEncryptIdByIssueId() {
		return BaseDomainIdEncryptUtil
				.encryptDomainId(getIssueId(), null, null);
	}

	public Integer getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Integer deadLine) {
		this.deadLine = deadLine;
	}

	public Integer getEaringWarnType() {
		return earingWarnType;
	}

	public void setEaringWarnType(Integer earingWarnType) {
		this.earingWarnType = earingWarnType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAppealContent() {
		return appealContent;
	}

	public void setAppealContent(String appealContent) {
		this.appealContent = appealContent;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
