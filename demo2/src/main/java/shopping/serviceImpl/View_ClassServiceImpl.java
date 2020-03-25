package shopping.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopping.dao.View_ClassDao;
import shopping.domain.View_Class;
import shopping.service.View_ClassService;
import shopping.util.Cons;
import shopping.util.Pager;

@Service
public class View_ClassServiceImpl implements View_ClassService {

	@Autowired
	private View_ClassDao view_ClassDao;
	
	@Autowired
	private Cons cons;
	
	@Override
	public Pager<View_Class> firstList(View_Class condition, String beginTime, String endTime, int page) {
		
		List<View_Class> datas=this.view_ClassDao.firstSelectsByCondition(condition, beginTime, endTime, (page-1)*cons.getPage_size(), cons.getPage_size());
		Integer size=this.view_ClassDao.firstSizeByCondition(condition, beginTime, endTime);
		return new Pager<View_Class>(datas,size,page,cons.getPage_size());
	}

	@Override
	public Pager<View_Class> secondList(View_Class condition, String beginTime, String endTime, int page) {
		List<View_Class> datas=this.view_ClassDao.secondSelectsByCondition(condition, beginTime, endTime, (page-1)*cons.getPage_size(), cons.getPage_size());
		Integer size=this.view_ClassDao.secondSizeByCondition(condition, beginTime, endTime);
		return new Pager<View_Class>(datas, size, page, cons.getPage_size());
	}

	@Override
	public Pager<View_Class> thridList(View_Class condition, String beginTime, String endTime, int page) {
		List<View_Class> datas=this.view_ClassDao.thridSelectsByCondition(condition, beginTime, endTime, (page-1)*cons.getPage_size(), cons.getPage_size());
		Integer size=this.view_ClassDao.thridSizeByCondition(condition, beginTime, endTime);
		return new Pager<View_Class>(datas, size, page, cons.getPage_size());
	}

}
