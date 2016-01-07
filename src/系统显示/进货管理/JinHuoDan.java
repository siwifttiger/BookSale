package 系统显示.进货管理;

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

import 数据库模型.TbGysinfo;
import 数据库模型.TbRukuDetail;
import 数据库模型.TbRukuMain;
import 数据库模型.TbSpinfo;
import 数据库模型.TbUserlist;
import 登录界面.Denglu;
import 系统显示.数据库信息.DaoRu;
import 系统显示.管理.Item;

public class JinHuoDan extends JInternalFrame {
	private final JTable table;
	private TbUserlist user = Denglu.getUser(); // 登录用户信息
	private final JTextField jhsj = new JTextField(); // 进货时间
	private final JTextField lian = new JTextField(); // 联系人
	private final JComboBox gys = new JComboBox(); // 书籍
	private final JTextField pzs = new JTextField("0"); // 单价
	private final JTextField hpzs = new JTextField("0");// 货品总数
	private final JTextField hjje = new JTextField("0");// 合计金额
	private final JTextField czy = new JTextField(user.getName());// 操作员
	private Date jhsjDate;
	private DateFormat jhsjDateFormat;

	public JinHuoDan() {
		super();
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setLayout(new GridBagLayout());
		setTitle("进货单");
		setBounds(100, 100, 800, 400);
		//setResizable(false);// 设置界面大小不可更改


		setupComponet(new JLabel("书名："), 0, 0, 1, 0, false);
		gys.setPreferredSize(new Dimension(60, 21));
		// 供应商下拉选择框的选择事件
		gys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doGysSelectAction();
			}
		});
		setupComponet(gys, 1, 0, 1, 1, true);

		

		setupComponet(new JLabel("进货时间："), 0, 1, 1, 0, false);
		jhsj.setFocusable(false);
		setupComponet(jhsj, 1, 1, 1, 1, true);


		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		initTable();
		// 添加事件完成品种数量、货品总数、合计金额的计算
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setPreferredSize(new Dimension(380, 200));
		setupComponet(scrollPanel, 0, 2, 6, 1, true);

	

		setupComponet(new JLabel("数量："), 2, 3, 1, 0, false);
		
		setupComponet(hpzs, 3, 3, 1, 50, true);



		// 单击入库按钮保存进货信息
		JButton rkButton = new JButton("入库");
		setupComponet(rkButton, 4, 3, 1, 1, false);
		rkButton.addActionListener(new RkActionListener());
		// 添加窗体监听器，完成初始化
		addInternalFrameListener(new initTasks());
	}

	// 初始化表格
	private void initTable() {
		String[] columnNames = { "编号","单位名称", "数量", "单价", "地址", "电话","联系人", "联系电话", "邮件地址", 
				"备注" };
		((DefaultTableModel) table.getModel())
				.setColumnIdentifiers(columnNames);// 设置表格的表头

		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		
		
		int num = dftm.getRowCount();
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);
	
	}

	// 初始化商品下拉选择框
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

	// 供应商选择时更新联系人字段
	private void doGysSelectAction() {
		Item item = (Item) gys.getSelectedItem();
		TbGysinfo gysInfo = DaoRu.getGysInfo(item);
		lian.setText(gysInfo.getLian());
		initSpBox();
	}

	class RkActionListener implements ActionListener { // 入库按钮的事件监听器
		public void actionPerformed(ActionEvent e) {
			int row = table.getSelectedRow();
			
			if(row == -1 
			|| hpzs.getText().toString().trim().equals("")){
				JOptionPane.showMessageDialog(
						JinHuoDan.this, "进货失败",
						"进货失败", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			
			int quantity = Integer.parseInt(hpzs.getText()); // 货品总数
			int price = Integer.parseInt(table.getValueAt(row, 3).toString().trim());
			String isbn = ((Item)gys.getSelectedItem()).getId();
			String id = table.getValueAt(row, 0).toString().trim();
			String rkDate = jhsjDateFormat.getDateTimeInstance().format(
					jhsjDate); // 入库时间
			
			TbRukuMain ruMain = new TbRukuMain("1", quantity+"", id, price+"",
					isbn, rkDate); // 创建入库主表实体对象
			boolean rs = DaoRu.insertRukuInfo(ruMain);
			JOptionPane.showMessageDialog(
					JinHuoDan.this, "进货成功",
					"进货成功", JOptionPane.INFORMATION_MESSAGE);
		}
	}


	// 在事件中计算品种数量、货品总数、合计金额
	

	// 窗体的初始化任务
	private final class initTasks extends InternalFrameAdapter {
		public void internalFrameActivated(InternalFrameEvent e) {
			super.internalFrameActivated(e);
			initTimeField();
			initGysField();
			initSpBox();
		}

		private void initGysField() {// 初始化商品字段
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

		private void initTimeField() {// 启动进货时间线程
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

	

	// 根据商品下拉框的选择，更新表格当前行的内容
	private synchronized void updateTable() {
	}

	
	// 停止表格单元的编辑
	private void stopTableCellEditing() {
		TableCellEditor cellEditor = table.getCellEditor();
		if (cellEditor != null)
			cellEditor.stopCellEditing();
	}
}
