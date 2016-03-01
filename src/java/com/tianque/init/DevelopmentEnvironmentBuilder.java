package com.tianque.init;

import net.rubyeye.xmemcached.XMemcachedClient;

import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.init.impl.CreateSessionForTestInitialization;
import com.tianque.init.impl.DatabaseInitialization;
import com.tianque.init.impl.DestoryCacheConnection;
import com.tianque.init.impl.GlobalSettingInitialization;
import com.tianque.init.impl.OrganizationInitialization;
import com.tianque.init.impl.PermissionXmlInit;
import com.tianque.init.impl.SystemPropertiesInitialization;
import com.tianque.init.impl.UserRoleInitialization;
import com.tianque.init.util.ApplicationContextFactory;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;
import com.tianque.userAuth.api.PropertyDomainDubboService;

public class DevelopmentEnvironmentBuilder extends InitializationsRunner {

	public DevelopmentEnvironmentBuilder() throws Exception {
		ApplicationContextFactory.getInstance().getApplicationContext(
				ContextType.development);
		addDefaultInitializations();
	}

	/**
	 * 默认数据初始化
	 * 
	 * @throws Exception
	 */
	private void addDefaultInitializations() throws Exception {
		new DatabaseInitialization(ContextType.development).init();
		getXMemcachedClient().flushAll();
		addInitialization(new CreateSessionForTestInitialization());
		addInitialization(new SystemPropertiesInitialization(
				getPropertyDomainService(), getPropertyDictService()));
		addInitialization(new PermissionXmlInit(getPermissionService()));
		addInitialization(new OrganizationInitialization(
				getOrganizationService(), getPropertyDictService()));
		addInitialization(new UserRoleInitialization(getPermissionService(),
				getOrganizationService(), getPropertyDictService()));
		addInitialization(new GlobalSettingInitialization(
				getGlobalSettingService()));
		addInitialization(new DestoryCacheConnection(getXMemcachedClient()));
	}

	public void builderTestEnv() throws Exception {
		executeInitialization();
		logger.info("开发环境初始化结束!");
		System.exit(0);
	}

	private XMemcachedClient getXMemcachedClient() {
		return (XMemcachedClient) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "memcachedClient");
	}

	private GlobalSettingService getGlobalSettingService() {
		return (GlobalSettingService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "globalSettingService");
	}

	private PermissionDubboService getPermissionService() {
		return (PermissionDubboService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "permissionDubboService");
	}

	private OrganizationDubboRemoteService getOrganizationService() {
		return (OrganizationDubboRemoteService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "organizationDubboService");
	}

	private PropertyDictDubboService getPropertyDictService() {
		return (PropertyDictDubboService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "propertyDictDubboService");
	}

	private PropertyDomainDubboService getPropertyDomainService() {
		return (PropertyDomainDubboService) ApplicationContextFactory.getInstance()
				.getBean(ContextType.development, "propertyDomainDubboService");
	}

}
