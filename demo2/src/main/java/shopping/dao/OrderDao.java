package shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shopping.domain.Advertise;
import shopping.domain.Brand;
import shopping.domain.Order;
import shopping.domain.OrderDetail;
import shopping.domain.User;

public interface OrderDao {

	/* 分页 */
	public List<Order> selectByCondition(@Param("condition") Order condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("skip") Integer skip, @Param("take") Integer take);
	
	/* 记录数 */
	public Integer sizeOrderByCondition(@Param("condition") Order condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
	
	/* 所有订单状态 */
	public List<Order> showState(Integer state);
	
	/* 修改state */
	public boolean updateState(Integer state, String orderid);
	
}


