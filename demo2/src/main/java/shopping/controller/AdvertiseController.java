package shopping.controller;

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

import shopping.domain.Advertise;
import shopping.domain.User;
import shopping.service.AdvertiseService;
import shopping.util.Pager;

@Controller
public class AdvertiseController {
	
	@Autowired
	private AdvertiseService advertiseService;
	
	@RequestMapping("/admin/advertises/{page}")
	public ModelAndView index(Advertise condition,String beginTime,String endTime,@PathVariable("page") Integer page) {
		
		Pager<Advertise> pager= this.advertiseService.list(condition, beginTime, endTime, page);
		return new ModelAndView("admin/advertise/index")
				.addObject("condition",condition)
				.addObject("beginTime",beginTime)
				.addObject("endTime",endTime)
				.addObject("pager",pager);
				
	}
	
	
	@GetMapping("/admin/advertise/sort")
	public ModelAndView sort() {
		return new ModelAndView("/admin/advertise/sort")
				.addObject("advertises",this.advertiseService.list());
	}
	
	@ResponseBody
	@PostMapping("/admin/advertise/sort")
	public String sort(Integer [] ids,Integer [] seqs) {
		
		if(this.advertiseService.modifySort(ids, seqs))
			return "true";
		else
			return "false";
	}
	
	/* 添加 */
	@GetMapping("/admin/advertise")
	public ModelAndView append() {
		Integer size=this.advertiseService.sizeAdvertiseByDisplay();
		return new ModelAndView("admin/advertise/append")
				.addObject("size",size);

	}
	
	@PostMapping("/admin/advertise")
	public ModelAndView append(Advertise advertise, MultipartFile file) {
		if (this.advertiseService.append(advertise, file))
			return new ModelAndView("redirect:/admin/advertises/1");
		else
			return new ModelAndView("admin/share/error").addObject("msg", "添加广告失败!");

	}
	
	
	/* 修改 */
	@GetMapping("/admin/advertise/modify/{id}")
	public ModelAndView detail(@PathVariable("id") Integer id) {
		Advertise advertise=this.advertiseService.detail(id);
		this.advertiseService.modifyToNotDisplay(id);
		Integer size=this.advertiseService.sizeAdvertiseByDisplay();
		return new ModelAndView("admin/advertise/modify")
				.addObject("size",size)
				.addObject("advertise", advertise);
	}
	

	@PostMapping("/admin/advertise/modify")
	public ModelAndView modify(Advertise advertise, MultipartFile file) {
		if (advertise != null) {
			if (this.advertiseService.modify(advertise,file))
				return new ModelAndView("redirect:/admin/advertises/1");
			else
				return new ModelAndView("admin/error").addObject("msg", "修改信息失败1！");
		} else {
			return new ModelAndView("admin/error").addObject("msg", "修改信息失败2！");
		}
	}
	
	
	  /* 删除 */
	  @RequestMapping("/admin/advertise/remove/{id}") 
	  public ModelAndView remove(@RequestParam(value = "page", defaultValue = "1") int page, User condition, @PathVariable("id") Integer id) {
	  
	            this.advertiseService.remove(id);
	            return new ModelAndView("redirect:/admin/advertises/1")
	            		.addObject("condition",condition) 
	            		.addObject("page", page);
	  
	  }

}
