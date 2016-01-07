package 系统显示.基础信息管理.商品信息管理;

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
import javax.swing.JTextField;

import 数据库模型.TbGysinfo;
import 数据库模型.TbSpinfo;
import 系统显示.数据库信息.DaoRu;
import 系统显示.管理.Item;

public class ShangPinAlterPanel extends JPanel {

	private JTextField beiZhu;//备注
	private JTextField piHao;  //书ISBN
	private JTextField chanDi;  //出版社
	private JTextField jianCheng; //作者
	private JTextField quanCheng;//书名
	private JComboBox nianFen;//出版年份
	
	private JButton modifyButton;
	private JButton delButton;
	private JComboBox sp;

	public ShangPinAlterPanel() {
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
		setupComponent(new JLabel("选择商品"), 0, 6, 1, 0, false);
		sp = new JComboBox();
		sp.setPreferredSize(new Dimension(230, 21));
		// 处理客户信息的下拉选择框的选择事件
		sp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSpSelectAction();
			}
		});
		// 定位商品信息的下拉选择框
		setupComponent(sp, 1, 6, 2, 0, true);
		modifyButton = new JButton("修改");
		delButton = new JButton("删除");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// 定位按钮
		setupComponent(panel, 3, 7, 1, 0, false);
		// 处理删除按钮的单击事件
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) sp.getSelectedItem();
				if (item == null || !(item instanceof Item))
					return;
				int confirm = JOptionPane.showConfirmDialog(
						ShangPinAlterPanel.this, "确认删除商品信息吗？");
				if (confirm == JOptionPane.YES_OPTION) {
					int rs = DaoRu.delete("delete from bookinfo where isbn='"
							+ item.getId() + "'");
					if (rs > 0) {
						JOptionPane.showMessageDialog(ShangPinAlterPanel.this,
								"商品：" + item.getName() + "。删除成功");
						sp.removeItem(item);
					}
				}
			}
		});
		// 处理修改按钮的单击事件
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) sp.getSelectedItem();
				TbSpinfo spInfo = new TbSpinfo();
				spInfo.setCd(chanDi.getText().trim());
				spInfo.setJc(jianCheng.getText().trim());
				spInfo.setBz(beiZhu.getText().trim());
				spInfo.setPh(piHao.getText().trim());
				spInfo.setSpname(quanCheng.getText().trim());
				spInfo.setNf((nianFen.getSelectedItem()+"").trim());
				if (DaoRu.updateSp(spInfo) == 1)
					JOptionPane.showMessageDialog(ShangPinAlterPanel.this,
							"修改完成");
				else
					JOptionPane.showMessageDialog(ShangPinAlterPanel.this,
							"修改失败");
			}
		});
	}

	// 设置组件位置并添加到容器中
	private void setupComponent(JComponent component, int gridx, int gridy,
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
		add(component, gridBagConstrains);
	}

	// 初始化商品下拉选择框
	public void initComboBox() {
		List khInfo = DaoRu.getSpInfos();
		List<Item> items = new ArrayList<Item>(); //其实作用是检测有无重复
		sp.removeAllItems();
		for (Iterator iter = khInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))//其实作用是检测有无重复
				continue;
			items.add(item);
			sp.addItem(item);
		}
		doSpSelectAction();
	}

	// 处理商品选择事件
	private void doSpSelectAction() {
		Item selectedItem;
		if (!(sp.getSelectedItem() instanceof Item)) {
			return;
		}
		selectedItem = (Item) sp.getSelectedItem();
		TbSpinfo spInfo = DaoRu.getSpInfo(selectedItem);
		if (!spInfo.getPh().isEmpty()) {
			quanCheng.setText(spInfo.getSpname());
			chanDi.setText(spInfo.getCd());
			jianCheng.setText(spInfo.getJc());
			beiZhu.setText(spInfo.getBz());
			piHao.setText(spInfo.getPh());
			nianFen.setSelectedIndex(Integer.parseInt(spInfo.getNf() ) - 1949);
		}
	}
}
