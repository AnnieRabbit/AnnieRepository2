package shopping.service;

import shopping.domain.View_Class;
import shopping.util.Pager;

public interface View_ClassService {
	
	/*一级分类分页 */
	public Pager<View_Class> firstList(View_Class condition, String beginTime, String endTime, int page);
	
	/*二级分类分页 */
	public Pager<View_Class> secondList(View_Class condition, String beginTime, String endTime, int page);
	
	/*三级分类分页 */
	public Pager<View_Class> thridList(View_Class condition, String beginTime, String endTime, int page);
	
	
}
