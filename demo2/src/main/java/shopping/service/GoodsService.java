package shopping.service;

import org.springframework.web.multipart.MultipartFile;

import shopping.domain.Brand;
import shopping.domain.Goods;
import shopping.domain.User;

public interface GoodsService {
			
	/* 启用禁用 */
	public boolean changeState(Integer id);	
	
	/* 删除 */
	public boolean remove(Integer id);
	
	/* 判断订单中是否有此商品,是否可以删除 */
	public boolean listOrdersCountByGoodsId(Integer id);
	
	/* 查询当前上下架状态*/
	public int listState(Integer id);
	
	/* 添加 */
	public boolean append(Goods goods, MultipartFile file);
	
	/* 详情 */
	public Goods detail(Integer id);
	
	/* 修改 */
	public boolean modify(Goods goods, MultipartFile file);
	

	
}
