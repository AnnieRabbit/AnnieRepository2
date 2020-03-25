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
import shopping.domain.User;
import shopping.domain.View_Order;
import shopping.service.BrandService;
import shopping.service.View_OrderService;
import shopping.util.Pager;

@Controller
public class View_OrderController {

	@Autowired
	private View_OrderService view_OrderService;

	/*@GetMapping("admin/order/{ordercode}")
	public ModelAndView index(@PathVariable("ordercode") String ordercode,View_Order condition) {
		List<View_Order> details = this.view_OrderService.list(ordercode,condition);

		return new ModelAndView("admin/order/detail")
				.addObject("condition", condition)
				.addObject("details", details);*/

	}

	
	  
	  
	 
	 


