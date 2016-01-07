package 主界面;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import 数据库模型.TbUserlist;
import 登录界面.Denglu;
import 系统显示.管理.WindowUtil;
import 系统显示.系统管理.ExitSystem;

public class JieM {
	private JDesktopPane desktopPane;
	private JLabel backLabel; // 背景标签
	private JFrame frame;
	private TbUserlist user = Denglu.getUser();
	// 添加容器

	// 声明按钮

	
	private JButton baojia;
	private JButton khG;
	private JButton spG;
	private JButton gyG;
	private JButton jt;
	private JButton jhD;
	private JButton xsT;
	private JButton xsD;
	private JButton kcP;
	//private JButton kcjP;
	private JButton czG;
	private JButton qxG;
	private JButton ggM;
	private JButton tcX;
	private JButton khC;
	private JButton spC;
	private JButton gyC;
	private JButton xsC;
	private JButton xsTC;
	private JButton rkC;
	//private JButton rktC;
	private JButton xsP;
	private JButton zdC;
	private JButton zdG;
	private Map<String, JInternalFrame> ifs = new HashMap<String, JInternalFrame>();

	public JieM() {
		frame = new JFrame("进销存管理系统");
		WindowUtil windowsuUtil = new WindowUtil();
		int x = windowsuUtil.getLocation().x;
		int y = windowsuUtil.getLocation().y;
		frame.getContentPane().setBackground(new Color(170, 188, 120)); // 设置背景色
		frame.addComponentListener(new FrameListener()); // 添加窗体事件监听器
		frame.getContentPane().setLayout(new BorderLayout()); // 设置布局管理器
		frame.setBounds(x, y, 1150, 700); // 设置窗体位置
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		backLabel = new JLabel(); // 背景标签
		backLabel.setVerticalAlignment(SwingConstants.TOP); // 设置背景标签垂直对齐方式
		backLabel.setHorizontalAlignment(SwingConstants.CENTER);// 设置背景标签水平对齐方式，居中
		updateBackImage(); // 更新或初始化背景图片
		desktopPane = new JDesktopPane(); // 创建桌面面板
		desktopPane.add(backLabel, new Integer(Integer.MIN_VALUE)); // 将背景标签添加到桌面面板中
		frame.getContentPane().add(desktopPane); // 添加桌面面板到窗体中
		JTabbedPane navigationPanel = createNavigationPanel(); // 创建导航标签面板
		frame.getContentPane().add(navigationPanel, BorderLayout.NORTH);// 添加导航面板到窗体中
		frame.setVisible(true); // 显示窗体

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Denglu();
			}
		});
	}

	public JTabbedPane createNavigationPanel() {
		TbUserlist user = Denglu.getUser();
		// 创建一个标签栏容器
		JTabbedPane jtp = new JTabbedPane();
		jtp.setFocusable(false); // 设置选项卡焦点不可取不可取
		jtp.setBackground(new Color(211, 230, 192)); // 设置选项卡背景颜色
		jtp.setBorder(new BevelBorder(BevelBorder.RAISED)); // 设置选项卡边框

		//报价管理按钮
		baojia = new JButton("报价");
		
		// 基础信息管理按钮
		khG = new JButton("客户信息管理");
		spG = new JButton("商品信息管理");
		gyG = new JButton("供应商信息管理");

		// 进货管理按钮
		//jt = new JButton("进货/退货");
		jhD = new JButton("进货单");
		// 销售管理
		xsT = new JButton("销售退货");
		xsD = new JButton("销售单");
		// 库存管理
		kcP = new JButton("库存盘点");
		//kcjP = new JButton("库存价格盘点");
		// 系统管理
		czG = new JButton("操作员管理");
		qxG = new JButton("权限管理");
		zdG = new JButton("数据字典管理");
		ggM = new JButton("更改密码");
		tcX = new JButton("");
		// 查询统计
		khC = new JButton("客户查询");
		spC = new JButton("商品查询");
		gyC = new JButton("供应商查询");
		xsC = new JButton("销售查询");
		xsTC = new JButton("销售退货查询");
		rkC = new JButton("入库查询");
		//rktC = new JButton("入库退货查询");
		xsP = new JButton("销售排行");
		//zdC = new JButton("数据字典查询");
		JPanel bj = new JPanel();
		bj.setBackground(new Color(215, 223, 194));
		bj.setLayout(new BoxLayout(bj, BoxLayout.X_AXIS));
		bj.add(createFrameButton("报价管理", "报价管理.BaoJia",baojia));
		
		JPanel jc = new JPanel();
		jc.setBackground(new Color(215, 223, 194)); // 设置背景颜色
		jc.setLayout(new BoxLayout(jc, BoxLayout.X_AXIS)); // 设置布局，并左对齐
		jc.add(createFrameButton("客户信息管理", "基础信息管理.KeHuXinX", khG));
		jc.add(createFrameButton("商品信息管理", "基础信息管理.ShangPinXinX", spG));
		jc.add(createFrameButton("供应商信息管理", "基础信息管理.GysXinX", gyG));

		JPanel jh = new JPanel();
		jh.setBackground(new Color(215, 223, 194)); // 设置背景颜色
		jh.setLayout(new BoxLayout(jh, BoxLayout.X_AXIS)); // 设置布局，并左对齐
		jh.add(createFrameButton("进货单", "进货管理.JinHuoDan", jhD));
		//jh.add(createFrameButton("进货退货", "进货管理.JinHuoTuiHuo", jt));

		JPanel xs = new JPanel();
		xs.setBackground(new Color(215, 223, 194));
		xs.setLayout(new BoxLayout(xs, BoxLayout.X_AXIS));
		xs.add(createFrameButton("销售单", "销售管理.XiaoShouDan", xsD));
		xs.add(createFrameButton("销售退货", "销售管理.XiaoShouTuiHuo", xsT));

		JPanel kc = new JPanel();
		kc.setBackground(new Color(215, 223, 194));
		kc.setLayout(new BoxLayout(kc, BoxLayout.X_AXIS));
		kc.add(createFrameButton("库存盘点", "库存管理.KuCunPanDian", kcP));
		//kc.add(createFrameButton("价格调整", "库存管理.JiaGeTiaoZheng", kcjP));

		JPanel xt = new JPanel();
		xt.setBackground(new Color(215, 223, 194));
		xt.setLayout(new BoxLayout(xt, BoxLayout.X_AXIS));

		xt.add(createFrameButton("更改密码", "系统管理.GengGaiMiMa", ggM));
		xt.add(createFrameButton("退出系统", "系统管理.ExitSystem", tcX));
		tcX.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				new ExitSystem();
			}
		});

		// String q =user.getQuan();
		// int r =q.compareTo("a");
		// if( r == 0 ) //如果登录的用户是管理员身份，则添加操作员管理和权限管理功能。
		// 问题：直接用if( user.getQuan()=="a" ) 判断不行，暂未找到原因。
		// {

		// }
		JPanel cx = new JPanel();
		cx.setBounds(0, 0, 600, 41);
		cx.setName("searchStatisticPanel");
		cx.setBackground(new Color(215, 223, 194));
		cx.setLayout(new BoxLayout(cx, BoxLayout.X_AXIS));
		cx.add(createFrameButton("客户信息查询", "查询统计.KeHuChaXun", khC));
		cx.add(createFrameButton("商品信息查询", "查询统计.ShangPinChaXun", spC));
		cx.add(createFrameButton("供应商信息查询", "查询统计.GongYingShangChaXun", gyC));
		cx.add(createFrameButton("销售信息查询", "查询统计.XiaoShouChaXun", xsC));
		cx.add(createFrameButton("销售退货查询", "查询统计.XiaoShouTuiHuoChaXun", xsTC));
		cx.add(createFrameButton("入库查询", "查询统计.RuKuChaXun", rkC));

		cx.add(createFrameButton("销售排行", "查询统计.XiaoShouPaiHang", xsP));


		// 把组件容器放入标签栏容器里面
		jtp.addTab("   基础信息管理   ", null, jc, "基础信息管理");
		jtp.addTab("   进货管理   ", null, jh, "进货管理");
		jtp.addTab("   销售管理   ", null, xs, "销售管理");
		jtp.addTab("   查询统计   ", null, cx, "查询统计");
		jtp.addTab("   库存管理   ", null, kc, "库存管理");
		jtp.addTab("   报价管理   ", null, bj, "报价管理");
		jtp.addTab("   系统管理   ", null, xt, "系统管理");
		return jtp;
	}

	/** *********************辅助方法************************* */
	// 为内部窗体添加Action的方法
	private JButton createFrameButton(String fName, String cname, JButton button) {
		String imgUrl = "Image/ActionIcon/" + fName + ".png";
		String imgUrl_roll = "Image/ActionIcon/" + fName + "_roll.png";
		String imgUrl_down = "Image/ActionIcon/" + fName + "_down.png";
		Icon icon = new ImageIcon(imgUrl); // 创建按钮图标
		Icon icon_roll = null;
		if (imgUrl_roll != null)
			icon_roll = new ImageIcon(imgUrl_roll); // 创建鼠标放在按钮上时的图标
		Icon icon_down = null;
		if (imgUrl_down != null)
			icon_down = new ImageIcon(imgUrl_down); // 创建按钮按下的图标
		Action action = new openFrameAction(fName, cname, icon); // 用openFrameAction
																	// 类创建Action对象
		button.setAction(action);
		button.setMargin(new Insets(2, 2, 2, 2)); // 设置按钮的四周边界为0
		//button.setHideActionText(true); // 隐藏按钮的 文本信息
		button.setFocusPainted(false);
		button.setBorderPainted(true);
		button.setContentAreaFilled(true);
		//if (icon_roll != null)
			//button.setRolloverIcon(icon_roll);
		//if (icon_down != null)
		//	button.setPressedIcon(icon_down);
		return button;
	}

	// 退出系统图标方法
	private JButton createSysExit(String fName, String cname, JButton button) {
		String imgUrl = "Image/ActionIcon/" + fName + ".png";
		String imgUrl_roll = "Image/ActionIcon/" + fName + "_roll.png";
		String imgUrl_down = "Image/ActionIcon/" + fName + "_down.png";
		Icon icon = new ImageIcon(imgUrl); // 创建按钮图标
		Icon icon_roll = null;
		if (imgUrl_roll != null)
			icon_roll = new ImageIcon(imgUrl_roll); // 创建鼠标放在按钮上时的图标
		Icon icon_down = null;
		if (imgUrl_down != null)
			icon_down = new ImageIcon(imgUrl_down); // 创建按钮按下的图标

		button.setMargin(new Insets(0, 0, 0, 0)); // 设置按钮的四周边界为0
		//button.setHideActionText(false); // 隐藏按钮的 文本信息
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(true);
		//button.setIcon(icon);
		//if (icon_roll != null)
		//	button.setRolloverIcon(icon_roll);
		//if (icon_down != null)
			//button.setPressedIcon(icon_down);
		return button;
	}

	// 获取内部窗体的唯一实例对象
	private JInternalFrame getIFrame(String frameName) {
		JInternalFrame jf = null;
		if (!ifs.containsKey(frameName)) {
			try {
				Class fClass = Class.forName("系统显示." + frameName);
				Constructor constructor = fClass.getConstructor(null);
				jf = (JInternalFrame) constructor.newInstance(null);
				ifs.put(frameName, jf);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			jf = ifs.get(frameName);
		return jf;
	}

	// 更新背景标签的方法，背景标签使用HTML语言设置主窗体的背景图片，该图片将随主窗体的大小自动缩放。
	private void updateBackImage() {
		if (backLabel != null) {
			int backw = frame.getWidth();
			int backh = frame.getHeight();
			backLabel.setSize(backw, backh); // 初始化背景标签的大小
			backLabel.setText("<html><body><image width='" + (backw)
					+ "' height='" + (backh - 110) + "' src="
					+ JieM.this.getClass().getResource("主界面1.0.jpg")
					+ "'></img></body></html>"); // 设置背景标签的图像
		}
	}

	// 窗体监听器
	private final class FrameListener extends ComponentAdapter {
		public void componentResized(final ComponentEvent e) {
			updateBackImage();
		}
	}

	// 主窗体菜单项的单击事件监听器
	protected final class openFrameAction extends AbstractAction {
		private String frameName = null;

		private openFrameAction() {
		}

		public openFrameAction(String cname, String frameName, Icon icon) {
			this.frameName = frameName;
			putValue(Action.NAME, cname); // 设置Action的名称
			putValue(Action.SHORT_DESCRIPTION, cname); // 设置Action的提示文本框
			//putValue(Action.SMALL_ICON, icon); // 设置Action的图标
		}

		public void actionPerformed(final ActionEvent e) {
			JInternalFrame jf = getIFrame(frameName); // 调用getFrame()方法获取相应的窗体对象
			// 在内部窗体闭关时，从内部窗体容器ifs对象中清除该窗体。
			jf.addInternalFrameListener(new InternalFrameAdapter() {
				public void internalFrameClosed(InternalFrameEvent e) {
					ifs.remove(frameName);
				}
			});
			if (jf.getDesktopPane() == null) {
				desktopPane.add(jf); // 将窗体添加到主窗体中
				jf.setVisible(true); // 显示窗体
			}
			try {
				jf.setSelected(true); // 使窗体处于被选择状态
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
	}

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());// 将外观设置为系统外观
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
