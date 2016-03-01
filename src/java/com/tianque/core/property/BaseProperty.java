package com.tianque.core.property;


public class BaseProperty {
	protected static GridInternalProperty getInternalProperty(int identify,
			String displayName) {
		return new GridInternalProperty(identify,displayName);
	}
}
