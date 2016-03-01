package com.tianque.plugin.account.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.PropertyDict;

/**
 * 困难群众成员
 * 
 * @author Administrator
 * 
 */
public class LedgerPoorPeopleMember extends BaseDomain {

	/*** 姓名 */
	private String name;
	/*** 性别 */
	private PropertyDict gender;
	/*** 联系电话 */
	private String telephone;
	/*** 固定电话 */
	private String fixPhone;
	/*** 身份证号 */
	private String idCardNo;
	/*** 保障类型 */
	private PropertyDict securityType;
	/*** 困难群众 */
	private LedgerPoorPeople ledgerPoorPeople;
	/*** 出生年月 */
	private Date birthday;
	/*** 与户主关系 */
	private String headHouseholdRelation;
	/*** 是否失业 */
	private Boolean unemployment;
	/*** 健康状况 */
	private String healthCondition;
	/*** 技能特长 */
	private String skillsSpeciality;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFixPhone() {
		return fixPhone;
	}

	public void setFixPhone(String fixPhone) {
		this.fixPhone = fixPhone;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public PropertyDict getSecurityType() {
		return securityType;
	}

	public void setSecurityType(PropertyDict securityType) {
		this.securityType = securityType;
	}

	public LedgerPoorPeople getLedgerPoorPeople() {
		return ledgerPoorPeople;
	}

	public void setLedgerPoorPeople(LedgerPoorPeople ledgerPoorPeople) {
		this.ledgerPoorPeople = ledgerPoorPeople;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getHeadHouseholdRelation() {
		return headHouseholdRelation;
	}

	public void setHeadHouseholdRelation(String headHouseholdRelation) {
		this.headHouseholdRelation = headHouseholdRelation;
	}

	public Boolean getUnemployment() {
		return unemployment;
	}

	public void setUnemployment(Boolean unemployment) {
		this.unemployment = unemployment;
	}

	public String getHealthCondition() {
		return healthCondition;
	}

	public void setHealthCondition(String healthCondition) {
		this.healthCondition = healthCondition;
	}

	public String getSkillsSpeciality() {
		return skillsSpeciality;
	}

	public void setSkillsSpeciality(String skillsSpeciality) {
		this.skillsSpeciality = skillsSpeciality;
	}
	

}
