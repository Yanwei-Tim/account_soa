package com.tianque.web.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import ognl.Ognl;
import ognl.OgnlContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.IssueType;
import com.tianque.domain.Session;
import com.tianque.service.IssueTypeService;

/**
 * 服务办事类型下拉自定义标签
 * demo: '<select name="domain.gender.id" id="gender" class="form-txt">
 * <pop:OptionTag name="性别" defaultValue="${domain.gender.id}"/> </select>'
 */
public class IssueTypeSelectTag extends TagSupport {
	private static Logger logger = LoggerFactory.getLogger(IssueTypeSelectTag.class);
	private static final String EMPTY_OPTION_TEMPLATE = "<option value=''></option>";
	private static final String SELECTED_OPTION_TEMPLATE = "<option value='%s' selected>%s</option>";
	private static final String OPTION_TEMPLATE = "<option value='%s'>%s</option>";
	
	private String id = "";
	private String cls = "";
	private String afterChange="";
	private boolean havePlease=false;
	
	public String getAfterChange() {
		return afterChange;
	}

	public void setAfterChange(String afterChange) {
		this.afterChange = afterChange;
	}

	/**
	 * label 里面的值是对应IssueType里面的一个属性. 将IssueType.get'label'的值取出来，
	 * 放到<option >IssueType.get'label'</option>中.
	 */
	
	private String label = "issueTypeName";
	
	/**
	 * 对应 IssueTypeDomains 表中 domainName;
	 */
	private String domainName = "";
		
	private String name = "";
	
	/**
	 * 判断是否允许空值存在
	 */
	private boolean notNull = false;
	/**
	 * 设置默认选中值 
	 */
	private String defaultValue = "";
	
	public int doStartTag() throws JspException{
		if(domainName == null || "".equals(domainName)){
			return Tag.SKIP_BODY;
		}
		Session session = (Session) ThreadVariable.getSession();
		if (session == null || session.getUserId() == null) {
			return Tag.SKIP_BODY;
		}
		IssueTypeService issueTypeService = getIssueTypeService();
		domainName = getDomainNameByExpression();
		List<IssueType> issueTypes = issueTypeService.findIssueTypesByParentName(domainName);
		toHtml(issueTypes);
		return Tag.EVAL_PAGE;
	}

	private String getDomainNameByExpression() {
		String resultStr = domainName;
		try{
			OgnlContext context = new OgnlContext();
			Object parseExpression = Ognl.parseExpression(domainName);
			Object value = Ognl.getValue(parseExpression,context);
			if(value != null)
				resultStr = value.toString();
		}catch(Exception e){
			logger.error("异常信息", e);
		}
		return resultStr;
	}

	private void toHtml(List<IssueType> issueTypes) {
		JspWriter out = this.pageContext.getOut();
		StringBuffer sb = new StringBuffer(50*issueTypes.size());
		if("".equals(cls)){
			cls="form-select";
		}
		sb.append("<select name='"+name+"' id='"+id+"' class='"+cls+"'>");
		if (!notNull)
			sb.append(EMPTY_OPTION_TEMPLATE);
		try{
			for(IssueType issueType:issueTypes){
				if(defaultValue.equals(issueType.getId().toString())){
					sb.append(String.format(SELECTED_OPTION_TEMPLATE,issueType.getId(),issueType.getIssueTypeName()+","+issueType.getFullPinYin()+","+issueType.getSimplePinYin()));
				}else{
					sb.append(String.format(OPTION_TEMPLATE, issueType.getId(),issueType.getIssueTypeName()+","+issueType.getFullPinYin()+","+issueType.getSimplePinYin()));
				}
			}
			sb.append("</select>");
			sb.append("<script>"); 
			sb.append("$(document).ready(function(){"); 
			sb.append("$('#"+id+"').combobox(");
			if(!"".equals(afterChange)){
				sb.append("{'afterChange':function(id){if("+afterChange+"!=undefined){"+afterChange+"(id);}},isHavePlease:").append(havePlease).append("}");
			}
			sb.append(");"); 
			sb.append("});"); 
			sb.append("</script>");
			out.print(sb.toString());
		}catch(Exception e){
			logger.error("异常信息", e);
		}
	}

	private IssueTypeService getIssueTypeService() {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.pageContext.getServletContext());
		return (IssueTypeService)applicationContext.getBean("issueTypeService");
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isHavePlease() {
		return havePlease;
	}

	public void setHavePlease(boolean havePlease) {
		this.havePlease = havePlease;
	}

}
