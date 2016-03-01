package com.tianque.workBench.moduelClickCount.dao;

import java.util.List;

import com.tianque.workBench.moduelClickCount.domain.ModuelClick;

public interface ModuelClickCountDao {

	public ModuelClick findModuelClickCountByPermissionIdAndUserId(Long permissionId, Long userId);

	public ModuelClick addModuelClickCount(ModuelClick moduelClick);

	public ModuelClick updateModuelClickCount(ModuelClick moduelClick);

	public List<ModuelClick> findModuelClickCountByUserId(Long userId,Integer nums);

	public ModuelClick getModuelClickById(Long Id);
	
}
