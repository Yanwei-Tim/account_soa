package com.tianque.plugin.account.service;

import java.util.Date;

public interface ThreeRecordsKeyGeneratorService {

	/**
	 * 按生成的编号和机构ID获取下一个生成的台账编号
	 * 
	 * @param prefix
	 * @param orgId
	 * @return
	 */
	public String getNextKey(String prefix, Long orgId);

	/**
	 * 数据导入时生成台账编号
	 * 
	 * @param prefix
	 * @param orgId
	 * @param createDate
	 * @return
	 */
	public String getNextKey(String prefix, Long orgId, Date createDate);

	/**
	 * 根据台账类型，表单类型 生成呈报单,回复单，交办单等编号
	 * 
	 * @param prefix
	 * @param formType
	 * @return
	 */
	public String getNextFormKey(String prefix, String formType);

	/**
	 * 数据导入时根据台账类型，表单类型 生成呈报单,回复单，交办单等编号
	 * 
	 * @param prefix
	 * @param formType
	 * @return
	 */
	public String getNextFormKey(String prefix, String formType, Date createDate);

}
