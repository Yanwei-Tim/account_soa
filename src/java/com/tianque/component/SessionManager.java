package com.tianque.component;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianque.controller.LoginType;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Session;
import com.tianque.domain.User;

public interface SessionManager {
	public final static String MODULE_NAME = "登录子系统";

	public boolean isLogin(HttpServletRequest request,
			HttpServletResponse response);

	public boolean isFirstLogin();

	public void deleteSessionsWhenTimeOut();

	public LoginType login(HttpServletRequest request,
			HttpServletResponse response);

	public boolean havePermission(Method method, String action,
			HttpServletRequest request);

	public PageInfo<Session> getSessionsByOrgInternalCode(
			String orgInternalCode, int pageNum, int pageSize,
			String sortField, String order);

	public void logout(HttpServletRequest request, HttpServletResponse response);

	public void deleteSessionBySessionId(String sessionId);

	public Session addSession(Session session);

	public Session findSessionBySessionId(String sessionId);

	public Session updateSessionAccessTimeBySessionId(String id,
			Date accessDate, String lastLoginUrl, String accessIp);

	public void validateUserSessionByUserName(String userName);

	public LoginType mockLogin(HttpServletRequest request,
			HttpServletResponse response);

	public void proccessLoginSuccess(HttpServletRequest request,
			HttpServletResponse response, String userName, String password,
			User user);
}
