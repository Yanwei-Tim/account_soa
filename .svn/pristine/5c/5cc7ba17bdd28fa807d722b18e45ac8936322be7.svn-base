package com.tianque.init.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.init.Initialization;

public class CreateSessionForTestInitialization implements Initialization{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	public CreateSessionForTestInitialization(){
	}

	@Override
	public void init() throws Exception {
		Session session = new Session();
		session.setUserName("admin");
		session.setUserRealName("超级管理员");
		session.setAccessIp("127.0.0.1");
		session.setAccessTime(CalendarUtil.now("yyyy-MM-dd hh:mm:ss"));
		session.setLoginDate(CalendarUtil.now("yyyy-MM-dd hh:mm:ss"));
		session.setLoginDate(CalendarUtil.now("yyyy-MM-dd hh:mm:ss"));
		session.setLogin(true);
		session.setUserId(1L);
		session.setSessionId(UUID.randomUUID().toString());
		Organization org = new Organization();
		org.setId(1L);
		org.setOrgInternalCode("1.1.");
		session.setOrganization(org);
		session.setOrgInternalCode("1.1.");
//		session = getSessionDao().addSession(session);
		User user = new User();
		user.setUserName("admin");
		user.setOrganization(org);
		ThreadVariable.setUser(user);
		ThreadVariable.setSession(session);
		logger.info("创建测试登录完成!");
	}

}
