package com.tianque.plugin.account.report.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.plugin.account.report.service.AccountReportDubboService;
import com.tianque.plugin.account.report.service.AccountReportService;

@Component
public class AccountReportDubboServiceImpl implements AccountReportDubboService {
	
	@Autowired
	private AccountReportService reportService;

	@Override
	public Map<Integer, Map<Integer, Integer>> getDataReport(Long orgId,
			int year, int month) {
		return reportService.getDataReport(orgId, year, month);
	}
}
