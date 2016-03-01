package com.tianque.plugin.account.vo;

import java.io.Serializable;
import java.util.List;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class SearchLedgerSteadyWorkVo implements Serializable {
	/** 发生网格 */
	private Organization occurOrg;
	/** 所属网格编号(ORGINTERNALCODE) */
	private String orgInternalCode;
	/** 姓名(NAME) */
	private String name;
	/** 身份证(IDCARDNO) */
	private String idCardNo;
	/** 性别(GENDER) */
	private PropertyDict gender;
	/** 联系电话(MOBILENUMBER) */
	private String mobileNumber;
	/** 状态 */
	private Integer status;
	/** 排序字段 * */
	private String sortField;
	/** 排序方式 * */
	private String order;
	private String seachValue;
	private int tag;
	private Long issueType;
	private Long orgLevel;
	private String leaderView;
	private Long functionalOrgType;
	private Integer submit;
	private Integer assgin;
	/** 已办事件的 查询条件：承办情况 */
	private Integer issueState;
	/** 待跟进的查询操作 ：事件表中已验证状态，值为300 */
	private int verification;
	/** 待跟进的查询操作 ：事件流程表中已验证的状态，值为1000 */
	private int issueCompleteCode;
	private Integer periodCode;
	/** 目标部门 * */
	private Long targeOrgId;
	/** 目标部门内部编码 * */
	private String targeOrgInternalCode;
	private Long orgId;
	private String orgCode;
	/** stateCode */
	private int completeCode;
	private String serialNumber;
	private List<Organization> targetOrgs;
	// 区分是主办还是协办，抄告
	private Integer isSupported;

	public int getCompleteCode() {
		return completeCode;
	}

	public void setCompleteCode(int completeCode) {
		this.completeCode = completeCode;
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

	public Organization getOccurOrg() {
		return occurOrg;
	}

	public void setOccurOrg(Organization occurOrg) {
		this.occurOrg = occurOrg;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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

	public String getSeachValue() {
		return seachValue;
	}

	public void setSeachValue(String seachValue) {
		this.seachValue = seachValue;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
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

	public Long getFunctionalOrgType() {
		return functionalOrgType;
	}

	public void setFunctionalOrgType(Long functionalOrgType) {
		this.functionalOrgType = functionalOrgType;
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

	public Integer getIssueState() {
		return issueState;
	}

	public void setIssueState(Integer issueState) {
		this.issueState = issueState;
	}

	public int getVerification() {
		return verification;
	}

	public void setVerification(int verification) {
		this.verification = verification;
	}

	public int getIssueCompleteCode() {
		return issueCompleteCode;
	}

	public void setIssueCompleteCode(int issueCompleteCode) {
		this.issueCompleteCode = issueCompleteCode;
	}

	public Integer getPeriodCode() {
		return periodCode;
	}

	public void setPeriodCode(Integer periodCode) {
		this.periodCode = periodCode;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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
