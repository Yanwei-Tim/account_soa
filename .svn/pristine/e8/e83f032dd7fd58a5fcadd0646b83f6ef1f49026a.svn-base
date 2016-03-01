package com.tianque.plugin.account.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

/**
 * 困难 - 建卡类型
 * 
 * @author Administrator
 * 
 */
public class LedgerPoorPeopleCreateCardType extends BaseProperty {

	/** 新建 */
	public final static int NEW_CARD = 0;
	/** 上年结转 */
	public final static int LAST_YEAR_OVER_TURN = 1;
	/** 其他台账转入 */
	public final static int OTHER_TURN = 2;
	/** 上级主管部门和领导班子有关领导交办 */
	public final static int SUPERIOR_LEADER_TURN = 3;
	/** 县委县政府和领导班子有关领导交办 */
	public final static int COUNTY_LEADER_TURN = 4;
	/** 人大议案、建议、意见交办 */
	public final static int NATIONAL_PEOPLE_CONGRESS_BILLS = 5;
	/** 政协提案、建议、意见交办 */
	public final static int CPPCC_PROPOSAL = 6;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		properties.add(getInternalProperty(NEW_CARD, "新建"));
		properties.add(getInternalProperty(LAST_YEAR_OVER_TURN, "上年结转"));
		properties.add(getInternalProperty(OTHER_TURN, "其他台账转入"));
		properties.add(getInternalProperty(SUPERIOR_LEADER_TURN,
				"级主管部门和领导班子有关领导交办"));
		properties.add(getInternalProperty(COUNTY_LEADER_TURN,
				"县委县政府和领导班子有关领导交办"));
		properties.add(getInternalProperty(NATIONAL_PEOPLE_CONGRESS_BILLS,
				"人大议案、建议、意见交办"));
		properties.add(getInternalProperty(CPPCC_PROPOSAL, "政协提案、建议、意见交办"));
		return properties;
	}
}
