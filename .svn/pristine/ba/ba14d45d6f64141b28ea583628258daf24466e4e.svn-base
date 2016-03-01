package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Proclamation;

public interface ProclamationDao {

	PageInfo findProclamations(Integer page, Integer rows, String sortField, String order);

	Proclamation getProclamationById(Long id);

	Proclamation addProclamation(Proclamation proclamation);

	Proclamation updateProclamation(Proclamation proclamation);

	Proclamation getDisplayProclamation();
	
	/**手机端 获取系统公告*/
	Proclamation getDisplayProclamationForMobile();

	boolean updateDisplay();

}
