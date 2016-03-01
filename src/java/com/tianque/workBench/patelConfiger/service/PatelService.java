package com.tianque.workBench.patelConfiger.service;



import java.util.List;

import com.tianque.service.util.PatelConfiger;
import com.tianque.service.util.TabPatel;

public interface PatelService {


	
	/**
	 * 获取常用配置
	 * @return
	 */
	public List<PatelConfiger> getPatelConfiger();
	
	/**
	 * 根据常用配置keyName获取标签配置
	 * @param keyName
	 * @return
	 */
	public List<TabPatel> getTabConfiger(String keyName);
	
	
	/**
	 * 添加常用配置
	 * @param keyName
	 * @param index
	 */
	public void addConfiguration(String keyName);
	
	/**
	 * 删除常用配置
	 * @param keyName
	 */
	public void deleteConfiguration(String keyName);
	
	/**
	 * 更新常用配置顺序
	 * @param keyNames
	 */
	public void updateConfigurationIndex(String[] keyNames);
	
	/**
	 * 添加标签配置
	 * @param keyName
	 * @param tabKeyName
	 * @param tabIndex
	 */
	public void addTabConfiguration(String keyName,String tabKeyName);
	
	/**
	 * 删除标签配置
	 * @param keyName
	 * @param tabKeyName
	 */
	public void deleteTabConfiguration(String keyName,String tabKeyName);
	
	/**
	 * 更新标签顺序
	 * @param keyName
	 * @param tabKeyNames
	 */
	public void updateTabConfigurationIndex(String keyName,String[] tabKeyNames);
	/**
	 * 获取所有配置keyName
	 * @param userId
	 * @return
	 */
	public List<String> getPatelConfigerKeyName();
	
	/**
	 * 删除用户配置
	 * @param userId
	 */
	public void deleteConfiguration(Long userId);
}
