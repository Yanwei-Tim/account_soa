package com.tianque.workBench.patelConfiger.dao;



import java.util.List;

public interface PatelDao {

	/**
	 * 常用获取配置
	 * @param userId
	 * @return
	 */
	public List<String> getKeyNamesByUserId(Long userId);
	
	
	/**
	 * 获取index
	 * @param userId
	 * @param keyName
	 * @return
	 */
	public Integer getIndexByUserIdAndKeyname(Long userId,String keyName);
	/**
	 * 获取每个常用配置下的子配置
	 * @param userId
	 * @param keyName
	 * @return
	 */
	public List<String> getTabConfiger(Long userId,String keyName);
	
	/**
	 * 删除全部配置
	 * @param userId
	 */
	public void deleteConfiguration(Long userId);
	/**
	 * 删除配置
	 * @param userId
	 * @param keyName
	 */
	public void deleteConfiguration(Long userId,String keyName);
	
	/**
	 * 删除子配置
	 * @param userId
	 * @param keyName
	 * @param tabKeyName
	 */
	public void deleteTabConfiguration(Long userId,String keyName,String tabKeyName);
	
	/**
	 * 获取当前排序最大值
	 * @return
	 */
	public Integer getCurrentMaxConfiger();
	
	/**
	 * 获取标签当前排序最大值
	 * @param keyName
	 * @return
	 */
	public Integer getCurrentMaxTabConfiger(String keyName);
	/**
	 * 创建配置
	 * @param userId
	 * @param keyName
	 * @param index
	 */
	public void buildConfiguration(Long userId,String keyName,Integer index,String tabKeyName,Integer tabIndex);
	/**
	 * 更新常用设置顺序
	 * @param userId
	 * @param keyName
	 * @param index
	 */
	public void updateConfigurationIndex(Long userId,String keyName,Integer index);
	
	/**
	 * 更新子选项顺序
	 * @param userId
	 * @param keyName
	 * @param tabKeyName
	 * @param tabIndex
	 */
	public void updateTabConfigurationIndex(Long userId,String keyName,String tabKeyName,Integer tabIndex);
}
