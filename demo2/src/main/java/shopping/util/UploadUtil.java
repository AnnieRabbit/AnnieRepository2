package shopping.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传工具类
 * 
 * 
 */
public class UploadUtil {

	/**
	 * 默认构造方法
	 */
	public UploadUtil(String uploadPath, MultipartFile... files) {
		
		this.uploadPath = uploadPath;
		this.files = files;
		this.fileNames=new ArrayList<String>();
	}



	/**
	 * 上传文件数组
	 */
	private MultipartFile[] files;

	/**
	 * 上传目录
	 */
	private String uploadPath;

	/**
	 * 上传成功文件名集合
	 */
	private List<String> fileNames;

	/**
	 * 获取上传成功文件名集合
	 * 
	 * @return
	 */
	public List<String> getFileNames() {
		return fileNames;
	}

	/**
	 * 上传结果信息
	 */
	private String result;

	/**
	 * 获取上传结果信息
	 * 
	 * @return 上传结果信息
	 */
	public String getResult() {
		return result;
	}

	/**
	 * 上传方法
	 * 
	 * @return 布尔值
	 */
	public boolean save() {
	
		
		boolean flag=true;
			
		for (MultipartFile file : this.files) {

			String randName = UUID.randomUUID().toString() + "."
					+ FilenameUtils.getExtension(file.getOriginalFilename());
		
			try {
				file.transferTo(new File(this.uploadPath, randName));				
				this.fileNames.add(randName);
			} catch (Exception e) {
				e.printStackTrace();
				this.result+=e.getMessage()+"\n\n";
				flag=false;
			}
		}

		if (flag) {
			this.result = "Upload success！";
			return true;
		}else
			return false;
	}
}
