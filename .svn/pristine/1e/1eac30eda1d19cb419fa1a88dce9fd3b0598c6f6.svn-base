package com.tianque.sysadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Proclamation;
import com.tianque.domain.User;
import com.tianque.service.ProclamationService;
import com.tianque.userAuth.api.PermissionDubboService;

@Scope("prototype")
@Transactional
@Controller("proclamationController")
public class ProclamationController extends BaseAction {

	@Autowired
	private ProclamationService proclamationService;

	@Autowired
	private PermissionDubboService permissionDubboService;

	private Proclamation proclamation;

	private Long proclamationId;

	private User user;

	public String proclamationsList() {
		user = permissionDubboService.getFullUserById(ThreadVariable
				.getSession().getUserId());
		return SUCCESS;
	}

	public String dispatch() {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(getMode())) {
			proclamation = new Proclamation();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())
				|| DialogMode.VIEW_MODE.equalsIgnoreCase(getMode())) {
			proclamation = proclamationService
					.getProclamationById(proclamationId);
		}
		return SUCCESS;
	}

	public String findProclamations() {
		try {
			gridPage = new GridPage(proclamationService.findProclamations(page,
					rows, sidx, sord));
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	public String addProclamation() {
		proclamation = proclamationService.addProclamation(proclamation);
		return SUCCESS;
	}

	public String updateProclamation() {
		proclamation = proclamationService.updateProclamation(proclamation);
		return SUCCESS;
	}

	public String validateHasDisplay() {
		Proclamation proclamation = proclamationService
				.getDisplayProclamation();
		if (DialogMode.ADD_MODE.equals(getMode()) && proclamation != null) {
			return ERROR;
		} else if (DialogMode.EDIT_MODE.equals(getMode())
				&& proclamation != null
				&& !proclamation.getId().equals(proclamationId)) {
			return ERROR;
		}
		return SUCCESS;
	}

	public String getDisplayProclamation() {
		proclamation = proclamationService.getDisplayProclamation();
		return SUCCESS;
	}

	public Proclamation getProclamation() {
		return proclamation;
	}

	public void setProclamation(Proclamation proclamation) {
		this.proclamation = proclamation;
	}

	public Long getProclamationId() {
		return proclamationId;
	}

	public void setProclamationId(Long proclamationId) {
		this.proclamationId = proclamationId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
