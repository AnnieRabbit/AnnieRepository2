package shopping.serviceImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import shopping.dao.GoodsDao;
import shopping.dao.View_GoodsSpecDao;
import shopping.domain.Specv;
import shopping.domain.View_GoodsSpec;
import shopping.service.View_GoodsSpecService;


@Service
public class View_GoodsSpecServiceImpl implements View_GoodsSpecService {

	@Autowired
	private View_GoodsSpecDao view_Goods_SpecDao;
	
	/* 详情 */
	@Override
	public List<View_GoodsSpec>  detail(Integer goodsid) {
		
		  List<View_GoodsSpec> goods_Specs=this.view_Goods_SpecDao.selectByCondition(goodsid); 
	
		  return goods_Specs;
		 
	}

	/* 修改 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor =Exception.class) 
	public boolean modify(Integer goodsid,String goodscode,Integer[] specids,Integer[] specvids) {
		boolean result=false;

			 List<View_GoodsSpec> gss=new ArrayList<View_GoodsSpec>(); 
			  int i=0;
			  for(Integer specid:specids) { 
				 View_GoodsSpec obj=new View_GoodsSpec();
				 obj.setGoodsid(goodsid);
			     obj.setSpecid(specid); 
			     obj.setGoodscode(goodscode); 
			     obj.setSpecvid(specvids[i]);
			     gss.add(obj); 
			     i++;
	          }
			        this.view_Goods_SpecDao.delete(goodsid);
				    if(gss.size()!=0) {
				    	result=	this.view_Goods_SpecDao.inserts(gss); 
				    }else {
				    	result=true;
				    }
			         return result;
	}
	
}
