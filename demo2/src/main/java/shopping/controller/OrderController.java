package shopping.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import shopping.domain.Order;
import shopping.domain.OrderDetail;
import shopping.service.OrderService;
import shopping.util.Pager;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping("admin/orders/{page}")
	public ModelAndView index(Order condition, String beginTime, String endTime, @PathVariable("page") Integer page) {
		if (condition== null) {
			  condition=new Order();
			  condition.setState(0);
		  }
		
		
		Pager<Order> pager = this.orderService.list(condition, beginTime, endTime, page);
	
		
		
        List<Order> statuses=this.orderService.listState(condition.getState());
		return new ModelAndView("admin/order/index")
				.addObject("condition", condition)
				.addObject("beginTime", beginTime)
				.addObject("endTime", endTime)
				.addObject("pager", pager)
				.addObject("statuses", statuses)
				;
				
	}
	
	
	  
	
	/* 转到详情页 */
	@GetMapping("/admin/order/{ordercode}")
	public ModelAndView orderDetail(@PathVariable("ordercode") String ordercode) {
	    List<OrderDetail> ods = this.orderService.listOrderDetails(ordercode);
		return new ModelAndView("admin/order/detail")
	            .addObject("ods",ods);
	          

	}

	 /* 发货 */
	@ResponseBody
	@RequestMapping("/admin/order/send/{orderid}")
	public Map<String,Object>  send(@PathVariable("orderid") String orderid,int page) {
	
		 Map<String,Object> map=(this.orderService.sendGoods(orderid));
		 if(map.get("result").toString()=="true") {
			 
				Pager<Order> pager = this.orderService.list(null, null, null, page);
				map.put("pager", pager);
		}	
			
	   return map;
		
	}
	
	
	 /* 退款 */
		@ResponseBody
		@RequestMapping("/admin/order/returnMoney/{orderid}")
		public Map<String,Object>  returnMoney(@PathVariable("orderid") String orderid,int page) {
				
			Map<String,Object> map=this.orderService.returnMoney(orderid);
			if(map.get("result").toString()=="true") {
					 
					Pager<Order> pager = this.orderService.list(null, null, null, page);
					map.put("pager", pager);
			}	
				
		   return map;
			
		}


}
