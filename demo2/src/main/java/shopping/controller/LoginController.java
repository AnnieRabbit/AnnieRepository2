package shopping.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import shopping.domain.Login;
import shopping.domain.View_Class;
import shopping.service.LoginService;
import shopping.util.Pager;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping("admin/logins/{page}")
	public ModelAndView index(Login condition, String beginTime, String endTime, @PathVariable("page") Integer page) {
		Pager<Login> pager = this.loginService.list(condition, beginTime, endTime, page);
		return new ModelAndView("admin/login/index")
				.addObject("condition", condition).addObject("beginTime", beginTime)
				.addObject("endTime", endTime)
				.addObject("pager", pager);

	}
		  
	/* 删除按钮 remove-2 实现删除 */ 

	@RequestMapping("/admin/login/remove/{id}")
	  public ModelAndView remove(@PathVariable("id")Integer id) {
		    this.loginService.remove(id);
			return new ModelAndView("redirect:/admin/logins/1");
	  }
	 
	/* 批量删除 */
	 @ResponseBody
	 @DeleteMapping("admin/login")
	 public Map<String,Object> removes(Integer[] ids){
		
		 if(ids!=null) {
			  boolean result=this.loginService.removes(ids);
			 Map<String ,Object>map=new HashMap<String,Object>();
			 if(result) {
				 Pager<Login> pager = this.loginService.list(null, null, null, 1);
					map.put("pager", pager);
					map.put("result", result);
				}else {
					map.put("result", result);
					
				}
				
			return map;
		 
	 }
			return null;

 }
}
