package shopping.domain;

public class User {

	private int id;
	private String addtime;
	private String username;
	private String password;
	private String tel;
	private String email;
	private String realname;
	private String sex;
	private String path;
	private int roles;
	private int state;
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", addtime=" + addtime + ", username=" + username + ", password=" + password
				+ ", tel=" + tel + ", email=" + email + ", realname=" + realname + ", sex=" + sex + ", path=" + path
				+ ", roles=" + roles + ", state=" + state + "]";
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getRoles() {
		return roles;
	}
	public void setRoles(int roles) {
		this.roles = roles;
	}
	
	
}
