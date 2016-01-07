package 系统显示.查询统计;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import 数据库模型.TbDictionary;
import 数据库模型.TbGysinfo;
import 系统显示.数据库信息.DaoRu;
import 系统显示.管理.Item;

public class ShuJuZiDianChaXun extends JInternalFrame {
	private JTable table;
	private JTextField conditionContent;
	private JComboBox conditionOperation;
	private JComboBox conditionName;

	public ShuJuZiDianChaXun() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("字典查询");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 100, 620, 400);

		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		String[] tableHeads = new String[] { "编号", "数据类型代码", "数据类型名称", "字典值",
				"字典值名称", "录入操作员", "录入时间" };
		dftm.setColumnIdentifiers(tableHeads);

		final JScrollPane scrollPane = new JScrollPane(table);
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.weighty = 1.0;
		gridBagConstraints_1.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_1.insets = new Insets(0, 5, 0, 5);
		gridBagConstraints_1.fill = GridBagConstraints.BOTH;
		gridBagConstraints_1.gridwidth = 6;
		gridBagConstraints_1.gridy = 1;
		gridBagConstraints_1.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_1);

		setupComponet(new JLabel(" 选择查询条件："), 0, 0, 1, 1, false);
		conditionName = new JComboBox();
		conditionName.setModel(new DefaultComboBoxModel(new String[] { "客户分类",
				"客户性质", "客户分区", "单位", "开户银行" }));
		setupComponet(conditionName, 1, 0, 1, 30, true);

		conditionOperation = new JComboBox();
		conditionOperation.setModel(new DefaultComboBoxModel(new String[] {
				"等于", "包含" }));
		setupComponet(conditionOperation, 2, 0, 1, 30, true);

		conditionContent = new JTextField();
		setupComponet(conditionContent, 3, 0, 1, 140, true);

		final JButton queryButton = new JButton();
		setupComponet(queryButton, 4, 0, 1, 1, false);
		queryButton.setText("查询");
		queryButton.addActionListener(new queryAction(dftm));

		final JButton showAllButton = new JButton();
		setupComponet(showAllButton, 5, 0, 1, 1, false);
		showAllButton.setText("显示全部数据");
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				conditionContent.setText("");
				List list = DaoRu.getDictionary();
				updateTable(list, dftm);
			}
		});
	}

	private void updateTable(List list, final DefaultTableModel dftm) {
		int num = dftm.getRowCount();
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);
		Iterator iterator = list.iterator();
		TbDictionary dictionary;
		while (iterator.hasNext()) {
			List info = (List) iterator.next();
			Item item = new Item();
			item.setId((String) info.get(0));
			item.setName((String) info.get(2));
			dictionary = DaoRu.getDictionary_gx(item);
			Vector rowData = new Vector();
			rowData.add(dictionary.getId());
			rowData.add(dictionary.getTypeCode());
			rowData.add(dictionary.getTypeName());
			rowData.add(dictionary.getValueCode());
			rowData.add(dictionary.getValueName());
			rowData.add(dictionary.getLrczy());
			rowData.add(dictionary.getLrdate());
			dftm.addRow(rowData);
		}
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
		getContentPane().add(component, gridBagConstrains);
	}
	//添加监听器方法
	private final class queryAction implements ActionListener {
		private final DefaultTableModel dftm;

		private queryAction(DefaultTableModel dftm) {
			this.dftm = dftm;
		}

		public void actionPerformed(final ActionEvent e) {
			String conName, conOperation, content;
			List list = null;
			conName = conditionName.getSelectedItem().toString().trim();
			conOperation = conditionOperation.getSelectedItem().toString()
					.trim();
			content = conditionContent.getText().trim();
			String sql = "select * from tb_dictionary where ";
			if (conName.equals("客户分类")) {
				if (conOperation.equals("等于")) {
					list = DaoRu
							.findForList(sql + "valueName='" + content + "'");
				} else {
					list = DaoRu.findForList(sql + "valueName like '%" + content
							+ "%'");
				}
			} else if (conName.equals("客户性质")) {
				if (conOperation.equals("等于")) {
					list = DaoRu
							.findForList(sql + "valueName='" + content + "'");
				} else {
					list = DaoRu.findForList(sql + "valueName like '%" + content
							+ "%'");
				}
			} else if (conName.equals("客户分区")) {
				if (conOperation.equals("等于")) {
					list = DaoRu
							.findForList(sql + "valueName='" + content + "'");
				} else {
					list = DaoRu.findForList(sql + "valueName like '%" + content
							+ "%'");
				}
			} else if (conName.equals("单位")) {
				if (conOperation.equals("等于")) {
					list = DaoRu
							.findForList(sql + "valueName='" + content + "'");
				} else {
					list = DaoRu.findForList(sql + "valueName like '%" + content
							+ "%'");
				}
			} else if (conName.equals("开户银行")) {
				if (conOperation.equals("等于")) {
					list = DaoRu
							.findForList(sql + "valueName='" + content + "'");
				} else {
					list = DaoRu.findForList(sql + "valueName like '%" + content
							+ "%'");
				}
			}
			updateTable(list, dftm);
		}
	}
}
