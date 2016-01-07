package ϵͳ��ʾ.��������;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import ���ݿ�ģ��.TbGysinfo;
import ���ݿ�ģ��.TbRukuDetail;
import ���ݿ�ģ��.TbRukuMain;
import ���ݿ�ģ��.TbSpinfo;
import ���ݿ�ģ��.TbUserlist;
import ��¼����.Denglu;
import ϵͳ��ʾ.���ݿ���Ϣ.DaoRu;
import ϵͳ��ʾ.����.Item;

public class JinHuoDan extends JInternalFrame {
	private final JTable table;
	private TbUserlist user = Denglu.getUser(); // ��¼�û���Ϣ
	private final JTextField jhsj = new JTextField(); // ����ʱ��
	private final JTextField lian = new JTextField(); // ��ϵ��
	private final JComboBox gys = new JComboBox(); // �鼮
	private final JTextField pzs = new JTextField("0"); // ����
	private final JTextField hpzs = new JTextField("0");// ��Ʒ����
	private final JTextField hjje = new JTextField("0");// �ϼƽ��
	private final JTextField czy = new JTextField(user.getName());// ����Ա
	private Date jhsjDate;
	private DateFormat jhsjDateFormat;

	public JinHuoDan() {
		super();
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setLayout(new GridBagLayout());
		setTitle("������");
		setBounds(100, 100, 800, 400);
		//setResizable(false);// ���ý����С���ɸ���


		setupComponet(new JLabel("������"), 0, 0, 1, 0, false);
		gys.setPreferredSize(new Dimension(60, 21));
		// ��Ӧ������ѡ����ѡ���¼�
		gys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doGysSelectAction();
			}
		});
		setupComponet(gys, 1, 0, 1, 1, true);

		

		setupComponet(new JLabel("����ʱ�䣺"), 0, 1, 1, 0, false);
		jhsj.setFocusable(false);
		setupComponet(jhsj, 1, 1, 1, 1, true);


		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		initTable();
		// ����¼����Ʒ����������Ʒ�������ϼƽ��ļ���
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setPreferredSize(new Dimension(380, 200));
		setupComponet(scrollPanel, 0, 2, 6, 1, true);

	

		setupComponet(new JLabel("������"), 2, 3, 1, 0, false);
		
		setupComponet(hpzs, 3, 3, 1, 50, true);



		// ������ⰴť���������Ϣ
		JButton rkButton = new JButton("���");
		setupComponet(rkButton, 4, 3, 1, 1, false);
		rkButton.addActionListener(new RkActionListener());
		// ��Ӵ������������ɳ�ʼ��
		addInternalFrameListener(new initTasks());
	}

	// ��ʼ�����
	private void initTable() {
		String[] columnNames = { "���","��λ����", "����", "����", "��ַ", "�绰","��ϵ��", "��ϵ�绰", "�ʼ���ַ", 
				"��ע" };
		((DefaultTableModel) table.getModel())
				.setColumnIdentifiers(columnNames);// ���ñ��ı�ͷ

		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		
		
		int num = dftm.getRowCount();
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);
	
	}

	// ��ʼ����Ʒ����ѡ���
	private void initSpBox() {
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		int num = dftm.getRowCount();
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);
		List gysInfos;
		String tempIsbn = ((Item) gys.getSelectedItem()).getId().toString().trim();
		gysInfos = DaoRu.getGysInfosByIsbn(tempIsbn);
		Iterator iterator = gysInfos.iterator();
		TbGysinfo gysInfo;
		while (iterator.hasNext()) {
			List info = (List) iterator.next();
			Item item = new Item();
			item.setId((String) info.get(0));
			item.setName("");
			gysInfo = DaoRu.getGysInfo(item);
			Vector rowData = new Vector();
			rowData.add(gysInfo.getId().trim());
			rowData.add(gysInfo.getName().trim());
			rowData.add((String) info.get(2));
			rowData.add((String) info.get(1));
			rowData.add(gysInfo.getAddress());
			rowData.add(gysInfo.getTel());
			rowData.add(gysInfo.getLian());
			rowData.add(gysInfo.getLtel());
			rowData.add(gysInfo.getMail());
			rowData.add(gysInfo.getRemark());
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

	// ��Ӧ��ѡ��ʱ������ϵ���ֶ�
	private void doGysSelectAction() {
		Item item = (Item) gys.getSelectedItem();
		TbGysinfo gysInfo = DaoRu.getGysInfo(item);
		lian.setText(gysInfo.getLian());
		initSpBox();
	}

	class RkActionListener implements ActionListener { // ��ⰴť���¼�������
		public void actionPerformed(ActionEvent e) {
			int row = table.getSelectedRow();
			
			if(row == -1 
			|| hpzs.getText().toString().trim().equals("")){
				JOptionPane.showMessageDialog(
						JinHuoDan.this, "����ʧ��",
						"����ʧ��", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			
			int quantity = Integer.parseInt(hpzs.getText()); // ��Ʒ����
			int price = Integer.parseInt(table.getValueAt(row, 3).toString().trim());
			String isbn = ((Item)gys.getSelectedItem()).getId();
			String id = table.getValueAt(row, 0).toString().trim();
			String rkDate = jhsjDateFormat.getDateTimeInstance().format(
					jhsjDate); // ���ʱ��
			
			TbRukuMain ruMain = new TbRukuMain("1", quantity+"", id, price+"",
					isbn, rkDate); // �����������ʵ�����
			boolean rs = DaoRu.insertRukuInfo(ruMain);
			JOptionPane.showMessageDialog(
					JinHuoDan.this, "�����ɹ�",
					"�����ɹ�", JOptionPane.INFORMATION_MESSAGE);
		}
	}


	// ���¼��м���Ʒ����������Ʒ�������ϼƽ��
	

	// ����ĳ�ʼ������
	private final class initTasks extends InternalFrameAdapter {
		public void internalFrameActivated(InternalFrameEvent e) {
			super.internalFrameActivated(e);
			initTimeField();
			initGysField();
			initSpBox();
		}

		private void initGysField() {// ��ʼ����Ʒ�ֶ�
			List gysInfos = DaoRu.getSpInfos();
			for (Iterator iter = gysInfos.iterator(); iter.hasNext();) {
				List list = (List) iter.next();
				Item item = new Item();
				item.setId(list.get(0).toString().trim());
				item.setName(list.get(1).toString().trim());
				gys.addItem(item);
			}
			doGysSelectAction();
		}

		private void initTimeField() {// ��������ʱ���߳�
			new Thread(new Runnable() {
				public void run() {
					try {
						while (true) {
							jhsjDate = new Date();
							jhsj.setText(jhsjDateFormat.getDateTimeInstance()
									.format(jhsjDate));
							Thread.sleep(100);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

	

	// ������Ʒ�������ѡ�񣬸��±��ǰ�е�����
	private synchronized void updateTable() {
	}

	
	// ֹͣ���Ԫ�ı༭
	private void stopTableCellEditing() {
		TableCellEditor cellEditor = table.getCellEditor();
		if (cellEditor != null)
			cellEditor.stopCellEditing();
	}
}
