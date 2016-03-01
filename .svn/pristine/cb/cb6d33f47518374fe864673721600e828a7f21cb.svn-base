package com.tianque.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Permission;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.workBench.moduelClickCount.domain.ModuelClick;
import com.tianque.workBench.moduelClickCount.service.ModuelClickCountService;

public class HotModuelFilter implements Filter {

	private FilterConfig filterConfig;
	private PermissionDubboService permissionDubboService;
	private ModuelClickCountService moduelClickCountService;

	@Override
	public void destroy() {
		filterConfig = null;
		permissionDubboService = null;
		moduelClickCountService = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String url = httpServletRequest.getServletPath();
		if(httpServletRequest.getQueryString().contains("&")){
			String [] temp = httpServletRequest.getQueryString().split("&");
			String parameter = new String();
			if(temp.length == 2){
				parameter = temp[0];
			} else if(temp.length > 2){
				parameter = temp[0];
				for (int i = 1; i < temp.length-1; i++) {
					parameter += temp[i];
				}
			}
			if(!(parameter==null||("").equals(parameter))){
				url = url+"?"+parameter;
			}
		}
		String newUrl = url.replaceFirst("/hotModuel", "");
		if (!"".equals(newUrl)) {
			moduelClickCount(url);
//			httpServletResponse.sendRedirect(newUrl);
			request.getRequestDispatcher(newUrl).forward(request,response);
			return;
		}

		filterChain.doFilter(request, response);

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

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		getPermissionService();
		getModuelClickCountService();
	}

	public PermissionDubboService getPermissionService() {
		if (this.permissionDubboService == null) {
			WebApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(filterConfig.getServletContext());
			AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext
					.getAutowireCapableBeanFactory();
			this.permissionDubboService = (PermissionDubboService) autowireCapableBeanFactory
					.getBean("permissionDubboService");
		}
		return permissionDubboService;
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public ModuelClickCountService getModuelClickCountService() {
		if (this.moduelClickCountService == null) {
			WebApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(filterConfig.getServletContext());
			AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext
					.getAutowireCapableBeanFactory();
			this.moduelClickCountService = (ModuelClickCountService) autowireCapableBeanFactory
					.getBean("moduelClickCountService");
		}
		return moduelClickCountService;
	}

}