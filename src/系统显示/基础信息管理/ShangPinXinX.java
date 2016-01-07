package 系统显示.基础信息管理;

import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import 系统显示.基础信息管理.商品信息管理.ShangPinAddPanel;
import 系统显示.基础信息管理.商品信息管理.ShangPinAlterPanel;

public class ShangPinXinX extends JInternalFrame{
	public ShangPinXinX(){
		setIconifiable(true);
		setClosable(true);
		setTitle("商品管理");
		//setResizable(false);//设置界面大小不可更改
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.setFocusable(false);				//设置选项卡焦点不可取不可取
		tabPane.setBackground(new Color(211, 230, 192));	//设置选项卡背景颜色
		tabPane.setBorder(new BevelBorder(BevelBorder.RAISED));	//设置选项卡边框
		final ShangPinAddPanel sptjPanel = new ShangPinAddPanel();
		final ShangPinAlterPanel spxgPanel = new ShangPinAlterPanel();
		tabPane.addTab("商品信息添加", null, sptjPanel, "商品添加");
		tabPane.addTab("商品信息修改与删除", null, spxgPanel, "修改与删除");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spxgPanel.initComboBox();
			}
		});
		pack();
		setVisible(true);
	}
}
