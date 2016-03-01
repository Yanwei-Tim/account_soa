/**
 * tianque-com.tianque.core.user.vo-PageInfo.java Created on 2009-8-5 下午04:36:04
 * Copyright (c) 2009 by 杭州天阙科技有限公司
 */
package com.tianque.core.vo;

import java.util.List;

/**
 * Title: 分页对象<br>
 * 
 * @author <a href=mailto:nifeng@hztianque.com>倪峰</a><br>
 * 
 * @description 用于封装分页<br/>
 * 
 * @version 1.0
 */
public class PageInfo<T> implements java.io.Serializable {
	
	private int perPageSize = 10;

	private int currentPage = 1;

	private List<T> result;

	private long totalRowSize = 0;
	
	public PageInfo() {};

	public PageInfo(int pageNum, int pageSize, Integer countNum, List<T> list) {
		int pageCount = 0;
		if((countNum % pageSize) == 0){
			pageCount = countNum / pageSize;
		}else{
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		
		setResult(list);
		setTotalRowSize(countNum);
		setCurrentPage(pageNum);
		setPerPageSize(pageSize);
		
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageNum() {
		if (totalRowSize % perPageSize > 0) {
			return (int) (totalRowSize / perPageSize) + 1;
		} else {
			return (int) (totalRowSize / perPageSize);
		}
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public long getTotalRowSize() {
		return totalRowSize;
	}

	public void setTotalRowSize(long totalRowSize) {
		this.totalRowSize = totalRowSize;
	}

	public boolean haveNextPage() {
		if ((currentPage - 1) * perPageSize + result.size() < totalRowSize) {
			return true;
		} else {
			return false;
		}
	}

	public boolean havePreviousPage() {
		if (currentPage > 1) {
			return true;
		} else {
			return false;
		}
	}

	public void setPerPageSize(int perPageSize) {
		this.perPageSize = perPageSize;
	}

	public int getPerPageSize() {
		return perPageSize;
	}

	public static PageInfo emptyPage(int pageSize) {
		return new PageInfo(1, pageSize, 0, null);
	}
}
