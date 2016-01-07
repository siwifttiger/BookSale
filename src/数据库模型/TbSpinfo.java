package 数据库模型;

//商品信息表——数据库
public class TbSpinfo implements java.io.Serializable {

	private String spname; //书名
	private String jc;    //作者
	private String cd;   //出版社
	private String bz;   //备注
	private String ph;   //ISBN
	private String nf;   //出版年份

	public TbSpinfo() {
	}

	public String getSpname() {
		return this.spname;
	}

	public void setSpname(String spname) {
		this.spname = spname;
	}

	public String getJc() {
		return this.jc;
	}

	public void setJc(String jc) {
		this.jc = jc;
	}

	public String getCd() {
		return this.cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getNf() {
		return this.nf;
	}

	public void setNf(String nf) {
		this.nf = nf;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getPh() {
		return this.ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}


	public String toString() {
		return getSpname();
	}

}