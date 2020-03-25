package shopping.controller;

import java.util.List;

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
import shopping.domain.Goods;
import shopping.domain.User;
import shopping.domain.View_Class;
import shopping.domain.View_Goods;
import shopping.service.BrandService;
import shopping.service.ClassService;
import shopping.service.View_GoodsService;
import shopping.util.Pager;

@Controller
public class View_GoodsController {

	@Autowired
	private View_GoodsService view_GoodsService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private BrandService brandService;

	@RequestMapping("admin/goods/{page}")
	public ModelAndView index(View_Goods condition, String beginTime, String endTime, @PathVariable("page") Integer page) {
		List<View_Class> firstClasses=this.classService.showFirClassInSecClassList();
		List<View_Class> secondClasses=this.classService.showSecClassByFirClassId(condition.getFirst_id());
		List<View_Class> thridClasses=this.classService.showThrClassBySecClassId(condition.getSecond_id());
		List<Brand> brands=this.brandService.listBrandsCountByBrandId();
		Pager<View_Goods> pager = this.view_GoodsService.list(condition, beginTime, endTime, page);

		return new ModelAndView("admin/goods/index").addObject("condition", condition).addObject("beginTime", beginTime)
				.addObject("firstClasses",firstClasses)
				.addObject("secondClasses",secondClasses)
				.addObject("thridClasses",thridClasses) 
				.addObject("brands",brands)
				.addObject("condition",condition)
				.addObject("endTime", endTime)
				.addObject("pager", pager);

	}

 

}
