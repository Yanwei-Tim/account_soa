package com.tianque.plugin.account.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.DateUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

@SuppressWarnings("serial")
public class SearchPeopleAspirationVo implements Serializable {
	/** 状态 */
	private Integer status;
	/** 排序字段 * */
	private String sortField;
	/** 排序方式 * */
	private String order;
	/** 主题 */
	private String subject;
	/** 发生网格 */
	private Organization occurOrg;
	/** 事件性质 */
	private PropertyDict issueKind;
	/** 是否重大事件 */
	private Boolean important;
	private String importantStr;
	/** 服务单号 */
	private String serialNumber;
	/** 来源人 */
	private String sourcePerson;
	/** 来源手机 */
	private String sourceMobile;
	/** 发生时间 从 */
	private Date occurFrom;
	/** 发生时间 到 */
	private Date occurEnd;
	/** 结案时间 从 */
	private Date endTimeFrom;
	/** 结案时间 到 */
	private Date endTimeEnd;
	/** 涉及人数区间 */
	private Integer relatePeopleMinCount;
	private Integer relatePeopleMaxCount;
	/** 主要人物 */
	private String mainCharacters;
	/** 涉及金额区间 */
	private Double relateMinAmount;
	private Double relateMaxAmount;
	/** 发生地点 */
	private String occurLocation;
	/** 目标部门 * */
	private Long targeOrgId;
	/** 目标部门内部编码 * */
	private String targeOrgInternalCode;
	private String orgInternalCode;
	/** 处理状态(是否完成) * */
	private Long dealState;
	private List<Long> dealStateList;
	/** 来源方式 */
	private List<PropertyDict> sourcekind;
	/** 发生地点 */
	private String issueContent;
	/** stateCode */
	private int completeCode;
	private String idCardNo;
	private Long searchOrgId;
	private String seachValue;
	private int tag;
	private Long issueType;
	private Long orgLevel;
	private String leaderView;
	private Long functionalOrgType;
	private Integer submit;
	private Integer assgin;
	private Integer orgLevelInternalId;
	private String viewType;
	private Long orgId;
	private String orgCode;
	private String statusType;
	private PropertyDict createTableType;
	private int orgCodeFindLevel;
	private String searchOrgCode;
	private Integer evaluateType;

	/** 已办事件的 查询条件：承办情况 */
	private Integer issueState;
	/** 待跟进的查询操作 ：事件表中已验证状态，值为300 */
	private int verification;
	/** 待跟进的查询操作 ：事件流程表中已验证的状态，值为1000 */
	private int issueCompleteCode;

	private Long sourceType;

	/** 当前时间用于待评分搜索 */
	private Date nowDate;
	private PropertyDict appealHumanType;
	private String serverContractor;
	private String name;
	private Integer periodCode;
	private List<Organization> targetOrgs;
	// 区分是主办还是协办，抄告
	private Integer isSupported;

	public Integer getEvaluateType() {
		return evaluateType;
	}

	public void setEvaluateType(Integer evaluateType) {
		this.evaluateType = evaluateType;
	}

	public int getCompleteCode() {
		return completeCode;
	}

	public void setCompleteCode(int completeCode) {
		this.completeCode = completeCode;
	}

	public String getIssueContent() {
		return issueContent;
	}

	public void setIssueContent(String issueContent) {
		this.issueContent = issueContent;
	}

	public String getSourceMobile() {
		return sourceMobile;
	}

	public void setSourceMobile(String sourceMobile) {
		this.sourceMobile = sourceMobile;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Long getTargeOrgId() {
		return targeOrgId;
	}

	public void setTargeOrgId(Long targeOrgId) {
		this.targeOrgId = targeOrgId;
	}

	public String getTargeOrgInternalCode() {
		return targeOrgInternalCode;
	}

	public void setTargeOrgInternalCode(String targeOrgInternalCode) {
		this.targeOrgInternalCode = targeOrgInternalCode;
	}

	public Long getDealState() {
		return dealState;
	}

	public void setDealState(Long dealState) {
		this.dealState = dealState;
	}

	public List<Long> getDealStateList() {
		return dealStateList;
	}

	public void setDealStateList(List<Long> dealStateList) {
		this.dealStateList = dealStateList;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Organization getOccurOrg() {
		return occurOrg;
	}

	public void setOccurOrg(Organization occurOrg) {
		this.occurOrg = occurOrg;
	}

	public PropertyDict getIssueKind() {
		return issueKind;
	}

	public void setIssueKind(PropertyDict issueKind) {
		this.issueKind = issueKind;
	}

	public Boolean getImportant() {
		return important;
	}

	public void setImportant(Boolean important) {
		this.important = important;
	}

	public String getImportantStr() {
		return importantStr;
	}

	public void setImportantStr(String importantStr) {
		this.importantStr = importantStr;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSourcePerson() {
		return sourcePerson;
	}

	public void setSourcePerson(String sourcePerson) {
		this.sourcePerson = sourcePerson;
	}

	public Date getOccurFrom() {
		return occurFrom;
	}

	public void setOccurFrom(Date occurFrom) {
		this.occurFrom = occurFrom;
	}

	public Date getOccurEnd() {
		return occurEnd;
	}

	public void setOccurEnd(Date occurEnd) {
		if (occurEnd != null) {

			occurEnd = DateUtil.getEndTime(occurEnd);
		}
		this.occurEnd = occurEnd;
	}

	public Integer getRelatePeopleMinCount() {
		return relatePeopleMinCount;
	}

	public void setRelatePeopleMinCount(Integer relatePeopleMinCount) {
		this.relatePeopleMinCount = relatePeopleMinCount;
	}

	public Integer getRelatePeopleMaxCount() {
		return relatePeopleMaxCount;
	}

	public void setRelatePeopleMaxCount(Integer relatePeopleMaxCount) {
		this.relatePeopleMaxCount = relatePeopleMaxCount;
	}

	public String getMainCharacters() {
		return mainCharacters;
	}

	public void setMainCharacters(String mainCharacters) {
		this.mainCharacters = mainCharacters;
	}

	public Double getRelateMinAmount() {
		return relateMinAmount;
	}

	public void setRelateMinAmount(Double relateMinAmount) {
		this.relateMinAmount = relateMinAmount;
	}

	public Double getRelateMaxAmount() {
		return relateMaxAmount;
	}

	public void setRelateMaxAmount(Double relateMaxAmount) {
		this.relateMaxAmount = relateMaxAmount;
	}

	public String getOccurLocation() {
		return occurLocation;
	}

	public void setOccurLocation(String occurLocation) {
		this.occurLocation = occurLocation;
	}

	public List<PropertyDict> getSourcekind() {
		return sourcekind;
	}

	public void setSourcekind(List<PropertyDict> sourcekind) {
		this.sourcekind = sourcekind;
	}

	public Date getEndTimeFrom() {
		return endTimeFrom;
	}

	public void setEndTimeFrom(Date endTimeFrom) {

		this.endTimeFrom = endTimeFrom;
	}

	public Date getEndTimeEnd() {

		return endTimeEnd;
	}

	public void setEndTimeEnd(Date endTimeEnd) {
		if (endTimeEnd != null) {
			endTimeEnd = DateUtil.getEndTime(endTimeEnd);
		}
		this.endTimeEnd = endTimeEnd;
	}

	public Date getNowDate() {

		return nowDate == null ? CalendarUtil.today() : nowDate;
	}

	public void setNowDate(Date nowDate) {
		if (nowDate == null) {
			nowDate = CalendarUtil.today();
		}
		this.nowDate = nowDate;
	}

	public Long getSearchOrgId() {
		return searchOrgId;
	}

	public void setSearchOrgId(Long searchOrgId) {
		this.searchOrgId = searchOrgId;
	}

	public String getSeachValue() {
		return seachValue;
	}

	public void setSeachValue(String seachValue) {
		this.seachValue = seachValue;
	}

	public Long getIssueType() {
		return issueType;
	}

	public void setIssueType(Long issueType) {
		this.issueType = issueType;
	}

	public Long getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Long orgLevel) {
		this.orgLevel = orgLevel;
	}

	public String getLeaderView() {
		return leaderView;
	}

	public void setLeaderView(String leaderView) {
		this.leaderView = leaderView;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public Integer getSubmit() {
		return submit;
	}

	public void setSubmit(Integer submit) {
		this.submit = submit;
	}

	public Integer getAssgin() {
		return assgin;
	}

	public void setAssgin(Integer assgin) {
		this.assgin = assgin;
	}

	public Integer getOrgLevelInternalId() {
		return orgLevelInternalId;
	}

	public void setOrgLevelInternalId(Integer orgLevelInternalId) {
		this.orgLevelInternalId = orgLevelInternalId;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Long getFunctionalOrgType() {
		return functionalOrgType;
	}

	public void setFunctionalOrgType(Long functionalOrgType) {
		this.functionalOrgType = functionalOrgType;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public int getOrgCodeFindLevel() {
		return orgCodeFindLevel;
	}

	public void setOrgCodeFindLevel(int orgCodeFindLevel) {
		this.orgCodeFindLevel = orgCodeFindLevel;
	}

	public String getSearchOrgCode() {
		return searchOrgCode;
	}

	public void setSearchOrgCode(String searchOrgCode) {
		this.searchOrgCode = searchOrgCode;
	}

	public Integer getIssueState() {
		return issueState;
	}

	public void setIssueState(Integer issueState) {
		this.issueState = issueState;
	}

	public int getVerification() {
		return verification;
	}

	public int getIssueCompleteCode() {
		return issueCompleteCode;
	}

	public void setVerification(int verification) {
		this.verification = verification;
	}

	public void setIssueCompleteCode(int issueCompleteCode) {
		this.issueCompleteCode = issueCompleteCode;
	}

	public Long getSourceType() {
		return sourceType;
	}

	public void setSourceType(Long sourceType) {
		this.sourceType = sourceType;
	}

	public PropertyDict getAppealHumanType() {
		return appealHumanType;
	}

	public void setAppealHumanType(PropertyDict appealHumanType) {
		this.appealHumanType = appealHumanType;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public PropertyDict getCreateTableType() {
		return createTableType;
	}

	public void setCreateTableType(PropertyDict createTableType) {
		this.createTableType = createTableType;
	}

	public String getServerContractor() {
		return serverContractor;
	}

	public void setServerContractor(String serverContractor) {
		this.serverContractor = serverContractor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPeriodCode() {
		return periodCode;
	}

	public void setPeriodCode(Integer periodCode) {
		this.periodCode = periodCode;
	}

	public List<Organization> getTargetOrgs() {
		return targetOrgs;
	}

	public void setTargetOrgs(List<Organization> targetOrgs) {
		this.targetOrgs = targetOrgs;
	}

	public Integer getIsSupported() {
		return isSupported;
	}

	public void setIsSupported(Integer isSupported) {
		this.isSupported = isSupported;
	}

}
