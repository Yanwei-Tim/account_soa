package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class OrganizationLevel extends BaseProperty {
	public final static int GRID = 0;
	public final static int VILLAGE = 10;
	public final static int TOWN = 20;
	public final static int DISTRICT = 30;
	public final static int CITY = 40;
	public final static int PROVINCE = 50;
	public final static int COUNTRY = 60;
	
	private static List<GridInternalProperty> properties=new ArrayList<GridInternalProperty>();
	public static List<GridInternalProperty> getInternalProperties() {
		if(properties.size()>0){
			return properties;
		}
		properties.add(getInternalProperty(GRID,"片组片格"));
		properties.add(getInternalProperty(VILLAGE,"村"));
		properties.add(getInternalProperty(TOWN,"镇、街道"));
		properties.add(getInternalProperty(DISTRICT,"区"));
		properties.add(getInternalProperty(CITY,"市"));
		properties.add(getInternalProperty(PROVINCE,"省"));
		properties.add(getInternalProperty(COUNTRY,"全国"));
		return properties;
	}
	
	public static int getLowerLevel(int level){
		if(level == VILLAGE)
			return GRID;
		if(level == TOWN)
			return VILLAGE;
		if(level == DISTRICT)
			return TOWN;
		if(level == CITY)
			return DISTRICT;
		if(level == PROVINCE)
			return CITY;
		if(level == COUNTRY)
			return PROVINCE;
		return GRID;
	}
	
	public static int levelCompare(int source,int target){
		return target==source?0:(source>target?1:-1);
	}
	
	public final static String ORG_LEVEL_KEY = PropertyTypes.ORGANIZATION_LEVEL;
}
