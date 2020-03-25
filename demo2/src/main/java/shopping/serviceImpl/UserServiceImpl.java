package shopping.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;

import shopping.dao.LoginDao;
import shopping.dao.UserDao;
import shopping.domain.Login;
import shopping.domain.User;
import shopping.service.UserService;
import shopping.util.Cons;
import shopping.util.Pager;
import shopping.util.UploadUtil;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private LoginDao loginDao;

	@Autowired
	private Cons cons;
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public Pager<User> list(User condition, String beginTime, String endTime, int page) {
		
		List<User> datas= this.userDao.selectByCondition(condition, beginTime, endTime, 
				(page-1)*cons.getPage_size(), cons.getPage_size());
		Integer size=this.userDao.sizeUserByCondition(condition, beginTime, endTime);
		return new Pager<User>(datas,size,page,cons.getPage_size());
	}



	@Override
	public User detail(Integer id) {
		
		return this.userDao.selectById(id);
	}

	@Override
	public boolean append(User user) {
		
		return this.userDao.insert(user);
	}

	@Override
	public boolean appendAdmin(User user, MultipartFile file) {
	
		 UploadUtil uu= new UploadUtil(cons.getUpload_save_user_path(),file);
		 if(uu.save()) {
			 
			 user.setRoles(1);
			 user.setPath(cons.getUpload_db_user_path()+uu.getFileNames().get(0));
			 return this.userDao.insert(user);
		 }
		 return false;
	}

	@Override
	public boolean appendNormal(User user, MultipartFile file) {
		UploadUtil uu= new UploadUtil(cons.getUpload_save_user_path(),file);
		if(uu.save()) {	
			user.setRoles(2);
			user.setPath(cons.getUpload_db_user_path()+uu.getFileNames().get(0));
			return this.userDao.insert(user);
		}
		return false;
	}

	@Override
	public User login(String username, String password,String ip) {
		
		User current= this.userDao.selectByUsernameAndPassword(username, password);
		if(current!=null) {
			Login login=new Login();
			login.setUserid(current.getId());
			login.setUsername(current.getUsername());
			login.setIp(ip);
			
			this.loginDao.insert(login);
			
		}
		return current;
	}

	@Override
	public boolean modify(User user,Integer id,MultipartFile file) {
		
		UploadUtil uu = new UploadUtil(cons.getUpload_save_user_path(), file);

		if (uu.save()) {

			user.setPath(cons.getUpload_db_user_path() + uu.getFileNames().get(0));

			return this.userDao.update(user,id);

		}

		return false;
		
	}

	@Override
	public boolean remove(Integer id) {
		
		return this.userDao.delete(id);
	}

	@Override
	public boolean isExists(String username) {
	
	    return this.userDao.isExists(username) > 0 ? true : false;
	}

	@Override
	public boolean enable(Integer id) {

		User user = new User();
		user.setId(id);
		user.setState(1);

		return this.userDao.ChangeState(user);
	}

	@Override
	public boolean disable(Integer id) {

		User user = new User();
		user.setId(id);
		user.setState(2);

		return this.userDao.ChangeState(user);
	}



	@Override
	public boolean resetPass(Integer id) {
	
		return this.userDao.reset(id);
	}



	@Override
	public boolean modifyPass(String password,Integer id) {
		
		   return  this.userDao.updatePass(password, id);
	   
	    
	}

}
