package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.exception.ServiceException;
import com.tianque.dao.DesktopMenuDao;
import com.tianque.domain.DesktopMenu;
import com.tianque.service.DesktopMenuService;

@Service("desktopmenuService")
@Transactional
public class DesktopMenuServiceImpl extends LogableService implements
		DesktopMenuService {
	@Autowired
	private DesktopMenuDao desktopmenuDao;

	@Override
	public DesktopMenu addDesktopMenu(DesktopMenu desktopMenu) {
		if (!valdateNotNull(desktopMenu)) {
			throw new ServiceException("必填字段不能为空 ，请检查");
		}
		return desktopmenuDao.addDesktopMenu(desktopMenu);
	}

	private boolean valdateNotNull(DesktopMenu desktopMenu) {
		if (null == desktopMenu || null == desktopMenu.getUserId()) {
			return false;
		}
		if (null == desktopMenu || null == desktopMenu.getPermissionId()) {
			return false;
		}
		return true;
	}

	@Override
	public void deleteDesktopMenuById(Long id) {
		if (id == null || id < 0L) {
			throw new ServiceException("id没有获得");
		}
		desktopmenuDao.deleteDesktopMenuById(id);
	}

	

	@Override
	public DesktopMenu getDesktopMenuById(Long id) {
		if (id == null || id < 0L) {
			throw new ServiceException("id没有获得");
		}
		return desktopmenuDao.getDesktopMenuById(id);
	}

	

	

	@Override
	public void moveOneMenuAfterTheOther(Long menuId, Long afterMenuId) {
		Long afterIndexId = getDesktopMenuById(afterMenuId).getIndexId();
		desktopmenuDao.updateIndexIdByFromAndTo(getDesktopMenuById(menuId)
				.getIndexId(), afterIndexId);
		desktopmenuDao.updateIndexIdById(menuId, afterIndexId);
	}

	@Override
	public List<DesktopMenu> findDeskTopMenuByUserId(Long userId) {
		if (null == userId || userId < 0) {
			throw new ServiceException("userId没有获得");
		}

		return desktopmenuDao.findDeskTopMenuByUserId(userId);
	}
}