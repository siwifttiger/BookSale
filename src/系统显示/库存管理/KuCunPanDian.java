package 系统显示.库存管理;

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

import 数据库模型.TbKhXinX;
import 系统显示.数据库信息.DaoRu;
import 系统显示.管理.Item;

public class KuCunPanDian extends JInternalFrame {
	private JTable table;
	private JTextField conditionContent;
	private JComboBox conditionBox2;
	private JComboBox conditionBox1;
	private JButton showAllButton;
	private DefaultTableModel dftm;
	private String sql = new String (" select isbn,bookname,supplyname,supplyprice,neededquantity,totalquantity from inventory natural join bookinfo natural join supplylist"
			+" natural join supplyinfo ");
	
	public KuCunPanDian() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("库存信息盘点");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 100, 650, 400);
		// setResizable(false);//设置界面大小不可更改

		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		dftm = (DefaultTableModel) table.getModel();
		setupComponet(new JLabel(" 选择查询条件："), 0, 0, 1, 1, false);
		conditionBox1 = new JComboBox();
		conditionBox1.setModel(new DefaultComboBoxModel(new String[] { "图书编号",
				"图书名称", "供应商名称"  }));
		setupComponet(conditionBox1, 1, 0, 1, 30, true);
		
		conditionBox2 = new JComboBox();
		conditionBox2.setModel(new DefaultComboBoxModel(new String[] { "等于",
				"包含" }));
		setupComponet(conditionBox2, 2, 0, 1, 30, true);
		
		//表头
		String[] tableHeads = new String[] { "图书编号", "图书名称", "供应商名称",  "进货单价",
				"进货数量",  "盘点数量" };
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		dftm.setColumnIdentifiers(tableHeads);

		final JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setAutoscrolls(true);
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.insets = new Insets(0, 10, 5, 10);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 6;
		gridBagConstraints_6.gridy = 1;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);



		conditionContent = new JTextField();
		setupComponet(conditionContent, 3, 0, 1, 140, true);

		final JButton queryButton = new JButton();
		
		queryButton.setText("盘点");
		queryButton.addActionListener(new QueryActionListerner());
		setupComponet(queryButton, 4, 0, 1, 1, false);

		showAllButton = new JButton();
		
		class ShowAllActionListener implements ActionListener { // 显示全部按钮的动作监听器
			public void actionPerformed(final ActionEvent e) {
				List list = DaoRu.findForList(sql);
				Iterator iterator = list.iterator();
				updateTable(iterator);
			}
		}
		showAllButton.addActionListener(new ShowAllActionListener());
		setupComponet(showAllButton, 5, 0, 1, 1, false);
		showAllButton.setText("显示全部数据");
	}
	
	//盘点按钮的动作监听器
		class QueryActionListerner implements ActionListener{

		@Override
			public void actionPerformed(ActionEvent e) {
				List list = null;
				String con;
				String opstr;
				switch(conditionBox1.getSelectedIndex()){
				case 0:
					con = "isbn";
					break;
				case 1:
					con = "bookname";
					break;
				case 2:
					con = "supplyname";
					break;
					default :
						con = "isbn";
						break;
				}
				int oper = conditionBox2.getSelectedIndex();
				opstr = oper== 0? " = " : " like ";
				String cont = conditionContent.getText();
				System.out.println(sql + " where " + con + opstr + (oper == 0? "'" + cont + "'" : "'%" + cont +"'%"));
				list = DaoRu.findForList(sql + " where " + con + opstr + (oper == 0? "'" + cont + "'" : "'%" + cont +"'%"));
				Iterator iterator = list.iterator();
				updateTable(iterator);
			}
			
		}

	private void updateTable(Iterator iterator) { // 更新表格数据
		int rowCount = dftm.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			dftm.removeRow(0);
		}
		while (iterator.hasNext()) {
			Vector vector = new Vector();
			List view = (List) iterator.next();
			vector.addAll(view);
			dftm.addRow(vector);
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


}
