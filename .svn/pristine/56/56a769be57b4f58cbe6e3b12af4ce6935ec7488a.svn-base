package com.tianque.core.base;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;

abstract public class BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String createUser;
	private Date createDate;
	private String sortField;
	private String order;

	private String updateUser;
	private Date updateDate;
	private PropertyDict dataFrom;
	
	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public void setDataFrom(PropertyDict dataFrom) {
		this.dataFrom = dataFrom;
	}

	public PropertyDict getDataFrom() {
		return dataFrom;
	}
	
	@Override
	public int hashCode() {
		if (id != null){
			final int prime = 31;
			int result = 1;
			result = prime * (prime *result+getClass().hashCode()) + id.hashCode();
			return result;
		}
		return super.hashCode();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ValidateResult validate(){
		return null;
	}
	
	
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BaseDomain)) {
			return false;
		}

		if ((getClass().isAssignableFrom(obj.getClass()))
				|| (obj.getClass().isAssignableFrom(getClass()))) {

		} else {
			return false;
		}

		BaseDomain other = (BaseDomain) obj;
		if (other.getId() == null || getId() == null) {
			return false;
		} else {
			if (other.getId().equals(getId())) {
				return true;
			} else {
				return false;
			}
		}
	}

	

	

	

}
