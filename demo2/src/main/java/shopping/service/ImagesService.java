package shopping.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import shopping.domain.Images;


public interface ImagesService {

	
	/* 修改附图 */
    public boolean modifyImages(Integer goodsid, MultipartFile[] file);
	
	/* 详情 */
	public List<Images> detail(Integer goodsid);
		
	
}
