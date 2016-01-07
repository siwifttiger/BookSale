package 系统显示.系统管理;

import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class ExitSystem {
	public ExitSystem() {
		int keys = JOptionPane.showConfirmDialog(null,
				"是否退出进销存管理系统？", "退出系统", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (keys == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}