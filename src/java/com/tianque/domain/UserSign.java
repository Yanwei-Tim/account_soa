package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

/**
 * @author 张静静
 * @data 2014-10-16 下午01:57:30
 * 
 */
public class UserSign extends BaseDomain {
	private static final long serialVersionUID = 1L;

	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
