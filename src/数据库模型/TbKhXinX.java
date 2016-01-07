package 数据库模型;
//客户信息——数据库
public class TbKhXinX implements java.io.Serializable{
	private String id;				//序列号
	private String name;			//公司名称
	private String companySite;		//公司地址
	private String companyPhone;	//公司电话
	private String linkman;			//联系人
	private String linkPhone;		//联系电话
	private String address;			//投递地址
	private String bankAccout;		//银行账号
	private String email;			//邮箱
	private String remark;			//备注
	public TbKhXinX(){
	}
	public TbKhXinX(String id){
		this.id = id;
	}
	public TbKhXinX(String id, String name , String companySite
			, String companyPhone, String linkman, String linkPhone, 
			 String address, 
			String bankAccout, String email,  String remark){
		this.id = id;
		this.name = name;
		this.companySite = companySite;
		this.companyPhone = companyPhone;
		this.linkman = linkman;
		this.linkPhone = linkPhone;
		this.address = address;
		this.bankAccout = bankAccout;
		this.email = email;
		this.remark = remark;
	}
	//构造方法
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompanySite() {
		return companySite;
	}
	public void setCompanySite(String companySite) {
		this.companySite = companySite;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBankAccout() {
		return bankAccout;
	}
	public void setBankAccout(String bankAccout) {
		this.bankAccout = bankAccout;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
