package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

/*** 联系人 **/
public abstract class Contacter extends BaseDomain {
	/** 姓名 */
	private String name;
	/** 所属类别 */
	private String belongClass;
	/** 姓名全拼 */
	private String fullPinyin;
	/** 姓名简拼 */
	private String simplePinyin;

	public static final String MYCONTACTER = "myContact";
	public static final String WORKCONTACTER = "workContact";
	public static final String MYGROUP = "myGroup";
	public static final String MYAREA = "myArea";

	public abstract String getMobile();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBelongClass() {
		return belongClass;
	}

	public void setBelongClass(String belongClass) {
		this.belongClass = belongClass;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		if (fullPinyin != null && fullPinyin.length() > 30) {
			fullPinyin = fullPinyin.substring(0, 30);
		}
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (simplePinyin != null && simplePinyin.length() > 10) {
			simplePinyin = simplePinyin.substring(0, 10);
		}
		this.simplePinyin = simplePinyin;
	}

}
