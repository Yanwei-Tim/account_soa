package com.tianque.workBench.patelConfiger.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.ThreadVariable;
import com.tianque.service.util.PatelConfiger;
import com.tianque.service.util.TabPatel;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.workBench.patelConfiger.dao.PatelDao;

@Service("patelService")
@Transactional
public class PatelServiceImpl implements PatelService {
	@Autowired
	private PatelDao patelDao;
	@Autowired
	private PermissionDubboService permissionDubboService;

	public List<String> getPatelConfigerKeyName() {
		Long userId = ThreadVariable.getUser().getId();
		List<String> list = patelDao.getKeyNamesByUserId(userId);
		List<String> temp = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			temp.addAll(patelDao.getTabConfiger(userId, list.get(i)));
		}
		list.addAll(temp);
		return list;
	}

	public List<PatelConfiger> getPatelConfiger() {
		Long userId = ThreadVariable.getUser().getId();
		if (ThreadVariable.getUser().getIsFristWorkBench() == null
				|| ThreadVariable.getUser().getIsFristWorkBench()) {
			init(userId);
			permissionDubboService.initWorkBench(userId, false);
		}
		return obtainEntity(patelDao.getKeyNamesByUserId(userId));
	}

	public List<TabPatel> getTabConfiger(String keyName) {
		Long userId = ThreadVariable.getUser().getId();
		return obtainTabEntity(keyName, userId);
	}

	public void addConfiguration(String keyName) {
		if (!"".equals(keyName)) {
			Long userId = ThreadVariable.getUser().getId();
			Integer index = patelDao.getCurrentMaxConfiger();
			if (index == null) {
				index = 0;
			} else {
				index++;
			}
			buildConfiguration(userId, keyName, index);
		}
	}

	public void deleteConfiguration(String keyName) {
		Long userId = ThreadVariable.getUser().getId();
		patelDao.deleteConfiguration(userId, keyName);
	}

	public void updateConfigurationIndex(String[] keyNames) {
		for (int i = 0; i < keyNames.length; i++) {
			updateConfigurationIndex(keyNames[i], i);
		}
	}

	private void updateConfigurationIndex(String keyName, Integer index) {
		Long userId = ThreadVariable.getUser().getId();
		patelDao.updateConfigurationIndex(userId, keyName, index);
	}

	private void init(Long userId) {
		deleteConfiguration(userId);
		if (permissionDubboService.isUserHasPermission(userId,
				PatelConfiger.allCatalogs.get(PatelConfiger.INFORMATIONTRAIN)
						.getEname())) {
			buildConfiguration(userId, PatelConfiger.INFORMATIONTRAIN, 0);
		}
	}

	public void addTabConfiguration(String keyName, String tabKeyName) {
		Long userId = ThreadVariable.getUser().getId();
		Integer index = patelDao.getIndexByUserIdAndKeyname(userId, keyName);
		Integer tabIndex = patelDao.getCurrentMaxTabConfiger(keyName);
		if (tabIndex == null) {
			tabIndex = 0;
		} else {
			tabIndex++;
		}
		patelDao.buildConfiguration(userId, keyName, index, tabKeyName,
				tabIndex);
	}

	public void deleteTabConfiguration(String keyName, String tabKeyName) {
		Long userId = ThreadVariable.getUser().getId();
		patelDao.deleteTabConfiguration(userId, keyName, tabKeyName);
	}

	public void updateTabConfigurationIndex(String keyName, String[] tabKeyNames) {
		for (int i = 0; i < tabKeyNames.length; i++) {
			updateConfigurationIndex(keyName, tabKeyNames[i], i);
		}
	}

	private void updateConfigurationIndex(String keyName, String tabKeyName,
			Integer index) {
		Long userId = ThreadVariable.getUser().getId();
		patelDao.updateTabConfigurationIndex(userId, keyName, tabKeyName, index);
	}

	private List<PatelConfiger> obtainEntity(List<String> list) {
		List<PatelConfiger> temps = new ArrayList<PatelConfiger>();
		for (int i = 0; i < list.size(); i++) {
			PatelConfiger patelConfiger = new PatelConfiger();
			PatelConfiger temp = PatelConfiger.allCatalogs.get(list.get(i));
			patelConfiger.setKeyName(temp.getKeyName());
			patelConfiger.setEname(temp.getEname());
			patelConfiger.setCname(temp.getCname());
			patelConfiger.setUrl(temp.getUrl());
			patelConfiger.setMaxUrl(temp.getMaxUrl());
			patelConfiger.setType(temp.getType());
			// patelConfiger.setTabPatels((ArrayList<TabPatel>)
			// getTabConfiger(patelConfiger.getKeyName()));
			temps.add(temp);
		}
		return temps;
	}

	private List<TabPatel> obtainTabEntity(String keyName, Long userId) {
		List<String> tabKeyNames = patelDao.getTabConfiger(userId, keyName);
		if (tabKeyNames.size() == 0) {
			return new ArrayList<TabPatel>();
		}
		TabPatel[] temp = new TabPatel[tabKeyNames.size()];
		for (TabPatel tabPatel : PatelConfiger.allCatalogs.get(keyName)
				.getTabPatels()) {
			String tabKeyName = tabPatel.getKeyName();
			if (tabKeyNames.contains(tabKeyName)) {
				temp[tabKeyNames.indexOf(tabKeyName)] = tabPatel;
			}
		}
		return Arrays.asList(temp);
	}

	public void buildConfiguration(Long userId, String keyName, Integer index) {
		int tablIndex = 0;
		PatelConfiger patelConfiger = PatelConfiger.allCatalogs.get(keyName);
		if (permissionDubboService.isUserHasPermission(ThreadVariable.getUser()
				.getId(), patelConfiger.getEname())) {
			if (patelConfiger.getTabPatels() != null) {
				for (TabPatel tabPatel : patelConfiger.getTabPatels()) {
					if (permissionDubboService.isUserHasPermission(
							ThreadVariable.getUser().getId(),
							tabPatel.getEname())) {
						patelDao.buildConfiguration(userId, keyName, index,
								tabPatel.getKeyName(), tablIndex++);
						if (tablIndex == 4) {
							break;
						}
					}
				}
			}
		}
	}

	public void deleteConfiguration(Long userId) {
		patelDao.deleteConfiguration(userId);
	}
}
