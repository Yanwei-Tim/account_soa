package com.tianque.core.struts;

import com.opensymphony.xwork2.ActionInvocation;
import com.tianque.core.util.GridProperties;

public class StrutsActionProxy extends
		org.apache.struts2.impl.StrutsActionProxy {

	public StrutsActionProxy(ActionInvocation inv, String namespace,
			String actionName, String methodName, boolean executeResult,
			boolean cleanupContext) {
		super(inv, namespace, actionName, methodName, executeResult,
				cleanupContext);
	}

	@Override
	protected void prepare() {
		if (null != configuration.getRuntimeConfiguration().getActionConfig(
				namespace + "/" + GridProperties.CURRENT_PROJECT, actionName)) {
			namespace = namespace + "/" + GridProperties.CURRENT_PROJECT;
		}
		super.prepare();
	}

}
