package com.tianque.core.web.tag;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.DateUtil;
import com.tianque.core.util.GridProperties;

public class SysBeginUseYearOptionsTag extends TagSupport {
	private static Logger logger = LoggerFactory.getLogger(SysBeginUseYearOptionsTag.class);
	@Override
	public int doStartTag() throws JspException {
		Long sysBeginYear = Long.valueOf(DateUtil.toString(DateUtil.parseDate(
				GridProperties.SYS_BEGIN_USE_YEAR, "yyyyMM"), "yyyy"));
		Long nowYear = Long.valueOf(DateUtil.toString(new Date(), "yyyy"));
		StringBuffer options = new StringBuffer();
		for (;nowYear>=sysBeginYear;nowYear--) {
			options.append("<option value='"+nowYear+"'>"+nowYear+"</option>");
		}
		JspWriter out = this.pageContext.getOut();
		try {
			out.print(options.toString());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("异常信息", e);
		}
		return Tag.EVAL_PAGE;
	}
}
