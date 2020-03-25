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
import shopping.domain.Brand;
import shopping.domain.User;
import shopping.service.BrandService;
import shopping.util.Pager;

@Controller
public class BrandController {

	@Autowired
	private BrandService brandService;

	@RequestMapping("admin/brands/{page}")
	public ModelAndView index(Brand condition, String beginTime, String endTime, @PathVariable("page") Integer page) {
		Pager<Brand> pager = this.brandService.list(condition, beginTime, endTime, page);

		return new ModelAndView("admin/brand/index").addObject("condition", condition).addObject("beginTime", beginTime)
				.addObject("endTime", endTime)
				.addObject("pager", pager);

	}

	/* 添加 */
	@GetMapping("/admin/brand")
	public String append() {
		return "admin/brand/append";

	}

	@PostMapping("/admin/brand")
	public ModelAndView append(Brand brand, MultipartFile file) {
		if (this.brandService.append(brand, file))
			return new ModelAndView("redirect:/admin/brands/1");
		else
			return new ModelAndView("admin/share/error").addObject("msg", "添加品牌失败!");

	}

	/* 修改 */
	@GetMapping("/admin/brand/modify/{id}")
	public ModelAndView detail(@PathVariable("id") Integer id) {
		Brand brand = this.brandService.detail(id);
		return new ModelAndView("admin/brand/modify").addObject("brand", brand);
	}

	@PostMapping("/admin/brand/modify")
	public ModelAndView modify(Brand brand, MultipartFile file) {
		if (brand != null) {
			if (this.brandService.modify(brand, file))
				return new ModelAndView("redirect:/admin/brands/1");
			else
				return new ModelAndView("admin/error").addObject("msg", "修改信息失败1！");
		} else {
			return new ModelAndView("admin/error").addObject("msg", "修改信息失败2！");
		}
	}

	/* 删除普通 */
	/*
	 * @RequestMapping("/admin/brand/remove/{id}") public ModelAndView
	 * remove(@RequestParam(value = "page", defaultValue = "1") int page, Brand
	 * condition,
	 * 
	 * @PathVariable("id") Integer id) {
	 * 
	 * this.brandService.remove(id); return new
	 * ModelAndView("redirect:/admin/brands/1").addObject("condition",
	 * condition).addObject("page", page);
	 * 
	 * }
	 */
	
	/* ajax判断该品牌下是否存在商品.删除按钮 remove-1 */
	  
	  @ResponseBody
	  @RequestMapping("/admin/brand/ajaxRemove") 
	  public boolean ajaxRemove(Integer id){
	    return this.brandService.listGoodsCountByBrandId(id);
	  
	  }
	  
	/* 删除按钮 remove-2 实现删除 */
	  @RequestMapping("/admin/brand/remove/{id}")
	  public ModelAndView remove(@PathVariable("id")Integer id) {
		    this.brandService.remove(id);
			return new ModelAndView("redirect:/admin/brands/1");
	  
	  }
	  
	  
	 
	 

}
