package 数据库模型;

//供应商信息表——数据库
public class TbGysinfo implements java.io.Serializable {

	// Fields

	private String id;    //id
	private String name;  //名称
	private String address;  //地址
	private String tel;    //公司电话
	private String lian;   //联系人
	private String ltel;   //联系电话
	private String yh;    //银行账号
	private String mail;   //邮箱

	private String remark;			//备注
	
	// Constructors

	/** default constructor */
	public TbGysinfo() {
	}

	/** minimal constructor */
	public TbGysinfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public TbGysinfo(String id, String name,  String address,
			 String tel, String lian, String ltel,
			String yh, String mail, String remark) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.lian = lian;
		this.ltel = ltel;
		this.yh = yh;
		this.mail = mail;
		this.remark = remark;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getLian() {
		return this.lian;
	}

	public void setLian(String lian) {
		this.lian = lian;
	}

	public String getLtel() {
		return this.ltel;
	}

	public void setLtel(String ltel) {
		this.ltel = ltel;
	}

	public String getYh() {
		return this.yh;
	}

	public void setYh(String yh) {
		this.yh = yh;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}