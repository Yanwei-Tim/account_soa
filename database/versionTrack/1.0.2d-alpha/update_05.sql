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