package 登录界面;
import java.awt.Color;
//缺少提示功能
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import 主界面.JieM;
import 数据库模型.TbUserlist;
import 系统显示.JWindowDemo;
import 系统显示.数据库信息.DaoRu;

public class Denglu extends JFrame{
	private JLabel userLabel;	//定义用户标签
	private JLabel passLabel;	//定义密码标签
	private JButton enter;		//定义登录按钮
	private JButton exit;		//定义退出按钮
	private static TbUserlist user;
	public Denglu(){
		setTitle("图书销售管理系统");	//设置窗体标题
		Toolkit toolkit=Toolkit.getDefaultToolkit();	//获得Toolkit实例
		Dimension screenSize =toolkit.getScreenSize();	//获得显示器大小
		final JPanel dlPanel = new DengluPanel();	//声明终态的容器，不允许修改
		int x=(screenSize.width-dlPanel.getWidth())/2;		//计算左上角横坐标
		int y=(screenSize.height-dlPanel.getHeight())/2;	//计算左上角纵坐标

		dlPanel.setLayout(null);	//设置为空布局
		getContentPane().add(dlPanel);				//将该容器添加到顶级容器里面
		setBounds(x, y, dlPanel.getWidth(), dlPanel.getHeight());	//设置登录界面位置以及大小
		//构造组件
		userLabel = new JLabel();
		passLabel = new JLabel();
		//设置标签文本
		userLabel.setText("用户名：");
		passLabel.setText("密  码：");
		userLabel.setForeground(Color.WHITE);
		passLabel.setForeground(Color.WHITE);
		//声明终态的文本，不允许修改
		final JTextField userName = new JTextField();
		final JPasswordField userPassword = new JPasswordField();	//密码文本，不显示内容，默认禁止使用输入法
		//设置标签的位置以及大小
		userLabel.setBounds(300, 100, 300, 50);		//用户标签
		passLabel.setBounds(300, 150, 300, 50);		//密码标签
		userName.setBounds(350, 100, 250, 30);		//用户文本
		userPassword.setBounds(350, 160, 250, 30);	//密码文本
		//把标签添加到中间容器中
		dlPanel.add(userLabel);	//用户标签
		dlPanel.add(passLabel);	//密码标签
		//把文本添加到中间容器中
		dlPanel.add(userName);		//用户文本
		dlPanel.add(userPassword);	//密码文本
		//构造按钮
		enter = new JButton();
		exit = new JButton();
		//为按钮设置文本
		enter.setText("登录");
		exit.setText("退出");
		//为按钮设置位置
		enter.setBounds(380, 250, 70, 40);
		exit.setBounds(500, 250, 70, 40);
		//监听器*******
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				user = DaoRu.getUser(userName.getText(),new String(userPassword.getPassword()));
				if (user.getUsername() == null || user.getName() == null) {
					userName.setText(null);
					userPassword.setText(null);
					return;
				}
				setVisible(false);
				new JWindowDemo().start();
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		});
		//把按钮添加到中间容器中
		dlPanel.add(enter);
		dlPanel.add(exit);

		//设置界面方法
		toFront();	//窗口前端显示
		setUndecorated(true);	//设置标题栏不可显示
		setVisible(true);	//设置界面可见
		setResizable(false);//设置界面大小不可更改
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);	//设置关闭按钮无操作
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void setClosable(boolean b) {
		// TODO Auto-generated method stub

	}
	public static TbUserlist getUser() {
		return user;
	}
	public static void setUser(TbUserlist user) {
		Denglu.user = user;
	}
}
