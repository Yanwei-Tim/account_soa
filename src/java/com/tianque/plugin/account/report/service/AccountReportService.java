package com.tianque.plugin.account.report.service;

import java.util.Map;

public interface AccountReportService {
	public Map<Integer, Map<Integer, Integer>> getDataReport(Long orgId, int year, int month);
	
	public void initMonthRepot(final int year, final int month);
}
