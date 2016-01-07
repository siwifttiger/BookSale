package 登录界面;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DengluPanel extends JPanel{
	protected ImageIcon beiJ;	//声明背景图片的接口
	public int width,height;	//声明界面大小的默认值
	public DengluPanel(){
		super();	//继承JPanel的方法
		beiJ = new ImageIcon("Image/登录界面1.0.jpg");	//创建背景图片图标
		//获得图标的高度和宽度，使界面等于图片的大小
		width = beiJ.getIconWidth();
		height = beiJ.getIconHeight();
		setSize(width, height);
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = beiJ.getImage();
		g.drawImage(img, 0, 0,getParent());
	}
}
