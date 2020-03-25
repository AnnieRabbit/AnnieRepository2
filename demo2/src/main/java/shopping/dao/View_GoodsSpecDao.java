package shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shopping.domain.Specv;
import shopping.domain.View_GoodsSpec;


public interface View_GoodsSpecDao {

	public List<View_GoodsSpec>  selectByCondition(Integer goodsid);
	
	public boolean delete(Integer goodsid);
	
	public boolean inserts(@Param("gss") List<View_GoodsSpec> gss);
}
