package shopping.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import shopping.dao.BrandDao;
import shopping.dao.GoodsDao;
import shopping.domain.Brand;
import shopping.domain.Goods;
import shopping.service.GoodsService;
import shopping.util.Cons;
import shopping.util.UploadUtil;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;

	
	@Autowired
	private Cons cons;

	/* 上架下架 */
	@Override
	public boolean changeState(Integer id) {

		Goods goods = new Goods();
		goods.setId(id);
		if(this.goodsDao.selectState(id)==1) {
			goods.setState(2);
		}else {
			goods.setState(1);
		}
	    
		return this.goodsDao.ChangeState(goods);
	}


	/* 删除 */
	@Override
	public boolean remove(Integer id) {
		
		return this.goodsDao.delete(id);
	}

	/* 订单表中是否存在该商品,以判断商品是否能被删除 */
	@Override
	public boolean listOrdersCountByGoodsId(Integer id) {
		
		return  this.goodsDao.isExistsInOrderDetail(id) > 0 ? true : false;
	}

	/* 查询上下架状态 */
	@Override
	public int listState(Integer id) {
	
		return this.goodsDao.selectState(id);
	}


	/* 添加 */
	@Override
	public boolean append(Goods goods,MultipartFile file) {
		UploadUtil uu= new UploadUtil(cons.getUpload_save_goods_path_1(),file);
		if(uu.save()) {	
	
			goods.setPath(cons.getUpload_db_goods_path_1()+uu.getFileNames().get(0));
			return this.goodsDao.insert(goods);
		}
		return false;
	
	}


	@Override
	public Goods detail(Integer id) {
		
		return this.goodsDao.selectById(id);
	}


	@Override
	public boolean modify(Goods goods,MultipartFile file) {
		UploadUtil uu= new UploadUtil(cons.getUpload_save_goods_path_1(),file);
		if(uu.save()) {	
	
			goods.setPath(cons.getUpload_db_goods_path_1()+uu.getFileNames().get(0));
			return this.goodsDao.update(goods);
		}
		 return false;
			
		
	}

	

}
