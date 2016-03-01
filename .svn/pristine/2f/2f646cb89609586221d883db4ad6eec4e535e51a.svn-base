package com.tianque.core.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ParametersTrimFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		filterChain.doFilter(new ParametersTrimRequest(httpServletRequest), response);
	}

	private class ParametersTrimRequest extends HttpServletRequestWrapper {

		public ParametersTrimRequest(HttpServletRequest request) {
			super(request);
		}
		
		@Override
		public Map getParameterMap() {
			Map parameterMap = super.getParameterMap();
			Map map=new HashMap();
			Iterator<Entry> iterator = parameterMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry entry = iterator.next();
				Object value =entry.getValue();
				if (value != null) {
					String[] arrayValue = (String[]) value;
					for (int i = 0; i < arrayValue.length; i++) {
						if (arrayValue[i] != null) {
							arrayValue[i] = arrayValue[i].trim();
						}
					}
					map.put(entry.getKey(), arrayValue);
				}
			}
			return map;
		}
		
		public String[] getParameterValues(String parameter) {
			String[] results = super.getParameterValues(parameter);
			if (results == null)
				return null;
			int count = results.length;
			String[] trimResults = new String[count];
			for (int i = 0; i < count; i++) {
				trimResults[i] = results[i].trim();
			}
			return trimResults;
		}
		
		public String getParameter(String parameter) {
			String result = super.getParameter(parameter);
			if (result == null)
				return null;
			return result.trim();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
