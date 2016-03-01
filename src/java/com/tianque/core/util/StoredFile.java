package com.tianque.core.util;

import java.io.File;

public class StoredFile {
	private String storedFilePath;
	private String storedFileName;
	private Long fileSize;
	private String storedTruthFileName;
	public String getStoredTruthFileName() {
		return storedTruthFileName;
	}
	public void setStoredTruthFileName(String storedTruthFileName) {
		this.storedTruthFileName = storedTruthFileName;
	}
	public String getStoredFilePath() {
		return storedFilePath;
	}
	public void setStoredFilePath(String storedFilePath) {
		this.storedFilePath = storedFilePath;
	}
	public String getStoredFileName() {
		return storedFileName;
	}
	public void setStoredFileName(String storedFileName) {
		this.storedFileName = storedFileName;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	public String getFullName(){
		return getStoredFilePath() + File.separatorChar + getStoredFileName();
	}
}
