package 系统显示.基础信息管理.客户信息管理;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;
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
import 数据库模型.TbGysinfo;
import 数据库模型.TbKhXinX;
import 系统显示.数据库信息.DaoRu;
import 系统显示.管理.Item;

public class KeHuAlterPanel extends JPanel {
	private JTextField keHuDaiMa; // 客户代码
	private JTextField gongSiMingCheng; // 公司名称
	private JTextField diZhi; // 公司地址
	private JTextField dianHua; // 公司电话
	private JTextField lianXiRen; // 联系人
	private JTextField lianXiDianHua; // 联系电话
	private JTextField touDiDiZhi; // 投递地址
	private JTextField yinHangZhangHao; // 银行账号
	private JTextField EMail; // E-Mail
	private JTextArea beiZhu; // 备注
	private JScrollPane bz; // 备注滚动条
	private JButton modifyButton;
	private JButton delButton;
	private JComboBox kehu;

	public KeHuAlterPanel() {
		// 界面方法
		setBounds(10, 10, 300, 350); // 设置位置以及大小
		setLayout(new GridBagLayout()); // 设置网格布局管理器
		setVisible(true); // 设置视图可见
		// 创建终态标签，并设置文本，不可修改
		final JLabel khDM = new JLabel("客户ID："); // 客户代码
		final JLabel gsName = new JLabel("公司名称："); // 公司名称
		final JLabel gsSite = new JLabel("公司地址："); // 公司地址
		final JLabel phone = new JLabel("公司电话："); // 公司电话
		final JLabel linkMan = new JLabel("联系人："); // 联系人
		final JLabel linkPhone = new JLabel("联系电话："); // 联系电话
		final JLabel touDDZ = new JLabel("投递地址："); // 投递地址
		final JLabel yhZH = new JLabel("银行账号："); // 银行账号
		final JLabel email = new JLabel("E-Mail："); // E-Mail
		final JLabel beiZ = new JLabel("备注："); // 备注
		final JLabel xzKH = new JLabel("选择客户"); // 选择客户
		// 构建文本框以及各种组件
		keHuDaiMa = new JTextField(); // 客户代码
		gongSiMingCheng = new JTextField(); // 公司名称
		diZhi = new JTextField(); // 公司地址
		dianHua = new JTextField(); // 公司电话
		dianHua.addKeyListener(new InputKeyListener());

		lianXiRen = new JTextField(); // 联系人
		lianXiDianHua = new JTextField(); // 联系电话
		lianXiDianHua.addKeyListener(new InputKeyListener());

		touDiDiZhi = new JTextField(); // 投递地址

		yinHangZhangHao = new JTextField(); // 银行帐号
		EMail = new JTextField(); // E-Mail
		beiZhu = new JTextArea(); // 备注
		bz = new JScrollPane(beiZhu);
		// 构建 按钮
		modifyButton = new JButton("修改");
		delButton = new JButton("删除");
		// 声明并构建 中间容器
		JPanel panel = new JPanel();
		// 把按钮添加到中间容器中
		panel.add(modifyButton);
		panel.add(delButton);
		// 处理删除按钮的单击事件
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) kehu.getSelectedItem();
				if (item == null || !(item instanceof Item))
					return;
				int confirm = JOptionPane.showConfirmDialog(
						KeHuAlterPanel.this, "确认删除客户信息吗？");
				if (confirm == JOptionPane.YES_OPTION) {
					int rs = DaoRu.delete("delete from customerinfo where customerid='"
							+ item.getId() + "'");
					if (rs > 0) {
						JOptionPane.showMessageDialog(KeHuAlterPanel.this,
								"客户：" + item.getName() + "。删除成功");
						kehu.removeItem(item);
					}
				}
			}
		});
		// 处理修改按钮的单击事件
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				if (DaoRu.updateKeHu(khinfo) == 1)
					JOptionPane.showMessageDialog(KeHuAlterPanel.this, "修改完成");
				else
					JOptionPane.showMessageDialog(KeHuAlterPanel.this, "修改失败");
			}
		});
		// 构建 下拉菜单
		kehu = new JComboBox();
		initComboBox();// 初始化下拉选择框

		// 处理客户信息的下拉选择框的选择事件
		kehu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doKeHuSelectAction();
			}
		});

		// 属性设置
		gongSiMingCheng.setEditable(false); // 设置文本不可编辑
		keHuDaiMa.setEditable(false); // 设置文本不可编辑
		kehu.setPreferredSize(new Dimension(230, 21));
		// 定位 标签与文本框 位置
		// 第一行
		setupZuJian(khDM, 0, 0, 1, 0, false);
		setupZuJian(keHuDaiMa, 1, 0, 3, 300, true);
		// 第二行
		// 第三行
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
		// 第12行
		setupZuJian(xzKH, 0, 9, 1, 0, false);
		setupZuJian(kehu, 1, 9, 2, 0, true);
		// 定位 中间容器（2个按钮） 位置
		setupZuJian(panel, 3, 9, 1, 0, false);
		// 定位客户信息的下拉选择框

	}

	// 设置组件位置并添加到容器中
	private void setupZuJian(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(3, 1, 2, 1);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		if (component == bz) {
			beiZhu.setLineWrap(true);
			gridBagConstrains.gridheight = 3;
			gridBagConstrains.ipadx = ipadx;
			gridBagConstrains.ipady = 50;

		}
		add(component, gridBagConstrains);
	}

	// 初始化客户下拉选择框
	public void initComboBox() {
		List khInfo = DaoRu.getKhInfos();
		List<Item> items = new ArrayList<Item>();
		kehu.removeAllItems();
		for (Iterator iter = khInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))
				continue;
			items.add(item);
			kehu.addItem(item);
		}
		doKeHuSelectAction();
	}

	private void doKeHuSelectAction() {
		Item selectedItem;
		if (!(kehu.getSelectedItem() instanceof Item)) {
			return;
		}
		selectedItem = (Item) kehu.getSelectedItem();
		TbKhXinX khInfo = DaoRu.getKhInfo(selectedItem);
		keHuDaiMa.setText(khInfo.getId());
		gongSiMingCheng.setText(khInfo.getName());
		diZhi.setText(khInfo.getCompanySite());
		dianHua.setText(khInfo.getCompanyPhone());
		lianXiRen.setText(khInfo.getLinkman());
		lianXiDianHua.setText(khInfo.getLinkPhone());
		touDiDiZhi.setText(khInfo.getAddress());
		yinHangZhangHao.setText(khInfo.getBankAccout());
		EMail.setText(khInfo.getEmail());
		beiZhu.setText(khInfo.getRemark());
	}


}
