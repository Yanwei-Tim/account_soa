package com.tianque.domain;

import com.tianque.core.base.BaseDomain;


/**
 * 属性字典
 */
@SuppressWarnings("serial")
public class PropertyDict extends BaseDomain {

	/**
	 * 用户自定义名称
	 */
	private String displayName;
	/**
	 * 系统内置ID,只有属性元信息表里定义好系统敏感，并且 systemRestrict有相应约束才有意义
	 */
	private int internalId;
	/**
	 * 显示顺序
	 */
	private int displaySeq = 0;
	/**
	 * 全拼
	 */
	private String simplePinyin;
	/**
	 * 简拼
	 */
	private String fullPinyin;
	/**
	 * 域属性
	 */
	private PropertyDomain propertyDomain;

	public PropertyDomain getPropertyDomain() {
		return propertyDomain;
	}

	public void setPropertyDomain(PropertyDomain propertyDomain) {
		this.propertyDomain = propertyDomain;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getInternalId() {
		return internalId;
	}

	public void setInternalId(int internalId) {
		this.internalId = internalId;
	}

	public int getDisplaySeq() {
		return displaySeq;
	}

	public void setDisplaySeq(int displaySeq) {
		this.displaySeq = displaySeq;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (simplePinyin.length() <= 10) {
			this.simplePinyin = simplePinyin;
		} else {
			this.simplePinyin = simplePinyin.substring(0, 10);
		}
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		if (fullPinyin.length() <= 30) {
			this.fullPinyin = fullPinyin;
		} else {
			this.fullPinyin = fullPinyin.substring(0, 30);
		}
	}
	
	@Override
	public String toString() {
		return displayName;
	}

}
