package com.tianque.init.impl;

import net.rubyeye.xmemcached.XMemcachedClient;

import com.tianque.init.Initialization;

public class DestoryCacheConnection implements Initialization{
	private XMemcachedClient cacheClient;
	
	public DestoryCacheConnection(XMemcachedClient cacheClient){
		this.cacheClient=cacheClient;
	}

	@Override
	public void init() throws Exception {
		cacheClient.shutdown();
	}

}
