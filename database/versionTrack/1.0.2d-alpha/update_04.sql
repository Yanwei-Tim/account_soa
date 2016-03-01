create sequence S_accountAcceptForm
minvalue 1
maxvalue 9999999999
start with 1
increment by 1
cache 20;

CREATE TABLE accountAcceptForm (
    id number(10) not null,  
	stepId   number(10),
    ledgerId number(10),  
    ledgerType number(10),  
    acceptOrg number(10),  
    formComeDate DATE,  
    formAcceptDate DATE,  
    formType number(10) not null,
    currentOrgOpinion varchar2(50),  
    inChargeOfLeaderOpinion varchar2(50),  
    majorLeaderOpinion varchar2(50),  
	handleResult  varchar2(50),
    createUser           VARCHAR2(32),
  	updateUser           VARCHAR2(32),
    createDate           DATE,
    updateDate           DATE,
	serialNumber    varchar2(50),
	dealUserName    varchar2(100),
	constraint PKaccountAcceptForm primary key (id)
) ;
comment on table accountAcceptForm  is '受理单';
comment on column accountAcceptForm.id  is '受理单 ID';
comment on column accountAcceptForm.stepId  is '步骤 ID';
comment on column accountAcceptForm.ledgerId  is '台账编号';
comment on column accountAcceptForm.ledgerType  is '台账类型';
comment on column accountAcceptForm.acceptOrg  is '受理部门';
comment on column accountAcceptForm.formComeDate  is '来件时间';
comment on column accountAcceptForm.formAcceptDate  is '接件时间';
comment on column accountAcceptForm.formType  is '来件类型';
comment on column accountAcceptForm.currentOrgOpinion  is '本级台账办拟办意见';
comment on column accountAcceptForm.inChargeOfLeaderOpinion  is '分管领导意见';
comment on column accountAcceptForm.majorLeaderOpinion  is '主要领导意见';
comment on column accountAcceptForm.handleResult  is '办理结果';
comment on column accountAcceptForm.serialNumber  is '台账编码';
comment on column accountAcceptForm.dealUserName  is '承办人';

commit;


--三本台账工作处理单来件类型数据字典
insert into propertydomains(id, domainname)values(s_propertydomains.NEXTVAL, '来件类型');
  
insert into propertydicts(ID,PROPERTYDOMAINID,INTERNALID,DISPLAYSEQ,DISPLAYNAME,SIMPLEPINYIN,FULLPINYIN,CREATEUSER,UPDATEUSER,CREATEDATE,UPDATEDATE)
values(s_propertydicts.nextval,(select id from propertydomains p where domainname = '来件类型'),0,0,'呈报件','cbj','chengbaojian','admin','',sysdate,null);
   
insert into propertydicts(ID,PROPERTYDOMAINID,INTERNALID,DISPLAYSEQ,DISPLAYNAME,SIMPLEPINYIN,FULLPINYIN,CREATEUSER,UPDATEUSER,CREATEDATE,UPDATEDATE)
values(s_propertydicts.nextval,(select id from propertydomains p where domainname = '来件类型'),0,1,'交办件','jbj','jiaobanjian','admin',
   '',sysdate,null);
   
insert into propertydicts(ID,PROPERTYDOMAINID,INTERNALID,DISPLAYSEQ,DISPLAYNAME,SIMPLEPINYIN,FULLPINYIN,CREATEUSER,UPDATEUSER,CREATEDATE,UPDATEDATE)
values(s_propertydicts.nextval,(select id from propertydomains p where domainname = '来件类型'),0,2,'转办件','zbj','zhuanbanjian','admin',
   '',sysdate,null);
   
insert into propertydicts(ID,PROPERTYDOMAINID,INTERNALID,DISPLAYSEQ,DISPLAYNAME,SIMPLEPINYIN,FULLPINYIN,CREATEUSER,UPDATEUSER,CREATEDATE,UPDATEDATE)
values(s_propertydicts.nextval,(select id from propertydomains p where domainname = '来件类型'),0,3,'来电来信来访件','ldlxlfj','laidianlaixinlaifangjian','admin','', sysdate, null);


insert into propertydicts(ID,PROPERTYDOMAINID,INTERNALID,DISPLAYSEQ,DISPLAYNAME,SIMPLEPINYIN,FULLPINYIN,CREATEUSER,UPDATEUSER,CREATEDATE,UPDATEDATE)
values(s_propertydicts.nextval,(select id from propertydomains p where domainname = '来件类型'),0,4,'干部走基层信息搜集','gbzjcxxsj','ganbuzoujicengxinxisouji','admin',
   '',sysdate,null);
   
   insert into propertydicts(ID,PROPERTYDOMAINID,INTERNALID,DISPLAYSEQ,DISPLAYNAME,SIMPLEPINYIN,FULLPINYIN,CREATEUSER,UPDATEUSER,CREATEDATE,UPDATEDATE)
values(s_propertydicts.nextval,(select id from propertydomains p where domainname = '来件类型'),0,5,'其他','qt','qita','admin',
   '',sysdate,null);