package com.tianque.component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.component.dao.SessionDao;
import com.tianque.controller.LoginType;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.systemLog.domain.SystemLog;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.CookieUtil;
import com.tianque.core.util.EncryptUtil;
import com.tianque.core.util.GlobalValue;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PermissionDubboService;

@Component("sessionManager")
public class SessionManagerImpl implements SessionManager {
	private final static Logger logger = LoggerFactory
			.getLogger(SessionManagerImpl.class);
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;

	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	@Override
	@Transactional
	public boolean isLogin(HttpServletRequest request,
			HttpServletResponse response) {
		String sessionId = request.getParameter(GlobalValue.LOGIN_SESSION_ID);
		if (sessionId == null || "".equals(sessionId.trim())) {
			sessionId = CookieUtil.getSesssionIdFromCookies(request);
		}
		Session session = sessionDao.findSessionBySessionId(sessionId);
		if (session == null || null == session.getSessionId()) {
			return false;
		}

		if (!session.isLogin()) {
			return false;
		}

		if (isTimeOut(session)) {
			sessionDao.deleteSessionBySessionId(sessionId);
			return false;
		}

		sessionDao.updateSessionAccessTimeBySessionId(sessionId, Calendar
				.getInstance().getTime(), request.getRequestURI(),
				getIpAddr(request));
		ThreadVariable.setSession(session);
		User user = permissionDubboService.getSimpleUserById(session.getUserId());
		ThreadVariable.setUser(user);
		ThreadVariable.setOrganization(organizationDubboService
				.getFullOrgById(ThreadVariable.getUser().getOrganization()
						.getId()));
		CookieUtil.putSessionIdInCookies(request, response,
				GlobalValue.LOGIN_SESSION_ID, session.getSessionId());
		return true;
	}

	private boolean isTimeOut(Session session) {
		if ((Calendar.getInstance().getTime().getTime() - session
				.getAccessTime().getTime()) > GridProperties.SESSION_TIME_OUT) {
			logger.info("用户：{}Session失效，上一次访问时间{}", session.getUserName(),
					session.getAccessTime());
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		String sessionId = getSessionIdFromCookie(request);
		sessionDao.deleteSessionBySessionId(sessionId);
		systemLogService.log("用户登出", ModelType.LOGGIN, OperatorType.LOGINUP);
		ThreadVariable.setSession(null);
		clearValueInCookies(request, response, GlobalValue.LOGIN_SESSION_ID,
				sessionId);
	}

	private String getSessionIdFromCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return "";
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(GlobalValue.LOGIN_SESSION_ID)) {
				return cookie.getValue();
			}
		}
		return "";
	}

	private void clearValueInCookies(HttpServletRequest request,
			HttpServletResponse response, String key, String value) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	private boolean checkValidateCode(HttpServletRequest request,
			String validateCode) {
		String sessionId = CookieUtil.getSesssionIdFromCookies(request);
		if (sessionId == null) {
			return true;
		}
		Session session = sessionDao.findSessionBySessionId(sessionId);
		if (validateCode != null
				&& validateCode.equals(session.getValidateCode())) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public LoginType login(HttpServletRequest request,
			HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String sso = (String) request.getAttribute("sso");
		if (sso != null && !"".equals(sso)) {
			if ("admin".equals(userName)) {
				return LoginType.loginFailure;
			}
			password = "password";
		}
		String validateCode = request.getParameter("validateCode");
		LoginType resultType = proccessLogin(request, response, userName,
				password, validateCode);
		if (LoginType.loginSuccess != resultType) {
			return resultType;
		}
		return LoginType.loginSuccess;

	}

	private LoginType proccessLogin(HttpServletRequest request,
			HttpServletResponse response, String userName, String password,
			String validateCode) {
		setParametersToRequest(request, userName, password, validateCode);
		clearSessionIdFromCookie(request, response);

		User user = permissionDubboService.findUserByUserName(userName);
		if (!validateLoginUser(request, userName, password)) {
			return LoginType.loginFailure;
		}

		if (!compareUserToParameters(user, request, userName, password,
				validateCode)) {
			return LoginType.loginFailure;
		}
		proccessLoginSuccess(request, response, userName, password, user);
		if (!isUserFirstLogin(user)) {
			return LoginType.firstLogin;
		}
		return LoginType.loginSuccess;
	}

	private boolean validateLoginUser(HttpServletRequest request,
			String userName, String password) {
		if (userName == null || password == null) {
			return false;
		}
		return true;
	}

	/**
	 * 这个是重复登录
	 * */
	private void fireLoginedUser(String userName) {
		// sessionDao.updateSessionHasLoginedByUserName(userName, false);
		List<Session> sessions = sessionDao.findSessionByUserName(userName);
		for (Session session : sessions) {
			sessionDao.updateSessionHasLogined(session.getSessionId(), false);
		}
	}

	private boolean isUserFirstLogin(User user) {
		if (user.isChangePassword()) {
			return false;
		}
		return true;
	}

	public void proccessLoginSuccess(HttpServletRequest request,
			HttpServletResponse response, String userName, String password,
			User user) {
		fireLoginedUser(userName);
		String oldSid = CookieUtil.getOldSessionId();
		if (oldSid != null && !"".equals(oldSid.trim())) {
			Session oldSession = sessionDao.findSessionBySessionId(oldSid);
			fireLoginedUser(oldSession.getUserName());
		}
		generateLoginedSession(request, response, userName, password, user);
		updateLoginUser(request, user);
		systemLogService.log("用户登录成功!", ModelType.LOGGIN, OperatorType.LOGIN);
		if (user.getFailureTimes() > 0) {
			user.setFailureTimes(0);
			permissionDubboService.updateFailureTimesById(user.getId(),
					user.getFailureTimes());
		}
	}

	private void setParametersToRequest(HttpServletRequest request,
			String userName, String password, String validateCode) {
		request.setAttribute("userName", userName);
		request.setAttribute("password", password);
		request.setAttribute("validateCode", validateCode);
	}

	private boolean compareUserToParameters(User user,
			HttpServletRequest request, String userName, String password,
			String validateCode) {
		if (user == null) {
			systemLogService.log("用户登录失败，不存在用户名:" + userName, ModelType.LOGGIN,
					OperatorType.LOGIN, SystemLog.WARN, getIpAddr(request));
			request.setAttribute(GlobalValue.LOGIN_FAILURE_MSG,
					"{userName:'用户名或密码错误'}");
			return false;
		}
		if (user.isLock()) {
			systemLogService.log("用户登录失败，该用户已被锁定:" + userName,
					ModelType.LOGGIN, OperatorType.LOGIN, SystemLog.WARN,
					getIpAddr(request));
			request.setAttribute(GlobalValue.LOGIN_FAILURE_MSG,
					"{userName:'用户登录失败，该用户已被锁定'}");
			return false;
		}
		if (request.getAttribute("sso") != null
				&& !"".equals(request.getAttribute("sso"))) {

		} else {
			if (!validatePassword(user, password)) {
				systemLogService.log("用户登录失败，密码错误！ 用户名为:" + userName,
						ModelType.LOGGIN, OperatorType.LOGIN, SystemLog.WARN,
						getIpAddr(request));
				request.setAttribute("user", user);
				proccessFailureTimeslimit(request, user);
				return false;
			}
		}

		if (user.getFailureTimes() != null
				&& user.getFailureTimes().intValue() >= 3
				&& !checkValidateCode(request, validateCode)) {
			systemLogService.log("用户登录失败，验证码错误", ModelType.LOGGIN,
					OperatorType.LOGIN, SystemLog.WARN, getIpAddr(request));
			request.setAttribute(GlobalValue.LOGIN_FAILURE_MSG,
					"{validateCode:'验证码错误'}");
			return false;
		}
		return true;
	}

	/**
	 * 如果有sid，清除掉该cookie； 如果有oldSid，也清除之
	 * */
	private void clearSessionIdFromCookie(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		String sessionId = null;
		if (cookies == null)
			return;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(GlobalValue.LOGIN_SESSION_ID)) {
				String sid = cookie.getValue();
				if (sid != null && !"".equals(sid.trim()))
					clearValueInCookies(request, response,
							GlobalValue.LOGIN_SESSION_ID, sid);
				// break;
			} else if (cookie.getName()
					.equals(GlobalValue.OLD_LOGIN_SESSION_ID)) {
				sessionId = cookie.getValue();
				if (sessionId != null && !"".equals(sessionId.trim()))
					clearValueInCookies(request, response,
							GlobalValue.OLD_LOGIN_SESSION_ID, sessionId);
			}
		}

	}

	/**
	 * 登录失败次数限制，如果超过5次将锁定用户
	 * 
	 * @param request
	 * @param user
	 */
	private void proccessFailureTimeslimit(HttpServletRequest request, User user) {
		if (user.isAdmin()) {
			request.setAttribute(GlobalValue.LOGIN_FAILURE_MSG,
					"{userName:'用户名或密码错误'}");
			return;
		}
		user.setFailureTimes(user.getFailureTimes() + 1);
		permissionDubboService.updateFailureTimesById(user.getId(),
				user.getFailureTimes());

		if (user.getFailureTimes() >= 5) {
			user.setLock(true);
			permissionDubboService.lockOperate(user.getId(), user.isLock());
			request.setAttribute(GlobalValue.LOGIN_FAILURE_MSG,
					"{userName:'用户登录失败，密码错误！<br>您登录失败次数超过5次已被锁定，请与管理员联系',failureTimes:'"
							+ user.getFailureTimes() + "'}");
		} else {
			request.setAttribute(
					GlobalValue.LOGIN_FAILURE_MSG,
					"{userName:'用户登录失败，密码错误！<br>您已有" + user.getFailureTimes()
							+ "次登录失败，超过5次之后将被锁定',failureTimes:'"
							+ user.getFailureTimes() + "'}");
		}
	}

	private void updateLoginUser(HttpServletRequest request, User user) {
		user.setPreviousLoginIp(user.getLastLoginIp());
		user.setPreviousLoginTime(user.getLastLoginTime());
		user.setLastLoginIp(getIpAddr(request));
		user.setLastLoginTime(Calendar.getInstance().getTime());
		this.permissionDubboService.updateUser(user);
	}

	private void generateLoginedSession(HttpServletRequest request,
			HttpServletResponse response, String userName, String password,
			User user) {
		String sessionId = CookieUtil.getSesssionIdFromCookies(request);
		if (sessionId != null) {
			sessionDao.deleteSessionBySessionId(sessionId);
		}

		logger.debug("userName:{} password:{}", userName,
				EncryptUtil.md5Encrypt(password));

		Session session = addSession(user.getId(), userName, user.getName(),
				user.getOrganization(), getIpAddr(request),
				request.getRequestURI(), true, getIpAddr(request),
				user.getOrgInternalCode());
		logger.debug("user.getOrgInternalCode:", user.getOrgInternalCode());
		ThreadVariable.setSession(session);
		CookieUtil.putSessionIdInCookies(request, response,
				GlobalValue.LOGIN_SESSION_ID, session.getSessionId());
	}

	// 假设所有访问时间加上过期时间，还没到当前时间，则不增加Session
	// TODO 本行为对外公开，应当做业务约束
	public Session addSession(Session session) {
		if (session.getAccessTime().getTime() + GridProperties.SESSION_TIME_OUT < System
				.currentTimeMillis()) {
			return null;
		}
		return sessionDao.addSession(session);
	}

	private Session addSession(Long userId, String userName,
			String userRealName, Organization org, String loginIp,
			String lastUrl, boolean isLogin, String accessIp,
			String orgInternalCode) {
		Session session = new Session();
		session.setLoginIp(loginIp);
		session.setUserName(userName);
		session.setUserRealName(userRealName);
		session.setOrganization(org);
		session.setAccessTime(Calendar.getInstance().getTime());
		String randomUUId = UUID.randomUUID().toString();
		session.setSessionId(randomUUId);
		session.setLogin(isLogin);
		session.setLastUrl(lastUrl);
		session.setAccessIp(accessIp);
		session.setUserId(userId);
		session.setOrgInternalCode(orgInternalCode);
		if (isLogin) {
			session.setLoginDate(Calendar.getInstance().getTime());
		}
		return addSession(session);
	}

	private boolean validatePassword(User user, String password) {

		if (password == null
				|| !EncryptUtil.md5Encrypt(password).equals(user.getPassword())) {
			return false;
		}
		return true;
	}

	// private boolean haveSessionIdInCookies(HttpServletRequest request,
	// HttpServletResponse response) {
	// if (CookieUtil.getSesssionIdFromCookies(request) != null) {
	// return true;
	// }
	// return false;
	// }

	@Override
	@Transactional
	public void deleteSessionsWhenTimeOut() {
		int deletedSessionCounts = sessionDao.deleteSessionsWhenTimeOut();
		logger.info("自动清理过期Session,总共清理{}个在线用户", deletedSessionCounts);
	}

	@Override
	@Transactional
	public boolean isFirstLogin() {
		if (ThreadVariable.getSession() == null) {
			return false;
		}
		Long userId = ThreadVariable.getSession().getUserId();
		User user = permissionDubboService.getSimpleUserById(userId);
		return user.isChangePassword();
	}

	@Override
	@Transactional
	public boolean havePermission(Method method, String action,
			HttpServletRequest request) {
		logger.debug("into havePermission");
		Annotation[] annotations = method.getDeclaredAnnotations();
		if (annotations == null || annotations.length == 0) {
			return true;
		}
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return false;
		}
		String sessionId = CookieUtil.getSesssionIdFromCookies(request);
		for (int i = 0; i < annotations.length; i++) {
			if (annotations[i].annotationType().getName()
					.equals(PermissionFilters.class.getName())) {
				return permissionFilters(method, action, sessionId);
			} else if (annotations[i].annotationType().getName()
					.equals(PermissionFilter.class.getName())) {
				return permissionFilter(method, sessionId);
			}
		}
		return true;
	}

	private boolean permissionFilter(Method method, String sessionId) {
		logger.debug("into permissionFilter");
		PermissionFilter permission = method
				.getAnnotation(PermissionFilter.class);
		String ename = permission.ename();
		logger.debug("ename:={}", ename);
		if (isSessionHavePermission(sessionId, ename)) {
			logger.debug("ename:={}", true);
			return true;
		} else {
			logger.debug("ename:={}", false);
			return false;
		}
	}

	private boolean permissionFilters(Method method, String action,
			String sessionId) {
		logger.debug("into permissionFilters");
		PermissionFilters annotation = method
				.getAnnotation(PermissionFilters.class);
		PermissionFilter[] permissionAnnotations = annotation.value();
		for (int i = 0; i < permissionAnnotations.length; i++) {
			PermissionFilter permission = permissionAnnotations[i];
			String ename = permission.ename();
			String actionName = permission.actionName();
			if (action.equals(actionName)
					&& isSessionHavePermission(sessionId, ename)) {
				return true;
			}
		}
		return false;
	}

	private boolean isSessionHavePermission(String sessionId, String ename) {
		Session session = ThreadVariable.getSession();
		if (session == null || session.getUserId() == null)
			session = sessionDao.findSessionBySessionId(sessionId);
		return permissionDubboService
				.isUserHasPermission(session.getUserId(), ename);
	}

	public void deleteSessionBySessionId(String sessionId) {
		sessionDao.deleteSessionBySessionId(sessionId);
	}

	/**
	 * 安全验证,如果成功，返回最初登录的session
	 * */
	private Session verifySession(String userName) {
		String oldSessionId = CookieUtil.getOldSessionId();
		String curSessionId = CookieUtil.getSessionId();
		Session verifySession = null;
		if (oldSessionId != null && !"".equals(oldSessionId.trim())) {
			verifySession = sessionDao.findSessionBySessionId(oldSessionId);
		} else {
			verifySession = sessionDao.findSessionBySessionId(curSessionId);
		}
		if (!"admin".equals(verifySession.getUserName())) {
			User oldUser = permissionDubboService.getSimpleUserById(verifySession
					.getUserId());
			if (!oldUser.isAdmin())
				return null;
		}
		if ("admin".equals(userName)) {
			return null;
		}
		return verifySession;

	}

	@Override
	public LoginType mockLogin(HttpServletRequest request,
			HttpServletResponse response) {
		String userName = request.getParameter("switchUserName");
		Session oldSession = verifySession(userName);
		if (null == oldSession) {
			return LoginType.loginFailure;
		}
		LoginType resultType = mockProccessLogin(request, response, userName,
				oldSession);
		if (LoginType.loginSuccess != resultType) {
			return resultType;
		}
		return LoginType.loginSuccess;
	}

	private LoginType mockProccessLogin(HttpServletRequest request,
			HttpServletResponse response, String userName, Session oldSession) {
		// 密码和验证码不用
		User user = permissionDubboService.findUserByUserName(userName);
		if (user == null) {
			request.setAttribute(GlobalValue.LOGIN_FAILURE_MSG,
					"{userName:'用户名错误'}");
			return LoginType.loginFailure;
		}
		setParametersToRequest(request, userName, null, null);
		clearSessionIdFromCookie(request, response);
		mockProccessLoginSuccess(request, response, user, oldSession);

		return LoginType.loginSuccess;
	}

	private void mockProccessLoginSuccess(HttpServletRequest request,
			HttpServletResponse response, User user, Session oldSession) {
		// 先把要更换的用户在线的session都fire掉
		// fireLoginedUser(user.getUserName());
		mockGenerateLoginedSession(request, response, user, oldSession);
		// updateLoginUser(request, user);
		systemLogService.log(
				oldSession.getUserName() + "切换为" + user.getUserName() + "成功!",
				ModelType.LOGGIN, OperatorType.LOGIN);
		if (user.getFailureTimes() > 0) {
			user.setFailureTimes(0);
			permissionDubboService.updateFailureTimesById(user.getId(),
					user.getFailureTimes());
		}
	}

	/**
	 * 有两种情况：有oldSid和没有oldSid； 如果有oldSid，证明是更换用户后再次更换用户，那么删除当前session(即当前更换的用户)，
	 * 然后再新增新更换的用户的session(这个用户已经fire过);
	 * 如果没有oldSid，证明是第一次更换用户，则将当前的sessionId存入oldSid，
	 * 然后将新增更换的用户的session(这个用户已经fire过);
	 * */
	private void mockGenerateLoginedSession(HttpServletRequest request,
			HttpServletResponse response, User user, Session oldSession) {
		String oldSid = CookieUtil.getOldSessionId();
		String sessionId = CookieUtil.getSessionId();
		if (oldSid != null && !"".equals(oldSid.trim())) {
			sessionDao.deleteSessionBySessionId(sessionId);
		} else {
			oldSid = sessionId;
		}

		logger.debug("mockUserName:{} mockPassword:{}", user.getUserName(),
				user.getPassword());
		// 同时添加为要切换的用户添加session
		Session session = null;
		if (user.getUserName().equals(oldSession.getUserName())) {
			session = oldSession;
		} else {
			String userName = request.getParameter("switchUserName");

			session = addSession(user.getId(), userName, user.getName(),
					user.getOrganization(), getIpAddr(request),
					request.getRequestURI(), true, getIpAddr(request),
					user.getOrgInternalCode());
		}
		logger.debug("switchUser.getOrgInternalCode:",
				user.getOrgInternalCode());
		ThreadVariable.setSession(session);
		CookieUtil.putSessionIdInCookies(request, response,
				GlobalValue.LOGIN_SESSION_ID, session.getSessionId());
		CookieUtil.putSessionIdInCookies(request, response,
				GlobalValue.OLD_LOGIN_SESSION_ID, oldSid);
	}

	@Autowired
	private SessionDao sessionDao;
	@Autowired
	private PermissionDubboService permissionDubboService;
	@Autowired
	private SystemLogService systemLogService;

	public PageInfo<Session> getSessionsByOrgInternalCode(
			String orgInternalCode, int pageNum, int pageSize,
			String sortField, String order) {
		return this.sessionDao.getSessionsByOrgInternalCode(orgInternalCode,
				pageNum, pageSize, sortField, order);
	}

	@Override
	public Session findSessionBySessionId(String sessionId) {
		return this.sessionDao.findSessionBySessionId(sessionId);
	}

	@Override
	public Session updateSessionAccessTimeBySessionId(String id,
			Date accessDate, String lastLoginUrl, String accessIp) {
		return this.sessionDao.updateSessionAccessTimeBySessionId(id,
				accessDate, lastLoginUrl, accessIp);
	}

	@Override
	public void validateUserSessionByUserName(String userName) {
		sessionDao.validateUserSessionByUserName(userName);
	}

}