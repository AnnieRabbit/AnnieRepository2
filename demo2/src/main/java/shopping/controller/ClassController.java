package shopping.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shopping.domain.View_Class;
import shopping.service.ClassService;
import shopping.service.View_ClassService;
import shopping.util.Pager;

@Controller
public class ClassController {
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private View_ClassService view_ClassService;
	
	
	  /* 一级二级编辑查详情 */
	  @ResponseBody
	  @GetMapping("/admin/class/first/{id}") 
	  public shopping.domain.Class firstDetail(@PathVariable("id")Integer id) {
	  
	         return this.classService.selectFirAndSecById(id); 
	  
	  }
	  
	  /* 三级编辑查详情 */
	  @ResponseBody
	  @GetMapping("/admin/class/thrid/{thrid_id}") 
	  public View_Class thridDetail(@PathVariable("thrid_id")Integer thrid_id) {
	 
	         return this.classService.selectThridById(thrid_id); 
	  
	  }
	
	/* 一级新增 */
	@ResponseBody
	@PostMapping("/admin/class/first")
	public Map<String,Object> firstAppend(shopping.domain.Class obj){
		
		boolean result =this.classService.appendFirst(obj);
		Map<String ,Object>map=new HashMap<String,Object>();
		if(result) {
			Pager<View_Class> pager=this.view_ClassService.firstList(null, null, null, 1);
			map.put("pager", pager);
			map.put("result", result);
		}else {
			map.put("result", result);
			
		}
		
		return map;
		
	}
	

	  

	  /* 一级编辑保存 */
	  @ResponseBody
	  @PutMapping("admin/class/first") 
	  public  Map<String,Object> firstModify(shopping.domain.Class obj) {

	  boolean result =this.classService.modifyFirstClass(obj);
		 Map<String ,Object>map=new HashMap<String,Object>(); 
		
			if(result) {
				Pager<View_Class> pager=this.view_ClassService.firstList(null, null, null, 1);
				map.put("pager", pager);
				map.put("result", result);
			}else {
				map.put("result", result);
				
			}
 
	   return map;
	  
	  }
	  
	  
	  /* 一级删除 */ 
	  @ResponseBody
	  @DeleteMapping("admin/class/first/{id}") 
	  public  Map<String,Object> firstDelete(@PathVariable("id")Integer id) {
      List<View_Class> secondClasses=this.classService.showSecClassByFirClassId(id);
            boolean result=false;
	       if(secondClasses.size()==0) {  
	    	  result =this.classService.removeClass(id);
	       }else {
	    	  result=false;
	       }
	  
		 Map<String ,Object>map=new HashMap<String,Object>(); 
		
			if(result) {
				Pager<View_Class> pager=this.view_ClassService.firstList(null, null, null, 1);
				map.put("pager", pager);
				map.put("result", result);
			}else {
				map.put("result", result);
				
			}
 
	   return map;
	  
	  }
	  
	
   /*二级index ajax通过一级分类Id显示二级分类下拉列表 */		
   @ResponseBody
   @PostMapping("admin/classes/second/ajaxSecondIndex/{first_id}")
   public Collection<View_Class> ajaxSecondIndex(@PathVariable("first_id")Integer first_id){
        List<View_Class> secondClasses=this.classService.showSecClassByFirClassId(first_id);
            return secondClasses;     
   }
		 
	/* 二级新增 */
	@ResponseBody
	@PostMapping("/admin/class/second")
	public Map<String,Object> secondAppend(shopping.domain.Class obj){
		boolean result =this.classService.appendSecond(obj);
		Map<String ,Object>map=new HashMap<String,Object>();
		if(result) {
			Pager<View_Class> pager=this.view_ClassService.secondList(null, null, null, 1);
			map.put("pager", pager);
			map.put("result", result);
		}else {
			map.put("result", result);
			
		}
		
		return map;
		
	}
	  

	  /* 二级编辑保存 */
	  @ResponseBody
	  @PutMapping("admin/class/second") 
	  public  Map<String,Object> secondModify(shopping.domain.Class obj) {

	  boolean result =this.classService.modifySecondClass(obj);
		 Map<String ,Object>map=new HashMap<String,Object>(); 
		
			if(result) {
				Pager<View_Class> pager=this.view_ClassService.secondList(null, null, null, 1);
				map.put("pager", pager);
				map.put("result", result);
			}else {
				map.put("result", result);
				
			}

	   return map;
	  
	  }
	  
	  
	  
	  /* 二级删除 */ 
	  @ResponseBody
	  @DeleteMapping("admin/class/second/{id}") 
	  public  Map<String,Object> secondDelete(@PathVariable("id")Integer id) {
      List<View_Class> thridClasses=this.classService.showThrClassBySecClassId(id);
            boolean result=false;
	       if(thridClasses.size()==0) {
	    	  result =this.classService.removeClass(id);
	       }else {
	    	   result=false;
	       }
		 Map<String ,Object>map=new HashMap<String,Object>(); 
			if(result) {
				Pager<View_Class> pager=this.view_ClassService.secondList(null, null, null, 1);
				map.put("pager", pager);
				map.put("result", result);
			}else {
				map.put("result", result);
			}
	   return map;
	  }
	  
	  
	   /*三级index ajax通过二级分类Id显示三级分类下拉列表 */
	   @ResponseBody
	   @PostMapping("admin/classes/thrid/ajaxThridIndex/{second_id}")
	   public Collection<View_Class> ajaxThridIndex(@PathVariable("second_id")Integer second_id){
	        List<View_Class> thridClasses=this.classService.showThrClassBySecClassId(second_id);
	            return thridClasses;     
	   }
	   
	   
	   /* 三级添加 */
		@ResponseBody
		@PostMapping("/admin/class/thrid")
		public Map<String,Object> thridAppend(shopping.domain.Class obj){
			boolean result =this.classService.appendThrid(obj);
			Map<String ,Object>map=new HashMap<String,Object>();
			if(result) {
				Pager<View_Class> pager=this.view_ClassService.thridList(null, null, null, 1);
				map.put("pager", pager);
				map.put("result", result);
			}else {
				map.put("result", result);
				
			}
			
			return map;
			
		}
		
		
		  /* 三级编辑保存 */
		  @ResponseBody
		  @PutMapping("admin/class/thrid") 
		  public  Map<String,Object> thridModify(shopping.domain.Class obj) {

		  boolean result =this.classService.modifySecondClass(obj);
			 Map<String ,Object>map=new HashMap<String,Object>(); 
			
				if(result) {
					Pager<View_Class> pager=this.view_ClassService.thridList(null, null, null, 1);
					map.put("pager", pager);
					map.put("result", result);
				}else {
					map.put("result", result);
					
				}

		   return map;
		  
		  }
		
		  @ResponseBody
		  @RequestMapping("/admin/class/ajaxRemove") 
		  public boolean ajaxRemove(Integer id){
		    return this.classService.listGoodsCountByClassId(id);
		  
		  }
		  
		  
		  /* 三级删除 */ 
		  @ResponseBody
		  @DeleteMapping("admin/class/thrid/{id}") 
		  public  Map<String,Object> thridDelete(@PathVariable("id")Integer id) {
	            boolean result=false;
		       if(this.classService.listGoodsCountByClassId(id)) {
		    	  result =false;
		       }else {
		    	   result=this.classService.removeClass(id);
		       }
			 Map<String ,Object>map=new HashMap<String,Object>(); 
				if(result) {
					Pager<View_Class> pager=this.view_ClassService.thridList(null, null, null, 1);
					map.put("pager", pager);
					map.put("result", result);
				}else {
					map.put("result", result);
				}
		   return map;
		  }
	
}
