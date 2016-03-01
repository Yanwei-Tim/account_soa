
create sequence S_LEDGERCONVERT
increment by 1
 start with 1
 minvalue 1
 cache 20
 maxvalue 9999999999;

 /*==============================================================*/
/* Table: ledgerConvert                                      */
/*==============================================================*/
create table ledgerConvert  (
   id                   NUMBER(10)                      not null,
   issueTypeName        varchar2(200)     ,
   issueName        varchar2(200)     ,
   ledgerId             NUMBER(10)         ,
   ledgerType           NUMBER(10)          ,
   occurOrg          	NUMBER(10)          ,
   occurOrgInternalCode      	varchar2(32) ,
   createOrg          	NUMBER(10)          ,
   createOrgInternalCode      	varchar2(32) ,
   occurDate           	DATE	,
   issueId          	NUMBER(10)          ,
   serialNumber		VARCHAR2(30) ,
   name		VARCHAR2(100) ,
   MOBILE VARCHAR2(15),
   description		VARCHAR2(500) ,
   status		NUMBER(1) default 0,
   ledgerSerialNumber		VARCHAR2(32) ,
   createUser          VARCHAR2(60) ,
   updateUser          VARCHAR2(60),
   createDate          DATE,
   updateDate          DATE,
   constraint pkLedgerConvert primary key (id)
);
comment on table ledgerConvert is
'事件转换台账表';
comment on column ledgerConvert.ledgerId is
'台账id';
comment on column ledgerConvert.issueTypeName is
'事件类型名称';
comment on column ledgerConvert.issueName is
'事件名称';
comment on column ledgerConvert.occurOrg is
'发生网格id';
comment on column ledgerConvert.occurOrgInternalCode is
'发生网格';
comment on column ledgerConvert.createOrg is
'创建网格id';
comment on column ledgerConvert.createOrgInternalCode is
'创建网格';
comment on column ledgerConvert.occurDate is
'发生时间';
comment on column ledgerConvert.issueId is
'事件编号';
comment on column ledgerConvert.serialNumber is
'事件编码';
comment on column ledgerConvert.name is
'当事人姓名';
comment on column ledgerConvert.MOBILE is
'当事人电话';
comment on column ledgerConvert.description is
'事件简述';
comment on column ledgerConvert.status is
'转入状态';
comment on column ledgerConvert.ledgerSerialNumber is
'三本台账编号';


insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '事件转入', 'ledgerConvertManagement', 1, '中江台账处理', 1, 
(select parentid from permissions where CNAME='统计报表' and modulename='中江台账处理' ), '', '/account/ledgerConvert/ledgerConvertList.jsp', '', 0, '');

update permissions set permissions.cname='首页' where permissions.cname = '首页信息' and permissions.modulename='中江台账处理';
update propertydicts set propertydicts.displayname='政策性直补' where propertydicts.propertydomainid=(select id from propertydomains where propertydomains.domainname='中江稳定台账涉稳问题类别') and propertydicts.displayname='正策性直补';
update propertydicts set propertydicts.displayname='军转干部' where propertydicts.propertydomainid=(select id from propertydomains where propertydomains.domainname='中江稳定台账涉稳问题类别') and propertydicts.displayname='军团干部';
alter table ledgerpoorpeople add (historyId varchar2(20)); 
COMMENT ON COLUMN ledgerpoorpeople.historyId IS '历史编号';
alter table ledgerpoorpeople drop (poorSource,poortype,REQUIREDTYPE);
alter table ledgerpoorpeople add (poorSource varchar2(200)) ;
alter table ledgerpoorpeople add(poortype varchar2(100)) ;
alter table ledgerpoorpeople add (REQUIREDTYPE varchar2(200)) ;
alter table ledgerpoorpeople add (remark varchar2(500)) ;

alter table ledgersteadywork modify involvingsteadyinfo varchar(500);
alter table ledgersteadywork add (historyId varchar2(20));
COMMENT ON COLUMN ledgersteadywork.historyId IS '历史编号';

alter table ledgersteadywork add (isAnonymity	NUMBER(1) 	default 0);
COMMENT ON COLUMN ledgersteadywork.isAnonymity IS '是否匿名';

alter table ledgersteadywork modify name 	 null;
alter table ledgersteadywork modify gender 	 null;
alter table ledgersteadywork modify position 	 null;

alter table platformAccountLogs add (submitToCommittee  NUMBER(1) default 0 ); 
COMMENT ON COLUMN platformAccountLogs.submitToCommittee IS '是否同意呈报县委';
alter table platformAccountLogs add (opinion  varchar2(500) ); 
COMMENT ON COLUMN platformAccountLogs.opinion IS '当事人意见';
alter table platformAccountLogs add (operateTime   DATE); 
COMMENT ON COLUMN platformAccountLogs.operateTime IS '台账办理时间';


update permissions set permissions.modulename='新三本台账' where permissions.modulename='中江台账处理';
update permissions set permissions.cname='新三本台账' where permissions.cname='中江台账处理';

