package shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import shopping.domain.User;

public interface UserDao {

	/* 分页 */
	public List<User> selectByCondition(@Param("condition") User condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("skip") Integer skip, @Param("take") Integer take);
	
	/* 记录数 */
	public Integer sizeUserByCondition(@Param("condition") User condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
	
	/* 启用禁用*/
	public boolean ChangeState(User user);
	
	/* 详情 */
	public User selectById(Integer id);
	
	/* 添加 */
	public boolean insert(User user);
	
	/* 登录 */
	public User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	/* 修改 */
	public boolean update(@Param("user") User user, @Param("id") Integer id);
	
	/* 重置密码 */
	public boolean reset(@Param("id") Integer id);
	
	/* 修改密码 */
	public boolean updatePass(@Param("password") String password, @Param("id") Integer id);
	
	/* 删除 */
	public boolean delete(Integer id);
	
	/* 是否存在 */
	public int isExists(String username);
	
}



