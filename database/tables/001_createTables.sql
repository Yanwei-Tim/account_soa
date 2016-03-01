

create sequence s_permissions
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;

create sequence s_organizations
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;
 

create sequence s_users
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;

create sequence s_userSessions
increment by 1
start with 1
 minvalue 1
 maxvalue 9999999999
 cache 20;

 
create sequence s_roles
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;

create sequence s_systemLogs
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;


create sequence s_proclamations
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;

create sequence s_propertyDicts
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;

create sequence s_propertyDomains
increment by 1
start with 1
 maxvalue 9999999999
 minvalue 1
 cache 20;


create sequence s_moduelClickCounts
increment by 1
start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;
 
create sequence s_WorkMemo
	increment by 1
	start with 1
 	minvalue 1
 	cache 20
 	maxvalue 9999999999;
 
/*==============================================================*/
/* Table: workMemo  工作备忘录                                         */
/*==============================================================*/
create table workMemo(
id          number(10)          not null,
userId      number(10)          not null,
addMemoDate   DATE              not null,
memoContents  varchar2(200)     not null,
createUser    VARCHAR2(32)      not null,
updateUser    VARCHAR2(32),
createDate      DATE             not null,
updateDate      DATE
);
-- Add comments to the table
comment on table WORKMEMO
  is '工作备忘录 ';
-- Add comments to the columns
comment on column WORKMEMO.USERID
  is '用户ID';
comment on column WORKMEMO.ADDMEMODATE
  is '添加备忘录的日期';
comment on column WORKMEMO.MEMOCONTENTS
  is '备忘录内容';

/*==============================================================*/
/* Table: permissions                                           */
/*==============================================================*/
create table permissions  (
   id                   NUMBER(10)                      not null,
   cname                VARCHAR2(100)                   not null,
   ename                VARCHAR2(100)                   not null,
   permissionType       NUMBER(10)                      not null,
   moduleName           VARCHAR2(45)                    not null,
   enable               NUMBER(1)                      default 0 not null,
   parentId             NUMBER(10),
   description          VARCHAR2(200),
   normalUrl            VARCHAR2(200),
   leaderUrl            VARCHAR2(200),
   indexId              NUMBER(10),
   constraint pkPermissions primary key (id)
);

comment on column permissions.cname is
'权限英文名称';

comment on column permissions.ename is
'权限中文名称';

comment on column permissions.permissionType is
'权限类型:菜单，控制点';

comment on column permissions.moduleName is
'模块名称';

comment on column permissions.enable is
'是否启用该模块';

comment on column permissions.parentId is
'父菜单id';

comment on column permissions.description is
'权限描述';

/*==============================================================*/
/* Table: organizations                                         */
/*==============================================================*/
create table organizations  (
   id                   NUMBER(10)                      not null,
   parentFunOrgId       NUMBER(10),
   parentId             NUMBER(10),
   orgType              NUMBER(10)                      not null,
   orgLevel             NUMBER(10),
   subCount             NUMBER(10)                     default 0 not null,
   seq                  NUMBER(10)                     default 0 not null,
   maxCode              NUMBER(10)                     default 0 not null,
   subCountFun          NUMBER(10)                     default 0 not null,
   departmentNo         VARCHAR2(32),
   orgName              VARCHAR2(60)                    not null,
   contactWay           VARCHAR2(15),
   orgInternalCode      VARCHAR2(32)                    not null,
   simplePinyin         VARCHAR2(10)                    not null,
   fullpinyin           VARCHAR2(30)                    not null,
   remark               VARCHAR2(600),
   createUser           VARCHAR2(32)                    not null,
   buildingId      		VARCHAR2(30),
   centerX 				NUMBER(10),
   centerY 				NUMBER(10),
   updateUser           VARCHAR2(32),
   updateDate           DATE,
   createDate           DATE                            not null,
   constraint pkOrganizations primary key (id),
   constraint fkParentOrg foreign key (parentId)
         references organizations (id),
   constraint fkParentFunOrg foreign key (parentFunOrgId)
         references organizations (id)
);

comment on column organizations.parentFunOrgId is
'所属职能部门';

comment on column organizations.orgType is
'部门类型';

comment on column organizations.orgLevel is
'组织机构层级';

comment on column organizations.subCount is
'子部门数';

comment on column organizations.seq is
'序号';

comment on column organizations.maxCode is
'分配子部门最大编码';

comment on column organizations.subCountFun is
'子职能部门数';

comment on column organizations.departmentNo is
'部门编号';

comment on column organizations.orgName is
'部门名称';

comment on column organizations.contactWay is
'部门联系方式';

comment on column organizations.orgInternalCode is
'部门内置类型码';

comment on column organizations.simplePinyin is
'部门名称简拼';

comment on column organizations.fullpinyin is
'部门名称全拼';

comment on column organizations.remark is
'备注';

comment on column organizations.createUser is
'创建用户';

comment on column organizations.updateUser is
'更新用户名';

comment on column organizations.updateDate is
'更新用户';

comment on column organizations.createDate is
'创建日期';


/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users  (
   id                   NUMBER(10)                      not null,
   organizationId       NUMBER(10)                      not null,
   credits1             NUMBER(10)                     default 0 not null,
   credits2             NUMBER(10)                     default 0 not null,
   failureTimes         NUMBER(5)                      default 0,
   isLock               NUMBER(1)                      default 0 not null,
   isAdmin              NUMBER(1)                      default 0 not null,
   changePassword       NUMBER(1)                      default 1 not null,
   hasNewMessage        NUMBER(1)                      default 0 not null,
   userName             VARCHAR2(32)                    not null,
   name                 VARCHAR2(32),
   password             VARCHAR2(32)                    not null,
   mobile               VARCHAR2(15),
   fullPinyin           VARCHAR2(30),
   simplePinyin         VARCHAR2(10),
   email                VARCHAR2(32),
   lastLoginIp          VARCHAR2(32),
   previousLoginIp      VARCHAR2(32),
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   orgInternalCode      VARCHAR2(32)                    not null,
   workCompany          VARCHAR2(64),
   workPhone            VARCHAR2(32),
   homePhone            VARCHAR2(32),
   headerUrl 			VARCHAR2(300),
   previousLoginTime    DATE,
   lastLoginTime        DATE,
   createDate           DATE                            not null,
   updateDate           DATE,
   vpdn                 VARCHAR2(50)                    ,
   isShutDown           NUMBER(1)                      default 0 not null,
   connectVpdn			NUMBER(1)                      default 1 not null,
   ISFRISTWORKBENCH     NUMBER(1)                      default 1 not null,
   constraint pkUsers primary key (id),
   constraint fkUserOrganization foreign key (organizationId)
         references organizations (id)
);

comment on column users.credits1 is
'积分1';

comment on column users.credits2 is
'积分2';

comment on column users.failureTimes is
'记录用户登录失败次数';

comment on column users.isLock is
'是否锁定';

comment on column users.isAdmin is
'是否超级管理员';

comment on column users.changePassword is
'登录时需要修改密码不';

comment on column users.userName is
'用户名';

comment on column users.name is
'用户姓名';

comment on column users.password is
'密码';

comment on column users.mobile is
'手机号码';

comment on column users.fullPinyin is
'姓名全拼';

comment on column users.simplePinyin is
'姓名简拼';

comment on column users.email is
'电子邮件';

comment on column users.lastLoginIp is
'最后登录ip';

comment on column users.previousLoginIp is
'上次登录IP';

comment on column users.createUser is
'创建用户名';

comment on column users.updateUser is
'最后更新用户名';

comment on column users.previousLoginTime is
'上次登录时间';

comment on column users.lastLoginTime is
'最后登录时间';

comment on column users.createDate is
'创建日期';

comment on column users.updateDate is
'最后更新日期';

comment on column users.vpdn is
'用户vpdn帐号';

comment on column users.isShutDown is
'用户是否启用';

comment on column users.connectVpdn is
'登陆时是否连接vpdn';

comment on column users.isfristworkbench is
'是否首次登录工作台';


/*==============================================================*/
/* Table: userSessions                                          */
/*==============================================================*/
create table userSessions  (
   id                   NUMBER(10)                      not null,
   sessionId            VARCHAR2(45)                   default '' not null,
   userId               NUMBER(10)                     default NULL,
   userName             VARCHAR2(45)                   default NULL,
   userRealName         varchar2(32),
   orgId                number(10),
   accessIp             VARCHAR2(32),
   accessTime           DATE                           default NULL,
   isLogin              NUMBER(1)                      default 0 not null,
   loginDate            DATE                           default NULL,
   lastUrl              VARCHAR2(200)                  default NULL,
   loginIp              VARCHAR2(32),
   validateCode         VARCHAR2(4),
   orgInternalCode      VARCHAR2(32),
   constraint pkUserSessions primary key (id)
);

comment on column userSessions.sessionId is
'32位uuid';

comment on column userSessions.userId is
'用户id';

comment on column userSessions.userName is
'用户名';

comment on column userSessions.accessIp is
'最后访问ip';

comment on column userSessions.accessTime is
'最后访问时间';

comment on column userSessions.isLogin is
'是否登录';

comment on column userSessions.validateCode is
'验证码';

comment on column userSessions.orgInternalCode is
'组织机构内置编码';


/*==============================================================*/
/* Table: roles                                                 */
/*==============================================================*/
create table roles  (
   id                   NUMBER(10)                      not null,
   roleName             VARCHAR2(45)                    not null,
   createDate           DATE                            not null,
   description          VARCHAR2(600),
   createUser           VARCHAR2(32)                    not null,
   updateDate           DATE,
   updateUser           VARCHAR2(32),
   useInLevel           NUMBER(10),
   useInOrgType         NUMBER(10),
   workBenchType        NUMBER(10),
   constraint pkRoles primary key (id)
);

comment on table roles is
'岗位';

comment on column roles.roleName is
'角色名称';

comment on column roles.createDate is
'创建日期';

comment on column roles.description is
'描述';

comment on column roles.createUser is
'创建的用户名';

comment on column roles.updateDate is
'最后更新时间 ';

comment on column roles.updateUser is
'最后更新用户名';

comment on column roles.useInLevel is
'岗位应用层级';

comment on column roles.useInOrgType is
'岗位应用于何种类型责任区';

comment on column roles.workBenchType is
'工作台类型';


/*==============================================================*/
/* Table: userHasMultizones                                     */
/*==============================================================*/
create table userHasMultizones  (
   userId               NUMBER(10)                      not null,
   organizationId       NUMBER(10)                      not null,
   constraint pkUserHasMultizones primary key (userId, organizationId),
   constraint fkUserHasOrganizationOrg foreign key (organizationId)
         references organizations (id),
   constraint fkUserHasOrganizationUser foreign key (userId)
         references users (id)
);

/*==============================================================*/
/* Table: userHasRoles                                          */
/*==============================================================*/
create table userHasRoles  (
   userId               NUMBER(10)                      not null,
   roleId               NUMBER(10)                      not null,
   constraint pkUserHasRoles primary key (userId, roleId),
   constraint fkUserHasRoleRole foreign key (roleId)
         references roles (id),
   constraint fkUserHasRoleUser foreign key (userId)
         references users (id)
);

/*==============================================================*/
/* Table: roleHasPermissions                                    */
/*==============================================================*/
create table roleHasPermissions  (
   roleId               NUMBER(10)                      not null,
   permissionId         NUMBER(10)                      not null,
   constraint pkRoleHasPermissions primary key (roleId, permissionId),
   constraint fkRoleHasPermissionPermission foreign key (permissionId)
         references permissions (id),
   constraint fkRoleHasPermissionRole foreign key (roleId)
         references roles (id)
);


/*==============================================================*/
/* Table: "GlobalSettings"                                      */
/*==============================================================*/
create table GlobalSettings (
  GlobalValue			clob		not null
);
comment on table GlobalSettings is
'系统全局配置';
comment on column GlobalSettings.GlobalValue is
'全局配置';



/*==============================================================*/
/* Table:                                             */
/*==============================================================*/
create table systemLogs  (
   id                   NUMBER(10)                      not null,
   orgId                NUMBER(10),
   operationContent     clob,
   logLevel             NUMBER(10),
   operation            VARCHAR2(255),
   moduleName           VARCHAR2(60),
   username VARCHAR2(60),
   clientIp             VARCHAR2(32),
   orgInternalCode      VARCHAR2(32),
   operateTime          DATE                            not null,
   operationType 		NUMBER(10),
   constraint pkSystemLogs primary key (id),
   constraint fkSystemLogsOrg foreign key (orgId)
         references organizations (id)
);
comment on column systemLogs.operationType is
'操作类型';
comment on column systemLogs.logLevel is
'日志等级';

comment on column systemLogs.operation is
'术语编号';

comment on column systemLogs.moduleName is
'模块名';

comment on column systemLogs.username is
'操作的用户';

comment on column systemLogs.clientIp is
'访问IP ';

comment on column systemLogs.orgInternalCode is
'部门内部编码';

comment on column systemLogs.operateTime is
'术语系统默认名称';



/*==============================================================*/
/* Table: proclamations                                         */
/*==============================================================*/
create table proclamations  (
   id                   NUMBER(10)                      not null,
   display              NUMBER(1),
   content              VARCHAR2(1500)                  not null,
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                            not null,
   updateDate           DATE,
   constraint pkProclamations primary key (id)
);

comment on table proclamations is
'公告信息';

comment on column proclamations.id is
'公告id';

comment on column proclamations.display is
'是否显示';

comment on column proclamations.content is
'公告内容';

/*==============================================================*/
/* Table: propertyDomains                                       */
/*==============================================================*/
create table propertyDomains  (
   id                   NUMBER(10)                      not null,
   domainName           VARCHAR2(60)                    not null,
   systemSensitive      NUMBER(1)                      default 0 not null,
   systemRestrict       CLOB,
   constraint pkPropertyDomains primary key (id)
);

comment on column propertyDomains.domainName is
'什么领域的属性';

comment on column propertyDomains.systemSensitive is
'系统敏感，是否具有系统内置属性';

comment on column propertyDomains.systemRestrict is
'系统约束';

/*==============================================================*/
/* Table: propertyDicts                                         */
/*==============================================================*/
create table propertyDicts  (
   id                   NUMBER(10)                      not null,
   propertyDomainId     NUMBER(10)                      not null,
   internalId           NUMBER(10)                     default 0,
   displaySeq           NUMBER(10)                     default 0 not null,
   displayName          VARCHAR2(80)                    not null,
   simplePinyin         VARCHAR2(10)                    not null,
   fullPinyin           VARCHAR2(30)                    not null,
   createUser           VARCHAR2(32)                    not null,
   updateUser           VARCHAR2(32),
   createDate           DATE                            not null,
   updateDate           DATE,
   constraint pkPropertyDicts primary key (id),
   constraint fkPropertyDictPropertyDomain foreign key (propertyDomainId)
         references propertyDomains (id),
   constraint uniqueDomainIdAndPropertyName unique(propertyDomainId,displayName)
);

comment on column propertyDicts.propertyDomainId is
'领域属性id';

comment on column propertyDicts.internalId is
'系统内置id';

comment on column propertyDicts.displaySeq is
'显示顺序';

comment on column propertyDicts.displayName is
'什么领域的属性';

comment on column propertyDicts.simplePinyin is
'简拼';

comment on column propertyDicts.fullPinyin is
'全拼';

comment on column propertyDicts.createUser is
'创建人';

comment on column propertyDicts.updateUser is
'修改人';

comment on column propertyDicts.createDate is
'创建时间';

comment on column propertyDicts.updateDate is
'修改时间';


/* Table: moduelClickCounts */
create table moduelClickCounts  (
   id                	NUMBER(10)    		not null,
   userId             	NUMBER(10)			not null,
   permissionId         NUMBER(10)          not null,
   clickTimes           NUMBER(10)          not null,
   constraint pkModuelClickCounts primary key (id),
   constraint fkModuelClickCounts foreign key (permissionId)
         references permissions (id)
);
-- Add comments to the table
comment on table moduelClickCounts is
'模块点击次数统计';
-- Add comments to the columns
comment on column moduelClickCounts.userId is
'点击用户Id';

comment on column moduelClickCounts.permissionId is
'模块Id';

comment on column moduelClickCounts.clickTimes is
'模块点击次数';