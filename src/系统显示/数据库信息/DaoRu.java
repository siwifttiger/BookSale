package 系统显示.数据库信息;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import java.sql.PreparedStatement;

import 数据库模型.TbDictionary;
import 数据库模型.TbGysinfo;
import 数据库模型.TbKhXinX;
import 数据库模型.TbKucun;
import 数据库模型.TbRkthDetail;
import 数据库模型.TbRkthMain;
import 数据库模型.TbRukuDetail;
import 数据库模型.TbRukuMain;
import 数据库模型.TbSellDetail;
import 数据库模型.TbSellMain;
import 数据库模型.TbSpinfo;
import 数据库模型.TbUserlist;
import 数据库模型.TbXsthDetail;
import 数据库模型.TbXsthMain;
import 系统显示.管理.Item;

public class DaoRu {
	public static Connection conn = null;
	private static String second = null;
	private static Statement stmt;
	private ResultSet rs;
	private static String dbClassName = "com.mysql.jdbc.Driver";
	private static String dbUrl = "jdbc:mysql://127.0.0.1/bookmanage";
	// 打开游标
	private static String dbUser = "root";
	private static String dbPwd = "ssbb1618";
	static {
		try {
			if (conn == null) {
				Class.forName(dbClassName);
				// 连接数据库
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败xxx");
		}
	}

	private DaoRu() {
	}

	// 读取客户所有信息
	public static List getKhInfos() {
		List list = findForList("select customerid,customername from customerinfo");
		return list;
	}

	// 读取数据字典所有信息
	public static List getDictionary() {
		List list = findForList("select * from tb_dictionary");
		return list;
	}

	// 读取指定数据字典
	public static List getDictionary_cz(String typeName) {
		List list = findForList("select id,valueName from tb_dictionary where typeName = '"
				+ typeName + "'");
		return list;
	}

	// 读取所有供应商信息
	public static List getGysInfos() {
		List list = findForList("select supplyid,supplyname from supplyinfo");
		return list;
	}
	
	public static List getGysInfosByIsbn(String isbn) {
		List list = findForList("select supplyid,supplyprice,supplyquantity from quotelist where isbn ='"+isbn+"'");
		return list;
	}

	// 读取客户信息
	public static TbKhXinX getKhInfo(Item item) {
		String where = "customername='" + item.getName() + "'";
		if (item.getId() != null)
			where = "customerid='" + item.getId() + "'";
		TbKhXinX info = new TbKhXinX();
		ResultSet set = findForResultSet("select * from customerinfo where "
				+ where);
		try {
			if (set.next()) {
				info.setId(set.getString("customerid").trim());
				info.setName(set.getString("customername").trim());
				info.setCompanySite(set.getString("customeraddress").trim());
				info.setCompanyPhone(set.getString("customertelephone").trim());
				info.setLinkman(set.getString("customercontact").trim());
				info.setLinkPhone(set.getString("customercontacttel").trim());
				
				info.setAddress(set.getString("deliveryaddress").trim());
				
				info.setBankAccout(set.getString("customeraccount").trim());
				info.setEmail(set.getString("customeremail").trim());
				
				info.setRemark(set.getString("customerremark").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}

	// 读取指定供应商信息
	public static TbGysinfo getGysInfo(Item item) {
		String where = "supplyname='" + item.getName() + "'"; // 默认的查询条件以供应商名称为主
		if (item.getId() != null) // 如果Item对象中存有ID编号
			where = "supplyid='" + item.getId() + "'"; // 则以ID编号为查询条件
		TbGysinfo info = new TbGysinfo();
		ResultSet set = findForResultSet("select * from supplyinfo where "
				+ where);
		try {
			if (set.next()) {
				info.setId(set.getString("supplyid").trim()); // 封装供应商数据到实体对象中
				info.setAddress(set.getString("supplyaddress").trim());
				info.setLian(set.getString("supplycontact").trim());
				info.setLtel(set.getString("supplycontacttel").trim());
				info.setMail(set.getString("suppluyemail").trim());
				info.setName(set.getString("supplyname").trim());
				info.setTel(set.getString("supplytelephone").trim());
				info.setYh(set.getString("supplyaccount").trim());
				info.setRemark(set.getString("supplyremark").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}

	// 读取用户
	public static TbUserlist getUser(String name, String password) {
		TbUserlist user = new TbUserlist();
		try {
			
			PreparedStatement prep = conn.prepareStatement("select * from userlist where username=?");
			prep.setString(1, name);
			ResultSet rs = prep.executeQuery();
				if (rs.next()) {
					user.setUsername(name);
					user.setPass(rs.getString("pass"));
					if (user.getPass().equals(password)) {
						user.setName(rs.getString("name"));
						// user.setQuan(rs.getString("quan"));
					}
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		return user;
	}

	// 读取数据字典信息——返回相应的选项
	public static TbDictionary getDictionary(Item item) {
		String where = "valueName='" + item.getName() + "'"; // 默认的查询条件以对应数据为主
		TbDictionary info = new TbDictionary();
		ResultSet set = findForResultSet("select * from tb_dictionary where "
				+ where);
		try {
			if (set.next()) {
				info.setId(set.getString("id").trim());
				info.setTypeCode(set.getString("typeCode").trim());
				info.setTypeName(set.getString("typeName").trim());
				info.setValueCode(set.getString("valueCode").trim());
				info.setValueName(set.getString("valueName").trim());
				info.setLrczy(set.getString("lrczy").trim());
				info.setLrdate(set.getString("lrdate").trim());
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return info;
	}

	// 读取数据字典信息——更新资料
	public static TbDictionary getDictionary_gx(Item item) {
		String where = "typeName='" + item.getName() + "'"; // 默认的查询条件以对应数据为主
		TbDictionary info = new TbDictionary();
		if (item.getId() != null) // 如果Item对象中存有ID编号
			where = "id='" + item.getId() + "'"; // 则以ID编号为查询条件
		ResultSet set = findForResultSet("select * from tb_dictionary where "
				+ where);
		try {
			if (set.next()) {
				info.setId(set.getString("id").trim());
				info.setTypeCode(set.getString("typeCode").trim());
				info.setTypeName(set.getString("typeName").trim());
				info.setValueCode(set.getString("valueCode").trim());
				info.setValueName(set.getString("valueName").trim());
				info.setLrczy(set.getString("lrczy").trim());
				info.setLrdate(set.getString("lrdate").trim());
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return info;
	}
	// 执行指定查询
	public static ResultSet query(String QueryStr) {
		ResultSet set = findForResultSet(QueryStr);
		return set;
	}

	// 执行删除
	public static int delete(String sql) {
		return update(sql);
	}

	// 添加客户信息的方法
	public static boolean addKeHu(TbKhXinX khinfo) {
		if (khinfo == null)
			return false;
		return insert("insert customerinfo values('" + khinfo.getId() + "','"
				+ khinfo.getName() + "','"
				+ khinfo.getCompanySite() + "','" + khinfo.getCompanyPhone()
				+ "','" + khinfo.getBankAccout() + "','" + khinfo.getLinkman() + "','" + khinfo.getLinkPhone()
				+ "','"
				 + khinfo.getAddress()
				+ "','" + khinfo.getEmail()
				+ "','" 
				+ khinfo.getRemark() + "')");
	}

	// 添加数据字典信息的方法
	public static boolean addSjZd(TbDictionary dictionary) {
		if (dictionary == null) {
			return false;
		}
		return insert("insert tb_dictionary (typeCode,typeName,valueCode,valueName,lrczy,lrdate) values('"
				+ dictionary.getTypeCode()
				+ "','"
				+ dictionary.getTypeName()
				+ "','"
				+ dictionary.getValueCode()
				+ "','"
				+ dictionary.getValueName()
				+ "','"
				+ dictionary.getLrczy()
				+ "','" + dictionary.getLrdate() + "')");
	}

	// 修改客户信息的方法
	public static int updateKeHu(TbKhXinX khinfo) {
		return update("update customerinfo set customerid='" + khinfo.getId() 
				+ "',customername='" + khinfo.getId()
				+ "',customeraddress='" + khinfo.getCompanySite()
				+ "',customertelephone='" + khinfo.getCompanyPhone() 
				+ "',customercontact='" + khinfo.getLinkman() 
				+ "',customercontacttel='" + khinfo.getLinkPhone()
				+  "',deliveryaddress='" + khinfo.getAddress() 
				+  "',customeraccount='" + khinfo.getBankAccout() + "',customeremail='" + khinfo.getEmail()
				+ "',customerremark='" + khinfo.getRemark() 
				+ "' where customerid='" + khinfo.getId() + "'");
	}

	// 修改数据字典信息的方法
	public static int updateSjZd(TbDictionary dictionary) {
		return update("update tb_dictionary set valueCode='"
				+ dictionary.getValueCode() + "',valueName='"
				+ dictionary.getValueName() + "',lrcay='"
				+ dictionary.getLrczy() + "',lrdate='" + dictionary.getLrdate()
				+ "' where typeCode='" + dictionary.getTypeCode() + "'");
	}

	// 修改库存商品单价的方法
	public static int updateKucunDj(TbKucun kcInfo) {
		return update("update tb_kucun set dj=" + kcInfo.getId()
				+ " where id='" + kcInfo.getId() + "'");
	}

	// 修改供应商信息的方法
	public static int updateGys(TbGysinfo gysInfo) {
		return update("update supplyinfo set supplyid='" + gysInfo.getId()
				+ "',supplyname='" + gysInfo.getName() + "',supplyaddress='"
				+ gysInfo.getAddress() + "',supplytelephone='" + gysInfo.getTel()
				+ "',supplyaccount='" + gysInfo.getYh() + "',supplycontact='" + gysInfo.getLian()
				+ "',supplycontacttel='" + gysInfo.getLtel() + "',supplyemail='"
				+ gysInfo.getMail() + "',supplyremark='" + gysInfo.getRemark()
				+ "' where supplyid='" + gysInfo.getId() + "'");
	}

	// 添加供应商信息的方法
	public static boolean addGys(TbGysinfo gysInfo) {
		if (gysInfo == null)
			return false;
		return insert("insert supplyinfo values('" + gysInfo.getId() + "','"
				+ gysInfo.getName() + "','" 
				+ gysInfo.getAddress() + "','" 
				+ gysInfo.getTel() + "','" + gysInfo.getYh() + "','"
				+ gysInfo.getLian() + "','" + gysInfo.getLtel() + "','"
				+ gysInfo.getMail() + "','" + gysInfo.getRemark() + "')");
	}

	// 添加商品
	public static boolean addSp(TbSpinfo spInfo) {
		if (spInfo == null)
			return false;
		return insert("insert bookInfo values('" + spInfo.getPh() + "','"
				+ spInfo.getSpname() + "','" + spInfo.getJc() + "','"
				+ spInfo.getCd() + "','" + spInfo.getNf() + "','"
				+ spInfo.getBz() +"')");
	}

	public static boolean addBj(String isbn, String supplyId, int price, int quantity) {
		return insert("insert quotelist values('" + isbn + "','"
				+ supplyId + "','" + price + "','"
				+ quantity +"')");
	}
	
	
	// 更新商品
	public static int updateSp(TbSpinfo spInfo) {
		return update("update bookInfo set isbn='" + spInfo.getPh() + "',bookname='"
				+ spInfo.getSpname() + "',author='" + spInfo.getJc() + "',publish='"
				+ spInfo.getCd() + "',year='" + spInfo.getNf() + "',bookremark='"
				+ spInfo.getBz() +  "' where isbn='" + spInfo.getPh() + "'");
	}

	// 读取商品信息
	public static TbSpinfo getSpInfo(Item item) {
		String where = "bookname='" + item.getName() + "'";
		if (item.getId() != null)
			where = "isbn='" + item.getId() + "'";
		ResultSet rs = findForResultSet("select * from bookinfo where "
				+ where);
		TbSpinfo spInfo = new TbSpinfo();
		try {
			if (rs.next()) {
				spInfo.setBz(rs.getString("bookremark").trim());
				spInfo.setCd(rs.getString("publish").trim());
				spInfo.setJc(rs.getString("author").trim());
				spInfo.setPh(rs.getString("isbn").trim());
				spInfo.setSpname(rs.getString("bookname").trim());
				spInfo.setNf(rs.getString("year").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return spInfo;
	}

	

	public static List getKcInfos(String isbn) {
		List list = findForList("select totalquantity from inventory where isbn = '" + isbn + "'");
		return list;
	}
	
	// 获取所有商品信息
	public static List getSpInfos() {
		List list = findForList("select * from bookinfo");
		return list;
	}

	public static List getSpInfosByName(String name) {
		List list = findForList("select * from bookinfo where bookname like '%" + name + "%'");
		return list;
	}
	
	public static List getSpInfosById(String id) {
		List list = findForList("select * from salelist where customerid = '" + id + "'");
		return list;
	}
	
	// 获取库存商品信息
	public static TbKucun getKucun(Item item) {
		String where = "totalquantity='" + item.getName() + "'";
		if (item.getId() != null)
			where = "isbn='" + item.getId() + "'";
		ResultSet rs = findForResultSet("select * from inventory where " + where);
		TbKucun kucun = new TbKucun();
		try {
			if (rs.next()) {
				kucun.setId(rs.getString("isbn"));
				kucun.setSpname(rs.getString("totalquantity"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kucun;
	}

	// 获取入库单的最大ID，即最大入库票号
	public static String getRuKuMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "tb_ruku_main", "RK", "rkid");
	}

	// 在事务中添加入库信息
	public static boolean insertRukuInfo(TbRukuMain ruMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// 添加入库主表记录
			insert("insert into supplylist(supplyid,isbn,supplyprice,neededquantity,date) values('" + ruMain.getJe() + "'," + ruMain.getGysname() + ",'"
					+ ruMain.getYsjl() + "','" + ruMain.getPzs() + "','"
					+ ruMain.getRkdate() + "')");
				// 添加或修改库存表记录
				Item item = new Item();
				item.setId(ruMain.getGysname());
				TbSpinfo spInfo = getSpInfo(item);
				TbKucun kucun = getKucun(item);
				if (kucun.getId() == null || kucun.getId().isEmpty()) { // 如果是新增商品，则添加一条记录
					insert("insert into inventory values('" + spInfo.getPh()
								+ "','" + ruMain.getPzs() + "')");
					} else { // 若仓库中已存该商品，则将新增入库数量加到已有库存商品中
						int sl = Integer.parseInt(kucun.getSpname()) + Integer.parseInt(ruMain.getPzs());
						update("update inventory set totalquantity=" + sl + " where isbn='"
								+ kucun.getId() + "'");
					}
				
			
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return true;
	}

	public static ResultSet findForResultSet(String sql) {
		if (conn == null)
			return null;
		long time = System.currentTimeMillis();
		ResultSet rs = null;
		try {
			Statement stmt = null;
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			second = ((System.currentTimeMillis() - time) / 1000d) + "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static boolean insert(String sql) {
		boolean result = false;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static int update(String sql) {
		int result = 0;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static List findForList(String sql) {
		List<List> list = new ArrayList<List>();
		ResultSet rs = findForResultSet(sql);
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			while (rs.next()) {
				List<String> row = new ArrayList<String>();
				for (int i = 1; i <= colCount; i++) {
					String str = rs.getString(i);
					if (str != null && !str.isEmpty())
						str = str.trim();
					row.add(str);
				}
				list.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 获取退货最大ID
	public static String getRkthMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "tb_rkth_main", "RT", "rkthId");
	}

	// 在事务中添加入库退货信息
	public static boolean insertRkthInfo(TbRkthMain rkthMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// 添加入库退货主表记录
			insert("insert into tb_rkth_main values('" + rkthMain.getRkthId()
					+ "','" + rkthMain.getPzs() + "'," + rkthMain.getJe()
					+ ",'" + rkthMain.getYsjl() + "','" + rkthMain.getGysname()
					+ "','" + rkthMain.getRtdate() + "','" + rkthMain.getCzy()
					+ "','" + rkthMain.getJsr() + "','" + rkthMain.getJsfs()
					+ "')");
			Set<TbRkthDetail> rkDetails = rkthMain.getTbRkthDetails();
			for (Iterator<TbRkthDetail> iter = rkDetails.iterator(); iter
					.hasNext();) {
				TbRkthDetail details = iter.next();
				// 添加入库退货详细表记录
				String sql = "select max(id) from  tb_rkth_detail "; // 查找tb_rkth_detail表最大id
				ResultSet set = query(sql);
				int MaxId = 0;
				try {
					if (set.next())
						MaxId = set.getInt(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				MaxId = MaxId + 1;
				insert("insert into tb_rkth_detail values('" + MaxId + "','"
						+ rkthMain.getRkthId() + "','" + details.getSpid()
						+ "'," + details.getDj() + "," + details.getSl() + ")");
				// 添加或修改库存表记录
				Item item = new Item();
				item.setId(details.getSpid());
				TbSpinfo spInfo = getSpInfo(item);
				if (spInfo.getCd() != null && !spInfo.getCd().isEmpty()) {
					TbKucun kucun = getKucun(item);
					if (kucun.getId() != null && !kucun.getId().isEmpty()) {
						//int sl = kucun.getId() - details.getSl();
						//update("update tb_kucun set kcsl=" + sl + " where id='"
								//+ kucun.getId() + "'");
					}
				}
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	// 获取销售主表最大ID
	public static String getSellMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "tb_sell_main", "XS", "sellID");
	}

	// 在事务中添加销售信息
	public static boolean insertSellInfo(TbSellMain sellMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// 添加销售主表记录
			insert("insert into salelist(customerid,isbn,saleprice,salequantity,date) values('" 
					+ sellMain.getKhname()
					+ "','" + sellMain.getYsjl() 
					+ "','" + sellMain.getPzs() 
					+ "','" + sellMain.getJe()
					+ "','" + sellMain.getXsdate() 
					+ "')");
			
				// 修改库存表记录
				Item item = new Item();
				item.setId(sellMain.getYsjl());
				TbSpinfo spInfo = getSpInfo(item);
				
					TbKucun kucun = getKucun(item);
					if (kucun.getId() != null && !kucun.getId().isEmpty()) {
						int sl = Integer.parseInt(kucun.getSpname()) - Integer.parseInt(sellMain.getJe());
						update("update inventory set totalquantity=" + sl + " where isbn='"
								+ kucun.getId() + "'");
					}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	// 获取各类主表最大ID
	private static String getMainTypeTableMaxId(Date date, String table,
			String idChar, String idName) {
		String dateStr = date.toString().replace("-", ""); // 去掉时间字符串中的字符"-"
		String id = idChar + dateStr;
		String sql = "select max(" + idName + ") from " + table + " where " // 查找当日最大ID
				+ idName + " like '" + id + "%'";
		ResultSet set = query(sql);
		String baseId = null;
		try {
			if (set.next())
				baseId = set.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		baseId = baseId == null ? "000" : baseId.substring(baseId.length() - 3);
		int idNum = Integer.parseInt(baseId) + 1;
		id += String.format("%03d", idNum);
		return id;
	}

	public static String getXsthMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "tb_xsth_main", "XT", "xsthID");
	}

	public static List getKucunInfos() {
		List list = findForList("select isbn,bookname,supplyname,supplyprice,neededquantity,totalquantity from inventory natural join bookinfo natural join"
+"supplylist natural join supplyinfo;");
		return list;
	}

	// 在事务中添加销售退货信息
	public static boolean insertXsthInfo(TbXsthMain xsthMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// 添加销售退货主表记录
			insert("insert into returnlist(customerid,isbn,returnprice,returnquantity,date) values('" 
					+ xsthMain.getPzs() + "'," + xsthMain.getJe()
					+ ",'" + xsthMain.getYsjl() + "','" + xsthMain.getKhname()
					+ "','" + xsthMain.getThdate() 
					+ "')");
			
				Item item = new Item();
				item.setId(xsthMain.getPzs());
				TbSpinfo spInfo = getSpInfo(item);
				
					TbKucun kucun = getKucun(item);
					if (kucun.getId() != null && !kucun.getId().isEmpty()) {
						int sl = Integer.parseInt(kucun.getSpname()) + Integer.parseInt(xsthMain.getKhname());
						update("update inventory set totalquantity=" + sl + " where isbn='"
								+ kucun.getId() + "'");
					}
				
			
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	// 添加用户
	public static int addUser(TbUserlist ul) {
		return update("insert tb_userlist values('" + ul.getUsername() + "','"
				+ ul.getName() + "','" + ul.getPass() + "','" + ul.getQuan()
				+ "')");
	}

	public static List getUsers() {
		List list = findForList("select * from tb_userlist");
		return list;
	}

	// 修改用户方法
	public static int updateUser(TbUserlist user) {
		/**
		 * 不能添加权限管理功能
		 */
		/*String sql="update tb_userlist set username='" + user.getUsername()
				+ "',name='" + user.getName() + "',pass='" + user.getPass()
				+ "',quan='" + user.getQuan() + "' where name='"
				+ user.getName() + "'";
		System.out.println(sql);*/
		return update("update tb_userlist set username='" + user.getUsername()
				+ "',name='" + user.getName() + "',pass='" + user.getPass()
				 + "' where name='"
				+ user.getName() + "'");
	}

	// 获取用户对象的方法
	public static TbUserlist getUser(Item item) {
		String where = "username='" + item.getName() + "'";
		if (item.getId() != null)
			where = "name='" + item.getId() + "'";
		ResultSet rs = findForResultSet("select * from tb_userlist where "
				+ where);
		TbUserlist user = new TbUserlist();
		try {
			if (rs.next()) {
				user.setName(rs.getString("name").trim());
				user.setUsername(rs.getString("username").trim());
				user.setPass(rs.getString("pass").trim());
				user.setQuan(rs.getString("quan").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
