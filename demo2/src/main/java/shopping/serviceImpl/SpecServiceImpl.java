package shopping.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import shopping.dao.SpecDao;
import shopping.dao.SpecvDao;
import shopping.domain.Spec;
import shopping.domain.Specv;
import shopping.service.SpecService;
import shopping.util.Cons;
import shopping.util.Pager;
@Service
public class SpecServiceImpl implements SpecService {

	@Autowired
	private SpecDao specDao;

	@Autowired
	private SpecvDao specvDao;
	
	@Autowired
	private Cons cons;
	
	
	/* 分页 */
	@Override
	public Pager<Spec> list(Spec condition, String beginTime, String endTime, int page) {

		List<Spec> datas=this.specDao.selectsByCondition(condition, beginTime, endTime, (page-1)*cons.getPage_size(), cons.getPage_size());
		
		int size=this.specDao.sizeByCondition(condition, beginTime, endTime);
		return new Pager<Spec>(datas, size, page, cons.getPage_size());
	}

	  /* 添加 */
	  @Override  
	  @Transactional(propagation = Propagation.REQUIRED,rollbackFor =
	  Exception.class) 
	  public boolean append(Spec spec, String[] names) { 
		  int maxSeq=this.specDao.selectMaxSeq()+1; 
		  spec.setSeq(maxSeq);	  
	  
	      boolean result1=this.specDao.insert(spec); 
		  boolean result2=false; 
		  if(result1) {
		  List<Specv>specvs=new ArrayList<Specv>(); 
		  int specvMaxSeq=this.specvDao.selectMaxSeq();
		  for(String name:names) { 
		     Specv obj=new Specv(); 
		     obj.setSpecid(spec.getId()); 
		     obj.setSpecvname(name);
		     obj.setSeq(++specvMaxSeq); 
		  specvs.add(obj); 
		  }
			  result2= this.specvDao.insert(specvs); 
			  
		} 
		  return result1&&result2;
 
	    }
	 

		
	/* 详情 */
	@Override
	public Spec detail(Integer id) {
		return this.specDao.selectById(id);
	}

	
	/* 删除规格及详情 */
	@Override
	public boolean remove(Integer id) {	
		boolean remove1=this.specDao.delete(id);
		boolean remove2=this.specvDao.deleteAllSpecvs(id);
			
	  return remove1&&remove2;
	}

	
	/* 删除一条规格详情 */
	@Override
	public boolean removeOneSpecv(Integer id) {
		
		return this.specvDao.deleteOneSpecv(id);
	}
	
	
	/* 修改spec */
	@Override
	public boolean modify(Spec spec) {
		return this.specDao.update(spec);
	}

	

	/* 修改页3.0最终 */
	@Override
	public boolean modifies(Spec spec, String[] names, Integer[] ids, Integer[] seqs) {
		
		List<Specv>modifySpecvs=new ArrayList<Specv>();
		List<Specv>addSpecvs=new ArrayList<Specv>();
		int specid=spec.getId();
		  int i=0;
		  boolean result=false;
	
		  result=this.specDao.update(spec);
		
			 for(Integer id:ids) { 
				  if(id!=0) {
					  Specv obj=new Specv(); 
					     obj.setId(id); 
					     obj.setSpecvname(names[i]);
					     obj.setSeq(seqs[i]); 
					     i++;
					     modifySpecvs.add(obj); 
				  }else {
					  Specv obj=new Specv(); 
					     obj.setSpecvname(names[i]);
					     obj.setSeq(seqs[i]); 
					     obj.setSpecid(specid);
					     
					     i++;
					     addSpecvs.add(obj);   
				  }
			  }
		 
	 if(result) {
		    if(addSpecvs.size()!=0) {
			this.specvDao.insert(addSpecvs);
		    }
		 
            if(modifySpecvs.size()!=0) {
        	 this.specvDao.updateSpecvs(modifySpecvs);
       
            }
	 }
		
		return result;   
	}
	/* 无条件查询所有规格 */
	@Override
	public List<Spec> showSpecList() {
		
				List<Spec> specs=this.specDao.selectsByCondition(null, null, null, 0, 0);
				return 	specs;
				
	}

	/* 根据规格id查询规格详情 */
	@Override
	public List<Specv> showSpecvsList(Integer id) {
	
		List<Specv> specvs= this.specvDao.selectById(id);
		return specvs;
	}

	/* 无条件查询所有规格详情 */
	@Override
	public List<Specv> showAllSpecvsList() {
		
		List<Specv> specvs= this.specvDao.selectAllSpecvs();
		
		return specvs;
	}

}
