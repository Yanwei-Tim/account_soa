package com.tianque.component.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tianque.component.SessionManager;
import com.tianque.controller.LoginType;
import com.tianque.core.util.GlobalValue;

public class FirstLoginInterceptor extends AbstractInterceptor {
	@Autowired
	private SessionManager sessionManager;

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		
		if(mockNotJugde(ai) && (doNotFirstLoginJugde(ai) && sessionManager.isFirstLogin())) {
			return LoginType.firstLogin.name();
		}
		
		return ai.invoke();
	}

	private boolean doNotFirstLoginJugde(ActionInvocation ai) {
		for (String path:GlobalValue.DO_NOT_FIRST_LOGIN_VALIDATE_PATH) {
			if(path.equals(ai.getProxy().getActionName())){
				return false;
			}
		}
		return true;
	}
	
	private boolean mockNotJugde(ActionInvocation ai){
		HttpServletRequest request = (HttpServletRequest) ai.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		Cookie[] cookies = request.getCookies();
		if(null==cookies || 0==cookies.length)
			return true;
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (GlobalValue.OLD_LOGIN_SESSION_ID.equals(cookie.getName())) {
				return false;
			}
		}
		
		return true;
	}
}
