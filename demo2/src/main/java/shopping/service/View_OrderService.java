package shopping.service;


import java.util.List;

import shopping.domain.View_Order;
import shopping.util.Pager;

public interface View_OrderService {
	
	/*分页 */
	public List<View_Order> list(String ordercode, View_Order condition);
	
	
}
