package com.tianque.sysadmin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.property.GridInternalProperty;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.PropertyDomain;
import com.tianque.sysadmin.service.impl.ReferType;
import com.tianque.userAuth.api.PropertyDictDubboService;
import com.tianque.userAuth.api.PropertyDomainDubboService;

@Transactional
@Scope("prototype")
@Controller("propertyDictController")
public class PropertyDictController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(PropertyDictController.class);
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;

	@Autowired
	private PropertyDomainDubboService propertyDomainDubboService;

	private PropertyDict propertyDict;

	private PropertyDomain propertyDomain;

	private Long propertyDomainId;

	private List<PropertyDict> propertyDicts;

	private List<GridInternalProperty> gridInternalProperty;

	private Long referPropertyDictId;

	private String position;

	private int[] internalIds;

	private int count;

	public PropertyDomain getPropertyDomain() {
		return propertyDomain;
	}

	public void setPropertyDomain(PropertyDomain propertyDomain) {
		this.propertyDomain = propertyDomain;
	}

	public PropertyDict getPropertyDict() {
		return propertyDict;
	}

	public void setPropertyDict(PropertyDict propertyDict) {
		this.propertyDict = propertyDict;
	}

	public Long getPropertyDomainId() {
		return propertyDomainId;
	}

	public void setPropertyDomainId(Long propertyDomainId) {
		this.propertyDomainId = propertyDomainId;
	}

	public List<PropertyDict> getPropertyDicts() {
		return propertyDicts;
	}

	public void setPropertyDicts(List<PropertyDict> propertyDicts) {
		this.propertyDicts = propertyDicts;
	}

	public List<GridInternalProperty> getGridInternalProperty() {
		return gridInternalProperty;
	}

	public void setGridInternalProperty(
			List<GridInternalProperty> gridInternalProperty) {
		this.gridInternalProperty = gridInternalProperty;
	}

	public Long getReferPropertyDictId() {
		return referPropertyDictId;
	}

	public void setReferPropertyDictId(Long referPropertyDictId) {
		this.referPropertyDictId = referPropertyDictId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 根据域属性Id查询系统属性对象
	 * 
	 * @return SUCCESS
	 */
	public String findPropertyDict() {

		if (propertyDomainId == null) {
			this.errorMessage = "域属性不能为空！";
			return ERROR;
		}
		try {
			propertyDomain = propertyDomainDubboService
					.getPropertyDomainById(propertyDomainId);
			gridInternalProperty = propertyDomain.getInternaleProperties();
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("异常信息", e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 根据域属性Id查询属性字典
	 * 
	 * @return SUCCESS
	 */
	public String findPropertyDictByPropertyDomainId() {
		if (propertyDomainId == null) {
			this.errorMessage = "域属性不能为空！";
			return ERROR;
		}
		try {
			gridPage = new GridPage(
					propertyDictDubboService
							.findPropertyDictByPropertyDomainId(propertyDomainId));
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("异常信息", e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 新增系统属性字典信息
	 * 
	 * @return SUCCESS
	 */
	public String addPropertyDict() {
		if (propertyDict.getPropertyDomain() == null
				|| propertyDict.getPropertyDomain().getId() == null) {
			this.errorMessage = "请选择系统属性！";
			return ERROR;
		}
		if (ERROR.equals(validateDisplayName())) {
			this.errorMessage = "系统属性字典显示名称已经存在！";
			return ERROR;
		}
		try {
			propertyDict = propertyDictDubboService
					.addPropertyDict(propertyDict);
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 校验字典属性在数据库中是否已经存在
	 * 
	 * @return SUCCESS
	 */
	public String validateDisplayName() {
		propertyDicts = propertyDictDubboService
				.findPropertyDictByDisplayNameAndDomainPropertyId(
						propertyDomainId, propertyDict.getDisplayName(),
						propertyDict.getId());
		if (propertyDicts.size() >= 1) {
			return ERROR;
		} else {
			return SUCCESS;
		}
	}

	/**
	 * 根据域属性Id查询系统属性对象
	 * 
	 * @return SUCCESS
	 */
	public String prepareProperty() {
		if (DialogMode.ADD_MODE.equals(this.mode)) {
			propertyDomain = propertyDomainDubboService
					.getPropertyDomainById(propertyDomainId);
			gridInternalProperty = propertyDomain.getInternaleProperties();
		} else if (DialogMode.COPY_MODE.equals(this.mode)) {
			propertyDict = this.propertyDictDubboService
					.getPropertyDictById(propertyDict.getId());
			propertyDomain = propertyDict.getPropertyDomain();
			gridInternalProperty = propertyDomain.getInternaleProperties();
			propertyDict.setId(null);
		} else {
			propertyDict = this.propertyDictDubboService
					.getPropertyDictById(propertyDict.getId());
			propertyDomain = propertyDict.getPropertyDomain();
			gridInternalProperty = propertyDomain.getInternaleProperties();
		}
		return SUCCESS;
	}

	/**
	 * 根据域属性Id删除系统属性字典
	 * 
	 * @return SUCCESS
	 */
	public String deletePropertyDict() {
		try {
			this.propertyDictDubboService.deletePropertyDictById(propertyDict
					.getId());
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	public String deletePropertyDictById() {
		if (null == propertyDict || null == propertyDict.getId()) {
			this.errorMessage = "请选择字典类型！";
			return ERROR;
		}
		try {
			count = propertyDictDubboService
					.deletePropertyDictById(propertyDict.getId());
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	public String updatePropertyDict() {
		try {
			PropertyDict toUpdatePropertyDict = this.propertyDictDubboService
					.getPropertyDictById(propertyDict.getId());
			toUpdatePropertyDict.setDisplayName(propertyDict.getDisplayName());
			toUpdatePropertyDict.setInternalId(propertyDict.getInternalId());
			propertyDict = this.propertyDictDubboService
					.updatePropertyDict(toUpdatePropertyDict);
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	public String findPropertyDictByDomainName() {

		if (propertyDict != null) {
			propertyDicts = propertyDictDubboService
					.findPropertyDictByDomainNameAndInternalId(
							propertyDomain.getDomainName(),
							propertyDict.getInternalId());
		} else if (internalIds != null && internalIds.length > 0) {
			propertyDicts = propertyDictDubboService
					.findPropertyDictByDomainNameAndInternalIds(
							propertyDomain.getDomainName(), internalIds);
		} else {
			propertyDicts = propertyDictDubboService
					.findPropertyDictByDomainName(propertyDomain
							.getDomainName());
		}
		return SUCCESS;
	}

	public String getPropertyDictById() {
		try {
			propertyDict = propertyDictDubboService
					.getPropertyDictById(propertyDict.getId());
		} catch (Exception e) {
			logger.error("异常信息", e);
			return ERROR;
		}
		return SUCCESS;
	}

	public String movePropertyDict() {
		propertyDictDubboService.movePropertyDict(propertyDict.getId(),
				propertyDict.getPropertyDomain().getId(), getReferType(),
				referPropertyDictId);
		return SUCCESS;
	}

	private ReferType getReferType() {
		ReferType referType = null;
		ReferType[] values = ReferType.values();
		for (ReferType r : values) {
			if (r.toString().equals(position)) {
				referType = r;
			}
		}
		return referType;
	}

	public int[] getInternalIds() {
		return internalIds;
	}

	public void setInternalIds(int[] internalIds) {
		this.internalIds = internalIds;
	}

}
