package shopping.domain;

public class Specv {

	private int id;
	private String addtime;
	private int specid;
	private String specvname;
	private int seq;
	
	@Override
	public String toString() {
		return "Specv [id=" + id + ", addtime=" + addtime + ", specid=" + specid + ", specvname=" + specvname + ", seq="
				+ seq + "]";
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
	public String getSpecvname() {
		return specvname;
	}
	public void setSpecvname(String specvname) {
		this.specvname = specvname;
	}
	public int getSpecid() {
		return specid;
	}
	public void setSpecid(int specid) {
		this.specid = specid;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	
}
