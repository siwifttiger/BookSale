package ���ݿ�ģ��;

//�����˻���ϸ�������ݿ�
public class TbXsthDetail implements java.io.Serializable {
	private String id;//�˿�id
	private String tbXsthMain;    //����
	private String spid;  //isbn
	private String dj;  //�۸�
	private String sl;  //����

	public TbXsthDetail() {
	}

	public TbXsthDetail(String id, String tbXsthMain, String spid, String dj, String sl) {
		this.id = id;
		this.tbXsthMain = tbXsthMain;
		this.spid = spid;
		this.dj = dj;
		this.sl = sl;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTbXsthMain() {
		return this.tbXsthMain;
	}

	public void setTbXsthMain(String tbXsthMain) {
		this.tbXsthMain = tbXsthMain;
	}

	public String getSpid() {
		return this.spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public String getDj() {
		return this.dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	public String getSl() {
		return this.sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}
}