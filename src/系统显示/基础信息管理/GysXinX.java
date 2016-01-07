package 系统显示.基础信息管理;

import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import 系统显示.基础信息管理.供应商信息管理.GysAddPanel;
import 系统显示.基础信息管理.供应商信息管理.GysAlterPanel;

public class GysXinX extends JInternalFrame{
	public GysXinX(){
		setIconifiable(true);
		setClosable(true);
		setTitle("供应商信息管理");
		setResizable(false);//设置界面大小不可更改
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.setFocusable(false);				//设置选项卡焦点不可取不可取
		tabPane.setBackground(new Color(211, 230, 192));	//设置选项卡背景颜色
		tabPane.setBorder(new BevelBorder(BevelBorder.RAISED));	//设置选项卡边框
		final GysAddPanel gystjPanel = new GysAddPanel();
		final GysAlterPanel gysxgPanel = new GysAlterPanel();
		tabPane.addTab("供应商信息添加", null, gystjPanel, "供应商添加");
		tabPane.addTab("供应商信息修改与删除", null, gysxgPanel, "修改与删除");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				gysxgPanel.initComboBox();
			}
		});
		pack();
		//this.setBounds(300, 300, 600, 400);
		setVisible(true);
	}
}
