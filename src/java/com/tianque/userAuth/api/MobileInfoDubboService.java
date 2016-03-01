package com.tianque.userAuth.api;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.mobile.domain.MobileInfo;

public interface MobileInfoDubboService {

	boolean existImsiNo(String imsiNo);

	MobileInfo addMobileInfo(MobileInfo mobileInfo);

	PageInfo<?> findMobileInfoForPage(Integer page, Integer rows, String sidx,
			String sord);

	MobileInfo getMobileInfoById(Long id);

	List<MobileInfo> searchMobileInfosForExport(MobileInfo mobileInfo,
			Integer page, Integer rows, String sidx, String sord);

	PageInfo<MobileInfo> findMobileInfosByQueryCondition(MobileInfo mobileInfo,
			Integer page, Integer rows, String sidx, String sord);

	int deleteMobileInfo(String imsiNo);
}
