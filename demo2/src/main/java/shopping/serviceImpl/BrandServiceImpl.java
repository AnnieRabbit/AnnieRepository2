package shopping.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import shopping.dao.BrandDao;
import shopping.domain.Advertise;
import shopping.domain.Brand;
import shopping.service.BrandService;
import shopping.util.Cons;
import shopping.util.Pager;
import shopping.util.UploadUtil;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDao brandDao;

	@Autowired
	private Cons cons;

	/* 分页 */
	@Override
	public Pager<Brand> list(Brand condition, String beginTime, String endTime, int page) {
		
		List <Brand> datas= this.brandDao.selectByCondition(condition, beginTime, endTime, (page-1)*cons.getPage_size(), cons.getPage_size());
		Integer size =this.brandDao.sizeBrandByCondition(condition, beginTime, endTime);
		return new Pager<Brand>(datas,size,page,cons.getPage_size());
		
	}

	/* 详情 */
	@Override
	public Brand detail(Integer id) {
		return this.brandDao.selectById(id);
	}
	/* 删除 */
	@Override
	public boolean remove(Integer id) {
		return this.brandDao.delete(id);
	}


	/* 添加 */
	@Override
	public boolean append(Brand brand, MultipartFile file) {

		 UploadUtil uu= new UploadUtil(cons.getUpload_save_brand_path(),file);
		 if(uu.save()) {
			 brand.setPath(cons.getUpload_db_brand_path()+uu.getFileNames().get(0));
			 return this.brandDao.insert(brand);
		 }
		 return false;

	}

	/* 修改 */
	@Override
	public boolean modify(Brand brand, MultipartFile file) {
		 UploadUtil uu= new UploadUtil(cons.getUpload_save_brand_path(),file);
		 System.out.println(file);

		 if(uu.save()) {
			 brand.setPath(cons.getUpload_db_brand_path()+uu.getFileNames().get(0));
			 return this.brandDao.update(brand);
		 }
		 return false;
	}

	/* 删除前查询商品表中是否有该品牌 */
	@Override
	public boolean listGoodsCountByBrandId(Integer id) {
	   return this.brandDao.selectGoodsCountByBrandId(id)>0? true:false;
	}
	
	/* 商品新增页面品牌下拉列表框 */
	@Override
	public List<Brand> listBrandsCountByBrandId() {
		
		return this.brandDao.selectBrandsCountByBrandId();
	}
}
