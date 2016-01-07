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

import 数据库模型.TbSpinfo;
import 系统显示.数据库信息.DaoRu;
import 系统显示.管理.Item;

public class ShangPinChaXun extends JInternalFrame {
	private JTable table;
	private JTextField conditionContent;
	private JComboBox conditionOperation;
	private JComboBox conditionName;
	
	public ShangPinChaXun() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("图书信息查询");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 100, 650, 400);
		// setResizable(false);//设置界面大小不可更改

		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		String[] tableHeads = new String[] {"isdn","书名","作者","出版社","出版年份","备注"};
		dftm.setColumnIdentifiers(tableHeads);

		final JScrollPane scrollPane = new JScrollPane(table);
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_6.insets = new Insets(0, 10, 0, 10);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 6;
		gridBagConstraints_6.gridy = 1;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);

		setupComponet(new JLabel(" 选择查询条件："), 0, 0, 1, 1, false);
		conditionName = new JComboBox();
		conditionName.setModel(new DefaultComboBoxModel(new String[] { "图书名称",
				"作者名称", "出版社", "isbn编号" }));
		setupComponet(conditionName, 1, 0, 1, 30, true);

		conditionOperation = new JComboBox();
		conditionOperation.setModel(new DefaultComboBoxModel(new String[] {
				"等于", "包含" }));
		setupComponet(conditionOperation, 2, 0, 1, 30, true);

		conditionContent = new JTextField();
		setupComponet(conditionContent, 3, 0, 1, 140, true);

		final JButton queryButton = new JButton();
		queryButton.addActionListener(new QueryAction(dftm));
		setupComponet(queryButton, 4, 0, 1, 1, false);
		queryButton.setText("查询");

		final JButton showAllButton = new JButton();
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				conditionContent.setText("");
				List list = DaoRu.getSpInfos();
				updateTable(list, dftm);
			}
		});
		setupComponet(showAllButton, 5, 0, 1, 1, false);
		showAllButton.setText("显示全部数据");
	}
	private void updateTable(List list, final DefaultTableModel dftm) {
		int num = dftm.getRowCount();
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);
		Iterator iterator = list.iterator();
		TbSpinfo spInfo;
		while (iterator.hasNext()) {
			List info = (List) iterator.next();
			Item item = new Item();
			item.setId((String) info.get(0));
			item.setName((String) info.get(1));
			spInfo = DaoRu.getSpInfo(item);
			Vector rowData = new Vector();
			rowData.add(spInfo.getPh().trim());
			rowData.add(spInfo.getSpname().trim());
			rowData.add(spInfo.getJc().trim());
			rowData.add(spInfo.getCd().trim());
			rowData.add(spInfo.getNf().trim());
			rowData.add(spInfo.getBz().trim());
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
	private final class QueryAction implements ActionListener {
		private final DefaultTableModel dftm;
		private QueryAction(DefaultTableModel dftm) {
			this.dftm = dftm;
		}
		public void actionPerformed(final ActionEvent e) {
			String conName, conOperation, content;
			conName = conditionName.getSelectedItem().toString().trim();
			conOperation = conditionOperation.getSelectedItem().toString();
			content = conditionContent.getText().trim();
			List list = null;
			list = searchInfo(conName, conOperation, content, list);
			updateTable(list, dftm);
		}
		private List searchInfo(String conName, String conOperation,
				String content, List list) {
			String sql = "select * from bookinfo where ";
			if (conOperation.equals("等于")) {
				if (conName.equals("图书名称"))
					list = DaoRu.findForList(sql + "name='" + content + "'");
				if (conName.equals("作者名称"))
					list = DaoRu.findForList(sql + "author='" + content + "'");
				if (conName.equals("出版社"))
					list = DaoRu.findForList(sql + "publish='" + content + "'");
				if (conName.equals("isbn编号"))
					list = DaoRu.findForList(sql + "isdn='" + content + "'");
			} else {
				if (conName.equals("图书名称"))
					list = DaoRu.findForList(sql + "name like '%" + content
							+ "%'");
				if (conName.equals("作者名称"))
					list = DaoRu.findForList(sql + "author like '%" + content
							+ "%'");
				if (conName.equals("出版社"))
					list = DaoRu.findForList(sql + "publish like '%" + content + "%'");
				if (conName.equals("isbn编号"))
					list = DaoRu.findForList(sql + "isdn like '%" + content + "%'");
			}
			return list;
		}
	}
}
