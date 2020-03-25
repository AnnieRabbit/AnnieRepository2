package shopping.service;
import java.util.List;

import shopping.domain.Class;
import shopping.domain.View_Class;
public interface ClassService {

	   /*一级二级分类详情 */
	   public Class selectFirAndSecById(Integer id);
		   
	   /* 删除多级 */
	   public boolean removeClass(Integer id);
		   
	   /* 一级分类添加 */
	   public boolean appendFirst(Class obj);
	   
	   /* 一级分类编辑保存按钮 */
	   public boolean modifyFirstClass(Class obj);
	        
		/* 二级分类添加 */
	   public boolean appendSecond(Class obj);
	   
	   /* 二级分类编辑保存按钮 */
	   public boolean modifySecondClass(Class obj);
	   
	   /* 三级分类添加 */
	   public boolean appendThrid(Class obj);
	   
	   /*三级分类详情 */
	   public View_Class selectThridById(Integer thrid_id);
	    
	   /* 查询所有一级分类list */
	   public List<View_Class> showFirClassInSecClassList();
	   
	   /*根据一级id查二级分类list 用于一级删除和二级中下拉列表 */
	   public List<View_Class> showSecClassByFirClassId(Integer first_id);
	   
	   /*根据二级id查三级分类list 用于二级删除和三级中下拉列表 */
	   public List<View_Class> showThrClassBySecClassId(Integer second_id);
	   
		/* 分类下是否有商品*/
		public boolean listGoodsCountByClassId(Integer id);
}
