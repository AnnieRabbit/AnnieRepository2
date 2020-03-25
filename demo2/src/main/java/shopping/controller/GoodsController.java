package shopping.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import shopping.domain.Brand;
import shopping.domain.Goods;
import shopping.domain.View_Class;
import shopping.domain.View_Goods;
import shopping.service.BrandService;
import shopping.service.ClassService;
import shopping.service.GoodsService;
import shopping.service.View_GoodsService;
import shopping.util.Pager;


@Controller
public class GoodsController extends BaseController {

	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private View_GoodsService view_GoodsService;

	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ClassService classService;

	/**
	 * 修改用户在线状态
	 * 
	 * @return
	 */


	 /* 上下架 */
	@ResponseBody
	@RequestMapping("/admin/good/state/{id}")
	public Map<String,Object>  state(int page, Goods condition,
			@PathVariable("id") Integer id) {
		  boolean result=false;
		  String msg="";
		 if(this.goodsService.changeState(id)) {
			 result=true;
			 msg="";
		 }else {
			 result=false;
			 msg="修改失败";
		 }

		 Map<String ,Object> map=new HashMap<String,Object>(); 
			if(result) {
				Pager<View_Goods> pager=this.view_GoodsService.list(null, null, null, page);
				map.put("pager", pager);
				map.put("result", result);
				map.put("msg", msg);
			}else {
				map.put("result", result);
				map.put("msg", msg);
			}
	   return map;
		
	}
	
	
	  /* 删除 */
	  @ResponseBody
	  @DeleteMapping("/admin/good/{id}")
	  public Map<String,Object> remove(@PathVariable("id") Integer id,int page) {
		      boolean result=false;
		      String msg="";
	         
	          if(this.goodsService.listOrdersCountByGoodsId(id)) {
		    	   result =false;
		    	   msg="订单中存在该商品,不能被删除!";
		       }else if( this.goodsService.listState(id)==1) {
		    	   result =false;
		    	   msg="商品为上架状态,不能被删除!";
		       }else {
		    	   result=this.goodsService.remove(id);
		    	   msg="删除成功";
		       }
			 Map<String ,Object> map=new HashMap<String,Object>(); 
				if(result) {
					Pager<View_Goods> pager=this.view_GoodsService.list(null, null, null, page);
					map.put("pager", pager);
					map.put("result", result);
					map.put("msg", msg);
				}else {
					map.put("result", result);
					map.put("msg", msg);
				}
		   return map;
	  
	  }

	  /* 转到添加页 */
	  @GetMapping("/admin/good")
		public ModelAndView append() {
		  
		  List<View_Class> thridClasses=this.classService.showThrClassBySecClassId(0);
		  List<Brand> brands=this.brandService.listBrandsCountByBrandId();
			return  new ModelAndView("admin/goods/append")
					.addObject("brands",brands)
		            .addObject("thridClasses",thridClasses) ;
					
		}

	  /* 添加方法 */
	 @PostMapping("/admin/good")
	 public ModelAndView append(Goods goods, MultipartFile file) {
			
			if (this.goodsService.append(goods, file))
				return new ModelAndView("redirect:/admin/goods/1");
			else
				return new ModelAndView("admin/share/error")
						.addObject("msg", "Fail To add Goods");

		}
	  
	/* 编辑前判断是否为下架状态 */
		@ResponseBody
		@RequestMapping("/admin/good/ajaxModify/{id}")
		public boolean ajaxModify(@PathVariable("id") Integer id){
			if( this.goodsService.listState(id)==2) {
				return true;
			}else {
				return false;
			}
				
			
		}
	  /* 转到修改页 */
	  @GetMapping("/admin/good/{id}")
			public ModelAndView modify(@PathVariable("id") Integer id) {
			  
			  List<View_Class> thridClasses=this.classService.showThrClassBySecClassId(0);
			  List<Brand> brands=this.brandService.listBrandsCountByBrandId();
	          Goods goods=this.goodsService.detail(id);
				return  new ModelAndView("admin/goods/detail")
						.addObject("brands",brands)
			            .addObject("thridClasses",thridClasses)
			            .addObject("goods",goods);
					
			}
	 
	  /* 修改 */
	  @PostMapping("/admin/good/modify")
		 public ModelAndView modify(Goods goods, MultipartFile file) {
			
			if (goods != null) {
				if (this.goodsService.modify(goods,file))
					return new ModelAndView("redirect:/admin/goods/1");
				else
					return new ModelAndView("admin/share/error").addObject("msg", "修改信息失败1！");
			} else {
				return new ModelAndView("admin/share/error").addObject("msg", "修改信息失败2！");
			}
			
			

		}
		 

}
