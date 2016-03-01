package com.tianque.plugin.account.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 交办单
 */
public class AssignForm extends BaseDomain {
	private Long stepId; // 步骤编号
	private Long ledgerId; // 台账编号
	private Long ledgerType; // 台账类型
	private Organization sourceOrg; // 当前处理的部门ID
	private String reason; // 主要事项
	private String handleContent; // 会议议定事项摘要
	private String serialNumber; // 交办单编码
	private String ledgerSerialNumber; // 台账编码
	private Date handleStartDate;// 办理起始时间
	private Date handleEndDate;// 办理结束时间
	private Integer periods;// 会议期数
	private PropertyDict type;// 会议类型
	private Integer assignType;// 交办类型
	private List<AssignFormReceiver> receivers;// 接件人

	public Long getStepId() {
		return stepId;
	}

	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	public Long getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(Long ledgerId) {
		this.ledgerId = ledgerId;
	}

	public Long getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(Long ledgerType) {
		this.ledgerType = ledgerType;
	}

	public Organization getSourceOrg() {
		return sourceOrg;
	}

	public void setSourceOrg(Organization sourceOrg) {
		this.sourceOrg = sourceOrg;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getHandleContent() {
		return handleContent;
	}

	public void setHandleContent(String handleContent) {
		this.handleContent = handleContent;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getLedgerSerialNumber() {
		return ledgerSerialNumber;
	}

	public void setLedgerSerialNumber(String ledgerSerialNumber) {
		this.ledgerSerialNumber = ledgerSerialNumber;
	}

	public Date getHandleStartDate() {
		return handleStartDate;
	}

	public void setHandleStartDate(Date handleStartDate) {
		this.handleStartDate = handleStartDate;
	}

	public Date getHandleEndDate() {
		return handleEndDate;
	}

	public void setHandleEndDate(Date handleEndDate) {
		this.handleEndDate = handleEndDate;
	}

	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public PropertyDict getType() {
		return type;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public Integer getAssignType() {
		return assignType;
	}

	public void setAssignType(Integer assignType) {
		this.assignType = assignType;
	}

	public List<AssignFormReceiver> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<AssignFormReceiver> receivers) {
		this.receivers = receivers;
	}

	public void convertReceivers(String receivers) {
		if (!StringUtil.isStringAvaliable(receivers)) {
			return;
		}
		List<AssignFormReceiver> objs = null;
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(receivers);
		if (jsonArray != null) {
			objs = new ArrayList<AssignFormReceiver>();
			List list = (List) JSONSerializer.toJava(jsonArray);
			for (Object o : list) {
				JSONObject jsonObject = JSONObject.fromObject(o);
				AssignFormReceiver obj = (AssignFormReceiver) JSONObject
						.toBean(jsonObject, AssignFormReceiver.class);
				objs.add(obj);
			}
		}
		this.receivers = objs;
	}

}
