package shopping.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import shopping.domain.View_Class;
import shopping.service.ClassService;
import shopping.service.View_ClassService;
import shopping.util.Pager;

@Controller
public class View_ClassController {
	
	@Autowired
	private View_ClassService view_ClassService;
	
	@Autowired
	private ClassService classService;
	
	/* 一级分页 */
	@RequestMapping("admin/classes/first/{page}")
	public ModelAndView firstIndex(View_Class condition,String beginTime,String endTime,@PathVariable("page") Integer page) {
	
		Pager<View_Class> pager= this.view_ClassService.firstList(condition, beginTime, endTime, page);
		return new ModelAndView("admin/class/first/index")
				
				.addObject("condition",condition)
				.addObject("beginTime",beginTime)
				.addObject("endTime",endTime)
				.addObject("pager",pager);
				
	}
	
	/* 二级分页 */
	@RequestMapping("admin/classes/second/{page}")
	public ModelAndView secondIndex(View_Class condition,String beginTime,String endTime,@PathVariable("page") Integer page) {
		 if (condition== null) {
			  condition=new View_Class();
			  condition.setFirst_id(0);
		  }
		List<View_Class> firstClasses=this.classService.showFirClassInSecClassList();
		List<View_Class> secondClasses=this.classService.showSecClassByFirClassId(condition.getFirst_id());
		Pager<View_Class> pager= this.view_ClassService.secondList(condition, beginTime, endTime, page);
		return new ModelAndView("admin/class/second/index")
				.addObject("firstClasses",firstClasses)
				.addObject("secondClasses",secondClasses)
				.addObject("condition",condition)
				.addObject("beginTime",beginTime)
				.addObject("endTime",endTime)
				.addObject("pager",pager);
				
	}
	

	
	
	/* 三级分页 */
	@RequestMapping("admin/classes/thrid/{page}")
	public ModelAndView thridIndex(View_Class condition,String beginTime,String endTime,@PathVariable("page") Integer page) {
		
		
		  if (condition== null) {
			  condition=new View_Class();
			  condition.setFirst_id(0);
			  condition.setSecond_id(0);
		  }
			 
		 
		
		List<View_Class> firstClasses=this.classService.showFirClassInSecClassList();
		List<View_Class> secondClasses=this.classService.showSecClassByFirClassId(condition.getFirst_id());
		List<View_Class> thridClasses=this.classService.showThrClassBySecClassId(condition.getSecond_id());
		Pager<View_Class> pager= this.view_ClassService.thridList(condition, beginTime, endTime, page);
		return new ModelAndView("admin/class/thrid/index")
				.addObject("firstClasses",firstClasses)
				.addObject("secondClasses",secondClasses)
				.addObject("thridClasses",thridClasses) 
				.addObject("condition",condition)
				.addObject("beginTime",beginTime)
				.addObject("endTime",endTime)
				.addObject("pager",pager);
				
	}
	
	

}
