package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class OrganizationType extends BaseProperty {
    public final static int ADMINISTRATIVE_REGION = 0;
    public final static int FUNCTIONAL_ORG = 1;
    public final static int OTHER = 2;
    public final static int PARTYWORK = 3;
    public final static int ORG_LEVEL = 2;


    /** 根据用户ORGID查询某个组织信息 */
    public final static Integer PROVINCE_LEVEL = 2;
    public final static Integer CITY_LEVEL = 3;
    public final static Integer DISTRICT_LEVEL = 4;
    public final static Integer TOWN_LEVEL = 5;
    public final static Integer VILLAGE_LEVEL = 6;
    public final static Integer GRID_LEVEL = 7;

    /** 查询职能部门所在的组织机构信息 */
    public final static Integer FUNCTIONAL_ORG_PROVINCE_LEVEL = 1;
    public final static Integer FUNCTIONAL_ORG_CITY_LEVEL = 2;
    public final static Integer FUNCTIONAL_ORG_DISTRICT_LEVEL = 3;
    public final static Integer FUNCTIONAL_ORG_TOWN_LEVEL = 4;
    public final static Integer FUNCTIONAL_ORG_VILLAGE_LEVEL = 5;

    public final static String ADMINISTRATIVE_KEY = "行政区域";
    public final static String FUNCTION_KEY = "职能部门";

    private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

    public static List<GridInternalProperty> getInternalProperties() {
        if (properties.size() > 0) {
            return properties;
        }
        properties.add(getInternalProperty(ADMINISTRATIVE_REGION,
                ADMINISTRATIVE_KEY));
        properties.add(getInternalProperty(FUNCTIONAL_ORG, "职能部门"));
        properties.add(getInternalProperty(OTHER, "其他"));
        // properties.add(getInternalProperty(PARTYWORK, "党工委"));
        return properties;
    }

    public final static String ORG_TYPE_KEY = PropertyTypes.ORGANIZATION_TYPE;
}
