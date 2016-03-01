package com.tianque.sysadmin.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.imgscalr.Scalr;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.domain.Photo;

@SuppressWarnings("serial")
@Controller("imageUploadController")
@Scope("request")
public class ImageUploadController extends BaseAction {
	private File header; // 上传照片
	private File filedata;
	private String filedataFileName;
	private String headerFileName;
	public Photo photo;

	private String storedFilePath;
	private String storedFileName;
	private File storedFile;

	private String srcPath;
	private String subPath;
	private int cropX;
	private int cropY;
	private int cropWidth;
	private int cropHeight;
	private int imgWidth;
	private int imgHeight;
	private String filename;
	private String msg;
	private String err;

	public void scanAndCutImage() throws Exception {
		scanImage(FileUtil.getWebRoot() + File.separator + srcPath, imgWidth,
				imgHeight);
		cut(new File(FileUtil.getWebRoot() + File.separator + srcPath),
				new File(FileUtil.getWebRoot() + File.separator + srcPath),
				cropX, cropY, cropWidth, cropHeight);

	}

	private void scanImage(String imageSrc, int imgWidth, int imgHeight)
			throws Exception {
		File fileInput = new File(imageSrc);
		BufferedImage img = ImageIO.read(fileInput);
		BufferedImage scanBufferedImage = Scalr.resize(img, imgWidth,
				imgHeight, Scalr.OP_BRIGHTER);
		ImageIO.write(scanBufferedImage, "jpeg", fileInput);
	}

	private void cut(File file, File targetFile, int x, int y, int width,
			int height) throws IOException {
		ImageIO.write(Scalr.crop(ImageIO.read(file), x, y, width, height,
				Scalr.OP_BRIGHTER), "jpeg", targetFile);
	}

	public void uploadImg() throws Exception {
		File file = createStoreFile();
		BufferedImage im = ImageIO.read(header);
		ImageIO.write(im, getFileSuffix().substring(1), file);
		printOutData();

	}
	
	public void ajaxUploadImg() throws Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);;
		headerFileName = filedataFileName;
		File file = createStoreFile();
		BufferedImage im = ImageIO.read(filedata);
		ImageIO.write(im, getFileSuffix().substring(1), file);
		String projectBasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		msg = projectBasePath + File.separator+ GridProperties.UPLOAD_FILE_FOLDER + storedFilePath
				+ File.separator + storedFileName	;	
		msg = msg.replaceAll("\\\\", "/");
		printInfo("",msg);

	}
	/**
	 * 使用I/O流输出 json格式的数据
	 * @param response
	 * @param err
	 * @param newFileName
	 * @throws IOException
	 */
	public void printInfo( String err, String newFileName) throws IOException {
		ActionContext ctx = ActionContext.getContext();
		ctx.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ctx
				.get(ServletActionContext.HTTP_RESPONSE);
		PrintWriter out = response.getWriter();
		out.println("{\"err\":\"" + err + "\",\"msg\":\"" + newFileName + "\"}");
		out.flush();
		out.close();
	}
	private void printOutData() throws IOException {
		ActionContext ctx = ActionContext.getContext();
		ctx.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ctx
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.print(GridProperties.UPLOAD_FILE_FOLDER + storedFilePath
					+ File.separator + storedFileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
				pw = null;
			}
		}
	}

	protected File createStoreFile() throws Exception {
		storedFile = new File(FileUtil.getWebRoot() + File.separator
				+ GridProperties.UPLOAD_FILE_FOLDER + File.separator
				+ createStoreFilePath() + File.separator
				+ createStoreFileName());
		if (!storedFile.getParentFile().isDirectory()) {
			storedFile.getParentFile().mkdirs();
		}
		if (!storedFile.exists()) {
			storedFile.createNewFile();
		}
		return storedFile;
	}

	private String getFileSuffix() {
		return headerFileName.substring(headerFileName.lastIndexOf("."))
				.toLowerCase();
	}

	protected String createStoreFilePath() {
		storedFilePath = File.separator + getFileSuffix().substring(1)
				+ File.separator + Calendar.getInstance().get(Calendar.YEAR)
				+ File.separator
				+ (Calendar.getInstance().get(Calendar.MONTH) + 1)
				+ File.separator
				+ (Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		return storedFilePath;
	}

	protected String createStoreFileName() {
		storedFileName = new StringBuffer().append(
				Calendar.getInstance().get(Calendar.HOUR_OF_DAY)).append(
				Calendar.getInstance().get(Calendar.MINUTE)).append(
				Calendar.getInstance().get(Calendar.SECOND)).append(
				Calendar.getInstance().get(Calendar.MILLISECOND)).append(
				(int) Math.random() * 1000).append(getFileSuffix()).toString();
		return storedFileName;
	}
	public File getFiledata() {
		return filedata;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}
	public File getHeader() {
		return header;
	}

	public void setHeader(File header) {
		this.header = header;
	}

	public String getHeaderFileName() {
		return headerFileName;
	}

	public void setHeaderFileName(String headerFileName) {
		this.headerFileName = headerFileName;
	}

	public String getStoredFilePath() {
		return storedFilePath;
	}

	public void setStoredFilePath(String storedFilePath) {
		this.storedFilePath = storedFilePath;
	}

	public String getStoredFileName() {
		return storedFileName;
	}

	public void setStoredFileName(String storedFileName) {
		this.storedFileName = storedFileName;
	}

	public File getStoredFile() {
		return storedFile;
	}

	public void setStoredFile(File storedFile) {
		this.storedFile = storedFile;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public String getSrcPath() {
		return srcPath;
	}

	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}

	public String getSubPath() {
		return subPath;
	}

	public void setSubPath(String subPath) {
		this.subPath = subPath;
	}

	public int getCropX() {
		return cropX;
	}

	public void setCropX(int cropX) {
		this.cropX = cropX;
	}

	public int getCropY() {
		return cropY;
	}

	public void setCropY(int cropY) {
		this.cropY = cropY;
	}

	public int getCropWidth() {
		return cropWidth;
	}

	public void setCropWidth(int cropWidth) {
		this.cropWidth = cropWidth;
	}

	public int getCropHeight() {
		return cropHeight;
	}

	public void setCropHeight(int cropHeight) {
		this.cropHeight = cropHeight;
	}

	public int getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}

	public int getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getFiledataFileName() {
		return filedataFileName;
	}

	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	

}
