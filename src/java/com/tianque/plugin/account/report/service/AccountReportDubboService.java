package com.tianque.plugin.account.report.service;

import java.util.Map;

public interface AccountReportDubboService {
	public Map<Integer, Map<Integer, Integer>> getDataReport(Long orgId, int year, int month);
}
