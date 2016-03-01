/////const //////////////////////////

/////define object  /////////////////////////////////
try
{
    document.writeln("<OBJECT classid=\"clsid:FCAA4851-9E71-4BFE-8E55-212B5373F040\" height=1 id=bjcactrl  style=\"HEIGHT: 1px; LEFT: 10px; TOP: 28px; WIDTH: 1px\" width=1 VIEWASTEXT>");
    document.writeln("</OBJECT>");
    bjcactrl.GetUserListPnp();	 
}
catch(e)
{
	alert("ActiveXObject BJCASECCOM.dll����û��ע��ɹ���"+e.message);
}

var errorMsg = "��ȷ�ϲ�����ȷ��֤����ʻ����µ�¼ϵͳ��";

/////����ӿ�ת��Ϊ�ű��ӿ�////////////////////////
var g_xmluserlist;

//�õ��û��б�
function GetUserList() {

     try
     {
	    g_xmluserlist = bjcactrl.GetUserList();
     }
     catch(e)
     {
	    g_xmluserlist="";
	    alert(e.message);
    	
     }
	    return g_xmluserlist;
	    
}

//�õ��û���Ϣ
function GetCertBasicinfo(Cert, Type) 
{

	return bjcactrl.GetCertInfo(Cert,Type);
	
}
function GetExtCertInfoByOID(Cert, oid) 
{
    
    return bjcactrl.GetCertInfoByOid(Cert,oid);
}

//��¼
function Login(strFormName,strContainerName,strPin) {
	var ret;
	var objForm = eval(strFormName);

	if (objForm == null) {
		alert("Form Error");
		return false;
	}
	if (strPin == null || strPin == "") {
		alert("������Key�ı�������");
		return false;
	}
	//Add a hidden item ...
	var strSignItem = "<input type=\"hidden\" name=\"UserSignedData\" value=\"\">";
	if (objForm.UserSignedData == null) {
		objForm.insertAdjacentHTML("BeforeEnd",strSignItem);
	}
	var strCertItem = "<input type=\"hidden\" name=\"UserCert\" value=\"\">";
	if (objForm.UserCert == null) {
		objForm.insertAdjacentHTML("BeforeEnd",strCertItem);
	}
	var strContainerItem = "<input type=\"hidden\" name=\"ContainerName\" value=\"\">";
	if (objForm.ContainerName == null) {
		objForm.insertAdjacentHTML("BeforeEnd",strContainerItem);
	}


    if(!bjcactrl.UserLogin(strContainerName,strPin))
    {
    	
    	var extLib = bjcactrl.GetUserInfo(strContainerName, 15);
    	
    	var remainTimes = bjcactrl.GetBjcaKeyParam(extLib , 8);
    	if(Number(remainTimes)<=0){
    		alert("��������10�δ�������,֤���Ѿ�������,�뾡����ϵ������Ա����");
    		return false;
    	}
        alert("��¼ʧ��,ʣ���������Դ���" + remainTimes);
        return false;
    }
    
    strClientSignedData = SignedData(strContainerName,strServerRan);
    //alert("strServerCert:"+strServerCert+"\n\nstrServerRan:"+strServerRan+"\n\nstrServerSignedData:"+strServerSignedData);
    if(!VerifySignedData(strServerCert,strServerRan,strServerSignedData))
    {
        
        alert("��֤����������Ϣʧ��!");
        return false;
    }
    
    objForm.UserSignedData.value = strClientSignedData;
	var varCert =  GetSignCert(strContainerName);
	objForm.UserCert.value =  varCert;
	objForm.ContainerName.value = strContainerName;
    
    return alertValidDay(varCert);
    
}

//���֤��Ωһ��ʶ����ȡBase64�����֤���ַ�ָ����ȡ���ܣ�������֤�顣
function GetExchCert(strContainerName)
{  
    var UserCert = bjcactrl.ExportExChangeUserCert(strContainerName);
   
    return UserCert;
}

//ǩ��֤��
function GetSignCert(strContainerName)
{  
    var UserCert = bjcactrl.ExportUserCert(strContainerName);
   
    return UserCert;
}


//xmlǩ��
function SignedDataXML(signdata,ContainerName)
{
	return bjcactrl.SignDataXML(ContainerName,signdata);
}

function SignedData(sContainerName,sInData) 
{
	if (sContainerName != null)
		return bjcactrl.SignData(sContainerName,sInData);
	else
		return "";
}


function VerifySignedData(cert,indata,signvalue)
{
    return bjcactrl.VerifySignedData(cert,indata,signvalue);

}


function PubKeyEncrypt(exchCert,inData)
{
	try
	{
		var ret = bjcactrl.PubKeyEncrypt(exchCert,inData);
		return ret;
	}
    catch(e)
    {
    	 
    }
}


function PriKeyDecrypt(sContainerName,inData)
{
	try
	{
		var ret = bjcactrl.PriKeyDecrypt(sContainerName,inData);
		return ret;
	}
    catch(e)
    {
	 
    }
}


function EncryptData(sKey,inData)
{
	try
	{
		var ret = bjcactrl.EncryptData(sKey,inData);
		return ret;
	}
    catch(e)
    {
	 
    }
}


function DecryptData(sKey,inData)
{
	try
	{
		var ret = bjcactrl.DecryptData(sKey,inData);
		return ret;
	}
    catch(e)
    {
	 
    }
}

function GenerateRandom(RandomLen) 
{

	return bjcactrl.GenRandom(RandomLen);
}


//�ļ�ǩ�� ����ǩ�����
function SignFile(sFileName,sContainerName)
{
	 return bjcactrl.SignFile(sContainerName,sFileName)
}

function VerifySignFile(sFileName,sCert,SignData)
{
	 return bjcactrl.VerifySignedFile(sCert,sFileName,SignData);
}

//�޸�����
function ChangeUserPassword(strContainerName,oldPwd,newPwd)
{	
	return bjcactrl.ChangePasswd(strContainerName,oldPwd,newPwd);
}

//xmlǩ��
function SignedDataXML(signdata,ContainerName)
{
	return bjcactrl.SignDataXML(ContainerName,signdata);
}

//xml��֤ǩ��
function VerifySignXML(signxml)
{
	return bjcactrl.verifySignedDataXML(signxml);
}

//p7ǩ��
function SignByP7(CertID,InData)
{
    return bjcactrl.SignDataByP7(CertID,InData)
}

//��֤p7ǩ��
function VerifyDatabyP7(P7Data)
{
    return bjcactrl.VerifySignedDatabyP7(P7Data);
}

//p7�����ŷ����
function EncodeP7Enveloped(Cert,InData)
{
    return bjcactrl.EncodeP7EnvelopedData(Cert,InData);
}

//p7�����ŷ����
function DecodeP7Enveloped(CertID,InData)
{ 
    return bjcactrl.DecodeP7EnvelopedData(CertID,InData);
}

//�ж�֤����Ч������
function alertValidDay(cert){
	var endDate = GetCertBasicinfo(cert,12);
	var nowDate = new Date().Format("yyyy/MM/dd");
	var days = (Date.parse(endDate)-Date.parse(nowDate))/(1000*60*60*24);
	if(days<=60&&days>0){
		alert("���֤�黹��"+days+"�����,�뾡�����");
	}
	if(days<=-45){
		alert("���֤���Ѿ�����"+(-days)+"��,���������ʹ������,�뾡�����");
		return false;
	}
	if(days<=0){
		alert("���֤���Ѿ�����"+(-days)+"��,�뾡�����");
	}
	return true;
}
