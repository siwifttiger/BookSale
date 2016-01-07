package 数据库模型;

import java.util.HashSet;
import java.util.Set;

//入库主表——数据库
public class TbRukuMain implements java.io.Serializable {
	private String rkId;  //编号
	private String pzs;   //数量
	private String je;    //供应商
	private String ysjl;  //价格
	private String gysname;  //isbn
	private String rkdate;  //日期
	

	public TbRukuMain() {
	}

	public TbRukuMain(String rkId, String pzs, String je, String ysjl,
			String gysname, String rkdate) {
		this.rkId = rkId;
		this.pzs = pzs;
		this.je = je;
		this.ysjl = ysjl;
		this.gysname = gysname;
		this.rkdate = rkdate;
	}

	public String getRkId() {
		return this.rkId;
	}

	public void setRkId(String rkId) {
		this.rkId = rkId;
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

	public void setYsjl(String sf) {
		this.ysjl = sf;
	}

	public String getGysname() {
		return this.gysname;
	}

	public void setGysname(String gysname) {
		this.gysname = gysname;
	}

	public String getRkdate() {
		return this.rkdate;
	}

	public void setRkdate(String rkdate) {
		this.rkdate = rkdate;
	}


}