package com.tianque.plugin.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class ThreeRecordsViewObject implements Serializable {
	private String dealUserName;
	private String dealdescription;
	private Date createDate;
	private String serialNumber;
	private Organization org;
	private String appealContent;
	private Integer responseGroupNo;
	private PropertyDict projectCategory;
	private PropertyDict buildType;
	private String projectName;
	private PropertyDict projectSubCategory;
	private Long beneficiaryNumber;
	private BigDecimal plannedInvestment;
	private BigDecimal selfFund;
	private BigDecimal gapFund;
	/** 水利  */
	private String name;
	private String mobileNumber;
	private String fromAddress;
	private String toAddress;
	private Long num;
	private Long centralized;
	private Double scatter;
	private Double impoundage;
	private Double mileage;//里程（包含城乡建设规划管理）
	/** 交通  */
	private PropertyDict roadCategory;//道路类别
	private PropertyDict roadSurfaceCategory;//路面类型
	private PropertyDict securityCategory;//安保设施类型(包括能源)
	private PropertyDict passengerCategory;//班线客运
	private PropertyDict publicTransportCategory;//城市公共交通
	private PropertyDict passengerManageCategory;//客运站管理
	private PropertyDict passengerBuildCategory;//客运站建设
	private Double rodeLength;
	private Double rodeWide;
	/** 能源  */
	private Long energyNumber;//数量
	private Double capacity;//容积
	private PropertyDict lineCategory;//线路类型
	private PropertyDict pipeLineCategory;//管道类型
	private PropertyDict pipeMaterialCategory;//管道材质
	/** 教育 */
	private String buildCompanyName;//建设单位名称
	private Double buildArea;//建设面积(包含医疗)
	private PropertyDict distanceCategory;//路程
	private PropertyDict roadConditionCategory;//路况
	/** 科技 */
	private PropertyDict projectLevelCategory;//项目级别
	private Long scienceScope;//规模
	/** 医疗 */
	private Long equipment;//设备
	/** 劳动 */
	private PropertyDict projectSubContentCategory;//项目类容
	private Long relationNumber;//涉及人数
	private BigDecimal money;//涉及金额
	private String company;//工资拖欠施工单位
	/** 环境保护*/
	private Double governNumber;//治理数量
	/** 城乡规划建设管理*/
	private String projectNumber;//项目库编号
	private Long scopeNumber;//数量(包含农业)
	private Double area;//面积
	/** 农业 */
	private BigDecimal quantities;//工程量
	/** 其他 */
	private String scopeContent;//公司规模及类容
	private String otherContent;//其他
	/** 困难*/
	private String permanentAddress;
	private PropertyDict gender;
	private java.util.Date birthDay;
	private Integer memberNo;
	private String poorType;
	private String poorSource;
	private PropertyDict securityType;
	/** 稳定 */
	private PropertyDict steadyWorkWarnLevel;
	private String serverContractor;
	private Integer involvingSteadyNum;
	private String idCardNo;
	private Organization occurOrg;
	private String involvingSteadyInfo;
	private PropertyDict steadyWorkType;
	private PropertyDict steadyWorkProblemType;
	
	public String getDealUserName() {
		return dealUserName;
	}
	public void setDealUserName(String dealUserName) {
		this.dealUserName = dealUserName;
	}
	public String getDealdescription() {
		return dealdescription;
	}
	public void setDealdescription(String dealdescription) {
		this.dealdescription = dealdescription;
	}
	@JSON(format = "yyyy-MM-dd")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Organization getOrg() {
		return org;
	}
	public void setOrg(Organization org) {
		this.org = org;
	}
	public String getAppealContent() {
		return appealContent;
	}
	public void setAppealContent(String appealContent) {
		this.appealContent = appealContent;
	}
	public Integer getResponseGroupNo() {
		return responseGroupNo;
	}
	public void setResponseGroupNo(Integer responseGroupNo) {
		this.responseGroupNo = responseGroupNo;
	}
	public PropertyDict getProjectCategory() {
		return projectCategory;
	}
	public void setProjectCategory(PropertyDict projectCategory) {
		this.projectCategory = projectCategory;
	}
	public PropertyDict getBuildType() {
		return buildType;
	}
	public void setBuildType(PropertyDict buildType) {
		this.buildType = buildType;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public PropertyDict getProjectSubCategory() {
		return projectSubCategory;
	}
	public void setProjectSubCategory(PropertyDict projectSubCategory) {
		this.projectSubCategory = projectSubCategory;
	}
	public Long getBeneficiaryNumber() {
		return beneficiaryNumber;
	}
	public void setBeneficiaryNumber(Long beneficiaryNumber) {
		this.beneficiaryNumber = beneficiaryNumber;
	}
	public BigDecimal getPlannedInvestment() {
		return plannedInvestment;
	}
	public void setPlannedInvestment(BigDecimal plannedInvestment) {
		this.plannedInvestment = plannedInvestment;
	}
	public BigDecimal getSelfFund() {
		return selfFund;
	}
	public void setSelfFund(BigDecimal selfFund) {
		this.selfFund = selfFund;
	}
	public BigDecimal getGapFund() {
		return gapFund;
	}
	public void setGapFund(BigDecimal gapFund) {
		this.gapFund = gapFund;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public Long getCentralized() {
		return centralized;
	}
	public void setCentralized(Long centralized) {
		this.centralized = centralized;
	}
	public Double getScatter() {
		return scatter;
	}
	public void setScatter(Double scatter) {
		this.scatter = scatter;
	}
	public Double getImpoundage() {
		return impoundage;
	}
	public void setImpoundage(Double impoundage) {
		this.impoundage = impoundage;
	}
	public Double getMileage() {
		return mileage;
	}
	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}
	public PropertyDict getRoadCategory() {
		return roadCategory;
	}
	public void setRoadCategory(PropertyDict roadCategory) {
		this.roadCategory = roadCategory;
	}
	public PropertyDict getRoadSurfaceCategory() {
		return roadSurfaceCategory;
	}
	public void setRoadSurfaceCategory(PropertyDict roadSurfaceCategory) {
		this.roadSurfaceCategory = roadSurfaceCategory;
	}
	public PropertyDict getSecurityCategory() {
		return securityCategory;
	}
	public void setSecurityCategory(PropertyDict securityCategory) {
		this.securityCategory = securityCategory;
	}
	public PropertyDict getPassengerCategory() {
		return passengerCategory;
	}
	public void setPassengerCategory(PropertyDict passengerCategory) {
		this.passengerCategory = passengerCategory;
	}
	public PropertyDict getPublicTransportCategory() {
		return publicTransportCategory;
	}
	public void setPublicTransportCategory(PropertyDict publicTransportCategory) {
		this.publicTransportCategory = publicTransportCategory;
	}
	public PropertyDict getPassengerManageCategory() {
		return passengerManageCategory;
	}
	public void setPassengerManageCategory(PropertyDict passengerManageCategory) {
		this.passengerManageCategory = passengerManageCategory;
	}
	public PropertyDict getPassengerBuildCategory() {
		return passengerBuildCategory;
	}
	public void setPassengerBuildCategory(PropertyDict passengerBuildCategory) {
		this.passengerBuildCategory = passengerBuildCategory;
	}
	public Double getRodeLength() {
		return rodeLength;
	}
	public void setRodeLength(Double rodeLength) {
		this.rodeLength = rodeLength;
	}
	public Double getRodeWide() {
		return rodeWide;
	}
	public void setRodeWide(Double rodeWide) {
		this.rodeWide = rodeWide;
	}
	public Long getEnergyNumber() {
		return energyNumber;
	}
	public void setEnergyNumber(Long energyNumber) {
		this.energyNumber = energyNumber;
	}
	public Double getCapacity() {
		return capacity;
	}
	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}
	public PropertyDict getLineCategory() {
		return lineCategory;
	}
	public void setLineCategory(PropertyDict lineCategory) {
		this.lineCategory = lineCategory;
	}
	public PropertyDict getPipeLineCategory() {
		return pipeLineCategory;
	}
	public void setPipeLineCategory(PropertyDict pipeLineCategory) {
		this.pipeLineCategory = pipeLineCategory;
	}
	public PropertyDict getPipeMaterialCategory() {
		return pipeMaterialCategory;
	}
	public void setPipeMaterialCategory(PropertyDict pipeMaterialCategory) {
		this.pipeMaterialCategory = pipeMaterialCategory;
	}
	public String getBuildCompanyName() {
		return buildCompanyName;
	}
	public void setBuildCompanyName(String buildCompanyName) {
		this.buildCompanyName = buildCompanyName;
	}
	public Double getBuildArea() {
		return buildArea;
	}
	public void setBuildArea(Double buildArea) {
		this.buildArea = buildArea;
	}
	public PropertyDict getDistanceCategory() {
		return distanceCategory;
	}
	public void setDistanceCategory(PropertyDict distanceCategory) {
		this.distanceCategory = distanceCategory;
	}
	public PropertyDict getRoadConditionCategory() {
		return roadConditionCategory;
	}
	public void setRoadConditionCategory(PropertyDict roadConditionCategory) {
		this.roadConditionCategory = roadConditionCategory;
	}
	public PropertyDict getProjectLevelCategory() {
		return projectLevelCategory;
	}
	public void setProjectLevelCategory(PropertyDict projectLevelCategory) {
		this.projectLevelCategory = projectLevelCategory;
	}
	public Long getScienceScope() {
		return scienceScope;
	}
	public void setScienceScope(Long scienceScope) {
		this.scienceScope = scienceScope;
	}
	public Long getEquipment() {
		return equipment;
	}
	public void setEquipment(Long equipment) {
		this.equipment = equipment;
	}
	public PropertyDict getProjectSubContentCategory() {
		return projectSubContentCategory;
	}
	public void setProjectSubContentCategory(PropertyDict projectSubContentCategory) {
		this.projectSubContentCategory = projectSubContentCategory;
	}
	public Long getRelationNumber() {
		return relationNumber;
	}
	public void setRelationNumber(Long relationNumber) {
		this.relationNumber = relationNumber;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Double getGovernNumber() {
		return governNumber;
	}
	public void setGovernNumber(Double governNumber) {
		this.governNumber = governNumber;
	}
	public String getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	public Long getScopeNumber() {
		return scopeNumber;
	}
	public void setScopeNumber(Long scopeNumber) {
		this.scopeNumber = scopeNumber;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public BigDecimal getQuantities() {
		return quantities;
	}
	public void setQuantities(BigDecimal quantities) {
		this.quantities = quantities;
	}
	public String getScopeContent() {
		return scopeContent;
	}
	public void setScopeContent(String scopeContent) {
		this.scopeContent = scopeContent;
	}
	public String getOtherContent() {
		return otherContent;
	}
	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public PropertyDict getGender() {
		return gender;
	}
	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}
	public java.util.Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(java.util.Date birthDay) {
		this.birthDay = birthDay;
	}
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	public String getPoorType() {
		return poorType;
	}
	public void setPoorType(String poorType) {
		this.poorType = poorType;
	}
	public String getPoorSource() {
		return poorSource;
	}
	public void setPoorSource(String poorSource) {
		this.poorSource = poorSource;
	}
	public PropertyDict getSecurityType() {
		return securityType;
	}
	public void setSecurityType(PropertyDict securityType) {
		this.securityType = securityType;
	}
	public PropertyDict getSteadyWorkWarnLevel() {
		return steadyWorkWarnLevel;
	}
	public void setSteadyWorkWarnLevel(PropertyDict steadyWorkWarnLevel) {
		this.steadyWorkWarnLevel = steadyWorkWarnLevel;
	}
	public String getServerContractor() {
		return serverContractor;
	}
	public void setServerContractor(String serverContractor) {
		this.serverContractor = serverContractor;
	}
	public Integer getInvolvingSteadyNum() {
		return involvingSteadyNum;
	}
	public void setInvolvingSteadyNum(Integer involvingSteadyNum) {
		this.involvingSteadyNum = involvingSteadyNum;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public Organization getOccurOrg() {
		return occurOrg;
	}
	public void setOccurOrg(Organization occurOrg) {
		this.occurOrg = occurOrg;
	}
	public String getInvolvingSteadyInfo() {
		return involvingSteadyInfo;
	}
	public void setInvolvingSteadyInfo(String involvingSteadyInfo) {
		this.involvingSteadyInfo = involvingSteadyInfo;
	}
	public PropertyDict getSteadyWorkType() {
		return steadyWorkType;
	}
	public void setSteadyWorkType(PropertyDict steadyWorkType) {
		this.steadyWorkType = steadyWorkType;
	}
	public PropertyDict getSteadyWorkProblemType() {
		return steadyWorkProblemType;
	}
	public void setSteadyWorkProblemType(PropertyDict steadyWorkProblemType) {
		this.steadyWorkProblemType = steadyWorkProblemType;
	}
	
}
