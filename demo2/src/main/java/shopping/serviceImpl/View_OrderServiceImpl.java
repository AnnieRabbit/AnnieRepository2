package shopping.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.dao.View_OrderDao;
import shopping.domain.View_Order;
import shopping.service.View_OrderService;
import shopping.util.Cons;
import shopping.util.Pager;

@Service
public class View_OrderServiceImpl implements View_OrderService {

	
	@Autowired
	private View_OrderDao view_OrderDao;
	
	@Autowired
	private Cons cons;

	@Override
	public List<View_Order> list(String ordercode,View_Order condition) {
		return this.view_OrderDao.selectsByCondition(ordercode,condition, 0, 0);
	
	}


}
