package shopping.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import shopping.dao.LoginDao;
import shopping.domain.Login;
import shopping.domain.View_GoodsSpec;
import shopping.service.LoginService;
import shopping.util.Cons;
import shopping.util.Pager;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	@Autowired
	private Cons cons;

	@Override
	public Pager<Login> list(Login condition, String beginTime, String endTime, int page) {
		
		List <Login> datas= this.loginDao.selectByCondition(condition, beginTime, endTime, (page-1)*cons.getPage_size(), cons.getPage_size());
		Integer size =this.loginDao.sizeLoginByCondition(condition, beginTime, endTime);
		return new Pager<Login>(datas,size,page,cons.getPage_size());	
	}


	@Override
	public boolean remove(Integer id) {
		return this.loginDao.delete(id);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor =Exception.class)
	public boolean removes(Integer[] ids) {
	
		return this.loginDao.deletes(ids);
		       
	}


}
