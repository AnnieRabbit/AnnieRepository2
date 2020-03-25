package shopping.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;
import shopping.domain.Brand;
import shopping.domain.Goods;
import shopping.domain.View_Goods;
import shopping.util.Pager;

public interface View_GoodsService {

	/* 分页 */
	public Pager<View_Goods> list(@Param("condition") View_Goods condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("page") int page);
	
}
