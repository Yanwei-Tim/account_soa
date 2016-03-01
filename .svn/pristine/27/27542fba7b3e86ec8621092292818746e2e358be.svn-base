package com.tianque.core.validate;

import java.io.Serializable;
import java.util.List;

public class ValidateResult extends DomainValidateResult implements Serializable{

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public void addErrorMessage(int row,int column,String msg) {
		this.getMessages().add("在单元格["+convertToExcelCell(row,column-1)+"]发现数据错误，错误为："+msg);
	}

	public void addErrorMessage(int row,String msg){
		if(row > 0)
			this.getMessages().add("单元行第["+row+"]行" + msg);
		else
			this.getMessages().add("单元行第["+row+"]行" + msg);
	}


	private String convertToExcelCell(int row,int column){
		return getCellColumnString(column)+row;
	}

	private String getCellColumnString(int column){
		int letterPrefixIndex=column / 26;
		int letterIndex=column % 26;
		if (letterPrefixIndex>0){
			return getCellColumnString(letterPrefixIndex-1)+getCellColumnString(letterIndex);
		}
		return String.valueOf((char)(65+letterIndex));
	}

	public boolean hasError() {
		return messages.size()>0;
	}

}
