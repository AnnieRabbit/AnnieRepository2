package shopping.service;

import java.util.List;

import shopping.domain.View_GoodsSpec;

public interface View_GoodsSpecService {

	
	/* 修改 */
	public boolean modify(Integer goodsid, String goodscode, Integer[] specids, Integer[] specvids);

	/* 详情 */
	public List<View_GoodsSpec> detail(Integer goodsid);

}
