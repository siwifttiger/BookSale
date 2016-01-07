package ϵͳ��ʾ.���۹���;

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
import ���ݿ�ģ��.TbKucun;
import ���ݿ�ģ��.TbRkthDetail;
import ���ݿ�ģ��.TbRkthMain;
import ���ݿ�ģ��.TbSpinfo;
import ���ݿ�ģ��.TbUserlist;
import ��¼����.Denglu;
import ϵͳ��ʾ.������Ϣ����.��Ʒ��Ϣ����.ShangPinAddPanel;
import ϵͳ��ʾ.���ݿ���Ϣ.DaoRu;
import ϵͳ��ʾ.����.Item;

public class BaoJia extends JInternalFrame {
	private final JTable table;
	private TbUserlist user = Denglu.getUser(); // ��¼�û���Ϣ
	private final JTextField jhsj = new JTextField(); // �˻�ʱ��
	private final JTextField lian = new JTextField(); // ��ϵ��
	private final JComboBox gys = new JComboBox(); // ��Ӧ��
	private final JTextField shuming = new JTextField(); // ��������
	private final JTextField pzs = new JTextField("0"); // ����
	private final JTextField hpzs = new JTextField("0"); // ��Ʒ����
	private final JTextField hjje = new JTextField("0"); // �ϼƽ��
	
	private Date thsjDate;
	private DateFormat thsjDateFormat;


	public BaoJia() {
		super();
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setLayout(new GridBagLayout());
		setTitle("����");
		setBounds(50, 50, 700, 400);
		//setResizable(false);// ���ý����С���ɸ���

		setupComponet(new JLabel("��Ӧ�̣�"), 0, 0, 1, 0, false);
		gys.setPreferredSize(new Dimension(160, 21));
		// ��Ӧ������ѡ����ѡ���¼�
		gys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doGysSelectAction();
			}
		});
		setupComponet(gys, 1, 0, 1, 1, true);

		setupComponet(new JLabel("��ϵ�ˣ�"), 2, 0, 1, 0, false);
		lian.setFocusable(false);
		lian.setPreferredSize(new Dimension(80, 21));
		setupComponet(lian, 3, 0, 1, 0, true);


		setupComponet(new JLabel("����ʱ�䣺"), 0, 1, 1, 0, false);
		jhsj.setFocusable(false);
		setupComponet(jhsj, 1, 1, 1, 1, true);

		setupComponet(new JLabel("������"), 2, 1, 1, 0, false);
		
		setupComponet(shuming, 3, 1, 1, 1, true);
		
		JButton czButton = new JButton("����");
		setupComponet(czButton, 4, 1, 1, 1, false);
		czButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initTable();
			}
		});
		
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		initTable();
		// ����¼����Ʒ����������Ʒ�������ϼƽ��ļ���
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setPreferredSize(new Dimension(380, 200));
		setupComponet(scrollPanel, 0, 2, 6, 1, true);

		setupComponet(new JLabel("���ۣ�"), 0, 3, 1, 0, false);
		setupComponet(pzs, 1, 3, 1, 50, true);

		
		
		setupComponet(new JLabel("������"), 2, 3, 1, 0, false);
		setupComponet(hpzs, 3, 3, 1, 50, true);

		
		
		
		
		// ������Ӱ�ť�ڱ��������µ�һ��
		JButton tjButton = new JButton("����");
		setupComponet(tjButton, 5, 3, 1, 1, false);
		tjButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
			
				if(row == -1 
				|| pzs.getText().toString().trim().equals("")
				|| hpzs.getText().toString().trim().equals("")){
					JOptionPane.showMessageDialog(
							BaoJia.this, "����ʧ��",
							"����ʧ��", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String isbn = table.getValueAt(row, 0).toString().trim();
				String supplyId = ((Item)gys.getSelectedItem()).getId();
				int price = Integer.parseInt(pzs.getText().toString().trim());
				int quantity = Integer.parseInt(hpzs.getText().toString().trim());
				DaoRu.addBj(isbn, supplyId, price, quantity);
				JOptionPane.showMessageDialog(BaoJia.this,
						"���۳ɹ�", "���۳ɹ�", JOptionPane.INFORMATION_MESSAGE);
				initTable();
			}
		});
		
		// ��Ӵ������������ɳ�ʼ��
		addInternalFrameListener(new initTasks());
	}

	// ��ʼ�����
	private void initTable() {

		pzs.setText("0"); 
		hpzs.setText("0");
		hjje.setText("0");
	
		String[] columnNames = { "���", "����", "������", "����", "�������", "��ע"};
		((DefaultTableModel) table.getModel())
				.setColumnIdentifiers(columnNames);
		TableColumn column = table.getColumnModel().getColumn(0);
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		
		
		int num = dftm.getRowCount();
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);
		List spInfos;
		if(shuming.getText().toString().trim() != "")
			spInfos = DaoRu.getSpInfosByName(shuming.getText().toString().trim() );
		else
			spInfos = DaoRu.getSpInfos();
		Iterator iterator = spInfos.iterator();
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
			rowData.add(spInfo.getJc());
			rowData.add(spInfo.getCd());
			rowData.add(spInfo.getNf());
			rowData.add(spInfo.getBz());
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
	}

	

	// ����ĳ�ʼ������
	private final class initTasks extends InternalFrameAdapter {
		public void internalFrameActivated(InternalFrameEvent e) {
			super.internalFrameActivated(e);
			initTimeField();
			initGysField();
		}

		private void initGysField() {// ��ʼ����Ӧ���ֶ�
			List gysInfos = DaoRu.getGysInfos();
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
							thsjDate = new Date();
							jhsj.setText(thsjDateFormat.getDateTimeInstance()
									.format(thsjDate));
							Thread.sleep(1000);
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


}
