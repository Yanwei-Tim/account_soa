package com.tianque.plugin.account.state;

import java.util.List;

import com.tianque.plugin.account.domain.BaseWorking;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStepGroup;
import com.tianque.plugin.account.state.exception.ThreeRecordsIssueOperationException;
import com.tianque.plugin.account.state.exception.ThreeRecordsUnsupportOperationException;

public interface ThreeRecordsIssueState {

	final static Integer DEALING = 1; // 办理中 issues
	final static Integer CLOSED = 250;// 关闭
	final static Integer DEALOVER = 200;//
	final static Integer COMPLETE = 150;// 事件办结（待验证）
	final static Integer VERIFICATION = 300; // 事件结案，验证通过
	final static Integer GRADEDELAY = 301;// 已评分，仅仅做页面显示不做实际存储

	public final static int RED_CARD_SUPERVISE = 200;
	public final static int YELLOW_CARD_SUPERVISE = 100;
	public final static int NORMAL_SUPERVISE = 1;
	public final static int NO_SUPERVISE = -1;
	public static final int ISSUE_EXTENDED = 1;// 超期督办
	public static final int ISSUE_NOT_EXTENDED = 0;// 取消督办

	public final static int UNGIVETO_CODE = 80;
	public final static int NEWISSUE_CODE = 100;
	public final static int UNCONCEPTED_CODE = 110;// 待受理
	public final static int DEALING_CODE = 120; // 处理中
	public final static int UNREAD_CODE = 140; // 待阅读
	public final static int STEPCOMPLETE_CODE = 500; // 转到下一个流程（新增一条流程信息）前，设置的值
	public final static int PERIOD_CODE = 600;// 阶段办结
	public final static int SUBSTANCE_CODE = 700;// 实质办结
	public final static int ISSUEVERIFICATION_CODE = 800;// 待验证
	public final static int ISSUEUNVERIFICATION_CODE = 900;// 验证未通过,回退 900
	public final static int ISSUECOMPLETE_CODE = 1000; // 已验证事件（验证通过的）
	/** 备案 即完成状态 */
	public final static int BACKUPS_CODE = 1100;
	public final static int UNSUPPORT_CODE = 160; // 待协助办理
	public final static int SUPPORTING_CODE = 170; // 协助办理中

	/**
	 * 办理台账
	 * 
	 * @param baseWork
	 *            台账基本信息
	 * @param currentStep
	 *            步骤
	 * @param context
	 *            台账变量
	 * @throws ThreeRecordsUnsupportOperationException
	 * @throws ThreeRecordsIssueOperationException
	 */
	void comment(BaseWorking baseWork, ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException;

	/**
	 * 上报指挥中心
	 * 
	 * @param baseWork
	 *            台账基本信息
	 * @param currentStep
	 *            步骤
	 * @param context
	 *            台账变量
	 * @return
	 * @throws ThreeRecordsUnsupportOperationException
	 * @throws ThreeRecordsIssueOperationException
	 */
	ThreeRecordsIssueStep reportTo(BaseWorking baseWork,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException;

	/**
	 * 交办
	 * 
	 * @param baseWork
	 *            台账基本信息
	 * @param currentStep
	 *            步骤
	 * @param context
	 *            台账变量
	 * @return
	 * @throws ThreeRecordsUnsupportOperationException
	 * @throws ThreeRecordsIssueOperationException
	 */
	ThreeRecordsIssueStepGroup assign(BaseWorking baseWork,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException;

	/**
	 * 实质办结台账
	 * 
	 * @param baseWork
	 *            台账基本信息
	 * @param currentStep
	 *            步骤
	 * @param context
	 *            台账变量
	 * @return
	 * @throws ThreeRecordsUnsupportOperationException
	 * @throws ThreeRecordsIssueOperationException
	 */
	ThreeRecordsIssueStepGroup complete(BaseWorking baseWork,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException;

	/**
	 * 上报台账
	 * 
	 * @param baseWork
	 *            台账基本信息
	 * @param currentStep
	 *            步骤
	 * @param context
	 *            台账变量
	 * @return
	 * @throws ThreeRecordsUnsupportOperationException
	 * @throws ThreeRecordsIssueOperationException
	 */
	ThreeRecordsIssueStepGroup submit(BaseWorking baseWork,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException;

	/**
	 * 受理台账
	 * 
	 * @param baseWork
	 *            台账基本信息
	 * @param currentStep
	 *            步骤
	 * @param context
	 *            台账变量
	 * @throws ThreeRecordsUnsupportOperationException
	 * @throws ThreeRecordsIssueOperationException
	 */
	void concept(BaseWorking baseWork, ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException;

	/**
	 * 阅读台账
	 * 
	 * @param baseWork
	 *            台账基本信息
	 * @param currentStep
	 *            步骤
	 * @param context
	 *            台账变量
	 * @throws ThreeRecordsUnsupportOperationException
	 * @throws ThreeRecordsIssueOperationException
	 */
	void read(BaseWorking baseWork, ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException;

	/**
	 * 派单
	 * 
	 * @param baseWork
	 *            台账基本信息
	 * @param currentStep
	 *            步骤
	 * @param context
	 *            台账变量
	 * @return
	 * @throws ThreeRecordsUnsupportOperationException
	 * @throws ThreeRecordsIssueOperationException
	 */
	ThreeRecordsIssueStepGroup giveTo(BaseWorking baseWork,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException;

	/**
	 * 回退
	 * 
	 * @param baseWork
	 *            台账基本信息
	 * @param currentStep
	 *            步骤
	 * @param context
	 *            台账变量
	 * @return
	 * @throws ThreeRecordsUnsupportOperationException
	 * @throws ThreeRecordsIssueOperationException
	 */
	ThreeRecordsIssueStepGroup back(BaseWorking baseWork,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException;

	/**
	 * 获取能做的操作
	 * 
	 * @param step
	 * @param orgInfo
	 * @return
	 */
	List<ThreeRecordsIssueOperate> getCando(ThreeRecordsIssueStepInfo step,
			ThreeRecordsOrganizationInfo orgInfo);

	/**
	 * 转办
	 * 
	 * @param baseWork
	 *            台账基本信息
	 * @param currentStep
	 *            步骤
	 * @param context
	 *            台账变量
	 * @return
	 * @throws ThreeRecordsUnsupportOperationException
	 * @throws ThreeRecordsIssueOperationException
	 */
	ThreeRecordsIssueStepGroup turn(BaseWorking baseWork,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException;

	/**
	 * 辅办
	 * 
	 * @param baseWork
	 *            台账基本信息
	 * @param currentStep
	 *            步骤
	 * @param context
	 *            台账变量
	 * @throws ThreeRecordsUnsupportOperationException
	 * @throws ThreeRecordsIssueOperationException
	 */
	void support(BaseWorking baseWork, ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException;

	/**
	 * 申报
	 * 
	 * @param baseWork
	 *            台账基本信息
	 * @param currentStep
	 *            步骤
	 * @param context
	 *            台账变量
	 * @return
	 * @throws ThreeRecordsUnsupportOperationException
	 * @throws ThreeRecordsIssueOperationException
	 */
	ThreeRecordsIssueStepGroup declare(BaseWorking baseWork,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException;

	/**
	 * 阶段办结台账
	 * 
	 * @param baseWork
	 *            台账基本信息
	 * @param currentStep
	 *            步骤
	 * @param context
	 *            台账变量
	 * @return
	 * @throws ThreeRecordsUnsupportOperationException
	 * @throws ThreeRecordsIssueOperationException
	 */
	public ThreeRecordsIssueStepGroup periodComplete(BaseWorking baseWork,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException;

	/**
	 * 程序办结台账
	 * 
	 * @param baseWork
	 *            台账基本信息
	 * @param currentStep
	 *            步骤
	 * @param context
	 *            台账变量
	 * @return
	 * @throws ThreeRecordsUnsupportOperationException
	 * @throws ThreeRecordsIssueOperationException
	 */
	public ThreeRecordsIssueStepGroup programComplete(BaseWorking baseWork,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException;

	/**
	 * 添加临时工作措施
	 * 
	 * @param baseWork
	 *            台账基本信息
	 * @param currentStep
	 *            步骤
	 * @param context
	 *            台账变量
	 * @throws ThreeRecordsUnsupportOperationException
	 * @throws ThreeRecordsIssueOperationException
	 */
	public void tmpComment(BaseWorking baseWork,
			ThreeRecordsIssueStep currentStep,
			ThreeRecordsIssueOperationContext context)
			throws ThreeRecordsUnsupportOperationException,
			ThreeRecordsIssueOperationException;
}
