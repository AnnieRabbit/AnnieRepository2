package shopping.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import shopping.domain.Images;
import shopping.domain.User;
import shopping.service.BrandService;
import shopping.service.ImagesService;
import shopping.util.Pager;

@Controller
public class ImagesController {

	@Autowired
	private ImagesService imagesService;

	@GetMapping("admin/good/image/{goodsid}")
	public ModelAndView detail(@PathVariable("goodsid") Integer goodsid) {
	List<Images> images = this.imagesService.detail(goodsid);
		return new ModelAndView("admin/images/detail")			
				.addObject("images", images)
				.addObject("goodsid", goodsid);

	}
	
	  
	/*修改四张附图  */
	  @PostMapping("/admin/image/{goodsid}")
	  public ModelAndView modifyImages(MultipartFile[] file,@PathVariable("goodsid") Integer goodsid) {
		  System.out.println("file"+file);
		  boolean result=false;
		  result= this.imagesService.modifyImages(goodsid, file);
		  return new ModelAndView("redirect:/admin/goods/1");
	  
	  }
	  	 

}
