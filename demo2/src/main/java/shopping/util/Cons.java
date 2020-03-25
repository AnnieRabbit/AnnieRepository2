package shopping.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value="classpath:cons.properties")
@ConfigurationProperties(prefix="cons")
public class Cons {

	

	private String upload_save_user_path;
	private String upload_db_user_path;
	private String upload_save_goods_path_1;
	private String upload_db_goods_path_1;
	private String upload_save_goods_path_2;
	private String upload_db_goods_path_2;
	private String upload_save_advertise_path;
	private String upload_db_advertise_path;
	private String upload_save_brand_path;
	private String upload_db_brand_path;
	private String advertise_num;
	private int page_size;
	

	
	public String getUpload_save_goods_path_1() {
		return upload_save_goods_path_1;
	}
	public void setUpload_save_goods_path_1(String upload_save_goods_path_1) {
		this.upload_save_goods_path_1 = upload_save_goods_path_1;
	}
	public String getUpload_db_goods_path_1() {
		return upload_db_goods_path_1;
	}
	public void setUpload_db_goods_path_1(String upload_db_goods_path_1) {
		this.upload_db_goods_path_1 = upload_db_goods_path_1;
	}
	public String getUpload_save_goods_path_2() {
		return upload_save_goods_path_2;
	}
	public void setUpload_save_goods_path_2(String upload_save_goods_path_2) {
		this.upload_save_goods_path_2 = upload_save_goods_path_2;
	}
	public String getUpload_db_goods_path_2() {
		return upload_db_goods_path_2;
	}
	public void setUpload_db_goods_path_2(String upload_db_goods_path_2) {
		this.upload_db_goods_path_2 = upload_db_goods_path_2;
	}
	public String getUpload_save_user_path() {
		return upload_save_user_path;
	}
	public void setUpload_save_user_path(String upload_save_user_path) {
		this.upload_save_user_path = upload_save_user_path;
	}
	public String getUpload_db_user_path() {
		return upload_db_user_path;
	}
	public void setUpload_db_user_path(String upload_db_user_path) {
		this.upload_db_user_path = upload_db_user_path;
	}
	public String getUpload_save_advertise_path() {
		return upload_save_advertise_path;
	}
	public void setUpload_save_advertise_path(String upload_save_advertise_path) {
		this.upload_save_advertise_path = upload_save_advertise_path;
	}
	public String getUpload_db_advertise_path() {
		return upload_db_advertise_path;
	}
	public void setUpload_db_advertise_path(String upload_db_advertise_path) {
		this.upload_db_advertise_path = upload_db_advertise_path;
	}
	public String getAdvertise_num() {
		return advertise_num;
	}
	public void setAdvertise_num(String advertise_num) {
		this.advertise_num = advertise_num;
	}
	public int getPage_size() {
		return page_size;
	}
	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
	public String getUpload_save_brand_path() {
		return upload_save_brand_path;
	}
	public void setUpload_save_brand_path(String upload_save_brand_path) {
		this.upload_save_brand_path = upload_save_brand_path;
	}
	public String getUpload_db_brand_path() {
		return upload_db_brand_path;
	}
	public void setUpload_db_brand_path(String upload_db_brand_path) {
		this.upload_db_brand_path = upload_db_brand_path;
	}
	
	
	
	
}
