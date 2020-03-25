package shopping.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import shopping.domain.User;
import shopping.util.Pager;

public interface UserService {
	
	/* 分页 */
	public Pager<User> list(@Param("condition") User condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("page") int page);
	
	/* 启用禁用 */
	public boolean enable(Integer id);	
	
	public boolean disable(Integer id);
		
	/* 详情 */
	public User detail(Integer id);
	
	public boolean append(User user);
	
	/* 添加管理员 */
	public boolean appendAdmin(User user, MultipartFile file);
	
	/* 添加普通用户 */
	public boolean appendNormal(User user, MultipartFile file);
	
	/* 登陆 */
	public User login(String username, String password, String ip);
	
	/* 修改 */
	public boolean modify(User user, Integer id, MultipartFile file);
	
	/* 重置密码 */
	public boolean resetPass(Integer id);
	
	/* 修改密码 */
	public boolean modifyPass(String password, Integer id);
	
	/* 删除*/
	public boolean remove(Integer id);
	
	/* 是否存在*/
	public boolean isExists(String username);
	
	
}
