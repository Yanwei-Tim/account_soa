package com.tianque.workBench.moduelClickCount.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.workBench.moduelClickCount.dao.ModuelClickCountDao;
import com.tianque.workBench.moduelClickCount.domain.ModuelClick;

@Service("moduelClickCountService")
@Transactional
public class ModuelClickCountServiceImpl implements ModuelClickCountService {

	@Autowired
	private ModuelClickCountDao moduelClickCountDao;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Override
	public ModuelClick findModuelClickCountByPermissionIdAndUserId(
			Long permissionId, Long userId) {
		return moduelClickCountDao.findModuelClickCountByPermissionIdAndUserId(
				permissionId, userId);
	}

	@Override
	public ModuelClick addModuelClickCount(ModuelClick moduelClick) {
		return moduelClickCountDao.addModuelClickCount(moduelClick);
	}

	@Override
	public ModuelClick updateModuelClickCount(ModuelClick moduelClick) {
		return moduelClickCountDao.updateModuelClickCount(moduelClick);
	}

	@Override
	public List<ModuelClick> findModuelClickCountByUserId(Long userId,
			Integer nums) {
		List<ModuelClick> moduelClicks = moduelClickCountDao
				.findModuelClickCountByUserId(userId, nums);
		if (moduelClicks != null) {
			String ename = null;
			String parentEname = null;
			for (ModuelClick moduelClick : moduelClicks) {
				ename = moduelClick.getPermission().getEname();
				if (moduelClick.getPermission().getParent() != null) {
					if (moduelClick.getPermission().getParent().getParentId() != null) {
						parentEname = permissionDubboService
								.getSimplePermissionById(
										moduelClick.getPermission().getParent()
												.getParentId()).getEname();

					} else {
						parentEname = moduelClick.getPermission().getParent()
								.getEname();
					}
				}
				// InformationTrainConfiger informationTrainConfiger =
				// InformationTrainConfiger.allInformationCatalogs
				// .get(moduelClick.getPermission().getEname());
				String imgUrl = "/resource/workBench/images/icon/" + ename
						+ ".png";
				String url = "/module.jsp#" + parentEname + "-" + ename;
				moduelClick.setRel(parentEname + "-" + ename);
				moduelClick.setImgUrl(imgUrl);
				moduelClick.setUrl(url);
			}
		}
		return moduelClicks;
	}

}
