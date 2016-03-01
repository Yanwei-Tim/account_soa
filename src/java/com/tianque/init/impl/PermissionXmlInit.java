package com.tianque.init.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.tianque.core.util.ConvertXmlAndBean;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.domain.Permission;
import com.tianque.init.Initialization;
import com.tianque.init.util.Project;
import com.tianque.init.vo.PermissionConfig;
import com.tianque.init.vo.PermissionObject;
import com.tianque.init.xml.XmlUtil;
import com.tianque.userAuth.api.PermissionDubboService;

public class PermissionXmlInit implements Initialization {
	private PermissionDubboService permissionDubboService;

	public PermissionXmlInit(PermissionDubboService permissionDubboService) {
		this.permissionDubboService = permissionDubboService;
	}

	@Override
	public void init() throws Exception {
		String projectName = GridProperties.CURRENT_PROJECT;
		String defaultXml = null;
		if (com.tianque.init.util.Project.YANGFANGDIAN.toString()
				.equalsIgnoreCase(projectName)) {
			defaultXml = "permissionConfig_yfd.xml";
		} else if (Project.XNCX.toString().equalsIgnoreCase(projectName)) {
			defaultXml = "permissionConfig_xncx.xml";
		} else {
			defaultXml = "permissionConfig.xml";
		}
		initPermission(defaultXml);

		try {
			InputStream[] xmls = XmlUtil.getPermissionInputSteams();
			for (InputStream xml : xmls) {
				initPermission(xml);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		permissionDubboService.setIndexIdPermission(-1L);

	}

	/**
	 * use the PermissionConfig Object from xml init it into the database
	 * 
	 * author:fuhao
	 * 
	 * @throws Exception
	 */
	public void initPermission(String xml) throws Exception {
		PermissionConfig permissionConfig = getPermissionConfig(xml);
		if (null == permissionConfig) {
			throw new Exception("permissionConfig为空");
		}
		createDataInDB(permissionConfig);

	}

	public void initPermission(InputStream inputStream) throws Exception {
		PermissionConfig permissionConfig = getPermissionConfig(inputStream);
		if (null == permissionConfig) {
			throw new Exception("permissionConfig为空");
		}
		createDataInDB(permissionConfig);

	}

	/**
	 * set data in DB,parentid is null;
	 */
	public void createDataInDB(PermissionConfig permissionConfig) {
		ArrayList permissionObjects = permissionConfig.getPermission();
		for (int i = 0; permissionObjects != null && i < permissionObjects.size(); i++) {
			PermissionObject permissionObject = (PermissionObject) permissionObjects.get(i);
			Permission permission = new Permission();
			permission.setCname(permissionObject.getCname());
			permission.setEname(permissionObject.getEname());
			permission.setPermissionType(permissionObject.getPermissionType());
			permission.setModuleName(permissionObject.getModuleName());
			permission.setEnable(true);
			permission.setIndexId(new Long(i));
			permission = permissionDubboService.addPermission(permission);
			insertData(permissionObject.getPermissionObject(), permission);
		}
	}

	/**
	 * set data in DB,parentid is not null;
	 */
	public void insertData(ArrayList<PermissionObject> permissionObjects,
			Permission parent) {
		if (permissionObjects == null) {
			return;
		}
		for (int i = 0; i < permissionObjects.size(); i++) {
			PermissionObject permissionObject = permissionObjects.get(i);
			Permission permission = new Permission();
			permission.setCname(permissionObject.getCname());
			permission.setEname(permissionObject.getEname());
			permission.setParent(parent);
			permission.setModuleName(permissionObject.getModuleName());
			permission.setEnable(true);
			permission.setPermissionType(permissionObject.getPermissionType());
			permission.setNormalUrl(permissionObject.getNormalUrl());
			permission.setLeaderUrl(permissionObject.getLeaderUrl());
			permission.setIndexId(new Long(i));
			permission = permissionDubboService.addPermission(permission);
			if (null == permissionObject.getPermissionObject()) {
				continue;
			}
			insertData(permissionObject.getPermissionObject(), permission);
		}
	}

	/**
	 * 
	 * @return permissionConfig object
	 */
	public PermissionConfig getPermissionConfig(String xml) {
		try {
			ConvertXmlAndBean xmlToBean = new ConvertXmlAndBean();
			PermissionConfig permissionConfig = (PermissionConfig) xmlToBean
					.xmlToBean(getPermissionConfigPath(xml),
							PermissionConfig.class, getMapInputPermissionPath());
			return permissionConfig;
		} catch (Exception e) {
			System.err.println("problem in execute!");
			System.out.println(e.getCause().toString());
			return null;
		}
	}

	public PermissionConfig getPermissionConfig(InputStream inputStream) {
		try {
			ConvertXmlAndBean xmlToBean = new ConvertXmlAndBean();
			PermissionConfig permissionConfig = (PermissionConfig) xmlToBean
					.xmlToBean(new InputStreamReader(inputStream),
							PermissionConfig.class, getMapInputPermissionPath());
			return permissionConfig;
		} catch (Exception e) {
			System.err.println("problem in execute!");
			System.out.println(e.getCause().toString());
			return null;
		}
	}

	protected String getPermissionConfigPath(String xml) {
		return xml;
	}

	protected String getMapInputPermissionPath() {
		return FileUtil.getWebRoot()
				+ "/WEB-INF/classes/mapInputPermission.xml";
	}
}
