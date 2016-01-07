package ϵͳ��ʾ.��ѯͳ��;

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

import ���ݿ�ģ��.TbKhXinX;
import ϵͳ��ʾ.���ݿ���Ϣ.DaoRu;
import ϵͳ��ʾ.����.Item;

public class KeHuChaXun extends JInternalFrame {
	private JTable table;
	private JTextField conditionContent;
	private JComboBox conditionBox2;
	private JComboBox conditionBox1;
	private JButton showAllButton;

	public KeHuChaXun() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("�ͻ���Ϣ��ѯ");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 100, 650, 400);
		// setResizable(false);//���ý����С���ɸ���

		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//��ͷ
		String[] tableHeads = new String[] { "�ͻ�����", "��˾����", "��˾��ַ", 
				  "��˾�绰", "�����˺�", "��  ϵ  ��", "��ϵ�绰", "Ͷ�ݵ�ַ","E-Mail",
				    "��ע" };
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

		setupComponet(new JLabel(" ѡ���ѯ������"), 0, 0, 1, 1, false);
		conditionBox1 = new JComboBox();
		conditionBox1.setModel(new DefaultComboBoxModel(new String[] { "��˾����",
				"��ϵ��", "��ϵ�绰","��˾��ַ", "Ͷ�ݵ�ַ", "E-mail"  }));
		setupComponet(conditionBox1, 1, 0, 1, 30, true);

		conditionBox2 = new JComboBox();
		conditionBox2.setModel(new DefaultComboBoxModel(new String[] { "����",
				"����" }));
		setupComponet(conditionBox2, 2, 0, 1, 30, true);

		conditionContent = new JTextField();
		setupComponet(conditionContent, 3, 0, 1, 140, true);

		final JButton queryButton = new JButton();
		queryButton.addActionListener(new queryAction(dftm));
		queryButton.setText("��ѯ");
		setupComponet(queryButton, 4, 0, 1, 1, false);

		showAllButton = new JButton();
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				conditionContent.setText("");
				List list = DaoRu.getKhInfos();
				updateTable(list, dftm);
			}
		});
		setupComponet(showAllButton, 5, 0, 1, 1, false);
		showAllButton.setText("��ʾȫ������");
	}

	private void updateTable(List list, final DefaultTableModel dftm) {
		int num = dftm.getRowCount();
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);
		Iterator iterator = list.iterator();
		TbKhXinX khInfo;
		while (iterator.hasNext()) {
			List info = (List) iterator.next();
			Item item = new Item();
			item.setId((String) info.get(0));
			item.setName((String) info.get(1));
			khInfo = DaoRu.getKhInfo(item);
			Vector rowData = new Vector();
			rowData.add(khInfo.getId().trim());
			rowData.add(khInfo.getName().trim());
			rowData.add(khInfo.getCompanySite().trim());
			rowData.add(khInfo.getCompanyPhone().trim());
			rowData.add(khInfo.getBankAccout().trim());
			rowData.add(khInfo.getLinkman().trim());
			rowData.add(khInfo.getLinkPhone().trim());
			
			rowData.add(khInfo.getAddress().trim());
			rowData.add(khInfo.getEmail().trim());
			rowData.add(khInfo.getRemark().trim());
			dftm.addRow(rowData);
		}
	}

	// �������λ�ò���ӵ�������
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

	private final class queryAction implements ActionListener {
		private final DefaultTableModel dftm;

		private queryAction(DefaultTableModel dftm) {
			this.dftm = dftm;
		}

		public void actionPerformed(final ActionEvent e) {
			String condition, conditionOperation, conditionString;
			List list = null;
			condition = conditionBox1.getSelectedItem().toString().trim();
			conditionOperation = conditionBox2.getSelectedItem().toString()
					.trim();
			conditionString = conditionContent.getText().trim();
			if (!conditionString.isEmpty()) {
				String sql = "select * from customerinfo where ";
				if (condition.equals("��˾����")) {
					if (conditionOperation.equals("����")) {
						list = DaoRu.findForList(sql + "name='"
								+ conditionString + "'");
					} else {
						list = DaoRu.findForList(sql + "name like '%"
								+ conditionString + "%'");
					}
				} else if (condition.equals("��ϵ��")) {
					if (conditionOperation.equals("����")) {
						list = DaoRu.findForList(sql + "contact='"
								+ conditionString + "'");
					} else {
						list = DaoRu.findForList(sql + "contact like '%"
								+ conditionString + "%'");
					}
				}else if (condition.equals("��ϵ�绰")) {
					if (conditionOperation.equals("����")) {
						list = DaoRu.findForList(sql + "contacttel='"
								+ conditionString + "'");
					} else {
						list = DaoRu.findForList(sql + "contacttel like '%"
								+ conditionString + "%'");
					}
				}else if (condition.equals("��˾��ַ")) {
					if (conditionOperation.equals("����")) {
						list = DaoRu.findForList(sql + "address='"
								+ conditionString + "'");
					} else {
						list = DaoRu.findForList(sql + "address like '%"
								+ conditionString + "%'");
					}
				}else if (condition.equals("Ͷ�ݵ�ַ")) {
					if (conditionOperation.equals("����")) {
						list = DaoRu.findForList(sql + " deliveryaddress='"
								+ conditionString + "'");
					} else {
						list = DaoRu.findForList(sql + " deliveryaddress like '%"
								+ conditionString + "%'");
					}
				}else if (condition.equals("E-mail")) {
					if (conditionOperation.equals("����")) {
						list = DaoRu.findForList(sql + "email"
								+ conditionString + "'");
					} else {
						list = DaoRu.findForList(sql + "email like '%"
								+ conditionString + "%'");
					}
				}
				updateTable(list, dftm);
			} else
				showAllButton.doClick();
		}
	}
}
