package shopping.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;
import shopping.domain.Brand;
import shopping.util.Pager;

public interface BrandService {

	/* 分页 */
	public Pager<Brand> list(@Param("condition") Brand condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("page") int page);
	
	/* 添加 */
	public boolean append(Brand brand, MultipartFile file);
	
	/* 修改*/
	public boolean modify(Brand brand, MultipartFile file);
	
	/* 详情 */
	public Brand detail(Integer id);
	
	/* 删除*/
	public boolean remove(Integer id);
	
	/* 删除前查询商品表中是否有该品牌*/
	public boolean listGoodsCountByBrandId(Integer id);
	
	/* 新增页品牌下拉列表 */
	public List<Brand> listBrandsCountByBrandId();
	
}
