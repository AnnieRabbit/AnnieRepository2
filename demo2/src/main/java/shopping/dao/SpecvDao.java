package shopping.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import shopping.domain.Specv;

public interface SpecvDao {
	
	/* 批量添加 */
    public boolean insert(@Param("specvs") List<Specv> specvs);
    
    /* 查询最大seq */
    public int selectMaxSeq();
    
    /*删除specid下所有规格详情 */
	public boolean deleteAllSpecvs(@Param("id") Integer id); 
	 
	/* detail页删除单一规格详情 */
	public boolean deleteOneSpecv(@Param("id") Integer id); 
	
	/* detail页查询good表中是否有specvid */
	public int isUsedInGoods(@Param("id") Integer id);
	
	/*批量更新*/
	public boolean updateSpecvs(@Param("specvs") List<Specv> specvs);
	
	/*查詢單個詳情*/
	public List<Specv> selectById(@Param("id") Integer id);
	
	/*查询所有规格详情*/
	public List<Specv>selectAllSpecvs();
	
 
}