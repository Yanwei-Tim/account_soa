package com.tianque.service;

import java.util.List;

import com.tianque.domain.Permission;

public interface MenuService {

	List<Permission> getChildMenuByEname(String string);
	List<Permission> getChildMenuByEnameAndExcludeButtons(String string);

}
