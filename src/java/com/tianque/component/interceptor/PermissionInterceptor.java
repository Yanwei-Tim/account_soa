package com.tianque.component.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tianque.component.SessionManager;
import com.tianque.core.util.GlobalValue;

public class PermissionInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		String action = ai.getProxy().getActionName();
		Class c = ai.getAction().getClass();
		Method method = c.getDeclaredMethod(ai.getProxy().getMethod());
		String className = c.getName();
		if (-1 != c.getName().indexOf('$')) {
			className = c.getName().substring(0, c.getName().indexOf('$'));
		}
		HttpServletRequest request = (HttpServletRequest) ai
				.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		if (sessionManager.havePermission(Class.forName(className).getMethod(
				method.getName()), action, request)) {
			return ai.invoke();
		} else {
			return GlobalValue.NOT_HAVE_PERMISSION_RESULT;
		}
	}

	@Autowired
	private SessionManager sessionManager;
}
