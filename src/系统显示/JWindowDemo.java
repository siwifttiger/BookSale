package 系统显示;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

import 主界面.JieM;

public class JWindowDemo extends JWindow implements Runnable {
	Thread splashThread; // 进度条更新线程
	JProgressBar progress; // 进度条

	public JWindowDemo() {
		Container container = getContentPane(); // 得到容器
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); // 设置光标
		URL url = getClass().getResource("启动界面1.0.gif"); // 图片的位置
		if (url != null) {
			container.add(new JLabel(new ImageIcon(url)), BorderLayout.CENTER); // 增加图片
		}
		progress = new JProgressBar(1, 50); // 实例化进度条
		progress.setStringPainted(true); // 描绘文字
		progress.setString("等一会儿，就好啦......"); // 设置显示文字
		progress.setBackground(Color.white); // 设置背景色
		container.add(progress, BorderLayout.SOUTH); // 增加进度条到容器上

		Dimension screen = getToolkit().getScreenSize(); // 得到屏幕尺寸
		pack(); // 窗口适应组件尺寸
		setLocation((screen.width - getSize().width) / 2,
				(screen.height - getSize().height) / 2); // 设置窗口位置
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		setVisible(true); // 显示窗口
		try {
			for (int i = 0; i < 50; i++) {
				Thread.sleep(50); // 线程休眠
				progress.setValue(progress.getValue() + 1); // 设置进度条值
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		dispose(); // 释放窗口
		new JieM(); // 运行主程序
	}

	public void start() {
		this.toFront(); // 窗口前端显示
		splashThread = new Thread(this); // 实例化线程
		splashThread.start(); // 开始运行线程
	}

}
