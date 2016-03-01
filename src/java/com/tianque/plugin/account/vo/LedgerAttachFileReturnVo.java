package com.tianque.plugin.account.vo;

import java.io.Serializable;
import java.util.List;

public class LedgerAttachFileReturnVo implements Serializable{
	
	private List<Long> attachFileId;
	
	private List<String> attachFileName;

	public List<Long> getAttachFileId() {
		return attachFileId;
	}

	public void setAttachFileId(List<Long> attachFileId) {
		this.attachFileId = attachFileId;
	}

	public List<String> getAttachFileName() {
		return attachFileName;
	}

	public void setAttachFileName(List<String> attachFileName) {
		this.attachFileName = attachFileName;
	}
	
	

}
