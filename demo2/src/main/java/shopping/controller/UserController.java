package shopping.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import shopping.domain.User;
import shopping.dto.UserDto;
import shopping.service.UserService;
import shopping.util.Pager;
import shopping.util.ValidateCode;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/admin/users/{page}")
	public ModelAndView index(User condition, String beginTime, String endTime, @PathVariable("page") Integer page) {

		Pager<User> pager = this.userService.list(condition, beginTime, endTime, page);

		return new ModelAndView("admin/user/index").addObject("condition", condition).addObject("beginTime", beginTime)
				.addObject("endTime", endTime).addObject("pager", pager);

	}


	/**
	 * 获取验证码Action
	 * 
	 * @param height 高度
	 * @return
	 */
	@RequestMapping("/admin/validate")
	public ModelAndView validate_code(@RequestParam(value = "height", defaultValue = "20") Integer height) {
		
		new ValidateCode(request,response, height).build();	

		return null;
	}
	
	 
	 @PostMapping("/admin/login")
	 public ModelAndView login(String username, String password,String validate_code) {
		User user = this.userService.login(username, password,request.getRemoteAddr().toString());
		if(request.getSession().getAttribute("validate_code").toString().equalsIgnoreCase(validate_code)) {
		if (user != null) {
			if (user.getState() == 1) {
				if (user.getRoles() == 1) {
					request.getSession().setAttribute("user", user);
					return new ModelAndView("admin/index");
				} else
					return new ModelAndView("admin/login").addObject("msg", "您没有访问权限!");
			} else
				return new ModelAndView("admin/login").addObject("msg", "您的用户被锁定,如要解锁请联系超级管理员!");
		} else
			return new ModelAndView("admin/login").addObject("msg", "用户名不存在或密码错误!");
	
	}else {
		return new ModelAndView("admin/login").addObject("msg", "验证码错误!");
    }
		
		
}

	@GetMapping("/admin/user")
	public String append() {
		return "admin/user/append";

	}

	@PostMapping("/admin/user")
	public ModelAndView append(UserDto userDto, MultipartFile file) {
		if (!userDto.getRepassword().equals(userDto.getUser().getPassword()))
			return new ModelAndView("admin/user/append").addObject("msg", "两次输入的密码不一致!");

		if (this.userService.appendAdmin(userDto.getUser(), file))
			return new ModelAndView("redirect:/admin/users/1");
		else
			return new ModelAndView("admin/share/error").addObject("msg", "Fail To add Admin");

	}

	/* 详情 */
	@GetMapping("/admin/user/{state}/{id}")
	public ModelAndView detail(@PathVariable("id") Integer id, @PathVariable("state") String state) {

		return new ModelAndView("admin/user/modify")
				.addObject("user", this.userService.detail(id))
				.addObject("state",state);

	}

	
	/* 个人中心 */
	  @GetMapping("/admin/user/personal/{id}") 
	  public ModelAndView Personal(@PathVariable("id")Integer id){
	  
	  return new ModelAndView("admin/user/personal")
	            .addObject("user",this.userService.detail(id));
	  
	  }
	 

	/* 修改 */
	@GetMapping("/admin/user/{id}")
	public ModelAndView detail(@PathVariable("id") Integer id) {
		return new ModelAndView("admin/user/modify").addObject("user", this.userService.detail(id));
	}

	@PostMapping("/admin/user/{id}")
	public ModelAndView modify(@PathVariable("id") Integer id, User user, MultipartFile file) {

		if (user != null) {
			if (this.userService.modify(user, id, file))
				return new ModelAndView("redirect:/admin/users/1");
			else
				return new ModelAndView("admin/error")
						.addObject("msg", "修改信息失败！");
		} else {
			return new ModelAndView("admin/error")
					.addObject("msg", "修改信息失败！");
		}
	}

	/**
	 * 修改用户上线Action
	 * 
	 * @return
	 */

	@RequestMapping("/admin/users/enable/{id}")
	public ModelAndView enable(@RequestParam(value = "page", defaultValue = "1") int page, User condition,
			@PathVariable("id") Integer id) {

		this.userService.enable(id);

		return new ModelAndView("forward:/admin/users/1")
				.addObject("condition", condition)
				.addObject("page", page);
	}

	/**
	 * 修改用户离线Action
	 * 
	 * @return
	 */

	@RequestMapping("/admin/users/disable/{id}")
	public ModelAndView disable(@RequestParam(value = "page", defaultValue = "1") int page, User condition,

			@PathVariable("id") Integer id) {

		this.userService.disable(id);

		return new ModelAndView("forward:/admin/users/1")
				.addObject("condition", condition)
				.addObject("page", page);
	}

	/* 退出 */
	@RequestMapping("/admin/user/quit")
	public String quit() {
		if (request.getSession().getAttribute("user") != null) {
			request.getSession().invalidate();
		}
		return "admin/login";

	}

	  /* 删除 */
	  @RequestMapping("/admin/user/remove/{id}") 
	  public ModelAndView remove(@RequestParam(value = "page", defaultValue = "1") int page, User condition, @PathVariable("id") Integer id) {
	            this.userService.remove(id);
	            return new ModelAndView("forward:/admin/users/1") .addObject("condition",condition) 
	            		.addObject("page", page);
	  
	  }
	 
	  
	  
	  

	    /* 重置密码 */
	    @RequestMapping("/admin/user/resetpass/{id}")
		public ModelAndView reset(@PathVariable("id") Integer id) {
			
			this.userService.resetPass(id);
			return new ModelAndView("redirect:/admin/users/1");

		}
	    
	    /* 修改密码 */
	    @ResponseBody
	    @PostMapping("/admin/user/resetpass")
		public Map<String,Object> modifypass(String oldpassword,String password,String repassword) {

	    	boolean result=false;
	    	String msg="";
	    	User user=(User)request.getSession().getAttribute("user");
	    	String originalPass=user.getPassword();
			if(oldpassword.equals(originalPass)) {
				
			    if(password.equals(repassword)) {
			    	
			    	if(this.userService.modifyPass(password, user.getId())) {
			    		result=true;
					    msg="修改成功！";
			    	}else {
			    		result=false;
				        msg="修改失败！";
			    	}
			    	
			    	
			    }else {
			    	   result=false;
				       msg="两次密码输入不一致！";
			    }
				
				
				
			}else{
			    result=false;
			    msg="密码错误!";
		     }
			
			 Map<String ,Object> map=new HashMap<String,Object>(); 
				

					map.put("result", result);
				    map.put("msg", msg);
				    return map;
	}
}
