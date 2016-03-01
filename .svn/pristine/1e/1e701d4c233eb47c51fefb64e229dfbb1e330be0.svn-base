create sequence s_platformAccountSteps
increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;

 
 create sequence s_platformAccountStepGroups
 increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 
 create sequence s_platformAccountHasAttachFile
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
 
 create sequence s_platformAccountLogs
increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 
 
 create sequence S_platformAccountRelatedPeople
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table:     platformAccountStep                                         */
/*==============================================================*/
create table platformAccountSteps  (
   id                   NUMBER(10)                      not null,
   source               NUMBER(10)                      not null,
   sourceInternalCode   VARCHAR2(32)                    not null,
   target               NUMBER(10)                      not null,
   targetInternalCode   VARCHAR2(32)                    not null,
   entryOperate         VARCHAR2(100),
   entryDate            DATE,
   endDate              DATE,
   lastDealDate         DATE,
   superviseLevel       NUMBER(10)                      not null,
   backTo               NUMBER(10),
   state                VARCHAR2(300)                   not null,
   stateCode            NUMBER(10)                      not null,
   issue                NUMBER(10)                     ,
   groupid              NUMBER(10) ,
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                           not null,
   updateDate           DATE,
   FORISSUELOGID 		NUMBER(10),
   DEALSTEPINDEX 		NUMBER(10),
   RETURNTOISSUELOGID 	NUMBER(10),
   DEALTYPE NUMBER(4),
   DEALSTATE NUMBER(4),
   DEALUSERNAME 		VARCHAR2(60),
   MOBILE VARCHAR2(15),
   DEALDESCRIPTION 		VARCHAR2(300),
   LOGCOMPLETETIME 		DATE,
   FORLOGENTRYTIME 		DATE,
   CONTENT CLOB,
   assign               NUMBER(10),
   submit               NUMBER(10),
   delayWorkday 		number(10) 						default 0,
   delaystate 			number(2) 						default 0,
   isextended 			number(1) 						default 0,
   isSupported          number(1) 						default 0,
   threeRecordsTypeID  	number(10) ,
   threeRecordId  			NUMBER(10) ,
   emergencylevel 		number(10),
   targetorglevel 		NUMBER(10),
   targetorgfunctionalorgType 	NUMBER(10) 				default 0,
   isfeedBack 			number(1) default 1,
   ledgerType           NUMBER(10)  ,
   ledgerId             NUMBER(10),
   constraint pkPlatformAccountSteps primary key (id)
);
comment on table platformAccountSteps is
'三本台账步骤表';
comment on column platformAccountSteps.id is
'处理步骤id';
comment on column platformAccountSteps.source is
'该步骤来源部门id';
comment on column platformAccountSteps.SOURCEINTERNALCODE
  is '该步骤来源部门orgCode';
comment on column platformAccountSteps.target is
'该步骤的目标处理部门';
comment on column platformAccountSteps.entryDate is
'进入该处理部门的时间';
comment on column platformAccountSteps.entryOperate is
'进入该处理部门的原因';
comment on column platformAccountSteps.endDate is
'该步骤的结束时间';
comment on column platformAccountSteps.lastDealDate is
'该步骤上一次处理的时间';
comment on column platformAccountSteps.superviseLevel is
'该步骤督办状态';
comment on column platformAccountSteps.state is
'该步骤状态';
comment on column platformAccountSteps.backTo is
'该步骤如果回退的参考步骤';
comment on column platformAccountSteps.issue is
'该步骤所属事件的id';
comment on column platformAccountSteps.groupid is
'处理部门的处理步骤组id';
comment on column platformAccountSteps.threeRecordId is '台账的编号 ';
comment on column platformAccountSteps.FORISSUELOGID is '针对的日志编号';
comment on column platformAccountSteps.DEALSTEPINDEX is '处理步骤编号';
comment on column platformAccountSteps.DEALTYPE is '处理类型';
comment on column platformAccountSteps.DEALSTATE is '处理状态(是否完成)';
comment on column platformAccountSteps.DEALUSERNAME is '处理用户';
comment on column platformAccountSteps.MOBILE is '处理人手机';
comment on column platformAccountSteps.DEALDESCRIPTION is '处理描述';
comment on column platformAccountSteps.LOGCOMPLETETIME is '日志完成时间';
comment on column platformAccountSteps.FORLOGENTRYTIME is '针对日志的创建时间';
comment on column platformAccountSteps.CONTENT is '内容';
comment on column platformAccountSteps.delayWorkday is '允许延长工作日数';
comment on column platformAccountSteps.delaystate is '延期状态(0 未申请 1 审核中 2 审核通过 3 审核不通过)';
comment on column platformAccountSteps.isextended is '是否超期';
comment on column platformAccountSteps.emergencylevel is '重大紧急等级';
comment on column platformAccountSteps.targetorglevel is '目标组织机构层级';
comment on column platformAccountSteps.targetorgfunctionalorgType is '目标组织机构类型';

CREATE INDEX PLATFORM_TARGETORGLEVEL ON PLATFORMACCOUNTSTEPS (TARGETORGLEVEL,TARGETORGFUNCTIONALORGTYPE,TARGETINTERNALCODE);



/*==============================================================*/
/* Table: platformAccountStepGroups                                       */
/*==============================================================*/
create table platformAccountStepGroups  (
   id                   NUMBER(10)                      not null,
   issue                NUMBER(10) ,
   keyStep        		NUMBER(10) ,
   entyLog         		NUMBER(10) ,
   outLog               NUMBER(10) ,
   ledgerType           NUMBER(10)  ,
   ledgerId             NUMBER(10),
   constraint pkPlatformAccountStepGroups primary key (id)
);
comment on table platformAccountStepGroups is
'处理步骤组 (指事件从进入该部门到从该部门流出或结案这期间的处理步骤)';
comment on column platformAccountStepGroups.id is
'处理步骤组id';
comment on column platformAccountStepGroups.issue is
'事件ID';
comment on column platformAccountStepGroups.keyStep is
'该事件正在处理的步骤id';
comment on column platformAccountStepGroups.entyLog is
'事件进入处理部门的处理记录ID';
comment on column platformAccountStepGroups.outLog is
'事件流出处理部门的处理记录ID';
comment on column platformAccountStepGroups.ledgerType is
'台账类型';
comment on column platformAccountStepGroups.ledgerId is
'台账ID';


/*==============================================================*/
/* Table: platformAccountHasAttachFiles                                      */
/*==============================================================*/
create table platformAccountHasAttachFiles  (
   id                     NUMBER(10)                      not null,
   issueId                NUMBER(10)                      ,
   issueLogId             NUMBER(10),
   fileType               VARCHAR2(150)                   not null,
   ledgerType           NUMBER(10)  ,
   ledgerId             NUMBER(10),
   constraint pkPlatformAccountFiles primary key (id)
);
comment on table platformAccountHasAttachFiles is
'台账附件关联表';
comment on column platformAccountHasAttachFiles.issueId is
'台账id';
comment on column platformAccountHasAttachFiles.issueLogId is
'台账处理记录id';
comment on column platformAccountHasAttachFiles.fileType is
'使用到该文件的对象类型（for_platformAccount  or for_platformAccountLog）';
comment on column platformAccountHasAttachFiles.ledgerId is
'台账编号';
comment on column platformAccountHasAttachFiles.ledgerType is
'台账类型';


/*==============================================================*/
/* Table: platformAccountLogs                                             */
/*==============================================================*/
create table platformAccountLogs  (
   id                   NUMBER(10)                      not null,
   dealOrgId            NUMBER(10)                      not null,
   targeOrgId           NUMBER(10),
   dealType             NUMBER(4),
   dealState            NUMBER(4),
   dealOrgInternalCode  VARCHAR2(32),
   dealUserName         VARCHAR2(60)                    not null,
   mobile               VARCHAR2(15),
   dealDescription      VARCHAR2(600),
   createUser           VARCHAR2(60)                    not null,
   updateUser           VARCHAR2(60),
   dealTime             DATE                            not null,
   dealDeadline         DATE,
   createDate           DATE                            not null,
   updateDate           DATE,
   content              CLOB,
   stepID               NUMBER(10),
   ledgerType           NUMBER(10)  ,
   ledgerId             NUMBER(10),
   completeDate   DATE,  
   completeType   NUMBER(2),
    ADDRESS   VARCHAR2(200),
   exchangeDate      DATE,
   constraint pkPlatformAccountLogs primary key (id)
);
comment on table platformAccountLogs is
'台账处理日志表';
comment on column platformAccountLogs.ledgerId is
'服务办事编号';
comment on column platformAccountLogs.ledgerType is
'台账类型';
comment on column platformAccountLogs.dealOrgId is
'处理部门编号';
comment on column platformAccountLogs.targeOrgId is
'目标部门编号';
comment on column platformAccountLogs.dealType is
'处理类型';
comment on column platformAccountLogs.dealState is
'处理状态(是否完成)';
comment on column platformAccountLogs.dealOrgInternalCode is
'处理部门内部编号';
comment on column platformAccountLogs.dealUserName is
'处理用户';
comment on column platformAccountLogs.mobile is
'处理人手机';
comment on column platformAccountLogs.dealDescription is
'处理描述';
comment on column platformAccountLogs.createUser is
'创建人';
comment on column platformAccountLogs.updateUser is
'修改人';
comment on column platformAccountLogs.dealTime is
'处理时间';
comment on column platformAccountLogs.dealDeadline is
'台账交办时 给主办部门指定的办理截止时间';
comment on column platformAccountLogs.createDate is
'创建时间';
comment on column platformAccountLogs.updateDate is
'修改时间';
comment on column platformAccountLogs.content is
'内容';
comment on column platformAccountLogs.stepid is
'处理步骤ID';
comment on column platformAccountLogs.ADDRESS is
'交流地点';
comment on column platformAccountLogs.exchangeDate is
'交流时间';

create table platformAccountRelatedPeople  (
   ID                   NUMBER(10)         not null,
   name                 VARCHAR2(60),
   telephone            VARCHAR2(15),
   fixPhone				VARCHAR2(15),
   issueId              NUMBER(10)         not null,
   ledgerId              NUMBER(10)                      not null,
   ledgerType          NUMBER(10)                    ,
   constraint PK_platformRelatedPeople primary key (ID)
);

create sequence s_topLedger
increment by 1
start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 
 
 /*==============================================================*/
/* Table: topLedger                                      */
/*==============================================================*/
create table topLedger  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10)                      not null,
   ledgerId             NUMBER(10)                      not null,
   ledgerType           NUMBER(10)                      ,
   tag          	NUMBER(2)                      	not null,
   topState      		NUMBER(1)                    	default 0,
   topDate           	DATE									,
   constraint pkTopLedger primary key (id)
);
comment on table topLedger is
'台账置顶关联表';
comment on column topLedger.ledgerId is
'台账id';
comment on column topLedger.orgId is
'置顶网格id';
comment on column topLedger.tag is
'台账标签(待办/已办/已办结/宣传案例/历史遗留)';
comment on column topLedger.topState is
'置顶状态(0:未置顶 1:置顶)';
comment on column topLedger.topDate is
'置顶时间';







create sequence s_ledgerPeopleAspirations
 increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 
 /*==============================================================*/
/* Table: ledgerledgerPeopleAspirations   民生诉求表	                        */
/*==============================================================*/
 create table ledgerPeopleAspirations(
 	id					NUMBER(10)                      not null,
 	orgId				NUMBER(10)                      not null,
 	orgInternalCode 	VARCHAR2(32)                    not null,
 	serialNumber		VARCHAR2(30)                    not null,
 	name				VARCHAR2(60)					,
 	idCardNo			VARCHAR2(60)					,
 	mobileNumber        VARCHAR2(15),
 	gender              NUMBER(10),
 	birthDay            DATE,
 	permanentAddress	VARCHAR2(150),
 	responseGroupNo		NUMBER(10),
 	isAnonymity		NUMBER(1)                     	default 0,
 	isPartyMember		NUMBER(1)                     	default 0,
 	position			NUMBER(10),
 	appealHumanType		NUMBER(10),
 	appealContent		CLOB							not null,
  	serverContractor 	VARCHAR2(60),
 	serverTelephone		VARCHAR2(30),
 	serverJob			VARCHAR2(60),
 	serverUnit			VARCHAR2(150),
 	createUser          VARCHAR2(60) 					not null,
	updateUser          VARCHAR2(60),
	createDate          DATE         					not null,
	updateDate          DATE,
	createTableType		NUMBER(10),
	occurOrgId			NUMBER(10)						,
	occurOrgInternalCode VARCHAR2(32)                  ,
	gridNo				VARCHAR2(60),
	bookingUnit 		VARCHAR2(60),
	registrant			VARCHAR2(32)                  ,
	registrationTime	DATE,
	sourceKind           NUMBER(10),
	subject              VARCHAR2(60),
	status           NUMBER(10),
	hours              VARCHAR2(2),
	minute              VARCHAR2(2),
	occurDate    DATE,
	ledgerType           NUMBER(10),
	oldIssueId           VARCHAR2(20),
	HistoryId           VARCHAR2(20),
	constraint pkLedgerPeopleAspirations primary key (ID)
 );
 -- Add comments to the table 
comment on table ledgerPeopleAspirations
  is '民生诉求表';
-- Add comments to the columns 
comment on column ledgerPeopleAspirations.id
  is '民生诉求id'; 
comment on column ledgerPeopleAspirations.orgId
  is '所属网格'; 
comment on column ledgerPeopleAspirations.orgInternalCode
  is '所属网格编号'; 
comment on column ledgerPeopleAspirations.serialNumber
  is '编号';
comment on column ledgerPeopleAspirations.name
  is '姓名';
comment on column ledgerPeopleAspirations.idCardNo
  is '身份证';   
comment on column ledgerPeopleAspirations.mobileNumber
  is '联系电话';     
comment on column ledgerPeopleAspirations.gender
  is '性别';  
comment on column ledgerPeopleAspirations.birthDay
  is '出生年月'; 
comment on column ledgerPeopleAspirations.permanentAddress
  is '常住地址';   
comment on column ledgerPeopleAspirations.responseGroupNo
  is '反应群体人数';     
comment on column ledgerPeopleAspirations.isPartyMember
  is '是否党员，0否 1是';
comment on column ledgerPeopleAspirations.position
  is '职业或身份'; 
comment on column ledgerPeopleAspirations.appealHumanType
  is '诉求人类别'; 
comment on column ledgerPeopleAspirations.appealContent
  is '主要愿望或诉求';  
comment on column ledgerPeopleAspirations.serverContractor
  is '服务联系人';
comment on column ledgerPeopleAspirations.serverTelephone
  is '服务联系电话';  
comment on column ledgerPeopleAspirations.serverJob
  is '服务职务';  
comment on column ledgerPeopleAspirations.serverUnit
  is '服务联系人单位'; 
comment on column ledgerPeopleAspirations.createUser
  is '创建人';
comment on column ledgerPeopleAspirations.updateUser
  is '修改人';
comment on column ledgerPeopleAspirations.createDate
  is '创建时间';
comment on column ledgerPeopleAspirations.updateDate
  is '修改时间';
comment on column ledgerPeopleAspirations.createTableType
  is '建表类型';
comment on column ledgerPeopleAspirations.occurOrgId
  is '发生网格id';  
comment on column ledgerPeopleAspirations.occurOrgInternalCode
  is '发生网格编号';
comment on column ledgerPeopleAspirations.gridNo
  is '网格号'; 
comment on column ledgerPeopleAspirations.bookingUnit
  is '登记单位'; 
comment on column ledgerPeopleAspirations.registrant
  is '登记人'; 
comment on column ledgerPeopleAspirations.registrationTime
  is '登记时间'; 
comment on column ledgerPeopleAspirations.oldIssueId
  is '原事件编号';   
  



create sequence s_ledgerPoorPeople
 increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;

create table ledgerPoorPeople  ( 
   id                   NUMBER(10)                not null,
   poorSource           NUMBER(10),
   islowhousehold       NUMBER(1)                 default 0,
   simplePinyin         VARCHAR2(10),
   fullPinyin           VARCHAR2(30),
   createUser           VARCHAR2(32)              not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                      not null,
   updateDate           DATE,
   isOwner             NUMBER(1)                  default 0,
   memberNo             NUMBER(10),
   accountNo            VARCHAR2(16),
   poorType             NUMBER(10),
   requiredType         NUMBER(10),
   securityType         NUMBER(10),
   difficultyDegree     VARCHAR2(32),
   attentionDegree		VARCHAR2(32),
   censusRegisterAddress VARCHAR2(62),
   censusRegisterNature  VARCHAR2(62),
   national             NUMBER(10),
   levelEducation 		NUMBER(10),
   maritalStatus		NUMBER(10),
   healthCondition		VARCHAR2(32),
   annualPerCapitaIncome VARCHAR2(32),
   goOutReason			VARCHAR2(32),
   isOrphan				NUMBER(1),
   isLonelinessOld		NUMBER(1),
   skillsSpeciality		VARCHAR2(32),
   isHousing			NUMBER(1),	
   housingStructure		VARCHAR2(32),
   housingArea			NUMBER(10),
   buildHouseDate       DATE,
   unemploymentDate     DATE,
   unemploymentReason   VARCHAR2(32),
   registrationCardNumber VARCHAR2(32),
   otherInfo			VARCHAR2(32),
   
   orgId                NUMBER(10)                not null,
   orgInternalCode      VARCHAR2(32)              not null,
   name                 VARCHAR2(60)              not null,
   idCardNo             VARCHAR2(18),
   gender               NUMBER(10)                not null,
   mobileNumber            VARCHAR2(15),
   birthday             DATE,
   isPartyMember      NUMBER(1)                   default 0,
   permanentAddress     VARCHAR2(150),
   POSITION    NUMBER(10)                         not null,
   serverContractor  VARCHAR2(60),
   serverTelephone    VARCHAR2(60),
   serverJob      VARCHAR2(60),
   serverUnit      VARCHAR2(60),
   SERIALNUMBER     VARCHAR2(30)         		  not null,
   createTableType      NUMBER(10), 
   occurOrg       NUMBER(10)                     , 
   occurOrgInternalCode  VARCHAR2(32)             ,
   gridNo        VARCHAR2(60),
    bookingUnit      VARCHAR2(60),
    registrant      VARCHAR2(60),
    registrationTime  DATE,
    hasAccountLog    NUMBER(1)                    default 0,
    displayLevel    VARCHAR2(60),
    earingWarn      NUMBER(10),
    ledgerType      NUMBER(10),
    ledgerId      NUMBER(10),
    createOrg      NUMBER(10),
    status        NUMBER(10),
    currentStep      NUMBER(10),
    currentOrg      NUMBER(10),
    hours        VARCHAR2(40),
    minute        VARCHAR2(40),
    occurDate      DATE,
    subject        VARCHAR2(40),
    sourceKind      NUMBER(10),
    oldIssueId           VARCHAR2(20),
   constraint pkledgerPoorPeople primary key (id)
);

  comment on table ledgerPoorPeople
  is '困难群众';
  comment on column ledgerPoorPeople.poorSource
  is '困难原因 (小)'; 
  comment on column ledgerPoorPeople.isLowHousehold
  is '是否低保户'; 
  comment on column ledgerPoorPeople.requiredType
  is '需求类型(具体需求)'; 
  comment on column ledgerPoorPeople.poorType
  is '困难类型(大)';
  comment on column ledgerPoorPeople.securityType
  is '保障类型';
  comment on column ledgerPoorPeople.simplePinyin
  is '简拼';
  comment on column ledgerPoorPeople.fullPinyin
  is '全拼';
  comment on column ledgerPoorPeople.memberNo
  is '家庭人口';
  comment on column ledgerPoorPeople.accountNo
  is '户口号';
  comment on column ledgerPoorPeople.isOwner
  is '是否是户主';
  comment on column ledgerPoorPeople.difficultyDegree
  is '困难程度'; 
  comment on column ledgerPoorPeople.attentionDegree
  is '关注程度'; 
  comment on column ledgerPoorPeople.censusRegisterAddress
  is '户籍地址'; 
  comment on column ledgerPoorPeople.censusRegisterNature
  is '户籍性质'; 
  comment on column ledgerPoorPeople.national
  is '民族';
  comment on column ledgerPoorPeople.levelEducation
  is '文化程度';
  comment on column ledgerPoorPeople.maritalStatus
  is '婚姻状况';
  comment on column ledgerPoorPeople.healthCondition
  is '健康状况 ';
  comment on column ledgerPoorPeople.annualPerCapitaIncome
  is '人均年收入';
  comment on column ledgerPoorPeople.goOutReason
  is '是否外出孤老及外出原因 ';
  comment on column ledgerPoorPeople.isOrphan
  is '是否孤儿';
  comment on column ledgerPoorPeople.isLonelinessOld
  is '是否孤老';
  comment on column ledgerPoorPeople.skillsSpeciality
  is '技能特长';
  comment on column ledgerPoorPeople.isHousing
  is '有无住房';
  comment on column ledgerPoorPeople.housingStructure
  is '住房结构';
  comment on column ledgerPoorPeople.housingArea
  is '住房面积';
  comment on column ledgerPoorPeople.buildHouseDate
  is '建房年月';
  comment on column ledgerPoorPeople.unemploymentDate
  is '失业时间';
  comment on column ledgerPoorPeople.unemploymentReason
  is '失业原因';
  comment on column ledgerPoorPeople.registrationCardNumber
  is '登记证号';
  comment on column ledgerPoorPeople.otherInfo
  is '其他信息';
   comment on column ledgerPoorPeople.oldIssueId
  is '原事件编号'; 

create sequence s_ledgerPoorPeopleMember
 increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;

create table ledgerPoorPeopleMember  (
   ID                   NUMBER(10)         not null,
   name                 VARCHAR2(60),
   gender               NUMBER(10),
   telephone            VARCHAR2(15),
   fixPhone				VARCHAR2(15),
   idCardNo             VARCHAR2(18),
   securityType         NUMBER(10),
   ledgerPoorPeopleId   NUMBER(10)         not null,
   birthday				DATE,
   headHouseholdRelation  VARCHAR2(30),
   isUnemployment		NUMBER(1),
   healthCondition		VARCHAR2(30),
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                            not null,
   updateDate           DATE,
   constraint PK_ledgerPoorPeopleMember primary key (ID)
);
 comment on table ledgerPoorPeopleMember
  is '困难群众成员';
  comment on column ledgerPoorPeopleMember.name
  is '姓名'; 
  comment on column ledgerPoorPeopleMember.gender
  is '性别'; 
  comment on column ledgerPoorPeopleMember.telephone
  is '联系电话'; 
  comment on column ledgerPoorPeopleMember.fixPhone
  is '固定电话'; 
  comment on column ledgerPoorPeopleMember.idCardNo
  is '身份证号'; 
  comment on column ledgerPoorPeopleMember.securityType
  is '保障类型'; 
  comment on column ledgerPoorPeopleMember.ledgerPoorPeopleId
  is '困难群众'; 
  comment on column ledgerPoorPeopleMember.birthday
  is '出生年月'; 
  comment on column ledgerPoorPeopleMember.headHouseholdRelation
  is '与户主关系'; 
  comment on column ledgerPoorPeopleMember.isUnemployment
  is '是否失业'; 
  comment on column ledgerPoorPeopleMember.healthCondition
  is '健康状况';  

create sequence S_ledgerSteadyWork
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table ledgerSteadyWork
(
  ID               NUMBER(10) not null,
  involvingSteadyNum  NUMBER(10),
  involvingSteadyType  NUMBER(10),
  involvingSteadyInfo    VARCHAR2(200) not null,
  steadyUnit VARCHAR2(200),
  steadyUser  VARCHAR2(30),
  resolveUnit        VARCHAR2(200),
  resolveUser       VARCHAR2(30),
  aspirationType      NUMBER(10),
  steadyInfo       VARCHAR2(1000),
  attentionLevel   NUMBER(10),
  steadyWorkType	NUMBER(10),
  steadyWorkProblemType NUMBER(10),
  CREATEUSER       VARCHAR2(60) not null,
  UPDATEUSER       VARCHAR2(60),
  CREATEDATE       DATE not null,
  UPDATEDATE       DATE,
  
   orgId                NUMBER(10)                      not null,
   orgInternalCode      VARCHAR2(32)                    not null,
   name                 VARCHAR2(60)                    not null,
   idCardNo             VARCHAR2(18),
   gender               NUMBER(10)                      not null,
   mobileNumber            VARCHAR2(15),
   birthday             DATE,
   isPartyMember      NUMBER(1)                      default 0,
   permanentAddress     VARCHAR2(150),
   POSITION    NUMBER(10)                      not null,
   serverContractor  VARCHAR2(60),
   serverTelephone    VARCHAR2(60),
   serverJob      VARCHAR2(60),
   serverUnit      VARCHAR2(60),
   SERIALNUMBER     VARCHAR2(30) not null,
   createTableType      NUMBER(10), 
   OCCURORGID            NUMBER(10)  ,
   occurOrgInternalCode  VARCHAR2(32)                   ,
   gridNo        VARCHAR2(60),
    bookingUnit      VARCHAR2(60),
    registrant      VARCHAR2(60),
    registrationTime  DATE,
    hasAccountLog    NUMBER(1)                      default 0,
    displayLevel    VARCHAR2(60),
    earingWarn      NUMBER(10),
    ledgerType      NUMBER(10),
    ledgerId      NUMBER(10),
    createOrg      NUMBER(10),
    status        NUMBER(10),
    currentStep      NUMBER(10),
    currentOrg      NUMBER(10),
    hours        VARCHAR2(40),
    minute        VARCHAR2(40),
    occurDate      DATE,
    subject        VARCHAR2(40),
    sourceKind      NUMBER(10),
    oldIssueId           VARCHAR2(20),
    steadyWorkWarnLevel    NUMBER(10),
    steadyWorkWarnLevelDate   DATE,
  constraint PKLedgerSteadyWork primary key (ID)
);
-- Add comments to the table 
comment on table ledgerSteadyWork
  is '稳定工作台账表';
-- Add comments to the columns 
comment on column ledgerSteadyWork.ID
  is '稳定工作台账id';
comment on column ledgerSteadyWork.ORGID
  is '所属网格';
comment on column ledgerSteadyWork.ORGINTERNALCODE
  is '所属网格编号';
comment on column ledgerSteadyWork.OCCURORGID
  is '发生网格';
comment on column ledgerSteadyWork.OCCURORGINTERNALCODE
  is '发生网格编号';
comment on column ledgerSteadyWork.gridno
  is '网格号';
comment on column ledgerSteadyWork.SERIALNUMBER
  is '编号';
comment on column ledgerSteadyWork.involvingSteadyNum
  is '涉稳群体人数';
comment on column ledgerSteadyWork.involvingSteadyType
  is '涉稳人群类别';
comment on column ledgerSteadyWork.involvingSteadyInfo
  is '涉稳事项';
comment on column ledgerSteadyWork.steadyUnit
  is '稳控责任单位';
comment on column ledgerSteadyWork.steadyUser
  is '稳控责任人';
comment on column ledgerSteadyWork.resolveUnit
  is '化解责任部门';
comment on column ledgerSteadyWork.resolveUser
  is '化解责任人';
comment on column ledgerSteadyWork.aspirationType
  is '诉求类别';
comment on column ledgerSteadyWork.steadyInfo
  is '涉稳人员或群体稳定现状';
 
comment on column ledgerSteadyWork.attentionLevel
  is '关注程度';
comment on column ledgerSteadyWork.SERVERCONTRACTOR
  is '服务联系人';
comment on column ledgerSteadyWork.SERVERTELEPHONE
  is '服务联系电话';
comment on column ledgerSteadyWork.SERVERJOB
  is '服务职务';
comment on column ledgerSteadyWork.SERVERUNIT
  is '服务联系人单位';
comment on column ledgerSteadyWork.bookingUnit
  is '登记单位';
comment on column ledgerSteadyWork.registrant
  is '登记人';
comment on column ledgerSteadyWork.registrationTime
  is '登记时间';
comment on column ledgerSteadyWork.CREATEUSER
  is '创建人';
comment on column ledgerSteadyWork.UPDATEUSER
  is '修改人';
comment on column ledgerSteadyWork.CREATEDATE
  is '创建时间';
comment on column ledgerSteadyWork.UPDATEDATE
  is '修改时间';
comment on column ledgerSteadyWork.CREATETABLETYPE
  is '建表类型';
 comment on column ledgerSteadyWork.oldIssueId
  is '原事件编号'; 
 comment on column ledgerSteadyWork.steadyWorkWarnLevel
  is '稳定预警级别';   
comment on column ledgerSteadyWork.steadyWorkWarnLevelDate
  is '稳定预警日期';
  
  
create sequence S_LedgerSteadyWorkPeopleInfo
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table ledgerSteadyWorkPeopleInfo
(
  ID               NUMBER(10) not null,
  steadyworkid     NUMBER(10) not null,
  NAME             VARCHAR2(60),
  IDCARDNO         VARCHAR2(60),
  MOBILENUMBER     VARCHAR2(15),
  GENDER           NUMBER(10),
  BIRTHDAY         DATE,
  PERMANENTADDRESS VARCHAR2(150),
  ISPARTYMEMBER    NUMBER(1) default 0,
  POSITION         NUMBER(10),
  CREATEUSER       VARCHAR2(60) not null,
  UPDATEUSER       VARCHAR2(60),
  CREATEDATE       DATE not null,
  UPDATEDATE       DATE,
  constraint PKlswPeopleinfo primary key (ID)
);
-- Add comments to the table 
comment on table ledgerSteadyWorkPeopleInfo
  is '稳定工作台账人员表';
-- Add comments to the columns 
comment on column ledgerSteadyWorkPeopleInfo.ID
  is '稳定工作台账人员表id';
comment on column ledgerSteadyWorkPeopleInfo.NAME
  is '姓名';
comment on column ledgerSteadyWorkPeopleInfo.IDCARDNO
  is '身份证';
comment on column ledgerSteadyWorkPeopleInfo.MOBILENUMBER
  is '联系电话';
comment on column ledgerSteadyWorkPeopleInfo.GENDER
  is '性别';
comment on column ledgerSteadyWorkPeopleInfo.BIRTHDAY
  is '出生年月';
comment on column ledgerSteadyWorkPeopleInfo.PERMANENTADDRESS
  is '常住地址';
comment on column ledgerSteadyWorkPeopleInfo.ISPARTYMEMBER
  is '是否党员，0否 1是';
comment on column ledgerSteadyWorkPeopleInfo.POSITION
  is '职业或身份';

/*==============================================================*/
/* Table: ledgerAttachFiles                                      */
/*==============================================================*/
create table ledgerAttachFiles  (
   fileName             VARCHAR2(150)                   not null,
   physicsFullFileName  VARCHAR2(250)                   not null,
   moduleKey            VARCHAR2(150)                   not null,
   moduleObjectId       VARCHAR2(100)                   not null
);

comment on column attachFiles.moduleKey is
'使用到该文件的功能模块';

comment on column attachFiles.moduleObjectId is
'使用到该文件的功能模块具体对象id';

comment on column attachFiles.fileName is
'附件文件名';

comment on column attachFiles.physicsFullFileName is
'文件存放的物理路径';


create sequence S_ledgerWaterResource
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table ledgerWaterResource
(
  ID               NUMBER(10) not null,
  ledgerPeopleAspirationsId     NUMBER(10) not null,
  serialNumber                  VARCHAR2(60),
  beneficiaryNumber             NUMBER(10),
  buildType                     NUMBER(10),  
  projectName                   VARCHAR2(150),
  projectCategory               NUMBER(10),
  projectSubCategory            NUMBER(10),
  plannedInvestment             NUMBER(15,4),
  selfFund                      NUMBER(15,4),
  gapFund                       NUMBER(15,4),
  fromAddress                   VARCHAR2(150),
  toAddress                     VARCHAR2(150),
  mileage         NUMBER(15,4),
  impoundage			 NUMBER(15,4),
  scatter  		NUMBER(15,4),
 centralized      NUMBER(10),
 createUser           VARCHAR2(32)                  ,
  updateUser           VARCHAR2(32),
  createDate           DATE                           ,
  updateDate           DATE,
   otherContent     VARCHAR2(150),
    num			 NUMBER(10),
  constraint PKLedgerWaterResource primary key (ID)
);
-- Add comments to the table 
comment on table ledgerWaterResource
  is '水利资源表';
-- Add comments to the columns 
comment on column ledgerWaterResource.ID
  is '水利资源id';
comment on column ledgerWaterResource.serialNumber
  is '序号';
comment on column ledgerWaterResource.beneficiaryNumber
  is '受益人口数';
  
comment on column ledgerWaterResource.buildType
  is ' 建设性质';
comment on column ledgerWaterResource.projectCategory
  is '类别';
comment on column ledgerWaterResource.projectSubCategory
  is '子类别';
comment on column ledgerWaterResource.plannedInvestment
  is '计划投资';
comment on column ledgerWaterResource.selfFund
  is '自筹资金';
comment on column ledgerWaterResource.gapFund
  is '缺口资金';

  create sequence S_ledgerTraffic
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table ledgerTraffic
(
  ID              NUMBER(10) not null,
  ledgerPeopleAspirationsId     NUMBER(10) not null,
  serialNumber                  VARCHAR2(60),
  beneficiaryNumber             NUMBER(10),
  buildType                     NUMBER(10),  
  projectName                   VARCHAR2(150),
  projectCategory               NUMBER(10),
  roadCategory            NUMBER(10),
  roadSurfaceCategory   NUMBER(10),
  securityCategory      NUMBER(10),
  passengerCategory             NUMBER(10),
  publicTransportCategory                       NUMBER(10),
  passengerManageCategory             NUMBER(10),
  passengerBuildCategory             NUMBER(10),
  fromAddress                   VARCHAR2(150),
  toAddress                     VARCHAR2(150),
  createUser           VARCHAR2(32)                  ,
  updateUser           VARCHAR2(32),
  createDate           DATE                           ,
  updateDate           DATE,
  mileage                 NUMBER(15,4),
  wide			 NUMBER(15,4),
  rodeWide			 NUMBER(15,4),
  rodeLength        NUMBER(15,4),
  plannedInvestment             NUMBER(15,4),
  selfFund                      NUMBER(15,4),
  gapFund                       NUMBER(15,4),
   otherContent     VARCHAR2(150),
   contentCategory      NUMBER(10),
   passengerLevelCategory      NUMBER(10),
   remark                     VARCHAR2(150),
   projectSubCategory            NUMBER(10),
  constraint PKLedgerTraffic primary key (ID)
);
-- Add comments to the table 
comment on table ledgerTraffic
  is '交通资源表';
-- Add comments to the columns 
comment on column ledgerTraffic.ID
  is '交通资源id';

 comment on column ledgerTraffic.beneficiaryNumber
  is '受益人口（人）';
  comment on column ledgerTraffic.buildType
  is '建设性质';
 comment on column ledgerTraffic.projectName
  is '工程名称';
  comment on column ledgerTraffic.projectCategory
  is '项目类别';
  comment on column ledgerTraffic.roadCategory
  is '道路类别';
  comment on column ledgerTraffic.roadSurfaceCategory
  is '路面类型';
  comment on column ledgerTraffic.securityCategory
  is '安保设施类型';
  comment on column ledgerTraffic.passengerCategory
  is '班线客运';
  comment on column ledgerTraffic.publicTransportCategory
  is '城市公共交通';
  comment on column ledgerTraffic.passengerManageCategory
  is '客运站管理';
  comment on column ledgerTraffic.passengerBuildCategory
  is '客运站建设';
  comment on column ledgerTraffic.mileage
  is '里程（公里）';
     comment on column ledgerTraffic.rodeLength
  is '桥长';
  comment on column ledgerTraffic.rodeWide
  is '宽';
    comment on column ledgerTraffic.fromAddress
  is '起点';
  comment on column ledgerTraffic.toAddress
  is '讫点';
  comment on column ledgerTraffic.projectSubCategory
  is '客运类型';
  comment on column ledgerTraffic.passengerLevelCategory
  is '客运等级';


create sequence S_ledgerEnergy
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table ledgerEnergy
(
  ID              NUMBER(10) not null,
  ledgerPeopleAspirationsId     NUMBER(10) not null,
  serialNumber                  VARCHAR2(60),
  beneficiaryNumber             NUMBER(10),
  buildType                     NUMBER(10),  
  projectName                   VARCHAR2(150),
  projectCategory               NUMBER(10),
  projectSubCategory            NUMBER(10),
  energyNumber            NUMBER(15,4),
  lineCategory   NUMBER(10),
  pipeLineCategory      NUMBER(10),
  pipeMaterialCategory             NUMBER(10),
  unitCategory            NUMBER(10),
  securityCategory                       NUMBER(10),
  fromAddress                   VARCHAR2(150),
  toAddress                     VARCHAR2(150),
  createUser           VARCHAR2(32)                  ,
  updateUser           VARCHAR2(32),
  createDate           DATE                           ,
  updateDate           DATE,
  plannedInvestment             NUMBER(15,4),
  selfFund                      NUMBER(15,4),
  gapFund                       NUMBER(15,4),
  capacity                       NUMBER(15,4),
  mileage                       NUMBER(15,4),
  depth                       NUMBER(15,4),
   otherContent     VARCHAR2(150),
   securityNum  NUMBER(10),
  constraint PKledgerEnergy primary key (ID)
);
-- Add comments to the table 
comment on table ledgerEnergy
  is '能源资源表';
-- Add comments to the columns 
comment on column ledgerEnergy.ID
  is '能源资源id';

 comment on column ledgerEnergy.beneficiaryNumber
  is '受益人口（人）';
  comment on column ledgerEnergy.buildType
  is '建设性质';
  comment on column ledgerEnergy.energyNumber
  is '数量';
  comment on column ledgerEnergy.projectName
  is '工程名称';
  comment on column ledgerEnergy.projectCategory
  is '项目类别';
  comment on column ledgerEnergy.projectSubCategory
  is '项目类型';
  comment on column ledgerEnergy.lineCategory
  is '线路类型';
  comment on column ledgerEnergy.pipeLineCategory
  is '管道类型';
  comment on column ledgerEnergy.pipeMaterialCategory
  is '管道材质';
  comment on column ledgerEnergy.securityCategory
  is '安全设施类型';
    comment on column ledgerEnergy.fromAddress
  is '起点';
  comment on column ledgerEnergy.toAddress
  is '讫点';
 comment on column ledgerEnergy.capacity
  is '容积';
  comment on column ledgerEnergy.mileage
  is '长度';
   comment on column ledgerEnergy.depth
  is '填埋深度';
  
  create sequence S_ledgerScience
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table ledgerScience
(
  ID              NUMBER(10) not null,
  ledgerPeopleAspirationsId     NUMBER(10) not null,
  serialNumber                  VARCHAR2(60),
  beneficiaryNumber             NUMBER(10),
  buildType                     NUMBER(10),  
  projectName                   VARCHAR2(150),
  projectCategory               NUMBER(10),
  projectSubCategory            NUMBER(10),
  projectLevelCategory            NUMBER(10),
  scienceScope        NUMBER(10),
  plannedInvestment             NUMBER(15,4),
  selfFund                      NUMBER(15,4),
  gapFund                      	NUMBER(15,4),
  fromAddress                   VARCHAR2(150),
  toAddress                     VARCHAR2(150),
  createUser           VARCHAR2(32)                  ,
  updateUser           VARCHAR2(32),
  createDate           DATE                           ,
  updateDate           DATE,
  otherContent     VARCHAR2(150),
  contentCategory NUMBER(10), 
  publicizeNum  NUMBER(10),
  itemName     VARCHAR2(150),
  content     VARCHAR2(150),
  constraint PKLedgerScience primary key (ID)
);
-- Add comments to the table 
comment on table ledgerScience
  is '科技文体表';
-- Add comments to the columns 
comment on column ledgerScience.ID
  is '科技文体id';

 comment on column ledgerScience.beneficiaryNumber
  is '受益人口（人）';
  comment on column ledgerScience.buildType
  is '建设性质';
  comment on column ledgerScience.projectLevelCategory
  is '项目级别';
  comment on column ledgerScience.projectName
  is '工程名称';
  comment on column ledgerScience.projectCategory
  is '项目类别';
  comment on column ledgerScience.projectSubCategory
  is '建设类型';
  comment on column ledgerScience.scienceScope
  is '规模';
  comment on column ledgerScience.plannedInvestment
  is '计划投资';
  comment on column ledgerScience.selfFund
  is '自筹资金';
  comment on column ledgerScience.gapFund
  is '缺口资金';
    comment on column ledgerScience.fromAddress
  is '起点';
  comment on column ledgerScience.toAddress
  is '讫点';
  comment on column ledgerScience.publicizeNum
  is '宣传次数';
  comment on column ledgerScience.content
  is '主题内容';
  comment on column ledgerScience.itemName
  is '科技项目名称';


  
create sequence S_ledgerEducation
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table ledgerEducation
(
  ID              NUMBER(10) not null,
  ledgerPeopleAspirationsId     NUMBER(10) not null,
  serialNumber                  VARCHAR2(60),
  beneficiaryNumber             NUMBER(10),
  buildType                     NUMBER(10),  
  projectName                   VARCHAR2(150),
  projectCategory               NUMBER(10),
  projectSubCategory            NUMBER(10),
  buildCompanyName        VARCHAR2(60),
  buildArea      NUMBER(10),
  plannedInvestment             NUMBER(15,4),
  selfFund                     	NUMBER(15,4),
  gapFund                       NUMBER(15,4),
  studentName					VARCHAR2(60),
  schoolName					VARCHAR2(60),
  fromAddress                   VARCHAR2(150),
  toAddress                     VARCHAR2(150),
  createUser           VARCHAR2(32)                  ,
  updateUser           VARCHAR2(32),
  createDate           DATE                           ,
  updateDate           DATE,
  otherContent     VARCHAR2(150),
  studyDate           DATE,
  scopeCategory            NUMBER(10),
  modeCategory            NUMBER(10),   
  itemCategory            NUMBER(10),
  roadCategory            NUMBER(10), 
  roadConditionCategory            NUMBER(10),
  distanceCategory            NUMBER(10), 
  degreeCategory            NUMBER(10),
  addressCategory            NUMBER(10), 
  
  
  
  
  constraint PKledgerEducation primary key (ID)
);
-- Add comments to the table 
comment on table ledgerEducation
  is '教育资源表';
-- Add comments to the columns 
comment on column ledgerEducation.ID
  is '教育资源id';
 comment on column ledgerEducation.beneficiaryNumber
  is '受益人口（人）';
  comment on column ledgerEducation.buildType
  is '建设性质';
  comment on column ledgerEducation.buildArea
  is '建设面积';
  comment on column ledgerEducation.projectName
  is '工程名称';
  comment on column ledgerEducation.projectCategory
  is '项目类别';
  comment on column ledgerEducation.projectSubCategory
  is '建设类型';
  comment on column ledgerEducation.studentName
  is '学生姓名';
    comment on column ledgerEducation.schoolName
  is '学生就读学校';
  comment on column ledgerEducation.buildCompanyName
  is '建设单位名称';
  comment on column ledgerEducation.plannedInvestment
  is '计划投资';
  comment on column ledgerEducation.selfFund
  is '自筹资金';
  comment on column ledgerEducation.gapFund
  is '缺口资金';
    comment on column ledgerEducation.fromAddress
  is '起点';
  comment on column ledgerEducation.toAddress
  is '讫点';
  comment on column ledgerEducation.studyDate
  is '资助时间,免补时间,补助时间,拟进城入学时间';
  comment on column ledgerEducation.scopeCategory
  is '规模';
  comment on column ledgerEducation.modeCategory
  is '方式';
  comment on column ledgerEducation.itemCategory
  is '项目名称';
  comment on column ledgerEducation.roadCategory
  is '上学路途难类型';
  comment on column ledgerEducation.roadConditionCategory
  is '路况';
  comment on column ledgerEducation.distanceCategory
  is '路程';
   comment on column ledgerEducation.degreeCategory
  is '就学类型';
  comment on column ledgerEducation.addressCategory
  is '建设地类型';
  
  
  
  
  
  create sequence S_ledgerMedical
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table ledgerMedical
(
  ID              NUMBER(10) not null,
  ledgerPeopleAspirationsId     NUMBER(10) not null,
  serialNumber                  VARCHAR2(60),
  beneficiaryNumber             NUMBER(10),
  buildType                     NUMBER(10),  
  projectName                   VARCHAR2(150),
  projectCategory               NUMBER(10),
  projectSubCategory            NUMBER(10),
  buildArea            NUMBER(10),
  equipment        NUMBER(10),
  plannedInvestment             NUMBER(15,4),
  selfFund                      NUMBER(15,4),
  gapFund                       NUMBER(15,4),
  fromAddress                   VARCHAR2(150),
  toAddress                     VARCHAR2(150),
  createUser           VARCHAR2(32)                  ,
  updateUser           VARCHAR2(32),
  createDate           DATE                           ,
  updateDate           DATE,
  otherContent     VARCHAR2(150),
  constraint PKLedgerMedical primary key (ID)
);
-- Add comments to the table 
comment on table ledgerMedical
  is '医疗卫生资源表';
-- Add comments to the columns 
comment on column ledgerMedical.ID
  is '医疗卫生资源id';
 comment on column ledgerMedical.beneficiaryNumber
  is '受益人口（人）';
  comment on column ledgerMedical.buildType
  is '建设性质';
  comment on column ledgerMedical.projectName
  is '工程名称';
  comment on column ledgerMedical.projectCategory
  is '项目类别';
  comment on column ledgerMedical.projectSubCategory
  is '项目内容类型';
  comment on column ledgerMedical.buildArea
  is '建设工程量';
    comment on column ledgerMedical.equipment
  is '设备';
  comment on column ledgerMedical.plannedInvestment
  is '计划投资';
  comment on column ledgerMedical.selfFund
  is '自筹资金';
  comment on column ledgerMedical.gapFund
  is '缺口资金';
    comment on column ledgerMedical.fromAddress
  is '起点';
  comment on column ledgerMedical.toAddress
  is '讫点';

create sequence S_ledgerEnvironment
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table ledgerEnvironment
(
  ID              NUMBER(10) not null,
  ledgerPeopleAspirationsId     NUMBER(10) not null,
  serialNumber                  VARCHAR2(60),
  beneficiaryNumber             NUMBER(10),
  buildType                     NUMBER(10),  
  projectName                   VARCHAR2(150),
  projectCategory               NUMBER(10),
  projectSubCategory            NUMBER(10),
  unitCategory            NUMBER(10),
  governNumber     NUMBER(10),
  plannedInvestment             NUMBER(15,4),
  selfFund                      NUMBER(15,4),
  gapFund                       NUMBER(15,4),
  fromAddress                   VARCHAR2(150),
  toAddress                     VARCHAR2(150),
  createUser           VARCHAR2(32)                  ,
  updateUser           VARCHAR2(32),
  createDate           DATE                           ,
  updateDate           DATE,
  otherContent     VARCHAR2(150),
  content     VARCHAR2(150),
  constraint PKLedgerEnvironment primary key (ID)
);
-- Add comments to the table 
comment on table ledgerEnvironment
  is '环境保护类资源表';
-- Add comments to the columns 
comment on column ledgerEnvironment.ID
  is '环境保护类资源id';
  comment on column ledgerEnvironment.beneficiaryNumber
  is '受益人口（人）';
  comment on column ledgerEnvironment.buildType
  is '建设性质';
  comment on column ledgerEnvironment.governNumber
  is '治理数量';
  comment on column ledgerEnvironment.projectName
  is '工程名称';
  comment on column ledgerEnvironment.projectCategory
  is '项目类别';
  comment on column ledgerEnvironment.projectSubCategory
  is '治理类型';
  comment on column ledgerEnvironment.plannedInvestment
  is '计划投资';
  comment on column ledgerEnvironment.selfFund
  is '自筹资金';
  comment on column ledgerEnvironment.gapFund
  is '缺口资金';
    comment on column ledgerEnvironment.fromAddress
  is '起点';
  comment on column ledgerEnvironment.toAddress
  is '讫点';
  comment on column ledgerEnvironment.content
  is '建设规模及建设内容';




create sequence S_ledgerLabor
maxvalue 9999999999
start with 1
increment by 1
cache 20;
create table ledgerLabor
(
  ID              NUMBER(10) not null,
  ledgerPeopleAspirationsId     NUMBER(10) not null,
  serialNumber                  VARCHAR2(60),
  relationNumber             NUMBER(10),
  projectName                   VARCHAR2(150),
  projectCategory               NUMBER(10),
  projectSubCategory            NUMBER(10),
  projectSubContentCategory            NUMBER(10),
  money          NUMBER(15,4),
  company        VARCHAR2(150),
  fromAddress                   VARCHAR2(150),
  toAddress                     VARCHAR2(150),
  createUser           VARCHAR2(32)                  ,
  updateUser           VARCHAR2(32),
  createDate           DATE                           ,
  updateDate           DATE,
  otherContent     VARCHAR2(150),
  yawnAddr                 VARCHAR2(60),
  yawnContactor                 VARCHAR2(60),
  yawnMobile                 VARCHAR2(60),
  skill     VARCHAR2(150),
  dignity    NUMBER(10),
  crippleLevel    NUMBER(10),
  ageLevel    NUMBER(10),
  degree     NUMBER(10),
  constraint PKLedgerLabor primary key (ID)
);
-- Add comments to the table 
comment on table ledgerLabor
  is '劳动和社会保障类表';
-- Add comments to the columns 
comment on column ledgerLabor.ID
  is '劳动和社会保障类id';
  comment on column ledgerLabor.relationNumber
  is '涉及人数';
  comment on column ledgerLabor.projectSubContentCategory
  is '项目内容';
 comment on column ledgerLabor.projectName
  is '名称';
  comment on column ledgerLabor.projectCategory
  is '项目类别';
  comment on column ledgerLabor.projectSubCategory
  is '项目类型';
   comment on column ledgerLabor.money
  is '涉及金额（万元）';
  comment on column ledgerLabor.company
  is '工资拖欠施工单位';

  comment on column ledgerLabor.fromAddress
  is '起点';
  comment on column ledgerLabor.toAddress
  is '讫点';
  comment on column ledgerLabor.yawnAddr
  is '工资拖欠施工单位地址';
  comment on column ledgerLabor.yawnContactor
  is '工资拖欠施工单位联系人姓名';
  comment on column ledgerLabor.yawnMobile
  is '联系电话';
  comment on column ledgerLabor.crippleLevel
  is '残疾等级';
  comment on column ledgerLabor.dignity
  is '身份性质';
  comment on column ledgerLabor.skill
  is '特长';
  comment on column ledgerLabor.degree
  is '学历';
  comment on column ledgerLabor.ageLevel
  is '年龄段';
  
 


  
  create sequence S_ledgerAgriculture 
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table ledgerAgriculture
(
  ID              NUMBER(10) not null,
  ledgerPeopleAspirationsId     NUMBER(10) not null,
  serialNumber                  VARCHAR2(60),
  beneficiaryNumber             NUMBER(10),
  buildType                     NUMBER(10),  
  projectName                   VARCHAR2(150),
  projectCategory               NUMBER(10),
  projectSubCategory            NUMBER(10),
  scopeNumber     NUMBER(15,4),
  scopeType    NUMBER(10),
  plannedInvestment             NUMBER(15,4),
  selfFund                      NUMBER(15,4),
  gapFund                       NUMBER(15,4),
  fromAddress                   VARCHAR2(150),
  toAddress                     VARCHAR2(150),
  createUser           VARCHAR2(32)                  ,
  updateUser           VARCHAR2(32),
  createDate           DATE                           ,
  updateDate           DATE,
  otherContent     VARCHAR2(150),
  quantities     NUMBER(15,4),
  trainNumber     NUMBER(10),
  trainPeopleNumber     NUMBER(10),
  num     NUMBER(10),
  capacity     NUMBER(15,4),
  waterYield     NUMBER(15,4),
  machineCategory  NUMBER(10),
  farmCategory NUMBER(10),
  constraint PKLedgerAgriculture primary key (ID)
);
-- Add comments to the table 
comment on table ledgerAgriculture
  is '农业资源表';
-- Add comments to the columns 
comment on column ledgerAgriculture.ID
  is '农业资源id';
comment on column ledgerAgriculture.beneficiaryNumber
  is '受益人口（人）';
  comment on column ledgerAgriculture.buildType
  is '建设性质';

  comment on column ledgerAgriculture.projectName
  is '工程名称';
  comment on column ledgerAgriculture.projectCategory
  is '项目类别';
  comment on column ledgerAgriculture.projectSubCategory
  is '项目类型';
  comment on column ledgerAgriculture.scopeNumber
  is '数量';
  comment on column ledgerAgriculture.plannedInvestment
  is '计划投资';
  comment on column ledgerAgriculture.selfFund
  is '自筹资金';
  comment on column ledgerAgriculture.gapFund
  is '缺口资金';
    comment on column ledgerAgriculture.fromAddress
  is '起点';
  comment on column ledgerAgriculture.toAddress
  is '讫点';
   comment on column ledgerAgriculture.quantities
  is '工程量';
     comment on column ledgerAgriculture.trainNumber
  is '培训次数';
     comment on column ledgerAgriculture.trainPeopleNumber
  is '培训人数';
     comment on column ledgerAgriculture.num
  is '数量';
     comment on column ledgerAgriculture.capacity
  is '装机容量';
   comment on column ledgerAgriculture.waterYield
  is '出水量';
  comment on column ledgerAgriculture.machineCategory
  is '农机类型';
  comment on column ledgerAgriculture.farmCategory
  is '农业类型';
  
  
  



create sequence S_ledgerTown
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table ledgerTown
(
  ID              NUMBER(10) not null,
  ledgerPeopleAspirationsId     NUMBER(10) not null,
  serialNumber                  VARCHAR2(60),
  beneficiaryNumber             NUMBER(10),
  buildType                     NUMBER(10),  
  projectNumber                 VARCHAR2(60),
  projectName                   VARCHAR2(150),
  projectCategory               NUMBER(10),
  projectSubCategory            NUMBER(10),
  scopeNumber     NUMBER(10),
  area   NUMBER(10),
  mileage                 NUMBER(10),
  plannedInvestment             NUMBER(15,4),
  selfFund                      NUMBER(15,4),
  gapFund                       NUMBER(15,4),
  fromAddress                   VARCHAR2(150),
  toAddress                     VARCHAR2(150),
  createUser           VARCHAR2(32)                  ,
  updateUser           VARCHAR2(32),
  createDate           DATE                           ,
  updateDate           DATE,
  otherContent     VARCHAR2(150),
  securityType    NUMBER(10),
 
  constraint PKLedgerTown primary key (ID)
);
-- Add comments to the table 
comment on table ledgerTown
  is '城乡规划建设管理类资源表';
-- Add comments to the columns 
comment on column ledgerTown.ID
  is '城乡规划建设管理类id';
 comment on column ledgerTown.beneficiaryNumber
  is '受益人口（人）';
  comment on column ledgerTown.buildType
  is '建设性质';
  comment on column ledgerTown.projectNumber
  is '县级项目库编号';
  comment on column ledgerTown.projectName
  is '工程名称';
  comment on column ledgerTown.projectCategory
  is '项目类别';
  comment on column ledgerTown.projectSubCategory
  is '建设类型';
  comment on column ledgerTown.scopeNumber
  is '数量';
    comment on column ledgerTown.area
  is '面积(平方米)';
  comment on column ledgerTown.mileage
  is '里程(公里)';
  comment on column ledgerTown.plannedInvestment
  is '计划投资';
  comment on column ledgerTown.selfFund
  is '自筹资金';
  comment on column ledgerTown.gapFund
  is '缺口资金';
    comment on column ledgerTown.fromAddress
  is '起点';
  comment on column ledgerTown.toAddress
  is '讫点';
   comment on column ledgerTown.securityType
  is '安保设施类型';



  
create sequence S_ledgerOther 
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table ledgerOther
(
  ID              NUMBER(10) not null,
  ledgerPeopleAspirationsId     NUMBER(10) not null,
  serialNumber                  VARCHAR2(60),
  buildType                     NUMBER(10),  
  projectName                   VARCHAR2(150),
  scopeContent     VARCHAR2(150),
  otherContent     VARCHAR2(150),
  plannedInvestment             NUMBER(15,4),
  selfFund                      NUMBER(15,4),
  gapFund                       NUMBER(15,4),
  fromAddress                   VARCHAR2(150),
  toAddress                     VARCHAR2(150),
  createUser           VARCHAR2(32)                  ,
  updateUser           VARCHAR2(32),
  createDate           DATE                           ,
  updateDate           DATE,
  beneficiaryNumber             NUMBER(10),
  projectCategory             NUMBER(10),
  otherBuildTypeContent    VARCHAR2(150),
  constraint PKledgerOther primary key (ID)
);
-- Add comments to the table 
comment on table ledgerOther
  is '其他表';
-- Add comments to the columns 
comment on column ledgerOther.ID
  is '其他资源id';
 comment on column ledgerOther.buildType
  is '建设性质';
 comment on column ledgerOther.projectName
  is '工程名称';
  comment on column ledgerOther.scopeContent
  is '工程规模及内容';
  comment on column ledgerOther.otherContent
  is '其他';
  comment on column ledgerOther.plannedInvestment
  is '计划投资';
  comment on column ledgerOther.selfFund
  is '自筹资金';
  comment on column ledgerOther.gapFund
  is '缺口资金';
    comment on column ledgerOther.fromAddress
  is '起点';
  comment on column ledgerOther.toAddress
  is '讫点';
  
  create sequence S_LEDGERFEEDBACK
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table ledgerFeedBack
(
  ID              NUMBER(10) not null,
  remark                  VARCHAR2(200),
  feedBackOpinion         VARCHAR2(200),
  orgId                   NUMBER(10),
  orgInternalCode         VARCHAR2(60),
  evaluateUser            VARCHAR2(60),
  feedBackType            NUMBER(10),
  ledgerId                NUMBER(10),
   stepId                 NUMBER(10),
  ledgerType              NUMBER(10),
  createUser           VARCHAR2(32) ,
  updateUser           VARCHAR2(32),
  createDate           DATE ,
  updateDate           DATE,
  constraint PKledgerFeedBack primary key (ID)
);

create sequence S_accountKeyInfos
increment by 1
start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 
 
 /*==============================================================*/
/* Table: accountKeyInfos                                        */
/*==============================================================*/
create table accountKeyInfos  (
   id                   NUMBER(10)                      not null,
   curNum               NUMBER(10)                     default 0,
   step                 NUMBER(10)                     default 1,
   name                 VARCHAR2(60)                    not null,
   remark               VARCHAR2(200),
   constraint pkAccountKeyInfos primary key (id)
);

comment on table accountKeyInfos is
'系统主键信息';

comment on column accountKeyInfos.curNum is
'当前序列号';

comment on column accountKeyInfos.step is
'每一次增加步长';

comment on column accountKeyInfos.name is
'系统主键名称';

comment on column accountKeyInfos.remark is
'备注';

commit;