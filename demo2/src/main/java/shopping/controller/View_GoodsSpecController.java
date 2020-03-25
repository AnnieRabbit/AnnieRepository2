package shopping.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import shopping.domain.Goods;
import shopping.domain.Spec;
import shopping.domain.Specv;
import shopping.domain.View_GoodsSpec;
import shopping.service.GoodsService;
import shopping.service.SpecService;
import shopping.service.View_GoodsSpecService;

@Controller
public class View_GoodsSpecController extends BaseController {

	@Autowired
	private View_GoodsSpecService view_Goods_SpecService;

	@Autowired
	private SpecService specService;

	@Autowired
	private GoodsService goodsService;

	/* 转到修改页取值 */
	@GetMapping("/admin/good/good_Spec/{goodsid}")
	public ModelAndView modify(@PathVariable("goodsid") Integer goodsid) {

		List<View_GoodsSpec> goods_Specs = this.view_Goods_SpecService.detail(goodsid);
		List<Spec> specs = this.specService.showSpecList();
		Goods goods = this.goodsService.detail(goodsid);
		List<Specv> specvs=this.specService.showAllSpecvsList();
		return new ModelAndView("admin/goods/spec")
				.addObject("goods_Specs", goods_Specs)
				.addObject("specs", specs)
				.addObject("goods", goods)
				.addObject("specvs", specvs);

	}

	/* 点击下拉列表框,实现二级联动 */
	@ResponseBody
	@PostMapping("/admin/good_Spec/ajaxGoods/{id}")
	public Collection<Specv> ajaxGoods(@PathVariable("id") Integer id) {
		List<Specv> specvs = this.specService.showSpecvsList(id);
		System.out.println("specvs" + specvs);
		return specvs;

	}


	/* 修改规格 */
	@ResponseBody
	@PutMapping("/admin/good_Spec")
	public Map<String, Object> modify(Integer goodsid, String goodscode, Integer[] specids, Integer[] specvids) {

		boolean result = this.view_Goods_SpecService.modify(goodsid, goodscode, specids, specvids);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;

	}

}
