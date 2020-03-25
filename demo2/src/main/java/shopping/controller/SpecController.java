package shopping.controller;

import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;

import shopping.dao.SpecDao;
import shopping.dao.SpecvDao;
import shopping.domain.Spec;
import shopping.service.SpecService;
import shopping.util.Pager;

@Controller
public class SpecController extends BaseController {

	@Autowired
	private SpecService specService;

	@Autowired
	private SpecDao specDao;

	@Autowired
	private SpecvDao specvDao;

	@RequestMapping("/admin/specs/{page}")
	public ModelAndView index(Spec condition, String beginTime, String endTime, @PathVariable("page") Integer page) {

		Pager<Spec> pager = this.specService.list(condition, beginTime, endTime, page);
		return new ModelAndView("admin/spec/index").addObject("condition", condition).addObject("beginTime", beginTime)
				.addObject("endTime", endTime).addObject("pager", pager);

	}

	/* 新增页面 */
	@GetMapping("/admin/spec")
	public String append() {
		return "admin/spec/detail";
	}

	/* 新增方法 */
	@PostMapping("/admin/spec")
	public ModelAndView detail(Spec spec, String[] names) {

		if (this.specService.append(spec, names))
			return new ModelAndView("admin/spec/detail").addObject("msg", "新增规格成功");
		else
			return new ModelAndView("admin/spec/detail").addObject("msg", "新增规格失败");

	}

	/* 编辑取值页面 */
	@GetMapping("/admin/spec/{id}")
	public ModelAndView detail(@PathVariable("id") Integer id) {
		return new ModelAndView("admin/spec/detail")
				.addObject("spec", this.specService.detail(id));
	}

	/* 列表页删除 */
	@ResponseBody
	@DeleteMapping("/admin/spec/{id}")
	public Map<String, Object> remove(@PathVariable("id") Integer id) {
		boolean result = false;
		if (this.specDao.isUsedInGoodsBySpecid(id) > 0) {
			result = false;
		} else {
			this.specService.remove(id);
			result = true;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}

	/* detail页删除 */
	@ResponseBody
	@DeleteMapping("/admin/spec/detail/{id}")
	public Map<String, Object> removeDetail(@PathVariable("id") Integer id) {
		boolean result = false;
		if (this.specvDao.isUsedInGoods(id) > 0) {
			result = false;
		} else {
			this.specService.removeOneSpecv(id);
			result = true;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}

	/* detail页 修改规格名称 */
	 @ResponseBody
	 @PutMapping("/admin/spec") 
	 public  Map<String, Object>  modify(Spec spec,String[]names,Integer[]ids,Integer[]seqs){
		 boolean result=this.specService.modifies(spec, names, ids, seqs);
		 Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			return map;
	  }
	 
	
}
