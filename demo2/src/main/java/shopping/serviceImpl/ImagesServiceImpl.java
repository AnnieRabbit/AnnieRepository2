package shopping.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import shopping.dao.ImagesDao;
import shopping.domain.Images;
import shopping.service.ImagesService;
import shopping.util.Cons;
import shopping.util.UploadUtil;
@Service
public class ImagesServiceImpl implements ImagesService {

	
	@Autowired
	private ImagesDao imagesDao;
	
	@Autowired
	private Cons cons;



	@Override
	public List<Images> detail(Integer goodsid) {
	
		return this.imagesDao.selectByGoodsId(goodsid);
	}


	 @Override
	 @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	 public boolean modifyImages(Integer goodsid,MultipartFile [] file) {
	  Images[] images = new Images[4];
	  for (int i = 0; i < 4; i++) {
	   UploadUtil uu= new UploadUtil(cons.getUpload_save_brand_path(),file[i]);
	   if(uu.save()) {
	    images[i]=new Images();
	    images[i].setImgpath(cons.getUpload_db_brand_path()+uu.getFileNames().get(0));
	    images[i].setGoodsid(goodsid);
	   }
	  }

		  
		  this.imagesDao.delete(goodsid);
		return  this.imagesDao.inserts(goodsid, images);
	 }

}
