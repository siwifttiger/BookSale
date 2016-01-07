package 数据库模型;

//用户表（操作员）——数据库
public class TbUserlist {
	private String name;	//用户姓名
	private String username;//登录名
	private String pass;	//登录密码
	private String quan;	//权限

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getQuan() {
		return this.quan;
	}

	public void setQuan(String quan) {
		this.quan = quan;
	}
}