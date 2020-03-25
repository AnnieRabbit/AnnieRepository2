package shopping.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import shopping.domain.Advertise;
import shopping.util.Pager;

public interface AdvertiseService {

	/* 分页 */
	public Pager<Advertise> list(Advertise condition, String beginTime, String endTime, int page);
	 
	/* 不分页 */
	public List<Advertise> list();

	/* 添加 */
	public boolean append(Advertise advertise, MultipartFile file);
	
	/* 修改*/
	public boolean modify(Advertise advertise, MultipartFile file);
	
	/* 进入修改页面自动修改为不显示 */
	public boolean modifyToNotDisplay(Integer id);
	
	/* 排序*/
	public boolean modifySort(Integer[] ids, Integer[] seqs);
	
	/* 详情*/
	public Advertise detail(Integer id);
	
	/* 显示记录数 */
	public Integer sizeAdvertiseByDisplay();
	
	
	/* 删除*/
	public boolean remove(Integer id);
	
	
}
