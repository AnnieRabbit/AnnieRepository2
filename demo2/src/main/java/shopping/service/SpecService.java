package shopping.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shopping.domain.Spec;
import shopping.domain.Specv;
import shopping.domain.View_Class;
import shopping.util.Pager;

public interface SpecService {
	
	/* 分页 */
	public Pager<Spec> list(@Param("condition") Spec condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("page") int page);
	
	/* 批量添加spec */	
	public boolean append(Spec spec, String[] names);
	
	/* 详情 */	
	public Spec detail(Integer id);	
	
	/* 删除规格及其详情 */
	public boolean remove(Integer id);
	
	/* 删除一条规格详情 */
	public boolean removeOneSpecv(Integer id);
	
	/* 修改规格名称 */
	public boolean modify(Spec spec);
	
	/* 修改页3.0最终 */
	public boolean modifies(Spec spec, String[] names, Integer[] ids, Integer[] seqs);
	
	/* 查询所有規格list,用於商品修改頁*/
	 public List<Spec> showSpecList();
	 
	 /* 根據規格id查询規格詳情*/
	 public List<Specv> showSpecvsList(Integer id);
	 
	 /* 查询所有規格详情list,用於商品修改规格页*/
	 public List<Specv> showAllSpecvsList();
	 
}
