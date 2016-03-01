package com.tianque.core.cache.service;

import java.io.Serializable;

public interface CacheService extends Serializable {
	int DEFAULT_EXPIRED_SECOND = 14400;// 4小时

	Object get(String key);

	void set(String key, Object value);

	void set(String key, int expried, Object value);

	/**
	 * 往缓存里添加对象，使用默认生效时间。<br>
	 * 缓存里不存在当前key，则把value放入到缓存并返回true，如果已存在则返回false
	 * 
	 * @param key
	 * @param value
	 * @return 是否成功放入缓存
	 */
	boolean add(String key, Object value);

	/**
	 * 往缓存里添加对象，指定生效时间。<br>
	 * 缓存里不存在当前key，则把value放入到缓存并返回true，如果已存在则返回false
	 * 
	 * @param key
	 * @param expried
	 *            生效时间，单位：秒
	 * @param value
	 * @return 是否成功放入缓存
	 */
	boolean add(String key, int expried, Object value);

	void remove(String key);

	Object get(String namespace, String key);

	void set(String namespace, String key, Object value);

	void set(String namespace, String key, int expried, Object value);

	void remove(String namespace, String key);

	void invalidateNamespaceCache(String namespace);

	boolean add(String namespace, String key, Object value);

	boolean add(String namespace, String key, int expried, Object value);

}
