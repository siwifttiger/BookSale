package 数据库模型;
//数据字典——数据库
public class TbDictionary {
	private String id;	//序列号
	private String typeCode;	//对应数据
	private String typeName;	//对应数据名称
	private String valueCode;	//对应数据选项
	private String valueName;	//对应数据选项名称
	private String lrczy;		//录入数据的操作员名称
	private String lrdate;		//录入数据的日期

	public TbDictionary(){

	}

	public TbDictionary(String id) {
		this.id = id;
	}

	/**
	 * @param id
	 * @param typeCode
	 * @param typeName
	 * @param valueCode
	 * @param valueName
	 * @param lrczy
	 * @param lrdate
	 */
	public TbDictionary(String id, String typeCode, String typeName,
			String valueCode, String valueName, String lrczy, String lrdate) {
		this.id = id;
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.valueCode = valueCode;
		this.valueName = valueName;
		this.lrczy = lrczy;
		this.lrdate = lrdate;
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id 要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * @param typeCode 要设置的 typeCode
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * @return typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName 要设置的 typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return valueCode
	 */
	public String getValueCode() {
		return valueCode;
	}

	/**
	 * @param valueCode 要设置的 valueCode
	 */
	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}

	/**
	 * @return valueName
	 */
	public String getValueName() {
		return valueName;
	}

	/**
	 * @param valueName 要设置的 valueName
	 */
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	/**
	 * @return lrczy
	 */
	public String getLrczy() {
		return lrczy;
	}

	/**
	 * @param lrczy 要设置的 lrczy
	 */
	public void setLrczy(String lrczy) {
		this.lrczy = lrczy;
	}

	/**
	 * @return lrdate
	 */
	public String getLrdate() {
		return lrdate;
	}

	/**
	 * @param lrdate 要设置的 lrdate
	 */
	public void setLrdate(String lrdate) {
		this.lrdate = lrdate;
	}



}
