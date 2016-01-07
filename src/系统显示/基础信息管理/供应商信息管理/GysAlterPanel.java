package 系统显示.基础信息管理.供应商信息管理;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import 数据库模型.TbGysinfo;
import 系统显示.数据库信息.DaoRu;
import 系统显示.管理.Item;
import 系统显示.管理.Time;

public class GysAlterPanel extends JPanel{
	private JTextField gongYingDaiMa;		//供应商id
	private JTextField EMailF;
	private JTextField yinHangF;
	private JTextField lianXiRenDianHuaF;
	private JTextField lianXiRenF;
	private JTextField dianHuaF;
	private JTextField diZhiF;
	private JTextField quanChengF;
	private JComboBox gys;

	private JTextArea beiZhu;			//备注
	private JScrollPane bz;				//备注滚动条
	public GysAlterPanel() {
		setLayout(new GridBagLayout());
		setBounds(10, 10, 510, 302);


		final JLabel khDM = new JLabel("供应商ID：");
		gongYingDaiMa = new JTextField();		//客户代码
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
		

		setupComponet(new JLabel("选择供应商"), 0, 9, 1, 0, false);
		gys = new JComboBox();
		gys.setPreferredSize(new Dimension(230, 21));
		initComboBox();// 初始化下拉选择框
		// 处理供应商信息的下拉选择框的选择事件
		gys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doGysSelectAction();
			}
		});
		// 定位供应商信息的下拉选择框
		setupComponet(gys, 1, 9, 2, 0, true);
		JButton modifyButton = new JButton("修改");
		JButton delButton = new JButton("删除");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// 定位按钮
		setupComponet(panel, 3, 9, 1, 0, false);
		// 处理删除按钮的单击事件
		delButton.addActionListener(new DelActionListener());
		// 处理修改按钮的单击事件
		modifyButton.addActionListener(new ModifyActionListener());
	}
	// 设置组件位置并添加到容器中
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
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
	// 初始化供应商下拉选择框
	public void initComboBox() {
		List gysInfo = DaoRu.getGysInfos();                          //调用getGysInfos()方法获取供应商列表
		List<Item> items = new ArrayList<Item>();                  //创建Item列表
		gys.removeAllItems();                                      //清除下拉列表框中原有的选项
		for (Iterator iter = gysInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();                                //封装供应商信息
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))                             //如果Items列表中包含该供应商的封装对象
				continue;                                         //跳出本次循环
			items.add(item);                                      //否则添加该对象到下拉列表框中
			gys.addItem(item);
		}
		doGysSelectAction();
	}
	// 处理供应商选择事件，用于根据选择的供应商名称，把供应商的其他信息填充到相应的文本框中。
	private void doGysSelectAction() {
		Item selectedItem;
		if (!(gys.getSelectedItem() instanceof Item)) {
			return;
		}
		selectedItem = (Item) gys.getSelectedItem();      //获取Item对象
		TbGysinfo gysInfo = DaoRu.getGysInfo(selectedItem); //通过Item对象调用getGysInfo()方法获取供应商信息
		quanChengF.setText(gysInfo.getName());            //填充供应商信息到文本框中
		diZhiF.setText(gysInfo.getAddress());
		dianHuaF.setText(gysInfo.getTel());
		lianXiRenF.setText(gysInfo.getLian());
		lianXiRenDianHuaF.setText(gysInfo.getLtel());
		EMailF.setText(gysInfo.getMail());
		yinHangF.setText(gysInfo.getYh());
		beiZhu.setText(gysInfo.getRemark());
		gongYingDaiMa.setText(gysInfo.getId());
	}
	//修改按钮的事件监听器
	class ModifyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			TbGysinfo gysInfo = new TbGysinfo();                  //创建供应商实体对象
			gysInfo.setId(gongYingDaiMa.getText().trim());                          //初始化供应商实体对象
			gysInfo.setAddress(diZhiF.getText().trim());
			gysInfo.setYh(yinHangF.getText().trim());
			gysInfo.setName(quanChengF.getText().trim());
			gysInfo.setLian(lianXiRenF.getText().trim());
			gysInfo.setLtel(lianXiRenDianHuaF.getText().trim());
			gysInfo.setMail(EMailF.getText().trim());
			gysInfo.setTel(dianHuaF.getText().trim());
			gysInfo.setRemark(beiZhu.getText().trim());
			if (DaoRu.updateGys(gysInfo) == 1)                              //更新供应商信息
				JOptionPane.showMessageDialog(GysAlterPanel.this, "修改完成");
			else
				JOptionPane.showMessageDialog(GysAlterPanel.this, "修改失败");
		}
	}
	//删除按钮的事件监听器
	class DelActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Item item = (Item) gys.getSelectedItem();               //获取当前选择的供应商
			if (item == null || !(item instanceof Item))
				return;
			int confirm = JOptionPane.showConfirmDialog(            //弹出确认删除对话框
					GysAlterPanel.this, "确认删除供应商信息吗？");
			if (confirm == JOptionPane.YES_OPTION) {               //如果确认删除
				int rs = DaoRu.delete("delete supplyinfo where supplyid='" //调用delete()方法
						+ item.getId() + "'");
				if (rs > 0) {
					JOptionPane.showMessageDialog(GysAlterPanel.this,   //显示删除成功对话框
							"供应商：" + item.getName() + "。删除成功");
					gys.removeItem(item);
				} else {
					JOptionPane.showMessageDialog(GysAlterPanel.this,
							"无法删除客户：" + item.getName() + "。");
				}
			}
		}
	}
}
