package shopping.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import shopping.domain.Spec;


public interface SpecDao {
	
	/* 查询good表中是否有specid */
	public int isUsedInGoodsBySpecid(Integer id);
	
	/* 添加 */
    public boolean insert(Spec spec);
    
	/* 修改 */
    public boolean update(Spec spec);
    
    /* 删除 */
    public boolean delete(Integer id);
    
    /* 查详情 */
    public Spec selectById(Integer id);
    
    /* 查询最大seq */
    public int selectMaxSeq();
    
    /* 分页 */
    public List<Spec> selectsByCondition(@Param("condition") Spec condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("skip") Integer skip, @Param("take") Integer take);

	/* 总记录数 */
	public Integer sizeByCondition(@Param("condition") Spec condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
