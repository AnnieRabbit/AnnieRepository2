package shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shopping.domain.Advertise;

public interface AdvertiseDao {
	/* 分页 */
	public List<Advertise>selectByCondition(@Param("condition") Advertise condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("skip") Integer skip, @Param("take") Integer take);
	
	/* 总记录数 */
	public Integer sizeAdvertiseByCondition(@Param("condition") Advertise condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
	
	/* 显示记录数 */
	public Integer sizeAdvertiseByDisplay();
	
	/* 添加 */
	public boolean insert(Advertise advertise);
	
	/* 修改 */
	public boolean update(Advertise advertise);
	
	/* 进入修改页面自动修改为不显示 */
	public boolean updateToNotDisplay(Integer id);
	
	/* 详情 */
	public Advertise selectById(Integer id);
	
	/* 删除*/
	public boolean delete(Integer id);
	
}
