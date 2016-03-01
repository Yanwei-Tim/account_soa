package com.tianque.userAuth.api;

import java.util.List;

public interface UserHasPlatformMessageTypesDubboService {
	/**
	 * 保存用户订阅的消息类型
	 * 
	 * @param messageTypes
	 *            消息类型集合
	 */
	public void addUserHasPlatformMessageType(List<Integer> messageTypes,
			Long userId);

	/***
	 * 删除用户订阅的平台消息类型
	 * 
	 * @param userId
	 */
	public void deleteUserHasPlatformMessageTypeByUserId(Long userId);
	
	public void deleteUserHasPlatformMessageTypeByUserName(String name);
	
	public List<Integer> findUserHasPlatformMessageTypeByUserId(Long userId);
	
}
