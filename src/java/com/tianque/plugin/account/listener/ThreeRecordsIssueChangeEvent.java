package com.tianque.plugin.account.listener;

import java.util.List;

import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.state.ThreeRecordsIssueOperate;

public class ThreeRecordsIssueChangeEvent {
	/** 台账处理记录 */
	private ThreeRecordsIssueLogNew operateLog;

	private List<ThreeRecordsIssueAttachFile> operateFiles;
	/** 操作 */
	private ThreeRecordsIssueOperate operate;

	public ThreeRecordsIssueChangeEvent(ThreeRecordsIssueLogNew log,
			List<ThreeRecordsIssueAttachFile> operateFiles,
			ThreeRecordsIssueOperate operate) {
		this.operateLog = log;
		this.operate = operate;
		this.operateFiles = operateFiles;
	}

	public ThreeRecordsIssueLogNew getOperateLog() {
		return operateLog;
	}

	public void setOperateLog(ThreeRecordsIssueLogNew operateLog) {
		this.operateLog = operateLog;
	}

	public List<ThreeRecordsIssueAttachFile> getOperateFiles() {
		return operateFiles;
	}

	public void setOperateFiles(List<ThreeRecordsIssueAttachFile> operateFiles) {
		this.operateFiles = operateFiles;
	}

	public ThreeRecordsIssueOperate getOperate() {
		return operate;
	}

	public void setOperate(ThreeRecordsIssueOperate operate) {
		this.operate = operate;
	}

}
