package com.tianque.plugin.account.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.ReportForm;
import com.tianque.plugin.account.vo.ReportFormVo;

public interface ReportFormDao {

	/**
	 * 新增呈报单
	 * 
	 * @param reportForm
	 * */
	public ReportForm addReportForm(ReportForm reportForm);

	/**
	 * 更新呈报单
	 * 
	 * @param reportForm
	 * */
	public ReportForm updateReportForm(ReportForm reportForm);

	/**
	 * 查询呈报单
	 * 
	 * */
	public PageInfo<ReportForm> findReportForms(ReportFormVo reportFormVo,
			Integer page, Integer rows);

	/*
	 * 根据编号获取呈报单
	 */
	public ReportForm getSimpleReportFormById(Long id);

	/*
	 * 根据步骤id获取呈报单
	 * 
	 * @param id
	 * 
	 * @return
	 */
	public List<ReportForm> findSimpleReportFormByStepId(Long id);

}
