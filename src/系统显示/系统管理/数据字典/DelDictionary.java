package 系统显示.系统管理.数据字典;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import 系统显示.数据库信息.DaoRu;
import 系统显示.系统管理.CzyGL;
import 系统显示.系统管理.SjZd;
import 系统显示.系统管理.操作员管理.ShanChuCaoZuoYuan;

public class DelDictionary extends JPanel {
	private JTable table;
	private JTextField jt_lrczy;
	private JTextField jt_sjxC;
	private JTextField jt_lrdate;
	private JTextField jt_sjx;
	private DefaultTableModel dftm;
	private String[] zdName;

	public DelDictionary() {
		super();
		setBounds(0, 0, 400, 287);
		setLayout(new GridBagLayout());

		final JScrollPane scrollPane = new JScrollPane();
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new Insets(0, 0, 20, 0);
		gridBagConstraints.gridwidth = 12;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.ipadx = 35;
		gridBagConstraints.ipady = -195;
		add(scrollPane, gridBagConstraints);

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		dftm = (DefaultTableModel) table.getModel();
		zdName = new String[] { "数据名称", "字典值", "数据代码", "录入操作员", "录入时间" };
		dftm.setColumnIdentifiers(zdName);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				String sjx, sjxc, lrczy, lrdate;
				int selRow = table.getSelectedRow();
				sjx = table.getValueAt(selRow, 1).toString().trim();
				sjxc = table.getValueAt(selRow, 2).toString().trim();
				lrczy = table.getValueAt(selRow, 3).toString().trim();
				lrdate = table.getValueAt(selRow, 4).toString().trim();
				jt_sjx.setText(sjx);
				jt_sjxC.setText(sjxc);
				jt_lrczy.setText(lrczy);
				jt_lrdate.setText(lrdate);
			}
		});
		scrollPane.setViewportView(table);

		final JLabel label_1 = new JLabel();
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.gridy = 2;
		gridBagConstraints_1.gridx = 0;
		add(label_1, gridBagConstraints_1);
		label_1.setText("字典值：");

		jt_sjx = new JTextField();
		jt_sjx.setEditable(false);
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_2.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_2.weightx = 1.0;
		gridBagConstraints_2.gridy = 2;
		gridBagConstraints_2.gridx = 1;
		add(jt_sjx, gridBagConstraints_2);

		final JLabel label_2 = new JLabel();
		label_2.setText("数据值：");
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.gridy = 2;
		gridBagConstraints_3.gridx = 2;
		add(label_2, gridBagConstraints_3);

		jt_sjxC = new JTextField();
		jt_sjxC.setEditable(false);
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.weightx = 1.0;
		gridBagConstraints_4.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_4.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_4.gridy = 2;
		gridBagConstraints_4.gridx = 3;
		add(jt_sjxC, gridBagConstraints_4);

		final JLabel label_3 = new JLabel();
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.gridy = 2;
		gridBagConstraints_5.gridx = 4;
		add(label_3, gridBagConstraints_5);
		label_3.setText("录入操作员：");

		jt_lrczy = new JTextField();
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_6.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_6.weightx = 1.0;
		gridBagConstraints_6.gridy = 2;
		gridBagConstraints_6.gridx = 5;
		add(jt_lrczy, gridBagConstraints_6);
		jt_lrczy.setEditable(false);

		final JLabel label_4 = new JLabel();
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.gridy = 2;
		gridBagConstraints_7.gridx = 6;
		add(label_4, gridBagConstraints_7);
		label_4.setText("录入时间：");

		jt_lrdate = new JTextField();
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_8.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_8.weightx = 1.0;
		gridBagConstraints_8.gridy = 2;
		gridBagConstraints_8.gridx = 7;
		add(jt_lrdate, gridBagConstraints_8);
		jt_lrdate.setEditable(false);

		final JButton button = new JButton("删除");
		final GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
		gridBagConstraints_9.insets = new Insets(5, 0, 5, 0);
		gridBagConstraints_9.gridy = 3;
		gridBagConstraints_9.gridx = 2;
		gridBagConstraints_9.gridwidth=2;
		add(button, gridBagConstraints_9);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(DelDictionary.this,
						"确认要删除该数据值？");
				if (op == JOptionPane.OK_OPTION) {
					DaoRu.delete("delete tb_dictionary where valueName='"
							+ jt_sjx.getText() + "'");
					jt_sjx.setText("");
					jt_sjxC.setText("");
					jt_lrczy.setText("");
					jt_lrdate.setText("");
					initTable();
				}
			}
		});

		final JButton button_1 = new JButton("关闭");
		final GridBagConstraints gridBagConstraints_10 = new GridBagConstraints();
		gridBagConstraints_10.insets = new Insets(5, 0, 5, 0);
		gridBagConstraints_10.gridy = 3;
		gridBagConstraints_10.gridx = 5;
		gridBagConstraints_10.gridwidth=2;
		add(button_1, gridBagConstraints_10);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				SjZd parent = (SjZd) DelDictionary.this.getRootPane()
						.getParent();
				parent.doDefaultCloseAction();
			}
		});
	}

	public void initTable() {
		List ul = DaoRu.getDictionary();
		Iterator it = ul.iterator();
		String[] data = new String[5];
		dftm.setDataVector(null, zdName);
		while (it.hasNext()) {
			List userlist = (List) it.next();
			data[0] = (String) userlist.get(2);
			data[1] = (String) userlist.get(4);
			data[2] = (String) userlist.get(3);
			data[3] = (String) userlist.get(5);
			data[4] = (String) userlist.get(6);
			dftm.addRow(data);
		}
		setVisible(true);
	}
}
