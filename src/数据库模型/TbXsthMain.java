package ���ݿ�ģ��;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
//�����˻����������ݿ�
public class TbXsthMain implements java.io.Serializable {
	private String xsthId;  //id
	private String pzs;   //�˿�id
	private String je;   //isbn
	private String ysjl;  //�۸�
	private String khname;  //����
	private String thdate;  //ʱ��

	public TbXsthMain() {
	}

	public TbXsthMain(String xsthId, String pzs, String je, String ysjl,
			String khname, String thdate) {
		this.xsthId = xsthId;
		this.pzs = pzs;
		this.je = je;
		this.ysjl = ysjl;
		this.khname = khname;
		this.thdate = thdate;
	}

	public String getXsthId() {
		return this.xsthId;
	}

	public void setXsthId(String xsthId) {
		this.xsthId = xsthId;
	}

	public String getPzs() {
		return this.pzs;
	}

	public void setPzs(String pzs) {
		this.pzs = pzs;
	}

	public String getJe() {
		return this.je;
	}

	public void setJe(String je) {
		this.je = je;
	}

	public String getYsjl() {
		return this.ysjl;
	}

	public void setYsjl(String ysjl) {
		this.ysjl = ysjl;
	}

	public String getKhname() {
		return this.khname;
	}

	public void setKhname(String khname) {
		this.khname = khname;
	}

	public String getThdate() {
		return this.thdate;
	}

	public void setThdate(String thdate) {
		this.thdate = thdate;
	}

}