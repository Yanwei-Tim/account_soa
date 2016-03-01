package com.tianque.component.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Permission;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.workBench.moduelClickCount.domain.ModuelClick;
import com.tianque.workBench.moduelClickCount.service.ModuelClickCountService;

public class ModuleClickStatisticsInterceptor extends AbstractInterceptor {

	@Autowired
	private PermissionDubboService permissionDubboService;
	@Autowired
	private ModuelClickCountService moduelClickCountService;

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		HttpServletRequest request = (HttpServletRequest) ai
				.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		String url = request.getRequestURI();
		if (url.startsWith("/hotModuel/")) {
			String parameter = request.getQueryString().split("&")[0];
			if(!(parameter==null||("").equals(parameter))){
				moduelClickCount(url+"?"+parameter);
			}else{
				moduelClickCount(url);
			}
		}
		return ai.invoke();
	}

	private void moduelClickCount(String url) {
		Permission permission = permissionDubboService
				.findPermissionByNormalUrl(url);
		if (permission != null) {
			ModuelClick moduelClick = moduelClickCountService
					.findModuelClickCountByPermissionIdAndUserId(permission
							.getId(), ThreadVariable.getSession().getUserId());
			if (moduelClick != null) {
				Long clickTimes = moduelClick.getClickTimes() + 1;
				moduelClick.setClickTimes(clickTimes);
				moduelClickCountService.updateModuelClickCount(moduelClick);
			} else {
				ModuelClick moduelClickTemp = new ModuelClick();
				moduelClickTemp.setPermissionId(permission.getId());
				moduelClickTemp.setUserId(ThreadVariable.getSession()
						.getUserId());
				moduelClickTemp.setClickTimes(1L);
				moduelClickCountService.addModuelClickCount(moduelClickTemp);
			}
		} else {
			return;
		}
	}

}

