package com.tianque.plugin.account.domain;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ThreeRecordsIssueMap implements Serializable{
	/** 当前issueStepGroupId */
	private Long id;
	/** 处理类型 */
	private Integer dealType;
	/** orgName */
	private String name;
	/** orgCode */
	private Long orgLevelInternalId;
	/** 操作名称(上报，回退等) */
	private String relation;
	/** 下一个issueStepGroupId */
	private Long to;

	private List<String> states;
	/** 督办等级 */
	private Integer superviselevel;
	/** 是否加急 */
	private Integer urgent;
	/** 部门id */
	private Long orgId;
	/**
	 * 是否职能部门
	 */
	private boolean isFunctionalOrg = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDealType() {
		return dealType;
	}

	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrgLevelInternalId() {
		return orgLevelInternalId;
	}

	public void setOrgLevelInternalId(Long orgLevelInternalId) {
		this.orgLevelInternalId = orgLevelInternalId;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Long getTo() {
		return to;
	}

	public void setTo(Long to) {
		this.to = to;
	}

	public List<String> getStates() {
		return states;
	}

	public void setStates(List<String> states) {
		this.states = states;
	}

	public Integer getSuperviselevel() {
		return superviselevel;
	}

	public void setSuperviselevel(Integer superviselevel) {
		this.superviselevel = superviselevel;
	}

	public Integer getUrgent() {
		return urgent;
	}

	public void setUrgent(Integer urgent) {
		this.urgent = urgent;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public boolean isFunctionalOrg() {
		return isFunctionalOrg;
	}

	public void setFunctionalOrg(boolean isFunctionalOrg) {
		this.isFunctionalOrg = isFunctionalOrg;
	}

}
