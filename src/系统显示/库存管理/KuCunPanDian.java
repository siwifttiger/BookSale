package ϵͳ��ʾ.������;

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
		setTitle("�����Ϣ�̵�");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 100, 650, 400);
		// setResizable(false);//���ý����С���ɸ���

		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		dftm = (DefaultTableModel) table.getModel();
		setupComponet(new JLabel(" ѡ���ѯ������"), 0, 0, 1, 1, false);
		conditionBox1 = new JComboBox();
		conditionBox1.setModel(new DefaultComboBoxModel(new String[] { "ͼ����",
				"ͼ������", "��Ӧ������"  }));
		setupComponet(conditionBox1, 1, 0, 1, 30, true);
		
		conditionBox2 = new JComboBox();
		conditionBox2.setModel(new DefaultComboBoxModel(new String[] { "����",
				"����" }));
		setupComponet(conditionBox2, 2, 0, 1, 30, true);
		
		//��ͷ
		String[] tableHeads = new String[] { "ͼ����", "ͼ������", "��Ӧ������",  "��������",
				"��������",  "�̵�����" };
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
		
		queryButton.setText("�̵�");
		queryButton.addActionListener(new QueryActionListerner());
		setupComponet(queryButton, 4, 0, 1, 1, false);

		showAllButton = new JButton();
		
		class ShowAllActionListener implements ActionListener { // ��ʾȫ����ť�Ķ���������
			public void actionPerformed(final ActionEvent e) {
				List list = DaoRu.findForList(sql);
				Iterator iterator = list.iterator();
				updateTable(iterator);
			}
		}
		showAllButton.addActionListener(new ShowAllActionListener());
		setupComponet(showAllButton, 5, 0, 1, 1, false);
		showAllButton.setText("��ʾȫ������");
	}
	
	//�̵㰴ť�Ķ���������
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

	private void updateTable(Iterator iterator) { // ���±������
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


}
