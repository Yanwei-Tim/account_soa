package com.tianque.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.tianque.core.util.ConvertXmlAndBean;
import com.tianque.core.util.FileUtil;

/**
 * 工作台常用设置
 * 
 * @author pursuer
 * 
 */
public final class PatelConfiger {

	public final static String INFORMATIONTRAIN = "informationTrain";

	public final static String BASICINFORMATION = "basicInformation";

	public final static String INTERACTIONMANAGEMENT = "interactionManagement";

	public final static String ISSUE = "issue";

	public final static String STATANALYSE = "statAnalyse";

	public final static String STATANALYSE_ACTUALPOPULATION = "statAnalyse_actualPopulation";

	public final static String STATANALYSE_OVERSEAPEOPLE = "statAnalyse_overseaPeople";

	public final static String STATANALYSE_IMPORTANTPERSON = "statAnalyse_importantPerson";

	public final static String STATANALYSE_ATTENTIONPERSON = "statAnalyse_attentionperson";

	public final static String STATANALYSE_NURTURESWOMEN = "statAnalyse_nurturesWomen";

	public final static String STATANALYSE_HOUSINFOMANAGE = "statAnalyse_housinfoManage";

	public final static String STATANALYSE_IMPORTPLACE = "statAnalyse_importPlace";

	public final static String STATANALYSE_ACTUALCOMPANY = "statAnalyse_actualCompany";

	public final static String STATANALYSE_DOUBLENEWORGANIZATIONS = "statAnalyse_doubleNewOrganizations";

	public final static String STATANALYSE_PEOPLELOG = "statAnalyse_peopleLog";

	public final static String STATANALYSE_ISSUE = "statAnalyse_issue";

	public final static String STATANALYSE_WORKINGRECORDMENU = "statAnalyse_workingRecordMenu";

	public final static String WORKINGRECORDMENU = "workingRecordMenu";

	public final static Map<String, PatelConfiger> allCatalogs = new HashMap<String, PatelConfiger>();

	public static ArrayList<PatelConfiger> patelConfigerAll = new ArrayList<PatelConfiger>();
	static {
		init();

	}

	/** 标识符 **/
	private String keyName;

	/** 中文名称 **/
	private String cname;

	/** 链接地址 **/
	private String url;

	/** 权限名称 **/
	private String ename;

	/** 页面类型（1 信息直通车页面 2 其他页面） **/
	private int type;
	
	/**最大化页面（页面类型为1是）**/
	private String maxUrl;

	private ArrayList<TabPatel> tabPatels;

	private ArrayList<PatelConfiger> patelConfigerChild;

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	private static void init() {
		try {
			ConvertXmlAndBean xmlToBean = new ConvertXmlAndBean();
			PatelConfigers patelConFiger = (PatelConfigers) xmlToBean
					.xmlToBean(getPatelConfigPath(), PatelConfigers.class,
							getMapInputPatelPath());
			initMap(patelConFiger);
		} catch (Exception e) {
			System.err.println("problem in execute!");
			System.out.println(e.getCause().toString());
		}
	}

	private static void initMap(PatelConfigers patelConfigers) {
		String oldName = "";
		for (PatelConfiger patelConfiger : patelConfigers.getPatelConfigers()) {
			allCatalogs.put(patelConfiger.getKeyName(), patelConfiger);
			if (oldName.split("_")[0].equals(patelConfiger.getKeyName().split(
					"_")[0])) {
				PatelConfiger temp = patelConfigerAll.get(patelConfigerAll
						.size() - 1);
				if (temp.getPatelConfigerChild() == null) {
					ArrayList<PatelConfiger> tempChild = new ArrayList<PatelConfiger>();
					temp.setPatelConfigerChild(tempChild);
				}
				temp.getPatelConfigerChild().add(patelConfiger);
			} else {
				patelConfigerAll.add(patelConfiger);
			}
			oldName = patelConfiger.getKeyName();
		}

	}

	protected static String getPatelConfigPath() {
		return FileUtil.getWebRoot() + "/WEB-INF/classes/PatelConfiger.xml";
	}

	protected static String getMapInputPatelPath() {
		return FileUtil.getWebRoot() + "/WEB-INF/classes/mapInputPatel.xml";
	}

	public static void main(String[] args) {
		for (String string : PatelConfiger.allCatalogs.keySet()) {
			System.err.println(string);
		}
	}

	public ArrayList<TabPatel> getTabPatels() {
		return tabPatels;
	}

	public void setTabPatels(ArrayList<TabPatel> tabPatels) {
		this.tabPatels = tabPatels;
	}

	public ArrayList<PatelConfiger> getPatelConfigerChild() {
		return patelConfigerChild;
	}

	public void setPatelConfigerChild(
			ArrayList<PatelConfiger> patelConfigerChild) {
		this.patelConfigerChild = patelConfigerChild;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMaxUrl() {
		return maxUrl;
	}

	public void setMaxUrl(String maxUrl) {
		this.maxUrl = maxUrl;
	}
	
}
