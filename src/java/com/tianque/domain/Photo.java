package com.tianque.domain;

import com.tianque.core.base.BaseDomain;


@SuppressWarnings("serial")
public class Photo extends BaseDomain {
	private String oldFileName;
	private String newFileName;
	private String filePath;
	private String urlPath;
	private String extName;
	private String xPoint;
	private String yPoint;
	private String height;
	private String width;

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getExtName() {
		return extName;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}

	public String getXPoint() {
		return xPoint;
	}

	public void setXPoint(String xPoint) {
		this.xPoint = xPoint;
	}

	public String getYPoint() {
		return yPoint;
	}

	public void setYPoint(String yPoint) {
		this.yPoint = yPoint;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getOldFileName() {
		return oldFileName;
	}

	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

}
