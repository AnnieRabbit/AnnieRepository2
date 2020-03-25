package shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;


import shopping.domain.Images;

public interface ImagesDao {

	/* 批量添加 */
	public boolean inserts(@Param("goodsid") Integer goodsid, @Param("images") Images[] images);
		
	/* 详情 */
	public List<Images> selectByGoodsId(Integer goodsid);
	
	/* 删除*/
	public boolean delete(Integer goodsid);
	
}


