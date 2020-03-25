package shopping.service;


import org.apache.ibatis.annotations.Param;
import shopping.domain.Login;
import shopping.util.Pager;

public interface LoginService {

	/* 分页 */
	public Pager<Login> list(@Param("condition") Login condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("page") int page);
	
	/* 删除*/
	public boolean remove(Integer id);
	
	/* 批量删除*/
	public boolean removes(Integer[] ids);
	
	
	
}
