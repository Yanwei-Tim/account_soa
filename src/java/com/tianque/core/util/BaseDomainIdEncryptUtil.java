package com.tianque.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.exception.base.OperationFailedException;
import com.tianque.exception.base.ParameterIllegalException;

/**
 * 对实体类id做加密工具类
 * */
public class BaseDomainIdEncryptUtil {

	/** 取生成的md5的前4位 */
	/** 组织机构code */
	private static final String ORGANIZATIONCODE = "fc0c_";// organizationCode=fc0c85c136c69cec42bc421158282525
	/** 实体类id */
	private static final String DOMAINID = "72ee_";// domainId=72ee76c5c29383b7c9f9225c1fa4d10b
	/** 类别 */
	private static final String TYPE = "599d_";// type=599dcce2998a6b40b1e38e8c6006cb0a
	/** IP */
	// private static final String IP = "a12a_";//
	// IP=a12a3079e14ced46e69ba52b8a90b21a

	/** 识别常量 */
	public static final String DISCERN_ENCRYPT_CONSTANT = "-dec-";

	public static final String ORG_CODE_CONSTANT = "orgCode";
	public static final String TYPE_CONSTANT = "type";
	public static final String DOMIAN_ID_CONSTANT = "domainId";

	/**
	 * 对id的加密算法
	 * 
	 * 
	 * @param id
	 *            donmainID
	 * @param orgCode
	 *            domain 的组织机构的orgCode
	 * @return 加密后的值
	 */
	public static String encryptDomainId(Long id, String orgCode, String type) {
		String encrypt = "";
		// String ip = "";
		// if (ThreadVariable.getSession() != null) {
		// ip = ThreadVariable.getSession().getLoginIp();
		// }
		if (id != null && !id.equals(0L)) {
			encrypt = encrypt + DOMAINID
					+ encryptOrgCode(String.valueOf(id).trim());
		}
		if (orgCode != null && !"".equals(orgCode.trim())) {
			encrypt = encrypt + "_" + ORGANIZATIONCODE
					+ encryptOrgCode(orgCode);
		}
		if (type != null && !"".equals(type.trim())) {
			encrypt = encrypt + "_" + TYPE + encryptOrgCode(type);
		}
		// if (ip == null || "".equals(ip.trim())) {
		// return null;
		// } else {
		// encrypt = encrypt + "_" + IP + encryptOrgCode(ip);
		// }
		if ("".equals(encrypt.trim())) {
			return null;
		}
		String discern = "_" + DISCERN_ENCRYPT_CONSTANT;
		encrypt = encrypt + discern;
		return encrypt;
	}

	/**
	 * 解密加密后的id返回需要的值
	 * 
	 * @param idTemp
	 *            传过来加密后的id
	 * @return
	 */

	public static Map<String, Object> decodeDomainId(String idsTemp) {

		if (idsTemp == null || "".equals(idsTemp.trim())) {
			throw new ParameterIllegalException("需要加密的Id不允许为空");
		}
		String[] ids = idsTemp.split(",");

		// String ip = "";
		// if (ThreadVariable.getSession() != null) {
		// ip = ThreadVariable.getSession().getAccessIp();
		// }
		if (ids.length <= 0) {
			throw new ParameterIllegalException("需要加密的Id不允许为空");
		}
		// if (ip == null || "".equals(ip.trim())) {
		// return null;
		// }
		Map<String, Object> map = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (String id : ids) {
			Map<String, Object> idMap = analyzeDomainId(id);
			if (idMap != null) {
				list.add(idMap);
			}
		}
		if (list != null && list.size() > 0) {
			Map<String, Object> tempMap = list.get(0);
			if (tempMap == null) {
				map = analyzeIdList(list, 1);
			} else {
				map = analyzeIdList(list, 0);
			}
		}

		return map;
	}

	/**
	 * 解析单个domainId的map的集合 为一个map value 是一个数组
	 * 
	 * @param list
	 *            解析单个domainId的map的集合
	 * @return
	 */
	private static Map<String, Object> analyzeIdList(
			List<Map<String, Object>> list, int size) {
		if (list == null || list.size() <= 0) {
			throw new ParameterIllegalException("需要加密的Id不允许为空");
		}

		Map<String, Object> domainIdsMap;
		domainIdsMap = new HashMap<String, Object>();
		String orgCode = "";
		String type = "";
		String domainId = "";
		String comma = "";
		for (int i = size; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			if (i == (list.size() - 1)) {
				comma = "";
			} else {
				comma = ",";
			}

			if (map == null) {
				domainId = domainId + comma + "";
				orgCode = orgCode + comma + "";
				type = type + comma + "";
				continue;
			}
			if (map.get(ORGANIZATIONCODE) != null
					&& !"".equals(String.valueOf(map.get(ORGANIZATIONCODE))
							.trim())) {
				orgCode = orgCode
						+ String.valueOf(map.get(ORGANIZATIONCODE)).trim()
						+ comma;

			} else {
				orgCode = orgCode + comma + "";
			}
			if (map.get(TYPE) != null
					&& !"".equals(String.valueOf(map.get(TYPE)).trim())) {
				type = type + String.valueOf(map.get(TYPE)).trim() + comma;
			} else {
				type = type + comma + "";
			}
			if (map.get(DOMAINID) != null
					&& !"0".equals(String.valueOf(map.get(DOMAINID)).trim())) {
				domainId = domainId + String.valueOf(map.get(DOMAINID)) + comma;
			} else {
				domainId = domainId + comma + "";
			}
		}
		domainIdsMap.put(DOMIAN_ID_CONSTANT, domainId);
		domainIdsMap.put(TYPE_CONSTANT, type);
		domainIdsMap.put(ORG_CODE_CONSTANT, orgCode);
		return domainIdsMap;
	}

	/**
	 * 解析单个的加密后domainId
	 * 
	 * @param idTemp
	 * @param ip
	 * @return
	 */
	public static Map<String, Object> analyzeDomainId(String idTemp) {
		// if (ip == null || "".equals(ip.trim())) {
		// return null;
		// }
		if (idTemp == null || "".equals(idTemp.trim())) {
			return null;
		}
		String[] temp = idTemp.trim().split("_");

		if (temp.length <= 0) {
			return null;
		}
		Map<String, Object> decode = new HashMap<String, Object>();
		for (int i = 0; i < temp.length; i++) {
			if (ORGANIZATIONCODE.equals(temp[i].trim() + "_")) {
				decode.put(ORGANIZATIONCODE, decodeOrgCode(temp[i + 1]));
			} else if (DOMAINID.equals(temp[i].trim() + "_")) {
				decode.put(DOMAINID, Long.parseLong(decodeOrgCode(temp[i + 1])));
			} else if (TYPE.equals(temp[i].trim() + "_")) {
				decode.put(TYPE, decodeOrgCode(String.valueOf(temp[i + 1])));
			}// else if (IP.equals(temp[i].trim() + "_")) {
				// if (!ip.trim().equals(decodeOrgCode(temp[i + 1]))) {
				// return null;
				// }
			// }
		}

		return decode;
	}

	/**
	 * 对组织机构code进行加密处理
	 * 
	 * @param orgCode
	 * @return
	 */
	private static String encryptOrgCode(String orgCode) {
		String result = "";
		try {
			if (orgCode != null && !"".equals(orgCode.trim())) {
				DESPlus desPlus = new DESPlus();
				result = desPlus.encrypt(orgCode.trim());
			}
		} catch (Exception e) {
			throw new OperationFailedException("加密orgcode失败");
		}
		return result;
	}

	/**
	 * 对组织机构code进行反处理
	 * 
	 * @param orgCode
	 * @return
	 */
	private static String decodeOrgCode(String orgCode) {
		String result = "";
		try {
			if (orgCode != null && !"".equals(orgCode.trim())) {
				DESPlus desPlus = new DESPlus();
				result = desPlus.decrypt(orgCode.trim());
			}
		} catch (Exception e) {
			throw new OperationFailedException("解密orgcode失败");
		}
		return result;
	}

	public static void main(String[] args) {

		// Map<String, Object> map = null;
		// JobHelper.createMockAdminSession();
		// ThreadVariable.getSession().setLoginIp("127.0.0.1");
		// String str1 = encryptDomainId(12L, ".1.1.1.1.1.", "people");
		// String str2 = encryptDomainId(14l, ".1.12.25.", "LIST");
		// String str3 = encryptDomainId(16l, ".1.12.25.13.", "LIST_12.1_");
		// String str4 = encryptDomainId(16l, "", "LIST_12.1_");
		// String str5 = encryptDomainId(16l, "", "");
		// String str6 = "," + str1 + "," + str2 + "," + str3 + "," + str4 + ","
		// + str5 + ",";
		// System.out.println(str6);//
		// ,72ee_12_fc0c_fbf9073c8035df0780bb64706f8617d0_599d_009954210b8f0453_a12a_e2b859310f56d850e6b92e28c43dccd4_-dec-,72ee_14_fc0c_bcf47f530f7aa8032711597cc41f3345_599d_785207101194e77a_a12a_e2b859310f56d850e6b92e28c43dccd4_-dec-,72ee_16_fc0c_bcf47f530f7aa803a45a4cedd4f7368d_599d_ce7c1f3d84e9e06374014d91c510c680_a12a_e2b859310f56d850e6b92e28c43dccd4_-dec-,72ee_16_599d_ce7c1f3d84e9e06374014d91c510c680_a12a_e2b859310f56d850e6b92e28c43dccd4_-dec-,72ee_16_a12a_e2b859310f56d850e6b92e28c43dccd4_-dec-,
		// map = decodeDomainId(str6);
		// System.out.println(map.get(ORG_CODE_CONSTANT));//
		// .1.1.1.1.1.,.1.12.25.,.1.12.25.13.,,
		// System.out.println(map.get(DOMIAN_ID_CONSTANT));// 12,14,16,16,16
		// System.out.println(map.get(TYPE_CONSTANT));//
		// people,LIST,LIST_12.1_,LIST_12.1_,
		decodeDomainId("72ee_22_");
	}
}
