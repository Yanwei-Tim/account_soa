package com.tianque.sysadmin.controller;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;

@SuppressWarnings("serial")
@Controller("globalSettingController")
@Scope("prototype")
@Transactional
public class GlobalSettingController extends BaseAction  {
	
	private static Logger logger = LoggerFactory.getLogger(GlobalSettingController.class);
	@Autowired
	private GlobalSettingService globalSettingService;
	private Map<String,String> map;
	private static final String SYMLINKSTR = "ln -s %s %s";
	private static final String SYMRMLINK = "rm -fr %s";
	private boolean isCreateLink;
	private File upload;
	private File storedFile;
	private File organizationExcel;
	private String organizationExcelFileName;
	
	public String getOrganizationExcelFileName() {
		return organizationExcelFileName;
	}
	public void setOrganizationExcelFileName(String organizationExcelFileName) {
		this.organizationExcelFileName = organizationExcelFileName;
	}
	private String uploadFileName;
	private String storedFilePath;
	private String storedFileName;

	public String dispatch() {
		map=globalSettingService.getGlobalSetting();
		backImageFileName = map.get(GlobalSetting.BACK_IMAGE);
		prveImageFileName = map.get(GlobalSetting.PREV_IMAGE);
		buttonImageFileName = map.get(GlobalSetting.BUTTON_IMAGE);
		mouseMoveImageFileName = map.get(GlobalSetting.MOUSE_MOVE_IMAGE);
		simple=map.get(GlobalSetting.SIMPLE_RELEASE);
		return mode;
	}

	private void processUploadFiles(File file1,String filesFileName) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(file1);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				fileInputStream);

		File file = createStoreFile1(filesFileName);

		FileOutputStream fileOutputStream = new FileOutputStream(file);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
				fileOutputStream);

		int i = -1;
		while ((i = bufferedInputStream.read()) != -1) {
			bufferedOutputStream.write(i);
		}

		bufferedInputStream.close();
		fileInputStream.close();

		bufferedOutputStream.close();
		fileOutputStream.close();
	}
	private File createStoreFile1(String filesFileName) throws Exception {
		storedFile = new File(FileUtil.getWebRoot() + File.separator
				+ createStoreFilePath1(filesFileName) + File.separator
				+ createStoreFileName1(filesFileName));
		if(!storedFile.getParentFile().isDirectory()){
			storedFile.getParentFile().mkdirs();
		}
		if (!storedFile.exists()) {
			storedFile.createNewFile();
		}
		return storedFile;
	}
	private String createStoreFilePath1(String filesFileName) {
		storedFilePath = GridProperties.UPLOAD_FILE_FOLDER + File.separator+ getFileSuffix1(filesFileName).substring(1).toLowerCase() + File.separator
				+ Calendar.getInstance().get(Calendar.YEAR) + File.separator
				+ (Calendar.getInstance().get(Calendar.MONTH) + 1)
				+ File.separator
				+ (Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		return storedFilePath;
	}
	private String createStoreFileName1(String filesFileName) {
		storedFileName = new StringBuffer().append(
				Calendar.getInstance().get(Calendar.HOUR_OF_DAY)).append(
				Calendar.getInstance().get(Calendar.MINUTE)).append(
				Calendar.getInstance().get(Calendar.SECOND)).append(
				Calendar.getInstance().get(Calendar.MILLISECOND)).append(
				UUID.randomUUID()).append(getFileSuffix1(filesFileName)).toString();
		return storedFileName;
	}
	private String getFileSuffix1(String filesFileName) {
		return filesFileName.substring(filesFileName.lastIndexOf("."));
	}
	private void printLoginPage(Map<String,String> map) {
		try {
			
			try {
				String val;
				val = JSONUtil.serialize(map);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=GBK");
				PrintWriter printWriter = response.getWriter();
				printWriter.print(val);
			} catch (JSONException e) {
				logger.error("printLoginPage JSONException ",e);
			}
		} catch (IOException e) {
			logger.error("printLoginPage IOException ",e);
		}
	}
	private boolean rightImageTyepe(String imageSrc){
		File fileInput = new File(imageSrc);
		Image image = null;
		try {
			image = ImageIO.read(fileInput);
		} catch (IOException e) {
			logger.error("rightImageTyepe IOException ",e);
		}
		if(image==null){
			return false;
		}else{
			return true;
		}
	}
	private boolean fileLength(File file){
		boolean minLength = true;
		boolean maxLength = true;
		if(file.length()==0){
			minLength = false;
		}else if(file.length()/ 1024 / 1024 >= 2){
			maxLength = false;
		}
		if(minLength == false || maxLength == false){
			return false;
		}else{
			return true;
		}
	}
	private boolean checkFileLength(){
		boolean backLength = true;
		boolean prvelength = true;
		boolean buttonLength = true;
		boolean mouseMoveLength = true;
		boolean logoImageLength = true;
		
		boolean globle = true;
		if(backImage!=null){
			backLength = fileLength(backImage);
		}
		if(prveImage!=null){
			prvelength = fileLength(prveImage);
		}
		if(buttonImage!=null){
			buttonLength = fileLength(buttonImage);
		}
		if(mouseMoveImage!=null){
			mouseMoveLength = fileLength(mouseMoveImage);
		}
		if(logoImage!=null){
			logoImageLength = fileLength(logoImage);
		}
	    if(backLength==false || prvelength==false 
	    		|| buttonLength==false || mouseMoveLength==false || logoImageLength==false){
	    	if(backLength==false){
	    		map.put("backImageLength", "背景图片大小应在0~2M之间");
	    	}else{
	    		map.put("backImageLength", "");
	    	}
	    	if(prvelength==false){
	    		map.put("prveImageLength", "前景图片大小应在0~2M之间");
	    	}else{
	    		map.put("prveImageLength", "");
	    	}
	    	if(buttonLength==false){
	    		map.put("buttonImageLength", "按钮图片大小应在0~2M之间");
	    	}else{
	    		map.put("buttonImageLength", "");
	    	}
	    	if(mouseMoveLength==false){
	    		map.put("mouseMoveLength", "鼠标移动到按钮上效果图片大小应在0~2M之间");
	    	}else{
	    		map.put("mouseMoveLength", "");
	    	}
	    	if(logoImageLength==false){
	    		map.put("logoImageLength", "logo图片大小应在0~2M之间");
	    	}else{
	    		map.put("logoImageLength", "");
	    	}
//	    	printLoginPage(map);
	    	globle = false;
	    	return globle;
	    }
	    return globle;
	}
//	private void initFileTypeForMap(){
//		map.put("errorBackImageType", "");
//		map.put("errorPrveImageType", "");
//		map.put("errorButtonImageType", "");
//		map.put("errorMouseMoveImageType", "");
//		map.put("backImageLength", "");
//		map.put("prveImageLength", "");
//		map.put("buttonImageLength", "");
//		map.put("mouseMoveLength", "");
//	}
	public void updateGlobalLoginSetting(){
			map = new HashMap<String, String>();
			boolean backImageRightType=false;
			boolean prveImageRightType=false;
			boolean buttonImageRightType=false;
			boolean mouseMoveImageRightType=false;
			boolean logoImageRightType=false;
//			initFileTypeForMap();
			if(!checkFileLength()){
				printLoginPage(map);
				return;
			}
				try {
					if(backImage!=null){
						processUploadFiles(backImage,backImageFileName);
						storedFilePath =  getStoredFilePath()+ File.separator + getStoredFileName();
						backImageRightType = rightImageTyepe(FileUtil.getWebRoot() + File.separator
								+ storedFilePath);
						map.put(GlobalSetting.BACK_IMAGE, storedFilePath);
					}else{
						backImageRightType = true;
						map.put(GlobalSetting.BACK_IMAGE, "");
					}
					if(prveImage!=null){
						processUploadFiles(prveImage,prveImageFileName);
						storedFilePath =  getStoredFilePath()+ File.separator + getStoredFileName();
						prveImageRightType = rightImageTyepe(FileUtil.getWebRoot() + File.separator
								+ storedFilePath);
						map.put(GlobalSetting.PREV_IMAGE, storedFilePath);
					}else{
						prveImageRightType = true;
						map.put(GlobalSetting.PREV_IMAGE, "");
					}
					if(buttonImage!=null){
						processUploadFiles(buttonImage,buttonImageFileName);
						storedFilePath =  getStoredFilePath()+ File.separator + getStoredFileName();
						buttonImageRightType = rightImageTyepe(FileUtil.getWebRoot() + File.separator
								+ storedFilePath);
						map.put(GlobalSetting.BUTTON_IMAGE, storedFilePath);
					}else{
						buttonImageRightType = true;
						map.put(GlobalSetting.BUTTON_IMAGE, "");
					}
					if(mouseMoveImage!=null){
						processUploadFiles(mouseMoveImage,mouseMoveImageFileName);
						storedFilePath =  getStoredFilePath()+ File.separator + getStoredFileName();
						mouseMoveImageRightType = rightImageTyepe(FileUtil.getWebRoot() + File.separator
								+ storedFilePath);
						map.put(GlobalSetting.MOUSE_MOVE_IMAGE, storedFilePath);
					}else{
						mouseMoveImageRightType = true;
						map.put(GlobalSetting.MOUSE_MOVE_IMAGE, "");
					}
					if(logoImage!=null){
						processUploadFiles(logoImage,logoImageFileName);
						storedFilePath =  getStoredFilePath()+ File.separator + getStoredFileName();
						logoImageRightType = rightImageTyepe(FileUtil.getWebRoot() + File.separator
								+ storedFilePath);
						map.put(GlobalSetting.LOGO_IMAGE, storedFilePath);
					}else{
						logoImageRightType = true;
						map.put(GlobalSetting.LOGO_IMAGE, "");
					}
			} catch (Exception e) {
				logger.error("updateGlobalLoginSetting  ",e);
			}
			if(backImageRightType==true && prveImageRightType==true 
					&& buttonImageRightType==true && mouseMoveImageRightType==true && logoImageRightType==true){
				mergeGlobalSetting();
				printLoginPage(map);
				return ;
			}
			if(backImageRightType==false){
				map.put("errorBackImageType", "请确保背景图片格式的正确性");
			}else{
				map.put("errorBackImageType", "");
			}
			if(prveImageRightType==false){
				map.put("errorPrveImageType", "请确保前景图片格式的正确性");
			}else{
				map.put("errorPrveImageType", "");
			}
			if(buttonImageRightType==false){
				map.put("errorButtonImageType", "请确保按钮图片格式的正确性");
			}else{
				map.put("errorButtonImageType", "");
			}
			if(mouseMoveImageRightType==false){
				map.put("errorMouseMoveImageType", "请确保移动到按钮上时效果图片格式的正确性");
			}else{
				map.put("errorMouseMoveImageType", "");
			}
			if(logoImageRightType==false){
				map.put("errorlogoImageType", "请确保logo图片格式的正确性");
			}else{
				map.put("errorlogoImageType", "");
			}
			printLoginPage(map);
			return;
		}
	public void resetGlobalLoginSetting(){
		map = new HashMap<String, String>();
		map.put(GlobalSetting.BACK_IMAGE, "");
		map.put(GlobalSetting.PREV_IMAGE, "");
		map.put(GlobalSetting.BUTTON_IMAGE, "");
		map.put(GlobalSetting.MOUSE_MOVE_IMAGE, "");
		map.put(GlobalSetting.LOGO_IMAGE, "");
		mergeGlobalSetting();
		printLoginPage(map);
	}	
	public String updateGlobalSetting(){
		mergeGlobalSetting();
		return SUCCESS;
	}
	public String updateFileDirectorySetting(){
		mergeGlobalSetting();
		buildPath();
		return SUCCESS;
	}
	
	public String updateMobileVersion(){
		uploadInstallFile();
		mergeGlobalSetting();
		return SUCCESS;
	}
	
	private void createSymLink(String source,String link){
		if(!isCreateLink)
			return ;
		String webRootPath=FileUtil.getWebRoot();
		link=webRootPath+File.separator+link;
		Runtime rt = Runtime.getRuntime();  
		try {
			rt.exec(String.format(SYMRMLINK,link));
			rt.exec(String.format(SYMLINKSTR, source,link));
		} catch (IOException e) {
			throw new RuntimeException("创建软链"+link+"失败");
		}
	}
	
	private void buildPath(){
		jaguCreateLink();
		String path=map.get(GlobalSetting.DAILYLOG_PATH);
		createPathIfNotExists(path);
		createSymLink(path,GridProperties.DAILYLOG_PATH);
		
		path=map.get(GlobalSetting.ISSUE_PATH);
		createPathIfNotExists(path);
		createSymLink(path,GridProperties.ISSUE_ATTACHFILE);
		
		path=map.get(GlobalSetting.MAIL_PATH);
		createPathIfNotExists(path);
		createSymLink(path,GridProperties.MAIL_ATTACHFILE_PATH);
		
		path=map.get(GlobalSetting.TMP_PATH);
		createPathIfNotExists(path);
		createSymLink(path,GridProperties.TMP);
		
		path=map.get(GlobalSetting.UPLOADFILE_PATH);
		createPathIfNotExists(path);
		createSymLink(path,GridProperties.UPLOAD_FILE_FOLDER);
	}
	
	private void jaguCreateLink(){
		String osName=System.getProperties().get("os.name").toString();
		System.out.println(osName);
		if(osName.contains("Linux")){
			isCreateLink=true;
		}
	}
	
	private void createPathIfNotExists(String path){
		if(path==null||"".equals(path.trim()))
			return ;
		File file=new File(path);
		if(!file.exists())
			file.mkdirs();
	}

	private void mergeGlobalSetting() {
		Map<String,String> oldSetting=globalSettingService.getGlobalSetting();
		if(oldSetting==null)
			oldSetting=new HashMap<String,String>();
		if(map!=null&&map.size()!=0)
			oldSetting.putAll(map);
		map=globalSettingService.updateGlobalSetting(oldSetting);
	}
	
	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	

	private void uploadInstallFile() {
		try {
			FileInputStream fileInputStream = new FileInputStream(upload);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					fileInputStream);
			File file = createStoreFile();
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					fileOutputStream);

			int i = -1;
			while ((i = bufferedInputStream.read()) != -1) {
				bufferedOutputStream.write(i);
			}

			bufferedInputStream.close();
			fileInputStream.close();

			bufferedOutputStream.close();
			fileOutputStream.close();
			
			if(map==null)
				map=new HashMap<String,String>();
			map.put(GlobalSetting.MOBILE_APK_PATH, file.getPath());
		} catch (Exception e) {
			logger.error("uploadInstallFile  ",e);
		}
	}
	
	private File createStoreFile() throws Exception {
		storedFile = new File(FileUtil.getWebRoot() + File.separator
				+ createStoreFilePath() + File.separator
				+ createStoreFileName());
		if(!storedFile.getParentFile().isDirectory()){
			storedFile.getParentFile().mkdirs();
		}
		if (!storedFile.exists()) {
			storedFile.createNewFile();
		}
		return storedFile;
	}
	
	private String createStoreFilePath() {
		storedFilePath = GridProperties.UPLOAD_FILE_FOLDER + File.separator+ getFileSuffix().substring(1).toLowerCase() + File.separator
				+ Calendar.getInstance().get(Calendar.YEAR) + File.separator
				+ (Calendar.getInstance().get(Calendar.MONTH) + 1)
				+ File.separator
				+ (Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		return storedFilePath;
	}
	
	private String getFileSuffix() {
		return uploadFileName.substring(uploadFileName.lastIndexOf("."));
	}

	private String createStoreFileName() {
		storedFileName = new StringBuffer().append(
				Calendar.getInstance().get(Calendar.HOUR_OF_DAY)).append(
				Calendar.getInstance().get(Calendar.MINUTE)).append(
				Calendar.getInstance().get(Calendar.SECOND)).append(
				Calendar.getInstance().get(Calendar.MILLISECOND)).append(
				UUID.randomUUID()).append(getFileSuffix()).toString();
		return storedFileName;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	public String getStoredFilePath() {
		return storedFilePath;
	}
	
	public void setStoredFilePath(String storedFilePath) {
		this.storedFilePath = storedFilePath;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getStoredFileName() {
		return storedFileName;
	}

	public void setStoredFileName(String storedFileName) {
		this.storedFileName = storedFileName;
	}
	private File backImage;
	private String backImageFileName;
	
	private File prveImage;
	private String prveImageFileName;
	
	private File buttonImage;
	private String buttonImageFileName;
	
	private File mouseMoveImage;
	private String mouseMoveImageFileName;
	
	private File logoImage;
	private String logoImageFileName;
	private String simple;

	
	public String getSimple() {
		return simple;
	}

	public void setSimple(String simple) {
		this.simple = simple;
	}

	public File getBackImage() {
		return backImage;
	}
	public void setBackImage(File backImage) {
		this.backImage = backImage;
	}
	public String getBackImageFileName() {
		return backImageFileName;
	}
	public void setBackImageFileName(String backImageFileName) {
		this.backImageFileName = backImageFileName;
	}
	public File getPrveImage() {
		return prveImage;
	}
	public void setPrveImage(File prveImage) {
		this.prveImage = prveImage;
	}
	public String getPrveImageFileName() {
		return prveImageFileName;
	}
	public void setPrveImageFileName(String prveImageFileName) {
		this.prveImageFileName = prveImageFileName;
	}
	public File getButtonImage() {
		return buttonImage;
	}
	public void setButtonImage(File buttonImage) {
		this.buttonImage = buttonImage;
	}
	public String getButtonImageFileName() {
		return buttonImageFileName;
	}
	public void setButtonImageFileName(String buttonImageFileName) {
		this.buttonImageFileName = buttonImageFileName;
	}
	public File getMouseMoveImage() {
		return mouseMoveImage;
	}
	public void setMouseMoveImage(File mouseMoveImage) {
		this.mouseMoveImage = mouseMoveImage;
	}
	public String getMouseMoveImageFileName() {
		return mouseMoveImageFileName;
	}
	public void setMouseMoveImageFileName(String mouseMoveImageFileName) {
		this.mouseMoveImageFileName = mouseMoveImageFileName;
	}
	public File getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(File logoImage) {
		this.logoImage = logoImage;
	}

	public String getLogoImageFileName() {
		return logoImageFileName;
	}

	public void setLogoImageFileName(String logoImageFileName) {
		this.logoImageFileName = logoImageFileName;
	}
	public File getOrganizationExcel() {
		return organizationExcel;
	}
	public void setOrganizationExcel(File organizationExcel) {
		this.organizationExcel = organizationExcel;
	}
}
