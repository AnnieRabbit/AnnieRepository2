package shopping.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.dao.OrderDao;
import shopping.dao.OrderDetailDao;
import shopping.domain.Order;
import shopping.domain.OrderDetail;
import shopping.domain.View_Class;
import shopping.service.OrderService;
import shopping.util.Cons;
import shopping.util.Pager;
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@Autowired
	private Cons cons;
	
	
	/* 分页 */
	@Override
	public Pager<Order> list(Order condition, String beginTime, String endTime, int page) {
	List<Order> datas=this.orderDao.selectByCondition(condition, beginTime, endTime, (page-1)*cons.getPage_size(), cons.getPage_size());
	Integer size=this.orderDao.sizeOrderByCondition(condition, beginTime, endTime);
	

	for(Order order:datas) {
		if(order.getState()==1)
			order.setStatus("待付款");
		else if(order.getState()==2)
			order.setStatus("待发货");
		else if(order.getState()==3)
			order.setStatus("待收货");
		else if(order.getState()==4)
			order.setStatus("延长收货");
		else if(order.getState()==5)
			order.setStatus("申请退款");
		else if(order.getState()==6)
			order.setStatus("交易关闭");
		else if(order.getState()==7)
			order.setStatus("交易成功");
		else if(order.getState()==8)
			order.setStatus("退款成功");
	}
	

		return new Pager<Order>(datas, size, page, cons.getPage_size());
	}

	/* 首页state列表 */
	@Override
	public List<Order> listState(Integer state) {
		
		 List<Order> orders=this.orderDao.showState(state); 
		for(Order order:orders) {
			if(order.getState()==1)
				order.setStatus("待付款");
			else if(order.getState()==2)
				order.setStatus("待发货");
			else if(order.getState()==3)
				order.setStatus("待收货");
			else if(order.getState()==4)
				order.setStatus("延长收货");
			else if(order.getState()==5)
				order.setStatus("申请退款");
			else if(order.getState()==6)
				order.setStatus("交易关闭");
			else if(order.getState()==7)
				order.setStatus("交易成功");
			else if(order.getState()==8)
				order.setStatus("退款成功");
	
	}
		return orders;
}

	/* 查订单详情 */
	@Override
	public List<OrderDetail> listOrderDetails(String ordercode) {
		
		return this.orderDetailDao.selectsByOrdercode(ordercode);
	}

	/* 发货 */
	@Override
	public  Map<String,Object>  sendGoods(String orderid) {
		boolean result=false;
		String msg="";
		Integer state=3;
		
		 if(this.orderDao.updateState(state, orderid)) {
			 result=true;
			 msg="发货成功!";
		 }else {
			 result=false;
			 msg="发货失败!";
		 }

		 Map<String ,Object> map=new HashMap<String,Object>(); 
			if(result) {
				map.put("result", result);
				map.put("msg", msg);
			}else {
				map.put("result", result);
				map.put("msg", msg);
			}
	   return map;
	
	}

	/* 退款 */
	@Override
	public Map<String,Object> returnMoney(String orderid) {

		boolean result=false;
		  String msg="";
		  Integer state=8;
		  
		 if(this.orderDao.updateState(state, orderid)) {
			 result=true;
			 msg="已同意退款";
		 }else {
			 result=false;
			 msg="同意退款失败";
		 }

		 Map<String ,Object> map=new HashMap<String,Object>(); 
			if(result) {
				map.put("result", result);
				map.put("msg", msg);
			}else {
				map.put("result", result);
				map.put("msg", msg);
			}
	   return map;
		
	}
		
	
		
	}
	
	
	
	

