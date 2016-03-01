package com.tianque.dao;

import java.util.List;

import com.tianque.domain.DesktopMenu;

public interface DesktopMenuDao {
	public DesktopMenu addDesktopMenu(DesktopMenu desktopmenu);

	public DesktopMenu getDesktopMenuById(Long id);

	public void deleteDesktopMenuById(Long id);

	

	

	public void updateIndexIdByFromAndTo(Long firstIndexId, Long afterIndexId);

	public void updateIndexIdById(Long menuId, Long afterIndexId);
	
	public List<DesktopMenu> findDeskTopMenuByUserId(Long userId);

}
