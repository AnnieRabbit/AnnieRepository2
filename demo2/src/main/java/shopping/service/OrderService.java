package shopping.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import shopping.domain.Order;
import shopping.domain.OrderDetail;
import shopping.util.Pager;

public interface OrderService {

	/* 分页 */
	public Pager<Order> list(@Param("condition") Order condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("page") int page);
	
	/* 根据状态查订单 */
	public List<Order> listState(Integer state);
	
	/* 根据订单号查订单详情 */
	public  List<OrderDetail> listOrderDetails(String ordercode);
	
	/* 发货 */
	public Map<String, Object> sendGoods(String orderid);
	
	/* 同意退款 */
	public Map<String, Object> returnMoney(String orderid);
}
