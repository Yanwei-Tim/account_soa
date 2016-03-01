package com.tianque.workBench.moduelClickCount.service;

import java.util.List;

import com.tianque.workBench.moduelClickCount.domain.ModuelClick;

public interface ModuelClickCountService {

	public ModuelClick findModuelClickCountByPermissionIdAndUserId(Long permissionId, Long userId);

	public ModuelClick addModuelClickCount(ModuelClick moduelClick);
	
	public ModuelClick updateModuelClickCount(ModuelClick moduelClick);

	/**
	 * 
	 * @param userId
	 * @param nums  取出多少条数据
	 * @return
	 */
	public List<ModuelClick> findModuelClickCountByUserId(Long userId,Integer nums);

}
