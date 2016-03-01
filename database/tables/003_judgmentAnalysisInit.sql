 
create sequence S_BUSINESSMODEL
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table businessModel(
	id 				NUMBER(10) 		primary key not null,
	name			VARCHAR2(32)	not null,
	keyName		VARCHAR2 (16) not null,
	type			VARCHAR2 (16)
);
comment on table businessModel is '业务模型配置表';
comment on column businessModel.id is 'id';
comment on column businessModel.name is '名称';
comment on column businessModel.keyName is '关键词';
comment on column businessModel.type is '类型';
 

create table dimensions(
  id              NUMBER(10)     primary key not null,
  name            VARCHAR2(32)  not null,
  keyName          VARCHAR2 (16) not null,
  type            number(2)  not null,
  code            VARCHAR2 (128),
  databaseType		varchar2(16),
  databaseLen		number(2),
  databaseDefault	varchar2(16),
  propertydomain  VARCHAR2(60)
);

comment on table dimensions is '维度表';
comment on column dimensions.id is 'id';
comment on column dimensions.name is '名称';
comment on column dimensions.keyName is '关键词';
comment on column dimensions.type is '类型（字典项、自定义）';
comment on column dimensions.code is '编码';
comment on column dimensions.DATABASETYPE
  is '字段类型';
comment on column dimensions.DATABASELEN
  is '字段长度';
comment on column dimensions.DATABASEDEFAULT
  is '字段默认值';
comment on column dimensions.propertydomain is '字典项名称';


-- Create sequence 
create sequence s_DIMENSION
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
 

create sequence s_dimensionCombination
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table dimensionCombination(
	id							NUMBER(10) 		primary key not null,
	name						VARCHAR2(128)	not null,
	keyName					VARCHAR2 (64) not null,
	mod						varchar2(32)  not null,
	tableName				VARCHAR2 (32) not null,
	modelId					NUMBER(10)  not null,
	combination			VARCHAR2 (128),
	period					varchar2(32),
	idx                 varchar2(300)
);
comment on table dimensionCombination is '维度组合表';
comment on column dimensionCombination.id is 'id';
comment on column dimensionCombination.name is '名称';
comment on column dimensionCombination.keyName is '关键词';
comment on column dimensionCombination.modelId is '业务模型id';
comment on column dimensionCombination.mod is '模式（准实时、历史）'; 
comment on column dimensionCombination.tableName is '组合表名';
comment on column dimensionCombination.combination is '组合名称';
comment on column dimensionCombination.period is '周期';
comment on column dimensionCombination.idx is '索引';


create sequence s_ScheduleJobLog
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table:  ScheduleJobLog                                       */
/*==============================================================*/
create table ScheduleJobLog  (
   ID                   NUMBER(10)                      not null,
   SCHEDULEID           NUMBER(10)                      not null,
   SCHEDULELOGID        NUMBER(10)                      not null,
   SCHEDULEINFOID       NUMBER(10)                      not null,
   STARTTIME            DATE,
   ENDTIME              DATE,
   APPNAME              VARCHAR2(32),
   JOBNAME              VARCHAR2(64),
   EXCEPTIONNUM         NUMBER(10) default 0,
   TASKPARAMETER        VARCHAR2(32),
   OWNSIGN              VARCHAR2(32),
   TASKITEMS            VARCHAR2(32),
   EACHFETCHDATA        NUMBER(10),
   LISTSIZE             NUMBER(10),
   REMARK               VARCHAR2(32),
   UPDATEDATE           DATE,
   UPDATEUSER           VARCHAR2(32),
   CREATEDATE           DATE,
   CREATEUSER           VARCHAR2(32),
   constraint PK_SCHEDULEJOBLOG primary key (ID)
);

comment on table  ScheduleJobLog  is
'调度日志表';

comment on column  ScheduleJobLog .ID is
'编码ID';

comment on column  ScheduleJobLog .SCHEDULEID is
'调度ID';

comment on column  ScheduleJobLog .SCHEDULEINFOID is
'调度明细ID';

comment on column  ScheduleJobLog .STARTTIME is
'开始时间';

comment on column  ScheduleJobLog .ENDTIME is
'结束时间';

comment on column  ScheduleJobLog .APPNAME is
'运行服务器名称';

comment on column  ScheduleJobLog .JOBNAME is
'调度名称';

comment on column  ScheduleJobLog .EXCEPTIONNUM is
'异常数';

comment on column  ScheduleJobLog .TASKPARAMETER is
'自定义参数';

comment on column  ScheduleJobLog .OWNSIGN is
'运行环境';

comment on column  ScheduleJobLog .TASKITEMS is
'任务项';

comment on column  ScheduleJobLog .EACHFETCHDATA is
'每次获取记录数';

comment on column  ScheduleJobLog .LISTSIZE is
'返回结果数';

comment on column  ScheduleJobLog .REMARK is
'备注';

comment on column  ScheduleJobLog .UPDATEDATE is
'更新时间';

comment on column  ScheduleJobLog .UPDATEUSER is
'更新人';

comment on column  ScheduleJobLog .CREATEDATE is
'创建时间';

comment on column  ScheduleJobLog .CREATEUSER is
'创建人';



create sequence s_scheduleJob
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
/*==============================================================*/
/* Table:  scheduleJob                                          */
/*==============================================================*/
create table  scheduleJob   (
   ID                   NUMBER(10)                      not null,
   NAME                 VARCHAR2(64) unique,
   CRONEXPRESSION       VARCHAR2(32),
   RUNTYPE              NUMBER(1)                       not null,
   PREFIXZERO           NUMBER(1)        default 1               not null,
   CURRENTCYCLE         NUMBER(10)                       not null,
   BEANNAME             VARCHAR2(32),
   MODELID           NUMBER(10),
   DIMCONMBINATIONID NUMBER(10),
   ENABLE               NUMBER(1)                       not null,
   UPDATEDATE           DATE,
   UPDATEUSER           VARCHAR2(32),
   CREATEDATE           DATE,
   CREATEUSER           VARCHAR2(32),
   NEXTSTARTTIME        DATE,
   constraint PK_SCHEDULEJOB primary key (ID)
);

comment on table  scheduleJob  is
'调度作业表';

comment on column  scheduleJob .ID is
'编码ID';

comment on column  scheduleJob .NAME is
'名称';

comment on column  scheduleJob .CRONEXPRESSION is
'执行时间';

comment on column  scheduleJob .RUNTYPE is
'运行方式';

comment on column  scheduleJob .CURRENTCYCLE is
'当前游标';

comment on column  scheduleJob .BEANNAME is
'bean名称';

comment on column  scheduleJob .MODELID is
'业务模型';

comment on column  scheduleJob .DIMCONMBINATIONID is
'维度组合';

comment on column  scheduleJob .ENABLE is
'是否启用';

comment on column  scheduleJob .UPDATEDATE is
'更新时间';

comment on column  scheduleJob .UPDATEUSER is
'更新人';

comment on column  scheduleJob .CREATEDATE is
'创建时间';

comment on column  scheduleJob .CREATEUSER is
'创建人';

comment on column SCHEDULEJOB.NEXTSTARTTIME
  is '下次执行时间';

create sequence s_scheduleJobInfo
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
/*==============================================================*/
/* Table:  scheduleJobInfo                                      */
/*==============================================================*/
create table  scheduleJobInfo   (
   ID                   NUMBER(10)                      not null,
   SCHEDULEID           NUMBER(10)                      not null,
   ORDERED              NUMBER(2),
   EXECUTOR             VARCHAR2(1024)                  not null,
   TYPE                 NUMBER(1)                       not null,
   SQLTYPE              NUMBER(1)                       not null,
   PARAMS               VARCHAR2(128),
   UPDATEDATE           DATE,
   UPDATEUSER           VARCHAR2(32),
   CREATEDATE           DATE,
   CREATEUSER           VARCHAR2(32),
   BATCHNUM             NUMBER(10),
   SAVEFLAG             NUMBER(1),
   GROUPNAME            VARCHAR2(20),
   constraint PK_SCHEDULEJOBINFO primary key (ID)
);

comment on table  scheduleJobInfo  is
'调度明细表';

comment on column  scheduleJobInfo .ID is
'编码ID';

comment on column  scheduleJobInfo .SCHEDULEID is
'调度ID';

comment on column  scheduleJobInfo .ORDERED is
'执行顺序';

comment on column  scheduleJobInfo .EXECUTOR is
'执行';

comment on column  scheduleJobInfo .TYPE is
'执行类型';

comment on column  scheduleJobInfo .SQLTYPE is
'SQL类型';

comment on column  scheduleJobInfo .UPDATEDATE is
'更新时间';

comment on column  scheduleJobInfo .UPDATEUSER is
'更新人';

comment on column  scheduleJobInfo .CREATEDATE is
'创建时间';

comment on column  scheduleJobInfo .CREATEUSER is
'创建人';

comment on column SCHEDULEJOBINFO.BATCHNUM is '批量数';
  
comment on column SCHEDULEJOBINFO.SAVEFLAG is '是否保存';
  
comment on column SCHEDULEJOBINFO.GROUPNAME is '分组标识';


create sequence s_schedulejobexception
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;
/*==============================================================*/
/* Table:  schedulejobexception                                 */
/*==============================================================*/
create table  schedulejobexception   (
   ID                   NUMBER(10)                      not null,
   LOGID                NUMBER(10)                      not null,
   APPNAME              VARCHAR2(32),
   JOBNAME              VARCHAR2(64),
   MODELNAME            VARCHAR2(32),
   TABLENAME            VARCHAR2(32),
   ERRORTIME            DATE,
   DESCRIPTION          VARCHAR2(4000),
   UPDATEDATE           DATE,
   UPDATEUSER           VARCHAR2(32),
   CREATEDATE           DATE,
   CREATEUSER           VARCHAR2(32),
   constraint PK_SCHEDULEJOBEXCEPTION primary key (ID)
);

comment on table  schedulejobexception  is
'调度异常详细表';

comment on column  schedulejobexception .ID is
'编码ID';

comment on column  schedulejobexception .LOGID is
'调度日志ID';

comment on column schedulejobexception.APPNAME
  is '运行服务器名称';
comment on column schedulejobexception.JOBNAME
  is '调度名称';
comment on column schedulejobexception.MODELNAME
  is '业务模型名称';
comment on column schedulejobexception.TABLENAME
  is '维度表名';
  
comment on column  schedulejobexception .ERRORTIME is
'异常时间';

comment on column  schedulejobexception .DESCRIPTION is
'异常描述';

comment on column  schedulejobexception .UPDATEDATE is
'更新时间';

comment on column  schedulejobexception .UPDATEUSER is
'更新人';

comment on column  schedulejobexception .CREATEDATE is
'创建时间';

comment on column  schedulejobexception .CREATEUSER is
'创建人';


create sequence s_scheduleLog
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

/*==============================================================*/
/* Table: scheduleLog                                         */
/*==============================================================*/
create table scheduleLog  (
   ID                      NUMBER(10)                      not null,
   NAME                    VARCHAR2(32),
   MODELNAME               VARCHAR2(32),
   TABLENAME               VARCHAR2(32),
   STARTTIME               DATE,
   ENDTIME                 DATE,
   NEXTSTARTTIME           DATE,
   CRONEXPRESSION          VARCHAR2(32),
   STATUS                  NUMBER(1),
   CURRENTCYCLE            NUMBER(10),
   UPDATEDATE              DATE,
   UPDATEUSER              VARCHAR2(32),
   CREATEDATE              DATE,
   CREATEUSER              VARCHAR2(32),
   EXCEPTIONDESCRIPTION   VARCHAR2(4000),
   constraint PK_SCHEDULELOG primary key (ID)
);

comment on table scheduleLog is
'日志主表';

comment on column scheduleLog.ID is
'编码ID';

comment on column scheduleLog.NAME is
'任务名称';

comment on column scheduleLog.MODELNAME is
'业务模型名称';

comment on column scheduleLog.TABLENAME is
'维度表名';

comment on column scheduleLog.STARTTIME is
'开始时间';

comment on column scheduleLog.ENDTIME is
'结束时间';

comment on column scheduleLog.NEXTSTARTTIME is
'下次执行时间';

comment on column scheduleLog.CRONEXPRESSION is
'克隆表达式';

comment on column scheduleLog.STATUS is
'状态';

comment on column scheduleLog.UPDATEDATE is
'更新时间';

comment on column scheduleLog.UPDATEUSER is
'更新人';

comment on column scheduleLog.CREATEDATE is
'创建时间';

comment on column scheduleLog.CREATEUSER is
'创建人';

comment on column scheduleLog.CURRENTCYCLE is
'当前游标';

comment on column  scheduleLog .EXCEPTIONDESCRIPTION is
'异常描述';


create sequence S_businessDescription
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table  businessDescription(
	id	number(10)  primary key not null,
	name    varchar2(30)	not null,
	keyName	varchar2(30)  not null,
	content	clob
);

comment on table businessDescription is
'业务描述表';

comment on column businessDescription.name is
'名称';

comment on column businessDescription.keyName is
'关键字';

comment on column businessDescription.content is
'内容';


create sequence S_CARPOPTOTAL
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;


--总况
create table CARPOPTOTAL
(
  id            NUMBER(10) not null,
  orgid         NUMBER(10),
  orgcode       VARCHAR2(32),
  orgname       VARCHAR2(64),
  total         NUMBER(20),
  eldertotal    NUMBER(20),
  elderpercent  NUMBER(20,10),
  handictotal   NUMBER(20),
  handicpercent NUMBER(20,10),
  optobjtotal   NUMBER(20),
  optobjpercent NUMBER(20,10),
  aidpoptotal   NUMBER(20),
  aidpoppercent NUMBER(20,10),
  updatedate    DATE,
  updateuser    VARCHAR2(30),
  createdate    DATE,
  createuser    VARCHAR2(30)
)
;
comment on table CARPOPTOTAL
  is '关怀对象';
comment on column CARPOPTOTAL.id
  is '编码ID';
comment on column CARPOPTOTAL.orgid
  is '组织机构编码ID';
comment on column CARPOPTOTAL.orgcode
  is '组织机构CODE';
comment on column CARPOPTOTAL.orgname
  is '组织机构名称';
comment on column CARPOPTOTAL.total
  is '人口总数';
comment on column CARPOPTOTAL.eldertotal
  is '老年人总数';
comment on column CARPOPTOTAL.elderpercent
  is '老年人占比';
comment on column CARPOPTOTAL.handictotal
  is '残疾人总数';
comment on column CARPOPTOTAL.handicpercent
  is '残疾人占比';
comment on column CARPOPTOTAL.optobjtotal
  is '优抚对象数量';
comment on column CARPOPTOTAL.optobjpercent
  is '优抚对象占比';
comment on column CARPOPTOTAL.aidpoptotal
  is '需要求助人员数量';
comment on column CARPOPTOTAL.aidpoppercent
  is '需要求助人员占比';
comment on column CARPOPTOTAL.updatedate
  is '更新时间';
comment on column CARPOPTOTAL.updateuser
  is '更新人';
comment on column CARPOPTOTAL.createdate
  is '创建时间';
comment on column CARPOPTOTAL.createuser
  is '创建人';
create index IDX_CARPOPTOTAL_ORG on CARPOPTOTAL (ORGID, ORGCODE);
create index IDX_CARPOPTOTAL_ORGID on CARPOPTOTAL (ORGID);
alter table CARPOPTOTAL
  add constraint PK_CARPOPTOTAL primary key (ID);


create sequence S_HOUSEHOLDSTAFFTOTAL
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table HOUSEHOLDSTAFFTOTAL
(
  id                NUMBER(10) not null,
  orgid             NUMBER(10),
  orgcode           VARCHAR2(50),
  orgname           VARCHAR2(64),
  total             NUMBER(20),
  housetotal        NUMBER(20),
  housepercent      NUMBER(20,10),
  populationtotal   NUMBER(20),
  populationpercent NUMBER(20,10),
  unpoptotal        NUMBER(20),
  unpoppercent      NUMBER(20,10),
  seapertotal       NUMBER(20),
  seaperpercent     NUMBER(20,10),
  emppoptotal       NUMBER(20),
  emppoppercent     NUMBER(20,10),
  carpoptotal       NUMBER(20),
  carpoppercent     NUMBER(20,10),
  youthstotal       NUMBER(20),
  youthspercent     NUMBER(20,10),
  unempytotal       NUMBER(20),
  unempypercent     NUMBER(20,10),
  nwomentotal       NUMBER(20),
  nwomentpercent    NUMBER(20,10),
  updatedate        DATE,
  updateuser        VARCHAR2(30),
  createdate        DATE,
  createuser        VARCHAR2(30)
)
;
comment on table HOUSEHOLDSTAFFTOTAL
  is '户籍人口汇总表';
comment on column HOUSEHOLDSTAFFTOTAL.id
  is '编码ID';
comment on column HOUSEHOLDSTAFFTOTAL.orgid
  is '组织机构ID';
comment on column HOUSEHOLDSTAFFTOTAL.orgcode
  is '组织机构编码';
comment on column HOUSEHOLDSTAFFTOTAL.orgname
  is '组织机构名称';
comment on column HOUSEHOLDSTAFFTOTAL.total
  is '人口总数';
comment on column HOUSEHOLDSTAFFTOTAL.housetotal
  is '户籍人口总数';
comment on column HOUSEHOLDSTAFFTOTAL.housepercent
  is '户籍人口占比';
comment on column HOUSEHOLDSTAFFTOTAL.populationtotal
  is '流动人口总数';
comment on column HOUSEHOLDSTAFFTOTAL.populationpercent
  is '流动人口占比';
comment on column HOUSEHOLDSTAFFTOTAL.unpoptotal
  is '未落户人口总数';
comment on column HOUSEHOLDSTAFFTOTAL.unpoppercent
  is '未落户人口占比';
comment on column HOUSEHOLDSTAFFTOTAL.seapertotal
  is '境外人口总数';
comment on column HOUSEHOLDSTAFFTOTAL.seaperpercent
  is '境外人口占比';
comment on column HOUSEHOLDSTAFFTOTAL.emppoptotal
  is '特殊人群总数';
comment on column HOUSEHOLDSTAFFTOTAL.emppoppercent
  is '特殊人群占比';
comment on column HOUSEHOLDSTAFFTOTAL.carpoptotal
  is '关怀对象总数';
comment on column HOUSEHOLDSTAFFTOTAL.carpoppercent
  is '关怀对象占比';
comment on column HOUSEHOLDSTAFFTOTAL.youthstotal
  is '青少年总数';
comment on column HOUSEHOLDSTAFFTOTAL.youthspercent
  is '青少年占比';
comment on column HOUSEHOLDSTAFFTOTAL.unempytotal
  is '失业人员总数';
comment on column HOUSEHOLDSTAFFTOTAL.unempypercent
  is '失业人员占比';
comment on column HOUSEHOLDSTAFFTOTAL.nwomentotal
  is '育龄妇女总数';
comment on column HOUSEHOLDSTAFFTOTAL.nwomentpercent
  is '育龄妇女占比';
comment on column HOUSEHOLDSTAFFTOTAL.updatedate
  is '更新时间';
comment on column HOUSEHOLDSTAFFTOTAL.updateuser
  is '更新人';
comment on column HOUSEHOLDSTAFFTOTAL.createdate
  is '创建时间';
comment on column HOUSEHOLDSTAFFTOTAL.createuser
  is '创建人';
create index IDX_HOUSEHOLDST_ORG on HOUSEHOLDSTAFFTOTAL (ORGID, ORGCODE);
create index IDX_HOUSEHOLDST_ORGID on HOUSEHOLDSTAFFTOTAL (ORGID);
alter table HOUSEHOLDSTAFFTOTAL
  add constraint PK_HOUSEHOLDSTAFFTOTAL primary key (ID);



create sequence S_KEYPERSONNELTOTAL
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

create table KEYPERSONNELTOTAL
(
  id            NUMBER(10) not null,
  orgid         NUMBER(10),
  orgcode       VARCHAR2(32),
  orgname       VARCHAR2(64),
  total         NUMBER(20),
  posittotal    NUMBER(20),
  positpercent  NUMBER(20,10),
  recpoptotal   NUMBER(20),
  recpoppercent NUMBER(20,10),
  mentaltotal   NUMBER(20),
  mentalpercent NUMBER(20,10),
  druggytotal   NUMBER(20),
  druggypercent NUMBER(20,10),
  youthstotal   NUMBER(20),
  youthspercent NUMBER(20,10),
  visittotal    NUMBER(20),
  visitpercent  NUMBER(20,10),
  dangertotal   NUMBER(20),
  dangerpercent NUMBER(20,10),
  othertotal    NUMBER(20),
  otherpercent  NUMBER(20,10),
  updatedate    DATE,
  updateuser    VARCHAR2(30),
  createdate    DATE,
  createuser    VARCHAR2(30)
)
;
comment on table KEYPERSONNELTOTAL
  is '重点人员';
comment on column KEYPERSONNELTOTAL.id
  is '编码ID';
comment on column KEYPERSONNELTOTAL.orgid
  is '组织机构ID';
comment on column KEYPERSONNELTOTAL.orgcode
  is '组织机构CODE';
comment on column KEYPERSONNELTOTAL.orgname
  is '组织机构名称';
comment on column KEYPERSONNELTOTAL.total
  is '人口总数';
comment on column KEYPERSONNELTOTAL.posittotal
  is '形释人员总数';
comment on column KEYPERSONNELTOTAL.positpercent
  is '形释人员占比';
comment on column KEYPERSONNELTOTAL.recpoptotal
  is '社区矫正人员总数';
comment on column KEYPERSONNELTOTAL.recpoppercent
  is '社区矫正人员占比';
comment on column KEYPERSONNELTOTAL.mentaltotal
  is '精神病人员数量';
comment on column KEYPERSONNELTOTAL.mentalpercent
  is '精神病人也占比';
comment on column KEYPERSONNELTOTAL.druggytotal
  is '吸毒人员数量';
comment on column KEYPERSONNELTOTAL.druggypercent
  is '吸毒人员占比';
comment on column KEYPERSONNELTOTAL.youthstotal
  is '重点青少年数量';
comment on column KEYPERSONNELTOTAL.youthspercent
  is '重点青少年占比';
comment on column KEYPERSONNELTOTAL.visittotal
  is '重点上访人员数量';
comment on column KEYPERSONNELTOTAL.visitpercent
  is '重点上访人员占比';
comment on column KEYPERSONNELTOTAL.dangertotal
  is '危险品从业人员数量';
comment on column KEYPERSONNELTOTAL.dangerpercent
  is '危险品从业人员占比';
comment on column KEYPERSONNELTOTAL.othertotal
  is '其他人员数量';
comment on column KEYPERSONNELTOTAL.otherpercent
  is '其他人员占比';
comment on column KEYPERSONNELTOTAL.updatedate
  is '更新时间';
comment on column KEYPERSONNELTOTAL.updateuser
  is '更新人';
comment on column KEYPERSONNELTOTAL.createdate
  is '创建时间';
comment on column KEYPERSONNELTOTAL.createuser
  is '创建人';
create index IDX_KEYPERSONNELT_ORG on KEYPERSONNELTOTAL (ORGID, ORGCODE);
create index IDX_KEYPERSONNELT_ORGID on KEYPERSONNELTOTAL (ORGID);
alter table KEYPERSONNELTOTAL
  add constraint PK_KEYPERSONNELTOTAL primary key (ID);

