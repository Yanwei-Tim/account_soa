/*   
 * Copyright (c) 2014-2020 TianQue Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * TianQue. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with TianQue.
 *   
 */
package com.tianque.core.dubbo.filter;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.GlobalValue;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.exception.base.IllegalOperationException;
import com.tianque.init.impl.CreateSessionForTestInitialization;
import com.tianque.job.JobHelper;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.userAuth.api.SessionManagerDubboService;

/**
 * @ClassName: DubboRPCFilter
 * @Description: dubbo请求过滤器
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2015年3月9日 下午4:02:57
 */
public class DubboRPCFilter implements Filter {

	private static final String DUBBO_URL_PREFIX = "dubbo:";
	private static final String INIT_APP_COOKIE = "INIT_APP";

	private static SessionManagerDubboService sessionManager;
	private static PermissionDubboService permissionDubboService;

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation)
			throws RpcException {
		String accessUrl = invoker.getUrl().toString();
		if (!accessUrl.startsWith(DUBBO_URL_PREFIX)
				|| isNeedFilter(invoker.getInterface().getName(),
						invocation.getMethodName())) {
			return getResult(invoker, invocation);
		}
		String cookie = invocation.getAttachment("cookie");
		if (!StringUtil.isStringAvaliable(cookie)) {
			throw new IllegalOperationException("非法操作，cookie 不存在！");
		}
		if (INIT_APP_COOKIE.equals(cookie)) {
			try {
				new CreateSessionForTestInitialization().init();
			} catch (Exception e) {
				throw new IllegalOperationException("创建初始化session失败！");
			}
			return getResult(invoker, invocation);
		}
		if (cookie.startsWith(GlobalValue.JOB_COOKIE)) {
			JobHelper.createMockAdminSession();
			return getResult(invoker, invocation);
		}
		Session session = getSessionManagerService().findSessionBySessionId(
				cookie);
		if (session == null) {
			throw new IllegalOperationException("登录身份信息失效，请重新登录！");
		}
		session.setAccessTime(CalendarUtil.now("yyyy-MM-dd HH:mm:ss"));
		User user = getPermissionService().getSimpleUserById(
				session.getUserId());
		user.setOrganization(session.getOrganization());
		ThreadVariable.setUser(user);
		ThreadVariable.setOrganization(session.getOrganization());
		ThreadVariable.setSession(session);
		return getResult(invoker, invocation);
	}

	private Result getResult(Invoker<?> invoker, Invocation invocation) {
		return invoker.invoke(invocation);
	}

	private boolean isNeedFilter(String interfaceName, String methodName) {
		String urlName = interfaceName + "." + methodName;
		String[] whiteList = GridProperties.DUBBO_WHITE_LIST.split(";");
		for (String witeName : whiteList) {
			if (urlName.trim().equals(witeName)) {
				return true;
			}
		}
		return false;
	}

	private static SessionManagerDubboService getSessionManagerService() {
		if (sessionManager == null) {
			sessionManager = (SessionManagerDubboService) SpringBeanUtil
					.getBeanFromSpringByBeanName("sessionManagerDubboService");
		}
		return sessionManager;
	}

	private static PermissionDubboService getPermissionService() {
		if (permissionDubboService == null) {
			permissionDubboService = (PermissionDubboService) SpringBeanUtil
					.getBeanFromSpringByBeanName("permissionDubboService");
		}
		return permissionDubboService;
	}
}
