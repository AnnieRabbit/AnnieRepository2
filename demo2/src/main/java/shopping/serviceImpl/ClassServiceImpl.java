package shopping.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.domain.Class;
import shopping.domain.View_Class;
import shopping.dao.ClassDao;
import shopping.dao.View_ClassDao;
import shopping.service.ClassService;


@Service
public  class ClassServiceImpl implements ClassService {

	
	@Autowired
	private ClassDao classDao;

	@Autowired
	private View_ClassDao view_ClassDao;
	/* 一级分类添加 */
	@Override
	public boolean appendFirst(Class obj) {
	    int max_seq=this.classDao.selectMaxSeqByLevel(1);
	    obj.setSeq(max_seq+1);
	    obj.setPid(0);
	    obj.setLevel(1);
		return this.classDao.insert(obj);
		
	}

	/* 一级二级分类详情 */
	@Override
	public Class selectFirAndSecById(Integer id) {
		return this.classDao.selectFirstById(id);
	}

	
	/* 一级分类编辑按钮 */
	@Override
	public boolean modifyFirstClass(Class obj) {
		obj.setPid(0);
		return  this.classDao.update(obj);
	}

	/* 多级分类删除按钮 */
	@Override
	public boolean removeClass(Integer id) {
		return  this.classDao.delete(id);
	}

	
	/* 查询所有一级分类*/
	@Override
	public List<View_Class> showFirClassInSecClassList() {
		/* return this.view_ClassDao.selectFirClassInSecClassList(); */
		return this.view_ClassDao.firstSelectsByCondition(null, null, null, 0, 0);
	}
  
	/* 一级分类id对应的二级下拉框 */
	@Override
	public List<View_Class> showSecClassByFirClassId(Integer first_id) {
		/* return this.view_ClassDao.selectSecClassByFirClassId(first_id); */
		
		View_Class condition=new View_Class();
		condition.setFirst_id(first_id);
		return this.view_ClassDao.secondSelectsByCondition(condition, null, null, 0, 0);
	}
	
	
	/* 二级分类id对应的三级下拉框 */
	@Override
	public List<View_Class> showThrClassBySecClassId(Integer second_id) {
		
		View_Class condition=new View_Class();
		condition.setSecond_id(second_id);
		return this.view_ClassDao.thridSelectsByCondition(condition, null, null, 0, 0);
	}

	/* 二级分类添加 */
	@Override
	public boolean appendSecond(Class obj) {
		 int max_seq=this.classDao.selectMaxSeqByLevel(2);
		    obj.setSeq(max_seq+1);
		    obj.setLevel(2);
			return this.classDao.insert(obj);
	}

	
	/* 二级分类编辑按钮 */
	@Override
	public boolean modifySecondClass(Class obj) {
		 return  this.classDao.update(obj);
	}



	/* 三级分类添加 */
	@Override
	public boolean appendThrid(Class obj) {
		int max_seq=this.classDao.selectMaxSeqByLevel(3);
	    obj.setSeq(max_seq+1);
	    obj.setLevel(3);
		return this.classDao.insert(obj);
	}

	/*三级分类详情 */
	@Override
	public View_Class selectThridById(Integer thrid_id) {
		
		return this.view_ClassDao.selectThridById(thrid_id);
	}

	/*分类下是否有商品 */
	@Override
	public boolean listGoodsCountByClassId(Integer id) {
		return this.classDao.selectGoodsCountByClassId(id)>0? true:false;
	}


}
