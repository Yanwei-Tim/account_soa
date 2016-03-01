package com.tianque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.dao.ProclamationDao;
import com.tianque.domain.Proclamation;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.ProclamationService;

@Service("proclamationService")
@Transactional
public class ProclamationServiceImpl implements ProclamationService {

	@Autowired
	private ProclamationDao proclamationDao;

	@Override
	public PageInfo findProclamations(Integer page, Integer rows,
			String sortField, String order) {
		return proclamationDao.findProclamations(page, rows, sortField, order);
	}

	@Override
	public Proclamation getProclamationById(Long id) {
		return proclamationDao.getProclamationById(id);
	}

	@Override
	public Proclamation addProclamation(Proclamation proclamation) {
		if (proclamation.isDisplay() && !proclamationDao.updateDisplay())
			throw new BusinessValidationException("取消之前显示的公告时出错！");
		return proclamationDao.addProclamation(proclamation);
	}

	@Override
	public Proclamation updateProclamation(Proclamation proclamation) {
		if (proclamation.isDisplay() && !proclamationDao.updateDisplay())
			throw new BusinessValidationException("取消之前显示的公告时出错！");
		return proclamationDao.updateProclamation(proclamation);
	}

	@Override
	public Proclamation getDisplayProclamation() {
		return proclamationDao.getDisplayProclamation();
	}

	/**
	 * 为手机端新增查询系统公告方法
	 */
	@Override
	public Proclamation getDisplayProclamationForMobile() {
		Proclamation proclamation = null;
		try {
			proclamation = proclamationDao.getDisplayProclamationForMobile();
		} catch (Exception e) {
			throw new BusinessValidationException("查询系统公告出错");
		}
		return proclamation;
	}

}
