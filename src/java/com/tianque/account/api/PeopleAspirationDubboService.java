package com.tianque.account.api;

import java.util.List;

import com.tianque.plugin.account.domain.Agriculture;
import com.tianque.plugin.account.domain.Education;
import com.tianque.plugin.account.domain.Energy;
import com.tianque.plugin.account.domain.Environment;
import com.tianque.plugin.account.domain.Labor;
import com.tianque.plugin.account.domain.LedgerPeopleAspirations;
import com.tianque.plugin.account.domain.Medical;
import com.tianque.plugin.account.domain.Other;
import com.tianque.plugin.account.domain.Science;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.Town;
import com.tianque.plugin.account.domain.Traffic;
import com.tianque.plugin.account.domain.WaterResources;

public interface PeopleAspirationDubboService {

	/**
	 * 修改民生信息，并上传附件
	 * 
	 * @param peopleAspirations
	 * @param files
	 * @return
	 */
	public LedgerPeopleAspirations updatePeopleAspirations(
			LedgerPeopleAspirations peopleAspirations,
			List<ThreeRecordsIssueAttachFile> files);

	/**
	 * 新增民生信息
	 * 
	 * @param peopleAspirations
	 * @return
	 */
	public LedgerPeopleAspirations addPeopleAspirations(
			LedgerPeopleAspirations peopleAspirations);

	/**
	 * 新增民生信息，并上传附件
	 * 
	 * @param peopleAspirations
	 * @param files
	 * @return
	 */
	public LedgerPeopleAspirations addPeopleAspirations(
			LedgerPeopleAspirations peopleAspirations,
			List<ThreeRecordsIssueAttachFile> files);

	/**
	 * 按ID查询民生信息
	 * 
	 * @param id
	 * @return
	 */
	public LedgerPeopleAspirations getPeopleAspiration(Long id);

	/**
	 * 按ID删除民生信息
	 * 
	 * @param id
	 */
	public void deletePeopleAspirationById(Long id);

	/**
	 * 按ID查询民生所有信息
	 * 
	 * @param id
	 * @return
	 */
	public LedgerPeopleAspirations getFullById(Long id);

	/**
	 * 新增民生-水利信息
	 * 
	 * @param waterResources
	 * @return
	 */
	public WaterResources addWaterResources(WaterResources waterResources);

	/**
	 * 修改民生-水利信息
	 * 
	 * @param waterResources
	 * @return
	 */
	public WaterResources updateWaterResources(WaterResources waterResources);

	/**
	 * 根据peopleAspiration 编号删除水利信息
	 * 
	 * @param id
	 */
	public void deleteWaterResourcesByAspirationId(Long id);

	/**
	 * 根据peopleAspiration 编号查询水利信息
	 * 
	 * @param id
	 * @return
	 */
	public WaterResources getWaterResourcesByAspirationId(Long id);

	/**
	 * 根据编号查询水利信息
	 * 
	 * @param id
	 * @return
	 */
	public WaterResources getWaterResourcesById(Long id);

	/**
	 * 根据编号查询交通信息
	 * 
	 * @param id
	 * @return
	 */
	public Traffic getTrafficById(Long id);

	/**
	 * 新增交通信息
	 * 
	 * @param traffic
	 * @return
	 */
	public Traffic addTraffic(Traffic traffic);

	/**
	 * 修改交通信息
	 * 
	 * @param traffic
	 * @return
	 */
	public Traffic updateTraffic(Traffic traffic);

	/**
	 * 根据peopleAspiration 编号删除交通信息
	 * 
	 * @param id
	 */
	public void deleteTrafficByAspirationId(Long id);

	/**
	 * 根据peopleAspiration 编号查询交通信息
	 * 
	 * @param id
	 * @return
	 */
	public Traffic getTrafficByAspirationId(Long id);

	/**
	 * 根据编号查询农业信息
	 * 
	 * @param id
	 * @return
	 */
	public Agriculture getAgricultureById(Long id);

	/**
	 * 新增农业信息
	 * 
	 * @param agriculture
	 * @return
	 */
	public Agriculture addAgriculture(Agriculture agriculture);

	/**
	 * 修改农业信息
	 * 
	 * @param agriculture
	 * @return
	 */
	public Agriculture updateAgriculture(Agriculture agriculture);

	/**
	 * 根据peopleAspiration 编号删除农业信息
	 * 
	 * @param id
	 */
	public void deleteAgricultureByAspirationId(Long id);

	/**
	 * 根据peopleAspiration 编号查询农业信息
	 * 
	 * @param id
	 * @return
	 */
	public Agriculture getAgricultureByAspirationId(Long id);

	/**
	 * 按ID查询民生其它台账信息
	 * 
	 * @param id
	 * @return
	 */
	public Other getOtherById(Long id);

	/**
	 * 新增民生其它台账信息
	 * 
	 * @param other
	 * @return
	 */
	public Other addOther(Other other);

	/**
	 * 修改民生其它台账信息
	 * 
	 * @param other
	 * @return
	 */
	public Other updateOther(Other other);

	/**
	 * 根据peopleAspiration 编号删除其它台账信息
	 * 
	 * @param id
	 */
	public void deleteOtherByAspirationId(Long id);

	/**
	 * 根据peopleAspiration 编号查询其它台账信息
	 * 
	 * @param id
	 * @return
	 */
	public Other getOtherByAspirationId(Long id);

	/**
	 * 按ID查询环境信息
	 * 
	 * @param id
	 * @return
	 */
	public Environment getEnvironmentById(Long id);

	/**
	 * 新增环境信息
	 * 
	 * @param environment
	 * @return
	 */
	public Environment addEnvironment(Environment environment);

	/**
	 * 修改环境信息
	 * 
	 * @param environment
	 * @return
	 */
	public Environment updateEnvironment(Environment environment);

	/**
	 * 根据peopleAspiration 编号删除环境保护信息
	 * 
	 * @param id
	 */
	public void deleteEnvironmentByAspirationId(Long id);

	/**
	 * 根据peopleAspiration 编号查询环境保护信息
	 * 
	 * @param id
	 * @return
	 */
	public Environment getEnvironmentByAspirationId(Long id);

	/**
	 * 根据peopleAspiration 编号查询教育信息
	 * 
	 * @param id
	 * @return
	 */
	public Education getEducationById(Long id);

	/**
	 * 新增教育信息
	 * 
	 * @param education
	 * @return
	 */
	public Education addEducation(Education education);

	/**
	 * 修改教育信息
	 * 
	 * @param education
	 * @return
	 */
	public Education updateEducation(Education education);

	/**
	 * 根据peopleAspiration 编号删除教育信息
	 * 
	 * @param id
	 */
	public void deleteEducationByAspirationId(Long id);

	/**
	 * 根据peopleAspiration 编号查询教育信息
	 * 
	 * @param id
	 * @return
	 */
	public Education getEducationByAspirationId(Long id);

	/**
	 * 按ID查询能源
	 * 
	 * @param id
	 * @return
	 */
	public Energy getEnergyById(Long id);

	/**
	 * 新增能源信息
	 * 
	 * @param energy
	 * @return
	 */
	public Energy addEnergy(Energy energy);

	/**
	 * 修改能源信息
	 * 
	 * @param energy
	 * @return
	 */
	public Energy updateEnergy(Energy energy);

	/**
	 * 根据peopleAspiration 编号删除能源信息
	 * 
	 * @param id
	 */
	public void deleteEnergyByAspirationId(Long id);

	/**
	 * 根据peopleAspiration 编号查询能源信息
	 * 
	 * @param id
	 * @return
	 */
	public Energy getEnergyByAspirationId(Long id);

	/**
	 * 按ID查询劳动和社会保障信息
	 * 
	 * @param id
	 * @return
	 */
	public Labor getLaborById(Long id);

	/**
	 * 新增劳动和社会保障信息
	 * 
	 * @param labor
	 * @return
	 */
	public Labor addLabor(Labor labor);

	/**
	 * 修改劳动和社会保障信息
	 * 
	 * @param labor
	 * @return
	 */
	public Labor updateLabor(Labor labor);

	/**
	 * 根据peopleAspiration 编号删除劳动和社会保障信息
	 * 
	 * @param id
	 */
	public void deleteLaborByAspirationId(Long id);

	/**
	 * 根据peopleAspiration 编号查询劳动和社会保障信息
	 * 
	 * @param id
	 * @return
	 */
	public Labor getLaborByAspirationId(Long id);

	/**
	 * 按ID查询医疗卫生信息
	 * 
	 * @param id
	 * @return
	 */
	public Medical getMedicalById(Long id);

	/**
	 * 新增医疗卫生信息
	 * 
	 * @param medical
	 * @return
	 */
	public Medical addMedical(Medical medical);

	/**
	 * 修改医疗卫生信息
	 * 
	 * @param medical
	 * @return
	 */
	public Medical updateMedical(Medical medical);

	/**
	 * 根据peopleAspiration 编号删除医疗卫生信息
	 * 
	 * @param id
	 */
	public void deleteMedicalByAspirationId(Long id);

	/**
	 * 根据peopleAspiration 编号查询医疗卫生信息
	 * 
	 * @param id
	 * @return
	 */
	public Medical getMedicalByAspirationId(Long id);

	/**
	 * 按ID查询科技文体信息
	 * 
	 * @param id
	 * @return
	 */
	public Science getScienceById(Long id);

	/**
	 * 新增科技文体信息
	 * 
	 * @param science
	 * @return
	 */
	public Science addScience(Science science);

	/**
	 * 修改科技文体信息
	 * 
	 * @param science
	 * @return
	 */
	public Science updateScience(Science science);

	/**
	 * 根据peopleAspiration 编号删除科技文体表信息
	 * 
	 * @param id
	 */
	public void deleteScienceByAspirationId(Long id);

	/**
	 * 根据peopleAspiration 编号查询科技文体表信息
	 * 
	 * @param id
	 * @return
	 */
	public Science getScienceByAspirationId(Long id);

	/**
	 * 按ID查询城乡规划建设信息
	 * 
	 * @param id
	 * @return
	 */
	public Town getTownById(Long id);

	/**
	 * 新增城乡规划建设信息
	 * 
	 * @param town
	 * @return
	 */
	public Town addTown(Town town);

	/**
	 * 修改城乡规划建设信息
	 * 
	 * @param town
	 * @return
	 */
	public Town updateTown(Town town);

	/**
	 * 根据peopleAspiration 编号删除城乡规划建设表信息
	 * 
	 * @param id
	 */
	public void deleteTownByAspirationId(Long id);

	/**
	 * 根据peopleAspiration 编号查询城乡规划建设表信息
	 * 
	 * @param id
	 * @return
	 */
	public Town getTownByAspirationId(Long id);

	/**
	 * 按水利ID删除台账信息
	 * 
	 * @param ledgerId
	 */
	public void deleteWaterLedgerAndStepById(Long ledgerId);

	/**
	 * 按其它台账ID删除台账信息
	 * 
	 * @param ledgerId
	 */
	public void deleteOtherLedgerAndStepById(Long ledgerId);

	/**
	 * 按城乡规划建设ID删除台账信息
	 * 
	 * @param ledgerId
	 */
	public void deleteTownLedgerAndStepById(Long ledgerId);

	/**
	 * 按科技文体ID删除台账信息
	 * 
	 * @param ledgerId
	 */
	public void deleteScienceLedgerAndStepById(Long ledgerId);

	/**
	 * 按医疗卫生ID删除台账信息
	 * 
	 * @param ledgerId
	 */
	public void deleteMedicalLedgerAndStepById(Long ledgerId);

	/**
	 * 按劳动和社会保障ID删除台账信息
	 * 
	 * @param ledgerId
	 */
	public void deleteLaborLedgerAndStepById(Long ledgerId);

	/**
	 * 按能源ID删除台账信息
	 * 
	 * @param ledgerId
	 */
	public void deleteEnergyLedgerAndStepById(Long ledgerId);

	/**
	 * 按教育信息ID删除台账信息
	 * 
	 * @param ledgerId
	 */
	public void deleteEducationLedgerAndStepById(Long ledgerId);

	/**
	 * 按环境信息ID删除台账信息
	 * 
	 * @param ledgerId
	 */
	public void deleteEnvironmentLedgerAndStepById(Long ledgerId);

	/**
	 * 按农业信息ID删除台账信息
	 * 
	 * @param ledgerId
	 */
	public void deleteAgricultureLedgerAndStepById(Long ledgerId);

	/**
	 * 按交通信息ID删除台账信息
	 * 
	 * @param ledgerId
	 */
	public void deleteTrafficLedgerAndStepById(Long ledgerId);

	/**
	 * 根据台账类型，组织机构编号 创建临时民生信息
	 * 
	 * @param ledgerType
	 * @param orgId
	 * @return
	 */
	public LedgerPeopleAspirations createTemporaryPeopleAspiration(
			int ledgerType, Long orgId);

	/**
	 * 查找台账相关的附件，如findLogAndLogFile为true时查找工作记录和记录附件,loadFeedBacks为true反馈信息
	 * 
	 * @param ledgerType
	 * @param ledgerId
	 * @param findLogAndLogFile
	 * @param loadFeedBacks
	 * @return
	 */
	public LedgerPeopleAspirations findPeopleAspirationAndFileOrLogAndLogFile(
			int ledgerType, Long ledgerId, boolean findLogAndLogFile,
			boolean loadFeedBacks);

	/**
	 * 数据导入时更新民生信息状态
	 * 
	 * @param peopleAspirations
	 */
	public void updateLedgerPeopleAspirationStatus(
			LedgerPeopleAspirations peopleAspirations);

	/**
	 * 数据导入时新增民生信息，不触发流程
	 * 
	 * @param peopleAspirations
	 * @return
	 */
	public LedgerPeopleAspirations savePeopleAspirations(
			LedgerPeopleAspirations peopleAspirations);

	/*
	 * 数据导入无日志记录的启动流程
	 * 
	 * @param peopleAspirations
	 */
	public void registerProcess(LedgerPeopleAspirations peopleAspirations);

	/**
	 * 查找台账相关的附件，如findLogAndLogFile为true时查找工作记录和记录附件,loadFeedBacks为true反馈信息,
	 * loadReplys为true回复信息
	 * 
	 * @param ledgerType
	 * @param ledgerId
	 * @param findLogAndLogFile
	 * @param loadFeedBacks
	 * @param loadReplys
	 * @return
	 */
	public LedgerPeopleAspirations findPeopleAspirationAndFileOrLogAndLogFile(
			int ledgerType, Long ledgerId, boolean findLogAndLogFile,
			boolean loadFeedBacks, boolean loadReplys);

	/**
	 * 统计台账数，数据导入用，判断是否已经导入过
	 * @param oldHistoryId
	 * @return
	 */
	public int countLedgerByOldHistoryId(String oldHistoryId, String orgCode);
}
