package 系统显示.系统管理;

import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import 系统显示.系统管理.数据字典.AddDictionary;
import 系统显示.系统管理.数据字典.DelDictionary;

public class SjZd extends JInternalFrame {
	public SjZd() {
		setIconifiable(true);
		setClosable(true);
		setTitle("数据字典管理");
		setResizable(false);
		setFocusable(true);
		// setBounds(100, 100, 400, 400);
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.setFocusable(false); // 设置选项卡焦点不可取
		tabPane.setBackground(new Color(211, 230, 192)); // 设置选项卡背景颜色
		tabPane.setBorder(new BevelBorder(BevelBorder.RAISED)); // 设置选项卡边框
		final AddDictionary tjzdPanel = new AddDictionary();
		final DelDictionary sczdPanel = new DelDictionary();

		tabPane.addTab("添加数据字典", null, tjzdPanel, "字典添加");
		tabPane.addTab("删除数据字典", null, sczdPanel, "字典删除");

		tabPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO 自动生成的方法存根
				sczdPanel.initTable();
			}
		});
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				// TODO 自动生成的方法存根
				super.internalFrameActivated(e);
				tjzdPanel.initTimeField();
			}

		});
		getContentPane().add(tabPane);
		pack();
		setVisible(true);

	}

}
