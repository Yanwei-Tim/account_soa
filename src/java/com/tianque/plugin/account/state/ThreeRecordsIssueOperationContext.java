package com.tianque.plugin.account.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.domain.Organization;

public class ThreeRecordsIssueOperationContext {
	private Map<String, Object> context = new HashMap<String, Object>();
	private final String TARGET_ORG_KEY = ThreeRecordsIssueOperationContext.class
			.getName()
			+ "_TARGET_ORG";
	private final String CONTENT_KEY = ThreeRecordsIssueOperationContext.class
			.getName()
			+ "_CONTENT";
	private final String OPERATOR_KEY = ThreeRecordsIssueOperationContext.class
			.getName()
			+ "_OPERATOR_KEY";
	private final String TELL_ORG_KEY = ThreeRecordsIssueOperationContext.class
			.getName()
			+ "_TELL_ORG_KEY";
	private final String CURRENT_OPERATE_ORG_KEY = ThreeRecordsIssueOperationContext.class
			.getName()
			+ "_CURRENT_OPERATE_ORG_KEY";
	public static final String SUPERVISE_LEVEL_KEY = ThreeRecordsIssueOperationContext.class
			.getName()
			+ "_SUPERVISE_LEVEL_KEY";

	private final String NOTICE_ORG_KEY = ThreeRecordsIssueOperationContext.class
			.getName()
			+ "_NOTICE_ORG_KEY";

	public ThreeRecordsIssueOperationContext(Organization loginedOrg,
			Organization target, String dealMsg, String operator) {
		context.put(TARGET_ORG_KEY, target);
		context.put(CONTENT_KEY, dealMsg);
		context.put(OPERATOR_KEY, operator);
		context.put(CURRENT_OPERATE_ORG_KEY, loginedOrg);
	}

	public Organization getTargetOrg() {
		return (Organization) context.get(TARGET_ORG_KEY);
	}

	public Organization getCurrentLoginedOrg() {
		return (Organization) context.get(CURRENT_OPERATE_ORG_KEY);
	}

	public String getOperationContent() {
		return (String) context.get(CONTENT_KEY);
	}

	public void addParameter(String key, Object value) {
		context.put(key, value);
	}

	public Object getParameter(String key) {
		return context.get(key);
	}

	public List<Organization> getTellOrgs() {
		return (List<Organization>) context.get(TELL_ORG_KEY);
	}

	public List<Organization> getNoticeOrgs() {
		return (List<Organization>) context.get(NOTICE_ORG_KEY);
	}

	public void addTellOrg(Organization org) {
		if (getTellOrgs() == null) {
			List<Organization> orgs = new ArrayList<Organization>();
			context.put(TELL_ORG_KEY, orgs);
		}
		getTellOrgs().add(org);
	}

	public void addNoticeOrg(Organization org) {
		if (getNoticeOrgs() == null) {
			List<Organization> orgs = new ArrayList<Organization>();
			context.put(NOTICE_ORG_KEY, orgs);
		}
		getNoticeOrgs().add(org);
	}

}
