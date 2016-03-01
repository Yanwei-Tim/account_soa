package com.tianque.web.tag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Session;
import com.tianque.userAuth.api.PropertyDictDubboService;

/**
 * 数据字典格式化自定义标签 v1.1
 *
 * @author lianjing.bao
 *
 */
/**
 * demo: <script type="text/javascript"> <pop:formatterProperty name="gender"
 * domainName="性别" /> ....... .......
 * {name:'gender',label:'性别',sortable:false,formatter:genderFormatter}, jqgrid
 * 里面的formatter值为自定义标签中的name+"Formatter"
 *
 */
public class FormatterPropertyTag extends TagSupport {
	private static Logger logger = LoggerFactory
			.getLogger(FormatterPropertyTag.class);
	private static final long serialVersionUID = 3763261848194745083L;
	/**
	 * key 里面的值是对应PropertyDict里面的一个属性. 将PropertyDict.get'key'的值取出来， var
	 * orgLevelData = new Array(); orgLevelData[id]
	 */
	private String key = "id";
	/**
	 * label 里面的值是对应PropertyDict里面的一个属性. 将PropertyDict.get'label'的值取出来，
	 */
	private String label = "displayName";
	/**
	 * domain 中对应实体变量的名称.
	 */
	private String name = "";
	/**
	 * 对应PropertyDomain表中的domainName.
	 */
	private String domainName = "";

	public int doStartTag() throws JspException {
		if (name == null || name.length() == 0) {
			return Tag.SKIP_BODY;
		}
		Session session = (Session) ThreadVariable.getSession();
		if (session == null || session.getUserId() == null) {
			return Tag.SKIP_BODY;
		}
		PropertyDictDubboService propertyDictDubboService = getPropertyDictService();

		List<String> propertyTypes = getPropertyTypeByExpression();

		List<PropertyDict> propertyDicts = new ArrayList<PropertyDict>();
		//		String propertyType=getPropertyTypeByExpression();
		for (int i = 0; i < propertyTypes.size(); i++) {
			propertyDicts.addAll(propertyDictDubboService
					.findPropertyDictByDomainName(propertyTypes.get(i)));
		}

		toHTML(propertyDicts);

		return Tag.EVAL_PAGE;
	}

	/**
	 *
	 *
	 * @param codes
	 */
	private void toHTML(List<PropertyDict> propertyDicts) {
		JspWriter out = this.pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		sb.append(getArrayData(propertyDicts));
		sb.append(createFormatterFunction());
		try {
			out.print(sb.toString());
		} catch (Exception e) {
			logger.error("异常信息", e);
		}
	}

	/**
	 * 将所有的propertyDict转换成javascript数组
	 *
	 * @param propertyDicts
	 * @return
	 */
	private String getArrayData(List<PropertyDict> propertyDicts) {
		if (propertyDicts == null)
			return "";
		/**
		 * 由于自定义标签是非线程安全的，所以在设计中只能选择线程安全的类StringBuffer
		 * 来拼接字符串；考虑到由于数据量过大导致StringBuffer不断的申请内存和数据拷贝，
		 * 在下面的处理中加入了默认以数据项的个数乘以10的字符空间,以加强性能。
		 */
		StringBuffer sb = new StringBuffer(propertyDicts.size() <= 1 ? 16
				: propertyDicts.size() * 10);
		String arrayName = name + "Data";
		if (name.contains(".")) {
			arrayName = name.split("\\.")[name.split("\\.").length - 1]
					+ "Date";
		}
		String arrayKey = "";
		String arrayValue = "";
		sb.append("var " + arrayName + " = new Array();\n");
		try {
			for (PropertyDict propertyDict : propertyDicts) {
				arrayKey = BeanUtils.getProperty(propertyDict, key);
				arrayValue = BeanUtils.getProperty(propertyDict, label);
				sb.append("\t").append(arrayName).append("[").append(arrayKey)
						.append("]=\"").append(arrayValue).append("\";\n");
			}
		} catch (Exception e) {
			logger.error("异常信息", e);
		}

		return sb.toString();
	}

	private String createFormatterFunction() {
		StringBuffer sb = new StringBuffer(50);
		String arrayName = name + "Data";
		if (name.contains(".")) {
			arrayName = name.split("\\.")[name.split("\\.").length - 1]
					+ "Date";
			sb.append("function ")
					.append(name.split("\\.")[name.split("\\.").length - 1])
					.append("Formatter(el,options,rowData){\n");
			key = "id";
		} else {
			sb.append("function ").append(name)
					.append("Formatter(el,options,rowData){\n");
		}
		String arrayKey = name + "." + key;

		// if(!rowData.orgLevel||!rowData.orgLevel.id){
		// return "&nbsp;";
		// }
		sb.append("\tif(rowData['" + name + ".id']||(rowData.").append(name)
				.append("&&rowData.").append(name).append(".id)){\n");
		// if(rowData["orgLevel.id"]){
		// return orgLevelData[rowData["orgLevel.id"]];
		// }
		sb.append("\tif(rowData[\"").append(arrayKey).append("\"]){\n");
		sb.append("\t\treturn ").append(arrayName).append("[rowData[\"")
				.append(arrayKey).append("\"]];\n");
		sb.append("\t}\n");

		// return orgLevelData[rowData.orgLevel.id];
		sb.append("\treturn ").append(arrayName).append("[rowData.")
				.append(arrayKey).append("];\n");
		sb.append("}else{\n");
		sb.append("\t\treturn \"&nbsp;\"\n");
		sb.append("\t}}\n");
		return sb.toString();
	}

	private PropertyDictDubboService getPropertyDictService() {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.pageContext.getServletContext());

		PropertyDictDubboService propertyDictDubboService = (PropertyDictDubboService) applicationContext
				.getBean("propertyDictDubboService");
		return propertyDictDubboService;
	}

	/**
	 * 即保留对原来写类别中文名的支持，又支持OGNL表达式写法.（支持从前台传入多个值）
	 * 
	 * @return
	 */
	private List<String> getPropertyTypeByExpression() {
		List<String> list = new ArrayList<String>();
		String result = domainName;
		try {
			String[] domains = result.split(",");
			for (String st : domains) {
				OgnlContext context = new OgnlContext();
				Object parseExpression = Ognl.parseExpression(st);
				Object value = Ognl.getValue(parseExpression, context);
				if (value != null)
					result = value.toString();
				list.add(result);
			}
		} catch (OgnlException e) {
			logger.error("异常信息", e);
		}
		return list;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

}
