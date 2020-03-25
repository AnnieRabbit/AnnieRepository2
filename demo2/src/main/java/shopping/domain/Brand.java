package shopping.domain;

public class Brand {

	private int id;
	private String addtime;
	private String brandname;
	private String path;
	private int seq;
	private int recommend;
	@Override
	public String toString() {
		return "Brand [id=" + id + ", addtime=" + addtime + ", brandname=" + brandname + ", path=" + path + ", seq="
				+ seq + ", recommend=" + recommend + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	
	
}
