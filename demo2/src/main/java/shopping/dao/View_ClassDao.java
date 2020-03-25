package shopping.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shopping.domain.View_Class;

public interface View_ClassDao {

	
	/* 一级分类条件查询 */
	public List<View_Class> firstSelectsByCondition(@Param("condition") View_Class condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("skip") int skip, @Param("take") int take);
	/* 一级分类条件记录数 */
    public int firstSizeByCondition(@Param("condition") View_Class condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
	
    /* 二级分类条件查询 */
	public List<View_Class> secondSelectsByCondition(@Param("condition") View_Class condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("skip") int skip, @Param("take") int take);
	/* 二级分类条件记录数 */
    public int secondSizeByCondition(@Param("condition") View_Class condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
	
    /* 三级分类条件查询 */
	public List<View_Class> thridSelectsByCondition(@Param("condition") View_Class condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("skip") int skip, @Param("take") int take);
	/* 三级分类条件记录数 */
    public int thridSizeByCondition(@Param("condition") View_Class condition, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
	  
    /* 查询所有一级分类list */
	/* public List<View_Class> selectFirClassInSecClassList(); */
    
    /*根据一级id查二级分类list  */
	/* public List<View_Class> selectSecClassByFirClassId(Integer first_id); */
       
    /*根据二级id查三级分类list 用于二级删除和三级index页下拉列表  */
	/* public List<View_Class> selectThrClassBySecClassId(Integer second_id); */
      
    /* 三级分类详情(编辑按钮调用) */
	public View_Class selectThridById(Integer thrid_id);
    
}