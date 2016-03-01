package com.tianque.plugin.account.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.constants.LedgerReportStatisticalFieldMapping;
import com.tianque.plugin.account.constants.LedgerReportType;
import com.tianque.plugin.account.constants.ThreeRecordsIssueConstants;
import com.tianque.plugin.account.domain.AccountReport;
import com.tianque.plugin.account.domain.LedgerReportGroupCount;
import com.tianque.plugin.account.vo.ThreeRecordsReportStatisticalVo;
import com.tianque.userAuth.api.PropertyDictDubboService;

@SuppressWarnings("unchecked")
public class ComparisonAttribute {

	/**
	 * 将得到的源数据统计到VO中
	 * 
	 * @param list
	 *            源数据
	 * @param vo
	 * @param ledgerType
	 *            统计类型
	 * @return
	 * @throws Exception
	 */

	public static ThreeRecordsReportStatisticalVo calculationRatioVo(
			List<LedgerReportGroupCount> list,
			ThreeRecordsReportStatisticalVo vo, Integer ledgerType) {
		if (null == list || list.size() == 0) {
			return vo;
		}
		Class cla = vo.getClass();
		Integer total = 0;
		Map<String, String> map = LedgerReportStatisticalFieldMapping
				.getThreeRecordsIssueMappingMap();
		try {
			for (LedgerReportGroupCount ledgerReportGroupCount : list) {
				String fieldName = null;
				if (ledgerReportGroupCount.getLedgerType() == LedgerConstants.PEOPLEASPIRATION) {
					PropertyDict dict = LedgerReportType
							.getPeopleAsPirationPropertyDict(ledgerReportGroupCount
									.getItem().getId());
					ledgerReportGroupCount.setItem(dict);
				}
				if (ledgerReportGroupCount != null
						&& ledgerReportGroupCount.getItem() != null) {
					fieldName = map.get(ledgerReportGroupCount.getItem()
							.getDisplayName());
				}

				if (StringUtils.isEmpty(fieldName)) {
					continue;
				}
				Integer ledgerReportCount = ledgerReportGroupCount.getCount();
				String methodName = upperCase(fieldName, 1);
				String setMethodName = setMethodName(methodName);
				String getMethodName = getMethodName(methodName);

				Integer voTotal = ((Integer) cla.getDeclaredMethod(
						getMethodName).invoke(vo))
						+ ledgerReportCount;
				cla.getDeclaredMethod(setMethodName, Integer.class).invoke(vo,
						voTotal);
				total += ledgerReportCount;
			}
		} catch (Exception e) {
			throw new ServiceValidationException("参数错误，转换类型失败！", e);
		}
		if (ledgerType == LedgerConstants.PEOPLEASPIRATION) {
			vo.setPeopleAsWorkTotal(total);
		} else if (ledgerType == LedgerConstants.POORPEOPLE) {
			vo.setLedgerPoorPeopleTotal(total);
		} else if (ledgerType == LedgerConstants.STEADYWORK) {
			vo.setSteadyRecordWorkTotal(total);
		}
		return vo;
	}

	public static ThreeRecordsReportStatisticalVo statisticsTotal(
			ThreeRecordsReportStatisticalVo totalVo,
			List<ThreeRecordsReportStatisticalVo> vos) {
		if (null == totalVo || null == vos || vos.size() == 0) {
			return totalVo;
		}
		try {
			for (ThreeRecordsReportStatisticalVo vo : vos) {
				statisticsTotal(totalVo, vo);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("计算错误！", e);
		}
		return totalVo;
	}

	public static ThreeRecordsReportStatisticalVo statisticsTotal(
			ThreeRecordsReportStatisticalVo totalVo,
			ThreeRecordsReportStatisticalVo vo) {
		if (null == totalVo || null == vo) {
			return totalVo;
		}
		Class totalVoClass = totalVo.getClass();
		Field[] totalVoFields = totalVoClass.getDeclaredFields();
		try {
			for (Field field : totalVoFields) {
				String fieldName = field.getName();
				if (!"columnName".equals(fieldName)) {
					Class voClass = vo.getClass();
					String methodName = upperCase(fieldName, 1);
					String setMethodName = setMethodName(methodName);
					String getMethodName = getMethodName(methodName);
					Integer total = ((Integer) voClass.getDeclaredMethod(
							getMethodName).invoke(vo))
							+ ((Integer) totalVoClass.getDeclaredMethod(
									getMethodName).invoke(totalVo));
					totalVoClass
							.getDeclaredMethod(setMethodName, Integer.class)
							.invoke(totalVo, total);
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException("计算错误！", e);
		}
		return totalVo;
	}

	public static String upperCase(String methodName, Integer num) {
		return methodName.substring(0, num).toUpperCase()
				+ methodName.substring(num);

	}

	public static String getMethodName(String methodName) {
		return "get" + methodName;
	}

	public static String setMethodName(String methodName) {
		return "set" + methodName;
	}

	public static void analysisFiled(String object, Class classObj, Object objVo)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, ParseException {
		if (StringUtils.isEmpty(object)) {
			return;
		}
		String[] fileds = object.split(",");
		String[] filed = null;
		for (int i = 0; i < fileds.length; i++) {
			filed = fileds[i].split(":");
			if (filed.length <= 1 || StringUtils.isEmpty(filed[1])) {
				break;
			}
			String setMethodName = setMethodName(upperCase(filed[0], 1));
			Field totalVoField = classObj.getDeclaredField(filed[0]);
			Class fieldType = totalVoField.getType();
			if (fieldType.getName().equals(PropertyDict.class.getName())) {
				PropertyDict dict = new PropertyDict();
				dict.setId(Long.valueOf(filed[1]));
				classObj.getDeclaredMethod(setMethodName, fieldType).invoke(
						objVo, dict);
			} else if (fieldType.getName().equals(Date.class.getName())) {
				classObj.getDeclaredMethod(setMethodName, fieldType).invoke(
						objVo,
						new SimpleDateFormat("yyyy-MM-dd").parse(filed[1]));
			} else if (fieldType.getName().equals(Boolean.class.getName())) {
				classObj.getDeclaredMethod(setMethodName, fieldType).invoke(
						objVo, Boolean.valueOf(filed[1]));
			} else if (fieldType.getName().equals(Long.class.getName())) {
				classObj.getDeclaredMethod(setMethodName, fieldType).invoke(
						objVo, Long.valueOf(filed[1]));
			} else if (fieldType.getName().equals(Integer.class.getName())) {
				classObj.getDeclaredMethod(setMethodName, fieldType).invoke(
						objVo, Integer.valueOf(filed[1]));
			} else {
				classObj.getDeclaredMethod(setMethodName, fieldType).invoke(
						objVo, filed[1]);
			}
		}
	}

	public static void loadPropertyDictValue(Object objVo, Class voClass,
			PropertyDictDubboService propertyDictService)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Field[] fields = voClass.getDeclaredFields();
		for (Field field : fields) {
			Class fieldType = field.getType();
			if (fieldType.getName().equals(PropertyDict.class.getName())) {
				String methodName = ComparisonAttribute.upperCase(field
						.getName(), 1);
				String setMethodName = ComparisonAttribute
						.setMethodName(methodName);
				String getMethodName = ComparisonAttribute
						.getMethodName(methodName);
				PropertyDict dict = (PropertyDict) voClass.getDeclaredMethod(
						getMethodName).invoke(objVo);
				if (null != dict && null != dict.getId()) {
					dict = propertyDictService
							.getPropertyDictById(dict.getId());
					voClass
							.getDeclaredMethod(setMethodName,
									PropertyDict.class).invoke(objVo, dict);
				}
			}
		}
	}

	/**
	 * 是否是县台账办
	 * 
	 * @param organization
	 * @return
	 */
	public static Boolean isRecordHandleAffairs(Organization organization) {
		return null != organization
				&& !StringUtils.isEmpty(organization.getDepartmentNo())
				&& organization.getDepartmentNo().indexOf(
						ThreeRecordsIssueConstants.COUNTY_LEDGER) > -1;
	}

	/**
	 * 县区
	 */
	public static boolean isDistrictOrganization(Integer organizationLevel) {
		return OrganizationLevel.DISTRICT == organizationLevel;
	}

	/**
	 * 乡镇
	 */
	public static boolean isTownOrganization(Integer organizationLevel) {
		return OrganizationLevel.TOWN == organizationLevel;
	}

	/**
	 * 村
	 */
	public static boolean isVillageOrganization(Integer organizationLevel) {
		return OrganizationLevel.VILLAGE == organizationLevel;
	}

	/**
	 * 网格
	 */
	public static boolean isGridOrganization(Integer organizationLevel) {
		return OrganizationLevel.GRID == organizationLevel;
	}

	public static String getNextLayer(Integer organizationLevel) {
		if (isVillageOrganization(organizationLevel)) {
			return "网格级";
		} else if (isTownOrganization(organizationLevel)) {
			return "村级";
		} else if (isDistrictOrganization(organizationLevel)) {
			return "乡镇级";
		}
		return null;
	}

	/**
	 * 计算日期
	 * 
	 * @param year
	 *            当前年份要减去的数
	 * @param month
	 *            当前月份要减去的数
	 * @param day
	 *            当前天要减去的数
	 * @param accountReport
	 *            查询条件
	 * @param isLastDay
	 *            是否获取最后一天
	 * @return
	 */
	public static Date getDateUtil(Integer year, Integer month, Integer day,
			AccountReport accountReport, Boolean isLastDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, accountReport.getReportYear() - year);
		calendar.set(Calendar.MONTH, accountReport.getReportMonth() - month);
		if (isLastDay) {
			Integer lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			calendar.set(Calendar.DAY_OF_MONTH, lastDay);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
		} else {
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		}
		// 当前年份的当前月份当前天23:59:59【当月月报表结束时间】
		return calendar.getTime();
	}

	public static List<Long> getOrgIds(List<Organization> orgs) {
		if (orgs == null || orgs.size() == 0) {
			return null;
		}
		List<Long> orgIdList = new ArrayList<Long>();
		for (Organization org : orgs) {
			orgIdList.add(org.getId());
		}
		return orgIdList;
	}
}
