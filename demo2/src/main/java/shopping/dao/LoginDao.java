package shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shopping.domain.Login;

public interface LoginDao {

	/* 分页 */
	public List<Login> selectByCondition(@Param("condition") Login condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("skip") Integer skip, @Param("take") Integer take);
	
	/* 记录数 */
	public Integer sizeLoginByCondition(@Param("condition") Login condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
	
	/* 添加 */
	public boolean insert(Login login);
	
	/* 删除 */
	public boolean delete(Integer id);
	
	/* 批量删除 */
	public boolean deletes(@Param("ids") Integer[] ids);
	
}
