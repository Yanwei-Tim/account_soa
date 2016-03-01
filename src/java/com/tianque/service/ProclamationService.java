package com.tianque.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Proclamation;

public interface ProclamationService {

	PageInfo findProclamations(Integer page, Integer rows, String sortField, String order);

	Proclamation getProclamationById(Long id);

	Proclamation addProclamation(Proclamation proclamation);

	Proclamation updateProclamation(Proclamation proclamation);

	Proclamation getDisplayProclamation();
	
	/**为手机端新增查询系统公告方法*/
	Proclamation getDisplayProclamationForMobile();

}
