package shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import shopping.domain.OrderDetail;
import shopping.domain.Specv;


public interface OrderDetailDao {
	
	/* 查询一个ordercode下所有订单detail */
	public List<OrderDetail> selectsByOrdercode(@Param("ordercode") String ordercode);

}


