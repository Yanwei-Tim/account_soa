package com.tianque.web.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Permission;
import com.tianque.domain.Session;
import com.tianque.userAuth.api.PermissionDubboService;

public class JugePermissionTag extends TagSupport {
	private static Logger logger = LoggerFactory
			.getLogger(JugePermissionTag.class);
	private String ename;

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int doStartTag() throws JspException {

		if (ename == null || ename.length() == 0) {
			return Tag.SKIP_BODY;
		}

		Session session = (Session) ThreadVariable.getSession();
		if (session == null || session.getUserId() == null) {
			return Tag.SKIP_BODY;
		}

		String[] enames = ename.split(",");

		try {
			boolean hasSkipBody = true;
			for (int i = 0; i < enames.length; i++) {
				PermissionDubboService permissionDubboService = (PermissionDubboService) WebApplicationContextUtils
						.getWebApplicationContext(
								pageContext.getServletContext()).getBean(
								"permissionDubboService");
				Permission permission = permissionDubboService
						.findPermissionByEname(enames[i]);
				if (null != permission && null != permission.getEname()) {
					pageContext.getRequest().setAttribute("name",
							permission.getCname());
					hasSkipBody = false;
					break;
				}
			}
			if (hasSkipBody) {
				return Tag.SKIP_BODY;
			}
		} catch (Exception e) {
			logger.error("JugePermissionTag报错", e);
		}

		if (ThreadVariable.getUser() != null
				&& ThreadVariable.getUser().isAdmin()) {
			return Tag.EVAL_BODY_INCLUDE;
		}

		if (ThreadVariable.getPermissionEnameList() != null) {
			if (isHasPermissionFromThreadLocal()) {
				return Tag.EVAL_BODY_INCLUDE;
			} else {
				return Tag.SKIP_BODY;
			}
		}

		boolean isEvalBodyInclude = false;
		PermissionDubboService permissionDubboService = getPermissionService();
		for (String name : ename.split(",")) {
			if (permissionDubboService.isUserHasPermission(session.getUserId(),
					name)) {
				isEvalBodyInclude = true;
				break;
			}
		}

		if (isEvalBodyInclude) {
			return Tag.EVAL_BODY_INCLUDE;
		}
		return Tag.SKIP_BODY;
	}

	private boolean isHasPermissionFromThreadLocal() {
		String[] enames = ename.split(",");
		List<String> enameList = ThreadVariable.getPermissionEnameList();
		for (int i = 0; i < enames.length; i++) {
			if (enameList.contains(enames[i])) {
				return true;
			}
		}
		return false;
	}

	private PermissionDubboService getPermissionService() {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.pageContext.getServletContext());
		PermissionDubboService permissionDubboService = (PermissionDubboService) applicationContext
				.getBean("permissionDubboService");
		return permissionDubboService;
	}

	@Override
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;
	}
}
