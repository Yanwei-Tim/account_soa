package com.tianque.init.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkBenchType;
import com.tianque.init.AbstractSystemPropertiesInitialization;
import com.tianque.init.Initialization;
import com.tianque.init.xml.XmlUtil;
import com.tianque.userAuth.api.PropertyDictDubboService;
import com.tianque.userAuth.api.PropertyDomainDubboService;

public class SystemPropertiesInitialization extends
		AbstractSystemPropertiesInitialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public SystemPropertiesInitialization(
			PropertyDomainDubboService propertyDomainDubboService,
			PropertyDictDubboService propertyDictDubboService) {
		super(propertyDomainDubboService, propertyDictDubboService);
	}

	@Override
	public void init() {
		initOrganizationType();
		initOrganizationLevel();
		initWorkBenchType();
		logger.info(PropertyTypes.WORKBENCH_TYPE + "字典初始化结束!");
		initWaterBuildCategory();

		initTrafficBuildType();
		initTrafficPassenger();
		initTrafficPassengerBuild();
		initTrafficPassengerManage();
		initTrafficProject();
		initTrafficPublic();
		initTrafficRoad();
		initSecurity();
		initTrafficSurface();
		initLedgerOther();

		initLedgerAgriculture();

		initLedgerAgricultureCategory();

		initLedgerAgricultureSubCategory();

		initLedgerTownCategroy();

		initLedgerTownSubCategroy();

		initLedgeEnviroUnit();

		initLedgeEnviroCategroy();

		initLedgeEnviroSubCategroy();

		initLedgeMedicalBuildType();

		initLedgeMedicalCategroy();

		initLedgeMedicalSubCategroy();

		initLedgeEducationBuildType();

		initLedgeEducationCategroy();

		initLedgeEducationSubCategroy();

		initLedgeScienceBuildType();

		initLedgeScienceLevel();

		initLedgeScienceProject();

		initLedgeScienceSubProject();

		initLedgeEnergyBuildType();

		initLedgeEnergyUnit();

		initLedgeEnergyLine();
		initLedgeEnergyPipeLine();
		initLedgeEnergyPipeMaterial();
		initLedgeEnergyProject();
		initLedgeEnergySubProject();
		initLedgeEnergySecurity();
		initLedgeLabor();
		initLedgeLaborSub();
		initLedgeLaborContent();
		initLedgeTownBuildType();
		initLedgeEnviroBuildType();

		initLedgerPoorPeopleSecurityType();
		initLedgerPoorPeopleDifficultType();
		initLedgerPoorPeopleDifficultCause();
		initLedgerPoorPeopleSpecificNeed();
		initLedgerSteadyWorkInvolvingSteadyType();
		initLedgerSteadyWorkType();
		initLedgerSteadyWorkProblemType();
		initPeopleAspirationCreateTableType();
		initPeopleAspirationAppealHumanType();
		initTrafficSecurityService();
		initTrafficLevel();
		initScienceBroadcast();
		initLaborDignity();
		initLaborCripple();
		initLaborAge();
		initDegreeProperty();
		initTownSecurity();
		initAgricultureFraming();
		initAgricultureMachine();
		initOtherProject();
		initTrafficType();
		initEduScopeType();
		initEduModeType();
		initEduItemType();
		initEduRoadType();
		initEduDistanceType();
		initEduRoadConditionType();
		initEduDegreeType();
		initEduAddressType();

		initAssignMeetingType();
		logger.info(PropertyTypes.ASSGIN_MEETING_TYPE + "字典初始化结束!");
		
		initFormType();
		
		initLedgerType();
		logger.info(PropertyTypes.LEDGER_TYPE + "字典初始化结束!");
		
		initLedgerStatus();
		logger.info(PropertyTypes.LEDGER_STATUS + "字典初始化结束!");
		
		try {
			initPlugin();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void initPlugin() throws Exception, ClassNotFoundException,
			NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		String[] propertyInitClasses = XmlUtil.getSystemPropertiesInitClasses();
		for (String initClass : propertyInitClasses) {
			System.out.println(initClass);
			Class init = Class.forName(initClass);
			Constructor constructor = init.getDeclaredConstructor(new Class[] {
					PropertyDomainDubboService.class,
					PropertyDictDubboService.class });
			Initialization initialization = (Initialization) constructor
					.newInstance(new Object[] { propertyDomainDubboService,
							propertyDictDubboService });
			initialization.init();
		}
	}

	private void initWorkBenchType() {
		propertyDomain = addPropertyDomain(PropertyTypes.WORKBENCH_TYPE, false,
				null);
		addPropertyDict(WorkBenchType.SUPER_LEVEL_NAME,
				WorkBenchType.SUPER_LEVEL, 1);
		addPropertyDict(WorkBenchType.MIDDLE_LEVEL_NAME,
				WorkBenchType.MIDDLE_LEVEL, 2);
		addPropertyDict(WorkBenchType.LOWER_LEVEL_NAME,
				WorkBenchType.LOWER_LEVEL, 3);
	}

	private void initOrganizationType() {
		propertyDomain = addPropertyDomain(PropertyTypes.ORGANIZATION_TYPE,
				true, OrganizationType.getInternalProperties());
		addPropertyDict("行政区域", OrganizationType.ADMINISTRATIVE_REGION, 1);
		addPropertyDict("职能部门", OrganizationType.FUNCTIONAL_ORG, 2);
		addPropertyDict("其他", OrganizationType.OTHER, 3);
		addPropertyDict("党工委", OrganizationType.PARTYWORK, 4);
	}

	private void initOrganizationLevel() {
		propertyDomain = addPropertyDomain(PropertyTypes.ORGANIZATION_LEVEL,
				true, OrganizationLevel.getInternalProperties());
		addPropertyDict("片组片格", OrganizationLevel.GRID, 1);
		addPropertyDict("村（社区）", OrganizationLevel.VILLAGE, 2);
		addPropertyDict("乡镇（街道）", OrganizationLevel.TOWN, 3);
		addPropertyDict("县（区）", OrganizationLevel.DISTRICT, 4);
		addPropertyDict("市", OrganizationLevel.CITY, 5);
		addPropertyDict("省", OrganizationLevel.PROVINCE, 6);
		addPropertyDict("全国", OrganizationLevel.COUNTRY, 7);
	}

	private void initWaterCategory() {
		propertyDomain = addPropertyDomain(PropertyTypes.LEDGER_WATEER_PROJECT,
				false, null);
		addPropertyDict("饮水工程", 0, 1);
		addPropertyDict("山坪塘", 1, 2);
		addPropertyDict("石河堰", 2, 3);
		addPropertyDict("蓄水池", 3, 4);
		addPropertyDict("渠道", 4, 5);
		addPropertyDict("中小河流治理", 5, 6);
		addPropertyDict("其他", 6, 7);

	}

	private void initWaterSubCategory() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.LEDGER_WATEER_PROJECT_SUB_TYPE, false, null);
		// 水利服务
		addPropertyDict("农村饮水", 0, 1);
		addPropertyDict("场镇饮水", 0, 2);
		addPropertyDict("城市供水", 0, 3);
		addPropertyDict("其他", 0, 4);

		addPropertyDict("干渠", 4, 5);
		addPropertyDict("支渠", 4, 6);
		addPropertyDict("斗毛渠", 4, 7);
		addPropertyDict("其它", 4, 8);

		addPropertyDict("县管河流", 5, 9);
		addPropertyDict("乡管河流", 5, 10);

	}

	private void initWaterBuildCategory() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.LEDGER_WATEER_BUILD_TYPE, false, null);
		// 水利服务 新建、改建、扩建、维修
		addPropertyDict("新建", 0, 1);
		addPropertyDict("改建", 0, 2);
		addPropertyDict("扩建", 0, 3);
		addPropertyDict("维修", 0, 4);
	}

	private void initTrafficBuildType() {
		propertyDomain = addPropertyDomain(PropertyTypes.TRAFFIC_BUILD_TYPE,
				false, null);
		// 水利服务 新建、改建、扩建、维修
		addPropertyDict("新建", 0, 1);
		addPropertyDict("改建", 0, 2);
		addPropertyDict("扩建", 0, 3);
		addPropertyDict("维修", 0, 4);
	}

	private void initTrafficProject() {
		propertyDomain = addPropertyDomain(PropertyTypes.TRAFFIC_PROJECT,
				false, null);
		addPropertyDict("道路建设", 1, 1);
		addPropertyDict("道路维护", 2, 2);
		addPropertyDict("桥梁建设", 3, 3);
		addPropertyDict("桥梁维护", 4, 4);
		addPropertyDict("安保工程", 5, 5);
		addPropertyDict("客运", 6, 6);
		addPropertyDict("其他", 7, 7);
	}

	private void initTrafficRoad() {
		propertyDomain = addPropertyDomain(PropertyTypes.TRAFFIC_ROAD, false,
				null);
		// 国道、省道、县道、乡道、村道、社道、城区道路
		addPropertyDict("国道", 0, 1);
		addPropertyDict("省道", 0, 2);
		addPropertyDict("县道", 0, 3);
		addPropertyDict("乡道", 0, 4);
		addPropertyDict("村道", 0, 5);
		addPropertyDict("社道", 0, 6);
		addPropertyDict("城区道路", 0, 7);
	}

	private void initTrafficSurface() {
		propertyDomain = addPropertyDomain(PropertyTypes.TRAFFIC_ROADSURFACE,
				false, null);
		// 水泥、沥青、泥碎
		addPropertyDict("水泥", 0, 1);
		addPropertyDict("沥青", 0, 2);
		addPropertyDict("泥碎", 0, 3);
	}

	private void initSecurity() {
		propertyDomain = addPropertyDomain(PropertyTypes.TRAFFIC_SECURITY,
				false, null);
		// 防护栏、防撞墩、减速带、标志标牌、其他
		addPropertyDict("防护栏", 0, 1);
		addPropertyDict("防撞墩", 0, 2);
		addPropertyDict("减速带", 0, 3);
		addPropertyDict("标志标牌", 0, 4);
		addPropertyDict("其他", 0, 5);
	}

	private void initTrafficPassenger() {
		propertyDomain = addPropertyDomain(PropertyTypes.TRAFFIC_PASSENGER,
				false, null);
		// 农村客运、县际客运、市际客运、省际客运
		addPropertyDict("农村客运", 0, 1);
		addPropertyDict("县际客运", 0, 2);
		addPropertyDict("市际客运", 0, 3);
		addPropertyDict("省际客运", 0, 4);
	};

	private void initTrafficPassengerManage() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.TRAFFIC_PASSENGER_MANAGE, false, null);
		// 招呼站、客运站
		addPropertyDict("招呼站", 1, 1);
		addPropertyDict("客运站", 2, 2);
	};

	private void initTrafficPassengerBuild() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.TRAFFIC_PASSENGER_BUILD, false, null);
		// 招呼站、客运站
		addPropertyDict("招呼站", 1, 1);
		addPropertyDict("客运站", 2, 2);
	};

	private void initTrafficPublic() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.TRAFFIC_PUBLIC_TRANSPORT, false, null);
		// 招呼站、客运站
		addPropertyDict("公交汽车", 1, 1);
		addPropertyDict("出租汽车", 2, 2);
	}

	private void initLedgeLabor() {
		propertyDomain = addPropertyDomain(PropertyTypes.LABOR_PROJECT, false,
				null);
		addPropertyDict("就业", 1, 1);
		addPropertyDict("社会保障", 2, 2);
		addPropertyDict("农民工工资", 3, 3);
		addPropertyDict("其他", 4, 4);

	}

	private void initLedgeLaborSub() {
		propertyDomain = addPropertyDomain(PropertyTypes.LABOR_PROJECT_SUB,
				false, null);
		addPropertyDict("求职意愿登记", 1, 1);
		addPropertyDict("就业登记", 1, 2);
		addPropertyDict("失业登记", 1, 3);
		addPropertyDict("就业技能培训", 1, 4);

		addPropertyDict("养老保险", 2, 5);
		addPropertyDict("医疗保险", 2, 6);
		addPropertyDict("工伤保险", 2, 7);
		addPropertyDict("生育保险", 2, 8);
		addPropertyDict("失业保险", 2, 9);

		addPropertyDict("其它", 3, 10);
		addPropertyDict("其他", 4, 11);

	}

	private void initLedgeLaborContent() {
		propertyDomain = addPropertyDomain(PropertyTypes.LABOR_PROJECT_CONTENT,
				false, null);
		addPropertyDict("企业", 1, 1);
		addPropertyDict("建筑", 1, 2);
		addPropertyDict("运输", 1, 3);
		addPropertyDict("服务", 1, 4);
		addPropertyDict("其他求职意愿", 1, 5);

		addPropertyDict("个体经营", 2, 6);
		addPropertyDict("单位就业", 2, 7);
		addPropertyDict("灵活就业", 2, 8);
		addPropertyDict("其他就业登记", 2, 9);

		addPropertyDict("新成长失业人员", 3, 10);
		addPropertyDict("就业转失业人员", 3, 11);
		addPropertyDict("其他失业登记", 3, 12);

		addPropertyDict("职业技能培训", 4, 13);
		addPropertyDict("岗前培训", 4, 14);
		addPropertyDict("劳务品牌培训", 4, 15);
		addPropertyDict("其他就业技能培训", 4, 16);

		addPropertyDict("新型农村社会养老保险", 5, 17);
		addPropertyDict("城镇居民社会养老保险", 5, 18);
		addPropertyDict("城镇职工养老保险", 5, 19);
		addPropertyDict("城镇职工医疗保险", 6, 20);
		addPropertyDict("城镇居民医疗保险", 6, 21);

	}

	private void initLedgeEnergyProject() {
		propertyDomain = addPropertyDomain(PropertyTypes.ENERGY_PROJECT, false,
				null);
		addPropertyDict("天然气", 1, 1);
		addPropertyDict("石油液化气", 2, 2);
		addPropertyDict("汽柴油", 3, 3);
		addPropertyDict("电力", 4, 4);
		addPropertyDict("沼气池", 5, 5);
		addPropertyDict("其他", 6, 6);
	}

	private void initLedgeEnergySecurity() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.ENERGY_SECURITY_CATEGORY, false, null);

		addPropertyDict("防护栏", 0, 1);
		addPropertyDict("防撞墩", 2, 2);
		addPropertyDict("减速带", 2, 3);
		addPropertyDict("防火设施", 0, 4);
		addPropertyDict("安全标志标牌", 4, 5);
		addPropertyDict("使用说明书", 1, 6);
		addPropertyDict("警示牌", 5, 7);
		addPropertyDict("安全使用手册", 5, 8);
		addPropertyDict("其他", 5, 9);

	}

	private void initLedgeEnergySubProject() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.ENERGY_PROJECT_SUB_TYPE, false, null);
		addPropertyDict("管道建设", 1, 1);
		addPropertyDict("管道养护", 1, 2);
		addPropertyDict("户表工程", 1, 3);
		addPropertyDict("液化管道建设", 2, 4);
		addPropertyDict("液化管道养护", 2, 5);
		addPropertyDict("液化户表工程", 2, 6);

		addPropertyDict("加油站建设", 3, 7);
		addPropertyDict("电网设施", 4, 8);
		addPropertyDict("电网维护", 4, 9);
		addPropertyDict("电力户表工程", 4, 10);

		addPropertyDict("沼气池建设", 5, 11);
		addPropertyDict("沼气池安全设施建设", 5, 12);
	}

	private void initLedgeEnergyPipeMaterial() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.ENERGY_PIPE_MATERIAL_CATEGORY, false, null);
		addPropertyDict("PE管", 0, 1);
		addPropertyDict("钢管", 0, 2);
		addPropertyDict("其他", 0, 3);
	}

	private void initLedgeEnergyPipeLine() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.ENERGY_PIPELINE_CATEGORY, false, null);
		addPropertyDict("中压管道", 0, 1);
		addPropertyDict("低压管道", 0, 2);
		addPropertyDict("高压管道", 0, 3);
		addPropertyDict("户内管道", 0, 3);
	}

	private void initLedgeEnergyLine() {
		propertyDomain = addPropertyDomain(PropertyTypes.ENERGY_LINE_CATEGORY,
				false, null);
		addPropertyDict("变压器", 0, 1);
		addPropertyDict("高压线路", 0, 2);
		addPropertyDict("低压线路", 0, 3);
	}

	private void initLedgeEnergyUnit() {
		propertyDomain = addPropertyDomain(PropertyTypes.ENERGY_UNIT, false,
				null);
		addPropertyDict("处", 0, 1);
		addPropertyDict("KM", 0, 2);
		addPropertyDict("台", 0, 3);
		addPropertyDict("块", 0, 4);
		addPropertyDict("口", 0, 5);
	}

	private void initLedgeEnergyBuildType() {
		propertyDomain = addPropertyDomain(PropertyTypes.ENERGY_BUILD_TYPE,
				false, null);
		addPropertyDict("新建", 0, 1);
		addPropertyDict("改建", 0, 2);
		addPropertyDict("扩建", 0, 3);
		addPropertyDict("维修", 0, 4);
	}

	private void initLedgeScienceBuildType() {
		propertyDomain = addPropertyDomain(PropertyTypes.SCIENCE_BUILD_TYPE,
				false, null);
		addPropertyDict("新建", 0, 1);
		addPropertyDict("改建", 0, 2);
		addPropertyDict("扩建", 0, 3);
		addPropertyDict("维修", 0, 4);
		addPropertyDict("添置设备设施", 0, 5);
	}

	private void initLedgeScienceLevel() {
		propertyDomain = addPropertyDomain(PropertyTypes.SCIENCE_PROJECT_LEVEL,
				false, null);
		addPropertyDict("国家级类", 0, 1);
		addPropertyDict("省级类", 0, 2);
		addPropertyDict("市级类", 0, 3);
		addPropertyDict("县级类", 0, 4);
		addPropertyDict("其他", 0, 5);
	}

	private void initLedgeScienceProject() {
		propertyDomain = addPropertyDomain(PropertyTypes.SCIENCE_PROJECT,
				false, null);
		addPropertyDict("广播电视", 1, 1);
		addPropertyDict("旅游", 2, 2);
		addPropertyDict("文化", 3, 3);
		addPropertyDict("体育", 4, 4);
		addPropertyDict("科技、科协项目", 5, 5);
		addPropertyDict("科技、科协宣传", 6, 6);
		addPropertyDict("其它", 7, 7);
	}

	private void initLedgeScienceSubProject() {
		propertyDomain = addPropertyDomain(PropertyTypes.SCIENCE_PROJECT_SUB,
				false, null);

		addPropertyDict("电视“户户通”", 1, 1);
		addPropertyDict("广播“村村响”", 1, 2);
		addPropertyDict("电影“月月放”", 1, 3);

		addPropertyDict("A级旅游景区（点）管理", 2, 4);
		addPropertyDict("星级农家乐管理", 2, 5);
		addPropertyDict("星级旅游饭店管理", 2, 6);
		addPropertyDict("其他旅游", 2, 7);

		addPropertyDict("乡镇综合文化站", 3, 8);
		addPropertyDict("村（社区）农家书屋", 3, 9);
		addPropertyDict("其他文化", 3, 10);

		addPropertyDict("全民健身路径", 4, 4);
		addPropertyDict("乡镇社会体育指导站", 4, 4);
		addPropertyDict("其他体育", 4, 4);

	}

	private void initLedgeEducationCategroy() {
		propertyDomain = addPropertyDomain(PropertyTypes.EDUCATION_PROJECT,
				false, null);
		addPropertyDict("工程建设", 1, 1);
		addPropertyDict("就学", 2, 2);
		addPropertyDict("其他", 3, 3);
	}

	private void initLedgeEducationSubCategroy() {
		propertyDomain = addPropertyDomain(PropertyTypes.EDUCATION_PROJECT_SUB,
				false, null);
		addPropertyDict("幼儿园建设", 1, 1);
		addPropertyDict("农村薄弱学校改造", 1, 2);
		addPropertyDict("危房改造", 1, 3);
		addPropertyDict("教师周转房建设", 1, 4);
		addPropertyDict("维修维护", 1, 5);

		addPropertyDict("贫困大学新生资助", 2, 6);
		addPropertyDict("两免一补", 2, 7);
		addPropertyDict("高中及中职学生学杂费及生活困难补助", 2, 8);
		addPropertyDict("进城农民工子女就读", 2, 9);
		addPropertyDict("上学路途难", 2, 10);

	}

	private void initLedgeEducationBuildType() {
		propertyDomain = addPropertyDomain(PropertyTypes.EDUCATION_BUILD_TYPE,
				false, null);
		addPropertyDict("新建", 0, 1);
		addPropertyDict("改建", 0, 2);
		addPropertyDict("扩建", 0, 3);
		addPropertyDict("维修", 0, 4);
	}

	private void initLedgeMedicalBuildType() {
		propertyDomain = addPropertyDomain(PropertyTypes.MEDICAL_BUILD_TYPE,
				false, null);
		addPropertyDict("新建", 0, 1);
		addPropertyDict("改建", 0, 2);
		addPropertyDict("扩建", 0, 3);
		addPropertyDict("维修", 0, 4);
		addPropertyDict("添置设备", 0, 5);
	}

	private void initLedgeMedicalCategroy() {
		propertyDomain = addPropertyDomain(PropertyTypes.MEDICAL_PROJECT,
				false, null);
		addPropertyDict("食品卫生", 1, 1);
		addPropertyDict("公共卫生服务", 2, 2);
		addPropertyDict("医疗服务", 3, 3);
		addPropertyDict("新农合", 4, 4);
		addPropertyDict("服务能力建设", 5, 5);
		addPropertyDict("其他", 6, 5);
	}

	private void initLedgeMedicalSubCategroy() {
		propertyDomain = addPropertyDomain(PropertyTypes.MEDICAL_PROJECT_SUB,
				false, null);
		addPropertyDict("种养殖", 1, 1);
		addPropertyDict("生产", 1, 2);
		addPropertyDict("流通", 1, 3);
		addPropertyDict("消费", 1, 4);
		addPropertyDict("其他食品卫生", 1, 5);

		addPropertyDict("疾病预防", 2, 6);
		addPropertyDict("妇幼保健", 2, 7);
		addPropertyDict("卫生监督", 2, 8);
		addPropertyDict("卫生应急", 2, 9);
		addPropertyDict("精神卫生", 2, 10);
		addPropertyDict("其他公共卫生", 2, 11);

		addPropertyDict("服务质量", 3, 12);
		addPropertyDict("服务态度", 3, 13);
		addPropertyDict("医德医风", 3, 14);
		addPropertyDict("其他医疗服务", 3, 15);

		addPropertyDict("筹资", 4, 16);
		addPropertyDict("报销", 4, 17);
		addPropertyDict("监管", 4, 18);
		addPropertyDict("公示", 4, 19);
		addPropertyDict("其他新农合", 4, 20);

		addPropertyDict("队伍建设", 5, 21);
		addPropertyDict("医疗设备更新", 5, 22);
		addPropertyDict("基础设施", 5, 23);
		addPropertyDict("其他服务建设", 5, 24);

	}

	private void initLedgeEnviroCategroy() {
		propertyDomain = addPropertyDomain(PropertyTypes.ENVIRONMENT_PROJECT,
				false, null);
		addPropertyDict("工业企业", 1, 1);
		addPropertyDict("农村环保", 2, 2);
		addPropertyDict("生活污染源", 3, 3);
		addPropertyDict("电磁辐射", 4, 4);
		addPropertyDict("其他", 5, 5);
	}

	private void initLedgeEnviroSubCategroy() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.ENVIRONMENT_PROJECT_SUB, false, null);
		addPropertyDict("废水", 1, 1);
		addPropertyDict("废气", 1, 2);
		addPropertyDict("噪声", 1, 3);
		addPropertyDict("固体废弃物", 1, 4);
		addPropertyDict("其他工业物", 1, 5);

		addPropertyDict("畜禽养殖污染", 2, 6);
		addPropertyDict("土壤污染", 2, 7);
		addPropertyDict("白色污染", 2, 8);
		addPropertyDict("河流污染", 2, 9);
		addPropertyDict("水库污染", 2, 10);
		addPropertyDict("其他污染", 2, 11);

		addPropertyDict("生活污水（含地沟油）", 3, 12);
		addPropertyDict("垃圾处理", 3, 13);
		addPropertyDict("生活噪声", 3, 14);
		addPropertyDict("餐饮油烟", 3, 15);
		addPropertyDict("其他生活污染", 3, 16);

		addPropertyDict("电力设施", 4, 17);
		addPropertyDict("通信网络", 4, 18);
		addPropertyDict("三类射线辐射", 4, 19);
		addPropertyDict("其他辐射污染", 4, 20);

	}

	private void initLedgeEnviroUnit() {
		propertyDomain = addPropertyDomain(PropertyTypes.ENVIRONMENT_UNIT,
				false, null);
		addPropertyDict("吨/天", 0, 1);
		addPropertyDict("平方米", 0, 2);
		addPropertyDict("立方米", 0, 3);
		addPropertyDict("分贝", 0, 4);
		addPropertyDict("吨", 0, 5);
	}

	private void initLedgeEnviroBuildType() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.ENVIRONMENT_BUILD_TYPE, false, null);
		addPropertyDict("新建", 0, 1);
		addPropertyDict("改建", 0, 2);
		addPropertyDict("扩建", 0, 3);
		addPropertyDict("维修", 0, 4);
		addPropertyDict("整治", 0, 5);
		addPropertyDict("添置设备", 0, 6);
	}

	private void initLedgeTownBuildType() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.TOWN_PROJECT_BUILD_TYPE, false, null);
		addPropertyDict("新建", 0, 1);
		addPropertyDict("改建", 0, 2);
		addPropertyDict("扩建", 0, 3);
		addPropertyDict("维修", 0, 4);
		addPropertyDict("添置设施、设备", 0, 5);
	}

	private void initLedgerTownCategroy() {
		propertyDomain = addPropertyDomain(PropertyTypes.TOWN_PROJECT, false,
				null);
		addPropertyDict("城市管理", 1, 1);
		addPropertyDict("城市街道", 2, 2);
		addPropertyDict("市政公共设施", 3, 3);
		addPropertyDict("市政环卫", 4, 4);
		addPropertyDict("村镇规划建设管理", 5, 5);
		addPropertyDict("住房保障", 6, 6);
		addPropertyDict("建筑质量安全", 7, 7);
		addPropertyDict("其他", 8, 8);
	}

	private void initLedgerTownSubCategroy() {
		propertyDomain = addPropertyDomain(PropertyTypes.TOWN_PROJECT_SUB,
				false, null);
		addPropertyDict("占道经营", 1, 1);
		addPropertyDict("旧城改造", 1, 2);
		addPropertyDict("环境治理", 1, 3);
		addPropertyDict("其他城市管理", 1, 4);

		addPropertyDict("建设", 2, 5);
		addPropertyDict("维护", 2, 6);
		addPropertyDict("安保", 2, 7);

		addPropertyDict("公共停车场", 3, 8);
		addPropertyDict("道路桥梁", 3, 9);
		addPropertyDict("道路照明", 3, 10);
		addPropertyDict("排水设施", 3, 11);
		addPropertyDict("公园", 3, 12);
		addPropertyDict("广场", 3, 13);
		addPropertyDict("城区河道", 3, 14);
		addPropertyDict("公共绿地", 3, 15);

		addPropertyDict("公厕", 4, 16);
		addPropertyDict("环卫设施", 4, 17);
		addPropertyDict("城区生活垃圾处理", 4, 18);

		addPropertyDict("农村规划建设管理", 5, 19);
		addPropertyDict("场镇规划建设管理", 5, 20);
		addPropertyDict("新农村建设", 5, 21);
		addPropertyDict("基础设施配套产", 5, 22);
		addPropertyDict("其他村镇规划", 5, 23);

		addPropertyDict("廉租房", 6, 24);
		addPropertyDict("公租房", 6, 25);
		addPropertyDict("安置房", 6, 26);
		addPropertyDict("危房改造", 6, 27);
		addPropertyDict("经济适用房", 6, 28);
		addPropertyDict("其他住房保障", 6, 29);

		addPropertyDict("房屋", 7, 30);
		addPropertyDict("桥梁", 7, 31);
		addPropertyDict("工地", 7, 32);
		addPropertyDict("路段", 7, 33);
		addPropertyDict("公园绿地", 7, 34);
		addPropertyDict("其他建筑", 7, 35);

	}

	private void initLedgerAgricultureCategory() {
		propertyDomain = addPropertyDomain(PropertyTypes.AGRICULTURE_PROJECT,
				false, null);
		addPropertyDict("农业产业化", 1, 1);
		addPropertyDict("田间工程", 2, 2);
		addPropertyDict("电力提灌站", 3, 3);
		addPropertyDict("农业培训", 4, 4);
		addPropertyDict("其它", 5, 5);
	}

	private void initLedgerAgricultureSubCategory() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.AGRICULTURE_PROJECT_SUB, false, null);
		addPropertyDict("粮食生产", 1, 1);
		addPropertyDict("油料生产", 1, 2);
		addPropertyDict("食用菌", 1, 3);
		addPropertyDict("大棚西瓜", 1, 4);
		addPropertyDict("设施蔬菜", 1, 5);
		addPropertyDict("中药材", 1, 6);
		addPropertyDict("干果生产", 1, 7);
		addPropertyDict("水果生产", 1, 8);
		addPropertyDict("其他", 1, 9);

		addPropertyDict("排灌沟渠", 2, 10);
		addPropertyDict("作业道", 2, 11);
		addPropertyDict("机耕道", 2, 12);
		addPropertyDict("蓄水池", 2, 13);
		addPropertyDict("堰塘防渗", 2, 14);
		addPropertyDict("其它", 2, 15);

		addPropertyDict("电力提灌站", 3, 16);
		addPropertyDict("机沉井", 3, 17);
		addPropertyDict("农业培训", 4, 18);
		addPropertyDict("农机培训", 4, 19);
	}

	private void initLedgerAgriculture() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.AGRICULTURE_PROJECT_BUILD_TYPE, false, null);
		addPropertyDict("新建", 0, 1);
		addPropertyDict("改建", 0, 2);
		addPropertyDict("扩建", 0, 3);
		addPropertyDict("维修", 0, 4);
	}

	private void initLedgerOther() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.LEDGER_OTHER_BUILD_TYPE, false, null);
		addPropertyDict("新建", 1, 1);
		addPropertyDict("改建", 2, 2);
		addPropertyDict("扩建", 3, 3);
		addPropertyDict("维修", 4, 4);
		addPropertyDict("其他", 5, 5);
	}

	private void initLedgerPoorPeopleSecurityType() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.LEDGER_POOR_PEOPLE_SECURITY_TYPE, false, null);
		addPropertyDict("城镇低保", 0, 1);
		addPropertyDict("农村低保", 1, 2);
		addPropertyDict("农村五保", 2, 3);

	}

	private void initLedgerPoorPeopleDifficultType() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.LEDGER_POOR_PEOPLE_DIFFICULT_TYPE, false, null);
		addPropertyDict("生活", 0, 1);
		addPropertyDict("医疗", 1, 2);
		addPropertyDict("住房", 2, 3);
		addPropertyDict("就学", 3, 4);
		addPropertyDict("就业", 4, 5);
		addPropertyDict("其他困难", 5, 6);
	}

	private void initLedgerPoorPeopleDifficultCause() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.LEDGER_POOR_PEOPLE_DIFFICULT_CAUSE, false, null);
		addPropertyDict("因病", 0, 1);
		addPropertyDict("因残", 0, 2);
		addPropertyDict("因灾", 0, 3);
		addPropertyDict("缺乏劳动能力", 0, 4);
		addPropertyDict("生活-其他", 0, 5);

		addPropertyDict("重大疾病", 1, 6);
		addPropertyDict("医疗-其他", 1, 7);

		addPropertyDict("危房改造", 2, 8);
		addPropertyDict("水灾", 2, 9);
		addPropertyDict("地灾", 2, 10);
		addPropertyDict("火灾", 2, 11);
		addPropertyDict("贫困", 2, 12);
		addPropertyDict("住房-其他", 2, 13);

		addPropertyDict("学前教育", 3, 14);
		addPropertyDict("小学", 3, 15);
		addPropertyDict("初中", 3, 16);
		addPropertyDict("高中职高", 3, 17);
		addPropertyDict("大学", 3, 18);
		addPropertyDict("特殊教育", 3, 19);
		addPropertyDict("就学-其他", 3, 20);

		addPropertyDict("城镇登记失业人员", 4, 22);
		addPropertyDict("4050人员", 4, 23);
		addPropertyDict("残疾人员", 4, 21);
		addPropertyDict("低收入家庭人员", 4, 22);
		addPropertyDict("按城镇人口安置的被征地农民", 4, 23);
		addPropertyDict("连续失业一年以上的人员", 4, 21);
		addPropertyDict("土地（林地）被依法征用或流转的农村劳动者", 4, 22);
		addPropertyDict("就业-其他", 4, 23);

		addPropertyDict("其他", 5, 24);

	}

	private void initLedgerPoorPeopleSpecificNeed() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.LEDGER_POOR_PEOPLE_SPECIFIC_NEED, false, null);
		addPropertyDict("口粮", 0, 1);
		addPropertyDict("五保", 1, 2);
		addPropertyDict("低保", 2, 3);
		addPropertyDict("救助资金", 3, 4);
		addPropertyDict("救助物资", 4, 5);
		addPropertyDict("住房", 5, 6);
		addPropertyDict("职业培训", 6, 7);
		addPropertyDict("职业指导", 7, 8);
		addPropertyDict("求职登记", 8, 9);
		addPropertyDict("职业介绍", 9, 10);
		addPropertyDict("其他", 10, 11);
	}

	private void initLedgerSteadyWorkInvolvingSteadyType() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.STEADY_RECORD_WORK_INVOLVING_STEADY_TYPE, false,
				null);
		addPropertyDict("个人", 0, 1);
		addPropertyDict("反映群体代表", 1, 2);
	}

	private void initLedgerSteadyWorkType() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.STEADY_RECORD_WORK_TYPE, false, null);
		addPropertyDict("涉法涉诉", 0, 1);
		addPropertyDict("林水土", 1, 2);
		addPropertyDict("惠农政策及村（社区）政务、财务", 2, 3);
		addPropertyDict("民政问题", 3, 4);
		addPropertyDict("人口与医疗卫生", 4, 5);
		addPropertyDict("劳动保障", 5, 6);
		addPropertyDict("交通运输", 6, 7);
		addPropertyDict("城建及综合执法", 7, 8);
		addPropertyDict("党纪政纪", 8, 9);
		addPropertyDict("教育", 9, 10);
		addPropertyDict("企业改制", 10, 11);
		addPropertyDict("环境保护", 11, 12);
		addPropertyDict("组织人事", 12, 13);
		addPropertyDict("其他稳定工作", 13, 14);
		addPropertyDict("重点人员", 14, 15);
		addPropertyDict("其他", 15, 16);
	}

	private void initLedgerSteadyWorkProblemType() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.STEADY_RECORD_WORK_PROBLEM_TYPE, false, null);
		addPropertyDict("社会治安", 0, 1);
		addPropertyDict("民事申诉", 0, 2);
		addPropertyDict("刑事申诉", 0, 3);
		addPropertyDict("行政申诉", 0, 4);
		addPropertyDict("诉讼执行", 0, 5);
		addPropertyDict("刑事案件侦破", 0, 6);
		addPropertyDict("民事经济纠纷调解", 0, 7);
		addPropertyDict("司法作风", 0, 8);
		addPropertyDict("立案", 0, 9);
		addPropertyDict("涉法涉诉-其它", 0, 10);

		addPropertyDict("土地征用", 1, 11);
		addPropertyDict("土地承包", 1, 12);
		addPropertyDict("土地纠纷", 1, 13);
		addPropertyDict("水库水电移民", 1, 14);
		addPropertyDict("宅基地", 1, 15);
		addPropertyDict("退耕还林", 1, 16);
		addPropertyDict("地质灾害", 1, 17);
		addPropertyDict("林水土-其它", 1, 18);

		addPropertyDict("村社政务、财务", 2, 19);
		addPropertyDict("集体收入分配", 2, 20);
		addPropertyDict("集体工程项目", 2, 21);
		addPropertyDict("农民负担", 2, 22);
		addPropertyDict("政策性直补", 2, 23);
		addPropertyDict("危房改造", 2, 24);
		addPropertyDict("惠农政策及村（社区）政务、财务-其他", 2, 25);

		addPropertyDict("城镇和农村低保", 3, 26);
		addPropertyDict("复员退伍军人安置", 3, 27);
		addPropertyDict("退伍军人", 3, 28);
		addPropertyDict("伤残军人", 3, 29);
		addPropertyDict("优抚政策", 3, 30);
		addPropertyDict("村社选举", 3, 31);
		addPropertyDict("救灾和灾后安置", 3, 32);
		addPropertyDict("特困人员救助", 3, 33);
		addPropertyDict("民政问题-其他", 3, 34);

		addPropertyDict("医政管理", 4, 35);
		addPropertyDict("食品管理", 4, 36);
		addPropertyDict("药品管理", 4, 37);
		addPropertyDict("医患纠纷", 4, 38);
		addPropertyDict("新农合", 4, 39);
		addPropertyDict("传染病控制", 4, 40);
		addPropertyDict("计生优抚政策", 4, 41);
		addPropertyDict("再生育审批", 4, 42);
		addPropertyDict("违法生育处理", 4, 43);
		addPropertyDict("人口与医疗卫生-其他", 4, 44);

		addPropertyDict("城镇企业职工养老保险", 5, 45);
		addPropertyDict("城镇居民养老保险", 5, 46);
		addPropertyDict("农村居民养老保险", 5, 47);
		addPropertyDict("城镇职工医疗保险", 5, 48);
		addPropertyDict("城镇居民医疗保险", 5, 49);
		addPropertyDict("失业保险", 5, 50);
		addPropertyDict("工伤保险", 5, 51);
		addPropertyDict("生育保险", 5, 52);
		addPropertyDict("就业促进", 5, 53);
		addPropertyDict("农民工工资", 5, 54);
		addPropertyDict("特种行业保护", 5, 55);
		addPropertyDict("劳动仲裁", 5, 56);
		addPropertyDict("劳动保障-其他", 5, 57);

		addPropertyDict("道路运输安全", 6, 58);
		addPropertyDict("交通施工", 6, 59);
		addPropertyDict("行政管理", 6, 60);
		addPropertyDict("公路收费", 6, 61);
		addPropertyDict("水上安全", 6, 62);
		addPropertyDict("交通运输-其他", 6, 63);

		addPropertyDict("征地拆迁", 7, 64);
		addPropertyDict("城乡规划及实施", 7, 65);
		addPropertyDict("房产纠纷", 7, 66);
		addPropertyDict("工程质量", 7, 67);
		addPropertyDict("城乡环境综合执法", 7, 68);
		addPropertyDict("市政建设", 7, 69);
		addPropertyDict("城建及综合执法-其他", 7, 70);

		addPropertyDict("干部作风", 8, 71);
		addPropertyDict("贪污受贿", 8, 72);
		addPropertyDict("违法违规", 8, 73);
		addPropertyDict("干部处分", 8, 74);
		addPropertyDict("党纪政纪-其他", 8, 75);

		addPropertyDict("学校及在职教职工管理", 9, 76);
		addPropertyDict("退休教师", 9, 77);
		addPropertyDict("学生安全", 9, 78);
		addPropertyDict("教育布局", 9, 79);
		addPropertyDict("民办、代课教师及其他临聘人员", 9, 80);
		addPropertyDict("教师待遇", 9, 81);
		addPropertyDict("教育收费", 9, 82);
		addPropertyDict("教育-其他", 9, 83);

		addPropertyDict("企业破产", 10, 84);
		addPropertyDict("资产清算", 10, 85);
		addPropertyDict("职工安置", 10, 86);
		addPropertyDict("企业改制-其他", 10, 87);

		addPropertyDict("水、空气、噪音污染", 11, 88);
		addPropertyDict("污染物排放", 11, 89);
		addPropertyDict("环境保护-其他", 11, 90);

		addPropertyDict("离退休人员待遇", 12, 91);
		addPropertyDict("工资福利待遇", 12, 92);
		addPropertyDict("干部身份", 12, 93);
		addPropertyDict("军转干部", 12, 94);
		addPropertyDict("提拔任用", 12, 95);
		addPropertyDict("临聘人员", 12, 96);
		addPropertyDict("机构改革", 12, 97);
		addPropertyDict("组织人事-其他", 12, 98);

		addPropertyDict("精简下放", 13, 99);
		addPropertyDict("公私合营", 13, 100);
		addPropertyDict("经租房", 13, 101);
		addPropertyDict("乡镇债务", 13, 102);
		addPropertyDict("民间纠纷", 13, 103);
		addPropertyDict("其他", 13, 104);

		addPropertyDict("社区矫正人员", 14, 105);
		addPropertyDict("易肇事肇祸精神病人", 14, 106);
		addPropertyDict("重点青少年或群体", 14, 107);
		addPropertyDict("吸毒人员", 14, 108);
		addPropertyDict("吞食异物违法犯罪嫌疑人员", 14, 109);
		addPropertyDict("邪教及其他组织人员", 14, 110);
		addPropertyDict("其他应重点监管人员", 14, 111);
		addPropertyDict("其他内容", 15, 112);

	}

	public void initPeopleAspirationCreateTableType() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.LEDGER_PEOPLEASPIRATION_CREATE_TABLE_TYPE, false,
				null);
		addPropertyDict("新建", 0, 1);
		addPropertyDict("上年接转", 1, 2);
		addPropertyDict("其他台账转入", 2, 3);
		addPropertyDict("上级主管部门和县级领导班子有关领导交办(县级部门选填)", 3, 4);
		addPropertyDict("县委县政府及县级领导班子有关领导交办(乡镇选填)", 4, 5);
		addPropertyDict("县人大议案、建议、意见交办", 5, 6);
		addPropertyDict("县政协提案、建议、意见交办", 6, 7);

	}

	private void initPeopleAspirationAppealHumanType() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.LEDGER_APPEAL_HUMAN_TYPE, false, null);
		addPropertyDict("反映人", 0, 1);
		addPropertyDict("反映群体代表", 1, 2);
	}

	private void initOtherProject() {
		propertyDomain = addPropertyDomain(PropertyTypes.OTHER_PROJECT, false,
				null);
		addPropertyDict("建设工程", 1, 1);
		addPropertyDict("其他", 2, 2);

	}

	private void initAgricultureMachine() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.AGRICULTURE_MACHINERY_TRAIN, false, null);
		addPropertyDict("拖拉机驾驶员", 1, 1);
		addPropertyDict("其他操作", 2, 2);
		addPropertyDict("其他", 3, 3);
	}

	private void initAgricultureFraming() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.AGRICULTURE_FARMING_TRAIN, false, null);
		addPropertyDict("农技", 1, 1);
		addPropertyDict("农经", 2, 2);
		addPropertyDict("阳光工程", 3, 3);
		addPropertyDict("其他", 4, 4);

	}

	private void initTownSecurity() {
		propertyDomain = addPropertyDomain(PropertyTypes.TOWN_SECURITY_TYPE,
				false, null);
		addPropertyDict("防护栏", 1, 1);
		addPropertyDict("防撞柱", 2, 2);
		addPropertyDict("减速带", 3, 3);
		addPropertyDict("标志标牌", 4, 4);
		addPropertyDict("其它", 5, 5);
	}

	private void initDegreeProperty() {
		propertyDomain = addPropertyDomain(PropertyTypes.LEDGER_DEGREE, false,
				null);
		addPropertyDict("博士", 0, 1);
		addPropertyDict("研究生", 0, 2);
		addPropertyDict("大学本科", 0, 3);
		addPropertyDict("大专", 0, 4);
		addPropertyDict("高中\\中专", 0, 5);
		addPropertyDict("初中", 0, 6);
		addPropertyDict("小学", 0, 7);
		addPropertyDict("文盲", 0, 8);
		addPropertyDict("学龄前", 0, 9);
	}

	private void initLaborAge() {
		propertyDomain = addPropertyDomain(PropertyTypes.LABOR_AGE, false, null);
		addPropertyDict("60周岁以上", 1, 1);
		addPropertyDict("16—59周岁", 2, 2);
	}

	private void initLaborCripple() {
		propertyDomain = addPropertyDomain(PropertyTypes.LABOR_CRIPPLE, false,
				null);
		addPropertyDict("1—2级", 1, 1);
		addPropertyDict("3—4级", 2, 2);
		addPropertyDict("否", 3, 3);
	}

	private void initLaborDignity() {
		propertyDomain = addPropertyDomain(PropertyTypes.LABOR_DIGNITY, false,
				null);
		addPropertyDict("行政事业", 1, 1);
		addPropertyDict("企业", 2, 2);
		addPropertyDict("个体", 3, 3);
		addPropertyDict("学生", 4, 4);
		addPropertyDict("失地农民", 5, 5);
		addPropertyDict("重度残疾", 6, 6);
		addPropertyDict("新生婴儿", 7, 7);
		addPropertyDict("其他", 8, 8);
	}

	private void initScienceBroadcast() {
		propertyDomain = addPropertyDomain(PropertyTypes.SCIENCE_BROADCAST,
				false, null);
		addPropertyDict("直播卫星", 1, 1);
		addPropertyDict("有线电视“户户通”", 1, 2);
		addPropertyDict("地面数字电视“户户通”", 1, 3);
		addPropertyDict("广播站室", 2, 4);
		addPropertyDict("广播", 2, 5);
		addPropertyDict("村(社区)", 3, 6);
		addPropertyDict("其他", 7, 7);

	}

	private void initTrafficSecurityService() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.TRAFFIC_SECURITY_SERVICE, false, null);
		addPropertyDict("线路数量", 1, 1);
		addPropertyDict("覆盖区域", 2, 2);
		addPropertyDict("站台数量", 2, 3);
		addPropertyDict("车辆数量", 3, 4);
		addPropertyDict("安全管理", 4, 5);
		addPropertyDict("收费情况", 0, 6);
		addPropertyDict("服务质量", 0, 7);
		addPropertyDict("其他", 0, 8);

	}

	private void initTrafficLevel() {
		propertyDomain = addPropertyDomain(PropertyTypes.TRAFFIC_LEVEL, false,
				null);
		addPropertyDict("一级", 1, 1);
		addPropertyDict("二级", 2, 2);
		addPropertyDict("三级", 3, 3);
		addPropertyDict("四级", 4, 4);
		addPropertyDict("简易", 5, 5);
	}

	private void initTrafficType() {
		propertyDomain = addPropertyDomain(PropertyTypes.TRAFFIC_PASSTYPE,
				false, null);
		addPropertyDict("班线客运", 1, 1);
		addPropertyDict("城市公共交通", 2, 2);
		addPropertyDict("客运站管理", 3, 3);
		addPropertyDict("客运站建设", 4, 4);
	}

	private void initEduScopeType() {
		propertyDomain = addPropertyDomain(PropertyTypes.EDUCATION_SCOPE_TYPE,
				false, null);
		addPropertyDict("一个班", 1, 1);
		addPropertyDict("三个班", 0, 2);
		addPropertyDict("六个班", 0, 3);
		addPropertyDict("九个班以上", 1, 4);
		addPropertyDict("九个班", 2, 5);
		addPropertyDict("十二个班", 2, 6);
		addPropertyDict("十五个班以上", 2, 7);
		addPropertyDict("十二套", 3, 8);
		addPropertyDict("十八套", 3, 9);
		addPropertyDict("二十四套", 3, 10);
		addPropertyDict("三十六套以上", 3, 11);
		addPropertyDict("150人", 4, 12);
		addPropertyDict("300人", 4, 13);
		addPropertyDict("600人", 4, 14);
		addPropertyDict("800人以上", 4, 15);
	}

	private void initEduModeType() {
		propertyDomain = addPropertyDomain(PropertyTypes.EDUCATION_MODE_TYPE,
				false, null);
		addPropertyDict("助学贷款", 1, 1);
		addPropertyDict("社会捐赠", 2, 2);
		addPropertyDict("社会捐助", 3, 3);
		addPropertyDict("银行信贷", 4, 4);
	}

	private void initEduItemType() {
		propertyDomain = addPropertyDomain(PropertyTypes.EDUCATION_ITEM_TYPE,
				false, null);
		addPropertyDict("免学杂费", 1, 1);
		addPropertyDict("免书本费", 0, 2);
		addPropertyDict("生活困难补助", 1, 3);
	}

	private void initEduRoadType() {
		propertyDomain = addPropertyDomain(PropertyTypes.EDUCATION_ROAD_TYPE,
				false, null);
		addPropertyDict("路程", 1, 1);
		addPropertyDict("路况", 2, 2);
	}

	private void initEduDistanceType() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.EDUCATION_DISTANCE_TYPE, false, null);
		addPropertyDict("三公里", 1, 1);
		addPropertyDict("四公里", 2, 2);
		addPropertyDict("五公里", 3, 3);
		addPropertyDict("六公里", 4, 4);
		addPropertyDict("七公里及以上", 5, 5);
	}

	private void initEduRoadConditionType() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.EDUCATION_ROAD_CONDITION_TYPE, false, null);
		addPropertyDict("山路", 1, 1);
		addPropertyDict("渡河", 2, 2);
		addPropertyDict("过水库", 3, 3);
		addPropertyDict("乘车", 4, 4);
	}

	private void initEduDegreeType() {
		propertyDomain = addPropertyDomain(PropertyTypes.EDUCATION_DEGREE_TYPE,
				false, null);
		addPropertyDict("学前教育", 0, 1);
		addPropertyDict("小学", 2, 2);
		addPropertyDict("初中", 2, 3);
		addPropertyDict("高中", 1, 4);
		addPropertyDict("职中", 1, 5);
	}

	private void initEduAddressType() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.EDUCATION_ADDRESS_TYPE, false, null);
		addPropertyDict("村", 1, 1);
		addPropertyDict("社区", 1, 2);
		addPropertyDict("场镇", 1, 3);
		addPropertyDict("城市", 1, 4);
		addPropertyDict("村小", 2, 5);
		addPropertyDict("分校", 2, 6);
		addPropertyDict("小学", 2, 7);
		addPropertyDict("中学", 2, 8);
	}
	
	public void initSteadyRecordWorkWarnLevel() {
		propertyDomain = addPropertyDomain(
				PropertyTypes.STEADY_RECORD_WORK_WARN_LEVEL, false,
				null);
		addPropertyDict("蓝色", 0, 1);
		addPropertyDict("黄色", 1, 2);
		addPropertyDict("橙色", 2, 3);
		addPropertyDict("红色", 3, 4);
	}
	/**
	 * 会议类型
	 */
	public void initAssignMeetingType() {
		propertyDomain = addPropertyDomain(PropertyTypes.ASSGIN_MEETING_TYPE,
				false, null);
		addPropertyDict("县委常委会", 0, 1);
		addPropertyDict("县政府常务会", 0, 2);
		addPropertyDict("专题会议", 0, 3);
		addPropertyDict("其他会议", 0, 4);
	}
	
	public void initFormType(){
		propertyDomain = addPropertyDomain(PropertyTypes.FORM_TYPE,
				false, null);
		addPropertyDict("呈报件", 0, 1);
		addPropertyDict("交办件", 0, 2);
		addPropertyDict("转办件", 0, 3);
		addPropertyDict("来电来信来访件", 0, 4);
		addPropertyDict("干部走基层信息搜集", 0, 5);
		addPropertyDict("其他", 0, 6);
	}
	
	public void initLedgerType(){
		propertyDomain = addPropertyDomain(PropertyTypes.LEDGER_TYPE, false, null);
		addPropertyDict("水利信息", 0, 1);
		addPropertyDict("交通信息", 0, 2);
		addPropertyDict("能源信息", 0, 3);
		addPropertyDict("教育信息", 0, 4);
		addPropertyDict("科技文本信息", 0, 5);
		addPropertyDict("医疗卫生信息", 0, 6);
		addPropertyDict("劳动和社会保障信息", 0, 7);
		addPropertyDict("环境保护信息", 0, 8);
		addPropertyDict("城乡规划建设管理信息", 0, 9);
		addPropertyDict("农业信息", 0, 10);
		addPropertyDict("其它信息", 0, 11);
	}
	
	public void initLedgerStatus(){
		propertyDomain = addPropertyDomain(PropertyTypes.LEDGER_STATUS, false, null);
		addPropertyDict("待办", 0, 1);
		addPropertyDict("程序性办结", 0, 2);
		addPropertyDict("阶段性办结", 0, 3);
		addPropertyDict("实质性办结", 0, 4);
		addPropertyDict("已办", 0, 5);
		addPropertyDict("待反馈", 0, 6);
		addPropertyDict("上级交办", 0, 7);
		addPropertyDict("上报", 0, 8);
		addPropertyDict("协办", 0, 9);
		addPropertyDict("抄告", 0, 10);
	}
}