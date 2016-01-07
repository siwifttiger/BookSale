package 系统显示.管理;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JOptionPane;
//窗体居中显示
public class WindowUtil {
	//将窗体大小设置成500x309像素
	public static Dimension getSize(){
		return new Dimension(1150,700);
	}
	//计算窗体居中显示时左上角的坐标
	public static Point getLocation(){
		Toolkit toolkit=Toolkit.getDefaultToolkit();	//获得Toolkit实例
		Dimension screenSize =toolkit.getScreenSize();	//获得显示器大小
		if((screenSize.width<getSize().width)||(screenSize.height<getSize().height)){
			JOptionPane.showMessageDialog(null, "显示器分辨率至少为1150 x 700","",JOptionPane.WARNING_MESSAGE);
			System.exit(0);	//终止程序
		}
		int x=(screenSize.width-getSize().width)/2;		//计算左上角横坐标
		int y=(screenSize.height-getSize().height)/2;	//计算左上角纵坐标
		return new Point(x, y);
	}
}
