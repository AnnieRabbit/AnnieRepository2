package shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shopping.domain.View_Class;
import shopping.domain.View_Order;

public interface View_OrderDao {

	
	/* 一级分类条件查询 */
	public List<View_Order> selectsByCondition(@Param("ordercode") String ordercode, @Param("condition") View_Order condition, @Param("skip") int skip, @Param("take") int take);
	  
}