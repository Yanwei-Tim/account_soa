package com.tianque.sysadmin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.service.AutocompleteService;
import com.tianque.service.bridge.AutocompleteConvertDefine;
import com.tianque.userAuth.api.PermissionDubboService;

@Controller("userAutocompleteController")
@Scope("prototype")
@Transactional
public class UserAutocompleteController extends BaseAction implements
		ApplicationContextAware {
	@Autowired
	private PermissionDubboService permissionDubboService;
	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();
	private String tag;
	private Long messageType;
	private ApplicationContext appContext;
	private String dataType;
	private AutocompleteService converter;

	public String searchUserForAutoComplete() {
		Session session = ThreadVariable.getSession();
		User user = this.permissionDubboService.getSimpleUserById(session
				.getUserId());
		converter = getDataConvert(dataType);
		List<User> userList = converter.findUserForAutocomplete(tag, user);
		autoCompleteDatas = new ArrayList<AutoCompleteData>();
		for (User users : userList) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(users.getName());
			autoCompleteData.setValue(users.getId().toString());
			autoCompleteDatas.add(autoCompleteData);
		}
		return SUCCESS;
	}

	private AutocompleteService getDataConvert(String dataType) {
		if (StringUtil.isStringAvaliable(dataType)) {
			return (AutocompleteService) appContext
					.getBean(AutocompleteConvertDefine
							.getConvertBeanDefine(dataType));
		}
		return null;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Long getMessageType() {
		return messageType;
	}

	public void setMessageType(Long messageType) {
		this.messageType = messageType;
	}

	public List<AutoCompleteData> getAutoCompleteDatas() {
		return autoCompleteDatas;
	}

	public void setAutoCompleteDatas(List<AutoCompleteData> autoCompleteDatas) {
		this.autoCompleteDatas = autoCompleteDatas;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.appContext = context;
	}

}
