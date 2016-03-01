package com.tianque.plugin.account.service.impl;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.account.api.ReplyFormDubboService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.dao.PeopleAspirationDao;
import com.tianque.plugin.account.domain.Agriculture;
import com.tianque.plugin.account.domain.Education;
import com.tianque.plugin.account.domain.Energy;
import com.tianque.plugin.account.domain.Environment;
import com.tianque.plugin.account.domain.Labor;
import com.tianque.plugin.account.domain.LedgerConvert;
import com.tianque.plugin.account.domain.LedgerFeedBack;
import com.tianque.plugin.account.domain.LedgerPeopleAspirations;
import com.tianque.plugin.account.domain.Medical;
import com.tianque.plugin.account.domain.Other;
import com.tianque.plugin.account.domain.ReplyForm;
import com.tianque.plugin.account.domain.Science;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.domain.Town;
import com.tianque.plugin.account.domain.Traffic;
import com.tianque.plugin.account.domain.WaterResources;
import com.tianque.plugin.account.service.LedgerConvertService;
import com.tianque.plugin.account.service.LedgerFeedBackService;
import com.tianque.plugin.account.service.PeopleAspirationService;
import com.tianque.plugin.account.service.ThreeRecordsIssueProcessService;
import com.tianque.plugin.account.service.ThreeRecordsIssueService;
import com.tianque.plugin.account.service.ThreeRecordsIssueWorkFlowEngine;
import com.tianque.plugin.account.service.ThreeRecordsKeyGeneratorService;
import com.tianque.plugin.account.util.CollectionUtil;
import com.tianque.plugin.account.vo.LedgerAttachFileReturnVo;
import com.tianque.userAuth.api.OrganizationDubboRemoteService;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Transactional
@Service("threeRecordPeopleAspirationService")
public class PeopleAspirationServiceImpl implements PeopleAspirationService {

	@Autowired
	private ThreeRecordsIssueWorkFlowEngine threeRecordsIssueWorkFlowEngine;

	@Autowired
	private PeopleAspirationDao peopleAspirationDao;
	@Autowired
	private ThreeRecordsIssueService threeRecordsIssueService;

	@Autowired
	private ThreeRecordsKeyGeneratorService threeRecordsKeyGeneratorService;

	@Autowired
	private LedgerFeedBackService ledgerFeedBackService;
	@Autowired
	private OrganizationDubboRemoteService organizationDubboService;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	@Autowired
	private LedgerConvertService ledgerConvertService;
	@Autowired
	private ReplyFormDubboService replyFormDubboService;
	@Autowired
	private ThreeRecordsIssueProcessService threeRecordsIssueProcessService;

	@Autowired
	@Qualifier("ledgerPeopleAspirationValidatorImpl")
	private DomainValidator<LedgerPeopleAspirations> ledgerPeopleAspirationsValidator;

	public LedgerPeopleAspirations getPeopleAspiration(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		return peopleAspirationDao.getPeopleAspirations(id);
	}

	public LedgerPeopleAspirations addPeopleAspirations(
			LedgerPeopleAspirations peopleAspirations) {
		ValidateResult result = ledgerPeopleAspirationsValidator
				.validate(peopleAspirations);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			setOrganization(peopleAspirations);
			peopleAspirations = peopleAspirationDao
					.addPeopleAspirations(peopleAspirations);
			threeRecordsIssueWorkFlowEngine.register(peopleAspirations);

		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的addPeopleAspirations方法出现异常，原因：",
					"新增民生台账表信息出现错误", e);
		}
		return peopleAspirations;
	}

	@Override
	public LedgerPeopleAspirations updatePeopleAspirations(
			LedgerPeopleAspirations peopleAspirations,
			List<ThreeRecordsIssueAttachFile> files) {
		ValidateResult result = ledgerPeopleAspirationsValidator
				.validate(peopleAspirations);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		setOrganization(peopleAspirations);
		LedgerAttachFileReturnVo attachFileReturnUtil;
		try {
			attachFileReturnUtil = updateAttachFiles(peopleAspirations, files);
			peopleAspirations = peopleAspirationDao
					.updatePeopleAspirations(peopleAspirations);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的updatePeopleAspirations方法出现异常，原因：",
					"更新民生台账表信息出现错误", e);
		}
		peopleAspirations.setLedgerAttachFileReturnUtil(attachFileReturnUtil);
		return peopleAspirations;
	}

	@Override
	public void deletePeopleAspirationById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			peopleAspirationDao.deletePeopleAspirationById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deletePeopleAspirationById方法出现异常，原因：",
					"删除民生台账表信息出现错误", e);
		}

	}

	@Override
	public LedgerPeopleAspirations getFullById(Long id) {

		return peopleAspirationDao.getFullById(id);
	}

	@Override
	public WaterResources addWaterResources(WaterResources waterResources) {
		if (waterResources == null
				|| waterResources.getPeopleAspiration() == null) {
			throw new BusinessValidationException("参数不对！");
		}
		try {
			waterResources = peopleAspirationDao
					.addWaterResources(waterResources);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的addWaterResources方法出现异常，原因：",
					"添加水利表信息出现错误", e);
		}
		return waterResources;
	}

	@Override
	public void deleteWaterResourcesByAspirationId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			peopleAspirationDao.deleteWaterResourcesByAspirationId(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteWaterResourcesByAspirationId方法出现异常，原因：",
					"刪除水利表信息出现错误", e);
		}
	}

	@Override
	public WaterResources updateWaterResources(WaterResources waterResources) {
		try {
			return peopleAspirationDao.updateWaterResources(waterResources);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的updateWaterResources方法出现异常，原因：",
					"更新水利表信息出现错误", e);
		}
	}

	@Override
	public WaterResources getWaterResourcesByAspirationId(Long id) {
		WaterResources waterResource = peopleAspirationDao.getWaterResourcesByAspirationId(id);
		Field[] fields = WaterResources.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				if (waterResource != null
						&& PropertyDict.class.equals(field.getType())) {
					field.setAccessible(true);
					PropertyDict propertyDict = (PropertyDict) field.get(waterResource);
					if (propertyDict != null && propertyDict.getId() != null
							&& propertyDict.getDisplayName() == null) {
						propertyDict = propertyDictDubboService
								.getPropertyDictById(propertyDict.getId());
						field.set(waterResource,propertyDict);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的getWaterResourcesByAspirationId方法出现异常，原因：",
					"查询水利类信息出现错误", e);
		}
		return waterResource;
	}

	@Override
	public WaterResources getWaterResourcesById(Long id) {
		return peopleAspirationDao.getWaterResourcesById(id);
	}

	@Override
	public Traffic addTraffic(Traffic traffic) {
		try {
			return peopleAspirationDao.addTraffic(traffic);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的addTraffic方法出现异常，原因：",
					"新增交通表信息出现错误", e);
		}
	}

	@Override
	public void deleteTrafficByAspirationId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			peopleAspirationDao.deleteTrafficByAspirationId(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteTrafficByAspirationId方法出现异常，原因：",
					"删除交通表信息出现错误", e);
		}

	}

	@Override
	public Traffic getTrafficByAspirationId(Long id) {
		Traffic traffic = peopleAspirationDao.getTrafficByAspirationId(id);
		Field[] fields = Traffic.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				if (traffic != null
						&& PropertyDict.class.equals(field.getType())) {
					field.setAccessible(true);
					PropertyDict propertyDict = (PropertyDict) field.get(traffic);
					if (propertyDict != null && propertyDict.getId() != null
							&& propertyDict.getDisplayName() == null) {
						propertyDict = propertyDictDubboService
								.getPropertyDictById(propertyDict.getId());
						field.set(traffic,propertyDict);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的getTrafficByAspirationId方法出现异常，原因：",
					"查询交通表信息出现错误", e);
		}
		return traffic;
	}

	@Override
	public Traffic getTrafficById(Long id) {
		return peopleAspirationDao.getTrafficById(id);
	}

	@Override
	public Traffic updateTraffic(Traffic traffic) {
		try {
			return peopleAspirationDao.updateTraffic(traffic);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的updateTraffic方法出现异常，原因：",
					"更新交通表信息出现错误", e);
		}
	}

	@Override
	public Agriculture addAgriculture(Agriculture agriculture) {
		try {
			return peopleAspirationDao.addAgriculture(agriculture);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的addAgriculture方法出现异常，原因：",
					"新增农业表信息出现错误", e);
		}
	}

	@Override
	public Education addEducation(Education education) {
		try {
			return peopleAspirationDao.addEducation(education);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的addEducation方法出现异常，原因：",
					"新增教育表信息出现错误", e);
		}
	}

	@Override
	public Energy addEnergy(Energy energy) {
		try {
			return peopleAspirationDao.addEnergy(energy);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的addEnergy方法出现异常，原因：",
					"新增能源表信息出现错误", e);
		}
	}

	@Override
	public Environment addEnvironment(Environment environment) {
		try {
			return peopleAspirationDao.addEnvironment(environment);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的addEnvironment方法出现异常，原因：",
					"新增环境表信息出现错误", e);
		}
	}

	@Override
	public Labor addLabor(Labor labor) {
		try {
			return peopleAspirationDao.addLabor(labor);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的addLabor方法出现异常，原因：",
					"新增劳动表信息出现错误", e);
		}
	}

	@Override
	public Medical addMedical(Medical medical) {
		try {
			return peopleAspirationDao.addMedical(medical);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的addMedical方法出现异常，原因：",
					"新增医疗表信息出现错误", e);
		}
	}

	@Override
	public Other addOther(Other other) {
		try {
			return peopleAspirationDao.addOther(other);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的addOther方法出现异常，原因：",
					"新增其实表信息出现错误", e);
		}
	}

	@Override
	public Science addScience(Science science) {
		try {
			return peopleAspirationDao.addScience(science);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的addScience方法出现异常，原因：",
					"新增科技文化表信息出现错误", e);
		}
	}

	@Override
	public Town addTown(Town town) {
		try {
			return peopleAspirationDao.addTown(town);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的addTown方法出现异常，原因：",
					"新增城乡规划表信息出现错误", e);
		}
	}

	@Override
	public void deleteAgricultureByAspirationId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			peopleAspirationDao.deleteAgricultureByAspirationId(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteAgricultureByAspirationId方法出现异常，原因：",
					"删除农业表信息出现错误", e);
		}
	}

	@Override
	public void deleteEducationByAspirationId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			peopleAspirationDao.deleteEducationByAspirationId(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteEducationByAspirationId方法出现异常，原因：",
					"删除教育表信息出现错误", e);
		}
	}

	@Override
	public void deleteEnergyByAspirationId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			peopleAspirationDao.deleteEnergyByAspirationId(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteEnergyByAspirationId方法出现异常，原因：",
					"删除能源表信息出现错误", e);
		}

	}

	@Override
	public void deleteEnvironmentByAspirationId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			peopleAspirationDao.deleteEnvironmentByAspirationId(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteEnvironmentByAspirationId方法出现异常，原因：",
					"删除环境表信息出现错误", e);
		}

	}

	@Override
	public void deleteLaborByAspirationId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			peopleAspirationDao.deleteLaborByAspirationId(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteLaborByAspirationId方法出现异常，原因：",
					"删除劳动表信息出现错误", e);
		}

	}

	@Override
	public void deleteMedicalByAspirationId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			peopleAspirationDao.deleteMedicalByAspirationId(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteMedicalByAspirationId方法出现异常，原因：",
					"删除医疗表信息出现错误", e);
		}

	}

	@Override
	public void deleteOtherByAspirationId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			peopleAspirationDao.deleteOtherByAspirationId(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteOtherByAspirationId方法出现异常，原因：",
					"删除其它表信息出现错误", e);
		}

	}

	@Override
	public void deleteScienceByAspirationId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			peopleAspirationDao.deleteScienceByAspirationId(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteScienceByAspirationId方法出现异常，原因：",
					"删除科技文化表信息出现错误", e);
		}

	}

	@Override
	public void deleteTownByAspirationId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			peopleAspirationDao.deleteTownByAspirationId(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteTownByAspirationId方法出现异常，原因：",
					"删除城乡规划表信息出现错误", e);
		}

	}

	@Override
	public Agriculture getAgricultureByAspirationId(Long id) {
		Agriculture agriculture = peopleAspirationDao.getAgricultureByAspirationId(id);
		Field[] fields = Agriculture.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				if (agriculture != null
						&& PropertyDict.class.equals(field.getType())) {
					field.setAccessible(true);
					PropertyDict propertyDict = (PropertyDict) field.get(agriculture);
					if (propertyDict != null && propertyDict.getId() != null
							&& propertyDict.getDisplayName() == null) {
						propertyDict = propertyDictDubboService
								.getPropertyDictById(propertyDict.getId());
						field.set(agriculture,propertyDict);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的getAgricultureByAspirationId方法出现异常，原因：",
					"查询农业类信息出现错误", e);
		}
		return agriculture;
	}

	@Override
	public Agriculture getAgricultureById(Long id) {
		return peopleAspirationDao.getAgricultureById(id);
	}

	@Override
	public Education getEducationByAspirationId(Long id) {
		Education education = peopleAspirationDao.getEducationByAspirationId(id);
		Field[] fields = Education.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				if (education != null
						&& PropertyDict.class.equals(field.getType())) {
					field.setAccessible(true);
					PropertyDict propertyDict = (PropertyDict) field.get(education);
					if (propertyDict != null && propertyDict.getId() != null
							&& propertyDict.getDisplayName() == null) {
						propertyDict = propertyDictDubboService
								.getPropertyDictById(propertyDict.getId());
						field.set(education,propertyDict);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的getEducationByAspirationId方法出现异常，原因：",
					"查询教育类信息出现错误", e);
		}
		return education;
	}

	@Override
	public Education getEducationById(Long id) {
		return peopleAspirationDao.getEducationById(id);
	}

	@Override
	public Energy getEnergyByAspirationId(Long id) {
		Energy energy = peopleAspirationDao.getEnergyByAspirationId(id);
		Field[] fields = Energy.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				if (energy != null
						&& PropertyDict.class.equals(field.getType())) {
					field.setAccessible(true);
					PropertyDict propertyDict = (PropertyDict) field.get(energy);
					if (propertyDict != null && propertyDict.getId() != null
							&& propertyDict.getDisplayName() == null) {
						propertyDict = propertyDictDubboService
								.getPropertyDictById(propertyDict.getId());
						field.set(energy,propertyDict);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的getEnergyByAspirationId方法出现异常，原因：",
					"查询能源类信息出现错误", e);
		}
		return energy;
	}

	@Override
	public Energy getEnergyById(Long id) {
		return peopleAspirationDao.getEnergyById(id);
	}

	@Override
	public Environment getEnvironmentByAspirationId(Long id) {
		Environment environment = peopleAspirationDao.getEnvironmentByAspirationId(id);
		Field[] fields = Environment.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				if (environment != null
						&& PropertyDict.class.equals(field.getType())) {
					field.setAccessible(true);
					PropertyDict propertyDict = (PropertyDict) field.get(environment);
					if (propertyDict != null && propertyDict.getId() != null
							&& propertyDict.getDisplayName() == null) {
						propertyDict = propertyDictDubboService
								.getPropertyDictById(propertyDict.getId());
						field.set(environment,propertyDict);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的getEnvironmentByAspirationId方法出现异常，原因：",
					"查询环境类信息出现错误", e);
		}
		return environment;
	}

	@Override
	public Environment getEnvironmentById(Long id) {
		return peopleAspirationDao.getEnvironmentById(id);
	}

	@Override
	public Labor getLaborByAspirationId(Long id) {
		Labor labor = peopleAspirationDao.getLaborByAspirationId(id);
		Field[] fields = Labor.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				if (labor != null
						&& PropertyDict.class.equals(field.getType())) {
					field.setAccessible(true);
					PropertyDict propertyDict = (PropertyDict) field.get(labor);
					if (propertyDict != null && propertyDict.getId() != null
							&& propertyDict.getDisplayName() == null) {
						propertyDict = propertyDictDubboService
								.getPropertyDictById(propertyDict.getId());
						field.set(labor,propertyDict);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的getLaborByAspirationId方法出现异常，原因：",
					"查询劳动和社会保障类信息出现错误", e);
		}
		return labor;
	}

	@Override
	public Labor getLaborById(Long id) {
		return peopleAspirationDao.getLaborById(id);
	}

	@Override
	public Medical getMedicalByAspirationId(Long id) {
		Medical medical = peopleAspirationDao.getMedicalByAspirationId(id);
		Field[] fields = Medical.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				if (medical != null
						&& PropertyDict.class.equals(field.getType())) {
					field.setAccessible(true);
					PropertyDict propertyDict = (PropertyDict) field.get(medical);
					if (propertyDict != null && propertyDict.getId() != null
							&& propertyDict.getDisplayName() == null) {
						propertyDict = propertyDictDubboService
								.getPropertyDictById(propertyDict.getId());
						field.set(medical,propertyDict);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的getMedicalByAspirationId方法出现异常，原因：",
					"查询医疗卫生类信息出现错误", e);
		}
		return medical;
	}

	@Override
	public Medical getMedicalById(Long id) {
		return peopleAspirationDao.getMedicalById(id);
	}

	@Override
	public Other getOtherByAspirationId(Long id) {
		Other other = peopleAspirationDao.getOtherByAspirationId(id);
		Field[] fields = Other.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				if (other != null
						&& PropertyDict.class.equals(field.getType())) {
					field.setAccessible(true);
					PropertyDict propertyDict = (PropertyDict) field.get(other);
					if (propertyDict != null && propertyDict.getId() != null
							&& propertyDict.getDisplayName() == null) {
						propertyDict = propertyDictDubboService
								.getPropertyDictById(propertyDict.getId());
						field.set(other,propertyDict);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的getOtherByAspirationId方法出现异常，原因：",
					"查询其他类信息出现错误", e);
		}
		return other;
	}

	@Override
	public Other getOtherById(Long id) {
		return peopleAspirationDao.getOtherById(id);
	}

	@Override
	public Science getScienceByAspirationId(Long id) {
		Science science = peopleAspirationDao.getScienceByAspirationId(id);
		Field[] fields = Science.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				if (science != null
						&& PropertyDict.class.equals(field.getType())) {
					field.setAccessible(true);
					PropertyDict propertyDict = (PropertyDict) field.get(science);
					if (propertyDict != null && propertyDict.getId() != null
							&& propertyDict.getDisplayName() == null) {
						propertyDict = propertyDictDubboService
								.getPropertyDictById(propertyDict.getId());
						field.set(science,propertyDict);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的getScienceByAspirationId方法出现异常，原因：",
					"查询科技文体类信息出现错误", e);
		}
		return science;
	}

	@Override
	public Science getScienceById(Long id) {
		return peopleAspirationDao.getScienceById(id);
	}

	@Override
	public Town getTownByAspirationId(Long id) {
		Town town = peopleAspirationDao.getTownByAspirationId(id);
		Field[] fields = Town.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				if (town != null
						&& PropertyDict.class.equals(field.getType())) {
					field.setAccessible(true);
					PropertyDict propertyDict = (PropertyDict) field.get(town);
					if (propertyDict != null && propertyDict.getId() != null
							&& propertyDict.getDisplayName() == null) {
						propertyDict = propertyDictDubboService
								.getPropertyDictById(propertyDict.getId());
						field.set(town,propertyDict);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的getTownByAspirationId方法出现异常，原因：",
					"查询城乡规划建设类信息出现错误", e);
		}
		return town;
	}

	@Override
	public Town getTownById(Long id) {
		return peopleAspirationDao.getTownById(id);
	}

	@Override
	public Agriculture updateAgriculture(Agriculture agriculture) {
		if (agriculture == null || agriculture.getId() == null) {
			throw new BusinessValidationException("参数错误！农业信息编号不能为空");
		}
		try {
			return peopleAspirationDao.updateAgriculture(agriculture);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的updateAgriculture方法出现异常，原因：",
					"更新农业表信息出现错误", e);
		}
	}

	@Override
	public Education updateEducation(Education education) {
		if (education == null || education.getId() == null) {
			throw new BusinessValidationException("参数错误！教育信息编号不能为空");
		}
		try {
			return peopleAspirationDao.updateEducation(education);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的updateEducation方法出现异常，原因：",
					"更新教育表信息出现错误", e);
		}
	}

	@Override
	public Energy updateEnergy(Energy energy) {
		if (energy == null || energy.getId() == null) {
			throw new BusinessValidationException("参数错误！能源信息编号不能为空");
		}
		try {
			return peopleAspirationDao.updateEnergy(energy);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的updateEnergy方法出现异常，原因：",
					"更新能源表信息出现错误", e);
		}
	}

	@Override
	public Environment updateEnvironment(Environment environment) {
		if (environment == null || environment.getId() == null) {
			throw new BusinessValidationException("参数错误！环境信息编号不能为空");
		}
		try {
			return peopleAspirationDao.updateEnvironment(environment);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的updateEnvironment方法出现异常，原因：",
					"更新环境表信息出现错误", e);
		}
	}

	@Override
	public Labor updateLabor(Labor labor) {
		if (labor == null || labor.getId() == null) {
			throw new BusinessValidationException("参数错误！劳动信息编号不能为空");
		}
		try {
			return peopleAspirationDao.updateLabor(labor);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的updateLabor方法出现异常，原因：",
					"更新劳动表信息出现错误", e);
		}
	}

	@Override
	public Medical updateMedical(Medical medical) {
		if (medical == null || medical.getId() == null) {
			throw new BusinessValidationException("参数错误！医疗信息编号不能为空");
		}
		try {
			return peopleAspirationDao.updateMedical(medical);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的updateMedical方法出现异常，原因：",
					"更新医疗表信息出现错误", e);
		}
	}

	@Override
	public Other updateOther(Other other) {
		if (other == null || other.getId() == null) {
			throw new BusinessValidationException("参数错误！其它类信息编号不能为空");
		}
		try {
			return peopleAspirationDao.updateOther(other);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的updateOther方法出现异常，原因：",
					"更新其它表信息出现错误", e);
		}
	}

	@Override
	public Science updateScience(Science science) {
		if (science == null || science.getId() == null) {
			throw new BusinessValidationException("参数错误！科技文化编号不能为空");
		}

		try {
			return peopleAspirationDao.updateScience(science);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的updateScience方法出现异常，原因：",
					"更新科技文化表信息出现错误", e);
		}
	}

	@Override
	public Town updateTown(Town town) {
		try {
			return peopleAspirationDao.updateTown(town);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的updateTown方法出现异常，原因：",
					"更新城乡规划表信息出现错误", e);
		}
	}

	public void deleteWaterLedgerAndStepById(Long ledgerId) {
		if (ledgerId == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			threeRecordsIssueService.deleteIssueByIssueId(ledgerId,
					LedgerConstants.PEOPLEASPIRATION_WATER);
			peopleAspirationDao.deletePeopleAspirationById(ledgerId);
			peopleAspirationDao.deleteWaterResourcesByAspirationId(ledgerId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteWaterLedgerAndStepById方法出现异常，原因：",
					"删除水利信息出现错误", e);
		}
	}

	@Override
	public void deleteAgricultureLedgerAndStepById(Long ledgerId) {
		if (ledgerId == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			threeRecordsIssueService.deleteIssueByIssueId(ledgerId,
					LedgerConstants.PEOPLEASPIRATION_AGRICULTURE);
			peopleAspirationDao.deletePeopleAspirationById(ledgerId);
			peopleAspirationDao.deleteAgricultureByAspirationId(ledgerId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteAgricultureLedgerAndStepById方法出现异常，原因：",
					"删除农业信息出现错误", e);
		}

	}

	@Override
	public void deleteEducationLedgerAndStepById(Long ledgerId) {
		if (ledgerId == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			threeRecordsIssueService.deleteIssueByIssueId(ledgerId,
					LedgerConstants.PEOPLEASPIRATION_EDUCATION);
			peopleAspirationDao.deletePeopleAspirationById(ledgerId);
			peopleAspirationDao.deleteEducationByAspirationId(ledgerId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteEducationLedgerAndStepById方法出现异常，原因：",
					"删除教育信息出现错误", e);
		}

	}

	@Override
	public void deleteEnergyLedgerAndStepById(Long ledgerId) {
		if (ledgerId == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			threeRecordsIssueService.deleteIssueByIssueId(ledgerId,
					LedgerConstants.PEOPLEASPIRATION_ENERGY);
			peopleAspirationDao.deletePeopleAspirationById(ledgerId);
			peopleAspirationDao.deleteEnergyByAspirationId(ledgerId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteEnergyLedgerAndStepById方法出现异常，原因：",
					"删除能源信息出现错误", e);
		}
	}

	@Override
	public void deleteEnvironmentLedgerAndStepById(Long ledgerId) {
		if (ledgerId == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			threeRecordsIssueService.deleteIssueByIssueId(ledgerId,
					LedgerConstants.PEOPLEASPIRATION_ENVIRONMENT);
			peopleAspirationDao.deletePeopleAspirationById(ledgerId);
			peopleAspirationDao.deleteEnvironmentByAspirationId(ledgerId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteEnvironmentLedgerAndStepById方法出现异常，原因：",
					"删除环境信息出现错误", e);
		}

	}

	@Override
	public void deleteLaborLedgerAndStepById(Long ledgerId) {
		if (ledgerId == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			threeRecordsIssueService.deleteIssueByIssueId(ledgerId,
					LedgerConstants.PEOPLEASPIRATION_LABOR);
			peopleAspirationDao.deletePeopleAspirationById(ledgerId);
			peopleAspirationDao.deleteLaborByAspirationId(ledgerId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteLaborLedgerAndStepById方法出现异常，原因：",
					"删除劳动信息出现错误", e);
		}
	}

	@Override
	public void deleteMedicalLedgerAndStepById(Long ledgerId) {
		if (ledgerId == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			threeRecordsIssueService.deleteIssueByIssueId(ledgerId,
					LedgerConstants.PEOPLEASPIRATION_MEDICAL);
			peopleAspirationDao.deletePeopleAspirationById(ledgerId);
			peopleAspirationDao.deleteMedicalByAspirationId(ledgerId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteMedicalLedgerAndStepById方法出现异常，原因：",
					"删除医疗信息出现错误", e);
		}
	}

	@Override
	public void deleteOtherLedgerAndStepById(Long ledgerId) {
		if (ledgerId == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			threeRecordsIssueService.deleteIssueByIssueId(ledgerId,
					LedgerConstants.PEOPLEASPIRATION_OTHER);
			peopleAspirationDao.deletePeopleAspirationById(ledgerId);
			peopleAspirationDao.deleteOtherByAspirationId(ledgerId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteOtherLedgerAndStepById方法出现异常，原因：",
					"删除其它表信息出现错误", e);
		}
	}

	@Override
	public void deleteScienceLedgerAndStepById(Long ledgerId) {
		if (ledgerId == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			threeRecordsIssueService.deleteIssueByIssueId(ledgerId,
					LedgerConstants.PEOPLEASPIRATION_SCIENCE);
			peopleAspirationDao.deletePeopleAspirationById(ledgerId);
			peopleAspirationDao.deleteScienceByAspirationId(ledgerId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteScienceLedgerAndStepById方法出现异常，原因：",
					"删除科技文化信息出现错误", e);
		}
	}

	@Override
	public void deleteTownLedgerAndStepById(Long ledgerId) {
		if (ledgerId == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			threeRecordsIssueService.deleteIssueByIssueId(ledgerId,
					LedgerConstants.PEOPLEASPIRATION_TOWN);
			peopleAspirationDao.deletePeopleAspirationById(ledgerId);
			peopleAspirationDao.deleteTownByAspirationId(ledgerId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteTownLedgerAndStepById方法出现异常，原因：",
					"删除城乡规划信息出现错误", e);
		}
	}

	@Override
	public void deleteTrafficLedgerAndStepById(Long ledgerId) {
		if (ledgerId == null) {
			throw new BusinessValidationException("参数错误！台账编号不能为空");
		}
		try {
			threeRecordsIssueService.deleteIssueByIssueId(ledgerId,
					LedgerConstants.PEOPLEASPIRATION_TRAFFIC);
			peopleAspirationDao.deletePeopleAspirationById(ledgerId);
			peopleAspirationDao.deleteTrafficByAspirationId(ledgerId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的deleteTrafficLedgerAndStepById方法出现异常，原因：",
					"删除交通信息出现错误", e);
		}

	}

	@Override
	public LedgerPeopleAspirations addPeopleAspirations(
			LedgerPeopleAspirations peopleAspirations,
			List<ThreeRecordsIssueAttachFile> files) {
		ValidateResult result = ledgerPeopleAspirationsValidator
				.validate(peopleAspirations);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}

		try {
			setOrganization(peopleAspirations);
			Long convertId = peopleAspirations.getConvertId();
			peopleAspirations = peopleAspirationDao
					.addPeopleAspirations(peopleAspirations);
			peopleAspirations.setConvertId(convertId);
			fillIssueAttachFilesIssue(peopleAspirations, files);
			LedgerAttachFileReturnVo attachFileReturnUtil = null;
			if (files != null) {
				attachFileReturnUtil = threeRecordsIssueService
						.addIssueAttachFiles(files);
			}
			updateLedgerConvertStatus(peopleAspirations);
			threeRecordsIssueWorkFlowEngine.register(peopleAspirations);
			if (attachFileReturnUtil != null)
				peopleAspirations
						.setLedgerAttachFileReturnUtil(attachFileReturnUtil);
			return peopleAspirations;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleaspirationsServiceImpl的addPeopleAspirations方法出现异常，原因：",
					"新增台账信息出现错误", e);
		}

	}

	private void fillIssueAttachFilesIssue(
			LedgerPeopleAspirations peopleAspirations,
			List<ThreeRecordsIssueAttachFile> files) {
		if (files != null && !files.isEmpty()) {
			for (ThreeRecordsIssueAttachFile issueAttachFile : files) {
				issueAttachFile.setLedgerId(peopleAspirations.getId());
				issueAttachFile
						.setLedgerType(peopleAspirations.getLedgerType());
			}
		}
	}

	private LedgerAttachFileReturnVo updateAttachFiles(
			LedgerPeopleAspirations peopleAspirations,
			List<ThreeRecordsIssueAttachFile> postFiles) throws Exception {
		LedgerAttachFileReturnVo attachFileReturnUtil = new LedgerAttachFileReturnVo();
		List<Long> attachFileId = new ArrayList<Long>();
		List<String> attachFileName = new ArrayList<String>();
		List<ThreeRecordsIssueAttachFile> existAttachFiles = peopleAspirationDao
				.loadLedgerAttachFilesByLedgerIdAndType(
						peopleAspirations.getId(),
						peopleAspirations.getLedgerType());
		// 修改事件的时候没有上传附件
		if (postFiles == null || postFiles.isEmpty()) {
			// 该事件本来就没有附件
			if (existAttachFiles == null || existAttachFiles.isEmpty()) {
				return attachFileReturnUtil;
			} else {
				// 如果有附件则删除附件
				for (ThreeRecordsIssueAttachFile issueAttachFile : existAttachFiles) {
					peopleAspirationDao
							.deleteAttachFileByAttachfilesId(issueAttachFile
									.getId());
					deleteFile(issueAttachFile.getFileActualUrl());
				}
			}
		} else {
			if (existAttachFiles == null || existAttachFiles.isEmpty()) {
				fillIssueAttachFilesIssue(peopleAspirations, postFiles);
				attachFileReturnUtil = threeRecordsIssueService
						.addIssueAttachFiles(postFiles);
			} else {
				List<ThreeRecordsIssueAttachFile> addFile = new ArrayList<ThreeRecordsIssueAttachFile>();
				List<ThreeRecordsIssueAttachFile> saveFile = new ArrayList<ThreeRecordsIssueAttachFile>();
				for (ThreeRecordsIssueAttachFile file : postFiles) {
					if (file.getId() == null) {
						addFile.add(file);
					} else {
						saveFile.add(file);
						attachFileId.add(file.getId());
						attachFileName.add(file.getFileName());
					}
				}
				fillIssueAttachFilesIssue(peopleAspirations, addFile);
				attachFileReturnUtil = threeRecordsIssueService
						.addIssueAttachFiles(addFile);
				combine(attachFileReturnUtil, attachFileId, attachFileName);
				existAttachFiles.removeAll(saveFile);
				for (ThreeRecordsIssueAttachFile file : existAttachFiles) {
					peopleAspirationDao.deleteAttachFileByAttachfilesId(file
							.getId());
					deleteFile(file.getFileActualUrl());
				}
			}
		}
		return attachFileReturnUtil;
	}

	private LedgerAttachFileReturnVo combine(
			LedgerAttachFileReturnVo attachFileReturnUtil,
			List<Long> attachFileId, List<String> attachFileName) {
		if (attachFileReturnUtil == null) {
			attachFileReturnUtil = new LedgerAttachFileReturnVo();
			attachFileReturnUtil.setAttachFileId(attachFileId);
			attachFileReturnUtil.setAttachFileName(attachFileName);
		} else {
			attachFileReturnUtil.getAttachFileId().addAll(attachFileId);
			attachFileReturnUtil.getAttachFileName().addAll(attachFileName);
		}

		return attachFileReturnUtil;
	}

	/** 物理删除文件 */
	private void deleteFile(String filePath) throws Exception {
		filePath = FileUtil.getWebRoot() + File.separator + filePath;
		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}

	@Override
	public LedgerPeopleAspirations createTemporaryPeopleAspiration(
			int ledgerType, Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构编号不能为空");
		}
		LedgerPeopleAspirations peopleAspirations;
		try {
			peopleAspirations = new LedgerPeopleAspirations();
			String serialNumber = threeRecordsKeyGeneratorService.getNextKey(
					LedgerConstants.peopleAsparitionPreKeyMap.get(ledgerType),
					orgId);
			peopleAspirations.setSerialNumber(serialNumber);
			peopleAspirations.setOccurDate(CalendarUtil.now());
			peopleAspirations.setRegistrationTime(CalendarUtil.now());
			peopleAspirations.setLedgerType(ledgerType);
			peopleAspirations.setOrganization(organizationDubboService
					.getSimpleOrgById(orgId));
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleaspirationsServiceImpl的createTemporaryPeopleAspiration方法出现异常，原因：",
					"创建临时台账信息出现错误", e);
		}
		return peopleAspirations;
	}

	@Override
	public LedgerPeopleAspirations findPeopleAspirationAndFileOrLogAndLogFile(
			int ledgerType, Long ledgerId, boolean findLogAndLogFile,
			boolean loadFeedBacks) {
		if (ledgerId == null) {
			throw new BusinessValidationException("参数错误！");
		}
		LedgerPeopleAspirations peopleAspirations = peopleAspirationDao
				.getFullById(ledgerId);
		if (peopleAspirations == null
				|| peopleAspirations.getOrganization() == null) {
			throw new BusinessValidationException("台账不存在！");
		}
		peopleAspirations.setOrganization(organizationDubboService
				.getSimpleOrgById(peopleAspirations.getOrganization().getId()));
		if (peopleAspirations.getCreateTableType() != null
				&& peopleAspirations.getCreateTableType().getId() != null) {
			PropertyDict createTableType = propertyDictDubboService
					.getPropertyDictById(peopleAspirations.getCreateTableType()
							.getId());
			peopleAspirations.setCreateTableType(createTableType);
		}
		if (peopleAspirations.getAppealHumanType() != null
				&& peopleAspirations.getAppealHumanType().getId() != null) {
			PropertyDict appealHumanType = propertyDictDubboService
					.getPropertyDictById(peopleAspirations.getAppealHumanType()
							.getId());
			peopleAspirations.setAppealHumanType(appealHumanType);
		}
		if (peopleAspirations.getOccurOrg() != null
				&& peopleAspirations.getOccurOrg().getId() != null) {
			peopleAspirations.setOccurOrg(organizationDubboService
					.getSimpleOrgById(peopleAspirations.getOccurOrg().getId()));
		}

		if (peopleAspirations.getGender() != null
				&& peopleAspirations.getGender().getId() != null) {
			PropertyDict appealHumanType = propertyDictDubboService
					.getPropertyDictById(peopleAspirations.getGender().getId());
			peopleAspirations.setGender(appealHumanType);
		}
		if (peopleAspirations.getPosition() != null
				&& peopleAspirations.getPosition().getId() != null) {
			PropertyDict appealHumanType = propertyDictDubboService
					.getPropertyDictById(peopleAspirations.getPosition()
							.getId());
			peopleAspirations.setPosition(appealHumanType);
		}

		loadAttachFiles(peopleAspirations, ledgerType, findLogAndLogFile);
		if (loadFeedBacks) {
			loadIssueFeedBacks(peopleAspirations, ledgerType);
		}
		return peopleAspirations;
	}

	/*
	 * 加载反馈信息
	 */
	private void loadIssueFeedBacks(LedgerPeopleAspirations peopleAspirations,
			int ledgerType) {
		if (peopleAspirations == null || peopleAspirations.getId() == null) {
			return;
		}
		List<LedgerFeedBack> feedbacks = ledgerFeedBackService
				.getLedgerFeedByLedgerIdAndType(peopleAspirations.getId(),
						ledgerType);
		if (feedbacks != null) {
			peopleAspirations.setFeedbacks(feedbacks);
		}
	}

	/***************************************************************************
	 * 加载台账的附件和处理记录，记录附件
	 * 
	 * 
	 */
	private void loadAttachFiles(LedgerPeopleAspirations peopleAspirations,
			int ledgerType, boolean findLogAndLogFile) {
		if (peopleAspirations == null || peopleAspirations.getId() == null) {
			return;
		}
		if (findLogAndLogFile) {
			List<ThreeRecordsIssueLogNew> issueDealLogs = threeRecordsIssueService
					.loadIssueOperationLogsByIssueId(peopleAspirations.getId(),
							Long.valueOf(ledgerType));
			peopleAspirations.setIssueDealLogs(issueDealLogs);
		}
		List<ThreeRecordsIssueAttachFile> issueAttachFiles = threeRecordsIssueService
				.loadLedgerAndLogAttachFilesByLedgerIdAndType(
						peopleAspirations.getId(), ledgerType);
		if (!CollectionUtil.isAvaliable(issueAttachFiles)) {
			return;
		}
		Map<Long, List<ThreeRecordsIssueAttachFile>> issueLogAttachFiles = new HashMap<Long, List<ThreeRecordsIssueAttachFile>>();
		for (int index = issueAttachFiles.size(); index > 0; index--) {
			ThreeRecordsIssueAttachFile file = issueAttachFiles.get(index - 1);
			if (!isLogAttachFile(file)) {
				continue;
			}
			if (file.getIssueLog() == null
					|| file.getIssueLog().getId() == null) {
				continue;
			}
			if (findLogAndLogFile) {
				List<ThreeRecordsIssueAttachFile> logFiles = lookupLogFilesFromAllLogFile(
						file.getIssueLog().getId(), issueLogAttachFiles);
				logFiles.add(file);
			}
			issueAttachFiles.remove(index - 1);
		}
		peopleAspirations.setIssueAttachFiles(issueAttachFiles);
		if (findLogAndLogFile) {
			peopleAspirations.setIssueLogAttachFiles(issueLogAttachFiles);
		}
	}

	private boolean isLogAttachFile(ThreeRecordsIssueAttachFile file) {
		return file.getIssueLog() != null && file.getIssueLog().getId() != null;
	}

	private List<ThreeRecordsIssueAttachFile> lookupLogFilesFromAllLogFile(
			Long id,
			Map<Long, List<ThreeRecordsIssueAttachFile>> issueLogAttachFiles) {
		if (issueLogAttachFiles.containsKey(id)) {
			return issueLogAttachFiles.get(id);
		} else {
			List<ThreeRecordsIssueAttachFile> files = new ArrayList<ThreeRecordsIssueAttachFile>();
			issueLogAttachFiles.put(id, files);
			return files;
		}
	}

	private void setOrganization(LedgerPeopleAspirations peopleAspirations) {
		if (peopleAspirations == null) {
			return;
		}
		if (peopleAspirations.getOrganization() != null
				&& peopleAspirations.getOrganization().getId() != null) {
			peopleAspirations.setOrgInternalCode(organizationDubboService
					.getSimpleOrgById(
							peopleAspirations.getOrganization().getId())
					.getOrgInternalCode());
		}
		if (peopleAspirations.getOccurOrg() != null
				&& peopleAspirations.getOccurOrg().getId() != null) {
			peopleAspirations.setOccurOrgInternalCode(organizationDubboService
					.getSimpleOrgById(peopleAspirations.getOccurOrg().getId())
					.getOrgInternalCode());
		}
	}

	@Override
	public void updateLedgerPeopleAspirationStatus(
			LedgerPeopleAspirations peopleAspirations) {
		if (peopleAspirations == null || peopleAspirations.getId() == null) {
			throw new BusinessValidationException("参数错误！");
		}
		peopleAspirationDao.updateLedgerCurrentStepAndOrg(peopleAspirations);
	}

	@Override
	public LedgerPeopleAspirations savePeopleAspirations(
			LedgerPeopleAspirations peopleAspirations) {
		ValidateResult result = ledgerPeopleAspirationsValidator
				.validate(peopleAspirations);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		try {
			setOrganization(peopleAspirations);
			setOldLedgerIdAndType(peopleAspirations);
			peopleAspirations = peopleAspirationDao
					.addPeopleAspirations(peopleAspirations);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的savePeopleAspirations方法出现异常，原因：",
					"新增民生台账表信息出现错误", e);
		}
		return peopleAspirations;
	}

	@Override
	public void registerProcess(LedgerPeopleAspirations peopleAspirations) {
		try {
			threeRecordsIssueWorkFlowEngine.register(peopleAspirations);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PeopleAspirationServiceImpl的registerProcess方法出现异常，原因：",
					"民生台账表启动流程信息出现错误", e);
		}
	}

	/*
	 * 更新事件转入状态
	 */
	private void updateLedgerConvertStatus(
			LedgerPeopleAspirations peopleAspirations) {
		if (peopleAspirations == null
				|| peopleAspirations.getConvertId() == null) {
			return;
		}
		LedgerConvert ledgerConvert = ledgerConvertService
				.getSimpleLedgerConvertById(peopleAspirations.getConvertId());
		if (ledgerConvert == null) {
			return;
		}
		ledgerConvert.setStatus(LedgerConstants.LEDGER_CONVERT_STATUS_YES);
		ledgerConvert
				.setLedgerSerialNumber(peopleAspirations.getSerialNumber());
		ledgerConvert.setLedgerId(peopleAspirations.getId());
		ledgerConvert.setLedgerType(peopleAspirations.getLedgerType());
		ledgerConvert.setConvertDate(peopleAspirations.getCreateDate());
		ledgerConvertService.updateLedgerConvert(ledgerConvert);
	}

	@Override
	public LedgerPeopleAspirations findPeopleAspirationAndFileOrLogAndLogFile(
			int ledgerType, Long ledgerId, boolean findLogAndLogFile,
			boolean loadFeedBacks, boolean loadReplys) {
		LedgerPeopleAspirations peopleAspiration = findPeopleAspirationAndFileOrLogAndLogFile(
				ledgerType, ledgerId, findLogAndLogFile, loadFeedBacks);
		if (loadReplys) {
			loadReplys(peopleAspiration, ledgerId, ledgerType);
		}
		for (ThreeRecordsIssueLogNew log : peopleAspiration.getIssueDealLogs()) {//查询台账处理步骤
			log.setIssueStep(threeRecordsIssueProcessService
					.getSimpleIssueStepById(log.getIssueStep().getId()));
			//			log.getDealOrg().setOrgLevel(propertyDictDubboService.getPropertyDictById(log.getDealOrg().getOrgLevel().getId()));
			if (log.getDealType() == null) {//处理类型为空时表示为新增，用台账登记时间替换处理时间
				log.setOperateTime(peopleAspiration.getOccurDate());
			}
		}
		return peopleAspiration;
	}

	private void loadReplys(LedgerPeopleAspirations peopleAspiration,
			Long keyId, int ledgerType) {
		if (keyId == null || peopleAspiration == null) {
			return;
		}
		List<ReplyForm> replys = replyFormDubboService
				.getReplyFormAndFilesByLedgerIdAndType(keyId, ledgerType);
		peopleAspiration.setReplys(replys);

	}

	@Override
	public List<LedgerPeopleAspirations> getPeopleAspirationByHistoryId(
			String id) {
		if (!StringUtil.isStringAvaliable(id)) {
			return null;
		}
		return peopleAspirationDao.getPeopleAspirationByHistoryId(id);
	}

	private void setOldLedgerIdAndType(
			LedgerPeopleAspirations ledgerPeopleAspirations) {
		if (ledgerPeopleAspirations == null
				|| !StringUtil.isStringAvaliable(ledgerPeopleAspirations
						.getOldHistoryId())) {
			return;
		}
		List<LedgerPeopleAspirations> history = getPeopleAspirationByHistoryId(ledgerPeopleAspirations
				.getOldHistoryId());
		if (history == null || history.size() == 0) {
			return;
		}
		LedgerPeopleAspirations peopleAspirations = history.get(0);
		ledgerPeopleAspirations.setOldLedgerId(peopleAspirations.getId());
		ledgerPeopleAspirations.setOldLedgerType(peopleAspirations
				.getLedgerType());
	}

	@Override
	public void updateFeedBackStatus(Long ledgerId, int isFeedBack) {
		if (ledgerId == null) {
			throw new BusinessValidationException("参数错误！");
		}
		try {
			peopleAspirationDao.updateFeedBackStatus(ledgerId, isFeedBack);
		} catch (Exception e) {
			throw new ServiceValidationException("更新台账反馈状态失败！", e);
		}
	}

	@Override
	public int countLedgerByOldHistoryId(String oldHistoryId, String orgCode) {
		return peopleAspirationDao.countLedgerByOldHistoryId(oldHistoryId,
				orgCode);
	}

}
