package com.tianque.userAuth.api;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Role;

public interface SearchRoleDubboService {
	public PageInfo<Role> searchRoles(Role condition, int pageNum, int pageSize, String sortField,
			String order);

	public PageInfo<Role> searchRolesLike(Role role, Integer page, Integer rows, String sidx,
			String sord);
}
