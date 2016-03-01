<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<div id="accordion" style="display: none;">

    <pop:JugePermissionTag ename="gridIntroductionInformation">
	<div>
		  <h1><a href="javascript:void(0)"></a></h1>
		  <ul>
			<pop:JugePermissionTag ename="gridIntroductionManagement">
				<li id="choose"><a id="orgIntroduce" href="javascript:void(0)" onclick="showJspByOrgLevel(this);"><span><s:property value="#request.name"/></span></a></li>
			</pop:JugePermissionTag>
		 </ul>
	</div>
 	</pop:JugePermissionTag>
  	<pop:JugePermissionTag ename="peopleInformation">
	<div>
		  <h1><a href="javascript:void(0)">重点人员</a></h1>
		  <ul>
			<pop:JugePermissionTag ename="druggyManagement">
				<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/druggyManage/druggyList.jsp')"><span>吸毒人员</span></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="superiorVisitManagement">
			   	<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/superiorVisit/superiorVisitList.jsp')"><span>重点上访人员</span></a></li>
			</pop:JugePermissionTag>
		   	<pop:JugePermissionTag ename="mentalPatientManagement">
				<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/mentalPatient/mentalPatientlist.jsp?')"><span>精神病人员</span></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="positiveInfoManagement">
		       	<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/positiveInfo/positiveInfoList.jsp')"><span>刑释解教人员</span></a></li>
		   	</pop:JugePermissionTag>
		  	<pop:JugePermissionTag ename="rectificativePersonManageMent">
		       	<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/rectify/rectifiyList.jsp')"><span>矫正人员</span></a></li>
		   	</pop:JugePermissionTag>
		   	<pop:JugePermissionTag ename="idleYouthManagement">
			  	<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/idleYouth/idleYouthList.jsp')"><span>闲散青少年</span></a></li>
		   	</pop:JugePermissionTag>
		   <pop:JugePermissionTag ename="dangerousGoodsPractitionerManagement">
		   	<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/dangerousGoodsPractitioner/dangerousGoodsPractitionerList.jsp')"><span>危险品从业人员</span></a></li>
		   	</pop:JugePermissionTag>
		   	<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/poorPeopleManage/poorPeopleList.jsp')"><span>困难群众</span></a></li>
		   	<pop:JugePermissionTag ename="poorPeopleManagement">
		   	</pop:JugePermissionTag>
		   	<pop:JugePermissionTag ename="dangerTrampResidentManagement">
			  	<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/dangerTrampResident/dangerTrampResidentList.jsp')"><span>高危流动人口</span></a></li>
		   	</pop:JugePermissionTag>
		   	<pop:JugePermissionTag ename="otherAttentionPersonnelManagement">
			  	<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/otherAttentionPersonnel/otherAttentionPersonnelList.jsp')"><span>其他人员</span></a></li>
		   	</pop:JugePermissionTag>
		 </ul>
	</div>
 	</pop:JugePermissionTag>
 	<pop:JugePermissionTag ename="locationInformation">
	<div>
		 <h1><a href="javascript:void(0)">重点场所</a></h1>
		 <ul>
		   	<pop:JugePermissionTag ename="lettingHouseManagement">
			  	<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/siteinfo/lettingHouse/lettingHouseList.jsp')"><span>出租房</span></a></li>
		   	</pop:JugePermissionTag>
		    <pop:JugePermissionTag ename="enterpriseManagement">
		   		<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/siteinfo/enterprise/enterpriseList.jsp?keyType=enterpriseKey')"><span>规上企业</span></a></li>
		   	</pop:JugePermissionTag>
		   	<pop:JugePermissionTag ename="enterpriseManagement">
		   		<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/siteinfo/enterprise/enterpriseList.jsp?keyType=protectionKey')"><span>重点保障</span></a></li>
		   	</pop:JugePermissionTag>
		   	<pop:JugePermissionTag ename="enterpriseManagement">
		   		<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/siteinfo/enterprise/enterpriseList.jsp?keyType=safetyProductionKey')"><span>安全生产重点</span></a></li>
		   	</pop:JugePermissionTag>
		   	<pop:JugePermissionTag ename="enterpriseManagement">
		   		<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/siteinfo/enterprise/enterpriseList.jsp?keyType=fireSafetyKey')"><span>消防安全重点</span></a></li>
		   	</pop:JugePermissionTag>
		   	<pop:JugePermissionTag ename="enterpriseManagement">
		   		<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/siteinfo/enterprise/enterpriseList.jsp?keyType=securityKey')"><span>治安重点</span></a></li>
		   	</pop:JugePermissionTag>
		   	<pop:JugePermissionTag ename="schoolManageMent">
			  	<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/siteinfo/school/schoolList.jsp')"><span>学校</span></a></li>
		   	</pop:JugePermissionTag>
		   	<pop:JugePermissionTag ename="HospitalManagement">
			  	<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/siteinfo/hospital/hospitalList.jsp')"><span>医院</span></a></li>
		   	</pop:JugePermissionTag>
		    <pop:JugePermissionTag ename="commonComplexPlaceManagement">
				<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/siteinfo/commonComplexPlace/commonComplexPlaceList.jsp')"><span>公共复杂场所</span></a></li>
		   	</pop:JugePermissionTag>
		   	<pop:JugePermissionTag ename="specialTradeManagement">
		   		<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/siteinfo/specialTrade/specialTradeList.jsp')"><span>特种行业</span></a></li>
		   	</pop:JugePermissionTag>
		    <pop:JugePermissionTag ename="newSocietyFederationManagement">
		   		<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/siteinfo/newSocietyFederation/newSocietyFederationList.jsp')"><span>社会组织</span></a></li>
		   	</pop:JugePermissionTag>
		    <pop:JugePermissionTag ename="otherLocaleManagement">
		    	<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/siteinfo/otherLocale/otherLocaleList.jsp')"><span>其他场所</span></a></li>
		   	</pop:JugePermissionTag>
		 </ul>
	</div>
	 </pop:JugePermissionTag>
<!--
	 <pop:JugePermissionTag ename="serviceWorkContradiction">
        <div>
             <h1><a href="javascript:void(0)">矛盾纠纷</a></h1>
             <ul>
               <pop:JugePermissionTag ename="contradictionManagement">
                    <li><a href="javascript:void(0)"
                        onclick="asyncMiddleLevelOpen(this,'${path}/contradiction/contradictionManageNew/preContradictionList.action')"><span>矛盾纠纷</span></a></li>
               </pop:JugePermissionTag>
             </ul>
        </div>
     </pop:JugePermissionTag>
 -->
  	<pop:JugePermissionTag ename="trampResidentInformation">
	<div>
		  <h1><a href="javascript:void(0)">流动人口</a></h1>
		  <ul>
			<pop:JugePermissionTag ename="trampResidentManagement">
				<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/trampResident/trampResidentList.jsp')"><span>流动人口</span></a></li>
			</pop:JugePermissionTag>
		 </ul>
	</div>
 	</pop:JugePermissionTag>
 	<pop:JugePermissionTag ename="residentInformation">
	<div>
		  <h1><a href="javascript:void(0)">常住人口</a></h1>
		  <ul>
			<pop:JugePermissionTag ename="residentManagement">
				<li><a href="javascript:void(0)" onclick="asyncOpen(this,'${path}/baseinfo/resident/residentList.jsp')"><span>常住人口</span></a></li>
			</pop:JugePermissionTag>
		 </ul>
	</div>
 	</pop:JugePermissionTag>
</div>
<script>
	$(function() {
		$("#accordion").show();
		$("#accordion").accordionFunction("h1","div");
		$('#accordion > div > ul > li:first > a').click();
	});
	function showJspByOrgLevel(orgIntroduce){
		if(isCountryDownOrganization()){
			asyncOpen(orgIntroduce,'${path}/baseinfo/villageProfile/villageProfileComplete.jsp');
		}else{

			asyncOpen(orgIntroduce,'${path}/baseinfo/villageProfile/introduction.jsp');
		}
	};
</script>
