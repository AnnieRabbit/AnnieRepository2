package shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shopping.domain.Advertise;
import shopping.domain.Brand;
import shopping.domain.User;

public interface BrandDao {

	/* 分页 */
	public List<Brand> selectByCondition(@Param("condition") Brand condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("skip") Integer skip, @Param("take") Integer take);
	
	/* 记录数 */
	public Integer sizeBrandByCondition(@Param("condition") Brand condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
	
	/* 添加 */
	public boolean insert(Brand brand);
	
	/* 修改 */
	public boolean update(Brand brand);
	
	/* 详情 */
	public Brand selectById(Integer id);
	
	/* 删除*/
	public boolean delete(Integer id);
	
	/* 删除前判断品牌下是否有商品*/
	public Integer selectGoodsCountByBrandId(Integer id);
	
	/*商品新增页中品牌下拉框*/
	public List<Brand> selectBrandsCountByBrandId();
	
}


