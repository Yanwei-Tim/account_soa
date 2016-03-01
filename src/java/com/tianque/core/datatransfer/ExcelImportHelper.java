package com.tianque.core.datatransfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ognl.Ognl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.DataConvertionHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;

@Component("excelImportHelper")
public class ExcelImportHelper {

	public final static Logger logger = LoggerFactory
			.getLogger(ExcelImportHelper.class);

	protected static DataConvertionHelper convertHelper;

	protected static ValidateHelper validateHelper;

	private static Map<String, String> dataColumMap = new HashMap<String, String>();

	public static ThreadLocal<Boolean> isImport = new ThreadLocal<Boolean>();

	public static ThreadLocal<Integer> realRow = new ThreadLocal<Integer>();
	protected static OrganizationDubboRemoteService organizationDubboService;

	@Autowired
	public void setConvertHelper(DataConvertionHelper convertHelper) {
		ExcelImportHelper.convertHelper = convertHelper;
	}

	@Autowired
	public void setConvertHelper(ValidateHelper validateHelper) {
		ExcelImportHelper.validateHelper = validateHelper;
	}

	@Autowired
	public void setOrganizationService(OrganizationDubboRemoteService organizationDubboService) {
		ExcelImportHelper.organizationDubboService = organizationDubboService;
	}

	public static Object procImportDate(String[][] excelDefines,
			String[] cellValues, Organization uploadOrganization, Object object) {

		StringBuffer bf = new StringBuffer();
		try {
			ExcelImportHelper.isImport.set(true);
			for (int i = 0; i < excelDefines.length; i++) {
				putDataColumMap(excelDefines[i][1], excelDefines[i][0]);

				if (DataType.DATA_TYPE_ORG.equals(excelDefines[i][3])) {
					Organization org = convertHelper.convertToOrganization(
							uploadOrganization, cellValues[i]);
					if (null == org || null == org.getId()) {
						bf.append("第[")
								.append((Integer.valueOf(excelDefines[i][0]) + 1))
								.append("]列输入").append(excelDefines[i][2])
								.append("不正确");
						throw new ServiceException();
					}
					if (!org.getOrgInternalCode().startsWith(
							organizationDubboService.getSimpleOrgById(
									ThreadVariable.getUser().getOrganization()
											.getId()).getOrgInternalCode())) {
						bf.append("第[")
								.append((Integer.valueOf(excelDefines[i][0]) + 1))
								.append("]列输入").append(excelDefines[i][2])
								.append("不正确,权限越界，不能将数据导入到该网格下！");
						throw new ServiceException();
					}
					Ognl.setValue(excelDefines[i][1], object, org);
					continue;
				}

				if (StringUtils.isEmpty(cellValues[i])) {
					continue;
				}
				if (DataType.DATA_TYPE_DICT.equals(excelDefines[i][3])) {
					PropertyDict dict = convertHelper.convertToPropertyDict(
							excelDefines[i][4], cellValues[i]);
					if (!validateHelper.emptyString(cellValues[i])) {
						if (null == dict || null == dict.getId()) {
							bf.append("第[")
									.append((Integer
											.valueOf(excelDefines[i][0]) + 1))
									.append("]列输入").append(excelDefines[i][2])
									.append("不正确");
							throw new ServiceException();
						}
					}
					Ognl.setValue(excelDefines[i][1], object, dict);
					continue;
				}
				if (DataType.DATA_TYPE_DATE.equals(excelDefines[i][3])) {
					if (validateHelper.illegalDate(cellValues[i])) {
						bf.append("第[")
								.append((Integer.valueOf(excelDefines[i][0]) + 1))
								.append("]列").append(excelDefines[i][2])
								.append("格式不正确");
						throw new ServiceException();
					}
					Ognl.setValue(excelDefines[i][1], object,
							convertHelper.convertToDate(cellValues[i]));
					continue;
				}
				if (DataType.DATA_TYPE_BOOLEAN.equals(excelDefines[i][3])
						|| DataType.DATA_TYPE_BOOLEANS
								.equals(excelDefines[i][3])) {
					if ("是".equals(cellValues[i]) || "有".equals(cellValues[i])) {
						Ognl.setValue(excelDefines[i][1], object,
								Long.valueOf("1"));
					} else {
						Ognl.setValue(excelDefines[i][1], object,
								Long.valueOf("0"));
					}
					continue;
				}
				if (DataType.DATA_TYPE_DICT_LIST.equals(excelDefines[i][3])) {
					if ("是".equals(cellValues[i]) || "有".equals(cellValues[i])) {
						List<PropertyDict> dicts = null;
						if (null == Ognl.getValue(excelDefines[i][1], object)) {
							dicts = new ArrayList<PropertyDict>();
							dicts.add(convertHelper.convertToPropertyDict(
									excelDefines[i][4], excelDefines[i][5]));
							Ognl.setValue(excelDefines[i][1], object, dicts);
						} else {
							dicts = (List<PropertyDict>) Ognl.getValue(
									excelDefines[i][1], object);
							dicts.add(convertHelper.convertToPropertyDict(
									excelDefines[i][4], excelDefines[i][5]));
						}
						if (excelDefines[i].length > 9) {
							if (excelDefines[i][7].length() > 0) {
								Ognl.setValue(excelDefines[i][6], object,
										convertHelper.convertToPropertyDict(
												excelDefines[i][7],
												excelDefines[i][8]));
							} else {
								Ognl.setValue(excelDefines[i][6], object,
										excelDefines[i][8]);
							}
						}
					}
					continue;
				} else {
					if (!StringUtils.isEmpty(cellValues[i])
							&& !StringUtils.isEmpty(excelDefines[i][1])) {
						Ognl.setValue(excelDefines[i][1], object, cellValues[i]);
					}
				}
			}
			return object;
		} catch (Exception e) {
			logger.error("类ExcelImportHelper的procImportDate方法出现异常，原因：", e);
			logger.error(bf.toString());
			throw new ServiceException(bf.toString());
		}
	}

	public static String getDataColumMap(String key) {
		return dataColumMap.get(key);
	}

	public static void putDataColumMap(String key, String value) {
		dataColumMap.put(key, value);
	}
}
