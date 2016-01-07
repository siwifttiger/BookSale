package ϵͳ��ʾ;

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

import ������.JieM;

public class JWindowDemo extends JWindow implements Runnable {
	Thread splashThread; // �����������߳�
	JProgressBar progress; // ������

	public JWindowDemo() {
		Container container = getContentPane(); // �õ�����
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); // ���ù��
		URL url = getClass().getResource("��������1.0.gif"); // ͼƬ��λ��
		if (url != null) {
			container.add(new JLabel(new ImageIcon(url)), BorderLayout.CENTER); // ����ͼƬ
		}
		progress = new JProgressBar(1, 50); // ʵ����������
		progress.setStringPainted(true); // �������
		progress.setString("��һ������ͺ���......"); // ������ʾ����
		progress.setBackground(Color.white); // ���ñ���ɫ
		container.add(progress, BorderLayout.SOUTH); // ���ӽ�������������

		Dimension screen = getToolkit().getScreenSize(); // �õ���Ļ�ߴ�
		pack(); // ������Ӧ����ߴ�
		setLocation((screen.width - getSize().width) / 2,
				(screen.height - getSize().height) / 2); // ���ô���λ��
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		setVisible(true); // ��ʾ����
		try {
			for (int i = 0; i < 50; i++) {
				Thread.sleep(50); // �߳�����
				progress.setValue(progress.getValue() + 1); // ���ý�����ֵ
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		dispose(); // �ͷŴ���
		new JieM(); // ����������
	}

	public void start() {
		this.toFront(); // ����ǰ����ʾ
		splashThread = new Thread(this); // ʵ�����߳�
		splashThread.start(); // ��ʼ�����߳�
	}

}
