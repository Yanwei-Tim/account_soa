package com.tianque.userAuth.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.Permission;
import com.tianque.domain.User;

public class SerializationOptimizerImpl implements SerializationOptimizer
{
	/**
	 * 在此添加实体类的class
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Collection<Class> getSerializableClasses()
	{
		List<Class> classes = new LinkedList<Class>();
		classes.add(Permission.class);
		classes.add(User.class);
		classes.add(PageInfo.class);
		classes.add(Organization.class);
		classes.add(List.class);
		classes.add(Map.class);
		return classes;
	}
}
