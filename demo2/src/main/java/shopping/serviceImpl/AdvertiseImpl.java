package shopping.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import shopping.dao.AdvertiseDao;
import shopping.domain.Advertise;
import shopping.service.AdvertiseService;
import shopping.util.Cons;
import shopping.util.Pager;
import shopping.util.UploadUtil;
@Service
public class AdvertiseImpl  implements AdvertiseService {
    @Autowired
    private AdvertiseDao advertiseDao;
    @Autowired
    private Cons cons;
    
	@Override
	public Pager<Advertise> list(Advertise condition, String beginTime, String endTime, int page) {
		List<Advertise> datas=this.advertiseDao.selectByCondition(condition, beginTime, endTime,(page-1)*cons.getPage_size(),cons.getPage_size());
		Integer size=this.advertiseDao.sizeAdvertiseByCondition(condition, beginTime, endTime);
		return new Pager<Advertise>(datas, size, page, cons.getPage_size());
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean modifySort(Integer[] ids, Integer [] seqs) {
		boolean flag=true;
		for(int i=0; i<ids.length;i++) {
			
			Advertise obj=new Advertise();
			obj.setId(ids[i]);
			obj.setSeq(seqs[i]);
			flag=this.advertiseDao.update(obj);
			
		}
		
		return flag;
	}

	@Override
	public List<Advertise> list() {
		return this.advertiseDao.selectByCondition(null, null, null, 0, 0);
	}

	
	/* 添加 */
	@Override
	public boolean append(Advertise advertise, MultipartFile file) {

		 UploadUtil uu= new UploadUtil(cons.getUpload_save_advertise_path(),file);
		 if(uu.save()) {
			 advertise.setPath(cons.getUpload_db_advertise_path()+uu.getFileNames().get(0));
			 return this.advertiseDao.insert(advertise);
		 }
		 return false;

	}

	/* 修改 */
	@Override
	public boolean modify(Advertise advertise, MultipartFile file) {
		 UploadUtil uu= new UploadUtil(cons.getUpload_save_advertise_path(),file);
		 if(uu.save()) {
			 advertise.setPath(cons.getUpload_db_advertise_path()+uu.getFileNames().get(0));
			 return this.advertiseDao.update(advertise);
		 }
		 return false;
	}

	/* 详情 */
	@Override
	public Advertise detail(Integer id) {
		return this.advertiseDao.selectById(id);
	}

	/* 显示数量 */
	@Override
	public Integer sizeAdvertiseByDisplay() {
		return this.advertiseDao.sizeAdvertiseByDisplay();
	}

	/* 改为不显示 */
	@Override
	public boolean modifyToNotDisplay(Integer id) {	
		return this.advertiseDao.updateToNotDisplay(id);
	}

	/* 删除 */
	@Override
	public boolean remove(Integer id) {	
		return this.advertiseDao.delete(id);
	}
	

}
