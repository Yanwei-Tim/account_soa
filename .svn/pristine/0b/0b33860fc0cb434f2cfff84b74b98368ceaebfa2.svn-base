package com.tianque.core.struts;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;

public class StrutsActionProxyFactory extends
		org.apache.struts2.impl.StrutsActionProxyFactory {

	@Override
	public ActionProxy createActionProxy(ActionInvocation inv,
			String namespace, String actionName, String methodName,
			boolean executeResult, boolean cleanupContext) {
		StrutsActionProxy proxy = new StrutsActionProxy(inv, namespace, actionName, methodName, executeResult, cleanupContext);
        container.inject(proxy);
        proxy.prepare();
		return proxy;

	}
}
