package com.tianque.workMemo.controller;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DateUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.workMemo.domain.WorkMemo;
import com.tianque.workMemo.service.WorkMemoService;

@Namespace("/workMemo/workMemoManage")
@Transactional
@Scope("request")
@Controller("workMemoController")
public class WorkMemoController extends BaseAction {

	private String today;
	private String date;
	private List<String> listDays;
	private List<WorkMemo> list;
	private String year;
	private String month;
	private WorkMemo workMemo;
	private Long workMemoId;
	@Autowired
	private WorkMemoService workMemoService;

	@Action(value = "addWorkMemo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"workMemo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addWorkMemo() {
		try {
			workMemo = workMemoService.addWorkMemo(workMemo);
		} catch (Exception e) {
			errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "deleteWorkMemoById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"workMemoId" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteWorkMemoById() {
		try {
			workMemoService.deleteWorkMemoById(workMemoId);
		} catch (Exception e) {
			errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "getDaysByUserIdAndDate", results = {
			@Result(name = "success", type = "json", params = { "root",
					"listDays", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getDaysByUserIdAndDate() {
		try {
			listDays = workMemoService.getDaysByUserIdAndDate(ThreadVariable
					.getSession().getUserId(), year, replenishPrefix(month));
		} catch (Exception e) {
			errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	private String replenishPrefix(String month) {
		if (month.length() == 1) {
			month = "0" + month;
		}
		return month;
	}

	@Action(value = "getMemoContentsByUserIdAndAddMemoDate", results = { @Result(type = "json", name = "success", params = {
			"root", "list", "ignoreHierarchy", "false" }) })
	public String getMemoContentsByUserIdAndAddMemoDate() {
		list = workMemoService.getMemoContentsByUserIdAndAddMemoDate(
				ThreadVariable.getSession().getUserId(), date);
		return SUCCESS;
	}

	@Action(value = "getTodayMemoContentsByUserIdAndAddMemoDate", results = { @Result(type = "json", name = "success", params = {
			"root", "list", "ignoreHierarchy", "false" }) })
	public String getTodayMemoContentsByUserIdAndAddMemoDate() {
		today = DateUtil.formateYMD(new Date());
		list = workMemoService.getMemoContentsByUserIdAndAddMemoDate(
				ThreadVariable.getSession().getUserId(), today);
		return SUCCESS;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public WorkMemo getWorkMemo() {
		return workMemo;
	}

	public void setWorkMemo(WorkMemo workMemo) {
		this.workMemo = workMemo;
	}

	public void setWorkMemoId(Long workMemoId) {
		this.workMemoId = workMemoId;
	}

	public Long getWorkMemoId() {
		return workMemoId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setListDays(List<String> listDays) {
		this.listDays = listDays;
	}

	public List<String> getListDays() {
		return listDays;
	}

	public void setList(List<WorkMemo> list) {
		this.list = list;
	}

	public List<WorkMemo> getList() {
		return list;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getToday() {
		return today;
	}
}
