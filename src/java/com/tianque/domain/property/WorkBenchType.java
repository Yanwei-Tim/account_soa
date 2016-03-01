package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class WorkBenchType extends BaseProperty {
	public final static int SUPER_LEVEL = 0;
	public final static int MIDDLE_LEVEL = 1;
	public final static int LOWER_LEVEL = 2;
	
	public final static String SUPER_LEVEL_NAME = "高层";
	public final static String MIDDLE_LEVEL_NAME = "中层";
	public final static String LOWER_LEVEL_NAME = "基层";
	
	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(SUPER_LEVEL, SUPER_LEVEL_NAME));
		properties.add(getInternalProperty(MIDDLE_LEVEL, MIDDLE_LEVEL_NAME));
		properties.add(getInternalProperty(LOWER_LEVEL, LOWER_LEVEL_NAME));
		return properties;
	}
}