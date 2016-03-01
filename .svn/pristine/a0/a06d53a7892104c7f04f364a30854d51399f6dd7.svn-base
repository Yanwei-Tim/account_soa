package com.tianque.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tianque.core.cache.service.CacheService;
import com.tianque.core.cache.util.CacheKeyGenerator;
import com.tianque.core.util.GlobalValue;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

public class ControllerHelper {
	private static Logger logger = LoggerFactory
			.getLogger(ControllerHelper.class);

	public static PageInfo processAllOrgRelativeName(PageInfo pageInfo,
			OrganizationDubboRemoteService organizationDubboService, String[] orgPropertyNames,
			Long organizationId) {
		if (pageInfo == null || pageInfo.getResult() == null
				|| pageInfo.getResult().size() == 0)
			return pageInfo;
		processAllOrgRelativeName(pageInfo.getResult(), organizationDubboService,
				orgPropertyNames, organizationId);
		return pageInfo;
	}

	public static List processAllOrgRelativeName(List list,
			OrganizationDubboRemoteService organizationDubboService, String[] orgPropertyNames,
			Long organizationId) {
		if (list == null || list.size() == 0)
			return list;
		try {
			Map<Long, String> cacheValues = new HashMap();
			List<Method> orgMethods = getOrganizationMethods(list.get(0)
					.getClass(), orgPropertyNames);
			for (Object object : list) {
				for (Method readOrgMethod : orgMethods) {
					processSingleOrganizationRelativeName(readOrgMethod,
							object, cacheValues, organizationDubboService,
							organizationId);
				}
			}
		} catch (Exception e) {
			logger.error("processAllOrgRelativeName错误", e);
			throw new RuntimeException(e);
		}
		return list;
	}

	public static List processAllOrgName(List list,
			OrganizationDubboRemoteService organizationDubboService, String[] orgPropertyNames) {
		if (list == null || list.size() == 0)
			return list;
		try {
			Map<Long, String> cacheValues = new HashMap();
			List<Method> orgMethods = getOrganizationMethods(list.get(0)
					.getClass(), orgPropertyNames);
			for (Object object : list) {
				for (Method readOrgMethod : orgMethods) {
					processSingleOrganizationName(readOrgMethod, object,
							cacheValues, organizationDubboService);
				}
			}
		} catch (Exception e) {
			logger.error("processAllOrgName错误", e);
			throw new RuntimeException(e);
		}
		return list;
	}

	public static Organization proccessRelativeOrgNameByOrg(
			Organization organization, OrganizationDubboRemoteService organizationDubboService) {
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		if (organization.getParentOrg() != null) {
			organization.setOrgName(getOrganizationRelativeName(organization
					.getId(), organizationDubboService));
		}
		return organization;
	}

	public static Organization proccessRelativeOrgNameByOrgId(Long orgId,
			OrganizationDubboRemoteService organizationDubboService) {
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		if (organization.getParentOrg() != null) {
			organization.setOrgName(getOrganizationRelativeName(organization
					.getId(), organizationDubboService));
		}
		return organization;
	}

	private static void processSingleOrganizationRelativeName(
			Method readOrgMethod, Object object, Map<Long, String> cacheValues,
			OrganizationDubboRemoteService organizationDubboService, Long organizationId)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Organization organization = (Organization) readOrgMethod.invoke(object);
		if (organization != null) {
			String orgRelativeName = cacheValues.get(organization.getId());
			if (orgRelativeName == null) {
				Organization org = organizationDubboService
						.getSimpleOrgById(organization.getId());
				if (org != null && org.getParentOrg() == null) {
					orgRelativeName = org.getOrgName();
					// if (org.getParentOrg() == null){
					// orgRelativeName=org.getOrgName();
					// }else{
					// orgRelativeName =
					// ControllerHelper.getRelativeOrgNameByOrgId(organization.getId(),
					// organizationService);
					// }
					// cacheValues.put(organization.getId(),orgRelativeName);
				}
				if (org != null && org.getParentOrg() != null) {
					orgRelativeName = ControllerHelper
							.getRelativeOrgNameListByOrgId(
									organization.getId(), organizationDubboService,
									organizationId);
				}
				cacheValues.put(organization.getId(), orgRelativeName);
			}
			organization.setOrgName(orgRelativeName);
		}

	}

	private static void processSingleOrganizationName(Method readOrgMethod,
			Object object, Map<Long, String> cacheValues,
			OrganizationDubboRemoteService organizationDubboService)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Organization organization = (Organization) readOrgMethod.invoke(object);
		if (organization == null)
			return;
		String orgName = cacheValues.get(organization.getId());
		if (orgName == null) {
			orgName = organizationDubboService
					.getSimpleOrgById(organization.getId()).getOrgName();

			cacheValues.put(organization.getId(), orgName);
		}
		organization.setOrgName(orgName);
	}

	private static List getOrganizationMethods(Class clazz,
			String[] orgPropertyNames) {
		List<Method> result = new ArrayList<Method>();
		for (String propertyName : orgPropertyNames) {
			Method fieldReadMethod = getFieldReadMethod(clazz, propertyName,
					Organization.class);
			if (fieldReadMethod != null && isAccessible(fieldReadMethod)) {
				result.add(fieldReadMethod);
			}
		}
		return result;
	}

	private static boolean isAccessible(Method method) {
		return Modifier.isPublic(method.getModifiers())
				&& Modifier.isPublic(method.getDeclaringClass().getModifiers());
	}

	private static Method getFieldReadMethod(Class clazz, String propertyName,
			Class expireReturnClass) {
		Method result = null;
		try {
			result = clazz.getMethod("get"
					+ propertyName.substring(0, 1).toUpperCase()
					+ propertyName.substring(1));
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
			try {
				result = clazz.getMethod("is"
						+ propertyName.substring(0, 1).toUpperCase()
						+ propertyName.substring(1));
			} catch (SecurityException e1) {
			} catch (NoSuchMethodException e1) {
			}
		}
		if (result != null
				&& !expireReturnClass.isAssignableFrom(result.getReturnType())) {
			result = null;
		}
		return result;
	}

	public static String getRelativeOrgNameByOrgId(Long orgId,
			OrganizationDubboRemoteService organizationDubboService) {
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		if (organization.getParentOrg() != null) {
			return getOrganizationRelativeName(orgId, organizationDubboService);
		}
		return organization.getOrgName();
	}

	/**
	 * 列表查询方法
	 * 
	 * */
	public static String getRelativeOrgNameListByOrgId(Long orgId,
			OrganizationDubboRemoteService organizationDubboService, Long organizationId) {
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		if (organization.getParentOrg() != null) {
			return getOrganizationRelativeNameList(orgId, organizationDubboService,
					organizationId);
		}
		return organization.getOrgName();
	}

	/**
	 * 列表查询方法
	 * 
	 * */
	public static String getOrganizationRelativeNameList(Long orgId,
			OrganizationDubboRemoteService organizationDubboService, Long organizationId) {
		String path = "";
		Long actualRoot = ThreadVariable.getUser().getOrganization().getId();
		if ("test".equals(GlobalValue.environment)) {
			path = getRelativeNameList(orgId, organizationDubboService, actualRoot,
					organizationId);
		} else {
			ApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(ServletActionContext
							.getServletContext());
			CacheService cacheService = (CacheService) applicationContext
					.getBean("cacheService");
			if (orgId != null) {
				if (cacheService
						.get(CacheKeyGenerator.generateCacheKeyFromString(
								ControllerHelper.class, actualRoot + "_"
										+ orgId + "_" + organizationId)) != null) {
					path = cacheService.get(
							CacheKeyGenerator.generateCacheKeyFromString(
									ControllerHelper.class, actualRoot + "_"
											+ orgId + "_" + organizationId))
							.toString();
				} else {
					path = getRelativeNameList(orgId, organizationDubboService,
							actualRoot, organizationId);
					cacheService.set(CacheKeyGenerator
							.generateCacheKeyFromString(ControllerHelper.class,
									actualRoot + "_" + orgId + "_"
											+ organizationId), path);
				}
			}
		}
		return path;
	}

	/**
	 * 获取orgid在指定rootOrgid下的相对路径名，->分割
	 * 
	 * @return
	 */
	public static String getOrganizationRelativeName(Long orgId,
			OrganizationDubboRemoteService organizationDubboService) {
		String path = "";
		Long actualRoot = ThreadVariable.getUser().getOrganization().getId();
		if ("test".equals(GlobalValue.environment)) {
			path = getRelativeName(orgId, organizationDubboService, actualRoot);
		} else {
			ApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(ServletActionContext
							.getServletContext());
			CacheService cacheService = (CacheService) applicationContext
					.getBean("cacheService");
			if (orgId != null) {
				if (cacheService.get(CacheKeyGenerator
						.generateCacheKeyFromString(ControllerHelper.class,
								actualRoot + "_" + orgId)) != null) {
					path = cacheService.get(
							CacheKeyGenerator.generateCacheKeyFromString(
									ControllerHelper.class, actualRoot + "_"
											+ orgId)).toString();
				} else {
					path = getRelativeName(orgId, organizationDubboService,
							actualRoot);
					cacheService.set(CacheKeyGenerator
							.generateCacheKeyFromString(ControllerHelper.class,
									actualRoot + "_" + orgId), path);
				}
			}
		}
		return path;
	}

	private static String getRelativeName(Long orgId,
			OrganizationDubboRemoteService organizationDubboService, Long actualRoot) {
		String path;
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		path = org.getOrgName();
		if (!orgId.equals(actualRoot)) {
			while (org.getParentOrg() != null
					&& !org.getParentOrg().getId().equals(actualRoot)) {
				org = organizationDubboService.getSimpleOrgById(org.getParentOrg()
						.getId());
				path = org.getOrgName() + "->" + path;
			}
		}
		return path;
	}

	/**
	 * 列表查询方法
	 * */
	private static String getRelativeNameList(Long orgId,
			OrganizationDubboRemoteService organizationDubboService, Long actualRoot,
			Long organizationId) {
		String path;
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		path = org.getOrgName();
		if (!orgId.equals(actualRoot)) {
			while (org.getParentOrg() != null
					&& !org.getParentOrg().getId().equals(actualRoot)
					&& !org.getId().equals(organizationId)) {
				org = organizationDubboService.getSimpleOrgById(org.getParentOrg()
						.getId());
				path = org.getOrgName() + "->" + path;
				if (org.getId() == organizationId)
					break;
			}
		}
		if (organizationId != null) {
			StringBuffer strbuf = new StringBuffer("");
			String[] str = path.split("->");
			if (str.length > 3) {
				for (int i = 0; i < 3; i++) {
					strbuf.append(str[i]);
					if (i != 2) {
						strbuf.append("->");
					}
				}
				path = strbuf.toString();
			}
		}
		return path;
	}

}
