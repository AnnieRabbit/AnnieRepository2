package shopping.dao;

import shopping.domain.Goods;


public interface GoodsDao {

	
	/* 上架下架*/
	public boolean ChangeState(Goods goods);
	
	/* 删除*/
	public boolean delete(Integer id);  
	
	/* 判断是否可以删除 */
	public int isExistsInOrderDetail(Integer goodsid);
	
	/* 查询当前上下架状态*/
	public int selectState(Integer id);
	
	/*新增*/
	public boolean insert(Goods goods);
	
	/*详情*/
	public Goods selectById(Integer id);
	
	/*修改*/
	public boolean update(Goods goods);
}



