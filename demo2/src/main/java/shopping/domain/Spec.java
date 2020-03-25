package shopping.domain;

import java.util.List;

/*@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})*/
public class Spec {

	private int id;
	private String addtime;
	private String specname;
	private int seq;
	private List<Specv> specvs;
	
	@Override
	public String toString() {
		return "Spec [id=" + id + ", addtime=" + addtime + ", specname=" + specname + ", seq=" + seq + ", specvs="
				+ specvs + "]";
	}
	public List<Specv> getSpecvs() {
		return specvs;
	}
	public void setSpecvs(List<Specv> specvs) {
		this.specvs = specvs;
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
	public String getSpecname() {
		return specname;
	}
	public void setSpecname(String specname) {
		this.specname = specname;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	
}
