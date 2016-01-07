package 系统显示.基础信息管理.客户信息管理;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import 关键字监听器.InputKeyListener;
import 数据库模型.TbDictionary;
import 数据库模型.TbKhXinX;
import 系统显示.数据库信息.DaoRu;
import 系统显示.管理.Item;
import 系统显示.管理.Time;

public class KeHuAddPanel extends JPanel{
	private JTextField keHuDaiMa;		//客户代码
	private JTextField gongSiMingCheng;	//公司名称
	private JTextField diZhi;			//公司地址
	private JTextField dianHua;			//公司电话
	private JTextField lianXiRen;		//联系人
	private JTextField lianXiDianHua;	//联系电话
	private JTextField touDiDiZhi;		//投递地址
	private JTextField yinHangZhangHao;	//银行账号
	private JTextField EMail;			//E-Mail
	private JTextArea beiZhu;			//备注
	private JScrollPane bz;				//备注滚动条
	private JButton resetButton;
	private String code = null;			//客户代码设置

	public KeHuAddPanel(){
		super();
		//JPanel 界面属性
		setBounds(10, 10, 300, 350);	//设置布局位置
		setLayout(new GridBagLayout());	//设置为网格布局管理器
		setVisible(true);				//设置视图可见
		//创建终态标签，并设置文本，不可修改
		final JLabel khDM = new JLabel("客户ID：");			//客户代码
		final JLabel gsName = new JLabel("公司名称：");		//公司名称
		final JLabel gsSite = new JLabel("公司地址：");		//公司地址
		final JLabel phone = new JLabel("公司电话：");			//公司电话
		final JLabel linkMan = new JLabel("联系人：");		//联系人
		final JLabel linkPhone = new JLabel("联系电话：");		//联系电话		//客户分区
		final JLabel touDDZ = new JLabel("投递地址：");		//投递地址

		final JLabel yhZH = new JLabel("银行账号：");			//银行账号
		final JLabel email = new JLabel("E-Mail：");			//主页
		final JLabel beiZ = new JLabel("备注：");				//备注
		//构建文本框以及各种组件
		keHuDaiMa = new JTextField();		//客户代码
		code = new Time().getTime();
		keHuDaiMa.setText(code);
		keHuDaiMa.setEditable(false);
		gongSiMingCheng = new JTextField();	//公司名称
		diZhi = new JTextField();			//公司地址

		dianHua = new JTextField();			//公司电话
		dianHua.addKeyListener(new InputKeyListener());

		lianXiRen = new JTextField();		//联系人

		lianXiDianHua = new JTextField();	//联系电话
		lianXiDianHua.addKeyListener(new InputKeyListener());

		touDiDiZhi = new JTextField();		//投递地址
		yinHangZhangHao = new JTextField();	//银行帐号
		EMail = new JTextField();			//E-Mail
		beiZhu = new JTextArea();			//备注
		bz = new JScrollPane(beiZhu);
		//声明并构建终态按钮
		final JButton saveButton = new JButton("保存");	//保存按钮
		saveButton.addActionListener(new SaveButtonActionListener());	//添加监听器
		resetButton = new JButton("重置");				//重置按钮
		resetButton.addActionListener(new ChongZheButtonActionListener());	//添加监听器
		//定位 标签与文本框 位置
		//第一行
		setupZuJian(khDM, 0, 0, 1, 0, false);
		setupZuJian(keHuDaiMa, 1, 0, 3, 300, true);
		//第2行
		setupZuJian(gsName, 0, 1, 1, 0, false);
		setupZuJian(gongSiMingCheng, 1, 1, 1, 150, true);
		setupZuJian(gsSite, 2, 1, 1, 0, false);
		setupZuJian(diZhi, 3, 1, 1, 150, true);
		//第3行
		setupZuJian(phone, 0, 2, 1, 0, false);
		setupZuJian(dianHua, 1, 2, 1, 150, true);
		setupZuJian(yhZH, 2, 2, 1, 0, false);
		setupZuJian(yinHangZhangHao, 3, 2, 1, 150, true);
		//第4行
		setupZuJian(linkMan, 0, 3, 1, 0, false);
		setupZuJian(lianXiRen, 1, 3, 1, 150, true);
		setupZuJian(linkPhone, 2, 3, 1, 0, false);
		setupZuJian(lianXiDianHua, 3, 3, 1, 150, true);
		setupZuJian(touDDZ, 0, 4, 1, 0, false);
		setupZuJian(touDiDiZhi, 1, 4, 1, 150, true);
		setupZuJian(email, 2, 4, 1, 0, false);
		setupZuJian(EMail, 3, 4, 1, 150, true);
		//第6行
		setupZuJian(beiZ, 0, 5, 1, 0, false);
		setupZuJian(bz, 1, 5, 3, 300, true);
		//定位 按钮 位置
		setupZuJian(saveButton, 1, 9, 1, 0, false);
		setupZuJian(resetButton, 3, 9, 1, 0, false);

	}
	// 设置组件位置并添加到容器中
	private void setupZuJian(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		gridBagConstrains.insets = new Insets(3, 1, 2, 1);
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		if(component==bz){
			beiZhu.setLineWrap(true);
			gridBagConstrains.gridheight = 3;
			gridBagConstrains.ipadx = ipadx;
			gridBagConstrains.ipady = 50;
		}
		add(component, gridBagConstrains);
	}

	private final class SaveButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(final ActionEvent e) {
			// TODO 自动生成的方法存根
			if(gongSiMingCheng.getText().equals("")
					||diZhi.getText().equals("")
					||dianHua.getText().equals("")
					||lianXiRen.getText().equals("")
					||lianXiDianHua.getText().equals("")
					||touDiDiZhi.getText().equals("")
					||yinHangZhangHao.getText().equals("")){
				JOptionPane.showMessageDialog(null, "请填写全部信息");
				return;
			}
			ResultSet haveUser = DaoRu
					.query("select * from customerinfo where customername='"
							+ gongSiMingCheng.getText().trim() + "'");
			try {
				if (haveUser.next()){
					System.out.println("error");
					JOptionPane.showMessageDialog(KeHuAddPanel.this,
							"客户信息添加失败，存在同名客户", "客户添加信息",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			} catch (Exception er) {
				er.printStackTrace();
			}
			TbKhXinX khinfo = new TbKhXinX();
			khinfo.setId(keHuDaiMa.getText().trim());
			khinfo.setName(gongSiMingCheng.getText().trim());
			khinfo.setCompanySite(diZhi.getText().trim());
			khinfo.setCompanyPhone(dianHua.getText().trim());
			khinfo.setLinkman(lianXiRen.getText().trim());
			khinfo.setLinkPhone(lianXiDianHua.getText().trim());
			khinfo.setAddress(touDiDiZhi.getText().trim());
			khinfo.setBankAccout(yinHangZhangHao.getText().trim());
			khinfo.setEmail(EMail.getText().trim());
			khinfo.setRemark(beiZhu.getText().trim());
			DaoRu.addKeHu(khinfo);
			JOptionPane.showMessageDialog(KeHuAddPanel.this, "已成功添加客户",
					"客户添加信息", JOptionPane.INFORMATION_MESSAGE);
			resetButton.doClick();
		}
	}
	// 重置按钮的事件监听类
	private class ChongZheButtonActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			code = new Time().getTime();
			keHuDaiMa.setText(code);
			gongSiMingCheng.setText("");
			diZhi.setText("");
			dianHua.setText("");
			lianXiRen.setText("");
			lianXiDianHua.setText("");
			touDiDiZhi.setText("");
			yinHangZhangHao.setText("");
			EMail.setText("");
			beiZhu.setText("");
		}
	}
	
}
