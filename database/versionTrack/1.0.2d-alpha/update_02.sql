insert into propertydomains (ID, DOMAINNAME, SYSTEMSENSITIVE, SYSTEMRESTRICT)
values (s_propertyDomains.NEXTVAL, '交办单会议类型', 0, null);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='交办单会议类型'), 0, 1, '县委常委会', 'xwcwh', 'xianweichangweihui', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='交办单会议类型'), 0, 2, '县政府常务会', 'xzfcwh', 'xianzhengfuchangwuhu', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='交办单会议类型'), 0, 3, '专题会议', 'zthy', 'zhuantihuiyi', 'admin', sysdate);

insert into propertydicts (ID, PROPERTYDOMAINID, INTERNALID, DISPLAYSEQ, DISPLAYNAME, SIMPLEPINYIN, FULLPINYIN, CREATEUSER, CREATEDATE)
values (s_propertyDicts.Nextval, (select id from propertydomains where domainname='交办单会议类型'), 0, 4, '其他会议', 'qthy', 'qitahuiyi', 'admin', sysdate);
commit;
