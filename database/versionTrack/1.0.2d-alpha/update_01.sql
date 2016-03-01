alter table platformAccountHasAttachFiles  add (replyFormId number(10));
create sequence S_accountAssignFormReceiver
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;


CREATE TABLE accountAssignFormReceiver (
    id number(10),  
    assignId number(10),
	stepId number(10),	
    targetOrg number(10),  
    name varchar2(50),  
    mobile varchar2(50),  
    createUser           VARCHAR2(32),
  	updateUser           VARCHAR2(32),
    createDate           DATE,
    updateDate           DATE,
	receiveDate           DATE,
	manager varchar2(50), 
	isManage number(1),  
	constraint PKaccountAssignFormReceiver primary key (id)
) ;
comment on table accountAssignFormReceiver  is '交办单接件人';
 comment on column accountAssignFormReceiver.id  is '交办单接件人 ID';
 comment on column accountAssignFormReceiver.assignId  is '交办单编号';
 comment on column accountAssignFormReceiver.targetOrg  is '转办部门ID';
 comment on column accountAssignFormReceiver.name  is '承办单位联系人';
 comment on column accountAssignFormReceiver.mobile  is '联系电话';
 comment on column accountAssignFormReceiver.manager  is '承办责任人';
 comment on column accountAssignFormReceiver.receiveDate  is '接件时间';
 comment on column accountAssignFormReceiver.isManage  is '是否是主办';


 create sequence S_accountAssignForm
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;


CREATE TABLE accountAssignForm (
    id number(10),  
    stepId number(10),  
    ledgerId number(10),  
    ledgerType number(10),  
    sourceOrg number(10),  
    reason varchar2(500),  
    handleContent varchar2(500),  
    serialNumber varchar2(20),  
    createUser           VARCHAR2(32),
  	updateUser           VARCHAR2(32),
    createDate           DATE,
    updateDate           DATE,
	handleStartDate           DATE,
	handleEndDate           DATE,
	ledgerSerialNumber  varchar2(50), 
	periods number(10),  
	type number(10), 
	assignType	number(10), 
	constraint PKaccountAssignForm primary key (id)
) ;
comment on table accountAssignForm  is '交办单';
 comment on column accountAssignForm.id  is '交办单 ID';
 comment on column accountAssignForm.stepId  is '步骤编号';
 comment on column accountAssignForm.ledgerId  is '台账编号';
 comment on column accountAssignForm.ledgerType  is '台账类型';
 comment on column accountAssignForm.sourceOrg  is ' 当前处理部门ID';
 comment on column accountAssignForm.reason  is '交办主要事项及原因';
 comment on column accountAssignForm.handleContent  is '办理结果';
 comment on column accountAssignForm.serialNumber  is '交办单编码';
 comment on column accountAssignForm.handleStartDate  is '办理起始时间';
 comment on column accountAssignForm.handleEndDate  is '办理结束时间';
 comment on column accountAssignForm.periods  is '会议期数';
 comment on column accountAssignForm.type  is '会议类型';
 comment on column accountAssignForm.assignType  is '交办类型';


 create sequence S_accountDeclareForm
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;


CREATE TABLE accountDeclareForm (
    id number(10),  
    stepId number(10),  
    ledgerId number(10),  
    ledgerType number(10),  
    sourceOrg number(10),  
    targetOrg number(10),  
    reason varchar2(500),  
    handleContent varchar2(500),  
    serialNumber varchar2(20),  
    name varchar2(50),  
    mobile varchar2(50),  
    createUser           VARCHAR2(32),
  	updateUser           VARCHAR2(32),
    createDate           DATE,
    updateDate           DATE,
	ledgerSerialNumber  varchar2(50), 
	declareDate           DATE,
	ledgerHandleContent varchar2(500),  
	jointContent varchar2(500),  
	constraint PKaccountDeclareForm primary key (id)
) ;
comment on table accountDeclareForm  is '申报单';
 comment on column accountDeclareForm.id  is '申报单 ID';
 comment on column accountDeclareForm.stepId  is '步骤编号';
 comment on column accountDeclareForm.ledgerId  is '台账编号';
 comment on column accountDeclareForm.ledgerType  is '台账类型';
 comment on column accountDeclareForm.sourceOrg  is '当前申报的部门ID';
 comment on column accountDeclareForm.targetOrg  is '上级部门ID';
 comment on column accountDeclareForm.reason  is '申报主要事项及原因';
 comment on column accountDeclareForm.handleContent  is '申报单位领导意见';
 comment on column accountDeclareForm.serialNumber  is '申报单编码';
 comment on column accountDeclareForm.name  is '申报单位联系人';
 comment on column accountDeclareForm.mobile  is '联系电话';
 comment on column accountDeclareForm.jointContent  is '联席会议召集人审核意见';
 comment on column accountDeclareForm.ledgerHandleContent  is '县台账办拟办意见';


 create sequence S_accountTurnForm
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;


CREATE TABLE accountTurnForm (
    id number(10),  
    stepId number(10),  
    ledgerId number(10),  
    ledgerType number(10),  
    targetOrg number(10),  
    reason varchar2(500),  
    handleResult varchar2(500),  
    serialNumber varchar2(20),  
    name varchar2(50),  
    mobile varchar2(50),  
    createUser           VARCHAR2(32),
  	updateUser           VARCHAR2(32),
    createDate           DATE,
    updateDate           DATE,
	ledgerSerialNumber  varchar2(50), 
	turnDate           DATE,
	subOpinion  varchar2(500),
	opinion  varchar2(500),
	handleStartDate           DATE,
	handleEndDate           DATE,
	receiveDate           DATE,
	manager varchar2(50), 
	constraint PKaccountTurnForm primary key (id)
) ;
comment on table accountTurnForm  is '转办单';
 comment on column accountTurnForm.id  is '转办单 ID';
 comment on column accountTurnForm.stepId  is '步骤编号';
 comment on column accountTurnForm.ledgerId  is '台账编号';
 comment on column accountTurnForm.ledgerType  is '台账类型';
 comment on column accountTurnForm.targetOrg  is '转办部门ID';
 comment on column accountTurnForm.reason  is '转办主要事项及原因';
 comment on column accountTurnForm.handleResult  is '办理结果';
 comment on column accountTurnForm.serialNumber  is '转办单编码';
 comment on column accountTurnForm.name  is '承办单位联系人';
 comment on column accountTurnForm.mobile  is '联系电话';
 comment on column accountTurnForm.manager  is '承办责任人';
 comment on column accountTurnForm.subOpinion  is '转办单位分管领导意见';
 comment on column accountTurnForm.opinion  is '转办单位主要领导意见';
 comment on column accountTurnForm.turnDate  is '转办时间';
 comment on column accountTurnForm.handleStartDate  is '办理起始时间';
 comment on column accountTurnForm.handleEndDate  is '办理结束时间';
 comment on column accountTurnForm.receiveDate  is '接件时间';


 create sequence S_accountReplyForm
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;


CREATE TABLE accountReplyForm (
    id number(10),  
    stepId number(10),  
    ledgerId number(10),  
    ledgerType number(10),  
    sourceOrg number(10),  
    targetOrg number(10),  
    reason varchar2(500),  
    handleContent varchar2(500),  
    serialNumber varchar2(20),  
    createUser           VARCHAR2(32),
  	updateUser           VARCHAR2(32),
    createDate           DATE,
    updateDate           DATE,
	ledgerSerialNumber  varchar2(50), 
	name  varchar2(50), 
	mobile  varchar2(50), 
	replyDate           DATE,
	constraint PKaccountReplyForm primary key (id)
) ;
comment on table accountReplyForm  is '回复单';
 comment on column accountReplyForm.id  is '回复单 ID';
 comment on column accountReplyForm.stepId  is '步骤编号';
 comment on column accountReplyForm.ledgerId  is '台账编号';
 comment on column accountReplyForm.ledgerType  is '台账类型';
 comment on column accountReplyForm.sourceOrg  is '当前上报的部门ID';
 comment on column accountReplyForm.targetOrg  is '上级部门ID';
 comment on column accountReplyForm.reason  is '回复主要事项及原因';
 comment on column accountReplyForm.handleContent  is '回复主要事项及原因';
 comment on column accountReplyForm.serialNumber  is '回复单编码';
 comment on column accountReplyForm.replyDate  is '回复时间';



 create sequence S_accountReportForm
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;


CREATE TABLE accountReportForm (
    id number(10),  
    stepId number(10),  
    ledgerId number(10),  
    ledgerType number(10),  
    sourceOrg number(10),  
    targetOrg number(10),  
    reason varchar2(500),  
    handleContent varchar2(500),  
    serialNumber varchar2(20),  
    name varchar2(50),  
    mobile varchar2(50),  
    createUser           VARCHAR2(32),
  	updateUser           VARCHAR2(32),
    createDate           DATE,
    updateDate           DATE,
	ledgerSerialNumber  varchar2(50), 
	reportDate           DATE,
	constraint PKaccountReportForm primary key (id)
) ;
comment on table accountReportForm  is '呈报单';
 comment on column accountReportForm.id  is '呈报单 ID';
 comment on column accountReportForm.stepId  is '步骤编号';
 comment on column accountReportForm.ledgerId  is '台账编号';
 comment on column accountReportForm.ledgerType  is '台账类型';
 comment on column accountReportForm.sourceOrg  is '当前上报的部门ID';
 comment on column accountReportForm.targetOrg  is '上级部门ID';
 comment on column accountReportForm.reason  is '呈报主要事项及原因';
 comment on column accountReportForm.handleContent  is '呈报主要事项及原因';
 comment on column accountReportForm.serialNumber  is '呈报单编码';
 comment on column accountReportForm.name  is '呈报单位联系人';
 comment on column accountReportForm.mobile  is '联系电话';
 commit;
