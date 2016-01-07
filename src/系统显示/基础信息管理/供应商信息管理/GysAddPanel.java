package 系统显示.基础信息管理.供应商信息管理;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import 数据库模型.TbGysinfo;
import 系统显示.数据库信息.DaoRu;
import 系统显示.管理.Time;

public class GysAddPanel extends JPanel{
	private JTextField gongYingDaiMa;		//供应商id
	private JTextField EMailF;    //邮箱
	private JTextField yinHangF;  //银行账号
	private JTextField lianXiRenDianHuaF;   //联系人电话
	private JTextField lianXiRenF;  //联系人
	private JTextField dianHuaF;  //公司电话
	private JTextField diZhiF; //公司地址
	private JTextField quanChengF;   //公司名称
	private JTextArea beiZhu;			//备注
	
	private JButton resetButton;
	private String code = null;			//供应商代码设置

	
	private JScrollPane bz;				//备注滚动条
	
	public GysAddPanel() {
		setLayout(new GridBagLayout());
		setBounds(10, 10, 510, 302);
		
		final JLabel khDM = new JLabel("供应商ID：");
		gongYingDaiMa = new JTextField();		//客户代码
		code = new Time().getTime();
		gongYingDaiMa.setText(code);
		gongYingDaiMa.setEditable(false);
		setupComponet(khDM, 0, 0, 1, 0, false);
		setupComponet(gongYingDaiMa, 1, 0, 3, 300, true);
		
		setupComponet(new JLabel("供应商名称："), 0, 1, 1, 0, false);

		quanChengF = new JTextField();
		setupComponet(quanChengF, 1, 1, 1, 160, true);
		
		setupComponet(new JLabel("地址："), 2, 1, 1, 1, false);
		diZhiF = new JTextField();
		setupComponet(diZhiF, 3, 1, 3, 160, true);

		setupComponet(new JLabel("电话："), 0, 2, 1, 1, false);
		dianHuaF = new JTextField();
		setupComponet(dianHuaF, 1, 2, 1, 160, true);

		setupComponet(new JLabel("银行账号："), 2, 2, 1, 1, false);
		yinHangF = new JTextField();
		setupComponet(yinHangF, 3, 2, 1, 160, true);
		
		setupComponet(new JLabel("联系人："), 0, 3, 1, 1, false);
		lianXiRenF = new JTextField();
		setupComponet(lianXiRenF, 1, 3, 1, 160, true);

		setupComponet(new JLabel("联系人电话："), 2, 3, 1, 1, false);
		lianXiRenDianHuaF = new JTextField();
		setupComponet(lianXiRenDianHuaF, 3, 3, 1, 160, true);

		setupComponet(new JLabel("邮箱："), 0, 4, 1, 1, false);
		EMailF = new JTextField();
		setupComponet(EMailF, 1, 4, 1, 160, true);

		final JLabel beiZ = new JLabel("备注：");	
		beiZhu = new JTextArea();			//备注
		bz = new JScrollPane(beiZhu);

		setupComponet(beiZ, 0, 5, 1, 0, false);
		setupComponet(bz, 1, 5, 3, 300, true);
		
		final JButton tjButton = new JButton();
		tjButton.setText("添加");
		tjButton.addActionListener(new TjActionListener());
		setupComponet(tjButton, 2, 9, 1, 0, false);

		resetButton = new JButton();
		setupComponet(resetButton, 3, 9, 1, 0, false);
		resetButton.addActionListener(new ResetActionListener());
		resetButton.setText("重填");
	}
	// 设置组件位置并添加到容器中
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
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
	
	class ResetActionListener implements ActionListener {// 重填按钮的事件监听类
		public void actionPerformed(final ActionEvent e) {
			diZhiF.setText("");
			lianXiRenF.setText("");
			lianXiRenDianHuaF.setText("");
			EMailF.setText("");
			quanChengF.setText("");
			dianHuaF.setText("");
			yinHangF.setText("");
			beiZhu.setText("");
			code = new Time().getTime();
			gongYingDaiMa.setText(code);
		}
	}
	class TjActionListener implements ActionListener {// 添加按钮的事件监听类
		public void actionPerformed(final ActionEvent e) {
			if (diZhiF.getText().equals("") || quanChengF.getText().equals("") //验证用户输入
					|| yinHangF.getText().equals("")
					|| diZhiF.getText().equals("")
					|| lianXiRenF.getText().equals("")
					|| lianXiRenDianHuaF.getText().equals("")
					|| EMailF.getText().equals("")
					|| dianHuaF.getText().equals("")) {
				JOptionPane.showMessageDialog(GysAddPanel.this, "请填写全部信息");
				return;
			}
			try {                                                            //验证是否存在同名供应商
				ResultSet haveUser = DaoRu
						.query("select * from supplyinfo where supplyname='"
								+ quanChengF.getText().trim() + "'");
				if (haveUser.next()) {
					JOptionPane.showMessageDialog(GysAddPanel.this,
							"供应商信息添加失败，存在同名供应商", "供应商添加信息",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				TbGysinfo gysInfo = new TbGysinfo();                            //创建供应商实体对象
				gysInfo.setId(gongYingDaiMa.getText().trim());                                              //初始化供应商对象
				gysInfo.setAddress(diZhiF.getText().trim());
				gysInfo.setYh(yinHangF.getText().trim());
				gysInfo.setName(quanChengF.getText().trim());
				gysInfo.setLian(lianXiRenF.getText().trim());
				gysInfo.setLtel(lianXiRenDianHuaF.getText().trim());
				gysInfo.setMail(EMailF.getText().trim());
				gysInfo.setTel(dianHuaF.getText().trim());
				gysInfo.setRemark(beiZhu.getText().trim());
				DaoRu.addGys(gysInfo);                                           //调用addGys()方法存储供应商
				JOptionPane.showMessageDialog(GysAddPanel.this, "已成功添加供应商",
						"供应商添加信息", JOptionPane.INFORMATION_MESSAGE);
				resetButton.doClick();                                         //触发"充填"按钮的单击动作
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
