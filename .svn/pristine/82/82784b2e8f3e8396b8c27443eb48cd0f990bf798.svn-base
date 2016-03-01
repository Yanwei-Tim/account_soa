package com.tianque.job;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.GlobalValue;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;

public class JobHelper {
	public static void createMockAdminSession() {
		Session session = new Session();
		session.setUserName("admin");
		session.setUserRealName("超级管理员");
		session.setAccessIp("127.0.0.1");
		session.setAccessTime(CalendarUtil.now("yyyy-MM-dd hh:mm:ss"));
		session.setLoginDate(CalendarUtil.now("yyyy-MM-dd hh:mm:ss"));
		session.setLogin(true);
		session.setUserId(1L);
		session.setSessionId(GlobalValue.JOB_COOKIE);
		Organization org = new Organization();
		org.setId(1L);
		org.setOrgInternalCode("1.");
		session.setOrganization(org);
		session.setOrgInternalCode("1.");
		User user = new User();
		ThreadVariable.setUser(user);
		user.setOrganization(org);
		ThreadVariable.setSession(session);
		ThreadVariable.setOrganization(org);
	}

}
