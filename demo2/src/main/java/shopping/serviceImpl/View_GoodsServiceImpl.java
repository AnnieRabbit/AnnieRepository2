package shopping.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import shopping.dao.View_GoodsDao;

import shopping.domain.View_Goods;
import shopping.service.View_GoodsService;
import shopping.util.Cons;
import shopping.util.Pager;


@Service
public class View_GoodsServiceImpl implements View_GoodsService {

	@Autowired
	private View_GoodsDao view_GoodsDao;

	@Autowired
	private Cons cons;

	@Override
	public Pager<View_Goods> list(View_Goods condition, String beginTime, String endTime, int page) {
		
		List <View_Goods> datas= this.view_GoodsDao.selectByCondition(condition, beginTime, endTime, (page-1)*cons.getPage_size(), cons.getPage_size());
		Integer size =this.view_GoodsDao.sizeGoodsByCondition(condition, beginTime, endTime);
		return new Pager<View_Goods>(datas,size,page,cons.getPage_size());	
	}

}
