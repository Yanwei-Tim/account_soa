package com.tianque.plugin.account.domain;

import java.util.Date;

import org.apache.http.entity.mime.MultipartEntity;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;

/*** 台账处理步骤 */
public class ThreeRecordsIssueStep extends BaseDomain {
	/** 该步骤来源部门 */
	private Organization source;
	/** 该步骤的处理部门 */
	private Organization target;
	/** 该步骤要用到的处理类类名 */
	private String state;
	/** 该步骤的状态代码 */
	private int stateCode;
	/** 进入该步骤的时间 */
	private Date entryDate;
	/** 该步骤的结束时间 */
	private Date endDate;
	/** 该步骤的最后处理时间 */
	private Date lastDealDate;
	/** 该步骤处理的督办状态， 默认不督办 */
	private int superviseLevel = ThreeRecordsIssueState.NO_SUPERVISE;
	/** 如果可以回退，该步骤要回退到的上一步骤 */
	private ThreeRecordsIssueStep backTo;
	/** 步骤组的id */
	private Long groupId;
	/** 项目类型id */
	private Long itemTypeId;

	private Long dealType;

	private Integer assign;

	private Integer submit;
	/** 延期状态 */
	private Integer delayState;
	/** 延期delayWorkday个工作日 */
	private Integer delayWorkday;

	/**
	 * 是否超期办结
	 * 
	 */
	private Integer isExtended;

	/** 台 账类型 **/
	private int ledgerType;
	/*
	 * 台账编号
	 */
	private Long ledgerId;

	// 区分是主办还是协办，抄告
	private int isSupported;

	private Integer isFeedBack;

	private PropertyDict emergencyLevel;// 重大紧急等级
	/** 返回log数据 */
	private MultipartEntity multipartEntityData;

	public ThreeRecordsIssueStep getBackTo() {
		return backTo;
	}

	public void setBackTo(ThreeRecordsIssueStep backTo) {
		this.backTo = backTo;
	}

	public Organization getSource() {
		return source;
	}

	public void setSource(Organization source) {
		this.source = source;
	}

	public Organization getTarget() {
		return target;
	}

	public void setTarget(Organization target) {
		this.target = target;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getLastDealDate() {
		return lastDealDate;
	}

	public void setLastDealDate(Date lastDealDate) {
		this.lastDealDate = lastDealDate;
	}

	public int getSuperviseLevel() {
		return superviseLevel;
	}

	public void setSuperviseLevel(int superviseLevel) {
		this.superviseLevel = superviseLevel;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public Long getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(Long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public void setAssign(Integer assign) {
		this.assign = assign;
	}

	public Integer getAssign() {
		return assign;
	}

	public void setSubmit(Integer submit) {
		this.submit = submit;
	}

	public Integer getSubmit() {
		return submit;
	}

	public Integer getDelayState() {
		return delayState;
	}

	public void setDelayState(Integer delayState) {
		this.delayState = delayState;
	}

	public Integer getDelayWorkday() {
		return delayWorkday;
	}

	public void setDelayWorkday(Integer delayWorkday) {
		this.delayWorkday = delayWorkday;
	}

	public Integer getIsExtended() {
		return isExtended;
	}

	public void setIsExtended(Integer isExtended) {
		this.isExtended = isExtended;
	}

	public void setEmergencyLevel(PropertyDict emergencyLevel) {
		this.emergencyLevel = emergencyLevel;
	}

	public PropertyDict getEmergencyLevel() {
		return emergencyLevel;
	}

	public MultipartEntity getMultipartEntityData() {
		return multipartEntityData;
	}

	public void setMultipartEntityData(MultipartEntity multipartEntityData) {
		this.multipartEntityData = multipartEntityData;
	}

	public Long getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(Long ledgerId) {
		this.ledgerId = ledgerId;
	}

	public int getIsSupported() {
		return isSupported;
	}

	public void setIsSupported(int isSupported) {
		this.isSupported = isSupported;
	}

	public int getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(int ledgerType) {
		this.ledgerType = ledgerType;
	}

	public Integer getIsFeedBack() {
		return isFeedBack;
	}

	public void setIsFeedBack(Integer isFeedBack) {
		this.isFeedBack = isFeedBack;
	}

	public Long getDealType() {
		return dealType;
	}

	public void setDealType(Long dealType) {
		this.dealType = dealType;
	}

}
