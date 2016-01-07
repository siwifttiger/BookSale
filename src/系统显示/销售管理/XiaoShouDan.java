package 系统显示.销售管理;

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
import 数据库模型.TbKhXinX;
import 数据库模型.TbKucun;
import 数据库模型.TbSellDetail;
import 数据库模型.TbSellMain;
import 数据库模型.TbSpinfo;
import 数据库模型.TbUserlist;
import 登录界面.Denglu;
import 系统显示.数据库信息.DaoRu;
import 系统显示.管理.Item;
import 系统显示.报价管理.BaoJia;


public class XiaoShouDan extends JInternalFrame {
	private final JTable table;
	private TbUserlist user = Denglu.getUser(); // 登录用户信息
	private final JTextField jhsj = new JTextField(); // 退货时间
	private final JTextField lian = new JTextField(); // 联系人
	private final JComboBox gys = new JComboBox(); // 供应商
	
	private final JTextField pzs = new JTextField("0"); // 单价
	private final JTextField hpzs = new JTextField("0"); // 货品总数
	private final JTextField shuming = new JTextField(); //书名搜索
	private Date xssjDate;
	private DateFormat xssjDateFormat;

	public XiaoShouDan() {
		super();
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setLayout(new GridBagLayout());
		setTitle("销售");
		setBounds(50, 50, 700, 400);
		//setResizable(false);// 设置界面大小不可更改

		setupComponet(new JLabel("收购人："), 0, 0, 1, 0, false);
		gys.setPreferredSize(new Dimension(160, 21));
		// 供应商下拉选择框的选择事件
		gys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doGysSelectAction();
			}
		});
		setupComponet(gys, 1, 0, 1, 1, true);

		setupComponet(new JLabel("联系人："), 2, 0, 1, 0, false);
		lian.setFocusable(false);
		lian.setPreferredSize(new Dimension(80, 21));
		setupComponet(lian, 3, 0, 1, 0, true);


		setupComponet(new JLabel("销售时间："), 0, 1, 1, 0, false);
		jhsj.setFocusable(false);
		setupComponet(jhsj, 1, 1, 1, 1, true);

		setupComponet(new JLabel("书名："), 2, 1, 1, 0, false);
		//shuming.setFocusable(false);
		setupComponet(shuming, 3, 1, 1, 1, true);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		initTable();
		// 添加事件完成品种数量、货品总数、合计金额的计算
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setPreferredSize(new Dimension(380, 200));
		setupComponet(scrollPanel, 0, 2, 6, 1, true);

		setupComponet(new JLabel("单价："), 0, 3, 1, 0, false);
		//pzs.setFocusable(false);
		setupComponet(pzs, 1, 3, 1, 50, true);

		setupComponet(new JLabel("数量："), 2, 3, 1, 0, false);
		//hpzs.setFocusable(false);
		setupComponet(hpzs, 3, 3, 1, 50, true);

		
		
		// 单击添加按钮在表格中添加新的一行
		JButton tjButton = new JButton("销售");
		setupComponet(tjButton, 5, 3, 1, 1, false);
		tjButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				if(row == -1 
				|| pzs.getText().toString().trim().equals("")
				|| hpzs.getText().toString().trim().equals("")
				|| Integer.parseInt(hpzs.getText()) > Integer.parseInt(table.getValueAt(row, 5).toString() )){
					JOptionPane.showMessageDialog(
							XiaoShouDan.this, "报价失败",
							"报价失败", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String isbn = table.getValueAt(row, 0).toString().trim();
				String supplyId = ((Item)gys.getSelectedItem()).getId();
				int price = Integer.parseInt(pzs.getText().toString().trim());
				int quantity = Integer.parseInt(hpzs.getText().toString().trim());
				String rkDate = xssjDateFormat.getDateTimeInstance().format(
						xssjDate); // 销售时间
				
				TbSellMain sellMain = new TbSellMain("1", price+"", quantity+"",
						isbn, supplyId, rkDate);
				boolean rs = DaoRu.insertSellInfo(sellMain);
				if (rs) {
					JOptionPane.showMessageDialog(XiaoShouDan.this, "销售完成");
					DefaultTableModel dftm = new DefaultTableModel();
					table.setModel(dftm);
					initTable();
					pzs.setText("0");
					hpzs.setText("0");
				}
			}
		});
		
		// 添加窗体监听器，完成初始化
		addInternalFrameListener(new initTasks());
	}

	// 初始化表格
	private void initTable() {
		String[] columnNames = { "书号", "书名", "出版社", "作者", "出版年份","库存", "备注"};
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
			rowData.add(spInfo.getCd().trim());
			rowData.add(spInfo.getJc().trim());
			rowData.add(spInfo.getNf().trim());

			
			List kuInfos = DaoRu.getKcInfos(spInfo.getPh().trim());
			Iterator tempIterator = kuInfos.iterator();
			if(tempIterator.hasNext()){
				List tempInfo = (List) tempIterator.next();
				rowData.add( (String)tempInfo.get(0) );
			}
			else
				rowData.add("0");
			
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

	// 供应商选择时更新联系人字段
	private void doGysSelectAction() {
		Item item = (Item) gys.getSelectedItem();
		TbKhXinX khInfo = DaoRu.getKhInfo(item);
		lian.setText(khInfo.getLinkman());
	}

	

	// 窗体的初始化任务
	private final class initTasks extends InternalFrameAdapter {
		public void internalFrameActivated(InternalFrameEvent e) {
			super.internalFrameActivated(e);
			initTimeField();
			initGysField();
		}

		private void initGysField() {// 初始化供应商字段
			List khInfos = DaoRu.getKhInfos();
			for (Iterator iter = khInfos.iterator(); iter.hasNext();) {
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
							xssjDate = new Date();
							jhsj.setText(xssjDateFormat.getDateTimeInstance()
									.format(xssjDate));
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


}
