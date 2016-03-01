package com.tianque.account.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.account.api.PeopleAspirationDubboService;
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
import com.tianque.plugin.account.service.PeopleAspirationService;

@Component
public class PeopleAspirationDubboServiceImpl implements
		PeopleAspirationDubboService {

	@Autowired
	private PeopleAspirationService peopleAspirationService;

	@Override
	public Agriculture addAgriculture(Agriculture agriculture) {
		return peopleAspirationService.addAgriculture(agriculture);
	}

	@Override
	public Education addEducation(Education education) {
		return peopleAspirationService.addEducation(education);
	}

	@Override
	public Energy addEnergy(Energy energy) {
		return peopleAspirationService.addEnergy(energy);
	}

	@Override
	public Environment addEnvironment(Environment environment) {
		return peopleAspirationService.addEnvironment(environment);
	}

	@Override
	public Labor addLabor(Labor labor) {
		return peopleAspirationService.addLabor(labor);
	}

	@Override
	public Medical addMedical(Medical medical) {
		return peopleAspirationService.addMedical(medical);
	}

	@Override
	public Other addOther(Other other) {
		return peopleAspirationService.addOther(other);
	}

	@Override
	public LedgerPeopleAspirations addPeopleAspirations(
			LedgerPeopleAspirations peopleAspirations) {
		return peopleAspirationService.addPeopleAspirations(peopleAspirations);
	}

	@Override
	public LedgerPeopleAspirations addPeopleAspirations(
			LedgerPeopleAspirations peopleAspirations,
			List<ThreeRecordsIssueAttachFile> files) {
		return peopleAspirationService.addPeopleAspirations(peopleAspirations,
				files);
	}

	@Override
	public Science addScience(Science science) {
		return peopleAspirationService.addScience(science);
	}

	@Override
	public Town addTown(Town town) {
		return peopleAspirationService.addTown(town);
	}

	@Override
	public Traffic addTraffic(Traffic traffic) {
		return peopleAspirationService.addTraffic(traffic);
	}

	@Override
	public WaterResources addWaterResources(WaterResources waterResources) {
		return peopleAspirationService.addWaterResources(waterResources);
	}

	@Override
	public void deleteAgricultureByAspirationId(Long id) {
		peopleAspirationService.deleteAgricultureByAspirationId(id);

	}

	@Override
	public void deleteAgricultureLedgerAndStepById(Long ledgerId) {
		peopleAspirationService.deleteAgricultureLedgerAndStepById(ledgerId);

	}

	@Override
	public void deleteEducationByAspirationId(Long id) {
		peopleAspirationService.deleteEducationByAspirationId(id);

	}

	@Override
	public void deleteEducationLedgerAndStepById(Long ledgerId) {
		peopleAspirationService.deleteEducationLedgerAndStepById(ledgerId);

	}

	@Override
	public void deleteEnergyByAspirationId(Long id) {
		peopleAspirationService.deleteEnergyByAspirationId(id);

	}

	@Override
	public void deleteEnergyLedgerAndStepById(Long ledgerId) {
		peopleAspirationService.deleteEnergyLedgerAndStepById(ledgerId);

	}

	@Override
	public void deleteEnvironmentByAspirationId(Long id) {
		peopleAspirationService.deleteEnvironmentByAspirationId(id);

	}

	@Override
	public void deleteEnvironmentLedgerAndStepById(Long ledgerId) {
		peopleAspirationService.deleteEnvironmentLedgerAndStepById(ledgerId);

	}

	@Override
	public void deleteLaborByAspirationId(Long id) {
		peopleAspirationService.deleteLaborByAspirationId(id);
	}

	@Override
	public void deleteLaborLedgerAndStepById(Long ledgerId) {
		peopleAspirationService.deleteLaborLedgerAndStepById(ledgerId);

	}

	@Override
	public void deleteMedicalByAspirationId(Long id) {
		peopleAspirationService.deleteMedicalByAspirationId(id);

	}

	@Override
	public void deleteMedicalLedgerAndStepById(Long ledgerId) {
		peopleAspirationService.deleteMedicalLedgerAndStepById(ledgerId);

	}

	@Override
	public void deleteOtherByAspirationId(Long id) {
		peopleAspirationService.deleteOtherByAspirationId(id);

	}

	@Override
	public void deleteOtherLedgerAndStepById(Long ledgerId) {
		peopleAspirationService.deleteOtherLedgerAndStepById(ledgerId);

	}

	@Override
	public void deletePeopleAspirationById(Long id) {
		peopleAspirationService.deletePeopleAspirationById(id);

	}

	@Override
	public void deleteScienceByAspirationId(Long id) {
		peopleAspirationService.deleteScienceByAspirationId(id);

	}

	@Override
	public void deleteScienceLedgerAndStepById(Long ledgerId) {
		peopleAspirationService.deleteScienceLedgerAndStepById(ledgerId);

	}

	@Override
	public void deleteTownByAspirationId(Long id) {
		peopleAspirationService.deleteTownByAspirationId(id);

	}

	@Override
	public void deleteTownLedgerAndStepById(Long ledgerId) {
		peopleAspirationService.deleteTownLedgerAndStepById(ledgerId);

	}

	@Override
	public void deleteTrafficByAspirationId(Long id) {
		peopleAspirationService.deleteTrafficByAspirationId(id);

	}

	@Override
	public void deleteTrafficLedgerAndStepById(Long ledgerId) {
		peopleAspirationService.deleteTrafficLedgerAndStepById(ledgerId);

	}

	@Override
	public void deleteWaterLedgerAndStepById(Long ledgerId) {
		peopleAspirationService.deleteWaterLedgerAndStepById(ledgerId);

	}

	@Override
	public void deleteWaterResourcesByAspirationId(Long id) {
		peopleAspirationService.deleteWaterResourcesByAspirationId(id);

	}

	@Override
	public Agriculture getAgricultureByAspirationId(Long id) {
		return peopleAspirationService.getAgricultureByAspirationId(id);
	}

	@Override
	public Agriculture getAgricultureById(Long id) {
		return peopleAspirationService.getAgricultureById(id);
	}

	@Override
	public Education getEducationByAspirationId(Long id) {
		return peopleAspirationService.getEducationByAspirationId(id);
	}

	@Override
	public Education getEducationById(Long id) {
		return peopleAspirationService.getEducationById(id);
	}

	@Override
	public Energy getEnergyByAspirationId(Long id) {
		return peopleAspirationService.getEnergyByAspirationId(id);
	}

	@Override
	public Energy getEnergyById(Long id) {
		return peopleAspirationService.getEnergyById(id);
	}

	@Override
	public Environment getEnvironmentByAspirationId(Long id) {
		return peopleAspirationService.getEnvironmentByAspirationId(id);
	}

	@Override
	public Environment getEnvironmentById(Long id) {
		return peopleAspirationService.getEnvironmentById(id);
	}

	@Override
	public LedgerPeopleAspirations getFullById(Long id) {
		return peopleAspirationService.getFullById(id);
	}

	@Override
	public Labor getLaborByAspirationId(Long id) {
		return peopleAspirationService.getLaborByAspirationId(id);
	}

	@Override
	public Labor getLaborById(Long id) {
		return peopleAspirationService.getLaborById(id);
	}

	@Override
	public Medical getMedicalByAspirationId(Long id) {
		return peopleAspirationService.getMedicalByAspirationId(id);
	}

	@Override
	public Medical getMedicalById(Long id) {
		return peopleAspirationService.getMedicalById(id);
	}

	@Override
	public Other getOtherByAspirationId(Long id) {
		return peopleAspirationService.getOtherByAspirationId(id);
	}

	@Override
	public Other getOtherById(Long id) {
		return peopleAspirationService.getOtherById(id);
	}

	@Override
	public LedgerPeopleAspirations getPeopleAspiration(Long id) {
		return peopleAspirationService.getPeopleAspiration(id);
	}

	@Override
	public Science getScienceByAspirationId(Long id) {
		return peopleAspirationService.getScienceByAspirationId(id);
	}

	@Override
	public Science getScienceById(Long id) {
		return peopleAspirationService.getScienceById(id);
	}

	@Override
	public Town getTownByAspirationId(Long id) {
		return peopleAspirationService.getTownByAspirationId(id);
	}

	@Override
	public Town getTownById(Long id) {
		return peopleAspirationService.getTownById(id);
	}

	@Override
	public Traffic getTrafficByAspirationId(Long id) {
		return peopleAspirationService.getTrafficByAspirationId(id);
	}

	@Override
	public Traffic getTrafficById(Long id) {
		return peopleAspirationService.getTrafficById(id);
	}

	@Override
	public WaterResources getWaterResourcesByAspirationId(Long id) {
		return peopleAspirationService.getWaterResourcesByAspirationId(id);
	}

	@Override
	public WaterResources getWaterResourcesById(Long id) {
		return peopleAspirationService.getWaterResourcesById(id);
	}

	@Override
	public Agriculture updateAgriculture(Agriculture agriculture) {
		return peopleAspirationService.updateAgriculture(agriculture);
	}

	@Override
	public Education updateEducation(Education education) {
		return peopleAspirationService.updateEducation(education);
	}

	@Override
	public Energy updateEnergy(Energy energy) {
		return peopleAspirationService.updateEnergy(energy);
	}

	@Override
	public Environment updateEnvironment(Environment environment) {
		return peopleAspirationService.updateEnvironment(environment);
	}

	@Override
	public Labor updateLabor(Labor labor) {
		return peopleAspirationService.updateLabor(labor);
	}

	@Override
	public Medical updateMedical(Medical medical) {
		return peopleAspirationService.updateMedical(medical);
	}

	@Override
	public Other updateOther(Other other) {
		return peopleAspirationService.updateOther(other);
	}

	@Override
	public LedgerPeopleAspirations updatePeopleAspirations(
			LedgerPeopleAspirations peopleAspirations,
			List<ThreeRecordsIssueAttachFile> files) {
		return peopleAspirationService.updatePeopleAspirations(
				peopleAspirations, files);
	}

	@Override
	public Science updateScience(Science science) {
		return peopleAspirationService.updateScience(science);
	}

	@Override
	public Town updateTown(Town town) {
		return peopleAspirationService.updateTown(town);
	}

	@Override
	public Traffic updateTraffic(Traffic traffic) {
		return peopleAspirationService.updateTraffic(traffic);
	}

	@Override
	public WaterResources updateWaterResources(WaterResources waterResources) {
		return peopleAspirationService.updateWaterResources(waterResources);
	}

	@Override
	public LedgerPeopleAspirations createTemporaryPeopleAspiration(
			int ledgerType, Long orgId) {

		return peopleAspirationService.createTemporaryPeopleAspiration(
				ledgerType, orgId);
	}

	@Override
	public LedgerPeopleAspirations findPeopleAspirationAndFileOrLogAndLogFile(
			int ledgerType, Long ledgerId, boolean findLogAndLogFile,
			boolean loadFeedBacks) {

		return peopleAspirationService
				.findPeopleAspirationAndFileOrLogAndLogFile(ledgerType,
						ledgerId, findLogAndLogFile, loadFeedBacks);
	}
	
	@Override
	public LedgerPeopleAspirations findPeopleAspirationAndFileOrLogAndLogFile(
			int ledgerType, Long ledgerId, boolean findLogAndLogFile,
			boolean loadFeedBacks,boolean loadReplys) {

		return peopleAspirationService
				.findPeopleAspirationAndFileOrLogAndLogFile(ledgerType,
						ledgerId, findLogAndLogFile, loadFeedBacks,loadReplys);
	}

	@Override
	public void updateLedgerPeopleAspirationStatus(
			LedgerPeopleAspirations peopleAspirations) {
		peopleAspirationService
				.updateLedgerPeopleAspirationStatus(peopleAspirations);
	}

	@Override
	public LedgerPeopleAspirations savePeopleAspirations(
			LedgerPeopleAspirations peopleAspirations) {
		return peopleAspirationService.savePeopleAspirations(peopleAspirations);
	}

	@Override
	public void registerProcess(LedgerPeopleAspirations peopleAspirations) {
		peopleAspirationService.registerProcess(peopleAspirations);

	}

	@Override
	public int countLedgerByOldHistoryId(String oldHistoryId, String orgCode) {
		return peopleAspirationService.countLedgerByOldHistoryId(oldHistoryId, orgCode);
	}

}
