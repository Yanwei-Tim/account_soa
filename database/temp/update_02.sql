insert into permissions
 (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '中江台账处理', 'serviceWorkThreeRecords', 1, ' ', 1, null, '', '', '', 20, '');

insert into permissions 
(ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '首页信息', 'homePageListManagement', 1, '中江台账处理', 1, 
(select id from permissions where ename='serviceWorkThreeRecords'), '', '/account/homePage.jsp', '', 0, '');

insert into permissions
 (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '民生信息', 'threeRecordsListManagement', 1, '中江台账处理', 1,
 (select id from permissions where ename='serviceWorkThreeRecords'), '', '', '', 1, '');

insert into permissions 
(ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '困难群众信息', 'difficultPeopleRecordManagement', 1, '中江台账处理', 1,
(select id from permissions where ename='serviceWorkThreeRecords'), '', '/account/difficultPeopleRecord/ledgerPoorPeopleRecordList.jsp', '', 2, '');

insert into permissions 
(ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '稳定信息', 'steadyRecordManagement', 1, '中江台账处理', 1, 
(select id from permissions where ename='serviceWorkThreeRecords'), '', '/account/steadyRecord/ledgerSteadyWorkRecordList.jsp', '', 3, '');

insert into permissions 
(ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '统计报表', 'ledgerReportManagement', 1, '中江台账处理', 1, 
(select id from permissions where ename='serviceWorkThreeRecords'), '', '', '', 4, '');

--民生信息下辖所有权限
insert into permissions 
(ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '水利信息', 'waterResourcesListManagement', 1, '中江水利处理', 1, 
(select id from permissions where ename='threeRecordsListManagement'), '', '/account/peopleAspiration/waterResourceList.jsp', '', 0, '');

insert into permissions 
(ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '交通信息', 'trafficListManagement', 1, '中江交通处理', 1, 
(select id from permissions where ename='threeRecordsListManagement'), '', '/account/peopleAspiration/trafficList.jsp', '', 1, '');

insert into permissions 
(ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '能源信息', 'energyListManagement', 1, '中江能源处理', 1, 
(select id from permissions where ename='threeRecordsListManagement'), '', '/account/peopleAspiration/energyList.jsp', '', 2, '');

insert into permissions 
(ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '教育信息', 'educationListManagement', 1, '中江教育处理', 1, 
(select id from permissions where ename='threeRecordsListManagement'), '', '/account/peopleAspiration/educationList.jsp', '',3, '');

insert into permissions 
(ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '科技文体信息', 'scienceListManagement', 1, '中江科技文体处理', 1, 
(select id from permissions where ename='threeRecordsListManagement'), '', '/account/peopleAspiration/scienceLi
st.jsp', '', 4, '');

insert into permissions 
(ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '医疗卫生信息', 'medicalListManagement', 1, '中江医疗卫生处理', 1, 
(select id from permissions where ename='threeRecordsListManagement'), '', '/account/peopleAspiration/medicalList.jsp', '', 5, '');

insert into permissions 
(ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '劳动和社会保障信息', 'laborListManagement', 1, '中江劳动和社会保障处理', 1, 
(select id from permissions where ename='threeRecordsListManagement'), '', '/account/peopleAspiration/laborList.jsp', '', 6, '');

insert into permissions 
(ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '环境保护信息', 'environmentListManagement', 1, '中江环境保护处理', 1, 
(select id from permissions where ename='threeRecordsListManagement'), '', '/account/peopleAspiration/environmentList.jsp', '', 7, '');

insert into permissions 
(ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '城乡规划建设管理信息', 'townListManagement', 1, '中江劳动和社会保障处理', 1, 
(select id from permissions where ename='threeRecordsListManagement'), '', '/account/peopleAspiration/townList.jsp', '', 8, '');

insert into permissions
(ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '农业信息', 'agricultureListManagement', 1, '中江农业资源处理', 1, 
(select id from permissions where ename='threeRecordsListManagement'), '', '/account/peopleAspiration/agricultureList.jsp', '', 9, '');

insert into permissions 
(ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '其他信息', 'otherListManagement', 1, '中江农业资源处理', 1, 
(select id from permissions where ename='threeRecordsListManagement'), '', '/account/peopleAspiration/otherList.jsp', '', 10, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addWaterResource', 0, '水利信息', 1, (select id from permissions where ename='waterResourcesListManagement'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateWaterResource', 0, '水利信息', 1,  (select id from permissions where ename='waterResourcesListManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteWaterResource', 0, '水利信息', 1,  (select id from permissions where ename='waterResourcesListManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '导入', 'importWaterResource', 0, '水利信息', 1,  (select id from permissions where ename='waterResourcesListManagement'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'queryWaterResource', 0, '水利信息', 1,  (select id from permissions where ename='waterResourcesListManagement'), '', '', '', 4, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addTraffic', 0, '交通信息', 1,  (select id from permissions where ename='trafficListManagement'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateTraffic', 0, '交通信息', 1, (select id from permissions where ename='trafficListManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteTraffic', 0, '交通信息', 1, (select id from permissions where ename='trafficListManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'queryTraffic', 0, '交通信息', 1, (select id from permissions where ename='trafficListManagement'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addEnergy', 0, '能源信息', 1, (select id from permissions where ename='energyListManagement'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateEnergy', 0, '能源信息', 1, (select id from permissions where ename='energyListManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteEnergy', 0, '能源信息', 1, (select id from permissions where ename='energyListManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'queryEnergy', 0, '能源信息', 1, (select id from permissions where ename='energyListManagement'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addEducation', 0, '教育信息', 1, (select id from permissions where ename='educationListManagement'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateEducation', 0, '教育信息', 1, (select id from permissions where ename='educationListManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteEducation', 0, '教育信息', 1, (select id from permissions where ename='educationListManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'queryEducation', 0, '教育信息', 1, (select id from permissions where ename='educationListManagement'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addScience', 0, '科技文体信息', 1, (select id from permissions where ename='scienceListManagement'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateScience', 0, '科技文体信息', 1, (select id from permissions where ename='scienceListManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteScience', 0, '科技文体信息', 1, (select id from permissions where ename='scienceListManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'queryScience', 0, '科技文体信息', 1, (select id from permissions where ename='scienceListManagement'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addMedical', 0, '医疗卫生信息', 1, (select id from permissions where ename='medicalListManagement'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateMedical', 0, '医疗卫生信息', 1, (select id from permissions where ename='medicalListManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteMedical', 0, '医疗卫生信息', 1, (select id from permissions where ename='medicalListManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'queryMedical', 0, '医疗卫生信息', 1, (select id from permissions where ename='medicalListManagement'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addLabor', 0, '劳动和社会保障信息', 1, (select id from permissions where ename='laborListManagement'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateLabor', 0, '劳动和社会保障信息', 1, (select id from permissions where ename='laborListManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteLabor', 0, '劳动和社会保障信息', 1, (select id from permissions where ename='laborListManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'queryLabor', 0, '劳动和社会保障信息', 1, (select id from permissions where ename='laborListManagement'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addEnvironment', 0, '环境保护信息', 1, (select id from permissions where ename='environmentListManagement'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateEnvironment', 0, '环境保护信息', 1, (select id from permissions where ename='environmentListManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteEnvironment', 0, '环境保护信息', 1, (select id from permissions where ename='environmentListManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'queryEnvironment', 0, '环境保护信息', 1, (select id from permissions where ename='environmentListManagement'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addTown', 0, '城乡规划建设管理信息', 1, (select id from permissions where ename='townListManagement'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateTown', 0, '城乡规划建设管理信息', 1, (select id from permissions where ename='townListManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteTown', 0, '城乡规划建设管理信息', 1, (select id from permissions where ename='townListManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'queryTown', 0, '城乡规划建设管理信息', 1, (select id from permissions where ename='townListManagement'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addAgriculture', 0, '农业信息', 1, (select id from permissions where ename='agricultureListManagement'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateAgriculture', 0, '农业信息', 1, (select id from permissions where ename='agricultureListManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteAgriculture', 0, '农业信息', 1, (select id from permissions where ename='agricultureListManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'queryAgriculture', 0, '农业信息', 1, (select id from permissions where ename='agricultureListManagement'), '', '', '', 3, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addOther', 0, '其他资源', 1, (select id from permissions where ename='otherListManagement'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateOther', 0, '其他资源', 1, (select id from permissions where ename='otherListManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteOther', 0, '其他资源', 1, (select id from permissions where ename='otherListManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'queryOther', 0, '其他资源', 1, (select id from permissions where ename='otherListManagement'), '', '', '', 3, '');

--困难群众信息
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addDifficultPeopleRecord', 0, '困难群众信息', 1,
 (select id from permissions where ename = 'difficultPeopleRecordManagement'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateDifficultPeopleRecord', 0, '困难群众信息', 1,  
(select id from permissions where ename = 'difficultPeopleRecordManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteDifficultPeopleRecord', 0, '困难群众信息', 1, 
 (select id from permissions where ename = 'difficultPeopleRecordManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'queryDifficultPeopleRecord', 0, '困难群众信息', 1, 
 (select id from permissions where ename = 'difficultPeopleRecordManagement'), '', '', '', 3, '');

--稳定
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '新增', 'addLedgerSteadyWorkRecord', 0, '稳定信息', 1, (select id from permissions where ename='steadyRecordManagement'), '', '', '', 0, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '修改', 'updateLedgerSteadyWorkRecord', 0, '稳定信息', 1, 
(select id from permissions where ename='steadyRecordManagement'), '', '', '', 1, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '删除', 'deleteLedgerSteadyWorkRecord', 0, '稳定信息', 1, 
(select id from permissions where ename='steadyRecordManagement'), '', '', '', 2, '');

insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '查询', 'queryLedgerSteadyWorkRecord', 0, '稳定信息', 1, 
(select id from permissions where ename='steadyRecordManagement'), '', '', '', 3, '');

--统计报表
insert into permissions (ID, CNAME, ENAME, PERMISSIONTYPE, MODULENAME, ENABLE, PARENTID, DESCRIPTION, NORMALURL, LEADERURL, INDEXID, GRIDURL)
values (s_permissions.nextval, '工作月报表', 'ledgerReportWorkMonth', 1, '统计报表', 1, (select id from permissions where ename='ledgerReportManagement'), '', '/account/report/index.jsp?reportType=1', '', 0, '');



commit;


