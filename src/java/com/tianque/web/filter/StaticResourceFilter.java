package com.tianque.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class StaticResourceFilter implements Filter{
	private long cacheMaxAge=24*3600*1000;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response=(HttpServletResponse)servletResponse;
        response.setHeader("Cache-Control", "max-age="+cacheMaxAge);  
        response.setDateHeader("Expires", cacheMaxAge);  
        chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
