package com.tianque.plugin.account.util;

import java.util.List;

public final class CollectionUtil {

	// 私有构造方法，不允许外部创建CollectionUtil对象
	private CollectionUtil() {

	}

	/**
	 * 
	 * @param list
	 * @return 如果list合法（即对象存在且不为空）返回true，否则返回false
	 */
	public static boolean isAvaliable(List list) {
		return list != null && !list.isEmpty();
	}
}
