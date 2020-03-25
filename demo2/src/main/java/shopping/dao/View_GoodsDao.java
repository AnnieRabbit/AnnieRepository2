package shopping.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import shopping.domain.Goods;
import shopping.domain.View_Goods;

public interface View_GoodsDao {

	/* 分页 */
	public List<View_Goods> selectByCondition(@Param("condition") View_Goods condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("skip") Integer skip, @Param("take") Integer take);
	
	/* 记录数 */
	public Integer sizeGoodsByCondition(@Param("condition") View_Goods condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
