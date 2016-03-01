package com.tianque.plugin.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.dao.ReportFormDao;
import com.tianque.plugin.account.domain.ReportForm;
import com.tianque.plugin.account.vo.ReportFormVo;

@Repository("reportFormDao")
public class ReportFormDaoImpl extends AbstractBaseDao implements ReportFormDao {

	@Override
	public ReportForm addReportForm(ReportForm reportForm) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"reportForm.addReportForm", reportForm);

		return getSimpleReportFormById(id);

	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<ReportForm> findReportForms(ReportFormVo reportFormVo,
			Integer page, Integer rows) {
		int totalRecord = (Integer) getSqlMapClientTemplate().queryForObject(
				"reportForm.countFindReportForms", reportFormVo);
		PageInfo<ReportForm> result = new PageInfo<ReportForm>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(page);
		result.setPerPageSize(rows);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"reportForm.findReportForms", reportFormVo, (page - 1) * rows,
				rows));

		return result;
	}

	@Override
	public ReportForm updateReportForm(ReportForm reportForm) {
		getSqlMapClientTemplate().update("reportForm.updateReportForm",
				reportForm);
		return reportForm;
	}

	@Override
	public ReportForm getSimpleReportFormById(Long id) {
		return (ReportForm) getSqlMapClientTemplate().queryForObject(
				"reportForm.getSimpleReportFormById", id);
	}

	@Override
	public List<ReportForm> findSimpleReportFormByStepId(Long id) {
		return (List) getSqlMapClientTemplate().queryForList(
				"reportForm.getSimpleReportFormByStepId", id);
	}

}
