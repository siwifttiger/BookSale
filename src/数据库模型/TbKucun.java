package ���ݿ�ģ��;
//���������ݿ�
public class TbKucun  implements java.io.Serializable {
     private String id;   //isbn
     private String spname;  //����
    public TbKucun() {
    }
    public TbKucun(String id) {
        this.id = id;
    }
    public TbKucun(String id, String spname) {
        this.id = id;
        this.spname = spname;
      
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSpname() {
        return this.spname;
    }
    public void setSpname(String spname) {
        this.spname = spname;
    }
	public String toString() {
		return getSpname();
	}
}