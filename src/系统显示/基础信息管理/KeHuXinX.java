package 系统显示.基础信息管理;

import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import 系统显示.基础信息管理.客户信息管理.KeHuAddPanel;
import 系统显示.基础信息管理.客户信息管理.KeHuAlterPanel;

public class KeHuXinX extends JInternalFrame{
	public KeHuXinX(){
		setTitle("客户信息管理");
		setIconifiable(true);
		setClosable(true);
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.setFocusable(false);				//设置选项卡焦点不可取
		tabPane.setBackground(new Color(211, 230, 192));	//设置选项卡背景颜色
		tabPane.setBorder(new BevelBorder(BevelBorder.RAISED));	//设置选项卡边框
		final KeHuAddPanel khtjPanel = new KeHuAddPanel();
		final KeHuAlterPanel khxgPanel = new KeHuAlterPanel();
		tabPane.addTab("客户信息添加", null, khtjPanel, "客户添加");
		tabPane.addTab("客户信息修改与删除", null, khxgPanel, "修改与删除");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//客户修改
				khxgPanel.initComboBox();
			}
		});
		addInternalFrameListener(new InternalFrameAdapter() {

			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				// TODO 自动生成的方法存根
				super.internalFrameActivated(e);
			}

		});
		pack();
		setVisible(true);
	}
}
