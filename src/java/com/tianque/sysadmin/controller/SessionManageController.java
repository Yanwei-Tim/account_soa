package com.tianque.sysadmin.controller;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.component.SessionManager;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.LoginType;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.GlobalValue;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PermissionDubboService;

@Transactional
@Scope("request")
@SuppressWarnings("serial")
@Controller("sessionManageController")
public class SessionManageController extends BaseAction {
	private static final Logger logger = LoggerFactory
			.getLogger(SessionManageController.class);
	private Session session;
	private User user;
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;

	private Organization organization;
	private String path;
	private List<Session> sessions;

	public String login() {
		// try{
		// String loginusername =
		// ServletActionContext.getRequest().getParameter("userName");
		// if
		// (!"admin".equals(loginusername)&&"production".equals(GlobalValue.environment)
		// && GridProperties.IS_CONNECT_VPDN
		// && loginusername.indexOf("@shzc") == -1){
		// if(!sessionManager.vpdnConnect(
		// ServletActionContext.getRequest(),
		// ServletActionContext.getResponse())){
		// errorMessage = (String)
		// ServletActionContext.getRequest().getAttribute(
		// GlobalValue.LOGIN_FAILURE_MSG);
		// return "loginFailure";
		// }
		// }
		// }catch (Exception e) {
		// logger.warn(e.getMessage(),e);
		// }
		LoginType loginResult = null;
		try {
			loginResult = sessionManager.login(
					ServletActionContext.getRequest(),
					ServletActionContext.getResponse());
			errorMessage = (String) ServletActionContext.getRequest()
					.getAttribute(GlobalValue.LOGIN_FAILURE_MSG);
		} catch (Exception e) {
			logger.error("错误信息：", e);
		}
		return loginResult.name();
	}

	public String mockLogin() {
		LoginType loginResult = null;
		loginResult = sessionManager.mockLogin(
				ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		errorMessage = (String) ServletActionContext.getRequest().getAttribute(
				GlobalValue.LOGIN_FAILURE_MSG);
		return loginResult.name();
	}

	public String logout() {
		sessionManager.logout(ServletActionContext.getRequest(),
				ServletActionContext.getResponse());
		return SUCCESS;
	}

	public String getSessionList() {
		PageInfo pageInfo = ControllerHelper.processAllOrgRelativeName(
				sessionManager.getSessionsByOrgInternalCode(ThreadVariable
						.getUser().getOrgInternalCode(), this.getPage(), this
						.getRows(), sidx, sord), organizationDubboService,
				new String[] { "organization" }, ThreadVariable.getUser()
						.getOrganization().getId());
		this.gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String findAllSessions() {
		PageInfo<Session> pageInfo = sessionManager
				.getSessionsByOrgInternalCode(ThreadVariable.getUser()
						.getOrgInternalCode(), this.getPage(), this.getRows(),
						sidx, sord);
		sessions = pageInfo.getResult();
		return SUCCESS;
	}

	// public String getLoginInfo() {
	// session = ThreadVariable.getSession();
	// if (this.session == null || !session.isLogin()) {
	// return SUCCESS;
	// }
	// user = this.permissionService.getSimpleUserById(session.getUserId());
	// return SUCCESS;
	// }
	public String getLoginInfo() {
		session = ThreadVariable.getSession();
		if (this.session == null || !session.isLogin()) {
			return SUCCESS;
		}
		user = this.permissionDubboService.getSimpleUserById(session
				.getUserId());
		organization = organizationDubboService.getSimpleOrgById(user
				.getOrganization().getId());
		path = ControllerHelper.getRelativeOrgNameByOrgId(user
				.getOrganization().getId(), organizationDubboService);
		organization.setOrgName(path);
		user.setOrganization(organization);
		return SUCCESS;
	}

	public String getCurrentSession() {
		session = ThreadVariable.getSession();
		return SUCCESS;
	}

	public String deleteSession() {
		sessionManager.deleteSessionBySessionId(sessionId);
		return SUCCESS;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String sessionId;
	public String loginname;

	@Autowired
	private SessionManager sessionManager;

	@Autowired
	private PermissionDubboService permissionDubboService;

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	/**
	 * 显示页
	 */
	private Integer page = 1;
	/**
	 * 角色grid的数据源
	 */
	private GridPage gridPage;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public GridPage getGridPage() {
		return gridPage;
	}

	public void setGridPage(GridPage gridPage) {
		this.gridPage = gridPage;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	/**
	 * 排序的字段名
	 */
	private String sidx;

	/**
	 * 排序的顺序
	 */
	private String sord;

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 一页行数
	 */
	private Integer rows = 15;

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

}
