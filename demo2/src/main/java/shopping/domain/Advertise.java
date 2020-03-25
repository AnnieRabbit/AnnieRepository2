package shopping.domain;

public class Advertise {

	private int id;
	private String addtime;
	private String adname;
	private String path;
	private String url;
	private int seq;
	private int display;
	
	
	@Override
	public String toString() {
		return "Advertise [id=" + id + ", addtime=" + addtime + ", adname=" + adname + ", path=" + path + ", url=" + url
				+ ", seq=" + seq + ", display=" + display + "]";
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
	public String getAdname() {
		return adname;
	}
	public void setAdname(String adname) {
		this.adname = adname;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getDisplay() {
		return display;
	}
	public void setDisplay(int display) {
		this.display = display;
	}
	
	
}
