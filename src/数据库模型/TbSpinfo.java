package ���ݿ�ģ��;

//��Ʒ��Ϣ�������ݿ�
public class TbSpinfo implements java.io.Serializable {

	private String spname; //����
	private String jc;    //����
	private String cd;   //������
	private String bz;   //��ע
	private String ph;   //ISBN
	private String nf;   //�������

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