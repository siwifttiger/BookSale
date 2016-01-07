package 系统显示.基础信息管理.商品信息管理;

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
import javax.swing.JTextField;

import 数据库模型.TbSpinfo;
import 系统显示.数据库信息.DaoRu;
import 系统显示.管理.Item;

public class ShangPinAddPanel extends JPanel{
	private JTextField beiZhu;//备注
	private JTextField piHao;  //书ISBN
	private JTextField chanDi;  //出版社
	private JTextField jianCheng; //作者
	private JTextField quanCheng;//书名
	private JButton resetButton;

	private JComboBox nianFen;//出版年份
	public ShangPinAddPanel() {
		setLayout(new GridBagLayout());
		setBounds(10, 10, 550, 400);
		setupComponent(new JLabel("图书ISBN："), 0, 0, 1, 1, false);
		piHao = new JTextField();
		setupComponent(piHao, 1, 0, 3, 1, true);
		setupComponent(new JLabel("图书名称："), 0, 1, 1, 1, false);
		quanCheng = new JTextField();
		setupComponent(quanCheng, 1, 1, 3, 10, true);
		setupComponent(new JLabel("作者："), 0, 2, 1, 1, false);
		jianCheng = new JTextField();
		setupComponent(jianCheng, 1, 2, 3, 300, true);
		setupComponent(new JLabel("出版社："), 0, 3, 1, 1, false);
		chanDi = new JTextField();
		setupComponent(chanDi, 1, 3, 3, 130, true);
		setupComponent(new JLabel("出版年份："), 0, 4, 1, 1, false);
		
		String[] arry = new String[70];
        for (int i = 1949; i < 2019; i++) {
            arry[i-1949] = i + "";
        }
		nianFen = new JComboBox(arry);;
		setupComponent(nianFen, 1, 4, 1, 50, true);
		
		
		setupComponent(new JLabel("备注："), 0, 5, 1, 1, false);
		beiZhu = new JTextField();
		setupComponent(beiZhu, 1, 5, 3, 1, true);
		final JButton tjButton = new JButton();
		tjButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (chanDi.getText().equals("")
						|| jianCheng.getText().equals("")
						|| piHao.getText().equals("")
						|| quanCheng.getText().equals("")) {
					JOptionPane.showMessageDialog(ShangPinAddPanel.this,
							"请完成未填写的信息。", "商品添加", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ResultSet haveUser = DaoRu
						.query("select * from bookinfo where isbn='"
								+ piHao.getText().trim() + "'");
				try {
					if (haveUser.next()) {
						System.out.println("error");
						JOptionPane.showMessageDialog(
								ShangPinAddPanel.this, "商品信息添加失败，存在相同ISBN号",
								"客户添加信息", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch (Exception er) {
					er.printStackTrace();
				}
				TbSpinfo spInfo = new TbSpinfo();
				
				spInfo.setCd(chanDi.getText().trim());
				spInfo.setJc(jianCheng.getText().trim());
				spInfo.setBz(beiZhu.getText().trim());
				spInfo.setPh(piHao.getText().trim());
				spInfo.setSpname(quanCheng.getText().trim());
				spInfo.setNf((nianFen.getSelectedItem()+"").trim());
				DaoRu.addSp(spInfo);
				JOptionPane.showMessageDialog(ShangPinAddPanel.this,
						"该书信息已经成功添加", "书的添加", JOptionPane.INFORMATION_MESSAGE);
				resetButton.doClick();
			}
		});
		tjButton.setText("添加");
		setupComponent(tjButton, 1, 8, 1, 1, false);
		/*
		final GridBagConstraints gridBagConstraints_20 = new GridBagConstraints();
		gridBagConstraints_20.weighty = 1.0;
		gridBagConstraints_20.insets = new Insets(0, 65, 0, 15);
		gridBagConstraints_20.gridy = 8;
		
		gridBagConstraints_20.gridx = 1;
		*/
		// 重添按钮的事件监听类
		resetButton = new JButton();
		setupComponent(tjButton, 3, 8, 1, 1, false);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				chanDi.setText("");
				jianCheng.setText("");
				beiZhu.setText("");
				piHao.setText("");
				quanCheng.setText("");
				nianFen.setSelectedIndex(0);
			}
		});
		resetButton.setText("重添");
	}
	// 设置组件位置并添加到容器中
	private void setupComponent(JComponent component, int gridx, int gridy,
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
		add(component, gridBagConstrains);
	}
}
