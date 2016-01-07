package 系统显示.查询统计;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
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
import javax.swing.table.DefaultTableModel;

import 数据库模型.TbUserlist;
import 系统显示.数据库信息.DaoRu;
public class XiaoShouPaiHang extends JInternalFrame {
	private JButton okButton;
	private JComboBox month;
	private JComboBox year;
	private JTable table;
	private JComboBox operation;
	private JComboBox condition;
	private TbUserlist user;
	private DefaultTableModel dftm;
	private Calendar date = Calendar.getInstance();
	public XiaoShouPaiHang() {
		setIconifiable(true);
		setClosable(true);
		setTitle("销售排行");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 100, 690, 400);
		//setResizable(false);//设置界面大小不可更改

		final JLabel label_1 = new JLabel();
		label_1.setText("对");
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.anchor = GridBagConstraints.EAST;
		gridBagConstraints_8.gridy = 0;
		gridBagConstraints_8.gridx = 0;
		getContentPane().add(label_1, gridBagConstraints_8);

		year = new JComboBox();
		for (int i = 1981, j = 0; i <= date.get(Calendar.YEAR) + 1; i++, j++) {
			year.addItem(i);
			if (i == date.get(Calendar.YEAR))
				year.setSelectedIndex(j);
		}
		year.setPreferredSize(new Dimension(100, 21));
		setupComponet(year, 1, 0, 1, 90, true);

		setupComponet(new JLabel("年"), 2, 0, 1, 1, false);

		month = new JComboBox();
		for (int i = 1; i <= 12; i++) {
			month.addItem(String.format("%02d", i));
			if (date.get(Calendar.MONTH) == i)
				month.setSelectedIndex(i - 1);
		}
		month.setPreferredSize(new Dimension(100, 21));
		setupComponet(month, 3, 0, 1, 30, true);

		setupComponet(new JLabel(" 月份的销售信息，按"), 4, 0, 1, 1, false);
		condition = new JComboBox();
		condition.setModel(new DefaultComboBoxModel(new String[]{"金额", "数量"}));
		setupComponet(condition, 5, 0, 1, 30, true);

		setupComponet(new JLabel(" 进行"), 6, 0, 1, 1, false);

		operation = new JComboBox();
		operation.setModel(new DefaultComboBoxModel(
				new String[]{"升序排列", "降序排列"}));
		setupComponet(operation, 7, 0, 1, 30, true);

		okButton = new JButton();
		okButton.addActionListener(new OkAction());
		setupComponet(okButton, 8, 0, 1, 1, false);
		okButton.setText("确定");

		final JScrollPane scrollPane = new JScrollPane();
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_6.insets = new Insets(0, 10, 5, 10);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 9;
		gridBagConstraints_6.gridy = 1;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);

		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		dftm = (DefaultTableModel) table.getModel();
		String[] tableHeads = new String[]{"图书编号", "图书名称", "销售金额", "销售数量"};
		dftm.setColumnIdentifiers(tableHeads);
		scrollPane.setViewportView(table);
	}

	private void updateTable(Iterator iterator) {
		int rowCount = dftm.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			dftm.removeRow(0);
		}
		while (iterator.hasNext()) {
			Vector vector = new Vector();
			List view = (List) iterator.next();
			Vector row=new Vector(view);
			int rowSize = row.size();
			for(int i=rowSize-2;i<rowSize;i++){
				Object colValue = row.get(i);
				row.remove(i);
				row.insertElementAt(colValue, 2);
			}
			vector.addAll(row);
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
	private final class OkAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			List list = null;
			String strMonth = (String) month.getSelectedItem();
			String date = year.getSelectedItem() + "-"+strMonth;
			//System.out.println(date);
			String con = condition.getSelectedIndex() == 0 ? "money " : "total ";
			int oper = operation.getSelectedIndex();
			String opstr = oper == 0 ? " asc" : " desc";
			String sql1 = " select isbn,bookname,sum(salequantity) as total,sum(salequantity*saleprice) as money from salelist natural join bookinfo where substring(date,1,7) ='"
					 + date + "' group by isbn order by " + con + opstr ;
			//System.out.println(sql1);
			list = DaoRu.findForList(sql1);
			Iterator iterator = list.iterator();
			updateTable(iterator);
		}
	}
}