package ���ݿ�ģ��;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//�������������ݿ�
public class TbSellMain implements java.io.Serializable {
	private String sellId;   //id
	private String pzs;   //�۸�
	private String je;    //����
	private String ysjl;  //isbn
	private String khname;  //�ͻ�id
	private String xsdate;   //ʱ��

	public TbSellMain() {
	}

	public TbSellMain(String sellId, String pzs, String je, String ysjl,
			String khname, String  xsdate) {
		this.sellId = sellId;
		this.pzs = pzs;
		this.je = je;
		this.ysjl = ysjl;
		this.khname = khname;
		this.xsdate = xsdate;
	}

	public String getSellId() {
		return this.sellId;
	}

	public void setSellId(String sellId) {
		this.sellId = sellId;
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

	public String getXsdate() {
		return this.xsdate;
	}

	public void setXsdate(String xsdate) {
		this.xsdate = xsdate;
	}

}