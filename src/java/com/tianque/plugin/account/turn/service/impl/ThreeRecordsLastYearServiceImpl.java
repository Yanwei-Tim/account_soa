package com.tianque.plugin.account.turn.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.job.JobHelper;
import com.tianque.plugin.account.constants.LedgerConstants;
import com.tianque.plugin.account.dao.LedgerPoorPeopleDao;
import com.tianque.plugin.account.dao.LedgerSteadyWorkDao;
import com.tianque.plugin.account.dao.PeopleAspirationDao;
import com.tianque.plugin.account.dao.ThreeRecordsIssueDao;
import com.tianque.plugin.account.dao.ThreeRecordsIssueLogDao;
import com.tianque.plugin.account.dao.ThreeRecordsIssueProcessDao;
import com.tianque.plugin.account.domain.AcceptForm;
import com.tianque.plugin.account.domain.Agriculture;
import com.tianque.plugin.account.domain.AssignForm;
import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.DeclareForm;
import com.tianque.plugin.account.domain.Education;
import com.tianque.plugin.account.domain.Energy;
import com.tianque.plugin.account.domain.Environment;
import com.tianque.plugin.account.domain.Labor;
import com.tianque.plugin.account.domain.LedgerAttachFile;
import com.tianque.plugin.account.domain.LedgerPeopleAspirations;
import com.tianque.plugin.account.domain.LedgerPoorPeople;
import com.tianque.plugin.account.domain.LedgerPoorPeopleMember;
import com.tianque.plugin.account.domain.LedgerSteadyWork;
import com.tianque.plugin.account.domain.LedgerSteadyWorkPeopleInfo;
import com.tianque.plugin.account.domain.Medical;
import com.tianque.plugin.account.domain.Other;
import com.tianque.plugin.account.domain.ReportForm;
import com.tianque.plugin.account.domain.Science;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;
import com.tianque.plugin.account.domain.ThreeRecordsYearTurn;
import com.tianque.plugin.account.domain.Town;
import com.tianque.plugin.account.domain.Traffic;
import com.tianque.plugin.account.domain.TurnForm;
import com.tianque.plugin.account.domain.WaterResources;
import com.tianque.plugin.account.service.AcceptFormService;
import com.tianque.plugin.account.service.AssignFormService;
import com.tianque.plugin.account.service.DeclareFormService;
import com.tianque.plugin.account.service.LedgerPoorPeopleMemberService;
import com.tianque.plugin.account.service.LedgerSteadyWorkPeopleInfoService;
import com.tianque.plugin.account.service.ReportFormService;
import com.tianque.plugin.account.service.ThreeRecordsKeyGeneratorService;
import com.tianque.plugin.account.service.TurnFormService;
import com.tianque.plugin.account.state.ThreeRecordsIssueState;
import com.tianque.plugin.account.turn.service.ThreeRecordsLastYearService;
import com.tianque.plugin.account.util.DealYearOrMonthUtil;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Service("threeRecordsLastYearServiceImpl")
@Transactional
public class ThreeRecordsLastYearServiceImpl implements ThreeRecordsLastYearService {
	@Autowired
	private PeopleAspirationDao peopleAspirationDao;
	@Autowired
	private ThreeRecordsIssueLogDao threeRecordsIssueLogDao;
	@Autowired
	private ThreeRecordsIssueProcessDao threeRecordsIssueProcessDao;
	@Autowired
	private ThreeRecordsIssueDao threeRecordsIssueDao;
	@Autowired
	private ThreeRecordsKeyGeneratorService threeRecordsKeyGeneratorService;
	@Autowired
    private PropertyDictDubboService propertyDictDubboService;
	@Autowired
	private LedgerPoorPeopleDao poorDao;
	@Autowired
	private LedgerSteadyWorkDao steadyWorkDao;
	@Autowired
	private LedgerPoorPeopleMemberService ledgerPoorPeopleMemberService;
	@Autowired
	private LedgerSteadyWorkPeopleInfoService ledgerSteadyWorkPeopleInfoService;
	@Autowired
	private ReportFormService reportFormService;
	@Autowired
	private AcceptFormService acceptFormService;
	@Autowired
	private TurnFormService turnFormService;
	@Autowired
	private DeclareFormService declareFormService;
	@Autowired
	private AssignFormService assignFormService;
	
	
	public void addThreeRecordsYearTurn(ThreeRecordsYearTurn yearTurn) {
		threeRecordsIssueProcessDao.addThreeRecordsYearTurn(yearTurn);
	}

	@Override
	public void lastYearTurn(){
		new Thread(){
			public void run(){
				JobHelper.createMockAdminSession();
				System.out.println("三本台账开始上年接转...");
				List<PropertyDict> createTypeList = propertyDictDubboService.findPropertyDictByDomainName(PropertyTypes.LEDGER_PEOPLEASPIRATION_CREATE_TABLE_TYPE);
				PropertyDict turnDict = new PropertyDict();
				for(PropertyDict dict : createTypeList){
					if(dict.getDisplayName().equals("上年接转")){
						turnDict = dict;
						break;
					}
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("completeCode", ThreeRecordsIssueState.SUBSTANCE_CODE);
				map.put("lastYearTurn", "上年接转");
				map.put("createTableType", PropertyTypes.LEDGER_PEOPLEASPIRATION_CREATE_TABLE_TYPE);
				map.put("ledgerPoorPeopleType", LedgerConstants.POORPEOPLE);
				map.put("ledgerSteadyWorkType", LedgerConstants.STEADYWORK);
				map.put("year", DealYearOrMonthUtil.dealYear(Calendar.getInstance().get(Calendar.YEAR) - 1));
//				threeRecordsIssueProcessDao.deleteALLTurnInfo();//删除所有接转信息，数据更正后需删除此行代码
				turnPeopleAspirations(turnDict, map);//民生接转
				turnPoorPeople(turnDict, map);//困难接转
				turnSteadyWork(turnDict, map);//稳定接转
				System.out.println("三本台账上年接转结束...");
			}
		}.start();
	}
	
	/**
	 * 困难台账上年接转
	 * @param turnDict
	 */
	private void turnPoorPeople(PropertyDict turnDict, Map<String, Object> map) {
		List<LedgerPoorPeople> poorList = poorDao.needTurnLedgerPoorPeople(map);//需要接转的困难台账列表
		for(LedgerPoorPeople oldPoor : poorList){
			LedgerPoorPeople newPoor = poorDao.addTurnLedgerPoorPeople(cloneLedgerPoorPeople(oldPoor, turnDict));
			
			turn(oldPoor, newPoor);
			
			List<LedgerPoorPeopleMember> poorPeopleMemberList = ledgerPoorPeopleMemberService.findByLedgerPoorPeople(oldPoor);//接转家庭成员
			if(!poorPeopleMemberList.isEmpty()){
				newPoor.setLedgerPoorPeopleMembers(poorPeopleMemberList);
				ledgerPoorPeopleMemberService.addPoorPeopleMembers(newPoor);
			}
		}
	}
	
	/**
	 * 稳定台账上年接转
	 * @param turnDict
	 */
	private void turnSteadyWork(PropertyDict turnDict, Map<String, Object> map){
		List<LedgerSteadyWork> steadyWorkList = steadyWorkDao.needTurnLedgerSteadyWork(map);
		for(LedgerSteadyWork oldSteadyWork : steadyWorkList){
			LedgerSteadyWork newSteadyWork = steadyWorkDao.addTurnLedgerSteadyWork(cloneLedgerSteadyWork(oldSteadyWork, turnDict));
			
			turn(oldSteadyWork, newSteadyWork);
			
			List<LedgerSteadyWorkPeopleInfo> steadyWorkPeopleInfoList = ledgerSteadyWorkPeopleInfoService.findByLedgerSteadyWork(oldSteadyWork);//接转涉稳人员
			if(!steadyWorkPeopleInfoList.isEmpty()){
				newSteadyWork.setLedgerSteadyWorkPeopleInfos(steadyWorkPeopleInfoList);
				ledgerSteadyWorkPeopleInfoService.addLedgerSteadyWorkPeopleInfos(newSteadyWork);
			}
		}
	}
	
	/**
	 * 民生台账上年接转
	 * @param turnDict
	 */
	private void turnPeopleAspirations(PropertyDict turnDict, Map<String, Object> map){
		List<LedgerPeopleAspirations> peopleList = peopleAspirationDao.getNeedTurnPeopleAspirations(map);//需要接转的民生台账列表
		for(LedgerPeopleAspirations oldPeople : peopleList){
			LedgerPeopleAspirations newPeople = peopleAspirationDao.addPeopleAspirations(cloneLedgerPeopleAspirations(oldPeople, turnDict));//接转后people
			
			turnResearch(oldPeople.getId(), newPeople);//民生台账分类相关的研究整理接转
			
			turn(oldPeople, newPeople);
		}
	}
	
	/**
	 * 保存接转记录
	 * @param oldLedger
	 * @param newLedger
	 */
	private void dealTurnLedger(BaseWorking oldLedger, BaseWorking newLedger){
		List<ThreeRecordsIssueStep> list = threeRecordsIssueProcessDao.getIssueLastStepByLedgerIdAndType(oldLedger.getId(), oldLedger.getLedgerType());
		if (!list.isEmpty()) {
			ThreeRecordsIssueStep step = list.get(0);
			ThreeRecordsYearTurn turn = new ThreeRecordsYearTurn();
			turn.setCreateOrg(oldLedger.getOrganization());
			turn.setYear(Calendar.getInstance().get(Calendar.YEAR));
			turn.setNewLedgerId(newLedger.getId());
			turn.setOldLedgerId(oldLedger.getId());
			turn.setTurnOrg(step.getTarget());
			if (oldLedger.getLedgerType() == LedgerConstants.POORPEOPLE) {//按困难类型填充ledgerType
				String[] poors = ((LedgerPoorPeople)oldLedger).getPoorType().split(",");
				for(String poor : poors){
					turn.setLedgerType(Integer.parseInt(poor));
					addThreeRecordsYearTurn(turn);
				}
			} else if (oldLedger.getLedgerType() == LedgerConstants.STEADYWORK) {//按稳定类型填充ledgerType
				turn.setLedgerType(Integer.parseInt(((LedgerSteadyWork)oldLedger).getSteadyWorkType().getId() + ""));
				addThreeRecordsYearTurn(turn);
			} else {
				turn.setLedgerType(oldLedger.getLedgerType());
				addThreeRecordsYearTurn(turn);
			}
		}
	}
	
	private void turn(BaseWorking oldLedger, BaseWorking newLedger){
		dealTurnLedger(oldLedger, newLedger);//保存接转记录,主要用于报表统计
		
		turnRecordsFiles(oldLedger, newLedger);//台账本身的附件转接(不包含具体流程中上传的附件，流程中的附件在后面转接)
		
		Map<ThreeRecordsIssueLogNew, ThreeRecordsIssueLogNew> logMap = new HashMap<ThreeRecordsIssueLogNew, ThreeRecordsIssueLogNew>();//保存日志转节前和转接后的对应关系
		Map<ThreeRecordsIssueStep, ThreeRecordsIssueStep> stepMap = new HashMap<ThreeRecordsIssueStep, ThreeRecordsIssueStep>();//新来步骤对应关系
		
		List<ThreeRecordsIssueStep> oldSteps =  threeRecordsIssueProcessDao.getIssueStepByLedgerIdAndType(oldLedger.getId(), oldLedger.getLedgerType());
		for(ThreeRecordsIssueStep oldStep : oldSteps){
			ThreeRecordsIssueStep newStep = threeRecordsIssueProcessDao.addLedgerStep(cloneStep(oldStep, newLedger));//此处groupI未更新，在后面group转接后再更新
			stepMap.put(oldStep, newStep);
			List<ThreeRecordsIssueLogNew> oldLogs = threeRecordsIssueLogDao.getIssueLogsByStepId(oldStep.getId());//根据步骤得到相关log
			for(ThreeRecordsIssueLogNew oldLog : oldLogs){
				ThreeRecordsIssueLogNew newLog = threeRecordsIssueLogDao.addLog(cloneThreeRecordsIssueLogNew(oldLog, newLedger, newStep));//转接log
				logMap.put(oldLog, newLog);
				turnLogFiles(oldLog, newLog, newLedger);//接转台账处理log中上传的附件信息
			}
			
			turnReportForm(oldStep, newStep, newLedger);//呈报单接转
			turnAcceptForm(oldStep, newStep, newLedger);//受理单接转
			turnTurnForm(oldStep, newStep, newLedger);//转办单接转
			turnDeclareForm(oldStep, newStep, newLedger);//申报单接转
			turnAssignForm(oldStep, newStep, newLedger);//交办单接转
		}
		
		List<ThreeRecordsIssueStepGroup> oldGroups = threeRecordsIssueProcessDao.getIssueStepGroupByIssueId(oldLedger.getId(), oldLedger.getLedgerType());
		for(ThreeRecordsIssueStep oldStep : oldSteps){//接转group
			if(oldStep.getGroupId() != null){
				for(ThreeRecordsIssueStepGroup oldGroup : oldGroups){
					if(oldGroup.getKeyStep().getId().longValue() == oldStep.getId().longValue()){
						ThreeRecordsIssueStepGroup newGroup = new ThreeRecordsIssueStepGroup();
						newGroup.setKeyStep(stepMap.get(oldStep));
						newGroup.setEntyLog(getNewLogByOldLogId(oldGroup.getEntyLog().getId(), logMap));
						if(oldGroup.getOutLog() != null){
							newGroup.setOutLog(getNewLogByOldLogId(oldGroup.getOutLog().getId(), logMap));
						}
						newGroup.setLedgerId(newLedger.getId());
						newGroup.setLedgerType(oldGroup.getLedgerType());
						ThreeRecordsIssueStepGroup group = threeRecordsIssueProcessDao.addIssueStepGroup(newGroup);
						stepMap.get(oldStep).setGroupId(group.getId());//更新步骤中的groupId
					}
				}
			}
		}
		
		Map<ThreeRecordsIssueStep, ThreeRecordsIssueStep> copyStepMap =  stepMap;
		for(Map.Entry<ThreeRecordsIssueStep, ThreeRecordsIssueStep> entry : stepMap.entrySet()){
			ThreeRecordsIssueStep newStep = entry.getValue();
			threeRecordsIssueProcessDao.updateGroupId(newStep);//更新完group后，更新步骤中的groupId
			if(newStep.getBackTo() != null){
				newStep.setBackTo(getNewStepByOldStepId(newStep.getBackTo().getId(), copyStepMap));
				threeRecordsIssueProcessDao.updateBackTo(newStep);//更新完step后，更新BackTo
			}
		}
	}
	
	private void turnReportForm(ThreeRecordsIssueStep oldStep, ThreeRecordsIssueStep newStep, BaseWorking newLedger){
		ReportForm oldReportForm = reportFormService.getSimpleReportFormByStepId(oldStep.getId());
		if(oldReportForm != null){
			ReportForm newReportForm = new ReportForm();
			if(oldReportForm.getCreateDate() != null) newReportForm.setCreateDate(oldReportForm.getCreateDate());
			if(oldReportForm.getCreateUser() != null) newReportForm.setCreateUser(oldReportForm.getCreateUser());
			if(oldReportForm.getDataFrom() != null) newReportForm.setDataFrom(oldReportForm.getDataFrom());
			if(oldReportForm.getHandleContent() != null) newReportForm.setHandleContent(oldReportForm.getHandleContent());
			newReportForm.setLedgerId(newLedger.getId());
			newReportForm.setLedgerSerialNumber(newLedger.getSerialNumber());
			newReportForm.setLedgerType((long)newLedger.getLedgerType());
			if(oldReportForm.getMobile() != null) newReportForm.setMobile(oldReportForm.getMobile());
			if(oldReportForm.getName() != null) newReportForm.setName(oldReportForm.getName());
			if(oldReportForm.getOrder() != null) newReportForm.setOrder(oldReportForm.getOrder());
			if(oldReportForm.getReason() != null) newReportForm.setReason(oldReportForm.getReason());
			if(oldReportForm.getReportDate() != null) newReportForm.setReportDate(oldReportForm.getReportDate());
			if(oldReportForm.getSerialNumber() != null) newReportForm.setSerialNumber(oldReportForm.getSerialNumber());
			if(oldReportForm.getSortField() != null) newReportForm.setSortField(oldReportForm.getSortField());
			if(oldReportForm.getSourceOrg() != null) newReportForm.setSourceOrg(oldReportForm.getSourceOrg());
			newReportForm.setStepId(newStep.getId());
			if(oldReportForm.getTargetOrg() != null) newReportForm.setTargetOrg(oldReportForm.getTargetOrg());
//			newReportForm.setUpdateDate(CalendarUtil.now());
			newReportForm.setUpdateDate(initTurnTime());
			if(oldReportForm.getUpdateUser() != null) newReportForm.setUpdateUser(oldReportForm.getUpdateUser());
			reportFormService.addReportForm(newReportForm);
		}
	}
	
	private void turnAcceptForm(ThreeRecordsIssueStep oldStep, ThreeRecordsIssueStep newStep, BaseWorking newLedger){
		AcceptForm oldAcceptForm = acceptFormService.getSimpleAcceptFormByStepId(oldStep.getId());
		if(oldAcceptForm != null){
			AcceptForm newAcceptForm = new AcceptForm();
			if(oldAcceptForm.getAcceptOrg() != null) newAcceptForm.setAcceptOrg(oldAcceptForm.getAcceptOrg());
			if(oldAcceptForm.getCreateDate() != null) newAcceptForm.setCreateDate(oldAcceptForm.getCreateDate());
			if(oldAcceptForm.getCreateUser() != null) newAcceptForm.setCreateUser(oldAcceptForm.getCreateUser());
			if(oldAcceptForm.getCurrentOrgOpinion() != null) newAcceptForm.setCurrentOrgOpinion(oldAcceptForm.getCurrentOrgOpinion());
			if(oldAcceptForm.getDataFrom() != null) newAcceptForm.setDataFrom(oldAcceptForm.getDataFrom());
			if(oldAcceptForm.getDealUserName() != null) newAcceptForm.setDealUserName(oldAcceptForm.getDealUserName());
			if(oldAcceptForm.getFormAcceptDate() != null) newAcceptForm.setFormAcceptDate(oldAcceptForm.getFormAcceptDate());
			if(oldAcceptForm.getFormComeDate() != null) newAcceptForm.setFormComeDate(oldAcceptForm.getFormComeDate());
			if(oldAcceptForm.getFormType() != null) newAcceptForm.setFormType(oldAcceptForm.getFormType());
			if(oldAcceptForm.getHandleResult() != null) newAcceptForm.setHandleResult(oldAcceptForm.getHandleResult());
			if(oldAcceptForm.getInChargeOfLeaderOpinion() != null) newAcceptForm.setInChargeOfLeaderOpinion(oldAcceptForm.getInChargeOfLeaderOpinion());
			newAcceptForm.setLedgerId(newLedger.getId());
			newAcceptForm.setLedgerType((long)newLedger.getLedgerType());
			if(oldAcceptForm.getMajorLeaderOpinion() != null) newAcceptForm.setMajorLeaderOpinion(oldAcceptForm.getMajorLeaderOpinion());
			if(oldAcceptForm.getOrder() != null) newAcceptForm.setOrder(oldAcceptForm.getOrder());
			if(oldAcceptForm.getSerialNumber() != null) newAcceptForm.setSerialNumber(oldAcceptForm.getSerialNumber());
			if(oldAcceptForm.getSortField() != null) newAcceptForm.setSortField(oldAcceptForm.getSortField());
			newAcceptForm.setStepId(newStep.getId());
//			newAcceptForm.setUpdateDate(CalendarUtil.now());
			newAcceptForm.setUpdateDate(initTurnTime());
			if(oldAcceptForm.getUpdateUser() != null) newAcceptForm.setUpdateUser(oldAcceptForm.getUpdateUser());
			acceptFormService.addAcceptForm(newAcceptForm);
		}
	}
	
	private void turnTurnForm(ThreeRecordsIssueStep oldStep, ThreeRecordsIssueStep newStep, BaseWorking newLedger){
		TurnForm oldTurnForm = turnFormService.getSimpleTurnFormByStepId(oldStep.getId());
		if(oldTurnForm != null){
			TurnForm newTurnForm = new TurnForm();
			if(newTurnForm.getCreateDate() != null) newTurnForm.setCreateDate(oldTurnForm.getCreateDate());
			if(newTurnForm.getCreateUser() != null) newTurnForm.setCreateUser(oldTurnForm.getCreateUser());
			if(newTurnForm.getDataFrom() != null) newTurnForm.setDataFrom(oldTurnForm.getDataFrom());
			if(newTurnForm.getCreateDate() != null) newTurnForm.setHandleEndDate(oldTurnForm.getHandleEndDate());
			if(newTurnForm.getHandleEndDate() != null) newTurnForm.setHandleResult(oldTurnForm.getHandleResult());
			if(newTurnForm.getHandleResult() != null) newTurnForm.setHandleStartDate(oldTurnForm.getHandleStartDate());
			newTurnForm.setLedgerId(newLedger.getId());
			newTurnForm.setLedgerSerialNumber(newLedger.getSerialNumber());
			newTurnForm.setLedgerType((long)newLedger.getLedgerType());
			if(newTurnForm.getManager() != null) newTurnForm.setManager(oldTurnForm.getManager());
			if(newTurnForm.getMobile() != null) newTurnForm.setMobile(oldTurnForm.getMobile());
			if(newTurnForm.getName() != null) newTurnForm.setName(oldTurnForm.getName());
			if(newTurnForm.getOpinion() != null) newTurnForm.setOpinion(oldTurnForm.getOpinion());
			if(newTurnForm.getOrder() != null) newTurnForm.setOrder(oldTurnForm.getOrder());
			if(newTurnForm.getReason() != null) newTurnForm.setReason(oldTurnForm.getReason());
			if(newTurnForm.getReceiveDate() != null) newTurnForm.setReceiveDate(oldTurnForm.getReceiveDate());
			if(newTurnForm.getSerialNumber() != null) newTurnForm.setSerialNumber(oldTurnForm.getSerialNumber());
			if(newTurnForm.getSortField() != null) newTurnForm.setSortField(oldTurnForm.getSortField());
			newTurnForm.setStepId(newStep.getId());
			if(newTurnForm.getSubOpinion() != null) newTurnForm.setSubOpinion(oldTurnForm.getSubOpinion());
			if(newTurnForm.getTargetOrg() != null) newTurnForm.setTargetOrg(oldTurnForm.getTargetOrg());
			if(newTurnForm.getTurnDate() != null) newTurnForm.setTurnDate(oldTurnForm.getTurnDate());
//			newTurnForm.setUpdateDate(CalendarUtil.now());
			newTurnForm.setUpdateDate(initTurnTime());
			if(newTurnForm.getUpdateUser() != null) newTurnForm.setUpdateUser(oldTurnForm.getUpdateUser());
			turnFormService.addTurnForm(newTurnForm);
		}
	}
	
	private void turnDeclareForm(ThreeRecordsIssueStep oldStep, ThreeRecordsIssueStep newStep, BaseWorking newLedger){
		DeclareForm oldDeclareForm = declareFormService.getSimpleDeclareFormByStepId(oldStep.getId());
		if(oldDeclareForm != null){
			DeclareForm newDeclareForm = new DeclareForm();
			if(oldDeclareForm.getCreateDate() != null) newDeclareForm.setCreateDate(oldDeclareForm.getCreateDate());
			if(oldDeclareForm.getCreateUser() != null) newDeclareForm.setCreateUser(oldDeclareForm.getCreateUser());
			if(oldDeclareForm.getDataFrom() != null) newDeclareForm.setDataFrom(oldDeclareForm.getDataFrom());
			if(oldDeclareForm.getDeclareDate() != null) newDeclareForm.setDeclareDate(oldDeclareForm.getDeclareDate());
			if(oldDeclareForm.getHandleContent() != null) newDeclareForm.setHandleContent(oldDeclareForm.getHandleContent());
			if(oldDeclareForm.getJointContent() != null) newDeclareForm.setJointContent(oldDeclareForm.getJointContent());
			if(oldDeclareForm.getLedgerHandleContent() != null) newDeclareForm.setLedgerHandleContent(oldDeclareForm.getLedgerHandleContent());
			newDeclareForm.setLedgerId(newLedger.getId());
			newDeclareForm.setLedgerSerialNumber(newLedger.getSerialNumber());
			newDeclareForm.setLedgerType((long)newLedger.getLedgerType());
			if(oldDeclareForm.getMobile() != null) newDeclareForm.setMobile(oldDeclareForm.getMobile());
			if(oldDeclareForm.getName() != null) newDeclareForm.setName(oldDeclareForm.getName());
			if(oldDeclareForm.getOrder() != null) newDeclareForm.setOrder(oldDeclareForm.getOrder());
			if(oldDeclareForm.getReason() != null) newDeclareForm.setReason(oldDeclareForm.getReason());
			if(oldDeclareForm.getSerialNumber() != null) newDeclareForm.setSerialNumber(oldDeclareForm.getSerialNumber());
			if(oldDeclareForm.getSortField() != null) newDeclareForm.setSortField(oldDeclareForm.getSortField());
			if(oldDeclareForm.getSourceOrg() != null) newDeclareForm.setSourceOrg(oldDeclareForm.getSourceOrg());
			newDeclareForm.setStepId(newStep.getId());
			if(oldDeclareForm.getTargetOrg() != null) newDeclareForm.setTargetOrg(oldDeclareForm.getTargetOrg());
//			newDeclareForm.setUpdateDate(CalendarUtil.now());
			newDeclareForm.setUpdateDate(initTurnTime());
			if(oldDeclareForm.getUpdateUser() != null) newDeclareForm.setUpdateUser(oldDeclareForm.getUpdateUser());
			declareFormService.addDeclareForm(newDeclareForm);
		}
	}
	
	private void turnAssignForm(ThreeRecordsIssueStep oldStep, ThreeRecordsIssueStep newStep, BaseWorking newLedger){
		AssignForm oldAssignForm = assignFormService.getSimpleAssignFormByStepId(oldStep.getId());
		if(oldAssignForm != null){
			AssignForm newAssignForm = new AssignForm();
			if(oldAssignForm.getAssignType() != null) newAssignForm.setAssignType(oldAssignForm.getAssignType());
			if(oldAssignForm.getCreateDate() != null) newAssignForm.setCreateDate(oldAssignForm.getCreateDate());
			if(oldAssignForm.getCreateUser() != null) newAssignForm.setCreateUser(oldAssignForm.getCreateUser());
			if(oldAssignForm.getDataFrom() != null) newAssignForm.setDataFrom(oldAssignForm.getDataFrom());
			if(oldAssignForm.getHandleContent() != null) newAssignForm.setHandleContent(oldAssignForm.getHandleContent());
			if(oldAssignForm.getHandleEndDate() != null) newAssignForm.setHandleEndDate(oldAssignForm.getHandleEndDate());
			if(oldAssignForm.getHandleStartDate() != null) newAssignForm.setHandleStartDate(oldAssignForm.getHandleStartDate());
			newAssignForm.setLedgerId(newLedger.getId());
			newAssignForm.setLedgerSerialNumber(newLedger.getSerialNumber());
			newAssignForm.setLedgerType((long)newLedger.getLedgerType());
			if(oldAssignForm.getOrder() != null) newAssignForm.setOrder(oldAssignForm.getOrder());
			if(oldAssignForm.getPeriods() != null) newAssignForm.setPeriods(oldAssignForm.getPeriods());
			if(oldAssignForm.getReason() != null) newAssignForm.setReason(oldAssignForm.getReason());
			if(oldAssignForm.getReceivers() != null) newAssignForm.setReceivers(oldAssignForm.getReceivers());
			if(oldAssignForm.getSerialNumber() != null) newAssignForm.setSerialNumber(oldAssignForm.getSerialNumber());
			if(oldAssignForm.getSortField() != null) newAssignForm.setSortField(oldAssignForm.getSortField());
			if(oldAssignForm.getSourceOrg() != null) newAssignForm.setSourceOrg(oldAssignForm.getSourceOrg());
			newAssignForm.setStepId(newStep.getId());
			if(oldAssignForm.getType() != null) newAssignForm.setType(oldAssignForm.getType());
//			newAssignForm.setUpdateDate(CalendarUtil.now());
			newAssignForm.setUpdateDate(initTurnTime());
			if(oldAssignForm.getUpdateUser() != null) newAssignForm.setUpdateUser(oldAssignForm.getUpdateUser());
			assignFormService.addAssignForm(newAssignForm);
		}
	}
	
	private ThreeRecordsIssueStep getNewStepByOldStepId(Long oldStepId, Map<ThreeRecordsIssueStep, ThreeRecordsIssueStep> copyStepMap){
		for(Map.Entry<ThreeRecordsIssueStep, ThreeRecordsIssueStep> entry : copyStepMap.entrySet()){
			ThreeRecordsIssueStep step = entry.getKey();
			if(step.getBackTo() != null){
				if(step.getBackTo().getId().longValue() == oldStepId.longValue()){
					return entry.getValue();
				}
			}
		}
		return null;
	}
	
	private ThreeRecordsIssueLogNew getNewLogByOldLogId(Long oldLogId, Map<ThreeRecordsIssueLogNew, ThreeRecordsIssueLogNew> logMap){
		for(Map.Entry<ThreeRecordsIssueLogNew, ThreeRecordsIssueLogNew> entry : logMap.entrySet()){
			if(entry.getKey().getId().longValue() == oldLogId.longValue()){
				return entry.getValue();
			}
		}
		return null;
	}
	
	
	private ThreeRecordsIssueStep cloneStep(ThreeRecordsIssueStep oldStep, BaseWorking newLedger){
		ThreeRecordsIssueStep newStep = new ThreeRecordsIssueStep();//backTo需要处理
		if(oldStep.getAssign() != null) newStep.setAssign(oldStep.getAssign());
		if(oldStep.getBackTo() != null) newStep.setBackTo(oldStep.getBackTo());
		if(oldStep.getCreateDate() != null) newStep.setCreateDate(oldStep.getCreateDate());
		if(oldStep.getCreateUser() != null) newStep.setCreateUser(oldStep.getCreateUser());
		if(oldStep.getDataFrom() != null) newStep.setDataFrom(oldStep.getDataFrom());
		if(oldStep.getDealType() != null) newStep.setDealType(oldStep.getDealType());
		if(oldStep.getDelayState() != null) newStep.setDelayState(oldStep.getDelayState());
		if(oldStep.getDelayWorkday() != null) newStep.setDelayWorkday(oldStep.getDelayWorkday());
		if(oldStep.getEmergencyLevel() != null) newStep.setEmergencyLevel(oldStep.getEmergencyLevel());
		if(oldStep.getEndDate() != null) newStep.setEndDate(oldStep.getEndDate());
		if(oldStep.getEntryDate() != null) newStep.setEntryDate(oldStep.getEntryDate());
		if(oldStep.getGroupId() != null) newStep.setGroupId(oldStep.getGroupId());
		if(oldStep.getIsExtended() != null) newStep.setIsExtended(oldStep.getIsExtended());
		if(oldStep.getIsFeedBack() != null) newStep.setIsFeedBack(oldStep.getIsFeedBack());
		newStep.setIsSupported(oldStep.getIsSupported());
		if(oldStep.getItemTypeId() != null) newStep.setItemTypeId(oldStep.getItemTypeId());
		if(oldStep.getLastDealDate() != null) newStep.setLastDealDate(oldStep.getLastDealDate());
		newStep.setLedgerId(newLedger.getId());
		newStep.setLedgerType(newLedger.getLedgerType());
		if(oldStep.getMultipartEntityData() != null) newStep.setMultipartEntityData(oldStep.getMultipartEntityData());
		if(oldStep.getOrder() != null) newStep.setOrder(oldStep.getOrder());
		if(oldStep.getSortField() != null) newStep.setSortField(oldStep.getSortField());
		if(oldStep.getSource() != null) newStep.setSource(oldStep.getSource());
		if(oldStep.getState() != null) newStep.setState(oldStep.getState());
		newStep.setStateCode(oldStep.getStateCode());
		if(oldStep.getSubmit() != null) newStep.setSubmit(oldStep.getSubmit());
		newStep.setSuperviseLevel(oldStep.getSuperviseLevel());
		if(oldStep.getTarget() != null) newStep.setTarget(oldStep.getTarget());
//		newStep.setUpdateDate(CalendarUtil.now());
		newStep.setUpdateDate(initTurnTime());
		if(oldStep.getUpdateUser() != null) newStep.setUpdateUser(oldStep.getUpdateUser());
		return newStep;
	}
	
	private ThreeRecordsIssueLogNew cloneThreeRecordsIssueLogNew(ThreeRecordsIssueLogNew oldLog, BaseWorking newLedger, ThreeRecordsIssueStep newStep){
		ThreeRecordsIssueLogNew newLog = new ThreeRecordsIssueLogNew();
		if(oldLog.getAddress() != null) newLog.setAddress(oldLog.getAddress());
		if(oldLog.getApplyDate() != null) newLog.setApplyDate(oldLog.getApplyDate());
		if(oldLog.getCompleteDate() != null) newLog.setCompleteDate(oldLog.getCompleteDate());
		if(oldLog.getCompleteType() != null) newLog.setCompleteType(oldLog.getCompleteType());
		if(oldLog.getContent() != null) newLog.setContent(oldLog.getContent());
		if(oldLog.getCreateDate() != null) newLog.setCreateDate(oldLog.getCreateDate());
		if(oldLog.getCreateUser() != null) newLog.setCreateUser(oldLog.getCreateUser());
		if(oldLog.getDataFrom() != null) newLog.setDataFrom(oldLog.getDataFrom());
		if(oldLog.getDealDeadline() != null) newLog.setDealDeadline(oldLog.getDealDeadline());
		if(oldLog.getDealDescription() != null) newLog.setDealDescription(oldLog.getDealDescription());
		if(oldLog.getDealOrg() != null) {
			oldLog.getDealOrg().setId(oldLog.getDealOrgId());
			newLog.setDealOrg(oldLog.getDealOrg());
		}else{
			Organization dealOrg = new Organization();
			dealOrg.setId(oldLog.getDealOrgId());
			newLog.setDealOrg(dealOrg);
		}
		if(oldLog.getDealOrgId() != null) newLog.setDealOrgId(oldLog.getDealOrgId());
		if(oldLog.getDealTime() != null) newLog.setDealTime(oldLog.getDealTime());
		if(oldLog.getDealType() != null) newLog.setDealType(oldLog.getDealType());
		if(oldLog.getDealUserName() != null) newLog.setDealUserName(oldLog.getDealUserName());
		if(oldLog.getExchangeDate() != null) newLog.setExchangeDate(oldLog.getExchangeDate());
		newLog.setIssueStep(newStep);
		if(oldLog.getJoinMen() != null) newLog.setJoinMen(oldLog.getJoinMen());
		newLog.setLedgerId(newLedger.getId());
		newLog.setLedgerType(newLedger.getLedgerType());
		if(oldLog.getMobile() != null) newLog.setMobile(oldLog.getMobile());
		if(oldLog.getOperateTime() != null) newLog.setOperateTime(oldLog.getOperateTime());
		if(oldLog.getOpinion() != null) newLog.setOpinion(oldLog.getOpinion());
		if(oldLog.getOrder() != null) newLog.setOrder(oldLog.getOrder());
		if(oldLog.getSortField() != null) newLog.setSortField(oldLog.getSortField());
		newLog.setSubmitToCommittee(oldLog.isSubmitToCommittee());
		if(oldLog.getTargeOrg() != null) newLog.setTargeOrg(oldLog.getTargeOrg());
//		newLog.setUpdateDate(CalendarUtil.now());
		newLog.setUpdateDate(initTurnTime());
		if(oldLog.getUpdateUser() != null) newLog.setUpdateUser(oldLog.getUpdateUser());
		return newLog;
	}
	
	private void turnLogFiles(ThreeRecordsIssueLogNew oldLog, ThreeRecordsIssueLogNew newLog, BaseWorking newLedger){
		List<ThreeRecordsIssueAttachFile> oldAttachFilesAboutLedgers = threeRecordsIssueDao.getAccounthasattachfilesByLogId(oldLog.getId());
		for(ThreeRecordsIssueAttachFile oldFile : oldAttachFilesAboutLedgers){
			ThreeRecordsIssueAttachFile newFile = new ThreeRecordsIssueAttachFile();
			newFile.setIssueLog(newLog);
			if(oldFile.getFileType() != null) newFile.setFileType(oldFile.getFileType());
			newFile.setLedgerType(newLedger.getLedgerType());
			if(oldFile.getId() != null) newFile.setLedgerId(newLedger.getId());
			if(oldFile.getReplyForm() != null) newFile.setReplyForm(oldFile.getReplyForm());
			Long newFileId = threeRecordsIssueDao.addIssueAttachFile(newFile);//插入新附件信息
			List<LedgerAttachFile> oldLedgerFiles = threeRecordsIssueDao.getLedgerattachfilesByModuleObjectId(String.valueOf(oldFile.getId()));
			for(LedgerAttachFile oldLedgerFile : oldLedgerFiles){
				threeRecordsIssueDao.addAttachFile(cloneLedgerAttachFile(oldLedgerFile, String.valueOf(newFileId)));//插入新的附加关联信息
			}
		}
	}
	
	
	private void turnRecordsFiles(BaseWorking oldLedger, BaseWorking newLedger){
		List<ThreeRecordsIssueAttachFile> oldAttachFilesAboutLedgers = threeRecordsIssueDao.getAccounthasattachfilesByLedgerIdAndLedgerType(oldLedger.getLedgerType(), oldLedger.getId());
		for(ThreeRecordsIssueAttachFile oldFile : oldAttachFilesAboutLedgers){
			Long newFileId = threeRecordsIssueDao.addIssueAttachFile(cloneThreeRecordsIssueAttachFile(oldFile, newLedger));//插入新附件信息
			List<LedgerAttachFile> oldLedgerFiles = threeRecordsIssueDao.getLedgerattachfilesByModuleObjectId(String.valueOf(oldFile.getId()));
			for(LedgerAttachFile oldLedgerFile : oldLedgerFiles){
				threeRecordsIssueDao.addAttachFile(cloneLedgerAttachFile(oldLedgerFile, String.valueOf(newFileId)));//插入新的附加关联信息
			}
		}
	}
	
	private LedgerAttachFile cloneLedgerAttachFile(LedgerAttachFile oldFile, String objectId){
		LedgerAttachFile newFile = new LedgerAttachFile();
		if(oldFile.getName() != null) newFile.setName(oldFile.getName());
		if(oldFile.getPhysicsFullFileName() != null) newFile.setPhysicsFullFileName(oldFile.getPhysicsFullFileName());
		if(oldFile.getModuleKey() != null) newFile.setModuleKey(oldFile.getModuleKey());
		newFile.setModuleObjectId(objectId);
		return newFile;
	}
	
	private ThreeRecordsIssueAttachFile cloneThreeRecordsIssueAttachFile(ThreeRecordsIssueAttachFile oldFile, BaseWorking newLedger){
		ThreeRecordsIssueAttachFile newFile = new ThreeRecordsIssueAttachFile();
		if(oldFile.getIssueLog() != null) newFile.setIssueLog(oldFile.getIssueLog());
		if(oldFile.getFileType() != null) newFile.setFileType(oldFile.getFileType());
		newFile.setLedgerType(newLedger.getLedgerType());
		newFile.setLedgerId(newLedger.getId());
		if(oldFile.getReplyForm() != null) newFile.setReplyForm(oldFile.getReplyForm());
		return newFile;
	}
	
	private LedgerSteadyWork cloneLedgerSteadyWork(LedgerSteadyWork oldSteadyWork, PropertyDict turnDict){
		LedgerSteadyWork newSteadyWork = new LedgerSteadyWork();
		String serialNumber = threeRecordsKeyGeneratorService.getNextKey(LedgerConstants.PRE_KEY_STEADY_WORK, oldSteadyWork.getOrganization().getId());	
		newSteadyWork.setAnonymity(oldSteadyWork.isAnonymity());
		if(oldSteadyWork.getAspirationType() != null) newSteadyWork.setAspirationType(oldSteadyWork.getAspirationType());
		if(oldSteadyWork.getAttentionLevel() != null) newSteadyWork.setAttentionLevel(oldSteadyWork.getAttentionLevel());
		if(oldSteadyWork.getBirthDay() != null) newSteadyWork.setBirthDay(oldSteadyWork.getBirthDay());
		if(oldSteadyWork.getBookingUnit() != null) newSteadyWork.setBookingUnit(oldSteadyWork.getBookingUnit());
		if(oldSteadyWork.getConvertId() != null) newSteadyWork.setConvertId(oldSteadyWork.getConvertId());
//		newSteadyWork.setCreateDate(CalendarUtil.now());
		newSteadyWork.setCreateDate(initTurnTime());
		if(oldSteadyWork.getCreateOrg() != null) newSteadyWork.setCreateOrg(oldSteadyWork.getCreateOrg());
		newSteadyWork.setCreateTableType(turnDict);
		if(oldSteadyWork.getCreateUser() != null) newSteadyWork.setCreateUser(oldSteadyWork.getCreateUser());
		if(oldSteadyWork.getCurrentOrg() != null) newSteadyWork.setCurrentOrg(oldSteadyWork.getCurrentOrg());
		if(oldSteadyWork.getCurrentStep() != null) newSteadyWork.setCurrentStep(oldSteadyWork.getCurrentStep());
		if(oldSteadyWork.getDataFrom() != null) newSteadyWork.setDataFrom(oldSteadyWork.getDataFrom());
		if(oldSteadyWork.getDisplayLevel() != null) newSteadyWork.setDisplayLevel(oldSteadyWork.getDisplayLevel());
		if(oldSteadyWork.getEaringWarn() != null) newSteadyWork.setEaringWarn(oldSteadyWork.getEaringWarn());
		if(oldSteadyWork.getGender() != null) newSteadyWork.setGender(oldSteadyWork.getGender());
		if(oldSteadyWork.getGridNo() != null) newSteadyWork.setGridNo(oldSteadyWork.getGridNo());
		if(oldSteadyWork.getHasAccountLog() != null) newSteadyWork.setHasAccountLog(oldSteadyWork.getHasAccountLog());
		if(oldSteadyWork.getSerialNumber() != null) newSteadyWork.setHistoryId(oldSteadyWork.getSerialNumber());
		if(oldSteadyWork.getHours() != null) newSteadyWork.setHours(oldSteadyWork.getHours());
		if(oldSteadyWork.getIdCardNo() != null) newSteadyWork.setIdCardNo(oldSteadyWork.getIdCardNo());
		if(oldSteadyWork.getInvolvingSteadyInfo() != null) newSteadyWork.setInvolvingSteadyInfo(oldSteadyWork.getInvolvingSteadyInfo());
		if(oldSteadyWork.getInvolvingSteadyNum() != null) newSteadyWork.setInvolvingSteadyNum(oldSteadyWork.getInvolvingSteadyNum());
		if(oldSteadyWork.getInvolvingSteadyType() != null) newSteadyWork.setInvolvingSteadyType(oldSteadyWork.getInvolvingSteadyType());
		if(oldSteadyWork.getIsCanFeedBack() != null) newSteadyWork.setIsCanFeedBack(oldSteadyWork.getIsCanFeedBack());
		if(oldSteadyWork.getIsPartyMember() != null) newSteadyWork.setIsPartyMember(oldSteadyWork.getIsPartyMember());
		if(oldSteadyWork.getLedgerId() != null) newSteadyWork.setLedgerId(oldSteadyWork.getLedgerId());
		if(oldSteadyWork.getLedgerSteadyWorkPeopleInfos() != null) newSteadyWork.setLedgerSteadyWorkPeopleInfos(oldSteadyWork.getLedgerSteadyWorkPeopleInfos());//此处需要单独转接
		newSteadyWork.setLedgerType(oldSteadyWork.getLedgerType());
		if(oldSteadyWork.getMinute() != null) newSteadyWork.setMinute(oldSteadyWork.getMinute());
		if(oldSteadyWork.getMobileNumber() != null) newSteadyWork.setMobileNumber(oldSteadyWork.getMobileNumber());
		if(oldSteadyWork.getName() != null) newSteadyWork.setName(oldSteadyWork.getName());
		if(oldSteadyWork.getOccurDate() != null) newSteadyWork.setOccurDate(oldSteadyWork.getOccurDate());
		if(oldSteadyWork.getOccurOrg() != null) newSteadyWork.setOccurOrg(oldSteadyWork.getOccurOrg());
		if(oldSteadyWork.getOccurOrgInternalCode() != null) newSteadyWork.setOccurOrgInternalCode(oldSteadyWork.getOccurOrgInternalCode());
		if(oldSteadyWork.getSerialNumber() != null) newSteadyWork.setOldHistoryId(oldSteadyWork.getSerialNumber());
		if(oldSteadyWork.getOldIssueId() != null) newSteadyWork.setOldIssueId(oldSteadyWork.getOldIssueId());
		if(oldSteadyWork.getId() != null) newSteadyWork.setOldLedgerId(oldSteadyWork.getId());
		newSteadyWork.setOldLedgerType(oldSteadyWork.getLedgerType());
		if(oldSteadyWork.getOrder() != null) newSteadyWork.setOrder(oldSteadyWork.getOrder());
		if(oldSteadyWork.getOrganization() != null) newSteadyWork.setOrganization(oldSteadyWork.getOrganization());
		if(oldSteadyWork.getOrgInternalCode() != null) newSteadyWork.setOrgInternalCode(oldSteadyWork.getOrgInternalCode());
		if(oldSteadyWork.getOtherRemark() != null) newSteadyWork.setOtherRemark(oldSteadyWork.getOtherRemark());
		if(oldSteadyWork.getPermanentAddress() != null) newSteadyWork.setPermanentAddress(oldSteadyWork.getPermanentAddress());
		if(oldSteadyWork.getPosition() != null) newSteadyWork.setPosition(oldSteadyWork.getPosition());
		if(oldSteadyWork.getRegistrant() != null) newSteadyWork.setRegistrant(oldSteadyWork.getRegistrant());
		if(oldSteadyWork.getRegistrationTime() != null) newSteadyWork.setRegistrationTime(oldSteadyWork.getRegistrationTime());
		if(oldSteadyWork.getResolveUnit() != null) newSteadyWork.setResolveUnit(oldSteadyWork.getResolveUnit());
		if(oldSteadyWork.getResolveUser() != null) newSteadyWork.setResolveUser(oldSteadyWork.getResolveUser());
		newSteadyWork.setSerialNumber(serialNumber);
		if(oldSteadyWork.getServerContractor() != null) newSteadyWork.setServerContractor(oldSteadyWork.getServerContractor());
		if(oldSteadyWork.getServerJob() != null) newSteadyWork.setServerJob(oldSteadyWork.getServerJob());
		if(oldSteadyWork.getServerTelephone() != null) newSteadyWork.setServerTelephone(oldSteadyWork.getServerTelephone());
		if(oldSteadyWork.getServerUnit() != null) newSteadyWork.setServerUnit(oldSteadyWork.getServerUnit());
		if(oldSteadyWork.getSortField() != null) newSteadyWork.setSortField(oldSteadyWork.getSortField());
		if(oldSteadyWork.getSourceKind() != null) newSteadyWork.setSourceKind(oldSteadyWork.getSourceKind());
		if(oldSteadyWork.getStatus() != null) newSteadyWork.setStatus(oldSteadyWork.getStatus());
		if(oldSteadyWork.getSteadyInfo() != null) newSteadyWork.setSteadyInfo(oldSteadyWork.getSteadyInfo());
		if(oldSteadyWork.getSteadyUnit() != null) newSteadyWork.setSteadyUnit(oldSteadyWork.getSteadyUnit());
		if(oldSteadyWork.getSteadyUser() != null) newSteadyWork.setSteadyUser(oldSteadyWork.getSteadyUser());
		if(oldSteadyWork.getSteadyWorkProblemType() != null) newSteadyWork.setSteadyWorkProblemType(oldSteadyWork.getSteadyWorkProblemType());
		if(oldSteadyWork.getSteadyWorkType() != null) newSteadyWork.setSteadyWorkType(oldSteadyWork.getSteadyWorkType());
		if(oldSteadyWork.getSteadyWorkWarnLevel() != null) newSteadyWork.setSteadyWorkWarnLevel(oldSteadyWork.getSteadyWorkWarnLevel());
		if(oldSteadyWork.getSteadyWorkWarnLevelDate() != null) newSteadyWork.setSteadyWorkWarnLevelDate(oldSteadyWork.getSteadyWorkWarnLevelDate());
		if(oldSteadyWork.getSubject() != null) newSteadyWork.setSubject(oldSteadyWork.getSubject());
//		newSteadyWork.setUpdateDate(CalendarUtil.now());
		newSteadyWork.setUpdateDate(initTurnTime());
		if(oldSteadyWork.getUpdateUser() != null) newSteadyWork.setUpdateUser(oldSteadyWork.getUpdateUser());
		return newSteadyWork;
	}
	
	private LedgerPoorPeople cloneLedgerPoorPeople(LedgerPoorPeople oldPoor, PropertyDict turnDict){
		LedgerPoorPeople newPoor = new LedgerPoorPeople();
		String serialNumber = threeRecordsKeyGeneratorService.getNextKey(LedgerConstants.PRE_KEY_POOR_PEOPLE, oldPoor.getOrganization().getId());	
		if(oldPoor.getAccountNo() != null) newPoor.setAccountNo(oldPoor.getAccountNo());
		if(oldPoor.getAnnualPerCapitaIncome() != null) newPoor.setAnnualPerCapitaIncome(oldPoor.getAnnualPerCapitaIncome());
		if(oldPoor.getAttentionDegree() != null) newPoor.setAttentionDegree(oldPoor.getAttentionDegree());
		if(oldPoor.getBirthDay() != null) newPoor.setBirthDay(oldPoor.getBirthDay());
		if(oldPoor.getBookingUnit() != null) newPoor.setBookingUnit(oldPoor.getBookingUnit());
		if(oldPoor.getBuildHouseDate() != null) newPoor.setBuildHouseDate(oldPoor.getBuildHouseDate());
		if(oldPoor.getCensusRegisterAddress() != null) newPoor.setCensusRegisterAddress(oldPoor.getCensusRegisterAddress());
		if(oldPoor.getCensusRegisterNature() != null) newPoor.setCensusRegisterNature(oldPoor.getCensusRegisterNature());
		if(oldPoor.getConvertId() != null) newPoor.setConvertId(oldPoor.getConvertId());
//		newPoor.setCreateDate(CalendarUtil.now());
		newPoor.setCreateDate(initTurnTime());
		if(oldPoor.getCreateOrg() != null) newPoor.setCreateOrg(oldPoor.getCreateOrg());
		newPoor.setCreateTableType(turnDict);
		if(oldPoor.getCreateUser() != null) newPoor.setCreateUser(oldPoor.getCreateUser());
		if(oldPoor.getCurrentOrg() != null) newPoor.setCurrentOrg(oldPoor.getCurrentOrg());
		if(oldPoor.getCurrentStep() != null) newPoor.setCurrentStep(oldPoor.getCurrentStep());
		if(oldPoor.getDataFrom() != null) newPoor.setDataFrom(oldPoor.getDataFrom());
		if(oldPoor.getDifficultyDegree() != null) newPoor.setDifficultyDegree(oldPoor.getDifficultyDegree());
		if(oldPoor.getDisplayLevel() != null) newPoor.setDisplayLevel(oldPoor.getDisplayLevel());
		if(oldPoor.getEaringWarn() != null) newPoor.setEaringWarn(oldPoor.getEaringWarn());
		if(oldPoor.getFullPinyin() != null) newPoor.setFullPinyin(oldPoor.getFullPinyin());
		if(oldPoor.getGender() != null) newPoor.setGender(oldPoor.getGender());
		if(oldPoor.getGoOutReason() != null) newPoor.setGoOutReason(oldPoor.getGoOutReason());
		if(oldPoor.getGridNo() != null) newPoor.setGridNo(oldPoor.getGridNo());
		if(oldPoor.getHasAccountLog() != null) newPoor.setHasAccountLog(oldPoor.getHasAccountLog());
		if(oldPoor.getHealthCondition() != null) newPoor.setHealthCondition(oldPoor.getHealthCondition());
		if(oldPoor.getSerialNumber() != null) newPoor.setHistoryId(oldPoor.getSerialNumber());
		if(oldPoor.getHours() != null) newPoor.setHours(oldPoor.getHours());
		if(oldPoor.getHousing() != null) newPoor.setHousing(oldPoor.getHousing());
		if(oldPoor.getHousingArea() != null) newPoor.setHousingArea(oldPoor.getHousingArea());
		if(oldPoor.getHousingStructure() != null) newPoor.setHousingStructure(oldPoor.getHousingStructure());
		if(oldPoor.getIdCardNo() != null) newPoor.setIdCardNo(oldPoor.getIdCardNo());
		if(oldPoor.getIsCanFeedBack() != null) newPoor.setIsCanFeedBack(oldPoor.getIsCanFeedBack());
		if(oldPoor.getIsPartyMember() != null) newPoor.setIsPartyMember(oldPoor.getIsPartyMember());
		if(oldPoor.getLedgerId() != null) newPoor.setLedgerId(oldPoor.getLedgerId());
		if(oldPoor.getLedgerPoorPeopleMembers() != null) newPoor.setLedgerPoorPeopleMembers(oldPoor.getLedgerPoorPeopleMembers());//此处需要单独转接
		newPoor.setLedgerType(oldPoor.getLedgerType());
		if(oldPoor.getLevelEducation() != null) newPoor.setLevelEducation(oldPoor.getLevelEducation());
		if(oldPoor.getLonelinessOld() != null) newPoor.setLonelinessOld(oldPoor.getLonelinessOld());
		if(oldPoor.getLowHousehold() != null) newPoor.setLowHousehold(oldPoor.getLowHousehold());
		if(oldPoor.getMaritalStatus() != null) newPoor.setMaritalStatus(oldPoor.getMaritalStatus());
		if(oldPoor.getMemberNo() != null) newPoor.setMemberNo(oldPoor.getMemberNo());
		if(oldPoor.getMinute() != null) newPoor.setMinute(oldPoor.getMinute());
		if(oldPoor.getMobileNumber() != null) newPoor.setMobileNumber(oldPoor.getMobileNumber());
		if(oldPoor.getName() != null) newPoor.setName(oldPoor.getName());
		if(oldPoor.getNational() != null) newPoor.setNational(oldPoor.getNational());
		if(oldPoor.getOccurDate() != null) newPoor.setOccurDate(oldPoor.getOccurDate());
		if(oldPoor.getOccurOrg() != null) newPoor.setOccurOrg(oldPoor.getOccurOrg());
		if(oldPoor.getOccurOrgInternalCode() != null) newPoor.setOccurOrgInternalCode(oldPoor.getOccurOrgInternalCode());
		if(oldPoor.getSerialNumber() != null) newPoor.setOldHistoryId(oldPoor.getSerialNumber());
		if(oldPoor.getOldIssueId() != null) newPoor.setOldIssueId(oldPoor.getOldIssueId());
		if(oldPoor.getId() != null) newPoor.setOldLedgerId(oldPoor.getId());
		newPoor.setOldLedgerType(oldPoor.getLedgerType());
		if(oldPoor.getOrder() != null) newPoor.setOrder(oldPoor.getOrder());
		if(oldPoor.getOrganization() != null) newPoor.setOrganization(oldPoor.getOrganization());
		if(oldPoor.getOrgInternalCode() != null) newPoor.setOrgInternalCode(oldPoor.getOrgInternalCode());
		if(oldPoor.getOrphan() != null) newPoor.setOrphan(oldPoor.getOrphan());
		if(oldPoor.getOtherInfo() != null) newPoor.setOtherInfo(oldPoor.getOtherInfo());
		if(oldPoor.getOwner() != null) newPoor.setOwner(oldPoor.getOwner());
		if(oldPoor.getPermanentAddress() != null) newPoor.setPermanentAddress(oldPoor.getPermanentAddress());
		if(oldPoor.getPoorSource() != null) newPoor.setPoorSource(oldPoor.getPoorSource());
		if(oldPoor.getPoorSourceName() != null) newPoor.setPoorSourceName(oldPoor.getPoorSourceName());
		if(oldPoor.getPoorType() != null) newPoor.setPoorType(oldPoor.getPoorType());
		if(oldPoor.getPoorTypeName() != null) newPoor.setPoorTypeName(oldPoor.getPoorTypeName());
		if(oldPoor.getPosition() != null) newPoor.setPosition(oldPoor.getPosition());
		if(oldPoor.getRegistrant() != null) newPoor.setRegistrant(oldPoor.getRegistrant());
		if(oldPoor.getRegistrationCardNumber() != null) newPoor.setRegistrationCardNumber(oldPoor.getRegistrationCardNumber());
		if(oldPoor.getRegistrationTime() != null) newPoor.setRegistrationTime(oldPoor.getRegistrationTime());
		if(oldPoor.getRemark() != null) newPoor.setRemark(oldPoor.getRemark());
		if(oldPoor.getRequiredType() != null) newPoor.setRequiredType(oldPoor.getRequiredType());
		if(oldPoor.getRequiredTypeName() != null) newPoor.setRequiredTypeName(oldPoor.getRequiredTypeName());
		if(oldPoor.getSecurityType() != null) newPoor.setSecurityType(oldPoor.getSecurityType());
		newPoor.setSerialNumber(serialNumber);
		if(oldPoor.getServerContractor() != null) newPoor.setServerContractor(oldPoor.getServerContractor());
		if(oldPoor.getServerJob() != null) newPoor.setServerJob(oldPoor.getServerJob());
		if(oldPoor.getServerTelephone() != null) newPoor.setServerTelephone(oldPoor.getServerTelephone());
		if(oldPoor.getServerUnit() != null) newPoor.setServerUnit(oldPoor.getServerUnit());
		if(oldPoor.getSimplePinyin() != null) newPoor.setSimplePinyin(oldPoor.getSimplePinyin());
		if(oldPoor.getSkillsSpeciality() != null) newPoor.setSkillsSpeciality(oldPoor.getSkillsSpeciality());
		if(oldPoor.getSortField() != null) newPoor.setSortField(oldPoor.getSortField());
		if(oldPoor.getSourceKind() != null) newPoor.setSourceKind(oldPoor.getSourceKind());
		if(oldPoor.getStatus() != null) newPoor.setStatus(oldPoor.getStatus());
		if(oldPoor.getSubject() != null) newPoor.setSubject(oldPoor.getSubject());
		if(oldPoor.getUnemploymentDate() != null) newPoor.setUnemploymentDate(oldPoor.getUnemploymentDate());
		if(oldPoor.getUnemploymentReason() != null) newPoor.setUnemploymentReason(oldPoor.getUnemploymentReason());
//		newPoor.setUpdateDate(CalendarUtil.now());
		newPoor.setUpdateDate(initTurnTime());
		if(oldPoor.getUpdateUser() != null) newPoor.setUpdateUser(oldPoor.getUpdateUser());
		return newPoor;
	}
	//由于数据出错，这次接转时间写死，数据更正后需修改
	private Date initTurnTime(){
		Calendar calendar=Calendar.getInstance();
//		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
//		calendar.set(Calendar.MONTH, 0);
//		calendar.set(Calendar.DAY_OF_MONTH, 1);
//		calendar.set(Calendar.HOUR, 1);
//		calendar.set(Calendar.MINUTE, 1);
//		calendar.set(Calendar.SECOND, 1);
		calendar.set(2016, 0, 1, 1, 1, 1);
		return calendar.getTime();
	}
	
	private LedgerPeopleAspirations cloneLedgerPeopleAspirations(LedgerPeopleAspirations oldPeopleAspirations, PropertyDict turnDict){
		LedgerPeopleAspirations newAspirations = new LedgerPeopleAspirations();
		String serialNumber = threeRecordsKeyGeneratorService.getNextKey(
				LedgerConstants.peopleAsparitionPreKeyMap.get(oldPeopleAspirations.getLedgerType()), oldPeopleAspirations.getOrganization().getId());	
		newAspirations.setAnonymity(oldPeopleAspirations.isAnonymity());
		if(oldPeopleAspirations.getAppealContent() != null) newAspirations.setAppealContent(oldPeopleAspirations.getAppealContent());
		if(oldPeopleAspirations.getAppealHumanType() != null) newAspirations.setAppealHumanType(oldPeopleAspirations.getAppealHumanType());
		if(oldPeopleAspirations.getBirthDay() != null) newAspirations.setBirthDay(oldPeopleAspirations.getBirthDay());
		if(oldPeopleAspirations.getBookingUnit() != null) newAspirations.setBookingUnit(oldPeopleAspirations.getBookingUnit());
		if(oldPeopleAspirations.getCategory() != null) newAspirations.setCategory(oldPeopleAspirations.getCategory());
		if(oldPeopleAspirations.getConvertId() != null) newAspirations.setConvertId(oldPeopleAspirations.getConvertId());
//		newAspirations.setCreateDate(CalendarUtil.now());
		newAspirations.setCreateDate(initTurnTime());
		if(oldPeopleAspirations.getCreateOrg() != null) newAspirations.setCreateOrg(oldPeopleAspirations.getCreateOrg());
		newAspirations.setCreateTableType(turnDict);
		if(oldPeopleAspirations.getCreateUser() != null) newAspirations.setCreateUser(oldPeopleAspirations.getCreateUser());
		if(oldPeopleAspirations.getCurrentOrg() != null) newAspirations.setCurrentOrg(oldPeopleAspirations.getCurrentOrg());
//		newAspirations.setCurrentStep(oldPeopleAspirations.getCurrentStep());
		if(oldPeopleAspirations.getDataFrom() != null) newAspirations.setDataFrom(oldPeopleAspirations.getDataFrom());
		if(oldPeopleAspirations.getDisplayLevel() != null) newAspirations.setDisplayLevel(oldPeopleAspirations.getDisplayLevel());
		if(oldPeopleAspirations.getEaringWarn() != null) newAspirations.setEaringWarn(oldPeopleAspirations.getEaringWarn());
		if(oldPeopleAspirations.getFeedbacks() != null) newAspirations.setFeedbacks(oldPeopleAspirations.getFeedbacks());
		if(oldPeopleAspirations.getGender() != null) newAspirations.setGender(oldPeopleAspirations.getGender());
		if(oldPeopleAspirations.getGridNo() != null) newAspirations.setGridNo(oldPeopleAspirations.getGridNo());
		if(oldPeopleAspirations.getHasAccountLog() != null) newAspirations.setHasAccountLog(oldPeopleAspirations.getHasAccountLog());
		if(oldPeopleAspirations.getSerialNumber() != null) newAspirations.setHistoryId(oldPeopleAspirations.getSerialNumber());
		if(oldPeopleAspirations.getHours() != null) newAspirations.setHours(oldPeopleAspirations.getHours());
		if(oldPeopleAspirations.getIdCardNo() != null) newAspirations.setIdCardNo(oldPeopleAspirations.getIdCardNo());
		if(oldPeopleAspirations.getIsCanFeedBack() != null) newAspirations.setIsCanFeedBack(oldPeopleAspirations.getIsCanFeedBack());
		if(oldPeopleAspirations.getIsPartyMember() != null) newAspirations.setIsPartyMember(oldPeopleAspirations.getIsPartyMember());
//		newAspirations.setIssueAttachFiles(oldPeopleAspirations.getIssueAttachFiles());
//		newAspirations.setIssueDealLogs(oldPeopleAspirations.getIssueDealLogs());
//		newAspirations.setIssueLogAttachFiles(oldPeopleAspirations.getIssueLogAttachFiles());
//		newAspirations.setLedgerAttachFileReturnUtil(oldPeopleAspirations.getLedgerAttachFileReturnUtil());
		if(oldPeopleAspirations.getLedgerId() != null) newAspirations.setLedgerId(oldPeopleAspirations.getLedgerId());
		newAspirations.setLedgerType(oldPeopleAspirations.getLedgerType());
		if(oldPeopleAspirations.getMinute() != null) newAspirations.setMinute(oldPeopleAspirations.getMinute());
		if(oldPeopleAspirations.getMobileNumber() != null) newAspirations.setMobileNumber(oldPeopleAspirations.getMobileNumber());
		if(oldPeopleAspirations.getName() != null) newAspirations.setName(oldPeopleAspirations.getName());
		if(oldPeopleAspirations.getOccurDate() != null) newAspirations.setOccurDate(oldPeopleAspirations.getOccurDate());
		if(oldPeopleAspirations.getOccurOrg() != null) newAspirations.setOccurOrg(oldPeopleAspirations.getOccurOrg());
		if(oldPeopleAspirations.getOccurOrgInternalCode() != null) newAspirations.setOccurOrgInternalCode(oldPeopleAspirations.getOccurOrgInternalCode());
		if(oldPeopleAspirations.getSerialNumber() != null) newAspirations.setOldHistoryId(oldPeopleAspirations.getSerialNumber());
		if(oldPeopleAspirations.getOldIssueId() != null) newAspirations.setOldIssueId(oldPeopleAspirations.getOldIssueId());
		if(oldPeopleAspirations.getLedgerId() != null) newAspirations.setOldLedgerId(oldPeopleAspirations.getLedgerId());
		newAspirations.setOldLedgerType(oldPeopleAspirations.getLedgerType());
		if(oldPeopleAspirations.getOrder() != null) newAspirations.setOrder(oldPeopleAspirations.getOrder());
		if(oldPeopleAspirations.getOrganization() != null) newAspirations.setOrganization(oldPeopleAspirations.getOrganization());
		if(oldPeopleAspirations.getOrgInternalCode() != null) newAspirations.setOrgInternalCode(oldPeopleAspirations.getOrgInternalCode());
		if(oldPeopleAspirations.getPermanentAddress() != null) newAspirations.setPermanentAddress(oldPeopleAspirations.getPermanentAddress());
		if(oldPeopleAspirations.getPosition() != null) newAspirations.setPosition(oldPeopleAspirations.getPosition());
		if(oldPeopleAspirations.getRegistrant() != null) newAspirations.setRegistrant(oldPeopleAspirations.getRegistrant());
		if(oldPeopleAspirations.getRegistrationTime() != null) newAspirations.setRegistrationTime(oldPeopleAspirations.getRegistrationTime());
//		newAspirations.setReplys(oldPeopleAspirations.getReplys());
		if(oldPeopleAspirations.getResponseGroupNo() != null) newAspirations.setResponseGroupNo(oldPeopleAspirations.getResponseGroupNo());
		newAspirations.setSerialNumber(serialNumber);
		if(oldPeopleAspirations.getServerContractor() != null) newAspirations.setServerContractor(oldPeopleAspirations.getServerContractor());
		if(oldPeopleAspirations.getServerJob() != null) newAspirations.setServerJob(oldPeopleAspirations.getServerJob());
		if(oldPeopleAspirations.getServerTelephone() != null) newAspirations.setServerTelephone(oldPeopleAspirations.getServerTelephone());
		if(oldPeopleAspirations.getServerUnit() != null) newAspirations.setServerUnit(oldPeopleAspirations.getServerUnit());
		if(oldPeopleAspirations.getSortField() != null) newAspirations.setSortField(oldPeopleAspirations.getSortField());
		if(oldPeopleAspirations.getSourceKind() != null) newAspirations.setSourceKind(oldPeopleAspirations.getSourceKind());
		if(oldPeopleAspirations.getStatus() != null) newAspirations.setStatus(oldPeopleAspirations.getStatus());
		if(oldPeopleAspirations.getSubject() != null) newAspirations.setSubject(oldPeopleAspirations.getSubject());
//		newAspirations.setUpdateDate(CalendarUtil.now());
		newAspirations.setUpdateDate(initTurnTime());
		if(oldPeopleAspirations.getUpdateUser() != null) newAspirations.setUpdateUser(oldPeopleAspirations.getUpdateUser());
		return newAspirations;
	}
	
	private void turnResearch(Long oldLedgerId, LedgerPeopleAspirations people){
		if(people.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_WATER){
			WaterResources water = peopleAspirationDao.getWaterResourcesByAspirationId(oldLedgerId);
			if(water != null)
				peopleAspirationDao.addWaterResources(cloneWater(water, people));
		}else if(people.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_AGRICULTURE){
			Agriculture agriculture = peopleAspirationDao.getAgricultureByAspirationId(oldLedgerId);
			if(agriculture != null)
				peopleAspirationDao.addAgriculture(cloneAgriculture(agriculture, people));
		}else if(people.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_EDUCATION){
			Education education = peopleAspirationDao.getEducationByAspirationId(oldLedgerId);
			if(education != null)
				peopleAspirationDao.addEducation(cloneEducation(education, people));
		}else if(people.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_ENERGY){
			Energy energy = peopleAspirationDao.getEnergyByAspirationId(oldLedgerId);
			if(energy != null)
				peopleAspirationDao.addEnergy(cloneEnergy(energy, people));
		}else if(people.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_ENVIRONMENT){
			Environment environment = peopleAspirationDao.getEnvironmentByAspirationId(oldLedgerId);
			if(environment != null)
				peopleAspirationDao.addEnvironment(cloneEnvironment(environment, people));
		}else if(people.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_LABOR){
			Labor labor = peopleAspirationDao.getLaborByAspirationId(oldLedgerId);
			if(labor != null)
				peopleAspirationDao.addLabor(cloneLabor(labor, people));
		}else if(people.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_MEDICAL){
			Medical medical = peopleAspirationDao.getMedicalByAspirationId(oldLedgerId);
			if(medical != null)
				peopleAspirationDao.addMedical(cloneMedical(medical, people));
		}else if(people.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_OTHER){
			Other other = peopleAspirationDao.getOtherByAspirationId(oldLedgerId);
			if(other != null)
				peopleAspirationDao.addOther(cloneOther(other, people));
		}else if(people.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_SCIENCE){
			Science science = peopleAspirationDao.getScienceByAspirationId(oldLedgerId);
			if(science != null)
				peopleAspirationDao.addScience(cloneScience(science, people));
		}else if(people.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_TOWN){
			Town town = peopleAspirationDao.getTownByAspirationId(oldLedgerId);
			if(town != null)
				peopleAspirationDao.addTown(cloneTown(town, people));
		}else if(people.getLedgerType() == LedgerConstants.PEOPLEASPIRATION_TRAFFIC){
			Traffic traffic = peopleAspirationDao.getTrafficByAspirationId(oldLedgerId);
			if(traffic != null)
				peopleAspirationDao.addTraffic(cloneTraffic(traffic, people));
		}
	}
	
	private WaterResources cloneWater(WaterResources oldWater, LedgerPeopleAspirations people){
		WaterResources newWater = new WaterResources();
		if(oldWater.getBeneficiaryNumber() != null) newWater.setBeneficiaryNumber(oldWater.getBeneficiaryNumber());
		if(oldWater.getBuildType() != null) newWater.setBuildType(oldWater.getBuildType());
		if(oldWater.getCentralized() != null) newWater.setCentralized(oldWater.getCentralized());
//		newWater.setCreateDate(CalendarUtil.now());
		newWater.setCreateDate(initTurnTime());
		if(oldWater.getCreateUser() != null) newWater.setCreateUser(oldWater.getCreateUser());
		if(oldWater.getDataFrom() != null) newWater.setDataFrom(oldWater.getDataFrom());
		if(oldWater.getFromAddress() != null) newWater.setFromAddress(oldWater.getFromAddress());
		if(oldWater.getGapFund() != null) newWater.setGapFund(oldWater.getGapFund());
		if(oldWater.getImpoundage() != null) newWater.setImpoundage(oldWater.getImpoundage());
		if(oldWater.getMileage() != null) newWater.setMileage(oldWater.getMileage());
		if(oldWater.getNum() != null) newWater.setNum(oldWater.getNum());
		if(oldWater.getOrder() != null) newWater.setOrder(oldWater.getOrder());
		if(oldWater.getOtherContent() != null) newWater.setOtherContent(oldWater.getOtherContent());
		newWater.setPeopleAspiration(people);
		if(oldWater.getPlannedInvestment() != null) newWater.setPlannedInvestment(oldWater.getPlannedInvestment());
		if(oldWater.getProjectCategory() != null) newWater.setProjectCategory(oldWater.getProjectCategory());
		if(oldWater.getProjectName() != null) newWater.setProjectName(oldWater.getProjectName());
		if(oldWater.getProjectSubCategory() != null) newWater.setProjectSubCategory(oldWater.getProjectSubCategory());
		if(oldWater.getScatter() != null) newWater.setScatter(oldWater.getScatter());
		if(oldWater.getSelfFund() != null) newWater.setSelfFund(oldWater.getSelfFund());
		if(oldWater.getSortField() != null) newWater.setSortField(oldWater.getSortField());
		if(oldWater.getToAddress() != null) newWater.setToAddress(oldWater.getToAddress());
//		newWater.setUpdateDate(CalendarUtil.now());
		newWater.setUpdateDate(initTurnTime());
		if(oldWater.getUpdateUser() != null) newWater.setUpdateUser(oldWater.getUpdateUser());
		return newWater;
	}
	
	private Agriculture cloneAgriculture(Agriculture oldA, LedgerPeopleAspirations people){
		Agriculture newA = new Agriculture();
		if(oldA.getBeneficiaryNumber() != null) newA.setBeneficiaryNumber(oldA.getBeneficiaryNumber());
		if(oldA.getBuildType() != null) newA.setBuildType(oldA.getBuildType());
		if(oldA.getCapacity() != null) newA.setCapacity(oldA.getCapacity());
//		newA.setCreateDate(CalendarUtil.now());
		newA.setCreateDate(initTurnTime());
		if(oldA.getCreateUser() != null) newA.setCreateUser(oldA.getCreateUser());
		if(oldA.getDataFrom() != null) newA.setDataFrom(oldA.getDataFrom());
		if(oldA.getFarmCategory() != null) newA.setFarmCategory(oldA.getFarmCategory());
		if(oldA.getFromAddress() != null) newA.setFromAddress(oldA.getFromAddress());
		if(oldA.getGapFund() != null) newA.setGapFund(oldA.getGapFund());
		if(oldA.getMachineCategory() != null) newA.setMachineCategory(oldA.getMachineCategory());
		if(oldA.getNum() != null) newA.setNum(oldA.getNum());
		if(oldA.getOrder() != null) newA.setOrder(oldA.getOrder());
		if(oldA.getOtherContent() != null) newA.setOtherContent(oldA.getOtherContent());
		newA.setPeopleAspiration(people);
		if(oldA.getPlannedInvestment() != null) newA.setPlannedInvestment(oldA.getPlannedInvestment());
		if(oldA.getProjectCategory() != null) newA.setProjectCategory(oldA.getProjectCategory());
		if(oldA.getProjectName() != null) newA.setProjectName(oldA.getProjectName());
		if(oldA.getProjectSubCategory() != null) newA.setProjectSubCategory(oldA.getProjectSubCategory());
		if(oldA.getQuantities() != null) newA.setQuantities(oldA.getQuantities());
		if(oldA.getScopeNumber() != null) newA.setScopeNumber(oldA.getScopeNumber());
		if(oldA.getSelfFund() != null) newA.setSelfFund(oldA.getSelfFund());
		if(oldA.getSortField() != null) newA.setSortField(oldA.getSortField());
		if(oldA.getToAddress() != null) newA.setToAddress(oldA.getToAddress());
		if(oldA.getTrainNumber() != null) newA.setTrainNumber(oldA.getTrainNumber());
		if(oldA.getTrainPeopleNumber() != null) newA.setTrainPeopleNumber(oldA.getTrainPeopleNumber());
//		newA.setUpdateDate(CalendarUtil.now());
		newA.setUpdateDate(initTurnTime());
		if(oldA.getUpdateUser() != null) newA.setUpdateUser(oldA.getUpdateUser());
		if(oldA.getWaterYield() != null) newA.setWaterYield(oldA.getWaterYield());
		return newA;
	}
	
	private Education cloneEducation(Education oldE, LedgerPeopleAspirations people){
		Education newE = new Education();
		if(oldE.getAddressCategory() != null) newE.setAddressCategory(oldE.getAddressCategory());
		if(oldE.getBeneficiaryNumber() != null) newE.setBeneficiaryNumber(oldE.getBeneficiaryNumber());
		if(oldE.getBuildArea() != null) newE.setBuildArea(oldE.getBuildArea());
		if(oldE.getBuildCompanyName() != null) newE.setBuildCompanyName(oldE.getBuildCompanyName());
		if(oldE.getBuildType() != null) newE.setBuildType(oldE.getBuildType());
//		newE.setCreateDate(CalendarUtil.now());
		newE.setCreateDate(initTurnTime());
		if(oldE.getCreateUser() != null) newE.setCreateUser(oldE.getCreateUser());
		if(oldE.getDataFrom() != null) newE.setDataFrom(oldE.getDataFrom());
		if(oldE.getDegreeCategory() != null) newE.setDegreeCategory(oldE.getDegreeCategory());
		if(oldE.getDistanceCategory() != null) newE.setDistanceCategory(oldE.getDistanceCategory());
		if(oldE.getFromAddress() != null) newE.setFromAddress(oldE.getFromAddress());
		if(oldE.getGapFund() != null) newE.setGapFund(oldE.getGapFund());
		if(oldE.getItemCategory() != null) newE.setItemCategory(oldE.getItemCategory());
		if(oldE.getModeCategory() != null) newE.setModeCategory(oldE.getModeCategory());
		if(oldE.getOrder() != null) newE.setOrder(oldE.getOrder());
		if(oldE.getOtherContent() != null) newE.setOtherContent(oldE.getOtherContent());
		newE.setPeopleAspiration(people);
		if(oldE.getPlannedInvestment() != null) newE.setPlannedInvestment(oldE.getPlannedInvestment());
		if(oldE.getProjectCategory() != null) newE.setProjectCategory(oldE.getProjectCategory());
		if(oldE.getProjectName() != null) newE.setProjectName(oldE.getProjectName());
		if(oldE.getProjectSubCategory() != null) newE.setProjectSubCategory(oldE.getProjectSubCategory());
		if(oldE.getRoadCategory() != null) newE.setRoadCategory(oldE.getRoadCategory());
		if(oldE.getRoadConditionCategory() != null) newE.setRoadConditionCategory(oldE.getRoadConditionCategory());
		if(oldE.getSchoolName() != null) newE.setSchoolName(oldE.getSchoolName());
		if(oldE.getScopeCategory() != null) newE.setScopeCategory(oldE.getScopeCategory());
		if(oldE.getSelfFund() != null) newE.setSelfFund(oldE.getSelfFund());
		if(oldE.getSortField() != null) newE.setSortField(oldE.getSortField());
		if(oldE.getStudentName() != null) newE.setStudentName(oldE.getStudentName());
		if(oldE.getStudyDate() != null) newE.setStudyDate(oldE.getStudyDate());
		if(oldE.getToAddress() != null) newE.setToAddress(oldE.getToAddress());
//		newE.setUpdateDate(CalendarUtil.now());
		newE.setUpdateDate(initTurnTime());
		if(oldE.getUpdateUser() != null) newE.setUpdateUser(oldE.getUpdateUser());
		return newE;
	}
	
	private Energy cloneEnergy(Energy oldE, LedgerPeopleAspirations people){
		Energy newE = new Energy();
		if(oldE.getBeneficiaryNumber() != null) newE.setBeneficiaryNumber(oldE.getBeneficiaryNumber());
		if(oldE.getBuildType() != null) newE.setBuildType(oldE.getBuildType());
		if(oldE.getCapacity() != null) newE.setCapacity(oldE.getCapacity());
//		newE.setCreateDate(CalendarUtil.now());
		newE.setCreateDate(initTurnTime());
		if(oldE.getCreateUser() != null) newE.setCreateUser(oldE.getCreateUser());
		if(oldE.getDataFrom() != null) newE.setDataFrom(oldE.getDataFrom());
		if(oldE.getDepth() != null) newE.setDepth(oldE.getDepth());
		if(oldE.getEnergyNumber() != null) newE.setEnergyNumber(oldE.getEnergyNumber());
		if(oldE.getFromAddress() != null) newE.setFromAddress(oldE.getFromAddress());
		if(oldE.getGapFund() != null) newE.setGapFund(oldE.getGapFund());
		if(oldE.getLineCategory() != null) newE.setLineCategory(oldE.getLineCategory());
		if(oldE.getMileage() != null) newE.setMileage(oldE.getMileage());
		if(oldE.getOrder() != null) newE.setOrder(oldE.getOrder());
		if(oldE.getOtherContent() != null) newE.setOtherContent(oldE.getOtherContent());
		newE.setPeopleAspiration(people);
		if(oldE.getPipeLineCategory() != null) newE.setPipeLineCategory(oldE.getPipeLineCategory());
		if(oldE.getPipeMaterialCategory() != null) newE.setPipeMaterialCategory(oldE.getPipeMaterialCategory());
		if(oldE.getPlannedInvestment() != null) newE.setPlannedInvestment(oldE.getPlannedInvestment());
		if(oldE.getProjectCategory() != null) newE.setProjectCategory(oldE.getProjectCategory());
		if(oldE.getProjectName() != null) newE.setProjectName(oldE.getProjectName());
		if(oldE.getProjectSubCategory() != null) newE.setProjectSubCategory(oldE.getProjectSubCategory());
		if(oldE.getSecurityCategory() != null) newE.setSecurityCategory(oldE.getSecurityCategory());
		if(oldE.getSecurityNum() != null) newE.setSecurityNum(oldE.getSecurityNum());
		if(oldE.getSelfFund() != null) newE.setSelfFund(oldE.getSelfFund());
		if(oldE.getSortField() != null) newE.setSortField(oldE.getSortField());
		if(oldE.getToAddress() != null) newE.setToAddress(oldE.getToAddress());
		if(oldE.getUnitCategory() != null) newE.setUnitCategory(oldE.getUnitCategory());
//		newE.setUpdateDate(CalendarUtil.now());
		newE.setUpdateDate(initTurnTime());
		if(oldE.getUpdateUser() != null) newE.setUpdateUser(oldE.getUpdateUser());
		return newE;
	}
	
	private Environment cloneEnvironment(Environment oldE, LedgerPeopleAspirations people){
		Environment newE = new Environment();
		if(oldE.getBeneficiaryNumber() != null) newE.setBeneficiaryNumber(oldE.getBeneficiaryNumber());
		if(oldE.getBuildType() != null) newE.setBuildType(oldE.getBuildType());
		if(oldE.getContent() != null) newE.setContent(oldE.getContent());
//		newE.setCreateDate(CalendarUtil.now());
		newE.setCreateDate(initTurnTime());
		if(oldE.getCreateUser() != null) newE.setCreateUser(oldE.getCreateUser());
		if(oldE.getDataFrom() != null) newE.setDataFrom(oldE.getDataFrom());
		if(oldE.getFromAddress() != null) newE.setFromAddress(oldE.getFromAddress());
		if(oldE.getGapFund() != null) newE.setGapFund(oldE.getGapFund());
		if(oldE.getGovernNumber() != null) newE.setGovernNumber(oldE.getGovernNumber());
		if(oldE.getOrder() != null) newE.setOrder(oldE.getOrder());
		if(oldE.getOtherContent() != null) newE.setOtherContent(oldE.getOtherContent());
		newE.setPeopleAspiration(people);
		if(oldE.getPlannedInvestment() != null) newE.setPlannedInvestment(oldE.getPlannedInvestment());
		if(oldE.getProjectCategory() != null) newE.setProjectCategory(oldE.getProjectCategory());
		if(oldE.getProjectName() != null) newE.setProjectName(oldE.getProjectName());
		if(oldE.getProjectSubCategory() != null) newE.setProjectSubCategory(oldE.getProjectSubCategory());
		if(oldE.getSelfFund() != null) newE.setSelfFund(oldE.getSelfFund());
		if(oldE.getSortField() != null) newE.setSortField(oldE.getSortField());
		if(oldE.getToAddress() != null) newE.setToAddress(oldE.getToAddress());
		if(oldE.getUnitCategory() != null) newE.setUnitCategory(oldE.getUnitCategory());
//		newE.setUpdateDate(CalendarUtil.now());
		newE.setUpdateDate(initTurnTime());
		if(oldE.getUpdateUser() != null) newE.setUpdateUser(oldE.getUpdateUser());
		return newE;
	}
	
	private Labor cloneLabor(Labor oldL, LedgerPeopleAspirations people){
		Labor newL = new Labor();
		if(oldL.getAgeLevel() != null) newL.setAgeLevel(oldL.getAgeLevel());
		if(oldL.getCompany() != null) newL.setCompany(oldL.getCompany());
//		newL.setCreateDate(CalendarUtil.now());
		newL.setCreateDate(initTurnTime());
		if(oldL.getCreateUser() != null) newL.setCreateUser(oldL.getCreateUser());
		if(oldL.getCrippleLevel() != null) newL.setCrippleLevel(oldL.getCrippleLevel());
		if(oldL.getDataFrom() != null) newL.setDataFrom(oldL.getDataFrom());
		if(oldL.getDegree() != null) newL.setDegree(oldL.getDegree());
		if(oldL.getDignity() != null) newL.setDignity(oldL.getDignity());
		if(oldL.getFromAddress() != null) newL.setFromAddress(oldL.getFromAddress());
		if(oldL.getMoney() != null) newL.setMoney(oldL.getMoney());
		if(oldL.getOrder() != null) newL.setOrder(oldL.getOrder());
		if(oldL.getOtherContent() != null) newL.setOtherContent(oldL.getOtherContent());
		newL.setPeopleAspiration(people);
		if(oldL.getProjectCategory() != null) newL.setProjectCategory(oldL.getProjectCategory());
		if(oldL.getProjectName() != null) newL.setProjectName(oldL.getProjectName());
		if(oldL.getProjectSubContentCategory() != null) newL.setProjectSubCategory(oldL.getProjectSubContentCategory());
		if(oldL.getRelationNumber() != null) newL.setRelationNumber(oldL.getRelationNumber());
		if(oldL.getSkill() != null) newL.setSkill(oldL.getSkill());
		if(oldL.getSortField() != null) newL.setSortField(oldL.getSortField());
		if(oldL.getToAddress() != null) newL.setToAddress(oldL.getToAddress());
//		newL.setUpdateDate(CalendarUtil.now());
		newL.setUpdateDate(initTurnTime());
		if(oldL.getUpdateUser() != null) newL.setUpdateUser(oldL.getUpdateUser());
		if(oldL.getYawnAddr() != null) newL.setYawnAddr(oldL.getYawnAddr());
		if(oldL.getYawnContactor() != null) newL.setYawnContactor(oldL.getYawnContactor());
		if(oldL.getYawnMobile() != null) newL.setYawnMobile(oldL.getYawnMobile());
		return newL;
	}
	
	private Medical cloneMedical(Medical oldM, LedgerPeopleAspirations people){
		Medical newM = new Medical();
		if(oldM.getBeneficiaryNumber() != null) newM.setBeneficiaryNumber(oldM.getBeneficiaryNumber());
		if(oldM.getBuildArea() != null) newM.setBuildArea(oldM.getBuildArea());
		if(oldM.getBuildCompanyName() != null) newM.setBuildCompanyName(oldM.getBuildCompanyName());
		if(oldM.getBuildType() != null) newM.setBuildType(oldM.getBuildType());
//		newM.setCreateDate(CalendarUtil.now());
		newM.setCreateDate(initTurnTime());
		if(oldM.getCreateUser() != null) newM.setCreateUser(oldM.getCreateUser());
		if(oldM.getDataFrom() != null) newM.setDataFrom(oldM.getDataFrom());
		if(oldM.getEquipment() != null) newM.setEquipment(oldM.getEquipment());
		if(oldM.getFromAddress() != null) newM.setFromAddress(oldM.getFromAddress());
		if(oldM.getGapFund() != null) newM.setGapFund(oldM.getGapFund());
		if(oldM.getOrder() != null) newM.setOrder(oldM.getOrder());
		if(oldM.getOtherContent() != null) newM.setOtherContent(oldM.getOtherContent());
		newM.setPeopleAspiration(people);
		if(oldM.getPlannedInvestment() != null) newM.setPlannedInvestment(oldM.getPlannedInvestment());
		if(oldM.getProjectCategory() != null) newM.setProjectCategory(oldM.getProjectCategory());
		if(oldM.getProjectName() != null) newM.setProjectName(oldM.getProjectName());
		if(oldM.getProjectSubCategory() != null) newM.setProjectSubCategory(oldM.getProjectSubCategory());
		if(oldM.getSelfFund() != null) newM.setSelfFund(oldM.getSelfFund());
		if(oldM.getSortField() != null) newM.setSortField(oldM.getSortField());
		if(oldM.getToAddress() != null) newM.setToAddress(oldM.getToAddress());
//		newM.setUpdateDate(CalendarUtil.now());
		newM.setUpdateDate(initTurnTime());
		if(oldM.getUpdateUser() != null) newM.setUpdateUser(oldM.getUpdateUser());
		return newM;
	}
	
	private Other cloneOther(Other oldO, LedgerPeopleAspirations people){
		Other newO = new Other();
		if(oldO.getBeneficiaryNumber() != null) newO.setBeneficiaryNumber(oldO.getBeneficiaryNumber());
		if(oldO.getBuildType() != null) newO.setBuildType(oldO.getBuildType());
//		newO.setCreateDate(CalendarUtil.now());
		newO.setCreateDate(initTurnTime());
		if(oldO.getCreateUser() != null) newO.setCreateUser(oldO.getCreateUser());
		if(oldO.getDataFrom() != null) newO.setDataFrom(oldO.getDataFrom());
		if(oldO.getFromAddress() != null) newO.setFromAddress(oldO.getFromAddress());
		if(oldO.getGapFund() != null) newO.setGapFund(oldO.getGapFund());
		if(oldO.getOrder() != null) newO.setOrder(oldO.getOrder());
		if(oldO.getOtherBuildTypeContent() != null) newO.setOtherBuildTypeContent(oldO.getOtherBuildTypeContent());
		if(oldO.getOtherContent() != null) newO.setOtherContent(oldO.getOtherContent());
		newO.setPeopleAspiration(people);
		if(oldO.getPlannedInvestment() != null) newO.setPlannedInvestment(oldO.getPlannedInvestment());
		if(oldO.getProjectCategory() != null) newO.setProjectCategory(oldO.getProjectCategory());
		if(oldO.getProjectName() != null) newO.setProjectName(oldO.getProjectName());
		if(oldO.getProjectSubCategory() != null) newO.setProjectSubCategory(oldO.getProjectSubCategory());
		if(oldO.getScopeContent() != null) newO.setScopeContent(oldO.getScopeContent());
		if(oldO.getSelfFund() != null) newO.setSelfFund(oldO.getSelfFund());
		if(oldO.getSortField() != null) newO.setSortField(oldO.getSortField());
		if(oldO.getToAddress() != null) newO.setToAddress(oldO.getToAddress());
//		newO.setUpdateDate(CalendarUtil.now());
		newO.setUpdateDate(initTurnTime());
		if(oldO.getUpdateUser() != null) newO.setUpdateUser(oldO.getUpdateUser());
		return newO;
	}

	private Science cloneScience(Science oldS, LedgerPeopleAspirations people){
		Science newS = new Science();
		if(oldS.getBeneficiaryNumber() != null) newS.setBeneficiaryNumber(oldS.getBeneficiaryNumber());
		if(oldS.getBuildCompanyName() != null) newS.setBuildCompanyName(oldS.getBuildCompanyName());
		if(oldS.getBuildType() != null) newS.setBuildType(oldS.getBuildType());
		if(oldS.getContent() != null) newS.setContent(oldS.getContent());
		if(oldS.getContentCategory() != null) newS.setContentCategory(oldS.getContentCategory());
//		newS.setCreateDate(CalendarUtil.now());
		newS.setCreateDate(initTurnTime());
		if(oldS.getCreateUser() != null) newS.setCreateUser(oldS.getCreateUser());
		if(oldS.getDataFrom() != null) newS.setDataFrom(oldS.getDataFrom());
		if(oldS.getFromAddress() != null) newS.setFromAddress(oldS.getFromAddress());
		if(oldS.getGapFund() != null) newS.setGapFund(oldS.getGapFund());
		if(oldS.getItemName() != null) newS.setItemName(oldS.getItemName());
		if(oldS.getOrder() != null) newS.setOrder(oldS.getOrder());
		if(oldS.getOtherContent() != null) newS.setOtherContent(oldS.getOtherContent());
		newS.setPeopleAspiration(people);
		if(oldS.getPlannedInvestment() != null) newS.setPlannedInvestment(oldS.getPlannedInvestment());
		if(oldS.getProjectCategory() != null) newS.setProjectCategory(oldS.getProjectCategory());
		if(oldS.getProjectLevelCategory() != null) newS.setProjectLevelCategory(oldS.getProjectLevelCategory());
		if(oldS.getProjectName() != null) newS.setProjectName(oldS.getProjectName());
		if(oldS.getProjectSubCategory() != null) newS.setProjectSubCategory(oldS.getProjectSubCategory());
		if(oldS.getPublicizeNum() != null) newS.setPublicizeNum(oldS.getPublicizeNum());
		if(oldS.getScienceScope() != null) newS.setScienceScope(oldS.getScienceScope());
		if(oldS.getSelfFund() != null) newS.setSelfFund(oldS.getSelfFund());
		if(oldS.getSortField() != null) newS.setSortField(oldS.getSortField());
		if(oldS.getToAddress() != null) newS.setToAddress(oldS.getToAddress());
//		newS.setUpdateDate(CalendarUtil.now());
		newS.setUpdateDate(initTurnTime());
		if(oldS.getUpdateUser() != null) newS.setUpdateUser(oldS.getUpdateUser());
		return newS;
	}
	
	private Town cloneTown(Town oldT, LedgerPeopleAspirations people){
		Town newT = new Town();
		if(oldT.getArea() != null) newT.setArea(oldT.getArea());
		if(oldT.getBeneficiaryNumber() != null) newT.setBeneficiaryNumber(oldT.getBeneficiaryNumber());
		if(oldT.getBuildType() != null) newT.setBuildType(oldT.getBuildType());
//		newT.setCreateDate(CalendarUtil.now());
		newT.setCreateDate(initTurnTime());
		if(oldT.getDataFrom() != null) newT.setDataFrom(oldT.getDataFrom());
		if(oldT.getFromAddress() != null) newT.setFromAddress(oldT.getFromAddress());
		if(oldT.getGapFund() != null) newT.setGapFund(oldT.getGapFund());
		if(oldT.getMileage() != null) newT.setMileage(oldT.getMileage());
		if(oldT.getOrder() != null) newT.setOrder(oldT.getOrder());
		if(oldT.getOtherContent() != null) newT.setOtherContent(oldT.getOtherContent());
		newT.setPeopleAspiration(people);
		if(oldT.getPlannedInvestment() != null) newT.setPlannedInvestment(oldT.getPlannedInvestment());
		if(oldT.getProjectCategory() != null) newT.setProjectCategory(oldT.getProjectCategory());
		if(oldT.getProjectName() != null) newT.setProjectName(oldT.getProjectName());
		if(oldT.getProjectNumber() != null) newT.setProjectNumber(oldT.getProjectNumber());
		if(oldT.getProjectSubCategory() != null) newT.setProjectSubCategory(oldT.getProjectSubCategory());
		if(oldT.getScopeNumber() != null) newT.setScopeNumber(oldT.getScopeNumber());
		if(oldT.getSecurityType() != null) newT.setSecurityType(oldT.getSecurityType());
		if(oldT.getSelfFund() != null) newT.setSelfFund(oldT.getSelfFund());
		if(oldT.getSortField() != null) newT.setSortField(oldT.getSortField());
		if(oldT.getToAddress() != null) newT.setToAddress(oldT.getToAddress());
//		newT.setUpdateDate(CalendarUtil.now());
		newT.setUpdateDate(initTurnTime());
		if(oldT.getUpdateUser() != null) newT.setUpdateUser(oldT.getUpdateUser());
		return newT;
	}
	
	private Traffic cloneTraffic(Traffic oldT, LedgerPeopleAspirations people){
		Traffic newT = new Traffic();
		if(oldT.getBeneficiaryNumber() != null) newT.setBeneficiaryNumber(oldT.getBeneficiaryNumber());
		if(oldT.getBuildType() != null) newT.setBuildType(oldT.getBuildType());
		if(oldT.getContentCategory() != null) newT.setContentCategory(oldT.getContentCategory());
//		newT.setCreateDate(CalendarUtil.now());
		newT.setCreateDate(initTurnTime());
		if(oldT.getCreateUser() != null) newT.setCreateUser(oldT.getCreateUser());
		if(oldT.getDataFrom() != null) newT.setDataFrom(oldT.getDataFrom());
		if(oldT.getFromAddress() != null) newT.setFromAddress(oldT.getFromAddress());
		if(oldT.getGapFund() != null) newT.setGapFund(oldT.getGapFund());
		if(oldT.getImpoundage() != null) newT.setImpoundage(oldT.getImpoundage());
		if(oldT.getMileage() != null) newT.setMileage(oldT.getMileage());
		if(oldT.getOrder() != null) newT.setOrder(oldT.getOrder());
		if(oldT.getOtherContent() != null) newT.setOtherContent(oldT.getOtherContent());
		if(oldT.getPassengerBuildCategory() != null) newT.setPassengerBuildCategory(oldT.getPassengerBuildCategory());
		if(oldT.getPassengerCategory() != null) newT.setPassengerCategory(oldT.getPassengerCategory());
		if(oldT.getPassengerLevelCategory() != null) newT.setPassengerLevelCategory(oldT.getPassengerLevelCategory());
		if(oldT.getPassengerManageCategory() != null) newT.setPassengerManageCategory(oldT.getPassengerManageCategory());
		newT.setPeopleAspiration(people);
		if(oldT.getPlannedInvestment() != null) newT.setPlannedInvestment(oldT.getPlannedInvestment());
		if(oldT.getProjectCategory() != null) newT.setProjectCategory(oldT.getProjectCategory());
		if(oldT.getProjectName() != null) newT.setProjectName(oldT.getProjectName());
		if(oldT.getProjectSubCategory() != null) newT.setProjectSubCategory(oldT.getProjectSubCategory());
		if(oldT.getPublicTransportCategory() != null) newT.setPublicTransportCategory(oldT.getPublicTransportCategory());
		if(oldT.getRemark() != null) newT.setRemark(oldT.getRemark());
		if(oldT.getRoadCategory() != null) newT.setRoadCategory(oldT.getRoadCategory());
		if(oldT.getRoadSurfaceCategory() != null) newT.setRoadSurfaceCategory(oldT.getRoadSurfaceCategory());
		if(oldT.getRodeLength() != null) newT.setRodeLength(oldT.getRodeLength());
		if(oldT.getRodeWide() != null) newT.setRodeWide(oldT.getRodeWide());
		if(oldT.getSecurityCategory() != null) newT.setSecurityCategory(oldT.getSecurityCategory());
		if(oldT.getSelfFund() != null) newT.setSelfFund(oldT.getSelfFund());
		if(oldT.getSortField() != null) newT.setSortField(oldT.getSortField());
		if(oldT.getToAddress() != null) newT.setToAddress(oldT.getToAddress());
//		newT.setUpdateDate(CalendarUtil.now());
		newT.setUpdateDate(initTurnTime());
		if(oldT.getUpdateUser() != null) newT.setUpdateUser(oldT.getUpdateUser());
		if(oldT.getWide() != null) newT.setWide(oldT.getWide());
		return newT;
	}
}
