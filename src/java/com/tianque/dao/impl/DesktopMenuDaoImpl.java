package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.exception.DAOException;
import com.tianque.dao.DesktopMenuDao;
import com.tianque.domain.DesktopMenu;

@Repository("desktopMenuDao")
public class DesktopMenuDaoImpl extends AbstractBaseDao implements DesktopMenuDao {

	@Override
	public DesktopMenu addDesktopMenu(DesktopMenu desktopMenu) {
		if (!valdateNotNull(desktopMenu)) {
			throw new DAOException("必填字段不能为空 ，请检查");
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"desktopMenu.addDesktopMenu", desktopMenu);
		return getDesktopMenuById(id);
	}

	private boolean valdateNotNull(DesktopMenu desktopMenu) {
		if (null == desktopMenu || null == desktopMenu.getUserId()) {
			return false;
		}
//		if (null == desktopMenu || null == desktopMenu.getIndexId()) {
//			return false;
//		}
		if (null == desktopMenu || null == desktopMenu.getPermissionId()) {
			return false;
		}
//		if (null == desktopMenu || null == desktopMenu.getPage()) {
//			return false;
//		}
//		if (null == desktopMenu || null == desktopMenu.getImgUrl()) {
//			return false;
//		}
//		if (null == desktopMenu || null == desktopMenu.getMenuType()) {
//			return false;
//		}
//		if (null == desktopMenu || null == desktopMenu.getName()) {
//			return false;
//		}
//		if (null == desktopMenu || null == desktopMenu.getUrl()) {
//			return false;
//		}
//		if (null == desktopMenu || null == desktopMenu.getWidth()) {
//			return false;
//		}
//		if (null == desktopMenu || null == desktopMenu.getHeight()) {
//			return false;
//		}
//		if (null == desktopMenu || null == desktopMenu.getEname()) {
//			return false;
//		}
		return true;
	}

	@Override
	public DesktopMenu getDesktopMenuById(Long id) {
		if (null == id || id < 0L) {
			throw new DAOException("id没有获得");
		}
		return (DesktopMenu) getSqlMapClientTemplate().queryForObject(
				"desktopMenu.getDesktopMenuById", id);
	}

	@Override
	public void deleteDesktopMenuById(Long id) {
		if (null == id || id < 0L) {
			throw new DAOException("id没有获得");
		}
		getSqlMapClientTemplate().delete("desktopMenu.deleteDesktopMenuById",
				id);
	}

	

	

	

	

	@Override
	public void updateIndexIdById(Long menuId, Long afterIndexId) {
		Map map = new HashMap();
		map.put("indexId", afterIndexId);
		map.put("id", menuId);
		getSqlMapClientTemplate().update("desktopMenu.updateIndexIdById", map);
	}

	@Override
	public void updateIndexIdByFromAndTo(Long firstIndexId, Long afterIndexId) {
		Map map = new HashMap();
		map.put("firstIndexId", firstIndexId);
		map.put("afterIndexId", afterIndexId);
		getSqlMapClientTemplate().update(
				"desktopMenu.updateIndexIdByFromIndexIdAndToIndexId", map);
	}

	@Override
	public List<DesktopMenu> findDeskTopMenuByUserId(Long userId) {
		if (null == userId || userId < 0L) {
			throw new DAOException("userId没有获得");
		}
		return (List<DesktopMenu>) getSqlMapClientTemplate().queryForList(
				"desktopMenu.findDeskTopMenuByUserId", userId);

	}

}
