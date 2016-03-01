package com.tianque.sysadmin.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.ThreadVariable;
import com.tianque.workBench.moduelClickCount.domain.ModuelClick;
import com.tianque.workBench.moduelClickCount.service.ModuelClickCountService;

@Namespace("/workBench/moduelClickCount")
@Transactional
@Scope("request")
@Controller("moduelClickCountController")
public class ModuelClickCountController extends BaseAction {
	@Autowired
	private ModuelClickCountService moduelClickCountService;
	private List<ModuelClick> moduelClicks;

	private static Logger logger = LoggerFactory
			.getLogger(ModuelClickCountController.class);

	@Actions({
			@Action(value = "findMyFeaturesByUserId", results = {
					@Result(type = "json", name = "success", params = { "root",
							"gridPage", "ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "findModuelClickCountByUserId", results = {
					@Result(name = "success", location = "/workBench/common/informationTrain.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String findModuelClickCountByUserId() {
		try {
			Long userId = ThreadVariable.getSession().getUserId();
			moduelClicks = moduelClickCountService
					.findModuelClickCountByUserId(userId,8);
			return SUCCESS;
		} catch (ServiceException e) {
			errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			logger.error("根据用户Id查找点击次数用于信息直通车", e);
			errorMessage = "根据用户Id查找点击次数用于信息直通车数据错误";
			return ERROR;
		}
	}

	public List<ModuelClick> getModuelClicks() {
		return moduelClicks;
	}

	public void setModuelClicks(List<ModuelClick> moduelClicks) {
		this.moduelClicks = moduelClicks;
	}

}
