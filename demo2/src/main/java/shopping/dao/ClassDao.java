package shopping.dao;

public interface ClassDao {

	/* 多级新增 */
	public boolean insert(shopping.domain.Class obj);
	
	/* 多级编辑保存按钮 */
	public boolean update(shopping.domain.Class obj);
	
	/* 多级删除 */
    public boolean delete(Integer id);  
    
	/* 一级二级分类详情 */
	public shopping.domain.Class selectFirstById(Integer id);
	
	/* 多级序列最大值 */
	public int selectMaxSeqByLevel(Integer level);
	
	/* 删除前判断分类下是否有商品*/
	public Integer selectGoodsCountByClassId(Integer id);
	

}